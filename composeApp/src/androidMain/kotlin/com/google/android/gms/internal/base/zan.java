package com.google.android.gms.internal.base;

import android.os.Build;
import androidx.annotation.ChecksSdkIntAtLeast;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
enum zan {
    ;

    @ChecksSdkIntAtLeast(api = 33)
    static boolean zaa() {
        return 33 <= Build.VERSION.SDK_INT;
    }
}
