package com.example.android.Widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import static com.example.android.Widget.WorkoutWidget.currentExercise;
import static com.example.android.Widget.WorkoutWidget.exerciseToInflate;
import static com.example.android.Widget.WorkoutWidget.widgetExercises;
import static com.example.android.Widget.WorkoutWidget.workoutId;

public class WorkoutWidgetIntents extends IntentService {
    public static final String ACTION_NEXT = "ActionRecieverNext";
    public static final String ACTION_PREVIOUS = "ActionRecieverPrevious";
    public static final String ACTION_EXERCISE = "ActionRecieverExercise";
    public static final String ACTION_WORKOUT = "ActionRecieverWorkout";
    public static int toLoadCode;

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
