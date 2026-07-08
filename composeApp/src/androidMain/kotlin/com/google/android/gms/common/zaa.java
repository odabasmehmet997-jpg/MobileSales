package com.google.android.gms.common;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zaa implements SuccessContinuation {
    public static final zaa zaa = new zaa();

    private zaa() {
    }

    public Task then(final Object obj) {
        final Map map = (Map) obj;
        final int i2 = GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        return Tasks.forResult(null);
    }
}
