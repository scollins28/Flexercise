package com.example.android.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.widget.Chronometer;
import android.widget.RemoteViews;

import com.example.android.HomeScreen;
import com.example.android.free.R;

import java.util.ArrayList;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.example.android.Widget.WorkoutWidgetIntents.ACTION_RESTART;
import static com.example.android.Widget.WorkoutWidgetIntents.ACTION_START;
import static com.example.android.Widget.WorkoutWidgetIntents.restartTimer;
import static com.example.android.free.R.id.exercise_previous_button;
import static com.example.android.free.R.id.load_exercise;
import static com.example.android.free.R.id.load_workouts;
import static com.example.android.free.R.id.restart;
import static com.example.android.free.R.id.start;
import static com.example.android.free.R.id.widget_next;
import static com.example.android.Widget.WorkoutWidgetIntents.ACTION_EXERCISE;
import static com.example.android.Widget.WorkoutWidgetIntents.ACTION_NEXT;
import static com.example.android.Widget.WorkoutWidgetIntents.ACTION_PREVIOUS;
import static com.example.android.Widget.WorkoutWidgetIntents.ACTION_WORKOUT;

/**
 * Implementation of App Widget functionality.
 */
public class WorkoutWidget extends AppWidgetProvider {
    public static ArrayList <WidgetWorkoutDetails> widgetExercises = new ArrayList<WidgetWorkoutDetails>();
    static WidgetWorkoutDetails exerciseToInflate;
    static int widgetNumber = 0;
    static int workoutId;
    static Context mContext;
    static int currentExercise = 0;
    static int exerciseType;
    static String unit;
    static String altUnit;
    static String timeUnit;
    static long baseposition = SystemClock.elapsedRealtime();
    static boolean playing = false;
    static int intentrecieved = 0;
    static int timerEnabled;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int[] appWidgetIds) {
        widgetExercises = HomeScreen.widgetWorkoutDetails;
        mContext = context;
        timerEnabled = HomeScreen.timerSwitchValue;
        RemoteViews views = new RemoteViews( context.getPackageName(), R.layout.workout_widget );
        if (HomeScreen.widgetCreated==1){
        widgetUnits();}
        workoutId = HomeScreen.widgetWorkoutId;
        if (widgetExercises==null){
            views.setViewVisibility( R.id.no_workout_text, VISIBLE );
            views.setViewVisibility( R.id.load_workouts, VISIBLE );
            views.setViewVisibility( R.id.appwidget_text, GONE);
            views.setViewVisibility( R.id.appwidget_exercise_number, GONE);
            views.setViewVisibility( R.id.appwidget_exercise_of, GONE);
            views.setViewVisibility( R.id.appwidget_exercise_number_of_x, GONE);
            views.setViewVisibility( R.id.number_of_Sets, GONE);
            views.setViewVisibility( R.id.number_of_sets_text, GONE);
            views.setViewVisibility( R.id.max_weight, GONE);
            views.setViewVisibility( R.id.max_weight_text, GONE);
            views.setViewVisibility( R.id.unit_one, GONE);
            views.setViewVisibility( R.id.starting_weight, GONE);
            views.setViewVisibility( R.id.starting_weight_text, GONE);
            views.setViewVisibility( R.id.unit_two, GONE);
            views.setViewVisibility( R.id.exercise_previous_button, GONE);
            views.setViewVisibility( R.id.load_exercise, GONE);
            views.setViewVisibility( R.id.widget_next, GONE);
            views.setViewVisibility( R.id.timer_layout, GONE );
            Intent intent = new Intent( context, HomeScreen.class );
            int code = 999999;
            intent.putExtra( "code", code);
            intent.setAction( ACTION_WORKOUT );
            PendingIntent pendingIntent = PendingIntent.getActivity( context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT );
            views.setOnClickPendingIntent( load_workouts, pendingIntent );
        }else {
            exerciseToInflate = widgetExercises.get( currentExercise );
            exerciseType = exerciseToInflate.getExerciseType();
            views.setViewVisibility( R.id.no_workout_text, GONE );
            views.setViewVisibility( R.id.load_workouts, GONE );
            views.setViewVisibility( R.id.appwidget_text, VISIBLE);
            views.setViewVisibility( R.id.appwidget_exercise_number, VISIBLE);
            views.setViewVisibility( R.id.appwidget_exercise_of, VISIBLE);
            views.setViewVisibility( R.id.appwidget_exercise_number_of_x, VISIBLE);
            views.setViewVisibility( R.id.number_of_Sets, VISIBLE);
            views.setViewVisibility( R.id.number_of_sets_text, VISIBLE);
            views.setViewVisibility( R.id.max_weight, VISIBLE);
            views.setViewVisibility( R.id.max_weight_text, VISIBLE);
            views.setViewVisibility( R.id.unit_one, VISIBLE);
            views.setViewVisibility( R.id.starting_weight, VISIBLE);
            views.setViewVisibility( R.id.starting_weight_text, VISIBLE);
            views.setViewVisibility( R.id.unit_two, VISIBLE);
            views.setViewVisibility( R.id.exercise_previous_button, VISIBLE);
            views.setViewVisibility( R.id.load_exercise, VISIBLE);
            views.setViewVisibility( R.id.widget_next, VISIBLE);

            views.setTextViewText( R.id.appwidget_text, exerciseToInflate.getCurrentExerciseName());
            views.setTextViewText( R.id.appwidget_exercise_number, String.valueOf(currentExercise+1));
            views.setTextViewText( R.id.appwidget_exercise_number_of_x, String.valueOf( widgetExercises.size()));
            views.setTextViewText( R.id.number_of_Sets, String.valueOf( exerciseToInflate.getNumberOfSets()));
            if (exerciseType==0) {
                views.setTextViewText( R.id.max_weight, String.valueOf( exerciseToInflate.getMaxWeight() ) );
                views.setTextViewText( R.id.starting_weight, String.valueOf( exerciseToInflate.getStartingWeight() ) );
                views.setTextViewText( R.id.max_weight_text, mContext.getString( R.string.max_weight ));
                views.setTextViewText( R.id.starting_weight_text, mContext.getString( R.string.starting_weight ));
                views.setTextViewText( R.id.unit_one, unit );
                views.setTextViewText( R.id.unit_two, unit );
            }else {
                views.setTextViewText( R.id.max_weight, String.valueOf( exerciseToInflate.getDistance() ) );
                views.setTextViewText( R.id.starting_weight, String.valueOf( exerciseToInflate.getTime() ) );
                views.setTextViewText( R.id.max_weight_text, mContext.getString( R.string.distance ));
                views.setTextViewText( R.id.starting_weight_text, mContext.getString( R.string.time ));
                views.setTextViewText( R.id.unit_one, altUnit );
                views.setTextViewText( R.id.unit_two, timeUnit );
            }

            if (timerEnabled==1) {
                Intent startIntent = new Intent( context, WorkoutWidgetIntents.class );
                startIntent.setAction( ACTION_START );
                PendingIntent startPendingIntent = PendingIntent.getService( context, 0, startIntent, PendingIntent.FLAG_UPDATE_CURRENT );
                views.setOnClickPendingIntent( start, startPendingIntent );

                Intent restartIntent = new Intent( context, WorkoutWidgetIntents.class );
                restartIntent.setAction( ACTION_RESTART );
                PendingIntent restartPendingIntent = PendingIntent.getService( context, 0, restartIntent, PendingIntent.FLAG_UPDATE_CURRENT );
                views.setOnClickPendingIntent( restart, restartPendingIntent );

                views.setViewVisibility( R.id.timer_layout, VISIBLE );
                if (intentrecieved==0){
                    restartTimer();
                    views.setChronometer( R.id.chronometerWidget, baseposition, null, playing );
                } else if (intentrecieved==2){
                    views.setChronometer( R.id.chronometerWidget, baseposition, null, playing );
                    intentrecieved = 1;
                }
            }

            Intent intent = new Intent( context, HomeScreen.class );
            intent.putExtra( "code", exerciseToInflate.getCurrentExerciseId());
            intent.putExtra("codeTwo", workoutId);
            intent.putExtra( "category", exerciseToInflate.mWorkoutCategory );

            intent.setAction( ACTION_WORKOUT );
            PendingIntent pendingIntent = PendingIntent.getActivity( context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT );
            views.setOnClickPendingIntent( load_exercise, pendingIntent );

            Intent nextIntent = new Intent( context, WorkoutWidgetIntents.class );
            nextIntent.setAction( ACTION_NEXT );
            PendingIntent nextPendingIntent = PendingIntent.getService( context, 0, nextIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent( widget_next, nextPendingIntent);

            Intent previousIntent = new Intent( context, WorkoutWidgetIntents.class );
            previousIntent.setAction( ACTION_PREVIOUS);
            PendingIntent previousPendingIntent = PendingIntent.getService( context, 0, previousIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent( exercise_previous_button, previousPendingIntent);
        }

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget( appWidgetIds, views );
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget( context, appWidgetManager, appWidgetIds );
        }
    }

    @Override
    public void onEnabled(Context context) {
        widgetNumber = 0;
    }

    @Override
    public void onDisabled(Context context) {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive( context, intent );
        if (intent != null) {
            final String action = intent.getAction();
            if (action.equals( ACTION_NEXT)) {
                handleNext();
            }
            else if (action.equals( ACTION_PREVIOUS)) {
                handlePrevious();
            }
            else if (action.equals( ACTION_EXERCISE)) {
            }
        }
    }

    private void handleNext() {
        int tempIngNumber = widgetExercises.size()-1;
        if (currentExercise<=tempIngNumber){
            currentExercise++;
        }
        else {
            currentExercise=0;
        }
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance( mContext);
        int [] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName( mContext, WorkoutWidget.class ));
        updateAppWidget( mContext, appWidgetManager, appWidgetIds );
    }

    private void handlePrevious() {
        int tempWidgetNumber = 0;
        if (tempWidgetNumber!=currentExercise){
            currentExercise--;
        }
        else {
            currentExercise=widgetExercises.size();
        }
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance( mContext );
        int [] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName( mContext, WorkoutWidget.class ));
        updateAppWidget( mContext, appWidgetManager, appWidgetIds );
    }

    @Override
    public void onRestored(Context context, int[] oldWidgetIds, int[] newWidgetIds) {
        super.onRestored( context, oldWidgetIds, newWidgetIds );

    }

    public static void widgetUnits() {
         if (HomeScreen.widgetCreated==1) {
            if (HomeScreen.kgValue == 1) {
                unit = mContext.getResources().getString( R.string.KG );
            } else {
                unit = mContext.getResources().getString( R.string.LBS );
            }
            if (HomeScreen.kmValue==1){
                altUnit = mContext.getString( R.string.KM );
            } else {
                altUnit = mContext.getString( R.string.miles );
            }
            timeUnit = mContext.getString( R.string.minutes );

        }
    }

}

