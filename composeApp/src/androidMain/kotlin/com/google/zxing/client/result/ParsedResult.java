package com.google.zxing.client.result;

public abstract class ParsedResult {
    public abstract String getDisplayResult();
    public final String toString() {
        return getDisplayResult();
    }
    public static void maybeAppend(String str, StringBuilder sb) {
        if (null != str && !str.isEmpty()) {
            if (0 < sb.length()) {
                sb.append(10);
            }
            sb.append(str);
        }
    }
    public static void maybeAppend(String[] strArr, StringBuilder sb) {
        if (null != strArr) {
            for (String maybeAppend : strArr) {
                maybeAppend(maybeAppend, sb);
            }
        }
    }
}
