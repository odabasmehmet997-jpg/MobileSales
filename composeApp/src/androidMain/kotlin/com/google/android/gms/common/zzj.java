package com.google.android.gms.common;

import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
abstract class zzj extends zzz {
    private final int zza;

    protected zzj(final byte[] bArr) {
        Preconditions.checkArgument(25 == bArr.length);
        zza = Arrays.hashCode(bArr);
    }

    protected static byte[] zze(final String str) {
        return str.getBytes(StandardCharsets.ISO_8859_1);
    }

    public final boolean equals(@Nullable final Object obj) {
        final IObjectWrapper zzd;
        if (null != obj && (obj instanceof zzaa zzaa)) {
            try {
                if (zzaa.zzc() != zza || null == (zzd = zzaa.zzd())) {
                    return false;
                }
                return Arrays.equals(this.zzf(), ObjectWrapper.unwrap(zzd));
            } catch (final RemoteException e2) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e2);
            }
        }
        return false;
    }

    public final int hashCode() {
        return zza;
    }

    public final int zzc() {
        return zza;
    }

    public final IObjectWrapper zzd() {
        return ObjectWrapper.wrap(this.zzf());
    }

    
    public abstract byte[] zzf();
}
