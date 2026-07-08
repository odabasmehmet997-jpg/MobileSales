package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzr;
import java.util.List;

/**
 * @param zza synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
record zzfi(zzfk zza) implements zzr {

    public void zza(int i2, String str, List list, boolean z, boolean z2) {
        zzeh zzeh;
        int i3 = i2 - 1;
        if (0 == i3) {
            zzeh = this.zza.zzs.zzay().zzc();
        } else if (1 != i3) {
            if (3 == i3) {
                zzeh = this.zza.zzs.zzay().zzj();
            } else if (4 != i3) {
                zzeh = this.zza.zzs.zzay().zzi();
            } else if (z) {
                zzeh = this.zza.zzs.zzay().zzm();
            } else if (!z2) {
                zzeh = this.zza.zzs.zzay().zzl();
            } else {
                zzeh = this.zza.zzs.zzay().zzk();
            }
        } else if (z) {
            zzeh = this.zza.zzs.zzay().zzh();
        } else if (!z2) {
            zzeh = this.zza.zzs.zzay().zze();
        } else {
            zzeh = this.zza.zzs.zzay().zzd();
        }
        int size = list.size();
        if (1 == size) {
            zzeh.zzb(str, list.get(0));
        } else if (2 == size) {
            zzeh.zzc(str, list.get(0), list.get(1));
        } else if (3 != size) {
            zzeh.zza(str);
        } else {
            zzeh.zzd(str, list.get(0), list.get(1), list.get(2));
        }
    }
}
