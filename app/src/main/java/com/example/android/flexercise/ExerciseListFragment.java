package com.example.android.flexercise;

import android.content.Context;
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
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.android.flexercise.Database.ExerciseContract;

import java.util.ArrayList;

import github.nisrulz.recyclerviewhelper.RVHItemClickListener;
import github.nisrulz.recyclerviewhelper.RVHItemDividerDecoration;
import github.nisrulz.recyclerviewhelper.RVHItemTouchHelperCallback;

import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.CATEGORY_FIVE_STATE;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.CATEGORY_FOUR_STATE;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.CATEGORY_ONE_STATE;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.CATEGORY_SIX_STATE;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.CATEGORY_THREE_STATE;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.CATEGORY_TWO_STATE;
import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.CONTENT_URI;


public class ExerciseListFragment extends android.support.v4.app.Fragment{

    public ExerciseListFragment () {
    }

    View rootView;
    ImageButton exerciseBackButton;
    com.github.clans.fab.FloatingActionButton newExerciseFabButton;
    ArrayList<Exercise> allExercises;
    ArrayList<Exercise> exercises;
    Context mContext;
    private RecyclerView recyclerView;
    private ExerciseListAdapter exerciseListAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        allExercises = addExercises();
        exercises = applyCategoryFilter(allExercises);

        mContext = getContext();
        rootView = inflater.inflate( R.layout.exercise_list_fragment, container, false );
        recyclerView = (RecyclerView) rootView.findViewById( R.id.exercises_list_view );
        exerciseListAdapter = new ExerciseListAdapter( exercises, mContext );
        recyclerView.setAdapter( exerciseListAdapter );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( mContext, LinearLayoutManager.VERTICAL, false );

