package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class HTTPConnection {
    private static HashMap<String, HTTPConnection> existingConnections = new HashMap<>();

    private URL url;
    private HttpURLConnection connection;
    private boolean isOpened = false;

    private HTTPConnection() {

    }

    public String downloadFile() throws IOException {
        if (!isOpened || connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IllegalStateException("Connection is not opened!");
        }

        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String nextline;

        while ((nextline = reader.readLine()) != null) {
            builder.append(nextline + "\n");
        }

        reader.close();
        return builder.toString();
    }

    public void open() throws IOException {
        connection = (HttpURLConnection) url.openConnection();
        isOpened = true;
    }

    public void close() {
        if (!isOpened) {
            throw new IllegalStateException("Connection is not opened!");
        }

        connection.disconnect();
        isOpened = false;
    }


    public static HTTPConnection createConnection(String url) throws IOException {
        if (existingConnections.containsKey(url)) {
            return existingConnections.get(url);
        }

        HTTPConnection newConnetion = new HTTPConnection();
        newConnetion.url = new URL(url);
        existingConnections.put(url, newConnetion);
        return newConnetion;
    }

    public int getResponseCode() throws IOException {
        return connection.getResponseCode();
    }
}
