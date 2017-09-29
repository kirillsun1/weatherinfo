package weatherdata;

import java.util.HashMap;
import java.util.Map;

public class WeatherRequest {
    private static Map<String, WeatherRequest> existingRequests = new HashMap<>();

    private String cityName;
    private String cityCode;

    private WeatherRequest() {

    }

    public String getCityName() {
        return cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public static WeatherRequest fromCityNameAndCode(String cityName, String cityCode) {
        String key = cityName + cityCode;
        if (existingRequests.containsKey(key)) {
            return existingRequests.get(key);
        }

        WeatherRequest request = new WeatherRequest();
        request.cityName = cityName;
        request.cityCode = cityCode;

        existingRequests.put(key, request);
        return request;
    }
}
