package com.google.android.gms.tagmanager;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzz {
    final boolean zza;
    final zzag zzb;
    private Long zzc;

    public boolean zza(Container container) {
        if (!this.zza) {
            return !container.isDefault();
        }
        long lastRefreshTime = container.getLastRefreshTime();
        if (this.zzc == null) {
            this.zzc = Long.valueOf(this.zzb.zzi.zza());
        }
        return lastRefreshTime + this.zzc.longValue() >= this.zzb.zza.currentTimeMillis();
    }
}
