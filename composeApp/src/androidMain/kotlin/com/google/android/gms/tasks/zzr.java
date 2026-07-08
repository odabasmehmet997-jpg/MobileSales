package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.ArrayDeque;
import java.util.Queue;

/* compiled from: com.google.android.gms:play-services-tasks@@18.1.0 */
final class zzr {
    private final Object zza = new Object();
    private Queue zzb;
    private boolean zzc;

    zzr() {
    }

    public void zza(@NonNull zzq zzq) {
        synchronized (this.zza) {
            try {
                if (null == zzb) {
                    this.zzb = new ArrayDeque();
                }
                this.zzb.add(zzq);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void zzb(@androidx.annotation.NonNull com.google.android.gms.tasks.Task r3) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tasks.zzr.zzb(com.google.android.gms.tasks.Task):void");
    }
}
