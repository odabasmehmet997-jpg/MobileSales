package com.google.zxing.common;

import java.nio.charset.Charset;

public class StringUtils {
    private static final boolean ASSUME_SHIFT_JIS;
    private static final String PLATFORM_DEFAULT_ENCODING;
    static {
        final String name = Charset.defaultCharset().name();
        PLATFORM_DEFAULT_ENCODING = name;
        ASSUME_SHIFT_JIS = "SJIS".equalsIgnoreCase(name) || "EUC_JP".equalsIgnoreCase(name);
    }
    public static java.lang.String guessEncoding(final byte[] r21, final java.util.Map<com.google.zxing.DecodeHintType, ?> r22) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.common.StringUtils.guessEncoding(byte[], java.util.Map):java.lang.String");
    }
}
