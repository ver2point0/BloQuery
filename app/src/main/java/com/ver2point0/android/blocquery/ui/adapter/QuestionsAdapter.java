package com.ver2point0.android.blocquery.ui.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ver2point0.android.blocquery.BloQueryApplication;
import com.ver2point0.android.blocquery.R;
import com.ver2point0.android.blocquery.api.DataSource;
import com.ver2point0.android.blocquery.api.model.QuestionsFeed;
import com.ver2point0.android.blocquery.api.model.QuestionsItem;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionsAdapterViewHolder> {

    @Override
    public QuestionsAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int index) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_questions, viewGroup, false);
        return new QuestionsAdapterViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(QuestionsAdapterViewHolder questionsAdapterViewHolder, int index) {
        DataSource sharedDataSource = BloQueryApplication.getSharedDataSource();
        questionsAdapterViewHolder.update(sharedDataSource.getFeeds().get(0), sharedDataSource.getItems().get(index));
    }

    @Override
    public int getItemCount() {
        return BloQueryApplication.getSharedDataSource().getItems().size();
    }

    class QuestionsAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView user;
        TextView questions;

        public QuestionsAdapterViewHolder(View questionsView) {
            super(questionsView);
            user = (TextView) questionsView.findViewById(R.id.tv_username);
            questions = (TextView) questionsView.findViewById(R.id.tv_question);
        }


        void update(QuestionsFeed questionsFeed, QuestionsItem questionsItem) {
            user.setText(questionsFeed.getUser());
            questions.setText(questionsItem.getQuestion());
        }
    }
}
