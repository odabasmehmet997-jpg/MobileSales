package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzjs extends zzii implements zzln {
    private static final zzjs zza;
    private float[] zzb;
    private int zzc;

    static {
        zzjs zzjs = new zzjs(new float[0], 0);
        zza = zzjs;
        zzjs.zzb();
    }

    zzjs() {
        this(new float[10], 0);
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
        float floatValue = ((Float) obj).floatValue();
        zzbK();
        if (0 > i2 || i2 > (i3 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzf(i2));
        }
        float[] fArr = this.zzb;
        if (i3 < fArr.length) {
            System.arraycopy(fArr, i2, fArr, i2 + 1, i3 - i2);
        } else {
            float[] fArr2 = new float[(((i3 * 3) / 2) + 1)];
            System.arraycopy(fArr, 0, fArr2, 0, i2);
            System.arraycopy(this.zzb, i2, fArr2, i2 + 1, this.zzc - i2);
            this.zzb = fArr2;
        }
        this.zzb[i2] = floatValue;
        this.zzc++;
        this.modCount++;
    }

    public boolean addAll(Collection collection) {
        zzbK();
        zzkh.zze(collection);
        if (!(collection instanceof final zzjs zzjs)) {
            return super.addAll(collection);
        }
        int i2 = zzjs.zzc;
        if (0 == i2) {
            return false;
        }
        int i3 = this.zzc;
        if (Integer.MAX_VALUE - i3 >= i2) {
            int i4 = i3 + i2;
            float[] fArr = this.zzb;
            if (i4 > fArr.length) {
                this.zzb = Arrays.copyOf(fArr, i4);
            }
            System.arraycopy(zzjs.zzb, 0, this.zzb, this.zzc, zzjs.zzc);
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
        if (!(obj instanceof final zzjs zzjs)) {
            return super.equals(obj);
        }
        if (this.zzc != zzjs.zzc) {
            return false;
        }
        float[] fArr = zzjs.zzb;
        for (int i2 = 0; i2 < this.zzc; i2++) {
            if (Float.floatToIntBits(this.zzb[i2]) != Float.floatToIntBits(fArr[i2])) {
                return false;
            }
        }
        return true;
    }

    public Object get(int i2) {
        zzg(i2);
        return Float.valueOf(this.zzb[i2]);
    }

    public int hashCode() {
        int i2 = 1;
        for (int i3 = 0; i3 < this.zzc; i3++) {
            i2 = (i2 * 31) + Float.floatToIntBits(this.zzb[i3]);
        }
        return i2;
    }

    public int indexOf(Object obj) {
        if (!(obj instanceof Float)) {
            return -1;
        }
        float floatValue = ((Float) obj).floatValue();
        int i2 = this.zzc;
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.zzb[i3] == floatValue) {
                return i3;
            }
        }
        return -1;
    }

    public Object remove(int i2) {
        zzbK();
        zzg(i2);
        float[] fArr = this.zzb;
        float f2 = fArr[i2];
        int i3 = this.zzc;
        if (i2 < i3 - 1) {
            System.arraycopy(fArr, i2 + 1, fArr, i2, (i3 - i2) - 1);
        }
        this.zzc--;
        this.modCount++;
        return Float.valueOf(f2);
    }

    
    public void removeRange(int i2, int i3) {
        zzbK();
        if (i3 >= i2) {
            float[] fArr = this.zzb;
            System.arraycopy(fArr, i3, fArr, i2, this.zzc - i3);
            this.zzc -= i3 - i2;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public Object set(int i2, Object obj) {
        float floatValue = ((Float) obj).floatValue();
        zzbK();
        zzg(i2);
        float[] fArr = this.zzb;
        float f2 = fArr[i2];
        fArr[i2] = floatValue;
        return Float.valueOf(f2);
    }

    public int size() {
        return this.zzc;
    }

    public zzkg zzd(int i2) {
        if (i2 >= this.zzc) {
            return new zzjs(Arrays.copyOf(this.zzb, i2), this.zzc);
        }
        throw new IllegalArgumentException();
    }

    public void zze(float f2) {
        zzbK();
        int i2 = this.zzc;
        float[] fArr = this.zzb;
        if (i2 == fArr.length) {
            float[] fArr2 = new float[(((i2 * 3) / 2) + 1)];
            System.arraycopy(fArr, 0, fArr2, 0, i2);
            this.zzb = fArr2;
        }
        float[] fArr3 = this.zzb;
        int i3 = this.zzc;
        this.zzc = i3 + 1;
        fArr3[i3] = f2;
    }

    private zzjs(float[] fArr, int i2) {
        this.zzb = fArr;
        this.zzc = i2;
    }

    public boolean add(Object obj) {
        zze(((Float) obj).floatValue());
        return true;
    }
}
