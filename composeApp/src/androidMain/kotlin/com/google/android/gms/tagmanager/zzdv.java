package com.google.android.gms.tagmanager;

import android.net.Uri;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@VisibleForTesting
@ShowFirstParty
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzdv {
    private static zzdv zza;
    private volatile String zzb = null;
    private volatile String zzc = null;
    private volatile String zzd = null;
    private volatile int zze = 1;

    zzdv() {
    }

    @ShowFirstParty
    static zzdv zza() {
        zzdv zzdv;
        synchronized (zzdv.class) {
            try {
                if (zza == null) {
                    zza = new zzdv();
                }
                zzdv = zza;
            } catch (Throwable th) {
                throw th;
            }
        }
        return zzdv;
    }

    private static String zzf(String str) {
        return str.split("&")[0].split("=")[1];
    }

    
    public String zzb() {
        return this.zzc;
    }

    
    public String zzc() {
        return this.zzb;
    }

    
    public synchronized boolean zzd(Uri uri) {
        String decode = URLDecoder.decode(uri.toString(), StandardCharsets.UTF_8);
        try {
            if (decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_auth=\\S+&gtm_preview=\\d+(&gtm_debug=x)?")) {
                zzdc.zzb.zzd("Container preview url: ".concat(decode));
                if (decode.matches(".*?&gtm_debug=x")) {
                    this.zze = 3;
                } else {
                    this.zze = 2;
                }
                this.zzd = uri.getQuery().replace("&gtm_debug=x", "");
                if (this.zze == 2 || this.zze == 3) {
                    this.zzc = "/r?".concat(String.valueOf(this.zzd));
                }
                this.zzb = zzf(this.zzd);
                return true;
            } else if (!decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_preview=")) {
                Log.w("GoogleTagManager", "Invalid preview uri: ".concat(decode));
                return false;
            } else if (zzf(uri.getQuery()).equals(this.zzb)) {
                zzdc.zzb.zzd("Exit preview mode for container: ".concat(String.valueOf(this.zzb)));
                this.zze = 1;
                this.zzc = null;
                return true;
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return false;
    }

    
    public int zze() {
        return this.zze;
    }
}
