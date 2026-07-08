package com.google.android.gms.internal.maps;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzbu extends zzbm {
    static final zzbu zza;
    private static final Object[] zzd;
    final transient Object[] zzb;
    final transient Object[] zzc;
    private final transient int zze;
    private final transient int zzf;
    private final transient int zzg;

    static {
        final Object[] objArr = new Object[0];
        zzd = objArr;
        zza = new zzbu(objArr, 0, objArr, 0, 0);
    }

    zzbu(final Object[] objArr, final int i2, final Object[] objArr2, final int i3, final int i4) {
        zzb = objArr;
        zze = i2;
        zzc = objArr2;
        zzf = i3;
        zzg = i4;
    }

    public boolean contains(final Object obj) {
        if (null != obj) {
            final Object[] objArr = zzc;
            if (0 != objArr.length) {
                int zza2 = zzbe.zza(obj.hashCode());
                while (true) {
                    final int i2 = zza2 & zzf;
                    final Object obj2 = objArr[i2];
                    if (null == obj2) {
                        return false;
                    }
                    if (obj2.equals(obj)) {
                        return true;
                    }
                    zza2 = i2 + 1;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        return zze;
    }

    public Iterator iterator() {
        return this.zzg().listIterator(0);
    }

    public int size() {
        return zzg;
    }

    
    public int zza(final Object[] objArr, final int i2) {
        System.arraycopy(zzb, 0, objArr, 0, zzg);
        return zzg;
    }

    
    public int zzb() {
        return zzg;
    }

    
    public int zzc() {
        return 0;
    }

    public zzbx zzd() {
        return this.zzg().listIterator(0);
    }

    
    public Object[] zze() {
        return zzb;
    }

    
    public zzbi zzh() {
        return zzbi.zzg(zzb, zzg);
    }

    
    public boolean zzj() {
        return true;
    }
}
