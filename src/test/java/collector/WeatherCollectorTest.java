package collector;

import org.junit.Test;
import repository.WeatherRepository;
import utility.FileReader;
import utility.FileWriter;
import weatherdata.CurrentWeatherReport;
import weatherdata.WeatherForecastReport;
import weatherdata.WeatherRequest;

import java.io.IOException;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WeatherCollectorTest {
    @Test
    public void testWeatherCollectorUsesWeatherRepositoryMethods() {
        FileReader mockedReader = mock(FileReader.class);
        try {
            when(mockedReader.readFile(anyString())).thenReturn("{cityname:\"Tallinn\", country:\"EE\"}");
        } catch (IOException e) {
            fail("Error occurred: " + e.getMessage());
        }
        FileWriter mockedWriter = mock(FileWriter.class);
        WeatherRepository mockedRepo = mock(WeatherRepository.class);

        // TODO: MORE MOCKS??

        fail("don't know how to write");

        when(mockedRepo.getCurrentWeatherReport(any(WeatherRequest.class))).thenReturn(mock(CurrentWeatherReport.class));
        when(mockedRepo.getWeatherForecastReport(any(WeatherRequest.class))).thenReturn(mock(WeatherForecastReport.class));

        WeatherCollector collector = new WeatherCollector(mockedRepo, mockedReader, mockedWriter);

        try {
            collector.saveWeatherDataToFile("1", "2");
        } catch (IOException e) {
            fail("Error occurred: " + e.getMessage());
        }

        verify(mockedRepo).getCurrentWeatherReport(any(WeatherRequest.class));
        verify(mockedRepo).getWeatherForecastReport(any(WeatherRequest.class));
    }
}