package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzka extends zzii implements zzke, zzln {
    private static final zzka zza;
    private int[] zzb;
    private int zzc;

    static {
        zzka zzka = new zzka(new int[0], 0);
        zza = zzka;
        zzka.zzb();
    }

    zzka() {
        this(new int[10], 0);
    }

    public static zzka zzf() {
        return zza;
    }

    private String zzi(int i2) {
        int i3 = this.zzc;
        final String sb = "Index:" +
                i2 +
                ", Size:" +
                i3;
        return sb;
    }

    private void zzj(int i2) {
        if (0 > i2 || i2 >= this.zzc) {
            throw new IndexOutOfBoundsException(zzi(i2));
        }
    }

    public void add(int i2, Object obj) {
        int i3;
        int intValue = ((Integer) obj).intValue();
        zzbK();
        if (0 > i2 || i2 > (i3 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzi(i2));
        }
        int[] iArr = this.zzb;
        if (i3 < iArr.length) {
            System.arraycopy(iArr, i2, iArr, i2 + 1, i3 - i2);
        } else {
            int[] iArr2 = new int[(((i3 * 3) / 2) + 1)];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            System.arraycopy(this.zzb, i2, iArr2, i2 + 1, this.zzc - i2);
            this.zzb = iArr2;
        }
        this.zzb[i2] = intValue;
        this.zzc++;
        this.modCount++;
    }

    public boolean addAll(Collection collection) {
        zzbK();
        zzkh.zze(collection);
        if (!(collection instanceof final zzka zzka)) {
            return super.addAll(collection);
        }
        int i2 = zzka.zzc;
        if (0 == i2) {
            return false;
        }
        int i3 = this.zzc;
        if (Integer.MAX_VALUE - i3 >= i2) {
            int i4 = i3 + i2;
            int[] iArr = this.zzb;
            if (i4 > iArr.length) {
                this.zzb = Arrays.copyOf(iArr, i4);
            }
            System.arraycopy(zzka.zzb, 0, this.zzb, this.zzc, zzka.zzc);
            this.zzc = i4;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public boolean contains(Object obj) {
        return this.contains(obj);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof final zzka zzka)) {
            return super.equals(obj);
        }
        if (this.zzc != zzka.zzc) {
            return false;
        }
        int[] iArr = zzka.zzb;
        for (int i2 = 0; i2 < this.zzc; i2++) {
            if (this.zzb[i2] != iArr[i2]) {
                return false;
            }
        }
        return true;
    }

    public Object get(int i2) {
        zzj(i2);
        return Integer.valueOf(this.zzb[i2]);
    }

    public int hashCode() {
        int i2 = 1;
        for (int i3 = 0; i3 < this.zzc; i3++) {
            i2 = (i2 * 31) + this.zzb[i3];
        }
        return i2;
    }

    public int indexOf(Object obj) {
        if (!(obj instanceof Integer)) {
            return -1;
        }
        int intValue = ((Integer) obj).intValue();
        int i2 = this.zzc;
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.zzb[i3] == intValue) {
                return i3;
            }
        }
        return -1;
    }

    public Object remove(int i2) {
        zzbK();
        zzj(i2);
        int[] iArr = this.zzb;
        int i3 = iArr[i2];
        int i4 = this.zzc;
        if (i2 < i4 - 1) {
            System.arraycopy(iArr, i2 + 1, iArr, i2, (i4 - i2) - 1);
        }
        this.zzc--;
        this.modCount++;
        return Integer.valueOf(i3);
    }

    
    public void removeRange(int i2, int i3) {
        zzbK();
        if (i3 >= i2) {
            int[] iArr = this.zzb;
            System.arraycopy(iArr, i3, iArr, i2, this.zzc - i3);
            this.zzc -= i3 - i2;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public Object set(int i2, Object obj) {
        int intValue = ((Integer) obj).intValue();
        zzbK();
        zzj(i2);
        int[] iArr = this.zzb;
        int i3 = iArr[i2];
        iArr[i2] = intValue;
        return Integer.valueOf(i3);
    }

    public int size() {
        return this.zzc;
    }

    public int zze(int i2) {
        zzj(i2);
        return this.zzb[i2];
    }

    /* renamed from: zzg */
    public zzke zzd(int i2) {
        if (i2 >= this.zzc) {
            return new zzka(Arrays.copyOf(this.zzb, i2), this.zzc);
        }
        throw new IllegalArgumentException();
    }

    public void zzh(int i2) {
        zzbK();
        int i3 = this.zzc;
        int[] iArr = this.zzb;
        if (i3 == iArr.length) {
            int[] iArr2 = new int[(((i3 * 3) / 2) + 1)];
            System.arraycopy(iArr, 0, iArr2, 0, i3);
            this.zzb = iArr2;
        }
        int[] iArr3 = this.zzb;
        int i4 = this.zzc;
        this.zzc = i4 + 1;
        iArr3[i4] = i2;
    }

    private zzka(int[] iArr, int i2) {
        this.zzb = iArr;
        this.zzc = i2;
    }

    public boolean add(Object obj) {
        zzh(((Integer) obj).intValue());
        return true;
    }
}
