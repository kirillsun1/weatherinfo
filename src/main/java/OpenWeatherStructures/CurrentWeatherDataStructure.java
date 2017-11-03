package OpenWeatherStructures;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public final class CurrentWeatherDataStructure {
    @SerializedName("coord")
    public HashMap<String, Float> coordinates;
    @SerializedName("sys")
    public HashMap<String, Object> systemData;
    @SerializedName("main")
    public HashMap<String, Object> mainData;
    @SerializedName("name")
    public String cityName;
}
