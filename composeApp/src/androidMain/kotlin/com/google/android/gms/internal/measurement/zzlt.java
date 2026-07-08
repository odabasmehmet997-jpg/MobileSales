package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
enum zzlt {
    ;
    private static final Class zza;
    private static final zzmi zzb = zzab(false);
    private static final zzmi zzc = zzab(true);
    private static final zzmi zzd = new zzmk();

    static {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        zza = cls;
    }

    public static zzmi zzA() {
        return zzc;
    }

    public static zzmi zzB() {
        return zzd;
    }

    static Object zzC(int i2, List list, zzkd zzkd, Object obj, zzmi zzmi) {
        if (null == zzkd) {
            return obj;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                Integer num = (Integer) list.get(i4);
                int intValue = num.intValue();
                if (zzkd.zza(intValue)) {
                    if (i4 != i3) {
                        list.set(i3, num);
                    }
                    i3++;
                } else {
                    obj = zzD(i2, intValue, obj, zzmi);
                }
            }
            if (i3 != size) {
                list.subList(i3, size).clear();
                return obj;
            }
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = ((Integer) it.next()).intValue();
                if (!zzkd.zza(intValue2)) {
                    obj = zzD(i2, intValue2, obj, zzmi);
                    it.remove();
                }
            }
        }
        return obj;
    }

    static Object zzD(int i2, int i3, Object obj, zzmi zzmi) {
        if (null == obj) {
            obj = zzmi.zze();
        }
        zzmi.zzf(obj, i2, i3);
        return obj;
    }

    static void zzE(zzjm zzjm, Object obj, Object obj2) {
        zzjm.zza(obj2);
        throw null;
    }

    static void zzF(zzmi zzmi, Object obj, Object obj2) {
        zzmi.zzh(obj, zzmi.zzd(zzmi.zzc(obj), zzmi.zzc(obj2)));
    }

    public static void zzG(Class cls) {
        Class cls2;
        if (!zzjz.class.isAssignableFrom(cls) && null != (cls2 = zzlt.zza) && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    static boolean zzH(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (null != obj) {
            return obj.equals(obj2);
        }
        return false;
    }

    static void zzI(zzlb zzlb, Object obj, Object obj2, long j2) {
        zzms.zzs(obj, j2, com.google.android.gms.internal.measurement.zzlb.zzb(zzms.zzf(obj, j2), zzms.zzf(obj2, j2)));
    }

    public static void zzJ(int i2, List list, zzjh zzjh, boolean z) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzjh.zzc(i2, list, z);
        }
    }

    public static void zzK(int i2, List list, zzjh zzjh) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzjh.zze(i2, list);
        }
    }

    public static void zzL(int i2, List list, zzjh zzjh, boolean z) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzjh.zzg(i2, list, z);
        }
    }

    public static void zzM(int i2, List list, zzjh zzjh, boolean z) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzjh.zzj(i2, list, z);
        }
    }

    public static void zzN(int i2, List list, zzjh zzjh, boolean z) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzjh.zzl(i2, list, z);
        }
    }

    public static void zzO(int i2, List list, zzjh zzjh, boolean z) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzjh.zzn(i2, list, z);
        }
    }

    public static void zzP(int i2, List list, zzjh zzjh, boolean z) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzjh.zzp(i2, list, z);
        }
    }

    public static void zzQ(int i2, List list, zzjh zzjh, zzlr zzlr) throws IOException {
        if (null != list && !list.isEmpty()) {
            for (int i3 = 0; i3 < list.size(); i3++) {
                zzjh.zzq(i2, list.get(i3), zzlr);
            }
        }
    }

    public static void zzR(int i2, List list, zzjh zzjh, boolean z) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzjh.zzs(i2, list, z);
        }
    }

    public static void zzS(int i2, List list, zzjh zzjh, boolean z) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzjh.zzu(i2, list, z);
        }
    }

    public static void zzT(int i2, List list, zzjh zzjh, zzlr zzlr) throws IOException {
        if (null != list && !list.isEmpty()) {
            for (int i3 = 0; i3 < list.size(); i3++) {
                zzjh.zzv(i2, list.get(i3), zzlr);
            }
        }
    }

    public static void zzU(int i2, List list, zzjh zzjh, boolean z) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzjh.zzx(i2, list, z);
        }
    }

    public static void zzV(int i2, List list, zzjh zzjh, boolean z) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzjh.zzz(i2, list, z);
        }
    }

    public static void zzW(int i2, List list, zzjh zzjh, boolean z) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzjh.zzB(i2, list, z);
        }
    }

    public static void zzX(int i2, List list, zzjh zzjh, boolean z) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzjh.zzD(i2, list, z);
        }
    }

    public static void zzY(int i2, List list, zzjh zzjh) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzjh.zzG(i2, list);
        }
    }

    public static void zzZ(int i2, List list, zzjh zzjh, boolean z) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzjh.zzI(i2, list, z);
        }
    }

    static int zza(int i2, List list, boolean z) {
        int size = list.size();
        if (0 == size) {
            return 0;
        }
        return size * (zzjg.zzA(i2 << 3) + 1);
    }

    public static void zzaa(int i2, List list, zzjh zzjh, boolean z) throws IOException {
        if (null != list && !list.isEmpty()) {
            zzjh.zzK(i2, list, z);
        }
    }

    private static zzmi zzab(boolean z) {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            cls = null;
        }
        if (null == cls) {
            return null;
        }
        try {
            return (zzmi) cls.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable unused2) {
            return null;
        }
    }

    static int zzb(List list) {
        return list.size();
    }

    static int zzc(int i2, List list) {
        int size = list.size();
        if (0 == size) {
            return 0;
        }
        int zzz = size * zzjg.zzz(i2);
        for (int i3 = 0; i3 < list.size(); i3++) {
            zzz += zzjg.zzt((zziy) list.get(i3));
        }
        return zzz;
    }

    static int zzd(int i2, List list, boolean z) {
        int size = list.size();
        if (0 == size) {
            return 0;
        }
        return zze(list) + (size * zzjg.zzz(i2));
    }

    static int zze(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (0 == size) {
            return 0;
        }
        if (list instanceof final zzka zzka) {
            i2 = 0;
            while (i3 < size) {
                i2 += zzjg.zzv(zzka.zze(i3));
                i3++;
            }
        } else {
            int i4 = 0;
            while (i3 < size) {
                i4 = i2 + zzjg.zzv(((Integer) list.get(i3)).intValue());
                i3++;
            }
        }
        return i2;
    }

    static int zzf(int i2, List list, boolean z) {
        int size = list.size();
        if (0 == size) {
            return 0;
        }
        return size * (zzjg.zzA(i2 << 3) + 4);
    }

    static int zzg(List list) {
        return list.size() * 4;
    }

    static int zzh(int i2, List list, boolean z) {
        int size = list.size();
        if (0 == size) {
            return 0;
        }
        return size * (zzjg.zzA(i2 << 3) + 8);
    }

    static int zzi(List list) {
        return list.size() * 8;
    }

    static int zzj(int i2, List list, zzlr zzlr) {
        int size = list.size();
        if (0 == size) {
            return 0;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += zzjg.zzu(i2, (zzlg) list.get(i4), zzlr);
        }
        return i3;
    }

    static int zzk(int i2, List list, boolean z) {
        int size = list.size();
        if (0 == size) {
            return 0;
        }
        return zzl(list) + (size * zzjg.zzz(i2));
    }

    static int zzl(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (0 == size) {
            return 0;
        }
        if (list instanceof final zzka zzka) {
            i2 = 0;
            while (i3 < size) {
                i2 += zzjg.zzv(zzka.zze(i3));
                i3++;
            }
        } else {
            int i4 = 0;
            while (i3 < size) {
                i4 = i2 + zzjg.zzv(((Integer) list.get(i3)).intValue());
                i3++;
            }
        }
        return i2;
    }

    static int zzm(int i2, List list, boolean z) {
        if (0 == list.size()) {
            return 0;
        }
        return zzn(list) + (list.size() * zzjg.zzz(i2));
    }

    static int zzn(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (0 == size) {
            return 0;
        }
        if (list instanceof final zzkv zzkv) {
            i2 = 0;
            while (i3 < size) {
                i2 += zzjg.zzB(zzkv.zza(i3));
                i3++;
            }
        } else {
            int i4 = 0;
            while (i3 < size) {
                i4 = i2 + zzjg.zzB(((Long) list.get(i3)).longValue());
                i3++;
            }
        }
        return i2;
    }

    static int zzo(int i2, Object obj, zzlr zzlr) {
        if (!(obj instanceof zzkm)) {
            return zzjg.zzA(i2 << 3) + zzjg.zzx((zzlg) obj, zzlr);
        }
        int zzA = zzjg.zzA(i2 << 3);
        int zza2 = ((zzkm) obj).zza();
        return zzA + zzjg.zzA(zza2) + zza2;
    }

    static int zzp(int i2, List list, zzlr zzlr) {
        int zzx;
        int size = list.size();
        if (0 == size) {
            return 0;
        }
        int zzz = zzjg.zzz(i2) * size;
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            if (obj instanceof zzkm) {
                zzx = zzjg.zzw((zzkm) obj);
            } else {
                zzx = zzjg.zzx((zzlg) obj, zzlr);
            }
            zzz += zzx;
        }
        return zzz;
    }

    static int zzq(int i2, List list, boolean z) {
        int size = list.size();
        if (0 == size) {
            return 0;
        }
        return zzr(list) + (size * zzjg.zzz(i2));
    }

    static int zzr(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (0 == size) {
            return 0;
        }
        if (list instanceof final zzka zzka) {
            i2 = 0;
            while (i3 < size) {
                int zze = zzka.zze(i3);
                i2 += zzjg.zzA((zze >> 31) ^ (zze + zze));
                i3++;
            }
        } else {
            int i4 = 0;
            while (i3 < size) {
                int intValue = ((Integer) list.get(i3)).intValue();
                i4 = i2 + zzjg.zzA((intValue >> 31) ^ (intValue + intValue));
                i3++;
            }
        }
        return i2;
    }

    static int zzs(int i2, List list, boolean z) {
        int size = list.size();
        if (0 == size) {
            return 0;
        }
        return zzt(list) + (size * zzjg.zzz(i2));
    }

    static int zzt(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (0 == size) {
            return 0;
        }
        if (list instanceof final zzkv zzkv) {
            i2 = 0;
            while (i3 < size) {
                long zza2 = zzkv.zza(i3);
                i2 += zzjg.zzB((zza2 >> 63) ^ (zza2 + zza2));
                i3++;
            }
        } else {
            int i4 = 0;
            while (i3 < size) {
                long longValue = ((Long) list.get(i3)).longValue();
                i4 = i2 + zzjg.zzB((longValue >> 63) ^ (longValue + longValue));
                i3++;
            }
        }
        return i2;
    }

    static int zzu(int i2, List list) {
        int zzy;
        int zzy2;
        int size = list.size();
        int i3 = 0;
        if (0 == size) {
            return 0;
        }
        int zzz = zzjg.zzz(i2) * size;
        if (list instanceof final zzko zzko) {
            while (i3 < size) {
                Object zzf = zzko.zzf(i3);
                if (zzf instanceof zziy) {
                    zzy2 = zzjg.zzt((zziy) zzf);
                } else {
                    zzy2 = zzjg.zzy((String) zzf);
                }
                zzz += zzy2;
                i3++;
            }
        } else {
            while (i3 < size) {
                Object obj = list.get(i3);
                if (obj instanceof zziy) {
                    zzy = zzjg.zzt((zziy) obj);
                } else {
                    zzy = zzjg.zzy((String) obj);
                }
                zzz += zzy;
                i3++;
            }
        }
        return zzz;
    }

    static int zzv(int i2, List list, boolean z) {
        int size = list.size();
        if (0 == size) {
            return 0;
        }
        return zzw(list) + (size * zzjg.zzz(i2));
    }

    static int zzw(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (0 == size) {
            return 0;
        }
        if (list instanceof final zzka zzka) {
            i2 = 0;
            while (i3 < size) {
                i2 += zzjg.zzA(zzka.zze(i3));
                i3++;
            }
        } else {
            int i4 = 0;
            while (i3 < size) {
                i4 = i2 + zzjg.zzA(((Integer) list.get(i3)).intValue());
                i3++;
            }
        }
        return i2;
    }

    static int zzx(int i2, List list, boolean z) {
        int size = list.size();
        if (0 == size) {
            return 0;
        }
        return zzy(list) + (size * zzjg.zzz(i2));
    }

    static int zzy(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (0 == size) {
            return 0;
        }
        if (list instanceof final zzkv zzkv) {
            i2 = 0;
            while (i3 < size) {
                i2 += zzjg.zzB(zzkv.zza(i3));
                i3++;
            }
        } else {
            int i4 = 0;
            while (i3 < size) {
                i4 = i2 + zzjg.zzB(((Long) list.get(i3)).longValue());
                i3++;
            }
        }
        return i2;
    }

    public static zzmi zzz() {
        return zzb;
    }
}
