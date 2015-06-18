package com.ver2point0.android.blocquery.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.text.util.Linkify;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseObject;
import com.ver2point0.android.blocquery.R;

import java.util.ArrayList;
import java.util.List;

/*
* Android login screen Activity
* */

public class BloQueryActivity extends Activity implements LoaderCallbacks<Cursor> {


    private static final String DUMMY_CREDENTIALS = "user@test.com:hello";

    private UserLoginTask mUserLoginTask = null;
    private View mLoginFormView;
    private View mProgressView;
    private AutoCompleteTextView mEmailTextView;
    private EditText mPasswordTextView;
    private TextView mSignUpTextView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloquery);

        // Testing Parse SDK
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

        mEmailTextView = (AutoCompleteTextView) findViewById(R.id.actv_email);
        loadAutoComplete();

        mPasswordTextView = (EditText) findViewById(R.id.et_password);
        mPasswordTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_NULL) {
                    initLogin();
                    return true;
                }
                return false;
            }
        });

        Button loginButton = (Button) findViewById(R.id.bt_login);
        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                initLogin();
            }
        });

        mLoginFormView = findViewById(R.id.sv_login_form);
        mProgressView = findViewById(R.id.pb_login_progress);

        //adding underling and link to signup textview
        mSignUpTextView = (TextView) findViewById(R.id.tv_sign_up);
        mSignUpTextView.setPaintFlags(mSignUpTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        Linkify.addLinks(mSignUpTextView, Linkify.ALL);

        mSignUpTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("LoginActivity", "Sign Up Activity activated.");
                // start signup Activity
                // BloQueryActivity.this.startActivity(new Intent(BloQueryActivity.this,
                // SignUpActivity.class));
            }
        });
    } // end onCreate() method

    private void loadAutoComplete() {
        getLoaderManager().initLoader(0, null, this);
    }


    /*
    * Validate login form and authenticate
    * */
    public void initLogin() {
        if (mUserLoginTask != null) {
            return;
        }

        mEmailTextView.setError(null);
        mPasswordTextView.setError(null);

        String email = mEmailTextView.getText().toString();
        String password = mPasswordTextView.getText().toString();

        boolean cancelLogin = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordTextView.setError(getString(R.string.invalid_password));
            focusView = mPasswordTextView;
            cancelLogin = true;
        }

        if (TextUtils.isEmpty(email)) {
            mEmailTextView.setError(getString(R.string.field_required));
            focusView = mEmailTextView;
            cancelLogin = true;
        } else if (!isEmailValid(email)) {
            mEmailTextView.setError(getString(R.string.invalid_email));
            focusView = mEmailTextView;
            cancelLogin = true;
        }

        if (cancelLogin) {
            // error in login
            focusView.requestFocus();
        } else {
            // show progress spinner, and start background task to login
            showProgress(true);
            mUserLoginTask = new UserLoginTask(email, password);
            mUserLoginTask.execute((Void) null);
        }
    } // end initLogin() method


    private boolean isEmailValid(String email) {
        // add your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        // add your own logic
        return password.length() > 4;
    }

    /*
    * Shows the progress UI and hides the login form
    **/
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY),
                ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<String>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }
        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {}

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(BloQueryActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);
        mEmailTextView.setAdapter(adapter);
    }

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };
        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Async Login Task to authenticate
     * */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String emailStr;
        private final String passwordStr;

        UserLoginTask(String email, String password) {
            emailStr = email;
            passwordStr = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            //this is where you should write your authentication code
            // or call external service
            // following try-catch just simulates network access

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            // using a local dummy credentials store to authenticate
            String[] pieces = DUMMY_CREDENTIALS.split(":");
            if (pieces[0].equals(emailStr) && pieces[1].equals(passwordStr)) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mUserLoginTask = null;
            // stop the progess spinner
            showProgress(false);

            if (success) {
                // login success and move to main Activity here.
            } else {
                // login failure
                mPasswordTextView.setError(getString(R.string.incorrect_password));
                mPasswordTextView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mUserLoginTask = null;
            showProgress(false);
        }


    } // end UserLoginTask class

} // end BloQuery class
