package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzkx implements zzle {
    private final zzle[] zza;

    zzkx(zzle... zzleArr) {
        this.zza = zzleArr;
    }

    public zzld zzb(Class cls) {
        zzle[] zzleArr = this.zza;
        for (int i2 = 0; 2 > i2; i2++) {
            zzle zzle = zzleArr[i2];
            if (zzle.zzc(cls)) {
                return zzle.zzb(cls);
            }
        }
        String name = cls.getName();
        throw new UnsupportedOperationException(0 != name.length() ? "No factory is available for message type: " + name : "No factory is available for message type: ");
    }

    public boolean zzc(Class cls) {
        zzle[] zzleArr = this.zza;
        for (int i2 = 0; 2 > i2; i2++) {
            if (zzleArr[i2].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
