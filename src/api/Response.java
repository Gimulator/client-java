package api;

/**
 * response class for api responses
 */
public class Response {

    private Error error;
    private final String body;
    private final int code;

    public Response(int code,String body){

        this.code = code;
        this.body = body;
        handleError();
    }

    /**
     * handling server errors here
     */
    private void handleError() {
        if (code /100 != 2){
            error = new Error(code,getMessage(body));
        }
    }

    private String getMessage(String body){
        return body;
    }

    public Error getError() {
        return error;
    }

    public int getCode() {
        return code;
    }

    public String getBody() {
        return body;
    }

    /**
     * error class for error api responses.
     */
    public static class Error {
        private final int code;
        private final String message;
        public Error(int code,String message){

            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}
