package com.example.android;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
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

import com.example.android.Database.WorkoutsDatabase.WorkoutContract;
import com.example.android.Exercise;
import com.example.android.HomeScreen;
import com.example.android.Workout;
import com.example.android.free.R;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.CATEGORY_FIVE_STATE;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.CATEGORY_FOUR_STATE;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.CATEGORY_SIX_STATE;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.CATEGORY_THREE_STATE;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.CATEGORY_TWO_STATE;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.WORKOUT_CONTENT_URI;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.WORKOUT_NAME;
import static com.example.android.WorkoutDetailsFragment.getExerciseTableNames;

public class WorkoutExerciseDetailsFragment extends android.support.v4.app.Fragment{

    View rootView;
    ConstraintLayout navigationButtons;
    ImageButton exerciseDetailsBackButton;
    Button exerciseDoneButton;
    Button nextButton;
    Button previousButton;
    public Exercise exercise;
    TextView subheading;
    TextView exerciseName;
    TextView numberOfSets;
    TextView maxWeightTv;
    TextView maxWeight;
    TextView startingWeightTv;
    TextView startingWeight;
    TextView unitOne;
    TextView unitTwo;
    TextView notes;
    Button mediaButton;
    String media;
    Boolean allowed;
    int mediaType = 0;
    String youTubeString;

