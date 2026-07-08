package com.proje.mobilesales.features.survey.util;

import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Response;

import java.io.IOException;

public final class SurveyDynamicHeaderInterceptor implements Interceptor {
    public Response intercept(Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        try {
            return chain.proceed(chain.request().newBuilder().addHeader("client_secret", SurveyConstants.INSTANCE.getCLIENT_SECRET()).build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
