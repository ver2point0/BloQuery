package com.ver2point0.android.blocquery.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseUser;
import com.ver2point0.android.bloquery.R;
import com.ver2point0.android.blocquery.ui.fragment.LogInFragment;
import com.ver2point0.android.blocquery.ui.fragment.QuestionFeedFragment;

public class BloQueryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloquery);

       // check whether a user is logged in
        ParseUser parseUser = ParseUser.getCurrentUser();
        if (parseUser != null) {
            // show feed QuestionFeedFragment
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new QuestionFeedFragment(), "QuestionFeedFragment")
                    .commit();
        } else {
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
