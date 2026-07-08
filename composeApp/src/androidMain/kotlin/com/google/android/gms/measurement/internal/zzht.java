package com.google.android.gms.measurement.internal;


class zzht(zzhy zze, zzah zza, int zzb, long zzc, boolean zzd) implements Runnable {

    public void run() {
        try {
            zze.zzW(zza);
            zzhy.zzv(zze, zza, zzb, zzc, false, zzd);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
