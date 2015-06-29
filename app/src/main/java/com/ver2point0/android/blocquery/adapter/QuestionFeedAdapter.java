package com.ver2point0.android.blocquery.adapter;


import android.app.FragmentManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseUser;
import com.ver2point0.android.blocquery.ui.activity.BloQueryActivity;
import com.ver2point0.android.blocquery.ui.fragment.ProfileViewFragment;
import com.ver2point0.android.bloquery.R;

import java.util.ArrayList;

public class QuestionFeedAdapter extends ArrayAdapter<ParseObject> {

    // questions list
    private ArrayList<ParseObject> mQuestionsList;

    public QuestionFeedAdapter(Context context, int textViewResourceId, ArrayList<ParseObject> parseObjects) {
        super(context, textViewResourceId, parseObjects);
        mQuestionsList = parseObjects;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_feed_item, null);
        }

        ParseObject questionParseObject = mQuestionsList.get(position);

        if (questionParseObject != null) {
            TextView userTextView = (TextView) view.findViewById(R.id.tv_user_fi);
            TextView questionTextView = (TextView) view.findViewById(R.id.tv_question_fi);

            // get user
            final ParseUser parseObjectUser = questionParseObject.getParseUser("user");

            // set text view
            userTextView.setText(parseObjectUser.getString("first_name") + " " + parseObjectUser.getString("last_name").substring(0, 1));
            questionTextView.setText(questionParseObject.getString("question"));

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
        }
        return view;
    }
}
