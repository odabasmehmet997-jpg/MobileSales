package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.Version;
import java.util.regex.Pattern;

public class VersionUtil {
    private static final Pattern V_SEP = Pattern.compile("[-_./;:]");
    protected VersionUtil() {
    }
    public static Version parseVersion(final String str, final String str2, final String str3) {
        if (null != str) {
            final String strTrim = str.trim();
            if (0 < strTrim.length()) {
                final String[] strArrSplit = VersionUtil.V_SEP.split(strTrim);
                return new Version(VersionUtil.parseVersionPart(strArrSplit[0]), 1 < strArrSplit.length ? VersionUtil.parseVersionPart(strArrSplit[1]) : 0, 2 < strArrSplit.length ? VersionUtil.parseVersionPart(strArrSplit[2]) : 0, 3 < strArrSplit.length ? strArrSplit[3] : null, str2, str3);
            }
        }
        return Version.unknownVersion();
    }
    protected static int parseVersionPart(final String str) {
        final int length = str.length();
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            final char cCharAt = str.charAt(i3);
            if ('9' < cCharAt || '0' > cCharAt) {
                break;
            }
            i2 = (i2 * 10) + (cCharAt - '0');
        }
        return i2;
    }
    public static final void throwInternal() {
        throw new RuntimeException("Internal error: this code path should never get executed");
    }
}
