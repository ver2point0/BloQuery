package com.ver2point0.android.blocquery.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseUser;
import com.ver2point0.android.blocquery.ui.fragment.LogInFragment;
import com.ver2point0.android.blocquery.ui.fragment.QuestionFeedFragment;
import com.ver2point0.android.bloquery.R;

public class BloQueryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloquery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        1) make your Toolbar a member variable of BloqueryActivity
//        2) create method called makeToolbarVisible
//        3) In your fragment, cast getActivity as your BloqueryActiivty
//        Call the makeToolbarVisible method from Fragment
//        If you have any problems, let me know

//        Move the logic to visify or invisify your Toolbar to onResume
//        Use flags for which fragment you're showing, and then just check if you're currently showing the login
//        If you are, make Toolbar gone
//        If you aren't, make it visible

       // check whether a user is logged in
        ParseUser parseUser = ParseUser.getCurrentUser();
        if (parseUser != null) {
            //toolbar.setVisibility(View.VISIBLE);
            // show feed QuestionFeedFragment
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new QuestionFeedFragment(), "QuestionFeedFragment")
                    .commit();
        } else {
            //toolbar.setVisibility(View.GONE);
            // show LogInFragment
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new LogInFragment(), "LogInFragment")
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflate the menu; this add items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_bloquery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_logout:
                userLogout();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void userLogout() {
        ParseUser.logOut();
        LogInFragment logInFragment = (LogInFragment) getFragmentManager().findFragmentByTag("LogInFragment");
        if (logInFragment == null) {
            logInFragment = new LogInFragment();
        }
        // switch to LogInFragment
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, logInFragment, "LogInFragment")
                .commit();
    }
}
