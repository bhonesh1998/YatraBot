package com.example.bhonesh.bot;

/**
 * Created by upesh on 20/3/18.
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
public class Helper {
    private static final String DIRECTION_API = "https://maps.googleapis.com/maps/api/directions/json?origin=";
    public static final String API_KEY = "AIzaSyCN1gq8Wgt0wBsZUkn2dldgNKZFXdT-iTc";
    public  static int MY_SOCKET_TIMEOUT_MS = 5000;
    public static String getUrl(String originLat, String originLon, String destinationLat, String destinationLon){
        return Helper.DIRECTION_API + originLat+","+originLon+"&destination="+destinationLat+","+destinationLon+"&key="+API_KEY;
    }
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
