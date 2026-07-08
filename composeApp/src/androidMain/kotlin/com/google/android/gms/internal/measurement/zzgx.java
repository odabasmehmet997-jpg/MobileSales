package com.google.android.gms.internal.measurement;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zzgx extends zzhs {
    private final Context zza;
    private final zzib zzb;

    zzgx(Context context, zzib zzib) {
        if (null != context) {
            this.zza = context;
            this.zzb = zzib;
            return;
        }
        throw new NullPointerException("Null context");
    }

    public boolean equals(java.lang.Object r5) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgx.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int i2;
        int hashCode = (this.zza.hashCode() ^ 1000003) * 1000003;
        zzib zzib = this.zzb;
        if (null == zzib) {
            i2 = 0;
        } else {
            i2 = zzib.hashCode();
        }
        return hashCode ^ i2;
    }

    public String toString() {
        String obj = this.zza.toString();
        String valueOf = String.valueOf(this.zzb);
        final String sb = "FlagsContext{context=" +
                obj +
                ", hermeticFileOverrides=" +
                valueOf +
                "}";
        return sb;
    }

    
    public Context zza() {
        return this.zza;
    }

    
    public zzib zzb() {
        return this.zzb;
    }
}
