package repository;

import exceptions.APIDataNotFoundException;
import exceptions.IncorrectAPIOutputException;
import org.junit.Before;
import org.junit.Test;
import utility.Constants;
import weatherdata.CurrentWeatherReport;
import weatherdata.WeatherForecastReport;
import weatherrequest.WeatherRequest;
import weatherrequest.WeatherRequestFactory;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OpenWeatherRepositoryTest {
    private final WeatherRequestFactory weatherRequestFactory = new WeatherRequestFactory();
    private WeatherRepository repository;

    @Before
    public void setUp() {
        repository = new OpenWeatherRepository();
    }

    @Test
    public void testRepositoryGivesCurrentWeatherReportForTheSameCityAsInRequest() {
        WeatherRequest request = weatherRequestFactory.makeWeatherRequest("Tallinn", "EE");

        CurrentWeatherReport currentWeatherReport = repository.getCurrentWeatherReport(request);

        assertEquals(request.getCityName(), currentWeatherReport.getCityName());
        assertEquals(request.getCountryCode().get(), currentWeatherReport.getCountryCode());
        // assertEquals(300.00, currentWeatherReport.getCurrentTemperature(), 0.01); // cannot know for sure
    }

    @Test(expected = APIDataNotFoundException.class)
    public void testGetCurrentWeatherReportThrowsExceptionIfIncorrectCity() throws APIDataNotFoundException {
        WeatherRequest request = weatherRequestFactory.makeWeatherRequest("kjjgyiv", "EE");

        repository.getCurrentWeatherReport(request);
    }

    @Test
    public void testTemperatureUnitInCurrentWeatherReportIsTheSameIfItIsNotSpecifiedInRequest() {
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
    public void testTempUnitInTheCurrentWeatherReportIsTheSameAsInRequest() {
        WeatherRequest request = weatherRequestFactory.makeWeatherRequest("New York", "US",
                Constants.TemperatureUnits.METRIC);

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
    public void testRepositoryGivesForecastForTheSameCityAsInRequest() {
        WeatherRequest request = weatherRequestFactory.makeWeatherRequest("Tallinn", "EE");

        WeatherForecastReport weatherForecastReport = null;
        try {
            weatherForecastReport = repository.getWeatherForecastReport(request);
        } catch (APIDataNotFoundException e) {
            fail("Error occurred: " + e.getMessage());
        }

        assertEquals(request.getCityName(), weatherForecastReport.getCityName());
        assertEquals(request.getCountryCode().get(), weatherForecastReport.getCountryCode());
    }

    @Test(expected = APIDataNotFoundException.class)
    public void testGetForecastReportThrowsExceptionIfCityIsNotCorrect() throws APIDataNotFoundException {
        WeatherRequest request = weatherRequestFactory.makeWeatherRequest("NoCity", "EE");

        repository.getWeatherForecastReport(request);
    }

    @Test
    public void testTemperatureUnitInForecastIsTheSameIfItIsNotSpecifiedInRequest() {
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
    public void testTempUnitInForecastReportIsTheSameAsInRequest() {
        WeatherRequest request = weatherRequestFactory.makeWeatherRequest("New York", "US",
                Constants.TemperatureUnits.IMPERIAL);

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