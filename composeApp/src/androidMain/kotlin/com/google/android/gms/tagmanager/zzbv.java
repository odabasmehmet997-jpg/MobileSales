package com.google.android.gms.tagmanager;

import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzbv {
    private final long zza;
    private final long zzb;
    private String zzc;

    zzbv(long j2, long j3, long j4) {
        this.zza = j2;
        this.zzb = j4;
    }

    
    public long zza() {
        return this.zzb;
    }

    
    public long zzb() {
        return this.zza;
    }

    
    public String zzc() {
        return this.zzc;
    }

    
    public void zzd(String str) {
        if (str != null && !TextUtils.isEmpty(str.trim())) {
            this.zzc = str;
        }
    }
}
