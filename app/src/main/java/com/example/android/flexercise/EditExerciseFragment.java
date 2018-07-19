package com.example.android.flexercise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.android.flexercise.Database.ExerciseTableCleaner;
import com.example.android.flexercise.Database.ExerciseUpdateWorkoutLists;
import com.example.android.flexercise.Database.WorkoutsDatabase.WorkoutContract;
import com.example.android.flexercise.Database.WorkoutsDatabase.WorkoutsTableCleaner;

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

public class EditExerciseFragment extends android.support.v4.app.Fragment{

    View rootView;
    ImageButton editExerciseBackButton;
    Button editExerciseDoneButton;
    Button editExerciseCategoryOneButton;
    Button editExerciseCategoryTwoButton;
    Button editExerciseCategoryThreeButton;
    Button editExerciseCategoryFourButton;
    Button editExerciseCategoryFiveButton;
    Button editExerciseCategorySixButton;
    ConstraintLayout categoriesFab;
    int editExerciseCategoryOneValue;
    int editExerciseCategoryTwoValue;
    int editExerciseCategoryThreeValue;
    int editExerciseCategoryFourValue;
    int editExerciseCategoryFiveValue;
    int editExerciseCategorySixValue;
    int categoriesFabValue = 0;
    ConstraintLayout categoriesHiddenLayout;
    Context mContext;
    static Context context;
    ArrayList <String> options;
    Exercise exerciseToEdit;
    EditText exerciseNameEditText;
    EditText numberOfSetsEditText;
    EditText maxWeightEditText;
    EditText startingWeightEditText;
    EditText notesEditText;
    Spinner mediaSpinner;
    TextView maxWeightTv;
    TextView startingWeightTv;
    MultiSelectSpinner exerciseListMultiSpinner;
    MultiSelectSpinner addToWorkoutSpinner;


