package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.errorprone.annotations.CheckReturnValue;
import com.google.errorprone.annotations.RestrictedInheritance;

@RestrictedInheritance(allowedOnPath = ".*java.*/com/google/android/gms/common/testing/.*", explanation = "Sub classing of GMS Core's APIs are restricted to testing fakes.", link = "go/gmscore-restrictedinheritance")
@ShowFirstParty
@KeepForSdk
@CheckReturnValue
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public class GoogleSignatureVerifier {
    private static GoogleSignatureVerifier zza;
    private final Context zzc;
    private volatile String zzd;

    public GoogleSignatureVerifier(@NonNull final Context context) {
        zzc = context.getApplicationContext();
    }

    @NonNull
    @KeepForSdk
    public static GoogleSignatureVerifier getInstance(@NonNull final Context context) {
        Preconditions.checkNotNull(context);
        synchronized (GoogleSignatureVerifier.class) {
            try {
                if (null == zza) {
                    zzn.zze(context);
                    GoogleSignatureVerifier.zza = new GoogleSignatureVerifier(context);
                }
            } catch (final Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return GoogleSignatureVerifier.zza;
    }

    static final zzj zza(final PackageInfo packageInfo, final zzj... zzjArr) {
        final Signature[] signatureArr = packageInfo.signatures;
        if (null != signatureArr) {
            if (1 != signatureArr.length) {
                Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
                return null;
            }
            final zzk zzk = new zzk(packageInfo.signatures[0].toByteArray());
            for (int i2 = 0; i2 < zzjArr.length; i2++) {
                if (zzjArr[i2].equals(zzk)) {
                    return zzjArr[i2];
                }
            }
        }
        return null;
    }

    public static final boolean zzb(@androidx.annotation.NonNull final android.content.pm.PackageInfo r4, final boolean r5) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.GoogleSignatureVerifier.zzb(android.content.pm.PackageInfo, boolean):boolean");
    }

    private final com.google.android.gms.common.zzx zzc(final java.lang.String r6, final boolean r7, final boolean r8) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.GoogleSignatureVerifier.zzc(java.lang.String, boolean, boolean):com.google.android.gms.common.zzx");
    }

    @KeepForSdk
    public boolean isGooglePublicSignedPackage(@NonNull final PackageInfo packageInfo) {
        if (null == packageInfo) {
            return false;
        }
        if (GoogleSignatureVerifier.zzb(packageInfo, false)) {
            return true;
        }
        if (GoogleSignatureVerifier.zzb(packageInfo, true)) {
            if (GooglePlayServicesUtilLight.honorsDebugCertificates(zzc)) {
                return true;
            }
            Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
        }
        return false;
    }

    @ShowFirstParty
    @KeepForSdk
    public boolean isUidGoogleSigned(final int i2) {
        zzx zzx;
        final int length;
        final String[] packagesForUid = zzc.getPackageManager().getPackagesForUid(i2);
        if (null != packagesForUid && 0 != (length = packagesForUid.length)) {
            zzx = null;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    Preconditions.checkNotNull(zzx);
                    break;
                }
                zzx = this.zzc(packagesForUid[i3], false, false);
                if (zzx.zza) {
                    break;
                }
                i3++;
            }
        } else {
            zzx = com.google.android.gms.common.zzx.zzc("no pkgs");
        }
        zzx.zze();
        return zzx.zza;
    }
}
