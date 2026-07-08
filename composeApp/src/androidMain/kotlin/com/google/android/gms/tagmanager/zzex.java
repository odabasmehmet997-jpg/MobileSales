package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Message;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
record zzex(zzey zza) implements Handler.Callback {

    public boolean handleMessage(Message message) {
        if (message.what == 1 && zzfa.zza.equals(message.obj)) {
            this.zza.zza.zza();
            zzey zzey = this.zza;
            if (!zzey.zza.zzm()) {
                zzey.zzc(1800000);
            }
        }
        return true;
    }
}
