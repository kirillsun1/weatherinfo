package network;

import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.UnknownHostException;

import static org.junit.Assert.*;

public class HTTPConnectionTest {
    @Test
    public void testConnectionToAPISample() {
        try {
            HTTPConnection con = HTTPConnection.createConnection(
                    "http://samples.openweathermap.org/data/2.5/forecast?id=524901&appid=b1b15e88fa797225412429c1c50c122a15");
            con.open();
            int responseCode = con.getResponseCode();

            assertEquals(HttpURLConnection.HTTP_OK, responseCode);
        } catch (IOException e) {
            fail("Exception raised: " + e.getMessage());
        }
    }

    @Test(expected = UnknownHostException.class)
    public void testConnectionToNotExistingLink() throws IOException {
        HTTPConnection con = HTTPConnection.createConnection("http://nolink.es");
        con.open();
        con.getResponseCode();
    }
}