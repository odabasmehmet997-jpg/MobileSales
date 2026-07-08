package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.IntentFilter;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.ShowFirstParty;

@VisibleForTesting
@ShowFirstParty
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzfa extends zzet {
    
    public static final Object zza = new Object();
    private static zzfa zzb;
    
    public Context zzc;
    
    public zzby zzd;
    private boolean zze = true;
    private boolean zzf = false;
    
    public boolean zzg = true;
    private zzew zzh;
    private zzdf zzi;
    private boolean zzj = false;
    private volatile zzbx zzk;
    private final zzeu zzl = new zzeu(this);

    private zzfa() {
    }

    public static zzfa zzg() {
        if (zzb == null) {
            zzb = new zzfa();
        }
        return zzb;
    }

    
    public boolean zzm() {
        return this.zzj || !this.zzg;
    }

    public synchronized void zza() {
        if (!this.zzf) {
            zzdc.zzb.zzd("Dispatch call queued. Dispatch will run once initialization is complete.");
            this.zze = true;
            return;
        }
        this.zzk.zze(new zzev(this));
    }

    public synchronized void zzb() {
        if (!zzm()) {
            this.zzh.zzb();
        }
    }

    public synchronized void zzc(boolean z) {
        zzi(this.zzj, z);
    }

    
    public synchronized zzby zzf() {
        try {
            if (this.zzd == null) {
                if (this.zzc != null) {
                    this.zzd = new zzdr(this.zzl, this.zzc);
                } else {
                    throw new IllegalStateException("Cant get a store unless we have a context");
                }
            }
            if (this.zzh == null) {
                zzey zzey = new zzey(this, null);
                this.zzh = zzey;
                zzey.zzc(1800000);
            }
            this.zzf = true;
            if (this.zze) {
                zza();
                this.zze = false;
            }
            if (this.zzi == null) {
                zzdf zzdf = new zzdf(this);
                this.zzi = zzdf;
                Context context = this.zzc;
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                context.registerReceiver(zzdf, intentFilter);
                IntentFilter intentFilter2 = new IntentFilter();
                intentFilter2.addAction("com.google.analytics.RADIO_POWERED");
                intentFilter2.addCategory(context.getPackageName());
                context.registerReceiver(zzdf, intentFilter2);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return this.zzd;
    }

    
    @VisibleForTesting
    public synchronized void zzi(boolean z, boolean z2) {
        boolean zzm = zzm();
        this.zzj = z;
        this.zzg = z2;
        if (zzm() != zzm) {
            if (zzm()) {
                this.zzh.zza();
                zzdc.zzb.zzd("PowerSaveMode initiated.");
                return;
            }
            this.zzh.zzc(1800000);
            zzdc.zzb.zzd("PowerSaveMode terminated.");
        }
    }

    public synchronized void zzl(android.content.Context r2, com.google.android.gms.tagmanager.zzbx r3) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzfa.zzl(android.content.Context, com.google.android.gms.tagmanager.zzbx):void");
    }
}
