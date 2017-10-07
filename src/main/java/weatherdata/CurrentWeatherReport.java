package weatherdata;

import city.City;
import jdk.nashorn.internal.parser.JSONParser;

public class CurrentWeatherReport {
    private City city;

    public String getCityName() {
        return city.getCityName();
    }

    public String getGEOCoordinates() {
        return null;
    }

    public double getCurrentTemperature() {
        return -1;
    }

    public static CurrentWeatherReport getFromJSON(String jsonFile) {
        return null;
    }
}
