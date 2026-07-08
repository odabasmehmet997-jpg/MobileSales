package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public enum zzwe {
    ;

    public static Object zza(Object obj, String str) {
        if (null != obj) {
            return obj;
        }
        throw new NullPointerException(str + " must not be null");
    }

    public static String zzb(String str) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("identifier must not be empty");
        } else if (zzc(str.charAt(0))) {
            int i2 = 1;
            while (i2 < str.length()) {
                char charAt = str.charAt(i2);
                if (zzc(charAt) || (('0' <= charAt && '9' >= charAt) || '_' == charAt)) {
                    i2++;
                } else {
                    throw new IllegalArgumentException("identifier must contain only ASCII letters, digits or underscore: " + str);
                }
            }
            return str;
        } else {
            throw new IllegalArgumentException("identifier must start with an ASCII letter: " + str);
        }
    }

    private static boolean zzc(char c2) {
        if ('a' <= c2 && 'z' >= c2) {
            return true;
        }
        if ('A' <= c2) {
            return 'Z' >= c2;
        }
        return false;
    }
}
