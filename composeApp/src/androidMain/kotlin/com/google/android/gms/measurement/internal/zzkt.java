package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzaa;
import com.google.android.gms.internal.measurement.zzej;
import com.google.android.gms.internal.measurement.zzel;
import com.google.android.gms.internal.measurement.zzeq;
import com.google.android.gms.internal.measurement.zzes;
import com.google.android.gms.internal.measurement.zzex;
import com.google.android.gms.internal.measurement.zzfk;
import com.google.android.gms.internal.measurement.zzfm;
import com.google.android.gms.internal.measurement.zzfn;
import com.google.android.gms.internal.measurement.zzfo;
import com.google.android.gms.internal.measurement.zzfr;
import com.google.android.gms.internal.measurement.zzfs;
import com.google.android.gms.internal.measurement.zzfw;
import com.google.android.gms.internal.measurement.zzfx;
import com.google.android.gms.internal.measurement.zzfy;
import com.google.android.gms.internal.measurement.zzgd;
import com.google.android.gms.internal.measurement.zzgf;
import com.google.android.gms.internal.measurement.zzgg;
import com.google.android.gms.internal.measurement.zzgh;
import com.google.android.gms.internal.measurement.zzjl;
import com.google.android.gms.internal.measurement.zzkj;
import com.google.android.gms.internal.measurement.zzlf;
import com.google.android.gms.internal.measurement.zzoz;
import com.proje.mobilesales.core.sql.SqlLiteVariable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.*;
import java.util.zip.GZIPOutputStream;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzkt extends zzkh {
    zzkt(final zzkr zzkr) {
        super(zzkr);
    }

    @WorkerThread
    static boolean zzA(final zzau zzau, final zzp zzp) {
        Preconditions.checkNotNull(zzau);
        Preconditions.checkNotNull(zzp);
        return !TextUtils.isEmpty(zzp.zzb) || !TextUtils.isEmpty(zzp.zzq);
    }

    static zzfs zzB(final zzfo zzfo, final String str) {
        for (final zzfs zzfs : zzfo.zzi()) {
            if (zzfs.zzg().equals(str)) {
                return zzfs;
            }
        }
        return null;
    }

    static Object zzC(final zzfo zzfo, final String str) {
        final zzfs zzB = zzkt.zzB(zzfo, str);
        if (null == zzB) {
            return null;
        }
        if (zzB.zzy()) {
            return zzB.zzh();
        }
        if (zzB.zzw()) {
            return Long.valueOf(zzB.zzd());
        }
        if (zzB.zzu()) {
            return Double.valueOf(zzB.zza());
        }
        if (0 >= zzB.zzc()) {
            return null;
        }
        final List<zzfs> zzi = zzB.zzi();
        final ArrayList arrayList = new ArrayList();
        for (final zzfs zzfs : zzi) {
            if (null != zzfs) {
                final Bundle bundle = new Bundle();
                for (final zzfs zzfs2 : zzfs.zzi()) {
                    if (zzfs2.zzy()) {
                        bundle.putString(zzfs2.zzg(), zzfs2.zzh());
                    } else if (zzfs2.zzw()) {
                        bundle.putLong(zzfs2.zzg(), zzfs2.zzd());
                    } else if (zzfs2.zzu()) {
                        bundle.putDouble(zzfs2.zzg(), zzfs2.zza());
                    }
                }
                if (!bundle.isEmpty()) {
                    arrayList.add(bundle);
                }
            }
        }
        return arrayList.toArray(new Bundle[arrayList.size()]);
    }

    private void zzD(final StringBuilder sb, final int i2, final List list) {
        if (null != list) {
            final int i3 = i2 + 1;
            final Iterator it = list.iterator();
            while (it.hasNext()) {
                final zzfs zzfs = (zzfs) it.next();
                if (null != zzfs) {
                    zzkt.zzF(sb, i3);
                    sb.append("param {\n");
                    Double d2 = null;
                    zzkt.zzI(sb, i3, "name", zzfs.zzx() ? zzs.zzj().zze(zzfs.zzg()) : null);
                    zzkt.zzI(sb, i3, "string_value", zzfs.zzy() ? zzfs.zzh() : null);
                    zzkt.zzI(sb, i3, "int_value", zzfs.zzw() ? Long.valueOf(zzfs.zzd()) : null);
                    if (zzfs.zzu()) {
                        d2 = Double.valueOf(zzfs.zza());
                    }
                    zzkt.zzI(sb, i3, "double_value", d2);
                    if (0 < zzfs.zzc()) {
                        this.zzD(sb, i3, zzfs.zzi());
                    }
                    zzkt.zzF(sb, i3);
                    sb.append("}\n");
                }
            }
        }
    }

    private void zzE(final StringBuilder sb, final int i2, final zzel zzel) {
        final String str;
        if (null != zzel) {
            zzkt.zzF(sb, i2);
            sb.append("filter {\n");
            if (zzel.zzh()) {
                zzkt.zzI(sb, i2, "complement", Boolean.valueOf(zzel.zzg()));
            }
            if (zzel.zzj()) {
                zzkt.zzI(sb, i2, "param_name", zzs.zzj().zze(zzel.zze()));
            }
            if (zzel.zzk()) {
                final int i3 = i2 + 1;
                final zzex zzd = zzel.zzd();
                if (null != zzd) {
                    zzkt.zzF(sb, i3);
                    sb.append("string_filter {\n");
                    if (zzd.zzi()) {
                        switch (zzd.zzj()) {
                            case 1:
                                str = "UNKNOWN_MATCH_TYPE";
                                break;
                            case 2:
                                str = "REGEXP";
                                break;
                            case 3:
                                str = "BEGINS_WITH";
                                break;
                            case 4:
                                str = "ENDS_WITH";
                                break;
                            case 5:
                                str = "PARTIAL";
                                break;
                            case 6:
                                str = "EXACT";
                                break;
                            default:
                                str = "IN_LIST";
                                break;
                        }
                        zzkt.zzI(sb, i3, "match_type", str);
                    }
                    if (zzd.zzh()) {
                        zzkt.zzI(sb, i3, "expression", zzd.zzd());
                    }
                    if (zzd.zzg()) {
                        zzkt.zzI(sb, i3, "case_sensitive", Boolean.valueOf(zzd.zzf()));
                    }
                    if (0 < zzd.zza()) {
                        zzkt.zzF(sb, i2 + 2);
                        sb.append("expression_list {\n");
                        for (final String append : zzd.zze()) {
                            zzkt.zzF(sb, i2 + 3);
                            sb.append(append);
                            sb.append(SqlLiteVariable._NEW_LINE);
                        }
                        sb.append("}\n");
                    }
                    zzkt.zzF(sb, i3);
                    sb.append("}\n");
                }
            }
            if (zzel.zzi()) {
                zzkt.zzJ(sb, i2 + 1, "number_filter", zzel.zzc());
            }
            zzkt.zzF(sb, i2);
            sb.append("}\n");
        }
    }

    private static void zzF(final StringBuilder sb, final int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            sb.append("  ");
        }
    }

    private static String zzG(final boolean z, final boolean z2, final boolean z3) {
        final StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("Dynamic ");
        }
        if (z2) {
            sb.append("Sequence ");
        }
        if (z3) {
            sb.append("Session-Scoped ");
        }
        return sb.toString();
    }

    private static void zzH(final StringBuilder sb, final int i2, final String str, final zzgd zzgd) {
        if (null != zzgd) {
            zzkt.zzF(sb, 3);
            sb.append(str);
            sb.append(" {\n");
            if (0 != zzgd.zzb()) {
                zzkt.zzF(sb, 4);
                sb.append("results: ");
                int i3 = 0;
                for (final Long l : zzgd.zzk()) {
                    final int i4 = i3 + 1;
                    if (0 != i3) {
                        sb.append(", ");
                    }
                    sb.append(l);
                    i3 = i4;
                }
                sb.append(10);
            }
            if (0 != zzgd.zzd()) {
                zzkt.zzF(sb, 4);
                sb.append("status: ");
                int i5 = 0;
                for (final Long l2 : zzgd.zzn()) {
                    final int i6 = i5 + 1;
                    if (0 != i5) {
                        sb.append(", ");
                    }
                    sb.append(l2);
                    i5 = i6;
                }
                sb.append(10);
            }
            if (0 != zzgd.zza()) {
                zzkt.zzF(sb, 4);
                sb.append("dynamic_filter_timestamps: {");
                int i7 = 0;
                for (final zzfm zzfm : zzgd.zzj()) {
                    final int i8 = i7 + 1;
                    if (0 != i7) {
                        sb.append(", ");
                    }
                    sb.append(zzfm.zzh() ? Integer.valueOf(zzfm.zza()) : null);
                    sb.append(":");
                    sb.append(zzfm.zzg() ? Long.valueOf(zzfm.zzb()) : null);
                    i7 = i8;
                }
                sb.append("}\n");
            }
            if (0 != zzgd.zzc()) {
                zzkt.zzF(sb, 4);
                sb.append("sequence_filter_timestamps: {");
                int i9 = 0;
                for (final zzgf zzgf : zzgd.zzm()) {
                    final int i10 = i9 + 1;
                    if (0 != i9) {
                        sb.append(", ");
                    }
                    sb.append(zzgf.zzi() ? Integer.valueOf(zzgf.zzb()) : null);
                    sb.append(": [");
                    int i11 = 0;
                    for (final Long longValue : zzgf.zzf()) {
                        final long longValue2 = longValue.longValue();
                        final int i12 = i11 + 1;
                        if (0 != i11) {
                            sb.append(", ");
                        }
                        sb.append(longValue2);
                        i11 = i12;
                    }
                    sb.append("]");
                    i9 = i10;
                }
                sb.append("}\n");
            }
            zzkt.zzF(sb, 3);
            sb.append("}\n");
        }
    }

    private static void zzI(final StringBuilder sb, final int i2, final String str, final Object obj) {
        if (null != obj) {
            zzkt.zzF(sb, i2 + 1);
            sb.append(str);
            sb.append(": ");
            sb.append(obj);
            sb.append(10);
        }
    }

    private static void zzJ(final StringBuilder sb, final int i2, final String str, final zzeq zzeq) {
        if (null != zzeq) {
            zzkt.zzF(sb, i2);
            sb.append(str);
            sb.append(" {\n");
            if (zzeq.zzg()) {
                final int zzm = zzeq.zzm();
                zzkt.zzI(sb, i2, "comparison_type", 1 != zzm ? 2 != zzm ? 3 != zzm ? 4 != zzm ? "BETWEEN" : "EQUAL" : "GREATER_THAN" : "LESS_THAN" : "UNKNOWN_COMPARISON_TYPE");
            }
            if (zzeq.zzi()) {
                zzkt.zzI(sb, i2, "match_as_float", Boolean.valueOf(zzeq.zzf()));
            }
            if (zzeq.zzh()) {
                zzkt.zzI(sb, i2, "comparison_value", zzeq.zzc());
            }
            if (zzeq.zzk()) {
                zzkt.zzI(sb, i2, "min_comparison_value", zzeq.zze());
            }
            if (zzeq.zzj()) {
                zzkt.zzI(sb, i2, "max_comparison_value", zzeq.zzd());
            }
            zzkt.zzF(sb, i2);
            sb.append("}\n");
        }
    }

    static int zza(final zzfx zzfx, final String str) {
        for (int i2 = 0; i2 < zzfx.zzb(); i2++) {
            if (str.equals(zzfx.zzaj(i2).zzf())) {
                return i2;
            }
        }
        return -1;
    }

    static zzlf zzl(final zzlf zzlf, final byte[] bArr) throws zzkj {
        final zzjl zzb = zzjl.zzb();
        if (null != zzb) {
            return zzlf.zzau(bArr, zzb);
        }
        return zzlf.zzat(bArr);
    }

    static List zzr(final BitSet bitSet) {
        final int length = (bitSet.length() + 63) / 64;
        final ArrayList arrayList = new ArrayList(length);
        for (int i2 = 0; i2 < length; i2++) {
            long j2 = 0;
            for (int i3 = 0; 64 > i3; i3++) {
                final int i4 = (i2 * 64) + i3;
                if (i4 >= bitSet.length()) {
                    break;
                }
                if (bitSet.get(i4)) {
                    j2 |= 1L << i3;
                }
            }
            arrayList.add(Long.valueOf(j2));
        }
        return arrayList;
    }

    static boolean zzv(final List list, final int i2) {
        if (i2 >= list.size() * 64) {
            return false;
        }
        return 0 != ((1L << (i2 % 64)) & ((Long) list.get(i2 / 64)).longValue());
    }

    static boolean zzx(final String str) {
        return null != str && str.matches("([+-])?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && 310 >= str.length();
    }

    static void zzz(final zzfn zzfn, final String str, final Object obj) {
        final List zzp = zzfn.zzp();
        int i2 = 0;
        while (true) {
            if (i2 >= zzp.size()) {
                i2 = -1;
                break;
            } else if (str.equals(((zzfs) zzp.get(i2)).zzg())) {
                break;
            } else {
                i2++;
            }
        }
        final zzfr zze = zzfs.zze();
        zze.zzj(str);
        if (obj instanceof Long) {
            zze.zzi(((Long) obj).longValue());
        }
        if (0 <= i2) {
            zzfn.zzj(i2, zze);
        } else {
            zzfn.zze(zze);
        }
    }


    public boolean zzb() {
        return false;
    }


    @WorkerThread
    public long zzd(final byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        zzs.zzv().zzg();
        final MessageDigest zzE = zzky.zzE();
        if (null != zzE) {
            return zzky.zzp(zzE.digest(bArr));
        }
        zzs.zzay().zzd().zza("Failed to get MD5");
        return 0;
    }


    public Bundle zzf(final Map map, final boolean z) {
        final Bundle bundle = new Bundle();
        for (final String str : map.keySet()) {
            final Object obj = map.get(str);
            if (null == obj) {
                bundle.putString(str, null);
            } else if (obj instanceof Long) {
                bundle.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof Double) {
                bundle.putDouble(str, ((Double) obj).doubleValue());
            } else if (!(obj instanceof ArrayList arrayList3)) {
                bundle.putString(str, obj.toString());
            } else if (z) {
                zzoz.zzc();
                if (zzs.zzf().zzs(null, zzdw.zzam)) {
                    final ArrayList arrayList2 = new ArrayList();
                    final int size = arrayList3.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        arrayList2.add(this.zzf((Map) arrayList3.get(i2), false));
                    }
                    bundle.putParcelableArray(str, (Parcelable[]) arrayList2.toArray(new Parcelable[0]));
                } else {
                    final ArrayList arrayList4 = new ArrayList();
                    final int size2 = arrayList3.size();
                    for (int i3 = 0; i3 < size2; i3++) {
                        arrayList4.add(this.zzf((Map) arrayList3.get(i3), false));
                    }
                    bundle.putParcelableArrayList(str, arrayList4);
                }
            }
        }
        return bundle;
    }


    /*  WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r4.zzs.zzay().zzd().zza("Failed to load parcelable from buffer");
     */
    /*  WARNING: Code restructure failed: missing block: B:12:0x002e, code lost:
        return null;
     */
    /*  WARNING: Code restructure failed: missing block: B:13:0x002f, code lost:
        r1.recycle();
     */
    /*  WARNING: Code restructure failed: missing block: B:14:0x0032, code lost:
        throw r5;
     */
    /*  WARNING: Code restructure failed: missing block: B:8:0x001a, code lost:
        r5 = move-exception;
     */
    /*  WARNING: Failed to process nested try/catch */
    /*  WARNING: Missing exception handler attribute for start block: B:9:0x001c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.os.Parcelable zzh(final byte[] r5, final android.os.Parcelable.Creator r6) {
        /*
            r4 = this;
            r0 = 0
            if (r5 != 0) goto L_0x0004
            return r0
        L_0x0004:
            android.os.Parcel r1 = android.os.Parcel.obtain()
            int r2 = r5.length     // Catch:{ ParseException -> 0x001c }
            r3 = 0
            r1.unmarshall(r5, r3, r2)     // Catch:{ ParseException -> 0x001c }
            r1.setDataPosition(r3)     // Catch:{ ParseException -> 0x001c }
            java.lang.Object r5 = r6.createFromParcel(r1)     // Catch:{ ParseException -> 0x001c }
            android.os.Parcelable r5 = (android.os.Parcelable) r5     // Catch:{ ParseException -> 0x001c }
            r1.recycle()
            return r5
        L_0x001a:
            r5 = move-exception
            goto L_0x002f
        L_0x001c:
            com.google.android.gms.measurement.internal.zzft r5 = r4.zzs     // Catch:{ all -> 0x001a }
            com.google.android.gms.measurement.internal.zzej r5 = r5.zzay()     // Catch:{ all -> 0x001a }
            com.google.android.gms.measurement.internal.zzeh r5 = r5.zzd()     // Catch:{ all -> 0x001a }
            java.lang.String r6 = "Failed to load parcelable from buffer"
            r5.zza(r6)     // Catch:{ all -> 0x001a }
            r1.recycle()
            return r0
        L_0x002f:
            r1.recycle()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkt.zzh(byte[], android.os.ParcelableCreator):android.os.Parcelable");
    }


    public zzau zzi(final zzaa zzaa) {
        final String str;
        final Object obj;
        final Bundle zzf = this.zzf(zzaa.zze(), true);
        if (!zzf.containsKey("_o") || null == (obj = zzf.get("_o"))) {
            str = "app";
        } else {
            str = obj.toString();
        }
        final String str2 = str;
        String zzb = zzgq.zzb(zzaa.zzd());
        if (null == zzb) {
            zzb = zzaa.zzd();
        }
        return new zzau(zzb, new zzas(zzf), str2, zzaa.zza());
    }


    public zzfo zzj(final zzap zzap) {
        final zzfn zze = zzfo.zze();
        zze.zzl(zzap.zze);
        final zzar zzar = new zzar(zzap.zzf);
        while (zzar.hasNext()) {
            final String zza = zzar.next();
            final zzfr zze2 = zzfs.zze();
            zze2.zzj(zza);
            final Object zzf = zzap.zzf.zzf(zza);
            Preconditions.checkNotNull(zzf);
            this.zzt(zze2, zzf);
            zze.zze(zze2);
        }
        return (zzfo) zze.zzay();
    }


    public String zzm(final zzfw zzfw) {
        if (null == zzfw) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("\nbatch {\n");
        for (final zzfy zzfy : zzfw.zzd()) {
            if (null != zzfy) {
                zzkt.zzF(sb, 1);
                sb.append("bundle {\n");
                if (zzfy.zzbf()) {
                    zzkt.zzI(sb, 1, "protocol_version", Integer.valueOf(zzfy.zzd()));
                }
                zzkt.zzI(sb, 1, "platform", zzfy.zzJ());
                if (zzfy.zzbb()) {
                    zzkt.zzI(sb, 1, "gmp_version", Long.valueOf(zzfy.zzn()));
                }
                if (zzfy.zzbl()) {
                    zzkt.zzI(sb, 1, "uploading_gmp_version", Long.valueOf(zzfy.zzs()));
                }
                if (zzfy.zzaZ()) {
                    zzkt.zzI(sb, 1, "dynamite_version", Long.valueOf(zzfy.zzk()));
                }
                if (zzfy.zzaW()) {
                    zzkt.zzI(sb, 1, "config_version", Long.valueOf(zzfy.zzi()));
                }
                zzkt.zzI(sb, 1, "gmp_app_id", zzfy.zzG());
                zzkt.zzI(sb, 1, "admob_app_id", zzfy.zzx());
                zzkt.zzI(sb, 1, "app_id", zzfy.zzy());
                zzkt.zzI(sb, 1, "app_version", zzfy.zzB());
                if (zzfy.zzaU()) {
                    zzkt.zzI(sb, 1, "app_version_major", Integer.valueOf(zzfy.zza()));
                }
                zzkt.zzI(sb, 1, "firebase_instance_id", zzfy.zzF());
                if (zzfy.zzaY()) {
                    zzkt.zzI(sb, 1, "dev_cert_hash", Long.valueOf(zzfy.zzj()));
                }
                zzkt.zzI(sb, 1, "app_store", zzfy.zzA());
                if (zzfy.zzbk()) {
                    zzkt.zzI(sb, 1, "upload_timestamp_millis", Long.valueOf(zzfy.zzr()));
                }
                if (zzfy.zzbi()) {
                    zzkt.zzI(sb, 1, "start_timestamp_millis", Long.valueOf(zzfy.zzq()));
                }
                if (zzfy.zzba()) {
                    zzkt.zzI(sb, 1, "end_timestamp_millis", Long.valueOf(zzfy.zzm()));
                }
                if (zzfy.zzbe()) {
                    zzkt.zzI(sb, 1, "previous_bundle_start_timestamp_millis", Long.valueOf(zzfy.zzp()));
                }
                if (zzfy.zzbd()) {
                    zzkt.zzI(sb, 1, "previous_bundle_end_timestamp_millis", Long.valueOf(zzfy.zzo()));
                }
                zzkt.zzI(sb, 1, "app_instance_id", zzfy.zzz());
                zzkt.zzI(sb, 1, "resettable_device_id", zzfy.zzK());
                zzkt.zzI(sb, 1, "ds_id", zzfy.zzE());
                if (zzfy.zzbc()) {
                    zzkt.zzI(sb, 1, "limited_ad_tracking", Boolean.valueOf(zzfy.zzaR()));
                }
                zzkt.zzI(sb, 1, "os_version", zzfy.zzI());
                zzkt.zzI(sb, 1, "device_model", zzfy.zzD());
                zzkt.zzI(sb, 1, "user_default_language", zzfy.zzL());
                if (zzfy.zzbj()) {
                    zzkt.zzI(sb, 1, "time_zone_offset_minutes", Integer.valueOf(zzfy.zzf()));
                }
                if (zzfy.zzaV()) {
                    zzkt.zzI(sb, 1, "bundle_sequential_index", Integer.valueOf(zzfy.zzb()));
                }
                if (zzfy.zzbh()) {
                    zzkt.zzI(sb, 1, "service_upload", Boolean.valueOf(zzfy.zzaS()));
                }
                zzkt.zzI(sb, 1, "health_monitor", zzfy.zzH());
                if (!zzs.zzf().zzs(null, zzdw.zzah) && zzfy.zzaT() && 0 != zzfy.zzh()) {
                    zzkt.zzI(sb, 1, "android_id", Long.valueOf(zzfy.zzh()));
                }
                if (zzfy.zzbg()) {
                    zzkt.zzI(sb, 1, "retry_counter", Integer.valueOf(zzfy.zze()));
                }
                if (zzfy.zzaX()) {
                    zzkt.zzI(sb, 1, "consent_signals", zzfy.zzC());
                }
                final List<zzgh> zzO = zzfy.zzO();
                if (null != zzO) {
                    for (final zzgh zzgh : zzO) {
                        if (null != zzgh) {
                            zzkt.zzF(sb, 2);
                            sb.append("user_property {\n");
                            zzkt.zzI(sb, 2, "set_timestamp_millis", zzgh.zzs() ? Long.valueOf(zzgh.zzc()) : null);
                            zzkt.zzI(sb, 2, "name", zzs.zzj().zzf(zzgh.zzf()));
                            zzkt.zzI(sb, 2, "string_value", zzgh.zzg());
                            zzkt.zzI(sb, 2, "int_value", zzgh.zzr() ? Long.valueOf(zzgh.zzb()) : null);
                            zzkt.zzI(sb, 2, "double_value", zzgh.zzq() ? Double.valueOf(zzgh.zza()) : null);
                            zzkt.zzF(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                final List<zzfk> zzM = zzfy.zzM();
                if (null != zzM) {
                    for (final zzfk zzfk : zzM) {
                        if (null != zzfk) {
                            zzkt.zzF(sb, 2);
                            sb.append("audience_membership {\n");
                            if (zzfk.zzk()) {
                                zzkt.zzI(sb, 2, "audience_id", Integer.valueOf(zzfk.zza()));
                            }
                            if (zzfk.zzm()) {
                                zzkt.zzI(sb, 2, "new_audience", Boolean.valueOf(zzfk.zzj()));
                            }
                            zzkt.zzH(sb, 2, "current_data", zzfk.zzd());
                            if (zzfk.zzn()) {
                                zzkt.zzH(sb, 2, "previous_data", zzfk.zze());
                            }
                            zzkt.zzF(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                final List<zzfo> zzN = zzfy.zzN();
                if (null != zzN) {
                    for (final zzfo zzfo : zzN) {
                        if (null != zzfo) {
                            zzkt.zzF(sb, 2);
                            sb.append("event {\n");
                            zzkt.zzI(sb, 2, "name", zzs.zzj().zzd(zzfo.zzh()));
                            if (zzfo.zzu()) {
                                zzkt.zzI(sb, 2, "timestamp_millis", Long.valueOf(zzfo.zzd()));
                            }
                            if (zzfo.zzt()) {
                                zzkt.zzI(sb, 2, "previous_timestamp_millis", Long.valueOf(zzfo.zzc()));
                            }
                            if (zzfo.zzs()) {
                                zzkt.zzI(sb, 2, "count", Integer.valueOf(zzfo.zza()));
                            }
                            if (0 != zzfo.zzb()) {
                                this.zzD(sb, 2, zzfo.zzi());
                            }
                            zzkt.zzF(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                zzkt.zzF(sb, 1);
                sb.append("}\n");
            }
        }
        sb.append("}\n");
        return sb.toString();
    }


    public String zzo(final zzej zzej) {
        if (null == zzej) {
            return "null";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("\nevent_filter {\n");
        if (zzej.zzp()) {
            zzkt.zzI(sb, 0, "filter_id", Integer.valueOf(zzej.zzb()));
        }
        zzkt.zzI(sb, 0, "event_name", zzs.zzj().zzd(zzej.zzg()));
        final String zzG = zzkt.zzG(zzej.zzk(), zzej.zzm(), zzej.zzn());
        if (!zzG.isEmpty()) {
            zzkt.zzI(sb, 0, "filter_type", zzG);
        }
        if (zzej.zzo()) {
            zzkt.zzJ(sb, 1, "event_count_filter", zzej.zzf());
        }
        if (0 < zzej.zza()) {
            sb.append("  filters {\n");
            for (final zzel zzE : zzej.zzh()) {
                this.zzE(sb, 2, zzE);
            }
        }
        zzkt.zzF(sb, 1);
        sb.append("}\n}\n");
        return sb.toString();
    }


    public String zzp(final zzes zzes) {
        if (null == zzes) {
            return "null";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("\nproperty_filter {\n");
        if (zzes.zzj()) {
            zzkt.zzI(sb, 0, "filter_id", Integer.valueOf(zzes.zza()));
        }
        zzkt.zzI(sb, 0, "property_name", zzs.zzj().zzf(zzes.zze()));
        final String zzG = zzkt.zzG(zzes.zzg(), zzes.zzh(), zzes.zzi());
        if (!zzG.isEmpty()) {
            zzkt.zzI(sb, 0, "filter_type", zzG);
        }
        this.zzE(sb, 1, zzes.zzb());
        sb.append("}\n");
        return sb.toString();
    }


    public List zzq(final List list, final List list2) {
        int i2;
        final ArrayList arrayList = new ArrayList(list);
        final Iterator it = list2.iterator();
        while (it.hasNext()) {
            final Integer num = (Integer) it.next();
            if (0 > num.intValue()) {
                zzs.zzay().zzk().zzb("Ignoring negative bit index to be cleared", num);
            } else {
                final int intValue = num.intValue() / 64;
                if (intValue >= arrayList.size()) {
                    zzs.zzay().zzk().zzc("Ignoring bit index greater than bitSet size", num, Integer.valueOf(arrayList.size()));
                } else {
                    arrayList.set(intValue, Long.valueOf(((Long) arrayList.get(intValue)).longValue() & (~(1L << (num.intValue() % 64)))));
                }
            }
        }
        int size = arrayList.size();
        int size2 = arrayList.size() - 1;
        while (true) {
            final int i3 = size2;
            i2 = size;
            size = i3;
            if (0 <= size && 0 == ((Long) arrayList.get(size)).longValue()) {
                size2 = size - 1;
            }
        }
        return arrayList.subList(0, i2);
    }


    public Map zzs(final Bundle bundle, final boolean z) {
        final HashMap hashMap = new HashMap();
        for (final String next : bundle.keySet()) {
            final Object obj = bundle.get(next);
            zzoz.zzc();
            if (!zzs.zzf().zzs(null, zzdw.zzam) ? (obj instanceof Bundle[]) || (obj instanceof ArrayList) || (obj instanceof Bundle) : (obj instanceof Parcelable[]) || (obj instanceof ArrayList) || (obj instanceof Bundle)) {
                if (z) {
                    final ArrayList arrayList = new ArrayList();
                    if (obj instanceof Parcelable[]) {
                        for (final Parcelable parcelable : (Parcelable[]) obj) {
                            if (parcelable instanceof Bundle) {
                                arrayList.add(this.zzs((Bundle) parcelable, false));
                            }
                        }
                    } else if (obj instanceof ArrayList arrayList2) {
                        final int size = arrayList2.size();
                        for (int i2 = 0; i2 < size; i2++) {
                            final Object obj2 = arrayList2.get(i2);
                            if (obj2 instanceof Bundle) {
                                arrayList.add(this.zzs((Bundle) obj2, false));
                            }
                        }
                    } else if (obj instanceof Bundle) {
                        arrayList.add(this.zzs((Bundle) obj, false));
                    }
                    hashMap.put(next, arrayList);
                }
            } else if (null != obj) {
                hashMap.put(next, obj);
            }
        }
        return hashMap;
    }


    public void zzt(final zzfr zzfr, final Object obj) {
        Preconditions.checkNotNull(obj);
        zzfr.zzg();
        zzfr.zze();
        zzfr.zzd();
        zzfr.zzf();
        if (obj instanceof String) {
            zzfr.zzk((String) obj);
        } else if (obj instanceof Long) {
            zzfr.zzi(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            zzfr.zzh(((Double) obj).doubleValue());
        } else if (obj instanceof Bundle[]) {
            final ArrayList arrayList = new ArrayList();
            for (final Bundle bundle : (Bundle[]) obj) {
                if (null != bundle) {
                    final zzfr zze = zzfs.zze();
                    for (final String next : bundle.keySet()) {
                        final zzfr zze2 = zzfs.zze();
                        zze2.zzj(next);
                        final Object obj2 = bundle.get(next);
                        if (obj2 instanceof Long) {
                            zze2.zzi(((Long) obj2).longValue());
                        } else if (obj2 instanceof String) {
                            zze2.zzk((String) obj2);
                        } else if (obj2 instanceof Double) {
                            zze2.zzh(((Double) obj2).doubleValue());
                        }
                        zze.zzc(zze2);
                    }
                    if (0 < zze.zza()) {
                        arrayList.add(zze.zzay());
                    }
                }
            }
            zzfr.zzb(arrayList);
        } else {
            zzs.zzay().zzd().zzb("Ignoring invalid (type) event param value", obj);
        }
    }


    public void zzu(final zzgg zzgg, final Object obj) {
        Preconditions.checkNotNull(obj);
        zzgg.zzc();
        zzgg.zzb();
        zzgg.zza();
        if (obj instanceof String) {
            zzgg.zzh((String) obj);
        } else if (obj instanceof Long) {
            zzgg.zze(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            zzgg.zzd(((Double) obj).doubleValue());
        } else {
            zzs.zzay().zzd().zzb("Ignoring invalid (type) user attribute value", obj);
        }
    }


    public boolean zzw(final long j2, final long j3) {
        return 0 == j2 || 0 >= j3 || Math.abs(zzs.zzav().currentTimeMillis() - j2) > j3;
    }


    public byte[] zzy(final byte[] bArr) throws IOException {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (final IOException e2) {
            zzs.zzay().zzd().zzb("Failed to gzip content", e2);
            throw e2;
        }
    }
}
