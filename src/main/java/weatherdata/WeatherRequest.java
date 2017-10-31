package weatherdata;

import utility.Constants;

import java.util.HashMap;
import java.util.Map;

public class WeatherRequest {
    private String cityName;
    private String cityCode;
    private Constants.TemperatureUnits tempUnit = Constants.TemperatureUnits.getUnitByDefault();

    private WeatherRequest() {

    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryCode() {
        return cityCode;
    }

    public Constants.TemperatureUnits getTemperatureUnit() {
        return tempUnit;
    }

    public static WeatherRequest of(String cityName, String countryCode) {
        WeatherRequest request = new WeatherRequest();
        request.cityName = cityName;
        request.cityCode = countryCode;

        return request;
    }

    public static WeatherRequest of(String cityName, String countryCode,
                                    Constants.TemperatureUnits tempUnit) {
        WeatherRequest request = of(cityName, countryCode);
        request.tempUnit = tempUnit;
        return request;
    }
}
