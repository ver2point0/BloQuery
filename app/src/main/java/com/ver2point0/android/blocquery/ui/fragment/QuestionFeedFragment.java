package com.ver2point0.android.blocquery.ui.fragment;

import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.ver2point0.android.blocquery.adapter.QuestionFeedAdapter;
import com.ver2point0.android.bloquery.R;

import java.util.ArrayList;
import java.util.List;

public class QuestionFeedFragment extends Fragment {

    private ArrayList<ParseObject> mQuestions = new ArrayList<ParseObject>();
    private QuestionFeedAdapter mQuestionFeedAdapter;


    public QuestionFeedFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_feed, container, false);
        ListView questionsList = (ListView) view.findViewById(R.id.lv_question_feed);

        mQuestionFeedAdapter = new QuestionFeedAdapter(getActivity(), 0, mQuestions);
        questionsList.setAdapter(mQuestionFeedAdapter);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Question");
        query.orderByDescending("createdAt");
        query.setLimit(10);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjectsList, ParseException e) {
                if (e == null) {
                    mQuestions.addAll(parseObjectsList);
                    mQuestionFeedAdapter.notifyDataSetChanged();
                } else {
                    e.printStackTrace();
                }
            }
        });

        questionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ParseObject singleQuestion = mQuestions.get(position);
                QuestionFragment questionFragment = (QuestionFragment) getFragmentManager().findFragmentByTag("QuestionFragment");
                if (questionFragment == null) {
                    questionFragment = new QuestionFragment();
                }
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (menu.findItem(R.id.action_new_question) == null) {
            menuInflater.inflate(R.menu.menu_feed, menu);
        }
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_new_question) {
            NewQuestionDialogFragment newQuestionDialogFragment = new NewQuestionDialogFragment();
            newQuestionDialogFragment.setListener(new NewQuestionDialogFragment().NewQuestionListener() {
                @Override
                public void onNewQuestionConfirm(DialogFragment dialogFragment, String question) {
                    ParseObject question = new ParseObject("Question");
                    question.put("question", question);
                    question.put("user", ParseUser.getCurrentUser());
                    question.saveInBackground();

                    mQuestionFeedAdapter.add(question);
                }
            });
            dialogFragment.show(getFragmentManager(), "NewQuestionDialog");
            return true;
        } else {
            return super.onOptionsItemSelected(menuItem);
        }
    }

}
