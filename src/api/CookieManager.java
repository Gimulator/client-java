package api;

import java.net.HttpURLConnection;

public class CookieManager {
    private static final ThreadLocal<CookieManager> instance = ThreadLocal.withInitial(CookieManager::new);

    public static CookieManager getInstance() {
        return instance.get();
    }


    private CookieManager(){

    }

    public void save(HttpURLConnection connection){

    }

    public void load(HttpURLConnection connection){

    }


}
