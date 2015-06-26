package com.ver2point0.android.blocquery.ui.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ver2point0.android.blocquery.R;
import com.ver2point0.android.blocquery.api.model.Question;
import com.ver2point0.android.blocquery.api.model.QuestionsFeed;
import com.ver2point0.android.blocquery.ui.activity.SingleQuestionActivity;

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
        questionsAdapterViewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SingleQuestionActivity.class);
                intent.putExtra(Question.EXTRA_SINGLE_QUESTION, "title");
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mQuestionsFeed.size();
    }

    class QuestionsAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView user;
        TextView questions;
        RelativeLayout rootView;

        public QuestionsAdapterViewHolder(View questionsView) {
            super(questionsView);
            user = (TextView) questionsView.findViewById(R.id.tv_username);
            questions = (TextView) questionsView.findViewById(R.id.tv_question);
            rootView = (RelativeLayout) questionsView.findViewById(R.id.rl_root_view);
        }


        void update(QuestionsFeed questionsFeed, Question question) {

//            user.setText(questionsFeed.getUser());
//            questions.setText(question.getQuestion());
        }
    }
}
