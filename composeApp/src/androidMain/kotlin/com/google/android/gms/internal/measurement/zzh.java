package com.google.android.gms.internal.measurement;

import java.util.*;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public enum zzh {
    ;

    public static double zza(double d2) {
        int i2;
        if (Double.isNaN(d2)) {
            return 0.0d;
        }
        if (Double.isInfinite(d2) || 0.0d == d2 || 0 == i2) {
            return d2;
        }
        return (0 < i2 ? 1 : -1) * Math.floor(Math.abs(d2));
    }

    public static int zzb(double d2) {
        int i2;
        if (Double.isNaN(d2) || Double.isInfinite(d2) || 0.0d == d2) {
            return 0;
        }
        return (int) ((long) (((0 < i2 ? 1 : -1) * Math.floor(Math.abs(d2))) % 4.294967296E9d));
    }

    public static int zzc(zzg zzg) {
        int zzb = zzb(zzg.zzd("runtime.counter").zzh(this.zzd, 3, list).doubleValue() + 1.0d);
        if (1000000 >= zzb) {
            zzg.zzg("runtime.counter", new zzah(Double.valueOf(zzb)));
            return zzb;
        }
        throw new IllegalStateException("Instructions allowed exceeded");
    }

    public static long zzd(double d2) {
        return zzb(d2) & 4294967295L;
    }

    public static zzbl zze(String str) {
        zzbl zzbl = null;
        if (null != str && !str.isEmpty()) {
            zzbl = com.google.android.gms.internal.measurement.zzbl.zza(Integer.parseInt(str));
        }
        if (null != zzbl) {
            return zzbl;
        }
        throw new IllegalArgumentException(String.format("Unsupported commandId %s", str));
    }

    public static Object zzf(zzap zzap) {
        if (com.google.android.gms.internal.measurement.zzap.zzg.equals(zzap)) {
            return null;
        }
        if (com.google.android.gms.internal.measurement.zzap.zzf.equals(zzap)) {
            return "";
        }
        if (zzap instanceof zzam) {
            return zzg((zzam) zzap);
        }
        if (zzap instanceof zzae) {
            ArrayList arrayList = new ArrayList();
            Iterator it = ((zzae) zzap).iterator();
            while (it.hasNext()) {
                Object zzf = zzf((zzap) it.next());
                if (null != zzf) {
                    arrayList.add(zzf);
                }
            }
            return arrayList;
        } else if (!zzap.zzh(this.zzd, 3, list).isNaN()) {
            return zzap.zzh(this.zzd, 3, list);
        } else {
            return zzap.zzi();
        }
    }

    public static Map zzg(zzam zzam) {
        HashMap hashMap = new HashMap();
        for (String str : zzam.zzb()) {
            Object zzf = zzf(zzam.zzf(str));
            if (null != zzf) {
                hashMap.put(str, zzf);
            }
        }
        return hashMap;
    }

    public static void zzh(String str, int i2, List list) {
        if (list.size() != i2) {
            throw new IllegalArgumentException(String.format("%s operation requires %s parameters found %s", str, Integer.valueOf(i2), Integer.valueOf(list.size())));
        }
    }

    public static void zzi(String str, int i2, List list) {
        if (list.size() < i2) {
            throw new IllegalArgumentException(String.format("%s operation requires at least %s parameters found %s", str, Integer.valueOf(i2), Integer.valueOf(list.size())));
        }
    }

    public static void zzj(String str, int i2, List list) {
        if (list.size() > i2) {
            throw new IllegalArgumentException(String.format("%s operation requires at most %s parameters found %s", str, Integer.valueOf(i2), Integer.valueOf(list.size())));
        }
    }

    public static boolean zzk(zzap zzap) {
        if (null == zzap) {
            return false;
        }
        Double zzh = zzap.zzh(this.zzd, 3, list);
        return !zzh.isNaN() && !(0.0d > zzh.doubleValue()) && zzh.equals(Double.valueOf(Math.floor(zzh.doubleValue())));
    }

    public static boolean zzl(zzap zzap, zzap zzap2) {
        if (!zzap.getClass().equals(zzap2.getClass())) {
            return false;
        }
        if ((zzap instanceof zzau) || (zzap instanceof zzan)) {
            return true;
        }
        if (zzap instanceof zzah) {
            if (Double.isNaN(zzap.zzh(this.zzd, 3, list).doubleValue()) || Double.isNaN(zzap2.zzh(this.zzd, 3, list).doubleValue())) {
                return false;
            }
            return zzap.zzh(this.zzd, 3, list).equals(zzap2.zzh(this.zzd, 3, list));
        } else if (zzap instanceof zzat) {
            return zzap.zzi().equals(zzap2.zzi());
        } else {
            if (zzap instanceof zzaf) {
                return zzap.zzg().equals(zzap2.zzg());
            }
            return zzap == zzap2;
        }
    }
}
