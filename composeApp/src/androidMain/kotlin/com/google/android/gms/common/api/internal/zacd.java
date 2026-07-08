package com.google.android.gms.common.api.internal;

import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zacd implements OnCompleteListener {
    private final GoogleApiManager zaa;
    private final int zab;
    private final ApiKey zac;
    private final long zad;
    private final long zae;

    @VisibleForTesting
    zacd(final GoogleApiManager googleApiManager, final int i2, final ApiKey apiKey, final long j2, final long j3, @Nullable final String str, @Nullable final String str2) {
        zaa = googleApiManager;
        zab = i2;
        zac = apiKey;
        zad = j2;
        zae = j3;
    }

    @Nullable
    static zacd zaa(final GoogleApiManager googleApiManager, final int i2, final ApiKey apiKey) {
        boolean z;
        if (!googleApiManager.zaD()) {
            return null;
        }
        final RootTelemetryConfiguration config = RootTelemetryConfigManager.getInstance().getConfig();
        if (null == config) {
            z = true;
        } else if (!config.getMethodInvocationTelemetryEnabled()) {
            return null;
        } else {
            z = config.getMethodTimingTelemetryEnabled();
            final zabq zai = googleApiManager.zai(apiKey);
            if (null != zai) {
                if (!(zai.zaf() instanceof BaseGmsClient baseGmsClient)) {
                    return null;
                }
                if (baseGmsClient.hasConnectionInfo() && !baseGmsClient.isConnecting()) {
                    final ConnectionTelemetryConfiguration zab2 = zacd.zab(zai, baseGmsClient, i2);
                    if (null == zab2) {
                        return null;
                    }
                    zai.zaq();
                    z = zab2.getMethodTimingTelemetryEnabled();
                }
            }
        }
        return new zacd(googleApiManager, i2, apiKey, z ? System.currentTimeMillis() : 0, z ? SystemClock.elapsedRealtime() : 0, null, null);
    }

    @Nullable
    private static ConnectionTelemetryConfiguration zab(final zabq zabq, final BaseGmsClient baseGmsClient, final int i2) {
        final int[] methodInvocationMethodKeyAllowlist;
        final int[] methodInvocationMethodKeyDisallowlist;
        final ConnectionTelemetryConfiguration telemetryConfiguration = baseGmsClient.getTelemetryConfiguration();
        if (null == telemetryConfiguration || !telemetryConfiguration.getMethodInvocationTelemetryEnabled() || (null != (methodInvocationMethodKeyAllowlist = telemetryConfiguration.getMethodInvocationMethodKeyAllowlist()) ? !ArrayUtils.contains(methodInvocationMethodKeyAllowlist, i2) : !(null == (methodInvocationMethodKeyDisallowlist = telemetryConfiguration.getMethodInvocationMethodKeyDisallowlist()) || !ArrayUtils.contains(methodInvocationMethodKeyDisallowlist, i2))) || zabq.zac() >= telemetryConfiguration.getMaxMethodInvocationsLogged()) {
            return null;
        }
        return telemetryConfiguration;
    }

    @WorkerThread
    public void onComplete(@NonNull final Task task) {
        final zabq zai;
        final int i2;
        final int i3;
        final int i4;
        int i5;
        final int i6;
        final long j2;
        final long j3;
        if (zaa.zaD()) {
            final RootTelemetryConfiguration config = RootTelemetryConfigManager.getInstance().getConfig();
            if ((null == config || config.getMethodInvocationTelemetryEnabled()) && null != (zai = this.zaa.zai(this.zac)) && (zai.zaf() instanceof BaseGmsClient baseGmsClient)) {
                boolean z = true;
                int i7 = 0;
                final boolean z2 = 0 < this.zad;
                final int gCoreServiceId = baseGmsClient.getGCoreServiceId();
                int i8 = 100;
                if (null != config) {
                    boolean methodTimingTelemetryEnabled = z2 & config.getMethodTimingTelemetryEnabled();
                    final int batchPeriodMillis = config.getBatchPeriodMillis();
                    int maxMethodInvocationsInBatch = config.getMaxMethodInvocationsInBatch();
                    i4 = config.getVersion();
                    if (baseGmsClient.hasConnectionInfo() && !baseGmsClient.isConnecting()) {
                        final ConnectionTelemetryConfiguration zab2 = zacd.zab(zai, baseGmsClient, zab);
                        if (null != zab2) {
                            if (!zab2.getMethodTimingTelemetryEnabled() || 0 >= this.zad) {
                                z = false;
                            }
                            maxMethodInvocationsInBatch = zab2.getMaxMethodInvocationsLogged();
                            methodTimingTelemetryEnabled = z;
                        } else {
                            return;
                        }
                    }
                    i3 = batchPeriodMillis;
                    i2 = maxMethodInvocationsInBatch;
                } else {
                    i4 = 0;
                    i2 = 100;
                    i3 = 5000;
                }
                final GoogleApiManager googleApiManager = zaa;
                if (task.isSuccessful()) {
                    i5 = 0;
                } else {
                    if (!task.isCanceled()) {
                        final Exception exception = task.getException();
                        if (exception instanceof ApiException) {
                            final Status status = ((ApiException) exception).getStatus();
                            i8 = status.getStatusCode();
                            final ConnectionResult connectionResult = status.getConnectionResult();
                            if (null != connectionResult) {
                                i5 = connectionResult.getErrorCode();
                                i7 = i8;
                            }
                        } else {
                            i7 = 101;
                            i5 = -1;
                        }
                    }
                    i7 = i8;
                    i5 = -1;
                }
                if (z2) {
                    final long j4 = zad;
                    final long j5 = zae;
                    final long currentTimeMillis = System.currentTimeMillis();
                    i6 = (int) (SystemClock.elapsedRealtime() - j5);
                    j2 = currentTimeMillis;
                    j3 = j4;
                } else {
                    j3 = 0;
                    j2 = 0;
                    i6 = -1;
                }
                googleApiManager.zaw(new MethodInvocation(zab, i7, i5, j3, j2, null, null, gCoreServiceId, i6), i4, i3, i2);
            }
        }
    }
}
