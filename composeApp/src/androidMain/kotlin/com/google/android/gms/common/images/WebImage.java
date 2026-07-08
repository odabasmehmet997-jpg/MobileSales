package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Locale;

@SafeParcelable.Class
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class WebImage extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<WebImage> CREATOR = new zah();
    @SafeParcelable.VersionField
    final int zaa;
    @SafeParcelable.Field
    private final Uri zab;
    @SafeParcelable.Field
    private final int zac;
    @SafeParcelable.Field
    private final int zad;

    @SafeParcelable.Constructor
    WebImage(@SafeParcelable.Param int i2, @SafeParcelable.Param Uri uri, @SafeParcelable.Param int i3, @SafeParcelable.Param int i4) {
        this.zaa = i2;
        this.zab = uri;
        this.zac = i3;
        this.zad = i4;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (null != obj && (obj instanceof final WebImage webImage)) {
            return Objects.equal(this.zab, webImage.zab) && this.zac == webImage.zac && this.zad == webImage.zad;
        }
    }

    public int getHeight() {
        return this.zad;
    }

    @NonNull
    public Uri getUrl() {
        return this.zab;
    }

    public int getWidth() {
        return this.zac;
    }

    public int hashCode() {
        return Objects.hashCode(this.zab, Integer.valueOf(this.zac), Integer.valueOf(this.zad));
    }

    @NonNull
    public String toString() {
        return String.format(Locale.US, "Image %dx%d %s", Integer.valueOf(this.zac), Integer.valueOf(this.zad), this.zab.toString());
    }

    public void writeToParcel(@NonNull Parcel parcel, int i2) {
        int i3 = this.zaa;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i3);
        SafeParcelWriter.writeParcelable(parcel, 2, zab, i2, false);
        SafeParcelWriter.writeInt(parcel, 3, zac);
        SafeParcelWriter.writeInt(parcel, 4, zad);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
