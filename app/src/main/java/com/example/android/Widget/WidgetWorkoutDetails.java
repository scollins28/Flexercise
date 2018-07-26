package com.example.android.Widget;

import android.os.Parcel;
import android.os.Parcelable;

public class WidgetWorkoutDetails implements Parcelable{

    public int mCurrentWorkoutId;
    int mCurrentExerciseId;
    String mExerciseName;
    int mNumberOfSets;
    int mMaxWeight;
    int mStartingWeight;
    int mDistance;
    int mTime;
    int mExerciseType;
    public int mWorkoutCategory;

    public WidgetWorkoutDetails(int currentExerciseId, String exerciseName, int numberOfSets, int maxWeight,
                                     int startingWeight, int distance, int time, int exerciseType){
    mCurrentExerciseId =currentExerciseId;
    mExerciseName = exerciseName;
    mNumberOfSets = numberOfSets;
    mMaxWeight = maxWeight;
    mStartingWeight = startingWeight;
    mCurrentWorkoutId = 999999;
    mDistance = distance;
    mTime = time;
    mExerciseType = exerciseType;
    }

    public WidgetWorkoutDetails (Parcel in){
        mCurrentExerciseId = in.readInt();
        mExerciseName = in.readString();
        mNumberOfSets = in.readInt();
        mMaxWeight = in.readInt();
        mStartingWeight = in.readInt();
        mCurrentWorkoutId = in.readInt();
        mWorkoutCategory = in.readInt();
        mExerciseType = in.readInt();
        mDistance = in.readInt();
        mTime = in.readInt();
    }

    public int getCurrentExerciseId () {
        return mCurrentExerciseId;
    }

    public String getCurrentExerciseName () {
        return mExerciseName;
    }

    public int getNumberOfSets () {
        return mNumberOfSets;
    }

    public int getMaxWeight () {
        return mMaxWeight;
    }

    public int getStartingWeight () {
        return mStartingWeight;
    }

    public int getWorkoutId (){
        return mCurrentWorkoutId;
    }

    public int getDistance (){
        return mDistance;
    }

    public int getTime (){
        return mTime;
    }

    public int getExerciseType (){
        return mExerciseType;
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt( mCurrentExerciseId );
        dest.writeString( mExerciseName );
        dest.writeInt(mNumberOfSets);
        dest.writeInt(mMaxWeight);
        dest.writeInt(mStartingWeight);
        dest.writeInt( mCurrentWorkoutId );
        dest.writeInt( mWorkoutCategory );
        dest.writeInt( mExerciseType );
        dest.writeInt( mDistance );
        dest.writeInt( mTime );
    }

    public static final Creator<WidgetWorkoutDetails> CREATOR = new Creator<WidgetWorkoutDetails>() {
        public WidgetWorkoutDetails createFromParcel(Parcel in) {
            return new WidgetWorkoutDetails(in);
        }

        public WidgetWorkoutDetails[] newArray(int size) {
            return new WidgetWorkoutDetails[size];
        }
    };
}
