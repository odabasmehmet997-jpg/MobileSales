package com.proje.mobilesales.core;

import dagger.internal.Preconditions;
import io.reactivex.Scheduler;
import javax.inject.Provider;
public final class ActivityModule_ProvideIoSchedulerFactory implements Provider {
    private final ActivityModule module;

    public ActivityModule_ProvideIoSchedulerFactory(ActivityModule activityModule) {
        this.module = activityModule;
    }

    @Override // javax.inject.Provider
    public Scheduler get() {
        return provideIoScheduler(this.module);
    }

    public static ActivityModule_ProvideIoSchedulerFactory create(ActivityModule activityModule) {
        return new ActivityModule_ProvideIoSchedulerFactory(activityModule);
    }

    public static Scheduler provideIoScheduler(ActivityModule activityModule) {
        return Preconditions.checkNotNullFromProvides(activityModule.provideIoScheduler());
    }
}
