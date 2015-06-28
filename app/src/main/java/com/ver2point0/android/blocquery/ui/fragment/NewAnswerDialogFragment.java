package com.ver2point0.android.blocquery.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.ver2point0.android.bloquery.R;


public class NewAnswerDialogFragment extends DialogFragment {

    public interface NewAnswerListener {
        public void onNewAnswerConfirm(DialogFragment dialogFragment, String answer);
    }

    private NewAnswerListener mNewAnswerListener;

    public NewAnswerDialogFragment() {}

    // listener setter
    public void setListener(NewAnswerListener newAnswerListener) {
        mNewAnswerListener = newAnswerListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // set view for dialog
        builder.setView(inflater.inflate(R.layout.fragment_new_answer_dialog, null));
        builder.setTitle(R.string.submit_an_answer);

        // setup buttons
        builder.setPositiveButton(R.string.button_submit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // get new answer
                EditText editText = (EditText) getDialog().findViewById(R.id.et_new_answer_dialog);

                // call confirm method
                mNewAnswerListener.onNewAnswerConfirm(NewAnswerDialogFragment.this, editText.getText().toString());
            }
        });

        builder.setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // cancel clicked
            }
        });
     return builder.create();
    }
}
