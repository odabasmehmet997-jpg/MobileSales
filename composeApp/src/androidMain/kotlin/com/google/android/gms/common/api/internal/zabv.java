package com.google.android.gms.common.api.internal;

import android.os.Looper;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Result;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zabv extends zaag {
    private final GoogleApi zaa;

    public zabv(final GoogleApi googleApi) {
        super("Method is not supported by connectionless client. APIs supporting connectionless client must not call this method.");
        zaa = googleApi;
    }

    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(@NonNull final T t) {
        return zaa.doWrite(t);
    }

    public Looper getLooper() {
        return zaa.getLooper();
    }

    public void zao(final zada zada) {
    }

    public void zap(final zada zada) {
    }
}
