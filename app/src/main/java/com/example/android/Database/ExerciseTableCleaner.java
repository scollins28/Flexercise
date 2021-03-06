package com.example.android.Database;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import com.example.android.Exercise;
import com.example.android.HomeScreen;
import java.util.ArrayList;
import java.util.Collections;
import static com.example.android.Database.ExerciseContract.ExerciseTable.ADD_TO_WORKOUT;
import static com.example.android.Database.ExerciseContract.ExerciseTable.CONTENT_URI;
import static com.example.android.Database.ExerciseContract.ExerciseTable.DISTANCE;
import static com.example.android.Database.ExerciseContract.ExerciseTable.EXERCISE_NAME;
import static com.example.android.Database.ExerciseContract.ExerciseTable.EXERCISE_TYPE;
import static com.example.android.Database.ExerciseContract.ExerciseTable.MAX_WEIGHT;
import static com.example.android.Database.ExerciseContract.ExerciseTable.MEDIA_SOURCE;
import static com.example.android.Database.ExerciseContract.ExerciseTable.MEDIA_TYPE;
import static com.example.android.Database.ExerciseContract.ExerciseTable.MINUTES;
import static com.example.android.Database.ExerciseContract.ExerciseTable.NOTES;
import static com.example.android.Database.ExerciseContract.ExerciseTable.NUMBER_OF_SETS;
import static com.example.android.Database.ExerciseContract.ExerciseTable.STARTING_WEIGHT;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_EIGHT;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_EIGHTEEN;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_ELEVEN;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_FIFTEEN;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_FIVE;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_FOUR;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_FOURTEEN;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_NINE;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_NINETEEN;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_ONE;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_SEVEN;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_SEVENTEEN;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_SIX;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_SIXTEEN;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_TEN;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_THIRTEEN;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_THREE;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_TWELVE;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_TWENTY;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_TWO;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.WORKOUT_CONTENT_URI;

public class ExerciseTableCleaner {

    public ExerciseTableCleaner(Context context){
    }

    public static Exercise cleanSingleExercise(Context context, Exercise exercise){
        Exercise toClean = cleanExercise (exercise);
        updateExercisesTableSingleExercise (toClean , context );
        return toClean;
    }

    public static ArrayList<Exercise> cleanExerciseList (Context context){
        ArrayList<Exercise> cleanedExercises = getExercisesAndClean( context );
        updateExercisesTable( cleanedExercises, context );
        return cleanedExercises;
    }


