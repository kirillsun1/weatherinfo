package collector;

import city.Coordinates;
import com.google.gson.Gson;
import iofiles.CollectorInputFile;
import iofiles.CollectorOutputFile;
import repository.WeatherRepository;
import utility.Constants;
import utility.FileReader;
import utility.FileWriter;
import weatherdata.CurrentWeatherReport;
import weatherdata.ForecastOneDayWeather;
import weatherdata.WeatherForecastReport;
import weatherdata.WeatherRequest;

import java.io.IOException;
import java.util.HashMap;

public class WeatherCollector {
    private final WeatherRepository weatherRepository;
    private final FileReader reader;
    private final FileWriter writer;

    public WeatherCollector(WeatherRepository weatherRepository, FileReader reader, FileWriter writer) {
        this.weatherRepository = weatherRepository;
        this.reader = reader;
        this.writer = writer;
    }

    private static WeatherRequest getRequestFromInputFile(CollectorInputFile inputFile) throws IllegalAccessException {
        Constants.TemperatureUnits temperatureUnit = inputFile.temperatureUnit == null
                ? Constants.TemperatureUnits.getUnitByDefault()
                : Constants.TemperatureUnits.of(inputFile.temperatureUnit);

        return WeatherRequest.of(inputFile.cityName, inputFile.countryCode, temperatureUnit);
    }

    public void saveWeatherDataToFile(String pathToInputFile, String pathToOutputFile) throws IOException {
        Gson gson = new Gson();
        CollectorInputFile inputFile = gson.fromJson(reader.readFile(pathToInputFile), CollectorInputFile.class);

        if (inputFile == null) {
            throw new IOException("Incorrect input file!");
        }

        WeatherRequest request;
        try {
            request = getRequestFromInputFile(inputFile);
        } catch (IllegalAccessException e) {
            throw new IOException("Incorrect input file!");
        }

        CurrentWeatherReport currentWeatherReport = weatherRepository.getCurrentWeatherReport(request);
        WeatherForecastReport weatherForecastReport = weatherRepository.getWeatherForecastReport(request);

        CollectorOutputFile outputFile = new CollectorOutputFile();

        outputFile.cityName = request.getCityName();
        outputFile.countryCode = request.getCountryCode();
        outputFile.temperatureUnit = request.getTemperatureUnit().toString();
        outputFile.currentWeather = currentWeatherReport.getCurrentTemperature();
        Coordinates coordinates = currentWeatherReport.getCoordinates();
        outputFile.coordinates.put("longitude", coordinates.getLongitude());
        outputFile.coordinates.put("latitude", coordinates.getLatitude());
        outputFile.forecast = new HashMap[WeatherForecastReport.DAYS_IN_FORECAST];
        for (int i = 0; i < outputFile.forecast.length; i++) {
            ForecastOneDayWeather oneDayWeather = weatherForecastReport.getOneDayWeathers().get(i);
            HashMap<String, Object> dayMap = new HashMap<>();

            dayMap.put("day", oneDayWeather.getCurrentDateTime().toString());
            dayMap.put("min_temp", oneDayWeather.getMinimumTemperature());
            dayMap.put("max_temp", oneDayWeather.getMinimumTemperature());

            outputFile.forecast[i] = dayMap;
        }

        writer.writeToFile(gson.toJson(outputFile), pathToOutputFile);
    }
}
