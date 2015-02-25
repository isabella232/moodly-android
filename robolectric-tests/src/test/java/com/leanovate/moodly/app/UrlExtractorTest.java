package com.leanovate.moodly.app;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

/**
 * Created by claus on 09/02/15.
 */
@RunWith(JUnit4.class)
public class UrlExtractorTest {

    private static final String MOODLY_ID = "moodly-id";
    private UrlExtractor urlExtractor;


    @Before
    public void setup() {
        urlExtractor = new UrlExtractor();
    }
    @Test
    public void shouldNotFail() {
        //do not fail
    }

    @Test
    public void shouldExtractMoodlyId() {
        String path = "/#/voting/" + MOODLY_ID;

        String moodlyID = urlExtractor.extractMoodlyId(path);

        assertThat(moodlyID, is(equalTo(MOODLY_ID)));

    }

    @Test
    public void shouldExtractMoodlyIdWithBackslashAtEndOfPath() {
        String path = "/#/voting/" + MOODLY_ID + "/";

        String moodlyID = urlExtractor.extractMoodlyId(path);

        assertThat(moodlyID, is(equalTo(MOODLY_ID)));

    }

    @Test
    public void testDataObject() throws URISyntaxException {
        URI data = new URI("http://moodly-alpha.herokuapp.com/#/voting/" + MOODLY_ID);
        String moodlyId = urlExtractor.extractMoodlyId(data.getFragment());

        assertThat(moodlyId,is(equalTo(MOODLY_ID)));

    }

}
