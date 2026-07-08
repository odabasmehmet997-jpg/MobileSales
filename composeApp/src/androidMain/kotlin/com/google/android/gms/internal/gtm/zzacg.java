package com.google.android.gms.internal.gtm;

import java.util.Arrays;
import java.util.Collection;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzacg extends zzyj implements zzack {
    private static final int[] zza;
    private static final zzacg zzb;
    private int[] zzc;
    private int zzd;

    static {
        int[] iArr = new int[0];
        zza = iArr;
        zzb = new zzacg(iArr, 0, false);
    }

    zzacg() {
        this(zza, 0, true);
    }

    public static zzacg zzf() {
        return zzb;
    }

    private static int zzj(int i2) {
        return Math.max(((i2 * 3) / 2) + 1, 10);
    }

    private String zzk(int i2) {
        int i3 = this.zzd;
        return "Index:" + i2 + ", Size:" + i3;
    }

    private void zzl(int i2) {
        if (0 > i2 || i2 >= this.zzd) {
            throw new IndexOutOfBoundsException(zzk(i2));
        }
    }

    public void add(int i2, Object obj) {
        int i3;
        int intValue = ((Integer) obj).intValue();
        zza();
        if (0 > i2 || i2 > (i3 = this.zzd)) {
            throw new IndexOutOfBoundsException(zzk(i2));
        }
        int i4 = i2 + 1;
        int[] iArr = this.zzc;
        int length = iArr.length;
        if (i3 < length) {
            System.arraycopy(iArr, i2, iArr, i4, i3 - i2);
        } else {
            int[] iArr2 = new int[zzj(length)];
            System.arraycopy(this.zzc, 0, iArr2, 0, i2);
            System.arraycopy(this.zzc, i2, iArr2, i4, this.zzd - i2);
            this.zzc = iArr2;
        }
        this.zzc[i2] = intValue;
        this.zzd++;
        this.modCount++;
    }

    public boolean addAll(Collection collection) {
        zza();
        byte[] bArr = zzaco.zzb;
        collection.getClass();
        if (!(collection instanceof final zzacg zzacg)) {
            return super.addAll(collection);
        }
        int i2 = zzacg.zzd;
        if (0 == i2) {
            return false;
        }
        int i3 = this.zzd;
        if (Integer.MAX_VALUE - i3 >= i2) {
            int i4 = i3 + i2;
            int[] iArr = this.zzc;
            if (i4 > iArr.length) {
                this.zzc = Arrays.copyOf(iArr, i4);
            }
            System.arraycopy(zzacg.zzc, 0, this.zzc, this.zzd, zzacg.zzd);
            this.zzd = i4;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public boolean contains(Object obj) {
        return -1 != indexOf(obj);
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzacg zzacg)) {
            return super.equals(obj);
        }
        if (zzd != zzacg.zzd) {
            return false;
        }
        final int[] iArr = zzacg.zzc;
        for (int i2 = 0; i2 < zzd; i2++) {
            if (zzc[i2] != iArr[i2]) {
                return false;
            }
        }
        return true;
    }

    public Object get(final int i2) {
        this.zzl(i2);
        return Integer.valueOf(zzc[i2]);
    }

    public int hashCode() {
        int i2 = 1;
        for (int i3 = 0; i3 < zzd; i3++) {
            i2 = (i2 * 31) + zzc[i3];
        }
        return i2;
    }

    public int indexOf(final Object obj) {
        if (!(obj instanceof Integer)) {
            return -1;
        }
        final int intValue = ((Integer) obj).intValue();
        final int i2 = zzd;
        for (int i3 = 0; i3 < i2; i3++) {
            if (zzc[i3] == intValue) {
                return i3;
            }
        }
        return -1;
    }

    public Object remove(final int i2) {
        this.zza();
        this.zzl(i2);
        final int[] iArr = zzc;
        final int i3 = iArr[i2];
        final int i4 = zzd;
        if (i2 < i4 - 1) {
            System.arraycopy(iArr, i2 + 1, iArr, i2, (i4 - i2) - 1);
        }
        zzd--;
        modCount++;
        return Integer.valueOf(i3);
    }

    
    public void removeRange(final int i2, final int i3) {
        this.zza();
        if (i3 >= i2) {
            final int[] iArr = zzc;
            System.arraycopy(iArr, i3, iArr, i2, zzd - i3);
            zzd -= i3 - i2;
            modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public Object set(final int i2, final Object obj) {
        final int intValue = ((Integer) obj).intValue();
        this.zza();
        this.zzl(i2);
        final int[] iArr = zzc;
        final int i3 = iArr[i2];
        iArr[i2] = intValue;
        return Integer.valueOf(i3);
    }

    public int size() {
        return zzd;
    }

    public int zze(final int i2) {
        this.zzl(i2);
        return zzc[i2];
    }

    /* renamed from: zzg */
    public zzack zzd(final int i2) {
        final int[] iArr;
        if (i2 >= zzd) {
            if (0 == i2) {
                iArr = zzacg.zza;
            } else {
                iArr = Arrays.copyOf(zzc, i2);
            }
            return new zzacg(iArr, zzd, true);
        }
        throw new IllegalArgumentException();
    }

    public void zzh(final int i2) {
        this.zza();
        final int i3 = zzd;
        final int length = zzc.length;
        if (i3 == length) {
            final int[] iArr = new int[zzacg.zzj(length)];
            System.arraycopy(zzc, 0, iArr, 0, zzd);
            zzc = iArr;
        }
        final int[] iArr2 = zzc;
        final int i4 = zzd;
        zzd = i4 + 1;
        iArr2[i4] = i2;
    }

    
    public void zzi(final int i2) {
        int length = zzc.length;
        if (i2 > length) {
            if (0 != length) {
                while (length < i2) {
                    length = zzacg.zzj(length);
                }
                zzc = Arrays.copyOf(zzc, length);
                return;
            }
            zzc = new int[Math.max(i2, 10)];
        }
    }

    private zzacg(final int[] iArr, final int i2, final boolean z) {
        super(z);
        zzc = iArr;
        zzd = i2;
    }

    public boolean add(final Object obj) {
        this.zzh(((Integer) obj).intValue());
        return true;
    }
}
