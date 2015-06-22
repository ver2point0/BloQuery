package com.ver2point0.android.blocquery.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.ver2point0.android.blocquery.R;

/*
* Android login screen Activity
* */

public class BloQueryActivity extends Activity {

    private Button mLoginButton;
    private Button mSignUpButton;
    private String mUserName;
    private String mPassword;
    private EditText mUserNameText;
    private EditText mPasswordText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloquery);

        mUserNameText = (EditText) findViewById(R.id.et_username);
        mPasswordText = (EditText) findViewById(R.id.et_password);

        mLoginButton = (Button) findViewById(R.id.bt_login);
        mSignUpButton = (Button) findViewById(R.id.bt_signup);

        // login button onClickListener
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // retrieve text from EditText
                mUserName = mUserNameText.getText().toString();
                mPassword = mPasswordText.getText().toString();

                // send to parse for verification
                ParseUser.logInInBackground(mUserName, mPassword,
                        new LogInCallback() {
                            @Override
                            public void done(ParseUser parseUser, ParseException e) {
                                if (parseUser != null) {
                                    // if parseUser exists and authenticated, send parseUser to Questions.class
                                    Intent intent = new Intent(BloQueryActivity.this, QuestionsActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(getApplicationContext(), getString(R.string.login_success), Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), getString(R.string.user_not_exist), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        // sign up button onClickListener
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // retrieve text from EditText
                mUserName = mUserNameText.getText().toString();
                mPassword = mPasswordText.getText().toString();

                // force user to fill out form
                if (mUserName.equals("") && mPassword.equals("")) {
                    Toast.makeText(getApplicationContext(), getString(R.string.complete_signup_form), Toast.LENGTH_SHORT).show();
                } else {
                    // save new user data into parse.com
                    ParseUser parseUser = new ParseUser();
                    parseUser.setUsername(mUserName);
                    parseUser.setPassword(mPassword);
                    parseUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                // show successful login
                                Toast.makeText(getApplicationContext(), getString(R.string.successful_login), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), getString(R.string.signup_error), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


    } // end onCreate() method

} // end BloQueryActivity class
