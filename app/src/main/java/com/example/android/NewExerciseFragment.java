package com.example.android;

import android.content.ContentValues;
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
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.android.Database.ExerciseUpdateWorkoutLists;
import com.example.android.free.R;
import java.util.ArrayList;
import io.apptik.widget.multiselectspinner.MultiSelectSpinner;
import static android.view.View.GONE;
import static com.example.android.Database.ExerciseContract.ExerciseTable.ADD_TO_WORKOUT;
import static com.example.android.Database.ExerciseContract.ExerciseTable.CATEGORY_FIVE_STATE;
import static com.example.android.Database.ExerciseContract.ExerciseTable.CATEGORY_FOUR_STATE;
import static com.example.android.Database.ExerciseContract.ExerciseTable.CATEGORY_ONE_STATE;
import static com.example.android.Database.ExerciseContract.ExerciseTable.CATEGORY_SIX_STATE;
import static com.example.android.Database.ExerciseContract.ExerciseTable.CATEGORY_THREE_STATE;
import static com.example.android.Database.ExerciseContract.ExerciseTable.CATEGORY_TWO_STATE;
import static com.example.android.Database.ExerciseContract.ExerciseTable.CONTENT_URI;
import static com.example.android.Database.ExerciseContract.ExerciseTable.DISTANCE;
import static com.example.android.Database.ExerciseContract.ExerciseTable.EXERCISE_NAME;
import static com.example.android.Database.ExerciseContract.ExerciseTable.EXERCISE_TYPE;
import static com.example.android.Database.ExerciseContract.ExerciseTable.MAX_WEIGHT;
import static com.example.android.Database.ExerciseContract.ExerciseTable.MEDIA_SOURCE;
import static com.example.android.Database.ExerciseContract.ExerciseTable.MEDIA_TYPE;
import static com.example.android.Database.ExerciseContract.ExerciseTable.MINUTES;
import static com.example.android.Database.ExerciseContract.ExerciseTable.NOTES;
import static com.example.android.Database.ExerciseContract.ExerciseTable.NUMBER_OF_SETS;
import static com.example.android.Database.ExerciseContract.ExerciseTable.STARTING_WEIGHT;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_EIGHT;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_EIGHTEEN;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_ELEVEN;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_FIFTEEN;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_FIVE;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_FOUR;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_FOURTEEN;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_NINE;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_NINETEEN;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_ONE;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_SEVEN;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_SEVENTEEN;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_SIX;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_SIXTEEN;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_TEN;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_THIRTEEN;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_THREE;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_TWELVE;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_TWENTY;
import static com.example.android.Database.ExerciseContract.ExerciseTable.WORKOUT_TWO;

public class NewExerciseFragment extends android.support.v4.app.Fragment{

    View rootView;
    ImageButton newExerciseBackButton;
    Button newExerciseDoneButton;
    Button newExerciseCategoryOneButton;
    Button newExerciseCategoryTwoButton;
    Button newExerciseCategoryThreeButton;
    Button newExerciseCategoryFourButton;
    Button newExerciseCategoryFiveButton;
    Button newExerciseCategorySixButton;
    ConstraintLayout categoriesFab;
    int newExerciseCategoryOneValue = 0;
    int newExerciseCategoryTwoValue = 0;
    int newExerciseCategoryThreeValue = 0;
    int newExerciseCategoryFourValue = 0;
    int newExerciseCategoryFiveValue = 0;
    int newExerciseCategorySixValue = 0;
    int categoriesFabValue = 0;
    ConstraintLayout categoriesHiddenLayout;
    Context mContext;
    TextView maxWeightTv;
    TextView startingWeightTv;
    static Context context;
    EditText startingWeightEditText;
    EditText maxWeightEditText;
    ArrayList <String> options;
    Spinner mediaSpinner;
    ConstraintLayout youtubeConstraintLayout;
    ConstraintLayout localVideoConstraintLayout;
    Button selectLocalVideoButton;
    int mediaType = 0;
    public static int newID;
    int exerciseType = 0;
    CheckBox weightCheckbox;
    CheckBox cardioCheckbox;
    Toolbar mToolbar;
    MultiSelectSpinner addToWorkoutSpinner;
    ScrollView scrollView;

