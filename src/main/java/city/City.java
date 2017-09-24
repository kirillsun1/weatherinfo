package city;

/**
 * City data.
 */
public class City {
    /**
     * Constructor.
     *
     * @param id          ID of the city.
     * @param name        Name of the city.
     * @param longitude   City coordinate.
     * @param latitude    City coordinate.
     * @param countryCode Code of the country where the city is in.
     */
    public City(int id, String name, int longitude, int latitude, String countryCode) {

    }

    /**
     * Return city id.
     *
     * @return city id as integer.
     */
    public int getCityID() {
        return Integer.MIN_VALUE;
    }

    /**
     * Get city name.
     *
     * @return name of the city.
     */
    public String getCityName() {
        return null;
    }

    /**
     * Get longitude of the city.
     *
     * @return Rounded up number.
     */
    public int getLongitude() {
        return Integer.MIN_VALUE;
    }

    /**
     * Get latitude of the city.
     *
     * @return Rounded up number.
     */
    public int getLatitude() {
        return Integer.MIN_VALUE;
    }

    /**
     * Return code of the country where the city is in.
     * Country code consist of two upper letters.
     *
     * @return Code.
     */
    public String getCountryCode() {
        return null;
    }
}
