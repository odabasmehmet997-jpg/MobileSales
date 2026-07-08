package com.google.android.datatransport.runtime.dagger.internal;

import com.google.android.datatransport.runtime.dagger.Lazy;

import javax.inject.Provider;
import java.util.Map;

public final class MapProviderFactory<K, V> extends AbstractMapFactory<K, V, Provider<V>> implements Lazy<Map<K, Provider<V>>> {
    public static final class Builder<K, V> extends AbstractMapFactory.Builder<K, V, Provider<V>> {
    }
    public Map<K, Provider<V>> get() {
        return contributingMap();
    }
}
