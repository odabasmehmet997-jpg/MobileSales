package com.google.android.datatransport;

public interface Transformer<T, U> {
    U apply(Object t);
}
