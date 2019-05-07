package com.example.saiapi.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AlertDialogFragment extends DialogFragment {


    private String details;
    public static final String DETAILS = "details";

    public static AlertDialogFragment newInstance(String details) {

        Bundle args = new Bundle();

        AlertDialogFragment fragment = new AlertDialogFragment();

        args.putString(DETAILS, details);
        fragment.setArguments(args);

        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        details = getArguments().getString(DETAILS);

        return new AlertDialog.Builder(getActivity())
                .setTitle(DETAILS)
                .setMessage(details)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> dialog.dismiss()).create();
    }
}
