package weatherdata;

import openweatherobjects.Forecast5Days3HoursData;
import city.City;
import city.Coordinates;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import exceptions.IncorrectAPIOutputException;
import utility.Utils;
import weatherrequest.WeatherRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WeatherForecastReportFabric {
    private static ArrayList<ForecastOneDayWeather> getOneDayWeathersList(Forecast5Days3HoursData structureObject) {
        ArrayList<ForecastOneDayWeather> oneDayWeatherList = new ArrayList<>();
        LocalDate currentDay = null;
        LocalDate todayDate = LocalDate.now();
        ForecastOneDayWeather currentDayWeather = null;
        for (HashMap<String, Object> listHashMap : structureObject.list) {
            String dateTimeString = (String) listHashMap.get("dt_txt");
            LocalDate day = LocalDate.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            // TODO: ask if today date should be included.
            if (Math.abs(ChronoUnit.DAYS.between(day, todayDate)) < 1) {
                continue;
            }
            if (currentDay == null || Math.abs(ChronoUnit.DAYS.between(day, currentDay)) >= 1) {
                currentDay = day;
                if (currentDayWeather != null) {
                    oneDayWeatherList.add(currentDayWeather);
                    // System.out.println(currentDayWeather);
                    if (oneDayWeatherList.size() >= WeatherForecastReport.DAYS_IN_FORECAST) {
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

        return oneDayWeatherList;
    }

    private static String getCityNameFromAPIStructureObject(Forecast5Days3HoursData structureObject)
            throws IncorrectAPIOutputException {
        String cityName = structureObject.city.name;

        if (cityName == null) {
            throw new IncorrectAPIOutputException("Incorrect city name!");
        }

        return cityName;
    }

    private static double[] getCoordinatesFromAPIStructureObject(Forecast5Days3HoursData structureObject) {
        return new double[]{
                structureObject.city.coordinates.get("lon"),
                structureObject.city.coordinates.get("lat")
        };
    }

    private static String getCountryCodeFromAPIStructureObject(Forecast5Days3HoursData structureObject)
            throws IncorrectAPIOutputException {
        String countryCode = structureObject.city.country;

        if (countryCode == null && !Utils.isCountryCodeCorrect(countryCode)) {
            throw new IncorrectAPIOutputException("Incorrect country code!");
        }

        return countryCode;
    }

    public static WeatherForecastReport createReportFromJSONAndRequest(String jsonFile, WeatherRequest request)
            throws IncorrectAPIOutputException {
        Gson gson = new GsonBuilder().create();
        Forecast5Days3HoursData apiForecast = gson.fromJson(jsonFile, Forecast5Days3HoursData.class);

        String cityName = getCityNameFromAPIStructureObject(apiForecast);
        double[] coordinates = getCoordinatesFromAPIStructureObject(apiForecast);
        String countryCode = getCountryCodeFromAPIStructureObject(apiForecast);

        List<ForecastOneDayWeather> oneDayWeatherList = getOneDayWeathersList(apiForecast);

        return new WeatherForecastReport(new City(cityName, Coordinates.of(coordinates[0], coordinates[1]), countryCode),
                oneDayWeatherList, request.getTemperatureUnit());
    }
}
