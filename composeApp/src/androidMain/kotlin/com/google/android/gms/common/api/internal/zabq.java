package com.google.android.gms.common.api.internal;

import android.os.*;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import androidx.lifecycle.CoroutineLiveDataKt;
import androidx.work.PeriodicWorkRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.*;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.service.zap;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.tasks.TaskCompletionSource;

import java.util.*;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zabq implements GoogleApiClient.OnConnectionFailedListener, zau {
    final GoogleApiManager zaa;
    private final Queue zab = new LinkedList();
    
    public final Api.Client zac;
    
    public final ApiKey zad;
    private final zaad zae;
    private final Set zaf = new HashSet();
    private final Map zag = new HashMap();
    private final int zah;
    @Nullable
    private final zact zai;
    
    public boolean zaj;
    private final List zak = new ArrayList();
    @Nullable
    private ConnectionResult zal;
    private int zam;

    @WorkerThread
    public zabq(final GoogleApiManager googleApiManager, final GoogleApi googleApi) {
        zaa = googleApiManager;
        final Api.Client zab2 = googleApi.zab(googleApiManager.zar.getLooper(), this);
        zac = zab2;
        zad = googleApi.getApiKey();
        zae = new zaad();
        zah = googleApi.zaa();
        if (zab2.requiresSignIn()) {
            zai = googleApi.zac(googleApiManager.zai, googleApiManager.zar);
        } else {
            zai = null;
        }
    }

    @WorkerThread
    @Nullable
    private Feature zaC(@Nullable final Feature[] featureArr) {
        if (!(null == featureArr || 0 == featureArr.length)) {
            Feature[] availableFeatures = zac.getAvailableFeatures();
            if (null == availableFeatures) {
                availableFeatures = new Feature[0];
            }
            final ArrayMap arrayMap = new ArrayMap(availableFeatures.length);
            for (final Feature feature : availableFeatures) {
                arrayMap.put(feature.getName(), Long.valueOf(feature.getVersion()));
            }
            for (final Feature feature2 : featureArr) {
                final Long l = (Long) arrayMap.get(feature2.getName());
                if (null == l || l.longValue() < feature2.getVersion()) {
                    return feature2;
                }
            }
        }
        return null;
    }

    @WorkerThread
    private void zaD(final ConnectionResult connectionResult) {
        for (final zal zac2 : zaf) {
            zac2.zac(zad, connectionResult, Objects.equal(connectionResult, ConnectionResult.RESULT_SUCCESS) ? zac.getEndpointPackageName() : null);
        }
        zaf.clear();
    }

    
    @WorkerThread
    public void zaE(final Status status) {
        Preconditions.checkHandlerThread(zaa.zar);
        this.zaF(status, null, false);
    }

    @WorkerThread
    private void zaF(@Nullable final Status status, @Nullable final Exception exc, final boolean z) {
        Preconditions.checkHandlerThread(zaa.zar);
        boolean z2 = true;
        final boolean z3 = null == status;
        if (null != exc) {
            z2 = false;
        }
        if (z3 != z2) {
            final Iterator it = zab.iterator();
            while (it.hasNext()) {
                final zai zai2 = (zai) it.next();
                if (!z || 2 == zai2.zac) {
                    if (null != status) {
                        zai2.zad(status);
                    } else {
                        zai2.zae(exc);
                    }
                    it.remove();
                }
            }
            return;
        }
        throw new IllegalArgumentException("Status XOR exception should be null");
    }

    @WorkerThread
    private void zaG() {
        final ArrayList arrayList = new ArrayList(zab);
        final int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            final zai zai2 = (zai) arrayList.get(i2);
            if (zac.isConnected()) {
                if (this.zaM(zai2)) {
                    zab.remove(zai2);
                }
                i2++;
            } else {
                return;
            }
        }
    }

    
    @WorkerThread
    public void zaH() {
        this.zan();
        this.zaD(ConnectionResult.RESULT_SUCCESS);
        this.zaL();
        final Iterator it = zag.values().iterator();
        while (it.hasNext()) {
            final zaci zaci = (zaci) it.next();
            if (null != zaC(zaci.zaa().getRequiredFeatures())) {
                it.remove();
            } else {
                try {
                    zaci.zaa().registerListener(zac, new TaskCompletionSource());
                } catch (final DeadObjectException unused) {
                    this.onConnectionSuspended(3);
                    zac.disconnect("DeadObjectException thrown while calling register listener method.");
                } catch (final RemoteException unused2) {
                    it.remove();
                }
            }
        }
        this.zaG();
        this.zaJ();
    }

    
    @WorkerThread
    public void zaI(final int i2) {
        this.zan();
        zaj = true;
        zae.zae(i2, zac.getLastDisconnectMessage());
        final ApiKey apiKey = zad;
        final GoogleApiManager googleApiManager = zaa;
        googleApiManager.zar.sendMessageDelayed(Message.obtain(googleApiManager.zar, 9, apiKey), CoroutineLiveDataKt.DEFAULT_TIMEOUT);
        final ApiKey apiKey2 = zad;
        final GoogleApiManager googleApiManager2 = zaa;
        googleApiManager2.zar.sendMessageDelayed(Message.obtain(googleApiManager2.zar, 11, apiKey2), 120000);
        zaa.zak.zac();
        for (final zaci zaci : zag.values()) {
            zaci.zac().run();
        }
    }

    private void zaJ() {
        zaa.zar.removeMessages(12, zad);
        final ApiKey apiKey = zad;
        final GoogleApiManager googleApiManager = zaa;
        googleApiManager.zar.sendMessageDelayed(googleApiManager.zar.obtainMessage(12, apiKey), zaa.zae);
    }

    @WorkerThread
    private void zaK(final zai zai2) {
        zai2.zag(zae, this.zaA());
        try {
            zai2.zaf(this);
        } catch (final DeadObjectException unused) {
            this.onConnectionSuspended(1);
            zac.disconnect("DeadObjectException thrown while running ApiCallRunner.");
        }
    }

    @WorkerThread
    private void zaL() {
        if (zaj) {
            final GoogleApiManager googleApiManager = zaa;
            googleApiManager.zar.removeMessages(11, zad);
            final GoogleApiManager googleApiManager2 = zaa;
            googleApiManager2.zar.removeMessages(9, zad);
            zaj = false;
        }
    }

    @WorkerThread
    private boolean zaM(final zai zai2) {
        if (!(zai2 instanceof zac zac2)) {
            this.zaK(zai2);
            return true;
        }
        final Feature zaC = this.zaC(zac2.zab(this));
        if (null == zaC) {
            this.zaK(zai2);
            return true;
        }
        final String name = zac.getClass().getName();
        final String name2 = zaC.getName();
        final long version = zaC.getVersion();
        Log.w("GoogleApiManager", name + " could not execute call because it requires feature (" + name2 + ", " + version + ").");
        if (!zaa.zas || !zac2.zaa(this)) {
            zac2.zae(new UnsupportedApiCallException(zaC));
            return true;
        }
        final zabs zabs = new zabs(zad, zaC, null);
        final int indexOf = zak.indexOf(zabs);
        if (0 <= indexOf) {
            final zabs zabs2 = (zabs) zak.get(indexOf);
            zaa.zar.removeMessages(15, zabs2);
            final GoogleApiManager googleApiManager = zaa;
            googleApiManager.zar.sendMessageDelayed(Message.obtain(googleApiManager.zar, 15, zabs2), CoroutineLiveDataKt.DEFAULT_TIMEOUT);
            return false;
        }
        zak.add(zabs);
        final GoogleApiManager googleApiManager2 = zaa;
        googleApiManager2.zar.sendMessageDelayed(Message.obtain(googleApiManager2.zar, 15, zabs), CoroutineLiveDataKt.DEFAULT_TIMEOUT);
        final GoogleApiManager googleApiManager3 = zaa;
        googleApiManager3.zar.sendMessageDelayed(Message.obtain(googleApiManager3.zar, 16, zabs), 120000);
        final ConnectionResult connectionResult = new ConnectionResult(2, null);
        if (this.zaN(connectionResult)) {
            return false;
        }
        zaa.zaE(connectionResult, zah);
        return false;
    }
    private boolean zaN(@androidx.annotation.NonNull final com.google.android.gms.common.ConnectionResult r4) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zabq.zaN(com.google.android.gms.common.ConnectionResult):boolean");
    }

    public boolean zaO(final boolean z) {
        Preconditions.checkHandlerThread(zaa.zar);
        if (!zac.isConnected() || !zag.isEmpty()) {
            return false;
        }
        if (!zae.zag()) {
            zac.disconnect("Timing out service connection.");
            return true;
        } else if (!z) {
            return false;
        } else {
            this.zaJ();
            return false;
        }
    }

    static void zal(final zabq zabq, final zabs zabs) {
        if (!zabq.zak.contains(zabs) || zabq.zaj) {
            return;
        }
        if (!zabq.zac.isConnected()) {
            zabq.zao();
        } else {
            zabq.zaG();
        }
    }

    static void zam(final zabq zabq, final zabs zabs) {
        Feature[] zab2;
        if (zabq.zak.remove(zabs)) {
            zabq.zaa.zar.removeMessages(15, zabs);
            zabq.zaa.zar.removeMessages(16, zabs);
            final Feature zaa2 = zabs.zab;
            final ArrayList arrayList = new ArrayList(zabq.zab.size());
            for (final zai zai2 : zabq.zab) {
                if ((zai2 instanceof zac) && null != (zab2 = ((zac) zai2).zab(zabq)) && ArrayUtils.contains((T[]) zab2, zaa2)) {
                    arrayList.add(zai2);
                }
            }
            final int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                final zai zai3 = (zai) arrayList.get(i2);
                zabq.zab.remove(zai3);
                zai3.zae(new UnsupportedApiCallException(zaa2));
            }
        }
    }

    public void onConnected(@Nullable final Bundle bundle) {
        if (Looper.myLooper() == zaa.zar.getLooper()) {
            this.zaH();
        } else {
            zaa.zar.post(new zabm(this));
        }
    }

    @WorkerThread
    public void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
        this.zar(connectionResult, null);
    }

    public void onConnectionSuspended(final int i2) {
        if (Looper.myLooper() == zaa.zar.getLooper()) {
            this.zaI(i2);
        } else {
            zaa.zar.post(new zabn(this, i2));
        }
    }

    public boolean zaA() {
        return zac.requiresSignIn();
    }

    
    @WorkerThread
    public boolean zaB() {
        return this.zaO(true);
    }

    public void zaa(final ConnectionResult connectionResult, final Api api, final boolean z) {
        throw null;
    }

    public int zab() {
        return zah;
    }

    
    @WorkerThread
    public int zac() {
        return zam;
    }

    @WorkerThread
    @Nullable
    public ConnectionResult zad() {
        Preconditions.checkHandlerThread(zaa.zar);
        return zal;
    }

    public Api.Client zaf() {
        return zac;
    }

    public Map zah() {
        return zag;
    }

    @WorkerThread
    public void zan() {
        Preconditions.checkHandlerThread(zaa.zar);
        zal = null;
    }

    @WorkerThread
    public void zao() {
        Preconditions.checkHandlerThread(zaa.zar);
        if (!zac.isConnected() && !zac.isConnecting()) {
            try {
                final GoogleApiManager googleApiManager = zaa;
                final int zab2 = googleApiManager.zak.zab(googleApiManager.zai, zac);
                if (0 != zab2) {
                    final ConnectionResult connectionResult = new ConnectionResult(zab2, null);
                    final String name = zac.getClass().getName();
                    final String obj = connectionResult.toString();
                    Log.w("GoogleApiManager", "The service for " + name + " is not available: " + obj);
                    this.zar(connectionResult, null);
                    return;
                }
                final GoogleApiManager googleApiManager2 = zaa;
                final Api.Client client = zac;
                final zabu zabu = new zabu(googleApiManager2, client, zad);
                if (client.requiresSignIn()) {
                    Preconditions.checkNotNull(zai).zae(zabu);
                }
                try {
                    zac.connect(zabu);
                } catch (final SecurityException e2) {
                    this.zar(new ConnectionResult(10), e2);
                }
            } catch (final IllegalStateException e3) {
                this.zar(new ConnectionResult(10), e3);
            }
        }
    }

    @WorkerThread
    public void zap(final zai zai2) {
        Preconditions.checkHandlerThread(zaa.zar);
        if (!zac.isConnected()) {
            zab.add(zai2);
            final ConnectionResult connectionResult = zal;
            if (null == connectionResult || !connectionResult.hasResolution()) {
                this.zao();
            } else {
                this.zar(zal, null);
            }
        } else if (this.zaM(zai2)) {
            this.zaJ();
        } else {
            zab.add(zai2);
        }
    }

    
    @WorkerThread
    public void zaq() {
        zam++;
    }

    @WorkerThread
    public void zar(@NonNull final ConnectionResult connectionResult, @Nullable final Exception exc) {
        Preconditions.checkHandlerThread(zaa.zar);
        final zact zact = zai;
        if (null != zact) {
            zact.zaf();
        }
        this.zan();
        zaa.zak.zac();
        this.zaD(connectionResult);
        if ((zac instanceof zap) && 24 != connectionResult.getErrorCode()) {
            zaa.zaf = true;
            final GoogleApiManager googleApiManager = zaa;
            googleApiManager.zar.sendMessageDelayed(googleApiManager.zar.obtainMessage(19), PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS);
        }
        if (4 == connectionResult.getErrorCode()) {
            this.zaE(GoogleApiManager.zab);
        } else if (zab.isEmpty()) {
            zal = connectionResult;
        } else if (null != exc) {
            Preconditions.checkHandlerThread(zaa.zar);
            this.zaF(null, exc, false);
        } else if (zaa.zas) {
            this.zaF(GoogleApiManager.zaF(zad, connectionResult), null, true);
            if (!zab.isEmpty() && !this.zaN(connectionResult) && !zaa.zaE(connectionResult, zah)) {
                if (18 == connectionResult.getErrorCode()) {
                    zaj = true;
                }
                if (zaj) {
                    final GoogleApiManager googleApiManager2 = zaa;
                    googleApiManager2.zar.sendMessageDelayed(Message.obtain(googleApiManager2.zar, 9, zad), CoroutineLiveDataKt.DEFAULT_TIMEOUT);
                    return;
                }
                this.zaE(GoogleApiManager.zaF(zad, connectionResult));
            }
        } else {
            this.zaE(GoogleApiManager.zaF(zad, connectionResult));
        }
    }

    @WorkerThread
    public void zas(@NonNull final ConnectionResult connectionResult) {
        Preconditions.checkHandlerThread(zaa.zar);
        final Api.Client client = zac;
        final String name = client.getClass().getName();
        final String valueOf = String.valueOf(connectionResult);
        client.disconnect("onSignInFailed for " + name + " with " + valueOf);
        this.zar(connectionResult, null);
    }

    @WorkerThread
    public void zat(final zal zal2) {
        Preconditions.checkHandlerThread(zaa.zar);
        zaf.add(zal2);
    }

    @WorkerThread
    public void zau() {
        Preconditions.checkHandlerThread(zaa.zar);
        if (zaj) {
            this.zao();
        }
    }

    @WorkerThread
    public void zav() {
        Preconditions.checkHandlerThread(zaa.zar);
        this.zaE(GoogleApiManager.zaa);
        zae.zaf();
        for (final ListenerHolder.ListenerKey zah2 : (ListenerHolder.ListenerKey[]) zag.keySet().toArray(new ListenerHolder.ListenerKey[0])) {
            this.zap(new zah(zah2, new TaskCompletionSource()));
        }
        this.zaD(new ConnectionResult(4));
        if (zac.isConnected()) {
            zac.onUserSignOut(new zabp(this));
        }
    }

    @WorkerThread
    public void zaw() {
        final Status status;
        Preconditions.checkHandlerThread(zaa.zar);
        if (zaj) {
            this.zaL();
            final GoogleApiManager googleApiManager = zaa;
            if (18 == googleApiManager.zaj.isGooglePlayServicesAvailable(googleApiManager.zai)) {
                status = new Status(21, "Connection timed out waiting for Google Play services update to complete.");
            } else {
                status = new Status(22, "API failed to connect while resuming due to an unknown error.");
            }
            this.zaE(status);
            zac.disconnect("Timing out connection while resuming.");
        }
    }

    
    public boolean zaz() {
        return zac.isConnected();
    }
}
