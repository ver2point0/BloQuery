package com.ver2point0.android.blocquery;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;
import com.ver2point0.android.blocquery.api.DataSource;

public class BloQueryApplication extends Application {

    private static BloQueryApplication mSharedInstance;
    private DataSource mDataSource;

    @Override
    public void onCreate() {
        super.onCreate();
        mSharedInstance = this;
        mDataSource = new DataSource();

        // Parse initialization
        Parse.initialize(this, "KbF3oiduq4h9bsIBfycOW5pS5pXWfFyJvdOaTx71", "VEWWrRR4pk35VY1eXFYr0WPuky4kQ8RziMIlI8QS");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }

    public static BloQueryApplication getSharedInstance() {
        return mSharedInstance;
    }

    public DataSource getDataSource() {
        return mDataSource;
    }

    public static DataSource getSharedDataSource() {
        return BloQueryApplication.getSharedInstance().getDataSource();
    }

}