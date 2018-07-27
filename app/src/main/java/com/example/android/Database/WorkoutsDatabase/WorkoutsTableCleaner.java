package com.example.android.Database.WorkoutsDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.example.android.Database.ExerciseContract;
import com.example.android.Workout;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.CATEGORY_FIVE_STATE;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.CATEGORY_FOUR_STATE;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.CATEGORY_ONE_STATE;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.CATEGORY_SIX_STATE;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.CATEGORY_THREE_STATE;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.CATEGORY_TWO_STATE;
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
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.WORKOUT_NAME;

public class WorkoutsTableCleaner {

    //Constructor.
    public WorkoutsTableCleaner(Context context){
    }

    // Cleans a single workout, takes in the workout and moves the exercises up in the table column position
    // to ensure no empty spaces e.g. 1 , 0, 3, 0, 5 becomes 1, 3, 5, 0, 0.
    public static Workout cleanSingleWorkout(Context context, Workout workout){
        Workout toClean = cleanWorkout (workout);
        updateWorkoutsTableSingleWorkout (toClean , context );
        return toClean;
    }
    // Cleans an ArrayList of workouts, takes in the arraylist and moves the exercises of each workout
    // up in the table column position to ensure no empty spaces e.g. 1 , 0, 3, 0, 5 becomes 1, 3, 5, 0, 0.
    public static ArrayList<Workout> cleanWorkoutList (Context context){
        ArrayList<Workout> cleanedWorkouts = getWorkoutsAndClean( context );
        updateWorkoutsTable( cleanedWorkouts, context );
        return cleanedWorkouts;
    }

