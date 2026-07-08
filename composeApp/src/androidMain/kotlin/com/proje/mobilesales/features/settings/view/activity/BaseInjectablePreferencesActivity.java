package com.proje.mobilesales.features.settings.view.activity;

import android.app.Application;
import android.os.Bundle;
import com.proje.mobilesales.core.ActivityModule;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.interfaces.Injectable;
import com.proje.mobilesales.core.interfaces.di.ActivityComponent;
import com.proje.mobilesales.core.interfaces.di.IComponent;
import kotlin.jvm.internal.Intrinsics;

public abstract class BaseInjectablePreferencesActivity extends BaseTrackablePreferencesActivity implements Injectable {
    private ActivityComponent activityComponent;
    public final ActivityComponent getActivityComponent() {
        return activityComponent;
    }
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        final ActivityModule activityModule = new ActivityModule(this);
        final Application application = this.getApplication();
        Intrinsics.checkNotNull(application, "null cannot be cast to non-null type com.proje.mobilesales.core.MobileSales");
        activityComponent = ((MobileSales) application).getAppComponent().plus(activityModule);
    }
    protected void onDestroy() {
        activityComponent = null;
        super.onDestroy();
    }
    public IComponent getComponent() {
        final ActivityComponent activityComponent = this.activityComponent;
        Intrinsics.checkNotNull(activityComponent);
        return activityComponent;
    }
}
