package com.google.android.gms.internal.common;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.jspecify.nullness.NullMarked;

import java.util.Iterator;
import java.util.NoSuchElementException;

@NullMarked
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
abstract class zzj implements Iterator {
    private Object zza;
    private int zzb = 2;

    protected zzj() {
    }

    public final Object next() {
        if (hasNext()) {
            this.zzb = 2;
            Object obj = this.zza;
            this.zza = null;
            return obj;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    
    public abstract Object zza();

    
    @CanIgnoreReturnValue
    public final Object zzb() {
        this.zzb = 3;
        return null;
    }

    public final boolean hasNext() {
        int i2 = this.zzb;
        if (4 != i2) {
            int i3 = i2 - 1;
            if (0 == i2) {
                throw null;
            } else if (0 == i3) {
                return true;
            } else {
                if (2 != i3) {
                    this.zzb = 4;
                    this.zza = zza();
                    if (3 != zzb) {
                        this.zzb = 1;
                        return true;
                    }
                }
                return false;
            }
        } else {
            throw new IllegalStateException();
        }
    }
}
