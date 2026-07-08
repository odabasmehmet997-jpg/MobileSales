package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.net.Uri;
import androidx.annotation.Nullable;
import com.google.firebase.analytics.FirebaseAnalytics;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public final class zzo {
    private static final Uri zza = new Uri.Builder().scheme(FirebaseAnalytics.Param.CONTENT).authority("com.google.android.gms.chimera").build();
    @Nullable
    private final String zzb;
    @Nullable
    private final String zzc;
    @Nullable
    private final ComponentName zzd = null;
    private final int zze = 4225;
    private final boolean zzf;

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof final zzo zzo)) {
            return false;
        }
        return Objects.equal(this.zzb, zzo.zzb) && Objects.equal(this.zzc, zzo.zzc) && Objects.equal(this.zzd, zzo.zzd) && this.zzf == zzo.zzf;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzb, this.zzc, this.zzd, 4225, Boolean.valueOf(this.zzf));
    }

    public String toString() {
        String str = this.zzb;
        if (null != str) {
            return str;
        }
        Preconditions.checkNotNull(this.zzd);
        return this.zzd.flattenToString();
    }

    @Nullable
    public ComponentName zza() {
        return this.zzd;
    }

    public android.content.Intent zzb(android.content.Context r6) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zzo.zzb(android.content.Context):android.content.Intent");
    }

    @Nullable
    public String zzc() {
        return this.zzc;
    }

    public zzo(String str, String str2, int i2, boolean z) {
        Preconditions.checkNotEmpty(str);
        this.zzb = str;
        Preconditions.checkNotEmpty(str2);
        this.zzc = str2;
        this.zzf = z;
    }
}
