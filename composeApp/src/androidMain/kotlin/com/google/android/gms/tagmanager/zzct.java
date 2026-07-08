package com.google.android.gms.tagmanager;

import android.util.Log;
import com.google.android.gms.internal.gtm.zzap;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzct extends zzbp {
    private static final String zza = zza.JOINER.toString();
    private static final String zzb = zzb.ARG0.toString();
    private static final String zzc = zzb.ITEM_SEPARATOR.toString();
    private static final String zzd = zzb.KEY_VALUE_SEPARATOR.toString();
    private static final String zze = zzb.ESCAPE.toString();

    public zzct() {
        super(zza, zzb);
    }

    private static void zzc(Set set, String str) {
        for (int i2 = 0; i2 < str.length(); i2++) {
            set.add(Character.valueOf(str.charAt(i2)));
        }
    }

    private static String zzd(String str, int i2, Set set) {
        int i3 = i2 - 1;
        if (i3 == 1) {
            try {
                return zzfs.zza(str);
            } catch (UnsupportedEncodingException e2) {
                Log.e("GoogleTagManager", "Joiner: unsupported encoding", e2);
                return str;
            }
        } else if (i3 != 2) {
            return str;
        } else {
            String replace = str.replace("\\", "\\\\");
            Iterator it = set.iterator();
            while (it.hasNext()) {
                String ch = it.next().toString();
                replace = replace.replace(ch, "\\".concat(ch));
            }
            return replace;
        }
    }

    public zzap zza(Map map) {
        int i2;
        zzap zzap = (zzap) map.get(zzb);
        if (zzap == null) {
            return zzfp.zza();
        }
        zzap zzap2 = (zzap) map.get(zzc);
        String zzm = zzap2 != null ? zzfp.zzm(zzfp.zzk(zzap2)) : "";
        zzap zzap3 = (zzap) map.get(zzd);
        String zzm2 = zzap3 != null ? zzfp.zzm(zzfp.zzk(zzap3)) : "=";
        zzap zzap4 = (zzap) map.get(zze);
        HashSet hashSet = null;
        boolean z = true;
        if (zzap4 != null) {
            String zzm3 = zzfp.zzm(zzfp.zzk(zzap4));
            if ("url".equals(zzm3)) {
                i2 = 2;
            } else if ("backslash".equals(zzm3)) {
                hashSet = new HashSet();
                zzc(hashSet, zzm);
                zzc(hashSet, zzm2);
                hashSet.remove('\\');
                i2 = 3;
            } else {
                Log.e("GoogleTagManager", "Joiner: unsupported escape type: ".concat(String.valueOf(zzm3)));
                return zzfp.zza();
            }
        } else {
            i2 = 1;
        }
        StringBuilder sb = new StringBuilder();
        int zzO = zzap.zzO();
        if (zzO == 2) {
            for (zzap zzap5 : zzap.zzr()) {
                if (!z) {
                    sb.append(zzm);
                }
                sb.append(zzd(zzfp.zzm(zzfp.zzk(zzap5)), i2, hashSet));
                z = false;
            }
        } else if (zzO != 3) {
            sb.append(zzd(zzfp.zzm(zzfp.zzk(zzap)), i2, hashSet));
        } else {
            for (int i3 = 0; i3 < zzap.zzc(); i3++) {
                if (i3 > 0) {
                    sb.append(zzm);
                }
                String zzm4 = zzfp.zzm(zzfp.zzk(zzap.zzk(i3)));
                String zzm5 = zzfp.zzm(zzfp.zzk(zzap.zzl(i3)));
                sb.append(zzd(zzm4, i2, hashSet));
                sb.append(zzm2);
                sb.append(zzd(zzm5, i2, hashSet));
            }
        }
        return zzfp.zzb(sb.toString());
    }

    public boolean zzb() {
        return true;
    }
}
