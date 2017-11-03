package weatherdata;

import openweatherobjects.CurrentWeatherData;
import city.City;
import city.Coordinates;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.IncorrectAPIOutputException;
import utility.Utils;

public class CurrentWeatherReportFabric {
    private static String getCityNameFromAPIStructureObject(CurrentWeatherData structureObject)
            throws IncorrectAPIOutputException {
        String cityname = structureObject.cityName;
        if (cityname == null) {
            throw new IncorrectAPIOutputException("Incorrect city name!");
        }
        return cityname;
    }

    private static String getCountryCodeFromAPIStructureObject(CurrentWeatherData structureObject)
            throws IncorrectAPIOutputException {
        String countryCode = (String) structureObject.systemData.get("country");
        if (countryCode == null || !Utils.isCountryCodeCorrect(countryCode)) {
            throw new IncorrectAPIOutputException("Incorrect country code!");
        }
        return countryCode;
    }

    private static double getCurrentTemperatureFromAPIStructureObject(CurrentWeatherData structureObject)
            throws IncorrectAPIOutputException {
        try {
            return Float.parseFloat(structureObject.mainData.get("temp").toString());
        } catch (NullPointerException ex) {
            throw new IncorrectAPIOutputException("Incorrect temperature!");
        }
    }

    private static double[] getCoordinatesFromAPIStructureObject(CurrentWeatherData structureObject)
            throws IncorrectAPIOutputException {
        try {
            double[] coordinates = new double[2];
            coordinates[0] = structureObject.coordinates.get("lon"); // longitude
            coordinates[1] = structureObject.coordinates.get("lat"); // latitude

            return coordinates;
        } catch (NullPointerException ex) {
            throw new IncorrectAPIOutputException("Incorrect coordinates!");
        }
    }

    public static CurrentWeatherReport createReportFromJSONAndRequest(String jsonFile, WeatherRequest request)
            throws IncorrectAPIOutputException {
        Gson gson = new GsonBuilder().create();
        final CurrentWeatherData weatherFromAPI = gson.fromJson(jsonFile, CurrentWeatherData.class);

        String cityName = getCityNameFromAPIStructureObject(weatherFromAPI);
        String countryCode = getCountryCodeFromAPIStructureObject(weatherFromAPI);
        double[] coordinates = getCoordinatesFromAPIStructureObject(weatherFromAPI);
        double currentTemperature = getCurrentTemperatureFromAPIStructureObject(weatherFromAPI);

        return new CurrentWeatherReport(new City(cityName, Coordinates.of(coordinates[0], coordinates[1]), countryCode),
                currentTemperature, request.getTemperatureUnit());
    }
}
