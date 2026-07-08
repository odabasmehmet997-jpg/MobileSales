package com.google.android.gms.common.api.internal;

import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.api.Status;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zadc {
    public static final Status zaa = new Status(8, "The connection to Google Play services was lost");
    @VisibleForTesting
    final Set zab = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));
    private final zadb zac = new zadb(this);

    
    public void zaa(final BasePendingResult basePendingResult) {
        zab.add(basePendingResult);
        basePendingResult.zan(zac);
    }

    public void zab() {
        for (final BasePendingResult basePendingResult : (BasePendingResult[]) zab.toArray(new BasePendingResult[0])) {
            basePendingResult.zan(null);
            if (basePendingResult.zam()) {
                zab.remove(basePendingResult);
            }
        }
    }
}
