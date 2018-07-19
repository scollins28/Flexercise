package com.example.android.flexercise;

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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.android.flexercise.Database.ExerciseContract;

import java.util.ArrayList;

import github.nisrulz.recyclerviewhelper.RVHItemClickListener;
import github.nisrulz.recyclerviewhelper.RVHItemDividerDecoration;
import github.nisrulz.recyclerviewhelper.RVHItemTouchHelperCallback;

import static android.provider.BaseColumns._ID;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.CONTENT_URI;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.EXERCISE_NAME;



public class WorkoutDetailsFragment extends android.support.v4.app.Fragment implements DialogInterface {

    View rootView;
    ImageButton workoutDetailsBackButton;
    Button workoutEditButton;
    public Workout workout;
    ArrayList <Exercise> exercises = new ArrayList<>(  );
    TextView subheading;
    private RecyclerView recyclerView;
    private WorkoutDetailsAdapter workoutDetailsAdapter;
    Context mContext;

    public WorkoutDetailsFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        exercises = getExercises();
        mContext = getContext();
        rootView = inflater.inflate( R.layout.workout_details_fragment, container, false );

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
                } else if (v == workoutEditButton) {
                    position = 1;
                }
                mCallback.onWorkoutDetailsButtonSelected( position );
            }
        };

        //Attaching the click listener to the buttons

        workoutDetailsBackButton.setOnClickListener( listener );
        workoutEditButton.setOnClickListener( listener );

        subheading = rootView.findViewById( R.id.workout_details_subheading );



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

                        exercisesToFeature.add( exerciseToAdd );

                            }
                        }
                    }
                }
                HomeScreen.exercisesInSetWorkout = exercisesToFeature;
        return exercisesToFeature;
    }
}
