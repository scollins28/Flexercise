package com.example.android.flexercise.Database;

import android.net.Uri;
import android.provider.BaseColumns;

public class ExerciseContract {

        public static final String AUTHORITY = "com.example.android.flexercise";
        public static final Uri BASE_CONTENT_URI = Uri.parse( "content://"+AUTHORITY );
        public static final String PATH_EXERCISES = "exercises";

        public static final class ExerciseTable implements BaseColumns {
            public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_EXERCISES).build();

            public static final String TABLE_NAME = "exercises";
            public static final String EXERCISE_NAME = "exerciseName";

            public static final String CATEGORY_ONE_STATE = "categoryOneState";
            public static final String CATEGORY_TWO_STATE = "categoryTwoState";
            public static final String CATEGORY_THREE_STATE = "categoryThreeState";
            public static final String CATEGORY_FOUR_STATE = "categoryFourState";
            public static final String CATEGORY_FIVE_STATE = "categoryFiveState";
            public static final String CATEGORY_SIX_STATE = "categorySixState";

            public static final String MEDIA_SOURCE = "mediaSource";

            public static final String NUMBER_OF_SETS = "numberOfSets";
            public static final String MAX_WEIGHT = "maxWeight";
            public static final String STARTING_WEIGHT = "startingWeight";

            public static final String ADD_TO_WORKOUT = "workoutsToAddTo";

            public static final String NOTES = "notes";

            public static final String WORKOUT_ONE = "workoutOneId";
            public static final String WORKOUT_TWO = "workoutTwoId";
            public static final String WORKOUT_THREE = "workoutThreeId";
            public static final String WORKOUT_FOUR = "workoutFourId";
            public static final String WORKOUT_FIVE = "workoutFiveId";
            public static final String WORKOUT_SIX = "workoutSixId";
            public static final String WORKOUT_SEVEN = "workoutSevenId";
            public static final String WORKOUT_EIGHT = "workoutEightId";
            public static final String WORKOUT_NINE = "workoutNineId";
            public static final String WORKOUT_TEN = "workoutTenId";
            public static final String WORKOUT_ELEVEN = "workoutElevenId";
            public static final String WORKOUT_TWELVE = "workoutTwelveId";
            public static final String WORKOUT_THIRTEEN = "workoutThirteenId";
            public static final String WORKOUT_FOURTEEN = "workoutFourteenId";
            public static final String WORKOUT_FIFTEEN = "workoutFifteenId";
            public static final String WORKOUT_SIXTEEN = "workoutSixteenId";
            public static final String WORKOUT_SEVENTEEN = "workoutSeventeenId";
            public static final String WORKOUT_EIGHTEEN = "workoutEighteenId";
            public static final String WORKOUT_NINETEEN = "workoutNineteenId";
            public static final String WORKOUT_TWENTY = "workoutTwentyId";

        }
}
