package com.proje.mobilesales.core.netsis;

import javax.inject.Provider;
import okhttp3.Call;

public final class NetsisRestServiceFactory_Impl_Factory implements Provider {
    private final Provider<Call.Factory> callFactoryProvider;

    public NetsisRestServiceFactory_Impl_Factory(Provider<Call.Factory> provider) {
        this.callFactoryProvider = provider;
    }

    @Override
    public NetsisRestServiceFactory.Impl get() {
        return newInstance(this.callFactoryProvider.get());
    }

    public static NetsisRestServiceFactory_Impl_Factory create(Provider<Call.Factory> provider) {
        return new NetsisRestServiceFactory_Impl_Factory(provider);
    }

    public static NetsisRestServiceFactory.Impl newInstance(Call.Factory factory) {
        return new NetsisRestServiceFactory.Impl(factory);
    }
}
