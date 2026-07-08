package com.google.android.gms.measurement.internal;

import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.measurement.zzbr;
import com.google.android.gms.internal.measurement.zzns;
import com.google.firebase.messaging.Constants;

/**
 * @param zza synthetic
 * @param zzb synthetic
 * @param zzc synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
record zzez(zzfa zzc, zzbr zza, ServiceConnection zzb) implements Runnable {

    public void run() {
        Bundle bundle;
        String str;
        zzfa zzfa = this.zzc;
        zzfb zzfb = zzfa.zza;
        String zza2 = zzfa.zzb;
        zzbr zzbr = this.zza;
        ServiceConnection serviceConnection = this.zzb;
        zzfb.zza.zzaz().zzg();
        Bundle bundle2 = new Bundle();
        bundle2.putString("package_name", zza2);
        try {
            bundle = zzbr.zzd(bundle2);
            if (null == bundle) {
                zzfb.zza.zzay().zzd().zza("Install Referrer Service returned a null response");
                bundle = null;
            }
        } catch (Exception e2) {
            zzfb.zza.zzay().zzd().zzb("Exception occurred while retrieving the Install Referrer", e2.getMessage());
        }
        zzfb.zza.zzaz().zzg();
        zzft.zzO();
        if (null != bundle) {
            long j2 = bundle.getLong("install_begin_timestamp_seconds", 0) * 1000;
            if (0 == j2) {
                zzfb.zza.zzay().zzk().zza("Service response is missing Install Referrer install timestamp");
            } else {
                String string = bundle.getString("install_referrer");
                if (null == string || string.isEmpty()) {
                    zzfb.zza.zzay().zzd().zza("No referrer defined in Install Referrer response");
                } else {
                    zzfb.zza.zzay().zzj().zzb("InstallReferrer API result", string);
                    zzky zzv = zzfb.zza.zzv();
                    if (0 != string.length()) {
                        str = "?" + string;
                    } else {
                        str = "?";
                    }
                    Uri parse = Uri.parse(str);
                    zzns.zzc();
                    Bundle zzs = zzv.zzs(parse, zzfb.zza.zzf().zzs(null, zzdw.zzau));
                    if (null == zzs) {
                        zzfb.zza.zzay().zzd().zza("No campaign params defined in Install Referrer result");
                    } else {
                        String string2 = zzs.getString("medium");
                        if (null != string2 && !"(not set)".equalsIgnoreCase(string2) && !"organic".equalsIgnoreCase(string2)) {
                            long j3 = bundle.getLong("referrer_click_timestamp_seconds", 0) * 1000;
                            if (0 == j3) {
                                zzfb.zza.zzay().zzd().zza("Install Referrer is missing click timestamp for ad campaign");
                            } else {
                                zzs.putLong("click_timestamp", j3);
                            }
                        }
                        if (j2 == zzfb.zza.zzm().zzd.zza()) {
                            zzfb.zza.zzay().zzj().zza("Logging Install Referrer campaign from module while it may have already been logged.");
                        }
                        if (zzfb.zza.zzJ()) {
                            zzfb.zza.zzm().zzd.zzb(j2);
                            zzfb.zza.zzay().zzj().zzb("Logging Install Referrer campaign from gmscore with ", "referrer API v2");
                            zzs.putString("_cis", "referrer API v2");
                            zzfb.zza.zzq().zzF("auto", Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, zzs, zza2);
                        }
                    }
                }
            }
        }
        ConnectionTracker.getInstance().unbindService(zzfb.zza.zzau(), serviceConnection);
    }
}
