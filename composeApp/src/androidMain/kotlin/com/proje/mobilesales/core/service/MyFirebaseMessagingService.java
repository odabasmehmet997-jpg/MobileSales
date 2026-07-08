package com.proje.mobilesales.core.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.util.Log;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.app.NotificationCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.activity.MainActivity;



public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "com.proje.mobilesales.core.service.MyFirebaseMessagingService";

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String str = TAG;
        Log.d(str, "onMessageReceived: " + remoteMessage.getFrom());
        if (remoteMessage.getData().size() > 0) {
            Log.d(str, "onMessageReceived: " + remoteMessage.getData());
        }
        if (remoteMessage.getNotification() != null) {
            Log.d(str, "onMessageReceived: " + remoteMessage.getNotification().getBody());
            createNotification(remoteMessage.getNotification().getBody());
        }
    }

    private void createNotification(String str) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        ((NotificationManager) getSystemService("notification")).notify(0, new NotificationCompat.Builder(this).setSmallIcon(R.mipmap.ic_launcher).setContentTitle("New Release").setContentText(str).setAutoCancel(true).setSound(RingtoneManager.getDefaultUri(2)).setContentIntent(PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE)).build());
    }
}
