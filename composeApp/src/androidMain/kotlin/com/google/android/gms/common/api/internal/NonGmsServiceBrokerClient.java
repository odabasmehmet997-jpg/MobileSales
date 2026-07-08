package com.google.android.gms.common.api.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Set;
 
public final class NonGmsServiceBrokerClient implements Api.Client, ServiceConnection { 
    private final String zab; 
    private final String zac; 
    private final ComponentName zad;
    private final Context zae;
    private final ConnectionCallbacks zaf;
    private final Handler zag;
    private final OnConnectionFailedListener zah; 
    private IBinder zai;
    private boolean zaj; 
    private String zak; 
    private String zal; 
    private void zad() {
        if (Thread.currentThread() != zag.getLooper().getThread()) {
            throw new IllegalStateException("This method should only run on the NonGmsServiceBrokerClient's handler thread.");
        }
    } 
    public void connect( final BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        zad();
        String.valueOf(zai);
        if (this.isConnected()) {
            try {
                this.disconnect("connect() called when already connected");
            } catch (final Exception unused) {
            }
        }
        try {
            final Intent intent = new Intent();
            final ComponentName componentName = zad;
            if (componentName != null) {
                intent.setComponent(componentName);
            } else {
                intent.setPackage(zab).setAction(zac);
            }
            final boolean bindService = zae.bindService(intent, this, GmsClientSupervisor.getDefaultBindFlags());
            zaj = bindService;
            if (!bindService) {
                zai = null;
                zah.onConnectionFailed(new ConnectionResult(16));
            }
            String.valueOf(zai);
        } catch (final SecurityException e2) {
            zaj = false;
            zai = null;
            throw e2;
        }
    } 
    public void disconnect() {
        this.zad();
        String.valueOf(zai);
        try {
            zae.unbindService(this);
        } catch (final IllegalArgumentException unused) {
        }
        zaj = false;
        zai = null;
    }
    public void dump( final String str,   final FileDescriptor fileDescriptor,  final PrintWriter printWriter,  final String[] strArr) {
    } 
    public Feature[] getAvailableFeatures() {
        return new Feature[0];
    } 
    public String getEndpointPackageName() {
        final String str = zab;
        if (str != null) {
            return str;
        }
        Preconditions.checkNotNull(zad);
        return zad.getPackageName();
    } 
    public String getLastDisconnectMessage() {
        return zak;
    }
    public int getMinApkVersion() {
        return 0;
    }
    public void getRemoteService( final IAccountAccessor iAccountAccessor, final Set<Scope> set) {
    } 
    public Set<Scope> getScopesForConnectionlessNonSignIn() {
        return Collections.emptySet();
    } 
    public Intent getSignInIntent() {
        return new Intent();
    }
    public boolean isConnected() {
        this.zad();
        return this.zai != null;
    } 
    public boolean isConnecting() {
        this.zad();
        return zaj;
    }
    public void onServiceConnected(final ComponentName componentName, final IBinder iBinder) {
        zag.post(new zacg(this, iBinder));
    }
    public void onServiceDisconnected(final ComponentName componentName) {
        zag.post(new zacf(this));
    }
    public void onUserSignOut(final BaseGmsClient.SignOutCallbacks signOutCallbacks) {
        zag.post(new zacj(this, signOutCallbacks));
    }
    public boolean providesSignIn() {
        return false;
    }
    public boolean requiresGooglePlayServices() {
        return false;
    }
    public boolean requiresSignIn() {
        return false;
    }
    public void zaa(final IBinder iBinder) {
        zaj = false;
        zai = iBinder;
        String.valueOf(iBinder);
        zaf.onConnected(new Bundle());
    }
    public void zab() {
        zaj = false;
        zai = null;
        zaf.onConnectionSuspended(1);
    }
    public void zac( final String str) {
        zal = str;
    } 
    public void disconnect(final String str) {
        this.zad();
        zak = str;
        this.disconnect();
    }
}
