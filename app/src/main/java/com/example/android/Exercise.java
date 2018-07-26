package com.example.android;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.android.free.R;

import java.util.ArrayList;
import java.util.List;

public class Exercise implements Parcelable {

    public int mID;
    String mExerciseName;
    int mCategoryOneValue;
    int mCategoryTwoValue;
    int mCategoryThreeValue;
    int mCategoryFourValue;
    int mCategoryFiveValue;
    int mCategorySixValue;
    public int mMediaType = 0;
    String mMediaSource;
    int mNumberofSets;
    int mMaxWeight = 0;
    int mStartingWeight = 0;
    String mAddToWorkout;
    String mNotes;
    ArrayList <String> mWorkoutListRawStrings = new ArrayList<String>();
    public ArrayList <Integer> mWorkoutIds = new ArrayList<>( );
    public int mDistance=0;
    public int mTime =0;
    public int mExerciseType = 0;

    public Exercise() {
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

    public int getMediaType (){ return mMediaType;}

    public int getDistance () {return mDistance;}

    public int getTime () {return  mTime;}

    public int getExerciseType () {return mExerciseType;}

    @Override
    public int describeContents() {
        return 0;
    }

    public Exercise (Parcel in){
        mID = in.readInt();
        mExerciseName = in.readString();
        mCategoryOneValue = in.readInt();
        mCategoryTwoValue = in.readInt();
        mCategoryThreeValue = in.readInt();
        mCategoryFourValue = in.readInt();
        mCategoryFiveValue = in.readInt();
        mCategorySixValue = in.readInt();
        mMediaType = in.readInt();
        mMediaSource = in.readString();
        mNumberofSets = in.readInt();
        mMaxWeight = in.readInt();
        mStartingWeight = in.readInt();
        mAddToWorkout = in.readString();
        mNotes = in.readString();
        List <String> string = in.createStringArrayList();
        for (int y=0; y<string.size(); y++) {
            mWorkoutListRawStrings.set( y, string.get( y ) );
        }
        int workoutIds[] = new int[19];
        in.readIntArray( workoutIds);
        for (int i = 0; i<mWorkoutIds.size(); i++) {
            mWorkoutIds.set(i, workoutIds[i]);
        }
        mDistance= in.readInt();
        mTime = in.readInt();
        mExerciseType = in.readInt();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt( mID);
        dest.writeString(mExerciseName);
        dest.writeInt( mCategoryOneValue);
        dest.writeInt( mCategoryTwoValue);
        dest.writeInt( mCategoryThreeValue);
        dest.writeInt( mCategoryFourValue);
        dest.writeInt( mCategoryFiveValue);
        dest.writeInt( mCategorySixValue);
        dest.writeInt( mMediaType = 0);
        dest.writeString(mMediaSource);
        dest.writeInt( mNumberofSets);
        dest.writeInt( mMaxWeight = 0);
        dest.writeInt( mStartingWeight = 0);
        dest.writeString(mAddToWorkout);
        dest.writeString(mNotes);
        List stringList = new ArrayList();
        for (int y = 0; y<mWorkoutListRawStrings.size(); y++){
            stringList.add( mWorkoutListRawStrings.get( y ) );
        }
        dest.writeList( stringList );
        int workoutIds[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        for (int i = 0; i<mWorkoutIds.size(); i++) {
            workoutIds[i] = mWorkoutIds.get( i );
        }
        dest.writeIntArray( workoutIds);
        dest.writeInt( mDistance=0);
        dest.writeInt( mTime =0);
        dest.writeInt( mExerciseType = 0);
    }

    public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };
}

