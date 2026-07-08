package com.google.android.gms.measurement.internal;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
final class zzg {
    private long zzA;
    @Nullable
    private String zzB;
    private boolean zzC;
    private long zzD;
    private long zzE;
    private final zzft zza;
    private final String zzb;
    @Nullable
    private String zzc;
    @Nullable
    private String zzd;
    @Nullable
    private String zze;
    @Nullable
    private String zzf;
    private long zzg;
    private long zzh;
    private long zzi;
    @Nullable
    private String zzj;
    private long zzk;
    @Nullable
    private String zzl;
    private long zzm;
    private long zzn;
    private boolean zzo;
    private long zzp;
    private boolean zzq;
    @Nullable
    private String zzr;
    @Nullable
    private Boolean zzs;
    private long zzt;
    @Nullable
    private List zzu;
    private long zzv;
    private long zzw;
    private long zzx;
    private long zzy;
    private long zzz;

    @WorkerThread
    zzg(zzft zzft, String str) {
        Preconditions.checkNotNull(zzft);
        Preconditions.checkNotEmpty(str);
        this.zza = zzft;
        this.zzb = str;
        zzft.zzaz().zzg();
    }

    @WorkerThread
    @Nullable
    public String zzA() {
        this.zza.zzaz().zzg();
        return this.zze;
    }

    @WorkerThread
    @Nullable
    public List zzB() {
        this.zza.zzaz().zzg();
        return this.zzu;
    }

    @WorkerThread
    public void zzC() {
        this.zza.zzaz().zzg();
        this.zzC = false;
    }

    @WorkerThread
    public void zzD() {
        this.zza.zzaz().zzg();
        long j2 = this.zzg + 1;
        if (2147483647L < j2) {
            this.zza.zzay().zzk().zzb("Bundle index overflow. appId", zzej.zzn(this.zzb));
            j2 = 0;
        }
        this.zzC = true;
        this.zzg = j2;
    }

    @WorkerThread
    public void zzE(@Nullable String str) {
        this.zza.zzaz().zzg();
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.zzC |= !zzky.zzak(this.zzr, str);
        this.zzr = str;
    }

