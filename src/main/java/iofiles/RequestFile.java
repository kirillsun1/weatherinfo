package iofiles;

import com.google.gson.annotations.SerializedName;

public class RequestFile {
    @SerializedName("cityname")
    String cityName;
    @SerializedName("country")
    String countryCode;
    @SerializedName("tempunit")
    String temperatureUnit;

    public String getCityName() {
        return cityName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getTemperatureUnit() {
        return temperatureUnit;
    }
}
