package com.google.android.gms.tasks;

import androidx.annotation.MainThread;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import java.lang.ref.WeakReference;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-tasks@@18.1.0 */
final class zzv extends LifecycleCallback {
    private final List zza;

    @MainThread
    public void onStop() {
        synchronized (this.zza) {
            for (final WeakReference weakReference : zza) {
                final zzq zzq = (zzq) weakReference.get();
                if (null != zzq) {
                    zzq.zzc();
                }
            }
            zza.clear();
        }
    }
}
