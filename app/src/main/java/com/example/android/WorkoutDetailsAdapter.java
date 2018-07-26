package com.example.android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.Exercise;
import com.example.android.HomeScreen;
import com.example.android.free.R;

import java.util.ArrayList;

import github.nisrulz.recyclerviewhelper.RVHAdapter;
import github.nisrulz.recyclerviewhelper.RVHViewHolder;


public class WorkoutDetailsAdapter extends RecyclerView.Adapter<WorkoutDetailsAdapter.WorkoutDetailsHolder> implements RVHAdapter {

    private Context mContext;
    private ArrayList<Exercise> exercises;

    public WorkoutDetailsAdapter(ArrayList<Exercise>exercises, Context context) {
        this.exercises = exercises;
        mContext = context;
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position, int direction) {
        return;
    }

    public class WorkoutDetailsHolder extends RecyclerView.ViewHolder implements RVHViewHolder{
        public TextView exerciseName;
        public TextView numberOfSets;
        public TextView maxWeight;
        public TextView minWeight;
        public TextView maxWeightTv;
        public TextView maxWeightUnit;
        public TextView startingWeightTv;
        public TextView startingWeightUnit;
        int exerciseType = 0;

        private Context mContext;

        public WorkoutDetailsHolder(View view){
            super(view);
            exerciseName = view.findViewById( R.id.workout_details_item_name);
            numberOfSets = view.findViewById( R.id.workout_details_item_sets_value);
            maxWeight = view.findViewById( R.id.workout_details_item_max_weight_value);
            minWeight = view.findViewById( R.id.workout_details_item_starting_weight_value);
            mContext = view.getContext();
            maxWeightTv = view.findViewById( R.id.workout_details_item_max_weight_name );
            maxWeightUnit =view.findViewById( R.id.workout_details_item_max_weight_unit );
            startingWeightTv = view.findViewById( R.id.workout_details_item_starting_weight_name );
            startingWeightUnit =view.findViewById( R.id.workout_details_item_starting_weight_unit );
        }

        public void bindWorkout (Exercise exercise) {
            Exercise mExercise = exercise;
            exerciseType = mExercise.getExerciseType();
            if (exerciseType == 0) {
                exerciseName.setText( mExercise.getExerciseName() );
                numberOfSets.setText( String.valueOf( mExercise.getNumberofSets() ) );
                maxWeight.setText( String.valueOf( mExercise.getMaxWeight() ) );
                minWeight.setText( String.valueOf( mExercise.getStartingWeight() ) );
            } else {
                exerciseName.setText( mExercise.getExerciseName() );
                numberOfSets.setText( String.valueOf( mExercise.getNumberofSets() ) );
                maxWeight.setText( String.valueOf( mExercise.getDistance() ) );
                minWeight.setText( String.valueOf( mExercise.getTime() ) );
                maxWeightTv.setText( mContext.getString( R.string.distance ) );
                startingWeightTv.setText( mContext.getString( R.string.time ) );
                if (HomeScreen.kmValue == 0) {
                    maxWeightUnit.setText( mContext.getString( R.string.miles ) );
                } else {
                    maxWeightUnit.setText( mContext.getString( R.string.miles ) );
                }
                startingWeightUnit.setText( mContext.getString( R.string.minutes ) );
            }
        }

        @Override
        public void onItemSelected(int actionstate) {
            System.out.println("Item is selected");
        }

        @Override
        public void onItemClear() {
            System.out.println("Item is unselected");
        }
    }


    @Override
    public WorkoutDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() ).inflate( R.layout.workout_details_item, parent, false );
        WorkoutDetailsHolder workoutsHolder = new WorkoutDetailsHolder( itemView );
        TextView unitsOne = itemView.findViewById( R.id.workout_details_item_max_weight_unit );
        TextView unitsTwo = itemView.findViewById( R.id.workout_details_item_starting_weight_unit );
        if (HomeScreen.kgValue==1){
            unitsOne.setText( R.string.KG );
            unitsTwo.setText( R.string.KG );
        } else {
            unitsOne.setText( R.string.LBS);
            unitsTwo.setText( R.string.LBS);
        }
        return workoutsHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutDetailsHolder holder, int position) {
      holder.bindWorkout( exercises.get( position ) );
        }

    @Override
    public int getItemCount() {
        return exercises.size();
    }
}

