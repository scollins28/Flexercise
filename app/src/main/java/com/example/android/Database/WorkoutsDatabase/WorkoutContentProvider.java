package com.example.android.Database.WorkoutsDatabase;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import static android.content.UriMatcher.NO_MATCH;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.WORKOUT_TABLE_NAME;

public class WorkoutContentProvider extends ContentProvider{

    private WorkoutDbHelper mWorkoutDbHelper;
    public static final int WORKOUTS = 100;
    public static final int WORKOUT_WITH_ID = 101;
    public static final int WORKOUT_WITH_NAME = 102;
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    public SQLiteDatabase db;

    public static UriMatcher buildUriMatcher(){
        UriMatcher uriMatcher = new UriMatcher( NO_MATCH );
        uriMatcher.addURI( WorkoutContract.AUTHORITY, WorkoutContract.PATH_WORKOUTS, WORKOUTS);
        uriMatcher.addURI( WorkoutContract.AUTHORITY, WorkoutContract.PATH_WORKOUTS+ "/#", WORKOUT_WITH_ID);
        uriMatcher.addURI( WorkoutContract.AUTHORITY, WorkoutContract.PATH_WORKOUTS+ "/*", WORKOUT_WITH_NAME);
        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        mWorkoutDbHelper = new WorkoutDbHelper(context);
        db = mWorkoutDbHelper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase db = mWorkoutDbHelper.getReadableDatabase();
        int match = sUriMatcher.match(uri);
        Cursor cursor;

        switch (match) {
            case WORKOUTS:
                cursor =  db.query(WORKOUT_TABLE_NAME, null, null, null, null, null, null);
                break;
            case WORKOUT_WITH_ID:
                String id = uri.getPathSegments().get( 1 );
                String mSelection = "_id=?";
                String [] mSelectionArgs = new String[]{id};
                cursor = db.query( WORKOUT_TABLE_NAME, projection, mSelection, mSelectionArgs , null, null, sortOrder );
                break;
            case WORKOUT_WITH_NAME:
                String altID = uri.getPathSegments().get( 1 );
                String mAltSelection = "WORKOUT_NAME=?";
                String [] mAltSelectionArgs = new String[]{altID};
                cursor = db.query( WORKOUT_TABLE_NAME, projection, mAltSelection, mAltSelectionArgs , null, null, sortOrder );
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {


        int match = sUriMatcher.match(uri);
        long id = 0;
        Uri returnUri;

        switch (match) {
            case WORKOUTS:
                id = db.insert( WORKOUT_TABLE_NAME, null, values);
                if ( id < 0 ) {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                } else {
                    returnUri = ContentUris.withAppendedId( WorkoutContract.WorkoutsTable.WORKOUT_CONTENT_URI, id);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        return returnUri;

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mWorkoutDbHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);

        int workoutsDeleted;

        switch (match) {
            case WORKOUT_WITH_ID:
                String id = uri.getPathSegments().get(1);
                String newSelection = "_ID=?";
                String [] newSelectionArgs = new String[]{id};
                workoutsDeleted = db.delete( WORKOUT_TABLE_NAME, newSelection, newSelectionArgs);
                Log.e( "WHICH IS ", String.valueOf( id ) );
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        Log.e("DELETED", String.valueOf(workoutsDeleted));
        return workoutsDeleted;

    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mWorkoutDbHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);

        int workoutsUpdated;

        switch (match) {
            case WORKOUT_WITH_ID:
                String id = uri.getPathSegments().get(1);
                String newSelection = "_ID=?";
                String [] newSelectionArgs = new String[]{id};
                workoutsUpdated = db.update( WORKOUT_TABLE_NAME, values, newSelection, newSelectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        return workoutsUpdated;
    }

}
