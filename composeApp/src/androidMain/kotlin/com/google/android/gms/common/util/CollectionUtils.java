package com.google.android.gms.common.util;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.errorprone.annotations.InlineMe;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public enum CollectionUtils {
    ;

    @InlineMe
    @NonNull
    @KeepForSdk
    @Deprecated
    public static <T> List<T> listOf(@NonNull final T t) {
        return Collections.singletonList(t);
    }

    @NonNull
    @KeepForSdk
    @Deprecated
    public static <T> List<T> listOf(@NonNull final T... tArr) {
        final int length = tArr.length;
        if (0 == length) {
            return Collections.emptyList();
        }
        if (1 != length) {
            return Collections.unmodifiableList(Arrays.asList(tArr));
        }
        return Collections.singletonList(tArr[0]);
    }
}
