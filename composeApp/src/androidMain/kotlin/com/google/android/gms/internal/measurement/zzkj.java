package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
public class zzkj extends IOException {
    public zzkj(final String str) {
        super(str);
    }

    static zzki zza() {
        return new zzki("Protocol message tag had invalid wire type.");
    }

    static zzkj zzb() {
        return new zzkj("Protocol message contained an invalid tag (zero).");
    }

    static zzkj zzc() {
        return new zzkj("Protocol message had invalid UTF-8.");
    }

    static zzkj zzd() {
        return new zzkj("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zzkj zze() {
        return new zzkj("Failed to parse the message.");
    }

    static zzkj zzf() {
        return new zzkj("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }
}
