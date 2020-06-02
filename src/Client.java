import api.HttpRequest;
import api.Response;

public class Client {
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

    public Client(GimulatorObserver observer){
        this.observer = observer;

        register();
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



    private String getUrl(String endPoint) {
        return host + "/" + endPoint;
    }


}
