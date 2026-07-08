package com.google.android.gms.internal.measurement;

import java.util.*;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzae implements Iterable, zzap, zzal {
    final SortedMap zza;
    final Map zzb;

    public zzae() {
        this.zza = new TreeMap();
        this.zzb = new TreeMap();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof final zzae zzae)) {
            return false;
        }
        if (zzc() != zzae.zzc()) {
            return false;
        }
        if (this.zza.isEmpty()) {
            return zzae.zza.isEmpty();
        }
        for (int intValue = ((Integer) this.zza.firstKey()).intValue(); intValue <= ((Integer) this.zza.lastKey()).intValue(); intValue++) {
            if (!zze(intValue).equals(zzae.zze(intValue))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return this.zza.hashCode() * 31;
    }

    public Iterator iterator() {
        return new zzad(this);
    }

    public String toString() {
        return zzj(",");
    }

    public int zzb() {
        return this.zza.size();
    }

    public zzap zzbI(String str, zzg zzg, List list) {
        if ("concat".equals(str) || "every".equals(str) || "filter".equals(str) || "forEach".equals(str) || "indexOf".equals(str) || "join".equals(str) || "lastIndexOf".equals(str) || "map".equals(str) || "pop".equals(str) || "push".equals(str) || "reduce".equals(str) || "reduceRight".equals(str) || "reverse".equals(str) || "shift".equals(str) || "slice".equals(str) || "some".equals(str) || "sort".equals(str) || "splice".equals(str) || "toString".equals(str) || "unshift".equals(str)) {
            return zzbb.zza(str, this, zzg, list);
        }
        return zzaj.zza(this, new zzat(str), zzg, list);
    }

    public int zzc() {
        if (this.zza.isEmpty()) {
            return 0;
        }
        return ((Integer) this.zza.lastKey()).intValue() + 1;
    }

    public zzap zzd() {
        zzae zzae = new zzae();
        for (Map.Entry entry : this.zza.entrySet()) {
            if (entry.getValue() instanceof zzal) {
                zzae.zza.put(entry.getKey(), entry.getValue());
            } else {
                zzae.zza.put(entry.getKey(), ((zzap) entry.getValue()).zzd());
            }
        }
        return zzae;
    }

    public com.google.android.gms.internal.measurement.zzap zze(int r2) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzae.zzf(java.lang.String):com.google.android.gms.internal.measurement.zzap");
    }

    public Boolean zzg() {
        return Boolean.TRUE;
    }

    public Double zzh(String zzd, int i, List list) {
        if (1 == zza.size()) {
            return zze(0).zzh(this.zzd, 3, list);
        }
        if (0 >= zza.size()) {
            return Double.valueOf(0.0d);
        }
        return Double.valueOf(Double.NaN);
    }

    public String zzi() {
        return zzj(",");
    }

    public String zzj(String str) {
        if (null == str) {
            str = "";
        }
        StringBuilder sb = new StringBuilder();
        if (!this.zza.isEmpty()) {
            for (int i2 = 0; i2 < zzc(); i2++) {
                zzap zze = zze(i2);
                sb.append(str);
                if (!(zze instanceof zzau) && !(zze instanceof zzan)) {
                    sb.append(zze.zzi());
                }
            }
            sb.delete(0, str.length());
        }
        return sb.toString();
    }

    public Iterator zzk() {
        return this.zza.keySet().iterator();
    }

    public Iterator zzl() {
        return new zzac(this, this.zza.keySet().iterator(), this.zzb.keySet().iterator());
    }

    public List zzm() {
        ArrayList arrayList = new ArrayList(zzc());
        for (int i2 = 0; i2 < zzc(); i2++) {
            arrayList.add(zze(i2));
        }
        return arrayList;
    }

    public void zzn() {
        this.zza.clear();
    }

    public void zzp(int i2) {
        int intValue = ((Integer) this.zza.lastKey()).intValue();
        if (i2 <= intValue && 0 <= i2) {
            this.zza.remove(Integer.valueOf(i2));
            if (i2 == intValue) {
                SortedMap sortedMap = this.zza;
                int i3 = i2 - 1;
                Integer valueOf = Integer.valueOf(i3);
                if (!sortedMap.containsKey(valueOf) && 0 <= i3) {
                    this.zza.put(valueOf, zzap.zzf);
                    return;
                }
                return;
            }
            while (true) {
                i2++;
                if (i2 <= ((Integer) this.zza.lastKey()).intValue()) {
                    SortedMap sortedMap2 = this.zza;
                    Integer valueOf2 = Integer.valueOf(i2);
                    zzap zzap = (zzap) sortedMap2.get(valueOf2);
                    if (null != zzap) {
                        this.zza.put(Integer.valueOf(i2 - 1), zzap);
                        this.zza.remove(valueOf2);
                    }
                } else {
                    return;
                }
            }
        }
    }

    public void zzr(String str, zzap zzap) {
        if (null == zzap) {
            this.zzb.remove(str);
        } else {
            this.zzb.put(str, zzap);
        }
    }

    public boolean zzs(int i2) {
        if (0 <= i2 && i2 <= ((Integer) this.zza.lastKey()).intValue()) {
            return this.zza.containsKey(Integer.valueOf(i2));
        }
        final String sb = "Out of bounds index: " +
                i2;
        throw new IndexOutOfBoundsException(sb);
    }

    public boolean zzt(String str) {
        return Name.LENGTH.equals(str) || this.zzb.containsKey(str);
    }

    public void zzo(int i2, zzap zzap) {
        if (0 > i2) {
            final String sb = "Invalid value index: " +
                    i2;
            throw new IllegalArgumentException(sb);
        } else if (i2 >= zzc()) {
            zzq(i2, zzap);
        } else {
            for (int intValue = ((Integer) this.zza.lastKey()).intValue(); intValue >= i2; intValue--) {
                SortedMap sortedMap = this.zza;
                Integer valueOf = Integer.valueOf(intValue);
                zzap zzap2 = (zzap) sortedMap.get(valueOf);
                if (null != zzap2) {
                    zzq(intValue + 1, zzap2);
                    this.zza.remove(valueOf);
                }
            }
            zzq(i2, zzap);
        }
    }

    public void zzq(int i2, zzap zzap) {
        if (32468 < i2) {
            throw new IllegalStateException("Array too large");
        } else if (0 > i2) {
            final String sb = "Out of bounds index: " +
                    i2;
            throw new IndexOutOfBoundsException(sb);
        } else if (null == zzap) {
            this.zza.remove(Integer.valueOf(i2));
        } else {
            this.zza.put(Integer.valueOf(i2), zzap);
        }
    }

    public zzae(List list) {
        this();
        if (null != list) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                zzq(i2, (zzap) list.get(i2));
            }
        }
    }
}
