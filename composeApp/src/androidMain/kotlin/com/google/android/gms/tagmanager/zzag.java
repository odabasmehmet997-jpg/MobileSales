package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.gtm.zzah;
import com.google.android.gms.internal.gtm.zzrb;
import com.google.android.gms.internal.gtm.zzrc;
import com.google.android.gms.internal.gtm.zzz;

@ShowFirstParty
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
public final class zzag extends BasePendingResult {
    
    public final Clock zza;
    private final zzac zzb;
    private final Looper zzc;
    
    public final zzdx zzd;
    private final Context zzf;
    private final TagManager zzg;
    private final String zzh;
    
    public final zzah zzi;
    private zzae zzj;
    
    public volatile zzx zzk;
    
    public volatile boolean zzl;
    
    public zzah zzm;
    
    public long zzn;
    private String zzo;
    private zzad zzp;
    private zzz zzq;

    
    public synchronized void zzr(long j2) {
        zzad zzad = this.zzp;
        if (zzad == null) {
            Log.w("GoogleTagManager", "Refresh requested, but no network load scheduler.");
        } else {
            zzad.zza(j2, this.zzm.zzh());
        }
    }

    
    public synchronized void zzt(zzah zzah) {
        if (this.zzj != null) {
            zzrb zze = zzrc.zze();
            zze.zzc(0);
            zze.zza(zzz.zzk());
            zze.zzc(this.zzn);
            zze.zza(zzz.zzk());
            zze.zzb(zzah);
            this.zzj.zzc((zzrc) zze.zzD());
        }
    }

    public synchronized void zzu(com.google.android.gms.internal.gtm.zzah r10, long r11, boolean r13) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzag.zzu(com.google.android.gms.internal.gtm.zzah, long, boolean):void");
    }

    
    public boolean zzv() {
        zzdv zza2 = zzdv.zza();
        return (zza2.zze() == 2 || zza2.zze() == 3) && this.zzh.equals(zza2.zzc());
    }

    
    /* renamed from: zzd */
    public ContainerHolder createFailedResult(Status status) {
        if (this.zzk != null) {
            return this.zzk;
        }
        if (status == Status.RESULT_TIMEOUT) {
            Log.e("GoogleTagManager", "timer expired: setting result to failure");
        }
        return new zzx(status);
    }

    
    public synchronized String zzh() {
        return this.zzo;
    }

    
    @VisibleForTesting
    public synchronized void zzo(String str) {
        this.zzo = str;
        zzad zzad = this.zzp;
        if (zzad != null) {
            zzad.zzb(str);
        }
    }
}
