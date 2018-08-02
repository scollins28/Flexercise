package com.example.android;

import android.content.Context;
import android.database.Cursor;

import com.example.android.Database.ExerciseContract;
import com.example.android.Database.WorkoutsDatabase.WorkoutContract;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.android.Database.ExerciseContract.ExerciseTable.CONTENT_URI;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.WORKOUT_CONTENT_URI;


public class FlexLoaderWorkouts extends android.support.v4.content.AsyncTaskLoader<ArrayList<Workout>> {
    int currentId;
    Context mContext;
    ArrayList<Workout> workoutsLoaded;

    //Constructor that takes in and stores mURL.
        public FlexLoaderWorkouts(Context context, int id){
            super(context);
            mContext = context;
            currentId = id;
            }

        public FlexLoaderWorkouts(Context context){
            super(context);
        }

    @Override
    public ArrayList<Workout> loadInBackground() {
        switch (currentId) {
            case 110:
                Cursor cursor = mContext.getContentResolver().query( WORKOUT_CONTENT_URI, null, null, null, null );
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
                        idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_ONE_ID )) );
                        idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_TWO_ID )) );
                        idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_THREE_ID )) );
                        idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_FOUR_ID )) );
                        idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_FIVE_ID )) );
                        idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_SIX_ID )) );
                        idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_SEVEN_ID )) );
                        idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_EIGHT_ID )) );
                        idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_NINE_ID )) );
                        idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_TEN_ID )) );
                        idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_ELEVEN_ID )) );
                        idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_TWELVE_ID )) );
                        idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_THIRTEEN_ID )) );
                        idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_FOURTEEN_ID )) );
                        idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_FIFTEEN_ID )) );
                        idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_SIXTEEN_ID )) );
                        idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_SEVENTEEN_ID )) );
                        idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_EIGHTEEN_ID )) );
                        idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_NINETEEN_ID )) );
                        idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_TWENTY_ID )) );

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
                        if (workoutToAdd.mID== HomeScreen.widgetWorkoutId){
                            HomeScreen.workout = workoutToAdd;
                        }
                    }
                }
                cursor.close();
                workoutsLoaded = workoutsList;
                HomeScreen.workoutsFromLoader = workoutsLoaded;
                return workoutsLoaded;
        }
        return workoutsLoaded;
    }

    //Forces the load.
        @Override
        protected void onStartLoading(){
            forceLoad();
        }

        //Loads the data for the grid in the background. If the url is null, terminates here. Initiates the Film Data fetchfilms methods (which then uses subsequent FilmData methods).

}
