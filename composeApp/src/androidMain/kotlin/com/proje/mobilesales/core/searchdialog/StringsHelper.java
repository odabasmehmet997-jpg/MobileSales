package com.proje.mobilesales.core.searchdialog;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import java.lang.reflect.Array;



public enum StringsHelper {
    ;
    static final boolean assertionsDisabled = false;

    public static SpannableStringBuilder highlightLCS(final String str, final String str2, final int i2) {
        String lcs = StringsHelper.lcs(str.toLowerCase(), str2.toLowerCase());
        final String lowerCase = str.toLowerCase();
        final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(str);
        for (int i3 = 0; i3 < lowerCase.length() && 0 < lcs.length(); i3++) {
            if (lowerCase.charAt(i3) == lcs.charAt(0)) {
                spannableStringBuilder.setSpan(new ForegroundColorSpan(i2), i3, i3 + 1, 0);
                lcs = lcs.substring(1);
            }
        }
        return spannableStringBuilder;
    }

    public static String lcs(final String str, final String str2) {
        final int[][] iArr = (int[][]) Array.newInstance(Integer.TYPE, str.length() + 1, str2.length() + 1);
        for (int i2 = 0; i2 < str.length(); i2++) {
            for (int i3 = 0; i3 < str2.length(); i3++) {
                if (str.charAt(i2) == str2.charAt(i3)) {
                    iArr[i2 + 1][i3 + 1] = iArr[i2][i3] + 1;
                } else {
                    final int[] iArr2 = iArr[i2 + 1];
                    final int i4 = i3 + 1;
                    iArr2[i4] = Math.max(iArr2[i3], iArr[i2][i4]);
                }
            }
        }
        final StringBuffer stringBuffer = new StringBuffer();
        int length = str.length();
        int length2 = str2.length();
        while (0 != length && 0 != length2) {
            final int[] iArr3 = iArr[length];
            final int i5 = iArr3[length2];
            final int i6 = length - 1;
            if (i5 == iArr[i6][length2]) {
                length--;
            } else {
                if (i5 != iArr3[length2 - 1]) {
                    stringBuffer.append(str.charAt(i6));
                    length--;
                }
                length2--;
            }
        }
        return stringBuffer.reverse().toString();
    }
}
