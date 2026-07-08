package com.google.android.gms.internal.gtm;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public abstract class zzyg<MessageType extends zzyh<MessageType, BuilderType>, BuilderType extends zzyg<MessageType, BuilderType>> implements zzadk {
    /* renamed from: zzv */
    public abstract zzyg clone();

    
    public abstract zzyg zzw(zzyh zzyh);

    public final zzadk zzx(final zzadl zzadl) {
        if (this.zzay().getClass().isInstance(zzadl)) {
            return this.zzw((zzyh) zzadl);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }

    public zzadk zzy(final zzzb zzzb, final zzabq zzabq) throws IOException {
        throw null;
    }
}
