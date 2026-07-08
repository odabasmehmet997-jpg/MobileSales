package com.google.android.gms.internal.gtm;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzcm extends zzbr {
    private volatile String zza;
    private Future zzb;

    zzcm(final zzbu zzbu) {
        super(zzbu);
    }

    
    @VisibleForTesting
    public String zzf() {
        FileOutputStream fileOutputStream;
        final String lowerCase = UUID.randomUUID().toString().toLowerCase(Locale.US);
        try {
            final Context zza2 = this.zzq().zza();
            Preconditions.checkNotEmpty(lowerCase);
            Preconditions.checkNotMainThread("ClientId should be saved from worker thread");
            fileOutputStream = null;
            try {
                this.zzO("Storing clientId", lowerCase);
                fileOutputStream = zza2.openFileOutput("gaClientId", 0);
                fileOutputStream.write(lowerCase.getBytes(StandardCharsets.UTF_8));
                try {
                    fileOutputStream.close();
                } catch (final IOException e2) {
                    this.zzJ("Failed to close clientId writing stream", e2);
                }
                return lowerCase;
                this.zzJ("Failed to close clientId writing stream", e);
                return "0";
            } catch (final FileNotFoundException e3) {
                this.zzJ("Error creating clientId file", e3);
                if (null != fileOutputStream) {
                    try {
                        fileOutputStream.close();
                    } catch (final IOException e4) {
                        e = e4;
                    }
                }
                return "0";
            } catch (final IOException e5) {
                this.zzJ("Error writing to clientId file", e5);
                if (null != fileOutputStream) {
                    try {
                        fileOutputStream.close();
                    } catch (final IOException e6) {
                        e = e6;
                    }
                }
                return "0";
            }
        } catch (final Exception e7) {
            this.zzJ("Error saving clientId file", e7);
            return "0";
        } catch (final Throwable th) {
            if (null != fileOutputStream) {
                try {
                    fileOutputStream.close();
                } catch (final IOException e8) {
                    this.zzJ("Failed to close clientId writing stream", e8);
                }
            }
            throw th;
        }
    }

    public String zzb() {
        final String str;
        this.zzV();
        synchronized (this) {
            try {
                if (null == this.zza) {
                    zzb = this.zzq().zzg(new zzck(this));
                }
                final Future future = zzb;
                if (null != future) {
                    zza = (String) future.get();
                    if (null == this.zza) {
                        zza = "0";
                    }
                    this.zzO("Loaded clientId", zza);
                    zzb = null;
                }
            } catch (final InterruptedException e2) {
                this.zzR("ClientId loading or generation was interrupted", e2);
                zza = "0";
            } catch (final ExecutionException e3) {
                this.zzJ("Failed to load or generate client id", e3);
                zza = "0";
            } catch (final Throwable th) {
                throw th;
            }
            str = zza;
        }
        return str;
    }

    public java.lang.String zzc() {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzcm.zzc():java.lang.String");
    }

    
    public void zzd() {
    }

    
    public String zze() {
        synchronized (this) {
            zza = null;
            zzb = this.zzq().zzg(new zzcl(this));
        }
        return this.zzb();
    }
}
