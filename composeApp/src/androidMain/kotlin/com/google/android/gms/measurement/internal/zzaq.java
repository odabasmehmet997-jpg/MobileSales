package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zzaq {
    final String zza;
    final String zzb;
    final long zzc;
    final long zzd;
    final long zze;
    final long zzf;
    final long zzg;
    final Long zzh;
    final Long zzi;
    final Long zzj;
    final Boolean zzk;

    zzaq(String str, String str2, long j2, long j3, long j4, long j5, long j6, Long l, Long l2, Long l3, Boolean bool) {
        long j7 = j2;
        long j8 = j3;
        long j9 = j4;
        long j10 = j6;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        final boolean z = false;
        Preconditions.checkArgument(0 <= j7);
        Preconditions.checkArgument(0 <= j8);
        Preconditions.checkArgument(0 <= j9);
        Preconditions.checkArgument(0 <= j10 || z);
        this.zza = str;
        this.zzb = str2;
        this.zzc = j7;
        this.zzd = j8;
        this.zze = j9;
        this.zzf = j5;
        this.zzg = j10;
        this.zzh = l;
        this.zzi = l2;
        this.zzj = l3;
        this.zzk = bool;
    }

    
    public zzaq zza(Long l, Long l2, Boolean bool) {
        return new zzaq(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, l, l2, (null == bool || bool.booleanValue()) ? bool : null);
    }

    
    public zzaq zzb(long j2, long j3) {
        return new zzaq(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, j2, Long.valueOf(j3), this.zzi, this.zzj, this.zzk);
    }

    
    public zzaq zzc(long j2) {
        return new zzaq(this.zza, this.zzb, this.zzc, this.zzd, this.zze, j2, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk);
    }
}
