package com.google.android.gms.internal.common;

import com.google.errorprone.annotations.DoNotCall;
import org.jspecify.nullness.NullMarked;

import java.util.Iterator;

@NullMarked
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public abstract class zzaj implements Iterator {
    protected zzaj() {
    }

    @DoNotCall
    @Deprecated
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