    public NewExerciseFragment(){}

    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate( R.layout.new_exercise_fragment, container, false );
        mToolbar = rootView.findViewById( R.id.toolbar );
        mContext = getActivity();
        context = mContext;
        newExerciseBackButton = rootView.findViewById( R.id.new_exercise_back_button );
        newExerciseDoneButton = rootView.findViewById( R.id.new_exercise_done_button);
        newExerciseCategoryOneButton = rootView.findViewById( R.id.button_one );
        newExerciseCategoryTwoButton = rootView.findViewById( R.id.button_two );
        newExerciseCategoryThreeButton = rootView.findViewById( R.id.button_three );
        newExerciseCategoryFourButton = rootView.findViewById( R.id.button_four );
        newExerciseCategoryFiveButton = rootView.findViewById( R.id.button_five );
        newExerciseCategorySixButton = rootView.findViewById( R.id.button_six );
        weightCheckbox = rootView.findViewById( R.id.exercise_type_weight );
        cardioCheckbox = rootView.findViewById( R.id.exercise_type_cardio );
        categoriesFab = rootView.findViewById( R.id.category_name_cl );
        categoriesHiddenLayout = rootView.findViewById( R.id.categories_buttons );
        maxWeightTv = rootView.findViewById( R.id.exercise_max_weight_tv );
        startingWeightTv = rootView.findViewById( R.id.exercise_starting_weight_tv );
        maxWeightEditText = rootView.findViewById( R.id.exercise_max_weight_edit_text);
        startingWeightEditText = rootView.findViewById( R.id.exercise_starting_weight_edit_text );
        mediaSpinner = rootView.findViewById( R.id.exercise_media_spinner );
        youtubeConstraintLayout = rootView.findViewById( R.id.youtube_entry_cl );
        localVideoConstraintLayout = rootView.findViewById( R.id.local_video_entry_cl );
        selectLocalVideoButton = rootView.findViewById( R.id.local_video_button);
        addToWorkoutSpinner = rootView.findViewById( R.id.new_exercise_workout_list_spinner );
        scrollView = rootView.findViewById( R.id.scrollView3 );

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


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = 0;
                if (v == newExerciseBackButton) {
                    rootView.findFocus().clearFocus();
                    position = 0;
                    mCallback.onNewExerciseButtonSelected( position );
                    EditText stripYoutube = rootView.findViewById( R.id.add_youtube_string_edit_text );
                    String stripeDownYouTube = stripYoutube.getText().toString();
                    stripDownYouTubeUrl(stripeDownYouTube);
                } else if (v == cardioCheckbox){
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
                        rootView.findFocus().clearFocus();
                        maxWeightTv.setText( R.string.exercise_max_weight_LBS );
                        maxWeightEditText.setHint( R.string.exercise_max_weight_hint_LBS );
                        startingWeightTv.setText( R.string.exercise_starting_weight_LBS);
                        startingWeightEditText.setHint( R.string.exercise_starting_weight_hint_LBS);
                    }
                }
                else if (v == newExerciseDoneButton){
                    int proceed = 1;
                    if (mediaType==1){
                    EditText youtubeEditText = rootView.findViewById( R.id.add_youtube_string_edit_text );
                    String editTextText = youtubeEditText.getText().toString();
                        if (editTextText.contains( "youtube.com/watch?v=" )) {
                            Exercise newExercise = addExercise();
                            insertNewData( newExercise );
                            new ExerciseUpdateWorkoutLists( newExercise, mContext );
                            position = 1;
                            proceed = 0;
                            mCallback.onNewExerciseButtonSelected( position );
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
                            proceed = 0;
                            android.support.v7.app.AlertDialog dialog = builder.create();
                            dialog.show();
                    }
                    }
                        if (newExerciseCategoryOneValue == 0 && newExerciseCategoryTwoValue == 0 &&
                                newExerciseCategoryThreeValue == 0 && newExerciseCategoryFourValue == 0
                                && newExerciseCategoryFiveValue == 0 && newExerciseCategorySixValue == 0){
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
                            proceed = 0;
                            android.support.v7.app.AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    boolean[] checkingMax = addToWorkoutSpinner.getSelected();
                    int selectedItems = 0;
                        for (int l = 0; l<checkingMax.length; l++){
                            if (checkingMax[l]){
                                selectedItems++;
                            }
                        }
                    if (selectedItems>19){
                        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder( getContext() );
                        builder.setCancelable( true );
                        builder.setTitle( R.string.too_many_workouts );
                        builder.setMessage( R.string.please_reduce_workouts);
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
                        proceed = 0;
                        android.support.v7.app.AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                    if (proceed == 1) {
                        Exercise newExercise = addExercise();
                        insertNewData( newExercise );
                        new ExerciseUpdateWorkoutLists( newExercise, mContext );
                        position = 1;
                        mCallback.onNewExerciseButtonSelected( position );
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
                        categoriesHiddenLayout.setVisibility( GONE);
                    }
                }
                else if (v == newExerciseCategoryOneButton) {
                    if (newExerciseCategoryOneValue==0){
                        newExerciseCategoryOneValue = 1;
                        newExerciseCategoryOneButton.setBackgroundColor( getResources().getColor(R.color.colorAccent ));
                        newExerciseCategoryOneButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        }
                    else {
                        newExerciseCategoryOneValue = 0;
                        newExerciseCategoryOneButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategoryOneButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == newExerciseCategoryTwoButton) {
                    if (newExerciseCategoryTwoValue==0){
                        newExerciseCategoryTwoValue = 1;
                        newExerciseCategoryTwoButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        newExerciseCategoryTwoButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        }
                    else {
                        newExerciseCategoryTwoValue = 0;
                        newExerciseCategoryTwoButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategoryTwoButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == newExerciseCategoryThreeButton) {
                    if (newExerciseCategoryThreeValue==0){
                        newExerciseCategoryThreeValue = 1;
                        newExerciseCategoryThreeButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        newExerciseCategoryThreeButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        }
                    else {
                        newExerciseCategoryThreeValue = 0;
                        newExerciseCategoryThreeButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategoryThreeButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == newExerciseCategoryFourButton) {
                    if (newExerciseCategoryFourValue==0){
                        newExerciseCategoryFourValue = 1;
                        newExerciseCategoryFourButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        newExerciseCategoryFourButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        }
                    else {
                        newExerciseCategoryFourValue = 0;
                        newExerciseCategoryFourButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategoryFourButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == newExerciseCategoryFiveButton) {
                    if (newExerciseCategoryFiveValue==0){
                        newExerciseCategoryFiveValue = 1;
                        newExerciseCategoryFiveButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        newExerciseCategoryFiveButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                        }
                    else {
                        newExerciseCategoryFiveValue = 0;
                        newExerciseCategoryFiveButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategoryFiveButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }
                else if (v == newExerciseCategorySixButton) {
                    if (newExerciseCategorySixValue==0){
                        newExerciseCategorySixValue = 1;
                        newExerciseCategorySixButton.setBackgroundColor( getResources().getColor(R.color.colorAccent));
                        newExerciseCategorySixButton.setTextColor( getResources().getColor(R.color.spinnerWhite));
                    }
                    else {
                        newExerciseCategorySixValue = 0;
                        newExerciseCategorySixButton.setBackgroundColor( getResources().getColor(R.color.colorHomeButton ));
                        newExerciseCategorySixButton.setTextColor( getResources().getColor(R.color.textColor));
                    }
                }


            }
        };


        //Attaching the click listener to the buttons

        newExerciseBackButton.setOnClickListener( listener );
        newExerciseDoneButton.setOnClickListener( listener );
        selectLocalVideoButton.setOnClickListener( listener );
        categoriesFab.setOnClickListener( listener );
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
        newExerciseCategoryOneButton.setOnClickListener( categoryButtonListener );
        newExerciseCategoryTwoButton.setOnClickListener( categoryButtonListener );
        newExerciseCategoryThreeButton.setOnClickListener( categoryButtonListener );
        newExerciseCategoryFourButton.setOnClickListener( categoryButtonListener );
        newExerciseCategoryFiveButton.setOnClickListener( categoryButtonListener );
        newExerciseCategorySixButton.setOnClickListener( categoryButtonListener );
        cardioCheckbox.setOnClickListener( listener );
        weightCheckbox.setOnClickListener( listener );
        categoriesFab.setOnClickListener( categoryButtonListener );


        options = new ArrayList<>();
        if (HomeScreen.workoutsFromLoader!=null) {
            for (int i = 0; i < HomeScreen.workoutsFromLoader.size(); i++) {
                String newWorkout = HomeScreen.workoutsFromLoader.get( i ).getWorkoutName();
                options.add( newWorkout );
            }
        }
        MultiSelectSpinner multiSelectSpinner = rootView.findViewById(R.id.new_exercise_workout_list_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter <String>(getActivity(), android.R.layout.simple_list_item_multiple_choice, options);

        multiSelectSpinner
                .setListAdapter(adapter);
        new MultiSelectSpinner.MultiSpinnerListener() {
                    @Override
                    public void onItemsSelected(boolean[] checkedItems) {
                    }
                };

        HomeScreen.checkDisplayBanner(rootView, HomeScreen.removeAdvertsValue);

        return rootView;
    }

    NewExerciseFragment.onNewExerciseButtonSelected mCallback;

    //Interface for the click listener to send which button has been pushed to the home screen, and activate the fragment transaction
    public interface onNewExerciseButtonSelected {
        void onNewExerciseButtonSelected(int position);
    }

    //Attaching the interface to the MasterListFragment
    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        try {
            mCallback = (NewExerciseFragment.onNewExerciseButtonSelected) context;
        } catch (ClassCastException e) {
            throw new ClassCastException( context.toString() + "must implement click listener" );
        }
    }

    public Exercise addExercise (){

        EditText exerciseNameEditText = rootView.findViewById( R.id.exercise_name_edit_text );
        String exerciseName = exerciseNameEditText.getText().toString();

        int categoryOneValue = newExerciseCategoryOneValue;
        int categoryTwoValue = newExerciseCategoryTwoValue;
        int categoryThreeValue = newExerciseCategoryThreeValue;
        int categoryFourValue = newExerciseCategoryFourValue;
        int categoryFiveValue = newExerciseCategoryFiveValue;
        int categorySixValue = newExerciseCategorySixValue;

        String mediaSource;
        EditText youTubeStringEditText = rootView.findViewById( R.id.add_youtube_string_edit_text );
        String youTubeString = youTubeStringEditText.getText().toString();
        Log.e( "Media Source Origin", String.valueOf( mediaType ) );
        if (mediaType ==1){
            mediaSource = stripDownYouTubeUrl(youTubeString);
            Log.e( "Media Source Origin", String.valueOf( mediaSource ) );
        }
        else {mediaSource="";}

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
        ArrayList<Integer> workoutsExerciseIsOn = new ArrayList<>( );
        if (HomeScreen.workoutsFromLoader!=null) {
            int hasValue = 0;
            for (int i = 0; i < (checked.length); i++) {
                if (checked[i] == true) {
                    if (hasValue == 1) {
                        String toAdd = options.get( i );
                        addToWorkoutRawValues.add( toAdd );
                    } else {
                        String starterString = options.get( i );
                        addToWorkoutRawValues.clear();
                        addToWorkoutRawValues.add( starterString );
                        hasValue = 1;
                    }
                    workoutsExerciseIsOn.add( HomeScreen.workoutsFromLoader.get( i ).getID());
                }
            }
        }

        while (workoutsExerciseIsOn.size()<20){
            workoutsExerciseIsOn.add( 0 );
        }

        EditText notesView = rootView.findViewById( R.id.exercise_notes_edit_text );
        String notes = notesView.getText().toString();

        Exercise exerciseToAdd = new Exercise( exerciseName, categoryOneValue, categoryTwoValue, categoryThreeValue,
                categoryFourValue, categoryFiveValue, categorySixValue, mediaSource, numberofSets, maxWeight,
                startingWeight, addToWorkoutRawValues, notes, context, workoutsExerciseIsOn);
        exerciseToAdd.mMediaType = mediaType;
        exerciseToAdd.mExerciseType = exerciseType;
        if (exerciseType==1) {
            exerciseToAdd.mDistance = distance;
            exerciseToAdd.mTime = time;
        }

        return exerciseToAdd;
    }

    public void insertNewData (Exercise exerciseToAdd){
        ContentValues contentValues = newContentValues(exerciseToAdd);
        mContext.getContentResolver().insert( CONTENT_URI, contentValues);
        exerciseToAdd.mID = newID;
    }

    public static ContentValues newContentValues (Exercise exerciseToAdd) {
        ContentValues contentValues = new ContentValues(  );
        contentValues.put( EXERCISE_NAME, exerciseToAdd.getExerciseName());
        contentValues.put( EXERCISE_TYPE, exerciseToAdd.getExerciseType() );
        contentValues.put( CATEGORY_ONE_STATE, exerciseToAdd.getCategoryOneValue() );
        contentValues.put( CATEGORY_TWO_STATE, exerciseToAdd.getCategoryTwoValue() );
        contentValues.put( CATEGORY_THREE_STATE, exerciseToAdd.getCategoryThreeValue() );
        contentValues.put( CATEGORY_FOUR_STATE, exerciseToAdd.getCategoryFourValue() );
        contentValues.put( CATEGORY_FIVE_STATE, exerciseToAdd.getCategoryFiveValue() );
        contentValues.put( CATEGORY_SIX_STATE, exerciseToAdd.getCategorySixValue() );
        contentValues.put (MEDIA_TYPE, exerciseToAdd.getMediaType());
        contentValues.put( MEDIA_SOURCE, exerciseToAdd.getMediaSource() );
        contentValues.put( NUMBER_OF_SETS, exerciseToAdd.getNumberofSets() );
        contentValues.put( MAX_WEIGHT, exerciseToAdd.getMaxWeight() );
        contentValues.put( STARTING_WEIGHT, exerciseToAdd.getStartingWeight() );
        contentValues.put( DISTANCE, exerciseToAdd.getDistance() );
        contentValues.put( MINUTES, exerciseToAdd.getTime() );
        contentValues.put( ADD_TO_WORKOUT, exerciseToAdd.getAddToWorkout() );
        contentValues.put( NOTES, exerciseToAdd.getNotes() );
        int i = 0;
        contentValues.put( WORKOUT_ONE, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_TWO, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_THREE, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_FOUR, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_FIVE, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_SIX, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_SEVEN, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_EIGHT, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_NINE, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_TEN, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_ELEVEN, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_TWELVE, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_THIRTEEN, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_FOURTEEN, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_FIFTEEN, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_SIXTEEN, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_SEVENTEEN, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_EIGHTEEN, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_NINETEEN, exerciseToAdd.getWorkoutIds().get(i) );
        i++;
        contentValues.put( WORKOUT_TWENTY, exerciseToAdd.getWorkoutIds().get(i) );
        return contentValues;
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

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored( savedInstanceState );
        if (savedInstanceState!=null) {
            if (savedInstanceState.containsKey( "scrollViewEditOrNewExercise" )) {
                scrollView.setScrollY( savedInstanceState.getInt( "scrollViewEditOrNewExercise" ) );
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState( outState );
        outState.putInt( "scrollViewEditOrNewExercise", scrollView.getScrollY() );
    }
}


