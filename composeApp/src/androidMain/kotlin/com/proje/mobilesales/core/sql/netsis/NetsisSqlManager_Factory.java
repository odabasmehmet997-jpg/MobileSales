package com.proje.mobilesales.core.sql.netsis;

import io.reactivex.Scheduler;
import javax.inject.Provider;

public final class NetsisSqlManager_Factory<T> implements Provider {
    private final Provider<Scheduler> ioSchedulerProvider;

    public NetsisSqlManager_Factory(Provider<Scheduler> provider) {
        this.ioSchedulerProvider = provider;
    }

    public NetsisSqlManager<T> get() {
        return newInstance(this.ioSchedulerProvider.get());
    }

    public static <T> NetsisSqlManager_Factory<T> create(Provider<Scheduler> provider) {
        return new NetsisSqlManager_Factory<>(provider);
    }

    public static <T> NetsisSqlManager<T> newInstance(Scheduler scheduler) {
        return new NetsisSqlManager<>(scheduler);
    }
}
