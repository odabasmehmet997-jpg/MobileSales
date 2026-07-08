package com.proje.mobilesales.core.asynctask;

import android.content.Context;
import com.proje.mobilesales.core.base.BaseCommunication_MembersInjector;
import com.proje.mobilesales.core.tigerwcf.WcfServiceFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class TigerWcfAsyncTask_MembersInjector implements MembersInjector<TigerWcfAsyncTask> {
    private final Provider<WcfServiceFactory> factoryProvider;
    private final Provider<Context> mContextProvider;

    public TigerWcfAsyncTask_MembersInjector(Provider<Context> provider, Provider<WcfServiceFactory> provider2) {
        this.mContextProvider = provider;
        this.factoryProvider = provider2;
    }

    public static MembersInjector<TigerWcfAsyncTask> create(Provider<Context> provider, Provider<WcfServiceFactory> provider2) {
        return new TigerWcfAsyncTask_MembersInjector(provider, provider2);
    }
    public void injectMembers(TigerWcfAsyncTask tigerWcfAsyncTask) {
        BaseCommunication_MembersInjector.injectMContext(tigerWcfAsyncTask, this.mContextProvider.get());
        injectFactory(tigerWcfAsyncTask, this.factoryProvider.get());
    }

    public static void injectFactory(TigerWcfAsyncTask tigerWcfAsyncTask, WcfServiceFactory wcfServiceFactory) {
        TigerWcfAsyncTask.factory = wcfServiceFactory;
    }
}
