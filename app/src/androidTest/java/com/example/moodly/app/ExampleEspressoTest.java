package com.example.moodly.app;

import android.support.test.espresso.ViewInteraction;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@LargeTest
public class ExampleEspressoTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public static final String MOODLY_ID = "bc16b958-d8c5-1004-8b85-dc068a7baf30";
    public static final String HOST = "moodly-alpha.herokuapp.com";

    public ExampleEspressoTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
        try {
            setupMoodlyIdAndHost(MOODLY_ID, HOST);
        } catch (Exception e) {}
    }

    @Override
    protected void tearDown() throws Exception {
        try {
            onView(withId(R.id.action_settings)).perform(click());
            setupMoodlyIdAndHost("", "");
        } catch (Exception e) {}
        super.tearDown();
    }

    public void testInitialViewIsDisplayed() {
        onView(withId(R.id.happy)).check(matches(isDisplayed()));
    }


    public void testSwipeRightViewFlipper() {
        onView(withId(R.id.happy)).check(matches(isDisplayed()));

        onView(withId(R.id.happy)).perform(swipeRight());

        onView(withId(R.id.fine)).check(matches(isDisplayed()));
    }

    public void testSwipeLeftViewFlipper() {
        onView(withId(R.id.happy)).check(matches(isDisplayed()));

        onView(withId(R.id.happy)).perform(swipeLeft());

        onView(withId(R.id.frustrated)).check(matches(isDisplayed()));
    }

    public void testDoubleClickMood() {
        onView(withId(R.id.moodViewFlipper)).perform(doubleClick());
        onView(withId(R.id.vote_label)).check(matches(isDisplayed()));

    }



    private void setupMoodlyIdAndHost(String moodlyId, String host) {
        onView(withId(R.id.settings_activity)).check(matches(isDisplayed()));
        onView(withId(R.id.moodlyIdValue)).perform(clearText());
        onView(withId(R.id.hostValue)).perform(clearText());
        onView(withId(R.id.moodlyIdValue)).perform(typeText(moodlyId));
        onView(withId(R.id.hostValue)).perform(typeText(host));
        onView(withId(R.id.updateSettings)).perform(click());
    }
}

