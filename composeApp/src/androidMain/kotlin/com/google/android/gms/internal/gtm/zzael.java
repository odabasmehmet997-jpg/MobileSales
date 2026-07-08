package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzael extends RuntimeException {
    public zzael(final zzadl zzadl) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    }

    public zzacq zza() {
        return new zzacq(this.getMessage());
    }
}
