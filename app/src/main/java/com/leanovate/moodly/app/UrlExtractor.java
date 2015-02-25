package com.leanovate.moodly.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by claus on 16/02/15.
 */
public class UrlExtractor {

    private Pattern moodlyIdPattern = Pattern.compile("voting/([^/]*)/?");

    public String extractMoodlyId(String path) {

        Matcher matcher = moodlyIdPattern.matcher(path);
        if (matcher.find())
        {
            return matcher.group(1);
        }
        return "";
    }


}
