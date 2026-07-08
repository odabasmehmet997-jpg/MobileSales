package com.google.android.gms.common.api.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.api.GoogleApiClient;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class zaag extends GoogleApiClient {
    private final String zaa = "Method is not supported by connectionless client. APIs supporting connectionless client must not call this method.";

    public zaag(final String str) {
    }

    public final void connect() {
        throw new UnsupportedOperationException(zaa);
    }

    public final void disconnect() {
        throw new UnsupportedOperationException(zaa);
    }

    public final void dump(final String str, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] strArr) {
        throw new UnsupportedOperationException(zaa);
    }

    public final void unregisterConnectionFailedListener(@NonNull final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        throw new UnsupportedOperationException(zaa);
    }
}
