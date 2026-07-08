package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzek {
    @NonNull
    public final String zza;
    @NonNull
    public final String zzb;
    public final long zzc;
    @NonNull
    public final Bundle zzd;

    public zzek(@NonNull final String str, @NonNull final String str2, @Nullable final Bundle bundle, final long j2) {
        zza = str;
        zzb = str2;
        zzd = bundle;
        zzc = j2;
    }

    public static zzek zzb(final zzau zzau) {
        return new zzek(zzau.zza, zzau.zzc, zzau.zzb.zzc(), zzau.zzd);
    }

    public String toString() {
        final String str = zzb;
        final String str2 = zza;
        final String obj = zzd.toString();
        String sb = "origin=" +
                str +
                ",name=" +
                str2 +
                ",params=" +
                obj;
        return sb;
    }

    public zzau zza() {
        return new zzau(zza, new zzas(new Bundle(zzd)), zzb, zzc);
    }
}
