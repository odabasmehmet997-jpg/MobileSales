package com.proje.mobilesales.core.base;

import android.Manifest;
import android.os.Bundle;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.analytics.GoogleAnalytics;

public abstract class BaseTrackableActivity extends BaseActivity {
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE})
    public void onStart() {
        super.onStart();
        GoogleAnalytics.getInstance(this).reportActivityStart(this);
    }
   @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE})
   public void onStop() {
        GoogleAnalytics.getInstance(this).reportActivityStop(this);
        super.onStop();
    }
}
