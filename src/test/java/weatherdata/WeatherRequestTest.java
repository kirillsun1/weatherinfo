package weatherdata;

import org.junit.Test;

import static org.junit.Assert.*;

public class WeatherRequestTest {
    @Test
    public void testGetWeatherRequestForTallinn() {
        WeatherRequest request = WeatherRequest.fromCityNameAndCode("Tallinn", "EE");

        assertEquals("Tallinn", request.getCityName());
        assertEquals("EE", request.getCityCode());
    }

    @Test
    public void testCheckIfGivesSameRequest() {
        WeatherRequest request1 = WeatherRequest.fromCityNameAndCode("Tallinn", "EE");
        WeatherRequest request2 = WeatherRequest.fromCityNameAndCode("Tallinn", "EE");

        assertEquals(request1, request2);
    }
}