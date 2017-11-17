package repository;

import exceptions.APIDataNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import utility.Constants;
import weatherdata.CurrentWeatherReport;
import weatherdata.WeatherForecastReport;
import weatherrequest.WeatherRequest;
import weatherrequest.WeatherRequestFactory;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class OpenWeatherRepositoryTest {
    private WeatherRepository repositoryMock;

    private final WeatherRequestFactory weatherRequestFactory = new WeatherRequestFactory();

    @Before
    public void setUp() {
        repositoryMock = mock(OpenWeatherRepository.class);
    }

    @Test
    public void testGetCurrentWeatherForTallinn() {
        WeatherRequest request = weatherRequestFactory.makeWeatherRequest("Tallinn", "EE");

        CurrentWeatherReport currentWeatherReportMock = mock(CurrentWeatherReport.class);
        when(currentWeatherReportMock.getCityName()).thenReturn("Tallinn");
        when(currentWeatherReportMock.getCountryCode()).thenReturn("EE");
        when(currentWeatherReportMock.getCurrentTemperature()).thenReturn(300.00);

        when(repositoryMock.getCurrentWeatherReport(request)).thenReturn(currentWeatherReportMock);

        CurrentWeatherReport currentWeatherReport = repositoryMock.getCurrentWeatherReport(request);

        assertEquals(request.getCityName(), currentWeatherReport.getCityName());
        assertEquals(request.getCountryCode().get(), currentWeatherReport.getCountryCode());
        assertEquals(300.00, currentWeatherReport.getCurrentTemperature(), 0.01);
    }

    @Test(expected = APIDataNotFoundException.class)
    public void testGetCurrentWeatherReportThrowExceptionIfIncorrectCity() throws APIDataNotFoundException {
        WeatherRequest request = weatherRequestFactory.makeWeatherRequest("kjjgyiv", "EE");

        when(repositoryMock.getCurrentWeatherReport(request)).thenThrow(APIDataNotFoundException.class);

        repositoryMock.getCurrentWeatherReport(request);
    }

    @Test(expected = APIDataNotFoundException.class)
    public void testGetCurrentWeatherForTallinnIncorrectCountryCode() throws APIDataNotFoundException {
        WeatherRequest request = weatherRequestFactory.makeWeatherRequest("Tallinn", "EESE");
        WeatherRepository mockedRepository = Mockito.mock(OpenWeatherRepository.class);

        doThrow(new APIDataNotFoundException()).when(mockedRepository).getCurrentWeatherReport(request);

        mockedRepository.getCurrentWeatherReport(request);
    }

    @Test
    public void testCurrentWeatherReportWithDefaultTemperatureUnit() {
        WeatherRequest request = weatherRequestFactory.makeWeatherRequest("New York", "US");

        CurrentWeatherReport currentWeatherReportMock = mock(CurrentWeatherReport.class);
        when(currentWeatherReportMock.getCityName()).thenReturn("New York");
        when(currentWeatherReportMock.getCountryCode()).thenReturn("US");
        when(currentWeatherReportMock.getTemperatureUnit()).thenReturn(Constants.TemperatureUnits.STANDART);

        when(repositoryMock.getCurrentWeatherReport(request)).thenReturn(currentWeatherReportMock);

        CurrentWeatherReport report = null;
        try {
            report = repositoryMock.getCurrentWeatherReport(request);
        } catch (APIDataNotFoundException e) {
            fail("Error occurred: " + e.getMessage());
        }

        assertEquals("New York", report.getCityName());
        assertEquals("US", report.getCountryCode());
        assertEquals(Constants.TemperatureUnits.getUnitByDefault(), report.getTemperatureUnit());
    }

    @Test
    public void testCurrentWeatherReportWithNotDefaultTemperatureUnit() {
        WeatherRequest request = weatherRequestFactory.makeWeatherRequest("New York", "US", Constants.TemperatureUnits.METRIC);

        CurrentWeatherReport currentWeatherReportMock = mock(CurrentWeatherReport.class);
        when(currentWeatherReportMock.getCityName()).thenReturn("New York");
        when(currentWeatherReportMock.getCountryCode()).thenReturn("US");
        when(currentWeatherReportMock.getTemperatureUnit()).thenReturn(Constants.TemperatureUnits.METRIC);

        when(repositoryMock.getCurrentWeatherReport(request)).thenReturn(currentWeatherReportMock);

        CurrentWeatherReport report = null;
        try {
            report = repositoryMock.getCurrentWeatherReport(request);
        } catch (APIDataNotFoundException e) {
            fail("Error occurred: " + e.getMessage());
        }

        assertEquals("New York", report.getCityName());
        assertEquals("US", report.getCountryCode());
        assertEquals(Constants.TemperatureUnits.METRIC, report.getTemperatureUnit());
    }

    @Test
    public void testGetWeatherForecastForTallinn() {
        WeatherRequest request = weatherRequestFactory.makeWeatherRequest("Tallinn", "EE");

        WeatherForecastReport currentWeatherReportMock = mock(WeatherForecastReport.class);
        when(currentWeatherReportMock.getCityName()).thenReturn("Tallinn");
        when(currentWeatherReportMock.getCountryCode()).thenReturn("EE");
        // when(currentWeatherReportMock.getTemperatureUnit()).thenReturn(Constants.TemperatureUnits.METRIC);

        when(repositoryMock.getWeatherForecastReport(request)).thenReturn(currentWeatherReportMock);

        WeatherForecastReport weatherForecastReport = null;
        try {
            weatherForecastReport = repositoryMock.getWeatherForecastReport(request);
        } catch (APIDataNotFoundException e) {
            fail("Error occured: " + e.getMessage());
        }

        assertEquals(request.getCityName(), weatherForecastReport.getCityName());
        assertEquals(request.getCountryCode().get(), weatherForecastReport.getCountryCode());
    }

    @Test(expected = APIDataNotFoundException.class)
    public void testGetWeatherForecastForIncorrectCity() throws APIDataNotFoundException {
        WeatherRequest request = weatherRequestFactory.makeWeatherRequest("NoCity", "EE");

        when(repositoryMock.getWeatherForecastReport(request)).thenThrow(APIDataNotFoundException.class);

        repositoryMock.getWeatherForecastReport(request);
    }

    @Test
    public void testWeatherForecastReportWithDefaultTemperatureUnit() {
        WeatherRequest request = weatherRequestFactory.makeWeatherRequest("New York", "US");

        WeatherForecastReport currentWeatherReportMock = mock(WeatherForecastReport.class);
        when(currentWeatherReportMock.getCityName()).thenReturn("New York");
        when(currentWeatherReportMock.getCountryCode()).thenReturn("US");
        when(currentWeatherReportMock.getTemperatureUnit()).thenReturn(Constants.TemperatureUnits.STANDART);

        when(repositoryMock.getWeatherForecastReport(request)).thenReturn(currentWeatherReportMock);

        WeatherForecastReport report = null;
        try {
            report = repositoryMock.getWeatherForecastReport(request);
        } catch (APIDataNotFoundException e) {
            fail("Error occurred: " + e.getMessage());
        }

        assertEquals("New York", report.getCityName());
        assertEquals("US", report.getCountryCode());
        assertEquals(Constants.TemperatureUnits.getUnitByDefault(), report.getTemperatureUnit());
    }

    @Test
    public void testWeatherForecastReportWithNotDefaultTemperatureUnit() {
        WeatherRequest request = weatherRequestFactory.makeWeatherRequest("New York", "US",
                Constants.TemperatureUnits.IMPERIAL);

        WeatherForecastReport currentWeatherReportMock = mock(WeatherForecastReport.class);
        when(currentWeatherReportMock.getCityName()).thenReturn("New York");
        when(currentWeatherReportMock.getCountryCode()).thenReturn("US");
        when(currentWeatherReportMock.getTemperatureUnit()).thenReturn(Constants.TemperatureUnits.IMPERIAL);

        when(repositoryMock.getWeatherForecastReport(request)).thenReturn(currentWeatherReportMock);

        WeatherForecastReport report = null;
        try {
            report = repositoryMock.getWeatherForecastReport(request);
        } catch (APIDataNotFoundException e) {
            fail("Error occurred: " + e.getMessage());
        }

        assertEquals("New York", report.getCityName());
        assertEquals("US", report.getCountryCode());
        assertEquals(Constants.TemperatureUnits.IMPERIAL, report.getTemperatureUnit());
    }
}