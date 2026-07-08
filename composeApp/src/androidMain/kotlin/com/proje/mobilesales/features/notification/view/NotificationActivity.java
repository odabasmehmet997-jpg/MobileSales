package com.proje.mobilesales.features.notification.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.proje.mobilesales.features.activity.MainActivity;

/* compiled from: NotificationActivity.kt */

public final class NotificationActivity extends AppCompatActivity {
    
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (isTaskRoot()) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(268435456);
            startActivity(intent);
        }
        finish();
    }
}