        recyclerView.setLayoutManager( linearLayoutManager );
        recyclerView.setHasFixedSize(false);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        ItemTouchHelper.Callback callback = new RVHItemTouchHelperCallback(exerciseListAdapter, true, true,
                true);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);

        // Set the divider in the recyclerview
        recyclerView.addItemDecoration(new RVHItemDividerDecoration(mContext, LinearLayoutManager.VERTICAL));

        // Set On Click Listener
        recyclerView.addOnItemTouchListener(new RVHItemClickListener(mContext, new RVHItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Exercise clickedExercise = exercises.get( position );
                HomeScreen.exercise = clickedExercise;
                mCallback.onExerciseButtonSelected(3);



            }
        }));

        exerciseListAdapter.notifyDataSetChanged();


        exerciseBackButton = rootView.findViewById( R.id.exercise_back_button );
        newExerciseFabButton = rootView.findViewById( R.id.exercise_fab_menu_item_one );

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = 0;
                if (v == exerciseBackButton ) {
                    position = 0;
                }
                if (v == newExerciseFabButton) {
                    position = 1;
                }
                mCallback.onExerciseButtonSelected( position );
            }
        };

        //Attaching the click listener to the buttons

        exerciseBackButton.setOnClickListener( listener );
        newExerciseFabButton.setOnClickListener( listener );

       return rootView;
    }

    ExerciseListFragment.OnExerciseButtonClickListener mCallback;

    //Interface for the click listener to send which button has been pushed to the home screen, and activate the fragment transaction
    public interface OnExerciseButtonClickListener {
        void onExerciseButtonSelected(int position);
    }

    //Attaching the interface to the MasterListFragment
    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        try {
            mCallback = (ExerciseListFragment.OnExerciseButtonClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException( context.toString() + "must implement click listener" );
        }
    }

    public ArrayList<Exercise> addExercises() {
            Cursor cursor = getActivity().getContentResolver().query( CONTENT_URI, null, null, null, null );
            ArrayList<Exercise> exercisesList = new ArrayList<>();
            if (cursor != null && cursor.moveToFirst()) {
                for (int i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToPosition( i );
                    String exerciseName = cursor.getString( cursor.getColumnIndex( ExerciseContract.ExerciseTable.EXERCISE_NAME ) );
                    int categoriesOneValue = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.CATEGORY_ONE_STATE ) );
                    int categoriesTwoValue = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.CATEGORY_TWO_STATE ) );
                    int categoriesThreeValue = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.CATEGORY_THREE_STATE ) );
                    int categoriesFourValue = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.CATEGORY_FOUR_STATE ) );
                    int categoriesFiveValue = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.CATEGORY_FIVE_STATE ) );
                    int categoriesSixValue = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.CATEGORY_SIX_STATE ) );
                    String mediaSource = cursor.getString( cursor.getColumnIndex( ExerciseContract.ExerciseTable.MEDIA_SOURCE ) );
                    int numberOfSets = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.NUMBER_OF_SETS ) );
                    int maxWeight = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.MAX_WEIGHT ) );
                    int startingWeight = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.STARTING_WEIGHT ) );
                    String addToWorkout = cursor.getString( cursor.getColumnIndex( ExerciseContract.ExerciseTable.ADD_TO_WORKOUT ) );
                    String notes = cursor.getString( cursor.getColumnIndex( ExerciseContract.ExerciseTable.NOTES ) );
                    ArrayList<Integer> workoutsExerciseFeaturesOn = new ArrayList<>();
                    workoutsExerciseFeaturesOn.add( cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.WORKOUT_ONE ) ) );
                    workoutsExerciseFeaturesOn.add( cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.WORKOUT_TWO ) ) );
                    workoutsExerciseFeaturesOn.add( cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.WORKOUT_THREE ) ) );
                    workoutsExerciseFeaturesOn.add( cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.WORKOUT_FOUR ) ) );
                    workoutsExerciseFeaturesOn.add( cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.WORKOUT_FIVE ) ) );
                    workoutsExerciseFeaturesOn.add( cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.WORKOUT_SIX ) ) );
                    workoutsExerciseFeaturesOn.add( cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.WORKOUT_SEVEN ) ) );
                    workoutsExerciseFeaturesOn.add( cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.WORKOUT_EIGHT ) ) );
                    workoutsExerciseFeaturesOn.add( cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.WORKOUT_NINE ) ) );
                    workoutsExerciseFeaturesOn.add( cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.WORKOUT_TEN ) ) );
                    workoutsExerciseFeaturesOn.add( cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.WORKOUT_ELEVEN ) ) );
                    workoutsExerciseFeaturesOn.add( cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.WORKOUT_TWELVE ) ) );
                    workoutsExerciseFeaturesOn.add( cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.WORKOUT_THIRTEEN ) ) );
                    workoutsExerciseFeaturesOn.add( cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.WORKOUT_FOURTEEN ) ) );
                    workoutsExerciseFeaturesOn.add( cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.WORKOUT_FIFTEEN ) ) );
                    workoutsExerciseFeaturesOn.add( cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.WORKOUT_SIXTEEN ) ) );
                    workoutsExerciseFeaturesOn.add( cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.WORKOUT_SEVENTEEN ) ) );
                    workoutsExerciseFeaturesOn.add( cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.WORKOUT_EIGHTEEN ) ) );
                    workoutsExerciseFeaturesOn.add( cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.WORKOUT_NINETEEN ) ) );
                    workoutsExerciseFeaturesOn.add( cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.WORKOUT_TWENTY ) ) );
                    Exercise exerciseToAdd = new Exercise( exerciseName, categoriesOneValue, categoriesTwoValue,
                            categoriesThreeValue, categoriesFourValue, categoriesFiveValue, categoriesSixValue,
                            mediaSource, numberOfSets, maxWeight, startingWeight, addToWorkout, notes );
                    exerciseToAdd.mID = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable._ID ) );
                    exerciseToAdd.mWorkoutIds = workoutsExerciseFeaturesOn;
                    exercisesList.add( exerciseToAdd );
                }
            }
        cursor.close();
        return exercisesList;
    }

    public ArrayList<Exercise> applyCategoryFilter (ArrayList<Exercise>allExercises){
        ArrayList <Exercise> exercisesInCategory = new ArrayList<>(  );
        int currentCategory = HomeScreen.exerciseCategory;
        String selection = "";
        for (int i=0; i<allExercises.size(); i++) {
            Exercise exerciseToCheck = allExercises.get( i );
            switch (currentCategory) {
                case 1:
                    if (exerciseToCheck.getCategoryOneValue()==1){
                        exercisesInCategory.add( exerciseToCheck );
                    };
                    break;
                case 2:
                    if (exerciseToCheck.getCategoryTwoValue()==1){
                        exercisesInCategory.add( exerciseToCheck );
                    };
                    break;
                case 3:
                    if (exerciseToCheck.getCategoryThreeValue()==1){
                        exercisesInCategory.add( exerciseToCheck );
                    };
                    break;
                case 4:
                    if (exerciseToCheck.getCategoryFourValue()==1){
                        exercisesInCategory.add( exerciseToCheck );
                    };
                    break;
                case 5:
                    if (exerciseToCheck.getCategoryFiveValue()==1){
                        exercisesInCategory.add( exerciseToCheck );
                    };
                    break;
                case 6:
                    if (exerciseToCheck.getCategorySixValue()==1){
                        exercisesInCategory.add( exerciseToCheck );
                    };
                    break;
            }
        }
        return exercisesInCategory;
    }
}
