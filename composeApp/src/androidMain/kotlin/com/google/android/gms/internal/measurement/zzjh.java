package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzjh {
    private final zzjg zza;

    private zzjh(zzjg zzjg) {
        zzkh.zzf(zzjg, "output");
        this.zza = zzjg;
        zzjg.zza = this;
    }

    public static zzjh zza(zzjg zzjg) {
        zzjh zzjh = zzjg.zza;
        return null != zzjh ? zzjh : new zzjh(zzjg);
    }

    public void zzA(int i2, int i3) throws IOException {
        this.zza.zzp(i2, (i3 >> 31) ^ (i3 + i3));
    }

    public void zzC(int i2, long j2) throws IOException {
        this.zza.zzr(i2, (j2 >> 63) ^ (j2 + j2));
    }

    @Deprecated
    public void zzE(int i2) throws IOException {
        this.zza.zzo(i2, 3);
    }

    public void zzF(int i2, String str) throws IOException {
        this.zza.zzm(i2, str);
    }

    public void zzG(int i2, List list) throws IOException {
        int i3 = 0;
        if (list instanceof final zzko zzko) {
            while (i3 < list.size()) {
                Object zzf = zzko.zzf(i3);
                if (zzf instanceof String) {
                    this.zza.zzm(i2, (String) zzf);
                } else {
                    this.zza.zze(i2, (zziy) zzf);
                }
                i3++;
            }
            return;
        }
        while (i3 < list.size()) {
            this.zza.zzm(i2, (String) list.get(i3));
            i3++;
        }
    }

    public void zzH(int i2, int i3) throws IOException {
        this.zza.zzp(i2, i3);
    }

    public void zzJ(int i2, long j2) throws IOException {
        this.zza.zzr(i2, j2);
    }

    public void zzb(int i2, boolean z) throws IOException {
        this.zza.zzd(i2, z);
    }

    public void zzd(int i2, zziy zziy) throws IOException {
        this.zza.zze(i2, zziy);
    }

    public void zze(int i2, List list) throws IOException {
        for (int i3 = 0; i3 < list.size(); i3++) {
            this.zza.zze(i2, (zziy) list.get(i3));
        }
    }

    public void zzf(int i2, double d2) throws IOException {
        this.zza.zzh(i2, Double.doubleToRawLongBits(d2));
    }

    @Deprecated
    public void zzh(int i2) throws IOException {
        this.zza.zzo(i2, 4);
    }

    public void zzi(int i2, int i3) throws IOException {
        this.zza.zzj(i2, i3);
    }

    public void zzk(int i2, int i3) throws IOException {
        this.zza.zzf(i2, i3);
    }

    public void zzm(int i2, long j2) throws IOException {
        this.zza.zzh(i2, j2);
    }

    public void zzo(int i2, float f2) throws IOException {
        this.zza.zzf(i2, Float.floatToRawIntBits(f2));
    }

    public void zzq(int i2, Object obj, zzlr zzlr) throws IOException {
        zzjg zzjg = this.zza;
        zzjg.zzo(i2, 3);
        zzlr.zzm((zzlg) obj, zzjg.zza);
        zzjg.zzo(i2, 4);
    }

    public void zzr(int i2, int i3) throws IOException {
        this.zza.zzj(i2, i3);
    }

    public void zzt(int i2, long j2) throws IOException {
        this.zza.zzr(i2, j2);
    }

    public void zzv(int i2, Object obj, zzlr zzlr) throws IOException {
        zzlg zzlg = (zzlg) obj;
        zzjd zzjd = (zzjd) this.zza;
        zzjd.zzq((i2 << 3) | 2);
        zzih zzih = (zzih) zzlg;
        int zzbm = zzih.zzbm();
        if (-1 == zzbm) {
            zzbm = zzlr.zza(zzih);
            zzih.zzbp(zzbm);
        }
        zzjd.zzq(zzbm);
        zzlr.zzm(zzlg, zzjd.zza);
    }

    public void zzw(int i2, int i3) throws IOException {
        this.zza.zzf(i2, i3);
    }

    public void zzy(int i2, long j2) throws IOException {
        this.zza.zzh(i2, j2);
    }

    public void zzB(int i2, List list, boolean z) throws IOException {
        int i3 = 0;
        if (z) {
            this.zza.zzo(i2, 2);
            int i4 = 0;
            for (int i5 = 0; i5 < list.size(); i5++) {
                int intValue = ((Integer) list.get(i5)).intValue();
                i4 += zzjg.zzA((intValue >> 31) ^ (intValue + intValue));
            }
            this.zza.zzq(i4);
            while (i3 < list.size()) {
                zzjg zzjg = this.zza;
                int intValue2 = ((Integer) list.get(i3)).intValue();
                zzjg.zzq((intValue2 >> 31) ^ (intValue2 + intValue2));
                i3++;
            }
            return;
        }
        while (i3 < list.size()) {
            zzjg zzjg2 = this.zza;
            int intValue3 = ((Integer) list.get(i3)).intValue();
            zzjg2.zzp(i2, (intValue3 >> 31) ^ (intValue3 + intValue3));
            i3++;
        }
    }

    public void zzD(int i2, List list, boolean z) throws IOException {
        int i3 = 0;
        if (z) {
            this.zza.zzo(i2, 2);
            int i4 = 0;
            for (int i5 = 0; i5 < list.size(); i5++) {
                long longValue = ((Long) list.get(i5)).longValue();
                i4 += zzjg.zzB((longValue >> 63) ^ (longValue + longValue));
            }
            this.zza.zzq(i4);
            while (i3 < list.size()) {
                zzjg zzjg = this.zza;
                long longValue2 = ((Long) list.get(i3)).longValue();
                zzjg.zzs((longValue2 >> 63) ^ (longValue2 + longValue2));
                i3++;
            }
            return;
        }
        while (i3 < list.size()) {
            zzjg zzjg2 = this.zza;
            long longValue3 = ((Long) list.get(i3)).longValue();
            zzjg2.zzr(i2, (longValue3 >> 63) ^ (longValue3 + longValue3));
            i3++;
        }
    }

    public void zzI(int i2, List list, boolean z) throws IOException {
        int i3 = 0;
        if (z) {
            this.zza.zzo(i2, 2);
            int i4 = 0;
            for (int i5 = 0; i5 < list.size(); i5++) {
                i4 += zzjg.zzA(((Integer) list.get(i5)).intValue());
            }
            this.zza.zzq(i4);
            while (i3 < list.size()) {
                this.zza.zzq(((Integer) list.get(i3)).intValue());
                i3++;
            }
            return;
        }
        while (i3 < list.size()) {
            this.zza.zzp(i2, ((Integer) list.get(i3)).intValue());
            i3++;
        }
    }

    public void zzK(int i2, List list, boolean z) throws IOException {
        int i3 = 0;
        if (z) {
            this.zza.zzo(i2, 2);
            int i4 = 0;
            for (int i5 = 0; i5 < list.size(); i5++) {
                i4 += zzjg.zzB(((Long) list.get(i5)).longValue());
            }
            this.zza.zzq(i4);
            while (i3 < list.size()) {
                this.zza.zzs(((Long) list.get(i3)).longValue());
                i3++;
            }
            return;
        }
        while (i3 < list.size()) {
            this.zza.zzr(i2, ((Long) list.get(i3)).longValue());
            i3++;
        }
    }

    public void zzc(int i2, List list, boolean z) throws IOException {
        int i3 = 0;
        if (z) {
            this.zza.zzo(i2, 2);
            int i4 = 0;
            for (int i5 = 0; i5 < list.size(); i5++) {
                ((Boolean) list.get(i5)).booleanValue();
                i4++;
            }
            this.zza.zzq(i4);
            while (i3 < list.size()) {
                this.zza.zzb(((Boolean) list.get(i3)).booleanValue() ? (byte) 1 : 0);
                i3++;
            }
            return;
        }
        while (i3 < list.size()) {
            this.zza.zzd(i2, ((Boolean) list.get(i3)).booleanValue());
            i3++;
        }
    }

    public void zzj(int i2, List list, boolean z) throws IOException {
        int i3 = 0;
        if (z) {
            this.zza.zzo(i2, 2);
            int i4 = 0;
            for (int i5 = 0; i5 < list.size(); i5++) {
                i4 += zzjg.zzv(((Integer) list.get(i5)).intValue());
            }
            this.zza.zzq(i4);
            while (i3 < list.size()) {
                this.zza.zzk(((Integer) list.get(i3)).intValue());
                i3++;
            }
            return;
        }
        while (i3 < list.size()) {
            this.zza.zzj(i2, ((Integer) list.get(i3)).intValue());
            i3++;
        }
    }

    public void zzl(int i2, List list, boolean z) throws IOException {
        int i3 = 0;
        if (z) {
            this.zza.zzo(i2, 2);
            int i4 = 0;
            for (int i5 = 0; i5 < list.size(); i5++) {
                ((Integer) list.get(i5)).intValue();
                i4 += 4;
            }
            this.zza.zzq(i4);
            while (i3 < list.size()) {
                this.zza.zzg(((Integer) list.get(i3)).intValue());
                i3++;
            }
            return;
        }
        while (i3 < list.size()) {
            this.zza.zzf(i2, ((Integer) list.get(i3)).intValue());
            i3++;
        }
    }

    public void zzn(int i2, List list, boolean z) throws IOException {
        int i3 = 0;
        if (z) {
            this.zza.zzo(i2, 2);
            int i4 = 0;
            for (int i5 = 0; i5 < list.size(); i5++) {
                ((Long) list.get(i5)).longValue();
                i4 += 8;
            }
            this.zza.zzq(i4);
            while (i3 < list.size()) {
                this.zza.zzi(((Long) list.get(i3)).longValue());
                i3++;
            }
            return;
        }
        while (i3 < list.size()) {
            this.zza.zzh(i2, ((Long) list.get(i3)).longValue());
            i3++;
        }
    }

    public void zzs(int i2, List list, boolean z) throws IOException {
        int i3 = 0;
        if (z) {
            this.zza.zzo(i2, 2);
            int i4 = 0;
            for (int i5 = 0; i5 < list.size(); i5++) {
                i4 += zzjg.zzv(((Integer) list.get(i5)).intValue());
            }
            this.zza.zzq(i4);
            while (i3 < list.size()) {
                this.zza.zzk(((Integer) list.get(i3)).intValue());
                i3++;
            }
            return;
        }
        while (i3 < list.size()) {
            this.zza.zzj(i2, ((Integer) list.get(i3)).intValue());
            i3++;
        }
    }

    public void zzu(int i2, List list, boolean z) throws IOException {
        int i3 = 0;
        if (z) {
            this.zza.zzo(i2, 2);
            int i4 = 0;
            for (int i5 = 0; i5 < list.size(); i5++) {
                i4 += zzjg.zzB(((Long) list.get(i5)).longValue());
            }
            this.zza.zzq(i4);
            while (i3 < list.size()) {
                this.zza.zzs(((Long) list.get(i3)).longValue());
                i3++;
            }
            return;
        }
        while (i3 < list.size()) {
            this.zza.zzr(i2, ((Long) list.get(i3)).longValue());
            i3++;
        }
    }

    public void zzx(int i2, List list, boolean z) throws IOException {
        int i3 = 0;
        if (z) {
            this.zza.zzo(i2, 2);
            int i4 = 0;
            for (int i5 = 0; i5 < list.size(); i5++) {
                ((Integer) list.get(i5)).intValue();
                i4 += 4;
            }
            this.zza.zzq(i4);
            while (i3 < list.size()) {
                this.zza.zzg(((Integer) list.get(i3)).intValue());
                i3++;
            }
            return;
        }
        while (i3 < list.size()) {
            this.zza.zzf(i2, ((Integer) list.get(i3)).intValue());
            i3++;
        }
    }

    public void zzz(int i2, List list, boolean z) throws IOException {
        int i3 = 0;
        if (z) {
            this.zza.zzo(i2, 2);
            int i4 = 0;
            for (int i5 = 0; i5 < list.size(); i5++) {
                ((Long) list.get(i5)).longValue();
                i4 += 8;
            }
            this.zza.zzq(i4);
            while (i3 < list.size()) {
                this.zza.zzi(((Long) list.get(i3)).longValue());
                i3++;
            }
            return;
        }
        while (i3 < list.size()) {
            this.zza.zzh(i2, ((Long) list.get(i3)).longValue());
            i3++;
        }
    }

    public void zzg(int i2, List list, boolean z) throws IOException {
        int i3 = 0;
        if (z) {
            this.zza.zzo(i2, 2);
            int i4 = 0;
            for (int i5 = 0; i5 < list.size(); i5++) {
                ((Double) list.get(i5)).doubleValue();
                i4 += 8;
            }
            this.zza.zzq(i4);
            while (i3 < list.size()) {
                this.zza.zzi(Double.doubleToRawLongBits(((Double) list.get(i3)).doubleValue()));
                i3++;
            }
            return;
        }
        while (i3 < list.size()) {
            this.zza.zzh(i2, Double.doubleToRawLongBits(((Double) list.get(i3)).doubleValue()));
            i3++;
        }
    }

    public void zzp(int i2, List list, boolean z) throws IOException {
        int i3 = 0;
        if (z) {
            this.zza.zzo(i2, 2);
            int i4 = 0;
            for (int i5 = 0; i5 < list.size(); i5++) {
                ((Float) list.get(i5)).floatValue();
                i4 += 4;
            }
            this.zza.zzq(i4);
            while (i3 < list.size()) {
                this.zza.zzg(Float.floatToRawIntBits(((Float) list.get(i3)).floatValue()));
                i3++;
            }
            return;
        }
        while (i3 < list.size()) {
            this.zza.zzf(i2, Float.floatToRawIntBits(((Float) list.get(i3)).floatValue()));
            i3++;
        }
    }
}
