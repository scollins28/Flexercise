package com.example.android.Database.WorkoutsDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import com.example.android.Database.ExerciseTableCleaner;
import com.example.android.Exercise;
import com.example.android.Workout;
import java.util.ArrayList;
import static com.example.android.Database.ExerciseContract.ExerciseTable.CONTENT_URI;
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

public class WorkoutUpdateExerciseLists {

    //This method takes in a newly updated workout and checks to see if any of the exercises referenced by it
    // have changed. It then adds or removes itself from the exercises (or skips over if unchanged).
    public WorkoutUpdateExerciseLists(Workout workout, Context context) {
        Workout updatedWorkout = workout;
        int updatedWorkoutId = workout.mID;
        ArrayList<Integer> exerciseIds = updatedWorkout.getArrayOfExerciseIds();
        ArrayList<Exercise> exercises = ExerciseTableCleaner.getExercisesAndClean( context );
        Exercise exercise;
        for (int y = 0; y < exercises.size(); y++) {
            exercise = exercises.get( y );
            int currentId = exercise.mID;
            int matched = 0;
            while (matched == 0) {
                ContentValues contentValues = new ContentValues();
                int onTheList = 0;
                for (int p = 1; p<exercises.size(); p++){
                    if (currentId==exerciseIds.get(p)){
                        onTheList=1;
                    }
                }
                if (onTheList==0) {
                    for (int i = 0; i < exerciseIds.size(); i++) {
                        if (exercise.getWorkoutIds().get( 0 ) == updatedWorkoutId) {
                            contentValues.put( WORKOUT_ONE, 0 );
                            updateWithNewWorkout( context, exercise, contentValues );
                            break;
                        } else if (exercise.getWorkoutIds().get( 1 ) == updatedWorkoutId) {
                            contentValues.put( WORKOUT_TWO, 0 );
                            updateWithNewWorkout( context, exercise, contentValues );
                            break;
                        } else if (exercise.getWorkoutIds().get(2) == updatedWorkoutId) {
                            contentValues.put( WORKOUT_THREE, 0 );
                            updateWithNewWorkout( context, exercise, contentValues );
                            break;
                        } else if (exercise.getWorkoutIds().get( 3 ) == updatedWorkoutId) {
                            contentValues.put( WORKOUT_FOUR, 0 );
                            updateWithNewWorkout( context, exercise, contentValues );
                            break;
                        } else if (exercise.getWorkoutIds().get( 4 ) == updatedWorkoutId) {
                            contentValues.put( WORKOUT_FIVE, 0 );
                            updateWithNewWorkout( context, exercise, contentValues );
                            break;
                        } else if (exercise.getWorkoutIds().get( 5 ) == updatedWorkoutId) {
                            contentValues.put( WORKOUT_SIX, 0 );
                            updateWithNewWorkout( context, exercise, contentValues );
                            break;
                        } else if (exercise.getWorkoutIds().get( 6 ) == updatedWorkoutId) {
                            contentValues.put( WORKOUT_SEVEN, 0 );
                            updateWithNewWorkout( context, exercise, contentValues );
                            break;
                        } else if (exercise.getWorkoutIds().get( 7 ) == updatedWorkoutId) {
                            contentValues.put( WORKOUT_EIGHT, 0 );
                            updateWithNewWorkout( context, exercise, contentValues );
                            break;
                        } else if (exercise.getWorkoutIds().get( 8 ) == updatedWorkoutId) {
                            contentValues.put( WORKOUT_NINE, 0 );
                            updateWithNewWorkout( context, exercise, contentValues );
                            break;
                        } else if (exercise.getWorkoutIds().get( 9 ) == updatedWorkoutId) {
                            contentValues.put( WORKOUT_TEN, 0 );
                            updateWithNewWorkout( context, exercise, contentValues );
                            break;
                        } else if (exercise.getWorkoutIds().get( 10 ) == updatedWorkoutId) {
                            contentValues.put( WORKOUT_ELEVEN, 0 );
                            updateWithNewWorkout( context, exercise, contentValues );
                            break;
                        } else if (exercise.getWorkoutIds().get( 11 ) == updatedWorkoutId) {
                            contentValues.put( WORKOUT_TWELVE, 0 );
                            updateWithNewWorkout( context, exercise, contentValues );
                            break;
                        } else if (exercise.getWorkoutIds().get( 12 ) == updatedWorkoutId) {
                            contentValues.put( WORKOUT_THIRTEEN, 0 );
                            updateWithNewWorkout( context, exercise, contentValues );
                            break;
                        } else if (exercise.getWorkoutIds().get( 13 ) == updatedWorkoutId) {
                            contentValues.put( WORKOUT_FOURTEEN, 0 );
                            updateWithNewWorkout( context, exercise, contentValues );
                            break;
                        } else if (exercise.getWorkoutIds().get( 14 ) == updatedWorkoutId) {
                            contentValues.put( WORKOUT_FIFTEEN, 0 );
                            updateWithNewWorkout( context, exercise, contentValues );
                            break;
                        } else if (exercise.getWorkoutIds().get( 15 ) == updatedWorkoutId) {
                            contentValues.put( WORKOUT_SIXTEEN, 0 );
                            updateWithNewWorkout( context, exercise, contentValues );
                            break;
                        } else if (exercise.getWorkoutIds().get( 16 ) == updatedWorkoutId) {
                            contentValues.put( WORKOUT_SEVENTEEN, 0 );
                            updateWithNewWorkout( context, exercise, contentValues );
                            break;
                        } else if (exercise.getWorkoutIds().get( 17 ) == updatedWorkoutId) {
                            contentValues.put( WORKOUT_EIGHTEEN, 0 );
                            updateWithNewWorkout( context, exercise, contentValues );
                            break;
                        } else if (exercise.getWorkoutIds().get( 18 ) == updatedWorkoutId) {
                            contentValues.put( WORKOUT_NINETEEN, 0 );
                            updateWithNewWorkout( context, exercise, contentValues );
                            break;
                        } else if (exercise.getWorkoutIds().get( 19 ) == updatedWorkoutId) {
                            contentValues.put( WORKOUT_TWENTY, 0 );
                            updateWithNewWorkout( context, exercise, contentValues );
                            break;
                        }
                    }
                }
                break;
            }
            while (matched == 0) {
                ContentValues contentValues1 = new ContentValues();
                for (int i = 0; i < exerciseIds.size(); i++) {
                    int exerciseFromWorkout = exerciseIds.get( i );
                    if (currentId == exerciseFromWorkout) {
                        if (exercise.getWorkoutIds().get( 0 ) == exerciseIds.get( i )) {
                            break;
                        } else if (exercise.getWorkoutIds().get( 1 ) == exerciseIds.get( i )) {
                            break;
                        } else if (exercise.getWorkoutIds().get( 2 ) ==  exerciseIds.get( i )) {
                            break;
                        } else if (exercise.getWorkoutIds().get( 3 ) ==  exerciseIds.get( i )) {
                            break;
                        } else if (exercise.getWorkoutIds().get( 4 ) ==  exerciseIds.get( i )) {
                            break;
                        } else if (exercise.getWorkoutIds().get( 5 ) ==  exerciseIds.get( i )) {
                            break;
                        } else if (exercise.getWorkoutIds().get( 6 ) ==  exerciseIds.get( i )) {
                            break;
                        } else if (exercise.getWorkoutIds().get( 7 ) ==  exerciseIds.get( i )) {
                            break;
                        } else if (exercise.getWorkoutIds().get( 8 ) ==  exerciseIds.get( i )) {
                            break;
                        } else if (exercise.getWorkoutIds().get( 9 ) ==  exerciseIds.get( i )) {
                            break;
                        } else if (exercise.getWorkoutIds().get( 10 ) ==  exerciseIds.get( i )) {
                            break;
                        } else if (exercise.getWorkoutIds().get( 11 ) ==  exerciseIds.get( i )) {
                            break;
                        } else if (exercise.getWorkoutIds().get( 12 ) ==  exerciseIds.get( i )) {
                            break;
                        } else if (exercise.getWorkoutIds().get( 13 ) ==  exerciseIds.get( i )) {
                            break;
                        } else if (exercise.getWorkoutIds().get( 14 ) ==  exerciseIds.get( i )) {
                            break;
                        } else if (exercise.getWorkoutIds().get( 15 ) ==  exerciseIds.get( i )) {
                            break;
                        } else if (exercise.getWorkoutIds().get( 16 ) ==  exerciseIds.get( i )) {
                            break;
                        } else if (exercise.getWorkoutIds().get( 17 ) ==  exerciseIds.get( i )) {
                            break;
                        } else if (exercise.getWorkoutIds().get( 18 ) ==  exerciseIds.get( i )) {
                            break;
                        } else if (exercise.getWorkoutIds().get( 19 ) ==  exerciseIds.get( i )) {
                            break;
                        }
                    }

                    if (currentId == exerciseFromWorkout) {
                        if (exercise.getWorkoutIds().get( 0 ) < 1) {
                            contentValues1.put( WORKOUT_ONE, updatedWorkoutId );
                            updateWithNewWorkout( context, exercise, contentValues1 );
                            break;
                        } else if (exercise.getWorkoutIds().get( 1 ) < 1) {
                            contentValues1.put( WORKOUT_TWO, updatedWorkoutId );
                            updateWithNewWorkout( context, exercise, contentValues1 );
                            break;
                        } else if (exercise.getWorkoutIds().get( 2 ) < 1) {
                            contentValues1.put( WORKOUT_THREE, updatedWorkoutId );
                            updateWithNewWorkout( context, exercise, contentValues1 );
                            break;
                        } else if (exercise.getWorkoutIds().get( 3 ) < 1) {
                            contentValues1.put( WORKOUT_FOUR, updatedWorkoutId );
                            updateWithNewWorkout( context, exercise, contentValues1 );
                            break;
                        } else if (exercise.getWorkoutIds().get( 4 ) < 1) {
                            contentValues1.put( WORKOUT_FIVE, updatedWorkoutId );
                            updateWithNewWorkout( context, exercise, contentValues1 );
                            break;
                        } else if (exercise.getWorkoutIds().get( 5 ) < 1) {
                            contentValues1.put( WORKOUT_SIX, updatedWorkoutId );
                            updateWithNewWorkout( context, exercise, contentValues1 );
                            break;
                        } else if (exercise.getWorkoutIds().get( 6 ) < 1) {
                            contentValues1.put( WORKOUT_SEVEN, updatedWorkoutId );
                            updateWithNewWorkout( context, exercise, contentValues1 );
                            break;
                        } else if (exercise.getWorkoutIds().get( 7 ) < 1) {
                            contentValues1.put( WORKOUT_EIGHT, updatedWorkoutId );
                            updateWithNewWorkout( context, exercise, contentValues1 );
                            break;
                        } else if (exercise.getWorkoutIds().get( 8 ) < 1) {
                            contentValues1.put( WORKOUT_NINE, updatedWorkoutId );
                            updateWithNewWorkout( context, exercise, contentValues1 );
                            break;
                        } else if (exercise.getWorkoutIds().get( 9 ) < 1) {
                            contentValues1.put( WORKOUT_TEN, updatedWorkoutId );
                            updateWithNewWorkout( context, exercise, contentValues1 );
                            break;
                        } else if (exercise.getWorkoutIds().get( 10 ) < 1) {
                            contentValues1.put( WORKOUT_ELEVEN, updatedWorkoutId );
                            updateWithNewWorkout( context, exercise, contentValues1 );
                            break;
                        } else if (exercise.getWorkoutIds().get( 11 ) < 1) {
                            contentValues1.put( WORKOUT_TWELVE, updatedWorkoutId );
                            updateWithNewWorkout( context, exercise, contentValues1 );
                            break;
                        } else if (exercise.getWorkoutIds().get( 12 ) < 1) {
                            contentValues1.put( WORKOUT_THIRTEEN, updatedWorkoutId );
                            updateWithNewWorkout( context, exercise, contentValues1 );
                            break;
                        } else if (exercise.getWorkoutIds().get( 13 ) < 1) {
                            contentValues1.put( WORKOUT_FOURTEEN, updatedWorkoutId );
                            updateWithNewWorkout( context, exercise, contentValues1 );
                            break;
                        } else if (exercise.getWorkoutIds().get( 14 ) < 1) {
                            contentValues1.put( WORKOUT_FIFTEEN, updatedWorkoutId );
                            updateWithNewWorkout( context, exercise, contentValues1 );
                            break;
                        } else if (exercise.getWorkoutIds().get( 15 ) < 1) {
                            contentValues1.put( WORKOUT_SIXTEEN, updatedWorkoutId );
                            updateWithNewWorkout( context, exercise, contentValues1 );
                            break;
                        } else if (exercise.getWorkoutIds().get( 16 ) < 1) {
                            contentValues1.put( WORKOUT_SEVENTEEN, updatedWorkoutId );
                            updateWithNewWorkout( context, exercise, contentValues1 );
                            break;
                        } else if (exercise.getWorkoutIds().get( 17 ) < 1) {
                            contentValues1.put( WORKOUT_EIGHTEEN, updatedWorkoutId );
                            updateWithNewWorkout( context, exercise, contentValues1 );
                            break;
                        } else if (exercise.getWorkoutIds().get( 18 ) < 1) {
                            contentValues1.put( WORKOUT_NINETEEN, updatedWorkoutId );
                            updateWithNewWorkout( context, exercise, contentValues1 );
                            break;
                        } else if (exercise.getWorkoutIds().get( 19 ) < 1) {
                            contentValues1.put( WORKOUT_TWENTY, updatedWorkoutId );
                            updateWithNewWorkout( context, exercise, contentValues1 );
                            break;
                        }
                    }
                }
                break;
            }
        }
    }


        // This method does the actual updating of exercises but is referred to only in the above method.
       public void updateWithNewWorkout (Context context, Exercise exercise, ContentValues contentValues) {
           String id = String.valueOf(exercise.getID());
           Uri updateUri =  CONTENT_URI.buildUpon().appendPath(id).build();
           context.getContentResolver().update( updateUri, contentValues, null, null);
       }

       }

