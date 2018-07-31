package com.clakestudio.pc.dayone;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by pc on 2017-08-17.
 */

public class Dialog extends DialogFragment {


    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setMessage("Tap the note to edit it, tap again on edge of it to save changes");
        alertDialog.setPositiveButton("got it!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        return alertDialog.create();
    }
}
