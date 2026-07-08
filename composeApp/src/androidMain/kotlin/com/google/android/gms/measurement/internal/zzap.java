package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzap {
    final String zza;
    final String zzb;
    final String zzc;
    final long zzd;
    final long zze;
    final zzas zzf;

    zzap(zzft zzft, String str, String str2, String str3, long j2, long j3, Bundle bundle) {
        zzas zzas;
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        this.zza = str2;
        this.zzb = str3;
        this.zzc = TextUtils.isEmpty(str) ? null : str;
        this.zzd = j2;
        this.zze = j3;
        if (0 != j3 && j3 > j2) {
            zzft.zzay().zzk().zzb("Event created with reverse previous/current timestamps. appId", zzej.zzn(str2));
        }
        if (null == bundle || bundle.isEmpty()) {
            zzas = new zzas(new Bundle());
        } else {
            Bundle bundle2 = new Bundle(bundle);
            Iterator<String> it = bundle2.keySet().iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (null == next) {
                    zzft.zzay().zzd().zza("Param name can't be null");
                    it.remove();
                } else {
                    Object zzA = zzft.zzv().zzA(next, bundle2.get(next));
                    if (null == zzA) {
                        zzft.zzay().zzk().zzb("Param value can't be null", zzft.zzj().zze(next));
                        it.remove();
                    } else {
                        zzft.zzv().zzN(bundle2, next, zzA);
                    }
                }
            }
            zzas = new zzas(bundle2);
        }
        this.zzf = zzas;
    }

    public String toString() {
        String str = this.zza;
        String str2 = this.zzb;
        String obj = this.zzf.toString();
        final String sb = "Event{appId='" +
                str +
                "', name='" +
                str2 +
                "', params=" +
                obj +
                '}';
        return sb;
    }

    
    public zzap zza(zzft zzft, long j2) {
        return new zzap(zzft, this.zzc, this.zza, this.zzb, this.zzd, j2, this.zzf);
    }

    private zzap(zzft zzft, String str, String str2, String str3, long j2, long j3, zzas zzas) {
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(zzas);
        this.zza = str2;
        this.zzb = str3;
        this.zzc = TextUtils.isEmpty(str) ? null : str;
        this.zzd = j2;
        this.zze = j3;
        if (0 != j3 && j3 > j2) {
            zzft.zzay().zzk().zzc("Event created with reverse previous/current timestamps. appId, name", zzej.zzn(str2), zzej.zzn(str3));
        }
        this.zzf = zzas;
    }
}
