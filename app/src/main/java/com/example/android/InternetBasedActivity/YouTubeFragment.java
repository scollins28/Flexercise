package com.example.android.InternetBasedActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.HomeScreen;
import com.example.android.free.BuildConfig;
import com.example.android.free.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

public class YouTubeFragment extends Fragment {

    public YouTubeFragment (){
    }

    public static String videoId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.youtube_fragment, container, false);

        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.youtubeFragmentAltd, youTubePlayerFragment).addToBackStack( null ).commit();

        youTubePlayerFragment.initialize( (BuildConfig.YOUTUBE_API_KEY), new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
                if (!wasRestored) {
                    videoId = HomeScreen.currentMediaSource;
                    Log.e( "initialised" , String.valueOf( videoId ));
                    player.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
                    player.setFullscreen( false );
                    player.loadVideo(videoId);
                    Log.e( "loaded" , String.valueOf( videoId ));
                    player.play();
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                String errorMessage = youTubeInitializationResult.toString();
                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
                Log.d("errorMessage:", errorMessage);
            }
        });

        return rootView;
    }
}