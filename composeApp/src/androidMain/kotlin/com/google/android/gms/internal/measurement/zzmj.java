package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
public final class zzmj {
    private static final zzmj zza = new zzmj(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzmj() {
        this(0, new int[8], new Object[8], true);
    }

    private zzmj(int i2, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i2;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    public static zzmj zzc() {
        return zza;
    }

    static zzmj zzd(zzmj zzmj, zzmj zzmj2) {
        int i2 = zzmj.zzb + zzmj2.zzb;
        int[] copyOf = Arrays.copyOf(zzmj.zzc, i2);
        System.arraycopy(zzmj2.zzc, 0, copyOf, zzmj.zzb, zzmj2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zzmj.zzd, i2);
        System.arraycopy(zzmj2.zzd, 0, copyOf2, zzmj.zzb, zzmj2.zzb);
        return new zzmj(i2, copyOf, copyOf2, true);
    }

    static zzmj zze() {
        return new zzmj(0, new int[8], new Object[8], true);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (null == obj || !(obj instanceof final zzmj zzmj)) {
            return false;
        }
        int i2 = this.zzb;
        if (i2 == zzmj.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzmj.zzc;
            int i3 = 0;
            while (true) {
                if (i3 >= i2) {
                    Object[] objArr = this.zzd;
                    Object[] objArr2 = zzmj.zzd;
                    int i4 = this.zzb;
                    int i5 = 0;
                    while (i5 < i4) {
                        if (objArr[i5].equals(objArr2[i5])) {
                            i5++;
                        }
                    }
                    return true;
                } else if (iArr[i3] != iArr2[i3]) {
                    break;
                } else {
                    i3++;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int i2 = this.zzb;
        int i3 = (i2 + 527) * 31;
        int[] iArr = this.zzc;
        int i4 = 17;
        int i5 = 17;
        for (int i6 = 0; i6 < i2; i6++) {
            i5 = (i5 * 31) + iArr[i6];
        }
        int i7 = (i3 + i5) * 31;
        Object[] objArr = this.zzd;
        int i8 = this.zzb;
        for (int i9 = 0; i9 < i8; i9++) {
            i4 = (i4 * 31) + objArr[i9].hashCode();
        }
        return i7 + i4;
    }

    public int zza() {
        int zzA;
        int zzB;
        int i2;
        int i3 = this.zze;
        if (-1 != i3) {
            return i3;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < this.zzb; i5++) {
            int i6 = this.zzc[i5];
            int i7 = i6 >>> 3;
            int i8 = i6 & 7;
            if (0 == i8) {
                long longValue = ((Long) this.zzd[i5]).longValue();
                zzA = zzjg.zzA(i7 << 3);
                zzB = zzjg.zzB(longValue);
                i2 = zzA + zzB;
            } else if (1 == i8) {
                ((Long) this.zzd[i5]).longValue();
                i2 = zzjg.zzA(i7 << 3) + 8;
            } else if (2 == i8) {
                int zzA2 = zzjg.zzA(i7 << 3);
                int zzd2 = ((zziy) this.zzd[i5]).zzd();
                i4 += zzA2 + zzjg.zzA(zzd2) + zzd2;
            } else if (3 == i8) {
                int zzz = zzjg.zzz(i7);
                zzA = zzz + zzz;
                zzB = ((zzmj) this.zzd[i5]).zza();
                i2 = zzA + zzB;
            } else if (5 == i8) {
                ((Integer) this.zzd[i5]).intValue();
                i2 = zzjg.zzA(i7 << 3) + 4;
            } else {
                throw new IllegalStateException(zzkj.zza());
            }
            i4 += i2;
        }
        this.zze = i4;
        return i4;
    }

    public int zzb() {
        int i2 = this.zze;
        if (-1 != i2) {
            return i2;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.zzb; i4++) {
            int i5 = this.zzc[i4];
            int zzA = zzjg.zzA(8);
            int zzd2 = ((zziy) this.zzd[i4]).zzd();
            i3 += zzA + zzA + zzjg.zzA(16) + zzjg.zzA(i5 >>> 3) + zzjg.zzA(24) + zzjg.zzA(zzd2) + zzd2;
        }
        this.zze = i3;
        return i3;
    }

    public void zzf() {
        this.zzf = false;
    }

    
    public void zzg(StringBuilder sb, int i2) {
        for (int i3 = 0; i3 < this.zzb; i3++) {
            zzli.zzb(sb, i2, String.valueOf(this.zzc[i3] >>> 3), this.zzd[i3]);
        }
    }

    
    public void zzh(int i2, Object obj) {
        if (this.zzf) {
            int i3 = this.zzb;
            int[] iArr = this.zzc;
            if (i3 == iArr.length) {
                int i4 = i3 + (4 > i3 ? 8 : i3 >> 1);
                this.zzc = Arrays.copyOf(iArr, i4);
                this.zzd = Arrays.copyOf(this.zzd, i4);
            }
            int[] iArr2 = this.zzc;
            int i5 = this.zzb;
            iArr2[i5] = i2;
            this.zzd[i5] = obj;
            this.zzb = i5 + 1;
            return;
        }
        throw new UnsupportedOperationException();
    }

    public void zzi(zzjh zzjh) throws IOException {
        if (0 != zzb) {
            for (int i2 = 0; i2 < this.zzb; i2++) {
                int i3 = this.zzc[i2];
                Object obj = this.zzd[i2];
                int i4 = i3 >>> 3;
                int i5 = i3 & 7;
                if (0 == i5) {
                    zzjh.zzt(i4, ((Long) obj).longValue());
                } else if (1 == i5) {
                    zzjh.zzm(i4, ((Long) obj).longValue());
                } else if (2 == i5) {
                    zzjh.zzd(i4, (zziy) obj);
                } else if (3 == i5) {
                    zzjh.zzE(i4);
                    ((zzmj) obj).zzi(zzjh);
                    zzjh.zzh(i4);
                } else if (5 == i5) {
                    zzjh.zzk(i4, ((Integer) obj).intValue());
                } else {
                    throw new RuntimeException(zzkj.zza());
                }
            }
        }
    }
}
