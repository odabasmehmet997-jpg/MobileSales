package com.google.android.gms.internal.common;

import com.google.firebase.analytics.FirebaseAnalytics;
import org.jspecify.nullness.NullMarked;

@NullMarked
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
final class zzai extends zzag {
    static final zzag zza = new zzai(new Object[0], 0);
    final transient Object[] zzb;
    private final transient int zzc;

    zzai(Object[] objArr, int i2) {
        this.zzb = objArr;
        this.zzc = i2;
    }

    public Object get(int i2) {
        zzs.zza(i2, this.zzc, FirebaseAnalytics.Param.INDEX);
        Object obj = this.zzb[i2];
        obj.getClass();
        return obj;
    }

    public int size() {
        return this.zzc;
    }

    
    public int zza(Object[] objArr, int i2) {
        System.arraycopy(this.zzb, 0, objArr, 0, this.zzc);
        return this.zzc;
    }

    
    public int zzb() {
        return this.zzc;
    }

    
    public int zzc() {
        return 0;
    }

    
    public boolean zzf() {
        return false;
    }

    
    public Object[] zzg() {
        return this.zzb;
    }
}
