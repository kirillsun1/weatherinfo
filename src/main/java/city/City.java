package city;

/**
 * City data.
 */
public class City {
    private final int cityID;
    private final String cityName;
    private final double longitude;
    private final double latitude;
    private final String countryCode;

    /**
     * Constructor.
     *
     * @param id          ID of the city.
     * @param name        Name of the city.
     * @param longitude   City coordinate.
     * @param latitude    City coordinate.
     * @param countryCode Code of the country where the city is in.
     */
    public City(int id, String name, double longitude, double latitude, String countryCode) {
        this.cityID = id;
        this.cityName = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.countryCode = countryCode;
    }

    /**
     * Return city id.
     *
     * @return city id as integer.
     */
    public int getCityID() {
        return cityID;
    }

    /**
     * Get city name.
     *
     * @return name of the city.
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Get longitude of the city.
     *
     * @return Rounded up number.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Get latitude of the city.
     *
     * @return Rounded up number.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Return code of the country where the city is in.
     * Country code consist of two upper letters.
     *
     * @return Code.
     */
    public String getCountryCode() {
        return countryCode;
    }
}
