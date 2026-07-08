package com.google.android.gms.internal.gtm;

import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzaen {
    private static final zzaen zza = new zzaen(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzaen() {
        this(0, new int[8], new Object[8], true);
    }

    private zzaen(final int i2, final int[] iArr, final Object[] objArr, final boolean z) {
        zze = -1;
        zzb = i2;
        zzc = iArr;
        zzd = objArr;
        zzf = z;
    }

    public static zzaen zzc() {
        return zzaen.zza;
    }

    static zzaen zze(final zzaen zzaen, final zzaen zzaen2) {
        final int i2 = zzaen.zzb + zzaen2.zzb;
        final int[] copyOf = Arrays.copyOf(zzaen.zzc, i2);
        System.arraycopy(zzaen2.zzc, 0, copyOf, zzaen.zzb, zzaen2.zzb);
        final Object[] copyOf2 = Arrays.copyOf(zzaen.zzd, i2);
        System.arraycopy(zzaen2.zzd, 0, copyOf2, zzaen.zzb, zzaen2.zzb);
        return new zzaen(i2, copyOf, copyOf2, true);
    }

    static zzaen zzf() {
        return new zzaen(0, new int[8], new Object[8], true);
    }

    private void zzm(int i2) {
        final int[] iArr = zzc;
        if (i2 > iArr.length) {
            final int i3 = zzb;
            final int i4 = i3 + (i3 / 2);
            if (i4 >= i2) {
                i2 = i4;
            }
            if (8 > i2) {
                i2 = 8;
            }
            zzc = Arrays.copyOf(iArr, i2);
            zzd = Arrays.copyOf(zzd, i2);
        }
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (null == obj || !(obj instanceof zzaen zzaen)) {
            return false;
        }
        final int i2 = zzb;
        if (i2 == zzaen.zzb) {
            final int[] iArr = zzc;
            final int[] iArr2 = zzaen.zzc;
            int i3 = 0;
            while (true) {
                if (i3 >= i2) {
                    final Object[] objArr = zzd;
                    final Object[] objArr2 = zzaen.zzd;
                    final int i4 = zzb;
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
        final int i2 = zzb;
        final int i3 = i2 + 527;
        final int[] iArr = zzc;
        int i4 = 17;
        int i5 = 17;
        for (int i6 = 0; i6 < i2; i6++) {
            i5 = (i5 * 31) + iArr[i6];
        }
        final int i7 = ((i3 * 31) + i5) * 31;
        final Object[] objArr = zzd;
        final int i8 = zzb;
        for (int i9 = 0; i9 < i8; i9++) {
            i4 = (i4 * 31) + objArr[i9].hashCode();
        }
        return i7 + i4;
    }

    public int zza() {
        int zzC;
        int zzD;
        int i2;
        final int i3 = zze;
        if (-1 != i3) {
            return i3;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < zzb; i5++) {
            final int i6 = zzc[i5];
            final int i7 = i6 >>> 3;
            final int i8 = i6 & 7;
            if (0 == i8) {
                final int i9 = i7 << 3;
                final long longValue = ((Long) zzd[i5]).longValue();
                zzC = zzzi.zzC(i9);
                zzD = zzzi.zzD(longValue);
                i2 = zzC + zzD;
            } else if (1 == i8) {
                ((Long) zzd[i5]).longValue();
                i2 = zzzi.zzC(i7 << 3) + 8;
            } else if (2 == i8) {
                final int zzC2 = zzzi.zzC(i7 << 3);
                final int zzd2 = ((zzyx) zzd[i5]).zzd();
                i2 = zzC2 + zzzi.zzC(zzd2) + zzd2;
            } else if (3 == i8) {
                final int zzC3 = zzzi.zzC(i7 << 3);
                zzC = zzC3 + zzC3;
                zzD = ((zzaen) zzd[i5]).zza();
                i2 = zzC + zzD;
            } else if (5 == i8) {
                ((Integer) zzd[i5]).intValue();
                i2 = zzzi.zzC(i7 << 3) + 4;
            } else {
                throw new IllegalStateException(new zzacp("Protocol message tag had invalid wire type."));
            }
            i4 += i2;
        }
        zze = i4;
        return i4;
    }

    public int zzb() {
        final int i2 = zze;
        if (-1 != i2) {
            return i2;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < zzb; i4++) {
            final int zzC = zzzi.zzC(8);
            final int zzC2 = zzzi.zzC(16) + zzzi.zzC(zzc[i4] >>> 3);
            final int zzC3 = zzzi.zzC(24);
            final int zzd2 = ((zzyx) zzd[i4]).zzd();
            i3 += zzC + zzC + zzC2 + zzC3 + zzzi.zzC(zzd2) + zzd2;
        }
        zze = i3;
        return i3;
    }

    
    public zzaen zzd(final zzaen zzaen) {
        if (zzaen.equals(zza)) {
            return this;
        }
        this.zzg();
        final int i2 = zzb + zzaen.zzb;
        this.zzm(i2);
        System.arraycopy(zzaen.zzc, 0, zzc, zzb, zzaen.zzb);
        System.arraycopy(zzaen.zzd, 0, zzd, zzb, zzaen.zzb);
        zzb = i2;
        return this;
    }

    
    public void zzg() {
        if (!zzf) {
            throw new UnsupportedOperationException();
        }
    }

    public void zzh() {
        if (zzf) {
            zzf = false;
        }
    }

    
    public void zzi(final StringBuilder sb, final int i2) {
        for (int i3 = 0; i3 < zzb; i3++) {
            zzadn.zzb(sb, i2, String.valueOf(zzc[i3] >>> 3), zzd[i3]);
        }
    }

    
    public void zzj(final int i2, final Object obj) {
        this.zzg();
        this.zzm(zzb + 1);
        final int[] iArr = zzc;
        final int i3 = zzb;
        iArr[i3] = i2;
        zzd[i3] = obj;
        zzb = i3 + 1;
    }

    
    public void zzk(final zzaez zzaez) throws IOException {
        for (int i2 = 0; i2 < zzb; i2++) {
            zzaez.zzw(zzc[i2] >>> 3, zzd[i2]);
        }
    }

    public void zzl(final zzaez zzaez) throws IOException {
        if (0 != this.zzb) {
            for (int i2 = 0; i2 < zzb; i2++) {
                final int i3 = zzc[i2];
                final Object obj = zzd[i2];
                final int i4 = i3 & 7;
                final int i5 = i3 >>> 3;
                if (0 == i4) {
                    zzaez.zzt(i5, ((Long) obj).longValue());
                } else if (1 == i4) {
                    zzaez.zzm(i5, ((Long) obj).longValue());
                } else if (2 == i4) {
                    zzaez.zzd(i5, (zzyx) obj);
                } else if (3 == i4) {
                    zzaez.zzF(i5);
                    ((zzaen) obj).zzl(zzaez);
                    zzaez.zzh(i5);
                } else if (5 == i4) {
                    zzaez.zzk(i5, ((Integer) obj).intValue());
                } else {
                    throw new RuntimeException(new zzacp("Protocol message tag had invalid wire type."));
                }
            }
        }
    }
}
