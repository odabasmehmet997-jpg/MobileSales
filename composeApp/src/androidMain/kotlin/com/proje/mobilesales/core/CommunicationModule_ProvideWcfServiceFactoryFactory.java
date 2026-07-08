package com.proje.mobilesales.core;

import com.proje.mobilesales.core.tigerwcf.WcfServiceFactory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.Call;

public final class CommunicationModule_ProvideWcfServiceFactoryFactory implements Provider {
    private final Provider<Call.Factory> callFactoryProvider;
    private final CommunicationModule module;

    public CommunicationModule_ProvideWcfServiceFactoryFactory(CommunicationModule communicationModule, Provider<Call.Factory> provider) {
        this.module = communicationModule;
        this.callFactoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public WcfServiceFactory get() {
        return provideWcfServiceFactory(this.module, this.callFactoryProvider.get());
    }

    public static CommunicationModule_ProvideWcfServiceFactoryFactory create(CommunicationModule communicationModule, Provider<Call.Factory> provider) {
        return new CommunicationModule_ProvideWcfServiceFactoryFactory(communicationModule, provider);
    }

    public static WcfServiceFactory provideWcfServiceFactory(CommunicationModule communicationModule, Call.Factory factory) {
        return Preconditions.checkNotNullFromProvides(communicationModule.provideWcfServiceFactory(factory));
    }
}
