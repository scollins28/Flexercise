package com.example.android;

import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.android.Database.WorkoutsDatabase.WorkoutContract;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.WORKOUT_CONTENT_URI;

public class Workout implements Parcelable {
    String mWorkoutName;
    int mCategoryOneValue;
    int mCategoryTwoValue;
    int mCategoryThreeValue;
    int mCategoryFourValue;
    int mCategoryFiveValue;
    int mCategorySixValue;
    public int mExerciseOneId;
    public int mExerciseTwoId;
    public int mExerciseThreeId;
    public int mExerciseFourId;
    public int mExerciseFiveId;
    public int mExerciseSixId;
    public int mExerciseSevenId;
    public int mExerciseEightId;
    public int mExerciseNineId;
    public int mExerciseTenId;
    public int mExerciseElevenId;
    public int mExerciseTwelveId;
    public int mExerciseThirteenId;
    public int mExerciseFourteenId;
    public int mExerciseFifteenId;
    public int mExerciseSixteenId;
    public int mExerciseSeventeenId;
    public int mExerciseEighteenId;
    public int mExerciseNineteenId;
    public int mExerciseTwentyId;
    public int mID;


    public Workout() {
    }

    public Workout(String workoutName, int categoryOneValue, int categoryTwoValue, int categoryThreeValue,
                   int categoryFourValue, int categoryFiveValue, int categorySixValue, int exerciseOneId,
                   int exerciseTwoId, int exerciseThreeId, int exerciseFourId, int exerciseFiveId,
                   int exerciseSixId, int exerciseSevenId, int exerciseEightId, int exerciseNineId, int exerciseTenId,
                   int exerciseElevenId, int exerciseTwelveId, int exerciseThirteenId, int exerciseFourteenId,
                   int exerciseFifteenId, int exerciseSixteenId, int exerciseSeventeenId, int exerciseEighteenId,
                   int exerciseNineteenId, int exerciseTwentyId) {
        mWorkoutName = workoutName;
        mCategoryOneValue = categoryOneValue;
        mCategoryTwoValue = categoryTwoValue;
        mCategoryThreeValue = categoryThreeValue;
        mCategoryFourValue = categoryFourValue;
        mCategoryFiveValue = categoryFiveValue;
        mCategorySixValue = categorySixValue;
        mExerciseOneId = exerciseOneId;
        mExerciseTwoId = exerciseTwoId;
        mExerciseThreeId = exerciseThreeId;
        mExerciseFourId = exerciseFourId;
        mExerciseFiveId = exerciseFiveId;
        mExerciseSixId = exerciseSixId;
        mExerciseSevenId = exerciseSevenId;
        mExerciseEightId = exerciseEightId;
        mExerciseNineId = exerciseNineId;
        mExerciseTenId = exerciseTenId;
        mExerciseElevenId = exerciseElevenId;
        mExerciseTwelveId = exerciseTwelveId;
        mExerciseThirteenId = exerciseThirteenId;
        mExerciseFourteenId = exerciseFourteenId;
        mExerciseFifteenId = exerciseFifteenId;
        mExerciseSixteenId = exerciseSixteenId;
        mExerciseSeventeenId = exerciseSeventeenId;
        mExerciseEighteenId = exerciseEighteenId;
        mExerciseNineteenId = exerciseNineteenId;
        mExerciseTwentyId = exerciseTwentyId;
    }

    public int getID () {return mID; }

    public String getWorkoutName() { return mWorkoutName; }

    public int getCategoryOneValue () { return mCategoryOneValue; }

    public int getCategoryTwoValue () { return mCategoryTwoValue; }

    public int getCategoryThreeValue () { return mCategoryThreeValue; }

    public int getCategoryFourValue () { return mCategoryFourValue; }

    public int getCategoryFiveValue () { return mCategoryFiveValue; }

    public int getCategorySixValue () { return mCategorySixValue; }

    public int getExerciseOneId () { return mExerciseOneId; }

    public int getExerciseTwoId () { return mExerciseTwoId; }

    public int getExerciseThreeId () { return mExerciseThreeId; }

    public int getExerciseFourId () { return mExerciseFourId; }

    public int getExerciseFiveId () { return mExerciseFiveId; }

    public int getExerciseSixId () { return mExerciseSixId; }

    public int getExerciseSevenId () { return mExerciseSevenId; }

    public int getExerciseEightId () { return mExerciseEightId; }

    public int getExerciseNineId () { return mExerciseNineId; }

    public int getExerciseTenId () { return mExerciseTenId; }

    public int getExerciseElevenId () { return mExerciseElevenId; }

    public int getExerciseTwelveId () { return mExerciseTwelveId; }

    public int getExerciseThirteenId () { return mExerciseThirteenId; }

