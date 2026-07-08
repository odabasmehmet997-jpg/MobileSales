package com.proje.mobilesales.core.netsis;

import java.io.IOException;

import okhttp3.Interceptor;

public class NetsisInterceptor implements Interceptor {
    public okhttp3.Response intercept(Chain chain) throws IOException {
        return chain.proceed(chain.request());
    }
}
