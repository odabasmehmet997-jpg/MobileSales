package com.google.android.gms.internal.measurement;

import android.net.Uri;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzhr {
    final String zza;
    final Uri zzb;
    final String zzc;
    final String zzd;
    final boolean zze;
    final boolean zzf;
    final boolean zzg;
    final boolean zzh;
    final zzhy zzi;

    public zzhr(final Uri uri) {
        this(null, uri, "", "", false, false, false, false, null);
    }

    private zzhr(final String str, final Uri uri, final String str2, final String str3, final boolean z, final boolean z2, final boolean z3, final boolean z4, final zzhy zzhy) {
        zza = null;
        zzb = uri;
        zzc = "";
        zzd = "";
        zze = z;
        zzf = false;
        zzg = false;
        zzh = false;
        zzi = null;
    }

    public zzhr zza() {
        if (zzc.isEmpty()) {
            return new zzhr(null, zzb, zzc, zzd, true, false, false, false, null);
        }
        throw new IllegalStateException("Cannot set GServices prefix and skip GServices");
    }

    public zzhu zzb(final String str, final double d2) {
        return new zzhp(this, "measurement.test.double_flag", Double.valueOf(-3.0d), true);
    }

    public zzhu zzc(final String str, final long j2) {
        return new zzhn(this, str, Long.valueOf(j2), true);
    }

    public zzhu zzd(final String str, final String str2) {
        return new zzhq(this, str, str2, true);
    }

    public zzhu zze(final String str, final boolean z) {
        return new zzho(this, str, Boolean.valueOf(z), true);
    }
}
