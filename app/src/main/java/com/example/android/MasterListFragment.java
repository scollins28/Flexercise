package com.example.android;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android.free.R;


public class MasterListFragment extends Fragment {

    Context mContext;
    Button exerciseButton;
    Button workoutButton;
    Button newsButton;
    Button settingsButton;
    ConstraintLayout constraintLayout;

    //Empty constructor
    public MasterListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Getting context and inflating the rootview

        mContext = getContext();
        final View rootView = inflater.inflate( R.layout.home_screen_fragment, container, false );

        //Attaching button views to variables
        exerciseButton = (Button) rootView.findViewById(R.id.exercises_button);
        workoutButton = (Button) rootView.findViewById(R.id.workouts_button);
        settingsButton = (Button) rootView.findViewById(R.id.setttings_button);
        newsButton = (Button) rootView.findViewById(R.id.news_button);

        // Setting on click listener for the three buttons

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = 0;
                if (v == exerciseButton) {
                    position = 0;
                } else if (v == workoutButton) {
                    position = 1;
                } else if (v == settingsButton) {
                    position = 2;
                } else if (v == newsButton){
                    position = 3;
                }
                mCallback.onButtonSelected( position );
            }
        };

        //Attaching the click listener to the buttons

        exerciseButton.setOnClickListener( listener );
        workoutButton.setOnClickListener( listener );
        settingsButton.setOnClickListener( listener );
        newsButton.setOnClickListener( listener );

        return rootView;
    }

    MasterListFragment.OnButtonClickListener mCallback;



    //Interface for the click listener to send which button has been pushed to the home screen, and activate the fragment transaction
    public interface OnButtonClickListener {
        void onButtonSelected(int position);
    }

    //Attaching the interface to the MasterListFragment
    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        try {
            mCallback = (MasterListFragment.OnButtonClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException( context.toString() + "must implement click listener" );
        }
    }


}
