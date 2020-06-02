package api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpRequest {

    public static final int HTTP_TIMEOUT = 10 * 1000;

    private String url;


    public void send() {
        try {
            URL requestUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();

            connection.setDoOutput(true);
            connection.setUseCaches(false);

            connection.setConnectTimeout(HTTP_TIMEOUT);
            connection.setReadTimeout(HTTP_TIMEOUT);


            connection.connect();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}