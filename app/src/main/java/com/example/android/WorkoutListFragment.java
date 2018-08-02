package com.example.android;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ScrollView;
import com.example.android.free.R;
import java.util.ArrayList;
import github.nisrulz.recyclerviewhelper.RVHItemClickListener;
import github.nisrulz.recyclerviewhelper.RVHItemDividerDecoration;
import github.nisrulz.recyclerviewhelper.RVHItemTouchHelperCallback;

public class WorkoutListFragment extends android.support.v4.app.Fragment{

    View rootView;
    ImageButton workoutListBackButton;
    com.github.clans.fab.FloatingActionButton newWorkoutFabButton;
    ArrayList<Workout> allWorkouts;
    ArrayList<Workout> workouts;
    public Context mContext;
    private RecyclerView recyclerView;
    private WorkoutListAdapter workoutListAdapter;
    Toolbar mToolbar;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mContext = getContext();

        allWorkouts = Workout.addWorkouts(mContext);
        workouts = applyCategoryFilter( allWorkouts );

        rootView = inflater.inflate( R.layout.workout_list_fragment, container, false );

        mToolbar = rootView.findViewById( R.id.toolbar );
        recyclerView = rootView.findViewById( R.id.workouts_list_view );
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

        ScrollView scrollView = rootView.findViewById( R.id.workout_list_sv );
        workoutListBackButton = rootView.findViewById( R.id.workout_list_back_button );
        newWorkoutFabButton = rootView.findViewById( R.id.workout_fab_menu_item_one);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = 0;
                if (v == workoutListBackButton ) {
                    position = 0;
                    HomeScreen.listStateOne =0;
                }
                else if (v == newWorkoutFabButton){
                    position = 1;
                    ScrollView scrollView = rootView.findViewById( R.id.workout_list_sv );
                    HomeScreen.listStateOne =scrollView.getScrollY();
                }
                mCallback.onWorkoutListButtonSelected( position );
            }
        };

        //Attaching the click listener to the buttons

        workoutListBackButton.setOnClickListener( listener );
        newWorkoutFabButton.setOnClickListener( listener );
        scrollView.setVerticalScrollbarPosition( HomeScreen.listStateOne );

        HomeScreen.checkDisplayBanner(rootView, HomeScreen.removeAdvertsValue);

        return rootView;
    }

    WorkoutListFragment.onWorkoutListButtonSelected mCallback;



    //Interface for the click listener to send which button has been pushed to the home screen, and activate the fragment transaction
    public interface onWorkoutListButtonSelected {
        void onWorkoutListButtonSelected(int position);
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
            for (int t = 0; t< workoutsInCategory.size(); t++){
                if (HomeScreen.widgetToInflate!=666666){
                    int toCompare = workoutsInCategory.get( t ).mID;
                    if (toCompare== HomeScreen.widgetToInflate){
                        HomeScreen.workout = workoutsInCategory.get( t );
                        Log.e( "homescreen workout", "updated" );
                    }
                }
            }
        }
        return workoutsInCategory;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

        super.onSaveInstanceState( outState );
    }
}
