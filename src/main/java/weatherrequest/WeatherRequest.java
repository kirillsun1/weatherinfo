package weatherrequest;

import utility.Constants;

public class WeatherRequest {
    String cityName;
    String cityCode;
    Constants.TemperatureUnits tempUnit = Constants.TemperatureUnits.getUnitByDefault();

    WeatherRequest() {

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
}
