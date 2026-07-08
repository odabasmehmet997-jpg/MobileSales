package com.google.android.gms.internal.measurement;

import android.os.Binder;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public enum zzhc {
    ;

    public static Object zza(final zzhd zzhd) {
        long clearCallingIdentity;
        try {
            return zzhd.zza();
        } catch (final SecurityException unused) {
            clearCallingIdentity = Binder.clearCallingIdentity();
            final Object zza = zzhd.zza();
            Binder.restoreCallingIdentity(clearCallingIdentity);
            return zza;
        } catch (final Throwable th) {
            Binder.restoreCallingIdentity(clearCallingIdentity);
            throw th;
        }
    }
}
