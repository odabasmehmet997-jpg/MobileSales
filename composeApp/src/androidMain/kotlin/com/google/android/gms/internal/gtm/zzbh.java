package com.google.android.gms.internal.gtm;

import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Locale;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzbh extends zzbr {
    public static boolean zza;
    private AdvertisingIdClient.Info zzb;
    private final zzfb zzc;
    private String zzd;
    private final boolean zze;
    private final Object zzf = new Object();

    zzbh(final zzbu zzbu) {
        super(zzbu);
        zzc = new zzfb(zzbu.zzr());
    }

    private synchronized com.google.android.gms.ads.identifier.AdvertisingIdClient.Info zzc() {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzbh.zzc():com.google.android.gms.ads.identifier.AdvertisingIdClientInfo");
    }

    private static String zze(final String str) {
        final MessageDigest zze2 = zzff.zze("MD5");
        if (null == zze2) {
            return null;
        }
        return String.format(Locale.US, "%032X", new BigInteger(1, zze2.digest(str.getBytes(StandardCharsets.UTF_8))));
    }

    private boolean zzf(final String str) {
        try {
            final String zze2 = zzbh.zze(str);
            this.zzN("Storing hashed adid.");
            final FileOutputStream openFileOutput = this.zzo().openFileOutput("gaClientIdData", 0);
            openFileOutput.write(zze2.getBytes(StandardCharsets.UTF_8));
            openFileOutput.close();
            zzd = zze2;
            return true;
        } catch (final IOException e2) {
            this.zzJ("Error creating hash file", e2);
            return false;
        }
    }

    public String zza() {
        this.zzV();
        final AdvertisingIdClient.Info zzc2 = this.zzc();
        final String id = null != zzc2 ? zzc2.getId() : null;
        if (TextUtils.isEmpty(id)) {
            return null;
        }
        return id;
    }

    public boolean zzb() {
        this.zzV();
        final AdvertisingIdClient.Info zzc2 = this.zzc();
        return null != zzc2 && !zzc2.isLimitAdTrackingEnabled();
    }

    
    public void zzd() {
    }
}
