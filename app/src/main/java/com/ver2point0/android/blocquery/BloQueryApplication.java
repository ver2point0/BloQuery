package com.ver2point0.android.blocquery;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

public class BloQueryApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Parse initialization
        Parse.initialize(this, "KbF3oiduq4h9bsIBfycOW5pS5pXWfFyJvdOaTx71", "VEWWrRR4pk35VY1eXFYr0WPuky4kQ8RziMIlI8QS");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }
}