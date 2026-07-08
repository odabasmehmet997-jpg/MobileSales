package com.google.android.gms.tagmanager;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzak;
import com.google.android.gms.internal.gtm.zzap;

import java.util.*;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
public final class zzfp {
    private static final Long zza = Long.valueOf(0);
    private static final Double zzb = new Double(0.0d);
    private static final zzfo zzc = zzfo.zzd(0);
    private static final String zzd;
    private static final Boolean zze = Boolean.FALSE;
    private static final zzap zzf;

    static {
        String str = "";
        zzd = str;
        new ArrayList(0);
        new HashMap();
        zzf = zzb(str);
    }

    public static zzap zza() {
        return zzf;
    }

    public static zzap zzb(Object obj) {
        String str;
        zzak zzg = zzap.zzg();
        zzg.zzt(1);
        if (obj instanceof zzap) {
            return (zzap) obj;
        }
        boolean z = false;
        if (obj instanceof String) {
            zzg.zzt(1);
            zzg.zzs((String) obj);
        } else if (obj instanceof List) {
            zzg.zzt(2);
            List<Object> list = (List) obj;
            ArrayList arrayList = new ArrayList(list.size());
            boolean z2 = false;
            for (Object zzb2 : list) {
                zzap zzb3 = zzb(zzb2);
                zzap zzap = zzf;
                if (zzb3 == zzap) {
                    return zzap;
                }
                z2 = z2 || zzb3.zzN();
                arrayList.add(zzb3);
            }
            zzg.zzj();
            zzg.zzb(arrayList);
            z = z2;
        } else if (obj instanceof Map) {
            zzg.zzt(3);
            Set<Map.Entry> entrySet = ((Map) obj).entrySet();
            ArrayList arrayList2 = new ArrayList(entrySet.size());
            ArrayList arrayList3 = new ArrayList(entrySet.size());
            boolean z3 = false;
            for (Map.Entry entry : entrySet) {
                zzap zzb4 = zzb(entry.getKey());
                zzap zzb5 = zzb(entry.getValue());
                zzap zzap2 = zzf;
                if (zzb4 == zzap2 || zzb5 == zzap2) {
                    return zzap2;
                }
                z3 = z3 || zzb4.zzN() || zzb5.zzN();
                arrayList2.add(zzb4);
                arrayList3.add(zzb5);
            }
            zzg.zzk();
            zzg.zzc(arrayList2);
            zzg.zzl();
            zzg.zzd(arrayList3);
            z = z3;
        } else if (zzq(obj)) {
            zzg.zzt(1);
            zzg.zzs(obj.toString());
        } else if (zzr(obj)) {
            zzg.zzt(6);
            zzg.zzq(zzo(obj));
        } else if (obj instanceof Boolean) {
            zzg.zzt(8);
            zzg.zzn(((Boolean) obj).booleanValue());
        } else {
            if (obj == null) {
                str = "null";
            } else {
                str = obj.getClass().toString();
            }
            Log.e("GoogleTagManager", "Converting to Value from unknown object type: ".concat(str));
            return zzf;
        }
        zzg.zzo(z);
        return (zzap) zzg.zzD();
    }

    public static zzfo zzc() {
        return zzc;
    }

    public static zzfo zzd(Object obj) {
        if (obj instanceof zzfo) {
            return (zzfo) obj;
        }
        if (zzr(obj)) {
            return zzfo.zzd(zzo(obj));
        }
        if (zzq(obj)) {
            return zzfo.zzc(Double.valueOf(zzn(obj)));
        }
        return zzp(zzm(obj));
    }

    public static Boolean zze() {
        return zze;
    }

    public static Boolean zzf(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        String zzm = zzm(obj);
        if ("true".equalsIgnoreCase(zzm)) {
            return Boolean.TRUE;
        }
        return "false".equalsIgnoreCase(zzm) ? Boolean.FALSE : zze;
    }

    public static Long zzi() {
        return zza;
    }

    public static Long zzj(Object obj) {
        if (zzr(obj)) {
            return Long.valueOf(zzo(obj));
        }
        zzfo zzp = zzp(zzm(obj));
        if (zzp == zzc) {
            return zza;
        }
        return Long.valueOf(zzp.zzb());
    }

    public static Object zzk(zzap zzap) {
        if (zzap == null) {
            return null;
        }
        switch (zzap.zzO()) {
            case 1:
                return zzap.zzp();
            case 2:
                ArrayList arrayList = new ArrayList(zzap.zza());
                for (zzap zzk : zzap.zzr()) {
                    Object zzk2 = zzk(zzk);
                    if (zzk2 == null) {
                        return null;
                    }
                    arrayList.add(zzk2);
                }
                return arrayList;
            case 3:
                if (zzap.zzc() != zzap.zzd()) {
                    Log.e("GoogleTagManager", "Converting an invalid value to object: ".concat(zzap.toString()));
                    return null;
                }
                HashMap hashMap = new HashMap(zzap.zzd());
                for (int i2 = 0; i2 < zzap.zzc(); i2++) {
                    Object zzk3 = zzk(zzap.zzk(i2));
                    Object zzk4 = zzk(zzap.zzl(i2));
                    if (zzk3 == null || zzk4 == null) {
                        return null;
                    }
                    hashMap.put(zzk3, zzk4);
                }
                return hashMap;
            case 4:
                Log.e("GoogleTagManager", "Trying to convert a macro reference to object");
                return null;
            case 5:
                Log.e("GoogleTagManager", "Trying to convert a function id to object");
                return null;
            case 6:
                return Long.valueOf(zzap.zzf());
            case 7:
                StringBuilder sb = new StringBuilder();
                for (zzap zzk5 : zzap.zzs()) {
                    String zzm = zzm(zzk(zzk5));
                    if (zzm == zzd) {
                        return null;
                    }
                    sb.append(zzm);
                }
                return sb.toString();
            default:
                return Boolean.valueOf(zzap.zzM());
        }
    }

    public static String zzl() {
        return zzd;
    }

    public static String zzm(Object obj) {
        return obj == null ? zzd : obj.toString();
    }

    private static double zzn(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        }
        Log.e("GoogleTagManager", "getDouble received non-Number");
        return 0.0d;
    }

    private static long zzo(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        Log.e("GoogleTagManager", "getInt64 received non-Number");
        return 0;
    }

    private static zzfo zzp(String str) {
        try {
            return zzfo.zze(str);
        } catch (NumberFormatException unused) {
            Log.e("GoogleTagManager", "Failed to convert '" + str + "' to a number.");
            return zzc;
        }
    }

    private static boolean zzq(Object obj) {
        if ((obj instanceof Double) || (obj instanceof Float)) {
            return true;
        }
        if (obj instanceof zzfo) {
            return ((zzfo) obj).zzf();
        }
        return false;
    }

    private static boolean zzr(Object obj) {
        if ((obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long)) {
            return true;
        }
        if (obj instanceof zzfo) {
            return ((zzfo) obj).zzg();
        }
        return false;
    }
}
