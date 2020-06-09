package websocket;

import model.GimulatorObject;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Map;

public class Websocket extends WebSocketClient {

    private final WebSocketListener listener;

    public Websocket(URI serverUri, Map<String, String> httpHeaders, WebSocketListener listener) {
        super(serverUri, httpHeaders);
        this.listener = listener;
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("connected to server");
    }

    @Override
    public void onMessage(String s) {
        listener.onReceive(GimulatorObject.parse(s));
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        System.out.println("Connection closed");
    }

    @Override
    public void onError(Exception e) {
        e.printStackTrace();
    }
}
