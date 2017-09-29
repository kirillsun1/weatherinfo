package weatherdata;

import utility.Constants;

/**
 * Forecast data.
 */
public class WeatherForecast {
    /**
     * Private constructor.
     * To create object, use fabric method parseFromJSON(String)
     */
    private WeatherForecast() {

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

    /**
     * Return current temperature in location.
     *
     * @return temperature in kelvin.
     */
    public int getCurrentTemperature() {
        return Integer.MIN_VALUE;
    }

    /**
     * Return current temperature in location in particular unit.
     *
     * @param unit Unit to convert value.
     * @return temperature.
     */
    public int getCurrentTemperature(Constants unit) {
        return Integer.MIN_VALUE;
    }
    
}