package com.google.android.gms.common.api.internal;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LifecycleKtExternalSyntheticBackportWithForwarding0;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.internal.base.zau;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public abstract class zap extends LifecycleCallback implements DialogInterface.OnCancelListener {
    protected volatile boolean zaa;
    protected final AtomicReference zab = new AtomicReference(null);
    protected final GoogleApiAvailability zac;
    private final Handler zad = new zau(Looper.getMainLooper());

    @VisibleForTesting
    zap(final LifecycleFragment lifecycleFragment, final GoogleApiAvailability googleApiAvailability) {
        super(lifecycleFragment);
        zac = googleApiAvailability;
    }

    
    public final void zaa(final ConnectionResult connectionResult, final int i2) {
        zab.set(null);
        this.zab(connectionResult, i2);
    }

    
    public final void zad() {
        zab.set(null);
        this.zac();
    }

    private static final int zae(@Nullable final zam zam) {
        if (null == zam) {
            return -1;
        }
        return zam.zaa();
    }

    public final void onActivityResult(final int i2, final int i3, final Intent intent) {
        final zam zam = (zam) zab.get();
        if (1 != i2) {
            if (2 == i2) {
                final int isGooglePlayServicesAvailable = zac.isGooglePlayServicesAvailable(this.getActivity());
                if (0 == isGooglePlayServicesAvailable) {
                    this.zad();
                    return;
                } else if (null == zam) {
                    return;
                } else {
                    if (18 == zam.zab().getErrorCode() && 18 == isGooglePlayServicesAvailable) {
                        return;
                    }
                }
            }
        } else if (-1 == i3) {
            this.zad();
            return;
        } else if (0 == i3) {
            if (null != zam) {
                int i4 = 13;
                if (null != intent) {
                    i4 = intent.getIntExtra("<<ResolutionFailureErrorDetail>>", 13);
                }
                this.zaa(new ConnectionResult(i4, null, zam.zab().toString()), zap.zae(zam));
                return;
            }
            return;
        }
        if (null != zam) {
            this.zaa(zam.zab(), zam.zaa());
        }
    }

    public final void onCancel(final DialogInterface dialogInterface) {
        this.zaa(new ConnectionResult(13, null), zap.zae((zam) zab.get()));
    }

    public final void onCreate(@Nullable final Bundle bundle) {
        super.onCreate(bundle);
        if (null != bundle) {
            zab.set(bundle.getBoolean("resolving_error", false) ? new zam(new ConnectionResult(bundle.getInt("failed_status"), bundle.getParcelable("failed_resolution")), bundle.getInt("failed_client_id", -1)) : null);
        }
    }

    public final void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        final zam zam = (zam) zab.get();
        if (null != zam) {
            bundle.putBoolean("resolving_error", true);
            bundle.putInt("failed_client_id", zam.zaa());
            bundle.putInt("failed_status", zam.zab().getErrorCode());
            bundle.putParcelable("failed_resolution", zam.zab().getResolution());
        }
    }

    public void onStart() {
        super.onStart();
        zaa = true;
    }

    public void onStop() {
        super.onStop();
        zaa = false;
    }

    
    public abstract void zab(ConnectionResult connectionResult, int i2);

    
    public abstract void zac();

    public final void zah(final ConnectionResult connectionResult, final int i2) {
        AtomicReference atomicReference;
        final zam zam = new zam(connectionResult, i2);
        do {
            atomicReference = zab;
            if (LifecycleKtExternalSyntheticBackportWithForwarding0.m(atomicReference, (Object) null, zam)) {
                zad.post(new zao(this, zam));
                return;
            }
        } while (null == atomicReference.get());
    }
}
