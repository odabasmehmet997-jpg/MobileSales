package com.proje.mobilesales.core;

import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.Call;

public final class CommunicationModule_ProvidePrintersPublicCallFactoryFactory implements Provider {
    private final CommunicationModule module;

    public CommunicationModule_ProvidePrintersPublicCallFactoryFactory(CommunicationModule communicationModule) {
        this.module = communicationModule;
    }

    @Override // javax.inject.Provider
    public Call.Factory get() {
        return providePrintersPublicCallFactory(this.module);
    }

    public static CommunicationModule_ProvidePrintersPublicCallFactoryFactory create(CommunicationModule communicationModule) {
        return new CommunicationModule_ProvidePrintersPublicCallFactoryFactory(communicationModule);
    }

    public static Call.Factory providePrintersPublicCallFactory(CommunicationModule communicationModule) {
        return Preconditions.checkNotNullFromProvides(communicationModule.providePrintersPublicCallFactory());
    }
}
