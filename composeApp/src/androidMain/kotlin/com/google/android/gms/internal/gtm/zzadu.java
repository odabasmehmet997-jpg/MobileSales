package com.google.android.gms.internal.gtm;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzadu extends zzyj {
    private static final Object[] zza;
    private static final zzadu zzb;
    private Object[] zzc;
    private int zzd;

    static {
        final Object[] objArr = new Object[0];
        zza = objArr;
        zzb = new zzadu(objArr, 0, false);
    }

    zzadu() {
        this(zzadu.zza, 0, true);
    }

    public static zzadu zze() {
        return zzadu.zzb;
    }

    private static int zzg(final int i2) {
        return Math.max(((i2 * 3) / 2) + 1, 10);
    }

    private String zzh(final int i2) {
        final int i3 = zzd;
        return "Index:" + i2 + ", Size:" + i3;
    }

    private void zzi(final int i2) {
        if (0 > i2 || i2 >= zzd) {
            throw new IndexOutOfBoundsException(this.zzh(i2));
        }
    }

    public void add(final int i2, final Object obj) {
        final int i3;
        this.zza();
        if (0 > i2 || i2 > (i3 = zzd)) {
            throw new IndexOutOfBoundsException(this.zzh(i2));
        }
        final int i4 = i2 + 1;
        final Object[] objArr = zzc;
        final int length = objArr.length;
        if (i3 < length) {
            System.arraycopy(objArr, i2, objArr, i4, i3 - i2);
        } else {
            final Object[] objArr2 = new Object[zzadu.zzg(length)];
            System.arraycopy(zzc, 0, objArr2, 0, i2);
            System.arraycopy(zzc, i2, objArr2, i4, zzd - i2);
            zzc = objArr2;
        }
        zzc[i2] = obj;
        zzd++;
        modCount++;
    }

    public Object get(final int i2) {
        this.zzi(i2);
        return zzc[i2];
    }

    public Object remove(final int i2) {
        this.zza();
        this.zzi(i2);
        final Object[] objArr = zzc;
        final Object obj = objArr[i2];
        final int i3 = zzd;
        if (i2 < i3 - 1) {
            System.arraycopy(objArr, i2 + 1, objArr, i2, (i3 - i2) - 1);
        }
        zzd--;
        modCount++;
        return obj;
    }

    public Object set(final int i2, final Object obj) {
        this.zza();
        this.zzi(i2);
        final Object[] objArr = zzc;
        final Object obj2 = objArr[i2];
        objArr[i2] = obj;
        modCount++;
        return obj2;
    }

    public int size() {
        return zzd;
    }

    public zzacn zzd(final int i2) {
        final Object[] objArr;
        if (i2 >= zzd) {
            if (0 == i2) {
                objArr = zzadu.zza;
            } else {
                objArr = Arrays.copyOf(zzc, i2);
            }
            return new zzadu(objArr, zzd, true);
        }
        throw new IllegalArgumentException();
    }

    
    public void zzf(final int i2) {
        int length = zzc.length;
        if (i2 > length) {
            if (0 != length) {
                while (length < i2) {
                    length = zzadu.zzg(length);
                }
                zzc = Arrays.copyOf(zzc, length);
                return;
            }
            zzc = new Object[Math.max(i2, 10)];
        }
    }

    private zzadu(final Object[] objArr, final int i2, final boolean z) {
        super(z);
        zzc = objArr;
        zzd = i2;
    }

    public boolean add(final Object obj) {
        this.zza();
        final int i2 = zzd;
        final int length = zzc.length;
        if (i2 == length) {
            zzc = Arrays.copyOf(zzc, zzadu.zzg(length));
        }
        final Object[] objArr = zzc;
        final int i3 = zzd;
        zzd = i3 + 1;
        objArr[i3] = obj;
        modCount++;
        return true;
    }
}
