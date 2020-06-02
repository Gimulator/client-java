package api;

import java.net.HttpURLConnection;

public class CookieManager {
    private static final CookieManager instance = new CookieManager();

    public static CookieManager getInstance() {
        return instance;
    }

    private String cookie;


    private CookieManager(){

    }

    public void save(HttpURLConnection connection){
        String cookie = connection.getHeaderField("Set-Cookie");
        if (cookie!=null){
            this.cookie = cookie;
        }
    }

    public void load(HttpURLConnection connection){
        connection.addRequestProperty("Cookie",cookie);
    }

}
