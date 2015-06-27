package com.ver2point0.android.blocquery.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseObject;
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
            TextView textView = (TextView) view.findViewById(R.id.tv_question);
            textView.setText(questionParseObject.getString("question"));
        }
        return view;
    }
}
