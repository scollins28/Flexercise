package com.example.android;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import com.example.android.Database.WorkoutsDatabase.WorkoutUpdateExerciseLists;
import com.example.android.Database.WorkoutsDatabase.WorkoutsTableCleaner;
import com.example.android.free.R;
import java.util.ArrayList;
import io.apptik.widget.multiselectspinner.MultiSelectSpinner;

public class EditWorkoutFragment extends android.support.v4.app.Fragment{

    View rootView;
    ImageButton editWorkoutBackButton;
    Button editWorkoutDoneButton;
    Button editWorkoutCategoryOneButton;
    Button editWorkoutCategoryTwoButton;
    Button editWorkoutCategoryThreeButton;
    Button editWorkoutCategoryFourButton;
    Button editWorkoutCategoryFiveButton;
    Button editWorkoutCategorySixButton;
    ConstraintLayout categoriesFab;
    int editWorkoutCategoryOneValue;
    int editWorkoutCategoryTwoValue;
    int editWorkoutCategoryThreeValue;
    int editWorkoutCategoryFourValue;
    int editWorkoutCategoryFiveValue;
    int editWorkoutCategorySixValue;
    int categoriesFabValue = 0;
    TextView subheading;
    ConstraintLayout categoriesHiddenLayout;
    Context mContext;
    static Context context;
    ArrayList <String> options;
    public Workout workoutToEdit;
    EditText workoutNameEditText;
    MultiSelectSpinner exerciseListMultiSpinner;
    MultiSelectSpinner addToWorkoutSpinner;
    Toolbar mToolbar;
    ScrollView scrollView;


    public EditWorkoutFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate( R.layout.new_workout_fragment, container, false );
        mToolbar = rootView.findViewById( R.id.toolbar );
        mContext = getActivity();
        context = mContext;
        addToWorkoutSpinner = rootView.findViewById( R.id.new_exercise_workout_list_spinner );
        editWorkoutBackButton = rootView.findViewById( R.id.new_workout_back_button );
        editWorkoutDoneButton = rootView.findViewById( R.id.new_workout_done_button);
        editWorkoutCategoryOneButton = rootView.findViewById( R.id.button_one );
        editWorkoutCategoryTwoButton = rootView.findViewById( R.id.button_two );
        editWorkoutCategoryThreeButton = rootView.findViewById( R.id.button_three );
        editWorkoutCategoryFourButton = rootView.findViewById( R.id.button_four );
        editWorkoutCategoryFiveButton = rootView.findViewById( R.id.button_five );
        editWorkoutCategorySixButton = rootView.findViewById( R.id.button_six );
        subheading = rootView.findViewById( R.id.subheading_new_workout );
        categoriesFab = rootView.findViewById( R.id.category_name_cl );
        categoriesHiddenLayout = rootView.findViewById( R.id.categories_buttons );
        scrollView = rootView.findViewById( R.id.scroll_view_new_work );

        workoutNameEditText = rootView.findViewById( R.id.workout_name_edit_text );
        workoutNameEditText.setText( workoutToEdit.getWorkoutName() );
        subheading.setText( R.string.edit_workout );


        editWorkoutCategoryOneValue = workoutToEdit.mCategoryOneValue;
        editWorkoutCategoryTwoValue = workoutToEdit.mCategoryTwoValue;
        editWorkoutCategoryThreeValue = workoutToEdit.mCategoryThreeValue;
        editWorkoutCategoryFourValue = workoutToEdit.mCategoryFourValue;
        editWorkoutCategoryFiveValue = workoutToEdit.mCategoryFiveValue;
        editWorkoutCategorySixValue = workoutToEdit.mCategorySixValue;

