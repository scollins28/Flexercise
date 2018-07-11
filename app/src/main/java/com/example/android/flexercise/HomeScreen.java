package com.example.android.flexercise;


import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.LoaderManager;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;



public class HomeScreen extends FragmentActivity implements MasterListFragment.OnButtonClickListener,
        ExerciseListFragment.OnExerciseButtonClickListener, WorkoutListFragment.onWorkoutListButtonSelected,
        SettingsFragment.onSettingsButtonSelected, NewExerciseFragment.onNewExerciseButtonSelected,
        NewWorkoutFragment.onNewWorkoutButtonSelected, ExerciseDetailsFragment.onExerciseDetailsButtonSelected {

    MasterListFragment masterListFragment = new MasterListFragment();
    ExerciseListFragment exerciseListFragment = new ExerciseListFragment();
    WorkoutListFragment workoutListFragment = new WorkoutListFragment();
    SettingsFragment settingsFragment = new SettingsFragment();
    NewExerciseFragment newExerciseFragment = new NewExerciseFragment();
    NewWorkoutFragment newWorkoutFragment = new NewWorkoutFragment();
    ExerciseDetailsFragment exerciseDetailsFragment = new ExerciseDetailsFragment();
    FragmentManager fragmentManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home_screen );
        android.support.v4.app.FragmentTransaction fragmentTransaction;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add( R.id.master_list_fragment, masterListFragment ).addToBackStack( null ).commit();
    }


    @Override
    public void onButtonSelected(int position) {
        android.support.v4.app.FragmentTransaction homeScreenButtonFragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                homeScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, exerciseListFragment );
                break;
            case 1:
                homeScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, workoutListFragment );
                break;
            case 2:
                homeScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, settingsFragment );
                break;
        }
        homeScreenButtonFragmentTransaction.addToBackStack( null ).commit();
    }

    @Override
    public void onExerciseButtonSelected(int position) {
        android.support.v4.app.FragmentTransaction exerciseScreenButtonFragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                masterListFragment = new MasterListFragment();
                exerciseScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, masterListFragment );
                break;
            case 1:
                exerciseScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, newExerciseFragment );
                break;
        }
        exerciseScreenButtonFragmentTransaction.addToBackStack( null ).commit();
    }

    @Override
    public void onWorkoutListButtonSelected (int position) {
        android.support.v4.app.FragmentTransaction workoutListScreenButtonFragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                masterListFragment = new MasterListFragment();
                workoutListScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, masterListFragment );
                break;
            case 1:
                newWorkoutFragment = new NewWorkoutFragment();
                workoutListScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, newWorkoutFragment);
                break;
        }
        workoutListScreenButtonFragmentTransaction.addToBackStack( null ).commit();
    }

    @Override
    public void onSettingsButtonSelected (int position) {
        android.support.v4.app.FragmentTransaction settingsScreenButtonFragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                masterListFragment = new MasterListFragment();
                settingsScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, masterListFragment );
                break;
        }
        settingsScreenButtonFragmentTransaction.addToBackStack( null ).commit();
    }

    @Override
    public void onNewExerciseButtonSelected (int position) {
        android.support.v4.app.FragmentTransaction newExerciseScreenButtonFragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                exerciseListFragment = new ExerciseListFragment();
                newExerciseScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, exerciseListFragment);
                break;
            case 1:
                exerciseListFragment = new ExerciseListFragment();
                newExerciseScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, exerciseListFragment);
                break;
        }
        newExerciseScreenButtonFragmentTransaction.addToBackStack( null ).commit();
    }

    @Override
    public void onNewWorkoutButtonSelected (int position) {
        android.support.v4.app.FragmentTransaction newWorkoutScreenButtonFragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                workoutListFragment = new WorkoutListFragment();
                newWorkoutScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, workoutListFragment);
                break;
            case 1:
                workoutListFragment = new WorkoutListFragment();
                newWorkoutScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, workoutListFragment);
                break;
        }
        newWorkoutScreenButtonFragmentTransaction.addToBackStack( null ).commit();
    }


    @Override
    public void onExerciseDetailsButtonSelected (int position) {
        android.support.v4.app.FragmentTransaction exerciseDetailsButtonFragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                exerciseListFragment = new ExerciseListFragment();
                exerciseDetailsButtonFragmentTransaction.replace( R.id.master_list_fragment, exerciseListFragment);
                break;
            }
        exerciseDetailsButtonFragmentTransaction.addToBackStack( null ).commit();
    }

}

