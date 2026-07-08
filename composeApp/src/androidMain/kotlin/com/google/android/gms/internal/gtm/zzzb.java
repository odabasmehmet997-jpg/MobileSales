package com.google.android.gms.internal.gtm;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public abstract class zzzb {
    private static final int zze = 100;
    int zza;
    final int zzb = zzzb.zze;
    zzzc zzc;

    private zzzb() {
    }

    zzzb(final zzza zzza) {
    }

    public static int zzF(final int i2) {
        return (i2 >>> 1) ^ (-(i2 & 1));
    }

    public static long zzG(final long j2) {
        return (j2 >>> 1) ^ (-(1 & j2));
    }

    static zzzb zzH(final byte[] bArr, final int i2, final int i3, final boolean z) {
        final zzyy zzyy = new zzyy(bArr, 0, i3, z, null);
        try {
            zzyy.zze(i3);
            return zzyy;
        } catch (final zzacq e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public abstract void zzA(int i2);

    public abstract boolean zzC() throws IOException;

    public abstract boolean zzD() throws IOException;

    public abstract boolean zzE(int i2) throws IOException;

    public final void zzI() throws IOException {
        int zzm;
        do {
            zzm = this.zzm();
            if (0 != zzm) {
                final int i2 = zza;
                if (i2 < zzb) {
                    zza = i2 + 1;
                    zza--;
                } else {
                    throw new zzacq("Protocol message had too many levels of nesting.  May be malicious.  Use setRecursionLimit() to increase the recursion depth limit.");
                }
            } else {
                return;
            }
        } while (this.zzE(zzm));
    }

    public abstract double zzb() throws IOException;

    public abstract float zzc() throws IOException;

    public abstract int zzd();

    public abstract int zze(int i2) throws zzacq;

    public abstract int zzf() throws IOException;

    public abstract int zzg() throws IOException;

    public abstract int zzh() throws IOException;

    public abstract int zzk() throws IOException;

    public abstract int zzl() throws IOException;

    public abstract int zzm() throws IOException;

    public abstract int zzn() throws IOException;

    public abstract long zzo() throws IOException;

    public abstract long zzp() throws IOException;

    public abstract long zzt() throws IOException;

    public abstract long zzu() throws IOException;

    public abstract long zzv() throws IOException;

    public abstract zzyx zzw() throws IOException;

    public abstract String zzx() throws IOException;

    public abstract String zzy() throws IOException;

    public abstract void zzz(int i2) throws zzacq;
}
