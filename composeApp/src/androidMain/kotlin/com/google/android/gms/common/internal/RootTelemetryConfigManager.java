package com.google.android.gms.common.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public final class RootTelemetryConfigManager {
    @Nullable
    private static RootTelemetryConfigManager zza;
    private static final RootTelemetryConfiguration zzb = new RootTelemetryConfiguration(0, false, false, 0, 0);
    @Nullable
    private RootTelemetryConfiguration zzc;

    private RootTelemetryConfigManager() {
    }

    @NonNull
    @KeepForSdk
    public static synchronized RootTelemetryConfigManager getInstance() {
        RootTelemetryConfigManager rootTelemetryConfigManager;
        synchronized (RootTelemetryConfigManager.class) {
            try {
                if (null == RootTelemetryConfigManager.zza) {
                    zza = new RootTelemetryConfigManager();
                }
                rootTelemetryConfigManager = zza;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return rootTelemetryConfigManager;
    }

    @KeepForSdk
    @Nullable
    public RootTelemetryConfiguration getConfig() {
        return this.zzc;
    }

    @VisibleForTesting
    public synchronized void zza(@Nullable RootTelemetryConfiguration rootTelemetryConfiguration) {
        if (null == rootTelemetryConfiguration) {
            this.zzc = zzb;
            return;
        }
        RootTelemetryConfiguration rootTelemetryConfiguration2 = this.zzc;
        if (null == rootTelemetryConfiguration2 || rootTelemetryConfiguration2.getVersion() < rootTelemetryConfiguration.getVersion()) {
            this.zzc = rootTelemetryConfiguration;
        }
    }
}
