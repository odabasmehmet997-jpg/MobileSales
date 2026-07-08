package com.google.android.gms.common.moduleinstall;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SafeParcelable.Class
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public class ModuleInstallStatusUpdate extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<ModuleInstallStatusUpdate> CREATOR = new zae();
    @SafeParcelable.Field
    private final int zaa;
    @SafeParcelable.Field
    @InstallState
    private final int zab;
    @SafeParcelable.Field
    @Nullable
    private final Long zac;
    @SafeParcelable.Field
    @Nullable
    private final Long zad;
    @SafeParcelable.Field
    private final int zae;
    @Nullable
    private final ProgressInfo zaf;

    @Retention(RetentionPolicy.CLASS)
    /* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
    public @interface InstallState {
    }

    /* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
    public static class ProgressInfo {
        private final long zaa;
        private final long zab;

        ProgressInfo(long j2, long j3) {
            Preconditions.checkNotZero(j3);
            this.zaa = j2;
            this.zab = j3;
        }
    }

    @SafeParcelable.Constructor
    @KeepForSdk
    public ModuleInstallStatusUpdate(@SafeParcelable.Param int i2, @SafeParcelable.Param @InstallState int i3, @SafeParcelable.Param @Nullable Long l, @SafeParcelable.Param @Nullable Long l2, @SafeParcelable.Param int i4) {
        this.zaa = i2;
        this.zab = i3;
        this.zac = l;
        this.zad = l2;
        this.zae = i4;
        this.zaf = (null == l || null == l2 || 0 == l2.longValue()) ? null : new ProgressInfo(l.longValue(), l2.longValue());
    }

    public int getErrorCode() {
        return this.zae;
    }

    @InstallState
    public int getInstallState() {
        return this.zab;
    }

    public int getSessionId() {
        return this.zaa;
    }

    public void writeToParcel(@NonNull Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, zaa);
        SafeParcelWriter.writeInt(parcel, 2, zab);
        SafeParcelWriter.writeLongObject(parcel, 3, this.zac, false);
        SafeParcelWriter.writeLongObject(parcel, 4, this.zad, false);
        SafeParcelWriter.writeInt(parcel, 5, zae);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
