package com.example.android.flexercise;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented com.example.android.flexercise.test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under com.example.android.flexercise.test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals( "com.example.android.flexercise", appContext.getPackageName() );
    }
}
