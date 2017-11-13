package iofiles;

import com.google.gson.annotations.SerializedName;
import weatherdata.CurrentWeatherReport;
import weatherdata.WeatherForecastReport;

import java.util.HashMap;

public class ReportFile {
    @SerializedName("current")
    private CurrentWeatherReport currentWeatherReport;
    @SerializedName("forecast")
    private WeatherForecastReport weatherForecastReport;

    public ReportFile(CurrentWeatherReport currentWeatherReport, WeatherForecastReport weatherForecastReport) {
        this.currentWeatherReport = currentWeatherReport;
        this.weatherForecastReport = weatherForecastReport;
    }
}