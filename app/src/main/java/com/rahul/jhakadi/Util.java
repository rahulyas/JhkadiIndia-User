package com.rahul.jhakadi;

import android.content.Context;
import android.widget.Toast;

public class Util {

    /**
     * Show the Toast message
     * @param context
     * @param text
     */
    public void showMessage(Context context, String text) {
        Toast.makeText(context,text,Toast.LENGTH_LONG).show();
    }

}
