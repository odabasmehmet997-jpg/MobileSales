package com.google.android.gms.internal.gtm;

import java.util.Arrays;
import java.util.Collection;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzada extends zzyj {
    private static final long[] zza;
    private long[] zzb;
    private int zzc;

    static {
        long[] jArr = new long[0];
        zza = jArr;
        new zzada(jArr, 0, false);
    }

    zzada() {
        this(zza, 0, true);
    }

    private static int zzh(int i2) {
        return Math.max(((i2 * 3) / 2) + 1, 10);
    }

    private String zzi(int i2) {
        int i3 = this.zzc;
        return "Index:" + i2 + ", Size:" + i3;
    }

    private void zzj(int i2) {
        if (0 > i2 || i2 >= this.zzc) {
            throw new IndexOutOfBoundsException(zzi(i2));
        }
    }

    public void add(int i2, Object obj) {
        int i3;
        long longValue = ((Long) obj).longValue();
        zza();
        if (0 > i2 || i2 > (i3 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzi(i2));
        }
        int i4 = i2 + 1;
        long[] jArr = this.zzb;
        int length = jArr.length;
        if (i3 < length) {
            System.arraycopy(jArr, i2, jArr, i4, i3 - i2);
        } else {
            long[] jArr2 = new long[zzh(length)];
            System.arraycopy(this.zzb, 0, jArr2, 0, i2);
            System.arraycopy(this.zzb, i2, jArr2, i4, this.zzc - i2);
            this.zzb = jArr2;
        }
        this.zzb[i2] = longValue;
        this.zzc++;
        this.modCount++;
    }

    public boolean addAll(Collection collection) {
        zza();
        byte[] bArr = zzaco.zzb;
        collection.getClass();
        if (!(collection instanceof final zzada zzada)) {
            return super.addAll(collection);
        }
        int i2 = zzada.zzc;
        if (0 == i2) {
            return false;
        }
        int i3 = this.zzc;
        if (Integer.MAX_VALUE - i3 >= i2) {
            int i4 = i3 + i2;
            long[] jArr = this.zzb;
            if (i4 > jArr.length) {
                this.zzb = Arrays.copyOf(jArr, i4);
            }
            System.arraycopy(zzada.zzb, 0, this.zzb, this.zzc, zzada.zzc);
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
        if (!(obj instanceof final zzada zzada)) {
            return super.equals(obj);
        }
        if (this.zzc != zzada.zzc) {
            return false;
        }
        long[] jArr = zzada.zzb;
        for (int i2 = 0; i2 < this.zzc; i2++) {
            if (this.zzb[i2] != jArr[i2]) {
                return false;
            }
        }
        return true;
    }

    public Object get(int i2) {
        zzj(i2);
        return Long.valueOf(this.zzb[i2]);
    }

    public int hashCode() {
        int i2 = 1;
        for (int i3 = 0; i3 < this.zzc; i3++) {
            long j2 = this.zzb[i3];
            byte[] bArr = zzaco.zzb;
            i2 = (i2 * 31) + ((int) (j2 ^ (j2 >>> 32)));
        }
        return i2;
    }

    public int indexOf(Object obj) {
        if (!(obj instanceof Long)) {
            return -1;
        }
        long longValue = ((Long) obj).longValue();
        int i2 = this.zzc;
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.zzb[i3] == longValue) {
                return i3;
            }
        }
        return -1;
    }

    public Object remove(int i2) {
        zza();
        zzj(i2);
        long[] jArr = this.zzb;
        long j2 = jArr[i2];
        int i3 = this.zzc;
        if (i2 < i3 - 1) {
            System.arraycopy(jArr, i2 + 1, jArr, i2, (i3 - i2) - 1);
        }
        this.zzc--;
        this.modCount++;
        return Long.valueOf(j2);
    }

    
    public void removeRange(int i2, int i3) {
        zza();
        if (i3 >= i2) {
            long[] jArr = this.zzb;
            System.arraycopy(jArr, i3, jArr, i2, this.zzc - i3);
            this.zzc -= i3 - i2;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public Object set(int i2, Object obj) {
        long longValue = ((Long) obj).longValue();
        zza();
        zzj(i2);
        long[] jArr = this.zzb;
        long j2 = jArr[i2];
        jArr[i2] = longValue;
        return Long.valueOf(j2);
    }

    public int size() {
        return this.zzc;
    }

    public zzacn zzd(int i2) {
        long[] jArr;
        if (i2 >= this.zzc) {
            if (0 == i2) {
                jArr = zza;
            } else {
                jArr = Arrays.copyOf(this.zzb, i2);
            }
            return new zzada(jArr, this.zzc, true);
        }
        throw new IllegalArgumentException();
    }

    public long zze(int i2) {
        zzj(i2);
        return this.zzb[i2];
    }

    public void zzf(long j2) {
        zza();
        int i2 = this.zzc;
        int length = this.zzb.length;
        if (i2 == length) {
            long[] jArr = new long[zzh(length)];
            System.arraycopy(this.zzb, 0, jArr, 0, this.zzc);
            this.zzb = jArr;
        }
        long[] jArr2 = this.zzb;
        int i3 = this.zzc;
        this.zzc = i3 + 1;
        jArr2[i3] = j2;
    }

    
    public void zzg(int i2) {
        int length = this.zzb.length;
        if (i2 > length) {
            if (0 != length) {
                while (length < i2) {
                    length = zzh(length);
                }
                this.zzb = Arrays.copyOf(this.zzb, length);
                return;
            }
            this.zzb = new long[Math.max(i2, 10)];
        }
    }

    private zzada(long[] jArr, int i2, boolean z) {
        super(z);
        this.zzb = jArr;
        this.zzc = i2;
    }

    public boolean add(Object obj) {
        zzf(((Long) obj).longValue());
        return true;
    }
}
