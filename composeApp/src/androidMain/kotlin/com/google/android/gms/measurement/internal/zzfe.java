package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzn;
import java.util.concurrent.Callable;

/**
 * @param zza synthetic
 * @param zzb synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public record zzfe(zzfk zza, String zzb) implements Callable {
    /* synthetic */

    public Object call() {
        return new zzn("internal.remoteConfig", new zzfj(this.zza, this.zzb));
    }
}
