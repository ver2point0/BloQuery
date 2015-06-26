package com.ver2point0.android.blocquery.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.ver2point0.android.blocquery.R;
import com.ver2point0.android.blocquery.api.model.Question;

public class SingleQuestionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_question);

        String singleQuestion = "default";
        TextView textView;
        textView = (TextView) findViewById(R.id.tv_single_question);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            singleQuestion = bundle.getString(Question.EXTRA_SINGLE_QUESTION);
        }
        textView.setText(singleQuestion);
    }

}
