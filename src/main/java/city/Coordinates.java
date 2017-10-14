package city;

public class Coordinates {
    private double longitude, latitude;

    private Coordinates(double newLongitude, double newLatitude) {
        longitude = newLongitude;
        latitude = newLatitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public static Coordinates of(double longitude, double latitude) {
        return new Coordinates(longitude, latitude);
    }

    @Override
    public String toString() {
        return String.format("%03.02f:%03.02f", latitude, longitude);
    }
}
