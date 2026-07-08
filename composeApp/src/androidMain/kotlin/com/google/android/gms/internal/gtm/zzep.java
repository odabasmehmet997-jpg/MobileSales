package com.google.android.gms.internal.gtm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzep extends BroadcastReceiver {
    @VisibleForTesting
    static final String zza = "com.google.android.gms.internal.gtm.zzep";
    private final zzbu zzb;
    private boolean zzc;
    private boolean zzd;

    zzep(final zzbu zzbu) {
        Preconditions.checkNotNull(zzbu);
        zzb = zzbu;
    }

    public void onReceive(final Context context, final Intent intent) {
        zzb.zzm();
        zzb.zzf();
        final String action = intent.getAction();
        zzb.zzm().zzO("NetworkBroadcastReceiver received action", action);
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            final boolean zze = this.zze();
            if (zzd != zze) {
                zzd = zze;
                final zzbp zzf = zzb.zzf();
                zzf.zzO("Network connectivity status changed", Boolean.valueOf(zze));
                zzf.zzq().zzi(new zzbi(zzf, zze));
            }
        } else if (!"com.google.analytics.RADIO_POWERED".equals(action)) {
            zzb.zzm().zzR("NetworkBroadcastReceiver received unknown action", action);
        } else if (!intent.hasExtra(zzep.zza)) {
            final zzbp zzf2 = zzb.zzf();
            zzf2.zzN("Radio powered up");
            zzf2.zzc();
        }
    }

    public void zza() {
        zzb.zzm();
        zzb.zzf();
        if (!zzc) {
            final Context zza2 = zzb.zza();
            ContextCompat.registerReceiver(zza2, this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"), 4);
            final IntentFilter intentFilter = new IntentFilter("com.google.analytics.RADIO_POWERED");
            intentFilter.addCategory(zza2.getPackageName());
            ContextCompat.registerReceiver(zza2, this, intentFilter, 4);
            zzd = this.zze();
            zzb.zzm().zzO("Registering connectivity change receiver. Network connected", Boolean.valueOf(zzd));
            zzc = true;
        }
    }

    @VisibleForTesting
    public void zzb() {
        final Context zza2 = zzb.zza();
        final Intent intent = new Intent("com.google.analytics.RADIO_POWERED");
        intent.addCategory(zza2.getPackageName());
        intent.putExtra(zzep.zza, true);
        zza2.sendOrderedBroadcast(intent, null);
    }

    public void zzc() {
        if (zzc) {
            zzb.zzm().zzN("Unregistering connectivity change receiver");
            zzc = false;
            zzd = false;
            try {
                zzb.zza().unregisterReceiver(this);
            } catch (final IllegalArgumentException e2) {
                zzb.zzm().zzJ("Failed to unregister the network broadcast receiver", e2);
            }
        }
    }

    public boolean zzd() {
        if (!zzc) {
            zzb.zzm().zzQ("Connectivity unknown. Receiver not registered");
        }
        return zzd;
    }

    
    @VisibleForTesting
    public boolean zze() {
        try {
            final NetworkInfo activeNetworkInfo = ((ConnectivityManager) zzb.zza().getSystemService("connectivity")).getActiveNetworkInfo();
            return null != activeNetworkInfo && activeNetworkInfo.isConnected();
        } catch (final SecurityException unused) {
        }
    }
}
