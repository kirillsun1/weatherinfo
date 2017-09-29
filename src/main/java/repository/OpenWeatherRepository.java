package repository;

import exceptions.APIDataNotFoundException;
import weatherdata.CurrentWeatherData;
import weatherdata.WeatherForecast;
import weatherdata.WeatherRequest;

public class OpenWeatherRepository implements WeatherRepository {
    private static final String API_LINK = "api.openweathermap.org/data/2.5/weather?";
    private static final String API_KEY = "846ff6c4ed10c7be2825f677097f8376";

    private static String makeCurrentWeatherRequestLinkFromWeatherRequest(WeatherRequest request) {
        return API_LINK + String.format("q=%s,%s&APPID=%s", request.getCityName(), request.getCityCode(), API_KEY);
    }

    @Override
    public WeatherForecast getWeatherForecast(WeatherRequest request) throws APIDataNotFoundException {
        return null;
    }

    @Override
    public CurrentWeatherData getCurrentWeather(WeatherRequest request) throws APIDataNotFoundException {
        return null;
    }
}
