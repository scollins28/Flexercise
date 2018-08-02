package com.example.android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.android.Database.ExerciseTableCleaner;
import com.example.android.Database.ExerciseUpdateWorkoutLists;
import com.example.android.free.R;
import java.util.ArrayList;
import io.apptik.widget.multiselectspinner.MultiSelectSpinner;


public class EditExerciseFragment extends android.support.v4.app.Fragment{

    View rootView;
    ImageButton editExerciseBackButton;
    Button editExerciseDoneButton;
    Button editExerciseCategoryOneButton;
    Button editExerciseCategoryTwoButton;
    Button editExerciseCategoryThreeButton;
    Button editExerciseCategoryFourButton;
    Button editExerciseCategoryFiveButton;
    Button editExerciseCategorySixButton;
    ConstraintLayout categoriesFab;
    int editExerciseCategoryOneValue;
    int editExerciseCategoryTwoValue;
    int editExerciseCategoryThreeValue;
    int editExerciseCategoryFourValue;
    int editExerciseCategoryFiveValue;
    int editExerciseCategorySixValue;
    int categoriesFabValue = 0;
    ConstraintLayout categoriesHiddenLayout;
    Context mContext;
    static Context context;
    ArrayList <String> options;
    public Exercise exerciseToEdit;
    EditText exerciseNameEditText;
    EditText numberOfSetsEditText;
    EditText maxWeightEditText;
    EditText startingWeightEditText;
    EditText notesEditText;
    Spinner mediaSpinner;
    TextView subheading;
    TextView maxWeightTv;
    TextView startingWeightTv;
    MultiSelectSpinner exerciseListMultiSpinner;
    MultiSelectSpinner addToWorkoutSpinner;
    ConstraintLayout youtubeConstraintLayout;
    CheckBox weightCheckbox;
    CheckBox cardioCheckbox;
    int mediaType = 0;
    int exerciseType = 0;
    Toolbar mToolbar;

    public EditExerciseFragment () {

    }

    @SuppressLint("CutPasteId")
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        exerciseType = exerciseToEdit.getExerciseType();

        rootView = inflater.inflate( R.layout.new_exercise_fragment, container, false );
        mToolbar = rootView.findViewById( R.id.toolbar );
        mContext = getActivity();
        context = mContext;
        subheading = rootView.findViewById( R.id.new_exercise_subheading);
        subheading.setText( getString( R.string.edit_exercise ) );
        editExerciseBackButton = rootView.findViewById( R.id.new_exercise_back_button );
        editExerciseDoneButton = rootView.findViewById( R.id.new_exercise_done_button);
        editExerciseCategoryOneButton = rootView.findViewById( R.id.button_one );
        editExerciseCategoryTwoButton = rootView.findViewById( R.id.button_two );
        editExerciseCategoryThreeButton = rootView.findViewById( R.id.button_three );
        editExerciseCategoryFourButton = rootView.findViewById( R.id.button_four );
        editExerciseCategoryFiveButton = rootView.findViewById( R.id.button_five );
        editExerciseCategorySixButton = rootView.findViewById( R.id.button_six );
        categoriesFab = rootView.findViewById( R.id.category_name_cl );
        categoriesHiddenLayout = rootView.findViewById( R.id.categories_buttons );
        maxWeightTv = rootView.findViewById( R.id.exercise_max_weight_tv );
        maxWeightEditText = rootView.findViewById( R.id.exercise_max_weight_edit_text );
        startingWeightTv = rootView.findViewById( R.id.exercise_starting_weight_tv );
        startingWeightEditText= rootView.findViewById( R.id.exercise_starting_weight_edit_text );
        mediaSpinner = rootView.findViewById( R.id.exercise_media_spinner );
        youtubeConstraintLayout = rootView.findViewById( R.id.youtube_entry_cl );
        weightCheckbox = rootView.findViewById( R.id.exercise_type_weight );
        cardioCheckbox = rootView.findViewById( R.id.exercise_type_cardio );
        if (exerciseType==1){
            weightCheckbox.setChecked( false );
            cardioCheckbox.setChecked( true );
        }


