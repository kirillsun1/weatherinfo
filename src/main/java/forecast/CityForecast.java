package forecast;

import city.City;
import jdk.nashorn.internal.parser.JSONParser;

/**
 * Forecast data.
 */
public class CityForecast {
    /**
     * Private constructor.
     * To create object, use fabric method parseFromJSON(String)
     */
    private CityForecast() {

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
    public int getCurrentTemperature(TemperatureUnits unit) {
        return Integer.MIN_VALUE;
    }

    /**
     * Return minimum temperature in location during 3 days.
     *
     * @return Temperature in kelvin.
     */
    public int get3DaysMinTemperature() {
        return 0;
    }

    /**
     * Return minimum temperature in location during 3 days.
     * Value will be converted to the chosen unit.
     *
     * @param unit Unit to convert value.
     * @return Temperature.
     */
    public int get3DaysMinTemperature(TemperatureUnits unit) {
        return 0;
    }

    /**
     * Return maximum temperature in location during 3 days.
     *
     * @return Temperature in kelvin.
     */
    public int get3DaysMaxTemperature() {
        return 0;
    }

    /**
     * Return maximum temperature in location during 3 days.
     * Value will be converted to the chosen unit.
     *
     * @param unit Unit to convert value.
     * @return Temperature.
     */
    public int get3DaysMaxTemperature(TemperatureUnits unit) {
        return 0;
    }

    /**
     * Fabric method which creates object.
     *
     * @param jsonFile Json file as string.
     * @return Object.
     */
    public static CityForecast parseFromJSON(String jsonFile) {
        return null;
    }
}