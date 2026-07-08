package com.proje.mobilesales.core.interfaces;

import java.io.IOException;

public interface ICursorGet<T> {
    T get() throws IOException;
}