    public EditExerciseFragment () {

    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        rootView = inflater.inflate( R.layout.new_exercise_fragment, container, false );
        mContext = getActivity();
        context = mContext;
        editExerciseBackButton = rootView.findViewById( R.id.new_exercise_back_button );
        editExerciseDoneButton = rootView.findViewById( R.id.new_exercise_done_button);
        editExerciseCategoryOneButton = rootView.findViewById( R.id.button_one );
        editExerciseCategoryTwoButton = rootView.findViewById( R.id.button_two );
        editExerciseCategoryThreeButton = rootView.findViewById( R.id.button_three );
        editExerciseCategoryFourButton = rootView.findViewById( R.id.button_four );
        editExerciseCategoryFiveButton = rootView.findViewById( R.id.button_five );
        editExerciseCategorySixButton = rootView.findViewById( R.id.button_six );
        categoriesFab = rootView.findViewById( R.id.category_name_cl );
        categoriesHiddenLayout = rootView.findViewById( R.id.categories_buttons );
        maxWeightTv = rootView.findViewById( R.id.exercise_max_weight_tv );
        maxWeightEditText = rootView.findViewById( R.id.exercise_max_weight_edit_text );
        startingWeightTv = rootView.findViewById( R.id.exercise_starting_weight_tv );
        startingWeightEditText= rootView.findViewById( R.id.exercise_starting_weight_edit_text );

        if (HomeScreen.kgValue==1){
            maxWeightTv.setText( R.string.exercise_max_weight_KG );
            maxWeightEditText.setHint( R.string.exercise_max_weight_hint_KG );
            startingWeightTv.setText( R.string.exercise_starting_weight_KG);
            startingWeightTv.setHint( R.string.exercise_starting_weight_hint_KG);
        }
        else {
            maxWeightTv.setText( R.string.exercise_max_weight_LBS );
            maxWeightEditText.setHint( R.string.exercise_max_weight_hint_LBS );
            startingWeightTv.setText( R.string.exercise_starting_weight_LBS);
            startingWeightTv.setHint( R.string.exercise_starting_weight_hint_LBS);
        }

        exerciseNameEditText = rootView.findViewById( R.id.exercise_name_edit_text );
        exerciseNameEditText.setText( exerciseToEdit.getExerciseName() );
        numberOfSetsEditText = rootView.findViewById( R.id.exercise_number_of_sets_edit_text);
        String numberOfSetsString = Integer.toString(exerciseToEdit.getNumberofSets());
        numberOfSetsEditText.setText(numberOfSetsString);
        maxWeightEditText = rootView.findViewById( R.id.exercise_max_weight_edit_text);
        String maxWeightString = Integer.toString( exerciseToEdit.getMaxWeight() );
        maxWeightEditText.setText( maxWeightString);
        startingWeightEditText = rootView.findViewById( R.id.exercise_starting_weight_edit_text);
        String startingWeightString = Integer.toString( exerciseToEdit.getStartingWeight() );
        startingWeightEditText.setText( startingWeightString);
        notesEditText = rootView.findViewById( R.id.exercise_notes_edit_text);
        notesEditText.setText( exerciseToEdit.getNotes());
        mediaSpinner = rootView.findViewById( R.id.exercise_media_spinner);
        /////////////////////////////////////////////////////////
        exerciseListMultiSpinner= rootView.findViewById( R.id.new_exercise_workout_list_spinner);
        addToWorkoutSpinner = rootView.findViewById( R.id.new_exercise_workout_list_spinner);


        editExerciseCategoryOneValue = exerciseToEdit.mCategoryOneValue;
        editExerciseCategoryTwoValue = exerciseToEdit.mCategoryTwoValue;
        editExerciseCategoryThreeValue = exerciseToEdit.mCategoryThreeValue;
        editExerciseCategoryFourValue = exerciseToEdit.mCategoryFourValue;
        editExerciseCategoryFiveValue = exerciseToEdit.mCategoryFiveValue;
        editExerciseCategorySixValue = exerciseToEdit.mCategorySixValue;

        if (editExerciseCategoryOneValue==1){
            editExerciseCategoryOneButton.setBackgroundColor( getResources().getColor(R.color.colorAccent ));
            editExerciseCategoryOneButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
        }
        if (editExerciseCategoryTwoValue==1){
            editExerciseCategoryTwoButton.setBackgroundColor( getResources().getColor(R.color.colorAccent ));
            editExerciseCategoryTwoButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
        }
        if (editExerciseCategoryThreeValue==1){
            editExerciseCategoryThreeButton.setBackgroundColor( getResources().getColor(R.color.colorAccent ));
            editExerciseCategoryThreeButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
        }
        if (editExerciseCategoryFourValue==1){
            editExerciseCategoryFourButton.setBackgroundColor( getResources().getColor(R.color.colorAccent ));
            editExerciseCategoryFourButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
        }
        if (editExerciseCategoryFiveValue==1){
            editExerciseCategoryFiveButton.setBackgroundColor( getResources().getColor(R.color.colorAccent ));
            editExerciseCategoryFiveButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
        }
        if (editExerciseCategorySixValue==1){
            editExerciseCategorySixButton.setBackgroundColor( getResources().getColor(R.color.colorAccent ));
            editExerciseCategorySixButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
        }


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = 0;
                if (v == editExerciseBackButton) {
                    position = 0;
                }
                else if (v == editExerciseDoneButton){
                    Exercise newExercise = updateExercise();
                    updateData (newExercise);
                    new ExerciseUpdateWorkoutLists(newExercise, mContext);
                    position = 1;
                }
                mCallback.onEditExerciseButtonSelected( position );
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
                else if (v == editExerciseCategoryOneButton) {
                    if (editExerciseCategoryOneValue==0){
                        editExerciseCategoryOneValue = 1;
                        editExerciseCategoryOneButton.setBackgroundColor( getResources().getColor(R.color.colorAccent ));
                        editExerciseCategoryOneButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        }
                    else {
                        editExerciseCategoryOneValue = 0;
                        editExerciseCategoryOneButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        editExerciseCategoryOneButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == editExerciseCategoryTwoButton) {
                    if (editExerciseCategoryTwoValue==0){
                        editExerciseCategoryTwoValue = 1;
                        editExerciseCategoryTwoButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        editExerciseCategoryTwoButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        }
                    else {
                        editExerciseCategoryTwoValue = 0;
                        editExerciseCategoryTwoButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        editExerciseCategoryTwoButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == editExerciseCategoryThreeButton) {
                    if (editExerciseCategoryThreeValue==0){
                        editExerciseCategoryThreeValue = 1;
                        editExerciseCategoryThreeButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        editExerciseCategoryThreeButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        }
                    else {
                        editExerciseCategoryThreeValue = 0;
                        editExerciseCategoryThreeButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        editExerciseCategoryThreeButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == editExerciseCategoryFourButton) {
                    if (editExerciseCategoryFourValue==0){
                        editExerciseCategoryFourValue = 1;
                        editExerciseCategoryFourButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        editExerciseCategoryFourButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        }
                    else {
                        editExerciseCategoryFourValue = 0;
                        editExerciseCategoryFourButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        editExerciseCategoryFourButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == editExerciseCategoryFiveButton) {
                    if (editExerciseCategoryFiveValue==0){
                        editExerciseCategoryFiveValue = 1;
                        editExerciseCategoryFiveButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        editExerciseCategoryFiveButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        }
                    else {
                        editExerciseCategoryFiveValue = 0;
                        editExerciseCategoryFiveButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        editExerciseCategoryFiveButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == editExerciseCategorySixButton) {
                    if (editExerciseCategorySixValue==0){
                        editExerciseCategorySixValue = 1;
                        editExerciseCategorySixButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        editExerciseCategorySixButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                    }
                    else {
                        editExerciseCategorySixValue = 0;
                        editExerciseCategorySixButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        editExerciseCategorySixButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }


            }
        };

        //Attaching the click listener to the buttons

        editExerciseBackButton.setOnClickListener( listener );
        editExerciseDoneButton.setOnClickListener( listener );
        editExerciseCategoryOneButton.setOnClickListener( categoryButtonListener );
        editExerciseCategoryTwoButton.setOnClickListener( categoryButtonListener );
        editExerciseCategoryThreeButton.setOnClickListener( categoryButtonListener );
        editExerciseCategoryFourButton.setOnClickListener( categoryButtonListener );
        editExerciseCategoryFiveButton.setOnClickListener( categoryButtonListener );
        editExerciseCategorySixButton.setOnClickListener( categoryButtonListener );
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
        final MultiSelectSpinner multiSelectSpinner = (MultiSelectSpinner) rootView.findViewById(R.id.new_exercise_workout_list_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter <String>(getActivity(), android.R.layout.simple_list_item_multiple_choice, options);

        multiSelectSpinner
                .setListAdapter(adapter);
        new MultiSelectSpinner.MultiSpinnerListener() {
            boolean [] checkStatus = checkState();
            @Override
            public void onItemsSelected(boolean[] checkList) {
                for (int x = 0; x<checkStatus.length; x++){
                    Log.e ("STATUS", String.valueOf(checkStatus[x]));
                    multiSelectSpinner.setSelection( x, checkStatus[x] );
                }}
        };
        return rootView;
    }

    EditExerciseFragment.onEditExerciseButtonSelected mCallback;

    //Interface for the click listener to send which button has been pushed to the home screen, and activate the fragment transaction
    public interface onEditExerciseButtonSelected {
        void onEditExerciseButtonSelected(int position);
    }

    //Attaching the interface to the MasterListFragment
    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        try {
            mCallback = (EditExerciseFragment.onEditExerciseButtonSelected) context;
        } catch (ClassCastException e) {
            throw new ClassCastException( context.toString() + "must implement click listener" );
        }
    }

    public Exercise updateExercise (){

        EditText exerciseNameEditText = rootView.findViewById( R.id.exercise_name_edit_text );
        String exerciseName = exerciseNameEditText.getText().toString();

        int categoryOneValue = editExerciseCategoryOneValue;
        int categoryTwoValue = editExerciseCategoryTwoValue;
        int categoryThreeValue = editExerciseCategoryThreeValue;
        int categoryFourValue = editExerciseCategoryFourValue;
        int categoryFiveValue = editExerciseCategoryFiveValue;
        int categorySixValue = editExerciseCategorySixValue;

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

        boolean[] checked = addToWorkoutSpinner.getSelected();
        ArrayList<String>  addToWorkoutRawValues =  new ArrayList<String>();
        ArrayList<Integer>  workoutsToAdd =  new ArrayList<Integer>();
        int hasValue = 0;
        Cursor cursor = getActivity().getContentResolver().query( WORKOUT_CONTENT_URI, null, null, null, null );
        cursor.moveToFirst();

        for (int i = 0; i < (checked.length); i++){
            if (checked[i]){
                if (hasValue == 1){
                    String toAdd = options.get( i );
                    addToWorkoutRawValues.add( toAdd );
                    for (int x = 0; x < cursor.getCount(); x++) {
                        cursor.moveToPosition( x );
                        if (cursor.getString( cursor.getColumnIndex( WORKOUT_NAME ) ).equals( toAdd )) {
                            int toAddId = cursor.getInt( cursor.getColumnIndex( _ID ) );
                            workoutsToAdd.add( toAddId );
                        }
                    }
                }
                else {
                    String starterString = options.get( i );
                    addToWorkoutRawValues.clear();
                    addToWorkoutRawValues.add( starterString );
                    hasValue = 1;
                    for (int x = 0; x < cursor.getCount(); x++) {
                        cursor.moveToPosition( x );
                        if (cursor.getString( cursor.getColumnIndex( WORKOUT_NAME ) ).equals( options.get(i) )) {
                            int toAddId = cursor.getInt( cursor.getColumnIndex( _ID ) );
                            workoutsToAdd.add( toAddId );
                        }
                    }
                }

            }
        }
        while (workoutsToAdd.size()<20) {
            workoutsToAdd.add( 0 );
        }

        EditText notesView = rootView.findViewById( R.id.exercise_notes_edit_text );
        String notes = notesView.getText().toString();

        Exercise exerciseToAdd = new Exercise( exerciseName, categoryOneValue, categoryTwoValue, categoryThreeValue,
                categoryFourValue, categoryFiveValue, categorySixValue, mediaSource, numberofSets, maxWeight,
                startingWeight, addToWorkoutRawValues, notes, context);
        exerciseToAdd.mWorkoutIds =workoutsToAdd;
        exerciseToAdd.mID = exerciseToEdit.mID;

        return exerciseToAdd;
    }

    public void updateData (Exercise exerciseToAdd){
        HomeScreen.exercise = ExerciseTableCleaner.cleanSingleExercise (context, exerciseToAdd);
    //    ContentValues contentValues = newContentValues(exerciseToAdd);
    //    long id = exerciseToEdit.getID();
    //    Uri updateUri =  CONTENT_URI.buildUpon().appendPath(Long.toString(id)).build();
     //   mContext.getContentResolver().update( updateUri, contentValues, null, null);
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

    public boolean [] checkState(){
        boolean[] checkList = addToWorkoutSpinner.getSelected();
        for (int i = 0; i < (checkList.length); i++) {
            String checklistOption = options.get( i );
            Cursor cursor = getActivity().getContentResolver().query( WORKOUT_CONTENT_URI, null, null, null, null );
            cursor.moveToFirst();
            int toCompareAgainstId = 0;
            for (int x = 0; x < cursor.getCount(); x++) {
                cursor.moveToPosition( x );
                String toCompareAgainst = cursor.getString( cursor.getColumnIndex( WORKOUT_NAME ) );
                if (toCompareAgainst.equals( checklistOption )) {
                    toCompareAgainstId = cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable._ID ) );
                    Log.e( "blah", String.valueOf( exerciseToEdit.getWorkoutIds() ) );
                }
            }
            if (toCompareAgainstId>0){
                if (toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 0 ) || toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 1 ) ||
                        toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 2 ) || toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 3 ) ||
                        toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 4 ) || toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 5 ) ||
                        toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 6 ) || toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 7 ) ||
                        toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 8 ) || toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 9 ) ||
                        toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 10 ) || toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 11 ) ||
                        toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 12 ) || toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 13 ) ||
                        toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 14 ) || toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 15 ) ||
                        toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 16 ) || toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 17 ) ||
                        toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 18 ) || toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 19 )){

                    checkList[i] = true;
                }
            }
            Log.e (String.valueOf( i ), String.valueOf(checkList[i]));
        }
        return checkList;
    }

}