import api.Response;

public class GimulatorException extends IllegalArgumentException {
    public GimulatorException(Response.Error error){
        super(error.getMessage());
    }
}
