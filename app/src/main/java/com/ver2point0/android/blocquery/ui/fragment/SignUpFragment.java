package com.ver2point0.android.blocquery.ui.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.ver2point0.android.bloquery.R;

public class SignUpFragment extends Fragment {

    private EditText mUsername;
    private EditText mFirstname;
    private EditText mLastname;
    private EditText mPassword;
    private Button mSignUp;

    public SignUpFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        mUsername = (EditText) view.findViewById(R.id.et_username);
        mFirstname = (EditText) view.findViewById(R.id.et_firstname);
        mLastname = (EditText) view.findViewById(R.id.et_lastname);
        mPassword = (EditText) view.findViewById(R.id.et_password);
        mSignUp = (Button) view.findViewById(R.id.bt_signup);

        // sign up button listener
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsername.getText().toString();
                String firstname = mFirstname.getText().toString();
                String lastname = mLastname.getText().toString();
                String password = mPassword.getText().toString();

                // fields cannot be null
                if ((username != null) && (firstname != null) && (password != null) && (lastname != null)) {

                    // create new parse user
                    ParseUser parseUser = new ParseUser();
                    parseUser.setUsername(username);
                    parseUser.setPassword(password);
                    parseUser.put("first_name", firstname);
                    parseUser.put("last_name", lastname);

                    // try to signup
                    parseUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                // created user successfully
                                getFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.container, new QuestionFeedFragment(), "QuestionFeedFragment")
                                        .commit();
                            } else {
                                // error
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
        return view;

    } // end method() onCreateView()

} // end class signup fragment
