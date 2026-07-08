package com.proje.mobilesales.core.exception;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.app.NotificationCompat;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.features.activity.LoginActivity;
import java.lang.Thread;

public class MyExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "MyExceptionHandler";
    Activity mActivity;

    public MyExceptionHandler(Activity activity) {
        this.mActivity = activity;
    }
    @SuppressLint("WrongConstant")
    public void uncaughtException(Thread thread, Throwable th) {
        Log.d(TAG, "uncaughtException: ", th);
        Intent intent = new Intent(this.mActivity, LoginActivity.class);
        intent.addFlags(335577088);
        ((AlarmManager) MobileSales.getInstance().getBaseContext().getSystemService(NotificationCompat.CATEGORY_ALARM)).set(1, System.currentTimeMillis() + 1000, PendingIntent.getActivity(MobileSales.getInstance().getBaseContext(), 0, intent, BasicMeasure.EXACTLY | PendingIntent.FLAG_IMMUTABLE));
        this.mActivity.finish();
        System.exit(2);
    }
}
