package weatherrequest;

import iofiles.RequestFile;
import utility.Constants;

public class WeatherRequestFactory {
    public WeatherRequest makeWeatherRequest(String cityName, String countryCode) {
        WeatherRequest request = new WeatherRequest();
        request.cityName = cityName;
        request.cityCode = countryCode;

        return request;
    }

    public WeatherRequest makeWeatherRequest(String cityName, String countryCode,
                                    Constants.TemperatureUnits tempUnit) {
        WeatherRequest request = makeWeatherRequest(cityName, countryCode);
        request.tempUnit = tempUnit;
        return request;
    }

    public WeatherRequest makeWeatherRequest(RequestFile inputFile) {
        return makeWeatherRequest(inputFile.getCityName(), inputFile.getCountryCode(),
                Constants.TemperatureUnits.of(inputFile.getTemperatureUnit()));
    }
}
