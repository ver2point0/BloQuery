package com.ver2point0.android.blocquery.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.parse.ParseObject;
import com.ver2point0.android.bloquery.R;

public class QuestionFragment extends Fragment {

    private ParseObject mQuestion;

    public void setQuestion(ParseObject question) {
        mQuestion = question;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (menu.findItem(R.id.action_new_answer) == null) {
            menuInflater.inflate(R.menu.menu_question, menu);
        }
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_new_answer) {
            return true;
        } else {
            return super.onOptionsItemSelected(menuItem);
        }
    }
}
