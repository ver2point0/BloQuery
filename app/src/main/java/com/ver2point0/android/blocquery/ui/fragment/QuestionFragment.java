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
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.ver2point0.android.blocquery.adapter.AnswerAdapter;
import com.ver2point0.android.bloquery.R;

import java.util.ArrayList;
import java.util.List;

public class QuestionFragment extends Fragment {

    private ParseObject mQuestion;
    private ArrayList<ParseObject> mAnswers = new ArrayList<ParseObject>();
    private AnswerAdapter mAnswerAdapter;

    public QuestionFragment() {}

    public void setQuestion(ParseObject question) {
        mQuestion = question;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_question, viewGroup, false);

        TextView userTextView = (TextView) view.findViewById(R.id.tv_user_fq);
        TextView questionTextView = (TextView) view.findViewById(R.id.tv_question_fq);
        ListView answerList = (ListView) view.findViewById(R.id.lv_answer_feed);

        // load question details
        if (mQuestion != null) {

            // get user
            ParseObject parseObjectUser = mQuestion.getParseUser("user");

            userTextView.setText(parseObjectUser.getString("first_name") + " " + parseObjectUser.getString("last_name").substring(0, 1));
            questionTextView.setText(mQuestion.getString("question"));

            // create adapter, set it to our list view
            mAnswerAdapter = new AnswerAdapter(getActivity(), 0, mAnswers);
            answerList.setAdapter(mAnswerAdapter);

            // get answers
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Answer");
            query.whereEqualTo("question", mQuestion);
            query.include("user");
            query.orderByDescending("points");
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    if (e == null) {
                        // add answers to our list view
                        mAnswers.addAll(list);
                        mAnswerAdapter.notifyDataSetChanged();
                    } else {
                        e.printStackTrace();
                    }
                }
            });

        }
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (menu.findItem(R.id.action_new_answer) == null) {
            menuInflater.inflate(R.menu.menu_question, menu);
        }
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_new_answer) {
            NewAnswerDialogFragment dialog = new NewAnswerDialogFragment();
            dialog.setListener(new NewAnswerDialogFragment.NewAnswerListener() {
                @Override
                public void onNewAnswerConfirm(DialogFragment dialogFragment, String answerText) {

                    // create new parse object answer
                    ParseObject parseObjectAnswer = new ParseObject("Answer");
                    parseObjectAnswer.put("answer", answerText);
                    parseObjectAnswer.put("points", 0);
                    parseObjectAnswer.put("question", mQuestion);
                    parseObjectAnswer.put("user", ParseUser.getCurrentUser());
                    parseObjectAnswer.saveInBackground();

                    // add answer to list view
                    mAnswerAdapter.add(parseObjectAnswer);
                }
            });
            dialog.show(getFragmentManager(), "NewAnswerDialog");

            return true;
        } else {
            return super.onOptionsItemSelected(menuItem);
        }

    } // end method onOptionsItemSelected()

} // end class QuestionFragment
