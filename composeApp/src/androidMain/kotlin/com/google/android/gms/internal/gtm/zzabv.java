package com.google.android.gms.internal.gtm;

import java.util.*;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzabv {
    private static final zzabv zzb = new zzabv(true);
    final zzaef zza = new zzaea();
    private boolean zzc;
    private boolean zzd;

    private zzabv() {
    }

    static int zza(final com.google.android.gms.internal.gtm.zzaex r4, final int r5, final java.lang.Object r6) {
        /*
            int r5 = r5 << 3
            int r5 = com.google.android.gms.internal.gtm.zzzi.zzC(r5)
            com.google.android.gms.internal.gtm.zzaex r0 = com.google.android.gms.internal.gtm.zzaex.GROUP
            if (r4 != r0) goto L_0x0017
            r0 = r6
            com.google.android.gms.internal.gtm.zzadl r0 = (com.google.android.gms.internal.gtm.zzadl) r0
            byte[] r1 = com.google.android.gms.internal.gtm.zzaco.zzb
            boolean r0 = r0 instanceof com.google.android.gms.internal.gtm.zzyi
            if (r0 != 0) goto L_0x0015
            int r5 = r5 + r5
            goto L_0x0017
        L_0x0015:
            r4 = 0
            throw r4
        L_0x0017:
            com.google.android.gms.internal.gtm.zzaey r0 = com.google.android.gms.internal.gtm.zzaey.INT
            int r4 = r4.ordinal()
            r0 = 4
            r1 = 8
            switch(r4) {
                case 0: goto L_0x0110;
                case 1: goto L_0x010a;
                case 2: goto L_0x00ff;
                case 3: goto L_0x00f4;
                case 4: goto L_0x00e8;
                case 5: goto L_0x00e1;
                case 6: goto L_0x00db;
                case 7: goto L_0x00d4;
                case 8: goto L_0x00be;
                case 9: goto L_0x00b7;
                case 10: goto L_0x00a1;
                case 11: goto L_0x0087;
                case 12: goto L_0x007b;
                case 13: goto L_0x005d;
                case 14: goto L_0x0056;
                case 15: goto L_0x004e;
                case 16: goto L_0x003d;
                case 17: goto L_0x002b;
                default: goto L_0x0023;
            }
        L_0x0023:
            java.lang.RuntimeException r4 = new java.lang.RuntimeException
            java.lang.String r5 = "There is no way to get here, but the compiler thinks otherwise."
            r4.<init>(r5)
            throw r4
        L_0x002b:
            java.lang.Long r6 = (java.lang.Long) r6
            long r0 = r6.longValue()
            long r2 = r0 + r0
            r4 = 63
            long r0 = r0 >> r4
            long r0 = r0 ^ r2
            int r0 = com.google.android.gms.internal.gtm.zzzi.zzD(r0)
            goto L_0x0117
        L_0x003d:
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r4 = r6.intValue()
            int r6 = r4 + r4
            int r4 = r4 >> 31
            r4 = r4 ^ r6
            int r0 = com.google.android.gms.internal.gtm.zzzi.zzC(r4)
            goto L_0x0117
        L_0x004e:
            java.lang.Long r6 = (java.lang.Long) r6
            r6.longValue()
        L_0x0053:
            r0 = r1
            goto L_0x0117
        L_0x0056:
            java.lang.Integer r6 = (java.lang.Integer) r6
            r6.intValue()
            goto L_0x0117
        L_0x005d:
            boolean r4 = r6 instanceof com.google.android.gms.internal.gtm.zzach
            if (r4 == 0) goto L_0x006e
            com.google.android.gms.internal.gtm.zzach r6 = (com.google.android.gms.internal.gtm.zzach) r6
            int r4 = r6.zza()
            long r0 = (long) r4
            int r0 = com.google.android.gms.internal.gtm.zzzi.zzD(r0)
            goto L_0x0117
        L_0x006e:
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r4 = r6.intValue()
            long r0 = (long) r4
            int r0 = com.google.android.gms.internal.gtm.zzzi.zzD(r0)
            goto L_0x0117
        L_0x007b:
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r4 = r6.intValue()
            int r0 = com.google.android.gms.internal.gtm.zzzi.zzC(r4)
            goto L_0x0117
        L_0x0087:
            boolean r4 = r6 instanceof com.google.android.gms.internal.gtm.zzyx
            if (r4 == 0) goto L_0x0099
            com.google.android.gms.internal.gtm.zzyx r6 = (com.google.android.gms.internal.gtm.zzyx) r6
            int r4 = r6.zzd()
            int r6 = com.google.android.gms.internal.gtm.zzzi.zzC(r4)
        L_0x0095:
            int r0 = r6 + r4
            goto L_0x0117
        L_0x0099:
            byte[] r6 = (byte[]) r6
            int r4 = r6.length
            int r6 = com.google.android.gms.internal.gtm.zzzi.zzC(r4)
            goto L_0x0095
        L_0x00a1:
            boolean r4 = r6 instanceof com.google.android.gms.internal.gtm.zzacv
            if (r4 == 0) goto L_0x00b0
            com.google.android.gms.internal.gtm.zzacv r6 = (com.google.android.gms.internal.gtm.zzacv) r6
            int r4 = r6.zza()
            int r6 = com.google.android.gms.internal.gtm.zzzi.zzC(r4)
            goto L_0x0095
        L_0x00b0:
            com.google.android.gms.internal.gtm.zzadl r6 = (com.google.android.gms.internal.gtm.zzadl) r6
            int r0 = com.google.android.gms.internal.gtm.zzzi.zzz(r6)
            goto L_0x0117
        L_0x00b7:
            com.google.android.gms.internal.gtm.zzadl r6 = (com.google.android.gms.internal.gtm.zzadl) r6
            int r0 = r6.zzY()
            goto L_0x0117
        L_0x00be:
            boolean r4 = r6 instanceof com.google.android.gms.internal.gtm.zzyx
            if (r4 == 0) goto L_0x00cd
            com.google.android.gms.internal.gtm.zzyx r6 = (com.google.android.gms.internal.gtm.zzyx) r6
            int r4 = r6.zzd()
            int r6 = com.google.android.gms.internal.gtm.zzzi.zzC(r4)
            goto L_0x0095
        L_0x00cd:
            java.lang.String r6 = (java.lang.String) r6
            int r0 = com.google.android.gms.internal.gtm.zzzi.zzB(r6)
            goto L_0x0117
        L_0x00d4:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            r6.booleanValue()
            r0 = 1
            goto L_0x0117
        L_0x00db:
            java.lang.Integer r6 = (java.lang.Integer) r6
            r6.intValue()
            goto L_0x0117
        L_0x00e1:
            java.lang.Long r6 = (java.lang.Long) r6
            r6.longValue()
            goto L_0x0053
        L_0x00e8:
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r4 = r6.intValue()
            long r0 = (long) r4
            int r0 = com.google.android.gms.internal.gtm.zzzi.zzD(r0)
            goto L_0x0117
        L_0x00f4:
            java.lang.Long r6 = (java.lang.Long) r6
            long r0 = r6.longValue()
            int r0 = com.google.android.gms.internal.gtm.zzzi.zzD(r0)
            goto L_0x0117
        L_0x00ff:
            java.lang.Long r6 = (java.lang.Long) r6
            long r0 = r6.longValue()
            int r0 = com.google.android.gms.internal.gtm.zzzi.zzD(r0)
            goto L_0x0117
        L_0x010a:
            java.lang.Float r6 = (java.lang.Float) r6
            r6.floatValue()
            goto L_0x0117
        L_0x0110:
            java.lang.Double r6 = (java.lang.Double) r6
            r6.doubleValue()
            goto L_0x0053
        L_0x0117:
            int r5 = r5 + r0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzabv.zza(com.google.android.gms.internal.gtm.zzaex, int, java.lang.Object):int");
    }

    public static int zzb(final zzabu zzabu, final Object obj) {
        final zzaex zzd2 = zzabu.zzd();
        final int zza2 = zzabu.zza();
        if (!zzabu.zzg()) {
            return zzabv.zza(zzd2, zza2, obj);
        }
        final List list = (List) obj;
        final int size = list.size();
        zzabu.zzf();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzabv.zza(zzd2, zza2, list.get(i3));
        }
        return i2;
    }

    public static zzabv zze() {
        return zzabv.zzb;
    }

    private static Object zzn(final Object obj) {
        if (obj instanceof zzadq) {
            return ((zzadq) obj).zzc();
        }
        if (!(obj instanceof byte[] bArr)) {
            return obj;
        }
        final int length = bArr.length;
        final byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        return bArr2;
    }

    private void zzo(final Map.Entry entry) {
        final Object obj;
        final zzabu zzabu = (zzabu) entry.getKey();
        final Object value = entry.getValue();
        final boolean z = value instanceof zzacv;
        if (zzabu.zzg()) {
            if (!z) {
                Object zzf = this.zzf(zzabu);
                final List list = (List) value;
                final int size = list.size();
                if (null == zzf) {
                    zzf = new ArrayList(size);
                }
                final List list2 = (List) zzf;
                for (int i2 = 0; i2 < size; i2++) {
                    list2.add(zzabv.zzn(list.get(i2)));
                }
                zza.put(zzabu, zzf);
                return;
            }
            throw new IllegalStateException("Lazy fields can not be repeated");
        } else if (zzaey.MESSAGE == zzabu.zze()) {
            final Object zzf2 = this.zzf(zzabu);
            if (null == zzf2) {
                zza.put(zzabu, zzabv.zzn(value));
                if (z) {
                    zzd = true;
                }
            } else if (!z) {
                if (zzf2 instanceof zzadq) {
                    obj = zzabu.zzc((zzadq) zzf2, (zzadq) value);
                } else {
                    obj = zzabu.zzb(((zzadl) zzf2).zzaw(), (zzadl) value).zzD();
                }
                zza.put(zzabu, obj);
            } else {
                throw null;
            }
        } else if (!z) {
            zza.put(zzabu, zzabv.zzn(value));
        } else {
            throw new IllegalStateException("Lazy fields must be message-valued");
        }
    }

    private static boolean zzp(final Map.Entry entry) {
        final zzabu zzabu = (zzabu) entry.getKey();
        if (zzaey.MESSAGE != zzabu.zze()) {
            return true;
        }
        if (!zzabu.zzg()) {
            return zzabv.zzq(entry.getValue());
        }
        final List list = (List) entry.getValue();
        final int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!zzabv.zzq(list.get(i2))) {
                return false;
            }
        }
        return true;
    }

    private static boolean zzq(final Object obj) {
        if (obj instanceof zzadm) {
            return ((zzadm) obj).zzaz();
        }
        if (obj instanceof zzacv) {
            return true;
        }
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
    }

    private static int zzr(final Map.Entry entry) {
        final int i2;
        final int zzC;
        final int zzC2;
        final zzabu zzabu = (zzabu) entry.getKey();
        final Object value = entry.getValue();
        if (zzaey.MESSAGE != zzabu.zze() || zzabu.zzg()) {
            return zzabv.zzb(zzabu, value);
        }
        zzabu.zzf();
        if (value instanceof zzacv) {
            final int zza2 = ((zzabu) entry.getKey()).zza();
            final int zzC3 = zzzi.zzC(8);
            i2 = zzC3 + zzC3;
            zzC = zzzi.zzC(16) + zzzi.zzC(zza2);
            final int zzC4 = zzzi.zzC(24);
            final int zza3 = ((zzacv) value).zza();
            zzC2 = zzC4 + zzzi.zzC(zza3) + zza3;
        } else {
            final int zza4 = ((zzabu) entry.getKey()).zza();
            final int zzC5 = zzzi.zzC(8);
            i2 = zzC5 + zzC5;
            zzC = zzzi.zzC(16) + zzzi.zzC(zza4);
            zzC2 = zzzi.zzC(24) + zzzi.zzz((zzadl) value);
        }
        return i2 + zzC + zzC2;
    }

    private static void zzs(final zzabu zzabu, final Object obj) {
        boolean z;
        final zzaex zzd2 = zzabu.zzd();
        final byte[] bArr = zzaco.zzb;
        obj.getClass();
        zzaex zzaex = com.google.android.gms.internal.gtm.zzaex.DOUBLE;
        zzaey zzaey = com.google.android.gms.internal.gtm.zzaey.INT;
        switch (zzd2.zza().ordinal()) {
            case 0:
                z = obj instanceof Integer;
                break;
            case 1:
                z = obj instanceof Long;
                break;
            case 2:
                z = obj instanceof Float;
                break;
            case 3:
                z = obj instanceof Double;
                break;
            case 4:
                z = obj instanceof Boolean;
                break;
            case 5:
                z = obj instanceof String;
                break;
            case 6:
                if ((obj instanceof zzyx) || (obj instanceof byte[])) {
                    return;
                }
            case 7:
                if ((obj instanceof Integer) || (obj instanceof zzach)) {
                    return;
                }
            case 8:
                if ((obj instanceof zzadl) || (obj instanceof zzacv)) {
                    return;
                }
        }
        if (z) {
            return;
        }
        throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzabu.zza()), zzabu.zzd().zza(), obj.getClass().getName()));
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzabv)) {
            return false;
        }
        return zza.equals(((zzabv) obj).zza);
    }

    public int hashCode() {
        return zza.hashCode();
    }

    public int zzc() {
        final int zzc2 = zza.zzc();
        int i2 = 0;
        for (int i3 = 0; i3 < zzc2; i3++) {
            i2 += zzabv.zzr(zza.zzg(i3));
        }
        for (final Map.Entry zzr : zza.zzd()) {
            i2 += zzabv.zzr(zzr);
        }
        return i2;
    }

    /* renamed from: zzd */
    public zzabv clone() {
        final zzabv zzabv = new zzabv();
        final int zzc2 = zza.zzc();
        for (int i2 = 0; i2 < zzc2; i2++) {
            final Map.Entry zzg = zza.zzg(i2);
            zzabv.zzk((zzabu) ((zzaeb) zzg).zza(), zzg.getValue());
        }
        for (final Map.Entry entry : zza.zzd()) {
            zzabv.zzk((zzabu) entry.getKey(), entry.getValue());
        }
        zzabv.zzd = zzd;
        return zzabv;
    }

    public Object zzf(final zzabu zzabu) {
        final Object obj = zza.get(zzabu);
        if (!(obj instanceof zzacv)) {
            return obj;
        }
        throw null;
    }

    public Iterator zzg() {
        if (zza.isEmpty()) {
            return Collections.emptyIterator();
        }
        if (zzd) {
            return new zzact(zza.entrySet().iterator());
        }
        return zza.entrySet().iterator();
    }

    public void zzh(final zzabu zzabu, final Object obj) {
        final List list;
        if (((zzacd) zzabu).zzd) {
            zzabv.zzs(zzabu, obj);
            final Object zzf = this.zzf(zzabu);
            if (null == zzf) {
                list = new ArrayList();
                zza.put(zzabu, list);
            } else {
                list = (List) zzf;
            }
            list.add(obj);
            return;
        }
        throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields.");
    }

    public void zzi() {
        if (!zzc) {
            final int zzc2 = zza.zzc();
            for (int i2 = 0; i2 < zzc2; i2++) {
                final Object value = zza.zzg(i2).getValue();
                if (value instanceof zzacf) {
                    ((zzacf) value).zzam();
                }
            }
            for (final Map.Entry value2 : zza.zzd()) {
                final Object value3 = value2.getValue();
                if (value3 instanceof zzacf) {
                    ((zzacf) value3).zzam();
                }
            }
            zza.zza();
            zzc = true;
        }
    }

    public void zzj(final zzabv zzabv) {
        final int zzc2 = zzabv.zza.zzc();
        for (int i2 = 0; i2 < zzc2; i2++) {
            this.zzo(zzabv.zza.zzg(i2));
        }
        for (final Map.Entry zzo : zzabv.zza.zzd()) {
            this.zzo(zzo);
        }
    }

    public void zzk(final zzabu zzabu, Object obj) {
        if (!zzabu.zzg()) {
            zzabv.zzs(zzabu, obj);
        } else if (obj instanceof List list) {
            final int size = list.size();
            final ArrayList arrayList = new ArrayList(size);
            for (int i2 = 0; i2 < size; i2++) {
                final Object obj2 = list.get(i2);
                zzabv.zzs(zzabu, obj2);
                arrayList.add(obj2);
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof zzacv) {
            zzd = true;
        }
        zza.put(zzabu, obj);
    }

    public boolean zzl() {
        return zzc;
    }

    public boolean zzm() {
        final int zzc2 = zza.zzc();
        for (int i2 = 0; i2 < zzc2; i2++) {
            if (!zzabv.zzp(zza.zzg(i2))) {
                return false;
            }
        }
        for (final Map.Entry zzp : zza.zzd()) {
            if (!zzabv.zzp(zzp)) {
                return false;
            }
        }
        return true;
    }

    private zzabv(final boolean z) {
        this.zzi();
        this.zzi();
    }
}
