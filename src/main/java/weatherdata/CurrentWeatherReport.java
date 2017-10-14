package weatherdata;

import OpenWeatherStructures.CurrentWeatherDataStructure;
import city.City;
import city.Coordinates;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.IncorrectAPIOutputException;
import utility.Constants;
import utility.Utils;

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

        // TODO: Ask if okay. I think it is not!

        String cityName, countryCode;
        double longtitude, latitude;

        cityName = weatherFromAPI.getCityName();
        if (cityName == null) {
            throw new IncorrectAPIOutputException("Incorrect city!");
        }

        try {
            longtitude = weatherFromAPI.getCoordinates().get("lon");
            latitude = weatherFromAPI.getCoordinates().get("lat");
        } catch (NullPointerException ex) {
            throw new IncorrectAPIOutputException("Incorrect coordinated!");
        }

        countryCode = (String) weatherFromAPI.getSys().get("country");
        if (countryCode == null || !Utils.isCountryCodeCorrect(countryCode)) {
            throw new IncorrectAPIOutputException("Incorrect country code!");
        }

        report.city = new City(cityName, Coordinates.of(longtitude, latitude), countryCode);

        try {
            report.currentTemperature = Float.parseFloat(weatherFromAPI.getMain().get("temp").toString());
        } catch (NullPointerException ex) {
            throw new IncorrectAPIOutputException("Incorrect temperature!");
        }
        return report;
    }
}
