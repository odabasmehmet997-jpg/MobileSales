package com.google.android.datatransport.runtime.dagger.internal;

import java.util.Map;
import javax.inject.Provider;

abstract class AbstractMapFactory<K, V, V2> implements Factory<Map<K, V2>> {
    private final Map<K, Provider<V>> contributingMap = null;
    public static abstract class Builder<K, V, V2> {
    }
    final Map<K, Provider<V>> contributingMap() {
        return this.contributingMap;
    }
}
