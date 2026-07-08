package com.google.android.gms.tagmanager;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.google.android.gms.internal.gtm.zzap;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzea extends zzbp {
    private static final String zza = zza.RESOLUTION.toString();
    private final Context zzb;

    public zzea(Context context) {
        super(zza);
        this.zzb = context;
    }

    public zzap zza(Map map) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.zzb.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i2 = displayMetrics.widthPixels;
        int i3 = displayMetrics.heightPixels;
        return zzfp.zzb(i2 + "x" + i3);
    }

    public boolean zzb() {
        return true;
    }
}
