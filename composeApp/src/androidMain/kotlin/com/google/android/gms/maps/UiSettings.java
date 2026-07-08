package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.IUiSettingsDelegate;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public final class UiSettings {
    private final IUiSettingsDelegate zza;
    UiSettings(final IUiSettingsDelegate iUiSettingsDelegate) {
        zza = iUiSettingsDelegate;
    }
    public void setCompassEnabled(final boolean z) {
        try {
            zza.setCompassEnabled(z);
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }
    public void setZoomControlsEnabled(final boolean z) {
        try {
            zza.setZoomControlsEnabled(z);
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }
}
