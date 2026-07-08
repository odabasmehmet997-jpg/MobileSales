package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
public final class zzje extends IOException {
    zzje() {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.");
    }

    zzje(java.lang.String r3, java.lang.Throwable r4) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzje.<init>(java.lang.String, java.lang.Throwable):void");
    }

    zzje(Throwable th) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
    }
}
