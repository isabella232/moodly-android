package com.leanovate.moodly.app;

import android.widget.ImageView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(RobolectricTestRunner.class)
@Config( emulateSdk = 18, reportSdk = 18, manifest = "../app/src/main/AndroidManifest.xml")
public class MainActivityTest {

    private ActivityController<MainActivity> activityController;

    @Before
    public void createControllers() {
        activityController = Robolectric.buildActivity(MainActivity.class);
    }

    @Test
    public void shouldStartActivityWithHappyMood() {
        MainActivity activity = activityController.create().resume().get();

        ImageView imageView = (ImageView) activity.findViewById(R.id.happy);

        assertThat(imageView, is(notNullValue()));
    }
}
