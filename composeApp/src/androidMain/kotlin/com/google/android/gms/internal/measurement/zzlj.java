package com.google.android.gms.internal.measurement;

import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

final class zzlj<T> implements zzlr<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzms.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzlg zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final int[] zzj;
    private final int zzk;
    private final int zzl;
    private final zzku zzm;
    private final zzmi zzn;
    private final zzjm zzo;
    private final zzll zzp;
    private final zzlb zzq;

    private zzlj(int[] iArr, Object[] objArr, int i2, int i3, zzlg zzlg, boolean z, boolean z2, int[] iArr2, int i4, int i5, zzll zzll, zzku zzku, zzmi zzmi, zzjm zzjm, zzlb zzlb, byte[] bArr) {
        zzlg zzlg2 = zzlg;
        zzjm zzjm2 = zzjm;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i2;
        this.zzf = i3;
        this.zzi = z;
        final boolean z3 = null != zzjm2 && zzjm2.zzc(zzlg);
        this.zzh = z3;
        this.zzj = iArr2;
        this.zzk = i4;
        this.zzl = i5;
        this.zzp = zzll;
        this.zzm = zzku;
        this.zzn = zzmi;
        this.zzo = zzjm2;
        this.zzg = zzlg2;
        this.zzq = zzlb;
    }

    private static int zzA(int i2) {
        return (i2 >>> 20) & 255;
    }

    private int zzB(int i2) {
        return this.zzc[i2 + 1];
    }

    private static long zzC(Object obj, long j2) {
        return ((Long) zzms.zzf(obj, j2)).longValue();
    }

    private zzkd zzD(int i2) {
        int i3 = i2 / 3;
        return (zzkd) this.zzd[i3 + i3 + 1];
    }

    private zzlr zzE(int i2) {
        int i3 = i2 / 3;
        int i4 = i3 + i3;
        zzlr zzlr = (zzlr) this.zzd[i4];
        if (null != zzlr) {
            return zzlr;
        }
        zzlr zzb2 = zzlo.zza().zzb((Class) this.zzd[i4 + 1]);
        this.zzd[i4] = zzb2;
        return zzb2;
    }

    private Object zzF(int i2) {
        int i3 = i2 / 3;
        return this.zzd[i3 + i3];
    }

    private static Field zzG(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            final String sb = "Field " +
                    str +
                    " for " +
                    name +
                    " not found. Known fields are " +
                    arrays;
            throw new RuntimeException(sb);
        }
    }

    private void zzH(Object obj, Object obj2, int i2) {
        long zzB = zzB(i2) & 1048575;
        if (zzM(obj2, i2)) {
            Object zzf2 = zzms.zzf(obj, zzB);
            Object zzf3 = zzms.zzf(obj2, zzB);
            if (null != zzf2 && null != zzf3) {
                zzms.zzs(obj, zzB, zzkh.zzg(zzf2, zzf3));
                zzJ(obj, i2);
            } else if (null != zzf3) {
                zzms.zzs(obj, zzB, zzf3);
                zzJ(obj, i2);
            }
        }
    }

    private void zzI(Object obj, Object obj2, int i2) {
        int zzB = zzB(i2);
        int i3 = this.zzc[i2];
        long j2 = zzB & 1048575;
        if (zzP(obj2, i3, i2)) {
            Object zzf2 = zzP(obj, i3, i2) ? zzms.zzf(obj, j2) : null;
            Object zzf3 = zzms.zzf(obj2, j2);
            if (null != zzf2 && null != zzf3) {
                zzms.zzs(obj, j2, zzkh.zzg(zzf2, zzf3));
                zzK(obj, i3, i2);
            } else if (null != zzf3) {
                zzms.zzs(obj, j2, zzf3);
                zzK(obj, i3, i2);
            }
        }
    }

    private void zzJ(Object obj, int i2) {
        int zzy = zzy(i2);
        long j2 = 1048575 & zzy;
        if (1048575 != j2) {
            zzms.zzq(obj, j2, (1 << (zzy >>> 20)) | zzms.zzc(obj, j2));
        }
    }

    private void zzK(Object obj, int i2, int i3) {
        zzms.zzq(obj, zzy(i3) & 1048575, i2);
    }

    private boolean zzL(Object obj, Object obj2, int i2) {
        return zzM(obj, i2) == zzM(obj2, i2);
    }

    private boolean zzM(Object obj, int i2) {
        int zzy = zzy(i2);
        long j2 = zzy & 1048575;
        if (1048575 != j2) {
            return 0 != (zzms.zzc(obj, j2) & (1 << (zzy >>> 20)));
        }
        int zzB = zzB(i2);
        long j3 = zzB & 1048575;
        switch (zzA(zzB)) {
            case 0:
                return 0 != Double.doubleToRawLongBits(zzms.zza(obj, j3));
            case 1:
                return 0 != Float.floatToRawIntBits(zzms.zzb(obj, j3));
            case 2:
                return 0 != zzms.zzd(obj, j3);
            case 3:
                return 0 != zzms.zzd(obj, j3);
            case 4:
                return 0 != zzms.zzc(obj, j3);
            case 5:
                return 0 != zzms.zzd(obj, j3);
            case 6:
                return 0 != zzms.zzc(obj, j3);
            case 7:
                return zzms.zzw(obj, j3);
            case 8:
                Object zzf2 = zzms.zzf(obj, j3);
                if (zzf2 instanceof String) {
                    return !((String) zzf2).isEmpty();
                }
                if (zzf2 instanceof zziy) {
                    return !zziy.zzb.equals(zzf2);
                }
                throw new IllegalArgumentException();
            case 9:
                return null != zzms.zzf(obj, j3);
            case 10:
                return !zziy.zzb.equals(zzms.zzf(obj, j3));
            case 11:
                return 0 != zzms.zzc(obj, j3);
            case 12:
                return 0 != zzms.zzc(obj, j3);
            case 13:
                return 0 != zzms.zzc(obj, j3);
            case 14:
                return 0 != zzms.zzd(obj, j3);
            case 15:
                return 0 != zzms.zzc(obj, j3);
            case 16:
                return 0 != zzms.zzd(obj, j3);
            case 17:
                return null != zzms.zzf(obj, j3);
            default:
                throw new IllegalArgumentException();
        }
    }

    private boolean zzN(Object obj, int i2, int i3, int i4, int i5) {
        if (1048575 == i3) {
            return zzM(obj, i2);
        }
        return 0 != (i4 & i5);
    }

    private static boolean zzO(Object obj, int i2, zzlr zzlr) {
        return zzlr.zzj(zzms.zzf(obj, i2 & 1048575));
    }

    private boolean zzP(Object obj, int i2, int i3) {
        return zzms.zzc(obj, zzy(i3) & 1048575) == i2;
    }

    private static boolean zzQ(Object obj, long j2) {
        return ((Boolean) zzms.zzf(obj, j2)).booleanValue();
    }

    private void zzR(java.lang.Object r17, com.google.android.gms.internal.measurement.zzjh r18) throws java.io.IOException {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            boolean r3 = r0.zzh
            if (r3 != 0) goto L_0x045d
            int[] r3 = r0.zzc
            int r3 = r3.length
            sun.misc.Unsafe r4 = zzb
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r9 = r5
            r7 = 0
            r8 = 0
        L_0x0015:
            if (r7 >= r3) goto L_0x0453
            int r10 = r0.zzB(r7)
            int[] r11 = r0.zzc
            r12 = r11[r7]
            int r13 = zzA(r10)
            r14 = 17
            r15 = 1
            if (r13 > r14) goto L_0x003b
            int r14 = r7 + 2
            r11 = r11[r14]
            r14 = r11 & r5
            if (r14 == r9) goto L_0x0036
            long r8 = (long) r14
            int r8 = r4.getInt(r1, r8)
            r9 = r14
        L_0x0036:
            int r11 = r11 >>> 20
            int r11 = r15 << r11
            goto L_0x003c
        L_0x003b:
            r11 = 0
        L_0x003c:
            r10 = r10 & r5
            long r5 = (long) r10
            switch(r13) {
                case 0: goto L_0x0440;
                case 1: goto L_0x0433;
                case 2: goto L_0x0426;
                case 3: goto L_0x0419;
                case 4: goto L_0x040c;
                case 5: goto L_0x03ff;
                case 6: goto L_0x03f2;
                case 7: goto L_0x03e5;
                case 8: goto L_0x03d7;
                case 9: goto L_0x03c5;
                case 10: goto L_0x03b5;
                case 11: goto L_0x03a7;
                case 12: goto L_0x0399;
                case 13: goto L_0x038b;
                case 14: goto L_0x037d;
                case 15: goto L_0x036f;
                case 16: goto L_0x0361;
                case 17: goto L_0x034f;
                case 18: goto L_0x033f;
                case 19: goto L_0x032f;
                case 20: goto L_0x031f;
                case 21: goto L_0x030f;
                case 22: goto L_0x02ff;
                case 23: goto L_0x02ef;
                case 24: goto L_0x02df;
                case 25: goto L_0x02cf;
                case 26: goto L_0x02c0;
                case 27: goto L_0x02ad;
                case 28: goto L_0x029e;
                case 29: goto L_0x028f;
                case 30: goto L_0x0280;
                case 31: goto L_0x0271;
                case 32: goto L_0x0262;
                case 33: goto L_0x0253;
                case 34: goto L_0x0242;
                case 35: goto L_0x0233;
                case 36: goto L_0x0224;
                case 37: goto L_0x0215;
                case 38: goto L_0x0206;
                case 39: goto L_0x01f7;
                case 40: goto L_0x01e8;
                case 41: goto L_0x01d9;
                case 42: goto L_0x01ca;
                case 43: goto L_0x01bb;
                case 44: goto L_0x01ac;
                case 45: goto L_0x019d;
                case 46: goto L_0x018e;
                case 47: goto L_0x017f;
                case 48: goto L_0x0170;
                case 49: goto L_0x015d;
                case 50: goto L_0x0154;
                case 51: goto L_0x0145;
                case 52: goto L_0x0136;
                case 53: goto L_0x0127;
                case 54: goto L_0x0118;
                case 55: goto L_0x0109;
                case 56: goto L_0x00fa;
                case 57: goto L_0x00eb;
                case 58: goto L_0x00dc;
                case 59: goto L_0x00cd;
                case 60: goto L_0x00ba;
                case 61: goto L_0x00aa;
                case 62: goto L_0x009c;
                case 63: goto L_0x008e;
                case 64: goto L_0x0080;
                case 65: goto L_0x0072;
                case 66: goto L_0x0064;
                case 67: goto L_0x0056;
                case 68: goto L_0x0044;
                default: goto L_0x0041;
            }
        L_0x0041:
            r13 = 0
            goto L_0x044c
        L_0x0044:
            boolean r10 = r0.zzP(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            java.lang.Object r5 = r4.getObject(r1, r5)
            com.google.android.gms.internal.measurement.zzlr r6 = r0.zzE(r7)
            r2.zzq(r12, r5, r6)
            goto L_0x0041
        L_0x0056:
            boolean r10 = r0.zzP(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            long r5 = zzC(r1, r5)
            r2.zzC(r12, r5)
            goto L_0x0041
        L_0x0064:
            boolean r10 = r0.zzP(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            int r5 = zzr(r1, r5)
            r2.zzA(r12, r5)
            goto L_0x0041
        L_0x0072:
            boolean r10 = r0.zzP(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            long r5 = zzC(r1, r5)
            r2.zzy(r12, r5)
            goto L_0x0041
        L_0x0080:
            boolean r10 = r0.zzP(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            int r5 = zzr(r1, r5)
            r2.zzw(r12, r5)
            goto L_0x0041
        L_0x008e:
            boolean r10 = r0.zzP(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            int r5 = zzr(r1, r5)
            r2.zzi(r12, r5)
            goto L_0x0041
        L_0x009c:
            boolean r10 = r0.zzP(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            int r5 = zzr(r1, r5)
            r2.zzH(r12, r5)
            goto L_0x0041
        L_0x00aa:
            boolean r10 = r0.zzP(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            java.lang.Object r5 = r4.getObject(r1, r5)
            com.google.android.gms.internal.measurement.zziy r5 = (com.google.android.gms.internal.measurement.zziy) r5
            r2.zzd(r12, r5)
            goto L_0x0041
        L_0x00ba:
            boolean r10 = r0.zzP(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            java.lang.Object r5 = r4.getObject(r1, r5)
            com.google.android.gms.internal.measurement.zzlr r6 = r0.zzE(r7)
            r2.zzv(r12, r5, r6)
            goto L_0x0041
        L_0x00cd:
            boolean r10 = r0.zzP(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            java.lang.Object r5 = r4.getObject(r1, r5)
            zzT(r12, r5, r2)
            goto L_0x0041
        L_0x00dc:
            boolean r10 = r0.zzP(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            boolean r5 = zzQ(r1, r5)
            r2.zzb(r12, r5)
            goto L_0x0041
        L_0x00eb:
            boolean r10 = r0.zzP(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            int r5 = zzr(r1, r5)
            r2.zzk(r12, r5)
            goto L_0x0041
        L_0x00fa:
            boolean r10 = r0.zzP(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            long r5 = zzC(r1, r5)
            r2.zzm(r12, r5)
            goto L_0x0041
        L_0x0109:
            boolean r10 = r0.zzP(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            int r5 = zzr(r1, r5)
            r2.zzr(r12, r5)
            goto L_0x0041
        L_0x0118:
            boolean r10 = r0.zzP(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            long r5 = zzC(r1, r5)
            r2.zzJ(r12, r5)
            goto L_0x0041
        L_0x0127:
            boolean r10 = r0.zzP(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            long r5 = zzC(r1, r5)
            r2.zzt(r12, r5)
            goto L_0x0041
        L_0x0136:
            boolean r10 = r0.zzP(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            float r5 = zzo(r1, r5)
            r2.zzo(r12, r5)
            goto L_0x0041
        L_0x0145:
            boolean r10 = r0.zzP(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            double r5 = zzn(r1, r5)
            r2.zzf(r12, r5)
            goto L_0x0041
        L_0x0154:
            java.lang.Object r5 = r4.getObject(r1, r5)
            r0.zzS(r2, r12, r5, r7)
            goto L_0x0041
        L_0x015d:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.measurement.zzlr r6 = r0.zzE(r7)
            com.google.android.gms.internal.measurement.zzlt.zzQ(r10, r5, r2, r6)
            goto L_0x0041
        L_0x0170:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.measurement.zzlt.zzX(r10, r5, r2, r15)
            goto L_0x0041
        L_0x017f:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.measurement.zzlt.zzW(r10, r5, r2, r15)
            goto L_0x0041
        L_0x018e:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.measurement.zzlt.zzV(r10, r5, r2, r15)
            goto L_0x0041
        L_0x019d:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.measurement.zzlt.zzU(r10, r5, r2, r15)
            goto L_0x0041
        L_0x01ac:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.measurement.zzlt.zzM(r10, r5, r2, r15)
            goto L_0x0041
        L_0x01bb:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.measurement.zzlt.zzZ(r10, r5, r2, r15)
            goto L_0x0041
        L_0x01ca:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.measurement.zzlt.zzJ(r10, r5, r2, r15)
            goto L_0x0041
        L_0x01d9:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.measurement.zzlt.zzN(r10, r5, r2, r15)
            goto L_0x0041
        L_0x01e8:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.measurement.zzlt.zzO(r10, r5, r2, r15)
            goto L_0x0041
        L_0x01f7:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.measurement.zzlt.zzR(r10, r5, r2, r15)
            goto L_0x0041
        L_0x0206:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.measurement.zzlt.zzaa(r10, r5, r2, r15)
            goto L_0x0041
        L_0x0215:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.measurement.zzlt.zzS(r10, r5, r2, r15)
            goto L_0x0041
        L_0x0224:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.measurement.zzlt.zzP(r10, r5, r2, r15)
            goto L_0x0041
        L_0x0233:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.measurement.zzlt.zzL(r10, r5, r2, r15)
            goto L_0x0041
        L_0x0242:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            r11 = 0
            com.google.android.gms.internal.measurement.zzlt.zzX(r10, r5, r2, r11)
        L_0x0250:
            r13 = r11
            goto L_0x044c
        L_0x0253:
            r11 = 0
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.measurement.zzlt.zzW(r10, r5, r2, r11)
            goto L_0x0250
        L_0x0262:
            r11 = 0
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.measurement.zzlt.zzV(r10, r5, r2, r11)
            goto L_0x0250
        L_0x0271:
            r11 = 0
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.measurement.zzlt.zzU(r10, r5, r2, r11)
            goto L_0x0250
        L_0x0280:
            r11 = 0
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.measurement.zzlt.zzM(r10, r5, r2, r11)
            goto L_0x0250
        L_0x028f:
            r11 = 0
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.measurement.zzlt.zzZ(r10, r5, r2, r11)
            goto L_0x0250
        L_0x029e:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.measurement.zzlt.zzK(r10, r5, r2)
            goto L_0x0041
        L_0x02ad:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.measurement.zzlr r6 = r0.zzE(r7)
            com.google.android.gms.internal.measurement.zzlt.zzT(r10, r5, r2, r6)
            goto L_0x0041
        L_0x02c0:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.measurement.zzlt.zzY(r10, r5, r2)
            goto L_0x0041
        L_0x02cf:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            r13 = 0
            com.google.android.gms.internal.measurement.zzlt.zzJ(r10, r5, r2, r13)
            goto L_0x044c
        L_0x02df:
            r13 = 0
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.measurement.zzlt.zzN(r10, r5, r2, r13)
            goto L_0x044c
        L_0x02ef:
            r13 = 0
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.measurement.zzlt.zzO(r10, r5, r2, r13)
            goto L_0x044c
        L_0x02ff:
            r13 = 0
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.measurement.zzlt.zzR(r10, r5, r2, r13)
            goto L_0x044c
        L_0x030f:
            r13 = 0
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.measurement.zzlt.zzaa(r10, r5, r2, r13)
            goto L_0x044c
        L_0x031f:
            r13 = 0
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.measurement.zzlt.zzS(r10, r5, r2, r13)
            goto L_0x044c
        L_0x032f:
            r13 = 0
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.measurement.zzlt.zzP(r10, r5, r2, r13)
            goto L_0x044c
        L_0x033f:
            r13 = 0
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.measurement.zzlt.zzL(r10, r5, r2, r13)
            goto L_0x044c
        L_0x034f:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            java.lang.Object r5 = r4.getObject(r1, r5)
            com.google.android.gms.internal.measurement.zzlr r6 = r0.zzE(r7)
            r2.zzq(r12, r5, r6)
            goto L_0x044c
        L_0x0361:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            long r5 = r4.getLong(r1, r5)
            r2.zzC(r12, r5)
            goto L_0x044c
        L_0x036f:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            int r5 = r4.getInt(r1, r5)
            r2.zzA(r12, r5)
            goto L_0x044c
        L_0x037d:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            long r5 = r4.getLong(r1, r5)
            r2.zzy(r12, r5)
            goto L_0x044c
        L_0x038b:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            int r5 = r4.getInt(r1, r5)
            r2.zzw(r12, r5)
            goto L_0x044c
        L_0x0399:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            int r5 = r4.getInt(r1, r5)
            r2.zzi(r12, r5)
            goto L_0x044c
        L_0x03a7:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            int r5 = r4.getInt(r1, r5)
            r2.zzH(r12, r5)
            goto L_0x044c
        L_0x03b5:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            java.lang.Object r5 = r4.getObject(r1, r5)
            com.google.android.gms.internal.measurement.zziy r5 = (com.google.android.gms.internal.measurement.zziy) r5
            r2.zzd(r12, r5)
            goto L_0x044c
        L_0x03c5:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            java.lang.Object r5 = r4.getObject(r1, r5)
            com.google.android.gms.internal.measurement.zzlr r6 = r0.zzE(r7)
            r2.zzv(r12, r5, r6)
            goto L_0x044c
        L_0x03d7:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            java.lang.Object r5 = r4.getObject(r1, r5)
            zzT(r12, r5, r2)
            goto L_0x044c
        L_0x03e5:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            boolean r5 = com.google.android.gms.internal.measurement.zzms.zzw(r1, r5)
            r2.zzb(r12, r5)
            goto L_0x044c
        L_0x03f2:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            int r5 = r4.getInt(r1, r5)
            r2.zzk(r12, r5)
            goto L_0x044c
        L_0x03ff:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            long r5 = r4.getLong(r1, r5)
            r2.zzm(r12, r5)
            goto L_0x044c
        L_0x040c:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            int r5 = r4.getInt(r1, r5)
            r2.zzr(r12, r5)
            goto L_0x044c
        L_0x0419:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            long r5 = r4.getLong(r1, r5)
            r2.zzJ(r12, r5)
            goto L_0x044c
        L_0x0426:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            long r5 = r4.getLong(r1, r5)
            r2.zzt(r12, r5)
            goto L_0x044c
        L_0x0433:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            float r5 = com.google.android.gms.internal.measurement.zzms.zzb(r1, r5)
            r2.zzo(r12, r5)
            goto L_0x044c
        L_0x0440:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            double r5 = com.google.android.gms.internal.measurement.zzms.zza(r1, r5)
            r2.zzf(r12, r5)
        L_0x044c:
            int r7 = r7 + 3
            r5 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x0015
        L_0x0453:
            com.google.android.gms.internal.measurement.zzmi r3 = r0.zzn
            java.lang.Object r1 = r3.zzc(r1)
            r3.zzi(r1, r2)
            return
        L_0x045d:
            com.google.android.gms.internal.measurement.zzjm r2 = r0.zzo
            r2.zza(r1)
            r1 = 0
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlj.zzR(java.lang.Object, com.google.android.gms.internal.measurement.zzjh):void");
    }

    private void zzS(zzjh zzjh, int i2, Object obj, int i3) throws IOException {
        if (null != obj) {
            zzkz zzkz = (zzkz) zzF(i3);
            throw null;
        }
    }

    private static void zzT(int i2, Object obj, zzjh zzjh) throws IOException {
        if (obj instanceof String) {
            zzjh.zzF(i2, (String) obj);
        } else {
            zzjh.zzd(i2, (zziy) obj);
        }
    }

    static zzmj zzd(Object obj) {
        zzjz zzjz = (zzjz) obj;
        zzmj zzmj = zzjz.zzc;
        if (zzmj != com.google.android.gms.internal.measurement.zzmj.zzc()) {
            return zzmj;
        }
        zzmj zze2 = com.google.android.gms.internal.measurement.zzmj.zze();
        zzjz.zzc = zze2;
        return zze2;
    }

    static zzlj zzk(Class cls, zzld zzld, zzll zzll, zzku zzku, zzmi zzmi, zzjm zzjm, zzlb zzlb) {
        if (zzld instanceof zzlq) {
            return zzl((zzlq) zzld, zzll, zzku, zzmi, zzjm, zzlb);
        }
        zzmf zzmf = (zzmf) zzld;
        throw null;
    }

    static com.google.android.gms.internal.measurement.zzlj zzl(com.google.android.gms.internal.measurement.zzlq r34, com.google.android.gms.internal.measurement.zzll r35, com.google.android.gms.internal.measurement.zzku r36, com.google.android.gms.internal.measurement.zzmi r37, com.google.android.gms.internal.measurement.zzjm r38, com.google.android.gms.internal.measurement.zzlb r39) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlj.zzl(com.google.android.gms.internal.measurement.zzlq, com.google.android.gms.internal.measurement.zzll, com.google.android.gms.internal.measurement.zzku, com.google.android.gms.internal.measurement.zzmi, com.google.android.gms.internal.measurement.zzjm, com.google.android.gms.internal.measurement.zzlb):com.google.android.gms.internal.measurement.zzlj");
    }

    private static double zzn(Object obj, long j2) {
        return ((Double) zzms.zzf(obj, j2)).doubleValue();
    }

    private static float zzo(Object obj, long j2) {
        return ((Float) zzms.zzf(obj, j2)).floatValue();
    }

    private int zzp(java.lang.Object r16) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlj.zzp(java.lang.Object):int");
    }

    private int zzq(java.lang.Object r12) {
        /*
            r11 = this;
            sun.misc.Unsafe r0 = zzb
            r1 = 0
            r2 = r1
            r3 = r2
        L_0x0005:
            int[] r4 = r11.zzc
            int r4 = r4.length
            if (r2 >= r4) goto L_0x0553
            int r4 = r11.zzB(r2)
            int r5 = zzA(r4)
            int[] r6 = r11.zzc
            r6 = r6[r2]
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r4 = r4 & r7
            long r7 = (long) r4
            com.google.android.gms.internal.measurement.zzjr r4 = com.google.android.gms.internal.measurement.zzjr.DOUBLE_LIST_PACKED
            int r4 = r4.zza()
            if (r5 < r4) goto L_0x0031
            com.google.android.gms.internal.measurement.zzjr r4 = com.google.android.gms.internal.measurement.zzjr.SINT64_LIST_PACKED
            int r4 = r4.zza()
            if (r5 > r4) goto L_0x0031
            int[] r4 = r11.zzc
            int r9 = r2 + 2
            r4 = r4[r9]
        L_0x0031:
            r4 = 63
            switch(r5) {
                case 0: goto L_0x0541;
                case 1: goto L_0x0533;
                case 2: goto L_0x051d;
                case 3: goto L_0x0507;
                case 4: goto L_0x04f1;
                case 5: goto L_0x04e3;
                case 6: goto L_0x04d5;
                case 7: goto L_0x04c7;
                case 8: goto L_0x0499;
                case 9: goto L_0x0485;
                case 10: goto L_0x0469;
                case 11: goto L_0x0453;
                case 12: goto L_0x043d;
                case 13: goto L_0x042f;
                case 14: goto L_0x0421;
                case 15: goto L_0x0406;
                case 16: goto L_0x03eb;
                case 17: goto L_0x03d5;
                case 18: goto L_0x03c9;
                case 19: goto L_0x03bd;
                case 20: goto L_0x03b1;
                case 21: goto L_0x03a5;
                case 22: goto L_0x0399;
                case 23: goto L_0x038d;
                case 24: goto L_0x0381;
                case 25: goto L_0x0375;
                case 26: goto L_0x0369;
                case 27: goto L_0x0359;
                case 28: goto L_0x034d;
                case 29: goto L_0x0341;
                case 30: goto L_0x0335;
                case 31: goto L_0x0329;
                case 32: goto L_0x031d;
                case 33: goto L_0x0311;
                case 34: goto L_0x0305;
                case 35: goto L_0x02ef;
                case 36: goto L_0x02d9;
                case 37: goto L_0x02c3;
                case 38: goto L_0x02ad;
                case 39: goto L_0x0297;
                case 40: goto L_0x0281;
                case 41: goto L_0x026b;
                case 42: goto L_0x0255;
                case 43: goto L_0x0240;
                case 44: goto L_0x022b;
                case 45: goto L_0x0216;
                case 46: goto L_0x0201;
                case 47: goto L_0x01ec;
                case 48: goto L_0x01d5;
                case 49: goto L_0x01c5;
                case 50: goto L_0x01b8;
                case 51: goto L_0x01aa;
                case 52: goto L_0x019c;
                case 53: goto L_0x0187;
                case 54: goto L_0x016f;
                case 55: goto L_0x0159;
                case 56: goto L_0x014b;
                case 57: goto L_0x013d;
                case 58: goto L_0x012d;
                case 59: goto L_0x0100;
                case 60: goto L_0x00ec;
                case 61: goto L_0x00ce;
                case 62: goto L_0x00b9;
                case 63: goto L_0x00a4;
                case 64: goto L_0x0095;
                case 65: goto L_0x0086;
                case 66: goto L_0x006c;
                case 67: goto L_0x004f;
                case 68: goto L_0x0038;
                default: goto L_0x0036;
            }
        L_0x0036:
            goto L_0x054f
        L_0x0038:
            boolean r4 = r11.zzP(r12, r6, r2)
            if (r4 == 0) goto L_0x054f
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzms.zzf(r12, r7)
            com.google.android.gms.internal.measurement.zzlg r4 = (com.google.android.gms.internal.measurement.zzlg) r4
            com.google.android.gms.internal.measurement.zzlr r5 = r11.zzE(r2)
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzu(r6, r4, r5)
        L_0x004c:
            int r3 = r3 + r4
            goto L_0x054f
        L_0x004f:
            boolean r5 = r11.zzP(r12, r6, r2)
            if (r5 == 0) goto L_0x054f
            long r7 = zzC(r12, r7)
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.measurement.zzjg.zzA(r5)
            long r9 = r7 + r7
            long r6 = r7 >> r4
            long r6 = r6 ^ r9
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzB(r6)
        L_0x0068:
            int r5 = r5 + r4
        L_0x0069:
            int r3 = r3 + r5
            goto L_0x054f
        L_0x006c:
            boolean r4 = r11.zzP(r12, r6, r2)
            if (r4 == 0) goto L_0x054f
            int r4 = zzr(r12, r7)
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.measurement.zzjg.zzA(r5)
            int r6 = r4 + r4
            int r4 = r4 >> 31
            r4 = r4 ^ r6
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x0068
        L_0x0086:
            boolean r4 = r11.zzP(r12, r6, r2)
            if (r4 == 0) goto L_0x054f
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
        L_0x0092:
            int r4 = r4 + 8
            goto L_0x004c
        L_0x0095:
            boolean r4 = r11.zzP(r12, r6, r2)
            if (r4 == 0) goto L_0x054f
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
        L_0x00a1:
            int r4 = r4 + 4
            goto L_0x004c
        L_0x00a4:
            boolean r4 = r11.zzP(r12, r6, r2)
            if (r4 == 0) goto L_0x054f
            int r4 = zzr(r12, r7)
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.measurement.zzjg.zzA(r5)
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzv(r4)
            goto L_0x0068
        L_0x00b9:
            boolean r4 = r11.zzP(r12, r6, r2)
            if (r4 == 0) goto L_0x054f
            int r4 = zzr(r12, r7)
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.measurement.zzjg.zzA(r5)
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x0068
        L_0x00ce:
            boolean r4 = r11.zzP(r12, r6, r2)
            if (r4 == 0) goto L_0x054f
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzms.zzf(r12, r7)
            com.google.android.gms.internal.measurement.zziy r4 = (com.google.android.gms.internal.measurement.zziy) r4
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.measurement.zzjg.zzA(r5)
            int r4 = r4.zzd()
            int r6 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
        L_0x00e8:
            int r6 = r6 + r4
            int r5 = r5 + r6
            goto L_0x0069
        L_0x00ec:
            boolean r4 = r11.zzP(r12, r6, r2)
            if (r4 == 0) goto L_0x054f
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzms.zzf(r12, r7)
            com.google.android.gms.internal.measurement.zzlr r5 = r11.zzE(r2)
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzo(r6, r4, r5)
            goto L_0x004c
        L_0x0100:
            boolean r4 = r11.zzP(r12, r6, r2)
            if (r4 == 0) goto L_0x054f
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzms.zzf(r12, r7)
            boolean r5 = r4 instanceof com.google.android.gms.internal.measurement.zziy
            if (r5 == 0) goto L_0x011f
            com.google.android.gms.internal.measurement.zziy r4 = (com.google.android.gms.internal.measurement.zziy) r4
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.measurement.zzjg.zzA(r5)
            int r4 = r4.zzd()
            int r6 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x00e8
        L_0x011f:
            java.lang.String r4 = (java.lang.String) r4
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.measurement.zzjg.zzA(r5)
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzy(r4)
            goto L_0x0068
        L_0x012d:
            boolean r4 = r11.zzP(r12, r6, r2)
            if (r4 == 0) goto L_0x054f
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
        L_0x0139:
            int r4 = r4 + 1
            goto L_0x004c
        L_0x013d:
            boolean r4 = r11.zzP(r12, r6, r2)
            if (r4 == 0) goto L_0x054f
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x00a1
        L_0x014b:
            boolean r4 = r11.zzP(r12, r6, r2)
            if (r4 == 0) goto L_0x054f
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x0092
        L_0x0159:
            boolean r4 = r11.zzP(r12, r6, r2)
            if (r4 == 0) goto L_0x054f
            int r4 = zzr(r12, r7)
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.measurement.zzjg.zzA(r5)
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzv(r4)
            goto L_0x0068
        L_0x016f:
            boolean r4 = r11.zzP(r12, r6, r2)
            if (r4 == 0) goto L_0x054f
            long r4 = zzC(r12, r7)
            int r6 = r6 << 3
            int r6 = com.google.android.gms.internal.measurement.zzjg.zzA(r6)
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzB(r4)
        L_0x0183:
            int r6 = r6 + r4
            int r3 = r3 + r6
            goto L_0x054f
        L_0x0187:
            boolean r4 = r11.zzP(r12, r6, r2)
            if (r4 == 0) goto L_0x054f
            long r4 = zzC(r12, r7)
            int r6 = r6 << 3
            int r6 = com.google.android.gms.internal.measurement.zzjg.zzA(r6)
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzB(r4)
            goto L_0x0183
        L_0x019c:
            boolean r4 = r11.zzP(r12, r6, r2)
            if (r4 == 0) goto L_0x054f
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x00a1
        L_0x01aa:
            boolean r4 = r11.zzP(r12, r6, r2)
            if (r4 == 0) goto L_0x054f
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x0092
        L_0x01b8:
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzms.zzf(r12, r7)
            java.lang.Object r5 = r11.zzF(r2)
            com.google.android.gms.internal.measurement.zzlb.zza(r6, r4, r5)
            goto L_0x054f
        L_0x01c5:
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzms.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.measurement.zzlr r5 = r11.zzE(r2)
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzj(r6, r4, r5)
            goto L_0x004c
        L_0x01d5:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzt(r4)
            if (r4 <= 0) goto L_0x054f
            int r5 = com.google.android.gms.internal.measurement.zzjg.zzz(r6)
            int r6 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
        L_0x01e9:
            int r5 = r5 + r6
            goto L_0x0068
        L_0x01ec:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzr(r4)
            if (r4 <= 0) goto L_0x054f
            int r5 = com.google.android.gms.internal.measurement.zzjg.zzz(r6)
            int r6 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x01e9
        L_0x0201:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzi(r4)
            if (r4 <= 0) goto L_0x054f
            int r5 = com.google.android.gms.internal.measurement.zzjg.zzz(r6)
            int r6 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x01e9
        L_0x0216:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzg(r4)
            if (r4 <= 0) goto L_0x054f
            int r5 = com.google.android.gms.internal.measurement.zzjg.zzz(r6)
            int r6 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x01e9
        L_0x022b:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzlt.zze(r4)
            if (r4 <= 0) goto L_0x054f
            int r5 = com.google.android.gms.internal.measurement.zzjg.zzz(r6)
            int r6 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x01e9
        L_0x0240:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzw(r4)
            if (r4 <= 0) goto L_0x054f
            int r5 = com.google.android.gms.internal.measurement.zzjg.zzz(r6)
            int r6 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x01e9
        L_0x0255:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzb(r4)
            if (r4 <= 0) goto L_0x054f
            int r5 = com.google.android.gms.internal.measurement.zzjg.zzz(r6)
            int r6 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x01e9
        L_0x026b:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzg(r4)
            if (r4 <= 0) goto L_0x054f
            int r5 = com.google.android.gms.internal.measurement.zzjg.zzz(r6)
            int r6 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x01e9
        L_0x0281:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzi(r4)
            if (r4 <= 0) goto L_0x054f
            int r5 = com.google.android.gms.internal.measurement.zzjg.zzz(r6)
            int r6 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x01e9
        L_0x0297:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzl(r4)
            if (r4 <= 0) goto L_0x054f
            int r5 = com.google.android.gms.internal.measurement.zzjg.zzz(r6)
            int r6 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x01e9
        L_0x02ad:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzy(r4)
            if (r4 <= 0) goto L_0x054f
            int r5 = com.google.android.gms.internal.measurement.zzjg.zzz(r6)
            int r6 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x01e9
        L_0x02c3:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzn(r4)
            if (r4 <= 0) goto L_0x054f
            int r5 = com.google.android.gms.internal.measurement.zzjg.zzz(r6)
            int r6 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x01e9
        L_0x02d9:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzg(r4)
            if (r4 <= 0) goto L_0x054f
            int r5 = com.google.android.gms.internal.measurement.zzjg.zzz(r6)
            int r6 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x01e9
        L_0x02ef:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzi(r4)
            if (r4 <= 0) goto L_0x054f
            int r5 = com.google.android.gms.internal.measurement.zzjg.zzz(r6)
            int r6 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x01e9
        L_0x0305:
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzms.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzs(r6, r4, r1)
            goto L_0x004c
        L_0x0311:
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzms.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzq(r6, r4, r1)
            goto L_0x004c
        L_0x031d:
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzms.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzh(r6, r4, r1)
            goto L_0x004c
        L_0x0329:
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzms.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzf(r6, r4, r1)
            goto L_0x004c
        L_0x0335:
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzms.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzd(r6, r4, r1)
            goto L_0x004c
        L_0x0341:
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzms.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzv(r6, r4, r1)
            goto L_0x004c
        L_0x034d:
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzms.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzc(r6, r4)
            goto L_0x004c
        L_0x0359:
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzms.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.measurement.zzlr r5 = r11.zzE(r2)
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzp(r6, r4, r5)
            goto L_0x004c
        L_0x0369:
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzms.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzu(r6, r4)
            goto L_0x004c
        L_0x0375:
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzms.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzlt.zza(r6, r4, r1)
            goto L_0x004c
        L_0x0381:
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzms.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzf(r6, r4, r1)
            goto L_0x004c
        L_0x038d:
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzms.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzh(r6, r4, r1)
            goto L_0x004c
        L_0x0399:
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzms.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzk(r6, r4, r1)
            goto L_0x004c
        L_0x03a5:
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzms.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzx(r6, r4, r1)
            goto L_0x004c
        L_0x03b1:
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzms.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzm(r6, r4, r1)
            goto L_0x004c
        L_0x03bd:
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzms.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzf(r6, r4, r1)
            goto L_0x004c
        L_0x03c9:
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzms.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzh(r6, r4, r1)
            goto L_0x004c
        L_0x03d5:
            boolean r4 = r11.zzM(r12, r2)
            if (r4 == 0) goto L_0x054f
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzms.zzf(r12, r7)
            com.google.android.gms.internal.measurement.zzlg r4 = (com.google.android.gms.internal.measurement.zzlg) r4
            com.google.android.gms.internal.measurement.zzlr r5 = r11.zzE(r2)
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzu(r6, r4, r5)
            goto L_0x004c
        L_0x03eb:
            boolean r5 = r11.zzM(r12, r2)
            if (r5 == 0) goto L_0x054f
            long r7 = com.google.android.gms.internal.measurement.zzms.zzd(r12, r7)
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.measurement.zzjg.zzA(r5)
            long r9 = r7 + r7
            long r6 = r7 >> r4
            long r6 = r6 ^ r9
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzB(r6)
            goto L_0x0068
        L_0x0406:
            boolean r4 = r11.zzM(r12, r2)
            if (r4 == 0) goto L_0x054f
            int r4 = com.google.android.gms.internal.measurement.zzms.zzc(r12, r7)
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.measurement.zzjg.zzA(r5)
            int r6 = r4 + r4
            int r4 = r4 >> 31
            r4 = r4 ^ r6
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x0068
        L_0x0421:
            boolean r4 = r11.zzM(r12, r2)
            if (r4 == 0) goto L_0x054f
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x0092
        L_0x042f:
            boolean r4 = r11.zzM(r12, r2)
            if (r4 == 0) goto L_0x054f
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x00a1
        L_0x043d:
            boolean r4 = r11.zzM(r12, r2)
            if (r4 == 0) goto L_0x054f
            int r4 = com.google.android.gms.internal.measurement.zzms.zzc(r12, r7)
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.measurement.zzjg.zzA(r5)
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzv(r4)
            goto L_0x0068
        L_0x0453:
            boolean r4 = r11.zzM(r12, r2)
            if (r4 == 0) goto L_0x054f
            int r4 = com.google.android.gms.internal.measurement.zzms.zzc(r12, r7)
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.measurement.zzjg.zzA(r5)
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x0068
        L_0x0469:
            boolean r4 = r11.zzM(r12, r2)
            if (r4 == 0) goto L_0x054f
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzms.zzf(r12, r7)
            com.google.android.gms.internal.measurement.zziy r4 = (com.google.android.gms.internal.measurement.zziy) r4
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.measurement.zzjg.zzA(r5)
            int r4 = r4.zzd()
            int r6 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x00e8
        L_0x0485:
            boolean r4 = r11.zzM(r12, r2)
            if (r4 == 0) goto L_0x054f
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzms.zzf(r12, r7)
            com.google.android.gms.internal.measurement.zzlr r5 = r11.zzE(r2)
            int r4 = com.google.android.gms.internal.measurement.zzlt.zzo(r6, r4, r5)
            goto L_0x004c
        L_0x0499:
            boolean r4 = r11.zzM(r12, r2)
            if (r4 == 0) goto L_0x054f
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzms.zzf(r12, r7)
            boolean r5 = r4 instanceof com.google.android.gms.internal.measurement.zziy
            if (r5 == 0) goto L_0x04b9
            com.google.android.gms.internal.measurement.zziy r4 = (com.google.android.gms.internal.measurement.zziy) r4
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.measurement.zzjg.zzA(r5)
            int r4 = r4.zzd()
            int r6 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x00e8
        L_0x04b9:
            java.lang.String r4 = (java.lang.String) r4
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.measurement.zzjg.zzA(r5)
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzy(r4)
            goto L_0x0068
        L_0x04c7:
            boolean r4 = r11.zzM(r12, r2)
            if (r4 == 0) goto L_0x054f
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x0139
        L_0x04d5:
            boolean r4 = r11.zzM(r12, r2)
            if (r4 == 0) goto L_0x054f
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x00a1
        L_0x04e3:
            boolean r4 = r11.zzM(r12, r2)
            if (r4 == 0) goto L_0x054f
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x0092
        L_0x04f1:
            boolean r4 = r11.zzM(r12, r2)
            if (r4 == 0) goto L_0x054f
            int r4 = com.google.android.gms.internal.measurement.zzms.zzc(r12, r7)
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.measurement.zzjg.zzA(r5)
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzv(r4)
            goto L_0x0068
        L_0x0507:
            boolean r4 = r11.zzM(r12, r2)
            if (r4 == 0) goto L_0x054f
            long r4 = com.google.android.gms.internal.measurement.zzms.zzd(r12, r7)
            int r6 = r6 << 3
            int r6 = com.google.android.gms.internal.measurement.zzjg.zzA(r6)
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzB(r4)
            goto L_0x0183
        L_0x051d:
            boolean r4 = r11.zzM(r12, r2)
            if (r4 == 0) goto L_0x054f
            long r4 = com.google.android.gms.internal.measurement.zzms.zzd(r12, r7)
            int r6 = r6 << 3
            int r6 = com.google.android.gms.internal.measurement.zzjg.zzA(r6)
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzB(r4)
            goto L_0x0183
        L_0x0533:
            boolean r4 = r11.zzM(r12, r2)
            if (r4 == 0) goto L_0x054f
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x00a1
        L_0x0541:
            boolean r4 = r11.zzM(r12, r2)
            if (r4 == 0) goto L_0x054f
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.measurement.zzjg.zzA(r4)
            goto L_0x0092
        L_0x054f:
            int r2 = r2 + 3
            goto L_0x0005
        L_0x0553:
            com.google.android.gms.internal.measurement.zzmi r0 = r11.zzn
            java.lang.Object r12 = r0.zzc(r12)
            int r12 = r0.zza(r12)
            int r3 = r3 + r12
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlj.zzq(java.lang.Object):int");
    }

    private static int zzr(Object obj, long j2) {
        return ((Integer) zzms.zzf(obj, j2)).intValue();
    }

    private int zzs(Object obj, byte[] bArr, int i2, int i3, int i4, long j2, zzik zzik) throws IOException {
        Unsafe unsafe = zzb;
        Object zzF = zzF(i4);
        Object object = unsafe.getObject(obj, j2);
        if (!((zzla) object).zze()) {
            zzla zzb2 = zzla.zza().zzb();
            zzlb.zzb(zzb2, object);
            unsafe.putObject(obj, j2, zzb2);
        }
        zzkz zzkz = (zzkz) zzF;
        throw null;
    }

    private int zzt(Object obj, byte[] bArr, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j2, int i9, zzik zzik) throws IOException {
        Object obj2 = obj;
        byte[] bArr2 = bArr;
        int i10 = i2;
        int i11 = i4;
        int i12 = i5;
        int i13 = i6;
        long j3 = j2;
        int i14 = i9;
        zzik zzik2 = zzik;
        Unsafe unsafe = zzb;
        long j4 = this.zzc[i14 + 2] & 1048575;
        switch (i8) {
            case 51:
                if (1 == i13) {
                    unsafe.putObject(obj2, j3, Double.valueOf(Double.longBitsToDouble(zzil.zzn(bArr, i2))));
                    unsafe.putInt(obj2, j4, i12);
                    return i10 + 8;
                }
                break;
            case 52:
                if (5 == i13) {
                    unsafe.putObject(obj2, j3, Float.valueOf(Float.intBitsToFloat(zzil.zzb(bArr, i2))));
                    unsafe.putInt(obj2, j4, i12);
                    return i10 + 4;
                }
                break;
            case 53:
            case 54:
                if (0 == i13) {
                    int zzm2 = zzil.zzm(bArr2, i10, zzik2);
                    unsafe.putObject(obj2, j3, Long.valueOf(zzik2.zzb));
                    unsafe.putInt(obj2, j4, i12);
                    return zzm2;
                }
                break;
            case 55:
            case 62:
                if (0 == i13) {
                    int zzj2 = zzil.zzj(bArr2, i10, zzik2);
                    unsafe.putObject(obj2, j3, Integer.valueOf(zzik2.zza));
                    unsafe.putInt(obj2, j4, i12);
                    return zzj2;
                }
                break;
            case 56:
            case 65:
                if (1 == i13) {
                    unsafe.putObject(obj2, j3, Long.valueOf(zzil.zzn(bArr, i2)));
                    unsafe.putInt(obj2, j4, i12);
                    return i10 + 8;
                }
                break;
            case 57:
            case 64:
                if (5 == i13) {
                    unsafe.putObject(obj2, j3, Integer.valueOf(zzil.zzb(bArr, i2)));
                    unsafe.putInt(obj2, j4, i12);
                    return i10 + 4;
                }
                break;
            case 58:
                if (0 == i13) {
                    int zzm3 = zzil.zzm(bArr2, i10, zzik2);
                    unsafe.putObject(obj2, j3, Boolean.valueOf(0 != zzik2.zzb));
                    unsafe.putInt(obj2, j4, i12);
                    return zzm3;
                }
                break;
            case 59:
                if (2 == i13) {
                    int zzj3 = zzil.zzj(bArr2, i10, zzik2);
                    int i15 = zzik2.zza;
                    if (0 == i15) {
                        unsafe.putObject(obj2, j3, "");
                    } else if (0 == (i7 & 536870912) || zzmx.zzf(bArr2, zzj3, zzj3 + i15)) {
                        unsafe.putObject(obj2, j3, new String(bArr2, zzj3, i15, zzkh.zzb));
                        zzj3 += i15;
                    } else {
                        throw zzkj.zzc();
                    }
                    unsafe.putInt(obj2, j4, i12);
                    return zzj3;
                }
                break;
            case 60:
                if (2 == i13) {
                    int zzd2 = zzil.zzd(zzE(i14), bArr2, i10, i3, zzik2);
                    Object object = unsafe.getInt(obj2, j4) == i12 ? unsafe.getObject(obj2, j3) : null;
                    if (null == object) {
                        unsafe.putObject(obj2, j3, zzik2.zzc);
                    } else {
                        unsafe.putObject(obj2, j3, zzkh.zzg(object, zzik2.zzc));
                    }
                    unsafe.putInt(obj2, j4, i12);
                    return zzd2;
                }
                break;
            case 61:
                if (2 == i13) {
                    int zza2 = zzil.zza(bArr2, i10, zzik2);
                    unsafe.putObject(obj2, j3, zzik2.zzc);
                    unsafe.putInt(obj2, j4, i12);
                    return zza2;
                }
                break;
            case 63:
                if (0 == i13) {
                    int zzj4 = zzil.zzj(bArr2, i10, zzik2);
                    int i16 = zzik2.zza;
                    zzkd zzD = zzD(i14);
                    if (null == zzD || zzD.zza(i16)) {
                        unsafe.putObject(obj2, j3, Integer.valueOf(i16));
                        unsafe.putInt(obj2, j4, i12);
                    } else {
                        zzd(obj).zzh(i11, Long.valueOf(i16));
                    }
                    return zzj4;
                }
                break;
            case 66:
                if (0 == i13) {
                    int zzj5 = zzil.zzj(bArr2, i10, zzik2);
                    unsafe.putObject(obj2, j3, Integer.valueOf(zzjc.zzb(zzik2.zza)));
                    unsafe.putInt(obj2, j4, i12);
                    return zzj5;
                }
                break;
            case 67:
                if (0 == i13) {
                    int zzm4 = zzil.zzm(bArr2, i10, zzik2);
                    unsafe.putObject(obj2, j3, Long.valueOf(zzjc.zzc(zzik2.zzb)));
                    unsafe.putInt(obj2, j4, i12);
                    return zzm4;
                }
                break;
            case 68:
                if (3 == i13) {
                    int zzc2 = zzil.zzc(zzE(i14), bArr, i2, i3, (i11 & -8) | 4, zzik);
                    Object object2 = unsafe.getInt(obj2, j4) == i12 ? unsafe.getObject(obj2, j3) : null;
                    if (null == object2) {
                        unsafe.putObject(obj2, j3, zzik2.zzc);
                    } else {
                        unsafe.putObject(obj2, j3, zzkh.zzg(object2, zzik2.zzc));
                    }
                    unsafe.putInt(obj2, j4, i12);
                    return zzc2;
                }
                break;
        }
        return i10;
    }

    private int zzu(java.lang.Object r30, byte[] r31, int r32, int r33, com.google.android.gms.internal.measurement.zzik r34) throws java.io.IOException {
        /*
            r29 = this;
            r15 = r29
            r14 = r30
            r12 = r31
            r13 = r33
            r11 = r34
            sun.misc.Unsafe r9 = zzb
            r10 = 1048575(0xfffff, float:1.469367E-39)
            r16 = 0
            r8 = -1
            r0 = r32
            r1 = r8
            r7 = r10
            r2 = r16
            r6 = r2
        L_0x0019:
            if (r0 >= r13) goto L_0x033d
            int r3 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x002b
            int r0 = com.google.android.gms.internal.measurement.zzil.zzk(r0, r12, r3, r11)
            int r3 = r11.zza
            r4 = r0
            r17 = r3
            goto L_0x002e
        L_0x002b:
            r17 = r0
            r4 = r3
        L_0x002e:
            int r5 = r17 >>> 3
            r3 = r17 & 7
            if (r5 <= r1) goto L_0x003c
            int r2 = r2 / 3
            int r0 = r15.zzx(r5, r2)
        L_0x003a:
            r2 = r0
            goto L_0x0041
        L_0x003c:
            int r0 = r15.zzw(r5)
            goto L_0x003a
        L_0x0041:
            if (r2 != r8) goto L_0x004e
            r2 = r4
            r21 = r5
            r18 = r8
            r28 = r9
            r24 = r16
            goto L_0x0316
        L_0x004e:
            int[] r0 = r15.zzc
            int r1 = r2 + 1
            r1 = r0[r1]
            int r8 = zzA(r1)
            r32 = r5
            r5 = r1 & r10
            long r10 = (long) r5
            r5 = 17
            r20 = r10
            if (r8 > r5) goto L_0x021b
            int r5 = r2 + 2
            r0 = r0[r5]
            int r5 = r0 >>> 20
            r11 = 1
            int r22 = r11 << r5
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            if (r0 == r7) goto L_0x0080
            if (r7 == r5) goto L_0x0078
            long r10 = (long) r7
            r9.putInt(r14, r10, r6)
        L_0x0078:
            if (r0 == r5) goto L_0x007f
            long r6 = (long) r0
            int r6 = r9.getInt(r14, r6)
        L_0x007f:
            r7 = r0
        L_0x0080:
            r0 = 5
            switch(r8) {
                case 0: goto L_0x01f9;
                case 1: goto L_0x01de;
                case 2: goto L_0x01bf;
                case 3: goto L_0x01bf;
                case 4: goto L_0x01a9;
                case 5: goto L_0x018c;
                case 6: goto L_0x0177;
                case 7: goto L_0x0158;
                case 8: goto L_0x0133;
                case 9: goto L_0x0109;
                case 10: goto L_0x00f3;
                case 11: goto L_0x01a9;
                case 12: goto L_0x00de;
                case 13: goto L_0x0177;
                case 14: goto L_0x018c;
                case 15: goto L_0x00c2;
                case 16: goto L_0x008e;
                default: goto L_0x0084;
            }
        L_0x0084:
            r21 = r32
            r11 = r34
            r10 = r2
            r8 = r4
            r24 = r5
            goto L_0x0212
        L_0x008e:
            if (r3 != 0) goto L_0x00b8
            r11 = r34
            r0 = r20
            int r8 = com.google.android.gms.internal.measurement.zzil.zzm(r12, r4, r11)
            long r3 = r11.zzb
            long r19 = com.google.android.gms.internal.measurement.zzjc.zzc(r3)
            r3 = r0
            r0 = r9
            r1 = r30
            r10 = r2
            r2 = r3
            r21 = r32
            r24 = r5
            r4 = r19
            r0.putLong(r1, r2, r4)
        L_0x00ad:
            r6 = r6 | r22
            r0 = r8
        L_0x00b0:
            r2 = r10
            r1 = r21
            r10 = r24
            r8 = -1
            goto L_0x0019
        L_0x00b8:
            r21 = r32
            r11 = r34
            r10 = r2
            r24 = r5
        L_0x00bf:
            r8 = r4
            goto L_0x0212
        L_0x00c2:
            r11 = r34
            r10 = r2
            r24 = r5
            r0 = r20
            r21 = r32
            if (r3 != 0) goto L_0x00bf
            int r2 = com.google.android.gms.internal.measurement.zzil.zzj(r12, r4, r11)
            int r3 = r11.zza
            int r3 = com.google.android.gms.internal.measurement.zzjc.zzb(r3)
            r9.putInt(r14, r0, r3)
        L_0x00da:
            r6 = r6 | r22
            r0 = r2
            goto L_0x00b0
        L_0x00de:
            r11 = r34
            r10 = r2
            r24 = r5
            r0 = r20
            r21 = r32
            if (r3 != 0) goto L_0x00bf
            int r2 = com.google.android.gms.internal.measurement.zzil.zzj(r12, r4, r11)
            int r3 = r11.zza
            r9.putInt(r14, r0, r3)
            goto L_0x00da
        L_0x00f3:
            r11 = r34
            r10 = r2
            r24 = r5
            r0 = r20
            r2 = 2
            r21 = r32
            if (r3 != r2) goto L_0x00bf
            int r2 = com.google.android.gms.internal.measurement.zzil.zza(r12, r4, r11)
            java.lang.Object r3 = r11.zzc
            r9.putObject(r14, r0, r3)
            goto L_0x00da
        L_0x0109:
            r11 = r34
            r10 = r2
            r24 = r5
            r0 = r20
            r2 = 2
            r21 = r32
            if (r3 != r2) goto L_0x00bf
            com.google.android.gms.internal.measurement.zzlr r2 = r15.zzE(r10)
            int r2 = com.google.android.gms.internal.measurement.zzil.zzd(r2, r12, r4, r13, r11)
            java.lang.Object r3 = r9.getObject(r14, r0)
            if (r3 != 0) goto L_0x0129
            java.lang.Object r3 = r11.zzc
            r9.putObject(r14, r0, r3)
            goto L_0x00da
        L_0x0129:
            java.lang.Object r4 = r11.zzc
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzkh.zzg(r3, r4)
            r9.putObject(r14, r0, r3)
            goto L_0x00da
        L_0x0133:
            r11 = r34
            r10 = r2
            r24 = r5
            r25 = r20
            r0 = 2
            r21 = r32
            if (r3 != r0) goto L_0x00bf
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r0 & r1
            if (r0 != 0) goto L_0x0149
            int r0 = com.google.android.gms.internal.measurement.zzil.zzg(r12, r4, r11)
            goto L_0x014d
        L_0x0149:
            int r0 = com.google.android.gms.internal.measurement.zzil.zzh(r12, r4, r11)
        L_0x014d:
            java.lang.Object r1 = r11.zzc
            r2 = r25
            r9.putObject(r14, r2, r1)
        L_0x0154:
            r6 = r6 | r22
            goto L_0x00b0
        L_0x0158:
            r11 = r34
            r10 = r2
            r24 = r5
            r1 = r20
            r21 = r32
            if (r3 != 0) goto L_0x00bf
            int r0 = com.google.android.gms.internal.measurement.zzil.zzm(r12, r4, r11)
            long r3 = r11.zzb
            r19 = 0
            int r3 = (r3 > r19 ? 1 : (r3 == r19 ? 0 : -1))
            if (r3 == 0) goto L_0x0171
            r3 = 1
            goto L_0x0173
        L_0x0171:
            r3 = r16
        L_0x0173:
            com.google.android.gms.internal.measurement.zzms.zzm(r14, r1, r3)
            goto L_0x0154
        L_0x0177:
            r11 = r34
            r10 = r2
            r24 = r5
            r1 = r20
            r21 = r32
            if (r3 != r0) goto L_0x00bf
            int r0 = com.google.android.gms.internal.measurement.zzil.zzb(r12, r4)
            r9.putInt(r14, r1, r0)
            int r0 = r4 + 4
            goto L_0x0154
        L_0x018c:
            r11 = r34
            r10 = r2
            r24 = r5
            r1 = r20
            r0 = 1
            r21 = r32
            if (r3 != r0) goto L_0x00bf
            long r19 = com.google.android.gms.internal.measurement.zzil.zzn(r12, r4)
            r0 = r9
            r2 = r1
            r1 = r30
            r8 = r4
            r4 = r19
            r0.putLong(r1, r2, r4)
        L_0x01a6:
            int r0 = r8 + 8
            goto L_0x0154
        L_0x01a9:
            r11 = r34
            r10 = r2
            r8 = r4
            r24 = r5
            r4 = r20
            r21 = r32
            if (r3 != 0) goto L_0x0212
            int r0 = com.google.android.gms.internal.measurement.zzil.zzj(r12, r8, r11)
            int r1 = r11.zza
            r9.putInt(r14, r4, r1)
            goto L_0x0154
        L_0x01bf:
            r11 = r34
            r10 = r2
            r8 = r4
            r24 = r5
            r4 = r20
            r21 = r32
            if (r3 != 0) goto L_0x0212
            int r8 = com.google.android.gms.internal.measurement.zzil.zzm(r12, r8, r11)
            long r2 = r11.zzb
            r0 = r9
            r1 = r30
            r19 = r2
            r2 = r4
            r4 = r19
            r0.putLong(r1, r2, r4)
            goto L_0x00ad
        L_0x01de:
            r11 = r34
            r10 = r2
            r8 = r4
            r24 = r5
            r4 = r20
            r21 = r32
            if (r3 != r0) goto L_0x0212
            int r0 = com.google.android.gms.internal.measurement.zzil.zzb(r12, r8)
            float r0 = java.lang.Float.intBitsToFloat(r0)
            com.google.android.gms.internal.measurement.zzms.zzp(r14, r4, r0)
            int r0 = r8 + 4
            goto L_0x0154
        L_0x01f9:
            r11 = r34
            r10 = r2
            r8 = r4
            r24 = r5
            r4 = r20
            r0 = 1
            r21 = r32
            if (r3 != r0) goto L_0x0212
            long r0 = com.google.android.gms.internal.measurement.zzil.zzn(r12, r8)
            double r0 = java.lang.Double.longBitsToDouble(r0)
            com.google.android.gms.internal.measurement.zzms.zzo(r14, r4, r0)
            goto L_0x01a6
        L_0x0212:
            r2 = r8
            r28 = r9
            r24 = r10
            r18 = -1
            goto L_0x0316
        L_0x021b:
            r11 = r34
            r10 = r2
            r2 = r4
            r4 = r20
            r24 = 1048575(0xfffff, float:1.469367E-39)
            r21 = r32
            r0 = 27
            if (r8 != r0) goto L_0x026c
            r0 = 2
            if (r3 != r0) goto L_0x0260
            java.lang.Object r0 = r9.getObject(r14, r4)
            com.google.android.gms.internal.measurement.zzkg r0 = (com.google.android.gms.internal.measurement.zzkg) r0
            boolean r1 = r0.zzc()
            if (r1 != 0) goto L_0x024a
            int r1 = r0.size()
            if (r1 != 0) goto L_0x0242
            r1 = 10
            goto L_0x0243
        L_0x0242:
            int r1 = r1 + r1
        L_0x0243:
            com.google.android.gms.internal.measurement.zzkg r0 = r0.zzd(r1)
            r9.putObject(r14, r4, r0)
        L_0x024a:
            r5 = r0
            com.google.android.gms.internal.measurement.zzlr r0 = r15.zzE(r10)
            r1 = r17
            r3 = r2
            r2 = r31
            r4 = r33
            r8 = r6
            r6 = r34
            int r0 = com.google.android.gms.internal.measurement.zzil.zze(r0, r1, r2, r3, r4, r5, r6)
            r6 = r8
            goto L_0x00b0
        L_0x0260:
            r14 = r2
            r23 = r6
            r15 = r7
            r28 = r9
            r24 = r10
            r18 = -1
            goto L_0x02f7
        L_0x026c:
            r0 = 49
            if (r8 > r0) goto L_0x02c6
            long r0 = (long) r1
            r19 = r0
            r0 = r29
            r1 = r30
            r32 = r2
            r2 = r31
            r22 = r3
            r3 = r32
            r25 = r4
            r4 = r33
            r5 = r17
            r15 = r6
            r6 = r21
            r23 = r15
            r15 = r7
            r7 = r22
            r27 = r8
            r18 = -1
            r8 = r10
            r28 = r9
            r24 = r10
            r9 = r19
            r11 = r27
            r12 = r25
            r14 = r34
            int r0 = r0.zzv(r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14)
            r14 = r32
            if (r0 == r14) goto L_0x02c0
        L_0x02a6:
            r14 = r30
            r12 = r31
            r13 = r33
            r11 = r34
            r7 = r15
            r8 = r18
            r1 = r21
            r6 = r23
            r2 = r24
            r9 = r28
            r10 = 1048575(0xfffff, float:1.469367E-39)
            r15 = r29
            goto L_0x0019
        L_0x02c0:
            r2 = r0
        L_0x02c1:
            r7 = r15
            r6 = r23
            goto L_0x0316
        L_0x02c6:
            r14 = r2
            r22 = r3
            r25 = r4
            r23 = r6
            r15 = r7
            r27 = r8
            r28 = r9
            r24 = r10
            r18 = -1
            r0 = 50
            r9 = r27
            if (r9 != r0) goto L_0x02f9
            r7 = r22
            r0 = 2
            if (r7 != r0) goto L_0x02f7
            r0 = r29
            r1 = r30
            r2 = r31
            r3 = r14
            r4 = r33
            r5 = r24
            r6 = r25
            r8 = r34
            int r0 = r0.zzs(r1, r2, r3, r4, r5, r6, r8)
            if (r0 == r14) goto L_0x02c0
            goto L_0x02a6
        L_0x02f7:
            r2 = r14
            goto L_0x02c1
        L_0x02f9:
            r7 = r22
            r0 = r29
            r8 = r1
            r1 = r30
            r2 = r31
            r3 = r14
            r4 = r33
            r5 = r17
            r6 = r21
            r10 = r25
            r12 = r24
            r13 = r34
            int r0 = r0.zzt(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13)
            if (r0 == r14) goto L_0x02c0
            goto L_0x02a6
        L_0x0316:
            com.google.android.gms.internal.measurement.zzmj r4 = zzd(r30)
            r0 = r17
            r1 = r31
            r3 = r33
            r5 = r34
            int r0 = com.google.android.gms.internal.measurement.zzil.zzi(r0, r1, r2, r3, r4, r5)
            r15 = r29
            r14 = r30
            r12 = r31
            r13 = r33
            r11 = r34
            r8 = r18
            r1 = r21
            r2 = r24
            r9 = r28
            r10 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x0019
        L_0x033d:
            r23 = r6
            r15 = r7
            r28 = r9
            r1 = r10
            if (r15 == r1) goto L_0x034f
            long r1 = (long) r15
            r3 = r30
            r6 = r23
            r4 = r28
            r4.putInt(r3, r1, r6)
        L_0x034f:
            r1 = r33
            if (r0 != r1) goto L_0x0354
            return r0
        L_0x0354:
            com.google.android.gms.internal.measurement.zzkj r0 = com.google.android.gms.internal.measurement.zzkj.zze()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlj.zzu(java.lang.Object, byte[], int, int, com.google.android.gms.internal.measurement.zzik):int");
    }

    private int zzv(java.lang.Object r16, byte[] r17, int r18, int r19, int r20, int r21, int r22, int r23, long r24, int r26, long r27, com.google.android.gms.internal.measurement.zzik r29) throws java.io.IOException {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r3 = r17
            r4 = r18
            r5 = r19
            r2 = r20
            r6 = r22
            r8 = r23
            r9 = r27
            r7 = r29
            sun.misc.Unsafe r11 = zzb
            java.lang.Object r12 = r11.getObject(r1, r9)
            com.google.android.gms.internal.measurement.zzkg r12 = (com.google.android.gms.internal.measurement.zzkg) r12
            boolean r13 = r12.zzc()
            if (r13 != 0) goto L_0x0032
            int r13 = r12.size()
            if (r13 != 0) goto L_0x002a
            r13 = 10
            goto L_0x002b
        L_0x002a:
            int r13 = r13 + r13
        L_0x002b:
            com.google.android.gms.internal.measurement.zzkg r12 = r12.zzd(r13)
            r11.putObject(r1, r9, r12)
        L_0x0032:
            r9 = 5
            r10 = 0
            r13 = 1
            r14 = 2
            switch(r26) {
                case 18: goto L_0x0401;
                case 19: goto L_0x03b4;
                case 20: goto L_0x0371;
                case 21: goto L_0x0371;
                case 22: goto L_0x0356;
                case 23: goto L_0x0315;
                case 24: goto L_0x02d4;
                case 25: goto L_0x027b;
                case 26: goto L_0x01c8;
                case 27: goto L_0x01ad;
                case 28: goto L_0x0153;
                case 29: goto L_0x0356;
                case 30: goto L_0x011a;
                case 31: goto L_0x02d4;
                case 32: goto L_0x0315;
                case 33: goto L_0x00cb;
                case 34: goto L_0x007c;
                case 35: goto L_0x0401;
                case 36: goto L_0x03b4;
                case 37: goto L_0x0371;
                case 38: goto L_0x0371;
                case 39: goto L_0x0356;
                case 40: goto L_0x0315;
                case 41: goto L_0x02d4;
                case 42: goto L_0x027b;
                case 43: goto L_0x0356;
                case 44: goto L_0x011a;
                case 45: goto L_0x02d4;
                case 46: goto L_0x0315;
                case 47: goto L_0x00cb;
                case 48: goto L_0x007c;
                default: goto L_0x003a;
            }
        L_0x003a:
            r1 = 3
            if (r6 != r1) goto L_0x044d
            com.google.android.gms.internal.measurement.zzlr r1 = r15.zzE(r8)
            r6 = r2 & -8
            r6 = r6 | 4
            r21 = r1
            r22 = r17
            r23 = r18
            r24 = r19
            r25 = r6
            r26 = r29
            int r4 = com.google.android.gms.internal.measurement.zzil.zzc(r21, r22, r23, r24, r25, r26)
            java.lang.Object r8 = r7.zzc
            r12.add(r8)
        L_0x005a:
            if (r4 >= r5) goto L_0x007b
            int r8 = com.google.android.gms.internal.measurement.zzil.zzj(r3, r4, r7)
            int r9 = r7.zza
            if (r2 == r9) goto L_0x0065
            goto L_0x007b
        L_0x0065:
            r21 = r1
            r22 = r17
            r23 = r8
            r24 = r19
            r25 = r6
            r26 = r29
            int r4 = com.google.android.gms.internal.measurement.zzil.zzc(r21, r22, r23, r24, r25, r26)
            java.lang.Object r8 = r7.zzc
            r12.add(r8)
            goto L_0x005a
        L_0x007b:
            return r4
        L_0x007c:
            if (r6 != r14) goto L_0x00a0
            com.google.android.gms.internal.measurement.zzkv r12 = (com.google.android.gms.internal.measurement.zzkv) r12
            int r1 = com.google.android.gms.internal.measurement.zzil.zzj(r3, r4, r7)
            int r2 = r7.zza
            int r2 = r2 + r1
        L_0x0087:
            if (r1 >= r2) goto L_0x0097
            int r1 = com.google.android.gms.internal.measurement.zzil.zzm(r3, r1, r7)
            long r4 = r7.zzb
            long r4 = com.google.android.gms.internal.measurement.zzjc.zzc(r4)
            r12.zzg(r4)
            goto L_0x0087
        L_0x0097:
            if (r1 != r2) goto L_0x009b
            goto L_0x044e
        L_0x009b:
            com.google.android.gms.internal.measurement.zzkj r1 = com.google.android.gms.internal.measurement.zzkj.zzf()
            throw r1
        L_0x00a0:
            if (r6 != 0) goto L_0x044d
            com.google.android.gms.internal.measurement.zzkv r12 = (com.google.android.gms.internal.measurement.zzkv) r12
            int r1 = com.google.android.gms.internal.measurement.zzil.zzm(r3, r4, r7)
            long r8 = r7.zzb
            long r8 = com.google.android.gms.internal.measurement.zzjc.zzc(r8)
            r12.zzg(r8)
        L_0x00b1:
            if (r1 >= r5) goto L_0x00ca
            int r4 = com.google.android.gms.internal.measurement.zzil.zzj(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x00bc
            goto L_0x00ca
        L_0x00bc:
            int r1 = com.google.android.gms.internal.measurement.zzil.zzm(r3, r4, r7)
            long r8 = r7.zzb
            long r8 = com.google.android.gms.internal.measurement.zzjc.zzc(r8)
            r12.zzg(r8)
            goto L_0x00b1
        L_0x00ca:
            return r1
        L_0x00cb:
            if (r6 != r14) goto L_0x00ef
            com.google.android.gms.internal.measurement.zzka r12 = (com.google.android.gms.internal.measurement.zzka) r12
            int r1 = com.google.android.gms.internal.measurement.zzil.zzj(r3, r4, r7)
            int r2 = r7.zza
            int r2 = r2 + r1
        L_0x00d6:
            if (r1 >= r2) goto L_0x00e6
            int r1 = com.google.android.gms.internal.measurement.zzil.zzj(r3, r1, r7)
            int r4 = r7.zza
            int r4 = com.google.android.gms.internal.measurement.zzjc.zzb(r4)
            r12.zzh(r4)
            goto L_0x00d6
        L_0x00e6:
            if (r1 != r2) goto L_0x00ea
            goto L_0x044e
        L_0x00ea:
            com.google.android.gms.internal.measurement.zzkj r1 = com.google.android.gms.internal.measurement.zzkj.zzf()
            throw r1
        L_0x00ef:
            if (r6 != 0) goto L_0x044d
            com.google.android.gms.internal.measurement.zzka r12 = (com.google.android.gms.internal.measurement.zzka) r12
            int r1 = com.google.android.gms.internal.measurement.zzil.zzj(r3, r4, r7)
            int r4 = r7.zza
            int r4 = com.google.android.gms.internal.measurement.zzjc.zzb(r4)
            r12.zzh(r4)
        L_0x0100:
            if (r1 >= r5) goto L_0x0119
            int r4 = com.google.android.gms.internal.measurement.zzil.zzj(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x010b
            goto L_0x0119
        L_0x010b:
            int r1 = com.google.android.gms.internal.measurement.zzil.zzj(r3, r4, r7)
            int r4 = r7.zza
            int r4 = com.google.android.gms.internal.measurement.zzjc.zzb(r4)
            r12.zzh(r4)
            goto L_0x0100
        L_0x0119:
            return r1
        L_0x011a:
            if (r6 != r14) goto L_0x0121
            int r2 = com.google.android.gms.internal.measurement.zzil.zzf(r3, r4, r12, r7)
            goto L_0x0132
        L_0x0121:
            if (r6 != 0) goto L_0x044d
            r2 = r20
            r3 = r17
            r4 = r18
            r5 = r19
            r6 = r12
            r7 = r29
            int r2 = com.google.android.gms.internal.measurement.zzil.zzl(r2, r3, r4, r5, r6, r7)
        L_0x0132:
            com.google.android.gms.internal.measurement.zzjz r1 = (com.google.android.gms.internal.measurement.zzjz) r1
            com.google.android.gms.internal.measurement.zzmj r3 = r1.zzc
            com.google.android.gms.internal.measurement.zzmj r4 = com.google.android.gms.internal.measurement.zzmj.zzc()
            if (r3 != r4) goto L_0x013d
            r3 = 0
        L_0x013d:
            com.google.android.gms.internal.measurement.zzkd r4 = r15.zzD(r8)
            com.google.android.gms.internal.measurement.zzmi r5 = r0.zzn
            r6 = r21
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzlt.zzC(r6, r12, r4, r3, r5)
            if (r3 != 0) goto L_0x014e
        L_0x014b:
            r1 = r2
            goto L_0x044e
        L_0x014e:
            com.google.android.gms.internal.measurement.zzmj r3 = (com.google.android.gms.internal.measurement.zzmj) r3
            r1.zzc = r3
            return r2
        L_0x0153:
            if (r6 != r14) goto L_0x044d
            int r1 = com.google.android.gms.internal.measurement.zzil.zzj(r3, r4, r7)
            int r4 = r7.zza
            if (r4 < 0) goto L_0x01a8
            int r6 = r3.length
            int r6 = r6 - r1
            if (r4 > r6) goto L_0x01a3
            if (r4 != 0) goto L_0x0169
            com.google.android.gms.internal.measurement.zziy r4 = com.google.android.gms.internal.measurement.zziy.zzb
            r12.add(r4)
            goto L_0x0171
        L_0x0169:
            com.google.android.gms.internal.measurement.zziy r6 = com.google.android.gms.internal.measurement.zziy.zzl(r3, r1, r4)
            r12.add(r6)
        L_0x0170:
            int r1 = r1 + r4
        L_0x0171:
            if (r1 >= r5) goto L_0x01a2
            int r4 = com.google.android.gms.internal.measurement.zzil.zzj(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x017c
            goto L_0x01a2
        L_0x017c:
            int r1 = com.google.android.gms.internal.measurement.zzil.zzj(r3, r4, r7)
            int r4 = r7.zza
            if (r4 < 0) goto L_0x019d
            int r6 = r3.length
            int r6 = r6 - r1
            if (r4 > r6) goto L_0x0198
            if (r4 != 0) goto L_0x0190
            com.google.android.gms.internal.measurement.zziy r4 = com.google.android.gms.internal.measurement.zziy.zzb
            r12.add(r4)
            goto L_0x0171
        L_0x0190:
            com.google.android.gms.internal.measurement.zziy r6 = com.google.android.gms.internal.measurement.zziy.zzl(r3, r1, r4)
            r12.add(r6)
            goto L_0x0170
        L_0x0198:
            com.google.android.gms.internal.measurement.zzkj r1 = com.google.android.gms.internal.measurement.zzkj.zzf()
            throw r1
        L_0x019d:
            com.google.android.gms.internal.measurement.zzkj r1 = com.google.android.gms.internal.measurement.zzkj.zzd()
            throw r1
        L_0x01a2:
            return r1
        L_0x01a3:
            com.google.android.gms.internal.measurement.zzkj r1 = com.google.android.gms.internal.measurement.zzkj.zzf()
            throw r1
        L_0x01a8:
            com.google.android.gms.internal.measurement.zzkj r1 = com.google.android.gms.internal.measurement.zzkj.zzd()
            throw r1
        L_0x01ad:
            if (r6 == r14) goto L_0x01b1
            goto L_0x044d
        L_0x01b1:
            com.google.android.gms.internal.measurement.zzlr r1 = r15.zzE(r8)
            r21 = r1
            r22 = r20
            r23 = r17
            r24 = r18
            r25 = r19
            r26 = r12
            r27 = r29
            int r1 = com.google.android.gms.internal.measurement.zzil.zze(r21, r22, r23, r24, r25, r26, r27)
            return r1
        L_0x01c8:
            if (r6 != r14) goto L_0x044d
            r8 = 536870912(0x20000000, double:2.652494739E-315)
            long r8 = r24 & r8
            int r1 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            java.lang.String r6 = ""
            if (r1 != 0) goto L_0x021b
            int r1 = com.google.android.gms.internal.measurement.zzil.zzj(r3, r4, r7)
            int r4 = r7.zza
            if (r4 < 0) goto L_0x0216
            if (r4 != 0) goto L_0x01e3
            r12.add(r6)
            goto L_0x01ee
        L_0x01e3:
            java.lang.String r8 = new java.lang.String
            java.nio.charset.Charset r9 = com.google.android.gms.internal.measurement.zzkh.zzb
            r8.<init>(r3, r1, r4, r9)
            r12.add(r8)
        L_0x01ed:
            int r1 = r1 + r4
        L_0x01ee:
            if (r1 >= r5) goto L_0x044e
            int r4 = com.google.android.gms.internal.measurement.zzil.zzj(r3, r1, r7)
            int r8 = r7.zza
            if (r2 != r8) goto L_0x044e
            int r1 = com.google.android.gms.internal.measurement.zzil.zzj(r3, r4, r7)
            int r4 = r7.zza
            if (r4 < 0) goto L_0x0211
            if (r4 != 0) goto L_0x0206
            r12.add(r6)
            goto L_0x01ee
        L_0x0206:
            java.lang.String r8 = new java.lang.String
            java.nio.charset.Charset r9 = com.google.android.gms.internal.measurement.zzkh.zzb
            r8.<init>(r3, r1, r4, r9)
            r12.add(r8)
            goto L_0x01ed
        L_0x0211:
            com.google.android.gms.internal.measurement.zzkj r1 = com.google.android.gms.internal.measurement.zzkj.zzd()
            throw r1
        L_0x0216:
            com.google.android.gms.internal.measurement.zzkj r1 = com.google.android.gms.internal.measurement.zzkj.zzd()
            throw r1
        L_0x021b:
            int r1 = com.google.android.gms.internal.measurement.zzil.zzj(r3, r4, r7)
            int r4 = r7.zza
            if (r4 < 0) goto L_0x0276
            if (r4 != 0) goto L_0x0229
            r12.add(r6)
            goto L_0x023c
        L_0x0229:
            int r8 = r1 + r4
            boolean r9 = com.google.android.gms.internal.measurement.zzmx.zzf(r3, r1, r8)
            if (r9 == 0) goto L_0x0271
            java.lang.String r9 = new java.lang.String
            java.nio.charset.Charset r10 = com.google.android.gms.internal.measurement.zzkh.zzb
            r9.<init>(r3, r1, r4, r10)
            r12.add(r9)
        L_0x023b:
            r1 = r8
        L_0x023c:
            if (r1 >= r5) goto L_0x044e
            int r4 = com.google.android.gms.internal.measurement.zzil.zzj(r3, r1, r7)
            int r8 = r7.zza
            if (r2 != r8) goto L_0x044e
            int r1 = com.google.android.gms.internal.measurement.zzil.zzj(r3, r4, r7)
            int r4 = r7.zza
            if (r4 < 0) goto L_0x026c
            if (r4 != 0) goto L_0x0254
            r12.add(r6)
            goto L_0x023c
        L_0x0254:
            int r8 = r1 + r4
            boolean r9 = com.google.android.gms.internal.measurement.zzmx.zzf(r3, r1, r8)
            if (r9 == 0) goto L_0x0267
            java.lang.String r9 = new java.lang.String
            java.nio.charset.Charset r10 = com.google.android.gms.internal.measurement.zzkh.zzb
            r9.<init>(r3, r1, r4, r10)
            r12.add(r9)
            goto L_0x023b
        L_0x0267:
            com.google.android.gms.internal.measurement.zzkj r1 = com.google.android.gms.internal.measurement.zzkj.zzc()
            throw r1
        L_0x026c:
            com.google.android.gms.internal.measurement.zzkj r1 = com.google.android.gms.internal.measurement.zzkj.zzd()
            throw r1
        L_0x0271:
            com.google.android.gms.internal.measurement.zzkj r1 = com.google.android.gms.internal.measurement.zzkj.zzc()
            throw r1
        L_0x0276:
            com.google.android.gms.internal.measurement.zzkj r1 = com.google.android.gms.internal.measurement.zzkj.zzd()
            throw r1
        L_0x027b:
            r1 = 0
            if (r6 != r14) goto L_0x02a3
            com.google.android.gms.internal.measurement.zzim r12 = (com.google.android.gms.internal.measurement.zzim) r12
            int r2 = com.google.android.gms.internal.measurement.zzil.zzj(r3, r4, r7)
            int r4 = r7.zza
            int r4 = r4 + r2
        L_0x0287:
            if (r2 >= r4) goto L_0x029a
            int r2 = com.google.android.gms.internal.measurement.zzil.zzm(r3, r2, r7)
            long r5 = r7.zzb
            int r5 = (r5 > r10 ? 1 : (r5 == r10 ? 0 : -1))
            if (r5 == 0) goto L_0x0295
            r5 = r13
            goto L_0x0296
        L_0x0295:
            r5 = r1
        L_0x0296:
            r12.zze(r5)
            goto L_0x0287
        L_0x029a:
            if (r2 != r4) goto L_0x029e
            goto L_0x014b
        L_0x029e:
            com.google.android.gms.internal.measurement.zzkj r1 = com.google.android.gms.internal.measurement.zzkj.zzf()
            throw r1
        L_0x02a3:
            if (r6 != 0) goto L_0x044d
            com.google.android.gms.internal.measurement.zzim r12 = (com.google.android.gms.internal.measurement.zzim) r12
            int r4 = com.google.android.gms.internal.measurement.zzil.zzm(r3, r4, r7)
            long r8 = r7.zzb
            int r6 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r6 == 0) goto L_0x02b3
            r6 = r13
            goto L_0x02b4
        L_0x02b3:
            r6 = r1
        L_0x02b4:
            r12.zze(r6)
        L_0x02b7:
            if (r4 >= r5) goto L_0x02d3
            int r6 = com.google.android.gms.internal.measurement.zzil.zzj(r3, r4, r7)
            int r8 = r7.zza
            if (r2 == r8) goto L_0x02c2
            goto L_0x02d3
        L_0x02c2:
            int r4 = com.google.android.gms.internal.measurement.zzil.zzm(r3, r6, r7)
            long r8 = r7.zzb
            int r6 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r6 == 0) goto L_0x02ce
            r6 = r13
            goto L_0x02cf
        L_0x02ce:
            r6 = r1
        L_0x02cf:
            r12.zze(r6)
            goto L_0x02b7
        L_0x02d3:
            return r4
        L_0x02d4:
            if (r6 != r14) goto L_0x02f4
            com.google.android.gms.internal.measurement.zzka r12 = (com.google.android.gms.internal.measurement.zzka) r12
            int r1 = com.google.android.gms.internal.measurement.zzil.zzj(r3, r4, r7)
            int r2 = r7.zza
            int r2 = r2 + r1
        L_0x02df:
            if (r1 >= r2) goto L_0x02eb
            int r4 = com.google.android.gms.internal.measurement.zzil.zzb(r3, r1)
            r12.zzh(r4)
            int r1 = r1 + 4
            goto L_0x02df
        L_0x02eb:
            if (r1 != r2) goto L_0x02ef
            goto L_0x044e
        L_0x02ef:
            com.google.android.gms.internal.measurement.zzkj r1 = com.google.android.gms.internal.measurement.zzkj.zzf()
            throw r1
        L_0x02f4:
            if (r6 != r9) goto L_0x044d
            com.google.android.gms.internal.measurement.zzka r12 = (com.google.android.gms.internal.measurement.zzka) r12
            int r1 = com.google.android.gms.internal.measurement.zzil.zzb(r17, r18)
            r12.zzh(r1)
        L_0x02ff:
            int r1 = r4 + 4
            if (r1 >= r5) goto L_0x0314
            int r4 = com.google.android.gms.internal.measurement.zzil.zzj(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x030c
            goto L_0x0314
        L_0x030c:
            int r1 = com.google.android.gms.internal.measurement.zzil.zzb(r3, r4)
            r12.zzh(r1)
            goto L_0x02ff
        L_0x0314:
            return r1
        L_0x0315:
            if (r6 != r14) goto L_0x0335
            com.google.android.gms.internal.measurement.zzkv r12 = (com.google.android.gms.internal.measurement.zzkv) r12
            int r1 = com.google.android.gms.internal.measurement.zzil.zzj(r3, r4, r7)
            int r2 = r7.zza
            int r2 = r2 + r1
        L_0x0320:
            if (r1 >= r2) goto L_0x032c
            long r4 = com.google.android.gms.internal.measurement.zzil.zzn(r3, r1)
            r12.zzg(r4)
            int r1 = r1 + 8
            goto L_0x0320
        L_0x032c:
            if (r1 != r2) goto L_0x0330
            goto L_0x044e
        L_0x0330:
            com.google.android.gms.internal.measurement.zzkj r1 = com.google.android.gms.internal.measurement.zzkj.zzf()
            throw r1
        L_0x0335:
            if (r6 != r13) goto L_0x044d
            com.google.android.gms.internal.measurement.zzkv r12 = (com.google.android.gms.internal.measurement.zzkv) r12
            long r8 = com.google.android.gms.internal.measurement.zzil.zzn(r17, r18)
            r12.zzg(r8)
        L_0x0340:
            int r1 = r4 + 8
            if (r1 >= r5) goto L_0x0355
            int r4 = com.google.android.gms.internal.measurement.zzil.zzj(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x034d
            goto L_0x0355
        L_0x034d:
            long r8 = com.google.android.gms.internal.measurement.zzil.zzn(r3, r4)
            r12.zzg(r8)
            goto L_0x0340
        L_0x0355:
            return r1
        L_0x0356:
            if (r6 != r14) goto L_0x035e
            int r1 = com.google.android.gms.internal.measurement.zzil.zzf(r3, r4, r12, r7)
            goto L_0x044e
        L_0x035e:
            if (r6 == 0) goto L_0x0362
            goto L_0x044d
        L_0x0362:
            r21 = r17
            r22 = r18
            r23 = r19
            r24 = r12
            r25 = r29
            int r1 = com.google.android.gms.internal.measurement.zzil.zzl(r20, r21, r22, r23, r24, r25)
            return r1
        L_0x0371:
            if (r6 != r14) goto L_0x0391
            com.google.android.gms.internal.measurement.zzkv r12 = (com.google.android.gms.internal.measurement.zzkv) r12
            int r1 = com.google.android.gms.internal.measurement.zzil.zzj(r3, r4, r7)
            int r2 = r7.zza
            int r2 = r2 + r1
        L_0x037c:
            if (r1 >= r2) goto L_0x0388
            int r1 = com.google.android.gms.internal.measurement.zzil.zzm(r3, r1, r7)
            long r4 = r7.zzb
            r12.zzg(r4)
            goto L_0x037c
        L_0x0388:
            if (r1 != r2) goto L_0x038c
            goto L_0x044e
        L_0x038c:
            com.google.android.gms.internal.measurement.zzkj r1 = com.google.android.gms.internal.measurement.zzkj.zzf()
            throw r1
        L_0x0391:
            if (r6 != 0) goto L_0x044d
            com.google.android.gms.internal.measurement.zzkv r12 = (com.google.android.gms.internal.measurement.zzkv) r12
            int r1 = com.google.android.gms.internal.measurement.zzil.zzm(r3, r4, r7)
            long r8 = r7.zzb
            r12.zzg(r8)
        L_0x039e:
            if (r1 >= r5) goto L_0x03b3
            int r4 = com.google.android.gms.internal.measurement.zzil.zzj(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x03a9
            goto L_0x03b3
        L_0x03a9:
            int r1 = com.google.android.gms.internal.measurement.zzil.zzm(r3, r4, r7)
            long r8 = r7.zzb
            r12.zzg(r8)
            goto L_0x039e
        L_0x03b3:
            return r1
        L_0x03b4:
            if (r6 != r14) goto L_0x03d8
            com.google.android.gms.internal.measurement.zzjs r12 = (com.google.android.gms.internal.measurement.zzjs) r12
            int r1 = com.google.android.gms.internal.measurement.zzil.zzj(r3, r4, r7)
            int r2 = r7.zza
            int r2 = r2 + r1
        L_0x03bf:
            if (r1 >= r2) goto L_0x03cf
            int r4 = com.google.android.gms.internal.measurement.zzil.zzb(r3, r1)
            float r4 = java.lang.Float.intBitsToFloat(r4)
            r12.zze(r4)
            int r1 = r1 + 4
            goto L_0x03bf
        L_0x03cf:
            if (r1 != r2) goto L_0x03d3
            goto L_0x044e
        L_0x03d3:
            com.google.android.gms.internal.measurement.zzkj r1 = com.google.android.gms.internal.measurement.zzkj.zzf()
            throw r1
        L_0x03d8:
            if (r6 != r9) goto L_0x044d
            com.google.android.gms.internal.measurement.zzjs r12 = (com.google.android.gms.internal.measurement.zzjs) r12
            int r1 = com.google.android.gms.internal.measurement.zzil.zzb(r17, r18)
            float r1 = java.lang.Float.intBitsToFloat(r1)
            r12.zze(r1)
        L_0x03e7:
            int r1 = r4 + 4
            if (r1 >= r5) goto L_0x0400
            int r4 = com.google.android.gms.internal.measurement.zzil.zzj(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x03f4
            goto L_0x0400
        L_0x03f4:
            int r1 = com.google.android.gms.internal.measurement.zzil.zzb(r3, r4)
            float r1 = java.lang.Float.intBitsToFloat(r1)
            r12.zze(r1)
            goto L_0x03e7
        L_0x0400:
            return r1
        L_0x0401:
            if (r6 != r14) goto L_0x0424
            com.google.android.gms.internal.measurement.zzji r12 = (com.google.android.gms.internal.measurement.zzji) r12
            int r1 = com.google.android.gms.internal.measurement.zzil.zzj(r3, r4, r7)
            int r2 = r7.zza
            int r2 = r2 + r1
        L_0x040c:
            if (r1 >= r2) goto L_0x041c
            long r4 = com.google.android.gms.internal.measurement.zzil.zzn(r3, r1)
            double r4 = java.lang.Double.longBitsToDouble(r4)
            r12.zze(r4)
            int r1 = r1 + 8
            goto L_0x040c
        L_0x041c:
            if (r1 != r2) goto L_0x041f
            goto L_0x044e
        L_0x041f:
            com.google.android.gms.internal.measurement.zzkj r1 = com.google.android.gms.internal.measurement.zzkj.zzf()
            throw r1
        L_0x0424:
            if (r6 != r13) goto L_0x044d
            com.google.android.gms.internal.measurement.zzji r12 = (com.google.android.gms.internal.measurement.zzji) r12
            long r8 = com.google.android.gms.internal.measurement.zzil.zzn(r17, r18)
            double r8 = java.lang.Double.longBitsToDouble(r8)
            r12.zze(r8)
        L_0x0433:
            int r1 = r4 + 8
            if (r1 >= r5) goto L_0x044c
            int r4 = com.google.android.gms.internal.measurement.zzil.zzj(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x0440
            goto L_0x044c
        L_0x0440:
            long r8 = com.google.android.gms.internal.measurement.zzil.zzn(r3, r4)
            double r8 = java.lang.Double.longBitsToDouble(r8)
            r12.zze(r8)
            goto L_0x0433
        L_0x044c:
            return r1
        L_0x044d:
            r1 = r4
        L_0x044e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlj.zzv(java.lang.Object, byte[], int, int, int, int, int, int, long, int, long, com.google.android.gms.internal.measurement.zzik):int");
    }

    private int zzw(int i2) {
        if (i2 < this.zze || i2 > this.zzf) {
            return -1;
        }
        return zzz(i2, 0);
    }

    private int zzx(int i2, int i3) {
        if (i2 < this.zze || i2 > this.zzf) {
            return -1;
        }
        return zzz(i2, i3);
    }

    private int zzy(int i2) {
        return this.zzc[i2 + 2];
    }

    private int zzz(int i2, int i3) {
        int length = (this.zzc.length / 3) - 1;
        while (i3 <= length) {
            int i4 = (length + i3) >>> 1;
            int i5 = i4 * 3;
            int i6 = this.zzc[i5];
            if (i2 == i6) {
                return i5;
            }
            if (i2 < i6) {
                length = i4 - 1;
            } else {
                i3 = i4 + 1;
            }
        }
        return -1;
    }

    public int zza(Object obj) {
        return this.zzi ? zzq(obj) : zzp(obj);
    }

    public int zzb(java.lang.Object r9) {
        /*
            r8 = this;
            int[] r0 = r8.zzc
            int r0 = r0.length
            r1 = 0
            r2 = r1
        L_0x0005:
            if (r1 >= r0) goto L_0x022e
            int r3 = r8.zzB(r1)
            int[] r4 = r8.zzc
            r4 = r4[r1]
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r5 = r5 & r3
            long r5 = (long) r5
            int r3 = zzA(r3)
            r7 = 37
            switch(r3) {
                case 0: goto L_0x021a;
                case 1: goto L_0x020e;
                case 2: goto L_0x0202;
                case 3: goto L_0x01f6;
                case 4: goto L_0x01ee;
                case 5: goto L_0x01e2;
                case 6: goto L_0x01da;
                case 7: goto L_0x01ce;
                case 8: goto L_0x01c0;
                case 9: goto L_0x01b5;
                case 10: goto L_0x01a9;
                case 11: goto L_0x01a1;
                case 12: goto L_0x0199;
                case 13: goto L_0x0191;
                case 14: goto L_0x0185;
                case 15: goto L_0x017d;
                case 16: goto L_0x0171;
                case 17: goto L_0x0162;
                case 18: goto L_0x0156;
                case 19: goto L_0x0156;
                case 20: goto L_0x0156;
                case 21: goto L_0x0156;
                case 22: goto L_0x0156;
                case 23: goto L_0x0156;
                case 24: goto L_0x0156;
                case 25: goto L_0x0156;
                case 26: goto L_0x0156;
                case 27: goto L_0x0156;
                case 28: goto L_0x0156;
                case 29: goto L_0x0156;
                case 30: goto L_0x0156;
                case 31: goto L_0x0156;
                case 32: goto L_0x0156;
                case 33: goto L_0x0156;
                case 34: goto L_0x0156;
                case 35: goto L_0x0156;
                case 36: goto L_0x0156;
                case 37: goto L_0x0156;
                case 38: goto L_0x0156;
                case 39: goto L_0x0156;
                case 40: goto L_0x0156;
                case 41: goto L_0x0156;
                case 42: goto L_0x0156;
                case 43: goto L_0x0156;
                case 44: goto L_0x0156;
                case 45: goto L_0x0156;
                case 46: goto L_0x0156;
                case 47: goto L_0x0156;
                case 48: goto L_0x0156;
                case 49: goto L_0x0156;
                case 50: goto L_0x014a;
                case 51: goto L_0x0134;
                case 52: goto L_0x0122;
                case 53: goto L_0x0110;
                case 54: goto L_0x00fe;
                case 55: goto L_0x00f0;
                case 56: goto L_0x00de;
                case 57: goto L_0x00d0;
                case 58: goto L_0x00be;
                case 59: goto L_0x00aa;
                case 60: goto L_0x0099;
                case 61: goto L_0x0088;
                case 62: goto L_0x007b;
                case 63: goto L_0x006e;
                case 64: goto L_0x0061;
                case 65: goto L_0x0050;
                case 66: goto L_0x0043;
                case 67: goto L_0x0032;
                case 68: goto L_0x001f;
                default: goto L_0x001d;
            }
        L_0x001d:
            goto L_0x022a
        L_0x001f:
            boolean r3 = r8.zzP(r9, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzms.zzf(r9, r5)
            int r3 = r3.hashCode()
        L_0x002f:
            int r2 = r2 + r3
            goto L_0x022a
        L_0x0032:
            boolean r3 = r8.zzP(r9, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            long r3 = zzC(r9, r5)
            int r3 = com.google.android.gms.internal.measurement.zzkh.zzc(r3)
            goto L_0x002f
        L_0x0043:
            boolean r3 = r8.zzP(r9, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            int r3 = zzr(r9, r5)
            goto L_0x002f
        L_0x0050:
            boolean r3 = r8.zzP(r9, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            long r3 = zzC(r9, r5)
            int r3 = com.google.android.gms.internal.measurement.zzkh.zzc(r3)
            goto L_0x002f
        L_0x0061:
            boolean r3 = r8.zzP(r9, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            int r3 = zzr(r9, r5)
            goto L_0x002f
        L_0x006e:
            boolean r3 = r8.zzP(r9, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            int r3 = zzr(r9, r5)
            goto L_0x002f
        L_0x007b:
            boolean r3 = r8.zzP(r9, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            int r3 = zzr(r9, r5)
            goto L_0x002f
        L_0x0088:
            boolean r3 = r8.zzP(r9, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzms.zzf(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x002f
        L_0x0099:
            boolean r3 = r8.zzP(r9, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzms.zzf(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x002f
        L_0x00aa:
            boolean r3 = r8.zzP(r9, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzms.zzf(r9, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x002f
        L_0x00be:
            boolean r3 = r8.zzP(r9, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            boolean r3 = zzQ(r9, r5)
            int r3 = com.google.android.gms.internal.measurement.zzkh.zza(r3)
            goto L_0x002f
        L_0x00d0:
            boolean r3 = r8.zzP(r9, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            int r3 = zzr(r9, r5)
            goto L_0x002f
        L_0x00de:
            boolean r3 = r8.zzP(r9, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            long r3 = zzC(r9, r5)
            int r3 = com.google.android.gms.internal.measurement.zzkh.zzc(r3)
            goto L_0x002f
        L_0x00f0:
            boolean r3 = r8.zzP(r9, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            int r3 = zzr(r9, r5)
            goto L_0x002f
        L_0x00fe:
            boolean r3 = r8.zzP(r9, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            long r3 = zzC(r9, r5)
            int r3 = com.google.android.gms.internal.measurement.zzkh.zzc(r3)
            goto L_0x002f
        L_0x0110:
            boolean r3 = r8.zzP(r9, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            long r3 = zzC(r9, r5)
            int r3 = com.google.android.gms.internal.measurement.zzkh.zzc(r3)
            goto L_0x002f
        L_0x0122:
            boolean r3 = r8.zzP(r9, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            float r3 = zzo(r9, r5)
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x002f
        L_0x0134:
            boolean r3 = r8.zzP(r9, r4, r1)
            if (r3 == 0) goto L_0x022a
            int r2 = r2 * 53
            double r3 = zzn(r9, r5)
            long r3 = java.lang.Double.doubleToLongBits(r3)
            int r3 = com.google.android.gms.internal.measurement.zzkh.zzc(r3)
            goto L_0x002f
        L_0x014a:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzms.zzf(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x002f
        L_0x0156:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzms.zzf(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x002f
        L_0x0162:
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzms.zzf(r9, r5)
            if (r3 == 0) goto L_0x016c
            int r7 = r3.hashCode()
        L_0x016c:
            int r2 = r2 * 53
            int r2 = r2 + r7
            goto L_0x022a
        L_0x0171:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.measurement.zzms.zzd(r9, r5)
            int r3 = com.google.android.gms.internal.measurement.zzkh.zzc(r3)
            goto L_0x002f
        L_0x017d:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.measurement.zzms.zzc(r9, r5)
            goto L_0x002f
        L_0x0185:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.measurement.zzms.zzd(r9, r5)
            int r3 = com.google.android.gms.internal.measurement.zzkh.zzc(r3)
            goto L_0x002f
        L_0x0191:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.measurement.zzms.zzc(r9, r5)
            goto L_0x002f
        L_0x0199:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.measurement.zzms.zzc(r9, r5)
            goto L_0x002f
        L_0x01a1:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.measurement.zzms.zzc(r9, r5)
            goto L_0x002f
        L_0x01a9:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzms.zzf(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x002f
        L_0x01b5:
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzms.zzf(r9, r5)
            if (r3 == 0) goto L_0x016c
            int r7 = r3.hashCode()
            goto L_0x016c
        L_0x01c0:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzms.zzf(r9, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x002f
        L_0x01ce:
            int r2 = r2 * 53
            boolean r3 = com.google.android.gms.internal.measurement.zzms.zzw(r9, r5)
            int r3 = com.google.android.gms.internal.measurement.zzkh.zza(r3)
            goto L_0x002f
        L_0x01da:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.measurement.zzms.zzc(r9, r5)
            goto L_0x002f
        L_0x01e2:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.measurement.zzms.zzd(r9, r5)
            int r3 = com.google.android.gms.internal.measurement.zzkh.zzc(r3)
            goto L_0x002f
        L_0x01ee:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.measurement.zzms.zzc(r9, r5)
            goto L_0x002f
        L_0x01f6:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.measurement.zzms.zzd(r9, r5)
            int r3 = com.google.android.gms.internal.measurement.zzkh.zzc(r3)
            goto L_0x002f
        L_0x0202:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.measurement.zzms.zzd(r9, r5)
            int r3 = com.google.android.gms.internal.measurement.zzkh.zzc(r3)
            goto L_0x002f
        L_0x020e:
            int r2 = r2 * 53
            float r3 = com.google.android.gms.internal.measurement.zzms.zzb(r9, r5)
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x002f
        L_0x021a:
            int r2 = r2 * 53
            double r3 = com.google.android.gms.internal.measurement.zzms.zza(r9, r5)
            long r3 = java.lang.Double.doubleToLongBits(r3)
            int r3 = com.google.android.gms.internal.measurement.zzkh.zzc(r3)
            goto L_0x002f
        L_0x022a:
            int r1 = r1 + 3
            goto L_0x0005
        L_0x022e:
            int r2 = r2 * 53
            com.google.android.gms.internal.measurement.zzmi r0 = r8.zzn
            java.lang.Object r0 = r0.zzc(r9)
            int r0 = r0.hashCode()
            int r2 = r2 + r0
            boolean r0 = r8.zzh
            if (r0 != 0) goto L_0x0240
            return r2
        L_0x0240:
            com.google.android.gms.internal.measurement.zzjm r0 = r8.zzo
            r0.zza(r9)
            r9 = 0
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlj.zzb(java.lang.Object):int");
    }

    public int zzc(java.lang.Object r31, byte[] r32, int r33, int r34, int r35, com.google.android.gms.internal.measurement.zzik r36) throws java.io.IOException {
        /*
            r30 = this;
            r15 = r30
            r14 = r31
            r12 = r32
            r13 = r34
            r11 = r35
            r9 = r36
            sun.misc.Unsafe r10 = zzb
            r16 = 0
            r0 = r33
            r1 = r16
            r3 = r1
            r5 = r3
            r2 = -1
            r6 = 1048575(0xfffff, float:1.469367E-39)
        L_0x001a:
            r17 = 0
            if (r0 >= r13) goto L_0x048a
            int r1 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x002d
            int r0 = com.google.android.gms.internal.measurement.zzil.zzk(r0, r12, r1, r9)
            int r1 = r9.zza
            r4 = r1
            r1 = r0
            goto L_0x002e
        L_0x002d:
            r4 = r0
        L_0x002e:
            int r0 = r4 >>> 3
            r8 = r4 & 7
            r7 = 3
            if (r0 <= r2) goto L_0x003d
            int r3 = r3 / r7
            int r2 = r15.zzx(r0, r3)
        L_0x003a:
            r3 = r2
            r2 = -1
            goto L_0x0042
        L_0x003d:
            int r2 = r15.zzw(r0)
            goto L_0x003a
        L_0x0042:
            if (r3 != r2) goto L_0x0053
            r33 = r0
            r19 = r2
            r7 = r4
            r20 = r5
            r28 = r10
            r0 = r11
            r22 = r16
            r2 = r1
            goto L_0x0419
        L_0x0053:
            int[] r2 = r15.zzc
            int r20 = r3 + 1
            r7 = r2[r20]
            int r11 = zzA(r7)
            r20 = r1
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r7 & r18
            long r12 = (long) r1
            r1 = 17
            r21 = r4
            if (r11 > r1) goto L_0x031a
            int r1 = r3 + 2
            r1 = r2[r1]
            int r2 = r1 >>> 20
            r4 = 1
            int r22 = r4 << r2
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r1 & r2
            if (r1 == r6) goto L_0x008b
            r18 = r3
            if (r6 == r2) goto L_0x0082
            long r2 = (long) r6
            r10.putInt(r14, r2, r5)
        L_0x0082:
            long r2 = (long) r1
            int r2 = r10.getInt(r14, r2)
            r23 = r1
            r6 = r2
            goto L_0x0090
        L_0x008b:
            r18 = r3
            r23 = r6
            r6 = r5
        L_0x0090:
            r1 = 5
            switch(r11) {
                case 0: goto L_0x02ed;
                case 1: goto L_0x02d0;
                case 2: goto L_0x02af;
                case 3: goto L_0x02af;
                case 4: goto L_0x0296;
                case 5: goto L_0x0265;
                case 6: goto L_0x0245;
                case 7: goto L_0x0217;
                case 8: goto L_0x01e7;
                case 9: goto L_0x01a1;
                case 10: goto L_0x0185;
                case 11: goto L_0x0296;
                case 12: goto L_0x014f;
                case 13: goto L_0x0245;
                case 14: goto L_0x0265;
                case 15: goto L_0x0126;
                case 16: goto L_0x00ee;
                default: goto L_0x0094;
            }
        L_0x0094:
            r2 = 3
            if (r8 != r2) goto L_0x00de
            r3 = r18
            com.google.android.gms.internal.measurement.zzlr r1 = r15.zzE(r3)
            int r2 = r0 << 3
            r4 = r2 | 4
            r11 = r0
            r0 = r1
            r5 = r20
            r1 = r32
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r19 = -1
            r2 = r5
            r7 = r3
            r3 = r34
            r8 = r21
            r5 = r36
            int r0 = com.google.android.gms.internal.measurement.zzil.zzc(r0, r1, r2, r3, r4, r5)
            r1 = r6 & r22
            if (r1 != 0) goto L_0x00c2
            java.lang.Object r1 = r9.zzc
            r10.putObject(r14, r12, r1)
            goto L_0x00cf
        L_0x00c2:
            java.lang.Object r1 = r10.getObject(r14, r12)
            java.lang.Object r2 = r9.zzc
            java.lang.Object r1 = com.google.android.gms.internal.measurement.zzkh.zzg(r1, r2)
            r10.putObject(r14, r12, r1)
        L_0x00cf:
            r5 = r6 | r22
            r12 = r32
            r13 = r34
            r3 = r7
            r1 = r8
            r2 = r11
        L_0x00d8:
            r6 = r23
        L_0x00da:
            r11 = r35
            goto L_0x001a
        L_0x00de:
            r11 = r0
            r7 = r18
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r19 = -1
            r12 = r32
            r24 = r7
            r13 = r20
            goto L_0x0309
        L_0x00ee:
            r11 = r0
            r7 = r18
            r5 = r20
            r4 = r21
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r19 = -1
            if (r8 != 0) goto L_0x011d
            r2 = r12
            r12 = r32
            int r8 = com.google.android.gms.internal.measurement.zzil.zzm(r12, r5, r9)
            long r0 = r9.zzb
            long r20 = com.google.android.gms.internal.measurement.zzjc.zzc(r0)
            r0 = r10
            r1 = r31
            r13 = r4
            r4 = r20
            r0.putLong(r1, r2, r4)
            r5 = r6 | r22
            r3 = r7
            r0 = r8
        L_0x0116:
            r2 = r11
            r1 = r13
            r6 = r23
            r13 = r34
            goto L_0x00da
        L_0x011d:
            r12 = r32
            r21 = r4
            r13 = r5
            r24 = r7
            goto L_0x0309
        L_0x0126:
            r11 = r0
            r2 = r12
            r7 = r18
            r5 = r20
            r13 = r21
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r19 = -1
            r12 = r32
            if (r8 != 0) goto L_0x0148
            int r0 = com.google.android.gms.internal.measurement.zzil.zzj(r12, r5, r9)
            int r1 = r9.zza
            int r1 = com.google.android.gms.internal.measurement.zzjc.zzb(r1)
            r10.putInt(r14, r2, r1)
        L_0x0144:
            r5 = r6 | r22
        L_0x0146:
            r3 = r7
            goto L_0x0116
        L_0x0148:
            r24 = r7
        L_0x014a:
            r21 = r13
            r13 = r5
            goto L_0x0309
        L_0x014f:
            r11 = r0
            r2 = r12
            r7 = r18
            r5 = r20
            r13 = r21
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r19 = -1
            r12 = r32
            if (r8 != 0) goto L_0x0148
            int r0 = com.google.android.gms.internal.measurement.zzil.zzj(r12, r5, r9)
            int r1 = r9.zza
            com.google.android.gms.internal.measurement.zzkd r4 = r15.zzD(r7)
            if (r4 == 0) goto L_0x0181
            boolean r4 = r4.zza(r1)
            if (r4 == 0) goto L_0x0173
            goto L_0x0181
        L_0x0173:
            com.google.android.gms.internal.measurement.zzmj r2 = zzd(r31)
            long r3 = (long) r1
            java.lang.Long r1 = java.lang.Long.valueOf(r3)
            r2.zzh(r13, r1)
            r5 = r6
            goto L_0x0146
        L_0x0181:
            r10.putInt(r14, r2, r1)
            goto L_0x0144
        L_0x0185:
            r11 = r0
            r2 = r12
            r7 = r18
            r5 = r20
            r13 = r21
            r0 = 2
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r19 = -1
            r12 = r32
            if (r8 != r0) goto L_0x0148
            int r0 = com.google.android.gms.internal.measurement.zzil.zza(r12, r5, r9)
            java.lang.Object r1 = r9.zzc
            r10.putObject(r14, r2, r1)
            goto L_0x0144
        L_0x01a1:
            r11 = r0
            r2 = r12
            r7 = r18
            r5 = r20
            r13 = r21
            r0 = 2
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r19 = -1
            r12 = r32
            if (r8 != r0) goto L_0x01e3
            com.google.android.gms.internal.measurement.zzlr r0 = r15.zzE(r7)
            r1 = r34
            int r0 = com.google.android.gms.internal.measurement.zzil.zzd(r0, r12, r5, r1, r9)
            r4 = r6 & r22
            if (r4 != 0) goto L_0x01c7
            java.lang.Object r4 = r9.zzc
            r10.putObject(r14, r2, r4)
            goto L_0x01d4
        L_0x01c7:
            java.lang.Object r4 = r10.getObject(r14, r2)
            java.lang.Object r5 = r9.zzc
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzkh.zzg(r4, r5)
            r10.putObject(r14, r2, r4)
        L_0x01d4:
            r5 = r6 | r22
            r3 = r7
        L_0x01d7:
            r2 = r11
            r6 = r23
        L_0x01da:
            r11 = r35
            r29 = r13
            r13 = r1
            r1 = r29
            goto L_0x001a
        L_0x01e3:
            r1 = r34
            goto L_0x0148
        L_0x01e7:
            r1 = r34
            r11 = r0
            r2 = r12
            r0 = r18
            r5 = r20
            r13 = r21
            r4 = 2
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r19 = -1
            r12 = r32
            if (r8 != r4) goto L_0x0213
            r4 = 536870912(0x20000000, float:1.0842022E-19)
            r4 = r4 & r7
            if (r4 != 0) goto L_0x0205
            int r4 = com.google.android.gms.internal.measurement.zzil.zzg(r12, r5, r9)
            goto L_0x0209
        L_0x0205:
            int r4 = com.google.android.gms.internal.measurement.zzil.zzh(r12, r5, r9)
        L_0x0209:
            java.lang.Object r5 = r9.zzc
            r10.putObject(r14, r2, r5)
            r5 = r6 | r22
            r3 = r0
            r0 = r4
            goto L_0x01d7
        L_0x0213:
            r24 = r0
            goto L_0x014a
        L_0x0217:
            r1 = r34
            r11 = r0
            r2 = r12
            r0 = r18
            r5 = r20
            r13 = r21
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r19 = -1
            r12 = r32
            if (r8 != 0) goto L_0x0213
            int r5 = com.google.android.gms.internal.measurement.zzil.zzm(r12, r5, r9)
            long r7 = r9.zzb
            r20 = 0
            int r7 = (r7 > r20 ? 1 : (r7 == r20 ? 0 : -1))
            if (r7 == 0) goto L_0x0237
            goto L_0x0239
        L_0x0237:
            r4 = r16
        L_0x0239:
            com.google.android.gms.internal.measurement.zzms.zzm(r14, r2, r4)
            r2 = r6 | r22
            r3 = r0
            r0 = r5
            r6 = r23
            r5 = r2
            r2 = r11
            goto L_0x01da
        L_0x0245:
            r11 = r0
            r2 = r12
            r0 = r18
            r5 = r20
            r13 = r21
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r19 = -1
            r12 = r32
            if (r8 != r1) goto L_0x0213
            int r1 = com.google.android.gms.internal.measurement.zzil.zzb(r12, r5)
            r10.putInt(r14, r2, r1)
            int r1 = r5 + 4
            r5 = r6 | r22
            r3 = r0
            r0 = r1
            goto L_0x0116
        L_0x0265:
            r11 = r0
            r2 = r12
            r0 = r18
            r5 = r20
            r13 = r21
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r19 = -1
            r12 = r32
            if (r8 != r4) goto L_0x0213
            long r7 = com.google.android.gms.internal.measurement.zzil.zzn(r12, r5)
            r4 = r0
            r0 = r10
            r1 = r31
            r24 = r4
            r21 = r13
            r13 = r5
            r4 = r7
            r0.putLong(r1, r2, r4)
        L_0x0287:
            int r0 = r13 + 8
        L_0x0289:
            r5 = r6 | r22
            r13 = r34
        L_0x028d:
            r2 = r11
            r1 = r21
            r6 = r23
            r3 = r24
            goto L_0x00da
        L_0x0296:
            r11 = r0
            r2 = r12
            r24 = r18
            r13 = r20
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r19 = -1
            r12 = r32
            if (r8 != 0) goto L_0x0309
            int r0 = com.google.android.gms.internal.measurement.zzil.zzj(r12, r13, r9)
            int r1 = r9.zza
            r10.putInt(r14, r2, r1)
            goto L_0x0289
        L_0x02af:
            r11 = r0
            r2 = r12
            r24 = r18
            r13 = r20
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r19 = -1
            r12 = r32
            if (r8 != 0) goto L_0x0309
            int r7 = com.google.android.gms.internal.measurement.zzil.zzm(r12, r13, r9)
            long r4 = r9.zzb
            r0 = r10
            r1 = r31
            r0.putLong(r1, r2, r4)
            r5 = r6 | r22
            r13 = r34
            r0 = r7
            goto L_0x028d
        L_0x02d0:
            r11 = r0
            r2 = r12
            r24 = r18
            r13 = r20
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r19 = -1
            r12 = r32
            if (r8 != r1) goto L_0x0309
            int r0 = com.google.android.gms.internal.measurement.zzil.zzb(r12, r13)
            float r0 = java.lang.Float.intBitsToFloat(r0)
            com.google.android.gms.internal.measurement.zzms.zzp(r14, r2, r0)
            int r0 = r13 + 4
            goto L_0x0289
        L_0x02ed:
            r11 = r0
            r2 = r12
            r24 = r18
            r13 = r20
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r19 = -1
            r12 = r32
            if (r8 != r4) goto L_0x0309
            long r0 = com.google.android.gms.internal.measurement.zzil.zzn(r12, r13)
            double r0 = java.lang.Double.longBitsToDouble(r0)
            com.google.android.gms.internal.measurement.zzms.zzo(r14, r2, r0)
            goto L_0x0287
        L_0x0309:
            r0 = r35
            r20 = r6
            r28 = r10
            r33 = r11
            r2 = r13
            r7 = r21
            r6 = r23
            r22 = r24
            goto L_0x0419
        L_0x031a:
            r4 = r0
            r24 = r3
            r2 = r12
            r13 = r20
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r19 = -1
            r12 = r32
            r0 = 27
            if (r11 != r0) goto L_0x037a
            r0 = 2
            if (r8 != r0) goto L_0x036d
            java.lang.Object r0 = r10.getObject(r14, r2)
            com.google.android.gms.internal.measurement.zzkg r0 = (com.google.android.gms.internal.measurement.zzkg) r0
            boolean r1 = r0.zzc()
            if (r1 != 0) goto L_0x034b
            int r1 = r0.size()
            if (r1 != 0) goto L_0x0343
            r1 = 10
            goto L_0x0344
        L_0x0343:
            int r1 = r1 + r1
        L_0x0344:
            com.google.android.gms.internal.measurement.zzkg r0 = r0.zzd(r1)
            r10.putObject(r14, r2, r0)
        L_0x034b:
            r7 = r0
            r8 = r24
            com.google.android.gms.internal.measurement.zzlr r0 = r15.zzE(r8)
            r1 = r21
            r2 = r32
            r3 = r13
            r11 = r4
            r4 = r34
            r20 = r5
            r5 = r7
            r23 = r6
            r6 = r36
            int r0 = com.google.android.gms.internal.measurement.zzil.zze(r0, r1, r2, r3, r4, r5, r6)
            r13 = r34
            r3 = r8
            r2 = r11
            r5 = r20
            goto L_0x00d8
        L_0x036d:
            r20 = r5
            r23 = r6
            r33 = r4
            r28 = r10
            r15 = r13
            r22 = r24
            goto L_0x03f3
        L_0x037a:
            r20 = r5
            r23 = r6
            r22 = r24
            r5 = r4
            r0 = 49
            if (r11 > r0) goto L_0x03cf
            long r6 = (long) r7
            r0 = r30
            r1 = r31
            r24 = r2
            r2 = r32
            r3 = r13
            r4 = r34
            r33 = r5
            r5 = r21
            r26 = r6
            r6 = r33
            r7 = r8
            r8 = r22
            r28 = r10
            r9 = r26
            r15 = r35
            r15 = r13
            r12 = r24
            r14 = r36
            int r0 = r0.zzv(r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14)
            if (r0 == r15) goto L_0x03c7
        L_0x03ad:
            r15 = r30
            r14 = r31
            r12 = r32
            r2 = r33
            r13 = r34
            r11 = r35
            r9 = r36
            r5 = r20
            r1 = r21
            r3 = r22
            r6 = r23
        L_0x03c3:
            r10 = r28
            goto L_0x001a
        L_0x03c7:
            r2 = r0
            r7 = r21
            r6 = r23
            r0 = r35
            goto L_0x0419
        L_0x03cf:
            r24 = r2
            r33 = r5
            r28 = r10
            r15 = r13
            r0 = 50
            if (r11 != r0) goto L_0x03fb
            r0 = 2
            if (r8 != r0) goto L_0x03f3
            r0 = r30
            r1 = r31
            r2 = r32
            r3 = r15
            r4 = r34
            r5 = r22
            r6 = r24
            r8 = r36
            int r0 = r0.zzs(r1, r2, r3, r4, r5, r6, r8)
            if (r0 == r15) goto L_0x03c7
            goto L_0x03ad
        L_0x03f3:
            r0 = r35
            r2 = r15
            r7 = r21
            r6 = r23
            goto L_0x0419
        L_0x03fb:
            r0 = r30
            r1 = r31
            r2 = r32
            r3 = r15
            r4 = r34
            r5 = r21
            r6 = r33
            r9 = r7
            r7 = r8
            r8 = r9
            r9 = r11
            r10 = r24
            r12 = r22
            r13 = r36
            int r0 = r0.zzt(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13)
            if (r0 == r15) goto L_0x03c7
            goto L_0x03ad
        L_0x0419:
            if (r7 != r0) goto L_0x042b
            if (r0 == 0) goto L_0x042b
            r8 = r30
            r12 = r31
            r9 = r0
            r0 = r2
            r1 = r7
            r5 = r20
        L_0x0426:
            r2 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x0494
        L_0x042b:
            r8 = r30
            r9 = r0
            boolean r0 = r8.zzh
            if (r0 == 0) goto L_0x0465
            r10 = r36
            com.google.android.gms.internal.measurement.zzjl r0 = r10.zzd
            com.google.android.gms.internal.measurement.zzjl r1 = com.google.android.gms.internal.measurement.zzjl.zza()
            if (r0 == r1) goto L_0x0460
            com.google.android.gms.internal.measurement.zzlg r0 = r8.zzg
            com.google.android.gms.internal.measurement.zzjl r1 = r10.zzd
            r11 = r33
            com.google.android.gms.internal.measurement.zzjx r0 = r1.zzc(r0, r11)
            if (r0 != 0) goto L_0x045a
            com.google.android.gms.internal.measurement.zzmj r4 = zzd(r31)
            r0 = r7
            r1 = r32
            r3 = r34
            r5 = r36
            int r0 = com.google.android.gms.internal.measurement.zzil.zzi(r0, r1, r2, r3, r4, r5)
            r12 = r31
            goto L_0x047a
        L_0x045a:
            r12 = r31
            r0 = r12
            com.google.android.gms.internal.measurement.zzjw r0 = (com.google.android.gms.internal.measurement.zzjw) r0
            throw r17
        L_0x0460:
            r12 = r31
            r11 = r33
            goto L_0x046b
        L_0x0465:
            r12 = r31
            r11 = r33
            r10 = r36
        L_0x046b:
            com.google.android.gms.internal.measurement.zzmj r4 = zzd(r31)
            r0 = r7
            r1 = r32
            r3 = r34
            r5 = r36
            int r0 = com.google.android.gms.internal.measurement.zzil.zzi(r0, r1, r2, r3, r4, r5)
        L_0x047a:
            r13 = r34
            r1 = r7
            r15 = r8
            r2 = r11
            r14 = r12
            r5 = r20
            r3 = r22
            r12 = r32
            r11 = r9
            r9 = r10
            goto L_0x03c3
        L_0x048a:
            r20 = r5
            r23 = r6
            r28 = r10
            r9 = r11
            r12 = r14
            r8 = r15
            goto L_0x0426
        L_0x0494:
            if (r6 == r2) goto L_0x049c
            long r3 = (long) r6
            r6 = r28
            r6.putInt(r12, r3, r5)
        L_0x049c:
            int r3 = r8.zzk
        L_0x049e:
            int r4 = r8.zzl
            if (r3 >= r4) goto L_0x04c9
            int[] r4 = r8.zzj
            r4 = r4[r3]
            int[] r5 = r8.zzc
            r5 = r5[r4]
            int r5 = r8.zzB(r4)
            r5 = r5 & r2
            long r5 = (long) r5
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzms.zzf(r12, r5)
            if (r5 != 0) goto L_0x04b7
            goto L_0x04bd
        L_0x04b7:
            com.google.android.gms.internal.measurement.zzkd r6 = r8.zzD(r4)
            if (r6 != 0) goto L_0x04c0
        L_0x04bd:
            int r3 = r3 + 1
            goto L_0x049e
        L_0x04c0:
            com.google.android.gms.internal.measurement.zzla r5 = (com.google.android.gms.internal.measurement.zzla) r5
            java.lang.Object r0 = r8.zzF(r4)
            com.google.android.gms.internal.measurement.zzkz r0 = (com.google.android.gms.internal.measurement.zzkz) r0
            throw r17
        L_0x04c9:
            if (r9 != 0) goto L_0x04d5
            r2 = r34
            if (r0 != r2) goto L_0x04d0
            goto L_0x04db
        L_0x04d0:
            com.google.android.gms.internal.measurement.zzkj r0 = com.google.android.gms.internal.measurement.zzkj.zze()
            throw r0
        L_0x04d5:
            r2 = r34
            if (r0 > r2) goto L_0x04dc
            if (r1 != r9) goto L_0x04dc
        L_0x04db:
            return r0
        L_0x04dc:
            com.google.android.gms.internal.measurement.zzkj r0 = com.google.android.gms.internal.measurement.zzkj.zze()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlj.zzc(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.measurement.zzik):int");
    }

    public Object zze() {
        return ((zzjz) this.zzg).zzl(4, null, null);
    }

    public void zzf(Object obj) {
        int i2;
        int i3 = this.zzk;
        while (true) {
            i2 = this.zzl;
            if (i3 >= i2) {
                break;
            }
            long zzB = zzB(this.zzj[i3]) & 1048575;
            Object zzf2 = zzms.zzf(obj, zzB);
            if (null != zzf2) {
                ((zzla) zzf2).zzc();
                zzms.zzs(obj, zzB, zzf2);
            }
            i3++;
        }
        int length = this.zzj.length;
        while (i2 < length) {
            this.zzm.zza(obj, this.zzj[i2]);
            i2++;
        }
        this.zzn.zzg(obj);
        if (this.zzh) {
            this.zzo.zzb(obj);
        }
    }

    public void zzh(Object obj, byte[] bArr, int i2, int i3, zzik zzik) throws IOException {
        if (this.zzi) {
            zzu(obj, bArr, i2, i3, zzik);
        } else {
            zzc(obj, bArr, i2, i3, 0, zzik);
        }
    }

    public boolean zzi(Object obj, Object obj2) {
        boolean z;
        int length = this.zzc.length;
        for (int i2 = 0; i2 < length; i2 += 3) {
            int zzB = zzB(i2);
            long j2 = zzB & 1048575;
            switch (zzA(zzB)) {
                case 0:
                    if (zzL(obj, obj2, i2) && Double.doubleToLongBits(zzms.zza(obj, j2)) == Double.doubleToLongBits(zzms.zza(obj2, j2))) {
                        continue;
                    }
                case 1:
                    if (zzL(obj, obj2, i2) && Float.floatToIntBits(zzms.zzb(obj, j2)) == Float.floatToIntBits(zzms.zzb(obj2, j2))) {
                        continue;
                    }
                case 2:
                    if (zzL(obj, obj2, i2) && zzms.zzd(obj, j2) == zzms.zzd(obj2, j2)) {
                        continue;
                    }
                case 3:
                    if (zzL(obj, obj2, i2) && zzms.zzd(obj, j2) == zzms.zzd(obj2, j2)) {
                        continue;
                    }
                case 4:
                    if (zzL(obj, obj2, i2) && zzms.zzc(obj, j2) == zzms.zzc(obj2, j2)) {
                        continue;
                    }
                case 5:
                    if (zzL(obj, obj2, i2) && zzms.zzd(obj, j2) == zzms.zzd(obj2, j2)) {
                        continue;
                    }
                case 6:
                    if (zzL(obj, obj2, i2) && zzms.zzc(obj, j2) == zzms.zzc(obj2, j2)) {
                        continue;
                    }
                case 7:
                    if (zzL(obj, obj2, i2) && zzms.zzw(obj, j2) == zzms.zzw(obj2, j2)) {
                        continue;
                    }
                case 8:
                    if (zzL(obj, obj2, i2) && zzlt.zzH(zzms.zzf(obj, j2), zzms.zzf(obj2, j2))) {
                        continue;
                    }
                case 9:
                    if (zzL(obj, obj2, i2) && zzlt.zzH(zzms.zzf(obj, j2), zzms.zzf(obj2, j2))) {
                        continue;
                    }
                case 10:
                    if (zzL(obj, obj2, i2) && zzlt.zzH(zzms.zzf(obj, j2), zzms.zzf(obj2, j2))) {
                        continue;
                    }
                case 11:
                    if (zzL(obj, obj2, i2) && zzms.zzc(obj, j2) == zzms.zzc(obj2, j2)) {
                        continue;
                    }
                case 12:
                    if (zzL(obj, obj2, i2) && zzms.zzc(obj, j2) == zzms.zzc(obj2, j2)) {
                        continue;
                    }
                case 13:
                    if (zzL(obj, obj2, i2) && zzms.zzc(obj, j2) == zzms.zzc(obj2, j2)) {
                        continue;
                    }
                case 14:
                    if (zzL(obj, obj2, i2) && zzms.zzd(obj, j2) == zzms.zzd(obj2, j2)) {
                        continue;
                    }
                case 15:
                    if (zzL(obj, obj2, i2) && zzms.zzc(obj, j2) == zzms.zzc(obj2, j2)) {
                        continue;
                    }
                case 16:
                    if (zzL(obj, obj2, i2) && zzms.zzd(obj, j2) == zzms.zzd(obj2, j2)) {
                        continue;
                    }
                case 17:
                    if (zzL(obj, obj2, i2) && zzlt.zzH(zzms.zzf(obj, j2), zzms.zzf(obj2, j2))) {
                        continue;
                    }
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    z = zzlt.zzH(zzms.zzf(obj, j2), zzms.zzf(obj2, j2));
                    break;
                case 50:
                    z = zzlt.zzH(zzms.zzf(obj, j2), zzms.zzf(obj2, j2));
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                    long zzy = zzy(i2) & 1048575;
                    if (zzms.zzc(obj, zzy) == zzms.zzc(obj2, zzy) && zzlt.zzH(zzms.zzf(obj, j2), zzms.zzf(obj2, j2))) {
                        continue;
                    }
            }
            if (!z) {
                return false;
            }
        }
        if (!this.zzn.zzc(obj).equals(this.zzn.zzc(obj2))) {
            return false;
        }
        if (!this.zzh) {
            return true;
        }
        this.zzo.zza(obj);
        this.zzo.zza(obj2);
        throw null;
    }

    public boolean zzj(Object obj) {
        int i2;
        int i3;
        Object obj2 = obj;
        int i4 = 1048575;
        int i5 = 0;
        int i6 = 0;
        while (i6 < this.zzk) {
            int i7 = this.zzj[i6];
            int i8 = this.zzc[i7];
            int zzB = zzB(i7);
            int i9 = this.zzc[i7 + 2];
            int i10 = i9 & 1048575;
            int i11 = 1 << (i9 >>> 20);
            if (i10 != i4) {
                if (1048575 != i10) {
                    i5 = zzb.getInt(obj2, i10);
                }
                i2 = i5;
                i3 = i10;
            } else {
                i3 = i4;
                i2 = i5;
            }
            if (0 != (268435456 & zzB) && !zzN(obj, i7, i3, i2, i11)) {
                return false;
            }
            int zzA = zzA(zzB);
            if (9 != zzA && 17 != zzA) {
                if (27 != zzA) {
                    if (60 == zzA || 68 == zzA) {
                        if (zzP(obj2, i8, i7) && !zzO(obj2, zzB, zzE(i7))) {
                            return false;
                        }
                    } else if (49 != zzA) {
                        if (50 == zzA && !((zzla) zzms.zzf(obj2, zzB & 1048575)).isEmpty()) {
                            zzkz zzkz = (zzkz) zzF(i7);
                            throw null;
                        }
                    }
                }
                List list = (List) zzms.zzf(obj2, zzB & 1048575);
                if (!list.isEmpty()) {
                    zzlr zzE = zzE(i7);
                    for (int i12 = 0; i12 < list.size(); i12++) {
                        if (!zzE.zzj(list.get(i12))) {
                            return false;
                        }
                    }
                    continue;
                } else {
                    continue;
                }
            } else if (zzN(obj, i7, i3, i2, i11) && !zzO(obj2, zzB, zzE(i7))) {
                return false;
            }
            i6++;
            i4 = i3;
            i5 = i2;
        }
        if (!this.zzh) {
            return true;
        }
        this.zzo.zza(obj2);
        throw null;
    }

    public void zzm(Object obj, zzjh zzjh) throws IOException {
        if (!this.zzi) {
            zzR(obj, zzjh);
        } else if (!this.zzh) {
            int length = this.zzc.length;
            for (int i2 = 0; i2 < length; i2 += 3) {
                int zzB = zzB(i2);
                int i3 = this.zzc[i2];
                switch (zzA(zzB)) {
                    case 0:
                        if (!zzM(obj, i2)) {
                            break;
                        } else {
                            zzjh.zzf(i3, zzms.zza(obj, (zzB & 1048575)));
                            break;
                        }
                    case 1:
                        if (!zzM(obj, i2)) {
                            break;
                        } else {
                            zzjh.zzo(i3, zzms.zzb(obj, (zzB & 1048575)));
                            break;
                        }
                    case 2:
                        if (!zzM(obj, i2)) {
                            break;
                        } else {
                            zzjh.zzt(i3, zzms.zzd(obj, (zzB & 1048575)));
                            break;
                        }
                    case 3:
                        if (!zzM(obj, i2)) {
                            break;
                        } else {
                            zzjh.zzJ(i3, zzms.zzd(obj, (zzB & 1048575)));
                            break;
                        }
                    case 4:
                        if (!zzM(obj, i2)) {
                            break;
                        } else {
                            zzjh.zzr(i3, zzms.zzc(obj, (zzB & 1048575)));
                            break;
                        }
                    case 5:
                        if (!zzM(obj, i2)) {
                            break;
                        } else {
                            zzjh.zzm(i3, zzms.zzd(obj, (zzB & 1048575)));
                            break;
                        }
                    case 6:
                        if (!zzM(obj, i2)) {
                            break;
                        } else {
                            zzjh.zzk(i3, zzms.zzc(obj, (zzB & 1048575)));
                            break;
                        }
                    case 7:
                        if (!zzM(obj, i2)) {
                            break;
                        } else {
                            zzjh.zzb(i3, zzms.zzw(obj, (zzB & 1048575)));
                            break;
                        }
                    case 8:
                        if (!zzM(obj, i2)) {
                            break;
                        } else {
                            zzT(i3, zzms.zzf(obj, (zzB & 1048575)), zzjh);
                            break;
                        }
                    case 9:
                        if (!zzM(obj, i2)) {
                            break;
                        } else {
                            zzjh.zzv(i3, zzms.zzf(obj, (zzB & 1048575)), zzE(i2));
                            break;
                        }
                    case 10:
                        if (!zzM(obj, i2)) {
                            break;
                        } else {
                            zzjh.zzd(i3, (zziy) zzms.zzf(obj, (zzB & 1048575)));
                            break;
                        }
                    case 11:
                        if (!zzM(obj, i2)) {
                            break;
                        } else {
                            zzjh.zzH(i3, zzms.zzc(obj, (zzB & 1048575)));
                            break;
                        }
                    case 12:
                        if (!zzM(obj, i2)) {
                            break;
                        } else {
                            zzjh.zzi(i3, zzms.zzc(obj, (zzB & 1048575)));
                            break;
                        }
                    case 13:
                        if (!zzM(obj, i2)) {
                            break;
                        } else {
                            zzjh.zzw(i3, zzms.zzc(obj, (zzB & 1048575)));
                            break;
                        }
                    case 14:
                        if (!zzM(obj, i2)) {
                            break;
                        } else {
                            zzjh.zzy(i3, zzms.zzd(obj, (zzB & 1048575)));
                            break;
                        }
                    case 15:
                        if (!zzM(obj, i2)) {
                            break;
                        } else {
                            zzjh.zzA(i3, zzms.zzc(obj, (zzB & 1048575)));
                            break;
                        }
                    case 16:
                        if (!zzM(obj, i2)) {
                            break;
                        } else {
                            zzjh.zzC(i3, zzms.zzd(obj, (zzB & 1048575)));
                            break;
                        }
                    case 17:
                        if (!zzM(obj, i2)) {
                            break;
                        } else {
                            zzjh.zzq(i3, zzms.zzf(obj, (zzB & 1048575)), zzE(i2));
                            break;
                        }
                    case 18:
                        zzlt.zzL(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh, false);
                        break;
                    case 19:
                        zzlt.zzP(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh, false);
                        break;
                    case 20:
                        zzlt.zzS(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh, false);
                        break;
                    case 21:
                        zzlt.zzaa(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh, false);
                        break;
                    case 22:
                        zzlt.zzR(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh, false);
                        break;
                    case 23:
                        zzlt.zzO(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh, false);
                        break;
                    case 24:
                        zzlt.zzN(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh, false);
                        break;
                    case 25:
                        zzlt.zzJ(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh, false);
                        break;
                    case 26:
                        zzlt.zzY(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh);
                        break;
                    case 27:
                        zzlt.zzT(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh, zzE(i2));
                        break;
                    case 28:
                        zzlt.zzK(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh);
                        break;
                    case 29:
                        zzlt.zzZ(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh, false);
                        break;
                    case 30:
                        zzlt.zzM(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh, false);
                        break;
                    case 31:
                        zzlt.zzU(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh, false);
                        break;
                    case 32:
                        zzlt.zzV(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh, false);
                        break;
                    case 33:
                        zzlt.zzW(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh, false);
                        break;
                    case 34:
                        zzlt.zzX(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh, false);
                        break;
                    case 35:
                        zzlt.zzL(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh, true);
                        break;
                    case 36:
                        zzlt.zzP(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh, true);
                        break;
                    case 37:
                        zzlt.zzS(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh, true);
                        break;
                    case 38:
                        zzlt.zzaa(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh, true);
                        break;
                    case 39:
                        zzlt.zzR(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh, true);
                        break;
                    case 40:
                        zzlt.zzO(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh, true);
                        break;
                    case 41:
                        zzlt.zzN(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh, true);
                        break;
                    case 42:
                        zzlt.zzJ(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh, true);
                        break;
                    case 43:
                        zzlt.zzZ(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh, true);
                        break;
                    case 44:
                        zzlt.zzM(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh, true);
                        break;
                    case 45:
                        zzlt.zzU(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh, true);
                        break;
                    case 46:
                        zzlt.zzV(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh, true);
                        break;
                    case 47:
                        zzlt.zzW(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh, true);
                        break;
                    case 48:
                        zzlt.zzX(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh, true);
                        break;
                    case 49:
                        zzlt.zzQ(i3, (List) zzms.zzf(obj, (zzB & 1048575)), zzjh, zzE(i2));
                        break;
                    case 50:
                        zzS(zzjh, i3, zzms.zzf(obj, (zzB & 1048575)), i2);
                        break;
                    case 51:
                        if (!zzP(obj, i3, i2)) {
                            break;
                        } else {
                            zzjh.zzf(i3, zzn(obj, (zzB & 1048575)));
                            break;
                        }
                    case 52:
                        if (!zzP(obj, i3, i2)) {
                            break;
                        } else {
                            zzjh.zzo(i3, zzo(obj, (zzB & 1048575)));
                            break;
                        }
                    case 53:
                        if (!zzP(obj, i3, i2)) {
                            break;
                        } else {
                            zzjh.zzt(i3, zzC(obj, (zzB & 1048575)));
                            break;
                        }
                    case 54:
                        if (!zzP(obj, i3, i2)) {
                            break;
                        } else {
                            zzjh.zzJ(i3, zzC(obj, (zzB & 1048575)));
                            break;
                        }
                    case 55:
                        if (!zzP(obj, i3, i2)) {
                            break;
                        } else {
                            zzjh.zzr(i3, zzr(obj, (zzB & 1048575)));
                            break;
                        }
                    case 56:
                        if (!zzP(obj, i3, i2)) {
                            break;
                        } else {
                            zzjh.zzm(i3, zzC(obj, (zzB & 1048575)));
                            break;
                        }
                    case 57:
                        if (!zzP(obj, i3, i2)) {
                            break;
                        } else {
                            zzjh.zzk(i3, zzr(obj, (zzB & 1048575)));
                            break;
                        }
                    case 58:
                        if (!zzP(obj, i3, i2)) {
                            break;
                        } else {
                            zzjh.zzb(i3, zzQ(obj, (zzB & 1048575)));
                            break;
                        }
                    case 59:
                        if (!zzP(obj, i3, i2)) {
                            break;
                        } else {
                            zzT(i3, zzms.zzf(obj, (zzB & 1048575)), zzjh);
                            break;
                        }
                    case 60:
                        if (!zzP(obj, i3, i2)) {
                            break;
                        } else {
                            zzjh.zzv(i3, zzms.zzf(obj, (zzB & 1048575)), zzE(i2));
                            break;
                        }
                    case 61:
                        if (!zzP(obj, i3, i2)) {
                            break;
                        } else {
                            zzjh.zzd(i3, (zziy) zzms.zzf(obj, (zzB & 1048575)));
                            break;
                        }
                    case 62:
                        if (!zzP(obj, i3, i2)) {
                            break;
                        } else {
                            zzjh.zzH(i3, zzr(obj, (zzB & 1048575)));
                            break;
                        }
                    case 63:
                        if (!zzP(obj, i3, i2)) {
                            break;
                        } else {
                            zzjh.zzi(i3, zzr(obj, (zzB & 1048575)));
                            break;
                        }
                    case 64:
                        if (!zzP(obj, i3, i2)) {
                            break;
                        } else {
                            zzjh.zzw(i3, zzr(obj, (zzB & 1048575)));
                            break;
                        }
                    case 65:
                        if (!zzP(obj, i3, i2)) {
                            break;
                        } else {
                            zzjh.zzy(i3, zzC(obj, (zzB & 1048575)));
                            break;
                        }
                    case 66:
                        if (!zzP(obj, i3, i2)) {
                            break;
                        } else {
                            zzjh.zzA(i3, zzr(obj, (zzB & 1048575)));
                            break;
                        }
                    case 67:
                        if (!zzP(obj, i3, i2)) {
                            break;
                        } else {
                            zzjh.zzC(i3, zzC(obj, (zzB & 1048575)));
                            break;
                        }
                    case 68:
                        if (!zzP(obj, i3, i2)) {
                            break;
                        } else {
                            zzjh.zzq(i3, zzms.zzf(obj, (zzB & 1048575)), zzE(i2));
                            break;
                        }
                }
            }
            zzmi zzmi = this.zzn;
            zzmi.zzi(zzmi.zzc(obj), zzjh);
        } else {
            this.zzo.zza(obj);
            throw null;
        }
    }

    public void zzg(Object obj, Object obj2) {
        obj2.getClass();
        for (int i2 = 0; i2 < this.zzc.length; i2 += 3) {
            int zzB = zzB(i2);
            long j2 = 1048575 & zzB;
            int i3 = this.zzc[i2];
            switch (zzA(zzB)) {
                case 0:
                    if (!zzM(obj2, i2)) {
                        break;
                    } else {
                        zzms.zzo(obj, j2, zzms.zza(obj2, j2));
                        zzJ(obj, i2);
                        break;
                    }
                case 1:
                    if (!zzM(obj2, i2)) {
                        break;
                    } else {
                        zzms.zzp(obj, j2, zzms.zzb(obj2, j2));
                        zzJ(obj, i2);
                        break;
                    }
                case 2:
                    if (!zzM(obj2, i2)) {
                        break;
                    } else {
                        zzms.zzr(obj, j2, zzms.zzd(obj2, j2));
                        zzJ(obj, i2);
                        break;
                    }
                case 3:
                    if (!zzM(obj2, i2)) {
                        break;
                    } else {
                        zzms.zzr(obj, j2, zzms.zzd(obj2, j2));
                        zzJ(obj, i2);
                        break;
                    }
                case 4:
                    if (!zzM(obj2, i2)) {
                        break;
                    } else {
                        zzms.zzq(obj, j2, zzms.zzc(obj2, j2));
                        zzJ(obj, i2);
                        break;
                    }
                case 5:
                    if (!zzM(obj2, i2)) {
                        break;
                    } else {
                        zzms.zzr(obj, j2, zzms.zzd(obj2, j2));
                        zzJ(obj, i2);
                        break;
                    }
                case 6:
                    if (!zzM(obj2, i2)) {
                        break;
                    } else {
                        zzms.zzq(obj, j2, zzms.zzc(obj2, j2));
                        zzJ(obj, i2);
                        break;
                    }
                case 7:
                    if (!zzM(obj2, i2)) {
                        break;
                    } else {
                        zzms.zzm(obj, j2, zzms.zzw(obj2, j2));
                        zzJ(obj, i2);
                        break;
                    }
                case 8:
                    if (!zzM(obj2, i2)) {
                        break;
                    } else {
                        zzms.zzs(obj, j2, zzms.zzf(obj2, j2));
                        zzJ(obj, i2);
                        break;
                    }
                case 9:
                    zzH(obj, obj2, i2);
                    break;
                case 10:
                    if (!zzM(obj2, i2)) {
                        break;
                    } else {
                        zzms.zzs(obj, j2, zzms.zzf(obj2, j2));
                        zzJ(obj, i2);
                        break;
                    }
                case 11:
                    if (!zzM(obj2, i2)) {
                        break;
                    } else {
                        zzms.zzq(obj, j2, zzms.zzc(obj2, j2));
                        zzJ(obj, i2);
                        break;
                    }
                case 12:
                    if (!zzM(obj2, i2)) {
                        break;
                    } else {
                        zzms.zzq(obj, j2, zzms.zzc(obj2, j2));
                        zzJ(obj, i2);
                        break;
                    }
                case 13:
                    if (!zzM(obj2, i2)) {
                        break;
                    } else {
                        zzms.zzq(obj, j2, zzms.zzc(obj2, j2));
                        zzJ(obj, i2);
                        break;
                    }
                case 14:
                    if (!zzM(obj2, i2)) {
                        break;
                    } else {
                        zzms.zzr(obj, j2, zzms.zzd(obj2, j2));
                        zzJ(obj, i2);
                        break;
                    }
                case 15:
                    if (!zzM(obj2, i2)) {
                        break;
                    } else {
                        zzms.zzq(obj, j2, zzms.zzc(obj2, j2));
                        zzJ(obj, i2);
                        break;
                    }
                case 16:
                    if (!zzM(obj2, i2)) {
                        break;
                    } else {
                        zzms.zzr(obj, j2, zzms.zzd(obj2, j2));
                        zzJ(obj, i2);
                        break;
                    }
                case 17:
                    zzH(obj, obj2, i2);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.zzm.zzb(obj, obj2, j2);
                    break;
                case 50:
                    zzlt.zzI(this.zzq, obj, obj2, j2);
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (!zzP(obj2, i3, i2)) {
                        break;
                    } else {
                        zzms.zzs(obj, j2, zzms.zzf(obj2, j2));
                        zzK(obj, i3, i2);
                        break;
                    }
                case 60:
                    zzI(obj, obj2, i2);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (!zzP(obj2, i3, i2)) {
                        break;
                    } else {
                        zzms.zzs(obj, j2, zzms.zzf(obj2, j2));
                        zzK(obj, i3, i2);
                        break;
                    }
                case 68:
                    zzI(obj, obj2, i2);
                    break;
            }
        }
        zzlt.zzF(this.zzn, obj, obj2);
        if (this.zzh) {
            zzlt.zzE(this.zzo, obj, obj2);
        }
    }
}
