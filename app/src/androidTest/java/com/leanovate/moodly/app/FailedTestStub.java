package com.leanovate.moodly.app;


import com.leanovate.moodly.app.MainActivity;
import junit.framework.TestCase;

public class FailedTestStub extends TestCase {

    private MainActivity mainActivity;

    public void failTestSomething() {
        mainActivity = new MainActivity();
    }
}
