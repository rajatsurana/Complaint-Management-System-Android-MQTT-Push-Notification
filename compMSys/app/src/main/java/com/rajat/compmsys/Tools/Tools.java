package com.rajat.compmsys.Tools;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.rajat.compmsys.R;

public class Tools {
    public static AlertDialog blackalert;
//creates a progress bar
    public static ProgressDialog showProgressBar(Context c) {
        ProgressDialog progressDialog = new ProgressDialog(c,R.style.DialogTheme);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        return progressDialog;
    }
// creates an alert dialog box
    public static void showAlertDialog(String message,Context con)
    {
        final AlertDialog.Builder alert = new AlertDialog.Builder(con,R.style.DialogTheme);
        alert.setTitle(message);
        alert.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {
                        blackalert.dismiss();
                    }
                });
        blackalert = alert.create();
        blackalert.show();
    }

}