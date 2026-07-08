package com.google.android.gms.internal.gtm;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public class zzaca<MessageType extends zzacf<MessageType, BuilderType>, BuilderType extends zzaca<MessageType, BuilderType>> extends zzyg<MessageType, BuilderType> {
    protected zzacf zza;
    private final zzacf zzb;

    protected zzaca(final MessageType messagetype) {
        zzb = messagetype;
        if (!messagetype.zzar()) {
            zza = messagetype.zzae();
            return;
        }
        throw new IllegalArgumentException("Default instance must be immutable.");
    }

    private static void zza(final Object obj, final Object obj2) {
        zzadt.zza().zzb(obj.getClass()).zzg(obj, obj2);
    }

    public final zzaca zzA(final zzacf zzacf) {
        if (!zzb.equals(zzacf)) {
            if (!zza.zzar()) {
                this.zzH();
            }
            zzaca.zza(zza, zzacf);
        }
        return this;
    }

    /* renamed from: zzB */
    public final MessageType zzD() {
        final MessageType zzC = this.zzE();
        if (zzacf.zzaq(zzC, true)) {
            return zzC;
        }
        throw new zzael(zzC);
    }

    /* renamed from: zzC */
    public MessageType zzE() {
        if (!zza.zzar()) {
            return zza;
        }
        zza.zzam();
        return zza;
    }

    
    public final void zzG() {
        if (!zza.zzar()) {
            this.zzH();
        }
    }

    
    public void zzH() {
        final zzacf zzae = zzb.zzae();
        zzaca.zza(zzae, zza);
        zza = zzae;
    }

    public final zzadl zzay() {
        return zzb;
    }

    public final boolean zzaz() {
        return zzacf.zzaq(zza, false);
    }

    
    public final zzyg zzw(final zzyh zzyh) {
        this.zzA((zzacf) zzyh);
        return this;
    }

    public final zzadk zzy(final zzzb zzzb, final zzabq zzabq) throws IOException {
        if (!zza.zzar()) {
            this.zzH();
        }
        try {
            zzadt.zza().zzb(zza.getClass()).zzh(zza, zzzc.zzq(zzzb), zzabq);
            return this;
        } catch (final RuntimeException e2) {
            if (e2.getCause() instanceof IOException) {
                throw ((IOException) e2.getCause());
            }
            throw e2;
        }
    }

    /* renamed from: zzz */
    public final zzaca zzv() {
        final zzaca zzaca = (zzaca) zzb.zzb(5, null, null);
        zzaca.zza = this.zzE();
        return zzaca;
    }
}
