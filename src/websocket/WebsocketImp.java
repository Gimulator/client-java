package websocket;

import api.CookieManager;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

public class WebsocketImp extends Thread {

    Websocket websocket;

    public WebsocketImp(String url,WebSocketListener listener) throws MalformedURLException, URISyntaxException {
        HashMap<String,String> headers = new HashMap<>();
        CookieManager.getInstance().appendToMap(headers);

        websocket = new Websocket(new URL(url).toURI(),headers,listener);
    }

    @Override
    public void run() {
        websocket.connect();
    }
}
