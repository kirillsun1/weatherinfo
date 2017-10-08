package weatherdata;

import city.City;
import exceptions.IncorrectAPIOutputException;
import org.json.*;
import utility.Utils;

public class CurrentWeatherReport {
    private City city;
    private double currentTemperature;

    private CurrentWeatherReport() {

    }

    public String getCityName() {
        return city.getCityName();
    }

    public String getCountryCode() {
        return city.getCountryCode();
    }

    public String getGEOCoordinates() {
        return String.format("%03d:%03d", Math.round(city.getLongitude()), Math.round(city.getLatitude()));
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    @Override
    public String toString() {
        return String.format("City: %s [%s]\nCoords: %s\nTemp: %.2f", getCityName(), getCountryCode(),
                getGEOCoordinates(), getCurrentTemperature());
    }

    public static CurrentWeatherReport getFromJSON(String jsonFile) throws IncorrectAPIOutputException {
        JSONObject parsedJson = new JSONObject(jsonFile);

        int cityID;
        String cityName, countryCode;
        double longitude, latitude;
        try {
            cityID = parsedJson.getInt("id");
            cityName = parsedJson.getString("name");
            countryCode = parsedJson.getJSONObject("sys").getString("country");
            JSONObject coordObj = parsedJson.getJSONObject("coord");
            longitude = coordObj.getDouble("lon");
            latitude = coordObj.getDouble("lat");

        } catch (JSONException ex) {
            throw new IncorrectAPIOutputException("Error while parsing output: " + ex.getMessage());
        }

        if (!Utils.isCountryCodeCorrect(countryCode)) {
            throw new IncorrectAPIOutputException("Incorrect country code!");
        }

        City city = new City(cityID, cityName, longitude, latitude, countryCode);
        CurrentWeatherReport report = new CurrentWeatherReport();
        report.city = city;
        // TODO: Consider tempunits!
        report.currentTemperature = parsedJson.getJSONObject("main").getDouble("temp");
        return report;
    }
}
