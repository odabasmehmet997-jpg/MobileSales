package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.service.Common;
import com.google.android.gms.common.internal.zak;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.errorprone.annotations.concurrent.GuardedBy;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import java.util.concurrent.locks.Lock;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zabe extends GoogleApiClient implements zabz {
    @VisibleForTesting
    final Queue zaa;
    @VisibleForTesting
    @Nullable
    zabx zab;
    final Map zac;
    Set zad;
    final ClientSettings zae;
    final Map zaf;
    final Api.AbstractClientBuilder zag;
    @Nullable
    Set zah;
    final zadc zai;
    private final Lock zaj;
    private final zak zak;
    @Nullable
    private zaca zal;
    private final int zam;
    
    public final Context zan;
    private final Looper zao;
    private volatile boolean zap;
    private long zaq;
    private long zar;
    private final zabc zas;
    private final GoogleApiAvailability zat;
    private final ListenerHolders zau;
    private final ArrayList zav;
    private Integer zaw;

    public static int zad(final Iterable iterable, final boolean z) {
        final Iterator it = iterable.iterator();
        boolean z2 = false;
        boolean z3 = false;
        while (it.hasNext()) {
            final Api.Client client = (Api.Client) it.next();
            z2 |= client.requiresSignIn();
            z3 |= client.providesSignIn();
        }
        if (z2) {
            return (!z3 || !z) ? 1 : 2;
        }
        return 3;
    }

    static String zag(final int i2) {
        return 1 != i2 ? 2 != i2 ? 3 != i2 ? "UNKNOWN" : "SIGN_IN_MODE_NONE" : "SIGN_IN_MODE_OPTIONAL" : "SIGN_IN_MODE_REQUIRED";
    }

    static void zai(final zabe zabe) {
        zabe.zaj.lock();
        try {
            if (zabe.zap) {
                zabe.zan();
            }
        } finally {
            zabe.zaj.unlock();
        }
    }

    static void zaj(final zabe zabe) {
        zabe.zaj.lock();
        try {
            if (zabe.zak()) {
                zabe.zan();
            }
        } finally {
            zabe.zaj.unlock();
        }
    }

    private void zal(final int i2) {
        final Integer num = zaw;
        if (null == num) {
            zaw = Integer.valueOf(i2);
        } else if (num.intValue() != i2) {
            throw new IllegalStateException("Cannot use sign-in mode: " + zabe.zag(i2) + ". Mode was already set to " + zabe.zag(zaw.intValue()));
        }
        if (null == this.zal) {
            boolean z = false;
            boolean z2 = false;
            for (final Api.Client client : zac.values()) {
                z |= client.requiresSignIn();
                z2 |= client.providesSignIn();
            }
            final int intValue = zaw.intValue();
            if (1 != intValue) {
                if (2 == intValue && z) {
                    zal = zaaa.zag(zan, this, zaj, zao, zat, zac, zae, zaf, zag, zav);
                    return;
                }
            } else if (!z) {
                throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
            } else if (z2) {
                throw new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            zal = new zabi(zan, this, zaj, zao, zat, zac, zae, zaf, zag, zav, this);
        }
    }

    
    public void zam(final GoogleApiClient googleApiClient, final StatusPendingResult statusPendingResult, final boolean z) {
        Common.zaa.zaa(googleApiClient).setResultCallback(new zabb(this, statusPendingResult, z, googleApiClient));
    }

    @GuardedBy
    private void zan() {
        zak.zab();
        Preconditions.checkNotNull(zal).zaq();
    }

    public void connect() {
        zaj.lock();
        try {
            int i2 = 2;
            boolean z = false;
            if (0 <= this.zam) {
                Preconditions.checkState(null != this.zaw, "Sign-in mode should have been set explicitly by auto-manage.");
            } else {
                final Integer num = zaw;
                if (null == num) {
                    zaw = Integer.valueOf(zabe.zad(zac.values(), false));
                } else if (2 == num.intValue()) {
                    throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
                }
            }
            final int intValue = Preconditions.checkNotNull(zaw).intValue();
            zaj.lock();
            if (3 == intValue || 1 == intValue) {
                i2 = intValue;
            } else if (2 != intValue) {
                i2 = intValue;
                Preconditions.checkArgument(z, "Illegal sign-in mode: " + i2);
                this.zal(i2);
                this.zan();
                zaj.unlock();
                zaj.unlock();
            }
            z = true;
            Preconditions.checkArgument(z, "Illegal sign-in mode: " + i2);
            this.zal(i2);
            this.zan();
            zaj.unlock();
            zaj.unlock();
        } catch (final Throwable th) {
            zaj.unlock();
            throw th;
        }
    }

    public void disconnect() {
        zaj.lock();
        try {
            zai.zab();
            final zaca zaca = zal;
            if (null != zaca) {
                zaca.zar();
            }
            zau.zab();
            for (final BaseImplementation.ApiMethodImpl apiMethodImpl : zaa) {
                apiMethodImpl.zan(null);
                apiMethodImpl.cancel();
            }
            zaa.clear();
            if (null != this.zal) {
                this.zak();
                zak.zaa();
            }
            zaj.unlock();
        } catch (final Throwable th) {
            zaj.unlock();
            throw th;
        }
    }

    public void dump(final String str, @Nullable final FileDescriptor fileDescriptor, final PrintWriter printWriter, @Nullable final String[] strArr) {
        printWriter.append(str).append("mContext=").println(zan);
        printWriter.append(str).append("mResuming=").print(zap);
        printWriter.append(" mWorkQueue.size()=").print(zaa.size());
        printWriter.append(" mUnconsumedApiCalls.size()=").println(zai.zab.size());
        final zaca zaca = zal;
        if (null != zaca) {
            zaca.zas(str, fileDescriptor, printWriter, strArr);
        }
    }

    
    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(@NonNull T t) {
        final Map map = zac;
        final Api<?> api = t.getApi();
        final boolean containsKey = map.containsKey(t.getClientKey());
        final String zad2 = null != api ? api.zad() : "the API";
        Preconditions.checkArgument(containsKey, "GoogleApiClient is not configured to use " + zad2 + " required for this call.");
        zaj.lock();
        try {
            final zaca zaca = zal;
            if (null != zaca) {
                if (zap) {
                    zaa.add(t);
                    while (!zaa.isEmpty()) {
                        final BaseImplementation.ApiMethodImpl apiMethodImpl = (BaseImplementation.ApiMethodImpl) zaa.remove();
                        zai.zaa(apiMethodImpl);
                        apiMethodImpl.setFailedResult(Status.RESULT_INTERNAL_ERROR);
                    }
                } else {
                    t = zaca.zaf(t);
                }
                zaj.unlock();
                return t;
            }
            throw new IllegalStateException("GoogleApiClient is not connected yet.");
        } catch (final Throwable th) {
            zaj.unlock();
            throw th;
        }
    }

    public Looper getLooper() {
        return zao;
    }

    public boolean isConnected() {
        final zaca zaca = zal;
        return null != zaca && zaca.zaw();
    }

    public void unregisterConnectionFailedListener(@NonNull final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        zak.zai(onConnectionFailedListener);
    }

    @GuardedBy
    public void zaa(final ConnectionResult connectionResult) {
        if (!zat.isPlayServicesPossiblyUpdating(zan, connectionResult.getErrorCode())) {
            this.zak();
        }
        if (!zap) {
            zak.zac(connectionResult);
            zak.zaa();
        }
    }

    @GuardedBy
    public void zab(@Nullable final Bundle bundle) {
        while (!zaa.isEmpty()) {
            this.execute((BaseImplementation.ApiMethodImpl) zaa.remove());
        }
        zak.zad(bundle);
    }

    
    public String zaf() {
        final StringWriter stringWriter = new StringWriter();
        this.dump("", null, new PrintWriter(stringWriter), null);
        return stringWriter.toString();
    }

    
    
    @GuardedBy
    public boolean zak() {
        if (!zap) {
            return false;
        }
        zap = false;
        zas.removeMessages(2);
        zas.removeMessages(1);
        final zabx zabx = zab;
        if (null != zabx) {
            zabx.zab();
            zab = null;
        }
        return true;
    }

    public void zao(final zada zada) {
        zaj.lock();
        try {
            if (null == this.zah) {
                zah = new HashSet();
            }
            zah.add(zada);
            zaj.unlock();
        } catch (final Throwable th) {
            zaj.unlock();
            throw th;
        }
    }
    public void zap(final com.google.android.gms.common.api.internal.zada r3) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zabe.zap(com.google.android.gms.common.api.internal.zada):void");
    }

    public void zac(int i2, final boolean z) {
        if (1 == i2) {
            if (!z && !zap) {
                zap = true;
                if (null == this.zab && !ClientLibraryUtils.isPackageSide()) {
                    try {
                        zab = zat.zac(zan.getApplicationContext(), new zabd(this));
                    } catch (final SecurityException unused) {
                    }
                }
                final zabc zabc = zas;
                zabc.sendMessageDelayed(zabc.obtainMessage(1), zaq);
                final zabc zabc2 = zas;
                zabc2.sendMessageDelayed(zabc2.obtainMessage(2), zar);
            }
            i2 = 1;
        }
        for (final BasePendingResult forceFailureUnlessReady : (BasePendingResult[]) zai.zab.toArray(new BasePendingResult[0])) {
            forceFailureUnlessReady.forceFailureUnlessReady(zadc.zaa);
        }
        zak.zae(i2);
        zak.zaa();
        if (2 == i2) {
            this.zan();
        }
    }
}
