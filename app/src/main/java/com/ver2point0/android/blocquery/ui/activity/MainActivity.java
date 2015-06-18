package com.ver2point0.android.blocquery.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;


public class MainActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Determine whether the current user is an anonymous user
        if (ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {
            // If user is anonymous, send the user to BloQueryActivity.class
            Intent intent = new Intent(MainActivity.this,
                    BloQueryActivity.class);
            startActivity(intent);
            finish();
        } else {
            // If current user is NOT anonymous user
            // Get current user data from Parse.com
            ParseUser currentUser = ParseUser.getCurrentUser();
            if (currentUser != null) {
                // Send logged in users to WelcomeActivity.class
                Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                // Send user to BloQueryActivity.class
                Intent intent = new Intent(MainActivity.this,
                        BloQueryActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
