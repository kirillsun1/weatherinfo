package runners;

import weatherdata.WeatherDataCollector;
import tasks.WeatherDataWritingTask;
import repository.OpenWeatherRepository;
import utility.FileReader;
import utility.FileWriter;
import io.InputFileReader;
import io.OutputFileWriter;

import java.io.IOException;

class WeatherDataWritingTaskRunner {
    public static void main(String[] args) {
        try {
            WeatherDataWritingTask weatherDataWritingTask = new WeatherDataWritingTask(
                    new WeatherDataCollector(new OpenWeatherRepository()),
                    new InputFileReader(new FileReader()),
                    new OutputFileWriter(new FileWriter()));

            weatherDataWritingTask.writeWeatherDataToFile("input.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
