package com.example.android.flexercise;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class WorkoutExerciseDetailsFragment extends android.support.v4.app.Fragment{

    View rootView;
    ConstraintLayout navigationButtons;
    ImageButton exerciseDetailsBackButton;
    Button exerciseDoneButton;
    Button nextButton;
    Button previousButton;
    Exercise exercise;
    TextView subheading;
    TextView exerciseName;
    TextView numberOfSets;
    TextView maxWeight;
    TextView startingWeight;
    TextView unitOne;
    TextView unitTwo;
    TextView notes;
    String media;
    Boolean allowed;

    public WorkoutExerciseDetailsFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate( R.layout.workout_exercise_details_fragment, container, false );

        exerciseDetailsBackButton = rootView.findViewById( R.id.workout_exercise_details_back_button );
        exerciseDoneButton = rootView.findViewById( R.id.workout_exercise_done_button );
        nextButton = rootView.findViewById( R.id.exercise_next_button );
        previousButton = rootView.findViewById( R.id.exercise_previous_button );
        unitOne = rootView.findViewById( R.id.kg_tv );
        unitTwo = rootView.findViewById( R.id.kg_tv2 );
        if (HomeScreen.kgValue==1){
            unitOne.setText( R.string.KG );
            unitTwo.setText( R.string.KG );
        } else {
            unitOne.setText( R.string.LBS);
            unitTwo.setText( R.string.LBS );
        }
        if (HomeScreen.calledPosition==0){
            previousButton.setVisibility( View.GONE );
        }
        if ((HomeScreen.calledPosition+1)==HomeScreen.exercisesInSetWorkout.size()){
            nextButton.setVisibility(View.GONE);
        }


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = 0;
                if (v == exerciseDetailsBackButton) {
                    position = 0;
                } else if (v == exerciseDoneButton){
                    position = 1;
                } else if (v == previousButton) {
                    HomeScreen.calledPosition -= 1;
                    position = 2;
                } else if (v == nextButton){
                   HomeScreen.calledPosition +=1;
                   position = 2;
                }
                mCallback.onWorkoutExerciseDetailsButtonSelected( position );
                }
        };

        //Attaching the click listener to the buttons

        exerciseDetailsBackButton.setOnClickListener( listener );
        exerciseDoneButton.setOnClickListener( listener );
        nextButton.setOnClickListener( listener );
        previousButton.setOnClickListener( listener );

        subheading = rootView.findViewById(R.id.exercise_details_subheading);
        exerciseName = rootView.findViewById( R.id.exercise_name_details_view_tv );
        numberOfSets = rootView.findViewById( R.id.exercise_number_of_sets_details_view_tv );
        maxWeight = rootView.findViewById( R.id.exercise_max_weight_details_view_tv);
        startingWeight = rootView.findViewById( R.id.exercise_starting_weight_details_view_tv);
        notes = rootView.findViewById( R.id.notes_details_tv);

        subheading.setText( exercise.getExerciseName());
        exerciseName.setText( exercise.getExerciseName() );
        numberOfSets.setText(String.valueOf(exercise.getNumberofSets()) );
        maxWeight.setText(String.valueOf(exercise.getMaxWeight()) );
        startingWeight.setText(String.valueOf(exercise.getStartingWeight()) );
        notes.setText( exercise.getNotes() );

        return rootView;
    }

    WorkoutExerciseDetailsFragment.onWorkoutExerciseDetailsButtonSelected mCallback;



    //Interface for the click listener to send which button has been pushed to the home screen, and activate the fragment transaction
    public interface onWorkoutExerciseDetailsButtonSelected {
        void onWorkoutExerciseDetailsButtonSelected(int position);
    }

    //Attaching the interface to the MasterListFragment
    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        try {
            mCallback = (WorkoutExerciseDetailsFragment.onWorkoutExerciseDetailsButtonSelected) context;
        } catch (ClassCastException e) {
            throw new ClassCastException( context.toString() + "must implement click listener" );
        }
    }

}
