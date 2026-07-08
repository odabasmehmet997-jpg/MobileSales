package com.google.android.gms.internal.common;

import com.google.firebase.analytics.FirebaseAnalytics;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
final class zzt extends zzw {
    final zzu zza;

    
    zzt(zzu zzu, zzx zzx, CharSequence charSequence) {
        super(zzx, charSequence);
        this.zza = zzu;
    }

    
    public int zzc(int i2) {
        return i2 + 1;
    }

    
    public int zzd(int i2) {
        CharSequence charSequence = this.zzb;
        int length = charSequence.length();
        zzs.zzb(i2, length, FirebaseAnalytics.Param.INDEX);
        while (i2 < length) {
            zzu zzu = this.zza;
            if (zzu.zza().zza(charSequence.charAt(i2))) {
                return i2;
            }
            i2++;
        }
        return -1;
    }
}
