package openweatherobjects;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class City {
    public String name;
    @SerializedName("coord")
    public HashMap<String, Double> coordinates;
    public String country;
}