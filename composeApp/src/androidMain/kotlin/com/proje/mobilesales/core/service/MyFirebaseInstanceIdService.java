package com.proje.mobilesales.core.service;

import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;



public class MyFirebaseInstanceIdService extends FirebaseMessagingService {
    private static final String TAG = "NotificationService";

    private void sendRegistrationToServer(String str) {
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onNewToken(String str) {
        Log.d(TAG, "Refreshed token: " + str);
        sendRegistrationToServer(str);
    }
}
