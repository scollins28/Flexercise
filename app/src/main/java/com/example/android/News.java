package com.example.android;

import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.android.Database.WorkoutsDatabase.WorkoutContract;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.WORKOUT_CONTENT_URI;

public class News  {
    String mNewsName;
    String mUrl;
    String mDescription;

    public News() {
    }

    public News(String newsName, String url, String description) {
        mNewsName = newsName;
        mUrl = url;
        mDescription = description;
    }

    public String getNewsName () {return mNewsName;}

    public String getUrl() {return mUrl;}

    public String getDescription() {return mDescription;}
}

