package com.proje.mobilesales.core.service;

import com.proje.mobilesales.core.service.PrinterPublicFactory;
import javax.inject.Provider;
import okhttp3.Call;



public final class PrinterPublicFactory_Impl_Factory implements Provider {
    private final Provider<Call.Factory> callFactoryProvider;

    public PrinterPublicFactory_Impl_Factory(Provider<Call.Factory> provider) {
        this.callFactoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public PrinterPublicFactory.Impl get() {
        return newInstance(this.callFactoryProvider.get());
    }

    public static PrinterPublicFactory_Impl_Factory create(Provider<Call.Factory> provider) {
        return new PrinterPublicFactory_Impl_Factory(provider);
    }

    public static PrinterPublicFactory.Impl newInstance(Call.Factory factory) {
        return new PrinterPublicFactory.Impl(factory);
    }
}
