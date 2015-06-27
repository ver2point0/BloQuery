package com.ver2point0.android.blocquery;

import android.app.Application;

import com.parse.Parse;
import com.ver2point0.android.bloquery.R;

public class BloQueryApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, getString(R.string.parse_app_id), getString(R.string.parse_client_key));

    }
}
