package com.google.android.gms.internal.gtm;

import java.io.IOException;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzzf extends IOException {
    zzzf() {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.");
    }

    zzzf(final long j2, final long j3, final int i2, final Throwable th) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.: " + String.format(Locale.US, "Pos: %d, limit: %d, len: %d", Long.valueOf(j2), Long.valueOf(j3), Integer.valueOf(i2)), th);
    }

    zzzf(final Throwable th) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
    }
}
