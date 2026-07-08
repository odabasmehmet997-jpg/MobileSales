package com.proje.mobilesales.core.utils;

import java.util.List;

final class VersionComparator {
    private static final int ALPHA = 2;
    private static final String ALPHA_STRING = "alpha";
    private static final int BETA = 3;
    private static final String BETA_STRING = "beta";
    static final int MAJOR = 0;
    static final int MINOR = 1;
    static final int PATCH = 2;
    private static final int PRE_ALPHA = 1;
    private static final String PRE_STRING = "pre";
    private static final int RC = 4;
    private static final String RC_STRING = "rc";
    private static final int SNAPSHOT = 0;
    private static final String SNAPSHOT_STRING = "snapshot";
    private static final int UNKNOWN = 5;
    VersionComparator() {}
    static int compareSubversionNumbers( List<Integer> list,  List<Integer> list2) {
        int size = list.size();
        int size2 = list2.size();
        int max = Math.max(size, size2);
        int i2 = 0;
        while (i2 < max) {
            if ((i2 < size ? list.get(i2) : 0) > (i2 < size2 ? list2.get(i2) : 0)) {
                return 1;
            }
            if ((i2 < size ? list.get(i2) : 0) < (i2 < size2 ? list2.get(i2) : 0)) {
                return -1;
            }
            i2++;
        }
        return 0;
    }
    static int compareSuffix(  String str,  String str2) {
        if (str.length() <= 0 && str2.length() <= 0) {
            return 0;
        }
        int qualifierToNumber = qualifierToNumber(str);
        int qualifierToNumber2 = qualifierToNumber(str2);
        if (qualifierToNumber > qualifierToNumber2) {
            return 1;
        }
        if (qualifierToNumber < qualifierToNumber2) {
            return -1;
        }
        if (qualifierToNumber == 5 || qualifierToNumber == 0) {
            return 0;
        }
        int preReleaseVersion = preReleaseVersion(str, qualifierToNumber);
        int preReleaseVersion2 = preReleaseVersion(str2, qualifierToNumber2);
        if (preReleaseVersion > preReleaseVersion2) {
            return 1;
        }
        return preReleaseVersion < preReleaseVersion2 ? -1 : 0;
    }
    private static int qualifierToNumber( String str) {
        if (str.length() <= 0) {
            return 5;
        }
        String lowerCase = str.toLowerCase();
        if (lowerCase.contains(RC_STRING)) {
            return 4;
        }
        if (lowerCase.contains(BETA_STRING)) {
            return 3;
        }
        return lowerCase.contains("alpha") ? lowerCase.substring(0, lowerCase.indexOf("alpha")).contains(PRE_STRING) ? 1 : 2 : lowerCase.contains(SNAPSHOT_STRING) ? 0 : 5;
    }
    private static int preReleaseVersion(  String str, int i2) {
        int indexOfQualifier = indexOfQualifier(str, i2);
        if (indexOfQualifier >= str.length() || !containsNumeric(str.substring(indexOfQualifier, Math.min(indexOfQualifier + 2, str.length())))) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        for (int i3 = indexOfQualifier; i3 < str.length(); i3++) {
            char charAt = str.charAt(i3);
            if (!Character.isDigit(charAt)) {
                if (i3 != indexOfQualifier) {
                    break;
                }
            } else {
                sb.append(charAt);
            }
        }
        return safeParseInt(sb.toString());
    }
    private static int indexOfQualifier( String str, int i2) {
        if (i2 == 4) {
            return str.indexOf(RC_STRING) + 2;
        }
        if (i2 == 3) {
            return str.indexOf(BETA_STRING) + 4;
        }
        if (i2 == 2 || i2 == 1) {
            return str.indexOf("alpha") + 5;
        }
        return 0;
    }
    static boolean startsNumeric( String str) {
        String trim = str.trim();
        return trim.length() <= 0 || !Character.isDigit(trim.charAt(0));
    }
    static int safeParseInt(String str) {
        if (str.length() > 9) {
            str = str.substring(0, 9);
        }
        return Integer.parseInt(str);
    }
    static boolean isNumeric( CharSequence charSequence) {
        int length = charSequence.length();
        if (length <= 0) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (!Character.isDigit(charSequence.charAt(i2))) {
                return false;
            }
        }
        return true;
    }
    private static boolean containsNumeric( CharSequence charSequence) {
        int length = charSequence.length();
        if (length <= 0) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (Character.isDigit(charSequence.charAt(i2))) {
                return true;
            }
        }
        return false;
    }
}
