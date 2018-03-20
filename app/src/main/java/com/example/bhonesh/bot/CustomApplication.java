package com.example.bhonesh.bot;

/**
 * Created by upesh on 20/3/18.
 */


import android.app.Application;
        import com.android.volley.RequestQueue;
public class CustomApplication extends Application{
    private RequestQueue requestQueue;
    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue = VolleySingleton.getInstance(getApplicationContext()).getRequestQueue();
    }
    public RequestQueue getVolleyRequestQueue(){
        return requestQueue;
    }
}