package com.proje.mobilesales.core;

import com.proje.mobilesales.core.netsis.NetsisRestPublicFactory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.Call;

public final class CommunicationModule_ProvideRestServicePublicFactoryFactory implements Provider {
    private final Provider<Call.Factory> callFactoryProvider;
    private final CommunicationModule module;

    public CommunicationModule_ProvideRestServicePublicFactoryFactory(CommunicationModule communicationModule, Provider<Call.Factory> provider) {
        this.module = communicationModule;
        this.callFactoryProvider = provider;
    }

    public NetsisRestPublicFactory get() {
        return provideRestServicePublicFactory(this.module, this.callFactoryProvider.get());
    }

    public static CommunicationModule_ProvideRestServicePublicFactoryFactory create(CommunicationModule communicationModule, Provider<Call.Factory> provider) {
        return new CommunicationModule_ProvideRestServicePublicFactoryFactory(communicationModule, provider);
    }

    public static NetsisRestPublicFactory provideRestServicePublicFactory(CommunicationModule communicationModule, Call.Factory factory) {
        return Preconditions.checkNotNullFromProvides(communicationModule.provideRestServicePublicFactory(factory));
    }
}
