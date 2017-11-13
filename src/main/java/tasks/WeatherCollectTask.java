package tasks;

import com.google.gson.Gson;
import iofiles.ReportFile;
import repository.WeatherRepository;
import utility.InputFileReader;
import utility.OutputFileWriter;
import weatherdata.CurrentWeatherReport;
import weatherdata.WeatherForecastReport;
import weatherrequest.WeatherRequest;
import weatherrequest.WeatherRequestFactory;

import java.io.IOException;

public class WeatherCollectTask {
    private final WeatherRepository weatherRepository;
    private final InputFileReader reader;
    private final OutputFileWriter writer;

    public WeatherCollectTask(WeatherRepository weatherRepository, InputFileReader reader, OutputFileWriter writer) {
        this.weatherRepository = weatherRepository;
        this.reader = reader;
        this.writer = writer;
    }

    public void saveWeatherDataToFile(String pathToInputFile, String pathToOutputFile) throws IOException {
        WeatherRequestFactory weatherRequestFactory = new WeatherRequestFactory();

        WeatherRequest request = weatherRequestFactory.makeWeatherRequest(reader.readFromFile(pathToInputFile));

        CurrentWeatherReport currentWeatherReport = weatherRepository.getCurrentWeatherReport(request);
        WeatherForecastReport weatherForecastReport = weatherRepository.getWeatherForecastReport(request);

        ReportFile outputFile = new ReportFile(currentWeatherReport, weatherForecastReport);

        writer.writeToFile(outputFile, pathToOutputFile);
    }
}
