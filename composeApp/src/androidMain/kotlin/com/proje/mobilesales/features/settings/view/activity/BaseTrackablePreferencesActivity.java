package com.proje.mobilesales.features.settings.view.activity;

import android.Manifest;
import android.os.Bundle;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.analytics.GoogleAnalytics;

public abstract class BaseTrackablePreferencesActivity extends BasePreferencesActivity {
      protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE})
    protected void onStart() {
        super.onStart();
        GoogleAnalytics.getInstance(this).reportActivityStart(this);
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE})
    protected void onStop() {
        GoogleAnalytics.getInstance(this).reportActivityStop(this);
        super.onStop();
    }
}
