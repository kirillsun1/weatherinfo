package weatherdata;

import exceptions.APIDataNotFoundException;
import io.ReportFile;
import org.junit.Before;
import org.junit.Test;
import repository.WeatherRepository;
import weatherrequest.WeatherRequest;
import weatherrequest.WeatherRequestFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WeatherDataCollectorTest {
    private WeatherRequestFactory weatherRequestFactory;
    private WeatherRepository weatherRepositoryMock;
    private WeatherDataCollector weatherDataCollector;

    @Before
    public void setUp() {
        weatherRequestFactory = new WeatherRequestFactory();
        weatherRepositoryMock = mock(WeatherRepository.class);
        weatherDataCollector = new WeatherDataCollector(weatherRepositoryMock);
    }

    @Test
    public void testSameAmountOfReportFilesAsRequestsIfAllCitiesAreCorrect() {
        List<WeatherRequest> weatherRequests = new ArrayList<>();

        WeatherRequest request1 = weatherRequestFactory.makeWeatherRequest("Tallinn");
        WeatherRequest request2 = weatherRequestFactory.makeWeatherRequest("Maardu");
        WeatherRequest request3 = weatherRequestFactory.makeWeatherRequest("Viljandi");

        weatherRequests.add(request1);
        weatherRequests.add(request2);
        weatherRequests.add(request3);

        when(weatherRepositoryMock.getCurrentWeatherReport(request1)).thenReturn(mock(CurrentWeatherReport.class));
        when(weatherRepositoryMock.getCurrentWeatherReport(request2)).thenReturn(mock(CurrentWeatherReport.class));
        when(weatherRepositoryMock.getCurrentWeatherReport(request3)).thenReturn(mock(CurrentWeatherReport.class));

        when(weatherRepositoryMock.getWeatherForecastReport(request1)).thenReturn(mock(WeatherForecastReport.class));
        when(weatherRepositoryMock.getWeatherForecastReport(request2)).thenReturn(mock(WeatherForecastReport.class));
        when(weatherRepositoryMock.getWeatherForecastReport(request3)).thenReturn(mock(WeatherForecastReport.class));

        List<ReportFile> reportFiles = weatherDataCollector.getReportFiles(weatherRequests);
        assertEquals(3, reportFiles.size());
    }

    @Test
    public void testNotSameAmountOfReportFilesAsRequestsIfNotAllCitiesAreCorrect() {
        List<WeatherRequest> weatherRequests = new ArrayList<>();

        WeatherRequest request1 = weatherRequestFactory.makeWeatherRequest("Tallinn");
        WeatherRequest request2 = weatherRequestFactory.makeWeatherRequest("Maardu");
        WeatherRequest request3 = weatherRequestFactory.makeWeatherRequest("123");

        weatherRequests.add(request1);
        weatherRequests.add(request2);
        weatherRequests.add(request3);

        when(weatherRepositoryMock.getCurrentWeatherReport(request1)).thenReturn(mock(CurrentWeatherReport.class));
        when(weatherRepositoryMock.getCurrentWeatherReport(request2)).thenReturn(mock(CurrentWeatherReport.class));
        when(weatherRepositoryMock.getCurrentWeatherReport(request3)).thenThrow(APIDataNotFoundException.class);

        when(weatherRepositoryMock.getWeatherForecastReport(request1)).thenReturn(mock(WeatherForecastReport.class));
        when(weatherRepositoryMock.getWeatherForecastReport(request2)).thenReturn(mock(WeatherForecastReport.class));
        when(weatherRepositoryMock.getWeatherForecastReport(request3)).thenThrow(APIDataNotFoundException.class);

        List<ReportFile> reportFiles = weatherDataCollector.getReportFiles(weatherRequests);
        assertEquals(2, reportFiles.size());
    }

    @Test
    public void tesNoReportFilesIfAllCitiesAreIncorrect() {
        List<WeatherRequest> weatherRequests = new ArrayList<>();

        WeatherRequest request1 = weatherRequestFactory.makeWeatherRequest("456");
        WeatherRequest request2 = weatherRequestFactory.makeWeatherRequest("777");
        WeatherRequest request3 = weatherRequestFactory.makeWeatherRequest("123");

        weatherRequests.add(request1);
        weatherRequests.add(request2);
        weatherRequests.add(request3);

        when(weatherRepositoryMock.getCurrentWeatherReport(request1)).thenThrow(APIDataNotFoundException.class);
        when(weatherRepositoryMock.getCurrentWeatherReport(request2)).thenThrow(APIDataNotFoundException.class);
        when(weatherRepositoryMock.getCurrentWeatherReport(request3)).thenThrow(APIDataNotFoundException.class);

        when(weatherRepositoryMock.getWeatherForecastReport(request1)).thenThrow(APIDataNotFoundException.class);
        when(weatherRepositoryMock.getWeatherForecastReport(request2)).thenThrow(APIDataNotFoundException.class);
        when(weatherRepositoryMock.getWeatherForecastReport(request3)).thenThrow(APIDataNotFoundException.class);

        List<ReportFile> reportFiles = weatherDataCollector.getReportFiles(weatherRequests);
        assertEquals(0, reportFiles.size());
    }
}