package com.example.android.flexercise;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class ExerciseListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Exercise> exercises;

    public ExerciseListAdapter(Context context, ArrayList<Exercise>exercises) {
        mContext = context;
        this.exercises = exercises;
    }

    @Override
    public int getCount() {
        int count = exercises.size();
        return count;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Creates a new ImageView for each item referenced by the adapter
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public View getView(final int position, View convertView, ViewGroup parent) {
          ViewHolder viewHolder;

        if (convertView==null){
            convertView = LayoutInflater.from( mContext ).inflate( R.layout.exercise_list_item, parent, false );
            viewHolder = new ViewHolder(convertView);
            convertView.setTag( viewHolder );
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String categoryName = getCategory( position );
        String exerciseName = exercises.get( position ).getExerciseName();

        viewHolder.textView.setText( exerciseName );

        if (!categoryName.equals( "" )) {
            viewHolder.button.setText( categoryName );
            viewHolder.button.setVisibility( View.VISIBLE );
        }
        return convertView;

    }

    private class ViewHolder {
        TextView textView;
        Button button;

        public ViewHolder (View view){
            textView = (TextView) view.findViewById( R.id.exercise_list_item_name );
            button = (Button) view.findViewById( R.id.exercise_list_item_category );
        }
    }

    public String getCategory (int position){
        String categoryName;
        if (exercises.get( position ).getCategoryOneValue()==1){
            categoryName = mContext.getResources().getString( R.string.chest);
        }
        else if (exercises.get( position ).getCategoryTwoValue() == 1){
            categoryName = mContext.getResources().getString( R.string.legs);
        }
        else if (exercises.get( position ).getCategoryThreeValue() ==1){
            categoryName = mContext.getResources().getString( R.string.arms);
        }
        else if (exercises.get( position ).getCategoryFourValue() ==1){
            categoryName = mContext.getResources().getString( R.string.abs);
        }
        else if (exercises.get( position ).getCategoryFiveValue() ==1){
            categoryName = mContext.getResources().getString( R.string.back);
        }
        else if (exercises.get( position ).getCategorySixValue() ==1){
            categoryName = mContext.getResources().getString( R.string.cardio);
        }
        else {
            categoryName = "";
        }
        return categoryName;
    }
}

