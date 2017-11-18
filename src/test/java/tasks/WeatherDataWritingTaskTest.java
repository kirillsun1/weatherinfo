package tasks;

import io.InputFileReader;
import io.OutputFileWriter;
import io.RequestFile;
import org.junit.Test;
import repository.WeatherRepository;
import utility.*;
import weatherdata.WeatherDataCollector;
import weatherrequest.WeatherRequest;

import java.io.IOException;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class WeatherDataWritingTaskTest {
    @Test
    public void testWeatherCollectorUsesWeatherRepositoryMethods() {
        try {
            WeatherRepository mockedRepository = mock(WeatherRepository.class);
            InputFileReader mockedReader = mock(InputFileReader.class);
            RequestFile mockedRequestFile = mock(RequestFile.class);
            OutputFileWriter mockedWriter = mock(OutputFileWriter.class);

            when(mockedRequestFile.getCitiesNames()).thenReturn(new String[]{"Tallinn", "Tartu"});
            when(mockedRequestFile.getTemperatureUnit()).thenReturn(Constants.TemperatureUnits.METRIC.toString());
            when(mockedReader.readFromFile(anyString())).thenReturn(mockedRequestFile);

            WeatherDataWritingTask weatherDataWritingTask = new WeatherDataWritingTask(
                    new WeatherDataCollector(mockedRepository), mockedReader, mockedWriter);

            weatherDataWritingTask.writeWeatherDataToFile("1");

            verify(mockedRepository, times(2)).getWeatherForecastReport(any(WeatherRequest.class));
            verify(mockedRepository, times(2)).getCurrentWeatherReport(any(WeatherRequest.class));
        } catch (IOException ex) {
            fail(ex.getMessage());
        }
    }
}