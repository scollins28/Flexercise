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
    com.github.clans.fab.FloatingActionButton newExerciseFabButton;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

      rootView = inflater.inflate( R.layout.exercise_list_fragment, container, false );

        exerciseBackButton = rootView.findViewById( R.id.exercise_back_button );
        newExerciseFabButton = rootView.findViewById( R.id.exercise_fab_menu_item_one );

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = 0;
                if (v == exerciseBackButton ) {
                    position = 0;
                }
                if (v == newExerciseFabButton) {
                    position = 1;
                }
                mCallback.onExerciseButtonSelected( position );
            }
        };

        //Attaching the click listener to the buttons

        exerciseBackButton.setOnClickListener( listener );
        newExerciseFabButton.setOnClickListener( listener );

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
