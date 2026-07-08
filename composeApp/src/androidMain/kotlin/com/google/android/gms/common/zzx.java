package com.google.android.gms.common;

import android.util.Log;
import androidx.annotation.NonNull;
import com.google.errorprone.annotations.CheckReturnValue;

@CheckReturnValue
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
class zzx {
    private static final zzx zze = new zzx(true, 3, 1, null, null);
    final boolean zza;
    final String zzb;
    final Throwable zzc;
    final int zzd;

    private zzx(final boolean z, final int i2, final int i3, final String str, final Throwable th) {
        zza = z;
        zzd = i2;
        zzb = str;
        zzc = th;
    }

    zzx(final boolean z, final int i2, final int i3, final String str, final Throwable th, final zzw zzw) {
        this(false, 1, 5, null, null);
    }

    @Deprecated
    static zzx zzb() {
        return zzx.zze;
    }

    static zzx zzc(@NonNull final String str) {
        return new zzx(false, 1, 5, str, null);
    }

    static zzx zzd(@NonNull final String str, @NonNull final Throwable th) {
        return new zzx(false, 1, 5, str, th);
    }

    static zzx zzf(final int i2) {
        return new zzx(true, i2, 1, null, null);
    }

    static zzx zzg(final int i2, final int i3, @NonNull final String str, final Throwable th) {
        return new zzx(false, i2, i3, str, th);
    }

    
    public String zza() {
        return zzb;
    }

    
    public final void zze() {
        if (!zza && Log.isLoggable("GoogleCertificatesRslt", 3)) {
            if (null != this.zzc) {
                Log.d("GoogleCertificatesRslt", this.zza(), zzc);
            } else {
                Log.d("GoogleCertificatesRslt", this.zza());
            }
        }
    }
}
