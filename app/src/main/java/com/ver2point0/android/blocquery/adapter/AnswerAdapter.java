package com.ver2point0.android.blocquery.adapter;

import android.app.FragmentManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseUser;
import com.ver2point0.android.blocquery.ui.activity.BloQueryActivity;
import com.ver2point0.android.blocquery.ui.fragment.ProfileViewFragment;
import com.ver2point0.android.bloquery.R;

import java.util.ArrayList;

public class AnswerAdapter extends ArrayAdapter<ParseObject> {

    // answers list
    private ArrayList<ParseObject> mAnswersList;

    public AnswerAdapter(Context context, int textViewResourceId, ArrayList<ParseObject> parseObjects) {
        super(context, textViewResourceId, parseObjects);
        mAnswersList = parseObjects;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.adapter_answer, null);
        }

        // parse object at current position
        final ParseObject answerParseObject = mAnswersList.get(position);

        if (mAnswersList != null) {

            TextView userTextView = (TextView) view.findViewById(R.id.tv_user_aa);
            TextView answerTextView = (TextView) view.findViewById(R.id.tv_answer_aa);
            final TextView pointsTextView = (TextView) view.findViewById(R.id.tv_points);
            ImageButton upVoteButton = (ImageButton) view.findViewById(R.id.ib_vote_up);
            ImageButton downVoteButton = (ImageButton) view.findViewById(R.id.ib_vote_down);

            // get the user
            final ParseUser parseObjectUser = answerParseObject.getParseUser("user");

            // set text
            userTextView.setText(parseObjectUser.getString("first_name") + " " + parseObjectUser.getString("last_name").substring(0, 1));
            answerTextView.setText(answerParseObject.getString("answer"));
            pointsTextView.setText(String.valueOf(answerParseObject.getInt("points")));

            // set user click listener
            userTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // give user newly created profile view
                    ProfileViewFragment viewFragment = new ProfileViewFragment();
                    viewFragment.setParseUser(parseObjectUser);

                    // show profile fragment
                    FragmentManager fragmentManager = ((BloQueryActivity) getContext()).getFragmentManager();
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.container, viewFragment, "ProfileViewFragment")
                            .addToBackStack(null)
                            .commit();
                }
            });

            // set button listeners
            upVoteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // increase points
                    answerParseObject.increment("points");
                    answerParseObject.saveInBackground();
                    pointsTextView.setText(String.valueOf(answerParseObject.getInt("points")));
                }
            });

            downVoteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // decrease points
                    answerParseObject.increment("points", -1);
                    answerParseObject.saveInBackground();
                    pointsTextView.setText(String.valueOf(answerParseObject.getInt("points")));
                }
            });
        }
        return view;
    }

}
