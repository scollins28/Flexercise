package com.example.android;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.android.Database.ExerciseContract;
import com.example.android.Widget.WidgetWorkoutDetails;
import com.example.android.Exercise;
import com.example.android.HomeScreen;
import com.example.android.Workout;
import com.example.android.WorkoutDetailsAdapter;
import com.example.android.free.R;

import java.util.ArrayList;

import github.nisrulz.recyclerviewhelper.RVHItemClickListener;
import github.nisrulz.recyclerviewhelper.RVHItemDividerDecoration;
import github.nisrulz.recyclerviewhelper.RVHItemTouchHelperCallback;

import static android.provider.BaseColumns._ID;
import static com.example.android.Database.ExerciseContract.ExerciseTable.CONTENT_URI;
import static com.example.android.Database.ExerciseContract.ExerciseTable.DISTANCE;
import static com.example.android.Database.ExerciseContract.ExerciseTable.EXERCISE_NAME;
import static com.example.android.Database.ExerciseContract.ExerciseTable.EXERCISE_TYPE;
import static com.example.android.Database.ExerciseContract.ExerciseTable.MAX_WEIGHT;
import static com.example.android.Database.ExerciseContract.ExerciseTable.MINUTES;
import static com.example.android.Database.ExerciseContract.ExerciseTable.NUMBER_OF_SETS;
import static com.example.android.Database.ExerciseContract.ExerciseTable.STARTING_WEIGHT;
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


public class WorkoutDetailsFragment extends android.support.v4.app.Fragment implements DialogInterface {

    View rootView;
    ImageButton workoutDetailsBackButton;
    Button workoutEditButton;
    public Workout workout;
    ArrayList <Exercise> exercises = new ArrayList<>(  );
    TextView subheading;
    private RecyclerView recyclerView;
    public WorkoutDetailsAdapter workoutDetailsAdapter;
    Context mContext;
    com.github.clans.fab.FloatingActionButton newExerciseFabButton;
    Cursor widgetCursor;
    Cursor widgetExerciseCursor;

    public WorkoutDetailsFragment() {

    }




    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        exercises = getExercises();
        mContext = getContext();
        rootView = inflater.inflate( R.layout.workout_details_fragment, container, false );
        widgetCursor = getActivity().getContentResolver().query( WORKOUT_CONTENT_URI, null, null, null, null );
        widgetExerciseCursor = getActivity().getContentResolver().query( CONTENT_URI, null, null, null, null );

        workoutDetailsBackButton = rootView.findViewById( R.id.workout_details_back_button );
        workoutEditButton = rootView.findViewById( R.id.workout_edit_button );
        recyclerView = (RecyclerView) rootView.findViewById( R.id.workouts_details_view );
        workoutDetailsAdapter = new WorkoutDetailsAdapter( exercises, mContext );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( mContext, LinearLayoutManager.VERTICAL, false );

