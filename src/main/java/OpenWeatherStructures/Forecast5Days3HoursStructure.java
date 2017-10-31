package OpenWeatherStructures;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public final class Forecast5Days3HoursStructure {
    public HashMap<String, Object>[] list;
    public CityStructure city;
}