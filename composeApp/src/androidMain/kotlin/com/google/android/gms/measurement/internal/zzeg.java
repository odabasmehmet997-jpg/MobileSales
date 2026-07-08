package com.google.android.gms.measurement.internal;

import android.util.Log;
import androidx.exifinterface.media.ExifInterface;

/**
 * @param zza synthetic
 * @param zzb synthetic
 * @param zzc synthetic
 * @param zzd synthetic
 * @param zze synthetic
 * @param zzf synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
record zzeg(zzej zzf, int zza, String zzb, Object zzc, Object zzd, Object zze) implements Runnable {

    public void run() {
        zzey zzm = this.zzf.zzs.zzm();
        if (zzm.zzx()) {
            zzej zzej = this.zzf;
            if (0 == zzej.zza) {
                if (zzej.zzs.zzf().zzy()) {
                    zzej zzej2 = this.zzf;
                    zzej2.zzs.zzaw();
                    zzej2.zza = 'C';
                } else {
                    zzej zzej3 = this.zzf;
                    zzej3.zzs.zzaw();
                    zzej3.zza = 'c';
                }
            }
            zzej zzej4 = this.zzf;
            if (0 > zzej4.zzb) {
                zzej4.zzs.zzf().zzh();
                zzej4.zzb = 60000;
            }
            char charAt = "01VDIWEA?".charAt(this.zza);
            zzej zzej5 = this.zzf;
            char zza2 = zzej5.zza;
            long zzb2 = zzej5.zzb;
            String zzo = com.google.android.gms.measurement.internal.zzej.zzo(true, this.zzb, this.zzc, this.zzd, this.zze);
            String sb2 = ExifInterface.GPS_MEASUREMENT_2D +
                    charAt +
                    zza2 +
                    zzb2 +
                    ":" +
                    zzo;
            if (1024 < sb2.length()) {
                sb2 = this.zzb.substring(0, 1024);
            }
            zzew zzew = zzm.zzb;
            if (null != zzew) {
                zzew.zzb(sb2, 1);
                return;
            }
            return;
        }
        Log.println(6, this.zzf.zzq(), "Persisted config not initialized. Not logging error/warn");
    }
}
