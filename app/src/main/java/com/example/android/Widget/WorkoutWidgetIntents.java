package com.example.android.Widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Chronometer;
import android.widget.LinearLayout;

import com.example.android.Workout;
import com.example.android.free.R;

import static com.example.android.Widget.WorkoutWidget.currentExercise;
import static com.example.android.Widget.WorkoutWidget.exerciseToInflate;
import static com.example.android.Widget.WorkoutWidget.intentrecieved;
import static com.example.android.Widget.WorkoutWidget.mContext;
import static com.example.android.Widget.WorkoutWidget.widgetExercises;
import static com.example.android.Widget.WorkoutWidget.workoutId;

public class WorkoutWidgetIntents extends IntentService {
    public static final String ACTION_NEXT = "ActionRecieverNext";
    public static final String ACTION_PREVIOUS = "ActionRecieverPrevious";
    public static final String ACTION_EXERCISE = "ActionRecieverExercise";
    public static final String ACTION_WORKOUT = "ActionRecieverWorkout";
    public static final String ACTION_START = "ActionRecieverStart";
    public static final String ACTION_RESTART = "ActionRecieverRestart";

    public WorkoutWidgetIntents() {
        super("WorkoutWidgetIntents");
    }

    public static void startActionNext(Context context) {
        Intent nextIntent = new Intent( context, WorkoutWidgetIntents.class );
        nextIntent.setAction( ACTION_NEXT );
        context.startService( nextIntent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (action.equals( ACTION_NEXT)) {
                handleNext();
            }
            else if (action.equals( ACTION_PREVIOUS)) {
                handlePrevious();
            }else if (action.equals( ACTION_WORKOUT)) {
                handleWorkout();
            }else if (action.equals( ACTION_EXERCISE)) {
                handleExercise();
            }else if (action.equals( ACTION_START)) {
                startTimer();
            }else if (action.equals( ACTION_RESTART)) {
                restartTimer();
            }
        }
    }

    private void handleNext() {
        int tempWidgetNumber = widgetExercises.size()-1;
        if (tempWidgetNumber==currentExercise){
            WorkoutWidget.currentExercise=0;
        }
        else {
            WorkoutWidget.currentExercise++;
        }
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance( this );
        int [] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName( this, WorkoutWidget.class ));
        WorkoutWidget.updateAppWidget( this, appWidgetManager, appWidgetIds );
    }

    private void handlePrevious() {
        int tempWidgetNumber = 0;
        if (tempWidgetNumber==currentExercise){
            WorkoutWidget.currentExercise=widgetExercises.size()-1;
        }
        else {
            WorkoutWidget.currentExercise--;
        }
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance( this );
        int [] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName( this, WorkoutWidget.class ));
        WorkoutWidget.updateAppWidget( this, appWidgetManager, appWidgetIds );
    }

    private void startTimer() {
        WorkoutWidget.playing = true;
        WorkoutWidget.baseposition = SystemClock.elapsedRealtime();
        if (intentrecieved==1){
            WorkoutWidget.intentrecieved = 2;
        }else {
            WorkoutWidget.intentrecieved = 1;
        }
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance( this );
        int [] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName( this, WorkoutWidget.class ));
        WorkoutWidget.updateAppWidget( this, appWidgetManager, appWidgetIds );
    }

    public static void restartTimer() {
        WorkoutWidget.playing = false;
        WorkoutWidget.baseposition = SystemClock.elapsedRealtime();
        if (intentrecieved==1){
            WorkoutWidget.intentrecieved = 2;
        }else {
            WorkoutWidget.intentrecieved = 1;
        }
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance( mContext);
        int [] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName( mContext, WorkoutWidget.class ));
        WorkoutWidget.updateAppWidget( mContext, appWidgetManager, appWidgetIds );
    }

    private void handleWorkout() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance( this );
        int [] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName( this, WorkoutWidget.class ));
        WorkoutWidget.updateAppWidget( this, appWidgetManager, appWidgetIds );
    }

    private void handleExercise() {
        Intent intent = new Intent(  );
        Bundle extras =new Bundle(  );
        extras.putInt( "code", exerciseToInflate.mCurrentExerciseId );
        extras.putInt( "codeTwo", workoutId );
        extras.putInt( "category", exerciseToInflate.mWorkoutCategory );
        intent.putExtras(extras);
        getApplicationContext().startActivity(intent);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance( this );
        int [] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName( this, WorkoutWidget.class ));
        WorkoutWidget.updateAppWidget( this, appWidgetManager, appWidgetIds );
    }
}
