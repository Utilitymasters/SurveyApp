package com.survey.app;

import android.app.Application;


import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by rohit.anvekar on 5/9/2017.
 */

public class SurveyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/comicsanslight.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
}
