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

import com.example.android.flexercise.Database.ExerciseContract;
import com.example.android.flexercise.Database.WorkoutsDatabase.WorkoutContract;

import java.util.ArrayList;

import github.nisrulz.recyclerviewhelper.RVHItemClickListener;
import github.nisrulz.recyclerviewhelper.RVHItemDividerDecoration;
import github.nisrulz.recyclerviewhelper.RVHItemTouchHelperCallback;

import static android.provider.BaseColumns._ID;
import static com.example.android.flexercise.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.WORKOUT_CONTENT_URI;

public class WorkoutListFragment extends android.support.v4.app.Fragment{

    View rootView;
    ImageButton workoutListBackButton;
    com.github.clans.fab.FloatingActionButton newWorkoutFabButton;
    ArrayList<Workout> allWorkouts;
    ArrayList<Workout> workouts;
    public Context mContext;
    private RecyclerView recyclerView;
    private WorkoutListAdapter workoutListAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        allWorkouts = addWorkouts();
        workouts = applyCategoryFilter( allWorkouts );

        mContext = getContext();
        rootView = inflater.inflate( R.layout.workout_list_fragment, container, false );
        recyclerView = (RecyclerView) rootView.findViewById( R.id.workouts_list_view );
        workoutListAdapter = new WorkoutListAdapter( workouts, mContext );
        recyclerView.setAdapter( workoutListAdapter );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( mContext, LinearLayoutManager.VERTICAL, false );

