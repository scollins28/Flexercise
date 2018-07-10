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

public class WorkoutListFragment extends android.support.v4.app.Fragment{

    View rootView;
    ImageButton workoutListBackButton;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate( R.layout.workout_list_fragment, container, false );

        workoutListBackButton = rootView.findViewById( R.id.workout_list_back_button );

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = 0;
                if (v == workoutListBackButton ) {
                    position = 0;
                }
                mCallback.onWorkoutListButtonSelected( position );
            }
        };

        //Attaching the click listener to the buttons

        workoutListBackButton.setOnClickListener( listener );

        return rootView;
    }

    WorkoutListFragment.onWorkoutListButtonSelected mCallback;



    //Interface for the click listener to send which button has been pushed to the home screen, and activate the fragment transaction
    public interface onWorkoutListButtonSelected {
        void onWorkoutListButtonSelected (int position);
    }

    //Attaching the interface to the MasterListFragment
    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        try {
            mCallback = (WorkoutListFragment.onWorkoutListButtonSelected) context;
        } catch (ClassCastException e) {
            throw new ClassCastException( context.toString() + "must implement click listener" );
        }
    }

}
