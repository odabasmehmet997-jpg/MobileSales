package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.common.internal.ShowFirstParty;

@ShowFirstParty
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
public final class zzfm {
    private GoogleAnalytics zza;
    private final Context zzb;
    private Tracker zzc;

    public zzfm(Context context) {
        this.zzb = context;
    }

    private synchronized void zzb(String str) {
        if (this.zza == null) {
            GoogleAnalytics instance = GoogleAnalytics.getInstance(this.zzb);
            this.zza = instance;
            instance.setLogger(new zzfl());
            this.zzc = this.zza.newTracker("_GTM_DEFAULT_TRACKER_");
        }
    }

    public Tracker zza(String str) {
        zzb("_GTM_DEFAULT_TRACKER_");
        return this.zzc;
    }
}
