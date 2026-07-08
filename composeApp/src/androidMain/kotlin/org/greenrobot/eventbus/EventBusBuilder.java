package org.greenrobot.eventbus;

import android.os.Looper;
import org.greenrobot.eventbus.android.AndroidLogger;
import org.greenrobot.eventbus.meta.SubscriberInfoIndex;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventBusBuilder {
    private static final ExecutorService DEFAULT_EXECUTOR_SERVICE = Executors.newCachedThreadPool();
    boolean ignoreGeneratedIndex;
    Logger logger;
    MainThreadSupport mainThreadSupport;
    boolean strictMethodVerification;
    List<SubscriberInfoIndex> subscriberInfoIndexes;
    boolean throwSubscriberException;
    boolean logSubscriberExceptions = true;
    boolean logNoSubscriberMessages = true;
    boolean sendSubscriberExceptionEvent = true;
    boolean sendNoSubscriberEvent = true;
    boolean eventInheritance = true;
    ExecutorService executorService = EventBusBuilder.DEFAULT_EXECUTOR_SERVICE;
    EventBusBuilder() {
    }
    Logger getLogger() {
        final Logger logger = this.logger;
        return null != logger ? logger : Logger.Default.get();
    }
    MainThreadSupport getMainThreadSupport() {
        final Object androidMainLooperOrNull;
        final MainThreadSupport mainThreadSupport = this.mainThreadSupport;
        if (null != mainThreadSupport) {
            return mainThreadSupport;
        }
        if (!AndroidLogger.isAndroidLogAvailable() || null == (androidMainLooperOrNull = getAndroidMainLooperOrNull())) {
            return null;
        }
        return new MainThreadSupport.AndroidHandlerMainThreadSupport((Looper) androidMainLooperOrNull);
    }
    static Object getAndroidMainLooperOrNull() {
        try {
            return Looper.getMainLooper();
        } catch (final RuntimeException unused) {
            return null;
        }
    }
}
