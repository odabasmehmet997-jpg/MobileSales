package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzky implements zzls {
    private static final zzle zza = new zzkw();
    private final zzle zzb;

    public zzky() {
        zzle zzle;
        zzju zza2 = zzju.zza();
        try {
            zzle = (zzle) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", (Class[]) null).invoke((Object) null, (Object[]) null);
        } catch (Exception unused) {
            zzle = zza;
        }
        zzkx zzkx = new zzkx(zza2, zzle);
        zzkh.zzf(zzkx, "messageInfoFactory");
        this.zzb = zzkx;
    }

    private static boolean zzb(zzld zzld) {
        return 1 == zzld.zzc();
    }

    public zzlr zza(Class cls) {
        zzlt.zzG(cls);
        zzld zzb2 = this.zzb.zzb(cls);
        final Class<zzjz> cls2 = zzjz.class;
        if (zzb2.zzb()) {
            if (cls2.isAssignableFrom(cls)) {
                return zzlk.zzc(zzlt.zzB(), zzjo.zzb(), zzb2.zza());
            }
            return zzlk.zzc(zzlt.zzz(), zzjo.zza(), zzb2.zza());
        } else if (cls2.isAssignableFrom(cls)) {
            if (zzb(zzb2)) {
                return zzlj.zzk(cls, zzb2, zzlm.zzb(), zzku.zzd(), zzlt.zzB(), zzjo.zzb(), zzlc.zzb());
            }
            return zzlj.zzk(cls, zzb2, zzlm.zzb(), zzku.zzd(), zzlt.zzB(), null, zzlc.zzb());
        } else if (zzb(zzb2)) {
            return zzlj.zzk(cls, zzb2, zzlm.zza(), zzku.zzc(), zzlt.zzz(), zzjo.zza(), zzlc.zza());
        } else {
            return zzlj.zzk(cls, zzb2, zzlm.zza(), zzku.zzc(), zzlt.zzA(), null, zzlc.zza());
        }
    }
}