    public WorkoutExerciseDetailsFragment() {

    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        createWorkout();
        rootView = inflater.inflate( R.layout.workout_exercise_details_fragment, container, false );

        exerciseDetailsBackButton = rootView.findViewById( R.id.workout_exercise_details_back_button );
        exerciseDoneButton = rootView.findViewById( R.id.workout_exercise_done_button );
        nextButton = rootView.findViewById( R.id.exercise_next_button );
        previousButton = rootView.findViewById( R.id.exercise_previous_button );
        mediaButton = rootView.findViewById( R.id.media_button );
        unitOne = rootView.findViewById( R.id.kg_tv );
        unitTwo = rootView.findViewById( R.id.kg_tv2 );
        mediaType = exercise.getMediaType();

        if (HomeScreen.kgValue==1){
            unitOne.setText( R.string.KG );
            unitTwo.setText( R.string.KG );
        } else {
            unitOne.setText( R.string.LBS);
            unitTwo.setText( R.string.LBS );
        }
        if (exercise.getExerciseType()==1){
            if (HomeScreen.kmValue==1){
                unitOne.setText( R.string.KM );
                unitTwo.setText( R.string.minutes );
            }else {
                unitOne.setText( R.string.miles );
                unitTwo.setText( R.string.minutes );
            }
        }
        if (HomeScreen.calledPosition==0){
            previousButton.setVisibility( View.GONE );
        }
        if ((HomeScreen.calledPosition+1)== HomeScreen.exercisesInSetWorkout.size()){
            nextButton.setVisibility(View.GONE);
        }


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = 0;
                if (v == exerciseDetailsBackButton) {
                    position = 0;
                    mCallback.onWorkoutExerciseDetailsButtonSelected( position );
                } else if (v == exerciseDoneButton){
                    position = 1;
                    mCallback.onWorkoutExerciseDetailsButtonSelected( position );
                } else if (v == previousButton) {
                    HomeScreen.calledPosition -= 1;
                    position = 2;
                    mCallback.onWorkoutExerciseDetailsButtonSelected( position );
                } else if (v == nextButton){
                   HomeScreen.calledPosition +=1;
                   position = 2;
                    mCallback.onWorkoutExerciseDetailsButtonSelected( position );
                } else if (v == mediaButton) {
                    if (mediaType == 1) {
                        youTubeString = exercise.getMediaSource();
                        HomeScreen.currentMediaSource = youTubeString;
                        position = 3;
                        mCallback.onWorkoutExerciseDetailsButtonSelected( position );
                    } else {
                        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder( getContext() );
                        builder.setCancelable( true );
                        builder.setTitle( R.string.no_media );
                        builder.setMessage( R.string.no_media_message );
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
                    }
                }
                }
        };

        //Attaching the click listener to the buttons

        exerciseDetailsBackButton.setOnClickListener( listener );
        exerciseDoneButton.setOnClickListener( listener );
        nextButton.setOnClickListener( listener );
        previousButton.setOnClickListener( listener );
        mediaButton.setOnClickListener( listener );

        subheading = rootView.findViewById(R.id.exercise_details_subheading);
        exerciseName = rootView.findViewById( R.id.exercise_name_details_view_tv );
        numberOfSets = rootView.findViewById( R.id.exercise_number_of_sets_details_view_tv );
        maxWeightTv = rootView.findViewById( R.id.exercise_max_weight_tv );
        maxWeight = rootView.findViewById( R.id.exercise_max_weight_details_view_tv);
        startingWeightTv = rootView.findViewById( R.id.exercise_starting_weight_tv );
        startingWeight = rootView.findViewById( R.id.exercise_starting_weight_details_view_tv);
        notes = rootView.findViewById( R.id.notes_details_tv);

        subheading.setText( exercise.getExerciseName());
        exerciseName.setText( exercise.getExerciseName() );
        numberOfSets.setText(String.valueOf(exercise.getNumberofSets()) );
        if (exercise.getExerciseType()==0) {
            maxWeight.setText( String.valueOf( exercise.getMaxWeight() ) );
            startingWeight.setText( String.valueOf( exercise.getStartingWeight() ) );
        } else {
            maxWeightTv.setText( getString( R.string.distance ) );
            startingWeightTv.setText( getString( R.string.time ) );
            maxWeight.setText( String.valueOf( exercise.getDistance() ) );
            startingWeight.setText( String.valueOf( exercise.getTime()) );
        }
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

    public void createWorkout (){
        if (HomeScreen.widgetWorkoutId != 777777) {
            Cursor widgetCursor;
            widgetCursor = getActivity().getContentResolver().query( WORKOUT_CONTENT_URI, null, null, null, null );
            for (int i = 0; i < widgetCursor.getCount(); i++) {
                widgetCursor.moveToPosition( i );
                if (HomeScreen.widgetWorkoutId == widgetCursor.getInt( widgetCursor.getColumnIndex( _ID ) )) {
                    String workoutName = widgetCursor.getString( widgetCursor.getColumnIndex( WORKOUT_NAME ) );
                    int categoryOne = widgetCursor.getInt( widgetCursor.getColumnIndex( WorkoutContract.WorkoutsTable.CATEGORY_ONE_STATE ) );
                    int categoryTwo = widgetCursor.getInt( widgetCursor.getColumnIndex( CATEGORY_TWO_STATE ) );
                    int categoryThree = widgetCursor.getInt( widgetCursor.getColumnIndex( CATEGORY_THREE_STATE ) );
                    int categoryFour = widgetCursor.getInt( widgetCursor.getColumnIndex( CATEGORY_FOUR_STATE ) );
                    int categoryFive = widgetCursor.getInt( widgetCursor.getColumnIndex( CATEGORY_FIVE_STATE ) );
                    int categorySix = widgetCursor.getInt( widgetCursor.getColumnIndex( CATEGORY_SIX_STATE ) );

                    ArrayList<String> exerciseIdNames = getExerciseTableNames();
                    ArrayList<Integer> exerciseIds = new ArrayList<>(  );
                    for (int w = 0; w<exerciseIdNames.size(); w++){
                        exerciseIds.add(widgetCursor.getInt( widgetCursor.getColumnIndex( exerciseIdNames.get( w )) ));
                    }
                    int f=0;
                    Workout tempWorkout = new Workout(  );
                    tempWorkout.mWorkoutName=workoutName;
                    tempWorkout.mCategoryOneValue=categoryOne;
                    tempWorkout.mCategoryTwoValue=categoryTwo;
                    tempWorkout.mCategoryThreeValue=categoryThree;
                    tempWorkout.mCategoryFourValue = categoryFour;
                    tempWorkout.mCategoryFiveValue = categoryFive;
                    tempWorkout.mCategorySixValue =  categorySix;
                    tempWorkout.mExerciseOneId=exerciseIds.get( f );
                    f++;
                    tempWorkout.mExerciseOneId=exerciseIds.get( f );
                    f++;
                    tempWorkout.mExerciseTwoId=exerciseIds.get( f );
                    f++;
                    tempWorkout.mExerciseThreeId=exerciseIds.get( f );
                    f++;
                    tempWorkout.mExerciseFourId=exerciseIds.get( f );
                    f++;
                    tempWorkout.mExerciseFiveId=exerciseIds.get( f );
                    f++;
                    tempWorkout.mExerciseSixId=exerciseIds.get( f );
                    f++;
                    tempWorkout.mExerciseSevenId=exerciseIds.get( f );
                    f++;
                    tempWorkout.mExerciseEightId=exerciseIds.get( f );
                    f++;
                    tempWorkout.mExerciseNineId=exerciseIds.get( f );
                    f++;
                    tempWorkout.mExerciseTenId=exerciseIds.get( f );
                    f++;
                    tempWorkout.mExerciseElevenId=exerciseIds.get( f );
                    f++;
                    tempWorkout.mExerciseTwelveId=exerciseIds.get( f );
                    f++;
                    tempWorkout.mExerciseThirteenId=exerciseIds.get( f );
                    f++;
                    tempWorkout.mExerciseFourteenId=exerciseIds.get( f );
                    f++;
                    tempWorkout.mExerciseFifteenId=exerciseIds.get( f );
                    f++;
                    tempWorkout.mExerciseSixteenId=exerciseIds.get( f );
                    f++;
                    tempWorkout.mExerciseSeventeenId=exerciseIds.get( f );
                    f++;
                    tempWorkout.mExerciseEighteenId=exerciseIds.get( f );
                    f++;
                    tempWorkout.mExerciseNineteenId=exerciseIds.get( f );
                    f++;
                    HomeScreen.workout = tempWorkout;
                }
            }}
    }

}
