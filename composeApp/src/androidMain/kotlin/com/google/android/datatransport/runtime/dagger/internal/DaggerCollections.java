package com.google.android.datatransport.runtime.dagger.internal;

import java.util.HashSet;
import java.util.LinkedHashMap;

public final class DaggerCollections {
    private static int calculateInitialCapacity(int i2) {
        if (i2 < 3) {
            return i2 + 1;
        }
        if (i2 < 1073741824) {
            return (int) ((i2 / 0.75f) + 1.0f);
        }
        return Integer.MAX_VALUE;
    }
    static <T> HashSet<T> newHashSetWithExpectedSize(int i2) {
        return new HashSet<>(calculateInitialCapacity(i2));
    }
    static <K, V> LinkedHashMap<K, V> newLinkedHashMapWithExpectedSize(int i2) {
        return new LinkedHashMap<>(calculateInitialCapacity(i2));
    }
}
