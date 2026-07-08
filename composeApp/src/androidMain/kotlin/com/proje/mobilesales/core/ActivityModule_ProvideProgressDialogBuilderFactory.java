package com.proje.mobilesales.core;

import android.content.Context;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ActivityModule_ProvideProgressDialogBuilderFactory implements Provider {
    private final Provider<Context> contextProvider;
    private final ActivityModule module;

    public ActivityModule_ProvideProgressDialogBuilderFactory(ActivityModule activityModule, Provider<Context> provider) {
        this.module = activityModule;
        this.contextProvider = provider;
    }

    @Override // javax.inject.Provider
    public ProgressDialogBuilder get() {
        return provideProgressDialogBuilder(this.module, this.contextProvider.get());
    }

    public static ActivityModule_ProvideProgressDialogBuilderFactory create(ActivityModule activityModule, Provider<Context> provider) {
        return new ActivityModule_ProvideProgressDialogBuilderFactory(activityModule, provider);
    }

    public static ProgressDialogBuilder provideProgressDialogBuilder(ActivityModule activityModule, Context context) {
        return Preconditions.checkNotNullFromProvides(activityModule.provideProgressDialogBuilder(context));
    }
}
