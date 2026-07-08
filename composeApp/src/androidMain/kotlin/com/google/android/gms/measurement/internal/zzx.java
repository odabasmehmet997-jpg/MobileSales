package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzeq;
import com.google.android.gms.internal.measurement.zzex;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
abstract class zzx {
    final String zzb;
    final int zzc;
    Boolean zzd;
    Boolean zze;
    Long zzf;
    Long zzg;

    zzx(String str, int i2) {
        this.zzb = str;
        this.zzc = i2;
    }

    private static Boolean zzd(String str, int i2, boolean z, String str2, List list, String str3, zzej zzej) {
        if (7 == i2) {
            if (null == list || 0 == list.size()) {
                return null;
            }
        } else if (null == str2) {
            return null;
        }
        if (!z && 2 != i2) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (i2 - 1) {
            case 1:
                if (null == str3) {
                    return null;
                }
                try {
                    return Boolean.valueOf(Pattern.compile(str3, !z ? 66 : 0).matcher(str).matches());
                } catch (PatternSyntaxException unused) {
                    if (null != zzej) {
                        zzej.zzk().zzb("Invalid regular expression in REGEXP audience filter. expression", str3);
                    }
                    return null;
                }
            case 2:
                return Boolean.valueOf(str.startsWith(str2));
            case 3:
                return Boolean.valueOf(str.endsWith(str2));
            case 4:
                return Boolean.valueOf(str.contains(str2));
            case 5:
                return Boolean.valueOf(str.equals(str2));
            case 6:
                if (null == list) {
                    return null;
                }
                return Boolean.valueOf(list.contains(str));
            default:
                return null;
        }
    }

    @VisibleForTesting
    static Boolean zze(BigDecimal bigDecimal, zzeq zzeq, double d2) {
        BigDecimal bigDecimal2;
        BigDecimal bigDecimal3;
        BigDecimal bigDecimal4;
        Preconditions.checkNotNull(zzeq);
        if (zzeq.zzg()) {
            boolean z = true;
            if (1 != zzeq.zzm()) {
                if (5 == zzeq.zzm()) {
                    if (!zzeq.zzk() || !zzeq.zzj()) {
                        return null;
                    }
                } else if (!zzeq.zzh()) {
                    return null;
                }
                int zzm = zzeq.zzm();
                if (5 == zzeq.zzm()) {
                    if (zzkt.zzx(zzeq.zze()) && zzkt.zzx(zzeq.zzd())) {
                        try {
                            BigDecimal bigDecimal5 = new BigDecimal(zzeq.zze());
                            bigDecimal3 = new BigDecimal(zzeq.zzd());
                            bigDecimal2 = bigDecimal5;
                            bigDecimal4 = null;
                        } catch (NumberFormatException unused) {
                        }
                    }
                    return null;
                } else if (!zzkt.zzx(zzeq.zzc())) {
                    return null;
                } else {
                    try {
                        bigDecimal4 = new BigDecimal(zzeq.zzc());
                        bigDecimal2 = null;
                        bigDecimal3 = null;
                    } catch (NumberFormatException unused2) {
                    }
                }
                if (5 == zzm) {
                    if (null == bigDecimal2) {
                        return null;
                    }
                } else if (null == bigDecimal4) {
                    return null;
                }
                int i2 = zzm - 1;
                if (1 != i2) {
                    if (2 != i2) {
                        if (3 != i2) {
                            if (4 != i2 || null == bigDecimal2) {
                                return null;
                            }
                            if (0 > bigDecimal.compareTo(bigDecimal2) || 0 < bigDecimal.compareTo(bigDecimal3)) {
                                z = false;
                            }
                            return Boolean.valueOf(z);
                        } else if (null == bigDecimal4) {
                            return null;
                        } else {
                            if (0.0d != d2) {
                                if (0 >= bigDecimal.compareTo(bigDecimal4.subtract(new BigDecimal(d2).multiply(new BigDecimal(2)))) || 0 <= bigDecimal.compareTo(bigDecimal4.add(new BigDecimal(d2).multiply(new BigDecimal(2))))) {
                                    z = false;
                                }
                                return Boolean.valueOf(z);
                            }
                            if (0 != bigDecimal.compareTo(bigDecimal4)) {
                                z = false;
                            }
                            return Boolean.valueOf(z);
                        }
                    } else if (null == bigDecimal4) {
                        return null;
                    } else {
                        if (0 >= bigDecimal.compareTo(bigDecimal4)) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    }
                } else if (null == bigDecimal4) {
                    return null;
                } else {
                    if (0 <= bigDecimal.compareTo(bigDecimal4)) {
                        z = false;
                    }
                    return Boolean.valueOf(z);
                }
            }
        }
        return null;
    }

    @VisibleForTesting
    static Boolean zzf(String str, zzex zzex, zzej zzej) {
        String zzd2;
        List list;
        Preconditions.checkNotNull(zzex);
        if (null == str || !zzex.zzi() || 1 == zzex.zzj()) {
            return null;
        }
        if (7 == zzex.zzj()) {
            if (0 == zzex.zza()) {
                return null;
            }
        } else if (!zzex.zzh()) {
            return null;
        }
        int zzj = zzex.zzj();
        boolean zzf2 = zzex.zzf();
        if (zzf2 || 2 == zzj || 7 == zzj) {
            zzd2 = zzex.zzd();
        } else {
            zzd2 = zzex.zzd().toUpperCase(Locale.ENGLISH);
        }
        String str2 = zzd2;
        if (0 == zzex.zza()) {
            list = null;
        } else {
            List<String> zze2 = zzex.zze();
            if (!zzf2) {
                ArrayList arrayList = new ArrayList(zze2.size());
                for (String upperCase : zze2) {
                    arrayList.add(upperCase.toUpperCase(Locale.ENGLISH));
                }
                zze2 = Collections.unmodifiableList(arrayList);
            }
            list = zze2;
        }
        return zzd(str, zzj, zzf2, str2, list, 2 == zzj ? str2 : null, zzej);
    }

    static Boolean zzg(double d2, zzeq zzeq) {
        try {
            return zze(new BigDecimal(d2), zzeq, Math.ulp(d2));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    static Boolean zzh(long j2, zzeq zzeq) {
        try {
            return zze(new BigDecimal(j2), zzeq, 0.0d);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    static Boolean zzi(String str, zzeq zzeq) {
        if (!zzkt.zzx(str)) {
            return null;
        }
        try {
            return zze(new BigDecimal(str), zzeq, 0.0d);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    @VisibleForTesting
    static Boolean zzj(Boolean bool, boolean z) {
        if (null == bool) {
            return null;
        }
        return Boolean.valueOf(bool.booleanValue() != z);
    }

    
    public abstract int zza();

    
    public abstract boolean zzb();

    
    public abstract boolean zzc();
}
