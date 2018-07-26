package com.example.android.Database.WorkoutsDatabase;

import android.net.Uri;
import android.provider.BaseColumns;

public class WorkoutContract {

        public static final String AUTHORITY = "com.example.android.Database.WorkoutsDatabase.WorkoutContentProvider";
        public static final Uri BASE_CONTENT_URI = Uri.parse( "content://"+AUTHORITY );
        public static final String PATH_WORKOUTS = "workouts";

        public static final class WorkoutsTable implements BaseColumns {

            public static final Uri WORKOUT_CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_WORKOUTS).build();

            public static final String WORKOUT_TABLE_NAME = "workouts";
            public static final String WORKOUT_NAME = "workoutName";

            public static final String CATEGORY_ONE_STATE = "categoryOneState";
            public static final String CATEGORY_TWO_STATE = "categoryTwoState";
            public static final String CATEGORY_THREE_STATE = "categoryThreeState";
            public static final String CATEGORY_FOUR_STATE = "categoryFourState";
            public static final String CATEGORY_FIVE_STATE = "categoryFiveState";
            public static final String CATEGORY_SIX_STATE = "categorySixState";


            public static final String EXERCISE_ONE_ID = "exerciseOneID";
            public static final String EXERCISE_TWO_ID = "exerciseTwoID";
            public static final String EXERCISE_THREE_ID = "exerciseThreeID";
            public static final String EXERCISE_FOUR_ID = "exerciseFourID";
            public static final String EXERCISE_FIVE_ID = "exerciseFiveID";
            public static final String EXERCISE_SIX_ID = "exerciseSixID";
            public static final String EXERCISE_SEVEN_ID = "exerciseSevenID";
            public static final String EXERCISE_EIGHT_ID = "exerciseEightID";
            public static final String EXERCISE_NINE_ID = "exerciseNineID";
            public static final String EXERCISE_TEN_ID = "exerciseTenID";
            public static final String EXERCISE_ELEVEN_ID = "exerciseElevenID";
            public static final String EXERCISE_TWELVE_ID = "exerciseTwelveID";
            public static final String EXERCISE_THIRTEEN_ID = "exerciseThirteenID";
            public static final String EXERCISE_FOURTEEN_ID = "exerciseFourteenID";
            public static final String EXERCISE_FIFTEEN_ID = "exerciseFifteenID";
            public static final String EXERCISE_SIXTEEN_ID = "exerciseSixteenID";
            public static final String EXERCISE_SEVENTEEN_ID = "exerciseSeventeenID";
            public static final String EXERCISE_EIGHTEEN_ID = "exerciseEighteenID";
            public static final String EXERCISE_NINETEEN_ID = "exerciseNineteenID";
            public static final String EXERCISE_TWENTY_ID = "exerciseTwentyID";

        }
}
