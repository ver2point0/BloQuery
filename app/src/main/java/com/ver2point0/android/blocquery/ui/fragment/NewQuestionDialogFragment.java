package com.ver2point0.android.blocquery.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.ver2point0.android.bloquery.R;

public class NewQuestionDialogFragment extends DialogFragment {

    public interface NewQuestionListener {
        public void onNewQuestionConfirm(DialogFragment dialogFragment, String question);
    }

    private NewQuestionListener mNewQuestionListener;

    public NewQuestionDialogFragment() {}

    public void setListener(NewQuestionListener newQuestionListener) {
        mNewQuestionListener = newQuestionListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.fragment_new_question_dialog, null));
        builder.setTitle(getString(R.string.ask_new_question));
        
        builder.setPositiveButton(getString(R.string.button_submit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText editText = (EditText) getDialog().findViewById(R.id.et_new_question_dialog);
                mNewQuestionListener.onNewQuestionConfirm(NewQuestionDialogFragment.this, editText.getText().toString());
            }
        });

        builder.setNegativeButton(getString(R.string.button_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // clicked cancel, do nothing
            }
        });
        return builder.create();
    }
}
