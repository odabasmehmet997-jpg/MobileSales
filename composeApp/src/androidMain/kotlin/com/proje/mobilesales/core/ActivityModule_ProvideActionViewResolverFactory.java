package com.proje.mobilesales.core;

import com.proje.mobilesales.core.base.ActionViewResolver;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ActivityModule_ProvideActionViewResolverFactory implements Provider {
    private final ActivityModule module;

    public ActivityModule_ProvideActionViewResolverFactory(ActivityModule activityModule) {
        this.module = activityModule;
    }

    @Override // javax.inject.Provider
    public ActionViewResolver get() {
        return provideActionViewResolver(this.module);
    }

    public static ActivityModule_ProvideActionViewResolverFactory create(ActivityModule activityModule) {
        return new ActivityModule_ProvideActionViewResolverFactory(activityModule);
    }

    public static ActionViewResolver provideActionViewResolver(ActivityModule activityModule) {
        return Preconditions.checkNotNullFromProvides(activityModule.provideActionViewResolver());
    }
}
