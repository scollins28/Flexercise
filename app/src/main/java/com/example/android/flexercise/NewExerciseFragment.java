package com.example.android.flexercise;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

import io.apptik.widget.multiselectspinner.MultiSelectSpinner;

import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.ADD_TO_WORKOUT;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.CATEGORY_FIVE_STATE;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.CATEGORY_FOUR_STATE;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.CATEGORY_ONE_STATE;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.CATEGORY_SIX_STATE;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.CATEGORY_THREE_STATE;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.CATEGORY_TWO_STATE;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.CONTENT_URI;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.EXERCISE_NAME;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.MAX_WEIGHT;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.MEDIA_SOURCE;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.NOTES;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.NUMBER_OF_SETS;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.STARTING_WEIGHT;

public class NewExerciseFragment extends android.support.v4.app.Fragment{

    View rootView;
    ImageButton newExerciseBackButton;
    Button newExerciseDoneButton;
    Button newExerciseCategoryOneButton;
    Button newExerciseCategoryTwoButton;
    Button newExerciseCategoryThreeButton;
    Button newExerciseCategoryFourButton;
    Button newExerciseCategoryFiveButton;
    Button newExerciseCategorySixButton;
    ConstraintLayout categoriesFab;
    int newExerciseCategoryOneValue = 0;
    int newExerciseCategoryTwoValue = 0;
    int newExerciseCategoryThreeValue = 0;
    int newExerciseCategoryFourValue = 0;
    int newExerciseCategoryFiveValue = 0;
    int newExerciseCategorySixValue = 0;
    int categoriesFabValue = 0;
    ConstraintLayout categoriesHiddenLayout;
    Context mContext;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate( R.layout.new_exercise_fragment, container, false );
        mContext = getActivity();
        newExerciseBackButton = rootView.findViewById( R.id.new_exercise_back_button );
        newExerciseDoneButton = rootView.findViewById( R.id.new_exercise_done_button);
        newExerciseCategoryOneButton = rootView.findViewById( R.id.button_one );
        newExerciseCategoryTwoButton = rootView.findViewById( R.id.button_two );
        newExerciseCategoryThreeButton = rootView.findViewById( R.id.button_three );
        newExerciseCategoryFourButton = rootView.findViewById( R.id.button_four );
        newExerciseCategoryFiveButton = rootView.findViewById( R.id.button_five );
        newExerciseCategorySixButton = rootView.findViewById( R.id.button_six );
        categoriesFab = rootView.findViewById( R.id.category_name_cl );
        categoriesHiddenLayout = rootView.findViewById( R.id.categories_buttons );


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = 0;
                if (v == newExerciseBackButton) {
                    position = 0;
                }
                else if (v == newExerciseDoneButton){
                    Exercise newExercise = addExercise();
                    insertNewData (newExercise);
                    position = 1;
                }
                mCallback.onNewExerciseButtonSelected( position );
            }
        };

        View.OnClickListener categoryButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == categoriesFab) {
                    if (categoriesFabValue==0){
                        categoriesFabValue = 1;
                        categoriesHiddenLayout.setVisibility( View.VISIBLE );
                    }
                    else if (categoriesFabValue==1){
                        categoriesFabValue = 0;
                        categoriesHiddenLayout.setVisibility( View.GONE);
                    }
                }
                else if (v == newExerciseCategoryOneButton) {
                    if (newExerciseCategoryOneValue==0){
                        newExerciseCategoryOneValue = 1;
                        newExerciseCategoryTwoValue = 0;
                        newExerciseCategoryThreeValue = 0;
                        newExerciseCategoryFourValue = 0;
                        newExerciseCategoryFiveValue = 0;
                        newExerciseCategorySixValue = 0;
                        newExerciseCategoryOneButton.setBackgroundColor( getResources().getColor(R.color.colorAccent ));
                        newExerciseCategoryOneButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        newExerciseCategoryTwoButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategoryTwoButton.setTextColor( getResources().getColor(R.color.textColor));
                        newExerciseCategoryThreeButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton));
                        newExerciseCategoryThreeButton.setTextColor( getResources().getColor(R.color.textColor));
                        newExerciseCategoryFourButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategoryFourButton.setTextColor( getResources().getColor(R.color.textColor));
                        newExerciseCategoryFiveButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton));
                        newExerciseCategoryFiveButton.setTextColor( getResources().getColor(R.color.textColor));
                        newExerciseCategorySixButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton));
                        newExerciseCategorySixButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                    else {
                        newExerciseCategoryOneValue = 0;
                        newExerciseCategoryOneButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategoryOneButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == newExerciseCategoryTwoButton) {
                    if (newExerciseCategoryTwoValue==0){
                        newExerciseCategoryOneValue = 0;
                        newExerciseCategoryTwoValue = 1;
                        newExerciseCategoryThreeValue = 0;
                        newExerciseCategoryFourValue = 0;
                        newExerciseCategoryFiveValue = 0;
                        newExerciseCategorySixValue = 0;
                        newExerciseCategoryOneButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategoryOneButton.setTextColor( getResources().getColor(R.color.textColor));
                        newExerciseCategoryTwoButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        newExerciseCategoryTwoButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        newExerciseCategoryThreeButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton));
                        newExerciseCategoryThreeButton.setTextColor( getResources().getColor(R.color.textColor));
                        newExerciseCategoryFourButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategoryFourButton.setTextColor( getResources().getColor(R.color.textColor));
                        newExerciseCategoryFiveButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton));
                        newExerciseCategoryFiveButton.setTextColor( getResources().getColor(R.color.textColor));
                        newExerciseCategorySixButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton));
                        newExerciseCategorySixButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                    else {
                        newExerciseCategoryTwoValue = 0;
                        newExerciseCategoryTwoButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategoryTwoButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == newExerciseCategoryThreeButton) {
                    if (newExerciseCategoryThreeValue==0){
                        newExerciseCategoryOneValue = 0;
                        newExerciseCategoryTwoValue = 0;
                        newExerciseCategoryThreeValue = 1;
                        newExerciseCategoryFourValue = 0;
                        newExerciseCategoryFiveValue = 0;
                        newExerciseCategorySixValue = 0;
                        newExerciseCategoryOneButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategoryOneButton.setTextColor( getResources().getColor(R.color.textColor));
                        newExerciseCategoryTwoButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategoryTwoButton.setTextColor( getResources().getColor(R.color.textColor));
                        newExerciseCategoryThreeButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        newExerciseCategoryThreeButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        newExerciseCategoryFourButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategoryFourButton.setTextColor( getResources().getColor(R.color.textColor));
                        newExerciseCategoryFiveButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton));
                        newExerciseCategoryFiveButton.setTextColor( getResources().getColor(R.color.textColor));
                        newExerciseCategorySixButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton));
                        newExerciseCategorySixButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                    else {
                        newExerciseCategoryThreeValue = 0;
                        newExerciseCategoryThreeButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategoryThreeButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == newExerciseCategoryFourButton) {
                    if (newExerciseCategoryFourValue==0){
                        newExerciseCategoryOneValue = 0;
                        newExerciseCategoryTwoValue = 0;
                        newExerciseCategoryThreeValue = 0;
                        newExerciseCategoryFourValue = 1;
                        newExerciseCategoryFiveValue = 0;
                        newExerciseCategorySixValue = 0;
                        newExerciseCategoryOneButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton));
                        newExerciseCategoryOneButton.setTextColor( getResources().getColor(R.color.textColor));
                        newExerciseCategoryTwoButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategoryTwoButton.setTextColor( getResources().getColor(R.color.textColor));
                        newExerciseCategoryThreeButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton));
                        newExerciseCategoryThreeButton.setTextColor( getResources().getColor(R.color.textColor));
                        newExerciseCategoryFourButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        newExerciseCategoryFourButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        newExerciseCategoryFiveButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton));
                        newExerciseCategoryFiveButton.setTextColor( getResources().getColor(R.color.textColor));
                        newExerciseCategorySixButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton));
                        newExerciseCategorySixButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                    else {
                        newExerciseCategoryFourValue = 0;
                        newExerciseCategoryFourButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategoryFourButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == newExerciseCategoryFiveButton) {
                    if (newExerciseCategoryFiveValue==0){
                        newExerciseCategoryOneValue = 0;
                        newExerciseCategoryTwoValue = 0;
                        newExerciseCategoryThreeValue = 0;
                        newExerciseCategoryFourValue = 0;
                        newExerciseCategoryFiveValue = 1;
                        newExerciseCategorySixValue = 0;
                        newExerciseCategoryOneButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategoryOneButton.setTextColor( getResources().getColor(R.color.textColor));
                        newExerciseCategoryTwoButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategoryTwoButton.setTextColor( getResources().getColor(R.color.textColor));
                        newExerciseCategoryThreeButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton));
                        newExerciseCategoryThreeButton.setTextColor( getResources().getColor(R.color.textColor));
                        newExerciseCategoryFourButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategoryFourButton.setTextColor( getResources().getColor(R.color.textColor));
                        newExerciseCategoryFiveButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        newExerciseCategoryFiveButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        newExerciseCategorySixButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton));
                        newExerciseCategorySixButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                    else {
                        newExerciseCategoryFiveValue = 0;
                        newExerciseCategoryFiveButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategoryFiveButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == newExerciseCategorySixButton) {
                    if (newExerciseCategorySixValue==0){
                        newExerciseCategoryOneValue = 0;
                        newExerciseCategoryTwoValue = 0;
                        newExerciseCategoryThreeValue = 0;
                        newExerciseCategoryFourValue = 0;
                        newExerciseCategoryFiveValue = 0;
                        newExerciseCategorySixValue = 1;
                        newExerciseCategoryOneButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategoryOneButton.setTextColor( getResources().getColor(R.color.textColor));
                        newExerciseCategoryTwoButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategoryTwoButton.setTextColor( getResources().getColor(R.color.textColor));
                        newExerciseCategoryThreeButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton));
                        newExerciseCategoryThreeButton.setTextColor( getResources().getColor(R.color.textColor));
                        newExerciseCategoryFourButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategoryFourButton.setTextColor( getResources().getColor(R.color.textColor));
                        newExerciseCategoryFiveButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton));
                        newExerciseCategoryFiveButton.setTextColor( getResources().getColor(R.color.textColor));
                        newExerciseCategorySixButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        newExerciseCategorySixButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                    }
                    else {
                        newExerciseCategorySixValue = 0;
                        newExerciseCategorySixButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategorySixButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }


            }
        };

        //Attaching the click listener to the buttons

        newExerciseBackButton.setOnClickListener( listener );
        newExerciseDoneButton.setOnClickListener( listener );
        newExerciseCategoryOneButton.setOnClickListener( categoryButtonListener );
        newExerciseCategoryTwoButton.setOnClickListener( categoryButtonListener );
        newExerciseCategoryThreeButton.setOnClickListener( categoryButtonListener );
        newExerciseCategoryFourButton.setOnClickListener( categoryButtonListener );
        newExerciseCategoryFiveButton.setOnClickListener( categoryButtonListener );
        newExerciseCategorySixButton.setOnClickListener( categoryButtonListener );
        categoriesFab.setOnClickListener( categoryButtonListener );


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

    public Exercise addExercise (){
        EditText exerciseNameEditText = rootView.findViewById( R.id.exercise_name_edit_text );
        String exerciseName = exerciseNameEditText.getText().toString();

        int categoryOneValue = newExerciseCategoryOneValue;
        int categoryTwoValue = newExerciseCategoryTwoValue;
        int categoryThreeValue = newExerciseCategoryThreeValue;
        int categoryFourValue = newExerciseCategoryFourValue;
        int categoryFiveValue = newExerciseCategoryFiveValue;
        int categorySixValue = newExerciseCategorySixValue;

        String mediaSource = rootView.findViewById( R.id.exercise_media_spinner ).toString();

        EditText numberofSetsView = rootView.findViewById( R.id.exercise_number_of_sets_edit_text );
        String numberofSetsAsString = numberofSetsView.getText().toString();
        if (numberofSetsAsString.equals( "" )){
            numberofSetsAsString="0";
        }
        int numberofSets = Integer.parseInt(numberofSetsAsString);

        EditText maxWeightView = rootView.findViewById( R.id.exercise_max_weight_edit_text );
        String maxWeightAsString = maxWeightView.getText().toString();
        if (maxWeightAsString.equals( "" )){
            maxWeightAsString="0";
        }
        int maxWeight = Integer.parseInt(maxWeightAsString);

        EditText startingWeightView = rootView.findViewById( R.id.exercise_starting_weight_edit_text);
        String startingWeightAsString = startingWeightView.getText().toString();
        if (startingWeightAsString.equals( "" )){
            startingWeightAsString="0";
        }
        int startingWeight = Integer.parseInt(startingWeightAsString);

        String addToWorkout = rootView.findViewById( R.id.new_exercise_workout_list_spinner ).toString();
        String notes = rootView.findViewById( R.id.exercise_notes_edit_text).toString();

        Exercise exerciseToAdd = new Exercise( exerciseName, categoryOneValue, categoryTwoValue, categoryThreeValue,
                categoryFourValue, categoryFiveValue, categorySixValue, mediaSource, numberofSets, maxWeight,
                startingWeight, addToWorkout, notes);

        return exerciseToAdd;
    }

    public void insertNewData (Exercise exerciseToAdd){
        ContentValues contentValues = newContentValues(exerciseToAdd);
        mContext.getContentResolver().insert( CONTENT_URI, contentValues);
    }

    public static ContentValues newContentValues (Exercise exerciseToAdd) {
        ContentValues contentValues = new ContentValues(  );
        contentValues.put( EXERCISE_NAME, exerciseToAdd.getExerciseName());
        contentValues.put( CATEGORY_ONE_STATE, exerciseToAdd.getCategoryOneValue() );
        contentValues.put( CATEGORY_TWO_STATE, exerciseToAdd.getCategoryTwoValue() );
        contentValues.put( CATEGORY_THREE_STATE, exerciseToAdd.getCategoryThreeValue() );
        contentValues.put( CATEGORY_FOUR_STATE, exerciseToAdd.getCategoryFourValue() );
        contentValues.put( CATEGORY_FIVE_STATE, exerciseToAdd.getCategoryFiveValue() );
        contentValues.put( CATEGORY_SIX_STATE, exerciseToAdd.getCategorySixValue() );
        contentValues.put( MEDIA_SOURCE, exerciseToAdd.getMediaSource() );
        contentValues.put( NUMBER_OF_SETS, exerciseToAdd.getNumberofSets() );
        contentValues.put( MAX_WEIGHT, exerciseToAdd.getMaxWeight() );
        contentValues.put( STARTING_WEIGHT, exerciseToAdd.getStartingWeight() );
        contentValues.put( ADD_TO_WORKOUT, exerciseToAdd.getAddToWorkout() );
        contentValues.put( NOTES, exerciseToAdd.getNotes() );
        return contentValues;
    }

}
