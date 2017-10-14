package weatherdata;

import exceptions.IncorrectAPIOutputException;
import org.junit.Test;

import static org.junit.Assert.*;

public class CurrentWeatherReportFromJsonTest {
    @Test
    public void testGetReportNormalCase() {
        String json = "\n" +
                "{\"coord\":\n" +
                "{\"lon\":145.77,\"lat\":16.92},\n" +
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

        CurrentWeatherReport report = null;
        try {
            report = CurrentWeatherReport.getFromJSON(json);
        } catch (IncorrectAPIOutputException e) {
            fail("Error occured: " + e.getMessage());
        }
        assertEquals("Cairns", report.getCityName());
        assertEquals(145.77, report.getCoordinates().getLongitude(), 0.01);
        assertEquals(16.92, report.getCoordinates().getLatitude(), 0.01);
        assertEquals(report.getCurrentTemperature(), 293.25, 0.01);
    }

    @Test
    public void testGetReportWithNegativeLatitude() {
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

        CurrentWeatherReport report = null;
        try {
            report = CurrentWeatherReport.getFromJSON(json);
        } catch (IncorrectAPIOutputException e) {
            fail("Error occured: " + e.getMessage());
        }
        assertEquals("Cairns", report.getCityName());
        assertEquals(145.77, report.getCoordinates().getLongitude(), 0.01);
        assertEquals(-16.92, report.getCoordinates().getLatitude(), 0.01);
        assertEquals(report.getCurrentTemperature(), 293.25, 0.01);
    }

    @Test
    public void testGetReportWithNegativeLongitude() {
        String json = "\n" +
                "{\"coord\":\n" +
                "{\"lon\":-145.77,\"lat\":146.92},\n" +
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

        CurrentWeatherReport report = null;
        try {
            report = CurrentWeatherReport.getFromJSON(json);
        } catch (IncorrectAPIOutputException e) {
            fail("Error occured: " + e.getMessage());
        }
        assertEquals("Cairns", report.getCityName());
        assertEquals(-145.77, report.getCoordinates().getLongitude(), 0.01);
        assertEquals(146.92, report.getCoordinates().getLatitude(), 0.01);
        assertEquals(report.getCurrentTemperature(), 293.25, 0.01);
    }

    @Test
    public void testGetReportWithNegativeTemperature() {
        String json = "\n" +
                "{\"coord\":\n" +
                "{\"lon\":145.77,\"lat\":16.92},\n" +
                "\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04n\"}],\n" +
                "\"base\":\"cmc stations\",\n" +
                "\"main\":{\"temp\":-293.25,\"pressure\":1019,\"humidity\":83,\"temp_min\":289.82,\"temp_max\":295.37},\n" +
                "\"wind\":{\"speed\":5.1,\"deg\":150},\n" +
                "\"clouds\":{\"all\":75},\n" +
                "\"rain\":{\"3h\":3},\n" +
                "\"dt\":1435658272,\n" +
                "\"sys\":{\"type\":1,\"id\":8166,\"message\":0.0166,\"country\":\"AU\",\"sunrise\":1435610796,\"sunset\":1435650870},\n" +
                "\"id\":2172797,\n" +
                "\"name\":\"Cairns\",\n" +
                "\"cod\":200}";

        CurrentWeatherReport report = null;
        try {
            report = CurrentWeatherReport.getFromJSON(json);
        } catch (IncorrectAPIOutputException e) {
            fail("Error occured: " + e.getMessage());
        }
        assertEquals("Cairns", report.getCityName());
        assertEquals(145.77, report.getCoordinates().getLongitude(), 0.01);
        assertEquals(16.92, report.getCoordinates().getLatitude(), 0.01);
        // TODO: ask: -283 OK?
        assertEquals(report.getCurrentTemperature(), -293.25, 0.01);
    }

    @Test(expected = IncorrectAPIOutputException.class)
    public void testThrowsExceptionIfIncorrectCity() throws IncorrectAPIOutputException {
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

        System.out.println(CurrentWeatherReport.getFromJSON(json));
    }

    @Test(expected = IncorrectAPIOutputException.class)
    public void testThrowsExceptionIfNoCoordinates() throws IncorrectAPIOutputException {
        String json = "\n" +
                "{\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04n\"}],\n" +
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

        CurrentWeatherReport.getFromJSON(json);
    }

    @Test(expected = IncorrectAPIOutputException.class)
    public void testThrowsExceptionsIfIncorrectCountryCode() throws IncorrectAPIOutputException {
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

    @Test(expected = IncorrectAPIOutputException.class)
    public void testThrowsExceptionsIfNoCityName() throws IncorrectAPIOutputException {
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
    public void testThrowsExceptionIfNoCountryCode() throws IncorrectAPIOutputException {
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
                "\"sys\":{\"type\":1,\"id\":8166,\"message\":0.0166,\"sunrise\":1435610796,\"sunset\":1435650870},\n" +
                "\"id\":2172797,\n" +
                "\"name\":\"Cairns\",\n" +
                "\"cod\":200}";

        CurrentWeatherReport.getFromJSON(json);
    }

    @Test(expected = IncorrectAPIOutputException.class)
    public void testThrowsExceptionIfNoTemperature() throws IncorrectAPIOutputException {
        String json = "\n" +
                "{\"coord\":\n" +
                "{\"lon\":145.77,\"lat\":16.92},\n" +
                "\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04n\"}],\n" +
                "\"base\":\"cmc stations\",\n" +
                "\"main\":{\"pressure\":1019,\"humidity\":83,\"temp_min\":289.82,\"temp_max\":295.37},\n" +
                "\"wind\":{\"speed\":5.1,\"deg\":150},\n" +
                "\"clouds\":{\"all\":75},\n" +
                "\"rain\":{\"3h\":3},\n" +
                "\"dt\":1435658272,\n" +
                "\"sys\":{\"type\":1,\"id\":8166,\"message\":0.0166,\"country\":\"AU\",\"sunrise\":1435610796,\"sunset\":1435650870},\n" +
                "\"id\":2172797,\n" +
                "\"name\":\"Cairns\",\n" +
                "\"cod\":200}";

        CurrentWeatherReport.getFromJSON(json);
    }
}