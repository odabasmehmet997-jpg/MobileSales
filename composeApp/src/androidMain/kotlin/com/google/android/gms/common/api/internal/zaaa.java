package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.base.zap;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.locks.Lock;

final class zaaa implements zaca {
    private final Context zaa;
    private final zabe zab;
    private final Looper zac;
    
    public final zabi zad;
    
    public final zabi zae;
    private final Map zaf;
    private final Set zag = Collections.newSetFromMap(new WeakHashMap());
    @Nullable
    private final Api.Client zah;
    @Nullable
    private Bundle zai;
    
    @Nullable
    public ConnectionResult zaj;
    
    @Nullable
    public ConnectionResult zak;
    
    public boolean zal;
    
    public final Lock zam;
    
    private int zan;

    private zaaa(final Context context, final zabe zabe, final Lock lock, final Looper looper, final GoogleApiAvailabilityLight googleApiAvailabilityLight, final Map map, final Map map2, final ClientSettings clientSettings, final Api.AbstractClientBuilder abstractClientBuilder, @Nullable final Api.Client client, final ArrayList arrayList, final ArrayList arrayList2, final Map map3, final Map map4) {
        zaa = context;
        zab = zabe;
        zam = lock;
        zac = looper;
        zah = client;
        final Context context2 = context;
        final zabe zabe2 = zabe;
        final Lock lock2 = lock;
        final Looper looper2 = looper;
        final GoogleApiAvailabilityLight googleApiAvailabilityLight2 = googleApiAvailabilityLight;
        final zabi zabi = r3;
        final zabi zabi2 = new zabi(context2, zabe2, lock2, looper2, googleApiAvailabilityLight2, map2, null, map4, null, arrayList2, new zax(this, null));
        zad = zabi;
        zae = new zabi(context2, zabe2, lock2, looper2, googleApiAvailabilityLight2, map, clientSettings, map3, abstractClientBuilder, arrayList, new zaz(this, null));
        final ArrayMap arrayMap = new ArrayMap();
        for (final Api.AnyClientKey put : map2.keySet()) {
            arrayMap.put(put, zad);
        }
        for (final Api.AnyClientKey put2 : map.keySet()) {
            arrayMap.put(put2, zae);
        }
        zaf = Collections.unmodifiableMap(arrayMap);
    }

    
    private void zaB() {
        for (final Object onComplete : zag) {
            onComplete.onComplete();
        }
        zag.clear();
    }

    
    private boolean zaC() {
        final ConnectionResult connectionResult = zak;
        return null != connectionResult && 4 == connectionResult.getErrorCode();
    }

    private boolean zaD(final BaseImplementation.ApiMethodImpl apiMethodImpl) {
        final zabi zabi = (zabi) zaf.get(apiMethodImpl.getClientKey());
        Preconditions.checkNotNull(zabi, "GoogleApiClient is not configured to use the API required for this call.");
        return zabi.equals(zae);
    }

    private static boolean zaE(@Nullable final ConnectionResult connectionResult) {
        return null != connectionResult && connectionResult.isSuccess();
    }

    public static zaaa zag(final Context context, final zabe zabe, final Lock lock, final Looper looper, final GoogleApiAvailabilityLight googleApiAvailabilityLight, final Map map, final ClientSettings clientSettings, final Map map2, final Api.AbstractClientBuilder abstractClientBuilder, final ArrayList arrayList) {
        final Map map3 = map2;
        final ArrayMap arrayMap = new ArrayMap();
        final ArrayMap arrayMap2 = new ArrayMap();
        Api.Client client = null;
        for (final Map.Entry entry : map.entrySet()) {
            final Api.Client client2 = (Api.Client) entry.getValue();
            if (client2.providesSignIn()) {
                client = client2;
            }
            if (client2.requiresSignIn()) {
                arrayMap.put(entry.getKey(), client2);
            } else {
                arrayMap2.put(entry.getKey(), client2);
            }
        }
        Preconditions.checkState(!arrayMap.isEmpty(), "CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
        final ArrayMap arrayMap3 = new ArrayMap();
        final ArrayMap arrayMap4 = new ArrayMap();
        for (  Api api : map2.keySet()) {
            final Api.AnyClientKey zab2 = api.zab();
            if (arrayMap.containsKey(zab2)) {
                arrayMap3.put(api, map3.get(api));
            } else if (arrayMap2.containsKey(zab2)) {
                arrayMap4.put(api, map3.get(api));
            } else {
                throw new IllegalStateException("Each API in the isOptionalMap must have a corresponding client in the clients map.");
            }
        }
        final ArrayList arrayList2 = new ArrayList();
        final ArrayList arrayList3 = new ArrayList();
        final int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            final zat zat = (zat) arrayList.get(i2);
            if (arrayMap3.containsKey(zat.zaa)) {
                arrayList2.add(zat);
            } else if (arrayMap4.containsKey(zat.zaa)) {
                arrayList3.add(zat);
            } else {
                throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the isOptionalMap");
            }
        }
        return new zaaa(context, zabe, lock, looper, googleApiAvailabilityLight, arrayMap, arrayMap2, clientSettings, abstractClientBuilder, client, arrayList2, arrayList3, arrayMap3, arrayMap4);
    }

