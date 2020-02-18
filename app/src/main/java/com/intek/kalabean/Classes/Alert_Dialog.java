package com.intek.kalabean.Classes;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;


import com.intek.kalabean.R;

public class Alert_Dialog {

    private Context context;
    private Dialog dialog;
    private TextView txtTitle,txtMessage;

    public Alert_Dialog(Context context) {
        this.context = context;
        dialog = new Dialog(context);
    }

    public void showAlert(){
        dialog.setContentView(R.layout.dialog_alert);
        dialog.setCancelable(false);
        dialog.getWindow().setLayout(1000,500);
        dialog.show();
    }

    public void dismiss(){

            dialog.dismiss();


    }
}
