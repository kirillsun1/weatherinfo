package iofiles;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class CollectorOutputFile {
    @SerializedName("city")
    public String cityName;
    @SerializedName("country")
    public String countryCode;
    @SerializedName("coordinates")
    public HashMap<String, Double> coordinates = new HashMap<>();
    @SerializedName("unit")
    public String temperatureUnit;
    @SerializedName("temperature")
    public double currentWeather;
    @SerializedName("forecast")
    public HashMap<String, Object>[] forecast;
}