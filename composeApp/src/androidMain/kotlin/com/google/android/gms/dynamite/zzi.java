package com.google.android.gms.dynamite;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
final class zzi implements DynamiteModule.VersionPolicy {
    zzi() {
    }

    public DynamiteModule.VersionPolicy.SelectionResult selectModule(final Context context, final String str, final DynamiteModule.VersionPolicy.IVersions iVersions) throws DynamiteModule.LoadingException {
        final DynamiteModule.VersionPolicy.SelectionResult selectionResult = new DynamiteModule.VersionPolicy.SelectionResult();
        selectionResult.localVersion = iVersions.zza(context, str);
        int i2 = 1;
        final int zzb = iVersions.zzb(context, str, true);
        selectionResult.remoteVersion = zzb;
        int i3 = selectionResult.localVersion;
        if (0 == i3) {
            i3 = 0;
            if (0 == zzb) {
                i2 = 0;
                selectionResult.selection = i2;
                return selectionResult;
            }
        }
        if (i3 >= zzb) {
            i2 = -1;
        }
        selectionResult.selection = i2;
        return selectionResult;
    }
}
