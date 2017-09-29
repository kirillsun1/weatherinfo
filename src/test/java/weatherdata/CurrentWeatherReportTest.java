package weatherdata;

import exceptions.IncorrectAPIOutputException;
import org.junit.Test;

import static org.junit.Assert.*;

public class CurrentWeatherReportTest {
    @Test
    public void testGetCurrentWeatherFromJSON() {
        String json = "\n" +
                "{\"coord\":\n" +
                "{\"lon\":145.77,\"lat\":-16.92},\n" +
                "\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04n\"}],\n" +
                "\"base\":\"cmc stations\",\n" +
                "\"main\":{\"temp\":293.25,\"pressure\":1019,\"humidity\":83,\"temp_min\":289.82,\"temp_max\":295.37},\n" +
                "\"wind\":{\"speed\":5.1,\"deg\":150},\n" +
                "\"clouds\":{\"all\":75},\n" +
                "\"rain\":{\"3h\":3},\n" +
                "\"dt\":1435658272,\n" +
                "\"sys\":{\"type\":1,\"id\":8166,\"message\":0.0166,\"country\":\"AU\",\"sunrise\":1435610796,\"sunset\":1435650870},\n" +
                "\"id\":2172797,\n" +
                "\"name\":\"Cairns\",\n" +
                "\"cod\":200}";

        CurrentWeatherReport report = CurrentWeatherReport.getFromJSON(json);
        assertEquals(report.getCityName(), "Cairns");
        assertEquals(report.getGEOCoordinates(), "146:-17");
        assertEquals(report.getCurrentTemperature(), 293.25, 0.01);
    }

    @Test(expected = IncorrectAPIOutputException.class)
    public void testGetCurrentWeatherFromJSONNoCity() {
        String json = "\n" +
                "{\"coord\":\n" +
                "{\"lon\":145.77,\"lat\":-16.92},\n" +
                "\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04n\"}],\n" +
                "\"base\":\"cmc stations\",\n" +
                "\"main\":{\"temp\":293.25,\"pressure\":1019,\"humidity\":83,\"temp_min\":289.82,\"temp_max\":295.37},\n" +
                "\"wind\":{\"speed\":5.1,\"deg\":150},\n" +
                "\"clouds\":{\"all\":75},\n" +
                "\"rain\":{\"3h\":3},\n" +
                "\"dt\":1435658272,\n" +
                "\"sys\":{\"type\":1,\"id\":8166,\"message\":0.0166,\"country\":\"AU\",\"sunrise\":1435610796,\"sunset\":1435650870},\n" +
                "\"id\":2172797,\n" +
                "\"cod\":200}";

        CurrentWeatherReport.getFromJSON(json);
    }

    @Test(expected = IncorrectAPIOutputException.class)
    public void testGetCurrentWeatherFromJSONIncorrectCountryCode() {
        String json = "\n" +
                "{\"coord\":\n" +
                "{\"lon\":145.77,\"lat\":-16.92},\n" +
                "\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04n\"}],\n" +
                "\"base\":\"cmc stations\",\n" +
                "\"main\":{\"temp\":293.25,\"pressure\":1019,\"humidity\":83,\"temp_min\":289.82,\"temp_max\":295.37},\n" +
                "\"wind\":{\"speed\":5.1,\"deg\":150},\n" +
                "\"clouds\":{\"all\":75},\n" +
                "\"rain\":{\"3h\":3},\n" +
                "\"dt\":1435658272,\n" +
                "\"sys\":{\"type\":1,\"id\":8166,\"message\":0.0166,\"country\":\"AUQ\",\"sunrise\":1435610796,\"sunset\":1435650870},\n" +
                "\"id\":2172797,\n" +
                "\"name\":\"Cairns\",\n" +
                "\"cod\":200}";

        CurrentWeatherReport.getFromJSON(json);
    }
}