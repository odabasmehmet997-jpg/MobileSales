package javax.mail.internet;

public class HeaderTokenizer {
    private static final Token EOFToken = new Token(-4, null);
    public static final String MIME = "()<>@,;:\\\"\t []/?=";
    public static final String RFC822 = "()<>@,;:\\\"\t .[]";
    private int currentPos;
    private final String delimiters;
    private final int maxPos;
    private int nextPos;
    private int peekPos;
    private final boolean skipComments;
    private final String string;

    public record Token(int type, String value) {
            public static final int ATOM = -1;
            public static final int COMMENT = -3;
            public static final int EOF = -4;
            public static final int QUOTEDSTRING = -2;
    }

    public HeaderTokenizer(String str, String str2, boolean z) {
        str = null == str ? "" : str;
        this.string = str;
        this.skipComments = z;
        this.delimiters = str2;
        this.peekPos = 0;
        this.nextPos = 0;
        this.currentPos = 0;
        this.maxPos = str.length();
    }

    public HeaderTokenizer(String str, String str2) {
        this(str, str2, true);
    }

    public HeaderTokenizer(String str) {
        this(str, RFC822);
    }

    public Token next() throws ParseException {
        return next((char) 0, false);
    }

    
    public Token next(char c2) throws ParseException {
        return next(c2, false);
    }

    
    public Token next(char c2, boolean z) throws ParseException {
        this.currentPos = this.nextPos;
        Token next = getNext(c2, z);
        int i2 = this.currentPos;
        this.peekPos = i2;
        this.nextPos = i2;
        return next;
    }

    public Token peek() throws ParseException {
        this.currentPos = this.peekPos;
        Token next = getNext((char) 0, false);
        this.peekPos = this.currentPos;
        return next;
    }

    public String getRemainder() {
        return this.string.substring(this.nextPos);
    }

