package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public interface zzap {
    zzap zzf = new zzau();
    zzap zzg = new zzan();
    zzap zzh = new zzag("continue");
    zzap zzi = new zzag("break");
    zzap zzj = new zzag("return");
    zzap zzk = new zzaf(Boolean.TRUE);
    zzap zzl = new zzaf(Boolean.FALSE);
    zzap zzm = new zzat("");

    zzap zzbI(String str, zzg zzg2, List list);

    zzap zzd();

    Boolean zzg();

    Double zzh(String zzd, int i, List list);

    String zzi();

    Iterator zzl();
}