        recyclerView.setLayoutManager( linearLayoutManager );
        recyclerView.setHasFixedSize(false);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        ItemTouchHelper.Callback callback = new RVHItemTouchHelperCallback(workoutListAdapter, true, true,
                true);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);

        // Set the divider in the recyclerview
        recyclerView.addItemDecoration(new RVHItemDividerDecoration(mContext, LinearLayoutManager.VERTICAL));

        // Set On Click Listener
        recyclerView.addOnItemTouchListener(new RVHItemClickListener(mContext, new RVHItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                HomeScreen.workout = workouts.get( position );
                mCallback.onWorkoutListButtonSelected(3);



            }
        }));

        workoutListAdapter.notifyDataSetChanged();


        workoutListBackButton = rootView.findViewById( R.id.workout_list_back_button );
        newWorkoutFabButton = rootView.findViewById( R.id.workout_fab_menu_item_one);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = 0;
                if (v == workoutListBackButton ) {
                    position = 0;
                }
                else if (v == newWorkoutFabButton){
                    position = 1;
                }
                mCallback.onWorkoutListButtonSelected( position );
            }
        };

        //Attaching the click listener to the buttons

        workoutListBackButton.setOnClickListener( listener );
        newWorkoutFabButton.setOnClickListener( listener );

        return rootView;
    }

    WorkoutListFragment.onWorkoutListButtonSelected mCallback;



    //Interface for the click listener to send which button has been pushed to the home screen, and activate the fragment transaction
    public interface onWorkoutListButtonSelected {
        void onWorkoutListButtonSelected (int position);
    }

    //Attaching the interface to the MasterListFragment
    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        try {
            mCallback = (WorkoutListFragment.onWorkoutListButtonSelected) context;
        } catch (ClassCastException e) {
            throw new ClassCastException( context.toString() + "must implement click listener" );
        }
    }

    public ArrayList<Workout> addWorkouts() {
        Cursor cursor = getActivity().getContentResolver().query( WORKOUT_CONTENT_URI, null, null, null, null );
        ArrayList<Workout> workoutsList = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition( i );
                String workoutName = cursor.getString( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.WORKOUT_NAME) );
                int categoriesOneValue = cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.CATEGORY_ONE_STATE) );
                int categoriesTwoValue = cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.CATEGORY_TWO_STATE) );
                int categoriesThreeValue = cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.CATEGORY_THREE_STATE) );
                int categoriesFourValue = cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.CATEGORY_FOUR_STATE) );
                int categoriesFiveValue = cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.CATEGORY_FIVE_STATE) );
                int categoriesSixValue = cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.CATEGORY_SIX_STATE) );
                ArrayList <Integer> idOfExercises = new ArrayList<>(  );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex(WorkoutContract.WorkoutsTable.EXERCISE_ONE_ID )) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex(WorkoutContract.WorkoutsTable.EXERCISE_TWO_ID )) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex(WorkoutContract.WorkoutsTable.EXERCISE_THREE_ID )) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex(WorkoutContract.WorkoutsTable.EXERCISE_FOUR_ID )) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex(WorkoutContract.WorkoutsTable.EXERCISE_FIVE_ID )) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex(WorkoutContract.WorkoutsTable.EXERCISE_SIX_ID )) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex(WorkoutContract.WorkoutsTable.EXERCISE_SEVEN_ID )) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex(WorkoutContract.WorkoutsTable.EXERCISE_EIGHT_ID )) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex(WorkoutContract.WorkoutsTable.EXERCISE_NINE_ID )) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex(WorkoutContract.WorkoutsTable.EXERCISE_TEN_ID )) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex(WorkoutContract.WorkoutsTable.EXERCISE_ELEVEN_ID )) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex(WorkoutContract.WorkoutsTable.EXERCISE_TWELVE_ID )) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex(WorkoutContract.WorkoutsTable.EXERCISE_THIRTEEN_ID )) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex(WorkoutContract.WorkoutsTable.EXERCISE_FOURTEEN_ID )) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex(WorkoutContract.WorkoutsTable.EXERCISE_FIFTEEN_ID )) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex(WorkoutContract.WorkoutsTable.EXERCISE_SIXTEEN_ID )) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex(WorkoutContract.WorkoutsTable.EXERCISE_SEVENTEEN_ID )) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex(WorkoutContract.WorkoutsTable.EXERCISE_EIGHTEEN_ID )) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex(WorkoutContract.WorkoutsTable.EXERCISE_NINETEEN_ID )) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex(WorkoutContract.WorkoutsTable.EXERCISE_TWENTY_ID )) );

                Workout workoutToAdd = new Workout( workoutName, categoriesOneValue, categoriesTwoValue,
                        categoriesThreeValue, categoriesFourValue, categoriesFiveValue, categoriesSixValue, idOfExercises.get( 0 ),
                        idOfExercises.get( 1 ), idOfExercises.get( 2 ), idOfExercises.get( 3 ), idOfExercises.get( 4 ),
                        idOfExercises.get( 5 ), idOfExercises.get( 6 ), idOfExercises.get( 7 ), idOfExercises.get( 8 ),
                        idOfExercises.get( 9 ), idOfExercises.get( 10 ), idOfExercises.get( 11 ), idOfExercises.get( 12 ),
                        idOfExercises.get( 13 ), idOfExercises.get( 14 ), idOfExercises.get( 15 ), idOfExercises.get( 16 ),
                        idOfExercises.get( 17 ), idOfExercises.get( 18 ), idOfExercises.get( 19 )
                        );
                workoutToAdd.mID = cursor.getInt (cursor.getColumnIndex(_ID));
                workoutsList.add( workoutToAdd);
            }
        }
        cursor.close();
        return workoutsList;
    }

    public ArrayList<Workout> applyCategoryFilter (ArrayList<Workout>allWorkouts){
        ArrayList <Workout> workoutsInCategory = new ArrayList<>(  );
        int currentCategory = HomeScreen.workoutCategory;
        for (int i=0; i<allWorkouts.size(); i++) {
            Workout workoutToCheck = allWorkouts.get( i );

            switch (currentCategory) {
                case 1:
                    if (workoutToCheck.getCategoryOneValue()==1){
                        workoutsInCategory.add( workoutToCheck);
                    };
                    break;
                case 2:
                    if (workoutToCheck.getCategoryTwoValue()==1){
                        workoutsInCategory.add( workoutToCheck);
                    };
                    break;
                case 3:
                    if (workoutToCheck.getCategoryThreeValue()==1){
                        workoutsInCategory.add( workoutToCheck);
                    };
                    break;
                case 4:
                    if (workoutToCheck.getCategoryFourValue()==1){
                        workoutsInCategory.add( workoutToCheck);
                    };
                    break;
                case 5:
                    if (workoutToCheck.getCategoryFiveValue()==1){
                        workoutsInCategory.add( workoutToCheck);
                    };
                    break;
                case 6:
                    if (workoutToCheck.getCategorySixValue()==1){
                        workoutsInCategory.add( workoutToCheck);
                    };
                    break;
            }
        }
        return workoutsInCategory;
    }

}
