package com.proje.mobilesales.core.expr;

class Token {
    public static final int TT_EOF = -2;
    public static final int TT_ERROR = -1;
    public static final int TT_GE = -7;
    public static final int TT_LE = -5;
    public static final int TT_NE = -6;
    public static final int TT_NUMBER = -3;
    public static final int TT_WORD = -4;
    public final int leadingWhitespace;
    public final int location;
    public final double nval;
    public final String sval;
    public final int trailingWhitespace;
    public final int ttype;

    public Token(int i2, double d2, String str, int i3, int i4) {
        this.ttype = i2;
        this.sval = str.substring(i3, i4);
        this.nval = d2;
        this.location = i3;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = i3 - 1; i7 >= 0 && Character.isWhitespace(str.charAt(i7)); i7--) {
            i6++;
        }
        this.leadingWhitespace = i6;
        while (i4 < str.length() && Character.isWhitespace(str.charAt(i4))) {
            i5++;
            i4++;
        }
        this.trailingWhitespace = i5;
    }

    Token(int i2, double d2, String str, Token token) {
        this.ttype = i2;
        this.sval = str;
        this.nval = d2;
        this.location = token.location;
        this.leadingWhitespace = token.leadingWhitespace;
        this.trailingWhitespace = token.trailingWhitespace;
    }
}
