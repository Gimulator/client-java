package api;

import java.net.HttpURLConnection;

public class CookieManager {
    private static final CookieManager instance = new CookieManager();

    public static CookieManager getInstance() {
        return instance;
    }


    private CookieManager(){

    }

    public void save(HttpURLConnection connection){

    }

    public void load(HttpURLConnection connection){

    }


}
