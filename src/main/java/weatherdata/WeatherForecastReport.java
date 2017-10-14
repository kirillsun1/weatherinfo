package weatherdata;

import OpenWeatherStructures.Forecast5Days3HoursStructure;
import city.City;
import city.Coordinates;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import exceptions.IncorrectAPIOutputException;
import utility.Constants;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Forecast data.
 */
public class WeatherForecastReport {
    private static final int DAYS_IN_FORECAST = 3;

    private City city;
    private List<ForecastOneDayWeather> oneDayWeathers;
    private Constants.TemperatureUnits temperatureUnit;

    /**
     * Private constructor.
     * To create object, use fabric method parseFromJSON(String)
     */
    private WeatherForecastReport() {
        oneDayWeathers = new ArrayList<>();
    }

    public String getCityName() {
        return city.getCityName();
    }

    public Coordinates getCoordinates() {
        return city.getCoordinates();
    }

    public Constants.TemperatureUnits getTemperatureUnit() {
        return temperatureUnit;
    }

    public String getCountryCode() {
        return city.getCountryCode();
    }

    public static WeatherForecastReport getFromJSON(String jsonFile) throws IncorrectAPIOutputException {
        Gson gson = new GsonBuilder().create();
        Forecast5Days3HoursStructure apiForecast = gson.fromJson(jsonFile, Forecast5Days3HoursStructure.class);
        WeatherForecastReport report = new WeatherForecastReport();

        String cityName = apiForecast.getCity().getCityName();
        double longtitude = apiForecast.getCity().getCoordinates().get("lon");
        double latitude = apiForecast.getCity().getCoordinates().get("lat");
        String countryCode = apiForecast.getCity().getCountryCode();

        report.city = new City(cityName, Coordinates.of(longtitude, latitude), countryCode);

        LocalDate currentDay = null;
        ForecastOneDayWeather currentDayWeather = null;
        for (HashMap<String, Object> listHashMap : apiForecast.getList()) {
            String dateTimeString = (String) listHashMap.get("dt_txt");
            LocalDate day = LocalDate.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            if (currentDay == null || Math.abs(ChronoUnit.DAYS.between(day, currentDay)) >= 1) {
                currentDay = day;
                if (currentDayWeather != null) {
                    report.oneDayWeathers.add(currentDayWeather);
                    System.out.println(currentDayWeather);
                    if (report.oneDayWeathers.size() >= DAYS_IN_FORECAST) {
                        break;
                    }
                }
                currentDayWeather = new ForecastOneDayWeather();
                currentDayWeather.currentDateTime = currentDay;
            }

            LinkedTreeMap<String, Double> mainMap = (LinkedTreeMap<String, Double>) listHashMap.get("main");
            // double currentTemperature = mainMap.get("temp");
            double minimumTemperature = mainMap.get("temp_min");
            double maximumTemperature = mainMap.get("temp_max");

            if (minimumTemperature < currentDayWeather.minimumTemperature) {
                currentDayWeather.minimumTemperature = minimumTemperature;
            }

            if (maximumTemperature > currentDayWeather.maximumTemperature) {
                currentDayWeather.maximumTemperature = maximumTemperature;
            }
        }

        return report;
    }

    static class ForecastOneDayWeather {
        double minimumTemperature = Integer.MAX_VALUE;
        double maximumTemperature = Integer.MIN_VALUE;
        // double currentTemperature = Integer.MIN_VALUE;
        LocalDate currentDateTime;

        @Override
        public String toString() {
            return String.format("[Day: %s] [Min temp: %f] [Max temp: %f]", currentDateTime,
                    minimumTemperature, maximumTemperature);
        }
    }
}