    public static ArrayList<Exercise> getExercisesAndClean (Context context){
        ArrayList<Exercise> exercisesList = new ArrayList<>();
        if (HomeScreen.exercisesFromLoader!=null){
            for (int i = 0; i < HomeScreen.exercisesFromLoader.size(); i++) {
                String exerciseName = HomeScreen.exercisesFromLoader.get( i ).getExerciseName();
                int categoryOneValue = HomeScreen.exercisesFromLoader.get( i ).getCategoryOneValue();
                int categoryTwoValue = HomeScreen.exercisesFromLoader.get( i ).getCategoryTwoValue();
                int categoryThreeValue = HomeScreen.exercisesFromLoader.get( i ).getCategoryThreeValue();
                int categoryFourValue = HomeScreen.exercisesFromLoader.get( i ).getCategoryFourValue();
                int categoryFiveValue = HomeScreen.exercisesFromLoader.get( i ).getCategoryFiveValue();
                int categorySixValue = HomeScreen.exercisesFromLoader.get( i ).getCategorySixValue();
                String mediaSource = HomeScreen.exercisesFromLoader.get( i ).getMediaSource();
                int numberOfSets = HomeScreen.exercisesFromLoader.get( i ).getNumberofSets();
                int maxWeight = HomeScreen.exercisesFromLoader.get( i ).getMaxWeight();
                int startingWeight = HomeScreen.exercisesFromLoader.get( i ).getStartingWeight();
                String addToWorkout = HomeScreen.exercisesFromLoader.get( i ).getAddToWorkout();
                String notes = HomeScreen.exercisesFromLoader.get( i ).getNotes();
                ArrayList <Integer> workoutsExerciseFeaturesOn = new ArrayList<>(  );
                int w = 0;
                workoutsExerciseFeaturesOn.add( HomeScreen.exercisesFromLoader.get( i ).getWorkoutIds().get( w ));
                w++;
                workoutsExerciseFeaturesOn.add( HomeScreen.exercisesFromLoader.get( i ).getWorkoutIds().get( w ));
                w++;
                workoutsExerciseFeaturesOn.add( HomeScreen.exercisesFromLoader.get( i ).getWorkoutIds().get( w ));
                w++;
                workoutsExerciseFeaturesOn.add( HomeScreen.exercisesFromLoader.get( i ).getWorkoutIds().get( w ));
                w++;
                workoutsExerciseFeaturesOn.add( HomeScreen.exercisesFromLoader.get( i ).getWorkoutIds().get( w ));
                w++;
                workoutsExerciseFeaturesOn.add( HomeScreen.exercisesFromLoader.get( i ).getWorkoutIds().get( w ));
                w++;
                workoutsExerciseFeaturesOn.add( HomeScreen.exercisesFromLoader.get( i ).getWorkoutIds().get( w ));
                w++;
                workoutsExerciseFeaturesOn.add( HomeScreen.exercisesFromLoader.get( i ).getWorkoutIds().get( w ));
                w++;
                workoutsExerciseFeaturesOn.add( HomeScreen.exercisesFromLoader.get( i ).getWorkoutIds().get( w ));
                w++;
                workoutsExerciseFeaturesOn.add( HomeScreen.exercisesFromLoader.get( i ).getWorkoutIds().get( w ));
                w++;
                workoutsExerciseFeaturesOn.add( HomeScreen.exercisesFromLoader.get( i ).getWorkoutIds().get( w ));
                w++;
                workoutsExerciseFeaturesOn.add( HomeScreen.exercisesFromLoader.get( i ).getWorkoutIds().get( w ));
                w++;
                workoutsExerciseFeaturesOn.add( HomeScreen.exercisesFromLoader.get( i ).getWorkoutIds().get( w ));
                w++;
                workoutsExerciseFeaturesOn.add( HomeScreen.exercisesFromLoader.get( i ).getWorkoutIds().get( w ));
                w++;
                workoutsExerciseFeaturesOn.add( HomeScreen.exercisesFromLoader.get( i ).getWorkoutIds().get( w ));
                w++;
                workoutsExerciseFeaturesOn.add( HomeScreen.exercisesFromLoader.get( i ).getWorkoutIds().get( w ));
                w++;
                workoutsExerciseFeaturesOn.add( HomeScreen.exercisesFromLoader.get( i ).getWorkoutIds().get( w ));
                w++;
                workoutsExerciseFeaturesOn.add( HomeScreen.exercisesFromLoader.get( i ).getWorkoutIds().get( w ));
                w++;
                workoutsExerciseFeaturesOn.add( HomeScreen.exercisesFromLoader.get( i ).getWorkoutIds().get( w ));
                w++;
                workoutsExerciseFeaturesOn.add( HomeScreen.exercisesFromLoader.get( i ).getWorkoutIds().get( w ));

                int idOfWorkoutsSize = workoutsExerciseFeaturesOn.size();
                int a = 0;
                for (int b = 0; b < idOfWorkoutsSize; b++) {
                    if (workoutsExerciseFeaturesOn.get( b )!= 0) {
                        if (a < b) {
                            Collections.swap( workoutsExerciseFeaturesOn, a, b );
                        }
                        a++;
                    }
                }
                ArrayList<String> addToWorkoutRawValues = new ArrayList<>(  );

                Exercise exerciseToAdd = new Exercise( exerciseName, categoryOneValue, categoryTwoValue, categoryThreeValue,
                        categoryFourValue, categoryFiveValue, categorySixValue, mediaSource, numberOfSets, maxWeight,
                        startingWeight, addToWorkoutRawValues, notes, context);
                exerciseToAdd.mWorkoutIds = workoutsExerciseFeaturesOn;
                exerciseToAdd.mID = HomeScreen.exercisesFromLoader.get( i ).getID();
                exerciseToAdd.mMediaType = HomeScreen.exercisesFromLoader.get( i ).getMediaType();

                exercisesList.add( exerciseToAdd);
            }
        }
        return exercisesList;
    }

