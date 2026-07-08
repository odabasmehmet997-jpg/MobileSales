package com.google.firebase.analytics;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.measurement.zzee;
import com.google.android.gms.measurement.internal.zzgt;
import com.google.android.gms.measurement.internal.zzgu;
import com.google.android.gms.measurement.internal.zzhz;
import java.util.List;
import java.util.Map;

/*  INFO: compiled from: com.google.android.gms:play-services-measurement-api@@20.1.1 */
/*  INFO: loaded from: classes2.dex */
final class zzc implements zzhz {
    final /* synthetic */ zzee zza;

    zzc(zzee zzeeVar) {
        this.zza = zzeeVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzhz
    public final int zza(String str) {
        return this.zza.zza(str);
    }

    @Override // com.google.android.gms.measurement.internal.zzhz
    public final long zzb() {
        return this.zza.zzb();
    }

    @Nullable
    public final Object zzg(int i2) {
        return this.zza.zzh(i2);
    }

    @Override // com.google.android.gms.measurement.internal.zzhz
    @Nullable
    public final String zzh() {
        return this.zza.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzhz
    @Nullable
    public final String zzi() {
        return this.zza.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zzhz
    @Nullable
    public final String zzj() {
        return this.zza.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzhz
    @Nullable
    public final String zzk() {
        return this.zza.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzhz
    public final List zzm(@Nullable String str, @Nullable String str2) {
        return this.zza.zzp(str, str2);
    }

    @Override // com.google.android.gms.measurement.internal.zzhz
    public final Map zzo(@Nullable String str, @Nullable String str2, boolean z) {
        return this.zza.zzq(str, str2, z);
    }

    @Override // com.google.android.gms.measurement.internal.zzhz
    public final void zzp(String str) {
        this.zza.zzu(str);
    }

    @Override // com.google.android.gms.measurement.internal.zzhz
    public final void zzq(String str, @Nullable String str2, @Nullable Bundle bundle) {
        this.zza.zzv(str, str2, bundle);
    }

    @Override // com.google.android.gms.measurement.internal.zzhz
    public final void zzr(String str) {
        this.zza.zzw(str);
    }

    @Override // com.google.android.gms.measurement.internal.zzhz
    public final void zzs(String str, String str2, Bundle bundle) {
        this.zza.zzy(str, str2, bundle);
    }

    public final void zzt(String str, String str2, Bundle bundle, long j2) {
        this.zza.zzz(str, str2, bundle, j2);
    }

    public final void zzu(zzgu zzguVar) {
        this.zza.zzB(zzguVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzhz
    public final void zzv(Bundle bundle) {
        this.zza.zzD(bundle);
    }

    public final void zzw(zzgt zzgtVar) {
        this.zza.zzJ(zzgtVar);
    }

    public final void zzx(zzgu zzguVar) {
        this.zza.zzO(zzguVar);
    }
}
