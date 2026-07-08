package com.proje.mobilesales.core.interfaces;

public interface GetLoaderSqlText {
    int LIMIT = 50;
    int OFFSET = 0;

    String getLoaderSqlText(int i2, int i3) throws IllegalArgumentException;
}
