package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzju implements zzle {
    private static final zzju zza = new zzju();

    private zzju() {
    }

    public static zzju zza() {
        return zza;
    }

    public zzld zzb(Class cls) {
        final Class<zzjz> cls2 = zzjz.class;
        if (!cls2.isAssignableFrom(cls)) {
            String name = cls.getName();
            throw new IllegalArgumentException(0 != name.length() ? "Unsupported message type: " + name : "Unsupported message type: ");
        }
        try {
            return (zzld) zzjz.zzbu(cls.asSubclass(cls2)).zzl(3, null, null);
        } catch (Exception e2) {
            String name2 = cls.getName();
            throw new RuntimeException(0 != name2.length() ? "Unable to get message info for " + name2 : "Unable to get message info for ", e2);
        }
    }

    public boolean zzc(Class cls) {
        return zzjz.class.isAssignableFrom(cls);
    }
}
