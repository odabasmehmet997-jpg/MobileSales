package io.reactivex.internal.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
 
public enum HashMapSupplier implements Callable<Map<Object, Object>> {
    INSTANCE;

    public static <K, V> HashMapSupplier asCallable() {
        return HashMapSupplier.INSTANCE;
    }
 
    public Map<Object, Object> call() throws Exception {
        return new HashMap();
    }
}
