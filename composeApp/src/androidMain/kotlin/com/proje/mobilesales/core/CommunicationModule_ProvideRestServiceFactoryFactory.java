package com.proje.mobilesales.core;

import com.proje.mobilesales.core.netsis.NetsisRestServiceFactory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.Call;

public final class CommunicationModule_ProvideRestServiceFactoryFactory implements Provider {
    private final Provider<Call.Factory> callFactoryProvider;
    private final CommunicationModule module;

    public CommunicationModule_ProvideRestServiceFactoryFactory(CommunicationModule communicationModule, Provider<Call.Factory> provider) {
        this.module = communicationModule;
        this.callFactoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public NetsisRestServiceFactory get() {
        return provideRestServiceFactory(this.module, this.callFactoryProvider.get());
    }

    public static CommunicationModule_ProvideRestServiceFactoryFactory create(CommunicationModule communicationModule, Provider<Call.Factory> provider) {
        return new CommunicationModule_ProvideRestServiceFactoryFactory(communicationModule, provider);
    }

    public static NetsisRestServiceFactory provideRestServiceFactory(CommunicationModule communicationModule, Call.Factory factory) {
        return Preconditions.checkNotNullFromProvides(communicationModule.provideRestServiceFactory(factory));
    }
}
