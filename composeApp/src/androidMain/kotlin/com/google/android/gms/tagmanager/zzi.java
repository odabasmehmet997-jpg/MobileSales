package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.gtm.zzap;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzi extends zzbp {
    private static final String zza = zza.APP_ID.toString();
    private final Context zzb;

    public zzi(Context context) {
        super(zza);
        this.zzb = context;
    }

    public zzap zza(Map map) {
        return zzfp.zzb(this.zzb.getPackageName());
    }

    public boolean zzb() {
        return true;
    }
}