        if (editWorkoutCategoryOneValue==1){
            editWorkoutCategoryOneButton.setBackgroundColor( getResources().getColor(R.color.colorAccent ));
            editWorkoutCategoryOneButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
        }
        if (editWorkoutCategoryTwoValue==1){
            editWorkoutCategoryTwoButton.setBackgroundColor( getResources().getColor(R.color.colorAccent ));
            editWorkoutCategoryTwoButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
        }
        if (editWorkoutCategoryThreeValue==1){
            editWorkoutCategoryThreeButton.setBackgroundColor( getResources().getColor(R.color.colorAccent ));
            editWorkoutCategoryThreeButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
        }
        if (editWorkoutCategoryFourValue==1){
            editWorkoutCategoryFourButton.setBackgroundColor( getResources().getColor(R.color.colorAccent ));
            editWorkoutCategoryFourButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
        }
        if (editWorkoutCategoryFiveValue==1){
            editWorkoutCategoryFiveButton.setBackgroundColor( getResources().getColor(R.color.colorAccent ));
            editWorkoutCategoryFiveButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
        }
        if (editWorkoutCategorySixValue==1){
            editWorkoutCategorySixButton.setBackgroundColor( getResources().getColor(R.color.colorAccent ));
            editWorkoutCategorySixButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
        }


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = 0;
                if (v == editWorkoutBackButton) {
                    position = 0;
                }
                else if (v == editWorkoutDoneButton) {
                    if (editWorkoutCategoryOneValue == 0 && editWorkoutCategoryTwoValue == 0 &&
                            editWorkoutCategoryThreeValue == 0 && editWorkoutCategoryFourValue == 0
                            && editWorkoutCategoryFiveValue == 0 && editWorkoutCategorySixValue == 0) {
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
                        Workout workoutToUpdate = updateWorkout();
                        insertNewData( workoutToUpdate );
                        new WorkoutUpdateExerciseLists( workoutToUpdate, mContext );
                        position = 1;
                    }
                }
                mCallback.onEditWorkoutButtonSelected( position );
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
                else if (v == editWorkoutCategoryOneButton) {
                    if (editWorkoutCategoryOneValue==0){
                        editWorkoutCategoryOneValue = 1;
                        editWorkoutCategoryOneButton.setBackgroundColor( getResources().getColor(R.color.colorAccent ));
                        editWorkoutCategoryOneButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        }
                    else {
                        editWorkoutCategoryOneValue = 0;
                        editWorkoutCategoryOneButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        editWorkoutCategoryOneButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == editWorkoutCategoryTwoButton) {
                    if (editWorkoutCategoryTwoValue==0){
                        editWorkoutCategoryTwoValue = 1;
                        editWorkoutCategoryTwoButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        editWorkoutCategoryTwoButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        }
                    else {
                        editWorkoutCategoryTwoValue = 0;
                        editWorkoutCategoryTwoButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        editWorkoutCategoryTwoButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == editWorkoutCategoryThreeButton) {
                    if (editWorkoutCategoryThreeValue==0){
                        editWorkoutCategoryThreeValue = 1;
                        editWorkoutCategoryThreeButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        editWorkoutCategoryThreeButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        }
                    else {
                        editWorkoutCategoryThreeValue = 0;
                        editWorkoutCategoryThreeButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        editWorkoutCategoryThreeButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == editWorkoutCategoryFourButton) {
                    if (editWorkoutCategoryFourValue==0){
                        editWorkoutCategoryFourValue = 1;
                        editWorkoutCategoryFourButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        editWorkoutCategoryFourButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        }
                    else {
                        editWorkoutCategoryFourValue = 0;
                        editWorkoutCategoryFourButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        editWorkoutCategoryFourButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == editWorkoutCategoryFiveButton) {
                    if (editWorkoutCategoryFiveValue==0){
                        editWorkoutCategoryFiveValue = 1;
                        editWorkoutCategoryFiveButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        editWorkoutCategoryFiveButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        }
                    else {
                        editWorkoutCategoryFiveValue = 0;
                        editWorkoutCategoryFiveButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        editWorkoutCategoryFiveButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == editWorkoutCategorySixButton) {
                    if (editWorkoutCategorySixValue==0){
                        editWorkoutCategorySixValue = 1;
                        editWorkoutCategorySixButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        editWorkoutCategorySixButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                    }
                    else {
                        editWorkoutCategorySixValue = 0;
                        editWorkoutCategorySixButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        editWorkoutCategorySixButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }


            }
        };

        //Attaching the click listener to the buttons

        editWorkoutBackButton.setOnClickListener( listener );
        editWorkoutDoneButton.setOnClickListener( listener );
        editWorkoutCategoryOneButton.setOnClickListener( categoryButtonListener );
        editWorkoutCategoryTwoButton.setOnClickListener( categoryButtonListener );
        editWorkoutCategoryThreeButton.setOnClickListener( categoryButtonListener );
        editWorkoutCategoryFourButton.setOnClickListener( categoryButtonListener );
        editWorkoutCategoryFiveButton.setOnClickListener( categoryButtonListener );
        editWorkoutCategorySixButton.setOnClickListener( categoryButtonListener );
        categoriesFab.setOnClickListener( categoryButtonListener );


        options = new ArrayList<>();
        if (HomeScreen.exercisesFromLoader!=null) {
            for (int i = 0; i < HomeScreen.exercisesFromLoader.size(); i++) {
                String newExercise = HomeScreen.exercisesFromLoader.get( i ).getExerciseName();
                options.add( newExercise );
            }
        }
        final MultiSelectSpinner multiSelectSpinner = rootView.findViewById(R.id.new_exercise_workout_list_spinner);
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

        HomeScreen.checkDisplayBanner(rootView, HomeScreen.removeAdvertsValue);

        return rootView;
    }

    EditWorkoutFragment.onEditWorkoutButtonSelected mCallback;

    //Interface for the click listener to send which button has been pushed to the home screen, and activate the fragment transaction
    public interface onEditWorkoutButtonSelected {
        void onEditWorkoutButtonSelected(int position);
    }

    //Attaching the interface to the MasterListFragment
    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        try {
            mCallback = (EditWorkoutFragment.onEditWorkoutButtonSelected) context;
        } catch (ClassCastException e) {
            throw new ClassCastException( context.toString() + "must implement click listener" );
        }
    }

    public Workout updateWorkout (){

        EditText workoutNameEditText = rootView.findViewById( R.id.workout_name_edit_text);
        String workoutName = workoutNameEditText.getText().toString();

        int categoryOneValue = editWorkoutCategoryOneValue;
        int categoryTwoValue = editWorkoutCategoryTwoValue;
        int categoryThreeValue = editWorkoutCategoryThreeValue;
        int categoryFourValue = editWorkoutCategoryFourValue;
        int categoryFiveValue = editWorkoutCategoryFiveValue;
        int categorySixValue = editWorkoutCategorySixValue;

        boolean[] checked = addToWorkoutSpinner.getSelected();
        ArrayList<Integer>  exercisesToAdd =  new ArrayList<Integer>();
        for (int i = 0; i < (checked.length); i++) {
            if (checked[i] == true) {
                String toAdd = options.get( i );
                if (HomeScreen.exercisesFromLoader != null) {
                    for (int x = 0; x < HomeScreen.exercisesFromLoader.size(); x++) {
                        if (HomeScreen.exercisesFromLoader.get( x ).getExerciseName().equals( toAdd )) {
                            int toAddId = HomeScreen.exercisesFromLoader.get(x).getID();
                            exercisesToAdd.add( toAddId );
                            Log.e( "id", String.valueOf( toAddId ) );
                        }
                    }
                }
            }
        }
            while (exercisesToAdd.size()<20){
                exercisesToAdd.add( 0 );
        }


        Workout workoutToAdd = new Workout( workoutName, categoryOneValue, categoryTwoValue, categoryThreeValue,
                categoryFourValue, categoryFiveValue, categorySixValue, exercisesToAdd.get( 0 ), exercisesToAdd.get( 1 ),
                exercisesToAdd.get( 2 ), exercisesToAdd.get( 3 ), exercisesToAdd.get( 4 ), exercisesToAdd.get( 5 ),
                exercisesToAdd.get( 6 ), exercisesToAdd.get( 7 ), exercisesToAdd.get( 8 ), exercisesToAdd.get( 9 ),
                exercisesToAdd.get( 10 ), exercisesToAdd.get( 11 ), exercisesToAdd.get( 12 ), exercisesToAdd.get( 13 ),
                exercisesToAdd.get( 14 ), exercisesToAdd.get( 15 ), exercisesToAdd.get( 16 ), exercisesToAdd.get( 17 ),
                exercisesToAdd.get( 18 ), exercisesToAdd.get( 19 ));

        workoutToAdd.mID = workoutToEdit.getID();

        return workoutToAdd;
    }

    public void insertNewData (Workout workoutToAdd){
        HomeScreen.workout = WorkoutsTableCleaner.cleanSingleWorkout (context, workoutToAdd);
    }

    public boolean [] checkState(){
        boolean[] checkList = addToWorkoutSpinner.getSelected();
        for (int i = 0; i < (checkList.length); i++) {
                String checklistOption = options.get( i );
                int toCompareAgainstId = 0;
                if (HomeScreen.exercisesFromLoader!=null) {
                    for (int x = 0; x < HomeScreen.exercisesFromLoader.size(); x++) {
                        String toCompareAgainst = HomeScreen.exercisesFromLoader.get( x ).getExerciseName();
                        if (toCompareAgainst.equals( checklistOption )) {
                            toCompareAgainstId = HomeScreen.exercisesFromLoader.get( x ).getID();
                        }
                    }
                }
            if (toCompareAgainstId>0){
            if (toCompareAgainstId==workoutToEdit.getExerciseOneId() || toCompareAgainstId==workoutToEdit.getExerciseTwoId() ||
                    toCompareAgainstId==workoutToEdit.getExerciseThreeId() || toCompareAgainstId==workoutToEdit.getExerciseFourId() ||
                    toCompareAgainstId==workoutToEdit.getExerciseFiveId() || toCompareAgainstId==workoutToEdit.getExerciseSixId() ||
                    toCompareAgainstId==workoutToEdit.getExerciseSevenId() || toCompareAgainstId==workoutToEdit.getExerciseEightId() ||
                    toCompareAgainstId==workoutToEdit.getExerciseNineId() || toCompareAgainstId==workoutToEdit.getExerciseTenId() ||
                    toCompareAgainstId==workoutToEdit.getExerciseElevenId() || toCompareAgainstId==workoutToEdit.getExerciseTwelveId() ||
                    toCompareAgainstId==workoutToEdit.getExerciseThirteenId() || toCompareAgainstId==workoutToEdit.getExerciseFourteenId() ||
                    toCompareAgainstId==workoutToEdit.getExerciseFifteenId() || toCompareAgainstId==workoutToEdit.getExerciseSixteenId() ||
                    toCompareAgainstId==workoutToEdit.getExerciseSeventeenId() || toCompareAgainstId==workoutToEdit.getExerciseEighteenId() ||
                    toCompareAgainstId==workoutToEdit.getExerciseNineteenId() || toCompareAgainstId==workoutToEdit.getExerciseTwentyId()) {

                checkList[i] = true;
            }
            }
            Log.e (String.valueOf( i ), String.valueOf(checkList[i]));
        }
        return checkList;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored( savedInstanceState );
        if (savedInstanceState!=null) {
        if (savedInstanceState.containsKey( "scrollViewEditOrNewWorkout" )) {
            scrollView.setScrollY( savedInstanceState.getInt( "scrollViewEditOrNewWorkout" ) );
        }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState( outState );
        outState.putInt( "scrollViewEditOrNewWorkout", scrollView.getScrollY() );
    }

}
