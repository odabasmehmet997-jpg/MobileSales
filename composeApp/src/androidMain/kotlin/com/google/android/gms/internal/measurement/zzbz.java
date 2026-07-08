package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.util.Log;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
public final class zzbz extends zzce {
    private final AtomicReference zza = new AtomicReference();
    private boolean zzb;

    public static Object zze(final Bundle bundle, final Class cls) {
        final Object obj;
        if (null == bundle || null == (obj = bundle.get("r"))) {
            return null;
        }
        try {
            return cls.cast(obj);
        } catch (final ClassCastException e2) {
            Log.w("AM", String.format("Unexpected object type. Expected, Received: %s, %s", cls.getCanonicalName(), obj.getClass().getCanonicalName()), e2);
            throw e2;
        }
    }

    public android.os.Bundle zzb(final long r3) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbz.zzb(long):android.os.Bundle");
    }

    public String zzc(final long j2) {
        return (String) zzbz.zze(this.zzb(j2), String.class);
    }

    public void zzd(final Bundle bundle) {
        synchronized (zza) {
            try {
                zza.set(bundle);
                zzb = true;
                zza.notify();
            } catch (final Throwable th) {
                throw th;
            }
        }
    }
}
