package com.example.android.flexercise.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.flexercise.Database.ExerciseContract.ExerciseTable;
import com.example.android.flexercise.Exercise;


public class ExerciseDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "exercises.db";
    public static final int VERSION_NUMBER = 2;

    public ExerciseDbHelper (Context context){
        super (context, DATABASE_NAME, null, VERSION_NUMBER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_EXERCISE_TABLE = "CREATE TABLE " +
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
                ExerciseTable.NOTES + " TEXT, " +
                ExerciseTable.WORKOUT_ONE + " INTEGER DEFAULT 0," +
                ExerciseTable.WORKOUT_TWO + " INTEGER DEFAULT 0," +
                ExerciseTable.WORKOUT_THREE + " INTEGER DEFAULT 0," +
                ExerciseTable.WORKOUT_FOUR + " INTEGER DEFAULT 0," +
                ExerciseTable.WORKOUT_FIVE + " INTEGER DEFAULT 0," +
                ExerciseTable.WORKOUT_SIX + " INTEGER DEFAULT 0," +
                ExerciseTable.WORKOUT_SEVEN + " INTEGER DEFAULT 0," +
                ExerciseTable.WORKOUT_EIGHT + " INTEGER DEFAULT 0," +
                ExerciseTable.WORKOUT_NINE + " INTEGER DEFAULT 0," +
                ExerciseTable.WORKOUT_TEN + " INTEGER DEFAULT 0," +
                ExerciseTable.WORKOUT_ELEVEN + " INTEGER DEFAULT 0," +
                ExerciseTable.WORKOUT_TWELVE + " INTEGER DEFAULT 0," +
                ExerciseTable.WORKOUT_THIRTEEN + " INTEGER DEFAULT 0," +
                ExerciseTable.WORKOUT_FOURTEEN + " INTEGER DEFAULT 0," +
                ExerciseTable.WORKOUT_FIFTEEN + " INTEGER DEFAULT 0," +
                ExerciseTable.WORKOUT_SIXTEEN + " INTEGER DEFAULT 0," +
                ExerciseTable.WORKOUT_SEVENTEEN + " INTEGER DEFAULT 0," +
                ExerciseTable.WORKOUT_EIGHTEEN + " INTEGER DEFAULT 0," +
                ExerciseTable.WORKOUT_NINETEEN + " INTEGER DEFAULT 0," +
                ExerciseTable.WORKOUT_TWENTY + " INTEGER DEFAULT 0"
                + ");";

        db.execSQL(SQL_CREATE_EXERCISE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "DROP TABLE IF EXISTS " + ExerciseTable.TABLE_NAME );
        onCreate( db );
    }
}
