package com.proje.mobilesales.core.interfaces;

public interface ConvertDb<T> {
    T convert();

    void convertDbType(T t);
}
