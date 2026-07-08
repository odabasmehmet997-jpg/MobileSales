package com.proje.mobilesales.core;

import android.content.Context;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ActivityModule_ProvideContextFactory implements Provider {
    private final ActivityModule module;

    public ActivityModule_ProvideContextFactory(ActivityModule activityModule) {
        this.module = activityModule;
    }

    @Override // javax.inject.Provider
    public Context get() {
        return provideContext(this.module);
    }

    public static ActivityModule_ProvideContextFactory create(ActivityModule activityModule) {
        return new ActivityModule_ProvideContextFactory(activityModule);
    }

    public static Context provideContext(ActivityModule activityModule) {
        return Preconditions.checkNotNullFromProvides(activityModule.provideContext());
    }
}
