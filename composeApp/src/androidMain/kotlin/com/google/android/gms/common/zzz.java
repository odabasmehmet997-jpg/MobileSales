package com.google.android.gms.common;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.common.zzag;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
final class zzz {
    @Nullable
    private String zza;
    private long zzb = -1;
    private zzag zzc = zzag.zzl();
    private zzag zzd = zzag.zzl();

    zzz() {
    }

    
    @CanIgnoreReturnValue
    public zzz zza(final long j2) {
        zzb = j2;
        return this;
    }

    
    @CanIgnoreReturnValue
    public zzz zzb(final List list) {
        Preconditions.checkNotNull(list);
        zzd = zzag.zzk(list);
        return this;
    }

    
    @CanIgnoreReturnValue
    public zzz zzc(final List list) {
        Preconditions.checkNotNull(list);
        zzc = zzag.zzk(list);
        return this;
    }

    
    @CanIgnoreReturnValue
    public zzz zzd(final String str) {
        zza = str;
        return this;
    }

    
    public zzab zze() {
        if (null == this.zza) {
            throw new IllegalStateException("packageName must be defined");
        } else if (0 > this.zzb) {
            throw new IllegalStateException("minimumStampedVersionNumber must be greater than or equal to 0");
        } else if (!zzc.isEmpty() || !zzd.isEmpty()) {
            return new zzab(zza, zzb, zzc, zzd, null);
        } else {
            throw new IllegalStateException("Either orderedTestCerts or orderedProdCerts must have at least one cert");
        }
    }
}
