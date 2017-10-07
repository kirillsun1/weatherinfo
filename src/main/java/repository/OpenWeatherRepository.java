package repository;

import exceptions.APIDataNotFoundException;
import network.HTTPConnection;
import weatherdata.CurrentWeatherReport;
import weatherdata.WeatherForecastReport;
import weatherdata.WeatherRequest;

import java.io.IOException;

public class OpenWeatherRepository implements WeatherRepository {
    private static final String WEATHER_API_LINK = "https://api.openweathermap.org/data/2.5/weather?";
    private static final String FORECAST_API_LINK = "https://api.openweathermap.org/data/2.5/forecast?";
    private static final String API_KEY = "846ff6c4ed10c7be2825f677097f8376";

    private static String makeCurrentWeatherRequestLinkFromWeatherRequest(WeatherRequest request) {
        return WEATHER_API_LINK + String.format("q=%s,%s&APPID=%s", request.getCityName(),
                request.getCityCode(), API_KEY);
    }

    private static String makeWeatherForecastRequestLinkFromWeatherRequest(WeatherRequest request) {
        return FORECAST_API_LINK + String.format("q=%s,%s&APPID=%s", request.getCityName(),
                request.getCityCode(), API_KEY);
    }

    @Override
    public WeatherForecastReport getWeatherForecastReport(WeatherRequest request) throws APIDataNotFoundException {
        String connectionLink = makeWeatherForecastRequestLinkFromWeatherRequest(request);
        HTTPConnection connection;
        try {
            connection = HTTPConnection.createConnection(connectionLink);
            connection.open();
        } catch (IOException e) {
            throw new APIDataNotFoundException("Connection is not established!");
        }

        String jsonFile;
        try {
            jsonFile = connection.downloadFile();
        } catch (IOException e) {
            throw new APIDataNotFoundException("Cannot load API data!");
        }

        return WeatherForecastReport.getFromJSON(jsonFile);
    }

    @Override
    public CurrentWeatherReport getCurrentWeatherReport(WeatherRequest request) throws APIDataNotFoundException {
        String connectionLink = makeCurrentWeatherRequestLinkFromWeatherRequest(request);
        HTTPConnection connection;
        try {
            connection = HTTPConnection.createConnection(connectionLink);
            connection.open();
        } catch (IOException e) {
            throw new APIDataNotFoundException("Connection is not established!");
        }

        String jsonFile;
        try {
            jsonFile = connection.downloadFile();
        } catch (IOException e) {
            throw new APIDataNotFoundException("Cannot load API data!");
        }

        return CurrentWeatherReport.getFromJSON(jsonFile);
    }
}