        if (HomeScreen.kgValue==1){
            maxWeightTv.setText( R.string.exercise_max_weight_KG );
            maxWeightEditText.setHint( R.string.exercise_max_weight_hint_KG );
            startingWeightTv.setText( R.string.exercise_starting_weight_KG);
            startingWeightTv.setHint( R.string.exercise_starting_weight_hint_KG);
        }
        else {
            maxWeightTv.setText( R.string.exercise_max_weight_LBS );
            maxWeightEditText.setHint( R.string.exercise_max_weight_hint_LBS );
            startingWeightTv.setText( R.string.exercise_starting_weight_LBS);
            startingWeightTv.setHint( R.string.exercise_starting_weight_hint_LBS);
        }
        if (exerciseType == 1){
            if (HomeScreen.milesValue == 0){
                maxWeightTv.setText( getString( R.string.distance_km ) );
                maxWeightEditText.setHint( R.string.exercise_distance_hint_KM );}
            else {
                maxWeightTv.setText( getString( R.string.distance_miles ) );
                maxWeightEditText.setHint( R.string.exercise_distance_hint_miles );
            }
            startingWeightTv.setText( getString( R.string.time) );
            startingWeightEditText.setHint( getString( R.string.time_minutes) );
        }


        exerciseNameEditText = rootView.findViewById( R.id.exercise_name_edit_text );
        exerciseNameEditText.setText( exerciseToEdit.getExerciseName() );
        numberOfSetsEditText = rootView.findViewById( R.id.exercise_number_of_sets_edit_text);
        String numberOfSetsString = Integer.toString(exerciseToEdit.getNumberofSets());
        numberOfSetsEditText.setText(numberOfSetsString);
        maxWeightEditText = rootView.findViewById( R.id.exercise_max_weight_edit_text);
        startingWeightEditText = rootView.findViewById( R.id.exercise_starting_weight_edit_text);
        if (exerciseType==0){
            String maxWeightString = Integer.toString( exerciseToEdit.getMaxWeight() );
            maxWeightEditText.setText( maxWeightString);
            String startingWeightString = Integer.toString( exerciseToEdit.getStartingWeight() );
            startingWeightEditText.setText( startingWeightString);
        } else {
            String maxWeightString = Integer.toString( exerciseToEdit.getDistance() );
            maxWeightEditText.setText( maxWeightString );
            String startingWeightString = Integer.toString( exerciseToEdit.getTime() );
            startingWeightEditText.setText( startingWeightString );
        }
        notesEditText = rootView.findViewById( R.id.exercise_notes_edit_text);
        notesEditText.setText( exerciseToEdit.getNotes());
        mediaSpinner = rootView.findViewById( R.id.exercise_media_spinner);
        exerciseListMultiSpinner= rootView.findViewById( R.id.new_exercise_workout_list_spinner);
        addToWorkoutSpinner = rootView.findViewById( R.id.new_exercise_workout_list_spinner);

        editExerciseCategoryOneValue = exerciseToEdit.mCategoryOneValue;
        editExerciseCategoryTwoValue = exerciseToEdit.mCategoryTwoValue;
        editExerciseCategoryThreeValue = exerciseToEdit.mCategoryThreeValue;
        editExerciseCategoryFourValue = exerciseToEdit.mCategoryFourValue;
        editExerciseCategoryFiveValue = exerciseToEdit.mCategoryFiveValue;
        editExerciseCategorySixValue = exerciseToEdit.mCategorySixValue;

