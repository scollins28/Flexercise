package com.example.android.flexercise;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class SettingsFragment extends android.support.v4.app.Fragment{

    View rootView;
    ImageButton settingsBackButton;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate( R.layout.settings_fragment, container, false );

        settingsBackButton = rootView.findViewById( R.id.settings_back_button );

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = 0;
                if (v == settingsBackButton) {
                    position = 0;
                }
                mCallback.onSettingsButtonSelected( position );
            }
        };

        //Attaching the click listener to the buttons

        settingsBackButton.setOnClickListener( listener );

        return rootView;
    }

    SettingsFragment.onSettingsButtonSelected mCallback;



    //Interface for the click listener to send which button has been pushed to the home screen, and activate the fragment transaction
    public interface onSettingsButtonSelected {
        void onSettingsButtonSelected (int position);
    }

    //Attaching the interface to the MasterListFragment
    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        try {
            mCallback = (SettingsFragment.onSettingsButtonSelected) context;
        } catch (ClassCastException e) {
            throw new ClassCastException( context.toString() + "must implement click listener" );
        }
    }

}
