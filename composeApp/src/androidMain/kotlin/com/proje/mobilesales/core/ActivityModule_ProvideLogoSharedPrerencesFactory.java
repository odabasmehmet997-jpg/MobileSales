package com.proje.mobilesales.core;

import android.content.Context;
import com.proje.mobilesales.core.utils.SharedPreferencesHelper;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ActivityModule_ProvideLogoSharedPrerencesFactory implements Provider {
    private final Provider<Context> contextProvider;
    private final ActivityModule module;

    public ActivityModule_ProvideLogoSharedPrerencesFactory(ActivityModule activityModule, Provider<Context> provider) {
        this.module = activityModule;
        this.contextProvider = provider;
    }

    @Override // javax.inject.Provider
    public SharedPreferencesHelper get() {
        return provideLogoSharedPrerences(this.module, this.contextProvider.get());
    }

    public static ActivityModule_ProvideLogoSharedPrerencesFactory create(ActivityModule activityModule, Provider<Context> provider) {
        return new ActivityModule_ProvideLogoSharedPrerencesFactory(activityModule, provider);
    }

    public static SharedPreferencesHelper provideLogoSharedPrerences(ActivityModule activityModule, Context context) {
        return Preconditions.checkNotNullFromProvides(activityModule.provideLogoSharedPrerences(context));
    }
}
