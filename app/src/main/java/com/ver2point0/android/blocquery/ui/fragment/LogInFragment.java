package com.ver2point0.android.blocquery.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.ver2point0.android.bloquery.R;


public class LogInFragment extends Fragment {

    private TextView mLoginFailed;
    private EditText mUsername;
    private EditText mPassword;
    private Button mLogin;
    private Button mSignup;

    public LogInFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mUsername = (EditText) view.findViewById(R.id.et_username);
        mPassword = (EditText) view.findViewById(R.id.et_password);
        mLogin = (Button) view.findViewById(R.id.bt_login);
        mSignup = (Button) view.findViewById(R.id.bt_signup);
        mLoginFailed = (TextView) view.findViewById(R.id.tv_login_failed);

        // login button listener
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin(mUsername.getText().toString(), mPassword.getText().toString());
            }
        });

        // sign up button listener
        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // switch to SignUpFragment
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new SignUpFragment(), "SignUpFragment")
                        .commit();
            }
        });

        return view;
    }

    private void userLogin(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (parseUser != null) {
                    // successful login, go to QuestionFeedFragment
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new QuestionFeedFragment(), "QuestionFeedFragment")
                            .commit();
                } else {
                    // login failed, login failed text visible
                    mLoginFailed.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}
