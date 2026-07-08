package com.proje.mobilesales.core.tigerwcf;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.Executor;
import javax.inject.Inject;
import javax.inject.Named;
import okhttp3.Call;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;



public interface WcfServiceFactory {
    <T> T create(String str, Class<T> cls);

    <T> T create(String str, Class<T> cls, Executor executor);

    WcfServiceFactory rxEnabled(boolean z);

    class Impl implements WcfServiceFactory {
        private final Call.Factory mCallFactory;
        private boolean mRxEnabled;
        public Impl(@Named("WCF") Call.Factory factory) {
            this.mCallFactory = factory;
        }
        public WcfServiceFactory rxEnabled(boolean z) {
            this.mRxEnabled = z;
            return this;
        }
        public <T> T create(String str, Class<T> cls) {
            return create(str, cls, null);
        }

        public <T> T create(String str, Class<T> cls, Executor executor) {
            Persister persister = new Persister(new AnnotationStrategy());
            Retrofit.Builder builder = new Retrofit.Builder();
            if (this.mRxEnabled) {
                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()));
            }
            Retrofit.Builder callFactory = builder.callFactory(this.mCallFactory);
            if (executor == null) {
                executor = new MainThreadExecutor();
            }
            callFactory.callbackExecutor(executor);
            return builder.baseUrl(str).addConverterFactory(SimpleXmlConverterFactory.create(persister)).build().create(cls);
        }
    }
    class MainThreadExecutor implements Executor {
        private final Handler handler = new Handler(Looper.getMainLooper());
        public void execute(@NonNull Runnable runnable) {
            this.handler.post(runnable);
        }
    }
}
