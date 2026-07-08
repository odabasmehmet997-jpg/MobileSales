package com.google.android.gms.tagmanager;

import android.os.Looper;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.api.Status;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzx implements ContainerHolder {
    private final Looper zza;
    private Container zzb;
    private Container zzc;
    private final Status zzd;
    private zzw zze;
    private zzv zzf;
    private boolean zzg;
    private TagManager zzh;

    public zzx(Status status) {
        this.zzd = status;
        this.zza = null;
    }

    public zzx(TagManager tagManager, Looper looper, Container container, zzv zzv) {
        this.zzh = tagManager;
        this.zza = looper == null ? Looper.getMainLooper() : looper;
        this.zzb = container;
        this.zzf = zzv;
        this.zzd = Status.RESULT_SUCCESS;
        tagManager.zza(this);
    }

    private void zzf() {
        zzw zzw = this.zze;
        if (zzw != null) {
            zzw.sendMessage(zzw.obtainMessage(1, this.zzc.zzc()));
        }
    }

    public Status getStatus() {
        return this.zzd;
    }

    public synchronized void refresh() {
        if (this.zzg) {
            Log.e("GoogleTagManager", "Refreshing a released ContainerHolder.");
        } else {
            this.zzf.zzb();
        }
    }

    public synchronized void release() {
        if (this.zzg) {
            Log.e("GoogleTagManager", "Releasing a released ContainerHolder.");
            return;
        }
        this.zzg = true;
        this.zzh.zzc(this);
        this.zzb.zze();
        this.zzb = null;
        this.zzc = null;
        this.zzf = null;
        this.zze = null;
    }

    
    public String zza() {
        if (!this.zzg) {
            return this.zzb.getContainerId();
        }
        Log.e("GoogleTagManager", "getContainerId called on a released ContainerHolder.");
        return "";
    }

    
    public String zzb() {
        if (!this.zzg) {
            return this.zzf.zza();
        }
        Log.e("GoogleTagManager", "setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        return "";
    }

    public synchronized void zzc(Container container) {
        if (!this.zzg) {
            this.zzc = container;
            zzf();
        }
    }

    public synchronized void zzd(String str) {
        if (!this.zzg) {
            this.zzb.zzd(str);
        }
    }

    
    public void zze(String str) {
        if (this.zzg) {
            Log.e("GoogleTagManager", "setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        } else {
            this.zzf.zzc(str);
        }
    }
}
