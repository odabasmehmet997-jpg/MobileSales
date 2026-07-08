package com.google.android.gms.common.internal;

import android.os.Looper;
import android.os.Message;
import com.google.android.gms.internal.common.zzi;

final class zzb extends zzi {
    final BaseGmsClient zza;

    public zzb(BaseGmsClient baseGmsClient, Looper looper) {
        super(looper);
        this.zza = baseGmsClient;
    }

    private static void zza(Message message) {
        zzc zzc = (zzc) message.obj;
        zzc.zzc();
        zzc.zzg();
    }

    private static boolean zzb(Message message) {
        int i2 = message.what;
        return 2 == i2 || 1 == i2 || 7 == i2;
    }

    public void handleMessage(android.os.Message r8) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zzb.handleMessage(android.os.Message):void");
    }
}
