package OpenWeatherStructures;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public final class CurrentWeatherDataStructure {
    public HashMap<String, Float> coord;
    public HashMap<String, Object> sys;
    public HashMap<String, Object> main;
    public String name;
}
