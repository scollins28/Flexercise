package com.example.android.InternetBasedActivity;

import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class
CheckInternetConnection {
    static int status = 0;

    public CheckInternetConnection (){

    }


    public static int internetConnectionCheck(String stringUrl){

        URL url = createURL( stringUrl );

        if (url == null){
            status = 0;
            return status;
        }
        HttpURLConnection urlConnection = null;

        int urlResponse=0;

        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout( 150000 );
            urlConnection.setReadTimeout( 100000 );
            urlConnection.setRequestMethod( "GET" );
            urlConnection.connect();
            if (urlConnection.getResponseCode()<300){
                status = 1;
            }
            else if (urlConnection.getResponseCode()>400){
                urlConnection.disconnect();
                status = 2;
                Log.e( "Url response = ", String.valueOf( urlConnection.getResponseCode() ) );
            }

            else {
                Log.e( "Check Internet", "Problem with the URL connection " + urlConnection.getResponseCode() + urlConnection.getInstanceFollowRedirects());
                status = 2;
            }
        }
        catch (IOException e) {
            Log.e( "Check Internet", "Problem getting the JSON file" );
            status = 2;
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        Log.e ("Check Internet", String.valueOf(status));
        return status;
    }

    private static URL createURL (String stringUrl){
        URL url = null;
        try{
            String youtube = "http://www.youtube.com/watch?v=";
            youtube.concat( stringUrl );
            url = new URL (youtube);
        }
        catch (MalformedURLException e){
            Log.e ("Check Internet", "Problem generating the URL");
        }
        return url;
    }
}

