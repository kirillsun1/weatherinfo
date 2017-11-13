package tasks;

import iofiles.ReportFile;
import iofiles.RequestFile;
import org.junit.Test;
import repository.WeatherRepository;
import utility.*;
import weatherdata.CurrentWeatherReport;
import weatherdata.WeatherForecastReport;
import weatherrequest.WeatherRequest;

import java.io.IOException;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class WeatherCollectTaskTest {
    @Test
    public void testWeatherCollectorUsesWeatherRepositoryMethods() {
        try {
            WeatherRepository mockedRepository = mock(WeatherRepository.class);
            InputFileReader mockedReader = mock(InputFileReader.class);
            RequestFile mockedRequestFile = mock(RequestFile.class);
            OutputFileWriter mockedWriter = mock(OutputFileWriter.class);

            when(mockedRequestFile.getTemperatureUnit()).thenReturn(Constants.TemperatureUnits.METRIC.toString());
            when(mockedReader.readFromFile(anyString())).thenReturn(mockedRequestFile);

            WeatherCollectTask weatherCollectTask = new WeatherCollectTask(mockedRepository, mockedReader, mockedWriter);

            weatherCollectTask.saveWeatherDataToFile("1", "2");

            verify(mockedRepository, times(1)).getWeatherForecastReport(any(WeatherRequest.class));
            verify(mockedRepository, times(1)).getCurrentWeatherReport(any(WeatherRequest.class));
        } catch (IOException ex) {
            fail(ex.getMessage());
        }
    }
}