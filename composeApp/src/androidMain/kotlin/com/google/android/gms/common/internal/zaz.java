package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.view.View;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zaz extends RemoteCreator {
    private static final zaz zaa = new zaz();

    private zaz() {
        super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
    }

    public static View zaa(Context context, int i2, int i3) throws RemoteCreator.RemoteCreatorException {
        zaz zaz = zaa;
        try {
            zax zax = new zax(1, i2, i3, null);
            return ObjectWrapper.unwrap(((zam) zaz.getRemoteCreatorInstance(context)).zae(ObjectWrapper.wrap(context), zax));
        } catch (Exception e2) {
            throw new RemoteCreator.RemoteCreatorException("Could not get button with size " + i2 + " and color " + i3, e2);
        }
    }

    public Object getRemoteCreator(IBinder iBinder) {
        if (null == iBinder) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.ISignInButtonCreator");
        return queryLocalInterface instanceof zam ? (zam) queryLocalInterface : new zam(iBinder);
    }
}