    @WorkerThread
    public void zzF(boolean z) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzq != z;
        this.zzq = z;
    }

    @WorkerThread
    public void zzG(long j2) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzp != j2;
        this.zzp = j2;
    }

    @WorkerThread
    public void zzH(@Nullable String str) {
        this.zza.zzaz().zzg();
        this.zzC |= !zzky.zzak(this.zzc, str);
        this.zzc = str;
    }

    @WorkerThread
    public void zzI(@Nullable String str) {
        this.zza.zzaz().zzg();
        this.zzC |= !zzky.zzak(this.zzl, str);
        this.zzl = str;
    }

    @WorkerThread
    public void zzJ(@Nullable String str) {
        this.zza.zzaz().zzg();
        this.zzC |= !zzky.zzak(this.zzj, str);
        this.zzj = str;
    }

    @WorkerThread
    public void zzK(long j2) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzk != j2;
        this.zzk = j2;
    }

    @WorkerThread
    public void zzL(long j2) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzD != j2;
        this.zzD = j2;
    }

    @WorkerThread
    public void zzM(long j2) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzy != j2;
        this.zzy = j2;
    }

    @WorkerThread
    public void zzN(long j2) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzz != j2;
        this.zzz = j2;
    }

    @WorkerThread
    public void zzO(long j2) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzx != j2;
        this.zzx = j2;
    }

    @WorkerThread
    public void zzP(long j2) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzw != j2;
        this.zzw = j2;
    }

    @WorkerThread
    public void zzQ(long j2) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzA != j2;
        this.zzA = j2;
    }

    @WorkerThread
    public void zzR(long j2) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzv != j2;
        this.zzv = j2;
    }

    @WorkerThread
    public void zzS(long j2) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzn != j2;
        this.zzn = j2;
    }

    @WorkerThread
    public void zzT(long j2) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzt != j2;
        this.zzt = j2;
    }

    @WorkerThread
    public void zzU(long j2) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzE != j2;
        this.zzE = j2;
    }

    @WorkerThread
    public void zzV(@Nullable String str) {
        this.zza.zzaz().zzg();
        this.zzC |= !zzky.zzak(this.zzf, str);
        this.zzf = str;
    }

    @WorkerThread
    public void zzW(@Nullable String str) {
        this.zza.zzaz().zzg();
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.zzC |= !zzky.zzak(this.zzd, str);
        this.zzd = str;
    }

    @WorkerThread
    public void zzX(long j2) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzm != j2;
        this.zzm = j2;
    }

    @WorkerThread
    public void zzY(@Nullable String str) {
        this.zza.zzaz().zzg();
        this.zzC |= !zzky.zzak(this.zzB, str);
        this.zzB = str;
    }

    @WorkerThread
    public void zzZ(long j2) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzi != j2;
        this.zzi = j2;
    }

    @WorkerThread
    public long zza() {
        this.zza.zzaz().zzg();
        return this.zzp;
    }

    @WorkerThread
    public void zzaa(long j2) {
        boolean z = false;
        Preconditions.checkArgument(0 <= j2);
        this.zza.zzaz().zzg();
        boolean z2 = this.zzC;
        if (this.zzg != j2) {
            z = true;
        }
        this.zzC = z2 | z;
        this.zzg = j2;
    }

    @WorkerThread
    public void zzab(long j2) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzh != j2;
        this.zzh = j2;
    }

    @WorkerThread
    public void zzac(boolean z) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzo != z;
        this.zzo = z;
    }

    @WorkerThread
    public void zzad(@Nullable Boolean bool) {
        this.zza.zzaz().zzg();
        boolean z = this.zzC;
        Boolean bool2 = this.zzs;
        final int i2 = zzky.r8clinit;
        this.zzC = z | (!(null == bool2 && null == bool || null != bool2 && bool2.equals(bool)));
        this.zzs = bool;
    }

    @WorkerThread
    public void zzae(@Nullable String str) {
        this.zza.zzaz().zzg();
        this.zzC |= !zzky.zzak(this.zze, str);
        this.zze = str;
    }

    @WorkerThread
    public void zzaf(@Nullable List list) {
        this.zza.zzaz().zzg();
        List list2 = this.zzu;
        final int i2 = zzky.r8clinit;
        if (null != list2 || null != list) {
            if (null == list2 || !list2.equals(list)) {
                this.zzC = true;
                this.zzu = null != list ? new ArrayList(list) : null;
            }
        }
    }

    @WorkerThread
    public boolean zzag() {
        this.zza.zzaz().zzg();
        return this.zzq;
    }

    @WorkerThread
    public boolean zzah() {
        this.zza.zzaz().zzg();
        return this.zzo;
    }

    @WorkerThread
    public boolean zzai() {
        this.zza.zzaz().zzg();
        return this.zzC;
    }

    @WorkerThread
    public long zzb() {
        this.zza.zzaz().zzg();
        return this.zzk;
    }

    @WorkerThread
    public long zzc() {
        this.zza.zzaz().zzg();
        return this.zzD;
    }

    @WorkerThread
    public long zzd() {
        this.zza.zzaz().zzg();
        return this.zzy;
    }

    @WorkerThread
    public long zze() {
        this.zza.zzaz().zzg();
        return this.zzz;
    }

    @WorkerThread
    public long zzf() {
        this.zza.zzaz().zzg();
        return this.zzx;
    }

    @WorkerThread
    public long zzg() {
        this.zza.zzaz().zzg();
        return this.zzw;
    }

    @WorkerThread
    public long zzh() {
        this.zza.zzaz().zzg();
        return this.zzA;
    }

    @WorkerThread
    public long zzi() {
        this.zza.zzaz().zzg();
        return this.zzv;
    }

    @WorkerThread
    public long zzj() {
        this.zza.zzaz().zzg();
        return this.zzn;
    }

    @WorkerThread
    public long zzk() {
        this.zza.zzaz().zzg();
        return this.zzt;
    }

    @WorkerThread
    public long zzl() {
        this.zza.zzaz().zzg();
        return this.zzE;
    }

    @WorkerThread
    public long zzm() {
        this.zza.zzaz().zzg();
        return this.zzm;
    }

    @WorkerThread
    public long zzn() {
        this.zza.zzaz().zzg();
        return this.zzi;
    }

    @WorkerThread
    public long zzo() {
        this.zza.zzaz().zzg();
        return this.zzg;
    }

    @WorkerThread
    public long zzp() {
        this.zza.zzaz().zzg();
        return this.zzh;
    }

    @WorkerThread
    @Nullable
    public Boolean zzq() {
        this.zza.zzaz().zzg();
        return this.zzs;
    }

    @WorkerThread
    @Nullable
    public String zzr() {
        this.zza.zzaz().zzg();
        return this.zzr;
    }

    @WorkerThread
    @Nullable
    public String zzs() {
        this.zza.zzaz().zzg();
        String str = this.zzB;
        zzY(null);
        return str;
    }

    @WorkerThread
    public String zzt() {
        this.zza.zzaz().zzg();
        return this.zzb;
    }

    @WorkerThread
    @Nullable
    public String zzu() {
        this.zza.zzaz().zzg();
        return this.zzc;
    }

    @WorkerThread
    @Nullable
    public String zzv() {
        this.zza.zzaz().zzg();
        return this.zzl;
    }

    @WorkerThread
    @Nullable
    public String zzw() {
        this.zza.zzaz().zzg();
        return this.zzj;
    }

    @WorkerThread
    @Nullable
    public String zzx() {
        this.zza.zzaz().zzg();
        return this.zzf;
    }

    @WorkerThread
    @Nullable
    public String zzy() {
        this.zza.zzaz().zzg();
        return this.zzd;
    }

    @WorkerThread
    @Nullable
    public String zzz() {
        this.zza.zzaz().zzg();
        return this.zzB;
    }
}
