package com.google.android.gms.internal.gtm;

import java.util.*;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzwa extends AbstractMap {
    
    public static final Comparator zza = new zzvx();
    
    public final Object[] zzb;
    
    public final int[] zzc;
    private final Set zzd = new zzvz(this, -1);
    private Integer zze;
    private String zzf;

    zzwa(final List list) {
        final Iterator it = list.iterator();
        if (!it.hasNext()) {
            final int size = list.size();
            Object[] objArr = new Object[size];
            final Iterator it2 = list.iterator();
            if (!it2.hasNext()) {
                final int[] iArr = {0};
                if (16 < size && 0 < size * 9) {
                    objArr = Arrays.copyOf(objArr, 0);
                }
                zzb = objArr;
                zzc = iArr;
                return;
            }
            final String unused = ((zzvw) it2.next()).zza();
            throw null;
        }
        final String unused2 = ((zzvw) it.next()).zza();
        throw null;
    }

    public Set entrySet() {
        return zzd;
    }

    public int hashCode() {
        if (null == this.zze) {
            zze = Integer.valueOf(super.hashCode());
        }
        return zze.intValue();
    }

    public String toString() {
        if (null == this.zzf) {
            zzf = super.toString();
        }
        return zzf;
    }
}
