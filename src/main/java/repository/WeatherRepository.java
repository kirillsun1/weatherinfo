package repository;

import exceptions.APIDataNotFoundException;
import exceptions.IncorrectAPIOutputException;
import weatherdata.CurrentWeatherReport;
import weatherdata.WeatherForecastReport;
import weatherdata.WeatherRequest;

import java.io.IOException;

public interface WeatherRepository {
    WeatherForecastReport getWeatherForecastReport(WeatherRequest request) throws APIDataNotFoundException;

    CurrentWeatherReport getCurrentWeatherReport(WeatherRequest request) throws APIDataNotFoundException;
}
