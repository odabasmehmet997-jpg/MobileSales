package com.google.android.gms.internal.gtm;

import android.os.Build;
import dalvik.system.VMStack;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzvg extends zzvc {
    private static final boolean zza = zzvg.zza.zza();
    private static final boolean zzb;
    private static final zzvb zzc = new zzvb() {
    };

    /* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
    final class zza {
        zza() {
        }

        static boolean zza() {
            return zzt();
        }
    }

    static {
        final String str = Build.FINGERPRINT;
        boolean z = null == str || "robolectric".equals(str);
        zzb = z;
    }

    static String zzq() {
        try {
            return VMStack.getStackClass2().getName();
        } catch (final Throwable unused) {
            return null;
        }
    }

    static boolean zzt() {
        try {
            Class.forName("dalvik.system.VMStack").getMethod("getStackClass2", (Class[]) null);
            return zza.class.getName().equals(zzvg.zzq());
        } catch (final Throwable unused) {
            return false;
        }
    }
}
