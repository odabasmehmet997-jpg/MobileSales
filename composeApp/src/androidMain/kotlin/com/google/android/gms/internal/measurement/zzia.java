package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zzia extends zzhz {
    private final Object zza;

    zzia(final Object obj) {
        zza = obj;
    }

    public boolean equals(final Object obj) {
        if (obj instanceof zzia) {
            return zza.equals(((zzia) obj).zza);
        }
        return false;
    }

    public int hashCode() {
        return zza.hashCode() + 1502476572;
    }

    public String toString() {
        final String obj = zza.toString();
        String sb = "Optional.of(" +
                obj +
                ")";
        return sb;
    }

    public Object zza() {
        return zza;
    }

    public boolean zzb() {
        return true;
    }
}
