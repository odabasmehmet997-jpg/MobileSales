package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.common.internal.zab;
import com.google.android.gms.common.internal.zav;
import com.google.android.gms.signin.internal.zak;
import com.google.android.gms.signin.zae;

import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zaaw implements zabf {
    
    public final zabi zaa;
    
    public final Lock zab;
    
    public final Context zac;
    
    public final GoogleApiAvailabilityLight zad;
    @Nullable
    private ConnectionResult zae;
    private int zaf;
    private int zag;
    private int zah;
    private final Bundle zai = new Bundle();
    private final Set zaj = new HashSet();
    
    @Nullable
    public zae zak;
    private boolean zal;
    
    public boolean zam;
    private boolean zan;
    
    @Nullable
    public IAccountAccessor zao;
    private boolean zap;
    private boolean zaq;
    
    @Nullable
    public final ClientSettings zar;
    private final Map zas;
    @Nullable
    private final Api.AbstractClientBuilder zat;
    private final ArrayList zau = new ArrayList();

    public zaaw(final zabi zabi, @Nullable final ClientSettings clientSettings, final Map map, final GoogleApiAvailabilityLight googleApiAvailabilityLight, @Nullable final Api.AbstractClientBuilder abstractClientBuilder, final Lock lock, final Context context) {
        zaa = zabi;
        zar = clientSettings;
        zas = map;
        zad = googleApiAvailabilityLight;
        zat = abstractClientBuilder;
        zab = lock;
        zac = context;
    }

    
    
    public void zaA() {
        zam = false;
        zaa.zag.zad = Collections.emptySet();
        for (final Object anyClientKey : zaj) {
            if (!zaa.zab.containsKey(anyClientKey)) {
                final zabi zabi = zaa;
                zabi.zab.put(anyClientKey, new ConnectionResult(17, null));
            }
        }
    }

    
    private void zaB(final boolean z) {
        final zae zae2 = zak;
        if (null != zae2) {
            if (zae2.isConnected() && z) {
                zae2.zaa();
            }
            zae2.disconnect();
            final ClientSettings clientSettings = Preconditions.checkNotNull(zar);
            zao = null;
        }
    }

    
    private void zaC() {
        final Bundle bundle;
        zaa.zai();
        zabj.zaa().execute(new zaak(this));
        final zae zae2 = zak;
        if (null != zae2) {
            if (zap) {
                zae2.zac(Preconditions.checkNotNull(zao), zaq);
            }
            this.zaB(false);
        }
        for (final Object anyClientKey : zaa.zab.keySet()) {
            Preconditions.checkNotNull((Api.Client) zaa.zaa.get(anyClientKey)).disconnect();
        }
        if (zai.isEmpty()) {
            bundle = null;
        } else {
            bundle = zai;
        }
        zaa.zah.zab(bundle);
    }

    
    
    public void zaD(final ConnectionResult connectionResult) {
        this.zaz();
        this.zaB(!connectionResult.hasResolution());
        zaa.zak(connectionResult);
        zaa.zah.zaa(connectionResult);
    }

    
    
    public void zaE(final ConnectionResult connectionResult, final Api api, final boolean z) {
        final int priority = api.zac().getPriority();
        if ((!z || connectionResult.hasResolution() || null != this.zad.getErrorResolutionIntent(connectionResult.getErrorCode())) && (null == this.zae || priority < zaf)) {
            zae = connectionResult;
            zaf = priority;
        }
        final zabi zabi = zaa;
        zabi.zab.put(api.zab(), connectionResult);
    }

    
    
    public void zaF() {
        if (0 == this.zah) {
            if (!zam || zan) {
                final ArrayList arrayList = new ArrayList();
                zag = 1;
                zah = zaa.zaa.size();
                for (final Object anyClientKey : zaa.zaa.keySet()) {
                    if (!zaa.zab.containsKey(anyClientKey)) {
                        arrayList.add(zaa.zaa.get(anyClientKey));
                    } else if (this.zaH()) {
                        this.zaC();
                    }
                }
                if (!arrayList.isEmpty()) {
                    zau.add(zabj.zaa().submit(new zaap(this, arrayList)));
                }
            }
        }
    }

    private boolean zaG(final int i2) {
        if (zag == i2) {
            return true;
        }
        Log.w("GACConnecting", zaa.zag.zaf());
        Log.w("GACConnecting", "Unexpected callback in " + zaa.zag.zaf());
        final int i3 = zah;
        Log.w("GACConnecting", "mRemainingConnections=" + i3);
        final String zaJ = zaaw.zaJ(zag);
        Log.e("GACConnecting", "GoogleApiClient connecting is in step " + zaJ + " but received callback for step " + zaaw.zaJ(i2), new Exception());
        this.zaD(new ConnectionResult(8, null));
        return false;
    }
    
    private boolean zaH() {
        final int i2 = zah - 1;
        zah = i2;
        if (0 < i2) {
            return false;
        }
        if (0 > i2) {
            Log.w("GACConnecting", zaa.zag.zaf());
            Log.wtf("GACConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
            this.zaD(new ConnectionResult(8, null));
            return false;
        }
        final ConnectionResult connectionResult = zae;
        if (null == connectionResult) {
            return true;
        }
        zaa.zaf = zaf;
        this.zaD(connectionResult);
        return false;
    }
    
    public boolean zaI(final ConnectionResult connectionResult) {
        return zal && !connectionResult.hasResolution();
    }

    private static String zaJ(final int i2) {
        return 0 != i2 ? "STEP_GETTING_REMOTE_SERVICE" : "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
    }

    static Set zao(final zaaw zaaw) {
        final ClientSettings clientSettings = zaaw.zar;
        if (null == clientSettings) {
            return Collections.emptySet();
        }
        final HashSet hashSet = new HashSet(clientSettings.getRequiredScopes());
        final Map zad2 = zaaw.zar.zad();
        for (final Object api : zad2.keySet()) {
            final zabi zabi = zaaw.zaa;
            try {
                if (!zabi.zab.containsKey(api.wait())) {
                    hashSet.addAll(((zab) zad2.get(api)).zaa());
                }
            } catch (final InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return hashSet;
    }

    static  void zar(final zaaw zaaw, final zak zak2) {
        if (zaaw.zaG(0)) {
            final ConnectionResult zaa2 = zak2.zaa();
            if (zaa2.isSuccess()) {
                final zav zav = Preconditions.checkNotNull(zak2.zab());
                final ConnectionResult zaa3 = zav.zaa();
                if (!zaa3.isSuccess()) {
                    final String valueOf = String.valueOf(zaa3);
                    Log.wtf("GACConnecting", "Sign-in succeeded with resolve account failure: " + valueOf, new Exception());
                    zaaw.zaD(zaa3);
                    return;
                }
                zaaw.zan = true;
                zaaw.zao = Preconditions.checkNotNull(zav.zab());
                zaaw.zap = zav.zac();
                zaaw.zaq = zav.zad();
                zaaw.zaF();
            } else if (zaaw.zaI(zaa2)) {
                zaaw.zaA();
                zaaw.zaF();
            } else {
                zaaw.zaD(zaa2);
            }
        }
    }

    private void zaz() {
        final ArrayList arrayList = zau;
        final int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((Future) arrayList.get(i2)).cancel(true);
        }
        zau.clear();
    }

    public BaseImplementation.ApiMethodImpl zab(final BaseImplementation.ApiMethodImpl apiMethodImpl) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    public void zad() {
        zaa.zab.clear();
        zam = false;
        zae = null;
        zag = 0;
        zal = true;
        zan = false;
        zap = false;
        final HashMap hashMap = new HashMap();
        boolean z = false;
        for (final Object api : zas.keySet()) {
            Api.Client client = null;
            try {
                client = Preconditions.checkNotNull((Api.Client) zaa.zaa.get(api.wait()));
            } catch (final InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                z |= 1 == api.wait();
            } catch (final InterruptedException e) {
                throw new RuntimeException(e);
            }
            final boolean booleanValue = ((Boolean) zas.get(api)).booleanValue();
            if (client.requiresSignIn()) {
                zam = true;
                if (booleanValue) {
                    try {
                        zaj.add(api.wait());
                    } catch (final InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    zal = false;
                }
            }
            hashMap.put(client, new zaal(this, (Api) api, booleanValue));
        }
        if (z) {
            zam = false;
        }
        if (zam) {
            Preconditions.checkNotNull(zar);
            Preconditions.checkNotNull(zat);
            zar.zae(Integer.valueOf(System.identityHashCode(zaa.zag)));
            final zaat zaat = new zaat(this, null);
            final Api.AbstractClientBuilder abstractClientBuilder = zat;
            final Context context = zac;
            final zabi zabi = zaa;
            final ClientSettings clientSettings = zar;
            zak = (zae) abstractClientBuilder.buildClient(context, zabi.zag.getLooper(), clientSettings, clientSettings.zaa(), zaat, zaat);
        }
        zah = zaa.zaa.size();
        zau.add(zabj.zaa().submit(new zaao(this, hashMap)));
    }

    public void zae() {
    }

    
    public void zag(@Nullable final Bundle bundle) {
        if (this.zaG(1)) {
            if (null != bundle) {
                zai.putAll(bundle);
            }
            if (this.zaH()) {
                this.zaC();
            }
        }
    }

    
    public void zah(final ConnectionResult connectionResult, final Api api, final boolean z) {
        if (this.zaG(1)) {
            this.zaE(connectionResult, api, z);
            if (this.zaH()) {
                this.zaC();
            }
        }
    }

    
    public void zai(final int i2) {
        this.zaD(new ConnectionResult(8, null));
    }

    
    public boolean zaj() {
        this.zaz();
        this.zaB(true);
        zaa.zak(null);
        return true;
    }
}
