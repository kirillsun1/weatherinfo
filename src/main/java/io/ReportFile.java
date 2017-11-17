package io;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import weatherdata.CurrentWeatherReport;
import weatherdata.WeatherForecastReport;

public class ReportFile {
    @Expose
    private String cityName;
    @SerializedName("current")
    private final CurrentWeatherReport currentWeatherReport;
    @SerializedName("forecast")
    private final WeatherForecastReport weatherForecastReport;

    public ReportFile(String cityName, CurrentWeatherReport currentWeatherReport,
                      WeatherForecastReport weatherForecastReport) {
        this.cityName = cityName;
        this.currentWeatherReport = currentWeatherReport;
        this.weatherForecastReport = weatherForecastReport;
    }

    public String getCityName() {
        return cityName;
    }
}