    public static void updateExercisesTable (ArrayList<Exercise>cleanedExercises, Context context){
        for (int i=0; i<cleanedExercises.size();i++){
            ContentValues contentValues = workoutContentValues( cleanedExercises.get( i ) );
            String id = String.valueOf(cleanedExercises.get( i ).getID());
            Uri updateUri =  WORKOUT_CONTENT_URI.buildUpon().appendPath(id).build();
            context.getContentResolver().update( updateUri, contentValues, null, null);
        }
    }

    public static ContentValues workoutContentValues (Exercise exerciseToAdd){
        ContentValues contentValues = new ContentValues(  );
        contentValues.put( EXERCISE_NAME, exerciseToAdd.getExerciseName());
        contentValues.put( EXERCISE_TYPE, exerciseToAdd.getExerciseType());
        contentValues.put( ExerciseContract.ExerciseTable.CATEGORY_ONE_STATE, exerciseToAdd.getCategoryOneValue() );
        contentValues.put( ExerciseContract.ExerciseTable.CATEGORY_TWO_STATE, exerciseToAdd.getCategoryTwoValue() );
        contentValues.put( ExerciseContract.ExerciseTable.CATEGORY_THREE_STATE, exerciseToAdd.getCategoryThreeValue() );
        contentValues.put( ExerciseContract.ExerciseTable.CATEGORY_FOUR_STATE, exerciseToAdd.getCategoryFourValue() );
        contentValues.put( ExerciseContract.ExerciseTable.CATEGORY_FIVE_STATE, exerciseToAdd.getCategoryFiveValue() );
        contentValues.put( ExerciseContract.ExerciseTable.CATEGORY_SIX_STATE, exerciseToAdd.getCategorySixValue() );
        contentValues.put( MEDIA_TYPE, exerciseToAdd.getMediaType() );
        contentValues.put( MEDIA_SOURCE, exerciseToAdd.getMediaSource() );
        contentValues.put( NUMBER_OF_SETS, exerciseToAdd.getNumberofSets() );
        contentValues.put( MAX_WEIGHT, exerciseToAdd.getMaxWeight() );
        contentValues.put( STARTING_WEIGHT, exerciseToAdd.getStartingWeight() );
        contentValues.put( DISTANCE, exerciseToAdd.getDistance() );
        contentValues.put( MINUTES, exerciseToAdd.getTime() );
        contentValues.put( ADD_TO_WORKOUT, exerciseToAdd.getAddToWorkout() );
        contentValues.put( NOTES, exerciseToAdd.getNotes() );
        int i = 0;
        contentValues.put( WORKOUT_ONE, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_TWO, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_THREE, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_FOUR, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_FIVE, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_SIX, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_SEVEN, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_EIGHT, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_NINE, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_TEN, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_ELEVEN, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_TWELVE, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_THIRTEEN, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_FOURTEEN, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_FIFTEEN, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_SIXTEEN, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_SEVENTEEN, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_EIGHTEEN, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_NINETEEN, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_TWENTY, exerciseToAdd.getWorkoutIds().get(i) );

        return contentValues;
    }