    private Token getNext(char r11, boolean r12) throws ParseException {
        /*
            r10 = this;
            r0 = -1
            r1 = 0
            r2 = 1
            int r3 = r10.currentPos
            int r4 = r10.maxPos
            if (r3 < r4) goto L_0x000c
            javax.mail.internet.HeaderTokenizerToken r11 = EOFToken
            return r11
        L_0x000c:
            int r3 = r10.skipWhiteSpace()
            r4 = -4
            if (r3 != r4) goto L_0x0016
            javax.mail.internet.HeaderTokenizerToken r11 = EOFToken
            return r11
        L_0x0016:
            java.lang.String r3 = r10.string
            int r5 = r10.currentPos
            char r3 = r3.charAt(r5)
            r5 = r1
        L_0x001f:
            r6 = 40
            if (r3 != r6) goto L_0x0092
            int r3 = r10.currentPos
            int r3 = r3 + r2
            r10.currentPos = r3
            r7 = r2
        L_0x0029:
            if (r7 <= 0) goto L_0x0056
            int r8 = r10.currentPos
            int r9 = r10.maxPos
            if (r8 >= r9) goto L_0x0056
            java.lang.String r9 = r10.string
            char r8 = r9.charAt(r8)
            r9 = 92
            if (r8 != r9) goto L_0x0042
            int r5 = r10.currentPos
            int r5 = r5 + r2
            r10.currentPos = r5
        L_0x0040:
            r5 = r2
            goto L_0x0050
        L_0x0042:
            r9 = 13
            if (r8 != r9) goto L_0x0047
            goto L_0x0040
        L_0x0047:
            if (r8 != r6) goto L_0x004b
            int r7 = r7 + r2
            goto L_0x0050
        L_0x004b:
            r9 = 41
            if (r8 != r9) goto L_0x0050
            int r7 = r7 + r0
        L_0x0050:
            int r8 = r10.currentPos
            int r8 = r8 + r2
            r10.currentPos = r8
            goto L_0x0029
        L_0x0056:
            if (r7 != 0) goto L_0x008a
            boolean r6 = r10.skipComments
            if (r6 != 0) goto L_0x0078
            if (r5 == 0) goto L_0x0068
            java.lang.String r11 = r10.string
            int r0 = r10.currentPos
            int r0 = r0 - r2
            java.lang.String r11 = filterToken(r11, r3, r0, r12)
            goto L_0x0071
        L_0x0068:
            java.lang.String r11 = r10.string
            int r12 = r10.currentPos
            int r12 = r12 - r2
            java.lang.String r11 = r11.substring(r3, r12)
        L_0x0071:
            javax.mail.internet.HeaderTokenizerToken r12 = new javax.mail.internet.HeaderTokenizerToken
            r0 = -3
            r12.<init>(r0, r11)
            return r12
        L_0x0078:
            int r3 = r10.skipWhiteSpace()
            if (r3 != r4) goto L_0x0081
            javax.mail.internet.HeaderTokenizerToken r11 = EOFToken
            return r11
        L_0x0081:
            java.lang.String r3 = r10.string
            int r6 = r10.currentPos
            char r3 = r3.charAt(r6)
            goto L_0x001f
        L_0x008a:
            javax.mail.internet.ParseException r11 = new javax.mail.internet.ParseException
            java.lang.String r12 = "Unbalanced comments"
            r11.<init>(r12)
            throw r11
        L_0x0092:
            r4 = 34
            if (r3 != r4) goto L_0x00a0
            int r11 = r10.currentPos
            int r11 = r11 + r2
            r10.currentPos = r11
            javax.mail.internet.HeaderTokenizerToken r11 = r10.collectString(r4, r12)
            return r11
        L_0x00a0:
            r5 = 32
            if (r3 < r5) goto L_0x00f1
            r7 = 127(0x7f, float:1.78E-43)
            if (r3 >= r7) goto L_0x00f1
            java.lang.String r8 = r10.delimiters
            int r8 = r8.indexOf(r3)
            if (r8 < 0) goto L_0x00b1
            goto L_0x00f1
        L_0x00b1:
            int r1 = r10.currentPos
        L_0x00b3:
            int r3 = r10.currentPos
            int r8 = r10.maxPos
            if (r3 >= r8) goto L_0x00e3
            java.lang.String r8 = r10.string
            char r3 = r8.charAt(r3)
            if (r3 < r5) goto L_0x00d8
            if (r3 >= r7) goto L_0x00d8
            if (r3 == r6) goto L_0x00d8
            if (r3 == r5) goto L_0x00d8
            if (r3 == r4) goto L_0x00d8
            java.lang.String r8 = r10.delimiters
            int r8 = r8.indexOf(r3)
            if (r8 < 0) goto L_0x00d2
            goto L_0x00d8
        L_0x00d2:
            int r3 = r10.currentPos
            int r3 = r3 + r2
            r10.currentPos = r3
            goto L_0x00b3
        L_0x00d8:
            if (r11 <= 0) goto L_0x00e3
            if (r3 == r11) goto L_0x00e3
            r10.currentPos = r1
            javax.mail.internet.HeaderTokenizerToken r11 = r10.collectString(r11, r12)
            return r11
        L_0x00e3:
            javax.mail.internet.HeaderTokenizerToken r11 = new javax.mail.internet.HeaderTokenizerToken
            java.lang.String r12 = r10.string
            int r2 = r10.currentPos
            java.lang.String r12 = r12.substring(r1, r2)
            r11.<init>(r0, r12)
            return r11
        L_0x00f1:
            if (r11 <= 0) goto L_0x00fa
            if (r3 == r11) goto L_0x00fa
            javax.mail.internet.HeaderTokenizerToken r11 = r10.collectString(r11, r12)
            return r11
        L_0x00fa:
            int r11 = r10.currentPos
            int r11 = r11 + r2
            r10.currentPos = r11
            char[] r11 = new char[r2]
            r11[r1] = r3
            javax.mail.internet.HeaderTokenizerToken r12 = new javax.mail.internet.HeaderTokenizerToken
            java.lang.String r0 = new java.lang.String
            r0.<init>(r11)
            r12.<init>(r3, r0)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.HeaderTokenizer.getNext(char, boolean):javax.mail.internet.HeaderTokenizerToken");
    }

    private Token collectString(char c2, boolean z) throws ParseException {
        String str;
        String str2;
        int i2 = this.currentPos;
        boolean z2 = false;
        while (true) {
            int i3 = this.currentPos;
            if (i3 < this.maxPos) {
                char charAt = this.string.charAt(i3);
                if ('\\' == charAt) {
                    this.currentPos++;
                } else if (13 != charAt) {
                    if (charAt == c2) {
                        int i4 = this.currentPos;
                        this.currentPos = i4 + 1;
                        if (z2) {
                            str2 = filterToken(this.string, i2, i4, z);
                        } else {
                            str2 = this.string.substring(i2, i4);
                        }
                        if ('\"' != charAt) {
                            str2 = trimWhiteSpace(str2);
                            this.currentPos--;
                        }
                        return new Token(-2, str2);
                    }
                    this.currentPos++;
                }
                z2 = true;
                this.currentPos++;
            } else if ('\"' != c2) {
                if (z2) {
                    str = filterToken(this.string, i2, i3, z);
                } else {
                    str = this.string.substring(i2, i3);
                }
                return new Token(-2, trimWhiteSpace(str));
            } else {
                throw new ParseException("Unbalanced quoted string");
            }
        }
    }

    private int skipWhiteSpace() {
        while (true) {
            int i2 = this.currentPos;
            if (i2 >= this.maxPos) {
                return -4;
            }
            char charAt = this.string.charAt(i2);
            if (' ' != charAt && 9 != charAt && 13 != charAt && 10 != charAt) {
                return this.currentPos;
            }
            this.currentPos++;
        }
    }

    private static String trimWhiteSpace(String str) {
        int length = str.length() - 1;
        while (0 <= length) {
            char charAt = str.charAt(length);
            if (' ' != charAt && 9 != charAt && 13 != charAt && 10 != charAt) {
                break;
            }
            length--;
        }
        if (0 >= length) {
            return "";
        }
        return str.substring(0, length + 1);
    }

    private static String filterToken(String str, int i2, int i3, boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        boolean z2 = false;
        boolean z3 = false;
        while (i2 < i3) {
            char charAt = str.charAt(i2);
            if (10 != charAt || !z2) {
                if (z3) {
                    if (z) {
                        stringBuffer.append('\\');
                    }
                    stringBuffer.append(charAt);
                    z2 = false;
                    z3 = false;
                } else if ('\\' == charAt) {
                    z2 = false;
                    z3 = true;
                } else if (13 == charAt) {
                    z2 = true;
                } else {
                    stringBuffer.append(charAt);
                }
                i2++;
            }
            z2 = false;
            i2++;
        }
        return stringBuffer.toString();
    }
}
