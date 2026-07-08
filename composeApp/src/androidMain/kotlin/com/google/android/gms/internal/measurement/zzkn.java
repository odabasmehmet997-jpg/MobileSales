package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
public final class zzkn extends zzii implements zzko {
    public static final zzko zza;
    private static final zzkn zzb;
    private final List zzc;

    static {
        zzkn zzkn = new zzkn(10);
        zzb = zzkn;
        zzkn.zzb();
        zza = zzkn;
    }

    public zzkn() {
        this(10);
    }

    private static String zzj(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zziy) {
            return ((zziy) obj).zzn(zzkh.zzb);
        }
        return zzkh.zzh((byte[]) obj);
    }

    public void add(int i2, Object obj) {
        zzbK();
        this.zzc.add(i2, obj);
        this.modCount++;
    }

    public boolean addAll(int i2, Collection collection) {
        zzbK();
        if (collection instanceof zzko) {
            collection = ((zzko) collection).zzh();
        }
        boolean addAll = this.zzc.addAll(i2, collection);
        this.modCount++;
        return addAll;
    }

    public void clear() {
        zzbK();
        this.zzc.clear();
        this.modCount++;
    }

    public Object remove(int i2) {
        zzbK();
        Object remove = this.zzc.remove(i2);
        this.modCount++;
        return zzj(remove);
    }

    public Object set(int i2, Object obj) {
        zzbK();
        return zzj(this.zzc.set(i2, obj));
    }

    public int size() {
        return this.zzc.size();
    }

    public zzkg zzd(int i2) {
        if (i2 >= size()) {
            ArrayList arrayList = new ArrayList(i2);
            arrayList.addAll(this.zzc);
            return new zzkn(arrayList);
        }
        throw new IllegalArgumentException();
    }

    public zzko zze() {
        return zzc() ? new zzmn(this) : this;
    }

    public Object zzf(int i2) {
        return this.zzc.get(i2);
    }

    /* renamed from: zzg */
    public String get(int i2) {
        Object obj = this.zzc.get(i2);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof final zziy zziy) {
            String zzn = zziy.zzn(zzkh.zzb);
            if (zziy.zzi()) {
                this.zzc.set(i2, zzn);
            }
            return zzn;
        }
        byte[] bArr = (byte[]) obj;
        String zzh = zzkh.zzh(bArr);
        if (zzkh.zzi(bArr)) {
            this.zzc.set(i2, zzh);
        }
        return zzh;
    }

    public List zzh() {
        return Collections.unmodifiableList(this.zzc);
    }

    public void zzi(zziy zziy) {
        zzbK();
        this.zzc.add(zziy);
        this.modCount++;
    }

    public zzkn(int i2) {
        this.zzc = new ArrayList(i2);
    }

    private zzkn(ArrayList arrayList) {
        this.zzc = arrayList;
    }

    public boolean addAll(Collection collection) {
        return addAll(size(), collection);
    }
}
