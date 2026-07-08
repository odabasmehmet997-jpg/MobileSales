package com.google.android.gms.internal.location;

import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public enum zzes {
    ;

    public static String zza(final String str, final Object... objArr) {
        int length;
        int length2;
        int indexOf;
        String str2;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            length = objArr.length;
            if (i3 >= length) {
                break;
            }
            final Object obj = objArr[i3];
            if (null == obj) {
                str2 = "null";
            } else {
                try {
                    str2 = obj.toString();
                } catch (final Exception e2) {
                    final String name = obj.getClass().getName();
                    final String hexString = Integer.toHexString(System.identityHashCode(obj));
                    final String sb2 = name +
                            "@" +
                            hexString;
                    Logger.getLogger("com.google.common.base.Strings").logp(Level.WARNING, "com.google.common.base.Strings", "lenientToString", "Exception during lenientFormat for " + sb2, e2);
                    final String name2 = e2.getClass().getName();
                    String sb3 = "<" +
                            sb2 +
                            " threw " +
                            name2 +
                            ">";
                    str2 = sb3;
                }
            }
            objArr[i3] = str2;
            i3++;
        }
        final StringBuilder sb4 = new StringBuilder(str.length() + (length * 16));
        int i4 = 0;
        while (true) {
            length2 = objArr.length;
            if (i2 >= length2 || -1 == (indexOf = str.indexOf("%s", i4))) {
                sb4.append(str, i4, str.length());
            } else {
                sb4.append(str, i4, indexOf);
                sb4.append(objArr[i2]);
                i4 = indexOf + 2;
                i2++;
            }
        }
        sb4.append(str, i4, str.length());
        if (i2 < length2) {
            sb4.append(" [");
            sb4.append(objArr[i2]);
            for (int i5 = i2 + 1; i5 < objArr.length; i5++) {
                sb4.append(", ");
                sb4.append(objArr[i5]);
            }
            sb4.append(']');
        }
        return sb4.toString();
    }
}
