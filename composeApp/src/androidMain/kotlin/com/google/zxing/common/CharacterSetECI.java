package com.google.zxing.common;

import com.google.zxing.FormatException;
import java.util.HashMap;
import java.util.Map;

public enum CharacterSetECI {

    Cp437(  new int[]{0, 2}),
    ISO8859_1( new int[]{1, 3}, "ISO-8859-1"),
    ISO8859_2( 4, new int[]{27, 170}, "ISO-8859-2"),
    ISO8859_3( 5, new int[]{27, 170}, "ISO-8859-3"),
    ISO8859_4( 6, new int[]{27, 170}, "ISO-8859-4"),
    ISO8859_5( 7, new int[]{27, 170}, "ISO-8859-5"),
    ISO8859_6( 8, new int[]{27, 170}, "ISO-8859-6"),
    ISO8859_7( 9, new int[]{27, 170}, "ISO-8859-7"),
    ISO8859_8( 10, new int[]{27, 170}, "ISO-8859-8"),
    ISO8859_9( 11, new int[]{27, 170}, "ISO-8859-9"),
    ISO8859_10( 12, new int[]{27, 170}, "ISO-8859-10"),
    ISO8859_11( 13, new int[]{27, 170}, "ISO-8859-11"),
    ISO8859_13( 15, new int[]{27, 170}, "ISO-8859-13"),
    ISO8859_14( 16, new int[]{27, 170}, "ISO-8859-14"),
    ISO8859_15( 17, new int[]{27, 170}, "ISO-8859-15"),
    ISO8859_16( 18, new int[]{27, 170}, "ISO-8859-16"),
    SJIS( 20, new int[]{27, 170}, "Shift_JIS"),
    Cp1250( 21, new int[]{27, 170}, "windows-1250"),
    Cp1251( 22, new int[]{27, 170}, "windows-1251"),
    Cp1252( 23, new int[]{27, 170}, "windows-1252"),
    Cp1256( 24, new int[]{27, 170}, "windows-1256"),
    UnicodeBigUnmarked( 25, new int[]{27, 170}, "UTF-16BE", "UnicodeBig"),
    UTF8( 26, new int[]{27, 170}, "UTF-8"),
    ASCII( 27,  new int[]{27, 170}, "US-ASCII"),
    Big5( 28, new int[]{27, 170}, "Big5"),
    GB18030( 29, new int[]{27, 170}, "GB2312", "EUC_CN", "GBK"),
    EUC_KR(  30, new int[]{27, 170}, "EUC-KR");
    
    private static final Map<String, CharacterSetECI> NAME_TO_ECI = new HashMap<>();
    private static final Map<Integer, CharacterSetECI> VALUE_TO_ECI = new HashMap<>();
    private final String[] otherEncodingNames;
    private final int[] values;
    static {
        for (final CharacterSetECI characterSetECI : CharacterSetECI.values()) {
            for (final int valueOf : characterSetECI.values) {
                VALUE_TO_ECI.put(valueOf, characterSetECI);
            }
            NAME_TO_ECI.put(characterSetECI.name(), characterSetECI);
            for (final String put : characterSetECI.otherEncodingNames) {
                NAME_TO_ECI.put(put, characterSetECI);
            }
        }
    }
    CharacterSetECI(final int i2) {
        this(i2, new int[]{27, 170});
    }
    CharacterSetECI(final int i2, int[] ints, final String... strArr) {
        values = new int[]{i2};
        otherEncodingNames = strArr;
    }
    CharacterSetECI(final int[] iArr, final String... strArr) {
        values = iArr;
        otherEncodingNames = strArr;
    }
    public int getValue() {
        return values[0];
    }
    public static CharacterSetECI getCharacterSetECIByValue(final int i2) throws FormatException {
        if (0 <= i2 && 900 > i2) {
            return CharacterSetECI.VALUE_TO_ECI.get(Integer.valueOf(i2));
        }
        throw FormatException.getFormatInstance();
    }
    public static CharacterSetECI getCharacterSetECIByName(final String str) {
        return CharacterSetECI.NAME_TO_ECI.get(str);
    }
}