    static void zan(final zaaa zaaa, final int i2, final boolean z) {
        zaaa.zab.zac(i2, z);
        zaaa.zak = null;
        zaaa.zaj = null;
    }

    static void zao(final zaaa zaaa, final Bundle bundle) {
        final Bundle bundle2 = zaaa.zai;
        if (null == bundle2) {
            zaaa.zai = bundle;
        } else if (null != bundle) {
            bundle2.putAll(bundle);
        }
    }

    static void zap(final zaaa zaaa) {
        final ConnectionResult connectionResult;
        if (zaE(zaaa.zaj)) {
            if (zaE(zaaa.zak) || zaaa.zaC()) {
                final int i2 = zaaa.zan;
                if (1 != i2) {
                    if (2 != i2) {
                        Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new AssertionError());
                        zaaa.zan = 0;
                        return;
                    }
                    Preconditions.checkNotNull(zaaa.zab).zab(zaaa.zai);
                }
                zaaa.zaB();
                zaaa.zan = 0;
                return;
            }
            final ConnectionResult connectionResult2 = zaaa.zak;
            if (null == connectionResult2) {
                return;
            }
            if (1 == zaaa.zan) {
                zaaa.zaB();
                return;
            }
            zaaa.zaA(connectionResult2);
            zaaa.zad.zar();
        } else if (null == zaaa.zaj || !zaE(zaaa.zak)) {
            ConnectionResult connectionResult3 = zaaa.zaj;
            if (null != connectionResult3 && null != (connectionResult = zaaa.zak)) {
                if (zaaa.zae.zaf < zaaa.zad.zaf) {
                    connectionResult3 = connectionResult;
                }
                zaaa.zaA(connectionResult3);
            }
        } else {
            zaaa.zae.zar();
            zaaa.zaA(Preconditions.checkNotNull(zaaa.zaj));
        }
    }
    private PendingIntent zaz() {
        final Api.Client client = zah;
        if (null == client) {
            return null;
        }
        return PendingIntent.getActivity(zaa, System.identityHashCode(zab), client.getSignInIntent(), zap.zaa | 134217728);
    }

    
    public BaseImplementation.ApiMethodImpl zaf(@NonNull final BaseImplementation.ApiMethodImpl apiMethodImpl) {
        if (!this.zaD(apiMethodImpl)) {
            return zad.zaf(apiMethodImpl);
        }
        if (!this.zaC()) {
            return zae.zaf(apiMethodImpl);
        }
        apiMethodImpl.setFailedResult(new Status(4, null, this.zaz()));
        return apiMethodImpl;
    }

    
    public void zaq() {
        zan = 2;
        zal = false;
        zak = null;
        zaj = null;
        zad.zaq();
        zae.zaq();
    } 
    public void zar() {
        zak = null;
        zaj = null;
        zan = 0;
        zad.zar();
        zae.zar();
        this.zaB();
    }

    public void zas(final String str, @Nullable final FileDescriptor fileDescriptor, final PrintWriter printWriter, @Nullable final String[] strArr) {
        printWriter.append(str).append("authClient").println(":");
        zae.zas(str + "  ", fileDescriptor, printWriter, strArr);
        printWriter.append(str).append("anonClient").println(":");
        zad.zas(str + "  ", fileDescriptor, printWriter, strArr);
    }

    
    public void zat() {
        zad.zat();
        zae.zat();
    }

    public boolean zaw() {
        zam.lock();
        try {
            boolean z = zad.zaw() && (zae.zaw() || this.zaC() || 1 == this.zan);
            return z;
        } finally {
            zam.unlock();
        }
    }

    
    private void zaA(final ConnectionResult connectionResult) {
        final int i2 = zan;
        if (1 != i2) {
            if (2 != i2) {
                Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
                zan = 0;
            }
            zab.zaa(connectionResult);
        }
        this.zaB();
        zan = 0;
    }
}
