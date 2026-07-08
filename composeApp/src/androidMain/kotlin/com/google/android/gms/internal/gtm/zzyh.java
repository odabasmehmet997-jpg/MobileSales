package com.google.android.gms.internal.gtm;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public abstract class zzyh<MessageType extends zzyh<MessageType, BuilderType>, BuilderType extends zzyg<MessageType, BuilderType>> implements zzadl {
    protected int zzb;

    protected static void zzS(final Iterable iterable, final List list) {
        final byte[] bArr = zzaco.zzb;
        final int size = ((Collection) iterable).size();
        if (list instanceof ArrayList) {
            ((ArrayList) list).ensureCapacity(list.size() + size);
        } else if (list instanceof zzadu) {
            ((zzadu) list).zzf(list.size() + size);
        }
        final int size2 = list.size();
        final List list2 = (List) iterable;
        final int size3 = list2.size();
        int i2 = 0;
        while (i2 < size3) {
            final Object obj = list2.get(i2);
            if (null == obj) {
                final String str = "Element at index " + (list.size() - size2) + " is null.";
                int size4 = list.size();
                while (true) {
                    size4--;
                    if (size4 >= size2) {
                        list.remove(size4);
                    } else {
                        throw new NullPointerException(str);
                    }
                }
            } else {
                list.add(obj);
                i2++;
            }
        }
    }

    
    public int zzQ(final zzadx zzadx) {
        throw null;
    }

    public final zzyx zzR() {
        try {
            final int zzY = this.zzY();
            zzyx zzyx = com.google.android.gms.internal.gtm.zzyx.zzb;
            final byte[] bArr = new byte[zzY];
            final zzze zzze = new zzze(bArr, 0, zzY);
            this.zzax(zzze);
            if (0 == zzze.zzb()) {
                return new zzyv(bArr);
            }
            throw new IllegalStateException("Did not write as much data as expected.");
        } catch (final IOException e2) {
            final String name = this.getClass().getName();
            throw new RuntimeException("Serializing " + name + " to a ByteString threw an IOException (should never happen).", e2);
        }
    }

    public final void zzT(final OutputStream outputStream) throws IOException {
        int zzY = this.zzY();
        final int i2 = zzzi.r8clinit;
        if (4096 < zzY) {
            zzY = 4096;
        }
        final zzzg zzzg = new zzzg(outputStream, zzY);
        this.zzax(zzzg);
        zzzg.zzI();
    }
}
