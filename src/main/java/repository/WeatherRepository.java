package repository;

import exceptions.APIDataNotFoundException;
import weatherdata.CurrentWeatherReport;
import weatherdata.WeatherForecastReport;
import weatherdata.WeatherRequest;

public interface WeatherRepository {
    WeatherForecastReport getWeatherForecastReport(WeatherRequest request) throws APIDataNotFoundException;

    CurrentWeatherReport getCurrentWeatherReport(WeatherRequest request) throws APIDataNotFoundException;
}
