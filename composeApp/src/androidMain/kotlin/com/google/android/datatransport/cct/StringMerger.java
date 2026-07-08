package com.google.android.datatransport.cct;

public class StringMerger {
    static String mergeStrings(String str, String str2) {
        int length = str.length() - str2.length();
        if (0 > length || 1 < length) {
            throw new IllegalArgumentException("Invalid input received");
        }
        StringBuilder sb = new StringBuilder(str.length() + str2.length());
        for (int i2 = 0; i2 < str.length(); i2++) {
            sb.append(str.charAt(i2));
            if (str2.length() > i2) {
                sb.append(str2.charAt(i2));
            }
        }
        return sb.toString();
    }
}
