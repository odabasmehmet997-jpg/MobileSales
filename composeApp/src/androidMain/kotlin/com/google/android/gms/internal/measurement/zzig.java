package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
public abstract class zzig<MessageType extends zzih<MessageType, BuilderType>, BuilderType extends zzig<MessageType, BuilderType>> implements zzlf {
    /* renamed from: zzao */
    public abstract zzig clone();

    
    public abstract zzig zzap(zzih zzih);

    public zzig zzaq(byte[] bArr, int i2, int i3) throws zzkj {
        throw null;
    }

    public zzig zzar(byte[] bArr, int i2, int i3, zzjl zzjl) throws zzkj {
        throw null;
    }

    public final zzlf zzas(zzlg zzlg) {
        if (zzbJ().getClass().isInstance(zzlg)) {
            return zzap((zzih) zzlg);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }

    public final zzlf zzat(byte[] bArr) throws zzkj {
        return zzaq(bArr, 0, bArr.length);
    }

    public final zzlf zzau(byte[] bArr, zzjl zzjl) throws zzkj {
        return zzar(bArr, 0, bArr.length, zzjl);
    }
}
