package com.google.android.gms.internal.maps;

import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public abstract class zzbm extends zzbf implements Set {
    private transient zzbi zza;

    zzbm() {
    }

    static int zzf(final int i2) {
        final int max = Math.max(i2, 2);
        if (751619276 > max) {
            int highestOneBit = Integer.highestOneBit(max - 1);
            do {
                highestOneBit += highestOneBit;
            } while (highestOneBit * 0.7d < max);
            return highestOneBit;
        } else if (1073741824 > max) {
            return BasicMeasure.EXACTLY;
        } else {
            throw new IllegalArgumentException("collection too large");
        }
    }

    @SafeVarargs
    public static zzbm zzi(final Object obj, final Object obj2, final Object obj3, final Object obj4, final Object obj5, final Object obj6, final Object... objArr) {
        final Object[] objArr2 = new Object[7];
        objArr2[0] = "ADMINISTRATIVE_AREA_LEVEL_1";
        objArr2[1] = "ADMINISTRATIVE_AREA_LEVEL_2";
        objArr2[2] = "COUNTRY";
        objArr2[3] = "LOCALITY";
        objArr2[4] = "POSTAL_CODE";
        objArr2[5] = "SCHOOL_DISTRICT";
        System.arraycopy(objArr, 0, objArr2, 6, 1);
        return zzbm.zzk(7, objArr2);
    }

    public final boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzbm) && this.zzj() && ((zzbm) obj).zzj() && this.hashCode() != obj.hashCode()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof Set set) {
            try {
                if (this.size() == set.size() && this.containsAll(set)) {
                    return true;
                }
            } catch (final ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public int hashCode() {
        return zzbv.zza(this);
    }

    /* renamed from: zzd */
    public abstract zzbx iterator();

    public final zzbi zzg() {
        final zzbi zzbi = zza;
        if (null != zzbi) {
            return zzbi;
        }
        final zzbi zzh = this.zzh();
        zza = zzh;
        return zzh;
    }

    
    public zzbi zzh() {
        final Object[] array = this.toArray();
        final int i2 = zzbi.zzd;
        return zzbi.zzg(array, array.length);
    }

    
    public boolean zzj() {
        return false;
    }

    private static zzbm zzk(final int i2, Object... objArr) {
        if (0 == i2) {
            return zzbu.zza;
        }
        if (1 != i2) {
            final int zzf = zzbm.zzf(i2);
            final Object[] objArr2 = new Object[zzf];
            final int i3 = zzf - 1;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (i4 < i2) {
                final Object obj = objArr[i4];
                if (null != obj) {
                    final int hashCode = obj.hashCode();
                    int zza2 = zzbe.zza(hashCode);
                    while (true) {
                        final int i7 = zza2 & i3;
                        final Object obj2 = objArr2[i7];
                        if (null != obj2) {
                            if (obj2.equals(obj)) {
                                break;
                            }
                            zza2++;
                        } else {
                            objArr[i6] = obj;
                            objArr2[i7] = obj;
                            i5 += hashCode;
                            i6++;
                            break;
                        }
                    }
                    i4++;
                } else {
                    throw new NullPointerException("at index " + i4);
                }
            }
            Arrays.fill(objArr, i6, i2, null);
            if (1 == i6) {
                final Object obj3 = objArr[0];
                Objects.requireNonNull(obj3);
                return new zzbw(obj3);
            }
            if (zzbm.zzf(i6) < zzf / 2) {
                return zzbm.zzk(i6, objArr);
            }
            if (4 > i6) {
                objArr = Arrays.copyOf(objArr, i6);
            }
            return new zzbu(objArr, i5, objArr2, i3, i6);
        }
        final Object obj4 = objArr[0];
        Objects.requireNonNull(obj4);
        return new zzbw(obj4);
    }
}
