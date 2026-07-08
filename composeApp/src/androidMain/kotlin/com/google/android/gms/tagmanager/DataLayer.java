package com.google.android.gms.tagmanager;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.Preconditions;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
public class DataLayer {
    @NonNull
    public static final Object OBJECT_NOT_PRESENT = new Object();
    static final String[] zza = "gtm.lifetime".split("\\.");
    private static final Pattern zzb = Pattern.compile("(\\d+)\\s*([smhd]?)");
    private final ConcurrentHashMap zzc;
    private final Map zzd;
    private final ReentrantLock zze;
    private final LinkedList zzf;
    private final zzas zzg;
    
    public final CountDownLatch zzh;

    @VisibleForTesting
    DataLayer() {
        this(new zzan());
    }

    @VisibleForTesting
    @NonNull
    public static Map<String, Object> mapOf(@NonNull Object... objArr) {
        if ((objArr.length & 1) == 0) {
            HashMap hashMap = new HashMap();
            int i2 = 0;
            while (i2 < objArr.length) {
                String str = objArr[i2];
                if (str instanceof String) {
                    hashMap.put(str, objArr[i2 + 1]);
                    i2 += 2;
                } else {
                    throw new IllegalArgumentException("key is not a string: ".concat(String.valueOf(str)));
                }
            }
            return hashMap;
        }
        throw new IllegalArgumentException("expected even number of key-value pairs");
    }

    private final void zzh(Map map, String str, Collection collection) {
        String str2;
        for (Map.Entry entry : map.entrySet()) {
            int length = str.length();
            String str3 = (String) entry.getKey();
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            if (length == 0) {
                str2 = "";
            } else {
                str2 = ".";
            }
            sb.append(str2);
            sb.append(str3);
            String sb2 = sb.toString();
            if (entry.getValue() instanceof Map) {
                zzh((Map) entry.getValue(), sb2, collection);
            } else if (!sb2.equals("gtm.lifetime")) {
                collection.add(new zzap(sb2, entry.getValue()));
            }
        }
    }

    
    public final void zzi(Map map) {
        Long l;
        long j2;
        this.zze.lock();
        try {
            this.zzf.offer(map);
            if (this.zze.getHoldCount() == 1) {
                int i2 = 0;
                while (true) {
                    Map map2 = (Map) this.zzf.poll();
                    if (map2 == null) {
                        break;
                    }
                    synchronized (this.zzd) {
                        for (String str : map2.keySet()) {
                            zzf(zza(str, map2.get(str)), this.zzd);
                        }
                    }
                    for (zzaq zza2 : this.zzc.keySet()) {
                        zza2.zza(map2);
                    }
                    i2++;
                    if (i2 > 500) {
                        this.zzf.clear();
                        throw new RuntimeException("Seems like an infinite loop of pushing to the data layer");
                    }
                }
            }
            String[] strArr = zza;
            int length = strArr.length;
            Object obj = map;
            int i3 = 0;
            while (true) {
                l = null;
                if (i3 >= length) {
                    break;
                }
                String str2 = strArr[i3];
                if (!(obj instanceof Map)) {
                    obj = null;
                    break;
                } else {
                    obj = ((Map) obj).get(str2);
                    i3++;
                }
            }
            if (obj != null) {
                String obj2 = obj.toString();
                Matcher matcher = zzb.matcher(obj2);
                if (!matcher.matches()) {
                    zzdc.zzb.zzb("unknown _lifetime: ".concat(obj2));
                } else {
                    try {
                        String group = matcher.group(1);
                        Preconditions.checkNotNull(group);
                        j2 = Long.parseLong(group);
                    } catch (NumberFormatException unused) {
                        Log.w("GoogleTagManager", "illegal number in _lifetime value: ".concat(obj2));
                        j2 = 0;
                    }
                    if (j2 <= 0) {
                        zzdc.zzb.zzb("non-positive _lifetime: ".concat(obj2));
                    } else {
                        String group2 = matcher.group(2);
                        Preconditions.checkNotNull(group2);
                        if (group2.length() == 0) {
                            l = Long.valueOf(j2);
                        } else {
                            char charAt = group2.charAt(0);
                            if (charAt == 'd') {
                                l = Long.valueOf(j2 * 86400000);
                            } else if (charAt == 'h') {
                                l = Long.valueOf(j2 * 3600000);
                            } else if (charAt == 'm') {
                                l = Long.valueOf(j2 * 60000);
                            } else if (charAt != 's') {
                                Log.w("GoogleTagManager", "unknown units in _lifetime: ".concat(obj2));
                            } else {
                                l = Long.valueOf(j2 * 1000);
                            }
                        }
                    }
                }
            }
            if (l != null) {
                ArrayList arrayList = new ArrayList();
                zzh(map, "", arrayList);
                this.zzg.zzc(arrayList, l.longValue());
            }
            this.zze.unlock();
        } catch (Throwable th) {
            this.zze.unlock();
            throw th;
        }
    }

