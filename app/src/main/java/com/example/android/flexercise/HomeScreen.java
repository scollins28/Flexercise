package com.example.android.flexercise;


import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;


public class HomeScreen extends FragmentActivity implements MasterListFragment.OnButtonClickListener,
        ExerciseListFragment.OnExerciseButtonClickListener, WorkoutListFragment.onWorkoutListButtonSelected,
        SettingsFragment.onSettingsButtonSelected, NewExerciseFragment.onNewExerciseButtonSelected,
        NewWorkoutFragment.onNewWorkoutButtonSelected, ExerciseDetailsFragment.onExerciseDetailsButtonSelected,
        EditExerciseFragment.onEditExerciseButtonSelected, EditWorkoutFragment.onEditWorkoutButtonSelected,
        WorkoutDetailsFragment.onWorkoutDetailsButtonSelected, WorkoutExerciseDetailsFragment.onWorkoutExerciseDetailsButtonSelected,
        Categories.OnCategorySelected, WorkoutsCategories.OnWorkoutCategorySelected {

    MasterListFragment masterListFragment = new MasterListFragment();
    ExerciseListFragment exerciseListFragment = new ExerciseListFragment();
    WorkoutListFragment workoutListFragment = new WorkoutListFragment();
    SettingsFragment settingsFragment = new SettingsFragment();
    NewExerciseFragment newExerciseFragment = new NewExerciseFragment();
    NewWorkoutFragment newWorkoutFragment = new NewWorkoutFragment();
    ExerciseDetailsFragment exerciseDetailsFragment = new ExerciseDetailsFragment();
    EditExerciseFragment editExerciseFragment = new EditExerciseFragment();
    WorkoutDetailsFragment workoutDetailsFragment = new WorkoutDetailsFragment();
    EditWorkoutFragment editWorkoutFragment = new EditWorkoutFragment();
    WorkoutsCategories workoutCategories = new WorkoutsCategories();
    WorkoutExerciseDetailsFragment workoutExerciseDetailsFragment = new WorkoutExerciseDetailsFragment();
    Categories categories = new Categories();
    FragmentManager fragmentManager;
    static ArrayList<Exercise> exercisesInSetWorkout = new ArrayList<>(  );
    static int calledPosition = 0;
    static int exerciseCategory = 0;
    static int workoutCategory = 0;
    static Exercise exercise = new Exercise(  );
    static Workout workout = new Workout ( );
    static int kgValue;
    static int lbsValue;
    static int kmValue;
    static int milesValue;
    static int timerSwitchValue;
    static int removeAdvertsValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home_screen );
        android.support.v4.app.FragmentTransaction fragmentTransaction;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add( R.id.master_list_fragment, masterListFragment ).addToBackStack( null ).commit();

        SharedPreferences sharedPref = getPreferences( Context.MODE_PRIVATE );
        kgValue = sharedPref.getInt( "kgValue", kgValue );
        lbsValue = sharedPref.getInt( "lbsValue", lbsValue );
        kmValue = sharedPref.getInt( "kmValue", kmValue );
        milesValue = sharedPref.getInt( "milesValue", milesValue );
        timerSwitchValue = sharedPref.getInt( "timerSwitchValue", timerSwitchValue );
        removeAdvertsValue = sharedPref.getInt( "removeAdvertsValue", removeAdvertsValue );
    }


    @Override
    public void onButtonSelected(int position) {
        android.support.v4.app.FragmentTransaction homeScreenButtonFragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                homeScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, categories);
                break;
            case 1:
                homeScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, workoutCategories );
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
                categories = new Categories();
                exerciseScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, categories);
                break;
            case 1:
                newExerciseFragment = new NewExerciseFragment();
                exerciseScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, newExerciseFragment );
                break;
            case 3:
                exerciseDetailsFragment = new ExerciseDetailsFragment();
                exerciseDetailsFragment.exercise = exercise;
                exerciseScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, exerciseDetailsFragment);
                break;
        }
        exerciseScreenButtonFragmentTransaction.addToBackStack( null ).commit();
    }

    @Override
    public void onWorkoutListButtonSelected (int position) {
        android.support.v4.app.FragmentTransaction workoutListScreenButtonFragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                workoutCategories = new WorkoutsCategories();
                workoutListScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, workoutCategories );
                break;
            case 1:
                newWorkoutFragment = new NewWorkoutFragment();
                workoutListScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, newWorkoutFragment);
                break;
            case 3:
                workoutDetailsFragment = new WorkoutDetailsFragment();
                workoutDetailsFragment.workout = workout;
                workoutListScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, workoutDetailsFragment);
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
    public void onEditExerciseButtonSelected (int position) {
        android.support.v4.app.FragmentTransaction editExerciseScreenButtonFragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                exerciseListFragment = new ExerciseListFragment();
                editExerciseScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, exerciseListFragment);
                break;
            case 1:
                exerciseListFragment = new ExerciseListFragment();
                editExerciseScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, exerciseListFragment);
                break;
        }
        editExerciseScreenButtonFragmentTransaction.addToBackStack( null ).commit();
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
            case 1:
                editExerciseFragment = new EditExerciseFragment();
                editExerciseFragment.exerciseToEdit = exercise;
                exerciseDetailsButtonFragmentTransaction.replace( R.id.master_list_fragment, editExerciseFragment );
        }

        exerciseDetailsButtonFragmentTransaction.addToBackStack( null ).commit();
    }

    @Override
    public void onEditWorkoutButtonSelected(int position) {
        android.support.v4.app.FragmentTransaction workoutDetailsButtonFragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                workoutDetailsFragment = new WorkoutDetailsFragment();
                workoutDetailsFragment.workout = workout;
                workoutDetailsButtonFragmentTransaction.replace( R.id.master_list_fragment, workoutDetailsFragment);
                break;
            case 1:
                workoutDetailsFragment = new WorkoutDetailsFragment();
                workoutDetailsFragment.workout = workout;
                workoutDetailsButtonFragmentTransaction.replace( R.id.master_list_fragment, workoutDetailsFragment);
                break;
        }

        workoutDetailsButtonFragmentTransaction.addToBackStack( null ).commit();
    }


    @Override
    public void onWorkoutDetailsButtonSelected (int position) {
        android.support.v4.app.FragmentTransaction workoutDetailsButtonFragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                workoutListFragment = new WorkoutListFragment ();
                workoutDetailsButtonFragmentTransaction.replace( R.id.master_list_fragment, workoutListFragment);
                break;
            case 1:
                editWorkoutFragment = new EditWorkoutFragment ();
                editWorkoutFragment.workoutToEdit = workout;
                workoutDetailsButtonFragmentTransaction.replace( R.id.master_list_fragment, editWorkoutFragment );
                break;
            case 3:
                workoutExerciseDetailsFragment = new WorkoutExerciseDetailsFragment();
                workoutExerciseDetailsFragment.exercise = exercise;
                workoutDetailsButtonFragmentTransaction.replace( R.id.master_list_fragment, workoutExerciseDetailsFragment );
        }

        workoutDetailsButtonFragmentTransaction.addToBackStack( null ).commit();
    }

    @Override
    public void onWorkoutExerciseDetailsButtonSelected(int position) {
        android.support.v4.app.FragmentTransaction workoutExerciseDetailsButtonFragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                workoutDetailsFragment = new WorkoutDetailsFragment();
                workoutDetailsFragment.workout = workout;
                workoutExerciseDetailsButtonFragmentTransaction.replace( R.id.master_list_fragment, workoutDetailsFragment );
                break;
            case 1:
                workoutDetailsFragment = new WorkoutDetailsFragment();
                workoutDetailsFragment.workout = workout;
                workoutExerciseDetailsButtonFragmentTransaction.replace( R.id.master_list_fragment, workoutDetailsFragment );
                break;
            case 2:
                workoutExerciseDetailsFragment = new WorkoutExerciseDetailsFragment();
                workoutExerciseDetailsFragment.exercise = exercisesInSetWorkout.get( calledPosition );
                workoutExerciseDetailsButtonFragmentTransaction.replace( R.id.master_list_fragment, workoutExerciseDetailsFragment );
                break;
            case 3:
                workoutExerciseDetailsFragment = new WorkoutExerciseDetailsFragment();
                workoutExerciseDetailsFragment.exercise = exercisesInSetWorkout.get( calledPosition );
                workoutExerciseDetailsButtonFragmentTransaction.replace( R.id.master_list_fragment, workoutExerciseDetailsFragment );
                break;
        }
        workoutExerciseDetailsButtonFragmentTransaction.addToBackStack( null ).commit();
    }

    @Override
    public void onCategorySelected(int position) {
        android.support.v4.app.FragmentTransaction categoriesFragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                masterListFragment = new MasterListFragment();
                categoriesFragmentTransaction.replace( R.id.master_list_fragment, masterListFragment);
                break;
            case 1:
                exerciseListFragment = new ExerciseListFragment();
                categoriesFragmentTransaction.replace( R.id.master_list_fragment, exerciseListFragment);
                break;
        }
        categoriesFragmentTransaction.addToBackStack( null ).commit();
    }

    @Override
    public void onWorkoutCategorySelected(int position) {
        android.support.v4.app.FragmentTransaction workoutCategoriesFragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                masterListFragment = new MasterListFragment();
                workoutCategoriesFragmentTransaction.replace( R.id.master_list_fragment, masterListFragment);
                break;
            case 1:
                workoutListFragment = new WorkoutListFragment();
                workoutCategoriesFragmentTransaction.replace( R.id.master_list_fragment, workoutListFragment);
                break;
        }
        workoutCategoriesFragmentTransaction.addToBackStack( null ).commit();
    }
}

