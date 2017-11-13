package repository;

import exceptions.APIDataNotFoundException;
import exceptions.IncorrectAPIOutputException;
import network.HTTPConnection;
import utility.Constants;
import weatherdata.*;
import weatherrequest.WeatherRequest;

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

        return FORECAST_API_LINK + String.format("q=%s,%s%s&APPID=%s", request.getCityName(), request.getCountryCode(),
                tempUnitString, API_KEY);
    }

    private static HTTPConnection getHTTPConnectionByLink(String connectionLink) throws APIDataNotFoundException {
        try {
            HTTPConnection connection = HTTPConnection.createConnectionFromURL(connectionLink);
            if (connection.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
                throw new APIDataNotFoundException("Report not found!");
            }
            return connection;
        } catch (IOException e) {
            throw new APIDataNotFoundException("Unable to get data from API: " + e.getMessage());
        }
    }

    private static String getJSONFileFromConnection(HTTPConnection connection) throws APIDataNotFoundException {
        try {
            return connection.downloadFile();
        } catch (IOException e) {
            throw new APIDataNotFoundException("Unable to get data from API: " + e.getMessage());
        }
    }

    @Override
    public WeatherForecastReport getWeatherForecastReport(WeatherRequest request) throws APIDataNotFoundException {
        String connectionLink = makeWeatherForecastRequestLinkFromWeatherRequest(request);
        HTTPConnection connection = getHTTPConnectionByLink(connectionLink);
        String jsonFile = getJSONFileFromConnection(connection);

        try {
            return WeatherForecastReportFabric.createReportFromJSONAndRequest(jsonFile, request);
        } catch (IncorrectAPIOutputException e) {
            throw new APIDataNotFoundException("Unable to get data from API: " + e.getMessage());
        }
    }

    @Override
    public CurrentWeatherReport getCurrentWeatherReport(WeatherRequest request) throws APIDataNotFoundException {
        String connectionLink = makeCurrentWeatherRequestLinkFromWeatherRequest(request);
        HTTPConnection connection = getHTTPConnectionByLink(connectionLink);
        String jsonFile = getJSONFileFromConnection(connection);

        try {
            return CurrentWeatherReportFabric.createReportFromJSONAndRequest(jsonFile, request);
        } catch (IncorrectAPIOutputException e) {
            throw new APIDataNotFoundException("Unable to get data from API: " + e.getMessage());
        }
    }
}
