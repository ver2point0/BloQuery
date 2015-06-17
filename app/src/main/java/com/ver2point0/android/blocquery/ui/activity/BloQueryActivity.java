package com.ver2point0.android.blocquery.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import com.ver2point0.android.blocquery.R;


/*
* Android login screen Activity
* */

public class BloQueryActivity extends Activity {

//    implements LoaderManager.LoaderCallbacks<Cursor>
    private static final String DUMMY_CREDENTIALS = "user@test.com:hello";

//    private UserLoginTask mUserLoginTask = null;
//    private View mLoginFormView;
//    private View mProgressView;
//    private AutoCompleteTextView mEmailTextView;
//    private EditText mPasswordTextView;
//    private TextView mSignUpTextView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloquery);

//        mEmailTextView = (AutoCompleteTextView) findViewById(R.id.actv_email);
//        loadAutoComplete();
//
//        mPasswordTextView = (EditText) findViewById(R.id.et_password);
//        mPasswordTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
//                if (id == EditorInfo.IME_NULL) {
//                    initLogin();
//                    return true;
//                }
//                return false;
//            }
//        });





    }


}
