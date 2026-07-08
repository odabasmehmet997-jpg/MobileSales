package com.google.android.datatransport.runtime.dagger.internal;

import com.google.android.datatransport.runtime.dagger.Lazy;
import javax.inject.Provider;

public final class ProviderOfLazy<T> implements Provider<Lazy<T>> {
    private Provider<T> provider;
    public Lazy<T> get() {
        return DoubleCheck.lazy(this.provider);
    }
    private ProviderOfLazy() {
    }
    private ProviderOfLazy(Provider<T> provider) {
        this.provider = provider;
    }
}
