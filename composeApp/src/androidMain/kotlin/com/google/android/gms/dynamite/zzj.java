package com.google.android.gms.dynamite;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
final class zzj implements DynamiteModule.VersionPolicy {
    zzj() {
    }

    public DynamiteModule.VersionPolicy.SelectionResult selectModule(final Context context, final String str, final DynamiteModule.VersionPolicy.IVersions iVersions) throws DynamiteModule.LoadingException {
        final int i2;
        final DynamiteModule.VersionPolicy.SelectionResult selectionResult = new DynamiteModule.VersionPolicy.SelectionResult();
        final int zza = iVersions.zza(context, str);
        selectionResult.localVersion = zza;
        int i3 = 1;
        int i4 = 0;
        if (0 != zza) {
            i2 = iVersions.zzb(context, str, false);
            selectionResult.remoteVersion = i2;
        } else {
            i2 = iVersions.zzb(context, str, true);
            selectionResult.remoteVersion = i2;
        }
        final int i5 = selectionResult.localVersion;
        if (0 != i5) {
            i4 = i5;
        } else if (0 == i2) {
            i3 = 0;
            selectionResult.selection = i3;
            return selectionResult;
        }
        if (i4 >= i2) {
            i3 = -1;
        }
        selectionResult.selection = i3;
        return selectionResult;
    }
}
