package com.example.android.flexercise;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

import io.apptik.widget.multiselectspinner.MultiSelectSpinner;

public class NewExerciseFragment extends android.support.v4.app.Fragment{

    View rootView;
    ImageButton newExerciseBackButton;
    Button newExerciseDoneButton;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate( R.layout.new_exercise_fragment, container, false );

        newExerciseBackButton = rootView.findViewById( R.id.new_exercise_back_button );
        newExerciseDoneButton = rootView.findViewById( R.id.new_exercise_done_button);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = 0;
                if (v == newExerciseBackButton) {
                    position = 0;
                }
                else if (v == newExerciseDoneButton){
                    position = 1;
                }
                mCallback.onNewExerciseButtonSelected( position );
            }
        };

        //Attaching the click listener to the buttons

        newExerciseBackButton.setOnClickListener( listener );
        newExerciseDoneButton.setOnClickListener( listener );


        ArrayList<String> options = new ArrayList<>();
        options.add("1");
        options.add("2");
        options.add("3");
        options.add("A");
        options.add("B");
        options.add("C");
        MultiSelectSpinner multiSelectSpinner = (MultiSelectSpinner) rootView.findViewById(R.id.new_exercise_workout_list_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter <String>(getActivity(), android.R.layout.simple_list_item_multiple_choice, options);

        multiSelectSpinner
                .setListAdapter(adapter);
        new MultiSelectSpinner.MultiSpinnerListener() {
                    @Override
                    public void onItemsSelected(boolean[] checkedItems) {
                    }
                };

        return rootView;
    }

    NewExerciseFragment.onNewExerciseButtonSelected mCallback;



    //Interface for the click listener to send which button has been pushed to the home screen, and activate the fragment transaction
    public interface onNewExerciseButtonSelected {
        void onNewExerciseButtonSelected (int position);
    }

    //Attaching the interface to the MasterListFragment
    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        try {
            mCallback = (NewExerciseFragment.onNewExerciseButtonSelected) context;
        } catch (ClassCastException e) {
            throw new ClassCastException( context.toString() + "must implement click listener" );
        }
    }

}
