package OpenWeatherStructures;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public final class Forecast5Days3HoursStructure {
    private HashMap<String, Object>[] list;
    private CityStructure city;

    public HashMap<String, Object>[] getList() {
        return list;
    }

    public CityStructure getCity() {
        return city;
    }

    public static class CityStructure {
        private String name;
        private HashMap<String, Double> coord;
        private String country;

        public String getCityName() {
            return name;
        }

        public HashMap<String, Double> getCoordinates() {
            return coord;
        }

        public String getCountryCode() {
            return country;
        }
    }
}
