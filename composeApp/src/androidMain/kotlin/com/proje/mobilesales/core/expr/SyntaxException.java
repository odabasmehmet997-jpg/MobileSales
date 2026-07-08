package com.proje.mobilesales.core.expr;

import com.fasterxml.jackson.core.JsonFactory;

public class SyntaxException extends Exception {
    public static final int BAD_FACTOR = 1;
    public static final int EXPECTED = 3;
    public static final int INCOMPLETE = 0;
    public static final int PREMATURE_EOF = 2;
    public static final int UNKNOWN_VARIABLE = 4;
    private final String expected;
    private String fixedInput;
    private final Parser parser;
    private final int reason;
    private final Scanner scanner;

    public SyntaxException(String str, Parser parser, int i2, String str2) {
        super(str);
        this.fixedInput = "";
        this.reason = i2;
        this.parser = parser;
        this.scanner = parser.tokens;
        this.expected = str2;
    }

    public String explain() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("I don't understand your formula ");
        quotify(stringBuffer, this.scanner.getInput());
        stringBuffer.append(".\n\n");
        explainWhere(stringBuffer);
        explainWhy(stringBuffer);
        explainWhat(stringBuffer);
        return stringBuffer.toString();
    }

    private void explainWhere(StringBuffer stringBuffer) {
        if (this.scanner.isEmpty()) {
            stringBuffer.append("It's empty!\n");
            return;
        }
        if (this.scanner.atStart()) {
            stringBuffer.append("It starts with ");
            quotify(stringBuffer, theToken());
            if (isLegalToken()) {
                stringBuffer.append(", which can never be the start of a formula.\n");
                return;
            } else {
                stringBuffer.append(", which is a meaningless symbol to me.\n");
                return;
            }
        }
        stringBuffer.append("I got as far as ");
        quotify(stringBuffer, asFarAs());
        stringBuffer.append(" and then ");
        if (this.scanner.atEnd()) {
            stringBuffer.append("reached the end unexpectedly.\n");
            return;
        }
        stringBuffer.append("saw ");
        quotify(stringBuffer, theToken());
        if (isLegalToken()) {
            stringBuffer.append(".\n");
        } else {
            stringBuffer.append(", which is a meaningless symbol to me.\n");
        }
    }

    private void explainWhy(StringBuffer stringBuffer) {
        int i2 = this.reason;
        if (i2 == 0) {
            if (isLegalToken()) {
                stringBuffer.append("The first part makes sense, but I don't see how the rest connects to it.\n");
                return;
            }
            return;
        }
        if (i2 == 1 || i2 == 2) {
            stringBuffer.append("I expected a value");
            if (!this.scanner.atStart()) {
                stringBuffer.append(" to follow");
            }
            stringBuffer.append(", instead.\n");
            return;
        }
        if (i2 != 3) {
            if (i2 == 4) {
                stringBuffer.append("That variable has no value.\n");
                return;
            }
            throw new Error("Can't happen");
        }
        stringBuffer.append("I expected ");
        quotify(stringBuffer, this.expected);
        stringBuffer.append(" at that point, instead.\n");
    }

    private void explainWhat(StringBuffer stringBuffer) {
        String tryToFix = tryToFix();
        this.fixedInput = tryToFix;
        if (tryToFix != null) {
            stringBuffer.append("An example of a formula I can parse is ");
            quotify(stringBuffer, this.fixedInput);
            stringBuffer.append(".\n");
        }
    }

    private String tryToFix() {
        if (this.parser.tryCorrections()) {
            return this.scanner.toString();
        }
        return null;
    }

    private void quotify(StringBuffer stringBuffer, String str) {
        stringBuffer.append(JsonFactory.DEFAULT_QUOTE_CHAR);
        stringBuffer.append(str);
        stringBuffer.append(JsonFactory.DEFAULT_QUOTE_CHAR);
    }

    private String asFarAs() {
        Token currentToken = this.scanner.getCurrentToken();
        return this.scanner.getInput().substring(0, currentToken.location - currentToken.leadingWhitespace);
    }

    private String theToken() {
        return this.scanner.getCurrentToken().sval;
    }

    private boolean isLegalToken() {
        int i2 = this.scanner.getCurrentToken().ttype;
        return i2 != -2 && i2 != -1;
    }
}
