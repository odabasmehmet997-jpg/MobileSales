package com.proje.mobilesales.core;

import android.content.Context;
import com.proje.mobilesales.core.interfaces.ToastBuilder;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ActivityModule_ProvideToastBuilderFactory implements Provider {
    private final Provider<Context> contextProvider;
    private final ActivityModule module;

    public ActivityModule_ProvideToastBuilderFactory(ActivityModule activityModule, Provider<Context> provider) {
        this.module = activityModule;
        this.contextProvider = provider;
    }

    @Override // javax.inject.Provider
    public ToastBuilder get() {
        return provideToastBuilder(this.module, this.contextProvider.get());
    }

    public static ActivityModule_ProvideToastBuilderFactory create(ActivityModule activityModule, Provider<Context> provider) {
        return new ActivityModule_ProvideToastBuilderFactory(activityModule, provider);
    }

    public static ToastBuilder provideToastBuilder(ActivityModule activityModule, Context context) {
        return Preconditions.checkNotNullFromProvides(activityModule.provideToastBuilder(context));
    }
}
