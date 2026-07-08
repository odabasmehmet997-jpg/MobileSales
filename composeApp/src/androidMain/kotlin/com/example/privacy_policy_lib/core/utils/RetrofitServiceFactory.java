package com.example.privacy_policy_lib.core.utils;

import com.example.privacy_policy_lib.core.model.api.AgreementServiceApi;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory; 
public final class RetrofitServiceFactory {
    public static final String APPROVE_AGREEMENT_CONTENT = "";
    public static final String BASE_NAMESPACE_PRODUCTION = "";
    public static String BASE_NAMESPACE_PRODUCTION2 = "";
    public static final String BASE_NAMESPACE_TEST = "";
    public static final String GET_AGREEMENT_CONTENT = "";
    public static final String GET_CURRENT_APPROVED_AGREEMENT_CONTENT_HASH_BY_TOKEN = "";
    public static final String HTTP = "";
    public static final String HTTPS = "";
    public static final RetrofitServiceFactory INSTANCE = new RetrofitServiceFactory();
    public static final String NAMESPACE_END = "";
    private static Retrofit retrofitInstance;
    private RetrofitServiceFactory() {
    }
    public static AgreementServiceApi createRetrofitdefault(RetrofitServiceFactory retrofitServiceFactory, boolean isProduction, long l, long l1, int i, Object o) {
        return null;
    }
    private String getBaseUrl(boolean z) {
        if (z) {
            return "https://license1.proje.com.tr/";
        }
        return "http://licensetest.proje.com.tr/";
    }
    public  String getSoapAction(boolean z, String str) {
        Intrinsics.checkNotNullParameter(str, "methodName");
        if (z) {
            return "http://license1.proje.com.tr/ProjeLicenseService/AgreementService/" + str;
        }
        return "http://license2.proje.com.tr/ProjeLicenseService/AgreementService/" + str;
    }
    public static AgreementServiceApi createRetrofitdefault(RetrofitServiceFactory retrofitServiceFactory, boolean z, long j2, long j3, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            j2 = 30;
        }
        if ((i2 & 4) != 0) {
            j3 = 10;
        }
        return retrofitServiceFactory.createRetrofit(z, j2, j3);
    }
    public AgreementServiceApi createRetrofit(boolean z, long j2, long j3) {
        if (retrofitInstance == null) {
            String baseUrl = getBaseUrl(z);
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(null, 1, null);
            httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder addInterceptor = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor);
            TimeUnit timeUnit = TimeUnit.SECONDS;
            retrofitInstance = new Retrofit.Builder().baseUrl(baseUrl).client(addInterceptor.readTimeout(j2, timeUnit).connectTimeout(j3, timeUnit).build()).addConverterFactory(SimpleXmlConverterFactory.create()).build();
        }
        Retrofit retrofit = retrofitInstance;
        Intrinsics.checkNotNull(retrofit);
        Object create = retrofit.create(AgreementServiceApi.class);
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        return (AgreementServiceApi) create;
    }
}
