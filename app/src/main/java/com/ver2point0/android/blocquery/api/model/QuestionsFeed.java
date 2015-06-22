package com.ver2point0.android.blocquery.api.model;


import java.util.ArrayList;

public class QuestionsFeed {

    private String mUser;
    private ArrayList<String> mQuestions;

    public QuestionsFeed(String user) {
        mUser = user;
        mQuestions = new ArrayList<String>();
        getDummyData();
    }

    public String getUser() {
        return mUser;
    }

    public String get(int index) {
        return mQuestions.get(index);
    }

    public int size() {
        return mQuestions.size();
    }

    public void getDummyData() {
        for (int i = 0; i < 10; i++) {
            mQuestions.add("Question" + i);
        }
    }

}
