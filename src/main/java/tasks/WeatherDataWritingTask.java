package tasks;

import io.ReportFile;
import io.InputFileReader;
import io.OutputFileWriter;
import weatherdata.WeatherDataCollector;
import weatherrequest.WeatherRequest;
import weatherrequest.WeatherRequestFactory;

import java.io.IOException;
import java.util.List;

public class WeatherDataWritingTask {
    private static final String OUTPUT_FILE_PATH = "weather_%s.json";

    private final WeatherDataCollector weatherDataCollector;
    private final InputFileReader inputFileReader;
    private final OutputFileWriter outputFileWriter;

    public WeatherDataWritingTask(WeatherDataCollector weatherDataCollector, InputFileReader inputFileReader,
                                  OutputFileWriter outputFileWriter) {
        this.weatherDataCollector = weatherDataCollector;
        this.inputFileReader = inputFileReader;
        this.outputFileWriter = outputFileWriter;
    }

    private static String getOutputFilePath(String cityName) {
        return String.format(OUTPUT_FILE_PATH, cityName);
    }

    public void writeWeatherDataToFile(String pathToInputFile) throws IOException {
        WeatherRequestFactory weatherRequestFactory = new WeatherRequestFactory();

        List<WeatherRequest> requests =
                weatherRequestFactory.makeWeatherRequests(inputFileReader.readFromFile(pathToInputFile));

        for (ReportFile reportFile : weatherDataCollector.getReportFiles(requests)) {
            outputFileWriter.writeToFile(reportFile, getOutputFilePath(reportFile.getCityName()));
        }
    }
}
