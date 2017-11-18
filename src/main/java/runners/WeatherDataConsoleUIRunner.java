package runners;

import io.ConsoleReader;
import repository.OpenWeatherRepository;
import ui.WeatherDataConsoleUI;

public class WeatherDataConsoleUIRunner {
    public static void main(String[] args) {
        new WeatherDataConsoleUI(new ConsoleReader(), new OpenWeatherRepository()).start();
    }
}
