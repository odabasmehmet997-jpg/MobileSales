package com.google.android.gms.internal.gtm;

import com.google.android.gms.common.internal.Preconditions;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzeq {
    final zzer zza;
    private int zzb;
    private final ByteArrayOutputStream zzc = new ByteArrayOutputStream();

    public zzeq(zzer zzer) {
        this.zza = zzer;
    }

    public int zza() {
        return this.zzb;
    }

    public boolean zzb(zzek zzek) {
        Preconditions.checkNotNull(zzek);
        int i2 = this.zzb + 1;
        this.zza.zzw();
        if (i2 <= zzcs.zzg()) {
            String zza2 = this.zza.zza(zzek, false);
            if (null == zza2) {
                this.zza.zzz().zzb(zzek, "Error formatting hit");
                return true;
            }
            byte[] bytes = zza2.getBytes(StandardCharsets.UTF_8);
            int length = bytes.length;
            this.zza.zzw();
            if (length > zzcs.zzf()) {
                this.zza.zzz().zzb(zzek, "Hit size exceeds the maximum size limit");
                return true;
            }
            if (0 < zzc.size()) {
                length++;
            }
            int size = this.zzc.size() + length;
            this.zza.zzw();
            if (size <= ((Integer) zzeh.zzt.zzb()).intValue()) {
                try {
                    if (0 < zzc.size()) {
                        this.zzc.write(zzer.zza);
                    }
                    this.zzc.write(bytes);
                    this.zzb++;
                    return true;
                } catch (IOException e2) {
                    this.zza.zzJ("Failed to write payload when batching hits", e2);
                    return true;
                }
            }
        }
        return false;
    }

    public byte[] zzc() {
        return this.zzc.toByteArray();
    }
}
