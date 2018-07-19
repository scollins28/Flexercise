package com.example.android.flexercise;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import github.nisrulz.recyclerviewhelper.RVHAdapter;
import github.nisrulz.recyclerviewhelper.RVHViewHolder;

import static com.example.android.flexercise.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.WORKOUT_CONTENT_URI;


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

        private Context mContext;

        public WorkoutDetailsHolder(View view){
            super(view);
            exerciseName = view.findViewById( R.id.workout_details_item_name);
            numberOfSets = view.findViewById( R.id.workout_details_item_sets_value);
            maxWeight = view.findViewById( R.id.workout_details_item_max_weight_value);
            minWeight = view.findViewById( R.id.workout_details_item_starting_weight_value);
            mContext = view.getContext();
        }

        public void bindWorkout (Exercise exercise){
            Exercise mExercise = exercise;
            exerciseName.setText( mExercise.getExerciseName() );
            numberOfSets.setText( String.valueOf(mExercise.getNumberofSets()) );
            maxWeight.setText( String.valueOf(mExercise.getMaxWeight()) );
            minWeight.setText( String.valueOf(mExercise.getStartingWeight()) );
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

