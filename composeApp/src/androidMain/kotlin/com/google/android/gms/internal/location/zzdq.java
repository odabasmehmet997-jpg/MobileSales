package com.google.android.gms.internal.location;

import androidx.annotation.GuardedBy;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.DeviceOrientation;
import com.google.android.gms.location.zzs;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzdq extends zzs {
    @GuardedBy("this")
    private ListenerHolder zza;

    zzdq(final ListenerHolder listenerHolder) {
        zza = listenerHolder;
    }

    
    public synchronized void zzc(final ListenerHolder listenerHolder) {
        final ListenerHolder listenerHolder2 = zza;
        if (listenerHolder2 != listenerHolder) {
            listenerHolder2.clear();
            zza = listenerHolder;
        }
    }

    public void zzd(final DeviceOrientation deviceOrientation) {
        final ListenerHolder listenerHolder;
        synchronized (this) {
            listenerHolder = zza;
        }
        listenerHolder.notifyListener(new zzdp(this, deviceOrientation));
    }

    
    public synchronized void zze() {
        zza.clear();
    }
}
