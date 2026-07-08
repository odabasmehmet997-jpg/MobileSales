package com.proje.mobilesales.core;

import com.proje.mobilesales.core.base.BaseErp;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ActivityModule_ProvideBaseErpFactory implements Provider {
    private final ActivityModule module;

    public ActivityModule_ProvideBaseErpFactory(ActivityModule activityModule) {
        this.module = activityModule;
    }
 
    public BaseErp get() {
        return provideBaseErp(this.module);
    }

    public static ActivityModule_ProvideBaseErpFactory create(ActivityModule activityModule) {
        return new ActivityModule_ProvideBaseErpFactory(activityModule);
    }

    public static BaseErp provideBaseErp(ActivityModule activityModule) {
        return Preconditions.checkNotNullFromProvides(activityModule.provideBaseErp());
    }
}
