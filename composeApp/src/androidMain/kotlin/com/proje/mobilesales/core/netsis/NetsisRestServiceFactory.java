package com.proje.mobilesales.core.netsis;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import com.google.gson.GsonBuilder;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.Executor;
import javax.inject.Inject;
import javax.inject.Named;
import okhttp3.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public interface NetsisRestServiceFactory {
    <T> T create(String str, Class<T> cls);

    <T> T create(String str, Class<T> cls, Executor executor);

    NetsisRestServiceFactory rxEnabled(boolean z);

    class Impl implements NetsisRestServiceFactory {
        private final Call.Factory mCallFactory;
        private boolean mRxEnabled;

        @Inject
        public Impl(@Named("NETSIS_TOKEN") Call.Factory factory) {
            this.mCallFactory = factory;
        }

        public NetsisRestServiceFactory rxEnabled(boolean z) {
            this.mRxEnabled = z;
            return this;
        }

        public <T> T create(String str, Class<T> cls) {
            return create(str, cls, null);
        }

        public <T> T create(String str, Class<T> cls, Executor executor) {
            Retrofit.Builder builder = new Retrofit.Builder();
            if (this.mRxEnabled) {
                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()));
            }
            Retrofit.Builder callFactory = builder.callFactory(this.mCallFactory);
            if (executor == null) {
                executor = new MainThreadExecutor();
            }
            callFactory.callbackExecutor(executor);
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.excludeFieldsWithoutExposeAnnotation();
            gsonBuilder.registerTypeAdapter(NetsisData.class, new NetsisDataDeserializer());
            return builder.baseUrl(str).addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(GsonConverterFactory.create(gsonBuilder.create())).build().create(cls);
        }
    }

    class MainThreadExecutor implements Executor {
        private final Handler handler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable runnable) {
            this.handler.post(runnable);
        }
    }
}
