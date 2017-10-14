package OpenWeatherStructures;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class CurrentWeatherDataStructure {
    private HashMap<String, Float> coord;
    private HashMap<String, Object> sys;
    private HashMap<String, Object> main;
    private String name;

    public HashMap<String, Float> getCoordinates() {
        return coord;
    }

    public HashMap<String, Object> getSys() {
        return sys;
    }

    public String getCityName() {
        return name;
    }

    public HashMap<String, Object> getMain() {
        return main;
    }
}
