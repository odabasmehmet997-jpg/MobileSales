package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.google.android.gms.internal.gtm.zzap;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzo extends zzfn {
    static final String zza;
    private static final String zzb;
    private static final String zzc = zzb.URL.toString();
    private static final String zzd = zzb.ADDITIONAL_PARAMS.toString();
    private static final String zze = zzb.UNREPEATABLE.toString();
    private static final Set zzf = new HashSet();
    private final zzn zzg;
    private final Context zzh;

    static {
        String zza2 = zza.ARBITRARY_PIXEL.toString();
        zzb = zza2;
        zza = "gtm_" + zza2 + "_unrepeatable";
    }

    
    public zzo(Context context) {
        super(zzb, zzc);
        zzm zzm = new zzm(context);
        this.zzg = zzm;
        this.zzh = context;
    }

    private synchronized boolean zzd(String str) {
        Set set = zzf;
        if (set.contains(str)) {
            return true;
        }
        if (!this.zzh.getSharedPreferences(zza, 0).contains(str)) {
            return false;
        }
        set.add(str);
        return true;
    }

    public void zzc(Map map) {
        String str = zze;
        String zzm = map.get(str) != null ? zzfp.zzm(zzfp.zzk((zzap) map.get(str))) : null;
        if (zzm == null || !zzd(zzm)) {
            Uri.Builder buildUpon = Uri.parse(zzfp.zzm(zzfp.zzk((zzap) map.get(zzc)))).buildUpon();
            zzap zzap = (zzap) map.get(zzd);
            if (zzap != null) {
                Object zzk = zzfp.zzk(zzap);
                if (!(zzk instanceof List)) {
                    Log.e("GoogleTagManager", "ArbitraryPixel: additional params not a list: not sending partial hit: ".concat(buildUpon.build().toString()));
                    return;
                }
                for (Object next : (List) zzk) {
                    if (!(next instanceof Map)) {
                        Log.e("GoogleTagManager", "ArbitraryPixel: additional params contains non-map: not sending partial hit: ".concat(buildUpon.build().toString()));
                        return;
                    }
                    for (Map.Entry entry : ((Map) next).entrySet()) {
                        buildUpon.appendQueryParameter(entry.getKey().toString(), entry.getValue().toString());
                    }
                }
            }
            String uri = buildUpon.build().toString();
            zzbc.zzb(((zzm) this.zzg).zza()).zza(uri);
            zzdc.zzb.zzd("ArbitraryPixel: url = ".concat(uri));
            if (zzm != null) {
                synchronized (zzo.class) {
                    zzf.add(zzm);
                    zzfb.zza(this.zzh, zza, zzm, "true");
                }
            }
        }
    }
}
