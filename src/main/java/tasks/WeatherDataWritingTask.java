package tasks;

import io.InputFileReader;
import io.OutputFileWriter;
import io.ReportFile;
import weatherdata.WeatherDataCollector;
import weatherrequest.WeatherRequest;
import weatherrequest.WeatherRequestFactory;

import java.io.IOException;
import java.util.List;

public class WeatherDataWritingTask {
    private final WeatherDataCollector weatherDataCollector;
    private final InputFileReader inputFileReader;
    private final OutputFileWriter outputFileWriter;

    public WeatherDataWritingTask(WeatherDataCollector weatherDataCollector, InputFileReader inputFileReader,
                                  OutputFileWriter outputFileWriter) {
        this.weatherDataCollector = weatherDataCollector;
        this.inputFileReader = inputFileReader;
        this.outputFileWriter = outputFileWriter;
    }

    public void writeWeatherDataToFile(String pathToInputFile) throws IOException {
        WeatherRequestFactory weatherRequestFactory = new WeatherRequestFactory();

        List<WeatherRequest> requests =
                weatherRequestFactory.makeWeatherRequests(inputFileReader.readFromFile(pathToInputFile));

        for (ReportFile reportFile : weatherDataCollector.getReportFiles(requests)) {
            outputFileWriter.writeReportToFile(reportFile);
        }
    }
}
