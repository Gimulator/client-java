import api.HttpRequest;
import api.Response;
import model.GimulatorObject;
import model.Key;
import websocket.WebSocketListener;
import websocket.WebsocketImp;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

public class ClientImp implements Client{
    public static final String GET = "get";
    public static final String DELETE = "delete";
    public static final String FIND = "find";
    public static final String SET = "set";
    public static final String WATCH = "watch";
    public static final String REGISTER = "register";
    public static final String SOCKET = "socket";

    private final String owner = System.getenv("CLIENT_ID");
    private final String host = System.getenv("GIMULATOR_HOST");
    private final GimulatorObserver observer;

    public ClientImp(GimulatorObserver observer){
        this.observer = observer;

        register();

        try {
            connectWebSocket();
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
            System.out.println("WebSocket connection failed");
        }
    }

    private void connectWebSocket() throws MalformedURLException, URISyntaxException {
        String url = getUrl(SOCKET);
        WebsocketImp websocket = new WebsocketImp(url, new WebSocketListener() {
            @Override
            public void onReceive(GimulatorObject object) {
                observer.onObserved(object);
            }
        });
        websocket.start();
    }

    private void register() throws GimulatorException {
        String url = getUrl(REGISTER);

        HttpRequest request = new HttpRequest(url);
        request.addPost("ID",owner);

        Response response = request.send();
        if (response.getError() != null){
            throw new GimulatorException(response.getError());
        }
    }

    @Override
    public GimulatorObject get(Key key) throws GimulatorException{
        String url = getUrl(GET);

        HttpRequest request = new HttpRequest(url);
        request.addPost("Owner",owner);
        request.addPost("key",key);

        Response response = request.send();

        if (response.getError() !=null){
            throw new GimulatorException(response.getError());
        }
        return GimulatorObject.parse(response.getBody());
    }

    @Override
    public List<GimulatorObject> find(Key key) throws GimulatorException {
        String url = getUrl(FIND);

        HttpRequest request = new HttpRequest(url);
        request.addPost("Owner",owner);
        request.addPost("key",key);

        Response response = request.send();

        if (response.getError() != null){
            throw new GimulatorException(response.getError());
        }
        return GimulatorObject.parseArray(response.getBody());
    }

    @Override
    public void set(Key key) throws GimulatorException {
        String url = getUrl(SET);

        HttpRequest request = new HttpRequest(url);
        request.addPost("Owner",owner);
        request.addPost("key",key);

        Response response = request.send();

        if (response.getError() != null){
            throw new GimulatorException(response.getError());
        }
    }

    @Override
    public void delete(Key key) throws GimulatorException {
        String url = getUrl(DELETE);

        HttpRequest request = new HttpRequest(url);
        request.addPost("Owner",owner);
        request.addPost("key",key);

        Response response = request.send();

        if (response.getError() != null){
            throw new GimulatorException(response.getError());
        }
    }

    @Override
    public void watch(Key key) throws GimulatorException {
        String url = getUrl(WATCH);

        HttpRequest request = new HttpRequest(url);
        request.addPost("Owner",owner);
        request.addPost("key",key);

        Response response = request.send();

        if (response.getError() != null){
            throw new GimulatorException(response.getError());
        }
    }


    private String getUrl(String endPoint) {
        return host + "/" + endPoint;
    }


}
