package com.ver2point0.android.blocquery.api;


import com.ver2point0.android.blocquery.api.model.QuestionsFeed;
import com.ver2point0.android.blocquery.api.model.QuestionsItem;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    private List<QuestionsFeed> mFeeds;
    private List<QuestionsItem> mItems;

    public DataSource() {
        mFeeds = new ArrayList<QuestionsFeed>();
        mItems = new ArrayList<QuestionsItem>();
        getQuestions();
    }

    public List<QuestionsFeed> getFeeds() {
        return mFeeds;
    }

    public List<QuestionsItem> getItems() {
        return mItems;
    }

    void getQuestions() {
        // return an ArrayList
        mFeeds.add(new QuestionsFeed("Username"));
        for (int i = 0; i < 10; i++) {
            mItems.add(new QuestionsItem("What do you want to do with your life when it is all over?"));
        }
//
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("Question");
//        query.orderByDescending("createdAt");
//        query.setLimit(10);
//        query.findInBackground(new FindCallback<ParseObject>() {
//            public void done(List<ParseObject> questionsList, ParseException e) {
//                // Display resulting items in Adapter
//            }
//        });
//
    }


}
