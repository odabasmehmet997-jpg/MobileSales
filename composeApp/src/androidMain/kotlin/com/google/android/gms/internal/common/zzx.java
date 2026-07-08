package com.google.android.gms.internal.common;

import org.jspecify.nullness.NullMarked;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@NullMarked
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public final class zzx {
    
    public final zzo zza;
    
    public final boolean zzb;
    private final zzu zzc;

    private zzx(zzu zzu, boolean z, zzo zzo, int i2) {
        this.zzc = zzu;
        this.zzb = z;
        this.zza = zzo;
    }

    public static zzx zzc(zzo zzo) {
        return new zzx(new zzu(zzo), false, zzn.zza, Integer.MAX_VALUE);
    }

    
    public Iterator zzh(CharSequence charSequence) {
        return new zzt(this.zzc, this, charSequence);
    }

    public zzx zzb() {
        return new zzx(this.zzc, true, this.zza, Integer.MAX_VALUE);
    }

    public Iterable zzd(CharSequence charSequence) {
        return new zzv(this, charSequence);
    }

    public List zzf(CharSequence charSequence) {
        charSequence.getClass();
        Iterator zzh = zzh(charSequence);
        ArrayList arrayList = new ArrayList();
        while (zzh.hasNext()) {
            arrayList.add(zzh.next());
        }
        return Collections.unmodifiableList(arrayList);
    }
}
