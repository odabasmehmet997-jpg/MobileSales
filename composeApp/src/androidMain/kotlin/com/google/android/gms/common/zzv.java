package com.google.android.gms.common;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
final class zzv extends zzx {
    private final Callable zze;

    zzv(final Callable callable, final zzu zzu) {
        super(false, 1, 5, null, null, null);
        zze = callable;
    }

    
    public String zza() {
        try {
            return (String) zze.call();
        } catch (final Exception e2) {
            throw new RuntimeException(e2);
        }
    }
}
