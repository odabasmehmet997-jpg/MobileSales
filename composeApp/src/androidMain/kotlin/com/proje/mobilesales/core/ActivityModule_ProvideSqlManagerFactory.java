package com.proje.mobilesales.core;

import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.sql.ISqlManager;
import dagger.internal.Preconditions;
import io.reactivex.Scheduler;
import javax.inject.Provider;

public final class ActivityModule_ProvideSqlManagerFactory implements Provider {
    private final Provider<ErpType> erpTypeProvider;
    private final Provider<Scheduler> ioSchedulerProvider;
    private final ActivityModule module;

    public ActivityModule_ProvideSqlManagerFactory(ActivityModule activityModule, Provider<Scheduler> provider, Provider<ErpType> provider2) {
        this.module = activityModule;
        this.ioSchedulerProvider = provider;
        this.erpTypeProvider = provider2;
    }
    public ISqlManager get() {
        return provideSqlManager(this.module, this.ioSchedulerProvider.get(), this.erpTypeProvider.get());
    }

    public static ActivityModule_ProvideSqlManagerFactory create(ActivityModule activityModule, Provider<Scheduler> provider, Provider<ErpType> provider2) {
        return new ActivityModule_ProvideSqlManagerFactory(activityModule, provider, provider2);
    }

    public static ISqlManager provideSqlManager(ActivityModule activityModule, Scheduler scheduler, ErpType erpType) {
        return Preconditions.checkNotNullFromProvides(activityModule.provideSqlManager(scheduler, erpType));
    }
}
