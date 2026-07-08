package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;
import javax.inject.Provider;

public final class EventStoreModule_PackageNameFactory implements Factory<String> {
    private final Provider<Context> contextProvider;
    public EventStoreModule_PackageNameFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }
    public String get() {
        return packageName(this.contextProvider.get());
    }
    public static EventStoreModule_PackageNameFactory instance() {
        return InstanceHolder.INSTANCE;
    }
    public static String packageName(Context context) {
        return Preconditions.checkNotNull(EventStoreModule.packageName(context), "Cannot return null from a non-@Nullable @Provides method");
    }
    private static final class InstanceHolder {
        private static final EventStoreModule_PackageNameFactory INSTANCE = new EventStoreModule_PackageNameFactory(EventStoreModule_PackageNameFactory.create());
        private InstanceHolder() {
        }
    }
    private static Provider<Context> create() {
        return null;
    }

}
