package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.wrappers.Wrappers;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@ShowFirstParty
@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public enum AndroidUtilsLight {
    ;

    @Nullable
    @KeepForSdk
    @Deprecated
    public static byte[] getPackageCertificateHashBytes(@NonNull final Context context, @NonNull final String str) throws PackageManager.NameNotFoundException {
        final MessageDigest zza;
        final PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(str, 64);
        final Signature[] signatureArr = packageInfo.signatures;
        if (null == signatureArr || 1 != signatureArr.length || null == (zza = zza("SHA1"))) {
            return null;
        }
        return zza.digest(packageInfo.signatures[0].toByteArray());
    }

    @Nullable
    public static MessageDigest zza(@NonNull final String str) {
        int i2 = 0;
        while (2 > i2) {
            try {
                final MessageDigest instance = MessageDigest.getInstance(str);
                if (null != instance) {
                    return instance;
                }
                i2++;
            } catch (final NoSuchAlgorithmException unused) {
            }
        }
        return null;
    }
}
