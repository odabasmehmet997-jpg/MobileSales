package com.proje.mobilesales.core.utils;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import androidx.annotation.RequiresApi;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static ads_mobile_sdk.go.th;

public class FilePath {
    private static Uri filePathUri;

    @RequiresApi(api = 19)
    public static String getPath(Context context, Uri uri) {
        filePathUri = uri;
        Uri uri2 = null;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                String[] split = DocumentsContract.getDocumentId(uri).split(":");
                if ("primary".equalsIgnoreCase(split[0])) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
                return "SDCARD/" + split[1];
            }
            if (isDownloadsDocument(uri)) {
                String documentId = DocumentsContract.getDocumentId(uri);
                if (documentId.startsWith("raw:")) {
                    return documentId.replaceFirst("raw:", "");
                }
                return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId).longValue()), null, null);
            }
            if (isMediaDocument(uri)) {
                String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
                String str = split2[0];
                if ("image".equals(str)) {
                    uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(str)) {
                    uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(str)) {
                    uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                return getDataColumn(context, uri2, "_id=?", new String[]{split2[1]});
            }
        } else {
            if (FirebaseAnalytics.Param.CONTENT.equalsIgnoreCase(uri.getScheme())) {
                if (isGooglePhotosUri(uri)) {
                    return uri.getLastPathSegment();
                }
                return getDataColumn(context, uri, null, null);
            }
            if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }
        return null;
    }
     public static int getDataColumn(Context context, Uri uri, Cursor str, String[] strArr) {
        Cursor cursor = null;
        try {
        } catch (Throwable th) {
            th = th;
            cursor = str;
        }
        try {
            try {
                try (Cursor cursor1 = str = context.getContentResolver().query(uri, new String[]{"_data"}, str.toString(), strArr, null)) {
                }
                if (str != 0) {
                    try {
                        if (str.moveToFirst()) {
                            String string = str.getString(str.getColumnIndexOrThrow("_data"));
                            str.close();
                            return string;
                        }
                    } catch (IllegalArgumentException e2) {
                        e = e2;
                        e.printStackTrace();
                        String absolutePath = new File(context.getCacheDir(), "tmp").getAbsolutePath();
                        try {
                            ParcelFileDescriptor openFileDescriptor = context.getContentResolver().openFileDescriptor(filePathUri, "r");
                            if (openFileDescriptor == null) {
                                if (str != 0) {
                                    str.close();
                                }
                                return null;
                            }
                            FileInputStream fileInputStream = new FileInputStream(openFileDescriptor.getFileDescriptor());
                            FileOutputStream fileOutputStream = new FileOutputStream(absolutePath);
                            byte[] bArr = new byte[4096];
                            while (true) {
                                int read = fileInputStream.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, read);
                            }
                            fileInputStream.close();
                            fileOutputStream.close();
                            String absolutePath2 = new File(absolutePath).getAbsolutePath();
                            if (str != 0) {
                                str.close();
                            }
                            return absolutePath2;
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        } catch (Exception unused) {
                            String path = uri.getPath();
                            if (str != 0) {
                                str.close();
                            }
                            return path;
                        }
                    } catch (Exception unused2) {
                        cursor = str;
                        String path2 = uri.getPath();
                        if (cursor != null) {
                            cursor.close();
                        }
                        return path2;
                    }
                }
            } catch (IllegalArgumentException e4) {
                e = e4;
                str = 0;
            } catch (Exception unused3) {
            }
        } catch (Throwable th2) {
            th = th2;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
         return str;
     }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}
