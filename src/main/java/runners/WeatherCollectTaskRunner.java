package runners;

import tasks.WeatherCollectTask;
import repository.OpenWeatherRepository;
import utility.FileReader;
import utility.FileWriter;
import utility.InputFileReader;
import utility.OutputFileWriter;

import java.io.IOException;

public class WeatherCollectTaskRunner {
    public static void main(String[] args) {
        WeatherCollectTask weatherCollectTask = new WeatherCollectTask(new OpenWeatherRepository(),
                new InputFileReader(new FileReader()), new OutputFileWriter(new FileWriter()));
        try {
            weatherCollectTask.saveWeatherDataToFile("input.txt", "output.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
