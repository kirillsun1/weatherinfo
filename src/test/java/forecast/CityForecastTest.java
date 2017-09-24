package forecast;

import city.City;
import org.junit.Test;

import static org.junit.Assert.*;

public class CityForecastTest {
    @Test
    public void testNormalForecase() {
        String json = "{\"cod\":\"200\",\"message\":0.0032,\n" +
                "\"city\":{\"id\":1851632,\"name\":\"Shuzenji\",\n" +
                "\"coord\":{\"lon\":138.933334,\"lat\":34.966671},\n" +
                "\"country\":\"JP\"},\n" +
                "\"cnt\":10,\n" +
                "\"list\":[{\n" +
                "\"dt\":1406080800,\n" +
                "\"temp\":{\n" +
                "\"day\":297.77,\n" +
                "\"min\":293.52,\n" +
                "\"max\":297.77,\n" +
                "\"night\":293.52,\n" +
                "\"eve\":297.77,\n" +
                "\"morn\":297.77},\n" +
                "\"pressure\":925.04,\n" +
                "\"humidity\":76,\n" +
                "\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],}\n" +
                "]}";

        CityForecast forecast = CityForecast.parseFromJSON(json);
        assertEquals("139:35", forecast.getGEOCoordinates());
        assertEquals(298, forecast.get3DaysMaxTemperature());
        assertEquals(294, forecast.get3DaysMinTemperature());
        assertEquals(298, forecast.getCurrentTemperature());
    }

    @Test(expected = RuntimeException.class)
    public void testForecastNoCoordinates() {
        String json = "{\"cod\":\"200\",\"message\":0.0032,\n" +
                "\"city\":{\"id\":1851632,\"name\":\"Shuzenji\",\n" +
                "\"coord\":{\"lon\":138.933334,\"lat\":34.966671},\n" +
                "\"country\":\"JP\"},\n" +
                "\"cnt\":10,\n" +
                "\"list\":[{\n" +
                "\"dt\":1406080800,\n" +
                "\"temp\":{\n" +
                "\"day\":297.77,\n" +
                "\"min\":293.52,\n" +
                "\"max\":297.77,\n" +
                "\"night\":293.52,\n" +
                "\"eve\":297.77,\n" +
                "\"morn\":297.77},\n" +
                "\"pressure\":925.04,\n" +
                "\"humidity\":76,\n" +
                "\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],}\n" +
                "]}";

        CityForecast forecast = CityForecast.parseFromJSON(json);
        forecast.getGEOCoordinates();
    }

    @Test(expected = RuntimeException.class)
    public void testForecastNoTemperature() {
        String json = "{\"cod\":\"200\",\"message\":0.0032,\n" +
                "\"city\":{\"id\":1851632,\"name\":\"Shuzenji\",\n" +
                "\"coord\":{\"lon\":138.933334,\"lat\":34.966671},\n" +
                "\"country\":\"JP\"},\n" +
                "\"cnt\":10,\n" +
                "\"list\":[{\n" +
                "\"dt\":1406080800,\n" +
                "\"temp\":{\n" +
                "\"min\":293.52,\n" +
                "\"max\":297.77,\n" +
                "\"night\":293.52,\n" +
                "\"eve\":297.77,\n" +
                "\"morn\":297.77},\n" +
                "\"pressure\":925.04,\n" +
                "\"humidity\":76,\n" +
                "\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],}\n" +
                "]}";
        CityForecast forecast = CityForecast.parseFromJSON(json);
        forecast.getCurrentTemperature();
    }

    @Test(expected = RuntimeException.class)
    public void testForecastNoMinimumTemperature() {
        String json = "{\"cod\":\"200\",\"message\":0.0032,\n" +
                "\"city\":{\"id\":1851632,\"name\":\"Shuzenji\",\n" +
                "\"coord\":{\"lon\":138.933334,\"lat\":34.966671},\n" +
                "\"country\":\"JP\"},\n" +
                "\"cnt\":10,\n" +
                "\"list\":[{\n" +
                "\"dt\":1406080800,\n" +
                "\"temp\":{\n" +
                "\"day\":297.77,\n" +
                "\"max\":297.77,\n" +
                "\"night\":293.52,\n" +
                "\"eve\":297.77,\n" +
                "\"morn\":297.77},\n" +
                "\"pressure\":925.04,\n" +
                "\"humidity\":76,\n" +
                "\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],}\n" +
                "]}";
        CityForecast forecast = CityForecast.parseFromJSON(json);
        forecast.get3DaysMinTemperature();
    }

    @Test(expected = RuntimeException.class)
    public void testForecastNoMaximumTemperature() {
        String json = "{\"cod\":\"200\",\"message\":0.0032,\n" +
                "\"city\":{\"id\":1851632,\"name\":\"Shuzenji\",\n" +
                "\"coord\":{\"lon\":138.933334,\"lat\":34.966671},\n" +
                "\"country\":\"JP\"},\n" +
                "\"cnt\":10,\n" +
                "\"list\":[{\n" +
                "\"dt\":1406080800,\n" +
                "\"temp\":{\n" +
                "\"day\":297.77,\n" +
                "\"min\":293.52,\n" +
                "\"night\":293.52,\n" +
                "\"eve\":297.77,\n" +
                "\"morn\":297.77},\n" +
                "\"pressure\":925.04,\n" +
                "\"humidity\":76,\n" +
                "\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],}\n" +
                "]}";
        CityForecast forecast = CityForecast.parseFromJSON(json);
        forecast.get3DaysMaxTemperature();
    }

    @Test
    public void testCoordinatesRoundedUp() {
        String json = "{\"cod\":\"200\",\"message\":0.0032,\n" +
                "\"city\":{\"id\":1851632,\"name\":\"Shuzenji\",\n" +
                "\"coord\":{\"lon\":139.933334,\"lat\":120.51},\n" +
                "\"country\":\"JP\"},\n" +
                "\"cnt\":10,\n" +
                "\"list\":[{\n" +
                "\"dt\":1406080800,\n" +
                "\"temp\":{\n" +
                "\"day\":297.77,\n" +
                "\"min\":293.52,\n" +
                "\"max\":297.77,\n" +
                "\"night\":293.52,\n" +
                "\"eve\":297.77,\n" +
                "\"morn\":297.77},\n" +
                "\"pressure\":925.04,\n" +
                "\"humidity\":76,\n" +
                "\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],}\n" +
                "]}";

        CityForecast forecast = CityForecast.parseFromJSON(json);
        assertEquals("140:121", forecast.getGEOCoordinates());
    }

    @Test
    public void testCoordinatesLessThan100() {
        String json = "{\"cod\":\"200\",\"message\":0.0032,\n" +
                "\"city\":{\"id\":1851632,\"name\":\"SomeCity\",\n" +
                "\"coord\":{\"lon\":39.933334,\"lat\":1.51},\n" +
                "\"country\":\"JP\"},\n" +
                "\"cnt\":10,\n" +
                "\"list\":[{\n" +
                "\"dt\":1406080800,\n" +
                "\"temp\":{\n" +
                "\"day\":297.77,\n" +
                "\"min\":293.52,\n" +
                "\"max\":297.77,\n" +
                "\"night\":293.52,\n" +
                "\"eve\":297.77,\n" +
                "\"morn\":297.77},\n" +
                "\"pressure\":925.04,\n" +
                "\"humidity\":76,\n" +
                "\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],}\n" +
                "]}";

        CityForecast forecast = CityForecast.parseFromJSON(json);
        assertEquals("040:002", forecast.getGEOCoordinates());
    }
}