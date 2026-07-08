package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;

public final class BinaryType {
    public static final BinaryType btIMAGE = new BinaryType();
    public static final BinaryType btSTRING = new BinaryType();
    public static final BinaryType btDOCUMENT = new BinaryType();

    private static  BinaryType[] values() {
        return new BinaryType[]{btIMAGE, btSTRING, btDOCUMENT};
    }
    public static Enum valueOf() {
        return null;
    }
    private static EnumEntries enumEntries() {
        return null;
    }
    private BinaryType() {
    }
}
