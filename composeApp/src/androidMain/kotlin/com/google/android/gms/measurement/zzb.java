package com.google.android.gms.measurement;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.internal.zzhz;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zzb extends zzd {
    private final zzhz zza;

    public zzb(zzhz zzhz) {
        super(null);
        Preconditions.checkNotNull(zzhz);
        this.zza = zzhz;
    }

    public int zza(String str) {
        return this.zza.zza(str);
    }

    public long zzb() {
        return this.zza.zzb();
    }

    public String zzh() {
        return this.zza.zzh();
    }

    public String zzi() {
        return this.zza.zzi();
    }

    public String zzj() {
        return this.zza.zzj();
    }

    public String zzk() {
        return this.zza.zzk();
    }

    public List zzm(String str, String str2) {
        return this.zza.zzm(str, str2);
    }

    public Map zzo(String str, String str2, boolean z) {
        return this.zza.zzo(str, str2, z);
    }

    public void zzp(String str) {
        this.zza.zzp(str);
    }

    public void zzq(String str, String str2, Bundle bundle) {
        this.zza.zzq(str, str2, bundle);
    }

    public void zzr(String str) {
        this.zza.zzr(str);
    }

    public void zzs(String str, String str2, Bundle bundle) {
        this.zza.zzs(str, str2, bundle);
    }

    public void zzv(Bundle bundle) {
        this.zza.zzv(bundle);
    }
}
