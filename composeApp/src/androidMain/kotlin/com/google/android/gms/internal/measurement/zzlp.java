package com.google.android.gms.internal.measurement;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzlp extends zzii {
    private static final zzlp zza;
    private Object[] zzb;
    private int zzc;

    static {
        zzlp zzlp = new zzlp(new Object[0], 0);
        zza = zzlp;
        zzlp.zzb();
    }

    zzlp() {
        this(new Object[10], 0);
    }

    public static zzlp zze() {
        return zza;
    }

    private String zzf(int i2) {
        int i3 = this.zzc;
        final String sb = "Index:" +
                i2 +
                ", Size:" +
                i3;
        return sb;
    }

    private void zzg(int i2) {
        if (0 > i2 || i2 >= this.zzc) {
            throw new IndexOutOfBoundsException(zzf(i2));
        }
    }

    public void add(int i2, Object obj) {
        int i3;
        zzbK();
        if (0 > i2 || i2 > (i3 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzf(i2));
        }
        Object[] objArr = this.zzb;
        if (i3 < objArr.length) {
            System.arraycopy(objArr, i2, objArr, i2 + 1, i3 - i2);
        } else {
            Object[] objArr2 = new Object[(((i3 * 3) / 2) + 1)];
            System.arraycopy(objArr, 0, objArr2, 0, i2);
            System.arraycopy(this.zzb, i2, objArr2, i2 + 1, this.zzc - i2);
            this.zzb = objArr2;
        }
        this.zzb[i2] = obj;
        this.zzc++;
        this.modCount++;
    }

    public Object get(int i2) {
        zzg(i2);
        return this.zzb[i2];
    }

    public Object remove(int i2) {
        zzbK();
        zzg(i2);
        Object[] objArr = this.zzb;
        Object obj = objArr[i2];
        int i3 = this.zzc;
        if (i2 < i3 - 1) {
            System.arraycopy(objArr, i2 + 1, objArr, i2, (i3 - i2) - 1);
        }
        this.zzc--;
        this.modCount++;
        return obj;
    }

    public Object set(int i2, Object obj) {
        zzbK();
        zzg(i2);
        Object[] objArr = this.zzb;
        Object obj2 = objArr[i2];
        objArr[i2] = obj;
        this.modCount++;
        return obj2;
    }

    public int size() {
        return this.zzc;
    }

    public zzkg zzd(int i2) {
        if (i2 >= this.zzc) {
            return new zzlp(Arrays.copyOf(this.zzb, i2), this.zzc);
        }
        throw new IllegalArgumentException();
    }

    private zzlp(Object[] objArr, int i2) {
        this.zzb = objArr;
        this.zzc = i2;
    }

    public boolean add(Object obj) {
        zzbK();
        int i2 = this.zzc;
        Object[] objArr = this.zzb;
        if (i2 == objArr.length) {
            this.zzb = Arrays.copyOf(objArr, ((i2 * 3) / 2) + 1);
        }
        Object[] objArr2 = this.zzb;
        int i3 = this.zzc;
        this.zzc = i3 + 1;
        objArr2[i3] = obj;
        this.modCount++;
        return true;
    }
}
