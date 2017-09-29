package repository;

import exceptions.APIDataNotFoundException;
import weatherdata.CurrentWeatherData;
import weatherdata.WeatherForecast;
import weatherdata.WeatherRequest;

public interface WeatherRepository {
    WeatherForecast getWeatherForecast(WeatherRequest request) throws APIDataNotFoundException;

    CurrentWeatherData getCurrentWeather(WeatherRequest request) throws APIDataNotFoundException;
}
