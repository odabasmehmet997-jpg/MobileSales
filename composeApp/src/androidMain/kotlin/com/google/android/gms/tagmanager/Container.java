package com.google.android.gms.tagmanager;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.gtm.*;
import com.google.android.gms.internal.gtm.zzaf;
import com.google.android.gms.internal.gtm.zzah;
import com.google.android.gms.internal.gtm.zzap;
import com.google.android.gms.internal.gtm.zzz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
public class Container {
    private final Context zza;
    private final String zzb;
    private final DataLayer zzc;
    private zzep zzd;
    private final Map zze = new HashMap();
    private final Map zzf = new HashMap();
    private final long zzg;
    private volatile String zzh = "";

    /* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
    public interface FunctionCallMacroCallback {
        @NonNull
        Object getValue(@NonNull String str, @NonNull Map<String, Object> map);
    }

    /* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
    public interface FunctionCallTagCallback {
        void execute(@NonNull String str, @NonNull Map<String, Object> map);
    }

    Container(Context context, DataLayer dataLayer, String str, long j2, zzah zzah) {
        this.zza = context;
        this.zzc = dataLayer;
        this.zzb = str;
        this.zzg = j2;
        zzz zzc2 = zzah.zzc();
        zzc2.getClass();
        try {
            zzg(zzrm.zzb(zzc2));
        } catch (zzrk e2) {
            Log.e("GoogleTagManager", "Not loading resource: " + zzc2 + " because it is invalid: " + e2);
        }
        if (zzah.zza() != 0) {
            zzaf[] zzafArr = (zzaf[]) zzah.zzi().toArray(new zzaf[0]);
            zzep zzf2 = zzf();
            if (zzf2 == null) {
                Log.e("GoogleTagManager", "evaluateTags called for closed container.");
                return;
            }
            ArrayList arrayList = new ArrayList();
            Collections.addAll(arrayList, zzafArr);
            zzf2.zze(arrayList);
        }
    }

    private final synchronized zzep zzf() {
        return this.zzd;
    }

    private final void zzg(zzrg zzrg) {
        this.zzh = zzrg.zzb();
        zzrg zzrg2 = zzrg;
        zzh(new zzep(Preconditions.checkNotNull(this.zza), zzrg2, Preconditions.checkNotNull(this.zzc), new zzs(this, null), new zzt(this, null), new zzdg()));
        if (getBoolean("_gtm.loadEventEnabled")) {
            this.zzc.pushEvent("gtm.load", DataLayer.mapOf("gtm.id", Preconditions.checkNotNull(this.zzb)));
        }
    }

    private final synchronized void zzh(zzep zzep) {
        this.zzd = zzep;
    }

    public boolean getBoolean(@NonNull String str) {
        zzep zzf2 = zzf();
        if (zzf2 == null) {
            Log.e("GoogleTagManager", "getBoolean called for closed container.");
            zzfp.zze().booleanValue();
            return false;
        }
        try {
            return zzfp.zzf(zzfp.zzk((zzap) zzf2.zza(str).zza())).booleanValue();
        } catch (Exception e2) {
            String message = e2.getMessage();
            Log.e("GoogleTagManager", "Calling getBoolean() threw an exception: " + message + " Returning default value.");
            zzfp.zze().booleanValue();
            return false;
        }
    }

    @NonNull
    public String getContainerId() {
        return this.zzb;
    }

    public long getLastRefreshTime() {
        return this.zzg;
    }

    public boolean isDefault() {
        return getLastRefreshTime() == 0;
    }

    
    @VisibleForTesting
    public final FunctionCallMacroCallback zza(String str) {
        FunctionCallMacroCallback functionCallMacroCallback;
        synchronized (this.zze) {
            functionCallMacroCallback = (FunctionCallMacroCallback) this.zze.get(str);
        }
        return functionCallMacroCallback;
    }

    @VisibleForTesting
    @NonNull
    public final FunctionCallTagCallback zzb(@NonNull String str) {
        FunctionCallTagCallback functionCallTagCallback;
        synchronized (this.zzf) {
            functionCallTagCallback = (FunctionCallTagCallback) this.zzf.get(str);
        }
        return functionCallTagCallback;
    }

    @VisibleForTesting
    @NonNull
    public final String zzc() {
        return this.zzh;
    }

    @VisibleForTesting
    public final void zzd(@NonNull String str) {
        zzep zzf2 = zzf();
        if (zzf2 == null) {
            Log.e("GoogleTagManager", "evaluateTags called for closed container.");
        } else {
            zzf2.zzc(str);
        }
    }

    
    public final void zze() {
        this.zzd = null;
    }
}
