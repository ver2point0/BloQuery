package com.ver2point0.android.blocquery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.parse.ParseObject;
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
        final ParseObject parseObjectAnswer = mAnswersList.get(position);

        if (mAnswersList != null) {

            TextView userTextView = (TextView) view.findViewById(R.id.tv_user_aa);
            TextView answerTextView = (TextView) view.findViewById(R.id.tv_answer_aa);
            final TextView pointsTextView = (TextView) view.findViewById(R.id.tv_points);
            ImageButton upVoteButton = (ImageButton) view.findViewById(R.id.ib_vote_up);
            ImageButton downVoteButton = (ImageButton) view.findViewById(R.id.ib_vote_down);

            // get the user
            ParseObject parseObjectUser = parseObjectAnswer.getParseUser("user");

            // set text
            userTextView.setText(parseObjectUser.getString("first_name") + " " + parseObjectUser.getString("last_name").substring(0, 1));
            answerTextView.setText(parseObjectAnswer.getString("answer"));
            pointsTextView.setText(String.valueOf(parseObjectAnswer.getInt("points")));

            // set button listeners
            upVoteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // increase points
                    parseObjectAnswer.increment("points");
                    parseObjectAnswer.saveInBackground();
                    pointsTextView.setText(String.valueOf(parseObjectAnswer.getInt("points")));
                }
            });

            downVoteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // decrease points
                    parseObjectAnswer.increment("points", -1);
                    parseObjectAnswer.saveInBackground();
                    pointsTextView.setText(String.valueOf(parseObjectAnswer.getInt("points")));
                }
            });
        }
        return view;
    }

}
