package com.google.android.gms.internal.measurement;

import androidx.core.app.NotificationCompat;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzt extends zzai {
    
    public final zzr zza;

    public zzt(final zzr zzr) {
        super("internal.logger");
        zza = zzr;
        zze.put("log", new zzs(this, false, true));
        zze.put(NotificationCompat.GROUP_KEY_SILENT, new zzp(this, NotificationCompat.GROUP_KEY_SILENT));
        ((zzai) zze.get(NotificationCompat.GROUP_KEY_SILENT)).zzr("log", new zzs(this, true, true));
        zze.put("unmonitored", new zzq(this, "unmonitored"));
        ((zzai) zze.get("unmonitored")).zzr("log", new zzs(this, false, false));
    }

    public zzap zza(final zzg zzg, final List list) {
        return zzf;
    }
}
