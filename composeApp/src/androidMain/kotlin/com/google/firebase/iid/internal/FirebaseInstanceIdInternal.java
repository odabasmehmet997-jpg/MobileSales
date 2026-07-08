package com.google.firebase.iid.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.tasks.Task;
import java.io.IOException;

/*  INFO: compiled from: com.google.firebase:firebase-iid-interop@@17.1.0 */
/*  INFO: loaded from: classes2.dex */
@KeepForSdk
public interface FirebaseInstanceIdInternal {

    /*  INFO: compiled from: com.google.firebase:firebase-iid-interop@@17.1.0 */
    @KeepForSdk
    public interface NewTokenListener {
        @KeepForSdk
        void onNewToken(String str);
    }

    @KeepForSdk
    void addNewTokenListener(NewTokenListener newTokenListener);

    @KeepForSdk
    void deleteToken(@NonNull String str, @NonNull String str2) throws IOException;

    @KeepForSdk
    String getId();

    @Nullable
    @KeepForSdk
    String getToken();

    @NonNull
    @KeepForSdk
    Task<String> getTokenTask();
}
