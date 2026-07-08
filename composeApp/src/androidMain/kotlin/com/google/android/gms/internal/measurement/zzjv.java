package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
public class zzjv<MessageType extends zzjz<MessageType, BuilderType>, BuilderType extends zzjv<MessageType, BuilderType>> extends zzig<MessageType, BuilderType> {
    protected zzjz zza;
    protected boolean zzb;
    private final zzjz zzc;

    protected zzjv(MessageType messagetype) {
        this.zzc = messagetype;
        this.zza = (zzjz) messagetype.zzl(4, null, null);
    }

    private static final void zza(zzjz zzjz, zzjz zzjz2) {
        zzlo.zza().zzb(zzjz.getClass()).zzg(zzjz, zzjz2);
    }

    
    public void zzaC() {
        zzjz zzjz = (zzjz) this.zza.zzl(4, null, null);
        zza(zzjz, this.zza);
        this.zza = zzjz;
    }

    
    public final zzig zzap(zzih zzih) {
        zzaw((zzjz) zzih);
        return this;
    }

    public final zzig zzaq(byte[] bArr, int i2, int i3) throws zzkj {
        zzax(bArr, 0, i3, zzjl.zza());
        return this;
    }

    public final zzig zzar(byte[] bArr, int i2, int i3, zzjl zzjl) throws zzkj {
        zzax(bArr, 0, i3, zzjl);
        return this;
    }

    /* renamed from: zzav */
    public final zzjv zzao() {
        zzjv zzjv = (zzjv) this.zzc.zzl(5, null, null);
        zzjv.zzaw(zzaA());
        return zzjv;
    }

    public final zzjv zzaw(zzjz zzjz) {
        if (this.zzb) {
            zzaC();
            this.zzb = false;
        }
        zza(this.zza, zzjz);
        return this;
    }

    public final zzjv zzax(byte[] bArr, int i2, int i3, zzjl zzjl) throws zzkj {
        if (this.zzb) {
            zzaC();
            this.zzb = false;
        }
        try {
            zzlo.zza().zzb(this.zza.getClass()).zzh(this.zza, bArr, 0, i3, new zzik(zzjl));
            return this;
        } catch (zzkj e2) {
            throw e2;
        } catch (IndexOutOfBoundsException unused) {
            throw zzkj.zzf();
        } catch (IOException e3) {
            throw new RuntimeException("Reading from byte array should not throw IOException.", e3);
        }
    }

    public final MessageType zzay() {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjv.zzay():com.google.android.gms.internal.measurement.zzjz");
    }

    public MessageType zzaA() {
        if (this.zzb) {
            return this.zza;
        }
        zzjz zzjz = this.zza;
        zzlo.zza().zzb(zzjz.getClass()).zzf(zzjz);
        this.zzb = true;
        return this.zza;
    }

    public final zzlg zzbJ() {
        return this.zzc;
    }
}