    @Nullable
    public Object get(@NonNull String str) {
        synchronized (this.zzd) {
            try {
                Object obj = this.zzd;
                for (String str2 : str.split("\\.")) {
                    if (!(obj instanceof Map)) {
                        return null;
                    }
                    obj = ((Map) obj).get(str2);
                    if (obj == null) {
                        return null;
                    }
                }
                return obj;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void push(@NonNull String str, @Nullable Object obj) {
        push(zza(str, obj));
    }

    public void pushEvent(@NonNull String str, @NonNull Map<String, Object> map) {
        HashMap hashMap = new HashMap(map);
        hashMap.put(NotificationCompat.CATEGORY_EVENT, str);
        push(hashMap);
    }

    @NonNull
    public String toString() {
        String sb;
        synchronized (this.zzd) {
            try {
                StringBuilder sb2 = new StringBuilder();
                for (Map.Entry entry : this.zzd.entrySet()) {
                    sb2.append(String.format("{\n\tKey: %s\n\tValue: %s\n}\n", entry.getKey(), entry.getValue()));
                }
                sb = sb2.toString();
            } catch (Throwable th) {
                throw th;
            }
        }
        return sb;
    }

    
    public final Map zza(String str, @Nullable Object obj) {
        HashMap hashMap = new HashMap();
        String[] split = str.split("\\.");
        int i2 = 0;
        HashMap hashMap2 = hashMap;
        while (true) {
            int length = split.length - 1;
            if (i2 < length) {
                HashMap hashMap3 = new HashMap();
                hashMap2.put(split[i2], hashMap3);
                i2++;
                hashMap2 = hashMap3;
            } else {
                hashMap2.put(split[length], obj);
                return hashMap;
            }
        }
    }

    
    public final void zzd(String str) {
        push(str, null);
        this.zzg.zza(str);
    }

    
    @VisibleForTesting
    public final void zze(List list, List list2) {
        while (list2.size() < list.size()) {
            list2.add(null);
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            Object obj = list.get(i2);
            if (obj instanceof List) {
                if (!(list2.get(i2) instanceof List)) {
                    list2.set(i2, new ArrayList());
                }
                Object obj2 = list2.get(i2);
                Preconditions.checkNotNull(obj2);
                zze((List) obj, (List) obj2);
            } else if (obj instanceof Map) {
                if (!(list2.get(i2) instanceof Map)) {
                    list2.set(i2, new HashMap());
                }
                Object obj3 = list2.get(i2);
                Preconditions.checkNotNull(obj3);
                zzf((Map) obj, (Map) obj3);
            } else if (obj != OBJECT_NOT_PRESENT) {
                list2.set(i2, obj);
            }
        }
    }

    
    @VisibleForTesting
    public final void zzf(Map map, Map map2) {
        for (String str : map.keySet()) {
            Object obj = map.get(str);
            if (obj instanceof List) {
                if (!(map2.get(str) instanceof List)) {
                    map2.put(str, new ArrayList());
                }
                Object obj2 = map2.get(str);
                Preconditions.checkNotNull(obj2);
                zze((List) obj, (List) obj2);
            } else if (obj instanceof Map) {
                if (!(map2.get(str) instanceof Map)) {
                    map2.put(str, new HashMap());
                }
                Object obj3 = map2.get(str);
                Preconditions.checkNotNull(obj3);
                zzf((Map) obj, (Map) obj3);
            } else {
                map2.put(str, obj);
            }
        }
    }

    
    public final void zzg(zzaq zzaq) {
        this.zzc.put(zzaq, 0);
    }

    DataLayer(zzas zzas) {
        this.zzg = zzas;
        this.zzc = new ConcurrentHashMap();
        this.zzd = new HashMap();
        this.zze = new ReentrantLock();
        this.zzf = new LinkedList();
        this.zzh = new CountDownLatch(1);
        zzas.zzb(new zzao(this));
    }

    public void push(@NonNull Map<String, Object> map) {
        try {
            this.zzh.await();
        } catch (InterruptedException unused) {
            Log.w("GoogleTagManager", "DataLayer.push: unexpected InterruptedException");
        }
        zzi(map);
    }
}
