package com.example.android.flexercise;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class NewWorkoutFragment extends android.support.v4.app.Fragment{

    View rootView;
    ImageButton newWorkoutBackButton;
    Button newWorkoutDoneButton;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate( R.layout.new_workout_fragment, container, false );

        newWorkoutBackButton = rootView.findViewById( R.id.new_workout_back_button );
        newWorkoutDoneButton = rootView.findViewById( R.id.new_workout_done_button);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = 0;
                if (v == newWorkoutBackButton) {
                    position = 0;
                }
                else if (v == newWorkoutDoneButton){
                    position = 1;
                }
                mCallback.onNewWorkoutButtonSelected( position );
            }
        };

        //Attaching the click listener to the buttons

        newWorkoutBackButton.setOnClickListener( listener );
        newWorkoutDoneButton.setOnClickListener( listener );

        return rootView;
    }

    NewWorkoutFragment.onNewWorkoutButtonSelected mCallback;



    //Interface for the click listener to send which button has been pushed to the home screen, and activate the fragment transaction
    public interface onNewWorkoutButtonSelected {
        void onNewWorkoutButtonSelected (int position);
    }

    //Attaching the interface to the MasterListFragment
    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        try {
            mCallback = (NewWorkoutFragment.onNewWorkoutButtonSelected) context;
        } catch (ClassCastException e) {
            throw new ClassCastException( context.toString() + "must implement click listener" );
        }
    }

}
