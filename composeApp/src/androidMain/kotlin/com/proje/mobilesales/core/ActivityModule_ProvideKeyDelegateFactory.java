package com.proje.mobilesales.core;

import com.proje.mobilesales.core.utils.KeyDelegate;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ActivityModule_ProvideKeyDelegateFactory implements Provider {
    private final ActivityModule module;

    public ActivityModule_ProvideKeyDelegateFactory(ActivityModule activityModule) {
        this.module = activityModule;
    }

    public KeyDelegate get() {
        return provideKeyDelegate(this.module);
    }

    public static ActivityModule_ProvideKeyDelegateFactory create(ActivityModule activityModule) {
        return new ActivityModule_ProvideKeyDelegateFactory(activityModule);
    }

    public static KeyDelegate provideKeyDelegate(ActivityModule activityModule) {
        return Preconditions.checkNotNullFromProvides(activityModule.provideKeyDelegate());
    }
}
