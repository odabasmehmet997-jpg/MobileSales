package com.proje.mobilesales.core.utils;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.io.Closeable;
import kotlin.io.IOStreams;
import okio.Source;

public final class ImageUtils {
    public static final ImageUtils INSTANCE = new ImageUtils();

    private ImageUtils() {
    }

    public static String getAbsolutePath(Activity activity, Uri selectedUri) {
        String str;
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(selectedUri, "selectedUri");
        String[] strArr = {"_data"};
        Cursor query = activity.getContentResolver().query(selectedUri, strArr, null, null, null);
        if (query != null) {
            query.moveToFirst();
        }
        Integer valueOf = query != null ? Integer.valueOf(query.getColumnIndex(strArr[0])) : null;
        if (query != null) {
            str = query.getString(valueOf != null ? valueOf.intValue() : 0);
        } else {
            str = null;
        }
        if (query != null) {
            query.close();
        }
        File file = str != null ? new File(str) : null;
        if (str == null) {
            file = INSTANCE.getTempFileFromUri(activity, selectedUri);
        }
        if (file != null) {
            return file.getAbsolutePath();
        }
        return null;
    }

    private File getTempFileFromUri(Context context, Uri uri) {
        String path;
        InputStream openInputStream;
        if (Intrinsics.areEqual(uri.getScheme(), FirebaseAnalytics.Param.CONTENT)) {
            Cursor query = context.getContentResolver().query(uri, null, null, null, null);
            try {
                if (query != null) {
                    path = query.moveToFirst() ? query.getString(query.getColumnIndexOrThrow("_display_name")) : null;
                    Unit unit = Unit.INSTANCE;
                    Closeable.closeFinally((Source) query, null);
                    File file = path == null ? new File(context.getCacheDir(), path) : null;
                    openInputStream = context.getContentResolver().openInputStream(uri);
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    if (openInputStream != null) {
                        IOStreams.copyTodefault(openInputStream, fileOutputStream, 0, 2, null);
                    }
                    if (openInputStream != null) {
                        openInputStream.close();
                    }
                    fileOutputStream.close();
                    return file;
                }
                openInputStream = context.getContentResolver().openInputStream(uri);
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                if (openInputStream != null) {
                }
                if (openInputStream != null) {
                }
                fileOutputStream2.close();
                return file;
            } catch (IOException e2) {
                e2.printStackTrace();
                return null;
            }
            path = null;
            if (path == null) {
            }
        } else {
            if (Intrinsics.areEqual(uri.getScheme(), "file")) {
                path = uri.getPath();
                if (path == null) {
                }
                openInputStream = context.getContentResolver().openInputStream(uri);
                FileOutputStream fileOutputStream22 = new FileOutputStream(file);
                if (openInputStream != null) {
                }
                if (openInputStream != null) {
                }
                fileOutputStream22.close();
                return file;
            }
            path = null;
            if (path == null) {
            }
            openInputStream = context.getContentResolver().openInputStream(uri);
            FileOutputStream fileOutputStream222 = new FileOutputStream(file);
            if (openInputStream != null) {
            }
            if (openInputStream != null) {
            }
            fileOutputStream222.close();
            return file;
        }
    }

    public static Bitmap modifyOrientation(Bitmap bitmap, String str) throws IOException {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNull(str);
        int attributeInt = new ExifInterface(str).getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
        if (attributeInt == 2) {
            return INSTANCE.flip(bitmap, true, false);
        }
        if (attributeInt == 3) {
            return INSTANCE.rotate(bitmap, 180.0f);
        }
        if (attributeInt == 4) {
            return INSTANCE.flip(bitmap, false, true);
        }
        if (attributeInt != 6) {
            return attributeInt != 8 ? bitmap : INSTANCE.rotate(bitmap, 270.0f);
        }
        return INSTANCE.rotate(bitmap, 90.0f);
    }

    public Bitmap rotate(Bitmap bitmap, float f2) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Matrix matrix = new Matrix();
        matrix.postRotate(f2);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public Bitmap flip(Bitmap bitmap, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Matrix matrix = new Matrix();
        matrix.preScale(z ? -1.0f : 1.0f, z2 ? -1.0f : 1.0f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
}
