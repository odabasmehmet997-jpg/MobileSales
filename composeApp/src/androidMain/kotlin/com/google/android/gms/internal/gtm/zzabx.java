package com.google.android.gms.internal.gtm;

import java.util.Arrays;
import java.util.Collection;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzabx extends zzyj {
    private static final float[] zza;
    private float[] zzb;
    private int zzc;

    static {
        final float[] fArr = new float[0];
        zza = fArr;
        new zzabx(fArr, 0, false);
    }

    zzabx() {
        this(zzabx.zza, 0, true);
    }

    private static int zzh(final int i2) {
        return Math.max(((i2 * 3) / 2) + 1, 10);
    }

    private String zzi(final int i2) {
        final int i3 = zzc;
        return "Index:" + i2 + ", Size:" + i3;
    }

    private void zzj(final int i2) {
        if (0 > i2 || i2 >= zzc) {
            throw new IndexOutOfBoundsException(this.zzi(i2));
        }
    }

    public void add(final int i2, final Object obj) {
        final int i3;
        final float floatValue = ((Float) obj).floatValue();
        this.zza();
        if (0 > i2 || i2 > (i3 = zzc)) {
            throw new IndexOutOfBoundsException(this.zzi(i2));
        }
        final int i4 = i2 + 1;
        final float[] fArr = zzb;
        final int length = fArr.length;
        if (i3 < length) {
            System.arraycopy(fArr, i2, fArr, i4, i3 - i2);
        } else {
            final float[] fArr2 = new float[zzabx.zzh(length)];
            System.arraycopy(zzb, 0, fArr2, 0, i2);
            System.arraycopy(zzb, i2, fArr2, i4, zzc - i2);
            zzb = fArr2;
        }
        zzb[i2] = floatValue;
        zzc++;
        modCount++;
    }

    public boolean addAll(final Collection collection) {
        this.zza();
        final byte[] bArr = zzaco.zzb;
        collection.getClass();
        if (!(collection instanceof zzabx zzabx)) {
            return super.addAll(collection);
        }
        final int i2 = zzabx.zzc;
        if (0 == i2) {
            return false;
        }
        final int i3 = zzc;
        if (Integer.MAX_VALUE - i3 >= i2) {
            final int i4 = i3 + i2;
            final float[] fArr = zzb;
            if (i4 > fArr.length) {
                zzb = Arrays.copyOf(fArr, i4);
            }
            System.arraycopy(zzabx.zzb, 0, zzb, zzc, zzabx.zzc);
            zzc = i4;
            modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public boolean contains(final Object obj) {
        return -1 != indexOf(obj);
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzabx zzabx)) {
            return super.equals(obj);
        }
        if (zzc != zzabx.zzc) {
            return false;
        }
        final float[] fArr = zzabx.zzb;
        for (int i2 = 0; i2 < zzc; i2++) {
            if (Float.floatToIntBits(zzb[i2]) != Float.floatToIntBits(fArr[i2])) {
                return false;
            }
        }
        return true;
    }

    public Object get(final int i2) {
        this.zzj(i2);
        return Float.valueOf(zzb[i2]);
    }

    public int hashCode() {
        int i2 = 1;
        for (int i3 = 0; i3 < zzc; i3++) {
            i2 = (i2 * 31) + Float.floatToIntBits(zzb[i3]);
        }
        return i2;
    }

    public int indexOf(final Object obj) {
        if (!(obj instanceof Float)) {
            return -1;
        }
        final float floatValue = ((Float) obj).floatValue();
        final int i2 = zzc;
        for (int i3 = 0; i3 < i2; i3++) {
            if (zzb[i3] == floatValue) {
                return i3;
            }
        }
        return -1;
    }

    public Object remove(final int i2) {
        this.zza();
        this.zzj(i2);
        final float[] fArr = zzb;
        final float f2 = fArr[i2];
        final int i3 = zzc;
        if (i2 < i3 - 1) {
            System.arraycopy(fArr, i2 + 1, fArr, i2, (i3 - i2) - 1);
        }
        zzc--;
        modCount++;
        return Float.valueOf(f2);
    }

    
    public void removeRange(final int i2, final int i3) {
        this.zza();
        if (i3 >= i2) {
            final float[] fArr = zzb;
            System.arraycopy(fArr, i3, fArr, i2, zzc - i3);
            zzc -= i3 - i2;
            modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public Object set(final int i2, final Object obj) {
        final float floatValue = ((Float) obj).floatValue();
        this.zza();
        this.zzj(i2);
        final float[] fArr = zzb;
        final float f2 = fArr[i2];
        fArr[i2] = floatValue;
        return Float.valueOf(f2);
    }

    public int size() {
        return zzc;
    }

    public zzacn zzd(final int i2) {
        final float[] fArr;
        if (i2 >= zzc) {
            if (0 == i2) {
                fArr = zzabx.zza;
            } else {
                fArr = Arrays.copyOf(zzb, i2);
            }
            return new zzabx(fArr, zzc, true);
        }
        throw new IllegalArgumentException();
    }

    public float zze(final int i2) {
        this.zzj(i2);
        return zzb[i2];
    }

    public void zzf(final float f2) {
        this.zza();
        final int i2 = zzc;
        final int length = zzb.length;
        if (i2 == length) {
            final float[] fArr = new float[zzabx.zzh(length)];
            System.arraycopy(zzb, 0, fArr, 0, zzc);
            zzb = fArr;
        }
        final float[] fArr2 = zzb;
        final int i3 = zzc;
        zzc = i3 + 1;
        fArr2[i3] = f2;
    }

    
    public void zzg(final int i2) {
        int length = zzb.length;
        if (i2 > length) {
            if (0 != length) {
                while (length < i2) {
                    length = zzabx.zzh(length);
                }
                zzb = Arrays.copyOf(zzb, length);
                return;
            }
            zzb = new float[Math.max(i2, 10)];
        }
    }

    private zzabx(final float[] fArr, final int i2, final boolean z) {
        super(z);
        zzb = fArr;
        zzc = i2;
    }

    public boolean add(final Object obj) {
        this.zzf(((Float) obj).floatValue());
        return true;
    }
}
