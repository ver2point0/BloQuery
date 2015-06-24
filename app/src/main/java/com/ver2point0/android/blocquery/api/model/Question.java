package com.ver2point0.android.blocquery.api.model;


import com.parse.ParseObject;

public class Question {

    private String mQuestion;
    private String mUser;
    public static final String USER_KEY = "user";
    public static final String TITLE_KEY = "title";

    public Question(String question) {
        mQuestion = question;
    }

    public Question(ParseObject parseObject) {
       mUser = parseObject.getString(USER_KEY);
       mQuestion = parseObject.getString(TITLE_KEY);

    }

    public String getUser() {
        return mUser;
    }

    public String getQuestion() {
        return mQuestion;
    }

}
