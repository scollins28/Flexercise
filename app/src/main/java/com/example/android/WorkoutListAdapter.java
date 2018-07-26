package com.example.android;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.free.R;
import com.example.android.Workout;

import java.util.ArrayList;
import java.util.Collections;

import github.nisrulz.recyclerviewhelper.RVHAdapter;
import github.nisrulz.recyclerviewhelper.RVHViewHolder;

import static com.example.android.Database.WorkoutsDatabase.WorkoutContract.WorkoutsTable.WORKOUT_CONTENT_URI;


public class WorkoutListAdapter extends RecyclerView.Adapter<WorkoutListAdapter.WorkoutsHolder> implements RVHAdapter {

    private Context mContext;
    private ArrayList<Workout> workouts;

    public WorkoutListAdapter(ArrayList<Workout>workouts, Context context) {
        this.workouts = workouts;
        mContext = context;
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        swap(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position, int direction) {
        remove(position);
    }

    public class WorkoutsHolder extends RecyclerView.ViewHolder implements RVHViewHolder{
        public TextView workoutName;

        private Context mContext;

        public WorkoutsHolder(View view){
            super(view);
            workoutName = view.findViewById( R.id.workout_list_item_name );
            mContext = view.getContext();
        }

        public void bindWorkout (Workout workout){
            Workout mWorkout = workout;
            workoutName.setText( mWorkout.getWorkoutName() );
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
    public WorkoutsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() ).inflate( R.layout.workout_list_item, parent, false );
        WorkoutsHolder workoutsHolder = new WorkoutsHolder( itemView );
        return workoutsHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutsHolder holder, int position) {
      holder.bindWorkout( workouts.get( position ) );
        }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    // Helper functions you might want to implement to make changes in the list as an event is fired
    private void remove(int position) {
        long id = workouts.get( position ).getID();
        Uri deleteUri =  WORKOUT_CONTENT_URI.buildUpon().appendPath(Long.toString(id)).build();
        mContext.getContentResolver().delete(deleteUri,null,null);

        workouts.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
        notifyItemRangeChanged(0, workouts.size());

    }

    private void swap(int firstPosition, int secondPosition) {
        Collections.swap(workouts, firstPosition, secondPosition);
        notifyItemMoved(firstPosition, secondPosition);
    }
}

