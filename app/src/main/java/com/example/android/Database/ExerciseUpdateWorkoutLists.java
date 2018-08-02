package com.example.android.Database;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import com.example.android.Database.WorkoutsDatabase.WorkoutsTableCleaner;
import com.example.android.Exercise;
import com.example.android.HomeScreen;
import com.example.android.Workout;
import java.util.ArrayList;
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

public class ExerciseUpdateWorkoutLists {


    public ExerciseUpdateWorkoutLists(Exercise exercise, Context context) {
        Exercise updatedExercise = exercise;
        int updatedExerciseId = exercise.mID;
        ArrayList<Integer> workoutIds = updatedExercise.mWorkoutIds;
        ArrayList<Workout> workouts = WorkoutsTableCleaner.getWorkoutsAndClean(context);

        Workout workout;
        for (int y = 0; y < workouts.size(); y++) {
            workout = workouts.get( y );
            int currentId = workout.mID;
            int matched = 0;
            while (matched == 0) {
                ContentValues contentValues = new ContentValues();
                int onTheList = 0;
                for (int p = 1; p<workouts.size(); p++){
                    if (currentId==workoutIds.get(p)){
                        onTheList=1;
                    }
                }
                if (onTheList==0) {
                    for (int i = 0; i < workoutIds.size(); i++) {
                        if (workout.getExerciseOneId() == updatedExerciseId) {
                            contentValues.put( EXERCISE_ONE_ID, 0 );
                            updateWithNewExercise( context, workout, contentValues );
                            break;
                        } else if (workout.getExerciseTwoId() == updatedExerciseId) {
                            contentValues.put( EXERCISE_TWO_ID, 0 );
                            updateWithNewExercise( context, workout, contentValues );
                            break;
                        } else if (workout.getExerciseThreeId() == updatedExerciseId) {
                            contentValues.put( EXERCISE_THREE_ID, 0 );
                            updateWithNewExercise( context, workout, contentValues );
                            break;
                        } else if (workout.getExerciseFourId() == updatedExerciseId) {
                            contentValues.put( EXERCISE_FOUR_ID, 0 );
                            updateWithNewExercise( context, workout, contentValues );
                            break;
                        } else if (workout.getExerciseFiveId() == updatedExerciseId) {
                            contentValues.put( EXERCISE_FIVE_ID, 0 );
                            updateWithNewExercise( context, workout, contentValues );
                            break;
                        } else if (workout.getExerciseSixId() == updatedExerciseId) {
                            contentValues.put( EXERCISE_SIX_ID, 0 );
                            updateWithNewExercise( context, workout, contentValues );
                            break;
                        } else if (workout.getExerciseSevenId() == updatedExerciseId) {
                            contentValues.put( EXERCISE_SEVEN_ID, 0 );
                            updateWithNewExercise( context, workout, contentValues );
                            break;
                        } else if (workout.getExerciseEightId() == updatedExerciseId) {
                            contentValues.put( EXERCISE_EIGHT_ID, 0 );
                            updateWithNewExercise( context, workout, contentValues );
                            break;
                        } else if (workout.getExerciseNineId() == updatedExerciseId) {
                            contentValues.put( EXERCISE_NINE_ID, 0 );
                            updateWithNewExercise( context, workout, contentValues );
                            break;
                        } else if (workout.getExerciseTenId() == updatedExerciseId) {
                            contentValues.put( EXERCISE_TEN_ID, 0 );
                            updateWithNewExercise( context, workout, contentValues );
                            break;
                        } else if (workout.getExerciseElevenId() == updatedExerciseId) {
                            contentValues.put( EXERCISE_ELEVEN_ID, 0 );
                            updateWithNewExercise( context, workout, contentValues );
                            break;
                        } else if (workout.getExerciseTwelveId() == updatedExerciseId) {
                            contentValues.put( EXERCISE_TWELVE_ID, 0 );
                            updateWithNewExercise( context, workout, contentValues );
                            break;
                        } else if (workout.getExerciseThirteenId() == updatedExerciseId) {
                            contentValues.put( EXERCISE_THIRTEEN_ID, 0 );
                            updateWithNewExercise( context, workout, contentValues );
                            break;
                        } else if (workout.getExerciseFourteenId() == updatedExerciseId) {
                            contentValues.put( EXERCISE_FOURTEEN_ID, 0 );
                            updateWithNewExercise( context, workout, contentValues );
                            break;
                        } else if (workout.getExerciseFifteenId() == updatedExerciseId) {
                            contentValues.put( EXERCISE_FIFTEEN_ID, 0 );
                            updateWithNewExercise( context, workout, contentValues );
                            break;
                        } else if (workout.getExerciseSixteenId() == updatedExerciseId) {
                            contentValues.put( EXERCISE_SIXTEEN_ID, 0 );
                            updateWithNewExercise( context, workout, contentValues );
                            break;
                        } else if (workout.getExerciseSeventeenId() == updatedExerciseId) {
                            contentValues.put( EXERCISE_SEVENTEEN_ID, 0 );
                            updateWithNewExercise( context, workout, contentValues );
                            break;
                        } else if (workout.getExerciseEighteenId() == updatedExerciseId) {
                            contentValues.put( EXERCISE_EIGHTEEN_ID, 0 );
                            updateWithNewExercise( context, workout, contentValues );
                            break;
                        } else if (workout.getExerciseNineteenId() == updatedExerciseId) {
                            contentValues.put( EXERCISE_NINETEEN_ID, 0 );
                            updateWithNewExercise( context, workout, contentValues );
                            break;
                        } else if (workout.getExerciseTwentyId() == updatedExerciseId) {
                            contentValues.put( EXERCISE_TWENTY_ID, 0 );
                            updateWithNewExercise( context, workout, contentValues );
                            break;
                        }
                    }
                }
                break;
            }
            while (matched == 0) {
                ContentValues contentValues1 = new ContentValues();
                for (int i = 0; i < workoutIds.size(); i++) {
                    int workoutFromExercise = workoutIds.get( i );
                    if (currentId == workoutFromExercise) {
                        if (workout.getExerciseOneId() == workoutIds.get( i )) {
                            break;
                        } else if (workout.getExerciseTwoId() == workoutIds.get( i )) {
                            break;
                        } else if (workout.getExerciseThreeId() == workoutIds.get( i )) {
                            break;
                        } else if (workout.getExerciseFourId() == workoutIds.get( i )) {
                            break;
                        } else if (workout.getExerciseFiveId() == workoutIds.get( i )) {
                            break;
                        } else if (workout.getExerciseSixId() == workoutIds.get( i )) {
                            break;
                        } else if (workout.getExerciseSevenId() == workoutIds.get( i )) {
                            break;
                        } else if (workout.getExerciseEightId() == workoutIds.get( i )) {
                            break;
                        } else if (workout.getExerciseNineId() == workoutIds.get( i )) {
                            break;
                        } else if (workout.getExerciseTenId() == workoutIds.get( i )) {
                            break;
                        } else if (workout.getExerciseElevenId() == workoutIds.get( i )) {
                            break;
                        } else if (workout.getExerciseTwelveId() == workoutIds.get( i )) {
                            break;
                        } else if (workout.getExerciseThirteenId() == workoutIds.get( i )) {
                            break;
                        } else if (workout.getExerciseFourteenId() == workoutIds.get( i )) {
                            break;
                        } else if (workout.getExerciseFifteenId() == workoutIds.get( i )) {
                            break;
                        } else if (workout.getExerciseSixteenId() == workoutIds.get( i )) {
                            break;
                        } else if (workout.getExerciseSeventeenId() == workoutIds.get( i )) {
                            break;
                        } else if (workout.getExerciseEighteenId() == workoutIds.get( i )) {
                            break;
                        } else if (workout.getExerciseNineteenId() == workoutIds.get( i )) {
                            break;
                        } else if (workout.getExerciseTwentyId() == workoutIds.get( i )) {
                            break;
                        }
                    }

                    if (currentId == workoutFromExercise) {
                        if (workout.getExerciseOneId() < 1) {
                            contentValues1.put( EXERCISE_ONE_ID, updatedExerciseId );
                            updateWithNewExercise( context, workout, contentValues1 );
                            break;
                        } else if (workout.getExerciseTwoId() < 1) {
                            contentValues1.put( EXERCISE_TWO_ID, updatedExerciseId );
                            updateWithNewExercise( context, workout, contentValues1 );
                            break;
                        } else if (workout.getExerciseThreeId() < 1) {
                            contentValues1.put( EXERCISE_THREE_ID, updatedExerciseId );
                            updateWithNewExercise( context, workout, contentValues1 );
                            break;
                        } else if (workout.getExerciseFourId() == 0) {
                            contentValues1.put( EXERCISE_FOUR_ID, updatedExerciseId );
                            updateWithNewExercise( context, workout, contentValues1 );
                            break;
                        } else if (workout.getExerciseFiveId() == 0) {
                            contentValues1.put( EXERCISE_FIVE_ID, updatedExerciseId );
                            updateWithNewExercise( context, workout, contentValues1 );
                            break;
                        } else if (workout.getExerciseSixId() == 0) {
                            contentValues1.put( EXERCISE_SIX_ID, updatedExerciseId );
                            updateWithNewExercise( context, workout, contentValues1 );
                            break;
                        } else if (workout.getExerciseSevenId() == 0) {
                            contentValues1.put( EXERCISE_SEVEN_ID, updatedExerciseId );
                            updateWithNewExercise( context, workout, contentValues1 );
                            break;
                        } else if (workout.getExerciseEightId() == 0) {
                            contentValues1.put( EXERCISE_EIGHT_ID, updatedExerciseId );
                            updateWithNewExercise( context, workout, contentValues1 );
                            break;
                        } else if (workout.getExerciseNineId() == 0) {
                            contentValues1.put( EXERCISE_NINE_ID, updatedExerciseId );
                            updateWithNewExercise( context, workout, contentValues1 );
                            break;
                        } else if (workout.getExerciseTenId() == 0) {
                            contentValues1.put( EXERCISE_TEN_ID, updatedExerciseId );
                            updateWithNewExercise( context, workout, contentValues1 );
                            break;
                        } else if (workout.getExerciseElevenId() == 0) {
                            contentValues1.put( EXERCISE_ELEVEN_ID, updatedExerciseId );
                            updateWithNewExercise( context, workout, contentValues1 );
                            break;
                        } else if (workout.getExerciseTwelveId() == 0) {
                            contentValues1.put( EXERCISE_TWELVE_ID, updatedExerciseId );
                            updateWithNewExercise( context, workout, contentValues1 );
                            break;
                        } else if (workout.getExerciseThirteenId() == 0) {
                            contentValues1.put( EXERCISE_THIRTEEN_ID, updatedExerciseId );
                            updateWithNewExercise( context, workout, contentValues1 );
                            break;
                        } else if (workout.getExerciseFourteenId() == 0) {
                            contentValues1.put( EXERCISE_FOURTEEN_ID, updatedExerciseId );
                            updateWithNewExercise( context, workout, contentValues1 );
                            break;
                        } else if (workout.getExerciseFifteenId() == 0) {
                            contentValues1.put( EXERCISE_FIFTEEN_ID, updatedExerciseId );
                            updateWithNewExercise( context, workout, contentValues1 );
                            break;
                        } else if (workout.getExerciseSixteenId() == 0) {
                            contentValues1.put( EXERCISE_SIXTEEN_ID, updatedExerciseId );
                            updateWithNewExercise( context, workout, contentValues1 );
                            break;
                        } else if (workout.getExerciseSeventeenId() == 0) {
                            contentValues1.put( EXERCISE_SEVENTEEN_ID, updatedExerciseId );
                            updateWithNewExercise( context, workout, contentValues1 );
                            break;
                        } else if (workout.getExerciseEighteenId() == 0) {
                            contentValues1.put( EXERCISE_EIGHTEEN_ID, updatedExerciseId );
                            updateWithNewExercise( context, workout, contentValues1 );
                            break;
                        } else if (workout.getExerciseNineteenId() == 0) {
                            contentValues1.put( EXERCISE_NINETEEN_ID, updatedExerciseId );
                            updateWithNewExercise( context, workout, contentValues1 );
                            break;
                        } else if (workout.getExerciseTwentyId() == 0) {
                            contentValues1.put( EXERCISE_TWENTY_ID, updatedExerciseId );
                            updateWithNewExercise( context, workout, contentValues1 );
                            break;
                        }
                    }
                }
                break;
            }
        }
    }

