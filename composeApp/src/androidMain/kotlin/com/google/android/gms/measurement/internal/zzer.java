package com.google.android.gms.measurement.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.annotation.MainThread;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
final class zzer extends BroadcastReceiver {
    
    public final zzkr zzb;
    private boolean zzc;
    private boolean zzd;

    zzer(zzkr zzkr) {
        Preconditions.checkNotNull(zzkr);
        this.zzb = zzkr;
    }

    @MainThread
    public void onReceive(Context context, Intent intent) {
        this.zzb.zzB();
        String action = intent.getAction();
        this.zzb.zzay().zzj().zzb("NetworkBroadcastReceiver received action", action);
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            boolean zza = this.zzb.zzl().zza();
            if (this.zzd != zza) {
                this.zzd = zza;
                this.zzb.zzaz().zzp(new zzeq(this, zza));
                return;
            }
            return;
        }
        this.zzb.zzay().zzk().zzb("NetworkBroadcastReceiver received unknown action", action);
    }

    @WorkerThread
    public void zzb() {
        this.zzb.zzB();
        this.zzb.zzaz().zzg();
        if (!this.zzc) {
            this.zzb.zzau().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            this.zzd = this.zzb.zzl().zza();
            this.zzb.zzay().zzj().zzb("Registering connectivity change receiver. Network connected", Boolean.valueOf(this.zzd));
            this.zzc = true;
        }
    }

    @WorkerThread
    public void zzc() {
        this.zzb.zzB();
        this.zzb.zzaz().zzg();
        this.zzb.zzaz().zzg();
        if (this.zzc) {
            this.zzb.zzay().zzj().zza("Unregistering connectivity change receiver");
            this.zzc = false;
            this.zzd = false;
            try {
                this.zzb.zzau().unregisterReceiver(this);
            } catch (IllegalArgumentException e2) {
                this.zzb.zzay().zzd().zzb("Failed to unregister the network broadcast receiver", e2);
            }
        }
    }
}
