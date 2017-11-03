package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPConnection {
    private HttpURLConnection connection;

    public String downloadFile() throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String nextline;

        while ((nextline = reader.readLine()) != null) {
            builder.append(nextline).append("\n");
        }

        reader.close();
        return builder.toString();
    }

    public int getResponseCode() throws IOException {
        return connection.getResponseCode();
    }

    public static HTTPConnection createConnectionFromURL(String url) throws IOException {
        HTTPConnection newConnection = new HTTPConnection();
        newConnection.connection = (HttpURLConnection) new URL(url).openConnection();
        newConnection.connection.connect();
        return newConnection;
    }
}
