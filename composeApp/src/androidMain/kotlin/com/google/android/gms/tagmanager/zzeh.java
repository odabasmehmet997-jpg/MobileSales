package com.google.android.gms.tagmanager;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzfm;
import com.google.android.gms.internal.gtm.zzfr;
import com.google.android.gms.internal.gtm.zzrc;
import java.io.File;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzeh implements zzae {
    private final Context zza;
    private final String zzb;
    private final ExecutorService zzc;
    private zzdb zzd;

    public synchronized void release() {
        this.zzc.shutdown();
    }

    public void zzc(zzrc zzrc) {
        this.zzc.execute(new zzeg(this, zzrc));
    }

    
    @VisibleForTesting
    public File zze() {
        File dir = this.zza.getDir("google_tagmanager", 0);
        zzfm.zza();
        int i2 = zzfr.r8clinit;
        return new File(new File(dir, "resource_".concat(String.valueOf(this.zzb))).getPath());
    }


    public void zzf() {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzeh.zzf():void");
    }
}
