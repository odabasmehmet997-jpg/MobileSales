package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzja extends zzjc {
    private final byte[] zzb;
    private int zzc;
    private int zzd;
    private int zze = Integer.MAX_VALUE;

    zzja(final byte[] bArr, final int i2, final int i3, final boolean z, final zziz zziz) {
        super(null);
        zzb = bArr;
        zzc = 0;
    }

    public int zza(final int i2) throws zzkj {
        final int i3 = zze;
        zze = 0;
        final int i4 = zzc + zzd;
        zzc = i4;
        if (0 < i4) {
            zzd = i4;
            zzc = 0;
        } else {
            zzd = 0;
        }
        return i3;
    }
}
