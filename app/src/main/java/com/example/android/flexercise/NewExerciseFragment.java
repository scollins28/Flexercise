package com.example.android.flexercise;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
import android.widget.TextView;

import com.example.android.flexercise.Database.ExerciseUpdateWorkoutLists;

import java.util.ArrayList;

import io.apptik.widget.multiselectspinner.MultiSelectSpinner;

import static android.provider.BaseColumns._ID;
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
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.WORKOUT_EIGHT;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.WORKOUT_EIGHTEEN;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.WORKOUT_ELEVEN;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.WORKOUT_FIFTEEN;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.WORKOUT_FIVE;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.WORKOUT_FOUR;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.WORKOUT_FOURTEEN;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.WORKOUT_NINE;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.WORKOUT_NINETEEN;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.WORKOUT_ONE;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.WORKOUT_SEVEN;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.WORKOUT_SEVENTEEN;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.WORKOUT_SIX;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.WORKOUT_SIXTEEN;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.WORKOUT_TEN;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.WORKOUT_THIRTEEN;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.WORKOUT_THREE;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.WORKOUT_TWELVE;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.WORKOUT_TWENTY;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.WORKOUT_TWO;
import static com.example.android.flexercise.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.WORKOUT_CONTENT_URI;
import static com.example.android.flexercise.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.WORKOUT_NAME;

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
    TextView maxWeightTv;
    TextView startingWeightTv;
    static Context context;
    EditText startingWeightEditText;
    EditText maxWeightEditText;
    ArrayList <String> options;
    public static int newID;

    public NewExerciseFragment(){

    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate( R.layout.new_exercise_fragment, container, false );
        mContext = getActivity();
        context = mContext;
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
        maxWeightTv = rootView.findViewById( R.id.exercise_max_weight_tv );
        startingWeightTv = rootView.findViewById( R.id.exercise_starting_weight_tv );
        maxWeightEditText = rootView.findViewById( R.id.exercise_max_weight_edit_text);
        startingWeightEditText = rootView.findViewById( R.id.exercise_starting_weight_edit_text );

        if (HomeScreen.kgValue==1){
            maxWeightTv.setText( R.string.exercise_max_weight_KG );
            maxWeightEditText.setHint( R.string.exercise_max_weight_hint_KG );
            startingWeightTv.setText( R.string.exercise_starting_weight_KG);
            startingWeightEditText.setHint( R.string.exercise_starting_weight_hint_KG);
        }
        else {
            maxWeightTv.setText( R.string.exercise_max_weight_LBS );
            maxWeightEditText.setHint( R.string.exercise_max_weight_hint_LBS );
            startingWeightTv.setText( R.string.exercise_starting_weight_LBS);
            startingWeightEditText.setHint( R.string.exercise_starting_weight_hint_LBS);
        }


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
                    new ExerciseUpdateWorkoutLists(newExercise, mContext);
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
                        newExerciseCategoryOneButton.setBackgroundColor( getResources().getColor(R.color.colorAccent ));
                        newExerciseCategoryOneButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        }
                    else {
                        newExerciseCategoryOneValue = 0;
                        newExerciseCategoryOneButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategoryOneButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == newExerciseCategoryTwoButton) {
                    if (newExerciseCategoryTwoValue==0){
                        newExerciseCategoryTwoValue = 1;
                        newExerciseCategoryTwoButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        newExerciseCategoryTwoButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        }
                    else {
                        newExerciseCategoryTwoValue = 0;
                        newExerciseCategoryTwoButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategoryTwoButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == newExerciseCategoryThreeButton) {
                    if (newExerciseCategoryThreeValue==0){
                        newExerciseCategoryThreeValue = 1;
                        newExerciseCategoryThreeButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        newExerciseCategoryThreeButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        }
                    else {
                        newExerciseCategoryThreeValue = 0;
                        newExerciseCategoryThreeButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategoryThreeButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == newExerciseCategoryFourButton) {
                    if (newExerciseCategoryFourValue==0){
                        newExerciseCategoryFourValue = 1;
                        newExerciseCategoryFourButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        newExerciseCategoryFourButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        }
                    else {
                        newExerciseCategoryFourValue = 0;
                        newExerciseCategoryFourButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategoryFourButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == newExerciseCategoryFiveButton) {
                    if (newExerciseCategoryFiveValue==0){
                        newExerciseCategoryFiveValue = 1;
                        newExerciseCategoryFiveButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        newExerciseCategoryFiveButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        }
                    else {
                        newExerciseCategoryFiveValue = 0;
                        newExerciseCategoryFiveButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategoryFiveButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == newExerciseCategorySixButton) {
                    if (newExerciseCategorySixValue==0){
                        newExerciseCategorySixValue = 1;
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


        options = new ArrayList<>();
        Cursor cursor = getActivity().getContentResolver().query( WORKOUT_CONTENT_URI, null, null, null, null );
        cursor.moveToFirst();
        for (int i=0; i<cursor.getCount(); i++){
            cursor.moveToPosition( i );
            String newWorkout = cursor.getString( cursor.getColumnIndex( WORKOUT_NAME) );
            options.add(newWorkout);
        }
        cursor.close();
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

        MultiSelectSpinner addToWorkoutSpinner = rootView.findViewById( R.id.new_exercise_workout_list_spinner );
        boolean[] checked = addToWorkoutSpinner.getSelected();
        ArrayList<String>  addToWorkoutRawValues =  new ArrayList<String>();
        ArrayList<Integer> workoutsExerciseIsOn = new ArrayList<>( );
        Cursor cursor = getActivity().getContentResolver().query( WORKOUT_CONTENT_URI, null, null, null, null );
        cursor.moveToFirst();
        int hasValue = 0;
        for (int i = 0; i < (checked.length); i++){
            cursor.moveToPosition( i );
            if (checked[i]==true){
                if (hasValue == 1){
                    String toAdd = options.get( i );
                    addToWorkoutRawValues.add( toAdd );
                }
                else {
                    String starterString = options.get( i );
                    addToWorkoutRawValues.clear();
                    addToWorkoutRawValues.add( starterString );
                    hasValue = 1;
                }
                workoutsExerciseIsOn.add( cursor.getInt( cursor.getColumnIndex( _ID ) ) );
            }
        }

        while (workoutsExerciseIsOn.size()<20){
            workoutsExerciseIsOn.add( 0 );
        }

        EditText notesView = rootView.findViewById( R.id.exercise_notes_edit_text );
        String notes = notesView.getText().toString();

        Exercise exerciseToAdd = new Exercise( exerciseName, categoryOneValue, categoryTwoValue, categoryThreeValue,
                categoryFourValue, categoryFiveValue, categorySixValue, mediaSource, numberofSets, maxWeight,
                startingWeight, addToWorkoutRawValues, notes, context, workoutsExerciseIsOn);

        return exerciseToAdd;
    }

    public void insertNewData (Exercise exerciseToAdd){
        ContentValues contentValues = newContentValues(exerciseToAdd);
        mContext.getContentResolver().insert( CONTENT_URI, contentValues);
        exerciseToAdd.mID = newID;
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
        int i = 0;
        contentValues.put( WORKOUT_ONE, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_TWO, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_THREE, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_FOUR, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_FIVE, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_SIX, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_SEVEN, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_EIGHT, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_NINE, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_TEN, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_ELEVEN, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_TWELVE, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_THIRTEEN, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_FOURTEEN, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_FIFTEEN, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_SIXTEEN, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_SEVENTEEN, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_EIGHTEEN, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_NINETEEN, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_TWENTY, exerciseToAdd.getWorkoutIds().get(i) );
        return contentValues;
    }

}
