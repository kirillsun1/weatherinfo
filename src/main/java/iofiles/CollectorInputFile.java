package iofiles;

import com.google.gson.annotations.SerializedName;

public class CollectorInputFile {
    @SerializedName("cityname")
    public String cityName;
    @SerializedName("country")
    public String countryCode;
    @SerializedName("tempunit")
    public String temperatureUnit;
}
