package runners;

import collector.WeatherCollector;
import repository.OpenWeatherRepository;
import utility.FileReader;
import utility.FileWriter;

import java.io.IOException;

public class WeatherGetterRunner {
    public static void main(String[] args) {
        WeatherCollector weatherCollector = new WeatherCollector(new OpenWeatherRepository(),
                new FileReader(), new FileWriter());
        try {
            weatherCollector.saveWeatherDataToFile("input.txt", "output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