    public static Exercise cleanExercise (Exercise toCleanExercise){
            Exercise exercise = toCleanExercise;
                ArrayList <Integer> idOfWorkouts = new ArrayList<>(  );
                int z = 0;
                idOfWorkouts.add(exercise.getWorkoutIds().get(z));
                z++;
                idOfWorkouts.add(exercise.getWorkoutIds().get(z));
                z++;
                idOfWorkouts.add(exercise.getWorkoutIds().get(z));
                z++;
                idOfWorkouts.add(exercise.getWorkoutIds().get(z));
                z++;
                idOfWorkouts.add(exercise.getWorkoutIds().get(z));
                z++;
                idOfWorkouts.add(exercise.getWorkoutIds().get(z));
                z++;
                idOfWorkouts.add(exercise.getWorkoutIds().get(z));
                z++;
                idOfWorkouts.add(exercise.getWorkoutIds().get(z));
                z++;
                idOfWorkouts.add(exercise.getWorkoutIds().get(z));
                z++;
                idOfWorkouts.add(exercise.getWorkoutIds().get(z));
                z++;
                idOfWorkouts.add(exercise.getWorkoutIds().get(z));
                z++;
                idOfWorkouts.add(exercise.getWorkoutIds().get(z));
                z++;
                idOfWorkouts.add(exercise.getWorkoutIds().get(z));
                z++;
                idOfWorkouts.add(exercise.getWorkoutIds().get(z));
                z++;
                idOfWorkouts.add(exercise.getWorkoutIds().get(z));
                z++;
                idOfWorkouts.add(exercise.getWorkoutIds().get(z));
                z++;
                idOfWorkouts.add(exercise.getWorkoutIds().get(z));
                z++;
                idOfWorkouts.add(exercise.getWorkoutIds().get(z));
                z++;
                idOfWorkouts.add(exercise.getWorkoutIds().get(z));
                z++;
                idOfWorkouts.add(exercise.getWorkoutIds().get(z));

                int idOfWorkoutsSize = idOfWorkouts.size();
                int a = 0;
                for (int b = 0; b < idOfWorkoutsSize; b++) {
                    if (idOfWorkouts.get( b )!= 0) {
                        if (a < b) {
                            Collections.swap( idOfWorkouts, a, b );
                        }
                        a++;
                    }
                }

               exercise.mWorkoutIds.add(idOfWorkouts.get( 0 ));
               exercise.mWorkoutIds.add(idOfWorkouts.get( 1 ));
               exercise.mWorkoutIds.add(idOfWorkouts.get( 2 ));
               exercise.mWorkoutIds.add(idOfWorkouts.get( 3 ));
               exercise.mWorkoutIds.add(idOfWorkouts.get( 4 ));
               exercise.mWorkoutIds.add(idOfWorkouts.get( 5 ));
               exercise.mWorkoutIds.add(idOfWorkouts.get( 6 ));
               exercise.mWorkoutIds.add(idOfWorkouts.get( 7 ));
               exercise.mWorkoutIds.add(idOfWorkouts.get( 8 ));
               exercise.mWorkoutIds.add(idOfWorkouts.get( 9 ));
               exercise.mWorkoutIds.add(idOfWorkouts.get( 10 ));
               exercise.mWorkoutIds.add(idOfWorkouts.get( 11 ));
               exercise.mWorkoutIds.add(idOfWorkouts.get( 12 ));
               exercise.mWorkoutIds.add(idOfWorkouts.get( 13 ));
               exercise.mWorkoutIds.add(idOfWorkouts.get( 14 ));
               exercise.mWorkoutIds.add(idOfWorkouts.get( 15 ));
               exercise.mWorkoutIds.add(idOfWorkouts.get( 16 ));
               exercise.mWorkoutIds.add(idOfWorkouts.get( 17 ));
               exercise.mWorkoutIds.add(idOfWorkouts.get( 18 ));
               exercise.mWorkoutIds.add(idOfWorkouts.get( 19 ));

        return exercise;
    }

    public static void updateExercisesTableSingleExercise (Exercise cleanedExercise, Context context){
            ContentValues contentValues = workoutContentValues( cleanedExercise );
            String id = String.valueOf(cleanedExercise.getID());
            Uri updateUri =  CONTENT_URI.buildUpon().appendPath(id).build();
            context.getContentResolver().update( updateUri, contentValues, null, null);
        }


}


