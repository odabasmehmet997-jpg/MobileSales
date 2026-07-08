package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzwd {
    private static final String[] zza = {"com.google.common.flogger.util.StackWalkerStackGetter", "com.google.common.flogger.util.JavaLangAccessStackGetter"};
    private static final zzwh zzb;

    static {
        zzwh zzwi;
        int i2 = 0;
        while (true) {
            if (2 <= i2) {
                zzwi = new zzwi();
                break;
            }
            zzwi = null;
            try {
                zzwi = Class.forName(zza[i2]).asSubclass(zzwh.class).getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
            } catch (Throwable unused) {
            }
            if (null != zzwi) {
                break;
            }
            i2++;
        }
        zzb = zzwi;
    }
}
