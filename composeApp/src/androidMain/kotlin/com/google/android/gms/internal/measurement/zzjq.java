package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzjq {
    private static final zzjq zzb = new zzjq(true);
    final zzme zza = new zzlu(16);
    private boolean zzc;
    private boolean zzd;

    private zzjq() {
    }

    public static zzjq zza() {
        throw null;
    }

    private static void zzd(zzjp zzjp, Object obj) {
        boolean z;
        zzmy zzb2 = zzjp.zzb();
        zzkh.zze(obj);
        final zzmy zzmy = com.google.android.gms.internal.measurement.zzmy.DOUBLE;
        final zzmz zzmz = com.google.android.gms.internal.measurement.zzmz.INT;
        switch (zzb2.zza().ordinal()) {
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
                if ((obj instanceof zziy) || (obj instanceof byte[])) {
                    return;
                }
            case 7:
                if ((obj instanceof Integer) || (obj instanceof zzkb)) {
                    return;
                }
            case 8:
                if ((obj instanceof zzlg) || (obj instanceof zzkl)) {
                    return;
                }
        }
        if (z) {
            return;
        }
        throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzjp.zza()), zzjp.zzb().zza(), obj.getClass().getName()));
    }

    public Object clone() throws CloneNotSupportedException {
        zzjq zzjq = new zzjq();
        for (int i2 = 0; i2 < this.zza.zzb(); i2++) {
            Map.Entry zzg = this.zza.zzg(i2);
            zzjq.zzc((zzjp) zzg.getKey(), zzg.getValue());
        }
        for (Map.Entry entry : this.zza.zzc()) {
            zzjq.zzc((zzjp) entry.getKey(), entry.getValue());
        }
        zzjq.zzd = this.zzd;
        return zzjq;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzjq)) {
            return false;
        }
        return this.zza.equals(((zzjq) obj).zza);
    }

    public int hashCode() {
        return this.zza.hashCode();
    }

    public void zzb() {
        if (!this.zzc) {
            this.zza.zza();
            this.zzc = true;
        }
    }

    public void zzc(zzjp zzjp, Object obj) {
        if (!zzjp.zzc()) {
            zzd(zzjp, obj);
        } else if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                zzd(zzjp, arrayList.get(i2));
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof zzkl) {
            this.zzd = true;
        }
        this.zza.put(zzjp, obj);
    }

    private zzjq(boolean z) {
        zzb();
        zzb();
    }
}
