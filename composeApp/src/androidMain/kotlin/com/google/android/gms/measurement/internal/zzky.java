package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.*;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.annotation.WorkerThread;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzcf;
import com.google.firebase.analytics.FirebaseAnalytics;

import javax.security.auth.x500.X500Principal;
import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzky extends zzgn {
    public static final int r8clinit = 0;
    private static final String[] zzb = {"firebase_", "google_", "ga_"};
    private static final String[] zzc = {"_err"};
    private SecureRandom zzd;
    private final AtomicLong zze = new AtomicLong(0);
    private int zzf;
    private Integer zzg;

    zzky(zzft zzft) {
        super(zzft);
    }

    static MessageDigest zzE() {
        int i2 = 0;
        while (2 > i2) {
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                if (null != instance) {
                    return instance;
                }
                i2++;
            } catch (NoSuchAlgorithmException unused) {
            }
        }
        return null;
    }

    public static ArrayList zzG(List list) {
        if (null == list) {
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            zzab zzab = (zzab) it.next();
            Bundle bundle = new Bundle();
            bundle.putString("app_id", zzab.zza);
            bundle.putString(FirebaseAnalytics.Param.ORIGIN, zzab.zzb);
            bundle.putLong("creation_timestamp", zzab.zzd);
            bundle.putString("name", zzab.zzc.zzb);
            zzgp.zzb(bundle, Preconditions.checkNotNull(zzab.zzc.zza()));
            bundle.putBoolean("active", zzab.zze);
            String str = zzab.zzf;
            if (null != str) {
                bundle.putString("trigger_event_name", str);
            }
            zzau zzau = zzab.zzg;
            if (null != zzau) {
                bundle.putString("timed_out_event_name", zzau.zza);
                zzas zzas = zzau.zzb;
                if (null != zzas) {
                    bundle.putBundle("timed_out_event_params", zzas.zzc());
                }
            }
            bundle.putLong("trigger_timeout", zzab.zzh);
            zzau zzau2 = zzab.zzi;
            if (null != zzau2) {
                bundle.putString("triggered_event_name", zzau2.zza);
                zzas zzas2 = zzau2.zzb;
                if (null != zzas2) {
                    bundle.putBundle("triggered_event_params", zzas2.zzc());
                }
            }
            bundle.putLong("triggered_timestamp", zzab.zzc.zzc);
            bundle.putLong("time_to_live", zzab.zzj);
            zzau zzau3 = zzab.zzk;
            if (null != zzau3) {
                bundle.putString("expired_event_name", zzau3.zza);
                zzas zzas3 = zzau3.zzb;
                if (null != zzas3) {
                    bundle.putBundle("expired_event_params", zzas3.zzc());
                }
            }
            arrayList.add(bundle);
        }
        return arrayList;
    }

    @WorkerThread
    public static void zzJ(zzif zzif, Bundle bundle, boolean z) {
        if (!(null == bundle || null == zzif)) {
            if (!bundle.containsKey("_sc") || z) {
                String str = zzif.zza;
                if (null != str) {
                    bundle.putString("_sn", str);
                } else {
                    bundle.remove("_sn");
                }
                String str2 = zzif.zzb;
                if (null != str2) {
                    bundle.putString("_sc", str2);
                } else {
                    bundle.remove("_sc");
                }
                bundle.putLong("_si", zzif.zzc);
                return;
            }
            z = false;
        }
        if (null != bundle && null == zzif && z) {
            bundle.remove("_sn");
            bundle.remove("_sc");
            bundle.remove("_si");
        }
    }

    static boolean zzag(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("_");
    }

    static boolean zzah(String str) {
        Preconditions.checkNotEmpty(str);
        return '_' != str.charAt(0) || "_ep".equals(str);
    }

    static boolean zzai(Context context) {
        ActivityInfo receiverInfo;
        Preconditions.checkNotNull(context);
        try {
            PackageManager packageManager = context.getPackageManager();
            return null != packageManager && null != (receiverInfo = packageManager.getReceiverInfo(new ComponentName(context, "com.google.android.gms.measurement.AppMeasurementReceiver"), 0)) && receiverInfo.enabled;
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    static boolean zzaj(Context context, boolean z) {
        Preconditions.checkNotNull(context);
        return zzat(context, "com.google.android.gms.measurement.AppMeasurementJobService");
    }

    static boolean zzak(String str, String str2) {
        if (null == str && null == str2) {
            return true;
        }
        if (null == str) {
            return false;
        }
        return str.equals(str2);
    }

    public static boolean zzal(String str) {
        return !zzc[0].equals(str);
    }

    static boolean zzao(Bundle bundle, int i2) {
        if (0 != bundle.getLong("_err")) {
            return false;
        }
        bundle.putLong("_err", i2);
        return true;
    }

    @VisibleForTesting
    static boolean zzap(String str) {
        Preconditions.checkNotNull(str);
        return str.matches("^(1:\\d+:android:[a-f0-9]+|ca-app-pub-.*)");
    }

    private int zzaq(String str) {
        if ("_ldl".equals(str)) {
            this.zzs.zzf();
            return 2048;
        } else if ("_id".equals(str)) {
            this.zzs.zzf();
            return 256;
        } else if ("_lgclid".equals(str)) {
            this.zzs.zzf();
            return 100;
        } else {
            this.zzs.zzf();
            return 36;
        }
    }

    private Object zzar(int i2, Object obj, boolean z, boolean z2) {
        if (null == obj) {
            return null;
        }
        if ((obj instanceof Long) || (obj instanceof Double)) {
            return obj;
        }
        if (obj instanceof Integer) {
            return Long.valueOf(((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return Long.valueOf(((Byte) obj).byteValue());
        }
        if (obj instanceof Short) {
            return Long.valueOf(((Short) obj).shortValue());
        }
        if (obj instanceof Boolean) {
            return Long.valueOf(!((Boolean) obj).booleanValue() ? 0 : 1);
        } else if (obj instanceof Float) {
            return Double.valueOf(((Float) obj).doubleValue());
        } else {
            if ((obj instanceof String) || (obj instanceof Character) || (obj instanceof CharSequence)) {
                return zzC(obj.toString(), i2, z);
            }
            if (!z2 || (!(obj instanceof Bundle[]) && !(obj instanceof Parcelable[]))) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Parcelable parcelable : (Parcelable[]) obj) {
                if (parcelable instanceof Bundle) {
                    Bundle zzt = zzt((Bundle) parcelable);
                    if (!zzt.isEmpty()) {
                        arrayList.add(zzt);
                    }
                }
            }
            return arrayList.toArray(new Bundle[arrayList.size()]);
        }
    }

    private static boolean zzas(String str, String[] strArr) {
        Preconditions.checkNotNull(strArr);
        for (String zzak : strArr) {
            if (zzak(str, zzak)) {
                return true;
            }
        }
        return false;
    }

    private static boolean zzat(Context context, String str) {
        ServiceInfo serviceInfo;
        try {
            PackageManager packageManager = context.getPackageManager();
            return null != packageManager && null != (serviceInfo = packageManager.getServiceInfo(new ComponentName(context, str), 0)) && serviceInfo.enabled;
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    @VisibleForTesting
    static long zzp(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        int length = bArr.length;
        int i2 = 0;
        Preconditions.checkState(0 < length);
        int i3 = length - 1;
        long j2 = 0;
        while (0 <= i3 && i3 >= bArr.length - 8) {
            j2 += (((long) bArr[i3]) & 255) << i2;
            i2 += 8;
            i3--;
        }
        return j2;
    }


    public Object zzA(String str, Object obj) {
        int i2 = 256;
        if ("_ev".equals(str)) {
            this.zzs.zzf();
            return zzar(256, obj, true, true);
        }
        if (zzag(str)) {
            this.zzs.zzf();
        } else {
            this.zzs.zzf();
            i2 = 100;
        }
        return zzar(i2, obj, false, true);
    }


    public Object zzB(String str, Object obj) {
        if ("_ldl".equals(str)) {
            return zzar(zzaq(str), obj, true, false);
        }
        return zzar(zzaq(str), obj, false, false);
    }

    public String zzC(String str, int i2, boolean z) {
        if (null == str) {
            return null;
        }
        if (str.codePointCount(0, str.length()) <= i2) {
            return str;
        }
        if (z) {
            return str.substring(0, str.offsetByCodePoints(0, i2)) + "...";
        }
        return null;
    }

    public URL zzD(long j2, String str, String str2, long j3) {
        try {
            Preconditions.checkNotEmpty(str2);
            Preconditions.checkNotEmpty(str);
            String format = String.format("https://www.googleadservices.com/pagead/conversion/app/deeplink?id_type=adid&sdk_version=%s&rdid=%s&bundleid=%s&retry=%s", String.format("v%s.%s", 60000L, Integer.valueOf(zzm())), str2, str, Long.valueOf(j3));
            if (str.equals(this.zzs.zzf().zzm())) {
                format = format + "&ddl_test=1";
            }
            return new URL(format);
        } catch (MalformedURLException e2) {
            e = e2;
            this.zzs.zzay().zzd().zzb("Failed to create BOW URL for Deferred Deep Link. exception", e.getMessage());
            return null;
        } catch (IllegalArgumentException e3) {
            e = e3;
            this.zzs.zzay().zzd().zzb("Failed to create BOW URL for Deferred Deep Link. exception", e.getMessage());
            return null;
        }
    }


    @WorkerThread
    public SecureRandom zzF() {
        zzg();
        if (null == zzd) {
            this.zzd = new SecureRandom();
        }
        return this.zzd;
    }


    @WorkerThread
    public void zzH(Bundle bundle, long j2) {
        long j3 = bundle.getLong("_et");
        if (0 != j3) {
            this.zzs.zzay().zzk().zzb("Params already contained engagement", Long.valueOf(j3));
        } else {
            j3 = 0;
        }
        bundle.putLong("_et", j2 + j3);
    }


    public void zzI(Bundle bundle, int i2, String str, String str2, Object obj) {
        if (zzao(bundle, i2)) {
            this.zzs.zzf();
            bundle.putString("_ev", zzC(str, 40, true));
            if (null != obj) {
                Preconditions.checkNotNull(bundle);
                if ((obj instanceof String) || (obj instanceof CharSequence)) {
                    bundle.putLong("_el", obj.toString().length());
                }
            }
        }
    }


    public void zzK(Bundle bundle, Bundle bundle2) {
        if (null != bundle2) {
            for (String next : bundle2.keySet()) {
                if (!bundle.containsKey(next)) {
                    this.zzs.zzv().zzN(bundle, next, bundle2.get(next));
                }
            }
        }
    }


    public void zzL(zzek zzek, int i2) {
        int i3 = 0;
        for (String str : new TreeSet(zzek.zzd.keySet())) {
            if (zzah(str) && (i3 = i3 + 1) > i2) {
                final String sb = "Event can't contain more than " +
                        i2 +
                        " params";
                this.zzs.zzay().zze().zzc(sb, this.zzs.zzj().zzd(zzek.zza), this.zzs.zzj().zzb(zzek.zzd));
                zzao(zzek.zzd, 5);
                zzek.zzd.remove(str);
            }
        }
    }


    public void zzM(zzkx zzkx, String str, int i2, String str2, String str3, int i3) {
        Bundle bundle = new Bundle();
        zzao(bundle, i2);
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            bundle.putString(str2, str3);
        }
        if (6 == i2 || 7 == i2 || 2 == i2) {
            bundle.putLong("_el", i3);
        }
        zzkx.zza(str, "_err", bundle);
    }


    public void zzN(Bundle bundle, String str, Object obj) {
        if (null != bundle) {
            if (obj instanceof Long) {
                bundle.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof String) {
                bundle.putString(str, String.valueOf(obj));
            } else if (obj instanceof Double) {
                bundle.putDouble(str, ((Double) obj).doubleValue());
            } else if (obj instanceof Bundle[]) {
                bundle.putParcelableArray(str, (Bundle[]) obj);
            } else if (null != str) {
                this.zzs.zzay().zzl().zzc("Not putting event parameter. Invalid value type. name, type", this.zzs.zzj().zze(str), null != obj ? obj.getClass().getSimpleName() : null);
            }
        }
    }

    public void zzO(zzcf zzcf, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("r", z);
        try {
            zzcf.zzd(bundle);
        } catch (RemoteException e2) {
            this.zzs.zzay().zzk().zzb("Error returning boolean value to wrapper", e2);
        }
    }

    public void zzP(zzcf zzcf, ArrayList arrayList) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("r", arrayList);
        try {
            zzcf.zzd(bundle);
        } catch (RemoteException e2) {
            this.zzs.zzay().zzk().zzb("Error returning bundle list to wrapper", e2);
        }
    }

    public void zzQ(zzcf zzcf, Bundle bundle) {
        try {
            zzcf.zzd(bundle);
        } catch (RemoteException e2) {
            this.zzs.zzay().zzk().zzb("Error returning bundle value to wrapper", e2);
        }
    }

    public void zzR(zzcf zzcf, byte[] bArr) {
        Bundle bundle = new Bundle();
        bundle.putByteArray("r", bArr);
        try {
            zzcf.zzd(bundle);
        } catch (RemoteException e2) {
            this.zzs.zzay().zzk().zzb("Error returning byte array to wrapper", e2);
        }
    }

    public void zzS(zzcf zzcf, int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt("r", i2);
        try {
            zzcf.zzd(bundle);
        } catch (RemoteException e2) {
            this.zzs.zzay().zzk().zzb("Error returning int value to wrapper", e2);
        }
    }

    public void zzT(zzcf zzcf, long j2) {
        Bundle bundle = new Bundle();
        bundle.putLong("r", j2);
        try {
            zzcf.zzd(bundle);
        } catch (RemoteException e2) {
            this.zzs.zzay().zzk().zzb("Error returning long value to wrapper", e2);
        }
    }

    public void zzU(zzcf zzcf, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("r", str);
        try {
            zzcf.zzd(bundle);
        } catch (RemoteException e2) {
            this.zzs.zzay().zzk().zzb("Error returning string value to wrapper", e2);
        }
    }


    public void zzV(String str, String str2, String str3, Bundle bundle, List list, boolean z) {
        int i2;
        String str4;
        int i3;
        String str5 = str2;
        Bundle bundle2 = bundle;
        List list2 = list;
        if (null != bundle2) {
            this.zzs.zzf();
            int i4 = 0;
            for (String str6 : new TreeSet(bundle.keySet())) {
                if (null == list2 || !list2.contains(str6)) {
                    int zzj = !z ? zzj(str6) : 0;
                    if (0 == zzj) {
                        zzj = zzi(str6);
                    }
                    i2 = zzj;
                } else {
                    i2 = 0;
                }
                if (0 != i2) {
                    zzI(bundle, i2, str6, str6, 3 == i2 ? str6 : null);
                    bundle2.remove(str6);
                } else {
                    if (zzae(bundle2.get(str6))) {
                        this.zzs.zzay().zzl().zzd("Nested Bundle parameters are not allowed; discarded. event name, param name, child param name", str5, str3, str6);
                        i3 = 22;
                        str4 = str6;
                    } else {
                        String str7 = str3;
                        str4 = str6;
                        i3 = zza(str, str2, str6, bundle2.get(str6), bundle, list, z, false);
                    }
                    if (0 != i3 && !"_ev".equals(str4)) {
                        zzI(bundle, i3, str4, str4, bundle2.get(str4));
                        bundle2.remove(str4);
                    } else if (zzah(str4) && !zzas(str4, zzgr.zzd) && 0 < (i4 = i4 + 1)) {
                        this.zzs.zzay().zze().zzc("Item cannot contain custom parameters", this.zzs.zzj().zzd(str5), this.zzs.zzj().zzb(bundle2));
                        zzao(bundle2, 23);
                        bundle2.remove(str4);
                    }
                }
            }
        }
    }


    public boolean zzW(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            if (zzap(str)) {
                return true;
            }
            if (this.zzs.zzL()) {
                this.zzs.zzay().zze().zzb("Invalid google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI. provided id", zzej.zzn(str));
            }
            return false;
        } else if (TextUtils.isEmpty(str2)) {
            if (this.zzs.zzL()) {
                this.zzs.zzay().zze().zza("Missing google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI");
            }
            return false;
        } else if (zzap(str2)) {
            return true;
        } else {
            this.zzs.zzay().zze().zzb("Invalid admob_app_id. Analytics disabled.", zzej.zzn(str2));
            return false;
        }
    }


    public boolean zzX(String str, int i2, String str2) {
        if (null == str2) {
            this.zzs.zzay().zze().zzb("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.codePointCount(0, str2.length()) <= i2) {
            return true;
        } else {
            this.zzs.zzay().zze().zzd("Name is too long. Type, maximum supported length, name", str, Integer.valueOf(i2), str2);
            return false;
        }
    }


    public boolean zzY(String str, String[] strArr, String[] strArr2, String str2) {
        if (null == str2) {
            this.zzs.zzay().zze().zzb("Name is required and can't be null. Type", str);
            return false;
        }
        Preconditions.checkNotNull(str2);
        String[] strArr3 = zzb;
        for (int i2 = 0; 3 > i2; i2++) {
            if (str2.startsWith(strArr3[i2])) {
                this.zzs.zzay().zze().zzc("Name starts with reserved prefix. Type, name", str, str2);
                return false;
            }
        }
        if (null == strArr || !zzas(str2, strArr)) {
            return true;
        }
        if (null != strArr2 && zzas(str2, strArr2)) {
            return true;
        }
        this.zzs.zzay().zze().zzc("Name is reserved. Type, name", str, str2);
        return false;
    }


    public boolean zzZ(String str, String str2, int i2, Object obj) {
        if (null != obj && !(obj instanceof Long) && !(obj instanceof Float) && !(obj instanceof Integer) && !(obj instanceof Byte) && !(obj instanceof Short) && !(obj instanceof Boolean) && !(obj instanceof Double)) {
            if (!(obj instanceof String) && !(obj instanceof Character) && !(obj instanceof CharSequence)) {
                return false;
            }
            String obj2 = obj.toString();
            if (obj2.codePointCount(0, obj2.length()) > i2) {
                this.zzs.zzay().zzl().zzd("Value is too long; discarded. Value kind, name, value length", str, str2, Integer.valueOf(obj2.length()));
                return false;
            }
        }
        return true;
    }


    /*  WARNING: Removed duplicated region for block: B:38:0x00c9  */
    /*  WARNING: Removed duplicated region for block: B:39:0x00d1  */
    /*  WARNING: Removed duplicated region for block: B:42:0x00de A[RETURN] */
    /*  WARNING: Removed duplicated region for block: B:43:0x00df  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int zza(java.lang.String r15, java.lang.String r16, java.lang.String r17, java.lang.Object r18, android.os.Bundle r19, java.util.List r20, boolean r21, boolean r22) {
        /*
            r14 = this;
            r7 = r14
            r8 = r17
            r0 = r18
            r1 = r19
            r14.zzg()
            boolean r2 = r14.zzae(r0)
            java.lang.String r3 = "param"
            r4 = 0
            if (r2 == 0) goto L_0x00a9
            if (r22 == 0) goto L_0x00ab
            java.lang.String[] r2 = com.google.android.gms.measurement.internal.zzgr.zzc
            boolean r2 = zzas(r8, r2)
            if (r2 != 0) goto L_0x0020
            r0 = 20
            return r0
        L_0x0020:
            com.google.android.gms.measurement.internal.zzft r2 = r7.zzs
            com.google.android.gms.measurement.internal.zzjm r2 = r2.zzt()
            r2.zzg()
            r2.zza()
            boolean r5 = r2.zzN()
            if (r5 != 0) goto L_0x0033
            goto L_0x0045
        L_0x0033:
            com.google.android.gms.measurement.internal.zzft r2 = r2.zzs
            com.google.android.gms.measurement.internal.zzky r2 = r2.zzv()
            int r2 = r2.zzm()
            r5 = 200900(0x310c4, float:2.81521E-40)
            if (r2 >= r5) goto L_0x0045
            r0 = 25
            return r0
        L_0x0045:
            com.google.android.gms.measurement.internal.zzft r2 = r7.zzs
            r2.zzf()
            boolean r2 = r0 instanceof android.os.Parcelable[]
            if (r2 == 0) goto L_0x0053
            r5 = r0
            android.os.Parcelable[] r5 = (android.os.Parcelable[]) r5
            int r5 = r5.length
            goto L_0x005e
        L_0x0053:
            boolean r5 = r0 instanceof java.util.ArrayList
            if (r5 == 0) goto L_0x00a9
            r5 = r0
            java.util.ArrayList r5 = (java.util.ArrayList) r5
            int r5 = r5.size()
        L_0x005e:
            r6 = 200(0xc8, float:2.8E-43)
            if (r5 <= r6) goto L_0x00a9
            com.google.android.gms.measurement.internal.zzft r9 = r7.zzs
            com.google.android.gms.measurement.internal.zzej r9 = r9.zzay()
            com.google.android.gms.measurement.internal.zzeh r9 = r9.zzl()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.String r10 = "Parameter array is too long; discarded. Value kind, name, array length"
            r9.zzd(r10, r3, r8, r5)
            com.google.android.gms.measurement.internal.zzft r5 = r7.zzs
            r5.zzf()
            r5 = 17
            if (r2 == 0) goto L_0x008f
            r2 = r0
            android.os.Parcelable[] r2 = (android.os.Parcelable[]) r2
            int r9 = r2.length
            if (r9 <= r6) goto L_0x008d
            java.lang.Object[] r2 = java.util.Arrays.copyOf(r2, r6)
            android.os.Parcelable[] r2 = (android.os.Parcelable[]) r2
            r1.putParcelableArray(r8, r2)
        L_0x008d:
            r9 = r5
            goto L_0x00ae
        L_0x008f:
            boolean r2 = r0 instanceof java.util.ArrayList
            if (r2 == 0) goto L_0x008d
            r2 = r0
            java.util.ArrayList r2 = (java.util.ArrayList) r2
            int r9 = r2.size()
            if (r9 <= r6) goto L_0x008d
            java.util.ArrayList r9 = new java.util.ArrayList
            java.util.List r2 = r2.subList(r4, r6)
            r9.<init>(r2)
            r1.putParcelableArrayList(r8, r9)
            goto L_0x008d
        L_0x00a9:
            r9 = r4
            goto L_0x00ae
        L_0x00ab:
            r0 = 21
            return r0
        L_0x00ae:
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzaf r1 = r1.zzf()
            com.google.android.gms.measurement.internal.zzdv r2 = com.google.android.gms.measurement.internal.zzdw.zzR
            r10 = r15
            boolean r1 = r1.zzs(r15, r2)
            if (r1 == 0) goto L_0x00c3
            boolean r1 = zzag(r16)
            if (r1 != 0) goto L_0x00c9
        L_0x00c3:
            boolean r1 = zzag(r17)
            if (r1 == 0) goto L_0x00d1
        L_0x00c9:
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            r1.zzf()
            r1 = 256(0x100, float:3.59E-43)
            goto L_0x00d8
        L_0x00d1:
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            r1.zzf()
            r1 = 100
        L_0x00d8:
            boolean r1 = r14.zzZ(r3, r8, r1, r0)
            if (r1 == 0) goto L_0x00df
            return r9
        L_0x00df:
            if (r22 == 0) goto L_0x0172
            boolean r1 = r0 instanceof android.os.Bundle
            if (r1 == 0) goto L_0x00f7
            r4 = r0
            android.os.Bundle r4 = (android.os.Bundle) r4
            r0 = r14
            r1 = r15
            r2 = r16
            r3 = r17
            r5 = r20
            r6 = r21
            r0.zzV(r1, r2, r3, r4, r5, r6)
            goto L_0x0171
        L_0x00f7:
            boolean r1 = r0 instanceof android.os.Parcelable[]
            if (r1 == 0) goto L_0x012f
            r11 = r0
            android.os.Parcelable[] r11 = (android.os.Parcelable[]) r11
            int r12 = r11.length
            r13 = r4
        L_0x0100:
            if (r13 >= r12) goto L_0x0171
            r0 = r11[r13]
            boolean r1 = r0 instanceof android.os.Bundle
            if (r1 != 0) goto L_0x011c
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzej r1 = r1.zzay()
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzl()
            java.lang.Class r0 = r0.getClass()
            java.lang.String r2 = "All Parcelable[] elements must be of type Bundle. Value type, name"
            r1.zzc(r2, r0, r8)
            goto L_0x0172
        L_0x011c:
            r4 = r0
            android.os.Bundle r4 = (android.os.Bundle) r4
            r0 = r14
            r1 = r15
            r2 = r16
            r3 = r17
            r5 = r20
            r6 = r21
            r0.zzV(r1, r2, r3, r4, r5, r6)
            int r13 = r13 + 1
            goto L_0x0100
        L_0x012f:
            boolean r1 = r0 instanceof java.util.ArrayList
            if (r1 == 0) goto L_0x0172
            r11 = r0
            java.util.ArrayList r11 = (java.util.ArrayList) r11
            int r12 = r11.size()
            r13 = r4
        L_0x013b:
            if (r13 >= r12) goto L_0x0171
            java.lang.Object r0 = r11.get(r13)
            boolean r1 = r0 instanceof android.os.Bundle
            if (r1 != 0) goto L_0x015e
            com.google.android.gms.measurement.internal.zzft r1 = r7.zzs
            com.google.android.gms.measurement.internal.zzej r1 = r1.zzay()
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzl()
            if (r0 == 0) goto L_0x0156
            java.lang.Class r0 = r0.getClass()
            goto L_0x0158
        L_0x0156:
            java.lang.String r0 = "null"
        L_0x0158:
            java.lang.String r2 = "All ArrayList elements must be of type Bundle. Value type, name"
            r1.zzc(r2, r0, r8)
            goto L_0x0172
        L_0x015e:
            r4 = r0
            android.os.Bundle r4 = (android.os.Bundle) r4
            r0 = r14
            r1 = r15
            r2 = r16
            r3 = r17
            r5 = r20
            r6 = r21
            r0.zzV(r1, r2, r3, r4, r5, r6)
            int r13 = r13 + 1
            goto L_0x013b
        L_0x0171:
            return r9
        L_0x0172:
            r0 = 4
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzky.zza(java.lang.String, java.lang.String, java.lang.String, java.lang.Object, android.os.Bundle, java.util.List, boolean, boolean):int");
    }


    @WorkerThread
    public void zzaA() {
        zzg();
        SecureRandom secureRandom = new SecureRandom();
        long nextLong = secureRandom.nextLong();
        if (0 == nextLong) {
            nextLong = secureRandom.nextLong();
            if (0 == nextLong) {
                this.zzs.zzay().zzk().zza("Utils falling back to Random for random id");
            }
        }
        this.zze.set(nextLong);
    }


    public boolean zzaa(String str, String str2) {
        if (null == str2) {
            this.zzs.zzay().zze().zzb("Name is required and can't be null. Type", str);
            return false;
        } else if (0 == str2.length()) {
            this.zzs.zzay().zze().zzb("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (!Character.isLetter(codePointAt)) {
                if (95 == codePointAt) {
                    codePointAt = 95;
                } else {
                    this.zzs.zzay().zze().zzc("Name must start with a letter or _ (underscore). Type, name", str, str2);
                    return false;
                }
            }
            int length = str2.length();
            int charCount = Character.charCount(codePointAt);
            while (charCount < length) {
                int codePointAt2 = str2.codePointAt(charCount);
                if (95 == codePointAt2 || Character.isLetterOrDigit(codePointAt2)) {
                    charCount += Character.charCount(codePointAt2);
                } else {
                    this.zzs.zzay().zze().zzc("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                    return false;
                }
            }
            return true;
        }
    }


    public boolean zzab(String str, String str2) {
        if (null == str2) {
            this.zzs.zzay().zze().zzb("Name is required and can't be null. Type", str);
            return false;
        } else if (0 == str2.length()) {
            this.zzs.zzay().zze().zzb("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (!Character.isLetter(codePointAt)) {
                this.zzs.zzay().zze().zzc("Name must start with a letter. Type, name", str, str2);
                return false;
            }
            int length = str2.length();
            int charCount = Character.charCount(codePointAt);
            while (charCount < length) {
                int codePointAt2 = str2.codePointAt(charCount);
                if (95 == codePointAt2 || Character.isLetterOrDigit(codePointAt2)) {
                    charCount += Character.charCount(codePointAt2);
                } else {
                    this.zzs.zzay().zze().zzc("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                    return false;
                }
            }
            return true;
        }
    }


    @WorkerThread
    public boolean zzac(String str) {
        zzg();
        if (0 == Wrappers.packageManager(zzs.zzau()).checkCallingOrSelfPermission(str)) {
            return true;
        }
        this.zzs.zzay().zzc().zzb("Permission not granted", str);
        return false;
    }


    public boolean zzad(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String zzl = this.zzs.zzf().zzl();
        this.zzs.zzaw();
        return zzl.equals(str);
    }


    public boolean zzae(Object obj) {
        return (obj instanceof Parcelable[]) || (obj instanceof ArrayList) || (obj instanceof Bundle);
    }


    @VisibleForTesting
    public boolean zzaf(Context context, String str) {
        Signature[] signatureArr;
        X500Principal x500Principal = new X500Principal("CN=Android Debug,O=Android,C=US");
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(str, 64);
            if (null == packageInfo || null == (signatureArr = packageInfo.signatures) || 0 >= signatureArr.length) {
                return true;
            }
            return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signatureArr[0].toByteArray()))).getSubjectX500Principal().equals(x500Principal);
        } catch (CertificateException e2) {
            this.zzs.zzay().zzd().zzb("Error obtaining certificate", e2);
            return true;
        } catch (PackageManager.NameNotFoundException e3) {
            this.zzs.zzay().zzd().zzb("Package name not found", e3);
            return true;
        }
    }


    public boolean zzam(String str, String str2, String str3, String str4) {
        boolean isEmpty = TextUtils.isEmpty(str);
        boolean isEmpty2 = TextUtils.isEmpty(str2);
        if (!isEmpty && !isEmpty2) {
            Preconditions.checkNotNull(str);
            return !str.equals(str2);
        } else if (isEmpty && isEmpty2) {
            return (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) ? !TextUtils.isEmpty(str4) : !str3.equals(str4);
        } else {
            if (isEmpty) {
                return TextUtils.isEmpty(str3) || !str3.equals(str4);
            }
            if (TextUtils.isEmpty(str4)) {
                return false;
            }
            return TextUtils.isEmpty(str3) || !str3.equals(str4);
        }
    }


    public byte[] zzan(Parcelable parcelable) {
        if (null == parcelable) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        try {
            parcelable.writeToParcel(obtain, 0);
            return obtain.marshall();
        } finally {
            obtain.recycle();
        }
    }


    public int zzd(String str, Object obj) {
        boolean z;
        if ("_ldl".equals(str)) {
            z = zzZ("user property referrer", str, zzaq(str), obj);
        } else {
            z = zzZ("user property", str, zzaq(str), obj);
        }
        return z ? 0 : 7;
    }


    public boolean zzf() {
        return true;
    }


    public int zzh(String str) {
        if (!zzaa(NotificationCompat.CATEGORY_EVENT, str)) {
            return 2;
        }
        if (!zzY(NotificationCompat.CATEGORY_EVENT, zzgq.zza, zzgq.zzb, str)) {
            return 13;
        }
        this.zzs.zzf();
        if (!zzX(NotificationCompat.CATEGORY_EVENT, 40, str)) {
            return 2;
        }
        return 0;
    }


    public int zzi(String str) {
        if (!zzaa("event param", str)) {
            return 3;
        }
        if (!zzY("event param", null, null, str)) {
            return 14;
        }
        this.zzs.zzf();
        if (!zzX("event param", 40, str)) {
            return 3;
        }
        return 0;
    }


    public int zzj(String str) {
        if (!zzab("event param", str)) {
            return 3;
        }
        if (!zzY("event param", null, null, str)) {
            return 14;
        }
        this.zzs.zzf();
        if (!zzX("event param", 40, str)) {
            return 3;
        }
        return 0;
    }


    public int zzl(String str) {
        if (!zzaa("user property", str)) {
            return 6;
        }
        if (!zzY("user property", zzgs.zza, null, str)) {
            return 15;
        }
        this.zzs.zzf();
        if (!zzX("user property", 24, str)) {
            return 6;
        }
        return 0;
    }

    public int zzm() {
        if (null == zzg) {
            this.zzg = Integer.valueOf(GoogleApiAvailabilityLight.getInstance().getApkVersion(this.zzs.zzau()) / 1000);
        }
        return this.zzg.intValue();
    }

    public int zzo(int i2) {
        return GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(this.zzs.zzau(), GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    public long zzq() {
        long andIncrement;
        long j2;
        if (0 == zze.get()) {
            synchronized (this.zze) {
                long nextLong = new Random(System.nanoTime() ^ this.zzs.zzav().currentTimeMillis()).nextLong();
                int i2 = this.zzf + 1;
                this.zzf = i2;
                j2 = nextLong + i2;
            }
            return j2;
        }
        synchronized (this.zze) {
            this.zze.compareAndSet(-1, 1);
            andIncrement = this.zze.getAndIncrement();
        }
        return andIncrement;
    }

    public long zzr(long j2, long j3) {
        return (j2 + (j3 * 60000)) / 86400000;
    }


    /*  WARNING: Removed duplicated region for block: B:31:0x006f  */
    /*  WARNING: Removed duplicated region for block: B:34:0x007a  */
    /*  WARNING: Removed duplicated region for block: B:37:0x0085  */
    /*  WARNING: Removed duplicated region for block: B:40:0x0090  */
    /*  WARNING: Removed duplicated region for block: B:43:0x009f  */
    /*  WARNING: Removed duplicated region for block: B:46:0x00b0  */
    /*  WARNING: Removed duplicated region for block: B:49:0x00c1  */
    /*  WARNING: Removed duplicated region for block: B:52:0x00d0  */
    /*  WARNING: Removed duplicated region for block: B:55:0x00df  */
    /*  WARNING: Removed duplicated region for block: B:57:0x00e4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.os.Bundle zzs(android.net.Uri r11, boolean r12) {
        /*
            r10 = this;
            r0 = 0
            if (r11 == 0) goto L_0x013b
            boolean r1 = r11.isHierarchical()     // Catch:{ UnsupportedOperationException -> 0x0030 }
            java.lang.String r2 = "dclid"
            java.lang.String r3 = "gclid"
            if (r1 == 0) goto L_0x0036
            java.lang.String r1 = "utm_campaign"
            java.lang.String r1 = r11.getQueryParameter(r1)     // Catch:{ UnsupportedOperationException -> 0x0030 }
            java.lang.String r4 = "utm_source"
            java.lang.String r4 = r11.getQueryParameter(r4)     // Catch:{ UnsupportedOperationException -> 0x0030 }
            java.lang.String r5 = "utm_medium"
            java.lang.String r5 = r11.getQueryParameter(r5)     // Catch:{ UnsupportedOperationException -> 0x0030 }
            java.lang.String r6 = r11.getQueryParameter(r3)     // Catch:{ UnsupportedOperationException -> 0x0030 }
            if (r12 == 0) goto L_0x0033
            java.lang.String r7 = "utm_id"
            java.lang.String r7 = r11.getQueryParameter(r7)     // Catch:{ UnsupportedOperationException -> 0x0030 }
            java.lang.String r8 = r11.getQueryParameter(r2)     // Catch:{ UnsupportedOperationException -> 0x0030 }
            goto L_0x003c
        L_0x0030:
            r11 = move-exception
            goto L_0x012c
        L_0x0033:
            r7 = r0
        L_0x0034:
            r8 = r7
            goto L_0x003c
        L_0x0036:
            r1 = r0
            r4 = r1
            r5 = r4
            r6 = r5
            r7 = r6
            goto L_0x0034
        L_0x003c:
            boolean r9 = android.text.TextUtils.isEmpty(r1)
            if (r9 == 0) goto L_0x0064
            boolean r9 = android.text.TextUtils.isEmpty(r4)
            if (r9 == 0) goto L_0x0064
            boolean r9 = android.text.TextUtils.isEmpty(r5)
            if (r9 == 0) goto L_0x0064
            boolean r9 = android.text.TextUtils.isEmpty(r6)
            if (r9 == 0) goto L_0x0064
            if (r12 == 0) goto L_0x0063
            boolean r9 = android.text.TextUtils.isEmpty(r7)
            if (r9 == 0) goto L_0x0064
            boolean r9 = android.text.TextUtils.isEmpty(r8)
            if (r9 != 0) goto L_0x0063
            goto L_0x0064
        L_0x0063:
            return r0
        L_0x0064:
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            boolean r9 = android.text.TextUtils.isEmpty(r1)
            if (r9 != 0) goto L_0x0074
            java.lang.String r9 = "campaign"
            r0.putString(r9, r1)
        L_0x0074:
            boolean r1 = android.text.TextUtils.isEmpty(r4)
            if (r1 != 0) goto L_0x007f
            java.lang.String r1 = "source"
            r0.putString(r1, r4)
        L_0x007f:
            boolean r1 = android.text.TextUtils.isEmpty(r5)
            if (r1 != 0) goto L_0x008a
            java.lang.String r1 = "medium"
            r0.putString(r1, r5)
        L_0x008a:
            boolean r1 = android.text.TextUtils.isEmpty(r6)
            if (r1 != 0) goto L_0x0093
            r0.putString(r3, r6)
        L_0x0093:
            java.lang.String r1 = "utm_term"
            java.lang.String r1 = r11.getQueryParameter(r1)
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            if (r3 != 0) goto L_0x00a4
            java.lang.String r3 = "term"
            r0.putString(r3, r1)
        L_0x00a4:
            java.lang.String r1 = "utm_content"
            java.lang.String r1 = r11.getQueryParameter(r1)
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            if (r3 != 0) goto L_0x00b5
            java.lang.String r3 = "content"
            r0.putString(r3, r1)
        L_0x00b5:
            java.lang.String r1 = "aclid"
            java.lang.String r3 = r11.getQueryParameter(r1)
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 != 0) goto L_0x00c4
            r0.putString(r1, r3)
        L_0x00c4:
            java.lang.String r1 = "cp1"
            java.lang.String r3 = r11.getQueryParameter(r1)
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 != 0) goto L_0x00d3
            r0.putString(r1, r3)
        L_0x00d3:
            java.lang.String r1 = "anid"
            java.lang.String r3 = r11.getQueryParameter(r1)
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 != 0) goto L_0x00e2
            r0.putString(r1, r3)
        L_0x00e2:
            if (r12 == 0) goto L_0x012b
            boolean r12 = android.text.TextUtils.isEmpty(r7)
            if (r12 != 0) goto L_0x00ef
            java.lang.String r12 = "campaign_id"
            r0.putString(r12, r7)
        L_0x00ef:
            boolean r12 = android.text.TextUtils.isEmpty(r8)
            if (r12 != 0) goto L_0x00f8
            r0.putString(r2, r8)
        L_0x00f8:
            java.lang.String r12 = "utm_source_platform"
            java.lang.String r12 = r11.getQueryParameter(r12)
            boolean r1 = android.text.TextUtils.isEmpty(r12)
            if (r1 != 0) goto L_0x0109
            java.lang.String r1 = "source_platform"
            r0.putString(r1, r12)
        L_0x0109:
            java.lang.String r12 = "utm_creative_format"
            java.lang.String r12 = r11.getQueryParameter(r12)
            boolean r1 = android.text.TextUtils.isEmpty(r12)
            if (r1 != 0) goto L_0x011a
            java.lang.String r1 = "creative_format"
            r0.putString(r1, r12)
        L_0x011a:
            java.lang.String r12 = "utm_marketing_tactic"
            java.lang.String r11 = r11.getQueryParameter(r12)
            boolean r12 = android.text.TextUtils.isEmpty(r11)
            if (r12 != 0) goto L_0x012b
            java.lang.String r12 = "marketing_tactic"
            r0.putString(r12, r11)
        L_0x012b:
            return r0
        L_0x012c:
            com.google.android.gms.measurement.internal.zzft r12 = r10.zzs
            com.google.android.gms.measurement.internal.zzej r12 = r12.zzay()
            com.google.android.gms.measurement.internal.zzeh r12 = r12.zzk()
            java.lang.String r1 = "Install referrer url isn't a hierarchical URI"
            r12.zzb(r1, r11)
        L_0x013b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzky.zzs(android.net.Uri, boolean):android.os.Bundle");
    }


    public Bundle zzt(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        if (null != bundle) {
            for (String next : bundle.keySet()) {
                Object zzA = zzA(next, bundle.get(next));
                if (null == zzA) {
                    this.zzs.zzay().zzl().zzb("Param value can't be null", this.zzs.zzj().zze(next));
                } else {
                    zzN(bundle2, next, zzA);
                }
            }
        }
        return bundle2;
    }

    /*  DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.lang.Object} */
    /*  DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: java.lang.String} */

    /*  WARNING: Multi-variable type inference failed */
    /*  WARNING: Removed duplicated region for block: B:36:0x00c4  */
    /*  WARNING: Removed duplicated region for block: B:47:0x010a A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.os.Bundle zzy(java.lang.String r21, java.lang.String r22, android.os.Bundle r23, java.util.List r24, boolean r25) {
        /*
            r20 = this;
            r9 = r20
            r10 = r22
            r11 = r23
            r12 = r24
            java.lang.String[] r0 = com.google.android.gms.measurement.internal.zzgq.zzd
            boolean r13 = zzas(r10, r0)
            if (r11 == 0) goto L_0x010f
            android.os.Bundle r15 = new android.os.Bundle
            r15.<init>(r11)
            com.google.android.gms.measurement.internal.zzft r0 = r9.zzs
            com.google.android.gms.measurement.internal.zzaf r0 = r0.zzf()
            int r8 = r0.zzc()
            java.util.TreeSet r0 = new java.util.TreeSet
            java.util.Set r1 = r23.keySet()
            r0.<init>(r1)
            java.util.Iterator r16 = r0.iterator()
            r17 = 0
            r18 = r17
        L_0x0030:
            boolean r0 = r16.hasNext()
            if (r0 == 0) goto L_0x010d
            java.lang.Object r0 = r16.next()
            r7 = r0
            java.lang.String r7 = (java.lang.String) r7
            if (r12 == 0) goto L_0x0049
            boolean r0 = r12.contains(r7)
            if (r0 != 0) goto L_0x0046
            goto L_0x0049
        L_0x0046:
            r2 = r17
            goto L_0x0059
        L_0x0049:
            if (r25 != 0) goto L_0x0050
            int r0 = r9.zzj(r7)
            goto L_0x0052
        L_0x0050:
            r0 = r17
        L_0x0052:
            if (r0 != 0) goto L_0x0058
            int r0 = r9.zzi(r7)
        L_0x0058:
            r2 = r0
        L_0x0059:
            if (r2 == 0) goto L_0x006f
            r0 = 3
            if (r2 != r0) goto L_0x0060
            r5 = r7
            goto L_0x0061
        L_0x0060:
            r5 = 0
        L_0x0061:
            r0 = r20
            r1 = r15
            r3 = r7
            r4 = r7
            r0.zzI(r1, r2, r3, r4, r5)
            r15.remove(r7)
            r14 = r8
            goto L_0x010a
        L_0x006f:
            java.lang.Object r4 = r11.get(r7)
            r0 = r20
            r1 = r21
            r2 = r22
            r3 = r7
            r5 = r15
            r6 = r24
            r19 = r7
            r7 = r25
            r14 = r8
            r8 = r13
            int r2 = r0.zza(r1, r2, r3, r4, r5, r6, r7, r8)
            r0 = 17
            if (r2 != r0) goto L_0x009c
            java.lang.Boolean r5 = java.lang.Boolean.FALSE
            r2 = 17
            r0 = r20
            r1 = r15
            r3 = r19
            r4 = r19
            r0.zzI(r1, r2, r3, r4, r5)
        L_0x0099:
            r6 = r19
            goto L_0x00be
        L_0x009c:
            if (r2 == 0) goto L_0x0099
            java.lang.String r0 = "_ev"
            r6 = r19
            boolean r0 = r0.equals(r6)
            if (r0 != 0) goto L_0x00be
            r0 = 21
            if (r2 != r0) goto L_0x00ae
            r3 = r10
            goto L_0x00af
        L_0x00ae:
            r3 = r6
        L_0x00af:
            java.lang.Object r5 = r11.get(r6)
            r0 = r20
            r1 = r15
            r4 = r6
            r0.zzI(r1, r2, r3, r4, r5)
            r15.remove(r6)
            goto L_0x010a
        L_0x00be:
            boolean r0 = zzah(r6)
            if (r0 == 0) goto L_0x010a
            int r0 = r18 + 1
            if (r0 <= r14) goto L_0x0108
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r2 = 48
            r1.<init>(r2)
            java.lang.String r2 = "Event can't contain more than "
            r1.append(r2)
            r1.append(r14)
            java.lang.String r2 = " params"
            r1.append(r2)
            com.google.android.gms.measurement.internal.zzft r2 = r9.zzs
            com.google.android.gms.measurement.internal.zzej r2 = r2.zzay()
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zze()
            java.lang.String r1 = r1.toString()
            com.google.android.gms.measurement.internal.zzft r3 = r9.zzs
            com.google.android.gms.measurement.internal.zzee r3 = r3.zzj()
            java.lang.String r3 = r3.zzd(r10)
            com.google.android.gms.measurement.internal.zzft r4 = r9.zzs
            com.google.android.gms.measurement.internal.zzee r4 = r4.zzj()
            java.lang.String r4 = r4.zzb(r11)
            r2.zzc(r1, r3, r4)
            r1 = 5
            zzao(r15, r1)
            r15.remove(r6)
        L_0x0108:
            r18 = r0
        L_0x010a:
            r8 = r14
            goto L_0x0030
        L_0x010d:
            r14 = r15
            goto L_0x0110
        L_0x010f:
            r14 = 0
        L_0x0110:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzky.zzy(java.lang.String, java.lang.String, android.os.Bundle, java.util.List, boolean):android.os.Bundle");
    }


    public zzau zzz(String str, String str2, Bundle bundle, String str3, long j2, boolean z, boolean z2) {
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        if (0 == this.zzh(str2)) {
            Bundle bundle2 = null != bundle ? new Bundle(bundle) : new Bundle();
            bundle2.putString("_o", str3);
            Bundle zzy = zzy(str, str2, bundle2, CollectionUtils.listOf("_o"), true);
            if (z) {
                zzy = zzt(zzy);
            }
            Preconditions.checkNotNull(zzy);
            return new zzau(str2, new zzas(zzy), str3, j2);
        }
        this.zzs.zzay().zzd().zzb("Invalid conditional property event name", this.zzs.zzj().zzf(str2));
        throw new IllegalArgumentException();
    }
}
