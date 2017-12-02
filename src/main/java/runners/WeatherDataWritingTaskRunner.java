package runners;

import io.InputFileReader;
import io.OutputFileWriter;
import repository.OpenWeatherRepository;
import tasks.WeatherDataWritingTask;
import utility.FileReader;
import utility.FileWriter;
import weatherdata.WeatherDataCollector;

import java.io.IOException;

class WeatherDataWritingTaskRunner {
    public static void main(String[] args) throws IOException {
        WeatherDataWritingTask weatherDataWritingTask = new WeatherDataWritingTask(
                new WeatherDataCollector(new OpenWeatherRepository()),
                new InputFileReader(new FileReader()),
                new OutputFileWriter(new FileWriter()));

        weatherDataWritingTask.writeWeatherDataToFile("input.txt");
    }
}
