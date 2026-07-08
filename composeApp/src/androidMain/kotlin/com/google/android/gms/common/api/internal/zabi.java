package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zabi implements zaca, zau {
    final Map zaa;
    final Map zab = new HashMap();
    @Nullable
    final ClientSettings zac;
    final Map zad;
    @Nullable
    final Api.AbstractClientBuilder zae;
    int zaf;
    final zabe zag;
    final zabz zah;
    
    public final Lock zai;
    private final Condition zaj;
    private final Context zak;
    private final GoogleApiAvailabilityLight zal;
    private final zabh zam;
    
    public volatile zabf zan;
    @Nullable
    private ConnectionResult zao;

    public zabi(final Context context, final zabe zabe, final Lock lock, final Looper looper, final GoogleApiAvailabilityLight googleApiAvailabilityLight, final Map map, @Nullable final ClientSettings clientSettings, final Map map2, @Nullable final Api.AbstractClientBuilder abstractClientBuilder, final ArrayList arrayList, final zabz zabz) {
        zak = context;
        zai = lock;
        zal = googleApiAvailabilityLight;
        zaa = map;
        zac = clientSettings;
        zad = map2;
        zae = abstractClientBuilder;
        zag = zabe;
        zah = zabz;
        final int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((zat) arrayList.get(i2)).zaa(this);
        }
        zam = new zabh(this, looper);
        zaj = lock.newCondition();
        zan = new zaax(this);
    }

    public void onConnected(@Nullable final Bundle bundle) {
        zai.lock();
        try {
            zan.zag(bundle);
        } finally {
            zai.unlock();
        }
    }

    public void onConnectionSuspended(final int i2) {
        zai.lock();
        try {
            zan.zai(i2);
        } finally {
            zai.unlock();
        }
    }

    public void zaa(@NonNull final ConnectionResult connectionResult, @NonNull final Api api, final boolean z) {
        zai.lock();
        try {
            zan.zah(connectionResult, api, z);
        } finally {
            zai.unlock();
        }
    }

    @GuardedBy
    public BaseImplementation.ApiMethodImpl zaf(@NonNull final BaseImplementation.ApiMethodImpl apiMethodImpl) {
        apiMethodImpl.zak();
        return zan.zab(apiMethodImpl);
    }

    
    public void zai() {
        zai.lock();
        try {
            zag.zak();
            zan = new zaaj(this);
            zan.zad();
            zaj.signalAll();
        } finally {
            zai.unlock();
        }
    }

    
    public void zaj() {
        zai.lock();
        try {
            zan = new zaaw(this, zac, zad, zal, zae, zai, zak);
            zan.zad();
            zaj.signalAll();
        } finally {
            zai.unlock();
        }
    }

    
    public void zak(@Nullable final ConnectionResult connectionResult) {
        zai.lock();
        try {
            zao = connectionResult;
            zan = new zaax(this);
            zan.zad();
            zaj.signalAll();
        } finally {
            zai.unlock();
        }
    }

    
    public void zal(final zabg zabg) {
        final zabh zabh = zam;
        zabh.sendMessage(zabh.obtainMessage(1, zabg));
    }

    
    public void zam(final RuntimeException runtimeException) {
        final zabh zabh = zam;
        zabh.sendMessage(zabh.obtainMessage(2, runtimeException));
    }

    @GuardedBy
    public void zaq() {
        zan.zae();
    }

    @GuardedBy
    public void zar() {
        if (zan.zaj()) {
            zab.clear();
        }
    }

    public void zas(final String str, @Nullable final FileDescriptor fileDescriptor, final PrintWriter printWriter, @Nullable final String[] strArr) {
        printWriter.append(str).append("mState=").println(zan);
        for (final Api api : zad.keySet()) {
            final String valueOf = String.valueOf(str);
            printWriter.append(str).append(api.zad()).println(":");
            Preconditions.checkNotNull((Api.Client) zaa.get(api.zab())).dump(valueOf + "  ", fileDescriptor, printWriter, strArr);
        }
    }

    @GuardedBy
    public void zat() {
        if (zan instanceof zaaj) {
            ((zaaj) zan).zaf();
        }
    }

    public boolean zaw() {
        return zan instanceof zaaj;
    }
}
