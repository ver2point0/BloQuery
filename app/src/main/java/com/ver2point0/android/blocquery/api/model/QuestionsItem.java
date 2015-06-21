package com.ver2point0.android.blocquery.api.model;


public class QuestionsItem {

    private String mUser;
    private String mQuestion;

    public QuestionsItem(String user, String question) {
        mUser = user;
        mQuestion = question;
    }

    public String getUser() {
        return mUser;
    }

    public String getQuestion() {
        return mQuestion;
    }

}
