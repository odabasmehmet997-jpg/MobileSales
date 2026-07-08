package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

public final class BackgroundDetector implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {
    private static final BackgroundDetector zza = new BackgroundDetector();
    private final AtomicBoolean zzb = new AtomicBoolean();
    private final AtomicBoolean zzc = new AtomicBoolean();
    private final ArrayList zzd = new ArrayList();
    private boolean zze = false;
    public interface BackgroundStateChangeListener {
        void onBackgroundStateChanged(boolean z);
    }
    private BackgroundDetector() {
    }
    public static BackgroundDetector getInstance() {
        return zza;
    }
    public static void initialize( Application application) {
        BackgroundDetector backgroundDetector = zza;
        synchronized (backgroundDetector) {
            try {
                if (!backgroundDetector.zze) {
                    application.registerActivityLifecycleCallbacks(backgroundDetector);
                    application.registerComponentCallbacks(backgroundDetector);
                    backgroundDetector.zze = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
    private void zza(boolean z) {
        synchronized (zza) {
            try {
                Iterator<BackgroundStateChangeListener> it = this.zzd.iterator();
                while (it.hasNext()) {
                    it.next().onBackgroundStateChanged(z);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
    public void addListener(BackgroundStateChangeListener backgroundStateChangeListener) {
        synchronized (zza) {
            this.zzd.add(backgroundStateChangeListener);
        }
    }
    public boolean isInBackground() {
        return this.zzb.get();
    }
    public void onActivityCreated(Activity activity, Bundle bundle) {
        AtomicBoolean atomicBoolean = this.zzc;
        boolean zCompareAndSet = this.zzb.compareAndSet(true, false);
        atomicBoolean.set(true);
        if (zCompareAndSet) {
            zza(false);
        }
    }
    public void onActivityDestroyed(Activity activity) {
    }
    public void onActivityPaused(Activity activity) {
    }
    public void onActivityResumed(Activity activity) {
        AtomicBoolean atomicBoolean = this.zzc;
        boolean zCompareAndSet = this.zzb.compareAndSet(true, false);
        atomicBoolean.set(true);
        if (zCompareAndSet) {
            zza(false);
        }
    }
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }
    public void onActivityStarted(Activity activity) {
    }
    public void onActivityStopped(Activity activity) {
    }
    public void onConfigurationChanged(Configuration configuration) {
    }
    public void onLowMemory() {
    }
    public void onTrimMemory(int i2) {
        if (i2 == 20 && this.zzb.compareAndSet(false, true)) {
            this.zzc.set(true);
            zza(true);
        }
    }
    public boolean readCurrentStateIfPossible(boolean z) {
        if (!this.zzc.get()) {
            if (!PlatformVersion.isAtLeastJellyBean()) {
                return z;
            }
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            if (!this.zzc.getAndSet(true) && runningAppProcessInfo.importance > 100) {
                this.zzb.set(true);
            }
        }
        return isInBackground();
    }
}
