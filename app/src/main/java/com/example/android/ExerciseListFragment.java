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


public class ExerciseListFragment extends android.support.v4.app.Fragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<ArrayList<Exercise>> {

    public ExerciseListFragment () {
    }

    View rootView;
    ImageButton exerciseBackButton;
    com.github.clans.fab.FloatingActionButton newExerciseFabButton;
    ArrayList<Exercise> allExercises;
    ArrayList<Exercise> exercises;
    Context mContext;
    Toolbar mToolbar;
    RecyclerView recyclerView;
    ExerciseListAdapter exerciseListAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getLoaderManager().initLoader(10, null, this);

        exercises = applyCategoryFilter( allExercises );
        mContext = getContext();
        rootView = inflater.inflate( R.layout.exercise_list_fragment, container, false );
        mToolbar = rootView.findViewById( R.id.toolbar );
        recyclerView = rootView.findViewById( R.id.exercises_list_view );
        exerciseListAdapter = new ExerciseListAdapter( exercises, mContext );
        recyclerView.setAdapter( exerciseListAdapter );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( mContext, LinearLayoutManager.VERTICAL, false );

        recyclerView.setLayoutManager( linearLayoutManager );
        recyclerView.setHasFixedSize(false);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        ItemTouchHelper.Callback callback = new RVHItemTouchHelperCallback( exerciseListAdapter, true, true,
                true);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView( recyclerView );

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


        ScrollView scrollView = rootView.findViewById( R.id.exercise_list_sv );
        scrollView.setScrollY( HomeScreen.listStateThree );

        exerciseBackButton = rootView.findViewById( R.id.exercise_back_button );
        newExerciseFabButton = rootView.findViewById( R.id.exercise_fab_menu_item_one );

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = 0;
                if (v == exerciseBackButton ) {
                    position = 0;
                    HomeScreen.listStateTwo = 0;
                }
                if (v == newExerciseFabButton) {
                    position = 1;
                    ScrollView scrollView = rootView.findViewById( R.id.exercise_list_sv );
                    HomeScreen.listStateThree =scrollView.getVerticalScrollbarPosition();
                }
                mCallback.onExerciseButtonSelected( position );
            }
        };

        //Attaching the click listener to the buttons

        exerciseBackButton.setOnClickListener( listener );
        newExerciseFabButton.setOnClickListener( listener );

        HomeScreen.checkDisplayBanner(rootView, HomeScreen.removeAdvertsValue);

       return rootView;
    }

    ExerciseListFragment.OnExerciseButtonClickListener mCallback;

    @NonNull
    @Override
    public android.support.v4.content.Loader<ArrayList<Exercise>> onCreateLoader(int id, @Nullable Bundle args) {
        return new FlexLoader( getContext(), 10 );
    }

    @Override
    public void onLoadFinished(@NonNull android.support.v4.content.Loader<ArrayList<Exercise>> loader, ArrayList<Exercise> data) {
        allExercises = data;
        exercises = applyCategoryFilter( allExercises );
        recyclerView.setAdapter( new ExerciseListAdapter( exercises, mContext ) );
        HomeScreen.exercisesFromLoader = allExercises;
    }

    @Override
    public void onLoaderReset(@NonNull android.support.v4.content.Loader<ArrayList<Exercise>> loader) {

    }

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

    public ArrayList<Exercise> applyCategoryFilter (ArrayList<Exercise>allExercises){
        ArrayList <Exercise> exercisesInCategory = new ArrayList<>(  );
        int currentCategory = HomeScreen.exerciseCategory;
        String selection = "";
        if (allExercises!=null) {
            for (int i = 0; i < allExercises.size(); i++) {
                Exercise exerciseToCheck = allExercises.get( i );
                switch (currentCategory) {
                    case 1:
                        if (exerciseToCheck.getCategoryOneValue() == 1) {
                            exercisesInCategory.add( exerciseToCheck );
                        }
                        ;
                        break;
                    case 2:
                        if (exerciseToCheck.getCategoryTwoValue() == 1) {
                            exercisesInCategory.add( exerciseToCheck );
                        }
                        ;
                        break;
                    case 3:
                        if (exerciseToCheck.getCategoryThreeValue() == 1) {
                            exercisesInCategory.add( exerciseToCheck );
                        }
                        ;
                        break;
                    case 4:
                        if (exerciseToCheck.getCategoryFourValue() == 1) {
                            exercisesInCategory.add( exerciseToCheck );
                        }
                        ;
                        break;
                    case 5:
                        if (exerciseToCheck.getCategoryFiveValue() == 1) {
                            exercisesInCategory.add( exerciseToCheck );
                        }
                        ;
                        break;
                    case 6:
                        if (exerciseToCheck.getCategorySixValue() == 1) {
                            exercisesInCategory.add( exerciseToCheck );
                        }
                        ;
                        break;
                }
            }
        }
        return exercisesInCategory;
    }
}
