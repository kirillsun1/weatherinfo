package city;

public class City {
    private final String cityName;
    private final Coordinates coordinates;
    private final String countryCode;

    public City(String name, Coordinates coordinates, String countryCode) {
        this.cityName = name;
        this.coordinates = coordinates;
        this.countryCode = countryCode;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
