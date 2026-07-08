package com.google.android.gms.common.data;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import java.io.*;
import java.nio.ByteBuffer;

@ShowFirstParty
@KeepForSdk
@SafeParcelable.Class
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public class BitmapTeleporter extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    @KeepForSdk
    public static final Parcelable.Creator<BitmapTeleporter> CREATOR = new zaa();
    @SafeParcelable.VersionField
    final int zaa;
    @SafeParcelable.Field
    @Nullable
    ParcelFileDescriptor zab;
    @SafeParcelable.Field
    final int zac;
    @Nullable
    private final Bitmap zad;
    private final boolean zae;
    private File zaf;

    @SafeParcelable.Constructor
    BitmapTeleporter(@SafeParcelable.Param final int i2, @SafeParcelable.Param final ParcelFileDescriptor parcelFileDescriptor, @SafeParcelable.Param final int i3) {
        zaa = i2;
        zab = parcelFileDescriptor;
        zac = i3;
    }

    private static final void zaa(final Closeable closeable) {
        try {
            closeable.close();
        } catch (final IOException e2) {
            Log.w("BitmapTeleporter", "Could not close stream", e2);
        }
    }

    public final void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        if (null == this.zab) {
            final Bitmap bitmap = Preconditions.checkNotNull(zad);
            final ByteBuffer allocate = ByteBuffer.allocate(bitmap.getRowBytes() * bitmap.getHeight());
            bitmap.copyPixelsToBuffer(allocate);
            final byte[] array = allocate.array();
            final File file = zaf;
            if (null != file) {
                try {
                    final File createTempFile = File.createTempFile("teleporter", ".tmp", file);
                    try {
                        final FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
                        zab = ParcelFileDescriptor.open(createTempFile, 268435456);
                        createTempFile.delete();
                        final DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(fileOutputStream));
                        try {
                            dataOutputStream.writeInt(array.length);
                            dataOutputStream.writeInt(bitmap.getWidth());
                            dataOutputStream.writeInt(bitmap.getHeight());
                            dataOutputStream.writeUTF(bitmap.getConfig().toString());
                            dataOutputStream.write(array);
                            BitmapTeleporter.zaa(dataOutputStream);
                        } catch (final IOException e2) {
                            throw new IllegalStateException("Could not write into unlinked file", e2);
                        } catch (final Throwable th) {
                            BitmapTeleporter.zaa(dataOutputStream);
                            throw th;
                        }
                    } catch (final FileNotFoundException unused) {
                        throw new IllegalStateException("Temporary file is somehow already deleted");
                    }
                } catch (final IOException e3) {
                    throw new IllegalStateException("Could not create temporary file", e3);
                }
            } else {
                throw new IllegalStateException("setTempDir() must be called before writing this object to a parcel");
            }
        }
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, zaa);
        SafeParcelWriter.writeParcelable(parcel, 2, zab, i2 | 1, false);
        SafeParcelWriter.writeInt(parcel, 3, zac);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        zab = null;
    }
}
