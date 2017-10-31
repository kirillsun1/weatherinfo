package weatherdata;

import city.City;
import city.Coordinates;
import utility.Constants;

import java.util.*;

public class WeatherForecastReport {
    static final int DAYS_IN_FORECAST = 3;

    private City city;
    private List<ForecastOneDayWeather> oneDayWeathers;
    private Constants.TemperatureUnits temperatureUnit;

    WeatherForecastReport(City city, List<ForecastOneDayWeather> oneDayWeathers,
                          Constants.TemperatureUnits temperatureUnit) {
        oneDayWeathers = new ArrayList<>();
        this.city = city;
        this.oneDayWeathers = oneDayWeathers;
        this.temperatureUnit = temperatureUnit;
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
}