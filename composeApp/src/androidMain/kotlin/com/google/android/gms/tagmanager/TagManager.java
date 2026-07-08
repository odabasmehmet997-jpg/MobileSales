package com.google.android.gms.tagmanager;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
public class TagManager {
    private static TagManager zza;
    private final Context zzb;
    private final DataLayer zzc;
    private final zzet zzd;
    private final ConcurrentMap zze = new ConcurrentHashMap();
    private final zzak zzf;

    @VisibleForTesting
    TagManager(Context context, zzfj zzfj, DataLayer dataLayer, zzet zzet) {
        Context applicationContext = context.getApplicationContext();
        this.zzb = applicationContext;
        this.zzd = zzet;
        this.zzc = dataLayer;
        dataLayer.zzg(new zzfg(this));
        dataLayer.zzg(new zzg(applicationContext));
        this.zzf = new zzak();
        Preconditions.checkNotNull(applicationContext);
        applicationContext.registerComponentCallbacks(new zzfi(this));
        Preconditions.checkNotNull(applicationContext);
        zzd.zzb(applicationContext);
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    @NonNull
    public static TagManager getInstance(@NonNull Context context) {
        TagManager tagManager;
        synchronized (TagManager.class) {
            try {
                if (zza == null) {
                    if (context != null) {
                        zza = new TagManager(context, new zzfh(), new DataLayer(new zzaz(context)), zzfa.zzg());
                    } else {
                        Log.e("GoogleTagManager", "TagManager.getInstance requires non-null context.");
                        throw null;
                    }
                }
                tagManager = zza;
            } catch (Throwable th) {
                throw th;
            }
        }
        return tagManager;
    }

    static void zzb(TagManager tagManager, String str) {
        Preconditions.checkNotNull(tagManager.zze);
        for (zzx zzd2 : tagManager.zze.values()) {
            zzd2.zzd(str);
        }
    }

    public void dispatch() {
        this.zzd.zza();
    }

    @NonNull
    public DataLayer getDataLayer() {
        return this.zzc;
    }

    @VisibleForTesting
    public final int zza(zzx zzx) {
        this.zze.put(zzx.zza(), zzx);
        return this.zze.size();
    }

    @VisibleForTesting
    public final boolean zzc(zzx zzx) {
        return this.zze.remove(zzx.zza()) != null;
    }


    /*  WARNING: Code restructure failed: missing block: B:27:0x0070, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean zzd(android.net.Uri r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            com.google.android.gms.tagmanager.zzdv r0 = com.google.android.gms.tagmanager.zzdv.zza()     // Catch:{ all -> 0x0050 }
            boolean r7 = r0.zzd(r7)     // Catch:{ all -> 0x0050 }
            if (r7 == 0) goto L_0x0072
            java.lang.String r7 = r0.zzc()     // Catch:{ all -> 0x0050 }
            int r1 = r0.zze()     // Catch:{ all -> 0x0050 }
            int r2 = r1 + -1
            r3 = 0
            if (r1 == 0) goto L_0x0071
            r1 = 1
            if (r2 == 0) goto L_0x005f
            if (r2 == r1) goto L_0x0021
            r4 = 2
            if (r2 == r4) goto L_0x0021
            goto L_0x006f
        L_0x0021:
            java.util.concurrent.ConcurrentMap r2 = r6.zze     // Catch:{ all -> 0x0050 }
            java.util.Set r2 = r2.keySet()     // Catch:{ all -> 0x0050 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0050 }
        L_0x002b:
            boolean r4 = r2.hasNext()     // Catch:{ all -> 0x0050 }
            if (r4 == 0) goto L_0x006f
            java.lang.Object r4 = r2.next()     // Catch:{ all -> 0x0050 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x0050 }
            java.util.concurrent.ConcurrentMap r5 = r6.zze     // Catch:{ all -> 0x0050 }
            java.lang.Object r5 = r5.get(r4)     // Catch:{ all -> 0x0050 }
            com.google.android.gms.tagmanager.zzx r5 = (com.google.android.gms.tagmanager.zzx) r5     // Catch:{ all -> 0x0050 }
            boolean r4 = r4.equals(r7)     // Catch:{ all -> 0x0050 }
            if (r4 == 0) goto L_0x0052
            java.lang.String r4 = r0.zzb()     // Catch:{ all -> 0x0050 }
            r5.zze(r4)     // Catch:{ all -> 0x0050 }
            r5.refresh()     // Catch:{ all -> 0x0050 }
            goto L_0x002b
        L_0x0050:
            r7 = move-exception
            goto L_0x0075
        L_0x0052:
            java.lang.String r4 = r5.zzb()     // Catch:{ all -> 0x0050 }
            if (r4 == 0) goto L_0x002b
            r5.zze(r3)     // Catch:{ all -> 0x0050 }
            r5.refresh()     // Catch:{ all -> 0x0050 }
            goto L_0x002b
        L_0x005f:
            java.util.concurrent.ConcurrentMap r0 = r6.zze     // Catch:{ all -> 0x0050 }
            java.lang.Object r7 = r0.get(r7)     // Catch:{ all -> 0x0050 }
            com.google.android.gms.tagmanager.zzx r7 = (com.google.android.gms.tagmanager.zzx) r7     // Catch:{ all -> 0x0050 }
            if (r7 == 0) goto L_0x006f
            r7.zze(r3)     // Catch:{ all -> 0x0050 }
            r7.refresh()     // Catch:{ all -> 0x0050 }
        L_0x006f:
            monitor-exit(r6)
            return r1
        L_0x0071:
            throw r3     // Catch:{ all -> 0x0050 }
        L_0x0072:
            monitor-exit(r6)
            r7 = 0
            return r7
        L_0x0075:
            monitor-exit(r6)     // Catch:{ all -> 0x0050 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.TagManager.zzd(android.net.Uri):boolean");
    }
}
