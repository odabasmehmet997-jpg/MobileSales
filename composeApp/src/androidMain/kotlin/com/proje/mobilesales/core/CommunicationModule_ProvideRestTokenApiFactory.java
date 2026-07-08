package com.proje.mobilesales.core;

import com.proje.mobilesales.core.netsis.NetsisRestTokenApi;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CommunicationModule_ProvideRestTokenApiFactory implements Provider {
    private final CommunicationModule module;

    public CommunicationModule_ProvideRestTokenApiFactory(CommunicationModule communicationModule) {
        this.module = communicationModule;
    }

    @Override // javax.inject.Provider
    public NetsisRestTokenApi get() {
        return provideRestTokenApi(this.module);
    }

    public static CommunicationModule_ProvideRestTokenApiFactory create(CommunicationModule communicationModule) {
        return new CommunicationModule_ProvideRestTokenApiFactory(communicationModule);
    }

    public static NetsisRestTokenApi provideRestTokenApi(CommunicationModule communicationModule) {
        return Preconditions.checkNotNullFromProvides(communicationModule.provideRestTokenApi());
    }
}
