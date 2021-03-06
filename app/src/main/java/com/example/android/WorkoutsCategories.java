package com.example.android;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.Loader;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ScrollView;

import com.example.android.free.R;

import java.util.ArrayList;


public class WorkoutsCategories extends Fragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<ArrayList<Exercise>> {

    Context mContext;
    ImageButton backButton;
    ImageButton chestCategoryButton;
    ImageButton backCategoryButton;
    ImageButton armsCategoryButton;
    ImageButton absCategoryButton;
    ImageButton legsCategoryButton;
    ImageButton cardioCategoryButton;
    Toolbar mToolbar;


    //Empty constructor
    public WorkoutsCategories() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getLoaderManager().initLoader(10, null, this);

        //Getting context and inflating the rootview

        mContext = getContext();
        final View rootView = inflater.inflate( R.layout.categories, container, false );
        mToolbar = rootView.findViewById( R.id.toolbar );
        //Attaching button views to variables

        backButton = rootView.findViewById(R.id.categories_back_button);
        chestCategoryButton = rootView.findViewById(R.id.category_chest_pic);
        armsCategoryButton = rootView.findViewById(R.id.category_arms_pic);
        absCategoryButton = rootView.findViewById(R.id.category_abs_pic);
        legsCategoryButton = rootView.findViewById(R.id.category_legs_pic);
        backCategoryButton = rootView.findViewById(R.id.category_back_pic);
        cardioCategoryButton = rootView.findViewById( R.id.category_cardio_pic);

        // Setting on click listener for the three buttons

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = 0;
                if (v == backButton) {
                    position = 0;
                } else if (v == chestCategoryButton) {
                    HomeScreen.workoutCategory = 1;
                    position = 1;
                } else if (v == backCategoryButton) {
                    HomeScreen.workoutCategory = 5;
                    position = 1;
                } else if (v == armsCategoryButton) {
                    HomeScreen.workoutCategory = 3;
                    position = 1;
                } else if (v == absCategoryButton) {
                    HomeScreen.workoutCategory = 4;
                    position = 1;
                } else if (v == legsCategoryButton) {
                    HomeScreen.workoutCategory = 2;
                    position = 1;
                } else if (v == cardioCategoryButton) {
                    HomeScreen.workoutCategory = 6;
                    position = 1;
                }
                mCallback.onWorkoutCategorySelected( position );
            }
        };

        //Attaching the click listener to the buttons

        backButton.setOnClickListener( listener );
        chestCategoryButton.setOnClickListener( listener );
        backCategoryButton.setOnClickListener( listener );
        armsCategoryButton.setOnClickListener( listener );
        absCategoryButton.setOnClickListener( listener );
        legsCategoryButton.setOnClickListener( listener );
        cardioCategoryButton.setOnClickListener( listener );

        return rootView;
    }

    WorkoutsCategories.OnWorkoutCategorySelected mCallback;

    @NonNull
    @Override
    public android.support.v4.content.Loader<ArrayList<Exercise>> onCreateLoader(int id, @Nullable Bundle args) {
        return new FlexLoader( getContext(), 10 );
    }

    @Override
    public void onLoadFinished(@NonNull android.support.v4.content.Loader<ArrayList<Exercise>> loader, ArrayList<Exercise> data) {
        HomeScreen.exercisesFromLoader = data;
    }

    @Override
    public void onLoaderReset(@NonNull android.support.v4.content.Loader<ArrayList<Exercise>> loader) {

    }

    //Interface for the click listener to send which button has been pushed to the home screen, and activate the fragment transaction
    public interface OnWorkoutCategorySelected {
        void onWorkoutCategorySelected(int position);
    }

    //Attaching the interface to the MasterListFragment
    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        try {
            mCallback = (WorkoutsCategories.OnWorkoutCategorySelected) context;
        } catch (ClassCastException e) {
            throw new ClassCastException( context.toString() + "must implement click listener" );
        }
    }





}
