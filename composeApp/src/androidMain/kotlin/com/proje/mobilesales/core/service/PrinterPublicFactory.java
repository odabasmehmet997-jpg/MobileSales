package com.proje.mobilesales.core.service;

import android.os.Handler;
import android.os.Looper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.proje.mobilesales.core.utils.StringUtils;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.Executor;
import javax.inject.Inject;
import javax.inject.Named;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/* compiled from: PrinterPublicFactory.kt */

public interface PrinterPublicFactory {
    <T> T create(String str, Class<T> cls);

    <T> T create(String str, Class<T> cls, Executor executor);

    PrinterPublicFactory rxEnabled(boolean z);

    /* compiled from: PrinterPublicFactory.kt */
    final class Impl implements PrinterPublicFactory {
        private final Call.Factory mCallFactory;
        private boolean mRxEnabled;

        @Inject
        public Impl(@Named("PRINTERS_PUBLIC") Call.Factory callFactory) {
            Intrinsics.checkNotNullParameter(callFactory, "callFactory");
            this.mCallFactory = callFactory;
        }

        @Override // com.proje.mobilesales.core.service.PrinterPublicFactory
        public PrinterPublicFactory rxEnabled(boolean z) {
            this.mRxEnabled = z;
            return this;
        }

        @Override // com.proje.mobilesales.core.service.PrinterPublicFactory
        public <T> T create(String str, Class<T> clazz) {
            Intrinsics.checkNotNullParameter(clazz, "clazz");
            return create(str, clazz, null);
        }

        @Override // com.proje.mobilesales.core.service.PrinterPublicFactory
        public <T> T create(String str, Class<T> clazz, Executor executor) {
            Intrinsics.checkNotNullParameter(clazz, "clazz");
            Retrofit.Builder builder = new Retrofit.Builder();
            if (this.mRxEnabled) {
                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.m636io()));
            }
            Retrofit.Builder callFactory = builder.callFactory(this.mCallFactory);
            if (executor == null) {
                executor = new MainThreadExecutor();
            }
            callFactory.callbackExecutor(executor);
            Gson create = new GsonBuilder().setLenient().create();
            Intrinsics.checkNotNull(str);
            return builder.baseUrl(StringUtils.urlAddHtpp(str)).addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(GsonConverterFactory.create(create)).build().create(clazz);
        }
    }

    /* compiled from: PrinterPublicFactory.kt */
    final class MainThreadExecutor implements Executor {
        private final Handler handler = new Handler(Looper.getMainLooper());

        @Override // java.util.concurrent.Executor
        public void execute(Runnable r) {
            Intrinsics.checkNotNullParameter(r, "r");
            this.handler.post(r);
        }
    }
}
