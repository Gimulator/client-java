package api;

import org.json.JSONObject;

/**
 * response class for api responses
 */
public class Response {

    public static Response ok(int code,String body){
        return new Response(code,body);
    }

    public static Response error(int code,String message){
        return new Response(code,message);
    }

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
        try {
            JSONObject jsonObject = new JSONObject(body);
            return jsonObject.getString("message");
        } catch (Exception e){
            return body;
        }
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
