package api;

import model.Key;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


public class HttpRequest {

    public static final int HTTP_TIMEOUT = 10 * 1000;

    private final String url;

    private final Map<String, String> headers = new HashMap<>();
    private final Map<String, Object> params = new HashMap<>();

    private Key key;

    public HttpRequest(String url) {
        this.url = url;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public void addPost(String key, Object value) {
        params.put(key, value);
    }


    public Response send() {
        try {
            URL requestUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();

            connection.setDoOutput(true);
            connection.setUseCaches(false);

            connection.setConnectTimeout(HTTP_TIMEOUT);
            connection.setReadTimeout(HTTP_TIMEOUT);

            cleanUpHeaders();

            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.addRequestProperty(entry.getKey(), entry.getValue());
            }

            JSONObject jsonObject = new JSONObject();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                jsonObject.put(entry.getKey(), entry.getValue().toString());
            }

            if (key != null)
                jsonObject.put("Key", key.toJson());

            CookieManager.getInstance().load(connection);

            System.out.println("json data: " + jsonObject.toString());

            connection.getOutputStream().write(jsonObject.toString().getBytes());


            InputStream in;

            if (connection.getResponseCode() >= HttpURLConnection.HTTP_BAD_REQUEST) {
                in = connection.getErrorStream();
            } else {
                in = connection.getInputStream();
            }

            String response = readFromStream(in);

            CookieManager.getInstance().save(connection);


            return Response.ok(connection.getResponseCode(), response);
        } catch (IOException e) {
            e.printStackTrace();

            return Response.error(-1, "Unknown http error");

        }
    }

    private void cleanUpHeaders() {
        headers.remove("Content-type");
        headers.put("Content-type", "Application/json");
    }

    private String readFromStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString(StandardCharsets.UTF_8);

    }
}