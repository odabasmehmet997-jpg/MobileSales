package com.google.android.gms.tagmanager;

import android.util.Log;
import com.google.android.gms.internal.gtm.zzap;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzbu extends zzbp {
    private static final String zza = zza.HASH.toString();
    private static final String zzb = zzb.ARG0.toString();
    private static final String zzc = zzb.ALGORITHM.toString();
    private static final String zzd = zzb.INPUT_FORMAT.toString();

    public zzbu() {
        super(zza, zzb);
    }

    public zzap zza(Map map) {
        String str;
        String str2;
        byte[] bArr;
        zzap zzap = (zzap) map.get(zzb);
        if (zzap == null || zzap == zzfp.zza()) {
            return zzfp.zza();
        }
        String zzm = zzfp.zzm(zzfp.zzk(zzap));
        zzap zzap2 = (zzap) map.get(zzc);
        if (zzap2 == null) {
            str = "MD5";
        } else {
            str = zzfp.zzm(zzfp.zzk(zzap2));
        }
        zzap zzap3 = (zzap) map.get(zzd);
        if (zzap3 == null) {
            str2 = "text";
        } else {
            str2 = zzfp.zzm(zzfp.zzk(zzap3));
        }
        if ("text".equals(str2)) {
            bArr = zzm.getBytes();
        } else if ("base16".equals(str2)) {
            bArr = zzp.zzb(zzm);
        } else {
            Log.e("GoogleTagManager", "Hash: unknown input format: ".concat(String.valueOf(str2)));
            return zzfp.zza();
        }
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            instance.update(bArr);
            return zzfp.zzb(zzp.zza(instance.digest()));
        } catch (NoSuchAlgorithmException unused) {
            Log.e("GoogleTagManager", "Hash: unknown algorithm: ".concat(str));
            return zzfp.zza();
        }
    }

    public boolean zzb() {
        return true;
    }
}
