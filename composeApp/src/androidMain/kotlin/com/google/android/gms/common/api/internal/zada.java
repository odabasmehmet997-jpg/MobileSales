package com.google.android.gms.common.api.internal;

import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.*;
import com.google.android.gms.common.internal.Preconditions;
import com.google.errorprone.annotations.concurrent.GuardedBy;

import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zada<R extends Result> extends TransformedResult<R> implements ResultCallback<R> {
    
    @Nullable
    public ResultTransform zaa;
    
    @Nullable
    public zada zab;
    @Nullable
    private volatile ResultCallbacks zac;
    @Nullable
    private PendingResult zad;
    
    public final Object zae;
    @Nullable
    private Status zaf;
    
    public final WeakReference zag;
    
    public final zacz zah;
    private boolean zai;

    
    public void zaj(final Status status) {
        synchronized (zae) {
            zaf = status;
            this.zal(status);
        }
    }

    @GuardedBy
    private void zak() {
        if (null != this.zaa || null != this.zac) {
            final GoogleApiClient googleApiClient = (GoogleApiClient) zag.get();
            if (!(zai || null == this.zaa || null == googleApiClient)) {
                googleApiClient.zao(this);
                zai = true;
            }
            final Status status = zaf;
            if (null != status) {
                this.zal(status);
                return;
            }
            final PendingResult pendingResult = zad;
            if (null != pendingResult) {
                pendingResult.setResultCallback(this);
            }
        }
    }

    private void zal(final Status status) {
        synchronized (zae) {
            try {
                final ResultTransform resultTransform = zaa;
                if (null != resultTransform) {
                    Preconditions.checkNotNull(zab).zaj(Preconditions.checkNotNull(resultTransform.onFailure(status), "onFailure must not return null"));
                } else if (this.zam()) {
                    Preconditions.checkNotNull(zac).onFailure(status);
                }
            } catch (final Throwable th) {
                throw th;
            }
        }
    }

    @GuardedBy
    private boolean zam() {
        return null != this.zac && null != this.zag.get();
    }

    
    public static void zan(final Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (final RuntimeException e2) {
                Log.w("TransformedResultImpl", "Unable to release " + result, e2);
            }
        }
    }

    public void onResult(final Result result) {
        synchronized (zae) {
            try {
                if (!result.getStatus().isSuccess()) {
                    this.zaj(result.getStatus());
                    zada.zan(result);
                } else if (null != this.zaa) {
                    zaco.zaa().submit(new zacy(this, result));
                } else if (this.zam()) {
                    Preconditions.checkNotNull(zac).onSuccess(result);
                }
            } catch (final Throwable th) {
                throw th;
            }
        }
    }

    
    public void zah() {
        zac = null;
    }

    public void zai(final PendingResult pendingResult) {
        synchronized (zae) {
            zad = pendingResult;
            this.zak();
        }
    }
}
