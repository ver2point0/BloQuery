package com.ver2point0.android.blocquery.api.model;


import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class QuestionsFeed {

    private String mUser;
    private ArrayList<Question> mQuestions;

    public QuestionsFeed(String user) {
        mUser = user;
        try {
            mQuestions = getQuestions();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Question get(int index) {
        return mQuestions.get(index);
    }

    public int size() {
        return mQuestions.size();
    }

    /*
       *
       * use findInBackground() method to load questions into adapter
       *  might involve an interface() Listener
       *  QuestionsAdapter implements OnListLoadCompleteListener
       *    onListLoadComplete(ArrayList<questions> ) method
        *
       *
       * */

    public ArrayList<Question> getQuestions() throws ParseException {
        final ArrayList<Question> questions = new ArrayList<Question>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Question");
        query.orderByDescending("createdAt");
        query.setLimit(10);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> questionsList, ParseException e) {
                if (e == null) {
                    // if no error, good list
                    for (ParseObject parseObject : questionsList) {
                        questions.add(new Question(parseObject));
                    }
//                    if (questionsList != null) {
//                        for (ParseObject parseObject : questionsList) {
//                            questions.add(new Question(parseObject));
//                        }
//                    }
                } else {
                    e.printStackTrace();
                }
            }
        });
        return questions;
    }

}
