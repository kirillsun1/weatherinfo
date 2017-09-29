package weatherdata;

import city.City;
import utility.Constants;

/**
 * Forecast data.
 */
public class WeatherForecastReport {
    private City city;

    /**
     * Private constructor.
     * To create object, use fabric method parseFromJSON(String)
     */
    private WeatherForecastReport() {

    }

    /**
     * Return location coordinates.
     * Format: "xxx:yyy" where xxx - longitude, yyy - latitude.
     * Numbers are rounded.
     *
     * @return String with coordinates.
     */
    public String getGEOCoordinates() {
        return null;
    }

    public String getCityName() {
        return city.getCityName();
    }

    public static WeatherForecastReport getFromJSON(String json) {
        return null;
    }
}