package ui;

import exceptions.APIDataNotFoundException;
import io.ConsoleReader;
import repository.WeatherRepository;
import utility.Constants;
import weatherdata.CurrentWeatherReport;
import weatherdata.WeatherForecastReport;
import weatherrequest.WeatherRequest;
import weatherrequest.WeatherRequestFactory;

public class WeatherDataConsoleUI {
    private final ConsoleReader consoleReader;
    private final WeatherRepository weatherRepository;
    private final WeatherRequestFactory weatherRequestFactory;

    public WeatherDataConsoleUI(ConsoleReader consoleReader, WeatherRepository weatherRepository) {
        this.consoleReader = consoleReader;
        this.weatherRepository = weatherRepository;
        weatherRequestFactory = new WeatherRequestFactory();
    }

    public void start() {
        try {
            System.out.println("Welcome to WeatherInfo app!");
            System.out.println("Here you can get weather data of any city in the world.");

            String cityName = askCityName();
            Constants.TemperatureUnits temperatureUnit = Constants.TemperatureUnits.getUnitByDefault();
            if (askIfToChangeTempUnit()) {
                temperatureUnit = askTemperatureUnit();
            }

            WeatherRequest request = weatherRequestFactory.makeWeatherRequest(cityName, temperatureUnit);
            System.out.println("Getting reports...");
            CurrentWeatherReport currentWeatherReport = weatherRepository.getCurrentWeatherReport(request);
            WeatherForecastReport weatherForecastReport = weatherRepository.getWeatherForecastReport(request);

            printReportsData(currentWeatherReport, weatherForecastReport);
        } catch (APIDataNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private String askCityName() {
        String cityName;
        while ((cityName = consoleReader.readValueFromConsole("Enter city name:"))
                .replace(' ', (char) 0).isEmpty()) {
            System.out.println("Incorrect city name! Try again.");
        }
        return cityName;
    }

    private boolean askIfToChangeTempUnit() {
        System.out.println(String.format("Temperature unit is set to %s by default.",
                Constants.TemperatureUnits.getUnitByDefault()));
        return consoleReader.readValueFromConsole("Do you want to change it? (y - yes): ").toLowerCase().equals("y");
    }

    private Constants.TemperatureUnits askTemperatureUnit() {
        while (true) {
            try {
                return Constants.TemperatureUnits.of(consoleReader.readValueFromConsole("Enter temperature unit:"));
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void printReportsData(CurrentWeatherReport currentWeatherReport,
                                  WeatherForecastReport weatherForecastReport) {
        String tempSymbol = currentWeatherReport.getTemperatureUnit().getSymbol();

        System.out.println("Weather report: ");
        System.out.println("City name: " + currentWeatherReport.getCityName());
        System.out.println("Country: " + currentWeatherReport.getCountryCode());
        System.out.println("Coordinates (latitude:longitude): " + currentWeatherReport.getCoordinates());
        System.out.println("Units in report: " + currentWeatherReport.getTemperatureUnit() + " " + tempSymbol);
        System.out.println(String.format("Current temperature: %.02f%s",
                currentWeatherReport.getCurrentTemperature(), tempSymbol));
        System.out.println("Forecast: ");
        weatherForecastReport.getOneDayWeathers().forEach(w -> System.out.println(
                String.format("%s: Min: %.2f%s Max: %.2f%s", w.getCurrentDateTime().toString(),
                        w.getMinimumTemperature(), tempSymbol, w.getMaximumTemperature(), tempSymbol)));
    }
}
