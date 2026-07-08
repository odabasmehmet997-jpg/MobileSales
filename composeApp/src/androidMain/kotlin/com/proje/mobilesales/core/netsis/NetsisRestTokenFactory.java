package com.proje.mobilesales.core.netsis;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public interface NetsisRestTokenFactory {
    <T> T create(String str, Class<T> cls);

    class Impl implements NetsisRestTokenFactory {
        @Override
        public <T> T create(String str, Class<T> cls) {
            Retrofit.Builder builder = new Retrofit.Builder();
            OkHttpClient.Builder builder2 = new OkHttpClient.Builder();
            TimeUnit timeUnit = TimeUnit.MINUTES;
            builder.callFactory(builder2.writeTimeout(3L, timeUnit).readTimeout(3L, timeUnit).connectTimeout(1L, timeUnit).build()).callbackExecutor(new MainThreadExecutor());
            return builder.baseUrl(str).addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build().create(cls);
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
