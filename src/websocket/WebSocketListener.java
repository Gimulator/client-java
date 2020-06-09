package websocket;

import model.GimulatorObject;

public interface WebSocketListener {
    void onReceive(GimulatorObject object);
}
