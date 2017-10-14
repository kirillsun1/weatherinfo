package weatherdata;

import org.junit.Test;

import static org.junit.Assert.*;

public class WeatherRequestTest {
    @Test
    public void testGetWeatherRequestForTallinn() {
        WeatherRequest request = WeatherRequest.of("Tallinn", "EE");

        assertEquals("Tallinn", request.getCityName());
        assertEquals("EE", request.getCountryCode());
    }

    @Test
    public void testCheckIfGivesSameRequest() {
        WeatherRequest request1 = WeatherRequest.of("Tallinn", "EE");
        WeatherRequest request2 = WeatherRequest.of("Tallinn", "EE");

        assertEquals(request1, request2);
    }
}