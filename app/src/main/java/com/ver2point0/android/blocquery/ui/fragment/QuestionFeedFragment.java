package com.ver2point0.android.blocquery.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ver2point0.android.bloquery.R;

public class QuestionFeedFragment extends Fragment {

    public QuestionFeedFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_feed, container, false);
        return view;
    }
}
