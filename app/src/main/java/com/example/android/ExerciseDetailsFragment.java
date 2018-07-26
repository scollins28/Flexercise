package com.example.android;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.android.Exercise;
import com.example.android.HomeScreen;
import com.example.android.free.R;

public class ExerciseDetailsFragment extends android.support.v4.app.Fragment {

    View rootView;
    ImageButton exerciseDetailsBackButton;
    Button exerciseEditButton;
    public Exercise exercise;
    TextView subheading;
    TextView exerciseName;
    TextView numberOfSets;
    TextView maxWeight;
    TextView startingWeight;
    TextView notes;
    TextView addedToWorkouts;
    Button categoryOne;
    Button categoryTwo;
    Button categoryThree;
    Button categoryFour;
    Button categoryFive;
    Button categorySix;
    Button mediaButton;
    TextView weightUnitOne;
    TextView weightUnitTwo;
    int mediaType;
    String youTubeString;
    int exerciseType;

    public ExerciseDetailsFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate( R.layout.exercise_details_fragment, container, false );

        exerciseDetailsBackButton = rootView.findViewById( R.id.exercise_details_back_button );
        exerciseEditButton = rootView.findViewById( R.id.exercise_edit_button );
        mediaButton = rootView.findViewById( R.id.media_button );
        exercise = HomeScreen.exercise;
        mediaType = exercise.getMediaType();
        exerciseType = exercise.getExerciseType();



        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = 0;
                if (v == exerciseDetailsBackButton) {
                    position = 0;
                    mCallback.onExerciseDetailsButtonSelected( position );
                } else if (v == exerciseEditButton){
                    position = 1;
                    HomeScreen.exercise = exercise;
                    mCallback.onExerciseDetailsButtonSelected( position );
                } else if (v == mediaButton){
                    if (mediaType ==1){
                        youTubeString = exercise.getMediaSource();
                        HomeScreen.currentMediaSource = youTubeString;
                        position =2;
                        mCallback.onExerciseDetailsButtonSelected( position );
                    Log.e( "Media source ", String.valueOf( youTubeString ) );
                    } }
            }
        };

        //Attaching the click listener to the buttons

        exerciseDetailsBackButton.setOnClickListener( listener );
        exerciseEditButton.setOnClickListener( listener );
        mediaButton.setOnClickListener( listener );

        subheading = rootView.findViewById(R.id.exercise_details_subheading);
        exerciseName = rootView.findViewById( R.id.exercise_name_details_view_tv );
        numberOfSets = rootView.findViewById( R.id.exercise_number_of_sets_details_view_tv );
        maxWeight = rootView.findViewById( R.id.exercise_max_weight_details_view_tv);
        startingWeight = rootView.findViewById( R.id.exercise_starting_weight_details_view_tv);
        notes = rootView.findViewById( R.id.notes_details_tv);
        addedToWorkouts = rootView.findViewById( R.id.workouts_list_exercise_details_view_tv);
        categoryOne = rootView.findViewById( R.id.button_one);
        categoryTwo = rootView.findViewById( R.id.button_two);
        categoryThree = rootView.findViewById( R.id.button_three);
        categoryFour = rootView.findViewById( R.id.button_four);
        categoryFive = rootView.findViewById( R.id.button_five);
        categorySix = rootView.findViewById( R.id.button_six);
        weightUnitOne = rootView.findViewById( R.id.kg_tv );
        weightUnitTwo = rootView.findViewById( R.id.kg_tv2 );
        if (HomeScreen.kgValue==1){
            weightUnitOne.setText( R.string.KG );
            weightUnitTwo.setText( R.string.KG );
        } else {
            weightUnitOne.setText( R.string.LBS );
            weightUnitTwo.setText( R.string.LBS );
        }



        subheading.setText( exercise.getExerciseName());
        exerciseName.setText( exercise.getExerciseName() );
        numberOfSets.setText(String.valueOf(exercise.getNumberofSets()) );
        if (exerciseType == 0) {
            maxWeight.setText( String.valueOf( exercise.getMaxWeight() ) );
            startingWeight.setText( String.valueOf( exercise.getStartingWeight() ) );
        }
        else {
            maxWeight.setText( String.valueOf( exercise.getDistance() ) );
            startingWeight.setText( String.valueOf( exercise.getTime() ) );
            if (HomeScreen.milesValue == 0){
                weightUnitOne.setText( getString( R.string.KM ) );
                weightUnitTwo.setText( getString( R.string.minutes ) );
            } else{
                weightUnitOne.setText( getString( R.string.miles ) );
                weightUnitTwo.setText( getString( R.string.minutes ) );
            }
        TextView maxWeightTv = rootView.findViewById( R.id.exercise_max_weight_tv );
            maxWeightTv.setText( R.string.distance );
        TextView startingWeightTv = rootView.findViewById( R.id.exercise_starting_weight_tv );
            startingWeightTv.setText( R.string.time );
        }
        notes.setText( exercise.getNotes() );
        addedToWorkouts.setText(exercise.getAddToWorkout() );
        if (exercise.getCategoryOneValue()==1){
            categoryOne.setBackgroundColor( getResources().getColor(R.color.colorAccent));
            categoryOne.setTextColor( getResources().getColor(R.color.spinnerWhite));
        }
        if (exercise.getCategoryTwoValue()==1){
            categoryTwo.setBackgroundColor( getResources().getColor(R.color.colorAccent));
            categoryTwo.setTextColor( getResources().getColor(R.color.spinnerWhite));
        }
        if (exercise.getCategoryThreeValue()==1){
            categoryThree.setBackgroundColor( getResources().getColor(R.color.colorAccent));
            categoryThree.setTextColor( getResources().getColor(R.color.spinnerWhite));
        }
        if (exercise.getCategoryFourValue()==1){
            categoryFour.setBackgroundColor( getResources().getColor(R.color.colorAccent));
            categoryFour.setTextColor( getResources().getColor(R.color.spinnerWhite));
        }
        if (exercise.getCategoryFiveValue()==1){
            categoryFive.setBackgroundColor( getResources().getColor(R.color.colorAccent));
            categoryFive.setTextColor( getResources().getColor(R.color.spinnerWhite));
        }
        if (exercise.getCategorySixValue()==1){
            categorySix.setBackgroundColor( getResources().getColor(R.color.colorAccent));
            categorySix.setTextColor( getResources().getColor(R.color.spinnerWhite));
        }

        HomeScreen.checkDisplayBanner(rootView, HomeScreen.removeAdvertsValue);

        return rootView;
    }

    ExerciseDetailsFragment.onExerciseDetailsButtonSelected mCallback;



    //Interface for the click listener to send which button has been pushed to the home screen, and activate the fragment transaction
    public interface onExerciseDetailsButtonSelected {
        void onExerciseDetailsButtonSelected(int position);
    }

    //Attaching the interface to the MasterListFragment
    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        try {
            mCallback = (ExerciseDetailsFragment.onExerciseDetailsButtonSelected) context;
        } catch (ClassCastException e) {
            throw new ClassCastException( context.toString() + "must implement click listener" );
        }
    }


}
