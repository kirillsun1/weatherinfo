package weatherrequest;

import org.junit.Before;
import org.junit.Test;
import utility.Constants;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class WeatherRequestFactoryTest {
    private WeatherRequestFactory factory;

    @Before
    public void setUp() {
        factory = new WeatherRequestFactory();
    }

    @Test
    public void testTempUnitIsDefaultIfNotSpecified() {
        WeatherRequest request = factory.makeWeatherRequest("New York", "US");

        assertEquals(Constants.TemperatureUnits.getUnitByDefault(), request.getTemperatureUnit());
    }

    @Test
    public void testMakeRequestWithOtherTempUnit() {
        WeatherRequest request = factory.makeWeatherRequest("New York", "US",
                Constants.TemperatureUnits.METRIC);

        assertEquals(Constants.TemperatureUnits.METRIC, request.getTemperatureUnit());
    }
}