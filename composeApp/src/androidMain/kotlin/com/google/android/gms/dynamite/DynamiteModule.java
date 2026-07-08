package com.google.android.gms.dynamite;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.os.IBinder;
import android.os.IInterface;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import org.kxml2.wap.Wbxml;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public final class DynamiteModule {
    @NonNull
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = new zzi();
    @NonNull
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zzj();
    @NonNull
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = new zzk();
    @NonNull
    @KeepForSdk
    public static final VersionPolicy PREFER_LOCAL = new zzg();
    @NonNull
    @KeepForSdk
    public static final VersionPolicy PREFER_REMOTE = new zzf();
    @NonNull
    @KeepForSdk
    public static final VersionPolicy PREFER_REMOTE_VERSION_NO_FORCE_STAGING = new zzh();
    @NonNull
    public static final VersionPolicy zza = new zzl();
    @Nullable
    @GuardedBy
    private static final Boolean zzb;
    @Nullable
    @GuardedBy
    private static final String zzc;
    @GuardedBy
    private static boolean zzd;
    @GuardedBy
    private static final int zze = -1;
    @Nullable
    @GuardedBy
    private static Boolean zzf;
    private static final ThreadLocal zzg = new ThreadLocal();
    private static final ThreadLocal zzh = new zzd();
    private static final VersionPolicy.IVersions zzi = new zze();
    @Nullable
    @GuardedBy
    private static zzq zzk;
    @Nullable
    @GuardedBy
    private static zzr zzl;
    private final Context zzj;

    @DynamiteApi
    /* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
    public enum DynamiteLoaderClassLoader {
        ;
        @Nullable
        @GuardedBy
        public static ClassLoader sClassLoader;
    }

    @KeepForSdk
    /* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
    public static class LoadingException extends Exception {
        LoadingException(final String str, final zzp zzp) {
            super(str);
        }

        LoadingException(final String str, final Throwable th, final zzp zzp) {
            super(str, th);
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
    public interface VersionPolicy {

        @KeepForSdk
        /* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */ interface IVersions {
            int zza(@NonNull Context context, @NonNull String str);

            int zzb(@NonNull Context context, @NonNull String str, boolean z) throws LoadingException;
        }

        @KeepForSdk
        /* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */ class SelectionResult {
            @KeepForSdk
            public int localVersion;
            @KeepForSdk
            public int remoteVersion;
            @KeepForSdk
            public int selection;
        }

        @NonNull
        @KeepForSdk
        SelectionResult selectModule(@NonNull Context context, @NonNull String str, @NonNull IVersions iVersions) throws LoadingException;
    }

    private DynamiteModule(final Context context) {
        Preconditions.checkNotNull(context);
        zzj = context;
    }

    @KeepForSdk
    public static int getLocalVersion(@NonNull final Context context, @NonNull final String str) {
        try {
            final ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            final Class<?> loadClass = classLoader.loadClass("com.google.android.gms.dynamite.descriptors." + str + ".ModuleDescriptor");
            final Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            final Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (Objects.equal(declaredField.get(null), str)) {
                return declaredField2.getInt(null);
            }
            final String valueOf = String.valueOf(declaredField.get(null));
            Log.e("DynamiteModule", "Module descriptor id '" + valueOf + "' didn't match expected id '" + str + "'");
            return 0;
        } catch (final ClassNotFoundException unused) {
            Log.w("DynamiteModule", "Local module descriptor class for " + str + " not found.");
            return 0;
        } catch (final Exception e2) {
            Log.e("DynamiteModule", "Failed to load module descriptor class: " + e2.getMessage());
            return 0;
        }
    }

    @KeepForSdk
    public static int getRemoteVersion(@NonNull final Context context, @NonNull final String str) {
        return DynamiteModule.zza(context, str, false);
    }

    public static com.google.android.gms.dynamite.DynamiteModule load(@androidx.annotation.NonNull final android.content.Context r18, @androidx.annotation.NonNull final com.google.android.gms.dynamite.DynamiteModule.VersionPolicy r19, @androidx.annotation.NonNull final java.lang.String r20) throws com.google.android.gms.dynamite.DynamiteModule.LoadingException {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.load(android.content.Context, com.google.android.gms.dynamite.DynamiteModuleVersionPolicy, java.lang.String):com.google.android.gms.dynamite.DynamiteModule");
    }

    public static int zza(@androidx.annotation.NonNull final android.content.Context r10, @androidx.annotation.NonNull final java.lang.String r11, final boolean r12) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zza(android.content.Context, java.lang.String, boolean):int");
    }

    private static int zzb(final android.content.Context r8, final java.lang.String r9, final boolean r10, final boolean r11) throws com.google.android.gms.dynamite.DynamiteModule.LoadingException {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zzb(android.content.Context, java.lang.String, boolean, boolean):int");
    }

    private static DynamiteModule zzc(final Context context, final String str) {
        Log.i("DynamiteModule", "Selected local version of " + str);
        return new DynamiteModule(context);
    }

    private static void zzd(final ClassLoader classLoader) throws LoadingException {
        final zzr zzr;
        try {
            final IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor((Class[]) null).newInstance((Object[]) null);
            if (null == iBinder) {
                zzr = null;
            } else {
                final IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                if (queryLocalInterface instanceof zzr) {
                    zzr = (zzr) queryLocalInterface;
                } else {
                    zzr = new zzr(iBinder);
                }
            }
            DynamiteModule.zzl = zzr;
        } catch (final ClassNotFoundException e2) {
            e = e2;
            throw new LoadingException("Failed to instantiate dynamite loader", e, (zzp) null);
        } catch (final IllegalAccessException e3) {
            e = e3;
            throw new LoadingException("Failed to instantiate dynamite loader", e, (zzp) null);
        } catch (final InstantiationException e4) {
            e = e4;
            throw new LoadingException("Failed to instantiate dynamite loader", e, (zzp) null);
        } catch (final InvocationTargetException e5) {
            e = e5;
            throw new LoadingException("Failed to instantiate dynamite loader", e, (zzp) null);
        } catch (final NoSuchMethodException e6) {
            e = e6;
            throw new LoadingException("Failed to instantiate dynamite loader", e, (zzp) null);
        }
    }

    private static boolean zze(final Cursor cursor) {
        final zzn zzn = (zzn) DynamiteModule.zzg.get();
        if (null == zzn || null != zzn.zza) {
            return false;
        }
        zzn.zza = cursor;
        return true;
    }

    @GuardedBy
    private static boolean zzf(final Context context) {
        final ApplicationInfo applicationInfo;
        final Boolean bool = Boolean.TRUE;
        if (bool.equals(null) || bool.equals(DynamiteModule.zzf)) {
            return true;
        }
        boolean z = false;
        if (null == zzf) {
            final ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider("com.google.android.gms.chimera", 0);
            if (0 == GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, 10000000) && null != resolveContentProvider && "com.google.android.gms".equals(resolveContentProvider.packageName)) {
                z = true;
            }
            DynamiteModule.zzf = Boolean.valueOf(z);
            if (z && null != (applicationInfo = resolveContentProvider.applicationInfo) && 0 == (applicationInfo.flags & Wbxml.EXT_T_1)) {
                Log.i("DynamiteModule", "Non-system-image GmsCore APK, forcing V1");
                DynamiteModule.zzd = true;
            }
        }
        if (!z) {
            Log.e("DynamiteModule", "Invalid GmsCore APK, remote loading disabled.");
        }
        return z;
    }

    @Nullable
    private static zzq zzg(final Context context) {
        final zzq zzq;
        synchronized (DynamiteModule.class) {
            final zzq zzq2 = DynamiteModule.zzk;
            if (null != zzq2) {
                return zzq2;
            }
            try {
                final IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                if (null == iBinder) {
                    zzq = null;
                } else {
                    final IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    zzq = queryLocalInterface instanceof zzq ? (zzq) queryLocalInterface : new zzq(iBinder);
                }
                if (null != zzq) {
                    DynamiteModule.zzk = zzq;
                    return zzq;
                }
            } catch (final Exception e2) {
                Log.e("DynamiteModule", "Failed to load IDynamiteLoader from GmsCore: " + e2.getMessage());
            }
        }
        return null;
    }

    public Context getModuleContext() {
        return zzj;
    }

    public IBinder instantiate(ClassLoader classLoader, @NonNull final String str) throws LoadingException {
        try {
            return (IBinder) zzj.getClassLoader().loadClass(str).newInstance();
        } catch (final ClassNotFoundException | IllegalAccessException | InstantiationException e2) {
            throw new LoadingException("Failed to instantiate module class: " + str, e2, null);
        }
    }
}
