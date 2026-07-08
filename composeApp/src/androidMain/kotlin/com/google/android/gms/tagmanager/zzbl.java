package com.google.android.gms.tagmanager;

import android.util.Base64;
import android.util.Log;
import com.google.android.gms.internal.gtm.zzap;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzbl extends zzbp {
    private static final String zza = zza.ENCODE.toString();
    private static final String zzb = zzb.ARG0.toString();
    private static final String zzc = zzb.NO_PADDING.toString();
    private static final String zzd = zzb.INPUT_FORMAT.toString();
    private static final String zze = zzb.OUTPUT_FORMAT.toString();

    public zzbl() {
        super(zza, zzb);
    }

    public zzap zza(Map map) {
        String str;
        String str2;
        byte[] bArr;
        String str3;
        zzap zzap = (zzap) map.get(zzb);
        if (zzap == null || zzap == zzfp.zza()) {
            return zzfp.zza();
        }
        String zzm = zzfp.zzm(zzfp.zzk(zzap));
        zzap zzap2 = (zzap) map.get(zzd);
        if (zzap2 == null) {
            str = "text";
        } else {
            str = zzfp.zzm(zzfp.zzk(zzap2));
        }
        zzap zzap3 = (zzap) map.get(zze);
        if (zzap3 == null) {
            str2 = "base16";
        } else {
            str2 = zzfp.zzm(zzfp.zzk(zzap3));
        }
        zzap zzap4 = (zzap) map.get(zzc);
        int i2 = 2;
        if (zzap4 != null && zzfp.zzf(zzfp.zzk(zzap4)).booleanValue()) {
            i2 = 3;
        }
        try {
            if ("text".equals(str)) {
                bArr = zzm.getBytes();
            } else if ("base16".equals(str)) {
                bArr = zzp.zzb(zzm);
            } else if ("base64".equals(str)) {
                bArr = Base64.decode(zzm, i2);
            } else if ("base64url".equals(str)) {
                bArr = Base64.decode(zzm, i2 | 8);
            } else {
                Log.e("GoogleTagManager", "Encode: unknown input format: " + str);
                return zzfp.zza();
            }
            if ("base16".equals(str2)) {
                str3 = zzp.zza(bArr);
            } else if ("base64".equals(str2)) {
                str3 = Base64.encodeToString(bArr, i2);
            } else if ("base64url".equals(str2)) {
                str3 = Base64.encodeToString(bArr, i2 | 8);
            } else {
                Log.e("GoogleTagManager", "Encode: unknown output format: ".concat(String.valueOf(str2)));
                return zzfp.zza();
            }
            return zzfp.zzb(str3);
        } catch (IllegalArgumentException unused) {
            Log.e("GoogleTagManager", "Encode: invalid input:");
            return zzfp.zza();
        }
    }

    public boolean zzb() {
        return true;
    }
}
