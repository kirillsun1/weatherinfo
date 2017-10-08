package weatherdata;

import utility.Constants;
import utility.Utils;

import java.util.HashMap;
import java.util.Map;

public class WeatherRequest {
    private static Map<String, WeatherRequest> existingRequests = new HashMap<>();

    private String cityName;
    private String cityCode;
    private Constants.TemperatureUnits tempUnit = Constants.TemperatureUnits.KELVIN;

    private WeatherRequest() {

    }

    public String getCityName() {
        return cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public Constants.TemperatureUnits getTemperatureUnit() {
        return tempUnit;
    }

    public static WeatherRequest fromCityNameAndCode(String cityName, String countryCode) {
        String key = cityName + countryCode;
        if (existingRequests.containsKey(key)) {
            return existingRequests.get(key);
        }

        WeatherRequest request = new WeatherRequest();
        request.cityName = cityName;
        request.cityCode = countryCode;

        existingRequests.put(key, request);
        return request;
    }

    public static WeatherRequest fromCityNameAndCode(String cityName, String countryCode,
                                                     Constants.TemperatureUnits tempUnit) {
        WeatherRequest request = fromCityNameAndCode(cityName, countryCode);
        request.tempUnit = tempUnit;
        return request;
    }
}
