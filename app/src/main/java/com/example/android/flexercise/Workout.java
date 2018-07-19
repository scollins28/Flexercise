package com.example.android.flexercise;

import android.content.Context;

import com.example.android.flexercise.R;

import java.util.ArrayList;

public class Workout {
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

}