        if (editExerciseCategoryOneValue==1){
            editExerciseCategoryOneButton.setBackgroundColor( getResources().getColor(R.color.colorAccent ));
            editExerciseCategoryOneButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
        }
        if (editExerciseCategoryTwoValue==1){
            editExerciseCategoryTwoButton.setBackgroundColor( getResources().getColor(R.color.colorAccent ));
            editExerciseCategoryTwoButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
        }
        if (editExerciseCategoryThreeValue==1){
            editExerciseCategoryThreeButton.setBackgroundColor( getResources().getColor(R.color.colorAccent ));
            editExerciseCategoryThreeButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
        }
        if (editExerciseCategoryFourValue==1){
            editExerciseCategoryFourButton.setBackgroundColor( getResources().getColor(R.color.colorAccent ));
            editExerciseCategoryFourButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
        }
        if (editExerciseCategoryFiveValue==1){
            editExerciseCategoryFiveButton.setBackgroundColor( getResources().getColor(R.color.colorAccent ));
            editExerciseCategoryFiveButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
        }
        if (editExerciseCategorySixValue==1){
            editExerciseCategorySixButton.setBackgroundColor( getResources().getColor(R.color.colorAccent ));
            editExerciseCategorySixButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
        }


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = 0;
                if (v == editExerciseBackButton) {
                    position = 0;
                    mCallback.onEditExerciseButtonSelected( position );
                }else if (v == cardioCheckbox){
                    weightCheckbox.setChecked( false );
                    cardioCheckbox.setChecked( true );
                    exerciseType = 1;
                    if (HomeScreen.milesValue == 0){
                        maxWeightTv.setText( getString( R.string.distance_km ) );
                        maxWeightEditText.setHint( R.string.exercise_distance_hint_KM );}
                    else {
                        maxWeightTv.setText( getString( R.string.distance_miles ) );
                        maxWeightEditText.setHint( R.string.exercise_distance_hint_miles );
                    }
                    startingWeightTv.setText( getString( R.string.time) );
                    startingWeightEditText.setHint( getString( R.string.time_minutes) );
                }
                else if (v == weightCheckbox){
                    weightCheckbox.setChecked( true);
                    cardioCheckbox.setChecked( false );
                    exerciseType = 0;
                    if (HomeScreen.kgValue==1){
                        maxWeightTv.setText( R.string.exercise_max_weight_KG );
                        maxWeightEditText.setHint( R.string.exercise_max_weight_hint_KG );
                        startingWeightTv.setText( R.string.exercise_starting_weight_KG);
                        startingWeightEditText.setHint( R.string.exercise_starting_weight_hint_KG);
                    }
                    else {
                        maxWeightTv.setText( R.string.exercise_max_weight_LBS );
                        maxWeightEditText.setHint( R.string.exercise_max_weight_hint_LBS );
                        startingWeightTv.setText( R.string.exercise_starting_weight_LBS);
                        startingWeightEditText.setHint( R.string.exercise_starting_weight_hint_LBS);
                    }
                }
                 else if (v == editExerciseDoneButton) {
                    if (mediaType == 1) {
                        EditText youtubeEditText = rootView.findViewById( R.id.add_youtube_string_edit_text );
                        String editTextText = youtubeEditText.getText().toString();
                        if (editTextText.contains( "youtube.com/watch?v=" )) {
                            Exercise newExercise = updateExercise();
                            updateData( newExercise );
                            new ExerciseUpdateWorkoutLists( newExercise, mContext );
                            position = 1;
                            mCallback.onEditExerciseButtonSelected( position );
                        } else {
                            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder( getContext() );
                            builder.setCancelable( true );
                            builder.setTitle( R.string.invalid_youtube );
                            builder.setMessage( R.string.invalid_youtube_message );
                            builder.setPositiveButton( R.string.confirm,
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    } );
                            builder.setNegativeButton( R.string.cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            } );

                            android.support.v7.app.AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                        if (editExerciseCategoryOneValue == 0 && editExerciseCategoryTwoValue == 0 &&
                                editExerciseCategoryThreeValue == 0 && editExerciseCategoryFourValue == 0
                                && editExerciseCategoryFiveValue == 0 && editExerciseCategorySixValue == 0){
                            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder( getContext() );
                            builder.setCancelable( true );
                            builder.setTitle( R.string.no_category );
                            builder.setMessage( R.string.please_select_a_category );
                            builder.setPositiveButton( R.string.confirm,
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    } );
                            builder.setNegativeButton( R.string.cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            } );