    public ArrayList<Exercise> getExercises(Context context) {

        ArrayList<Exercise> exercisesList = new ArrayList<>();
        if (HomeScreen.exercisesFromLoader!=null) {
                for (int i = 0; i < HomeScreen.exercisesFromLoader.size(); i++) {
                    String exerciseName = HomeScreen.exercisesFromLoader.get( i ).getExerciseName();
                    int categoriesOneValue = HomeScreen.exercisesFromLoader.get( i ).getCategoryOneValue();
                    int categoriesTwoValue = HomeScreen.exercisesFromLoader.get( i ).getCategoryTwoValue();
                    int categoriesThreeValue = HomeScreen.exercisesFromLoader.get( i ).getCategoryThreeValue();
                    int categoriesFourValue = HomeScreen.exercisesFromLoader.get( i ).getCategoryFourValue();
                    int categoriesFiveValue = HomeScreen.exercisesFromLoader.get( i ).getCategoryFiveValue();
                    int categoriesSixValue = HomeScreen.exercisesFromLoader.get( i ).getCategorySixValue();
                    String mediaSource = HomeScreen.exercisesFromLoader.get( i ).getMediaSource();
                    int numberOfSets = HomeScreen.exercisesFromLoader.get( i ).getNumberofSets();
                    int maxWeight = HomeScreen.exercisesFromLoader.get( i ).getMaxWeight();
                    int startingWeight = HomeScreen.exercisesFromLoader.get( i ).getStartingWeight();
                    String addToWorkout = HomeScreen.exercisesFromLoader.get( i ).getAddToWorkout();
                    String notes = HomeScreen.exercisesFromLoader.get( i ).getAddToWorkout();
                    ArrayList<Integer> workoutsExerciseFeaturesOn = new ArrayList<>();
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

                    Exercise exerciseToAdd = new Exercise( exerciseName, categoriesOneValue, categoriesTwoValue,
                            categoriesThreeValue, categoriesFourValue, categoriesFiveValue, categoriesSixValue,
                            mediaSource, numberOfSets, maxWeight, startingWeight, addToWorkout, notes );
                    exerciseToAdd.mID = HomeScreen.exercisesFromLoader.get( i ).getID();
                    exerciseToAdd.mWorkoutIds = workoutsExerciseFeaturesOn;
                    exercisesList.add( exerciseToAdd );
                }
            }
        return exercisesList;
       }

       public void updateWithNewExercise (Context context, Workout workout, ContentValues contentValues) {
           String id = String.valueOf(workout.getID());
           Uri updateUri =  WORKOUT_CONTENT_URI.buildUpon().appendPath(id).build();
           context.getContentResolver().update( updateUri, contentValues, null, null);
       }

       }

