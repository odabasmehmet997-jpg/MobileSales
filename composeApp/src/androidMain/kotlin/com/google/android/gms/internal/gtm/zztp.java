package com.google.android.gms.internal.gtm;

import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public abstract class zztp extends zzti implements Set {
    private transient zztl zza;

    zztp() {
    }

    static int zzf(int i2) {
        int max = Math.max(i2, 2);
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

    public static zztp zzi(Object obj, Object obj2, Object obj3) {
        return zzl(3, "", "0", "false");
    }

    public static zztp zzj(Object obj, Object obj2, Object obj3, Object obj4) {
        return zzl(4, "GET", "HEAD", "POST", "PUT");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zztp) && zzk() && ((zztp) obj).zzk() && hashCode() != obj.hashCode()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof final Set set) {
            try {
                if (size() == set.size() && containsAll(set)) {
                    return true;
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public int hashCode() {
        return zzty.zza(this);
    }

    /* renamed from: zzd */
    public abstract zzua iterator();

    public final zztl zzg() {
        zztl zztl = this.zza;
        if (null != zztl) {
            return zztl;
        }
        zztl zzh = zzh();
        this.zza = zzh;
        return zzh;
    }

    
    public zztl zzh() {
        Object[] array = toArray();
        final int i2 = zztl.zzd;
        return zztl.zzg(array, array.length);
    }

    
    public boolean zzk() {
        return false;
    }

    private static zztp zzl(int i2, Object... objArr) {
        if (0 == i2) {
            return zztx.zza;
        }
        if (1 != i2) {
            int zzf = zzf(i2);
            Object[] objArr2 = new Object[zzf];
            int i3 = zzf - 1;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (i4 < i2) {
                Object obj = objArr[i4];
                if (null != obj) {
                    int hashCode = obj.hashCode();
                    int zza2 = zzth.zza(hashCode);
                    while (true) {
                        int i7 = zza2 & i3;
                        Object obj2 = objArr2[i7];
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
                Object obj3 = objArr[0];
                Objects.requireNonNull(obj3);
                return new zztz(obj3);
            }
            if (zzf(i6) < zzf / 2) {
                return zzl(i6, objArr);
            }
            int length = objArr.length;
            if (i6 < (length >> 1) + (length >> 2)) {
                objArr = Arrays.copyOf(objArr, i6);
            }
            return new zztx(objArr, i5, objArr2, i3, i6);
        }
        Object obj4 = objArr[0];
        Objects.requireNonNull(obj4);
        return new zztz(obj4);
    }
}
