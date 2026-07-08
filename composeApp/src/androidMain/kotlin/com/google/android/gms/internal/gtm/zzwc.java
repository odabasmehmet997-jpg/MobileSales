package com.google.android.gms.internal.gtm;

import java.util.Collections;
import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzwc {
    
    public static final Comparator zza = new zzvv();
    private static final zzwc zzb = new zzwc(new zzwa(Collections.emptyList()));
    private final zzwa zzc;

    private zzwc(final zzwa zzwa) {
        zzc = zzwa;
    }

    public boolean equals(final Object obj) {
        return (obj instanceof zzwc) && ((zzwc) obj).zzc.equals(zzc);
    }

    public int hashCode() {
        return ~zzc.hashCode();
    }

    public String toString() {
        return zzc.toString();
    }
}
