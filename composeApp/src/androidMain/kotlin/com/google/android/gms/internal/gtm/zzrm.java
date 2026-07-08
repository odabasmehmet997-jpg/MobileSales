package com.google.android.gms.internal.gtm;

import android.util.Log;
import com.google.android.gms.tagmanager.zzfp;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
public enum zzrm {
    ;

    public static zzap zza(final zzap zzap) {
        final zzak zzg = com.google.android.gms.internal.gtm.zzap.zzg();
        zzg.zzt(1);
        zzg.zzt(zzap.zzO());
        zzg.zzi();
        zzg.zza(zzap.zzq());
        zzg.zzo(zzap.zzN());
        return (zzap) zzg.zzD();
    }

    public static zzrg zzb(final zzz zzz) throws zzrk {
        final zzap[] zzapArr = new zzap[zzz.zzf()];
        for (int i2 = 0; i2 < zzz.zzf(); i2++) {
            zzrm.zze(i2, zzz, zzapArr, new HashSet(0));
        }
        final zzrh zzrh = new zzrh(null);
        final ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < zzz.zze(); i3++) {
            arrayList.add(zzrm.zzf(zzz.zzi(i3), zzz, zzapArr));
        }
        final ArrayList arrayList2 = new ArrayList();
        for (int i4 = 0; i4 < zzz.zzc(); i4++) {
            arrayList2.add(zzrm.zzf(zzz.zzh(i4), zzz, zzapArr));
        }
        final ArrayList arrayList3 = new ArrayList();
        for (int i5 = 0; i5 < zzz.zza(); i5++) {
            final zzre zzf = zzrm.zzf(zzz.zzg(i5), zzz, zzapArr);
            zzrh.zzb(zzf);
            arrayList3.add(zzf);
        }
        for (final zzab zzab : zzz.zzq()) {
            final zzrj zzrj = new zzrj(null);
            for (final Integer intValue : zzab.zzh()) {
                zzrj.zzg((zzre) arrayList2.get(intValue.intValue()));
            }
            for (final Integer intValue2 : zzab.zzg()) {
                zzrj.zzf((zzre) arrayList2.get(intValue2.intValue()));
            }
            for (final Integer intValue3 : zzab.zze()) {
                zzrj.zzd((zzre) arrayList.get(intValue3.intValue()));
            }
            for (final Integer intValue4 : zzab.zzf()) {
                zzrj.zze(zzz.zzm(intValue4.intValue()).zzp());
            }
            for (final Integer intValue5 : zzab.zzk()) {
                zzrj.zzj((zzre) arrayList.get(intValue5.intValue()));
            }
            for (final Integer intValue6 : zzab.zzl()) {
                zzrj.zzk(zzz.zzm(intValue6.intValue()).zzp());
            }
            for (final Integer intValue7 : zzab.zzc()) {
                zzrj.zzb((zzre) arrayList3.get(intValue7.intValue()));
            }
            for (final Integer intValue8 : zzab.zzd()) {
                zzrj.zzc(zzz.zzm(intValue8.intValue()).zzp());
            }
            for (final Integer intValue9 : zzab.zzi()) {
                zzrj.zzh((zzre) arrayList3.get(intValue9.intValue()));
            }
            for (final Integer intValue10 : zzab.zzj()) {
                zzrj.zzi(zzz.zzm(intValue10.intValue()).zzp());
            }
            zzrh.zzc(zzrj.zza());
        }
        zzrh.zze(zzz.zzn());
        zzrh.zzd(zzz.zzd());
        return zzrh.zza();
    }

    public static void zzc(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        final byte[] bArr = new byte[1024];
        while (true) {
            final int read = inputStream.read(bArr);
            if (-1 != read) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    private static zzad zzd(final zzap zzap) throws zzrk {
        final zzace zzace = zzad.zza;
        if (!zzap.zzW(zzace)) {
            zzrm.zzh("Expected a ServingValue and didn't get one. Value is: " + zzap);
        }
        return (zzad) zzap.zzV(zzace);
    }

    private static zzap zze(final int i2, final zzz zzz, final zzap[] zzapArr, final Set set) throws zzrk {
        zzak zzak;
        final Integer valueOf = Integer.valueOf(i2);
        if (set.contains(valueOf)) {
            final String obj = set.toString();
            zzrm.zzh("Value cycle detected.  Current value reference: " + i2 + ".  Previous value references: " + obj + ".");
        }
        zzak zzak2 = (zzak) ((zzap) zzrm.zzg(zzz.zzr(), i2, "values")).zzaa();
        final zzap zzap = zzapArr[i2];
        if (null != zzap) {
            return zzap;
        }
        set.add(valueOf);
        final int zzu = zzak2.zzu();
        if (2 != zzu) {
            if (3 == zzu) {
                zzak = (zzak) zzrm.zza((zzap) zzak2.zzD()).zzaa();
                final zzad zzd = zzrm.zzd((zzap) zzak2.zzD());
                if (zzd.zzc() != zzd.zzd()) {
                    final int zzc = zzd.zzc();
                    final int zzd2 = zzd.zzd();
                    zzrm.zzh("Uneven map keys (" + zzc + ") and map values (" + zzd2 + ")");
                }
                zzak.zzk();
                zzak.zzl();
                for (final Integer intValue : zzd.zzg()) {
                    zzak.zzf(zzrm.zze(intValue.intValue(), zzz, zzapArr, set));
                }
                for (final Integer intValue2 : zzd.zzh()) {
                    zzak.zzg(zzrm.zze(intValue2.intValue(), zzz, zzapArr, set));
                }
            } else if (4 == zzu) {
                zzak = (zzak) zzrm.zza((zzap) zzak2.zzD()).zzaa();
                zzak.zzr(zzfp.zzm(zzfp.zzk(zzrm.zze(zzrm.zzd((zzap) zzak2.zzD()).zza(), zzz, zzapArr, set))));
            } else if (7 == zzu) {
                zzak = (zzak) zzrm.zza((zzap) zzak2.zzD()).zzaa();
                final zzad zzd3 = zzrm.zzd((zzap) zzak2.zzD());
                zzak.zzm();
                for (final Integer intValue3 : zzd3.zzi()) {
                    zzak.zzh(zzrm.zze(intValue3.intValue(), zzz, zzapArr, set));
                }
            }
            zzak2 = zzak;
        } else {
            final zzad zzd4 = zzrm.zzd((zzap) zzak2.zzD());
            zzak2 = (zzak) zzrm.zza((zzap) zzak2.zzD()).zzaa();
            zzak2.zzj();
            for (final Integer intValue4 : zzd4.zzf()) {
                zzak2.zze(zzrm.zze(intValue4.intValue(), zzz, zzapArr, set));
            }
        }
        zzapArr[i2] = (zzap) zzak2.zzD();
        set.remove(Integer.valueOf(i2));
        return (zzap) zzak2.zzD();
    }

    private static zzre zzf(final zzr zzr, final zzz zzz, final zzap[] zzapArr) throws zzrk {
        final zzrf zzrf = new zzrf(null);
        for (final Integer intValue : zzr.zzc()) {
            final zzx zzx = (zzx) zzrm.zzg(zzz.zzp(), intValue.intValue(), "properties");
            final String str = (String) zzrm.zzg(zzz.zzo(), zzx.zza(), UserMetadata.KEYDATA_FILENAME);
            final int zzc = zzx.zzc();
            if (0 > zzc || zzc >= zzapArr.length) {
                zzrm.zzh("Index out of bounds detected: " + zzc + " in values");
            }
            final zzap zzap = zzapArr[zzc];
            if (zzb.PUSH_AFTER_EVALUATE.toString().equals(str)) {
                zzrf.zzc(zzap);
            } else {
                zzrf.zzb(str, zzap);
            }
        }
        return zzrf.zza();
    }

    private static Object zzg(final List list, final int i2, final String str) throws zzrk {
        if (0 > i2 || i2 >= list.size()) {
            zzrm.zzh("Index out of bounds detected: " + i2 + " in " + str);
        }
        return list.get(i2);
    }

    private static void zzh(final String str) throws zzrk {
        Log.e("GoogleTagManager", str);
        throw new zzrk(str);
    }
}
