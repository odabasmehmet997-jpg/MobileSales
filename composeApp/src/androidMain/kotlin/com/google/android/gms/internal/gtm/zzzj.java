package com.google.android.gms.internal.gtm;

import java.io.IOException;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzzj implements zzaez {
    private final zzzi zza;

    private zzzj(final zzzi zzzi) {
        final byte[] bArr = zzaco.zzb;
        zza = zzzi;
        zzzi.zze = this;
    }

    public static zzzj zza(final zzzi zzzi) {
        final zzzj zzzj = zzzi.zze;
        return null != zzzj ? zzzj : new zzzj(zzzi);
    }

    public void zzB(final int i2, final int i3) throws IOException {
        zza.zzt(i2, (i3 >> 31) ^ (i3 + i3));
    }

    public void zzD(final int i2, final long j2) throws IOException {
        zza.zzv(i2, (j2 >> 63) ^ (j2 + j2));
    }

    @Deprecated
    public void zzF(final int i2) throws IOException {
        zza.zzs(i2, 3);
    }

    public void zzG(final int i2, final String str) throws IOException {
        zza.zzq(i2, str);
    }

    public void zzI(final int i2, final int i3) throws IOException {
        zza.zzt(i2, i3);
    }

    public void zzK(final int i2, final long j2) throws IOException {
        zza.zzv(i2, j2);
    }

    public void zzb(final int i2, final boolean z) throws IOException {
        zza.zzK(i2, z);
    }

    public void zzd(final int i2, final zzyx zzyx) throws IOException {
        zza.zzL(i2, zzyx);
    }

    public void zze(final int i2, final List list) throws IOException {
        for (int i3 = 0; i3 < list.size(); i3++) {
            zza.zzL(i2, (zzyx) list.get(i3));
        }
    }

    public void zzf(final int i2, final double d2) throws IOException {
        zza.zzj(i2, Double.doubleToRawLongBits(d2));
    }

    @Deprecated
    public void zzh(final int i2) throws IOException {
        zza.zzs(i2, 4);
    }

    public void zzi(final int i2, final int i3) throws IOException {
        zza.zzl(i2, i3);
    }

    public void zzk(final int i2, final int i3) throws IOException {
        zza.zzh(i2, i3);
    }

    public void zzm(final int i2, final long j2) throws IOException {
        zza.zzj(i2, j2);
    }

    public void zzo(final int i2, final float f2) throws IOException {
        zza.zzh(i2, Float.floatToRawIntBits(f2));
    }

    public void zzq(final int i2, final Object obj, final zzadx zzadx) throws IOException {
        final zzzi zzzi = zza;
        zzzi.zzs(i2, 3);
        zzadx.zzj((zzadl) obj, zzzi.zze);
        zzzi.zzs(i2, 4);
    }

    public void zzr(final int i2, final int i3) throws IOException {
        zza.zzl(i2, i3);
    }

    public void zzt(final int i2, final long j2) throws IOException {
        zza.zzv(i2, j2);
    }

    public void zzv(final int i2, final Object obj, final zzadx zzadx) throws IOException {
        zza.zzn(i2, (zzadl) obj, zzadx);
    }

    public void zzw(final int i2, final Object obj) throws IOException {
        if (obj instanceof zzyx) {
            zza.zzp(i2, (zzyx) obj);
        } else {
            zza.zzo(i2, (zzadl) obj);
        }
    }

    public void zzx(final int i2, final int i3) throws IOException {
        zza.zzh(i2, i3);
    }

    public void zzz(final int i2, final long j2) throws IOException {
        zza.zzj(i2, j2);
    }

    public void zzH(final int i2, final List list) throws IOException {
        int i3 = 0;
        if (list instanceof zzacx zzacx) {
            while (i3 < list.size()) {
                final Object zzb = zzacx.zzb();
                if (zzb instanceof String) {
                    zza.zzq(i2, (String) zzb);
                } else {
                    zza.zzL(i2, (zzyx) zzb);
                }
                i3++;
            }
            return;
        }
        while (i3 < list.size()) {
            zza.zzq(i2, (String) list.get(i3));
            i3++;
        }
    }

    public void zzJ(final int i2, final List list, final boolean z) throws IOException {
        int i3 = 0;
        if (list instanceof zzacg zzacg) {
            if (z) {
                zza.zzs(i2, 2);
                int i4 = 0;
                for (int i5 = 0; i5 < zzacg.size(); i5++) {
                    i4 += zzzi.zzC(zzacg.zze(i5));
                }
                zza.zzu(i4);
                while (i3 < zzacg.size()) {
                    zza.zzu(zzacg.zze(i3));
                    i3++;
                }
                return;
            }
            while (i3 < zzacg.size()) {
                zza.zzt(i2, zzacg.zze(i3));
                i3++;
            }
        } else if (z) {
            zza.zzs(i2, 2);
            int i6 = 0;
            for (int i7 = 0; i7 < list.size(); i7++) {
                i6 += zzzi.zzC(((Integer) list.get(i7)).intValue());
            }
            zza.zzu(i6);
            while (i3 < list.size()) {
                zza.zzu(((Integer) list.get(i3)).intValue());
                i3++;
            }
        } else {
            while (i3 < list.size()) {
                zza.zzt(i2, ((Integer) list.get(i3)).intValue());
                i3++;
            }
        }
    }

    public void zzL(final int i2, final List list, final boolean z) throws IOException {
        int i3 = 0;
        if (list instanceof zzada zzada) {
            if (z) {
                zza.zzs(i2, 2);
                int i4 = 0;
                for (int i5 = 0; i5 < zzada.size(); i5++) {
                    i4 += zzzi.zzD(zzada.zze(i5));
                }
                zza.zzu(i4);
                while (i3 < zzada.size()) {
                    zza.zzw(zzada.zze(i3));
                    i3++;
                }
                return;
            }
            while (i3 < zzada.size()) {
                zza.zzv(i2, zzada.zze(i3));
                i3++;
            }
        } else if (z) {
            zza.zzs(i2, 2);
            int i6 = 0;
            for (int i7 = 0; i7 < list.size(); i7++) {
                i6 += zzzi.zzD(((Long) list.get(i7)).longValue());
            }
            zza.zzu(i6);
            while (i3 < list.size()) {
                zza.zzw(((Long) list.get(i3)).longValue());
                i3++;
            }
        } else {
            while (i3 < list.size()) {
                zza.zzv(i2, ((Long) list.get(i3)).longValue());
                i3++;
            }
        }
    }

    public void zzl(final int i2, final List list, final boolean z) throws IOException {
        int i3 = 0;
        if (list instanceof zzacg zzacg) {
            if (z) {
                zza.zzs(i2, 2);
                int i4 = 0;
                for (int i5 = 0; i5 < zzacg.size(); i5++) {
                    zzacg.zze(i5);
                    i4 += 4;
                }
                zza.zzu(i4);
                while (i3 < zzacg.size()) {
                    zza.zzi(zzacg.zze(i3));
                    i3++;
                }
                return;
            }
            while (i3 < zzacg.size()) {
                zza.zzh(i2, zzacg.zze(i3));
                i3++;
            }
        } else if (z) {
            zza.zzs(i2, 2);
            int i6 = 0;
            for (int i7 = 0; i7 < list.size(); i7++) {
                ((Integer) list.get(i7)).intValue();
                i6 += 4;
            }
            zza.zzu(i6);
            while (i3 < list.size()) {
                zza.zzi(((Integer) list.get(i3)).intValue());
                i3++;
            }
        } else {
            while (i3 < list.size()) {
                zza.zzh(i2, ((Integer) list.get(i3)).intValue());
                i3++;
            }
        }
    }

    public void zzn(final int i2, final List list, final boolean z) throws IOException {
        int i3 = 0;
        if (list instanceof zzada zzada) {
            if (z) {
                zza.zzs(i2, 2);
                int i4 = 0;
                for (int i5 = 0; i5 < zzada.size(); i5++) {
                    zzada.zze(i5);
                    i4 += 8;
                }
                zza.zzu(i4);
                while (i3 < zzada.size()) {
                    zza.zzk(zzada.zze(i3));
                    i3++;
                }
                return;
            }
            while (i3 < zzada.size()) {
                zza.zzj(i2, zzada.zze(i3));
                i3++;
            }
        } else if (z) {
            zza.zzs(i2, 2);
            int i6 = 0;
            for (int i7 = 0; i7 < list.size(); i7++) {
                ((Long) list.get(i7)).longValue();
                i6 += 8;
            }
            zza.zzu(i6);
            while (i3 < list.size()) {
                zza.zzk(((Long) list.get(i3)).longValue());
                i3++;
            }
        } else {
            while (i3 < list.size()) {
                zza.zzj(i2, ((Long) list.get(i3)).longValue());
                i3++;
            }
        }
    }

    public void zzc(final int i2, final List list, final boolean z) throws IOException {
        int i3 = 0;
        if (list instanceof zzyo zzyo) {
            if (z) {
                zza.zzs(i2, 2);
                int i4 = 0;
                for (int i5 = 0; i5 < zzyo.size(); i5++) {
                    zzyo.zzf(i5);
                    i4++;
                }
                zza.zzu(i4);
                while (i3 < zzyo.size()) {
                    zza.zzJ(zzyo.zzf(i3) ? (byte) 1 : 0);
                    i3++;
                }
                return;
            }
            while (i3 < zzyo.size()) {
                zza.zzK(i2, zzyo.zzf(i3));
                i3++;
            }
        } else if (z) {
            zza.zzs(i2, 2);
            int i6 = 0;
            for (int i7 = 0; i7 < list.size(); i7++) {
                ((Boolean) list.get(i7)).booleanValue();
                i6++;
            }
            zza.zzu(i6);
            while (i3 < list.size()) {
                zza.zzJ(((Boolean) list.get(i3)).booleanValue() ? (byte) 1 : 0);
                i3++;
            }
        } else {
            while (i3 < list.size()) {
                zza.zzK(i2, ((Boolean) list.get(i3)).booleanValue());
                i3++;
            }
        }
    }

    public void zzs(final int i2, final List list, final boolean z) throws IOException {
        int i3 = 0;
        if (list instanceof zzacg zzacg) {
            if (z) {
                zza.zzs(i2, 2);
                int i4 = 0;
                for (int i5 = 0; i5 < zzacg.size(); i5++) {
                    i4 += zzzi.zzD(zzacg.zze(i5));
                }
                zza.zzu(i4);
                while (i3 < zzacg.size()) {
                    zza.zzm(zzacg.zze(i3));
                    i3++;
                }
                return;
            }
            while (i3 < zzacg.size()) {
                zza.zzl(i2, zzacg.zze(i3));
                i3++;
            }
        } else if (z) {
            zza.zzs(i2, 2);
            int i6 = 0;
            for (int i7 = 0; i7 < list.size(); i7++) {
                i6 += zzzi.zzD(((Integer) list.get(i7)).intValue());
            }
            zza.zzu(i6);
            while (i3 < list.size()) {
                zza.zzm(((Integer) list.get(i3)).intValue());
                i3++;
            }
        } else {
            while (i3 < list.size()) {
                zza.zzl(i2, ((Integer) list.get(i3)).intValue());
                i3++;
            }
        }
    }

    public void zzA(final int i2, final List list, final boolean z) throws IOException {
        int i3 = 0;
        if (list instanceof zzada zzada) {
            if (z) {
                zza.zzs(i2, 2);
                int i4 = 0;
                for (int i5 = 0; i5 < zzada.size(); i5++) {
                    zzada.zze(i5);
                    i4 += 8;
                }
                zza.zzu(i4);
                while (i3 < zzada.size()) {
                    zza.zzk(zzada.zze(i3));
                    i3++;
                }
                return;
            }
            while (i3 < zzada.size()) {
                zza.zzj(i2, zzada.zze(i3));
                i3++;
            }
        } else if (z) {
            zza.zzs(i2, 2);
            int i6 = 0;
            for (int i7 = 0; i7 < list.size(); i7++) {
                ((Long) list.get(i7)).longValue();
                i6 += 8;
            }
            zza.zzu(i6);
            while (i3 < list.size()) {
                zza.zzk(((Long) list.get(i3)).longValue());
                i3++;
            }
        } else {
            while (i3 < list.size()) {
                zza.zzj(i2, ((Long) list.get(i3)).longValue());
                i3++;
            }
        }
    }

    public void zzg(final int i2, final List list, final boolean z) throws IOException {
        int i3 = 0;
        if (list instanceof zzabn zzabn) {
            if (z) {
                zza.zzs(i2, 2);
                int i4 = 0;
                for (int i5 = 0; i5 < zzabn.size(); i5++) {
                    zzabn.zze(i5);
                    i4 += 8;
                }
                zza.zzu(i4);
                while (i3 < zzabn.size()) {
                    zza.zzk(Double.doubleToRawLongBits(zzabn.zze(i3)));
                    i3++;
                }
                return;
            }
            while (i3 < zzabn.size()) {
                zza.zzj(i2, Double.doubleToRawLongBits(zzabn.zze(i3)));
                i3++;
            }
        } else if (z) {
            zza.zzs(i2, 2);
            int i6 = 0;
            for (int i7 = 0; i7 < list.size(); i7++) {
                ((Double) list.get(i7)).doubleValue();
                i6 += 8;
            }
            zza.zzu(i6);
            while (i3 < list.size()) {
                zza.zzk(Double.doubleToRawLongBits(((Double) list.get(i3)).doubleValue()));
                i3++;
            }
        } else {
            while (i3 < list.size()) {
                zza.zzj(i2, Double.doubleToRawLongBits(((Double) list.get(i3)).doubleValue()));
                i3++;
            }
        }
    }

    public void zzp(final int i2, final List list, final boolean z) throws IOException {
        int i3 = 0;
        if (list instanceof zzabx zzabx) {
            if (z) {
                zza.zzs(i2, 2);
                int i4 = 0;
                for (int i5 = 0; i5 < zzabx.size(); i5++) {
                    zzabx.zze(i5);
                    i4 += 4;
                }
                zza.zzu(i4);
                while (i3 < zzabx.size()) {
                    zza.zzi(Float.floatToRawIntBits(zzabx.zze(i3)));
                    i3++;
                }
                return;
            }
            while (i3 < zzabx.size()) {
                zza.zzh(i2, Float.floatToRawIntBits(zzabx.zze(i3)));
                i3++;
            }
        } else if (z) {
            zza.zzs(i2, 2);
            int i6 = 0;
            for (int i7 = 0; i7 < list.size(); i7++) {
                ((Float) list.get(i7)).floatValue();
                i6 += 4;
            }
            zza.zzu(i6);
            while (i3 < list.size()) {
                zza.zzi(Float.floatToRawIntBits(((Float) list.get(i3)).floatValue()));
                i3++;
            }
        } else {
            while (i3 < list.size()) {
                zza.zzh(i2, Float.floatToRawIntBits(((Float) list.get(i3)).floatValue()));
                i3++;
            }
        }
    }

    public void zzy(final int i2, final List list, final boolean z) throws IOException {
        int i3 = 0;
        if (list instanceof zzacg zzacg) {
            if (z) {
                zza.zzs(i2, 2);
                int i4 = 0;
                for (int i5 = 0; i5 < zzacg.size(); i5++) {
                    zzacg.zze(i5);
                    i4 += 4;
                }
                zza.zzu(i4);
                while (i3 < zzacg.size()) {
                    zza.zzi(zzacg.zze(i3));
                    i3++;
                }
                return;
            }
            while (i3 < zzacg.size()) {
                zza.zzh(i2, zzacg.zze(i3));
                i3++;
            }
        } else if (z) {
            zza.zzs(i2, 2);
            int i6 = 0;
            for (int i7 = 0; i7 < list.size(); i7++) {
                ((Integer) list.get(i7)).intValue();
                i6 += 4;
            }
            zza.zzu(i6);
            while (i3 < list.size()) {
                zza.zzi(((Integer) list.get(i3)).intValue());
                i3++;
            }
        } else {
            while (i3 < list.size()) {
                zza.zzh(i2, ((Integer) list.get(i3)).intValue());
                i3++;
            }
        }
    }

    public void zzC(final int i2, final List list, final boolean z) throws IOException {
        int i3 = 0;
        if (list instanceof zzacg zzacg) {
            if (z) {
                zza.zzs(i2, 2);
                int i4 = 0;
                for (int i5 = 0; i5 < zzacg.size(); i5++) {
                    final int zze = zzacg.zze(i5);
                    i4 += zzzi.zzC((zze >> 31) ^ (zze + zze));
                }
                zza.zzu(i4);
                while (i3 < zzacg.size()) {
                    final zzzi zzzi = zza;
                    final int zze2 = zzacg.zze(i3);
                    zzzi.zzu((zze2 >> 31) ^ (zze2 + zze2));
                    i3++;
                }
                return;
            }
            while (i3 < zzacg.size()) {
                final zzzi zzzi2 = zza;
                final int zze3 = zzacg.zze(i3);
                zzzi2.zzt(i2, (zze3 >> 31) ^ (zze3 + zze3));
                i3++;
            }
        } else if (z) {
            zza.zzs(i2, 2);
            int i6 = 0;
            for (int i7 = 0; i7 < list.size(); i7++) {
                final int intValue = ((Integer) list.get(i7)).intValue();
                i6 += zzzi.zzC((intValue >> 31) ^ (intValue + intValue));
            }
            zza.zzu(i6);
            while (i3 < list.size()) {
                final zzzi zzzi3 = zza;
                final int intValue2 = ((Integer) list.get(i3)).intValue();
                zzzi3.zzu((intValue2 >> 31) ^ (intValue2 + intValue2));
                i3++;
            }
        } else {
            while (i3 < list.size()) {
                final zzzi zzzi4 = zza;
                final int intValue3 = ((Integer) list.get(i3)).intValue();
                zzzi4.zzt(i2, (intValue3 >> 31) ^ (intValue3 + intValue3));
                i3++;
            }
        }
    }

    public void zzE(final int i2, final List list, final boolean z) throws IOException {
        int i3 = 0;
        if (list instanceof zzada zzada) {
            if (z) {
                zza.zzs(i2, 2);
                int i4 = 0;
                for (int i5 = 0; i5 < zzada.size(); i5++) {
                    final long zze = zzada.zze(i5);
                    i4 += zzzi.zzD((zze >> 63) ^ (zze + zze));
                }
                zza.zzu(i4);
                while (i3 < zzada.size()) {
                    final zzzi zzzi = zza;
                    final long zze2 = zzada.zze(i3);
                    zzzi.zzw((zze2 >> 63) ^ (zze2 + zze2));
                    i3++;
                }
                return;
            }
            while (i3 < zzada.size()) {
                final zzzi zzzi2 = zza;
                final long zze3 = zzada.zze(i3);
                zzzi2.zzv(i2, (zze3 >> 63) ^ (zze3 + zze3));
                i3++;
            }
        } else if (z) {
            zza.zzs(i2, 2);
            int i6 = 0;
            for (int i7 = 0; i7 < list.size(); i7++) {
                final long longValue = ((Long) list.get(i7)).longValue();
                i6 += zzzi.zzD((longValue >> 63) ^ (longValue + longValue));
            }
            zza.zzu(i6);
            while (i3 < list.size()) {
                final zzzi zzzi3 = zza;
                final long longValue2 = ((Long) list.get(i3)).longValue();
                zzzi3.zzw((longValue2 >> 63) ^ (longValue2 + longValue2));
                i3++;
            }
        } else {
            while (i3 < list.size()) {
                final zzzi zzzi4 = zza;
                final long longValue3 = ((Long) list.get(i3)).longValue();
                zzzi4.zzv(i2, (longValue3 >> 63) ^ (longValue3 + longValue3));
                i3++;
            }
        }
    }

    public void zzj(final int i2, final List list, final boolean z) throws IOException {
        int i3 = 0;
        if (list instanceof zzacg zzacg) {
            if (z) {
                zza.zzs(i2, 2);
                int i4 = 0;
                for (int i5 = 0; i5 < zzacg.size(); i5++) {
                    i4 += zzzi.zzD(zzacg.zze(i5));
                }
                zza.zzu(i4);
                while (i3 < zzacg.size()) {
                    zza.zzm(zzacg.zze(i3));
                    i3++;
                }
                return;
            }
            while (i3 < zzacg.size()) {
                zza.zzl(i2, zzacg.zze(i3));
                i3++;
            }
        } else if (z) {
            zza.zzs(i2, 2);
            int i6 = 0;
            for (int i7 = 0; i7 < list.size(); i7++) {
                i6 += zzzi.zzD(((Integer) list.get(i7)).intValue());
            }
            zza.zzu(i6);
            while (i3 < list.size()) {
                zza.zzm(((Integer) list.get(i3)).intValue());
                i3++;
            }
        } else {
            while (i3 < list.size()) {
                zza.zzl(i2, ((Integer) list.get(i3)).intValue());
                i3++;
            }
        }
    }

    public void zzu(final int i2, final List list, final boolean z) throws IOException {
        int i3 = 0;
        if (list instanceof zzada zzada) {
            if (z) {
                zza.zzs(i2, 2);
                int i4 = 0;
                for (int i5 = 0; i5 < zzada.size(); i5++) {
                    i4 += zzzi.zzD(zzada.zze(i5));
                }
                zza.zzu(i4);
                while (i3 < zzada.size()) {
                    zza.zzw(zzada.zze(i3));
                    i3++;
                }
                return;
            }
            while (i3 < zzada.size()) {
                zza.zzv(i2, zzada.zze(i3));
                i3++;
            }
        } else if (z) {
            zza.zzs(i2, 2);
            int i6 = 0;
            for (int i7 = 0; i7 < list.size(); i7++) {
                i6 += zzzi.zzD(((Long) list.get(i7)).longValue());
            }
            zza.zzu(i6);
            while (i3 < list.size()) {
                zza.zzw(((Long) list.get(i3)).longValue());
                i3++;
            }
        } else {
            while (i3 < list.size()) {
                zza.zzv(i2, ((Long) list.get(i3)).longValue());
                i3++;
            }
        }
    }
}
