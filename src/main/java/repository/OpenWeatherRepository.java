package repository;

import exceptions.APIDataNotFoundException;
import exceptions.IncorrectAPIOutputException;
import network.HTTPConnection;
import utility.Constants;
import weatherdata.CurrentWeatherReport;
import weatherdata.WeatherForecastReport;
import weatherdata.WeatherRequest;

import java.io.IOException;
import java.net.HttpURLConnection;

public class OpenWeatherRepository implements WeatherRepository {
    private static final String WEATHER_API_LINK = "https://api.openweathermap.org/data/2.5/weather?";
    private static final String FORECAST_API_LINK = "https://api.openweathermap.org/data/2.5/forecast?";
    private static final String API_KEY = "846ff6c4ed10c7be2825f677097f8376";

    private static String makeCurrentWeatherRequestLinkFromWeatherRequest(WeatherRequest request) {
        String tempUnitString = request.getTemperatureUnit() == Constants.TemperatureUnits.getUnitByDefault() ?
                "" : String.format("&units=%s", request.getTemperatureUnit().toString().toLowerCase());

        return WEATHER_API_LINK + String.format("q=%s,%s%s&APPID=%s", request.getCityName(), request.getCountryCode(),
                tempUnitString, API_KEY);
    }

    private static String makeWeatherForecastRequestLinkFromWeatherRequest(WeatherRequest request) {
        String tempUnitString = request.getTemperatureUnit() == Constants.TemperatureUnits.getUnitByDefault() ?
                "" : String.format("&units=%s", request.getTemperatureUnit().toString().toLowerCase());

        return FORECAST_API_LINK + String.format("q=%s,%s&APPID=%s", request.getCityName(), request.getCountryCode(),
                tempUnitString, API_KEY);
    }

    @Override
    public WeatherForecastReport getWeatherForecastReport(WeatherRequest request) throws APIDataNotFoundException {
        String connectionLink = makeWeatherForecastRequestLinkFromWeatherRequest(request);
        HTTPConnection connection;
        try {
            connection = HTTPConnection.createConnectionFromURL(connectionLink);
        } catch (IOException e) {
            throw new APIDataNotFoundException("Unable to get data from API: " + e.getMessage());
        }

        try {
            if (connection.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
                throw new APIDataNotFoundException("Invalid data!");
            }
        } catch (IOException e) {
            throw new APIDataNotFoundException("Unable to get data from API: " + e.getMessage());
        }

        String jsonFile;
        try {
            jsonFile = connection.downloadFile();
        } catch (IOException e) {
            throw new APIDataNotFoundException("Unable to get data from API: " + e.getMessage());
        }

        try {
            return WeatherForecastReport.getFromJSON(jsonFile);
        } catch (IncorrectAPIOutputException e) {
            throw new APIDataNotFoundException("Unable to get data from API: " + e.getMessage());
        }
    }

    @Override
    public CurrentWeatherReport getCurrentWeatherReport(WeatherRequest request) throws APIDataNotFoundException {
        String connectionLink = makeCurrentWeatherRequestLinkFromWeatherRequest(request);
        HTTPConnection connection;
        try {
            connection = HTTPConnection.createConnectionFromURL(connectionLink);
        } catch (IOException e) {
            throw new APIDataNotFoundException("Connection is not established!");
        }

        try {
            if (connection.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
                throw new APIDataNotFoundException("Invalid data!");
            }
        } catch (IOException e) {
            throw new APIDataNotFoundException("Unable to get data from API: " + e.getMessage());
        }

        String jsonFile;
        try {
            jsonFile = connection.downloadFile();
        } catch (IOException e) {
            throw new APIDataNotFoundException("Cannot load API data!");
        }

        try {
            return CurrentWeatherReport.getFromJSON(jsonFile);
        } catch (IncorrectAPIOutputException e) {
            throw new APIDataNotFoundException("Unable to get data from API: " + e.getMessage());
        }
    }
}
