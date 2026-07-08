package com.proje.mobilesales.core;

import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.Dispatcher;

public final class CommunicationModule_ProvideDispatcherFactory implements Provider {
    private final CommunicationModule module;
    public CommunicationModule_ProvideDispatcherFactory(CommunicationModule communicationModule) {
        this.module = communicationModule;
    }
    public Dispatcher get() {
        return provideDispatcher(this.module);
    }
    public static CommunicationModule_ProvideDispatcherFactory create(CommunicationModule communicationModule) {
        return new CommunicationModule_ProvideDispatcherFactory(communicationModule);
    }
    public static Dispatcher provideDispatcher(CommunicationModule communicationModule) {
        return Preconditions.checkNotNullFromProvides(communicationModule.provideDispatcher());
    }
}
