package com.google.android.gms.common.stats;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@KeepForSdk
@Deprecated
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public abstract class StatsEvent extends AbstractSafeParcelable implements ReflectedParcelable {

    @KeepForSdk
    /* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
    public interface Types {
    }

    @NonNull
    public final String toString() {
        long zzb = zzb();
        int zza = zza();
        String zzc = zzc();
        return zzb + "\t" + zza + "\t-1" + zzc;
    }

    public abstract int zza();

    public abstract long zzb();

    @NonNull
    public abstract String zzc();
}
