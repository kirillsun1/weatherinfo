package weatherdata;

import com.google.gson.Gson;
import exceptions.IncorrectAPIOutputException;
import openweatherobjects.City;
import openweatherobjects.Forecast5Days3HoursData;
import org.junit.Before;
import org.junit.Test;
import utility.Constants;
import weatherrequest.WeatherRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WeatherForecastReportFabricTest {
    private Forecast5Days3HoursData testJSON;
    private Gson gson;
    private WeatherForecastReportFabric weatherForecastReportFabric;

    private HashMap<String, Object> generateForecastListObject(String dateTXT, Double tempMin, Double tempMax) {
        HashMap<String, Object> listObject = new HashMap<>();

        listObject.put("dt_txt", dateTXT);

        HashMap<String, Double> main = new HashMap<>();
        main.put("temp_min", tempMin);
        main.put("temp_max", tempMax);

        listObject.put("main", main);

        return listObject;
    }

    @SuppressWarnings("unchecked")
    private void setDataToTestJSON() {
        testJSON.city = new City();

        testJSON.city.name = "CityName";
        testJSON.city.country = "EE";
        testJSON.city.coordinates = new HashMap<>();
        testJSON.city.coordinates.put("lon", 111.11);
        testJSON.city.coordinates.put("lat", 113.11);

        testJSON.list = new HashMap[15];

        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        testJSON.list[0] = generateForecastListObject(today.format(formatter), 10.03, 20.20);
        testJSON.list[1] = generateForecastListObject(today.plusHours(3).format(formatter), 11.03, 30.20);
        testJSON.list[2] = generateForecastListObject(today.plusHours(6).format(formatter), 8.03, 40.20);

        testJSON.list[3] = generateForecastListObject(today.plusDays(1).format(formatter), 1.33, 30.26);
        testJSON.list[4] = generateForecastListObject(today.plusDays(1).plusHours(3).format(formatter), 2.43, 40.67);
        testJSON.list[5] = generateForecastListObject(today.plusDays(1).plusHours(6).format(formatter), 3.53, 50.88);

        testJSON.list[6] = generateForecastListObject(today.plusDays(2).format(formatter), 100.03, 150.20);
        testJSON.list[7] = generateForecastListObject(today.plusDays(2).plusHours(3).format(formatter), 200.03, 70.20);
        testJSON.list[8] = generateForecastListObject(today.plusDays(2).plusHours(6).format(formatter), 300.03, 900.20);

        testJSON.list[9] = generateForecastListObject(today.plusDays(3).format(formatter), 500.03, 600.20);
        testJSON.list[10] = generateForecastListObject(today.plusDays(3).plusHours(3).format(formatter), 600.03, 700.20);
        testJSON.list[11] = generateForecastListObject(today.plusDays(3).plusHours(6).format(formatter), 700.03, 999.20);

        testJSON.list[12] = generateForecastListObject(today.plusDays(4).format(formatter), 111.11, 666.66);
        testJSON.list[13] = generateForecastListObject(today.plusDays(4).plusHours(3).format(formatter), 222.34, 777.77);
        testJSON.list[14] = generateForecastListObject(today.plusDays(4).plusHours(6).format(formatter), 444.55, 878.77);
    }

    @Before
    public void setUp() {
        gson = new Gson();
        testJSON = new Forecast5Days3HoursData();
        weatherForecastReportFabric = new WeatherForecastReportFabric();

        setDataToTestJSON();
    }

    private String getJSONStringFromTestJSON() {
        return gson.toJson(testJSON);
    }

    @Test
    public void testCityDataIsCorrect() {
        WeatherRequest weatherRequestMock = mock(WeatherRequest.class);
        when(weatherRequestMock.getTemperatureUnit()).thenReturn(Constants.TemperatureUnits.STANDARD);

        WeatherForecastReport weatherForecastReport =
                weatherForecastReportFabric.createReportFromJSONAndRequest(getJSONStringFromTestJSON(),
                        weatherRequestMock);

        assertEquals("CityName", weatherForecastReport.getCityName());
        assertEquals("EE", weatherForecastReport.getCountryCode());
        assertEquals(Constants.TemperatureUnits.STANDARD, weatherForecastReport.getTemperatureUnit());
        assertEquals(113.11, weatherForecastReport.getCoordinates().getLatitude(), 0.01);
        assertEquals(111.11, weatherForecastReport.getCoordinates().getLongitude(), 0.01);
    }

    @Test
    public void testTemperatureUnitIsTheSameAsInWeatherRequest() {
        WeatherRequest weatherRequestMock = mock(WeatherRequest.class);
        when(weatherRequestMock.getTemperatureUnit()).thenReturn(Constants.TemperatureUnits.IMPERIAL);

        WeatherForecastReport weatherForecastReport =
                weatherForecastReportFabric.createReportFromJSONAndRequest(getJSONStringFromTestJSON(),
                        weatherRequestMock);

        assertEquals(Constants.TemperatureUnits.IMPERIAL, weatherForecastReport.getTemperatureUnit());
    }

    @Test(expected = IncorrectAPIOutputException.class)
    public void testThrowsExceptionIfIncorrectCountryCode() {
        WeatherRequest weatherRequestMock = mock(WeatherRequest.class);
        when(weatherRequestMock.getTemperatureUnit()).thenReturn(Constants.TemperatureUnits.STANDARD);

        testJSON.city.country = "AAA";

        weatherForecastReportFabric.createReportFromJSONAndRequest(getJSONStringFromTestJSON(), weatherRequestMock);
    }

    @Test(expected = IncorrectAPIOutputException.class)
    public void testThrowsExceptionIfCityNameIsNotSpecified() {
        WeatherRequest weatherRequestMock = mock(WeatherRequest.class);
        when(weatherRequestMock.getTemperatureUnit()).thenReturn(Constants.TemperatureUnits.STANDARD);

        testJSON.city.name = null;

        weatherForecastReportFabric.createReportFromJSONAndRequest(getJSONStringFromTestJSON(), weatherRequestMock);
    }

    @Test(expected = IncorrectAPIOutputException.class)
    public void testThrowsExceptionIfCountryCodeIsNotSpecified() {
        WeatherRequest weatherRequestMock = mock(WeatherRequest.class);
        when(weatherRequestMock.getTemperatureUnit()).thenReturn(Constants.TemperatureUnits.STANDARD);

        testJSON.city.country = null;

        weatherForecastReportFabric.createReportFromJSONAndRequest(getJSONStringFromTestJSON(), weatherRequestMock);
    }

    @Test(expected = IncorrectAPIOutputException.class)
    public void testThrowsExceptionIfCityIsNotSpecified() {
        WeatherRequest weatherRequestMock = mock(WeatherRequest.class);
        when(weatherRequestMock.getTemperatureUnit()).thenReturn(Constants.TemperatureUnits.STANDARD);

        testJSON.city = null;

        weatherForecastReportFabric.createReportFromJSONAndRequest(getJSONStringFromTestJSON(), weatherRequestMock);
    }

    @Test(expected = IncorrectAPIOutputException.class)
    public void testThrowsExceptioIfDataListIsNotSpecified() {
        WeatherRequest weatherRequestMock = mock(WeatherRequest.class);
        when(weatherRequestMock.getTemperatureUnit()).thenReturn(Constants.TemperatureUnits.STANDARD);

        testJSON.list = null;

        weatherForecastReportFabric.createReportFromJSONAndRequest(getJSONStringFromTestJSON(), weatherRequestMock);
    }

    @Test(expected = IncorrectAPIOutputException.class)
    public void testThrowsExceptionIfLongitudeIsNotSpecified() {
        WeatherRequest weatherRequestMock = mock(WeatherRequest.class);
        when(weatherRequestMock.getTemperatureUnit()).thenReturn(Constants.TemperatureUnits.STANDARD);

        testJSON.city.coordinates.remove("lon");

        weatherForecastReportFabric.createReportFromJSONAndRequest(getJSONStringFromTestJSON(), weatherRequestMock);
    }

    @Test(expected = IncorrectAPIOutputException.class)
    public void testThrowsExceptionIfLatitudeIsNotSpecified() {
        WeatherRequest weatherRequestMock = mock(WeatherRequest.class);
        when(weatherRequestMock.getTemperatureUnit()).thenReturn(Constants.TemperatureUnits.STANDARD);

        testJSON.city.coordinates.remove("lat");

        weatherForecastReportFabric.createReportFromJSONAndRequest(getJSONStringFromTestJSON(), weatherRequestMock);
    }

    @Test(expected = IncorrectAPIOutputException.class)
    public void testThrowsExceptionIfCoordinatesAreNotSpecified() {
        WeatherRequest weatherRequestMock = mock(WeatherRequest.class);
        when(weatherRequestMock.getTemperatureUnit()).thenReturn(Constants.TemperatureUnits.STANDARD);

        testJSON.city.coordinates = null;

        weatherForecastReportFabric.createReportFromJSONAndRequest(getJSONStringFromTestJSON(), weatherRequestMock);
    }
}