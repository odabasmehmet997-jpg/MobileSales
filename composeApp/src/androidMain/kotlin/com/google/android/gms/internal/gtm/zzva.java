package com.google.android.gms.internal.gtm;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzva {
    private static final zzvc zza = zzb(zzvc.zzd);

    private static zzvc zzb(String[] strArr) {
        zzvg zzvg;
        try {
            zzvg = zzvh.zza;
        } catch (NoClassDefFoundError unused) {
            zzvg = null;
        }
        if (null != zzvg) {
            return zzvg;
        }
        StringBuilder sb = new StringBuilder();
        int length = strArr.length;
        int i2 = 0;
        while (i2 < length) {
            String str = strArr[i2];
            try {
                return (zzvc) Class.forName(str).getConstructor((Class[]) null).newInstance((Object[]) null);
            } catch (Throwable th) {
                th = th;
                if (th instanceof InvocationTargetException) {
                    th = th.getCause();
                }
                sb.append(10);
                sb.append(str);
                sb.append(": ");
                sb.append(th);
                i2++;
            }
        }
        throw new IllegalStateException(sb.insert(0, "No logging platforms found:").toString());
    }
}
