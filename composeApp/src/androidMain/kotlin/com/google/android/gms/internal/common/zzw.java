package com.google.android.gms.internal.common;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
abstract class zzw extends zzj {
    final CharSequence zzb;
    final zzo zzc;
    final boolean zzd;
    int zze;
    int zzf;

    protected zzw(zzx zzx, CharSequence charSequence) {
        this.zzc = zzx.zza;
        this.zzd = zzx.zzb;
        this.zzf = Integer.MAX_VALUE;
        this.zzb = charSequence;
    }

    
    public final Object zza() {
        int zzd2;
        int i2;
        int i3 = this.zze;
        while (true) {
            int i4 = this.zze;
            if (-1 != i4) {
                zzd2 = zzd(i4);
                if (-1 == zzd2) {
                    zzd2 = this.zzb.length();
                    this.zze = -1;
                    i2 = -1;
                } else {
                    i2 = zzc(zzd2);
                    this.zze = i2;
                }
                if (i2 == i3) {
                    int i5 = i2 + 1;
                    this.zze = i5;
                    if (i5 > this.zzb.length()) {
                        this.zze = -1;
                    }
                } else {
                    if (i3 < zzd2) {
                        this.zzb.charAt(i3);
                    }
                    if (i3 < zzd2) {
                        this.zzb.charAt(zzd2 - 1);
                    }
                    if (!this.zzd || i3 != zzd2) {
                        int i6 = this.zzf;
                    } else {
                        i3 = this.zze;
                    }
                }
            } else {
                zzb();
                return null;
            }
        }
        int i62 = this.zzf;
        if (1 == i62) {
            zzd2 = this.zzb.length();
            this.zze = -1;
            if (zzd2 > i3) {
                this.zzb.charAt(zzd2 - 1);
            }
        } else {
            this.zzf = i62 - 1;
        }
        return this.zzb.subSequence(i3, zzd2).toString();
    }

    
    public abstract int zzc(int i2);

    
    public abstract int zzd(int i2);
}
