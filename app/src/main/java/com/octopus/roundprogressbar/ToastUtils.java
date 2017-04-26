package com.octopus.roundprogressbar;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
    public static Context getApplicationContext() {
        return BaseApplication.getContext();
    }

    public static void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(int resId) {
        Toast.makeText(getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
    }
}
