package com.ver2point0.android.blocquery;

import android.app.Application;

import com.parse.Parse;

public class BloQueryApplication extends Application {

    @Override
    public void onCreate() {
        // Enable Parse Local DataStore
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "KbF3oiduq4h9bsIBfycOW5pS5pXWfFyJvdOaTx71", "VEWWrRR4pk35VY1eXFYr0WPuky4kQ8RziMIlI8QS");
    }
}