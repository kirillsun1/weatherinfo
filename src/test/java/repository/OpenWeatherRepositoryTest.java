package repository;

import exceptions.APIDataNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import utility.Constants;
import weatherdata.CurrentWeatherReport;
import weatherdata.WeatherForecastReport;
import weatherdata.WeatherRequest;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class OpenWeatherRepositoryTest {
    WeatherRepository repository;

    @Before
    public void openRepositoryForTest() {
        repository = new OpenWeatherRepository();
    }

    @Test
    public void testGetCurrentWeatherForTallinn() {
        WeatherRequest request = WeatherRequest.of("Tallinn", "EE");
        CurrentWeatherReport currentWeatherReport = null;
        try {
            currentWeatherReport = repository.getCurrentWeatherReport(request);
        } catch (APIDataNotFoundException e) {
            fail("Error occured: " + e.getMessage());
        }

        assertEquals(request.getCityName(), currentWeatherReport.getCityName());
        assertEquals(request.getCountryCode(), currentWeatherReport.getCountryCode());
    }

    @Test(expected = APIDataNotFoundException.class)
    public void testGetCurrentWeatherReportThrowExceptionIfIncorrectCity() throws APIDataNotFoundException {
        WeatherRequest request = WeatherRequest.of("kjjgyiv", "EE");
        repository.getCurrentWeatherReport(request);
    }

    @Test(expected = APIDataNotFoundException.class)
    public void testGetCurrentWeatherForTallinnIncorrectCountryCode() throws APIDataNotFoundException {
        WeatherRequest request = WeatherRequest.of("Tallinn", "EESE");
        WeatherRepository mockedRepository = Mockito.mock(OpenWeatherRepository.class);

        doThrow(new APIDataNotFoundException()).when(mockedRepository).getCurrentWeatherReport(request);

        mockedRepository.getCurrentWeatherReport(request);
    }

    @Test
    public void testCurrentWeatherReportWithDefaultTemperatureUnit() {
        WeatherRequest request = WeatherRequest.of("New York", "US");

        CurrentWeatherReport report = null;
        try {
            report = repository.getCurrentWeatherReport(request);
        } catch (APIDataNotFoundException e) {
            fail("Error occured: " + e.getMessage());
        }

        assertEquals(report.getCityName(), "New York");
        assertEquals(report.getCountryCode(), "US");
        assertEquals(report.getTemperatureUnit(), Constants.TemperatureUnits.getUnitByDefault());
    }

    @Test
    public void testCurrentWeatherReportWithNotDefaultTemperatureUnit() {
        WeatherRequest request = WeatherRequest.of("New York", "US", Constants.TemperatureUnits.METRIC);

        CurrentWeatherReport report = null;
        try {
            report = repository.getCurrentWeatherReport(request);
        } catch (APIDataNotFoundException e) {
            fail("Error occured: " + e.getMessage());
        }

        assertEquals(report.getCityName(), "New York");
        assertEquals(report.getCountryCode(), "US");
        assertEquals(report.getTemperatureUnit(), Constants.TemperatureUnits.METRIC);
    }

    @Test
    public void testGetWeatherForecastForTallinn() {
        WeatherRequest request = WeatherRequest.of("Tallinn", "EE");
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
        WeatherRequest request = WeatherRequest.of("NoCity", "EE");

        repository.getWeatherForecastReport(request);
    }

    @Test
    public void testWeatherForecastReportWithDefaultTemperatureUnit() {
        WeatherRequest request = WeatherRequest.of("New York", "US");

        WeatherForecastReport report = null;
        try {
            report = repository.getWeatherForecastReport(request);
        } catch (APIDataNotFoundException e) {
            fail("Error occured: " + e.getMessage());
        }

        assertEquals(report.getCityName(), "New York");
        assertEquals(report.getCountryCode(), "US");
        assertEquals(report.getTemperatureUnit(), Constants.TemperatureUnits.getUnitByDefault());
    }

    @Test
    public void testWeatherForecastReportWithNotDefaultTemperatureUnit() {
        WeatherRequest request = WeatherRequest.of("New York", "US", Constants.TemperatureUnits.IMPERIAL);

        WeatherForecastReport report = null;
        try {
            report = repository.getWeatherForecastReport(request);
        } catch (APIDataNotFoundException e) {
            fail("Error occured: " + e.getMessage());
        }

        assertEquals(report.getCityName(), "New York");
        assertEquals(report.getCountryCode(), "US");
        assertEquals(report.getTemperatureUnit(), Constants.TemperatureUnits.IMPERIAL);
    }
}