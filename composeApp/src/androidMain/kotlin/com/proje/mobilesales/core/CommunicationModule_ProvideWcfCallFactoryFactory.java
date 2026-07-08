package com.proje.mobilesales.core;

import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.Call;

public final class CommunicationModule_ProvideWcfCallFactoryFactory implements Provider {
    private final CommunicationModule module;

    public CommunicationModule_ProvideWcfCallFactoryFactory(CommunicationModule communicationModule) {
        this.module = communicationModule;
    }

    @Override // javax.inject.Provider
    public Call.Factory get() {
        return provideWcfCallFactory(this.module);
    }

    public static CommunicationModule_ProvideWcfCallFactoryFactory create(CommunicationModule communicationModule) {
        return new CommunicationModule_ProvideWcfCallFactoryFactory(communicationModule);
    }

    public static Call.Factory provideWcfCallFactory(CommunicationModule communicationModule) {
        return Preconditions.checkNotNullFromProvides(communicationModule.provideWcfCallFactory());
    }
}
