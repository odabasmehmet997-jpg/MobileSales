package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzu;
import java.util.concurrent.Callable;

/**
 * @param zza synthetic
 * @param zzb synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public record zzfg(zzfk zza, String zzb) implements Callable {
    /* synthetic */

    public Object call() {
        return new zzu("internal.appMetadata", new zzff(this.zza, this.zzb));
    }
}
