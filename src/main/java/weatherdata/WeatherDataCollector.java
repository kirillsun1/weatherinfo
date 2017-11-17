package weatherdata;

import exceptions.APIDataNotFoundException;
import io.ReportFile;
import repository.WeatherRepository;
import weatherrequest.WeatherRequest;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

public class WeatherDataCollector {
    private static final Logger LOGGER = Logger.getLogger(WeatherDataCollector.class.getName());

    private final WeatherRepository weatherRepository;

    static {
        try {
            FileHandler fileHandler = new FileHandler("collector_log.txt");
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WeatherDataCollector(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public List<ReportFile> getReportFiles(List<WeatherRequest> requests) {
        return requests.stream()
                .map(request -> {
                    try {
                        CurrentWeatherReport currentWeatherReport = weatherRepository.getCurrentWeatherReport(request);
                        WeatherForecastReport weatherForecastReport = weatherRepository.getWeatherForecastReport(request);

                        return new ReportFile(request.getCityName(), currentWeatherReport, weatherForecastReport);
                    } catch (APIDataNotFoundException ex) {
                        LOGGER.warning(String.format("City \"%s\" skipped due to report getting fail.",
                                request.getCityName()));
                        return null; // will be soon filtered out from the list
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
