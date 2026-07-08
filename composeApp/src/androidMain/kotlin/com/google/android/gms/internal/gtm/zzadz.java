package com.google.android.gms.internal.gtm;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
enum zzadz {
    ;
    public static final int r8clinit = 0;
    private static final zzaem zzb = new zzaeo();

    static {
        final int i2 = zzadt.r8clinit;
    }

    public static void zzA(int i2, List list, zzaez zzaez, boolean z) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzaez.zzu(i2, list, z);
        }
    }

    public static void zzB(int i2, List list, zzaez zzaez, zzadx zzadx) throws IOException {
        if (null != list && !list.isEmpty()) {
            for (int i3 = 0; i3 < list.size(); i3++) {
                zzaez.zzv(i2, list.get(i3), zzadx);
            }
        }
    }

    public static void zzC(int i2, List list, zzaez zzaez, boolean z) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzaez.zzy(i2, list, z);
        }
    }

    public static void zzD(int i2, List list, zzaez zzaez, boolean z) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzaez.zzA(i2, list, z);
        }
    }

    public static void zzE(int i2, List list, zzaez zzaez, boolean z) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzaez.zzC(i2, list, z);
        }
    }

    public static void zzF(int i2, List list, zzaez zzaez, boolean z) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzaez.zzE(i2, list, z);
        }
    }

    public static void zzG(int i2, List list, zzaez zzaez) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzaez.zzH(i2, list);
        }
    }

    public static void zzH(int i2, List list, zzaez zzaez, boolean z) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzaez.zzJ(i2, list, z);
        }
    }

    public static void zzI(int i2, List list, zzaez zzaez, boolean z) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzaez.zzL(i2, list, z);
        }
    }

    static boolean zzJ(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (null != obj) {
            return obj.equals(obj2);
        }
        return false;
    }

    static int zza(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (0 == size) {
            return 0;
        }
        if (list instanceof final zzacg zzacg) {
            i2 = 0;
            while (i3 < size) {
                i2 += zzzi.zzD(zzacg.zze(i3));
                i3++;
            }
        } else {
            int i4 = 0;
            while (i3 < size) {
                i4 = i2 + zzzi.zzD(((Integer) list.get(i3)).intValue());
                i3++;
            }
        }
        return i2;
    }

    static int zzb(int i2, List list, boolean z) {
        int size = list.size();
        if (0 == size) {
            return 0;
        }
        return size * (zzzi.zzC(i2 << 3) + 4);
    }

    static int zzc(List list) {
        return list.size() * 4;
    }

    static int zzd(int i2, List list, boolean z) {
        int size = list.size();
        if (0 == size) {
            return 0;
        }
        return size * (zzzi.zzC(i2 << 3) + 8);
    }

    static int zze(List list) {
        return list.size() * 8;
    }

    static int zzf(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (0 == size) {
            return 0;
        }
        if (list instanceof final zzacg zzacg) {
            i2 = 0;
            while (i3 < size) {
                i2 += zzzi.zzD(zzacg.zze(i3));
                i3++;
            }
        } else {
            int i4 = 0;
            while (i3 < size) {
                i4 = i2 + zzzi.zzD(((Integer) list.get(i3)).intValue());
                i3++;
            }
        }
        return i2;
    }

    static int zzg(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (0 == size) {
            return 0;
        }
        if (list instanceof final zzada zzada) {
            i2 = 0;
            while (i3 < size) {
                i2 += zzzi.zzD(zzada.zze(i3));
                i3++;
            }
        } else {
            int i4 = 0;
            while (i3 < size) {
                i4 = i2 + zzzi.zzD(((Long) list.get(i3)).longValue());
                i3++;
            }
        }
        return i2;
    }

    static int zzh(int i2, Object obj, zzadx zzadx) {
        int i3 = i2 << 3;
        if (!(obj instanceof zzacw)) {
            return zzzi.zzC(i3) + zzzi.zzA((zzadl) obj, zzadx);
        }
        int zzC = zzzi.zzC(i3);
        int zza = ((zzacw) obj).zza();
        return zzC + zzzi.zzC(zza) + zza;
    }

    static int zzi(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (0 == size) {
            return 0;
        }
        if (list instanceof final zzacg zzacg) {
            i2 = 0;
            while (i3 < size) {
                int zze = zzacg.zze(i3);
                i2 += zzzi.zzC((zze >> 31) ^ (zze + zze));
                i3++;
            }
        } else {
            int i4 = 0;
            while (i3 < size) {
                int intValue = ((Integer) list.get(i3)).intValue();
                i4 = i2 + zzzi.zzC((intValue >> 31) ^ (intValue + intValue));
                i3++;
            }
        }
        return i2;
    }

    static int zzj(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (0 == size) {
            return 0;
        }
        if (list instanceof final zzada zzada) {
            i2 = 0;
            while (i3 < size) {
                long zze = zzada.zze(i3);
                i2 += zzzi.zzD((zze >> 63) ^ (zze + zze));
                i3++;
            }
        } else {
            int i4 = 0;
            while (i3 < size) {
                long longValue = ((Long) list.get(i3)).longValue();
                i4 = i2 + zzzi.zzD((longValue >> 63) ^ (longValue + longValue));
                i3++;
            }
        }
        return i2;
    }

    static int zzk(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (0 == size) {
            return 0;
        }
        if (list instanceof final zzacg zzacg) {
            i2 = 0;
            while (i3 < size) {
                i2 += zzzi.zzC(zzacg.zze(i3));
                i3++;
            }
        } else {
            int i4 = 0;
            while (i3 < size) {
                i4 = i2 + zzzi.zzC(((Integer) list.get(i3)).intValue());
                i3++;
            }
        }
        return i2;
    }

    static int zzl(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (0 == size) {
            return 0;
        }
        if (list instanceof final zzada zzada) {
            i2 = 0;
            while (i3 < size) {
                i2 += zzzi.zzD(zzada.zze(i3));
                i3++;
            }
        } else {
            int i4 = 0;
            while (i3 < size) {
                i4 = i2 + zzzi.zzD(((Long) list.get(i3)).longValue());
                i3++;
            }
        }
        return i2;
    }

    public static zzaem zzm() {
        return zzb;
    }

    static Object zzn(Object obj, int i2, List list, zzacj zzacj, Object obj2, zzaem zzaem) {
        if (null == zzacj) {
            return obj2;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                Integer num = (Integer) list.get(i4);
                int intValue = num.intValue();
                if (zzacj.zza(intValue)) {
                    if (i4 != i3) {
                        list.set(i3, num);
                    }
                    i3++;
                } else {
                    obj2 = zzo(obj, i2, intValue, obj2, zzaem);
                }
            }
            if (i3 != size) {
                list.subList(i3, size).clear();
                return obj2;
            }
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = ((Integer) it.next()).intValue();
                if (!zzacj.zza(intValue2)) {
                    obj2 = zzo(obj, i2, intValue2, obj2, zzaem);
                    it.remove();
                }
            }
        }
        return obj2;
    }

    static Object zzo(Object obj, int i2, int i3, Object obj2, zzaem zzaem) {
        if (null == obj2) {
            obj2 = zzaem.zza(obj);
        }
        zzaem.zzh(obj2, i2, i3);
        return obj2;
    }

    static void zzp(zzabr zzabr, Object obj, Object obj2) {
        zzabv zzabv = ((zzacc) obj2).zza;
        if (!zzabv.zza.isEmpty()) {
            ((zzacc) obj).zzU().zzj(zzabv);
        }
    }

    static void zzq(zzaem zzaem, Object obj, Object obj2) {
        zzacf zzacf = (zzacf) obj;
        zzaen zzaen = zzacf.zzc;
        zzaen zzaen2 = ((zzacf) obj2).zzc;
        if (!com.google.android.gms.internal.gtm.zzaen.zzc().equals(zzaen2)) {
            if (com.google.android.gms.internal.gtm.zzaen.zzc().equals(zzaen)) {
                zzaen = com.google.android.gms.internal.gtm.zzaen.zze(zzaen, zzaen2);
            } else {
                zzaen.zzd(zzaen2);
            }
        }
        zzacf.zzc = zzaen;
    }

    public static void zzr(int i2, List list, zzaez zzaez, boolean z) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzaez.zzc(i2, list, z);
        }
    }

    public static void zzs(int i2, List list, zzaez zzaez) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzaez.zze(i2, list);
        }
    }

    public static void zzt(int i2, List list, zzaez zzaez, boolean z) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzaez.zzg(i2, list, z);
        }
    }

    public static void zzu(int i2, List list, zzaez zzaez, boolean z) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzaez.zzj(i2, list, z);
        }
    }

    public static void zzv(int i2, List list, zzaez zzaez, boolean z) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzaez.zzl(i2, list, z);
        }
    }

    public static void zzw(int i2, List list, zzaez zzaez, boolean z) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzaez.zzn(i2, list, z);
        }
    }

    public static void zzx(int i2, List list, zzaez zzaez, boolean z) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzaez.zzp(i2, list, z);
        }
    }

    public static void zzy(int i2, List list, zzaez zzaez, zzadx zzadx) throws IOException {
        if (null != list && !list.isEmpty()) {
            for (int i3 = 0; i3 < list.size(); i3++) {
                ((zzzj) zzaez).zzq(i2, list.get(i3), zzadx);
            }
        }
    }

    public static void zzz(int i2, List list, zzaez zzaez, boolean z) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzaez.zzs(i2, list, z);
        }
    }
}
