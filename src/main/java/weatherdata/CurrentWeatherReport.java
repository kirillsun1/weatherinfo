package weatherdata;

import OpenWeatherStructures.CurrentWeatherDataStructure;
import city.City;
import city.Coordinates;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.IncorrectAPIOutputException;
import utility.Constants;

public class CurrentWeatherReport {
    private City city;
    private double currentTemperature;
    private Constants.TemperatureUnits temperatureUnit;

    private CurrentWeatherReport() {

    }

    public String getCityName() {
        return city.getCityName();
    }

    public String getCountryCode() {
        return city.getCountryCode();
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public Coordinates getCoordinates() {
        return city.getCoordinates();
    }

    public Constants.TemperatureUnits getTemperatureUnit() {
        return temperatureUnit;
    }

    @Override
    public String toString() {
        return String.format("City: %s [%s]\nCoords: %s\nTemp: %.2f", getCityName(), getCountryCode(),
                city.getCoordinates(), getCurrentTemperature());
    }

    public static CurrentWeatherReport getFromJSON(String jsonFile) throws IncorrectAPIOutputException {
        Gson gson = new GsonBuilder().create();
        final CurrentWeatherDataStructure weatherFromAPI = gson.fromJson(jsonFile, CurrentWeatherDataStructure.class);
        CurrentWeatherReport report = new CurrentWeatherReport();

        String cityName = weatherFromAPI.getCityName();
        double longtitude = weatherFromAPI.getCoordinates().get("lon");
        double latitude = weatherFromAPI.getCoordinates().get("lat");
        String countryCode = (String) weatherFromAPI.getSys().get("country");

        report.city = new City(cityName, Coordinates.of(longtitude, latitude), countryCode);

        report.currentTemperature = Float.parseFloat(weatherFromAPI.getMain().get("temp").toString());
        return report;
    }
}
