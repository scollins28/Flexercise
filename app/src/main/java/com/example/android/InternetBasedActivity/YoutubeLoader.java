package com.example.android.InternetBasedActivity;

import android.content.Context;
import android.util.Log;

import com.example.android.HomeScreen;

import static com.example.android.InternetBasedActivity.CheckInternetConnection.internetConnectionCheck;

public class YoutubeLoader extends android.support.v4.content.AsyncTaskLoader {
        private static final String LOG_TAG = YoutubeLoader.class.getSimpleName();
        private String mURL;
        private int webOrLocal = 0;
        public YouTubeFragment youTubeFragment;

    //Constructor that takes in and stores mURL.
        public YoutubeLoader (Context context, String url){
            super(context);
            mURL=url;
            webOrLocal = 1;
            }

        public YoutubeLoader (Context context){
            super(context);
        }

        //Forces the load.
        @Override
        protected void onStartLoading(){
            forceLoad();
        }

        //Loads the data for the grid in the background. If the url is null, terminates here. Initiates the Film Data fetchfilms methods (which then uses subsequent FilmData methods).
        @Override
        public Object loadInBackground (){
            int status = 0;
            if (mURL == null && webOrLocal ==1){
                return 0;
            }
            else if (mURL!= null && webOrLocal == 1) {
                Log.e( LOG_TAG, mURL );
                status = internetConnectionCheck( mURL );
                youTubeFragment = new YouTubeFragment();
                youTubeFragment.videoId = mURL;
                HomeScreen.youTubeFragment = youTubeFragment;
            }
            return youTubeFragment;
        }
        }