    //Method uses a cursor to retrieve the latest data in the workouts table. Then moves the sorts the columns
    // to ensure that the exercises are stored in the first available columns.
    public static ArrayList<Workout> getWorkoutsAndClean (Context context){
        Cursor cursor = context.getContentResolver().query( WORKOUT_CONTENT_URI, null, null, null, null );
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

                int idOfExercisesSize = idOfExercises.size();
                int a = 0;
                for (int b = 0; b < idOfExercisesSize; b++) {
                    if (idOfExercises.get( b )!= 0) {
                        if (a < b) {
                            Collections.swap( idOfExercises, a, b );
                        }
                        a++;
                    }
                }

                Workout workoutToAdd = new Workout( workoutName, categoriesOneValue, categoriesTwoValue,
                        categoriesThreeValue, categoriesFourValue, categoriesFiveValue, categoriesSixValue, idOfExercises.get( 0 ),
                        idOfExercises.get( 1 ), idOfExercises.get( 2 ), idOfExercises.get( 3 ), idOfExercises.get( 4 ),
                        idOfExercises.get( 5 ), idOfExercises.get( 6 ), idOfExercises.get( 7 ), idOfExercises.get( 8 ),
                        idOfExercises.get( 9 ), idOfExercises.get( 10 ), idOfExercises.get( 11 ), idOfExercises.get( 12 ),
                        idOfExercises.get( 13 ), idOfExercises.get( 14 ), idOfExercises.get( 15 ), idOfExercises.get( 16 ),
                        idOfExercises.get( 17 ), idOfExercises.get( 18 ), idOfExercises.get( 19 )
                );
                workoutToAdd.mID = cursor.getInt (cursor.getColumnIndex( ExerciseContract.ExerciseTable._ID ));
                workoutsList.add( workoutToAdd);
            }
        }
        cursor.close();
        return workoutsList;
    }

    //Using the QUID update method, updates each entry in the workouts table with the sorted exercises.
    public static void updateWorkoutsTable (ArrayList<Workout>cleanedWorkouts, Context context){
        for (int i=0; i<cleanedWorkouts.size();i++){
            ContentValues contentValues = workoutContentValues( cleanedWorkouts.get( i ) );
            String id = String.valueOf(cleanedWorkouts.get( i ).getID());
            Uri updateUri =  WORKOUT_CONTENT_URI.buildUpon().appendPath(id).build();
            context.getContentResolver().update( updateUri, contentValues, null, null);
        }
    }

    //Generates the content values for the QUID update methods.
    public static ContentValues workoutContentValues (Workout workoutToAdd){
            ContentValues contentValues = new ContentValues(  );
            contentValues.put( WORKOUT_NAME, workoutToAdd.getWorkoutName());
            contentValues.put( CATEGORY_ONE_STATE, workoutToAdd.getCategoryOneValue() );
            contentValues.put( CATEGORY_TWO_STATE, workoutToAdd.getCategoryTwoValue() );
            contentValues.put( CATEGORY_THREE_STATE, workoutToAdd.getCategoryThreeValue() );
            contentValues.put( CATEGORY_FOUR_STATE, workoutToAdd.getCategoryFourValue() );
            contentValues.put( CATEGORY_FIVE_STATE, workoutToAdd.getCategoryFiveValue() );
            contentValues.put( CATEGORY_SIX_STATE, workoutToAdd.getCategorySixValue() );
            contentValues.put( EXERCISE_ONE_ID, workoutToAdd.getExerciseOneId() );
            contentValues.put( EXERCISE_TWO_ID, workoutToAdd.getExerciseTwoId() );
            contentValues.put( EXERCISE_THREE_ID, workoutToAdd.getExerciseThreeId() );
            contentValues.put( EXERCISE_FOUR_ID, workoutToAdd.getExerciseFourId() );
            contentValues.put( EXERCISE_FIVE_ID, workoutToAdd.getExerciseFiveId() );
            contentValues.put( EXERCISE_SIX_ID, workoutToAdd.getExerciseSixId() );
            contentValues.put( EXERCISE_SEVEN_ID, workoutToAdd.getExerciseSevenId() );
            contentValues.put( EXERCISE_EIGHT_ID, workoutToAdd.getExerciseEightId() );
            contentValues.put( EXERCISE_NINE_ID, workoutToAdd.getExerciseNineId() );
            contentValues.put( EXERCISE_TEN_ID, workoutToAdd.getExerciseTenId() );
            contentValues.put( EXERCISE_ELEVEN_ID, workoutToAdd.getExerciseElevenId() );
            contentValues.put( EXERCISE_TWELVE_ID, workoutToAdd.getExerciseTwelveId() );
            contentValues.put( EXERCISE_THIRTEEN_ID, workoutToAdd.getExerciseThirteenId() );
            contentValues.put( EXERCISE_FOURTEEN_ID, workoutToAdd.getExerciseFourteenId() );
            contentValues.put( EXERCISE_FIFTEEN_ID, workoutToAdd.getExerciseFifteenId() );
            contentValues.put( EXERCISE_SIXTEEN_ID, workoutToAdd.getExerciseSixteenId() );
            contentValues.put( EXERCISE_SEVENTEEN_ID, workoutToAdd.getExerciseSeventeenId() );
            contentValues.put( EXERCISE_EIGHTEEN_ID, workoutToAdd.getExerciseEighteenId() );
            contentValues.put( EXERCISE_NINETEEN_ID, workoutToAdd.getExerciseNineteenId() );
            contentValues.put( EXERCISE_TWENTY_ID, workoutToAdd.getExerciseTwentyId() );
            return contentValues;
    }

    //The singular method is passed a specific workout and so doesn't use a QUID method to obtain the data.
    // This function therefore just sorts and returns the workout.
    public static Workout cleanWorkout (Workout workout){
                ArrayList <Integer> idOfExercises = new ArrayList<>(  );
                idOfExercises.add( workout.getExerciseOneId());
                idOfExercises.add( workout.getExerciseTwoId());
                idOfExercises.add( workout.getExerciseThreeId());
                idOfExercises.add( workout.getExerciseFourId());
                idOfExercises.add( workout.getExerciseFiveId());
                idOfExercises.add( workout.getExerciseSixId());
                idOfExercises.add( workout.getExerciseSevenId());
                idOfExercises.add( workout.getExerciseEightId());
                idOfExercises.add( workout.getExerciseNineId());
                idOfExercises.add( workout.getExerciseTenId());
                idOfExercises.add( workout.getExerciseElevenId());
                idOfExercises.add( workout.getExerciseTwelveId());
                idOfExercises.add( workout.getExerciseThirteenId());
                idOfExercises.add( workout.getExerciseFourteenId());
                idOfExercises.add( workout.getExerciseFifteenId());
                idOfExercises.add( workout.getExerciseSixteenId());
                idOfExercises.add( workout.getExerciseSeventeenId());
                idOfExercises.add( workout.getExerciseEighteenId());
                idOfExercises.add( workout.getExerciseNineteenId());
                idOfExercises.add( workout.getExerciseTwentyId());


                int idOfExercisesSize = idOfExercises.size();
                int a = 0;
                for (int b = 0; b < idOfExercisesSize; b++) {
                    if (idOfExercises.get( b )!= 0) {
                        if (a < b) {
                            Collections.swap( idOfExercises, a, b );
                        }
                        a++;
                    }
                }

               workout.mExerciseOneId = idOfExercises.get( 0 );
               workout.mExerciseTwoId = idOfExercises.get( 1 );
               workout.mExerciseThreeId = idOfExercises.get( 2 );
               workout.mExerciseFourId = idOfExercises.get( 3 );
               workout.mExerciseFiveId = idOfExercises.get( 4 );
               workout.mExerciseSixId = idOfExercises.get( 5 );
               workout.mExerciseSevenId = idOfExercises.get( 6 );
               workout.mExerciseEightId = idOfExercises.get( 7 );
               workout.mExerciseNineId = idOfExercises.get( 8 );
               workout.mExerciseTenId = idOfExercises.get( 9 );
               workout.mExerciseElevenId = idOfExercises.get( 10 );
               workout.mExerciseTwelveId = idOfExercises.get( 11 );
               workout.mExerciseThirteenId = idOfExercises.get( 12 );
               workout.mExerciseFourteenId = idOfExercises.get( 13 );
               workout.mExerciseFifteenId = idOfExercises.get( 14 );
               workout.mExerciseSixteenId = idOfExercises.get( 15 );
               workout.mExerciseSeventeenId = idOfExercises.get( 16 );
               workout.mExerciseEighteenId = idOfExercises.get( 17 );
               workout.mExerciseNineteenId = idOfExercises.get( 18 );
               workout.mExerciseTwentyId = idOfExercises.get( 19 );

        return workout;
    }

    //Using the QUID update method, updates the specific entry in the workouts table with the sorted exercises.
    public static void updateWorkoutsTableSingleWorkout (Workout cleanedWorkout, Context context){
            ContentValues contentValues = workoutContentValues( cleanedWorkout );
            String id = String.valueOf(cleanedWorkout.getID());
            Uri updateUri =  WORKOUT_CONTENT_URI.buildUpon().appendPath(id).build();
            context.getContentResolver().update( updateUri, contentValues, null, null);
        }


}


