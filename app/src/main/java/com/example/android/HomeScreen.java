package com.example.android;

import android.app.FragmentTransaction;
import android.app.LoaderManager;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.LinearLayout;

import com.example.android.Database.ExerciseContract;
import com.example.android.Database.WorkoutsDatabase.WorkoutContract;
import com.example.android.InternetBasedActivity.Analytics;
import com.example.android.InternetBasedActivity.NewsLoader;
import com.example.android.InternetBasedActivity.YouTubeFragment;
import com.example.android.Widget.WidgetWorkoutDetails;
import com.example.android.Widget.WorkoutWidget;
import com.example.android.free.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.android.Database.ExerciseContract.ExerciseTable.CONTENT_URI;
import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.WORKOUT_CONTENT_URI;


public class HomeScreen extends FragmentActivity implements MasterListFragment.OnButtonClickListener,
        ExerciseListFragment.OnExerciseButtonClickListener, WorkoutListFragment.onWorkoutListButtonSelected,
        SettingsFragment.onSettingsButtonSelected, NewExerciseFragment.onNewExerciseButtonSelected,
        NewWorkoutFragment.onNewWorkoutButtonSelected, ExerciseDetailsFragment.onExerciseDetailsButtonSelected,
        EditExerciseFragment.onEditExerciseButtonSelected, EditWorkoutFragment.onEditWorkoutButtonSelected,
        WorkoutDetailsFragment.onWorkoutDetailsButtonSelected, WorkoutExerciseDetailsFragment.onWorkoutExerciseDetailsButtonSelected,
        Categories.OnCategorySelected, WorkoutsCategories.OnWorkoutCategorySelected, NewsFeed.onNewsListButtonSelected,
        LoaderManager.LoaderCallbacks<ArrayList<News>> {

    private static final int NEWS_LOADER_ID = 0;
    android.support.v4.app.FragmentTransaction fragmentTransaction;
    MasterListFragment masterListFragment = new MasterListFragment();
    ExerciseListFragment exerciseListFragment = new ExerciseListFragment();
    WorkoutListFragment workoutListFragment = new WorkoutListFragment();
    SettingsFragment settingsFragment = new SettingsFragment();
    NewExerciseFragment newExerciseFragment = new NewExerciseFragment();
    NewWorkoutFragment newWorkoutFragment = new NewWorkoutFragment();
    ExerciseDetailsFragment exerciseDetailsFragment = new ExerciseDetailsFragment();
    EditExerciseFragment editExerciseFragment = new EditExerciseFragment();
    WorkoutDetailsFragment workoutDetailsFragment = new WorkoutDetailsFragment();
    public static YouTubeFragment youTubeFragment = new YouTubeFragment();
    EditWorkoutFragment editWorkoutFragment = new EditWorkoutFragment();
    WorkoutsCategories workoutCategories = new WorkoutsCategories();
    WorkoutExerciseDetailsFragment workoutExerciseDetailsFragment = new WorkoutExerciseDetailsFragment();
    NewsFeed newsFeedFragment = new NewsFeed();
    Categories categories = new Categories();
    private NetworkInfo isNetworkActive;
    int loaderChecker = 0;
    public static String currentMediaSource;
    public static int widgetWorkoutId = 777777;
    static int widgetExerciseId;
    public static int widgetToInflate = 666666;
    public static int widgetCreated = 0;
    static int listStateOne = 0;
    public static int listStateTwo = 0;
    public static int listStateThree = 0;
    Context mContext;
    public static Chronometer chronometer;
    private static boolean running = false;
    public static int warningcolor;
    public static int normalcolor;
    public static long mTime;
    public static ArrayList<WidgetWorkoutDetails> widgetWorkoutDetails;
    public static ArrayList<News> news = new ArrayList<>(  );
    public static ArrayList<Workout> workoutsFromLoader;
    public static ArrayList<Exercise> exercisesFromLoader;



    FragmentManager fragmentManager;
    static ArrayList<Exercise> exercisesInSetWorkout = new ArrayList<>();
    static int calledPosition = 0;
    public static int exerciseCategory = 0;
    static int workoutCategory = 0;
    public static Exercise exercise = new Exercise();
    public static Workout workout = new Workout();
    public static int kgValue = 1;
    static int lbsValue = 0;
    public static int kmValue = 1;
    static int milesValue = 0;
    public static int timerSwitchValue = 0;
    static int removeAdvertsValue = 0;
    public static FragmentTransaction widgetTransaction;


    private static GoogleAnalytics sAnalytics;
    private static Tracker mTracker;
    static SharedPreferences sharedPref;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState( outState );
        Log.e( "saving state", "NOW" );
        outState.putParcelableArrayList( "widgetWorkout", widgetWorkoutDetails );
        outState.putInt( "widgetWorkoutId", widgetWorkoutId );
        outState.putString( "currentMediaSource", currentMediaSource );
        outState.putInt( "listStateOne", listStateOne );
        outState.putInt( "listStateTwo", listStateTwo );
        outState.putInt( "listStateThree", listStateThree );
        outState.putParcelableArrayList( "exercisesInSetWorkout", exercisesInSetWorkout );
        outState.putInt( "calledPostion", calledPosition );
        outState.putInt( "exerciseCategory", exerciseCategory );
        outState.putInt( "workoutCategory", workoutCategory );
        ArrayList<Exercise> exerciseTempHolder = new ArrayList<>();
        exerciseTempHolder.add( exercise );
        outState.putParcelableArrayList( "exercise", exerciseTempHolder );
        ArrayList<Workout> workoutTempHolder = new ArrayList<>();
        workoutTempHolder.add( workout );
        outState.putParcelableArrayList( "workout", workoutTempHolder );
        outState.putInt( "kgValue", kgValue );
        outState.putInt( "lbsValue", lbsValue );
        outState.putInt( "kmValue", kmValue );
        outState.putInt( "milesValue", milesValue );
        outState.putInt( "timerSwitchValue", timerSwitchValue );
        outState.putInt( "removeAdvertsValue", removeAdvertsValue );
    }

    @Override
    protected void onDestroy() {
        widgetWorkoutId = 0;
        widgetWorkoutDetails = null;
        Intent intent = new Intent( mContext, WorkoutWidget.class );
        intent.setAction( "android.appwidget.action.APPWIDGET_UPDATE" );
        int ids[] = AppWidgetManager.getInstance( getApplication() ).getAppWidgetIds( new ComponentName( getApplication(), WorkoutWidget.class ) );
        intent.putExtra( AppWidgetManager.EXTRA_APPWIDGET_IDS, ids );
        sendBroadcast( intent );
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        mContext = getApplicationContext();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            widgetToInflate = extras.getInt( "code" );
            Log.e( "extras", String.valueOf( widgetToInflate ) + "  ................." + String.valueOf( widgetWorkoutId ) );
            if (extras.containsKey( "codeTwo" )) {
                widgetWorkoutId = extras.getInt( "codeTwo" );
                Log.e( "extras", String.valueOf( widgetToInflate ) + "  ................." + String.valueOf( widgetWorkoutId ) );
                workoutCategory = extras.getInt( "category" );
            }
        }
        if (savedInstanceState != null) {
            widgetWorkoutId = savedInstanceState.getInt( "widgetWorkoutId" );
            widgetCreated = savedInstanceState.getInt( "widgetCreated" );
            widgetWorkoutDetails = savedInstanceState.getParcelableArrayList( "widgetWorkout" );
            Intent intent = new Intent( getApplicationContext(), WorkoutWidget.class );
            intent.setAction( "android.appwidget.action.APPWIDGET_UPDATE" );
            int ids[] = AppWidgetManager.getInstance( getApplication() ).getAppWidgetIds( new ComponentName( getApplication(), WorkoutWidget.class ) );
            intent.putExtra( AppWidgetManager.EXTRA_APPWIDGET_IDS, ids );
            sendBroadcast( intent );
        }
        setContentView( R.layout.activity_home_screen );

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add( R.id.master_list_fragment, masterListFragment ).addToBackStack( null ).commit();

        updatePreferences();

        Analytics application = (Analytics) getApplication();
        mTracker = application.getDefaultTracker();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            warningcolor = getColor( R.color.warningRed );
            normalcolor = getColor (R.color.colorAccent);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent( intent );
        setIntent( intent );
    }

    @Override
    protected void onResume() {
        super.onResume();
        mContext = getApplicationContext();
        Bundle extras = getIntent().getExtras();
        Log.e( "extras", String.valueOf( extras ) );
        if (extras != null) {
            widgetToInflate = extras.getInt( "code" );
            Log.e( "extras", String.valueOf( widgetToInflate ) + "  ................." + String.valueOf( widgetWorkoutId ) );
            if (extras.containsKey( "codeTwo" )) {
                widgetWorkoutId = extras.getInt( "codeTwo" );
                Log.e( "extras", String.valueOf( widgetToInflate ) + "  ................." + String.valueOf( widgetWorkoutId ) );
                workoutCategory = extras.getInt( "category" );
            }
        }
        loadFromWidget( fragmentTransaction );
        sharedPref = getPreferences( Context.MODE_PRIVATE );
        if (sharedPref != null) {
            kgValue = sharedPref.getInt( "kgValue", kgValue );
            lbsValue = sharedPref.getInt( "lbsValue", lbsValue );
            kmValue = sharedPref.getInt( "kmValue", kmValue );
            milesValue = sharedPref.getInt( "milesValue", milesValue );
            timerSwitchValue = sharedPref.getInt( "timerSwitchValue", timerSwitchValue );
            removeAdvertsValue = sharedPref.getInt( "removeAdvertsValue", removeAdvertsValue );
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState( savedInstanceState );
        if (savedInstanceState.size()>0) {
            Log.e( "SAVED NOT NULL", "YAY" );
            widgetWorkoutDetails = savedInstanceState.getParcelableArrayList( "widgetWorkout" );
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                widgetToInflate = extras.getInt( "code" );
                if (extras.containsKey( "codeTwo" )) {
                    widgetWorkoutId = extras.getInt( "codeTwo" );
                    Log.e( "extras", String.valueOf( widgetToInflate ) + "  ................." + String.valueOf( widgetWorkoutId ) );
                    workoutCategory = extras.getInt( "category" );
                }
            }
            Intent intent = new Intent( getApplicationContext(), WorkoutWidget.class );
            intent.setAction( "android.appwidget.action.APPWIDGET_UPDATE" );
            int ids[] = AppWidgetManager.getInstance( getApplication() ).getAppWidgetIds( new ComponentName( getApplication(), WorkoutWidget.class ) );
            intent.putExtra( AppWidgetManager.EXTRA_APPWIDGET_IDS, ids );
            sendBroadcast( intent );
            widgetWorkoutDetails = extras.getParcelableArrayList( "widgetWorkout" );
            widgetWorkoutId = extras.getInt( "widgetWorkoutId" );
            Log.e( "widgetWorkoutId", String.valueOf( extras.getInt( "widgetWorkoutId" ) ) );
            currentMediaSource = extras.getString( "currentMediaSource" );
            listStateOne = extras.getInt( "listStateOne" );
            listStateTwo = extras.getInt( "listStateTwo" );
            listStateThree = extras.getInt( "listStateThree" );
            exercisesInSetWorkout = extras.getParcelableArrayList( "exercisesInSetWorkout" );
            calledPosition = extras.getInt( "calledPostion" );
            exerciseCategory = extras.getInt( "exerciseCategory" );
            workoutCategory = extras.getInt( "workoutCategory" );
            ArrayList<Exercise> exerciseTempHolder = new ArrayList<>();
            exerciseTempHolder = (extras.getParcelableArrayList( "exercise" ));
            exercise = exerciseTempHolder.get( 0 );
            ArrayList<Workout> workoutTempHolder = new ArrayList<>();
            workoutTempHolder = (extras.getParcelableArrayList( "workout" ));
            workout = workoutTempHolder.get( 0 );
            kgValue = extras.getInt( "kgValue" );
            lbsValue = extras.getInt( "lbsValue" );
            kmValue = extras.getInt( "kmValue" );
            milesValue = extras.getInt( "milesValue" );
            timerSwitchValue = extras.getInt( "timerSwitchValue" );
            removeAdvertsValue = extras.getInt( "removeAdvertsValue" );
            newConnection();
        }
    }

    @Override
    public void onButtonSelected(int position) {
        android.support.v4.app.FragmentTransaction homeScreenButtonFragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                homeScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, categories );
                mTracker.setScreenName( "Page: categroies" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
                break;
            case 1:
                homeScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, workoutCategories );
                mTracker.setScreenName( "Page: categroies" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
                break;
            case 2:
                homeScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, settingsFragment );
                mTracker.setScreenName( "Page: settings" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
                break;
            case 3:
                homeScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, newsFeedFragment );
                newConnection();
                mTracker.setScreenName( "Page: news feed" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
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
                exerciseScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, categories );
                mTracker.setScreenName( "Page: categroies" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
                break;
            case 1:
                newExerciseFragment = new NewExerciseFragment();
                exerciseScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, newExerciseFragment );
                mTracker.setScreenName( "Page: new exercise" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
                break;
            case 3:
                exerciseDetailsFragment = new ExerciseDetailsFragment();
                exerciseDetailsFragment.exercise = exercise;
                exerciseScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, exerciseDetailsFragment );
                mTracker.setScreenName( "Page: exercise details" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
                break;
        }
        exerciseScreenButtonFragmentTransaction.addToBackStack( null ).commit();
    }

    @Override
    public void onWorkoutListButtonSelected(int position) {
        android.support.v4.app.FragmentTransaction workoutListScreenButtonFragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                workoutCategories = new WorkoutsCategories();
                workoutListScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, workoutCategories );
                mTracker.setScreenName( "Page: categroies" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
                break;
            case 1:
                newWorkoutFragment = new NewWorkoutFragment();
                workoutListScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, newWorkoutFragment );
                mTracker.setScreenName( "Page: new workout" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
                break;
            case 3:
                workoutDetailsFragment = new WorkoutDetailsFragment();
                workoutDetailsFragment.workout = workout;
                workoutListScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, workoutDetailsFragment );
                mTracker.setScreenName( "Page: workout details" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
                break;
        }
        workoutListScreenButtonFragmentTransaction.addToBackStack( null ).commit();
    }

    @Override
    public void onSettingsButtonSelected(int position) {
        android.support.v4.app.FragmentTransaction settingsScreenButtonFragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                masterListFragment = new MasterListFragment();
                sharedPref = getPreferences( Context.MODE_PRIVATE );
                kgValue = sharedPref.getInt( "kgValue", kgValue );
                lbsValue = sharedPref.getInt( "lbsValue", lbsValue );
                kmValue = sharedPref.getInt( "kmValue", kmValue );
                milesValue = sharedPref.getInt( "milesValue", milesValue );
                timerSwitchValue = sharedPref.getInt( "timerSwitchValue", timerSwitchValue );
                removeAdvertsValue = sharedPref.getInt( "removeAdvertsValue", removeAdvertsValue );
                settingsScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, masterListFragment );
                mTracker.setScreenName( "Page: back to home page" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
                break;
        }
        settingsScreenButtonFragmentTransaction.addToBackStack( null ).commit();
    }

    @Override
    public void onNewExerciseButtonSelected(int position) {
        android.support.v4.app.FragmentTransaction newExerciseScreenButtonFragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                exerciseListFragment = new ExerciseListFragment();
                newExerciseScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, exerciseListFragment );
                mTracker.setScreenName( "Page: exercise list" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
                break;
            case 1:
                exerciseListFragment = new ExerciseListFragment();
                newExerciseScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, exerciseListFragment );
                mTracker.setScreenName( "Page: exercise list" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
                break;
        }
        newExerciseScreenButtonFragmentTransaction.addToBackStack( null ).commit();
    }

    @Override
    public void onEditExerciseButtonSelected(int position) {
        android.support.v4.app.FragmentTransaction editExerciseScreenButtonFragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                exerciseListFragment = new ExerciseListFragment();
                editExerciseScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, exerciseListFragment );
                mTracker.setScreenName( "Page: exercise list" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
                break;
            case 1:
                exerciseListFragment = new ExerciseListFragment();
                editExerciseScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, exerciseListFragment );
                mTracker.setScreenName( "Page: exercise list" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
                break;
        }
        editExerciseScreenButtonFragmentTransaction.addToBackStack( null ).commit();
    }

    @Override
    public void onNewWorkoutButtonSelected(int position) {
        android.support.v4.app.FragmentTransaction newWorkoutScreenButtonFragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                workoutListFragment = new WorkoutListFragment();
                newWorkoutScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, workoutListFragment );
                mTracker.setScreenName( "Page: workout list" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
                break;
            case 1:
                workoutListFragment = new WorkoutListFragment();
                newWorkoutScreenButtonFragmentTransaction.replace( R.id.master_list_fragment, workoutListFragment );
                mTracker.setScreenName( "Page: categroies" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
                break;
        }
        newWorkoutScreenButtonFragmentTransaction.addToBackStack( null ).commit();
    }


    @Override
    public void onExerciseDetailsButtonSelected(int position) {
        android.support.v4.app.FragmentTransaction exerciseDetailsButtonFragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                exerciseListFragment = new ExerciseListFragment();
                exerciseDetailsButtonFragmentTransaction.replace( R.id.master_list_fragment, exerciseListFragment );
                mTracker.setScreenName( "Page: exercise list" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
                break;
            case 1:
                editExerciseFragment = new EditExerciseFragment();
                editExerciseFragment.exerciseToEdit = exercise;
                exerciseDetailsButtonFragmentTransaction.replace( R.id.master_list_fragment, editExerciseFragment );
                mTracker.setScreenName( "Page: edit exercise" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
                break;
            case 2:
                youTubeFragment = new YouTubeFragment();
                exerciseDetailsButtonFragmentTransaction.replace( R.id.master_list_fragment, youTubeFragment );
                mTracker.setScreenName( "Page: youtube video" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
                break;
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
                workoutDetailsButtonFragmentTransaction.replace( R.id.master_list_fragment, workoutDetailsFragment );
                mTracker.setScreenName( "Page: workout details" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
                break;
            case 1:
                workoutDetailsFragment = new WorkoutDetailsFragment();
                workoutDetailsFragment.workout = workout;
                workoutDetailsButtonFragmentTransaction.replace( R.id.master_list_fragment, workoutDetailsFragment );
                mTracker.setScreenName( "Page: workout detials" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
                break;
        }

        workoutDetailsButtonFragmentTransaction.addToBackStack( null ).commit();
    }


    @Override
    public void onWorkoutDetailsButtonSelected(int position) {
        android.support.v4.app.FragmentTransaction workoutDetailsButtonFragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                workoutListFragment = new WorkoutListFragment();
                workoutDetailsButtonFragmentTransaction.replace( R.id.master_list_fragment, workoutListFragment );
                mTracker.setScreenName( "Page: workout list" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
                break;
            case 1:
                editWorkoutFragment = new EditWorkoutFragment();
                editWorkoutFragment.workoutToEdit = workout;
                workoutDetailsButtonFragmentTransaction.replace( R.id.master_list_fragment, editWorkoutFragment );
                mTracker.setScreenName( "Page: edit workout" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
                break;
            case 3:
                workoutExerciseDetailsFragment = new WorkoutExerciseDetailsFragment();
                workoutExerciseDetailsFragment.exercise = exercise;
                workoutDetailsButtonFragmentTransaction.replace( R.id.master_list_fragment, workoutExerciseDetailsFragment );
                mTracker.setScreenName( "Page: exercise details from workout" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
            case 4:
                widgetCreated = 1;
                Intent intent = new Intent( getApplicationContext(), WorkoutWidget.class );
                intent.setAction( "android.appwidget.action.APPWIDGET_UPDATE" );
                int ids[] = AppWidgetManager.getInstance( getApplication() ).getAppWidgetIds( new ComponentName( getApplication(), WorkoutWidget.class ) );
                intent.putExtra( AppWidgetManager.EXTRA_APPWIDGET_IDS, ids );
                sendBroadcast( intent );
                widgetSharedPrefs();
        }

        workoutDetailsButtonFragmentTransaction.addToBackStack( null ).commit();
    }

    @Override
    public void onWorkoutExerciseDetailsButtonSelected(int position) {
        android.support.v4.app.FragmentTransaction workoutExerciseDetailsButtonFragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                workoutDetailsFragment = new WorkoutDetailsFragment();
                addWorkouts();
                workoutDetailsFragment.workout = workout;
                workoutExerciseDetailsButtonFragmentTransaction.replace( R.id.master_list_fragment, workoutDetailsFragment );
                mTracker.setScreenName( "Page: workout details" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
                break;
            case 1:
                workoutDetailsFragment = new WorkoutDetailsFragment();
                workoutDetailsFragment.workout = workout;
                workoutExerciseDetailsButtonFragmentTransaction.replace( R.id.master_list_fragment, workoutDetailsFragment );
                mTracker.setScreenName( "Page: workout details" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
                break;
            case 2:
                workoutExerciseDetailsFragment = new WorkoutExerciseDetailsFragment();
                workoutExerciseDetailsFragment.exercise = exercisesInSetWorkout.get( calledPosition );
                workoutExerciseDetailsButtonFragmentTransaction.replace( R.id.master_list_fragment, workoutExerciseDetailsFragment );
                mTracker.setScreenName( "Page: workout details" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
                break;
            case 3:
                YouTubeFragment youTubeFragment = new YouTubeFragment();
                workoutExerciseDetailsButtonFragmentTransaction.replace( R.id.master_list_fragment, youTubeFragment );
                mTracker.setScreenName( "Page: youtube video" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
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
                categoriesFragmentTransaction.replace( R.id.master_list_fragment, masterListFragment );
                mTracker.setScreenName( "Page: home screen" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
                break;
            case 1:
                exerciseListFragment = new ExerciseListFragment();
                categoriesFragmentTransaction.replace( R.id.master_list_fragment, exerciseListFragment );
                mTracker.setScreenName( "Page: exercise list" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
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
                workoutCategoriesFragmentTransaction.replace( R.id.master_list_fragment, masterListFragment );
                mTracker.setScreenName( "Page: home screen" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
                break;
            case 1:
                workoutListFragment = new WorkoutListFragment();
                workoutCategoriesFragmentTransaction.replace( R.id.master_list_fragment, workoutListFragment );
                mTracker.setScreenName( "Page: workout list" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
                break;
        }
        workoutCategoriesFragmentTransaction.addToBackStack( null ).commit();
    }

    @Override
    public void onNewsListButtonSelected(int position) {
        android.support.v4.app.FragmentTransaction newsListFragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                masterListFragment = new MasterListFragment();
                newsListFragmentTransaction.replace( R.id.master_list_fragment, masterListFragment );
                mTracker.setScreenName( "Page: home screen" );
                mTracker.send( new HitBuilders.ScreenViewBuilder().build() );
                break;
        }
        newsListFragmentTransaction.addToBackStack( null ).commit();
    }

    public void widgetSharedPrefs() {
        SharedPreferences widgetPrefs = getPreferences( Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = widgetPrefs.edit();
        if (widgetPrefs != null) {
            editor.remove( "widgetWorkoutId" );
        }
        editor.putInt( "widgetWorkoutId", widgetWorkoutId );
        editor.commit();
    }


    public void loadFromWidget(android.support.v4.app.FragmentTransaction fragmentTransaction) {
        updatePreferences();
        if (widgetToInflate == 999999) {
            workoutCategories = new WorkoutsCategories();
            fragmentTransaction.replace( R.id.master_list_fragment, workoutCategories );
        } else if (widgetToInflate != 666666) {
            Log.e( "Widget to inflate", String.valueOf( widgetToInflate ) );
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add( R.id.master_list_fragment, masterListFragment ).addToBackStack( null );
            workoutExerciseDetailsFragment = new WorkoutExerciseDetailsFragment();
            Cursor exerciseCursor = getContentResolver().query( CONTENT_URI, null, null, null, null );
            exerciseCursor.moveToFirst();
            for (int a = 0; a < exerciseCursor.getCount(); a++) {
                exerciseCursor.moveToPosition( a );
                if (widgetToInflate == exerciseCursor.getInt( exerciseCursor.getColumnIndex( _ID ) )) {
                    String exerciseName = exerciseCursor.getString( exerciseCursor.getColumnIndex( ExerciseContract.ExerciseTable.EXERCISE_NAME ) );
                    int categoriesOneValue = exerciseCursor.getInt( exerciseCursor.getColumnIndex( ExerciseContract.ExerciseTable.CATEGORY_ONE_STATE ) );
                    int categoriesTwoValue = exerciseCursor.getInt( exerciseCursor.getColumnIndex( ExerciseContract.ExerciseTable.CATEGORY_TWO_STATE ) );
                    int categoriesThreeValue = exerciseCursor.getInt( exerciseCursor.getColumnIndex( ExerciseContract.ExerciseTable.CATEGORY_THREE_STATE ) );
                    int categoriesFourValue = exerciseCursor.getInt( exerciseCursor.getColumnIndex( ExerciseContract.ExerciseTable.CATEGORY_FOUR_STATE ) );
                    int categoriesFiveValue = exerciseCursor.getInt( exerciseCursor.getColumnIndex( ExerciseContract.ExerciseTable.CATEGORY_FIVE_STATE ) );
                    int categoriesSixValue = exerciseCursor.getInt( exerciseCursor.getColumnIndex( ExerciseContract.ExerciseTable.CATEGORY_SIX_STATE ) );
                    String mediaSource = exerciseCursor.getString( exerciseCursor.getColumnIndex( ExerciseContract.ExerciseTable.MEDIA_SOURCE ) );
                    int numberOfSets = exerciseCursor.getInt( exerciseCursor.getColumnIndex( ExerciseContract.ExerciseTable.NUMBER_OF_SETS ) );
                    int maxWeight = exerciseCursor.getInt( exerciseCursor.getColumnIndex( ExerciseContract.ExerciseTable.MAX_WEIGHT ) );
                    int startingWeight = exerciseCursor.getInt( exerciseCursor.getColumnIndex( ExerciseContract.ExerciseTable.STARTING_WEIGHT ) );
                    String addToWorkout = exerciseCursor.getString( exerciseCursor.getColumnIndex( ExerciseContract.ExerciseTable.ADD_TO_WORKOUT ) );
                    String notes = exerciseCursor.getString( exerciseCursor.getColumnIndex( ExerciseContract.ExerciseTable.NOTES ) );
                    Exercise exerciseToAdd = new Exercise( exerciseName, categoriesOneValue, categoriesTwoValue,
                            categoriesThreeValue, categoriesFourValue, categoriesFiveValue, categoriesSixValue,
                            mediaSource, numberOfSets, maxWeight, startingWeight, addToWorkout, notes );
                    exerciseToAdd.mID = exerciseCursor.getInt( exerciseCursor.getColumnIndex( ExerciseContract.ExerciseTable._ID ) );
                    workoutExerciseDetailsFragment.exercise = exerciseToAdd;
                    fragmentTransaction.replace( R.id.master_list_fragment, workoutExerciseDetailsFragment );
                    fragmentTransaction.addToBackStack( null ).commit();
                }
            }
            exerciseCursor.close();
        }
    }

    public ArrayList<Workout> addWorkouts() {
        Cursor cursor = getApplicationContext().getContentResolver().query( WORKOUT_CONTENT_URI, null, null, null, null );
        ArrayList<Workout> workoutsList = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition( i );
                String workoutName = cursor.getString( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.WORKOUT_NAME ) );
                int categoriesOneValue = cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.CATEGORY_ONE_STATE ) );
                int categoriesTwoValue = cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.CATEGORY_TWO_STATE ) );
                int categoriesThreeValue = cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.CATEGORY_THREE_STATE ) );
                int categoriesFourValue = cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.CATEGORY_FOUR_STATE ) );
                int categoriesFiveValue = cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.CATEGORY_FIVE_STATE ) );
                int categoriesSixValue = cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.CATEGORY_SIX_STATE ) );
                ArrayList<Integer> idOfExercises = new ArrayList<>();
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_ONE_ID ) ) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_TWO_ID ) ) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_THREE_ID ) ) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_FOUR_ID ) ) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_FIVE_ID ) ) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_SIX_ID ) ) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_SEVEN_ID ) ) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_EIGHT_ID ) ) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_NINE_ID ) ) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_TEN_ID ) ) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_ELEVEN_ID ) ) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_TWELVE_ID ) ) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_THIRTEEN_ID ) ) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_FOURTEEN_ID ) ) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_FIFTEEN_ID ) ) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_SIXTEEN_ID ) ) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_SEVENTEEN_ID ) ) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_EIGHTEEN_ID ) ) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_NINETEEN_ID ) ) );
                idOfExercises.add( cursor.getInt( cursor.getColumnIndex( WorkoutContract.WorkoutsTable.EXERCISE_TWENTY_ID ) ) );

                Workout workoutToAdd = new Workout( workoutName, categoriesOneValue, categoriesTwoValue,
                        categoriesThreeValue, categoriesFourValue, categoriesFiveValue, categoriesSixValue, idOfExercises.get( 0 ),
                        idOfExercises.get( 1 ), idOfExercises.get( 2 ), idOfExercises.get( 3 ), idOfExercises.get( 4 ),
                        idOfExercises.get( 5 ), idOfExercises.get( 6 ), idOfExercises.get( 7 ), idOfExercises.get( 8 ),
                        idOfExercises.get( 9 ), idOfExercises.get( 10 ), idOfExercises.get( 11 ), idOfExercises.get( 12 ),
                        idOfExercises.get( 13 ), idOfExercises.get( 14 ), idOfExercises.get( 15 ), idOfExercises.get( 16 ),
                        idOfExercises.get( 17 ), idOfExercises.get( 18 ), idOfExercises.get( 19 )
                );
                workoutToAdd.mID = cursor.getInt( cursor.getColumnIndex( _ID ) );
                workoutsList.add( workoutToAdd );
                Log.e( "WORKOUT", String.valueOf( workoutToAdd.mID ) + "....." + String.valueOf( widgetWorkoutId ) );
                if (workoutToAdd.mID == HomeScreen.widgetWorkoutId) {
                    HomeScreen.workout = workoutToAdd;
                    Log.e( "Changed", "here" );
                }
            }
        }
        cursor.close();
        return workoutsList;
    }

    public static void checkDisplayBanner(View view, int AdsDisabled) {
        AdView mAdView;
        mAdView = view.findViewById( R.id.adView );
        if (AdsDisabled == 0) {
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd( adRequest );
        } else {
            mAdView.setVisibility( View.GONE );
        }
    }

    public static void getChronometer(View view, int showTimerValue, int fragWantsTimer){
        if (showTimerValue == 1 && fragWantsTimer == 1) {
            if (running){
                mTime = chronometer.getBase();
            }
            chronometer = view.findViewById( R.id.chronometer );
            final LinearLayout linearLayout = view.findViewById( R.id.chronometerLinearLayout );
            linearLayout.setVisibility( View.VISIBLE );
            if (running){
                chronometer.start();
                chronometer.setBase(mTime);
            }
            chronometer.setOnChronometerTickListener( new Chronometer.OnChronometerTickListener() {
                @Override
                public void onChronometerTick(Chronometer chronometer) {
                    if ((SystemClock.elapsedRealtime()-chronometer.getBase())>=30000){
                        linearLayout.setBackgroundColor( warningcolor);
                    } else if ((SystemClock.elapsedRealtime()-chronometer.getBase()<30000)){
                        linearLayout.setBackgroundColor( normalcolor );
                    }
                }
            } );
        }
    }

    public static void startChronometer(View v){
     if (!running){
         chronometer.setBase( SystemClock.elapsedRealtime() );
         mTime = 0;
         chronometer.start();
         running = true;
     }
    }

    public static void restartChronometer(View v){
    if (running){
        chronometer.setBase( SystemClock.elapsedRealtime() );
        mTime = 0;
        running = false;
        chronometer.stop();
    }
    }

    public void updatePreferences () {
        sharedPref = getPreferences( Context.MODE_PRIVATE );
        if (sharedPref != null) {
            kgValue = sharedPref.getInt( "kgValue", kgValue );
            lbsValue = sharedPref.getInt( "lbsValue", lbsValue );
            kmValue = sharedPref.getInt( "kmValue", kmValue );
            milesValue = sharedPref.getInt( "milesValue", milesValue );
            timerSwitchValue = sharedPref.getInt( "timerSwitchValue", timerSwitchValue );
            removeAdvertsValue = sharedPref.getInt( "removeAdvertsValue", removeAdvertsValue );

        } }

    public void newConnection() {
        //Connectivity Manager, determines if there is internet connectivity
        Log.e( "New connection", "has started" );
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService( Context.CONNECTIVITY_SERVICE );
        isNetworkActive = cm.getActiveNetworkInfo();
        if (isNetworkActive != null && isNetworkActive.isConnectedOrConnecting()) {
            if (loaderChecker >= 1) {
                getLoaderManager().destroyLoader( NEWS_LOADER_ID );
                loaderChecker--;
            }
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader( NEWS_LOADER_ID, null, this );
            Log.e( "Init loader", "has started" );
            loaderChecker++;
        } else if (isNetworkActive == null) {
            if (loaderChecker >= 1) {
                getLoaderManager().destroyLoader( NEWS_LOADER_ID );
                loaderChecker--;
            }
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader( NEWS_LOADER_ID, null, this );
            loaderChecker++;
        }
    }

    @Override
    public Loader<ArrayList<News>> onCreateLoader(int id, Bundle args) {
        if (id>10){
            return new FlexLoader (this, id);
        }else {
            return new NewsLoader(this, getString( R.string.newsJson ));
        }
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<News>> loader, ArrayList<News> newNews) {
        Log.e( "Loader", "has finished" );
        news = newNews;
        newsFeedFragment = new NewsFeed();
        android.support.v4.app.FragmentTransaction loaderTransaction = getSupportFragmentManager().beginTransaction();
        loaderTransaction.replace( R.id.master_list_fragment, newsFeedFragment );
        loaderTransaction.addToBackStack( null ).commit();

    }

    @Override
    public void onLoaderReset(Loader<ArrayList<News>> loader) {

    }

}
