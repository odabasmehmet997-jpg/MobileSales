package com.google.android.gms.tagmanager;

import android.content.Context;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.internal.gtm.*;
import com.google.android.gms.internal.gtm.zzak;
import com.google.android.gms.internal.gtm.zzam;
import com.google.android.gms.internal.gtm.zzap;

import java.io.UnsupportedEncodingException;
import java.util.*;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzep {
    private static final zzdn zza = new zzdn(zzfp.zza(), true);
    private final zzrg zzb;
    private final Map zzc;
    private final Map zzd;
    private final Map zze;
    private final Set zzf;
    private final DataLayer zzg;
    private final Map zzh;
    private volatile String zzi;
    private int zzj;
    private final zzcw zzk = new zzcw(1048576, new zzei(this));
    private final zzcw zzl = new zzcw(1048576, new zzej(this));

    public zzep(Context context, zzrg zzrg, DataLayer dataLayer, zzal zzal, zzal zzal2, zzdg zzdg) {
        this.zzb = zzrg;
        HashSet<zzri> hashSet = new HashSet<>(zzrg.zzc());
        this.zzf = hashSet;
        this.zzg = dataLayer;
        HashMap hashMap = new HashMap();
        this.zzc = hashMap;
        zzj(hashMap, new zzo(context));
        zzj(hashMap, new zzam(zzal2));
        zzj(hashMap, new zzba(dataLayer));
        zzj(hashMap, new zzfq(context, dataLayer));
        HashMap hashMap2 = new HashMap();
        this.zzd = hashMap2;
        zzj(hashMap2, new zzaj());
        zzj(hashMap2, new zzbm());
        zzj(hashMap2, new zzbn());
        zzj(hashMap2, new zzbr());
        zzj(hashMap2, new zzbs());
        zzj(hashMap2, new zzcy());
        zzj(hashMap2, new zzcz());
        zzj(hashMap2, new zzdz());
        zzj(hashMap2, new zzfe());
        HashMap hashMap3 = new HashMap();
        this.zze = hashMap3;
        zzj(hashMap3, new zze(zzd.zzb(context)));
        zzj(hashMap3, new zzf(zzd.zzb(context)));
        zzj(hashMap3, new zzh(context));
        zzj(hashMap3, new zzi(context));
        zzj(hashMap3, new zzj(context));
        zzj(hashMap3, new zzk(context));
        zzj(hashMap3, new zzl(context));
        zzj(hashMap3, new zzr());
        zzj(hashMap3, new zzai(zzrg.zzb()));
        zzj(hashMap3, new zzam(zzal));
        zzj(hashMap3, new zzat(dataLayer));
        zzj(hashMap3, new zzbd(context));
        zzj(hashMap3, new zzbe());
        zzj(hashMap3, new zzbl());
        zzj(hashMap3, new zzbo(this));
        zzj(hashMap3, new zzbt());
        zzj(hashMap3, new zzbu());
        zzj(hashMap3, new zzcr(context));
        zzj(hashMap3, new zzct());
        zzj(hashMap3, new zzcx());
        zzj(hashMap3, new zzdd());
        zzj(hashMap3, new zzde(context));
        zzj(hashMap3, new zzdo());
        zzj(hashMap3, new zzds());
        zzj(hashMap3, new zzdw());
        zzj(hashMap3, new zzdy());
        zzj(hashMap3, new zzea(context));
        zzj(hashMap3, new zzeq());
        zzj(hashMap3, new zzer());
        zzj(hashMap3, new zzfk());
        zzj(hashMap3, new zzfr());
        this.zzh = new HashMap();
        for (zzri zzri : hashSet) {
            for (int i2 = 0; i2 < zzri.zza().size(); i2++) {
                zzre zzre = (zzre) zzri.zza().get(i2);
                zzeo zzg2 = zzg(this.zzh, zzh(zzre));
                zzg2.zzk(zzri);
                zzg2.zzg(zzri, zzre);
                zzg2.zzh(zzri, "Unknown");
            }
            for (int i3 = 0; i3 < zzri.zzf().size(); i3++) {
                zzre zzre2 = (zzre) zzri.zzf().get(i3);
                zzeo zzg3 = zzg(this.zzh, zzh(zzre2));
                zzg3.zzk(zzri);
                zzg3.zzi(zzri, zzre2);
                zzg3.zzj(zzri, "Unknown");
            }
        }
        for (Map.Entry entry : this.zzb.zzd().entrySet()) {
            for (zzre zzre3 : (List) entry.getValue()) {
                if (!zzfp.zzf(zzfp.zzk((zzap) zzre3.zzc().get(zzb.NOT_DEFAULT_MACRO.toString()))).booleanValue()) {
                    zzg(this.zzh, (String) entry.getKey()).zzl(zzre3);
                }
            }
        }
    }

    private static zzeo zzg(Map map, String str) {
        zzeo zzeo = (zzeo) map.get(str);
        if (zzeo != null) {
            return zzeo;
        }
        zzeo zzeo2 = new zzeo();
        map.put(str, zzeo2);
        return zzeo2;
    }

    private static String zzh(zzre zzre) {
        return zzfp.zzm(zzfp.zzk((zzap) zzre.zzc().get(zzb.INSTANCE_NAME.toString())));
    }

    private String zzi() {
        if (this.zzj <= 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.zzj);
        for (int i2 = 2; i2 < this.zzj; i2++) {
            sb.append(' ');
        }
        sb.append(": ");
        return sb.toString();
    }

    private static void zzj(Map map, zzbp zzbp) {
        if (!map.containsKey(zzbp.zze())) {
            map.put(zzbp.zze(), zzbp);
            return;
        }
        throw new IllegalArgumentException("Duplicate function type name: ".concat(String.valueOf(zzbp.zze())));
    }

    private void zzk(zzap zzap, Set set) {
        zzdn zzo;
        if (zzap != null && (zzo = zzo(zzap, set, new zzdl())) != zza) {
            Object zzk2 = zzfp.zzk((zzap) zzo.zza());
            if (zzk2 instanceof Map) {
                this.zzg.push((Map) zzk2);
            } else if (zzk2 instanceof List) {
                for (Object next : (List) zzk2) {
                    if (next instanceof Map) {
                        this.zzg.push((Map) next);
                    } else {
                        Log.w("GoogleTagManager", "pushAfterEvaluate: value not a Map");
                    }
                }
            } else {
                Log.w("GoogleTagManager", "pushAfterEvaluate: value not a Map or List");
            }
        }
    }

    private zzdn zzl(Set set, Set set2, zzem zzem, zzdk zzdk) {
        boolean z;
        zzdn zzdn;
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        Iterator it = set.iterator();
        while (true) {
            boolean z2 = true;
            while (true) {
                if (it.hasNext()) {
                    zzri zzri = (zzri) it.next();
                    zzdj zzdj = new zzdj();
                    Iterator it2 = zzri.zzd().iterator();
                    while (true) {
                        boolean z3 = true;
                        while (true) {
                            if (!it2.hasNext()) {
                                Iterator it3 = zzri.zze().iterator();
                                while (true) {
                                    if (!it3.hasNext()) {
                                        Boolean bool = Boolean.TRUE;
                                        zzfp.zzb(bool);
                                        zzdn = new zzdn(bool, z);
                                        break;
                                    }
                                    zzdn zzf2 = zzf((zzre) it3.next(), set2, new zzdi());
                                    if (!((Boolean) zzf2.zza()).booleanValue()) {
                                        Boolean bool2 = Boolean.FALSE;
                                        zzfp.zzb(bool2);
                                        zzdn = new zzdn(bool2, zzf2.zzb());
                                        break;
                                    }
                                    z = z && zzf2.zzb();
                                }
                            } else {
                                zzdn zzf3 = zzf((zzre) it2.next(), set2, new zzdi());
                                if (((Boolean) zzf3.zza()).booleanValue()) {
                                    Boolean bool3 = Boolean.FALSE;
                                    zzfp.zzb(bool3);
                                    zzdn = new zzdn(bool3, zzf3.zzb());
                                    break;
                                } else if (!z || !zzf3.zzb()) {
                                    z3 = false;
                                }
                            }
                        }
                    }
                    if (((Boolean) zzdn.zza()).booleanValue()) {
                        zzem.zza(zzri, hashSet, hashSet2, zzdj);
                    }
                    if (!z2 || !zzdn.zzb()) {
                        z2 = false;
                    }
                } else {
                    hashSet.removeAll(hashSet2);
                    return new zzdn(hashSet, z2);
                }
            }
        }
    }

    private zzdn zzm(String str, Set set, zzdh zzdh) {
        zzre zzre;
        boolean z = true;
        this.zzj++;
        zzen zzen = (zzen) this.zzl.zza(str);
        if (zzen == null) {
            zzeo zzeo = (zzeo) this.zzh.get(str);
            if (zzeo == null) {
                Log.e("GoogleTagManager", zzi() + "Invalid macro: " + str);
                this.zzj = this.zzj + -1;
                return zza;
            }
            zzdn zzl2 = zzl(zzeo.zzf(), set, new zzek(this, zzeo.zzc(), zzeo.zzb(), zzeo.zze(), zzeo.zzd()), new zzdk());
            if (((Set) zzl2.zza()).isEmpty()) {
                zzre = zzeo.zza();
            } else {
                if (((Set) zzl2.zza()).size() > 1) {
                    Log.w("GoogleTagManager", zzi() + "Multiple macros active for macroName " + str);
                }
                zzre = (zzre) ((Set) zzl2.zza()).iterator().next();
            }
            if (zzre == null) {
                this.zzj--;
                return zza;
            }
            zzdn zzn = zzn(this.zze, zzre, set, new zzdi());
            if (!zzl2.zzb() || !zzn.zzb()) {
                z = false;
            }
            zzdn zzdn = zza;
            if (zzn != zzdn) {
                zzdn = new zzdn(zzn.zza(), z);
            }
            zzap zza2 = zzre.zza();
            if (zzdn.zzb()) {
                this.zzl.zzb(str, new zzen(zzdn, zza2));
            }
            zzk(zza2, set);
            this.zzj--;
            return zzdn;
        }
        zzk(zzen.zzb(), set);
        this.zzj--;
        return zzen.zzc();
    }

    private zzdn zzn(Map map, zzre zzre, Set set, zzdi zzdi) {
        zzap zzap = (zzap) zzre.zzc().get(zzb.FUNCTION.toString());
        if (zzap == null) {
            Log.e("GoogleTagManager", "No function id in properties");
            return zza;
        }
        String zzn = zzap.zzn();
        zzbp zzbp = (zzbp) map.get(zzn);
        if (zzbp == null) {
            Log.e("GoogleTagManager", zzn.concat(" has no backing implementation."));
            return zza;
        }
        zzdn zzdn = (zzdn) this.zzk.zza(zzre);
        if (zzdn != null) {
            return zzdn;
        }
        HashMap hashMap = new HashMap();
        boolean z = true;
        boolean z2 = true;
        for (Map.Entry entry : zzre.zzc().entrySet()) {
            String str = (String) entry.getKey();
            zzap zzap2 = (zzap) entry.getValue();
            zzdn zzo = zzo((zzap) entry.getValue(), set, new zzdl());
            zzdn zzdn2 = zza;
            if (zzo == zzdn2) {
                return zzdn2;
            }
            if (zzo.zzb()) {
                zzre.zzd((String) entry.getKey(), (zzap) zzo.zza());
            } else {
                z2 = false;
            }
            hashMap.put(entry.getKey(), zzo.zza());
        }
        if (!zzbp.zzg(hashMap.keySet())) {
            Log.e("GoogleTagManager", "Incorrect keys for function " + zzn + " required " + zzbp.zzf().toString() + " had " + hashMap.keySet());
            return zza;
        }
        if (!z2 || !zzbp.zzb()) {
            z = false;
        }
        zzdn zzdn3 = new zzdn(zzbp.zza(hashMap), z);
        if (z) {
            this.zzk.zzb(zzre, zzdn3);
        }
        zzap zzap3 = (zzap) zzdn3.zza();
        return zzdn3;
    }

    private zzdn zzo(zzap zzap, Set set, zzdl zzdl) {
        if (!zzap.zzN()) {
            return new zzdn(zzap, true);
        }
        int zzO = zzap.zzO();
        if (zzO == 2) {
            zzak zzak = (zzak) zzrm.zza(zzap).zzaa();
            zzak.zzj();
            for (int i2 = 0; i2 < zzap.zza(); i2++) {
                zzdn zzo = zzo(zzap.zzj(i2), set, new zzdl());
                zzdn zzdn = zza;
                if (zzo == zzdn) {
                    return zzdn;
                }
                zzak.zze((zzap) zzo.zza());
            }
            return new zzdn(zzak.zzD(), false);
        } else if (zzO == 3) {
            zzak zzak2 = (zzak) zzrm.zza(zzap).zzaa();
            if (zzap.zzc() != zzap.zzd()) {
                Log.e("GoogleTagManager", "Invalid serving value: ".concat(zzap.toString()));
                return zza;
            }
            zzak2.zzk();
            zzak2.zzl();
            for (int i3 = 0; i3 < zzap.zzc(); i3++) {
                zzdn zzo2 = zzo(zzap.zzk(i3), set, new zzdl());
                zzdn zzo3 = zzo(zzap.zzl(i3), set, new zzdl());
                zzdn zzdn2 = zza;
                if (zzo2 == zzdn2 || zzo3 == zzdn2) {
                    return zzdn2;
                }
                zzak2.zzf((zzap) zzo2.zza());
                zzak2.zzg((zzap) zzo3.zza());
            }
            return new zzdn(zzak2.zzD(), false);
        } else if (zzO != 4) {
            if (zzO != 7) {
                Log.e("GoogleTagManager", "Unknown type: ".concat(Integer.toString(zzap.zzO())));
                return zza;
            }
            zzak zzak3 = (zzak) zzrm.zza(zzap).zzaa();
            zzak3.zzm();
            for (int i4 = 0; i4 < zzap.zze(); i4++) {
                zzdn zzo4 = zzo(zzap.zzm(i4), set, new zzdl());
                zzdn zzdn3 = zza;
                if (zzo4 == zzdn3) {
                    return zzdn3;
                }
                zzak3.zzh((zzap) zzo4.zza());
            }
            return new zzdn(zzak3.zzD(), false);
        } else if (set.contains(zzap.zzo())) {
            Log.e("GoogleTagManager", "Macro cycle detected.  Current macro reference: " + zzap.zzo() + ".  Previous macro references: " + set + ".");
            return zza;
        } else {
            set.add(zzap.zzo());
            zzdn zzm = zzm(zzap.zzo(), set, new zzdh());
            for (zzam zzam : (zzam[]) zzap.zzq().toArray(new zzam[0])) {
                if (!(zzfp.zzk((zzap) zzm.zza()) instanceof String)) {
                    Log.e("GoogleTagManager", "Escaping can only be applied to strings.");
                } else if (zzam.ordinal() == 11) {
                    try {
                        zzm = new zzdn(zzfp.zzb(zzfs.zza(zzfp.zzm(zzfp.zzk((zzap) zzm.zza())))), zzm.zzb());
                    } catch (UnsupportedEncodingException e2) {
                        Log.e("GoogleTagManager", "Escape URI: unsupported encoding", e2);
                    }
                }
            }
            set.remove(zzap.zzo());
            return zzm;
        }
    }

    public zzdn zza(String str) {
        this.zzj = 0;
        return zzm(str, new HashSet(), new zzdh());
    }


    public synchronized String zzb() {
        return this.zzi;
    }

    public synchronized void zzc(String str) {
        try {
            zzd(str);
            zzdk zzdk = new zzdk();
            for (zzre zzn : (Set) zzl(this.zzf, new HashSet(), new zzel(this), zzdk).zza()) {
                zzn(this.zzc, zzn, new HashSet(), new zzdi());
            }
            zzd(null);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }


    @VisibleForTesting
    public synchronized void zzd(String str) {
        this.zzi = str;
    }

    /*  DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v13, resolved type: java.lang.Object} */
    /*  DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: java.util.Map} */
    /*  WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void zze(java.util.List r13) {
        /*
            r12 = this;
            monitor-enter(r12)
            java.util.Iterator r13 = r13.iterator()     // Catch:{ all -> 0x0035 }
        L_0x0005:
            boolean r0 = r13.hasNext()     // Catch:{ all -> 0x0035 }
            if (r0 == 0) goto L_0x0193
            java.lang.Object r0 = r13.next()     // Catch:{ all -> 0x0035 }
            com.google.android.gms.internal.gtm.zzaf r0 = (com.google.android.gms.internal.gtm.zzaf) r0     // Catch:{ all -> 0x0035 }
            boolean r1 = r0.zzf()     // Catch:{ all -> 0x0035 }
            if (r1 == 0) goto L_0x0182
            java.lang.String r1 = r0.zzd()     // Catch:{ all -> 0x0035 }
            java.lang.String r2 = "gaExperiment:"
            boolean r1 = r1.startsWith(r2)     // Catch:{ all -> 0x0035 }
            if (r1 != 0) goto L_0x0025
            goto L_0x0182
        L_0x0025:
            com.google.android.gms.tagmanager.DataLayer r1 = r12.zzg     // Catch:{ all -> 0x0035 }
            boolean r2 = r0.zze()     // Catch:{ all -> 0x0035 }
            if (r2 != 0) goto L_0x0038
            java.lang.String r0 = "GoogleTagManager"
            java.lang.String r1 = "supplemental missing experimentSupplemental"
            android.util.Log.w(r0, r1)     // Catch:{ all -> 0x0035 }
            goto L_0x0005
        L_0x0035:
            r13 = move-exception
            goto L_0x0195
        L_0x0038:
            com.google.android.gms.internal.gtm.zzv r2 = r0.zza()     // Catch:{ all -> 0x0035 }
            java.util.List r2 = r2.zze()     // Catch:{ all -> 0x0035 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0035 }
        L_0x0044:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x0035 }
            if (r3 == 0) goto L_0x005c
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x0035 }
            com.google.android.gms.internal.gtm.zzap r3 = (com.google.android.gms.internal.gtm.zzap) r3     // Catch:{ all -> 0x0035 }
            java.lang.Object r3 = com.google.android.gms.tagmanager.zzfp.zzk(r3)     // Catch:{ all -> 0x0035 }
            java.lang.String r3 = com.google.android.gms.tagmanager.zzfp.zzm(r3)     // Catch:{ all -> 0x0035 }
            r1.zzd(r3)     // Catch:{ all -> 0x0035 }
            goto L_0x0044
        L_0x005c:
            com.google.android.gms.internal.gtm.zzv r2 = r0.zza()     // Catch:{ all -> 0x0035 }
            java.util.List r2 = r2.zzf()     // Catch:{ all -> 0x0035 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0035 }
        L_0x0068:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x0035 }
            r4 = 0
            if (r3 == 0) goto L_0x00a6
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x0035 }
            com.google.android.gms.internal.gtm.zzap r3 = (com.google.android.gms.internal.gtm.zzap) r3     // Catch:{ all -> 0x0035 }
            java.lang.Object r3 = com.google.android.gms.tagmanager.zzfp.zzk(r3)     // Catch:{ all -> 0x0035 }
            boolean r5 = r3 instanceof java.util.Map     // Catch:{ all -> 0x0035 }
            if (r5 != 0) goto L_0x009d
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x0035 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0035 }
            r5.<init>()     // Catch:{ all -> 0x0035 }
            java.lang.String r6 = "value: "
            r5.append(r6)     // Catch:{ all -> 0x0035 }
            r5.append(r3)     // Catch:{ all -> 0x0035 }
            java.lang.String r3 = " is not a map value, ignored."
            r5.append(r3)     // Catch:{ all -> 0x0035 }
            java.lang.String r3 = r5.toString()     // Catch:{ all -> 0x0035 }
            java.lang.String r5 = "GoogleTagManager"
            android.util.Log.w(r5, r3)     // Catch:{ all -> 0x0035 }
            goto L_0x00a0
        L_0x009d:
            r4 = r3
            java.util.Map r4 = (java.util.Map) r4     // Catch:{ all -> 0x0035 }
        L_0x00a0:
            if (r4 == 0) goto L_0x0068
            r1.push(r4)     // Catch:{ all -> 0x0035 }
            goto L_0x0068
        L_0x00a6:
            com.google.android.gms.internal.gtm.zzv r0 = r0.zza()     // Catch:{ all -> 0x0035 }
            java.util.List r0 = r0.zzd()     // Catch:{ all -> 0x0035 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0035 }
        L_0x00b2:
            boolean r2 = r0.hasNext()     // Catch:{ all -> 0x0035 }
            if (r2 == 0) goto L_0x0005
            java.lang.Object r2 = r0.next()     // Catch:{ all -> 0x0035 }
            com.google.android.gms.internal.gtm.zzt r2 = (com.google.android.gms.internal.gtm.zzt) r2     // Catch:{ all -> 0x0035 }
            boolean r3 = r2.zzh()     // Catch:{ all -> 0x0035 }
            if (r3 != 0) goto L_0x00cc
            java.lang.String r2 = "GoogleTagManager"
            java.lang.String r3 = "GaExperimentRandom: No key"
            android.util.Log.w(r2, r3)     // Catch:{ all -> 0x0035 }
            goto L_0x00b2
        L_0x00cc:
            java.lang.String r3 = r2.zzf()     // Catch:{ all -> 0x0035 }
            java.lang.Object r3 = r1.get(r3)     // Catch:{ all -> 0x0035 }
            boolean r5 = r3 instanceof java.lang.Number     // Catch:{ all -> 0x0035 }
            if (r5 != 0) goto L_0x00da
            r5 = r4
            goto L_0x00e5
        L_0x00da:
            r5 = r3
            java.lang.Number r5 = (java.lang.Number) r5     // Catch:{ all -> 0x0035 }
            long r5 = r5.longValue()     // Catch:{ all -> 0x0035 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0035 }
        L_0x00e5:
            long r6 = r2.zzd()     // Catch:{ all -> 0x0035 }
            long r8 = r2.zzc()     // Catch:{ all -> 0x0035 }
            boolean r10 = r2.zzg()     // Catch:{ all -> 0x0035 }
            if (r10 == 0) goto L_0x0105
            if (r5 == 0) goto L_0x0105
            long r10 = r5.longValue()     // Catch:{ all -> 0x0035 }
            int r10 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r10 < 0) goto L_0x0105
            long r10 = r5.longValue()     // Catch:{ all -> 0x0035 }
            int r5 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r5 <= 0) goto L_0x011a
        L_0x0105:
            int r3 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r3 > 0) goto L_0x0179
            long r8 = r8 - r6
            double r10 = java.lang.Math.random()     // Catch:{ all -> 0x0035 }
            double r8 = (double) r8     // Catch:{ all -> 0x0035 }
            double r10 = r10 * r8
            double r5 = (double) r6     // Catch:{ all -> 0x0035 }
            double r10 = r10 + r5
            long r5 = java.lang.Math.round(r10)     // Catch:{ all -> 0x0035 }
            java.lang.Long r3 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0035 }
        L_0x011a:
            java.lang.String r5 = r2.zzf()     // Catch:{ all -> 0x0035 }
            r1.zzd(r5)     // Catch:{ all -> 0x0035 }
            java.lang.String r5 = r2.zzf()     // Catch:{ all -> 0x0035 }
            java.util.Map r3 = r1.zza(r5, r3)     // Catch:{ all -> 0x0035 }
            long r5 = r2.zza()     // Catch:{ all -> 0x0035 }
            r7 = 0
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 <= 0) goto L_0x0174
            java.lang.String r5 = "gtm"
            boolean r5 = r3.containsKey(r5)     // Catch:{ all -> 0x0035 }
            if (r5 != 0) goto L_0x0153
            java.lang.String r5 = "lifetime"
            long r6 = r2.zza()     // Catch:{ all -> 0x0035 }
            java.lang.Long r2 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0035 }
            java.lang.Object[] r2 = new java.lang.Object[]{r5, r2}     // Catch:{ all -> 0x0035 }
            java.util.Map r2 = com.google.android.gms.tagmanager.DataLayer.mapOf(r2)     // Catch:{ all -> 0x0035 }
            java.lang.String r5 = "gtm"
            r3.put(r5, r2)     // Catch:{ all -> 0x0035 }
            goto L_0x0174
        L_0x0153:
            java.lang.String r5 = "gtm"
            java.lang.Object r5 = r3.get(r5)     // Catch:{ all -> 0x0035 }
            boolean r6 = r5 instanceof java.util.Map     // Catch:{ all -> 0x0035 }
            if (r6 == 0) goto L_0x016d
            java.util.Map r5 = (java.util.Map) r5     // Catch:{ all -> 0x0035 }
            long r6 = r2.zza()     // Catch:{ all -> 0x0035 }
            java.lang.Long r2 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0035 }
            java.lang.String r6 = "lifetime"
            r5.put(r6, r2)     // Catch:{ all -> 0x0035 }
            goto L_0x0174
        L_0x016d:
            java.lang.String r2 = "GoogleTagManager"
            java.lang.String r5 = "GaExperimentRandom: gtm not a map"
            android.util.Log.w(r2, r5)     // Catch:{ all -> 0x0035 }
        L_0x0174:
            r1.push(r3)     // Catch:{ all -> 0x0035 }
            goto L_0x00b2
        L_0x0179:
            java.lang.String r2 = "GoogleTagManager"
            java.lang.String r3 = "GaExperimentRandom: random range invalid"
            android.util.Log.w(r2, r3)     // Catch:{ all -> 0x0035 }
            goto L_0x00b2
        L_0x0182:
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0035 }
            java.lang.String r1 = "Ignored supplemental: "
            com.google.android.gms.tagmanager.zzbb r2 = com.google.android.gms.tagmanager.zzdc.zzb     // Catch:{ all -> 0x0035 }
            java.lang.String r0 = r1.concat(r0)     // Catch:{ all -> 0x0035 }
            r2.zzd(r0)     // Catch:{ all -> 0x0035 }
            goto L_0x0005
        L_0x0193:
            monitor-exit(r12)
            return
        L_0x0195:
            monitor-exit(r12)     // Catch:{ all -> 0x0035 }
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzep.zze(java.util.List):void");
    }


    @VisibleForTesting
    public zzdn zzf(zzre zzre, Set set, zzdi zzdi) {
        zzdn zzn = zzn(this.zzd, zzre, set, zzdi);
        Boolean zzf2 = zzfp.zzf(zzfp.zzk((zzap) zzn.zza()));
        zzfp.zzb(zzf2);
        return new zzdn(zzf2, zzn.zzb());
    }
}
