package com.google.android.gms.common.data;

import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;

@KeepName
@KeepForSdk
@SafeParcelable.Class
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class DataHolder extends AbstractSafeParcelable implements Closeable {
    @NonNull
    @KeepForSdk
    public static final Parcelable.Creator<DataHolder> CREATOR = new zaf();
    private static final Builder zaf = new zab(new String[0], null);
    @SafeParcelable.VersionField
    final int zaa;
    Bundle zab;
    int[] zac;
    int zad;
    boolean zae;
    @SafeParcelable.Field
    private final String[] zag;
    @SafeParcelable.Field
    private final CursorWindow[] zah;
    @SafeParcelable.Field
    private final int zai;
    @SafeParcelable.Field
    @Nullable
    private final Bundle zaj;
    private final boolean zak = true;

    @KeepForSdk
    /* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
    public static class Builder {
        private final String[] zaa;
        private final ArrayList zab = new ArrayList();
        private final HashMap zac = new HashMap();

        Builder(final String[] strArr, final String str, final zac zac2) {
            zaa = Preconditions.checkNotNull(strArr);
        }
    }

    @SafeParcelable.Constructor
    DataHolder(@SafeParcelable.Param final int i2, @SafeParcelable.Param final String[] strArr, @SafeParcelable.Param final CursorWindow[] cursorWindowArr, @SafeParcelable.Param final int i3, @SafeParcelable.Param @Nullable final Bundle bundle) {
        zaa = i2;
        zag = strArr;
        zah = cursorWindowArr;
        zai = i3;
        zaj = bundle;
    }

    private void zae(final String str, final int i2) {
        final Bundle bundle = zab;
        if (null == bundle || !bundle.containsKey(str)) {
            throw new IllegalArgumentException("No such column: " + str);
        } else if (this.isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
        } else if (0 > i2 || i2 >= zad) {
            throw new CursorIndexOutOfBoundsException(i2, zad);
        }
    }

    @KeepForSdk
    public void close() {
        synchronized (this) {
            try {
                if (!zae) {
                    zae = true;
                    int i2 = 0;
                    while (true) {
                        final CursorWindow[] cursorWindowArr = zah;
                        if (i2 >= cursorWindowArr.length) {
                            break;
                        }
                        cursorWindowArr[i2].close();
                        i2++;
                    }
                }
            } catch (final Throwable th) {
                throw th;
            }
        }
    }

    
    protected void finalize() throws Throwable {
        try {
            if (zak && 0 < this.zah.length && !this.isClosed()) {
                this.close();
                final String obj = this.toString();
                Log.e("DataBuffer", "Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: " + obj + ")");
            }
        } finally {
            super.finalize();
        }
    }

    @NonNull
    @KeepForSdk
    public byte[] getByteArray(@NonNull final String str, final int i2, final int i3) {
        this.zae(str, i2);
        return zah[i3].getBlob(i2, zab.getInt(str));
    }

    @KeepForSdk
    public int getCount() {
        return zad;
    }

    @KeepForSdk
    @Nullable
    public Bundle getMetadata() {
        return zaj;
    }

    @KeepForSdk
    public int getStatusCode() {
        return zai;
    }

    @NonNull
    @KeepForSdk
    public String getString(@NonNull final String str, final int i2, final int i3) {
        this.zae(str, i2);
        return zah[i3].getString(i2, zab.getInt(str));
    }

    @KeepForSdk
    public int getWindowIndex(final int i2) {
        int length;
        int i3 = 0;
        Preconditions.checkState(0 <= i2 && i2 < zad);
        while (true) {
            final int[] iArr = zac;
            length = iArr.length;
            if (i3 >= length) {
                break;
            } else if (i2 < iArr[i3]) {
                i3--;
                break;
            } else {
                i3++;
            }
        }
        return i3 == length ? i3 - 1 : i3;
    }

    @KeepForSdk
    public boolean isClosed() {
        final boolean z;
        synchronized (this) {
            z = zae;
        }
        return z;
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final String[] strArr = zag;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringArray(parcel, 1, strArr, false);
        SafeParcelWriter.writeTypedArray(parcel, 2, zah, i2, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zai);
        SafeParcelWriter.writeBundle(parcel, 4, this.zaj, false);
        SafeParcelWriter.writeInt(parcel, 1000, zaa);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        if (0 != (i2 & 1)) {
            this.close();
        }
    }

    public void zad() {
        zab = new Bundle();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            final String[] strArr = zag;
            if (i3 >= strArr.length) {
                break;
            }
            zab.putInt(strArr[i3], i3);
            i3++;
        }
        zac = new int[zah.length];
        int i4 = 0;
        while (true) {
            final CursorWindow[] cursorWindowArr = zah;
            if (i2 < cursorWindowArr.length) {
                zac[i2] = i4;
                i4 += zah[i2].getNumRows() - (i4 - cursorWindowArr[i2].getStartPosition());
                i2++;
            } else {
                zad = i4;
                return;
            }
        }
    }
}
