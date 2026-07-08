package com.google.android.gms.common;

import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
abstract class zzl extends zzj {
    private static final WeakReference zza = new WeakReference(null);
    private WeakReference zzb = zzl.zza;

    zzl(final byte[] bArr) {
        super(bArr);
    }

    
    public abstract byte[] zzb();

    
    public final byte[] zzf() {
        byte[] bArr;
        synchronized (this) {
            try {
                bArr = (byte[]) zzb.get();
                if (null == bArr) {
                    bArr = this.zzb();
                    zzb = new WeakReference(bArr);
                }
            } catch (final Throwable th) {
                throw th;
            }
        }
        return bArr;
    }
}
