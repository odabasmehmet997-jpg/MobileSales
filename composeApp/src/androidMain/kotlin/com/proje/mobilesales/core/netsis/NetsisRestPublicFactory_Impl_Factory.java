package com.proje.mobilesales.core.netsis;

import javax.inject.Provider;
import okhttp3.Call;

public final class NetsisRestPublicFactory_Impl_Factory implements Provider {
    private final Provider<Call.Factory> callFactoryProvider;

    public NetsisRestPublicFactory_Impl_Factory(Provider<Call.Factory> provider) {
        this.callFactoryProvider = provider;
    }

    public NetsisRestPublicFactory.Impl get() {
        return newInstance(this.callFactoryProvider.get());
    }

    public static NetsisRestPublicFactory_Impl_Factory create(Provider<Call.Factory> provider) {
        return new NetsisRestPublicFactory_Impl_Factory(provider);
    }

    public static NetsisRestPublicFactory.Impl newInstance(Call.Factory factory) {
        return new NetsisRestPublicFactory.Impl(factory);
    }
}
