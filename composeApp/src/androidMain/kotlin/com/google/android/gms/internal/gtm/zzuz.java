package com.google.android.gms.internal.gtm;

import java.util.Iterator;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public enum zzuz {
    ;
    private static final zzuu zza = new zzux();
    private static final zzut zzb = new zzuy();

    public static zzur zza(final Set set) {
        final zzur zzur = new zzur(zzuz.zza, null);
        final Iterator it = set.iterator();
        while (it.hasNext()) {
            zzur.zzd((zzui) it.next());
        }
        return zzur;
    }
}
