package com.google.android.gms.internal.location;

import androidx.annotation.GuardedBy;
import com.google.android.gms.common.api.internal.ListenerHolder;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzaz implements zzdr {
    @GuardedBy("this")
    private ListenerHolder zza;

    zzaz(final ListenerHolder listenerHolder) {
        zza = listenerHolder;
    }

    public synchronized ListenerHolder zza() {
        return zza;
    }

    public synchronized void zzb(final ListenerHolder listenerHolder) {
        final ListenerHolder listenerHolder2 = zza;
        if (listenerHolder2 != listenerHolder) {
            listenerHolder2.clear();
            zza = listenerHolder;
        }
    }

    public void zzc() {
    }
}
