package com.proje.mobilesales.core;

import android.content.Context;
import android.net.TrafficStats;
import android.os.Process;
import android.util.Log;
import com.proje.mobilesales.core.asynctask.ConnectivityInterceptors;
import com.proje.mobilesales.core.netsis.NetsisRestEndPointApi;
import com.proje.mobilesales.core.netsis.NetsisRestPublicFactory;
import com.proje.mobilesales.core.netsis.NetsisRestServiceFactory;
import com.proje.mobilesales.core.netsis.NetsisRestTokenApi;
import com.proje.mobilesales.core.netsis.NetsisTokenService;
import com.proje.mobilesales.core.netsis.TokenAuthenticator;
import com.proje.mobilesales.core.netsis.TokenInterceptor;
import com.proje.mobilesales.core.tigerwcf.WcfServiceFactory;
import com.proje.mobilesales.core.utils.ContextUtils;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;

public class CommunicationModule {
    private static final String TAG_OK_HTTP = "OkHttp";
    private final String mBaseUrl;
    private final Context mContext;

    public CommunicationModule(Context context, String str) {
        this.mContext = context;
        this.mBaseUrl = str;
    }
    public Context provideGetContext() {
        return ContextUtils.getmContext();
    }

    public NetsisRestEndPointApi provideRestEndPointApi(NetsisRestServiceFactory netsisRestServiceFactory) {
        return netsisRestServiceFactory.create(this.mBaseUrl, NetsisRestEndPointApi.class, null);
    }
    public WcfServiceFactory provideWcfServiceFactory( Call.Factory factory) {
        return new WcfServiceFactory.Impl(factory);
    }
    public NetsisRestServiceFactory provideRestServiceFactory( Call.Factory factory) {
        return new NetsisRestServiceFactory.Impl(factory);
    }
    public NetsisRestPublicFactory provideRestServicePublicFactory(Call.Factory factory) {
        return new NetsisRestPublicFactory.Impl(factory);
    }
    public Call.Factory provideWcfCallFactory() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.MINUTES;
        return builder.writeTimeout(3L, timeUnit).readTimeout(3L, timeUnit).connectTimeout(1L, timeUnit).addInterceptor(new LoggingInterceptor()).addInterceptor(new ConnectivityInterceptors()).build();
    }
    public Call.Factory provideNetsisPublicCallFactory() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.MINUTES;
        return builder.writeTimeout(3L, timeUnit).readTimeout(3L, timeUnit).connectTimeout(1L, timeUnit).addInterceptor(new LoggingInterceptor()).build();
    }
    public Call.Factory providePrintersPublicCallFactory() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        return builder.writeTimeout(30L, timeUnit).readTimeout(30L, timeUnit).connectTimeout(30L, timeUnit).addInterceptor(new LoggingInterceptor()).build();
    }
    public Dispatcher provideDispatcher() {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(1);
        return dispatcher;
    }
    public Call.Factory provideNetsisCallFactory(NetsisRestTokenApi netsisRestTokenApi, Dispatcher dispatcher) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.MINUTES;
        return builder.writeTimeout(3L, timeUnit).readTimeout(3L, timeUnit).connectTimeout(1L, timeUnit).dispatcher(dispatcher).addNetworkInterceptor(new TokenInterceptor(netsisRestTokenApi)).authenticator(new TokenAuthenticator(netsisRestTokenApi)).addInterceptor(new LoggingInterceptor()).build();
    }
    public NetsisRestTokenApi provideRestTokenApi() {
        return NetsisTokenService.getInstance(this.mBaseUrl).getRestTokenApi();
    }

    private static class LoggingInterceptor implements Interceptor {
        private final Interceptor debugInterceptor;

        private LoggingInterceptor() {
            this.debugInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                public void log(String str) {
                    Log.d(CommunicationModule.TAG_OK_HTTP, str);
                }
            }).setLevel(HttpLoggingInterceptor.Level.BASIC);
        }
        public Response intercept(Chain chain) throws IOException {
            TrafficStats.setThreadStatsTag(1);
            return this.debugInterceptor.intercept(chain);
        }
    }

    public static class BackgroundThreadExecutor implements Executor {
        public void execute( Runnable runnable) {
            Process.setThreadPriority(10);
            runnable.run();
        }
    }
}
