package com.google.android.gms.internal.gtm;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.google.firebase.messaging.Constants;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzfg extends zzbr {
    String zza;
    String zzb;
    boolean zzc;
    int zzd;
    private boolean zze;
    private boolean zzf;

    public zzfg(zzbu zzbu) {
        super(zzbu);
    }

    public String zza() {
        zzV();
        return this.zzb;
    }

    public String zzb() {
        zzV();
        return this.zza;
    }

    public boolean zzc() {
        zzV();
        return this.zzf;
    }

    
    public void zzd() {
        ApplicationInfo applicationInfo;
        int i2;
        int i3;
        Context zzo = zzo();
        try {
            applicationInfo = zzo.getPackageManager().getApplicationInfo(zzo.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e2) {
            zzR("PackageManager doesn't know about the app package", e2);
            applicationInfo = null;
        }
        if (null == applicationInfo) {
            zzQ("Couldn't get ApplicationInfo to load global config");
            return;
        }
        Bundle bundle = applicationInfo.metaData;
        if (null != bundle && 0 < (i2 = bundle.getInt("com.google.android.gms.analytics.globalConfigResource"))) {
            zzbu zzt = zzt();
            zzej zzej = (zzej) new zzcr(zzt, new zzei(zzt)).zza(i2);
            if (null != zzej) {
                zzN("Loading global XML config values");
                String str = zzej.zza;
                if (null != str) {
                    this.zzb = str;
                    zzF("XML config - app name", str);
                }
                String str2 = zzej.zzb;
                if (null != str2) {
                    this.zza = str2;
                    zzF("XML config - app version", str2);
                }
                String str3 = zzej.zzc;
                boolean z = false;
                if (null != str3) {
                    String lowerCase = str3.toLowerCase(Locale.US);
                    if ("verbose".equals(lowerCase)) {
                        i3 = 0;
                    } else if ("info".equals(lowerCase)) {
                        i3 = 1;
                    } else if ("warning".equals(lowerCase)) {
                        i3 = 2;
                    } else {
                        i3 = Constants.IPC_BUNDLE_KEY_SEND_ERROR.equals(lowerCase) ? 3 : -1;
                    }
                    if (0 <= i3) {
                        zzO("XML config - log level", Integer.valueOf(i3));
                    }
                }
                int i4 = zzej.zzd;
                if (0 <= i4) {
                    this.zzd = i4;
                    this.zzc = true;
                    zzF("XML config - dispatch period (sec)", Integer.valueOf(i4));
                }
                int i5 = zzej.zze;
                if (-1 != i5) {
                    if (1 == i5) {
                        z = true;
                    }
                    this.zzf = z;
                    this.zze = true;
                    zzF("XML config - dry run", Boolean.valueOf(z));
                }
            }
        }
    }

    public boolean zze() {
        zzV();
        return this.zze;
    }

    public boolean zzf() {
        zzV();
        return false;
    }
}
