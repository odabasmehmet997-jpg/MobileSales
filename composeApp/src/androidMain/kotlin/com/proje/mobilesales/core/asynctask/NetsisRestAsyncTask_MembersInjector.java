package com.proje.mobilesales.core.asynctask;

import android.content.Context;
import com.proje.mobilesales.core.base.BaseCommunication_MembersInjector;
import com.proje.mobilesales.core.netsis.NetsisRestPublicFactory;
import com.proje.mobilesales.core.netsis.NetsisRestServiceFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class NetsisRestAsyncTask_MembersInjector implements MembersInjector<NetsisRestAsyncTask> {
    private final Provider<Context> mContextProvider;
    private final Provider<NetsisRestServiceFactory> mFactoryProvider;
    private final Provider<NetsisRestPublicFactory> mPublicFactoryProvider;

    public NetsisRestAsyncTask_MembersInjector(Provider<Context> provider, Provider<NetsisRestServiceFactory> provider2, Provider<NetsisRestPublicFactory> provider3) {
        this.mContextProvider = provider;
        this.mFactoryProvider = provider2;
        this.mPublicFactoryProvider = provider3;
    }

    public static MembersInjector<NetsisRestAsyncTask> create(Provider<Context> provider, Provider<NetsisRestServiceFactory> provider2, Provider<NetsisRestPublicFactory> provider3) {
        return new NetsisRestAsyncTask_MembersInjector(provider, provider2, provider3);
    }
    public void injectMembers(NetsisRestAsyncTask netsisRestAsyncTask) {
        BaseCommunication_MembersInjector.injectMContext(netsisRestAsyncTask, this.mContextProvider.get());
        injectMFactory(netsisRestAsyncTask, this.mFactoryProvider.get());
        injectMPublicFactory(netsisRestAsyncTask, this.mPublicFactoryProvider.get());
    }

    public static void injectMFactory(NetsisRestAsyncTask netsisRestAsyncTask, NetsisRestServiceFactory netsisRestServiceFactory) {
        netsisRestAsyncTask.mFactory = netsisRestServiceFactory;
    }

    public static void injectMPublicFactory(NetsisRestAsyncTask netsisRestAsyncTask, NetsisRestPublicFactory netsisRestPublicFactory) {
        netsisRestAsyncTask.mPublicFactory = netsisRestPublicFactory;
    }
}
