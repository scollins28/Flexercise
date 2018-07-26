package com.example.android;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Switch;

import com.example.android.Widget.WorkoutWidget;
import com.example.android.HomeScreen;
import com.example.android.free.R;

import static android.provider.BaseColumns._ID;
import static com.example.android.Database.ExerciseContract.ExerciseTable.CONTENT_URI;
import static com.example.android.Database.ExerciseContract.ExerciseTable.DISTANCE;
import static com.example.android.Database.ExerciseContract.ExerciseTable.MAX_WEIGHT;
import static com.example.android.Database.ExerciseContract.ExerciseTable.STARTING_WEIGHT;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.WORKOUT_CONTENT_URI;

public class SettingsFragment extends android.support.v4.app.Fragment {

    View rootView;
    ImageButton settingsBackButton;
    CheckBox kgCheckbox;
    CheckBox lbsCheckbox;
    CheckBox kmCheckbox;
    CheckBox milesCheckbox;
    int kgValue;
    int lbsValue;
    int kmValue;
    int milesValue;

    Button eraseAllDataButton;
    Button removeAdvertsButton;
    int removeAdvertsValue;
    Switch timerSwitch;
    int timerSwitchValue;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        SharedPreferences sharedPref = getActivity().getPreferences( Context.MODE_PRIVATE );
        kgValue = sharedPref.getInt( "kgValue", kgValue );
        lbsValue = sharedPref.getInt( "lbsValue", lbsValue );
        kmValue = sharedPref.getInt( "kmValue", kmValue );
        milesValue = sharedPref.getInt( "milesValue", milesValue );
        timerSwitchValue = sharedPref.getInt( "timerSwitchValue", timerSwitchValue );
        removeAdvertsValue = sharedPref.getInt( "removeAdvertsValue", removeAdvertsValue );

        rootView = inflater.inflate( R.layout.settings_fragment, container, false );
        kgCheckbox = rootView.findViewById( R.id.weight_unit_kg_checkbox );
        lbsCheckbox = rootView.findViewById( R.id.weight_unit_lbs_checkbox );
        kmCheckbox = rootView.findViewById( R.id.distance_unit_km_checkbox );
        milesCheckbox = rootView.findViewById( R.id.distance_unit_miles_checkbox );
        eraseAllDataButton = rootView.findViewById( R.id.erase_all_button );
        removeAdvertsButton = rootView.findViewById( R.id.remove_ads_button );
        timerSwitch = rootView.findViewById( R.id.enable_timer_switch );

        checkWeightAndDistanceCheckboxs();


