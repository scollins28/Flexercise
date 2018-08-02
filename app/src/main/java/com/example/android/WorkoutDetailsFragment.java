package com.example.android;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import com.example.android.Widget.WidgetWorkoutDetails;
import com.example.android.free.R;
import java.util.ArrayList;
import github.nisrulz.recyclerviewhelper.RVHItemClickListener;
import github.nisrulz.recyclerviewhelper.RVHItemDividerDecoration;
import github.nisrulz.recyclerviewhelper.RVHItemTouchHelperCallback;

public class WorkoutDetailsFragment extends android.support.v4.app.Fragment implements DialogInterface {

    View rootView;
    ImageButton workoutDetailsBackButton;
    Button workoutEditButton;
    public Workout workout;
    Button startButton;
    Button restartButton;
    ArrayList <Exercise> exercises = new ArrayList<>(  );
    TextView subheading;
    private RecyclerView recyclerView;
    public WorkoutDetailsAdapter workoutDetailsAdapter;
    Context mContext;
    com.github.clans.fab.FloatingActionButton newExerciseFabButton;
    Toolbar mToolbar;

    public WorkoutDetailsFragment() {
    }


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (HomeScreen.widgetWorkoutId!=666666) {
            for (int i = 0; i < HomeScreen.workoutsFromLoader.size(); i++) {
                if (HomeScreen.widgetWorkoutId == HomeScreen.workoutsFromLoader.get( i ).getID()) {
                    workout = HomeScreen.workoutsFromLoader.get( i );
                }
            }
        }
        exercises = getExercises();
        mContext = getContext();
        rootView = inflater.inflate( R.layout.workout_details_fragment, container, false );
        mToolbar = rootView.findViewById( R.id.toolbar );
        workoutDetailsBackButton = rootView.findViewById( R.id.workout_details_back_button );
        workoutEditButton = rootView.findViewById( R.id.workout_edit_button );
        recyclerView = rootView.findViewById( R.id.workouts_details_view );
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

