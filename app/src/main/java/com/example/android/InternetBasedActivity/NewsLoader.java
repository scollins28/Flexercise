package com.example.android.InternetBasedActivity;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.example.android.HomeScreen;
import com.example.android.News;

import java.util.ArrayList;
import java.util.List;


public class NewsLoader extends AsyncTaskLoader<ArrayList<News>> {
        private static final String LOG_TAG = NewsLoader.class.getSimpleName();
        private String mURL;
        private int webOrLocal = 1;


    //Constructor that takes in and stores mURL.
        public NewsLoader (Context context, String url){
            super(context);
            mURL=url;
            webOrLocal = 1;
            }

        public NewsLoader (Context context){
            super(context);
        }

    @Override
    public ArrayList<News> loadInBackground() {
        Log.e( "Load in background", "has started" );
        ArrayList<News> news = new ArrayList<>(  );
        if (mURL == null && webOrLocal ==1){
            return null;
        }
        else if (mURL!= null && webOrLocal == 1) {
            Log.e( LOG_TAG, mURL );
            Log.e( "About to start", "fetch news" );
            news = NewsData.fetchNews( mURL, getContext() );
            return news;
        }
        return null;
    }

    //Forces the load.
        @Override
        protected void onStartLoading(){
            forceLoad();
        }

        //Loads the data for the grid in the background. If the url is null, terminates here. Initiates the Film Data fetchfilms methods (which then uses subsequent FilmData methods).

}
