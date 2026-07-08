package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zzhx extends zzhz {
    static final zzhx zza = new zzhx();

    private zzhx() {
    }

    public boolean equals(Object obj) {
        return obj == this;
    }

    public int hashCode() {
        return 2040732332;
    }

    public String toString() {
        return "Optional.absent()";
    }

    public Object zza() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    public boolean zzb() {
        return false;
    }
}
