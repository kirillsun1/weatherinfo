package network;

import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.junit.Assert.*;

public class HTTPConnectionTest {
    @Test
    public void testConnectionToAPI() {
        HttpURLConnection con = HTTPConnection.createConnection("http://api.openweathermap.org/forecast/2.5");
        try {
            int responseCode = con.getResponseCode();

            assertEquals(HttpURLConnection.HTTP_OK, responseCode);
        } catch (IOException e) {
            fail("Exception raised: " + e.getMessage());
        }
    }

    @Test
    public void testConnectionToNotExistingLink() {
        HttpURLConnection con = HTTPConnection.createConnection("nolink.es");
        try {
            int responseCode = con.getResponseCode();
            assertEquals(HttpURLConnection.HTTP_NOT_FOUND, responseCode);
        } catch (IOException e) {
            fail("Exception raised: " + e.getMessage());
        }
    }

    @Test
    public void testConnectionToNullLink() {
        HttpURLConnection con = HTTPConnection.createConnection(null);
        assertNull(con);
    }
}