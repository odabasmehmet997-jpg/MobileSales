package com.proje.mobilesales.core.netsis;

import android.text.TextUtils;
import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;

public class TokenInterceptor extends NetsisToken implements Interceptor {
    public TokenInterceptor(NetsisRestTokenApi netsisRestTokenApi) {
        super(netsisRestTokenApi);
    }

    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!TextUtils.isEmpty(getTokenManager().getAccessToken())) {
            String accessToken = getTokenManager().getAccessToken();
            Request.Builder newBuilder = request.newBuilder();
            setAuthHeader(newBuilder, accessToken);
            return chain.proceed(newBuilder.build());
        }
        return chain.proceed(chain.request());
    }
}
