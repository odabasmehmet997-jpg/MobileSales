package com.proje.mobilesales.core.expr;

import java.util.Vector;

class Scanner {
    private final String operatorChars;

    private final String f1184s;
    Vector tokens = new Vector();
    int index = -1;

    public Scanner(String str, String str2) {
        this.f1184s = str;
        this.operatorChars = str2;
        int i2 = 0;
        do {
            i2 = scanToken(i2);
        } while (i2 < this.f1184s.length());
    }

    public String getInput() {
        return this.f1184s;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        int i2 = 0;
        for (int i3 = 0; i3 < this.tokens.size(); i3++) {
            Token token = (Token) this.tokens.elementAt(i3);
            if (i2 == 0) {
                i2 = token.leadingWhitespace;
            }
            if (i3 == 0) {
                i2 = 0;
            } else if (i2 == 0 && !joinable((Token) this.tokens.elementAt(i3 - 1), token)) {
                i2 = 1;
            }
            while (i2 > 0) {
                stringBuffer.append(" ");
                i2--;
            }
            stringBuffer.append(token.sval);
            i2 = token.trailingWhitespace;
        }
        return stringBuffer.toString();
    }

    private boolean joinable(Token token, Token token2) {
        return !isAlphanumeric(token) || !isAlphanumeric(token2);
    }

    private boolean isAlphanumeric(Token token) {
        int i2 = token.ttype;
        return i2 == -4 || i2 == -3;
    }

    public boolean isEmpty() {
        return this.tokens.size() == 0;
    }

    public boolean atStart() {
        return this.index <= 0;
    }

    public boolean atEnd() {
        return this.tokens.size() <= this.index;
    }

    public Token nextToken() {
        this.index++;
        return getCurrentToken();
    }

    public Token getCurrentToken() {
        if (atEnd()) {
            String str = this.f1184s;
            return new Token(-2, 0.0d, str, str.length(), this.f1184s.length());
        }
        return (Token) this.tokens.elementAt(this.index);
    }

    private int scanToken(int i2) {
        int i3;
        int i4 = i2;
        while (i4 < this.f1184s.length() && Character.isWhitespace(this.f1184s.charAt(i4))) {
            i4++;
        }
        if (i4 == this.f1184s.length()) {
            return i4;
        }
        if (this.operatorChars.indexOf(this.f1184s.charAt(i4)) >= 0) {
            int i5 = i4 + 1;
            if (i5 < this.f1184s.length()) {
                int i6 = i4 + 2;
                String substring = this.f1184s.substring(i4, i6);
                if (substring.equals("<=")) {
                    i3 = -5;
                } else if (substring.equals(">=")) {
                    i3 = -7;
                } else {
                    i3 = substring.equals("<>") ? -6 : 0;
                }
                int i7 = i3;
                if (i7 != 0) {
                    this.tokens.addElement(new Token(i7, 0.0d, this.f1184s, i4, i6));
                    return i6;
                }
            }
            this.tokens.addElement(new Token(this.f1184s.charAt(i4), 0.0d, this.f1184s, i4, i5));
            return i5;
        }
        if (Character.isLetter(this.f1184s.charAt(i4))) {
            return scanSymbol(i4);
        }
        if (Character.isDigit(this.f1184s.charAt(i4)) || '.' == this.f1184s.charAt(i4)) {
            return scanNumber(i4);
        }
        int i8 = i4 + 1;
        this.tokens.addElement(makeErrorToken(i4, i8));
        return i8;
    }

    private int scanSymbol(int i2) {
        int i3 = i2;
        while (i3 < this.f1184s.length() && (Character.isLetter(this.f1184s.charAt(i3)) || Character.isDigit(this.f1184s.charAt(i3)))) {
            i3++;
        }
        this.tokens.addElement(new Token(-4, 0.0d, this.f1184s, i2, i3));
        return i3;
    }

    private int scanNumber(int i2) {
        int i3 = i2;
        while (i3 < this.f1184s.length() && ('.' == this.f1184s.charAt(i3) || Character.isDigit(this.f1184s.charAt(i3)) || Character.isLetter(this.f1184s.charAt(i3)))) {
            i3++;
        }
        try {
            this.tokens.addElement(new Token(-3, Double.valueOf(this.f1184s.substring(i2, i3)).doubleValue(), this.f1184s, i2, i3));
            return i3;
        } catch (NumberFormatException unused) {
            this.tokens.addElement(makeErrorToken(i2, i3));
            return i3;
        }
    }

    private Token makeErrorToken(int i2, int i3) {
        return new Token(-1, 0.0d, this.f1184s, i2, i3);
    }
}
