package org.springframework.util;

import java.util.Arrays;

public abstract class ObjectUtils {
    public static int hashCode(long j2) {
        return (int) (j2 ^ (j2 >>> 32));
    }
    public static int hashCode(boolean z) {
        return z ? 1231 : 1237;
    }
    public static boolean isEmpty(Object[] objArr) {
        return objArr == null || objArr.length == 0;
    }
    public static boolean nullSafeEquals(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj != null && obj2 != null) {
            if (obj.equals(obj2)) {
                return true;
            }
            if (obj.getClass().isArray() && obj2.getClass().isArray()) {
                if ((obj instanceof Object[]) && (obj2 instanceof Object[])) {
                    return Arrays.equals((Object[]) obj, (Object[]) obj2);
                }
                if ((obj instanceof boolean[]) && (obj2 instanceof boolean[])) {
                    return Arrays.equals((boolean[]) obj, (boolean[]) obj2);
                }
                if ((obj instanceof byte[]) && (obj2 instanceof byte[])) {
                    return Arrays.equals((byte[]) obj, (byte[]) obj2);
                }
                if ((obj instanceof char[]) && (obj2 instanceof char[])) {
                    return Arrays.equals((char[]) obj, (char[]) obj2);
                }
                if ((obj instanceof double[]) && (obj2 instanceof double[])) {
                    return Arrays.equals((double[]) obj, (double[]) obj2);
                }
                if ((obj instanceof float[]) && (obj2 instanceof float[])) {
                    return Arrays.equals((float[]) obj, (float[]) obj2);
                }
                if ((obj instanceof int[]) && (obj2 instanceof int[])) {
                    return Arrays.equals((int[]) obj, (int[]) obj2);
                }
                if ((obj instanceof long[]) && (obj2 instanceof long[])) {
                    return Arrays.equals((long[]) obj, (long[]) obj2);
                }
                if ((obj instanceof short[]) && (obj2 instanceof short[])) {
                    return Arrays.equals((short[]) obj, (short[]) obj2);
                }
            }
        }
        return false;
    }
    public static int nullSafeHashCode(Object obj) {
        if (obj == null) {
            return 0;
        }
        if (obj.getClass().isArray()) {
            if (obj instanceof Object[]) {
                return nullSafeHashCode((Object[]) obj);
            }
            if (obj instanceof boolean[]) {
                return nullSafeHashCode((boolean[]) obj);
            }
            if (obj instanceof byte[]) {
                return nullSafeHashCode((byte[]) obj);
            }
            if (obj instanceof char[]) {
                return nullSafeHashCode((char[]) obj);
            }
            if (obj instanceof double[]) {
                return nullSafeHashCode((double[]) obj);
            }
            if (obj instanceof float[]) {
                return nullSafeHashCode((float[]) obj);
            }
            if (obj instanceof int[]) {
                return nullSafeHashCode((int[]) obj);
            }
            if (obj instanceof long[]) {
                return nullSafeHashCode((long[]) obj);
            }
            if (obj instanceof short[]) {
                return nullSafeHashCode((short[]) obj);
            }
        }
        return obj.hashCode();
    }
    public static int nullSafeHashCode(Object[] objArr) {
        if (objArr == null) {
            return 0;
        }
        int iNullSafeHashCode = 7;
        for (Object obj : objArr) {
            iNullSafeHashCode = (iNullSafeHashCode * 31) + nullSafeHashCode(obj);
        }
        return iNullSafeHashCode;
    }
    public static int nullSafeHashCode(boolean[] zArr) {
        if (zArr == null) {
            return 0;
        }
        int iHashCode = 7;
        for (boolean z : zArr) {
            iHashCode = (iHashCode * 31) + hashCode(z);
        }
        return iHashCode;
    }
    public static int nullSafeHashCode(byte[] bArr) {
        if (bArr == null) {
            return 0;
        }
        int i2 = 7;
        for (byte b2 : bArr) {
            i2 = (i2 * 31) + b2;
        }
        return i2;
    }
    public static int nullSafeHashCode(char[] cArr) {
        if (cArr == null) {
            return 0;
        }
        int i2 = 7;
        for (char c2 : cArr) {
            i2 = (i2 * 31) + c2;
        }
        return i2;
    }
    public static int nullSafeHashCode(double[] dArr) {
        if (dArr == null) {
            return 0;
        }
        int iHashCode = 7;
        for (double d2 : dArr) {
            iHashCode = (iHashCode * 31) + hashCode(d2);
        }
        return iHashCode;
    }
    public static int nullSafeHashCode(float[] fArr) {
        if (fArr == null) {
            return 0;
        }
        int iHashCode = 7;
        for (float f2 : fArr) {
            iHashCode = (iHashCode * 31) + hashCode(f2);
        }
        return iHashCode;
    }
    public static int nullSafeHashCode(int[] iArr) {
        if (iArr == null) {
            return 0;
        }
        int i2 = 7;
        for (int i3 : iArr) {
            i2 = (i2 * 31) + i3;
        }
        return i2;
    }
    public static int nullSafeHashCode(long[] jArr) {
        if (jArr == null) {
            return 0;
        }
        int iHashCode = 7;
        for (long j2 : jArr) {
            iHashCode = (iHashCode * 31) + hashCode(j2);
        }
        return iHashCode;
    }
    public static int nullSafeHashCode(short[] sArr) {
        if (sArr == null) {
            return 0;
        }
        int i2 = 7;
        for (short s : sArr) {
            i2 = (i2 * 31) + s;
        }
        return i2;
    }
    public static int hashCode(double d2) {
        return hashCode(Double.doubleToLongBits(d2));
    }
    public static int hashCode(float f2) {
        return Float.floatToIntBits(f2);
    }
    public static String nullSafeToString(Object obj) {
        if (obj == null) {
            return "null";
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof Object[]) {
            return nullSafeToString((Object[]) obj);
        }
        if (obj instanceof boolean[]) {
            return nullSafeToString((boolean[]) obj);
        }
        if (obj instanceof byte[]) {
            return nullSafeToString((byte[]) obj);
        }
        if (obj instanceof char[]) {
            return nullSafeToString((char[]) obj);
        }
        if (obj instanceof double[]) {
            return nullSafeToString((double[]) obj);
        }
        if (obj instanceof float[]) {
            return nullSafeToString((float[]) obj);
        }
        if (obj instanceof int[]) {
            return nullSafeToString((int[]) obj);
        }
        if (obj instanceof long[]) {
            return nullSafeToString((long[]) obj);
        }
        if (obj instanceof short[]) {
            return nullSafeToString((short[]) obj);
        }
        String string = obj.toString();
        return string != null ? string : "";
    }
    public static String nullSafeToString(Object[] objArr) {
        if (objArr == null) {
            return "null";
        }
        int length = objArr.length;
        if (length == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 == 0) {
                sb.append("{");
            } else {
                sb.append(", ");
            }
            sb.append(objArr[i2]);
        }
        sb.append("}");
        return sb.toString();
    }
    public static String nullSafeToString(boolean[] zArr) {
        if (zArr == null) {
            return "null";
        }
        int length = zArr.length;
        if (length == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 == 0) {
                sb.append("{");
            } else {
                sb.append(", ");
            }
            sb.append(zArr[i2]);
        }
        sb.append("}");
        return sb.toString();
    }
    public static String nullSafeToString(byte[] bArr) {
        if (bArr == null) {
            return "null";
        }
        int length = bArr.length;
        if (length == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 == 0) {
                sb.append("{");
            } else {
                sb.append(", ");
            }
            sb.append(bArr[i2]);
        }
        sb.append("}");
        return sb.toString();
    }
    public static String nullSafeToString(char[] cArr) {
        if (cArr == null) {
            return "null";
        }
        int length = cArr.length;
        if (length == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 == 0) {
                sb.append("{");
            } else {
                sb.append(", ");
            }
            sb.append("'");
            sb.append(cArr[i2]);
            sb.append("'");
        }
        sb.append("}");
        return sb.toString();
    }
    public static String nullSafeToString(double[] dArr) {
        if (dArr == null) {
            return "null";
        }
        int length = dArr.length;
        if (length == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 == 0) {
                sb.append("{");
            } else {
                sb.append(", ");
            }
            sb.append(dArr[i2]);
        }
        sb.append("}");
        return sb.toString();
    }
    public static String nullSafeToString(float[] fArr) {
        if (fArr == null) {
            return "null";
        }
        int length = fArr.length;
        if (length == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 == 0) {
                sb.append("{");
            } else {
                sb.append(", ");
            }
            sb.append(fArr[i2]);
        }
        sb.append("}");
        return sb.toString();
    }
    public static String nullSafeToString(int[] iArr) {
        if (iArr == null) {
            return "null";
        }
        int length = iArr.length;
        if (length == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 == 0) {
                sb.append("{");
            } else {
                sb.append(", ");
            }
            sb.append(iArr[i2]);
        }
        sb.append("}");
        return sb.toString();
    }
    public static String nullSafeToString(long[] jArr) {
        if (jArr == null) {
            return "null";
        }
        int length = jArr.length;
        if (length == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 == 0) {
                sb.append("{");
            } else {
                sb.append(", ");
            }
            sb.append(jArr[i2]);
        }
        sb.append("}");
        return sb.toString();
    }
    public static String nullSafeToString(short[] sArr) {
        if (sArr == null) {
            return "null";
        }
        int length = sArr.length;
        if (length == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 == 0) {
                sb.append("{");
            } else {
                sb.append(", ");
            }
            sb.append(sArr[i2]);
        }
        sb.append("}");
        return sb.toString();
    }
}
