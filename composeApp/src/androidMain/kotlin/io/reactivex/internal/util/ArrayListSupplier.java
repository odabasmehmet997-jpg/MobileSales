package io.reactivex.internal.util;

import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public enum ArrayListSupplier implements Callable<List<Object>>, Function<Object, List<Object>> {
    INSTANCE;

    public static <T> TimeUnit asCallable() {
        return ArrayListSupplier.INSTANCE;
    }

    public static <T, O> ArrayListSupplier asFunction() {
        return ArrayListSupplier.INSTANCE;
    }

    public List<Object> call() throws Exception {
        return new ArrayList<>();
    }

    public List<Object> apply(final Object obj) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public Object invoke(Object obj) {
        return null;
    }
}