        settingsBackButton = rootView.findViewById( R.id.settings_back_button );

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position;
                if (v == settingsBackButton) {
                    position = 0;
                    SharedPreferences sharedPref = getActivity().getPreferences( Context.MODE_PRIVATE );
                    SharedPreferences.Editor editor = sharedPref.edit();
                    if (sharedPref!=null) {
                        editor.remove( "kgValue" );
                        editor.remove( "lbsValue" );
                        editor.remove( "milesValue" );
                        editor.remove( "kmValue" );
                        editor.remove( "timerSwitchValue" );
                        editor.remove( "removeAdvertsValue" );
                    }
                    editor.putInt( "kgValue", kgValue );
                    editor.putInt( "lbsValue", lbsValue );
                    editor.putInt( "kmValue", kmValue );
                    editor.putInt( "milesValue", milesValue );
                    editor.putInt( "timerSwitchValue", timerSwitchValue );
                    editor.putInt( "removeAdvertsValue", removeAdvertsValue );
                    editor.putInt("widgetCreated", HomeScreen.widgetCreated);
                    editor.commit();
                    mCallback.onSettingsButtonSelected( position );
                } else if (v == kgCheckbox) {
                    kgValue = 1;
                    lbsValue = 0;
                    updateExerciseValues();
                    checkWeightAndDistanceCheckboxs();
                    WorkoutWidget.widgetUnits();
                } else if (v == lbsCheckbox) {
                    kgValue = 0;
                    lbsValue = 1;
                    updateExerciseValues();
                    checkWeightAndDistanceCheckboxs();
                    WorkoutWidget.widgetUnits();
                } else if (v == kmCheckbox) {
                    kmValue = 1;
                    milesValue = 0;
                    updateCardioExerciseValues();
                    checkWeightAndDistanceCheckboxs();
                } else if (v == milesCheckbox) {
                    kmValue = 0;
                    milesValue = 1;
                    updateCardioExerciseValues();
                    checkWeightAndDistanceCheckboxs();
                }  else if (v == eraseAllDataButton){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setCancelable(true);
                    builder.setTitle(R.string.delete_all_title);
                    builder.setMessage(R.string.delete_all_message);
                    builder.setPositiveButton(R.string.confirm,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    deleteAll();
                                }
                            });
                    builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        };

        //Attaching the click listener to the buttons

        settingsBackButton.setOnClickListener( listener );
        kgCheckbox.setOnClickListener( listener );
        lbsCheckbox.setOnClickListener( listener );
        kmCheckbox.setOnClickListener( listener );
        milesCheckbox.setOnClickListener( listener );
        eraseAllDataButton.setOnClickListener( listener );

        return rootView;
    }

    SettingsFragment.onSettingsButtonSelected mCallback;


    //Interface for the click listener to send which button has been pushed to the home screen, and activate the fragment transaction
    public interface onSettingsButtonSelected {
        void onSettingsButtonSelected(int position);
    }

    //Attaching the interface to the MasterListFragment
    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        try {
            mCallback = (SettingsFragment.onSettingsButtonSelected) context;
        } catch (ClassCastException e) {
            throw new ClassCastException( context.toString() + "must implement click listener" );
        }
    }

    public void checkWeightAndDistanceCheckboxs() {
        if (kgValue == 1) {
            kgCheckbox.setChecked( true );
            lbsCheckbox.setChecked( false );
            lbsValue = 0;
        } else if (lbsValue == 1) {
            kgCheckbox.setChecked( false );
            lbsCheckbox.setChecked( true );
            kgValue = 0;
        }

        if (kmValue == 1) {
            kmCheckbox.setChecked( true );
            milesCheckbox.setChecked( false );
            milesValue = 0;
        } else if (milesValue == 1) {
            kmCheckbox.setChecked( false );
            milesCheckbox.setChecked( true );
            kmValue = 0;
        }

    }

    public void updateExerciseValues() {
        Cursor cursor = getActivity().getContentResolver().query( CONTENT_URI, null, null, null, null );
        cursor.moveToFirst();
        int rowsUpdated;
        for (int x = 0; x < cursor.getCount(); x++) {
            cursor.moveToPosition( x );
            int idOfExercise = cursor.getInt( cursor.getColumnIndex( _ID ) );
            int exerciseMaxWeight = cursor.getInt( cursor.getColumnIndex( MAX_WEIGHT ) );
            int exerciseStartingWeight = cursor.getInt( cursor.getColumnIndex( STARTING_WEIGHT ) );
            if (kgValue == 1) {
                double exerciseMaxWeightDouble = (exerciseMaxWeight / 2.2046);
                exerciseMaxWeight = (int) exerciseMaxWeightDouble;
                double exerciseStartingWeightDouble = (exerciseStartingWeight / 2.2046);
                exerciseStartingWeight = (int) exerciseStartingWeightDouble;
            } else {
                double exerciseMaxWeightDouble = (exerciseMaxWeight * 2.2046);
                exerciseMaxWeight = (int) exerciseMaxWeightDouble;
                double exerciseStartingWeightDouble = (exerciseStartingWeight * 2.2046);
                exerciseStartingWeight = (int) exerciseStartingWeightDouble;
            }
            ContentValues updateValues = new ContentValues();
            updateValues.put( MAX_WEIGHT, exerciseMaxWeight );
            updateValues.put( STARTING_WEIGHT, exerciseStartingWeight );
            String updateIDstring = String.valueOf( idOfExercise );
            Uri updateUri = CONTENT_URI.buildUpon().appendPath( updateIDstring ).build();
            rowsUpdated = getActivity().getContentResolver().update( updateUri, updateValues, null, null );
            Log.e( "updates", String.valueOf( rowsUpdated ) );
        }
    }

    public void updateCardioExerciseValues() {
        Cursor cardioCursor = getActivity().getContentResolver().query( CONTENT_URI, null, null, null, null );
        cardioCursor.moveToFirst();
        int cardioRowsUpdated;
        for (int y = 0; y < cardioCursor.getCount(); y++) {
            cardioCursor.moveToPosition( y );
            int idOfExercise = cardioCursor.getInt( cardioCursor.getColumnIndex( _ID ) );
            int distance = cardioCursor.getInt( cardioCursor.getColumnIndex( DISTANCE ) );
            if (kmValue == 0) {
                double exerciseDistanceDouble = (distance / 1.609344);
                distance = (int) exerciseDistanceDouble;
            } else {
                double exerciseDistanceDouble = (distance * 1.609344);
                distance = (int) exerciseDistanceDouble;
            }
            ContentValues updateValues = new ContentValues();
            updateValues.put( DISTANCE, distance );
            String updateIDstring = String.valueOf( idOfExercise );
            Uri updateUri = CONTENT_URI.buildUpon().appendPath( updateIDstring ).build();
            cardioRowsUpdated = getActivity().getContentResolver().update( updateUri, updateValues, null, null );
            Log.e( "updates", String.valueOf( cardioRowsUpdated ) );
        }
    }

    public void deleteAll (){
        int exercisesDeleted;
        int workoutsDeleted;
        Cursor cursor = getActivity().getContentResolver().query( CONTENT_URI, null, null, null, null );
        cursor.moveToFirst();
        for (int x = 0; x < cursor.getCount(); x++) {
            cursor.moveToPosition( x );
            int idOfExercise = cursor.getInt( cursor.getColumnIndex( _ID ) );
            String exerciseIdString = String.valueOf( idOfExercise );
            Uri deleteExercisesUri = CONTENT_URI.buildUpon().appendPath( exerciseIdString ).build();
            exercisesDeleted = getActivity().getContentResolver().delete( deleteExercisesUri, null, null );
        }
        Cursor cursorTwo = getActivity().getContentResolver().query( WORKOUT_CONTENT_URI, null, null, null, null );
        cursorTwo.moveToFirst();
        for (int x = 0; x < cursorTwo.getCount(); x++) {
            cursorTwo.moveToPosition( x );
            int idOfWorkout = cursorTwo.getInt( cursorTwo.getColumnIndex( _ID ) );
            String workoutsIdString = String.valueOf( idOfWorkout );
            Uri deleteWorkoutsUri = WORKOUT_CONTENT_URI.buildUpon().appendPath( workoutsIdString ).build();
            workoutsDeleted = getActivity().getContentResolver().delete( deleteWorkoutsUri, null, null );
        }
        cursor.close();
        cursorTwo.close();
    }
}