        startButton = rootView.findViewById( R.id.start );
        restartButton = rootView.findViewById( R.id.restart );

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = 0;
                if (v == workoutDetailsBackButton) {
                    position = 0;
                    HomeScreen.listStateTwo =0;
                    mCallback.onWorkoutDetailsButtonSelected( position );
                } else if (v == workoutEditButton) {
                    position = 1;
                    ScrollView scrollView = rootView.findViewById( R.id.workout_details_sv );
                    HomeScreen.listStateTwo = scrollView.getScrollY();
                    mCallback.onWorkoutDetailsButtonSelected( position );
                } else if (v == startButton) {
                    HomeScreen.startChronometer(HomeScreen.chronometer);
                }else if (v == restartButton) {
                    HomeScreen.restartChronometer( HomeScreen.chronometer );
                }else if (v == newExerciseFabButton){
                    HomeScreen.widgetWorkoutId = workout.mID;
                    ArrayList <WidgetWorkoutDetails> widgetExercises = new ArrayList<>(  );
                        for (int y = 0; y < HomeScreen.workoutsFromLoader.size(); y++) {
                            Workout currentWorkout = workout;
                            int [] workoutExerciseListIdsToCheck = workoutExerciseIds(currentWorkout);
                            int currentExerciseId = workoutExerciseListIdsToCheck[y];
                                if (HomeScreen.exercisesFromLoader != null) {
                                    for (int p = 0; p < HomeScreen.exercisesFromLoader.size(); p++) {
                                        Exercise currentExercise = HomeScreen.exercisesFromLoader.get( p );
                                        if (currentExercise.getID() == workoutExerciseListIdsToCheck[p]) {
                                            String exerciseName = currentExercise.getExerciseName();
                                            int numberOfSets = currentExercise.getNumberofSets();
                                            int maxWeight = currentExercise.getMaxWeight();
                                            int startingWeight = currentExercise.getStartingWeight();
                                            int distance = currentExercise.getDistance();
                                            int time = currentExercise.getTime();
                                            int exerciseType = currentExercise.getExerciseType();
                                            WidgetWorkoutDetails toAdd = new WidgetWorkoutDetails( currentExerciseId, exerciseName, numberOfSets, maxWeight, startingWeight,
                                                    distance, time, exerciseType );
                                            toAdd.mCurrentWorkoutId = workout.mID;
                                            toAdd.mWorkoutCategory = HomeScreen.workoutCategory;
                                            widgetExercises.add( toAdd );
                                        }
                                }
                        }
                    }
                    ScrollView scrollView = rootView.findViewById( R.id.workout_details_sv );
                    HomeScreen.listStateTwo =scrollView.getScrollY();
                    HomeScreen.widgetWorkoutDetails = widgetExercises;
                    position = 4;
                    mCallback.onWorkoutDetailsButtonSelected( position );
                }

            }
        };

        //Attaching the click listener to the buttons

        workoutDetailsBackButton.setOnClickListener( listener );
        workoutEditButton.setOnClickListener( listener );
        newExerciseFabButton.setOnClickListener( listener );
        startButton.setOnClickListener( listener );
        restartButton.setOnClickListener( listener );

        subheading = rootView.findViewById( R.id.workout_details_subheading );

        ScrollView scrollView = rootView.findViewById( R.id.workout_details_sv );
        scrollView.setVerticalScrollbarPosition( HomeScreen.listStateTwo );


        subheading.setText( workout.getWorkoutName() );

        HomeScreen.checkDisplayBanner(rootView, HomeScreen.removeAdvertsValue);

        HomeScreen.getChronometer(rootView, HomeScreen.timerSwitchValue, 1);

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
                for (int x = 0; x < HomeScreen.exercisesFromLoader.size(); x++) {
                    Exercise currentExercise = HomeScreen.exercisesFromLoader.get( x );
                    int currentId = currentExercise.getID();
                    if (currentId == exerciseIds.get( i )) {
                        exercisesToFeature.add( currentExercise );
                            }
                        }
                    }
                }
                HomeScreen.exercisesInSetWorkout = exercisesToFeature;
        return exercisesToFeature;
    }

    public static int[] workoutExerciseIds (Workout workout){
        int exerciseListIds [] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int g = 0;
        exerciseListIds[g] = workout.getExerciseOneId();
        g++;
        exerciseListIds[g] = workout.getExerciseTwoId();
        g++;
        exerciseListIds[g] = workout.getExerciseThreeId();
        g++;
        exerciseListIds[g] = workout.getExerciseFourId();
        g++;
        exerciseListIds[g] = workout.getExerciseFiveId();
        g++;
        exerciseListIds[g] = workout.getExerciseSixId();
        g++;
        exerciseListIds[g] = workout.getExerciseSevenId();
        g++;
        exerciseListIds[g] = workout.getExerciseEightId();
        g++;
        exerciseListIds[g] = workout.getExerciseNineId();
        g++;
        exerciseListIds[g] = workout.getExerciseTenId();
        g++;
        exerciseListIds[g] = workout.getExerciseElevenId();
        g++;
        exerciseListIds[g] = workout.getExerciseTwelveId();
        g++;
        exerciseListIds[g] = workout.getExerciseThirteenId();
        g++;
        exerciseListIds[g] = workout.getExerciseFourteenId();
        g++;
        exerciseListIds[g] = workout.getExerciseFifteenId();
        g++;
        exerciseListIds[g] = workout.getExerciseSixteenId();
        g++;
        exerciseListIds[g] = workout.getExerciseSeventeenId();
        g++;
        exerciseListIds[g] = workout.getExerciseEighteenId();
        g++;
        exerciseListIds[g] = workout.getExerciseNineteenId();
        g++;
        exerciseListIds[g] = workout.getExerciseTwentyId();
        return exerciseListIds;
    }
}
