package com.proje.mobilesales.features.settings.view.fragment;

import android.app.Application;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.proje.mobilesales.core.ActivityModule;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.interfaces.Injectable;
import com.proje.mobilesales.core.interfaces.di.ActivityComponent;
import com.proje.mobilesales.core.interfaces.di.IComponent;
import kotlin.jvm.internal.Intrinsics;

public abstract class InjectableSettingFragment extends BaseSettingsFragment implements Injectable {
    private ActivityComponent activityComponent;
    private boolean isActivityDestroyed;
    public final boolean isActivityDestroyed() {
        return isActivityDestroyed;
    }
    public final ActivityComponent getActivityComponent() {
        return activityComponent;
    }
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        final ActivityModule activityModule = new ActivityModule(getContext());
        final FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        assert null != activity;
        final Application application = activity.getApplication();
        Intrinsics.checkNotNull(application, "null cannot be cast to non-null type com.proje.mobilesales.core.MobileSales");
        activityComponent = ((MobileSales) application).getAppComponent().plus(activityModule);
    }
    public IComponent getComponent() {
        final ActivityComponent activityComponent = this.activityComponent;
        Intrinsics.checkNotNull(activityComponent);
        return activityComponent;
    }
    public void onDestroy() {
        super.onDestroy();
        isActivityDestroyed = true;
        activityComponent = null;
    }
}