                            android.support.v7.app.AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    }
                    else {
                        Exercise newExercise = updateExercise();
                        updateData( newExercise );
                        new ExerciseUpdateWorkoutLists( newExercise, mContext );
                        position = 1;
                        mCallback.onEditExerciseButtonSelected( position );
                    }
                }
            }
        };

        View.OnClickListener categoryButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == categoriesFab) {
                    if (categoriesFabValue==0){
                        categoriesFabValue = 1;
                        categoriesHiddenLayout.setVisibility( View.VISIBLE );
                    }
                    else if (categoriesFabValue==1){
                        categoriesFabValue = 0;
                        categoriesHiddenLayout.setVisibility( View.GONE);
                    }
                }
                else if (v == editExerciseCategoryOneButton) {
                    if (editExerciseCategoryOneValue==0){
                        editExerciseCategoryOneValue = 1;
                        editExerciseCategoryOneButton.setBackgroundColor( getResources().getColor(R.color.colorAccent ));
                        editExerciseCategoryOneButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        }
                    else {
                        editExerciseCategoryOneValue = 0;
                        editExerciseCategoryOneButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        editExerciseCategoryOneButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == editExerciseCategoryTwoButton) {
                    if (editExerciseCategoryTwoValue==0){
                        editExerciseCategoryTwoValue = 1;
                        editExerciseCategoryTwoButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        editExerciseCategoryTwoButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        }
                    else {
                        editExerciseCategoryTwoValue = 0;
                        editExerciseCategoryTwoButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        editExerciseCategoryTwoButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == editExerciseCategoryThreeButton) {
                    if (editExerciseCategoryThreeValue==0){
                        editExerciseCategoryThreeValue = 1;
                        editExerciseCategoryThreeButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        editExerciseCategoryThreeButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        }
                    else {
                        editExerciseCategoryThreeValue = 0;
                        editExerciseCategoryThreeButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        editExerciseCategoryThreeButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == editExerciseCategoryFourButton) {
                    if (editExerciseCategoryFourValue==0){
                        editExerciseCategoryFourValue = 1;
                        editExerciseCategoryFourButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        editExerciseCategoryFourButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        }
                    else {
                        editExerciseCategoryFourValue = 0;
                        editExerciseCategoryFourButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        editExerciseCategoryFourButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == editExerciseCategoryFiveButton) {
                    if (editExerciseCategoryFiveValue==0){
                        editExerciseCategoryFiveValue = 1;
                        editExerciseCategoryFiveButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        editExerciseCategoryFiveButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        }
                    else {
                        editExerciseCategoryFiveValue = 0;
                        editExerciseCategoryFiveButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        editExerciseCategoryFiveButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == editExerciseCategorySixButton) {
                    if (editExerciseCategorySixValue == 0) {
                        editExerciseCategorySixValue = 1;
                        editExerciseCategorySixButton.setBackgroundColor( getResources().getColor( R.color.colorAccent ) );
                        editExerciseCategorySixButton.setTextColor( getResources().getColor( R.color.spinnerWhite ) );
                    } else {
                        editExerciseCategorySixValue = 0;
                        editExerciseCategorySixButton.setBackgroundColor( getResources().getColor( R.color.colorHomeButton ) );
                        editExerciseCategorySixButton.setTextColor( getResources().getColor( R.color.textColor ) );
                    }
                }
            }
        };

        ArrayList<String> mSpinnerItems = new ArrayList<>(  );
        mSpinnerItems.add( getResources().getString( R.string.media_none ));
        mSpinnerItems.add( getResources().getString( R.string.media_one ));
        final ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, mSpinnerItems );
        myAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mediaSpinner.setAdapter(myAdapter);
        makeInvisible();

        myAdapter.notifyDataSetChanged();
        AdapterView.OnItemSelectedListener mediaSpinnerListener = new AdapterView.OnItemSelectedListener() {
            protected Adapter initializedAdapter = null;
            @Override
            public void onItemSelected(AdapterView<?> parentView,View selectedItemView, int position, long id) {
                if (initializedAdapter!= parentView.getAdapter()){
                    initializedAdapter = parentView.getAdapter();
                }
                if (position ==1 ) {
                    makeVisible();
                    mediaType = 1;
                }
                else {
                    makeInvisible();
                    mediaType = 0;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        };
        mediaSpinner.setOnItemSelectedListener( mediaSpinnerListener );

        mediaType = exerciseToEdit.getMediaType();
        if (mediaType ==1){
            mediaSpinner.setSelection(1);
            makeVisible();
            EditText youtubeEditText = rootView.findViewById( R.id.add_youtube_string_edit_text );
            String toEditFullURL = getString( R.string.youtube_url_start );
            toEditFullURL+=( exerciseToEdit.getMediaSource() );
            youtubeEditText.setText( toEditFullURL );
        }


        //Attaching the click listener to the buttons
        weightCheckbox.setOnClickListener( listener );
        cardioCheckbox.setOnClickListener( listener );
        editExerciseBackButton.setOnClickListener( listener );
        editExerciseDoneButton.setOnClickListener( listener );
        editExerciseCategoryOneButton.setOnClickListener( categoryButtonListener );
        editExerciseCategoryTwoButton.setOnClickListener( categoryButtonListener );
        editExerciseCategoryThreeButton.setOnClickListener( categoryButtonListener );
        editExerciseCategoryFourButton.setOnClickListener( categoryButtonListener );
        editExerciseCategoryFiveButton.setOnClickListener( categoryButtonListener );
        editExerciseCategorySixButton.setOnClickListener( categoryButtonListener );
        categoriesFab.setOnClickListener( categoryButtonListener );


        options = new ArrayList<>();
        if (HomeScreen.workoutsFromLoader!=null) {
            for (int i = 0; i < HomeScreen.workoutsFromLoader.size(); i++) {
                String newWorkout = HomeScreen.workoutsFromLoader.get( i ).getWorkoutName();
                options.add( newWorkout );
            }
        }
        final MultiSelectSpinner multiSelectSpinner = rootView.findViewById(R.id.new_exercise_workout_list_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter <String>(getActivity(), android.R.layout.simple_list_item_multiple_choice, options);

        multiSelectSpinner
                .setListAdapter(adapter);
        new MultiSelectSpinner.MultiSpinnerListener() {
            boolean [] checkStatus = checkState();
            @Override
            public void onItemsSelected(boolean[] checkList) {
                for (int x = 0; x<checkStatus.length; x++){
                    Log.e ("STATUS", String.valueOf(checkStatus[x]));
                    multiSelectSpinner.setSelection( x, checkStatus[x] );
                }}
        };

        HomeScreen.checkDisplayBanner(rootView, HomeScreen.removeAdvertsValue);

        return rootView;
    }

    EditExerciseFragment.onEditExerciseButtonSelected mCallback;

    //Interface for the click listener to send which button has been pushed to the home screen, and activate the fragment transaction
    public interface onEditExerciseButtonSelected {
        void onEditExerciseButtonSelected(int position);
    }

    //Attaching the interface to the MasterListFragment
    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        try {
            mCallback = (EditExerciseFragment.onEditExerciseButtonSelected) context;
        } catch (ClassCastException e) {
            throw new ClassCastException( context.toString() + "must implement click listener" );
        }
    }

    public Exercise updateExercise (){

        EditText exerciseNameEditText = rootView.findViewById( R.id.exercise_name_edit_text );
        String exerciseName = exerciseNameEditText.getText().toString();

        int categoryOneValue = editExerciseCategoryOneValue;
        int categoryTwoValue = editExerciseCategoryTwoValue;
        int categoryThreeValue = editExerciseCategoryThreeValue;
        int categoryFourValue = editExerciseCategoryFourValue;
        int categoryFiveValue = editExerciseCategoryFiveValue;
        int categorySixValue = editExerciseCategorySixValue;

        Log.e( "Media Source Origin", "not yet");
        String mediaSource = "";
        Log.e( "Media Source Origin", String.valueOf( mediaSource ) );
        EditText youTubeStringEditText = rootView.findViewById( R.id.add_youtube_string_edit_text );
        Log.e( "Media Source Origin", String.valueOf( mediaSource ) );
        String youTubeString = youTubeStringEditText.getText().toString();
        Log.e( "Media Source Origin", String.valueOf( mediaSource ) );
        if (mediaType ==1){
            mediaSource = stripDownYouTubeUrl(youTubeString);
            Log.e( "Media Source Origin", String.valueOf( mediaSource ) );
        }
        Log.e( "Media Source Origin", String.valueOf( mediaSource ) );

        EditText numberofSetsView = rootView.findViewById( R.id.exercise_number_of_sets_edit_text );
        String numberofSetsAsString = numberofSetsView.getText().toString();
        if (numberofSetsAsString.equals( "" )){
            numberofSetsAsString="0";
        }
        int numberofSets = Integer.parseInt(numberofSetsAsString);

        EditText maxWeightView = rootView.findViewById( R.id.exercise_max_weight_edit_text );
        String maxWeightAsString = maxWeightView.getText().toString();
        if (maxWeightAsString.equals( "" )){
            maxWeightAsString="0";
        }
        int maxWeight;
        int distance;
        if (exerciseType == 0) {
            maxWeight = Integer.parseInt( maxWeightAsString );
            distance = 0;
        }else {
            maxWeight = 0;
            distance = Integer.parseInt( maxWeightAsString );
        }

        EditText startingWeightView = rootView.findViewById( R.id.exercise_starting_weight_edit_text);
        String startingWeightAsString = startingWeightView.getText().toString();
        if (startingWeightAsString.equals( "" )){
            startingWeightAsString="0";
        }
        int startingWeight;
        int time;
        if (exerciseType==0) {
            startingWeight = Integer.parseInt( startingWeightAsString );
            time = 0;
        } else {
            startingWeight = 0;
            time = Integer.parseInt( startingWeightAsString );
        }

        boolean[] checked = addToWorkoutSpinner.getSelected();
        ArrayList<String>  addToWorkoutRawValues =  new ArrayList<String>();
        ArrayList<Integer>  workoutsToAdd =  new ArrayList<Integer>();
        int hasValue = 0;

        for (int i = 0; i < (checked.length); i++){
            if (checked[i]){
                if (hasValue == 1) {
                    String toAdd = options.get( i );
                    addToWorkoutRawValues.add( toAdd );
                    if (HomeScreen.workoutsFromLoader != null) {
                        for (int x = 0; x < HomeScreen.workoutsFromLoader.size(); x++) {
                            if (HomeScreen.workoutsFromLoader.get( x ).getWorkoutName().equals( toAdd )) {
                                int toAddId = HomeScreen.workoutsFromLoader.get( x ).getID();
                                workoutsToAdd.add( toAddId );
                            }
                        }
                    }
                }
                else {
                    String starterString = options.get( i );
                    addToWorkoutRawValues.clear();
                    addToWorkoutRawValues.add( starterString );
                    hasValue = 1;
                    if (HomeScreen.workoutsFromLoader != null) {
                        for (int x = 0; x < HomeScreen.workoutsFromLoader.size(); x++) {
                            if (HomeScreen.workoutsFromLoader.get( x ).getWorkoutName().equals( options.get( i ) )) {
                                int toAddId = HomeScreen.workoutsFromLoader.get( x ).getID();
                                workoutsToAdd.add( toAddId );
                            }
                        }
                    }
                }
            }
        }
        while (workoutsToAdd.size()<20) {
            workoutsToAdd.add( 0 );
        }

        EditText notesView = rootView.findViewById( R.id.exercise_notes_edit_text );
        String notes = notesView.getText().toString();

        Exercise exerciseToAdd = new Exercise( exerciseName, categoryOneValue, categoryTwoValue, categoryThreeValue,
                categoryFourValue, categoryFiveValue, categorySixValue, mediaSource, numberofSets, maxWeight,
                startingWeight, addToWorkoutRawValues, notes, context);
        exerciseToAdd.mWorkoutIds =workoutsToAdd;
        exerciseToAdd.mID = exerciseToEdit.mID;
        exerciseToAdd.mMediaType = mediaType;
        exerciseToAdd.mExerciseType = exerciseType;
        if (exerciseType==1) {
            exerciseToAdd.mDistance = distance;
            exerciseToAdd.mTime = time;
        }
        return exerciseToAdd;
    }

    public void updateData (Exercise exerciseToAdd){
        HomeScreen.exercise = ExerciseTableCleaner.cleanSingleExercise (context, exerciseToAdd);
        Log.e( "CLEANED", String.valueOf( HomeScreen.exercise.getMediaSource() ) );
    }


    public boolean [] checkState(){
        boolean[] checkList = addToWorkoutSpinner.getSelected();
        for (int i = 0; i < (checkList.length); i++) {
            String checklistOption = options.get( i );
            int toCompareAgainstId = 0;
            if (HomeScreen.workoutsFromLoader!=null) {
                for (int x = 0; x < HomeScreen.workoutsFromLoader.size(); x++) {
                    String toCompareAgainst = HomeScreen.workoutsFromLoader.get( x ).getWorkoutName();
                    if (toCompareAgainst.equals( checklistOption )) {
                        toCompareAgainstId = HomeScreen.workoutsFromLoader.get( x ).getID();
                    }
                }
            }
            if (toCompareAgainstId>0){
                if (toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 0 ) || toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 1 ) ||
                        toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 2 ) || toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 3 ) ||
                        toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 4 ) || toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 5 ) ||
                        toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 6 ) || toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 7 ) ||
                        toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 8 ) || toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 9 ) ||
                        toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 10 ) || toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 11 ) ||
                        toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 12 ) || toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 13 ) ||
                        toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 14 ) || toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 15 ) ||
                        toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 16 ) || toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 17 ) ||
                        toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 18 ) || toCompareAgainstId==exerciseToEdit.getWorkoutIds().get( 19 )){

                    checkList[i] = true;
                }
            }
            Log.e (String.valueOf( i ), String.valueOf(checkList[i]));
        }
        return checkList;
    }

    public void makeVisible(){
        youtubeConstraintLayout.setVisibility( View.VISIBLE);
    }

    public void makeInvisible(){
        youtubeConstraintLayout.setVisibility( View.GONE);
    }

    public String stripDownYouTubeUrl (String youTubeString){
        String condensedString;
        String toCondense = youTubeString;
        String subStrings[] = toCondense.split("=");
        Log.e( "substring", subStrings[subStrings.length-1] );
        condensedString = subStrings[subStrings.length-1];
        return condensedString;
    }

}
