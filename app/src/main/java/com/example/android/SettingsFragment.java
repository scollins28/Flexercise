package com.example.android;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import com.example.android.Widget.WorkoutWidget;
import com.example.android.free.R;
import static android.view.View.GONE;
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
    TextView premiumText;
    int kgValue;
    int lbsValue;
    int kmValue;
    int milesValue;
    Button eraseAllDataButton;
    Button removeAdvertsButton;
    int removeAdvertsValue;
    Switch timerSwitch;
    int timerSwitchValue;
    Toolbar mToolbar;
    ScrollView scrollView;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        SharedPreferences sharedPref = getActivity().getPreferences( Context.MODE_PRIVATE );
        kgValue = sharedPref.getInt( "kgValue", kgValue );
        lbsValue = sharedPref.getInt( "lbsValue", lbsValue );
        kmValue = sharedPref.getInt( "kmValue", kmValue );
        milesValue = sharedPref.getInt( "milesValue", milesValue );
        timerSwitchValue = sharedPref.getInt( "timerSwitchValue", timerSwitchValue );
        removeAdvertsValue = sharedPref.getInt( "removeAdvertsValue", removeAdvertsValue );

        rootView = inflater.inflate( R.layout.settings_fragment, container, false );
        mToolbar = rootView.findViewById( R.id.toolbar );
        kgCheckbox = rootView.findViewById( R.id.weight_unit_kg_checkbox );
        lbsCheckbox = rootView.findViewById( R.id.weight_unit_lbs_checkbox );
        kmCheckbox = rootView.findViewById( R.id.distance_unit_km_checkbox );
        milesCheckbox = rootView.findViewById( R.id.distance_unit_miles_checkbox );
        eraseAllDataButton = rootView.findViewById( R.id.erase_all_button );
        removeAdvertsButton = rootView.findViewById( R.id.remove_ads_button );
        timerSwitch = rootView.findViewById( R.id.enable_timer_switch );
        premiumText = rootView.findViewById( R.id.updrade_text );
        scrollView = rootView.findViewById( R.id.settings_sv );
        if (removeAdvertsValue==1) {
            premiumText.setVisibility( GONE );
            timerSwitch.setVisibility( View.VISIBLE );
            removeAdvertsButton.setClickable( false );
            removeAdvertsButton.setBackgroundColor( getResources().getColor( R.color.colorHomeButton ) );
            if (timerSwitchValue == 1) {
                timerSwitch.setChecked( true );
            } else {
                timerSwitch.setChecked( false );
            }
        }
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
                else if (v == removeAdvertsButton){
                    HomeScreen.removeAdvertsValue = 1;
                    removeAdvertsValue = 1;
                    timerSwitch.setChecked( true );
                    timerSwitchValue=1;
                    HomeScreen.timerSwitchValue = 1;
                    timerSwitch.setVisibility( View.VISIBLE );
                    premiumText.setVisibility( GONE );
                    removeAdvertsButton.setClickable( false );
                    removeAdvertsButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton));
                    HomeScreen.checkDisplayBanner(rootView, HomeScreen.removeAdvertsValue);
                }
                else if (v == timerSwitch){
                    if (timerSwitchValue == 0) {
                        HomeScreen.timerSwitchValue = 1;
                        timerSwitchValue =1;
                    } else {
                        HomeScreen.timerSwitchValue = 0;
                        timerSwitchValue = 0;
                    }

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
        removeAdvertsButton.setOnClickListener( listener );
        timerSwitch.setOnClickListener( listener );

        HomeScreen.checkDisplayBanner(rootView, HomeScreen.removeAdvertsValue);

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
        if (HomeScreen.exercisesFromLoader!=null){
        int rowsUpdated;
        for (int x = 0; x < HomeScreen.exercisesFromLoader.size(); x++) {
            int idOfExercise = HomeScreen.exercisesFromLoader.get( x ).getID();
            int exerciseMaxWeight = HomeScreen.exercisesFromLoader.get( x ).getMaxWeight();
            int exerciseStartingWeight = HomeScreen.exercisesFromLoader.get( x ).getStartingWeight();
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
    }

    public void updateCardioExerciseValues() {
        if (HomeScreen.exercisesFromLoader!=null) {
            int cardioRowsUpdated;
            for (int y = 0; y < HomeScreen.exercisesFromLoader.size(); y++) {
                int idOfExercise = HomeScreen.exercisesFromLoader.get( y ).getID();
                int distance = HomeScreen.exercisesFromLoader.get( y ).getDistance();
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
    }

    public void deleteAll (){
        int exercisesDeleted;
        int workoutsDeleted;
        if (HomeScreen.exercisesFromLoader!=null) {
            for (int x = 0; x < HomeScreen.exercisesFromLoader.size(); x++) {
                int idOfExercise = HomeScreen.exercisesFromLoader.get(x).getID();
                String exerciseIdString = String.valueOf( idOfExercise );
                Uri deleteExercisesUri = CONTENT_URI.buildUpon().appendPath( exerciseIdString ).build();
                exercisesDeleted = getActivity().getContentResolver().delete( deleteExercisesUri, null, null );
            }
        }
        if (HomeScreen.workoutsFromLoader!=null) {
            for (int x = 0; x < HomeScreen.workoutsFromLoader.size(); x++) {
                int idOfWorkout = HomeScreen.workoutsFromLoader.get( x ).getID();
                String workoutsIdString = String.valueOf( idOfWorkout );
                Uri deleteWorkoutsUri = WORKOUT_CONTENT_URI.buildUpon().appendPath( workoutsIdString ).build();
                workoutsDeleted = getActivity().getContentResolver().delete( deleteWorkoutsUri, null, null );
            }
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored( savedInstanceState );
        if (savedInstanceState!=null) {
            if (savedInstanceState.containsKey( "scrollViewSettings" )) {
                scrollView.setScrollY( savedInstanceState.getInt( "scrollViewSettings" ) );
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState( outState );
        outState.putInt( "scrollViewSettings", scrollView.getScrollY() );
    }
}
