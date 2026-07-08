package com.proje.mobilesales.core;

import android.content.Context;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CommunicationModule_ProvideGetContextFactory implements Provider {
    private final CommunicationModule module;

    public CommunicationModule_ProvideGetContextFactory(CommunicationModule communicationModule) {
        this.module = communicationModule;
    }

    @Override // javax.inject.Provider
    public Context get() {
        return provideGetContext(this.module);
    }

    public static CommunicationModule_ProvideGetContextFactory create(CommunicationModule communicationModule) {
        return new CommunicationModule_ProvideGetContextFactory(communicationModule);
    }

    public static Context provideGetContext(CommunicationModule communicationModule) {
        return Preconditions.checkNotNullFromProvides(communicationModule.provideGetContext());
    }
}
