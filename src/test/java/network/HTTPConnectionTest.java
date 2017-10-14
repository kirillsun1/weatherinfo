package network;

import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.UnknownHostException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HTTPConnectionTest {
    @Test
    public void testConnectionToAPISample() {
        try {
            HTTPConnection con = HTTPConnection.createConnectionFromURL(
                    "http://samples.openweathermap.org/data/2.5/forecast?id=524901&appid=b1b15e88fa797225412429c1c50c122a15");
            int responseCode = con.getResponseCode();

            assertEquals(HttpURLConnection.HTTP_OK, responseCode);
        } catch (IOException e) {
            fail("Exception raised: " + e.getMessage());
        }
    }

    @Test(expected = IOException.class)
    public void testConnectionToNotExistingLink() throws IOException {
        HTTPConnection con = HTTPConnection.createConnectionFromURL("http://nolink.es");
        con.getResponseCode();
    }

    @Test
    public void testDownloadFileFromMockedConnection() {
        HTTPConnection mockedConnection = mock(HTTPConnection.class);
        try {
            when(mockedConnection.downloadFile()).thenReturn("mocked well??");
        } catch (IOException e) {
            fail("Error occured: " + e.getMessage());
        }
        try {
            assertEquals("mocked well??", mockedConnection.downloadFile());
        } catch (IOException e) {
            fail("Error occured: " + e.getMessage());
        }
    }
}