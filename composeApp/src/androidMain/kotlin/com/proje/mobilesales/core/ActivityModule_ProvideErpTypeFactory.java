package com.proje.mobilesales.core;

import android.content.Context;
import com.proje.mobilesales.core.enums.ErpType;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ActivityModule_ProvideErpTypeFactory implements Provider {
    private final Provider<Context> contextProvider;
    private final ActivityModule module;

    public ActivityModule_ProvideErpTypeFactory(ActivityModule activityModule, Provider<Context> provider) {
        this.module = activityModule;
        this.contextProvider = provider;
    }

    @Override // javax.inject.Provider
    public ErpType get() {
        return provideErpType(this.module, this.contextProvider.get());
    }

    public static ActivityModule_ProvideErpTypeFactory create(ActivityModule activityModule, Provider<Context> provider) {
        return new ActivityModule_ProvideErpTypeFactory(activityModule, provider);
    }

    public static ErpType provideErpType(ActivityModule activityModule, Context context) {
        return Preconditions.checkNotNullFromProvides(activityModule.provideErpType(context));
    }
}
