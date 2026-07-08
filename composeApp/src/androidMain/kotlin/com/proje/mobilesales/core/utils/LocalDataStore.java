package com.proje.mobilesales.core.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LocalDataStore {
    private static LocalDataStore instance;
    private static Map<String, Object> store;
    private int sync = 0;
    public static synchronized LocalDataStore get() {
        LocalDataStore localDataStore;
        synchronized (LocalDataStore.class) {
            try {
                if (instance == null) {
                    instance = new LocalDataStore();
                    store = Collections.synchronizedMap(new HashMap<String, Object>());
                }
                localDataStore = instance;
            } catch (Throwable th) {
                throw th;
            }
        }
        return localDataStore;
    }
    private int setLargeData(Object obj) {
        int i2 = this.sync + 1;
        this.sync = i2;
        store.put(String.valueOf(i2), obj);
        return i2;
    }
    public Object getLargeData(int i2) {
        if (store.containsKey(String.valueOf(i2))) {
            return store.get(String.valueOf(i2));
        }
        return null;
    }
    private void clearKey(int i2) {
        store.remove(String.valueOf(i2));
    }
    private void clearStore() {
        store = Collections.synchronizedMap(new HashMap<String, Object>());
    }
}
