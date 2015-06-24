package com.ver2point0.android.blocquery;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

public class BloQueryApplication extends Application {

    private static BloQueryApplication mSharedInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        mSharedInstance = this;

        // Parse initialization
        Parse.initialize(this, getString(R.string.parse_app_id), getString(R.string.parse_app_key));

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }

    public static BloQueryApplication getSharedInstance() {
        return mSharedInstance;
    }


}