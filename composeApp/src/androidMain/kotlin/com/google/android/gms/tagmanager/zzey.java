package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Message;
import com.google.android.gms.internal.gtm.zzfy;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzey implements zzew {
    final zzfa zza;
    private final Handler zzb;

    zzey(zzfa zzfa, zzez zzez) {
        this.zza = zzfa;
        this.zzb = new zzfy(zzfa.zzc.getMainLooper(), new zzex(this));
    }

    private Message zzd() {
        return this.zzb.obtainMessage(1, zzfa.zza);
    }

    public void zza() {
        this.zzb.removeMessages(1, zzfa.zza);
    }

    public void zzb() {
        this.zzb.removeMessages(1, zzfa.zza);
        this.zzb.sendMessage(zzd());
    }

    public void zzc(long j2) {
        this.zzb.removeMessages(1, zzfa.zza);
        this.zzb.sendMessageDelayed(zzd(), 1800000);
    }
}
