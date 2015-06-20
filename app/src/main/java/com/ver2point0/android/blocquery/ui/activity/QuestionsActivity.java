package com.ver2point0.android.blocquery.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;
import com.ver2point0.android.blocquery.R;

public class QuestionsActivity extends Activity{

    private Button mLogOut;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        // Retrieve current user from Parse.com
        ParseUser currentUser = ParseUser.getCurrentUser();
        // Convert currentUser into String
        String userString = currentUser.getUsername().toString();
        // Locate TextView in activity_questions
        TextView userText = (TextView) findViewById(R.id.tv_username);
        // Set the currentUser String into TextView
        userText.setText(userString);

        // Locate Button in activity_questions
        mLogOut = (Button) findViewById(R.id.bt_logout);
        // Logout Button Click Listener
        mLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logout current user
                ParseUser.logOut();
                Toast.makeText(getApplicationContext(), getString(R.string.logged_out), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
