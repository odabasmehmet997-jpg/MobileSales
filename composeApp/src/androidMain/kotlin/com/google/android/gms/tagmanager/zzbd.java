package com.google.android.gms.tagmanager;

import android.content.Context;
import android.provider.Settings;
import com.google.android.gms.internal.gtm.zzap;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzbd extends zzbp {
    private static final String zza = zza.DEVICE_ID.toString();
    private final Context zzb;

    public zzbd(Context context) {
        super(zza);
        this.zzb = context;
    }

    public zzap zza(Map map) {
        String string = Settings.Secure.getString(this.zzb.getContentResolver(), "android_id");
        return string == null ? zzfp.zza() : zzfp.zzb(string);
    }

    public boolean zzb() {
        return true;
    }
}
