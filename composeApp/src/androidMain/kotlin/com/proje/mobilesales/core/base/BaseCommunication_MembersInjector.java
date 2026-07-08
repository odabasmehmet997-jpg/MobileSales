package com.proje.mobilesales.core.base;

import android.content.Context;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class BaseCommunication_MembersInjector<T> implements MembersInjector<BaseCommunication<T>> {
    private final Provider<Context> mContextProvider;
    public BaseCommunication_MembersInjector(Provider<Context> provider) {
        if (provider == null) {
            throw new IllegalArgumentException("Context provider cannot be null");
        }
        this.mContextProvider = provider;
    }
    public static <T> MembersInjector<BaseCommunication<T>> create(Provider<Context> provider) {
        if (provider == null) {
            throw new IllegalArgumentException("Context provider cannot be null");
        }
        return new BaseCommunication_MembersInjector(provider);
    }
    public void injectMembers(BaseCommunication<T> baseCommunication) {
        if (baseCommunication == null) {
            throw new IllegalArgumentException("BaseCommunication cannot be null");
        }
        injectMContext(baseCommunication, this.mContextProvider.get());
    }
    public static <T> void injectMContext(BaseCommunication<T> baseCommunication, Context context) {
        if (baseCommunication == null) {
            throw new IllegalArgumentException("BaseCommunication cannot be null");
        }
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null");
        }
        baseCommunication.mContext = context;
    }
}
