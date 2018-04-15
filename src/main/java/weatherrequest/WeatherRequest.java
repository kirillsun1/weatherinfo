package weatherrequest;

import utility.Constants;

import java.util.Optional;

public class WeatherRequest {
    String cityName;
    String cityCode;
    Constants.TemperatureUnits tempUnit = Constants.TemperatureUnits.getUnitByDefault();

    WeatherRequest() {

    }

    public String getCityName() {
        return cityName;
    }

    public Optional<String> getCountryCode() {
        return Optional.ofNullable(cityCode);
    }

    public Constants.TemperatureUnits getTemperatureUnit() {
        return tempUnit;
    }
}
