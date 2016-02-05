package com.hahaha.sandy.soccer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class DialogFragmentAbout extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_about,null);
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("About")
                .setPositiveButton("Ok",null)
                .create();
    }
}
