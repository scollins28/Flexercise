package com.example.android.flexercise;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import github.nisrulz.recyclerviewhelper.RVHAdapter;
import github.nisrulz.recyclerviewhelper.RVHViewHolder;

import static com.example.android.flexercise.Database.ExerciseContract.ExerciseTable.CONTENT_URI;


public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseListAdapter.ExerciseHolder> implements RVHAdapter {

    private Context mContext;
    private ArrayList<Exercise> exercises;

    public ExerciseListAdapter(ArrayList<Exercise>exercises, Context context) {
        this.exercises = exercises;
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

    public class ExerciseHolder extends RecyclerView.ViewHolder implements RVHViewHolder{
        public TextView exerciseName;

        private Context mContext;

        public ExerciseHolder(View view){
            super(view);
            exerciseName = view.findViewById( R.id.exercise_list_item_name );
            mContext = view.getContext();
        }

        public void bindExercise (Exercise exercise){
            Exercise mExercise = exercise;
            exerciseName.setText( mExercise.getExerciseName() );
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
    public ExerciseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() ).inflate( R.layout.exercise_list_item, parent, false );
        ExerciseHolder exerciseHolder = new ExerciseHolder( itemView );
        return exerciseHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseHolder holder, int position) {
      holder.bindExercise( exercises.get( position ) );
        }

    @Override
    public int getItemCount() {
        return exercises.size();
    }


    // Helper functions you might want to implement to make changes in the list as an event is fired
    private void remove(int position) {
        long id = exercises.get( position ).getID();
        Uri deleteUri =  CONTENT_URI.buildUpon().appendPath(Long.toString(id)).build();
        mContext.getContentResolver().delete(deleteUri,null,null);

        exercises.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
        notifyItemRangeChanged(0, exercises.size());

    }

    private void swap(int firstPosition, int secondPosition) {
        Collections.swap(exercises, firstPosition, secondPosition);
        notifyItemMoved(firstPosition, secondPosition);
    }
}

