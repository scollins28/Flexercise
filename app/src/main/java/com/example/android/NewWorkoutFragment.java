package com.example.android;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import com.example.android.Database.WorkoutsDatabase.WorkoutUpdateExerciseLists;
import com.example.android.free.R;
import java.util.ArrayList;
import io.apptik.widget.multiselectspinner.MultiSelectSpinner;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.CATEGORY_FIVE_STATE;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.CATEGORY_FOUR_STATE;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.CATEGORY_ONE_STATE;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.CATEGORY_SIX_STATE;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.CATEGORY_THREE_STATE;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.CATEGORY_TWO_STATE;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.EXERCISE_EIGHTEEN_ID;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.EXERCISE_EIGHT_ID;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.EXERCISE_ELEVEN_ID;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.EXERCISE_FIFTEEN_ID;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.EXERCISE_FIVE_ID;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.EXERCISE_FOURTEEN_ID;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.EXERCISE_FOUR_ID;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.EXERCISE_NINETEEN_ID;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.EXERCISE_NINE_ID;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.EXERCISE_ONE_ID;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.EXERCISE_SEVENTEEN_ID;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.EXERCISE_SEVEN_ID;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.EXERCISE_SIXTEEN_ID;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.EXERCISE_SIX_ID;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.EXERCISE_TEN_ID;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.EXERCISE_THIRTEEN_ID;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.EXERCISE_THREE_ID;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.EXERCISE_TWELVE_ID;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.EXERCISE_TWENTY_ID;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.EXERCISE_TWO_ID;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.WORKOUT_CONTENT_URI;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.WORKOUT_NAME;

public class NewWorkoutFragment extends android.support.v4.app.Fragment{

    View rootView;
    ImageButton newWorkoutBackButton;
    Button newWorkoutDoneButton;
    Button newWorkoutCategoryOneButton;
    Button newWorkoutCategoryTwoButton;
    Button newWorkoutCategoryThreeButton;
    Button newWorkoutCategoryFourButton;
    Button newWorkoutCategoryFiveButton;
    Button newWorkoutCategorySixButton;
    ConstraintLayout categoriesFab;
    int newWorkoutCategoryOneValue = 0;
    int newWorkoutCategoryTwoValue = 0;
    int newWorkoutCategoryThreeValue = 0;
    int newWorkoutCategoryFourValue = 0;
    int newWorkoutCategoryFiveValue = 0;
    int newWorkoutCategorySixValue = 0;
    int categoriesFabValue = 0;
    ConstraintLayout categoriesHiddenLayout;
    Context mContext;
    static Context context;
    ArrayList<String> options;
    Toolbar mToolbar;

