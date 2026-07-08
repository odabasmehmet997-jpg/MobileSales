package com.google.android.datatransport.runtime.dagger.internal;

import javax.inject.Provider;

public final class SingleCheck<T> implements Provider<T> {
    private static final Object UNINITIALIZED = new Object();
    private volatile Object instance;
    private volatile Provider<T> provider;
    private SingleCheck() {
    }
    private SingleCheck(Provider<T> provider) {
        this.provider = provider;
    }
    public T get() {
        T t = (T) this.instance;
        if (t != UNINITIALIZED) {
            return t;
        }
        Provider<T> provider = this.provider;
        if (provider == null) {
            return (T) this.instance;
        }
        T t2 = provider.get();
        this.instance = t2;
        this.provider = null;
        return t2;
    }
    public static <T> SingleCheck<T> create(Provider<T> provider) {
        return new SingleCheck<>(provider);
    }
    public static <T> SingleCheck<T> createWithDefault(Provider<T> provider) {
        return new SingleCheck<>(provider);
    }
}
