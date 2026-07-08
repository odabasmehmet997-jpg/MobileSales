package com.google.android.gms.internal.common;

import com.google.errorprone.annotations.DoNotCall;
import org.jspecify.nullness.NullMarked;

import java.util.ListIterator;

@NullMarked
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public abstract class zzak extends zzaj implements ListIterator {
    protected zzak() {
    }

    @DoNotCall
    @Deprecated
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @DoNotCall
    @Deprecated
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