    public NewWorkoutFragment(){

    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate( R.layout.new_workout_fragment, container, false );
        mContext = getActivity();
        context = mContext;

        mToolbar = rootView.findViewById( R.id.toolbar );

        newWorkoutBackButton = rootView.findViewById( R.id.new_workout_back_button );
        newWorkoutDoneButton = rootView.findViewById( R.id.new_workout_done_button);

        newWorkoutCategoryOneButton = rootView.findViewById( R.id.button_one );
        newWorkoutCategoryTwoButton = rootView.findViewById( R.id.button_two );
        newWorkoutCategoryThreeButton = rootView.findViewById( R.id.button_three );
        newWorkoutCategoryFourButton = rootView.findViewById( R.id.button_four );
        newWorkoutCategoryFiveButton = rootView.findViewById( R.id.button_five );
        newWorkoutCategorySixButton = rootView.findViewById( R.id.button_six );
        categoriesFab = rootView.findViewById( R.id.category_name_cl );
        categoriesHiddenLayout = rootView.findViewById( R.id.categories_buttons );

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = 0;
                if (v == newWorkoutBackButton) {
                    position = 0;
                }
                else if (v == newWorkoutDoneButton) {
                    if (newWorkoutCategoryOneValue == 0 && newWorkoutCategoryTwoValue == 0 &&
                            newWorkoutCategoryThreeValue == 0 && newWorkoutCategoryFourValue == 0
                            && newWorkoutCategoryFiveValue == 0 && newWorkoutCategorySixValue == 0) {
                        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder( getContext() );
                        builder.setCancelable( true );
                        builder.setTitle( R.string.no_category );
                        builder.setMessage( R.string.please_select_a_category_workout_version );
                        builder.setPositiveButton( R.string.confirm,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                } );
                        builder.setNegativeButton( R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        } );

                        android.support.v7.app.AlertDialog dialog = builder.create();
                        dialog.show();
                    } else {
                        Workout newWorkout = addWorkout();
                        insertNewData( newWorkout );
                        new WorkoutUpdateExerciseLists( newWorkout, mContext );
                        position = 1;
                    }
                }
                mCallback.onNewWorkoutButtonSelected( position );
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
                else if (v == newWorkoutCategoryOneButton) {
                    if (newWorkoutCategoryOneValue==0){
                        newWorkoutCategoryOneValue = 1;
                        newWorkoutCategoryOneButton.setBackgroundColor( getResources().getColor(R.color.colorAccent ));
                        newWorkoutCategoryOneButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        }
                    else {
                        newWorkoutCategoryOneValue = 0;
                        newWorkoutCategoryOneButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newWorkoutCategoryOneButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == newWorkoutCategoryTwoButton) {
                    if (newWorkoutCategoryTwoValue==0){
                        newWorkoutCategoryTwoValue = 1;
                        newWorkoutCategoryTwoButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        newWorkoutCategoryTwoButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        }
                    else {
                        newWorkoutCategoryTwoValue = 0;
                        newWorkoutCategoryTwoButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newWorkoutCategoryTwoButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == newWorkoutCategoryThreeButton) {
                    if (newWorkoutCategoryThreeValue==0){
                        newWorkoutCategoryThreeValue = 1;
                        newWorkoutCategoryThreeButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        newWorkoutCategoryThreeButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        }
                    else {
                        newWorkoutCategoryThreeValue = 0;
                        newWorkoutCategoryThreeButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newWorkoutCategoryThreeButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == newWorkoutCategoryFourButton) {
                    if (newWorkoutCategoryFourValue==0){
                        newWorkoutCategoryFourValue = 1;
                        newWorkoutCategoryFourButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        newWorkoutCategoryFourButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        }
                    else {
                        newWorkoutCategoryFourValue = 0;
                        newWorkoutCategoryFourButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newWorkoutCategoryFourButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == newWorkoutCategoryFiveButton) {
                    if (newWorkoutCategoryFiveValue==0){
                        newWorkoutCategoryFiveValue = 1;
                        newWorkoutCategoryFiveButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        newWorkoutCategoryFiveButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        }
                    else {
                        newWorkoutCategoryFiveValue = 0;
                        newWorkoutCategoryFiveButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newWorkoutCategoryFiveButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == newWorkoutCategorySixButton) {
                    if (newWorkoutCategorySixValue==0){
                        newWorkoutCategorySixValue = 1;
                        newWorkoutCategorySixButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        newWorkoutCategorySixButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                    }
                    else {
                        newWorkoutCategorySixValue = 0;
                        newWorkoutCategorySixButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newWorkoutCategorySixButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }


            }
        };


        //Attaching the click listener to the buttons

        newWorkoutBackButton.setOnClickListener( listener );
        newWorkoutDoneButton.setOnClickListener( listener );
        newWorkoutCategoryOneButton.setOnClickListener( categoryButtonListener );
        newWorkoutCategoryTwoButton.setOnClickListener( categoryButtonListener );
        newWorkoutCategoryThreeButton.setOnClickListener( categoryButtonListener );
        newWorkoutCategoryFourButton.setOnClickListener( categoryButtonListener );
        newWorkoutCategoryFiveButton.setOnClickListener( categoryButtonListener );
        newWorkoutCategorySixButton.setOnClickListener( categoryButtonListener );
        categoriesFab.setOnClickListener( categoryButtonListener );

        options = new ArrayList<>();
        if (HomeScreen.exercisesFromLoader!=null) {
            for (int i = 0; i < HomeScreen.exercisesFromLoader.size(); i++) {
                String newExercise = HomeScreen.exercisesFromLoader.get( i ).getExerciseName();
                options.add( newExercise );
            }
        }
        MultiSelectSpinner multiSelectSpinner = rootView.findViewById(R.id.new_exercise_workout_list_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter <String>(getActivity(), android.R.layout.simple_list_item_multiple_choice, options);

        multiSelectSpinner
                .setListAdapter(adapter);
        new MultiSelectSpinner.MultiSpinnerListener() {
            @Override
            public void onItemsSelected(boolean[] checkedItems) {
            }
        };

        HomeScreen.checkDisplayBanner(rootView, HomeScreen.removeAdvertsValue);

        return rootView;
    }

    NewWorkoutFragment.onNewWorkoutButtonSelected mCallback;



    //Interface for the click listener to send which button has been pushed to the home screen, and activate the fragment transaction
    public interface onNewWorkoutButtonSelected {
        void onNewWorkoutButtonSelected(int position);
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

    public Workout addWorkout (){

        EditText workoutNameEditText = rootView.findViewById( R.id.workout_name_edit_text);
        String workoutName = workoutNameEditText.getText().toString();

        int categoryOneValue = newWorkoutCategoryOneValue;
        int categoryTwoValue = newWorkoutCategoryTwoValue;
        int categoryThreeValue = newWorkoutCategoryThreeValue;
        int categoryFourValue = newWorkoutCategoryFourValue;
        int categoryFiveValue = newWorkoutCategoryFiveValue;
        int categorySixValue = newWorkoutCategorySixValue;

        MultiSelectSpinner addToWorkoutSpinner = rootView.findViewById( R.id.new_exercise_workout_list_spinner );
        boolean[] checked = addToWorkoutSpinner.getSelected();
        ArrayList<Integer>  exercisesToAdd =  new ArrayList<Integer>();
        for (int i = 0; i < (checked.length); i++){
            if (checked[i]){
                String toAdd = options.get( i );
                if (HomeScreen.exercisesFromLoader!=null){
                for (int x=0; x<HomeScreen.exercisesFromLoader.size(); x++){
                    String toCompare = HomeScreen.exercisesFromLoader.get( x ).getExerciseName();
                    if (toCompare.equals( toAdd )){
                        int toAddId = HomeScreen.exercisesFromLoader.get( x ).getID();
                        exercisesToAdd.add( toAddId );
                }
                }
            }
            }
        }
            while (exercisesToAdd.size()<20) {
                exercisesToAdd.add( 0 );
        }


        Workout workoutToAdd = new Workout( workoutName, categoryOneValue, categoryTwoValue, categoryThreeValue,
                categoryFourValue, categoryFiveValue, categorySixValue, exercisesToAdd.get( 0 ), exercisesToAdd.get( 1 ),
                exercisesToAdd.get( 2 ), exercisesToAdd.get( 3 ), exercisesToAdd.get( 4 ), exercisesToAdd.get( 5 ),
                exercisesToAdd.get( 6 ), exercisesToAdd.get( 7 ), exercisesToAdd.get( 8 ), exercisesToAdd.get( 9 ),
                exercisesToAdd.get( 10 ), exercisesToAdd.get( 11 ), exercisesToAdd.get( 12 ), exercisesToAdd.get( 13 ),
                exercisesToAdd.get( 14 ), exercisesToAdd.get( 15 ), exercisesToAdd.get( 16 ), exercisesToAdd.get( 17 ),
                exercisesToAdd.get( 18 ), exercisesToAdd.get( 19 ));

        return workoutToAdd;
    }

    public void insertNewData (Workout workoutToAdd){
        ContentValues contentValues = newContentValues(workoutToAdd);
        mContext.getContentResolver().insert( WORKOUT_CONTENT_URI, contentValues);
    }

    public static ContentValues newContentValues (Workout workoutToAdd) {
        ContentValues contentValues = new ContentValues(  );
        contentValues.put( WORKOUT_NAME, workoutToAdd.getWorkoutName());
        contentValues.put( CATEGORY_ONE_STATE, workoutToAdd.getCategoryOneValue() );
        contentValues.put( CATEGORY_TWO_STATE, workoutToAdd.getCategoryTwoValue() );
        contentValues.put( CATEGORY_THREE_STATE, workoutToAdd.getCategoryThreeValue() );
        contentValues.put( CATEGORY_FOUR_STATE, workoutToAdd.getCategoryFourValue() );
        contentValues.put( CATEGORY_FIVE_STATE, workoutToAdd.getCategoryFiveValue() );
        contentValues.put( CATEGORY_SIX_STATE, workoutToAdd.getCategorySixValue() );
        contentValues.put( EXERCISE_ONE_ID, workoutToAdd.getExerciseOneId() );
        contentValues.put( EXERCISE_TWO_ID, workoutToAdd.getExerciseTwoId() );
        contentValues.put( EXERCISE_THREE_ID, workoutToAdd.getExerciseThreeId() );
        contentValues.put( EXERCISE_FOUR_ID, workoutToAdd.getExerciseFourId() );
        contentValues.put( EXERCISE_FIVE_ID, workoutToAdd.getExerciseFiveId() );
        contentValues.put( EXERCISE_SIX_ID, workoutToAdd.getExerciseSixId() );
        contentValues.put( EXERCISE_SEVEN_ID, workoutToAdd.getExerciseSevenId() );
        contentValues.put( EXERCISE_EIGHT_ID, workoutToAdd.getExerciseEightId() );
        contentValues.put( EXERCISE_NINE_ID, workoutToAdd.getExerciseNineId() );
        contentValues.put( EXERCISE_TEN_ID, workoutToAdd.getExerciseTenId() );
        contentValues.put( EXERCISE_ELEVEN_ID, workoutToAdd.getExerciseElevenId() );
        contentValues.put( EXERCISE_TWELVE_ID, workoutToAdd.getExerciseTwelveId() );
        contentValues.put( EXERCISE_THIRTEEN_ID, workoutToAdd.getExerciseThirteenId() );
        contentValues.put( EXERCISE_FOURTEEN_ID, workoutToAdd.getExerciseFourteenId() );
        contentValues.put( EXERCISE_FIFTEEN_ID, workoutToAdd.getExerciseFifteenId() );
        contentValues.put( EXERCISE_SIXTEEN_ID, workoutToAdd.getExerciseSixteenId() );
        contentValues.put( EXERCISE_SEVENTEEN_ID, workoutToAdd.getExerciseSeventeenId() );
        contentValues.put( EXERCISE_EIGHTEEN_ID, workoutToAdd.getExerciseEighteenId() );
        contentValues.put( EXERCISE_NINETEEN_ID, workoutToAdd.getExerciseNineteenId() );
        contentValues.put( EXERCISE_TWENTY_ID, workoutToAdd.getExerciseTwentyId() );
        return contentValues;
    }

}
