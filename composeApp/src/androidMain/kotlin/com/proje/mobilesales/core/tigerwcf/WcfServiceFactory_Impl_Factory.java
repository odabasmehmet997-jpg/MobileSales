package com.proje.mobilesales.core.tigerwcf;

import javax.inject.Provider;
import okhttp3.Call;

final class WcfServiceFactory_Impl_Factory implements Provider {
    private final Provider<Call.Factory> callFactoryProvider;
    WcfServiceFactory_Impl_Factory(Provider<Call.Factory> provider) {
        this.callFactoryProvider = provider;
    }
    public WcfServiceFactory.Impl get() {
        return newInstance(this.callFactoryProvider.get());
    }
    static WcfServiceFactory.Impl newInstance(Call.Factory factory) {
        return new WcfServiceFactory.Impl(factory);
    }
}
