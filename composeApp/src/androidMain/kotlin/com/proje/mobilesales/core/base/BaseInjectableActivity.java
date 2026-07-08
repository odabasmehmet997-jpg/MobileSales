package com.proje.mobilesales.core.base;

import android.Manifest;
import android.os.Bundle;
import androidx.annotation.RequiresPermission;
import com.proje.mobilesales.core.ActivityModule;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.interfaces.Injectable;
import com.proje.mobilesales.core.interfaces.di.ActivityComponent;
import com.proje.mobilesales.core.interfaces.di.IComponent;

public abstract class BaseInjectableActivity extends BaseTrackableActivity implements Injectable {
    private ActivityComponent mActivityComponent;
    private boolean mDestroyed;
    public ActivityComponent getActivityComponent() {
        return this.mActivityComponent;
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mActivityComponent = ((MobileSales) getApplication()).getAppComponent().plus(new ActivityModule(this));
    }
    public IComponent getComponent() {
        return this.mActivityComponent;
    }
    public void onDestroy() {
        super.onDestroy();
        this.mDestroyed = true;
        this.mActivityComponent = null;
    }
    public void onBackPressed() {
        try {
            super.onBackPressed();
        } catch (IllegalStateException unused) {
            supportFinishAfterTransition();
        }
    }
    public boolean isActivityDestroyed() {
        return this.mDestroyed;
    }
}
