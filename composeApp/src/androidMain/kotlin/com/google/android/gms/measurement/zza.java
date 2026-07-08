package com.google.android.gms.measurement;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.internal.zzft;
import com.google.android.gms.measurement.internal.zzhy;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zza extends zzd {
    private final zzft zza;
    private final zzhy zzb;

    public zza(@NonNull zzft zzft) {
        super(null);
        Preconditions.checkNotNull(zzft);
        this.zza = zzft;
        this.zzb = zzft.zzq();
    }

    public int zza(String str) {
        this.zzb.zzh(str);
        return 25;
    }

    public long zzb() {
        return this.zza.zzv().zzq();
    }

    public String zzh() {
        return this.zzb.zzo();
    }

    public String zzi() {
        return this.zzb.zzp();
    }

    public String zzj() {
        return this.zzb.zzq();
    }

    public String zzk() {
        return this.zzb.zzo();
    }

    public List zzm(String str, String str2) {
        return this.zzb.zzs(str, str2);
    }

    public Map zzo(String str, String str2, boolean z) {
        return this.zzb.zzu(str, str2, z);
    }

    public void zzp(String str) {
        this.zza.zzd().zzd(str, this.zza.zzav().elapsedRealtime());
    }

    public void zzq(String str, String str2, Bundle bundle) {
        this.zza.zzq().zzz(str, str2, bundle);
    }

    public void zzr(String str) {
        this.zza.zzd().zze(str, this.zza.zzav().elapsedRealtime());
    }

    public void zzs(String str, String str2, Bundle bundle) {
        this.zzb.zzD(str, str2, bundle);
    }

    public void zzv(Bundle bundle) {
        this.zzb.zzP(bundle);
    }
}
