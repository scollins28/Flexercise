package com.example.android.flexercise.Database.WorkoutsDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.flexercise.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable;

import static com.example.android.flexercise.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.WORKOUT_TABLE_NAME;


public class WorkoutDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "workouts.db";
    public static final int VERSION_NUMBER = 2;

    public WorkoutDbHelper(Context context){
        super (context, DATABASE_NAME, null, VERSION_NUMBER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_WORKOUT_TABLE = "CREATE TABLE " +
                WORKOUT_TABLE_NAME + "(" +
                WorkoutsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                WorkoutsTable.WORKOUT_NAME + " TEXT," +
                WorkoutsTable.CATEGORY_ONE_STATE + " INTEGER DEFAULT 0," +
                WorkoutsTable.CATEGORY_TWO_STATE + " INTEGER DEFAULT 0," +
                WorkoutsTable.CATEGORY_THREE_STATE + " INTEGER DEFAULT 0," +
                WorkoutsTable.CATEGORY_FOUR_STATE + " INTEGER DEFAULT 0," +
                WorkoutsTable.CATEGORY_FIVE_STATE + " INTEGER DEFAULT 0," +
                WorkoutsTable.CATEGORY_SIX_STATE + " INTEGER DEFAULT 0," +
                WorkoutsTable.EXERCISE_ONE_ID + " INTEGER DEFAULT 0," +
                WorkoutsTable.EXERCISE_TWO_ID + " INTEGER DEFAULT 0," +
                WorkoutsTable.EXERCISE_THREE_ID + " INTEGER DEFAULT 0," +
                WorkoutsTable.EXERCISE_FOUR_ID + " INTEGER DEFAULT 0," +
                WorkoutsTable.EXERCISE_FIVE_ID + " INTEGER DEFAULT 0," +
                WorkoutsTable.EXERCISE_SIX_ID + " INTEGER DEFAULT 0," +
                WorkoutsTable.EXERCISE_SEVEN_ID + " INTEGER DEFAULT 0," +
                WorkoutsTable.EXERCISE_EIGHT_ID + " INTEGER DEFAULT 0," +
                WorkoutsTable.EXERCISE_NINE_ID + " INTEGER DEFAULT 0," +
                WorkoutsTable.EXERCISE_TEN_ID + " INTEGER DEFAULT 0," +
                WorkoutsTable.EXERCISE_ELEVEN_ID + " INTEGER DEFAULT 0," +
                WorkoutsTable.EXERCISE_TWELVE_ID + " INTEGER DEFAULT 0," +
                WorkoutsTable.EXERCISE_THIRTEEN_ID + " INTEGER DEFAULT 0," +
                WorkoutsTable.EXERCISE_FOURTEEN_ID + " INTEGER DEFAULT 0," +
                WorkoutsTable.EXERCISE_FIFTEEN_ID + " INTEGER DEFAULT 0," +
                WorkoutsTable.EXERCISE_SIXTEEN_ID + " INTEGER DEFAULT 0," +
                WorkoutsTable.EXERCISE_SEVENTEEN_ID + " INTEGER DEFAULT 0," +
                WorkoutsTable.EXERCISE_EIGHTEEN_ID + " INTEGER DEFAULT 0," +
                WorkoutsTable.EXERCISE_NINETEEN_ID + " INTEGER DEFAULT 0," +
                WorkoutsTable.EXERCISE_TWENTY_ID + " INTEGER DEFAULT 0"
                + ");";

        db.execSQL(SQL_CREATE_WORKOUT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "DROP TABLE IF EXISTS " + WORKOUT_TABLE_NAME );
        onCreate( db );
    }
}
