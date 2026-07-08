package com.google.android.gms.maps.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public enum zzcc {
    ;
    private static final String zza = "zzcc";
    @SuppressLint("StaticFieldLeak")
    @Nullable
    private static Context zzb;
    private static zzf zzc;

    public static zzf zza(Context context, @Nullable MapsInitializer.Renderer renderer) throws GooglePlayServicesNotAvailableException {
        Preconditions.checkNotNull(context);
        Log.d(zza, "preferredRenderer: " + renderer);
        zzf zzf = zzc;
        if (null != zzf) {
            return zzf;
        }
        int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context, 13400000);
        if (0 == isGooglePlayServicesAvailable) {
            zzf zzd = zzd(context, renderer);
            zzc = zzd;
            try {
                if (2 == zzd.zzd()) {
                    try {
                        zzc.zzm(ObjectWrapper.wrap(zzc(context, renderer)));
                    } catch (UnsatisfiedLinkError unused) {
                        Log.w(zza, "Caught UnsatisfiedLinkError attempting to load the LATEST renderer's native library. Attempting to use the LEGACY renderer instead.");
                        zzb = null;
                        zzc = zzd(context, MapsInitializer.Renderer.LEGACY);
                    } catch (RemoteException e2) {
                        throw new RuntimeRemoteException(e2);
                    }
                }
                try {
                    zzf zzf2 = zzc;
                    Context zzc2 = zzc(context, renderer);
                    Objects.requireNonNull(zzc2);
                    zzf2.zzk(ObjectWrapper.wrap(zzc2.getResources()), 19000000);
                    return zzc;
                } catch (RemoteException e3) {
                    throw new RuntimeRemoteException(e3);
                }
            } catch (RemoteException e4) {
                throw new RuntimeRemoteException(e4);
            }
        } else {
            throw new GooglePlayServicesNotAvailableException(isGooglePlayServicesAvailable);
        }
    }

    private static Context zzb(Exception exc, Context context) {
        Log.e(zza, "Failed to load maps module, use pre-Chimera", exc);
        return GooglePlayServicesUtil.getRemoteContext(context);
    }

    private static android.content.Context zzc(android.content.Context r4, @androidx.annotation.Nullable com.google.android.gms.maps.MapsInitializer.Renderer r5) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zzcc.zzc(android.content.Context, com.google.android.gms.maps.MapsInitializerRenderer):android.content.Context");
    }

    private static zzf zzd(Context context, @Nullable MapsInitializer.Renderer renderer) {
        Log.i(zza, "Making Creator dynamically");
        try {
            IBinder iBinder = (IBinder) zze(Preconditions.checkNotNull(zzc(context, renderer).getClassLoader()).loadClass("com.google.android.gms.maps.internal.CreatorImpl"));
            if (null == iBinder) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.ICreator");
            if (queryLocalInterface instanceof zzf) {
                return (zzf) queryLocalInterface;
            }
            return new zze(iBinder);
        } catch (ClassNotFoundException e2) {
            throw new IllegalStateException("Unable to find dynamic class com.google.android.gms.maps.internal.CreatorImpl", e2);
        }
    }

    private static Object zze(Class cls) {
        try {
            return cls.newInstance();
        } catch (InstantiationException e2) {
            throw new IllegalStateException("Unable to instantiate the dynamic class " + cls.getName(), e2);
        } catch (IllegalAccessException e3) {
            throw new IllegalStateException("Unable to call the default constructor of " + cls.getName(), e3);
        }
    }
}
