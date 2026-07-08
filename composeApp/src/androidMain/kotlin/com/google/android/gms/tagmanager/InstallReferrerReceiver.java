package com.google.android.gms.tagmanager;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.analytics.CampaignTrackingReceiver;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
public final class InstallReferrerReceiver extends CampaignTrackingReceiver {
    
    public void zza(@NonNull Context context, @NonNull String str) {
        zzcs.zzd(str);
        zzfb.zza(context, "gtm_install_referrer", "referrer", str);
        zzcs.zzc(context, str);
    }
}
