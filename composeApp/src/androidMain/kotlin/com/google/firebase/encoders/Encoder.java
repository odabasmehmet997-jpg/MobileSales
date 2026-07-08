package com.google.firebase.encoders;

import androidx.annotation.NonNull;
import java.io.IOException;

/*  INFO: Access modifiers changed from: package-private */
/*  INFO: loaded from: classes2.dex */
public interface Encoder<TValue, TContext> {
    void encode(@NonNull TValue tvalue, @NonNull TContext tcontext) throws IOException;
}
