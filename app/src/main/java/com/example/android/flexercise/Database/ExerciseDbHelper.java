package com.example.android.flexercise.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.flexercise.Database.ExerciseContract.ExerciseTable;


public class ExerciseDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "exercises.db";
    public static final int VERSION_NUMBER = 1;

    public ExerciseDbHelper (Context context){
        super (context, DATABASE_NAME, null, VERSION_NUMBER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_RECIPE_TABLE = "CREATE TABLE " +
                ExerciseTable.TABLE_NAME + "(" +
                ExerciseTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ExerciseTable.EXERCISE_NAME + " TEXT," +
                ExerciseTable.CATEGORY_ONE_STATE + " INTEGER DEFAULT 0," +
                ExerciseTable.CATEGORY_TWO_STATE + " INTEGER DEFAULT 0," +
                ExerciseTable.CATEGORY_THREE_STATE + " INTEGER DEFAULT 0," +
                ExerciseTable.CATEGORY_FOUR_STATE + " INTEGER DEFAULT 0," +
                ExerciseTable.CATEGORY_FIVE_STATE + " INTEGER DEFAULT 0," +
                ExerciseTable.CATEGORY_SIX_STATE + " INTEGER DEFAULT 0," +
                ExerciseTable.MEDIA_SOURCE + " TEXT," +
                ExerciseTable.NUMBER_OF_SETS + " INTEGER DEFAULT 0," +
                ExerciseTable.MAX_WEIGHT + " INTEGER DEFAULT 0," +
                ExerciseTable.STARTING_WEIGHT + " INTEGER DEFAULT 0," +
                ExerciseTable.ADD_TO_WORKOUT + " TEXT," +
                ExerciseTable.NOTES + " TEXT"
                + ");";

        db.execSQL(SQL_CREATE_RECIPE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "DROP TABLE IF EXISTS " + ExerciseTable.TABLE_NAME );
        onCreate( db );
    }
}
