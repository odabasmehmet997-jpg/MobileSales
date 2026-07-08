package com.google.android.gms.internal.gtm;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzabs extends zzabr {
    zzabs() {
    }

    
    public void zza(final Object obj) {
        ((zzacc) obj).zza.zzi();
    }

    
    public void zzb(final zzadw zzadw, final Object obj, final zzabq zzabq, final zzabv zzabv) throws IOException {
        final zzace zzace = (zzace) obj;
        zzabv.zzk(zzace.zzd, zzadw.zzs(zzace.zzc.getClass(), zzabq));
    }

    
    public void zzc(final zzaez zzaez, final Map.Entry entry) throws IOException {
        final zzacd zzacd = (zzacd) entry.getKey();
        if (zzacd.zzd) {
            zzaex zzaex = com.google.android.gms.internal.gtm.zzaex.DOUBLE;
            switch (zzacd.zzc.ordinal()) {
                case 0:
                    zzadz.zzt(zzacd.zzb, (List) entry.getValue(), zzaez, false);
                    return;
                case 1:
                    zzadz.zzx(zzacd.zzb, (List) entry.getValue(), zzaez, false);
                    return;
                case 2:
                    zzadz.zzA(zzacd.zzb, (List) entry.getValue(), zzaez, false);
                    return;
                case 3:
                    zzadz.zzI(zzacd.zzb, (List) entry.getValue(), zzaez, false);
                    return;
                case 4:
                    zzadz.zzz(zzacd.zzb, (List) entry.getValue(), zzaez, false);
                    return;
                case 5:
                    zzadz.zzw(zzacd.zzb, (List) entry.getValue(), zzaez, false);
                    return;
                case 6:
                    zzadz.zzv(zzacd.zzb, (List) entry.getValue(), zzaez, false);
                    return;
                case 7:
                    zzadz.zzr(zzacd.zzb, (List) entry.getValue(), zzaez, false);
                    return;
                case 8:
                    zzadz.zzG(zzacd.zzb, (List) entry.getValue(), zzaez);
                    return;
                case 9:
                    final List list = (List) entry.getValue();
                    if (null != list && !list.isEmpty()) {
                        zzadz.zzy(zzacd.zzb, (List) entry.getValue(), zzaez, zzadt.zza().zzb(list.get(0).getClass()));
                        return;
                    }
                    return;
                case 10:
                    final List list2 = (List) entry.getValue();
                    if (null != list2 && !list2.isEmpty()) {
                        zzadz.zzB(zzacd.zzb, (List) entry.getValue(), zzaez, zzadt.zza().zzb(list2.get(0).getClass()));
                        return;
                    }
                    return;
                case 11:
                    zzadz.zzs(zzacd.zzb, (List) entry.getValue(), zzaez);
                    return;
                case 12:
                    zzadz.zzH(zzacd.zzb, (List) entry.getValue(), zzaez, false);
                    return;
                case 13:
                    zzadz.zzz(zzacd.zzb, (List) entry.getValue(), zzaez, false);
                    return;
                case 14:
                    zzadz.zzC(zzacd.zzb, (List) entry.getValue(), zzaez, false);
                    return;
                case 15:
                    zzadz.zzD(zzacd.zzb, (List) entry.getValue(), zzaez, false);
                    return;
                case 16:
                    zzadz.zzE(zzacd.zzb, (List) entry.getValue(), zzaez, false);
                    return;
                case 17:
                    zzadz.zzF(zzacd.zzb, (List) entry.getValue(), zzaez, false);
                    return;
                default:
            }
        } else {
            final zzaex zzaex2 = zzaex.DOUBLE;
            switch (zzacd.zzc.ordinal()) {
                case 0:
                    zzaez.zzf(zzacd.zzb, ((Double) entry.getValue()).doubleValue());
                    return;
                case 1:
                    zzaez.zzo(zzacd.zzb, ((Float) entry.getValue()).floatValue());
                    return;
                case 2:
                    zzaez.zzt(zzacd.zzb, ((Long) entry.getValue()).longValue());
                    return;
                case 3:
                    zzaez.zzK(zzacd.zzb, ((Long) entry.getValue()).longValue());
                    return;
                case 4:
                    zzaez.zzr(zzacd.zzb, ((Integer) entry.getValue()).intValue());
                    return;
                case 5:
                    zzaez.zzm(zzacd.zzb, ((Long) entry.getValue()).longValue());
                    return;
                case 6:
                    zzaez.zzk(zzacd.zzb, ((Integer) entry.getValue()).intValue());
                    return;
                case 7:
                    zzaez.zzb(zzacd.zzb, ((Boolean) entry.getValue()).booleanValue());
                    return;
                case 8:
                    zzaez.zzG(zzacd.zzb, (String) entry.getValue());
                    return;
                case 9:
                    zzaez.zzq(zzacd.zzb, entry.getValue(), zzadt.zza().zzb(entry.getValue().getClass()));
                    return;
                case 10:
                    zzaez.zzv(zzacd.zzb, entry.getValue(), zzadt.zza().zzb(entry.getValue().getClass()));
                    return;
                case 11:
                    zzaez.zzd(zzacd.zzb, (zzyx) entry.getValue());
                    return;
                case 12:
                    zzaez.zzI(zzacd.zzb, ((Integer) entry.getValue()).intValue());
                    return;
                case 13:
                    zzaez.zzr(zzacd.zzb, ((Integer) entry.getValue()).intValue());
                    return;
                case 14:
                    zzaez.zzx(zzacd.zzb, ((Integer) entry.getValue()).intValue());
                    return;
                case 15:
                    zzaez.zzz(zzacd.zzb, ((Long) entry.getValue()).longValue());
                    return;
                case 16:
                    zzaez.zzB(zzacd.zzb, ((Integer) entry.getValue()).intValue());
                    return;
                case 17:
                    zzaez.zzD(zzacd.zzb, ((Long) entry.getValue()).longValue());
                    return;
                default:
            }
        }
    }
}
