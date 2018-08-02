package com.example.android;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.android.Database.ExerciseContract;
import com.example.android.InternetBasedActivity.NewsData;

import java.util.ArrayList;

import static com.example.android.Database.ExerciseContract.ExerciseTable.CONTENT_URI;


public class FlexLoader extends android.support.v4.content.AsyncTaskLoader<ArrayList<Exercise>> {
    int currentId;
    Context mContext;
    ArrayList<Exercise> exercisesLoaded;
    
    //Constructor that takes in and stores mURL.
        public FlexLoader(Context context, int id){
            super(context);
            mContext = context;
            currentId = id;
            }

        public FlexLoader(Context context){
            super(context);
        }

    @Override
    public ArrayList<Exercise> loadInBackground() {
        switch (currentId) {
            case 10:
                Cursor cursor = mContext.getContentResolver().query( CONTENT_URI, null, null, null, null );
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
                        exerciseToAdd.mMediaType = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.MEDIA_TYPE ) );
                        exerciseToAdd.mWorkoutIds = workoutsExerciseFeaturesOn;
                        exerciseToAdd.mExerciseType = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.EXERCISE_TYPE ) );
                        exerciseToAdd.mDistance = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.DISTANCE ) );
                        exerciseToAdd.mTime = cursor.getInt( cursor.getColumnIndex( ExerciseContract.ExerciseTable.MINUTES ) );
                        exercisesList.add( exerciseToAdd );
                    }
                    cursor.close();
                    exercisesLoaded = exercisesList;
                    return exercisesLoaded;
                }
                break;
        }
        return exercisesLoaded;
    }

    //Forces the load.
        @Override
        protected void onStartLoading(){
            forceLoad();
        }

        //Loads the data for the grid in the background. If the url is null, terminates here. Initiates the Film Data fetchfilms methods (which then uses subsequent FilmData methods).

}
