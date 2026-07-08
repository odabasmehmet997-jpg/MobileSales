package com.proje.mobilesales.core;

import android.content.Context;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ActivityModule_ProvideAlertDialogBuilderFactory implements Provider {
    private final Provider<Context> contextProvider;
    private final ActivityModule module;
    public ActivityModule_ProvideAlertDialogBuilderFactory(ActivityModule activityModule, Provider<Context> provider) {
        this.module = activityModule;
        this.contextProvider = provider;
    }
    public AlertDialogBuilder get() {
        return provideAlertDialogBuilder(this.module, this.contextProvider.get());
    }
    public static ActivityModule_ProvideAlertDialogBuilderFactory create(ActivityModule activityModule, Provider<Context> provider) {
        return new ActivityModule_ProvideAlertDialogBuilderFactory(activityModule, provider);
    }
    public static AlertDialogBuilder provideAlertDialogBuilder(ActivityModule activityModule, Context context) {
        return Preconditions.checkNotNullFromProvides(activityModule.provideAlertDialogBuilder(context));
    }
}
