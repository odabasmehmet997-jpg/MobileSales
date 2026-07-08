package com.proje.mobilesales.core.sql.tiger;

import io.reactivex.Scheduler;
import javax.inject.Provider;



public final class TigerSqlManager_Factory<T> implements Provider {
    private final Provider<Scheduler> ioSchedulerProvider;

    public TigerSqlManager_Factory(Provider<Scheduler> provider) {
        this.ioSchedulerProvider = provider;
    }

    @Override // javax.inject.Provider
    public TigerSqlManager<T> get() {
        return newInstance(this.ioSchedulerProvider.get());
    }

    public static <T> TigerSqlManager_Factory<T> create(Provider<Scheduler> provider) {
        return new TigerSqlManager_Factory<>(provider);
    }

    public static <T> TigerSqlManager<T> newInstance(Scheduler scheduler) {
        return new TigerSqlManager<>(scheduler);
    }
}