        recyclerView.setLayoutManager( linearLayoutManager );
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter( workoutDetailsAdapter );

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        ItemTouchHelper.Callback callback = new RVHItemTouchHelperCallback(workoutDetailsAdapter, true, true,
                true);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);
        newExerciseFabButton = rootView.findViewById( R.id.add_to_widget_FAB );

        // Set the divider in the recyclerview
        recyclerView.addItemDecoration(new RVHItemDividerDecoration(mContext, LinearLayoutManager.VERTICAL));

        // Set On Click Listener
        recyclerView.addOnItemTouchListener(new RVHItemClickListener(mContext, new RVHItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                HomeScreen.exercise = exercises.get( position );
                HomeScreen.calledPosition = position;
                mCallback.onWorkoutDetailsButtonSelected(3);
            }
        }));


                View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = 0;
                if (v == workoutDetailsBackButton) {
                    position = 0;
                    HomeScreen.listStateTwo =0;
                } else if (v == workoutEditButton) {
                    position = 1;
                    ScrollView scrollView = rootView.findViewById( R.id.workout_details_sv );
                    HomeScreen.listStateTwo = scrollView.getScrollY();
                } else if (v == newExerciseFabButton){
                    HomeScreen.widgetWorkoutId = workout.mID;
                    ArrayList <WidgetWorkoutDetails> widgetExercises = new ArrayList<>(  );
                    ArrayList<String> exerciseIds = getExerciseTableNames();
                    for (int i = 0; i<exerciseIds.size(); i++){
                        for (int y=0; y<widgetCursor.getCount(); y++){
                            widgetCursor.moveToPosition( y );
                            int currentExerciseId = widgetCursor.getInt(widgetCursor.getColumnIndex(getExerciseTableNames().get( i )));
                            if (currentExerciseId !=0) {
                                for (int p = 0; p<widgetExerciseCursor.getCount(); p++) {
                                    widgetExerciseCursor.moveToPosition( p );
                                    if (widgetExerciseCursor.getInt( widgetExerciseCursor.getColumnIndex( _ID ) ) == currentExerciseId) {
                                        String exerciseName = widgetExerciseCursor.getString( widgetExerciseCursor.getColumnIndex( EXERCISE_NAME ) );
                                        int numberOfSets = widgetExerciseCursor.getInt( widgetExerciseCursor.getColumnIndex( NUMBER_OF_SETS ) );
                                        int maxWeight = widgetExerciseCursor.getInt( widgetExerciseCursor.getColumnIndex( MAX_WEIGHT ) );
                                        int startingWeight = widgetExerciseCursor.getInt( widgetExerciseCursor.getColumnIndex( STARTING_WEIGHT ) );
                                        int distance = widgetExerciseCursor.getInt( widgetExerciseCursor.getColumnIndex( DISTANCE) );
                                        int time = widgetExerciseCursor.getInt( widgetExerciseCursor.getColumnIndex( MINUTES) );
                                        int exerciseType = widgetExerciseCursor.getInt( widgetExerciseCursor.getColumnIndex( EXERCISE_TYPE ));
                                        WidgetWorkoutDetails toAdd = new WidgetWorkoutDetails( currentExerciseId, exerciseName, numberOfSets, maxWeight, startingWeight,
                                                                                               distance, time, exerciseType );
                                        toAdd.mCurrentWorkoutId = workout.mID;
                                        toAdd.mWorkoutCategory = HomeScreen.workoutCategory;
                                        widgetExercises.add( toAdd);

                                    }
                                }
                            }
                        }
                        ScrollView scrollView = rootView.findViewById( R.id.workout_details_sv );
                        HomeScreen.listStateTwo =scrollView.getScrollY();
                    }
                    HomeScreen.widgetWorkoutDetails = widgetExercises;
                    position = 4;
                }
                mCallback.onWorkoutDetailsButtonSelected( position );
            }
        };

        //Attaching the click listener to the buttons

        workoutDetailsBackButton.setOnClickListener( listener );
        workoutEditButton.setOnClickListener( listener );
        newExerciseFabButton.setOnClickListener( listener );

        subheading = rootView.findViewById( R.id.workout_details_subheading );

        ScrollView scrollView = rootView.findViewById( R.id.workout_details_sv );
        scrollView.setVerticalScrollbarPosition( HomeScreen.listStateTwo );


        subheading.setText( workout.getWorkoutName() );


        return rootView;

    }

    WorkoutDetailsFragment.onWorkoutDetailsButtonSelected mCallback;

    @Override
    public void cancel() {

    }

    @Override
    public void dismiss() {

    }


    //Interface for the click listener to send which button has been pushed to the home screen, and activate the fragment transaction
    public interface onWorkoutDetailsButtonSelected {
        void onWorkoutDetailsButtonSelected(int position);
    }

    //Attaching the interface to the MasterListFragment
    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        try {
            mCallback = (WorkoutDetailsFragment.onWorkoutDetailsButtonSelected) context;
        } catch (ClassCastException e) {
            throw new ClassCastException( context.toString() + "must implement click listener" );
        }
    }

    public ArrayList<Exercise> getExercises() {

        ArrayList<Integer> exerciseIds = new ArrayList<>();
        exerciseIds.add( workout.getExerciseOneId() );
        exerciseIds.add( workout.getExerciseTwoId() );
        exerciseIds.add( workout.getExerciseThreeId() );
        exerciseIds.add( workout.getExerciseFourId() );
        exerciseIds.add( workout.getExerciseFiveId() );
        exerciseIds.add( workout.getExerciseSixId() );
        exerciseIds.add( workout.getExerciseSevenId() );
        exerciseIds.add( workout.getExerciseEightId() );
        exerciseIds.add( workout.getExerciseNineId() );
        exerciseIds.add( workout.getExerciseTenId() );
        exerciseIds.add( workout.getExerciseElevenId() );
        exerciseIds.add( workout.getExerciseTwelveId() );
        exerciseIds.add( workout.getExerciseThirteenId() );
        exerciseIds.add( workout.getExerciseFourteenId() );
        exerciseIds.add( workout.getExerciseFifteenId() );
        exerciseIds.add( workout.getExerciseSixteenId() );
        exerciseIds.add( workout.getExerciseSeventeenId() );
        exerciseIds.add( workout.getExerciseEighteenId() );
        exerciseIds.add( workout.getExerciseNineteenId() );
        exerciseIds.add( workout.getExerciseTwentyId() );

        ArrayList<Exercise> exercisesToFeature = new ArrayList<>();
        for (int i = 0; i < exerciseIds.size(); i++) {
            if (exerciseIds.get( i ) != 0) {
                Cursor cursor = getActivity().getContentResolver().query( CONTENT_URI, null, null, null, null );
                cursor.moveToFirst();
                for (int x = 0; x < cursor.getCount(); x++) {
                    cursor.moveToPosition( x );
                    int currentId = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable._ID ) );
                    if (currentId == exerciseIds.get( i )) {
                        String exerciseName = cursor.getString( cursor.getColumnIndex( ExerciseContract.ExerciseTable.EXERCISE_NAME) );
                        int categoriesOneValue = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.CATEGORY_ONE_STATE) );
                        int categoriesTwoValue = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.CATEGORY_TWO_STATE) );
                        int categoriesThreeValue = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.CATEGORY_THREE_STATE) );
                        int categoriesFourValue = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.CATEGORY_FOUR_STATE) );
                        int categoriesFiveValue = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.CATEGORY_FIVE_STATE) );
                        int categoriesSixValue = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.CATEGORY_SIX_STATE) );
                        String mediaSource = cursor.getString( cursor.getColumnIndex( ExerciseContract.ExerciseTable.MEDIA_SOURCE) );
                        int numberOfSets = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.NUMBER_OF_SETS) );
                        int maxWeight = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.MAX_WEIGHT) );
                        int startingWeight = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.STARTING_WEIGHT) );
                        String addToWorkout = cursor.getString( cursor.getColumnIndex( ExerciseContract.ExerciseTable.ADD_TO_WORKOUT) );
                        String notes = cursor.getString( cursor.getColumnIndex( ExerciseContract.ExerciseTable.NOTES) );
                        Exercise exerciseToAdd = new Exercise( exerciseName, categoriesOneValue, categoriesTwoValue,
                                categoriesThreeValue, categoriesFourValue, categoriesFiveValue, categoriesSixValue,
                                mediaSource, numberOfSets, maxWeight, startingWeight, addToWorkout, notes);
                        exerciseToAdd.mID = cursor.getInt (cursor.getColumnIndex( ExerciseContract.ExerciseTable._ID ));
                        exerciseToAdd.mMediaType = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.MEDIA_TYPE ) );
                        exerciseToAdd.mExerciseType = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.EXERCISE_TYPE ) );
                        if (cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.EXERCISE_TYPE ) )==1) {
                            exerciseToAdd.mDistance = cursor.getColumnIndex( ExerciseContract.ExerciseTable.DISTANCE );
                            exerciseToAdd.mTime = cursor.getColumnIndex( ExerciseContract.ExerciseTable.MINUTES);
                        }


                        exercisesToFeature.add( exerciseToAdd );

                            }
                        }
                    }
                }
                HomeScreen.exercisesInSetWorkout = exercisesToFeature;
        return exercisesToFeature;
    }

    public static ArrayList<String> getExerciseTableNames() {
        ArrayList <String> exerciseTableNames = new ArrayList<>(  );
        exerciseTableNames.add( EXERCISE_ONE_ID);
        exerciseTableNames.add( EXERCISE_TWO_ID);
        exerciseTableNames.add( EXERCISE_THREE_ID);
        exerciseTableNames.add( EXERCISE_FOUR_ID);
        exerciseTableNames.add( EXERCISE_FIVE_ID);
        exerciseTableNames.add( EXERCISE_SIX_ID);
        exerciseTableNames.add( EXERCISE_SEVEN_ID);
        exerciseTableNames.add( EXERCISE_EIGHT_ID);
        exerciseTableNames.add( EXERCISE_NINE_ID);
        exerciseTableNames.add( EXERCISE_TEN_ID);
        exerciseTableNames.add( EXERCISE_ELEVEN_ID);
        exerciseTableNames.add( EXERCISE_TWELVE_ID);
        exerciseTableNames.add( EXERCISE_THIRTEEN_ID);
        exerciseTableNames.add( EXERCISE_FOURTEEN_ID);
        exerciseTableNames.add( EXERCISE_FIFTEEN_ID);
        exerciseTableNames.add( EXERCISE_SIXTEEN_ID);
        exerciseTableNames.add( EXERCISE_SEVENTEEN_ID);
        exerciseTableNames.add( EXERCISE_EIGHTEEN_ID);
        exerciseTableNames.add( EXERCISE_NINETEEN_ID);
        exerciseTableNames.add( EXERCISE_TWENTY_ID);
        return exerciseTableNames;
    }
}
