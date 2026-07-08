package com.proje.mobilesales.core;

import com.proje.mobilesales.core.netsis.NetsisRestEndPointApi;
import com.proje.mobilesales.core.netsis.NetsisRestServiceFactory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CommunicationModule_ProvideRestEndPointApiFactory implements Provider {
    private final CommunicationModule module;
    private final Provider<NetsisRestServiceFactory> restServiceFactoryProvider;

    public CommunicationModule_ProvideRestEndPointApiFactory(CommunicationModule communicationModule, Provider<NetsisRestServiceFactory> provider) {
        this.module = communicationModule;
        this.restServiceFactoryProvider = provider;
    }

    public NetsisRestEndPointApi get() {
        return provideRestEndPointApi(this.module, this.restServiceFactoryProvider.get());
    }

    public static CommunicationModule_ProvideRestEndPointApiFactory create(CommunicationModule communicationModule, Provider<NetsisRestServiceFactory> provider) {
        return new CommunicationModule_ProvideRestEndPointApiFactory(communicationModule, provider);
    }

    public static NetsisRestEndPointApi provideRestEndPointApi(CommunicationModule communicationModule, NetsisRestServiceFactory netsisRestServiceFactory) {
        return Preconditions.checkNotNullFromProvides(communicationModule.provideRestEndPointApi(netsisRestServiceFactory));
    }
}
