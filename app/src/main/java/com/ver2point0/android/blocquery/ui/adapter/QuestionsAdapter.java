package com.ver2point0.android.blocquery.ui.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ver2point0.android.blocquery.R;
import com.ver2point0.android.blocquery.api.model.Question;
import com.ver2point0.android.blocquery.api.model.QuestionsFeed;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionsAdapterViewHolder> {


    private QuestionsFeed mQuestionsFeed;
    private Context mContext;

    public QuestionsAdapter(QuestionsFeed questionsFeed, Context context) {
        mQuestionsFeed = questionsFeed;
        mContext = context;
    }

    @Override
    public QuestionsAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int index) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_question, viewGroup, false);
        return new QuestionsAdapterViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(QuestionsAdapterViewHolder questionsAdapterViewHolder, int index) {
        questionsAdapterViewHolder.user.setText(mQuestionsFeed.get(index).getUser());
        questionsAdapterViewHolder.questions.setText(mQuestionsFeed.get(index).getQuestion());
    }

    @Override
    public int getItemCount() {
        return mQuestionsFeed.size();
    }


    // add a method to setListQuestions()
        // callNotifyDataSetChanged();

    class QuestionsAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView user;
        TextView questions;

        public QuestionsAdapterViewHolder(View questionsView) {
            super(questionsView);
            user = (TextView) questionsView.findViewById(R.id.tv_username);
            questions = (TextView) questionsView.findViewById(R.id.tv_question);
        }


        void update(QuestionsFeed questionsFeed, Question question) {

//            user.setText(questionsFeed.getUser());
//            questions.setText(question.getQuestion());
        }
    }
}
