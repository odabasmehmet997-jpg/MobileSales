package com.proje.mobilesales.core;

import com.proje.mobilesales.core.netsis.NetsisRestTokenApi;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.Call;
import okhttp3.Dispatcher;

public final class CommunicationModule_ProvideNetsisCallFactoryFactory implements Provider {
    private final Provider<Dispatcher> dispatcherProvider;
    private final CommunicationModule module;
    private final Provider<NetsisRestTokenApi> restTokenApiProvider;

    public CommunicationModule_ProvideNetsisCallFactoryFactory(CommunicationModule communicationModule, Provider<NetsisRestTokenApi> provider, Provider<Dispatcher> provider2) {
        this.module = communicationModule;
        this.restTokenApiProvider = provider;
        this.dispatcherProvider = provider2;
    }

    @Override // javax.inject.Provider
    public Call.Factory get() {
        return provideNetsisCallFactory(this.module, this.restTokenApiProvider.get(), this.dispatcherProvider.get());
    }

    public static CommunicationModule_ProvideNetsisCallFactoryFactory create(CommunicationModule communicationModule, Provider<NetsisRestTokenApi> provider, Provider<Dispatcher> provider2) {
        return new CommunicationModule_ProvideNetsisCallFactoryFactory(communicationModule, provider, provider2);
    }

    public static Call.Factory provideNetsisCallFactory(CommunicationModule communicationModule, NetsisRestTokenApi netsisRestTokenApi, Dispatcher dispatcher) {
        return Preconditions.checkNotNullFromProvides(communicationModule.provideNetsisCallFactory(netsisRestTokenApi, dispatcher));
    }
}
