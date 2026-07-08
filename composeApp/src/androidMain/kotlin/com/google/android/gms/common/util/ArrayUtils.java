package com.google.android.gms.common.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import java.lang.reflect.Array;
import java.util.Arrays;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public enum ArrayUtils {
    ;

    @NonNull
    @KeepForSdk
    public static <T> T[] concat(@NonNull final T[]... tArr) {
        if (0 == tArr.length) {
            return (Object[]) Array.newInstance(tArr.getClass(), 0);
        }
        int i2 = 0;
        for (final T[] length : tArr) {
            i2 += length.length;
        }
        final T[] copyOf = Arrays.copyOf(tArr[0], i2);
        int length2 = tArr[0].length;
        for (int i3 = 1; i3 < tArr.length; i3++) {
            final T[] tArr2 = tArr[i3];
            final int length3 = tArr2.length;
            System.arraycopy(tArr2, 0, copyOf, length2, length3);
            length2 += length3;
        }
        return copyOf;
    }

    @KeepForSdk
    public static boolean contains(@Nullable final int[] iArr, final int i2) {
        if (null != iArr) {
            for (final int i3 : iArr) {
                if (i3 == i2) {
                    return true;
                }
            }
        }
        return false;
    }

    @KeepForSdk
    public static void writeArray(@NonNull final StringBuilder sb, @NonNull final double[] dArr) {
        final int length = dArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (0 != i2) {
                sb.append(",");
            }
            sb.append(dArr[i2]);
        }
    }

    @KeepForSdk
    public static void writeStringArray(@NonNull final StringBuilder sb, @NonNull final String[] strArr) {
        final int length = strArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (0 != i2) {
                sb.append(",");
            }
            sb.append("\"");
            sb.append(strArr[i2]);
            sb.append("\"");
        }
    }

    @KeepForSdk
    public static <T> boolean contains(@NonNull final T[] tArr, @Nullable final T t) {
        final int length = null != tArr ? tArr.length : 0;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            } else if (!Objects.equal(tArr[i2], t)) {
                i2++;
            } else if (0 <= i2) {
                return true;
            }
        }
        return false;
    }

    @KeepForSdk
    public static void writeArray(@NonNull final StringBuilder sb, @NonNull final float[] fArr) {
        final int length = fArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (0 != i2) {
                sb.append(",");
            }
            sb.append(fArr[i2]);
        }
    }

    @KeepForSdk
    public static void writeArray(@NonNull final StringBuilder sb, @NonNull final int[] iArr) {
        final int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (0 != i2) {
                sb.append(",");
            }
            sb.append(iArr[i2]);
        }
    }

    @KeepForSdk
    public static void writeArray(@NonNull final StringBuilder sb, @NonNull final long[] jArr) {
        final int length = jArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (0 != i2) {
                sb.append(",");
            }
            sb.append(jArr[i2]);
        }
    }

    @KeepForSdk
    public static <T> void writeArray(@NonNull final StringBuilder sb, @NonNull final T[] tArr) {
        final int length = tArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (0 != i2) {
                sb.append(",");
            }
            sb.append(tArr[i2]);
        }
    }

    @KeepForSdk
    public static void writeArray(@NonNull final StringBuilder sb, @NonNull final boolean[] zArr) {
        final int length = zArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (0 != i2) {
                sb.append(",");
            }
            sb.append(zArr[i2]);
        }
    }
}
