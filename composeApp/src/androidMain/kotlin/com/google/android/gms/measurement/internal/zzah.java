package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import java.util.EnumMap;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzah {
    public static final zzah zza = new zzah(null, null);
    private final EnumMap zzb;

    public zzah(final Boolean bool, final Boolean bool2) {
        final EnumMap enumMap = new EnumMap(zzag.class);
        zzb = enumMap;
        enumMap.put(zzag.AD_STORAGE, bool);
        enumMap.put(zzag.ANALYTICS_STORAGE, bool2);
    }

    public static zzah zza(final Bundle bundle) {
        if (null == bundle) {
            return zzah.zza;
        }
        final EnumMap enumMap = new EnumMap(zzag.class);
        for (final zzag zzag : zzag.values()) {
            enumMap.put(zzag, zzah.zzm(bundle.getString(zzag.zzd)));
        }
        return new zzah(enumMap);
    }

    public static zzah zzb(final String str) {
        final EnumMap enumMap = new EnumMap(zzag.class);
        if (null != str) {
            int i2 = 0;
            while (true) {
                final zzag[] zzagArr = zzag.zzc;
                final int length = zzagArr.length;
                if (2 <= i2) {
                    break;
                }
                final zzag zzag = zzagArr[i2];
                final int i3 = i2 + 2;
                if (i3 < str.length()) {
                    final char charAt = str.charAt(i3);
                    Boolean bool = null;
                    if ('-' != charAt) {
                        if ('0' == charAt) {
                            bool = Boolean.FALSE;
                        } else if ('1' == charAt) {
                            bool = Boolean.TRUE;
                        }
                    }
                    enumMap.put(zzag, bool);
                }
                i2++;
            }
        }
        return new zzah(enumMap);
    }

    public static String zzg(final Bundle bundle) {
        String string;
        for (final zzag zzag : zzag.values()) {
            if (bundle.containsKey(zzag.zzd) && null != (string = bundle.getString(zzag.zzd)) && null == zzm(string)) {
                return string;
            }
        }
        return null;
    }

    public static boolean zzj(final int i2, final int i3) {
        return i2 <= i3;
    }

    static int zzl(final Boolean bool) {
        if (null == bool) {
            return 0;
        }
        return bool.booleanValue() ? 1 : 2;
    }

    private static Boolean zzm(final String str) {
        if (null == str) {
            return null;
        }
        if ("granted".equals(str)) {
            return Boolean.TRUE;
        }
        if ("denied".equals(str)) {
            return Boolean.FALSE;
        }
        return null;
    }

    public boolean equals(final Object obj) {
        if (!(obj instanceof zzah zzah)) {
            return false;
        }
        for (final zzag zzag : zzag.values()) {
            if (zzl((Boolean) zzb.get(zzag)) != zzl((Boolean) zzah.zzb.get(zzag))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i2 = 17;
        for (final Boolean zzl : zzb.values()) {
            i2 = (i2 * 31) + zzah.zzl(zzl);
        }
        return i2;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder("settings: ");
        final zzag[] values = zzag.values();
        final int length = values.length;
        for (int i2 = 0; i2 < length; i2++) {
            final zzag zzag = values[i2];
            if (0 != i2) {
                sb.append(", ");
            }
            sb.append(zzag.name());
            sb.append("=");
            final Boolean bool = (Boolean) zzb.get(zzag);
            if (zzb.containsKey(zzag) || null == bool) {
                sb.append("uninitialized");
            } else {
                sb.append(!bool.booleanValue() ? "denied" : "granted");
            }
        }
        return sb.toString();
    }

    public zzah zzc(final zzah zzah) {
        final EnumMap enumMap = new EnumMap(zzag.class);
        for (final zzag zzag : zzag.values()) {
            Boolean bool = (Boolean) zzb.get(zzag);
            final Boolean bool2 = (Boolean) zzah.zzb.get(zzag);
            if (null == bool) {
                bool = bool2;
            } else if (null != bool2) {
                bool = Boolean.valueOf(bool.booleanValue() && bool2.booleanValue());
            }
            enumMap.put(zzag, bool);
        }
        return new zzah(enumMap);
    }

    public zzah zzd(final zzah zzah) {
        final EnumMap enumMap = new EnumMap(zzag.class);
        for (final zzag zzag : zzag.values()) {
            Boolean bool = (Boolean) zzb.get(zzag);
            if (null == bool) {
                bool = (Boolean) zzah.zzb.get(zzag);
            }
            enumMap.put(zzag, bool);
        }
        return new zzah(enumMap);
    }

    public Boolean zze() {
        return (Boolean) zzb.get(zzag.AD_STORAGE);
    }

    public Boolean zzf() {
        return (Boolean) zzb.get(zzag.ANALYTICS_STORAGE);
    }

    public String zzh() {
        char c2;
        final StringBuilder sb = new StringBuilder("G1");
        final zzag[] zzagArr = zzag.zzc;
        final int length = zzagArr.length;
        for (int i2 = 0; 2 > i2; i2++) {
            final Boolean bool = (Boolean) zzb.get(zzagArr[i2]);
            if (null == bool) {
                c2 = '-';
            } else {
                c2 = bool.booleanValue() ? '1' : '0';
            }
            sb.append(c2);
        }
        return sb.toString();
    }

    public boolean zzi(final zzag zzag) {
        final Boolean bool = (Boolean) zzb.get(zzag);
        return null == bool || bool.booleanValue();
    }

    public boolean zzk(final zzah zzah) {
        for (final zzag zzag : (zzag[]) zzb.keySet().toArray(new zzag[0])) {
            final Boolean bool = (Boolean) zzb.get(zzag);
            final Boolean bool2 = (Boolean) zzah.zzb.get(zzag);
            final Boolean bool3 = Boolean.FALSE;
            if (bool == bool3 && bool2 != bool3) {
                return true;
            }
        }
        return false;
    }

    public zzah(final EnumMap enumMap) {
        final EnumMap enumMap2 = new EnumMap(zzag.class);
        zzb = enumMap2;
        enumMap2.putAll(enumMap);
    }
}
