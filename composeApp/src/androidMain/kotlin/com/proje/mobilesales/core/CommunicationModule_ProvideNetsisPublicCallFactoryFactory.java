package com.proje.mobilesales.core;

import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.Call;

public final class CommunicationModule_ProvideNetsisPublicCallFactoryFactory implements Provider {
    private final CommunicationModule module;

    public CommunicationModule_ProvideNetsisPublicCallFactoryFactory(CommunicationModule communicationModule) {
        this.module = communicationModule;
    }

    @Override // javax.inject.Provider
    public Call.Factory get() {
        return provideNetsisPublicCallFactory(this.module);
    }

    public static CommunicationModule_ProvideNetsisPublicCallFactoryFactory create(CommunicationModule communicationModule) {
        return new CommunicationModule_ProvideNetsisPublicCallFactoryFactory(communicationModule);
    }

    public static Call.Factory provideNetsisPublicCallFactory(CommunicationModule communicationModule) {
        return Preconditions.checkNotNullFromProvides(communicationModule.provideNetsisPublicCallFactory());
    }
}
