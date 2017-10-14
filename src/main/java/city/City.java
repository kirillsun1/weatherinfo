package city;

/**
 * CityStructure data.
 */
public class City {
    private final String cityName;
    private final Coordinates coordinates;
    private final String countryCode;

    /**
     * Constructor.
     *
     * @param name        Name of the city.
     * @param coordinates Longitude and latitude.
     * @param countryCode Code of the country where the city is in.
     */
    public City(String name, Coordinates coordinates, String countryCode) {
        this.cityName = name;
        this.coordinates = coordinates;
        this.countryCode = countryCode;
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
     * Return code of the country where the city is in.
     * Country code consist of two upper letters.
     *
     * @return Code.
     */
    public String getCountryCode() {
        return countryCode;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
