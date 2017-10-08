package repository;

import exceptions.APIDataNotFoundException;
import exceptions.IncorrectAPIOutputException;
import org.junit.Test;
import weatherdata.CurrentWeatherReport;
import weatherdata.WeatherForecastReport;
import weatherdata.WeatherRequest;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class OpenWeatherRepositoryTest {
    @Test
    public void testGetCurrentWeatherForTallinn() throws APIDataNotFoundException, IncorrectAPIOutputException,
            IOException {
        WeatherRequest request = WeatherRequest.fromCityNameAndCode("Tallinn", "EE");
        WeatherRepository repository = new OpenWeatherRepository();
        CurrentWeatherReport currentWeatherReport = repository.getCurrentWeatherReport(request);

        assertEquals(currentWeatherReport.getCityName(), request.getCityName());
    }

    @Test
    public void testGetWeatherForecastForTallinn() throws APIDataNotFoundException {
        WeatherRequest request = WeatherRequest.fromCityNameAndCode("Tallinn", "EE");
        WeatherRepository repository = new OpenWeatherRepository();
        WeatherForecastReport weatherForecastReport = repository.getWeatherForecastReport(request);

        assertEquals(weatherForecastReport.getCityName(), request.getCityName());
    }

    @Test(expected = APIDataNotFoundException.class)
    public void testGetCurrentWeatherForIncorrectCity() throws APIDataNotFoundException, IncorrectAPIOutputException,
            IOException {
        WeatherRequest request = WeatherRequest.fromCityNameAndCode("NoCity", "EE");
        WeatherRepository repository = new OpenWeatherRepository();
        repository.getCurrentWeatherReport(request);
    }

    @Test(expected = APIDataNotFoundException.class)
    public void testGetCurrentWeatherForTallinnIncorrectCountryCode() throws APIDataNotFoundException,
            IncorrectAPIOutputException, IOException {
        WeatherRequest request = WeatherRequest.fromCityNameAndCode("Tallinn", "EESE");
        WeatherRepository repository = new OpenWeatherRepository();

        // TODO: Ask, if it is okay that API does not consider invalid country code.
        System.out.println(repository.getCurrentWeatherReport(request));
    }

    @Test(expected = APIDataNotFoundException.class)
    public void testGetWeatherForecastForIncorrectCity() throws APIDataNotFoundException {
        WeatherRequest request = WeatherRequest.fromCityNameAndCode("NoCity", "EE");
        WeatherRepository repository = new OpenWeatherRepository();
        repository.getWeatherForecastReport(request);
    }
}