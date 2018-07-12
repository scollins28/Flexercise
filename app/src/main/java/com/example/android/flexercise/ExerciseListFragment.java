package com.example.android.flexercise;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.android.flexercise.Database.ExerciseContentProvider;
import com.example.android.flexercise.Database.ExerciseContract;
import com.example.android.flexercise.Database.ExerciseDbHelper;

import java.util.ArrayList;

import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.CONTENT_URI;


public class ExerciseListFragment extends android.support.v4.app.Fragment{

    View rootView;
    ImageButton exerciseBackButton;
    com.github.clans.fab.FloatingActionButton newExerciseFabButton;
    private ArrayList<Exercise> exercises;
    Context mContext;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        exercises = addExercises();

        mContext = getContext();
        rootView = inflater.inflate( R.layout.exercise_list_fragment, container, false );
        ListView exerciseListView = rootView.findViewById( R.id.exercises_list_view );
        ExerciseListAdapter exerciseListAdapter = new ExerciseListAdapter( mContext, exercises );
        exerciseListView.setAdapter( exerciseListAdapter );



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
                String exerciseName = cursor.getString( cursor.getColumnIndex( ExerciseContract.ExerciseTable.EXERCISE_NAME) );
                int categoriesOneValue = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.CATEGORY_ONE_STATE) );
                int categoriesTwoValue = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.CATEGORY_THREE_STATE) );
                int categoriesThreeValue = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.CATEGORY_TWO_STATE) );
                int categoriesFourValue = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.CATEGORY_FOUR_STATE) );
                int categoriesFiveValue = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.CATEGORY_FIVE_STATE) );
                int categoriesSixValue = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.CATEGORY_SIX_STATE) );
                String mediaSource = cursor.getString( cursor.getColumnIndex( ExerciseContract.ExerciseTable.MEDIA_SOURCE) );
                int numberOfSets = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.NUMBER_OF_SETS) );
                int maxWeight = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.EXERCISE_NAME) );
                int startingWeight = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.EXERCISE_NAME) );
                String addToWorkout = cursor.getString( cursor.getColumnIndex( ExerciseContract.ExerciseTable.ADD_TO_WORKOUT) );
                String notes = cursor.getString( cursor.getColumnIndex( ExerciseContract.ExerciseTable.NOTES) );
                Exercise exerciseToAdd = new Exercise( exerciseName, categoriesOneValue, categoriesTwoValue,
                        categoriesThreeValue, categoriesFourValue, categoriesFiveValue, categoriesSixValue,
                        mediaSource,numberOfSets, maxWeight, startingWeight, addToWorkout, notes);
                exercisesList.add( exerciseToAdd );
            }
        }
        cursor.close();
        return exercisesList;
    }

}
