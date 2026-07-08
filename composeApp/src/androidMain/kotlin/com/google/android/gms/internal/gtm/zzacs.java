package com.google.android.gms.internal.gtm;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzacs implements Map.Entry {
    private final Map.Entry zza;

    zzacs(Map.Entry entry, zzacu zzacu) {
        this.zza = entry;
    }

    public Object getKey() {
        return this.zza.getKey();
    }

    public Object getValue() {
        if (null == zza.getValue()) {
            return null;
        }
        throw null;
    }

    public Object setValue(Object obj) {
        if (obj instanceof zzadl) {
            return ((zzacv) this.zza.getValue()).zzc((zzadl) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }

    public zzacv zza() {
        return (zzacv) this.zza.getValue();
    }
}
