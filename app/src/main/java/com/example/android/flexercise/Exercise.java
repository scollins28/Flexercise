package com.example.android.flexercise;

import java.util.ArrayList;
import java.util.List;

public class Exercise {

    String mExerciseName;
    int mCategoryOneValue;
    int mCategoryTwoValue;
    int mCategoryThreeValue;
    int mCategoryFourValue;
    int mCategoryFiveValue;
    int mCategorySixValue;
    String mMediaSource;
    int mNumberofSets;
    int mMaxWeight;
    int mStartingWeight;
    String mAddToWorkout;
    String mNotes;

    Exercise() {
    }

    Exercise (String exerciseName,  int categoryOneValue, int categoryTwoValue, int categoryThreeValue,
            int categoryFourValue, int categoryFiveValue, int categorySixValue, String mediaSource,
            int numberofSets, int maxWeight, int startingWeight, String addToWorkout, String notes) {
        mExerciseName = exerciseName;
        mCategoryOneValue = categoryOneValue;
        mCategoryTwoValue = categoryTwoValue;
        mCategoryThreeValue = categoryThreeValue;
        mCategoryFourValue = categoryFourValue;
        mCategoryFiveValue = categoryFiveValue;
        mCategorySixValue = categorySixValue;
        mMediaSource = mediaSource;
        mNumberofSets = numberofSets;
        mMaxWeight = maxWeight;
        mStartingWeight = startingWeight;
        mAddToWorkout = addToWorkout;
        mNotes = notes;

    }


    public String getExerciseName() {
        return mExerciseName;
    }

    public int getCategoryOneValue () { return mCategoryOneValue; }

    public int getCategoryTwoValue () { return mCategoryTwoValue; }

    public int getCategoryThreeValue () { return mCategoryThreeValue; }

    public int getCategoryFourValue () { return mCategoryFourValue; }

    public int getCategoryFiveValue () { return mCategoryFiveValue; }

    public int getCategorySixValue () { return mCategorySixValue; }

    public String getMediaSource () { return mMediaSource; }

    public int getNumberofSets () { return mNumberofSets; }

    public int getMaxWeight () { return mMaxWeight; }

    public int getStartingWeight () { return mStartingWeight; }

    public String getAddToWorkout () { return mAddToWorkout; }

    public String getNotes () { return mNotes; }



}

