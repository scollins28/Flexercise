package com.example.android.flexercise;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Exercise {

    public int mID;
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
    ArrayList <String> mWorkoutListRawStrings = new ArrayList<String>();
    public ArrayList <Integer> mWorkoutIds = new ArrayList<>( );

    Exercise() {
    }

    public Exercise(String exerciseName, int categoryOneValue, int categoryTwoValue, int categoryThreeValue,
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

    public Exercise(String exerciseName, int categoryOneValue, int categoryTwoValue, int categoryThreeValue,
                    int categoryFourValue, int categoryFiveValue, int categorySixValue, String mediaSource,
                    int numberofSets, int maxWeight, int startingWeight, ArrayList<String> addToWorkoutRawValues,
                    String notes, Context context) {
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
        mWorkoutListRawStrings = addToWorkoutRawValues;
        mNotes = notes;

        ArrayList <String> rawStrings = mWorkoutListRawStrings;
        if (rawStrings.isEmpty()){
            mAddToWorkout = context.getString(R.string.no_workouts_selected);
        }
        else {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i <rawStrings.size(); i++){
                String toAdd = rawStrings.get( i );
                if (i==0) {
                    stringBuilder.append( toAdd );
                }
                else {
                    stringBuilder.append( ", " + toAdd);
                }
            }
            mAddToWorkout = stringBuilder.toString();
        }

    }

    public Exercise(String exerciseName, int categoryOneValue, int categoryTwoValue, int categoryThreeValue, int categoryFourValue, int categoryFiveValue, int categorySixValue, String mediaSource, int numberofSets, int maxWeight, int startingWeight, ArrayList<String> addToWorkoutRawValues, String notes, Context context, ArrayList<Integer> workoutsExerciseIsOn) {
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
        mWorkoutListRawStrings = addToWorkoutRawValues;
        mNotes = notes;
        mWorkoutIds = workoutsExerciseIsOn;
       }

    public int getID () {return mID; }

    public String getExerciseName() { return mExerciseName; }

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

    public ArrayList <String> getRawWorkoutStrings () {return mWorkoutListRawStrings; }

    public ArrayList<Integer> getWorkoutIds () {
        return mWorkoutIds;
    }

}

