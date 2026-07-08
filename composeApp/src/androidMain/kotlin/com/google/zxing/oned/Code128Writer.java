package com.google.zxing.oned;

public final class Code128Writer extends OneDimensionalCodeWriter {

    private enum CType {
        UNCODABLE,
        ONE_DIGIT,
        TWO_DIGITS,
        FNC_1
    }
}
