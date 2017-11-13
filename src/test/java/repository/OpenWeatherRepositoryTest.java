package repository;

import exceptions.APIDataNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import utility.Constants;
import weatherdata.CurrentWeatherReport;
import weatherdata.WeatherForecastReport;
import weatherrequest.WeatherRequest;
import weatherrequest.WeatherRequestFactory;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class OpenWeatherRepositoryTest {
    private WeatherRepository repository;
    private WeatherRequestFactory weatherRequestFactory = new WeatherRequestFactory();

    @Before
    public void openRepositoryForTest() {
        repository = new OpenWeatherRepository();
    }

    @Test
    public void testGetCurrentWeatherForTallinn() {
        WeatherRequest request = weatherRequestFactory.makeWeatherRequest("Tallinn", "EE");
        CurrentWeatherReport currentWeatherReport = null;
        try {
            currentWeatherReport = repository.getCurrentWeatherReport(request);
        } catch (APIDataNotFoundException e) {
            fail("Error occurred: " + e.getMessage());
        }

        assertEquals(request.getCityName(), currentWeatherReport.getCityName());
        assertEquals(request.getCountryCode(), currentWeatherReport.getCountryCode());
    }

    @Test(expected = APIDataNotFoundException.class)
    public void testGetCurrentWeatherReportThrowExceptionIfIncorrectCity() throws APIDataNotFoundException {
        WeatherRequest request = weatherRequestFactory.makeWeatherRequest("kjjgyiv", "EE");
        repository.getCurrentWeatherReport(request);
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

        CurrentWeatherReport report = null;
        try {
            report = repository.getCurrentWeatherReport(request);
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

        CurrentWeatherReport report = null;
        try {
            report = repository.getCurrentWeatherReport(request);
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
        WeatherForecastReport weatherForecastReport = null;
        try {
            weatherForecastReport = repository.getWeatherForecastReport(request);
        } catch (APIDataNotFoundException e) {
            fail(e.getMessage());
        }

        assertEquals(request.getCityName(), weatherForecastReport.getCityName());
        assertEquals(request.getCountryCode(), weatherForecastReport.getCountryCode());
    }

    @Test(expected = APIDataNotFoundException.class)
    public void testGetWeatherForecastForIncorrectCity() throws APIDataNotFoundException {
        WeatherRequest request = weatherRequestFactory.makeWeatherRequest("NoCity", "EE");

        repository.getWeatherForecastReport(request);
    }

    @Test
    public void testWeatherForecastReportWithDefaultTemperatureUnit() {
        WeatherRequest request = weatherRequestFactory.makeWeatherRequest("New York", "US");

        WeatherForecastReport report = null;
        try {
            report = repository.getWeatherForecastReport(request);
        } catch (APIDataNotFoundException e) {
            fail("Error occurred: " + e.getMessage());
        }

        assertEquals("New York", report.getCityName());
        assertEquals("US", report.getCountryCode());
        assertEquals(Constants.TemperatureUnits.getUnitByDefault(), report.getTemperatureUnit());
    }

    @Test
    public void testWeatherForecastReportWithNotDefaultTemperatureUnit() {
        WeatherRequest request = weatherRequestFactory.makeWeatherRequest("New York", "US", Constants.TemperatureUnits.IMPERIAL);

        WeatherForecastReport report = null;
        try {
            report = repository.getWeatherForecastReport(request);
        } catch (APIDataNotFoundException e) {
            fail("Error occurred: " + e.getMessage());
        }

        assertEquals("New York", report.getCityName());
        assertEquals("US", report.getCountryCode());
        assertEquals(Constants.TemperatureUnits.IMPERIAL, report.getTemperatureUnit());
    }
}