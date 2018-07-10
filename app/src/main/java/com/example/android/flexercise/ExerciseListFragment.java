package com.example.android.flexercise;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class ExerciseListFragment extends android.support.v4.app.Fragment{

    View rootView;
    ImageButton exerciseBackButton;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

      rootView = inflater.inflate( R.layout.exercise_list_fragment, container, false );

        exerciseBackButton = rootView.findViewById( R.id.exercise_back_button );

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = 0;
                if (v == exerciseBackButton ) {
                    position = 0;
                }
                mCallback.onExerciseButtonSelected( position );
            }
        };

        //Attaching the click listener to the buttons

        exerciseBackButton.setOnClickListener( listener );


        //Creating the FAB
/*
        com.github.clans.fab.FloatingActionButton exerciseListFab = rootView.findViewById(R.id.exerciseListFab);
        exerciseListFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
       return rootView;
    }

    ExerciseListFragment.OnExerciseButtonClickListener mCallback;



    //Interface for the click listener to send which button has been pushed to the home screen, and activate the fragment transaction
    public interface OnExerciseButtonClickListener {
        void onExerciseButtonSelected(int position);
    }

    //Attaching the interface to the MasterListFragment
    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        try {
            mCallback = (ExerciseListFragment.OnExerciseButtonClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException( context.toString() + "must implement click listener" );
        }
    }

}
