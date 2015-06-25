package com.ver2point0.android.blocquery.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ver2point0.android.blocquery.R;
import com.ver2point0.android.blocquery.api.model.QuestionsFeed;
import com.ver2point0.android.blocquery.ui.adapter.QuestionsAdapter;

public class QuestionsActivity extends Activity {

//    private Button mLogOut;
    private QuestionsAdapter mQuestionsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        QuestionsFeed feed = new QuestionsFeed("user");
        mQuestionsAdapter = new QuestionsAdapter(feed, this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_activity_question);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mQuestionsAdapter);

//        // Retrieve current user from Parse.com
//        ParseUser currentUser = ParseUser.getCurrentUser();
//        // Convert currentUser into String
//        String userString = currentUser.getUsername().toString();
//        // Locate TextView in activity_questions
//        TextView userText = (TextView) findViewById(R.id.tv_username);
//        // Set the currentUser String into TextView
//        userText.setText(userString);

//        // Locate Button in activity_questions
//        mLogOut = (Button) findViewById(R.id.bt_logout);
//        // Logout Button Click Listener
//        mLogOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Logout current user
//                ParseUser.logOut();
//                Toast.makeText(getApplicationContext(), getString(R.string.logged_out), Toast.LENGTH_SHORT).show();
//                finish();
//            }
//        });

    }
}
