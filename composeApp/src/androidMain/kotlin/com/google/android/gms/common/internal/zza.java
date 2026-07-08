package com.google.android.gms.common.internal;

import android.os.Bundle;
import androidx.annotation.BinderThread;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
abstract class zza extends zzc {
    public final int zza;
    @Nullable
    public final Bundle zzb;
    final BaseGmsClient zzc;

    protected zza(BaseGmsClient baseGmsClient, @Nullable int i2, Bundle bundle) {
        super(baseGmsClient, Boolean.TRUE);
        this.zzc = baseGmsClient;
        this.zza = i2;
        this.zzb = bundle;
    }

    public final void zza(java.lang.Object r3) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zza.zza(java.lang.Object):void");
    }

    public abstract void zzb(ConnectionResult connectionResult);

    public final void zzc() {
    }

    public abstract boolean zzd();
}