    public int getExerciseFourteenId () { return mExerciseFourteenId; }

    public int getExerciseFifteenId () { return mExerciseFifteenId; }

    public int getExerciseSixteenId () { return mExerciseSixteenId; }

    public int getExerciseSeventeenId () { return mExerciseSeventeenId; }

    public int getExerciseEighteenId () { return mExerciseEighteenId; }

    public int getExerciseNineteenId () { return mExerciseNineteenId; }

    public int getExerciseTwentyId () { return mExerciseTwentyId; }

    public ArrayList <Integer> getArrayOfExerciseIds () {
        ArrayList<Integer> exerciseIds = new ArrayList<>(  );
        exerciseIds.add(getExerciseOneId());
        exerciseIds.add(getExerciseTwoId());
        exerciseIds.add(getExerciseThreeId());
        exerciseIds.add(getExerciseFourId());
        exerciseIds.add(getExerciseFiveId());
        exerciseIds.add(getExerciseSixId());
        exerciseIds.add(getExerciseSevenId());
        exerciseIds.add(getExerciseEightId());
        exerciseIds.add(getExerciseNineId());
        exerciseIds.add(getExerciseTenId());
        exerciseIds.add(getExerciseElevenId());
        exerciseIds.add(getExerciseTwelveId());
        exerciseIds.add(getExerciseThirteenId());
        exerciseIds.add(getExerciseFourteenId());
        exerciseIds.add(getExerciseFifteenId());
        exerciseIds.add(getExerciseSixteenId());
        exerciseIds.add(getExerciseSeventeenId());
        exerciseIds.add(getExerciseEighteenId());
        exerciseIds.add(getExerciseNineteenId());
        exerciseIds.add(getExerciseTwentyId());
        return exerciseIds;
    }

    public Workout (Parcel in){
        mWorkoutName = in.readString();
        mCategoryOneValue = in.readInt();
        mCategoryTwoValue = in.readInt();
        mCategoryThreeValue = in.readInt();
        mCategoryFourValue = in.readInt();
        mCategoryFiveValue= in.readInt();
        mCategorySixValue = in.readInt();
        mExerciseOneId = in.readInt();
        mExerciseTwoId = in.readInt();
        mExerciseThreeId = in.readInt();
        mExerciseFourId = in.readInt();
        mExerciseFiveId = in.readInt();
        mExerciseSixId = in.readInt();
        mExerciseSevenId = in.readInt();
        mExerciseEightId = in.readInt();
        mExerciseNineId = in.readInt();
        mExerciseTenId = in.readInt();
        mExerciseElevenId = in.readInt();
        mExerciseTwelveId = in.readInt();
        mExerciseThirteenId = in.readInt();
        mExerciseFourteenId = in.readInt();
        mExerciseFifteenId = in.readInt();
        mExerciseSixteenId = in.readInt();
        mExerciseSeventeenId = in.readInt();
        mExerciseEighteenId = in.readInt();
        mExerciseNineteenId = in.readInt();
        mExerciseTwentyId = in.readInt();
        mID = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString( mWorkoutName);
        dest.writeInt( mCategoryOneValue);
        dest.writeInt( mCategoryTwoValue);
        dest.writeInt( mCategoryThreeValue);
        dest.writeInt( mCategoryFourValue);
        dest.writeInt( mCategoryFiveValue);
        dest.writeInt( mCategorySixValue);
        dest.writeInt( mExerciseOneId);
        dest.writeInt( mExerciseTwoId);
        dest.writeInt( mExerciseThreeId);
        dest.writeInt( mExerciseFourId);
        dest.writeInt( mExerciseFiveId);
        dest.writeInt( mExerciseSixId);
        dest.writeInt( mExerciseSevenId);
        dest.writeInt( mExerciseEightId);
        dest.writeInt( mExerciseNineId);
        dest.writeInt( mExerciseTenId);
        dest.writeInt( mExerciseElevenId);
        dest.writeInt( mExerciseTwelveId);
        dest.writeInt( mExerciseThirteenId);
        dest.writeInt( mExerciseFourteenId);
        dest.writeInt( mExerciseFifteenId);
        dest.writeInt( mExerciseSixteenId);
        dest.writeInt( mExerciseSeventeenId);
        dest.writeInt( mExerciseEighteenId);
        dest.writeInt( mExerciseNineteenId);
        dest.writeInt( mExerciseTwentyId);
        dest.writeInt( mID);
    }

    public static final Creator<Workout> CREATOR = new Creator<Workout>() {
        public Workout createFromParcel(Parcel in) {
            return new Workout(in);
        }

        public Workout[] newArray(int size) {
            return new Workout[size];
        }
    };

    public static ArrayList<Workout> addWorkouts(Context mContext) {
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
        return workoutsList;
    }

}

