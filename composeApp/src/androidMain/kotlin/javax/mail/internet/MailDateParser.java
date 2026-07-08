package javax.mail.internet;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.material.card.MaterialCardViewHelper;

import java.text.ParseException;

class MailDateParser {
    int index;
    char[] orig;

    public MailDateParser(char[] cArr, int i2) {
        this.orig = cArr;
        this.index = i2;
    }

    public void skipUntilNumber() throws ParseException {
        while (true) {
            try {
                char[] cArr = this.orig;
                int i2 = this.index;
                switch (cArr[i2]) {
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        return;
                    default:
                        this.index = i2 + 1;
                }
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParseException("No Number Found", this.index);
            }
        }
    }

    public void skipWhiteSpace() {
        int length = this.orig.length;
        while (true) {
            int i2 = this.index;
            if (i2 < length) {
                char c2 = this.orig[i2];
                if (9 == c2 || 10 == c2 || 13 == c2 || ' ' == c2) {
                    this.index = i2 + 1;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    public int peekChar() throws ParseException {
        int i2 = this.index;
        char[] cArr = this.orig;
        if (i2 < cArr.length) {
            return cArr[i2];
        }
        throw new ParseException("No more characters", this.index);
    }

    public void skipChar(char c2) throws ParseException {
        int i2 = this.index;
        char[] cArr = this.orig;
        if (i2 >= cArr.length) {
            throw new ParseException("No more characters", this.index);
        } else if (cArr[i2] == c2) {
            this.index = i2 + 1;
        } else {
            throw new ParseException("Wrong char", this.index);
        }
    }

    public boolean skipIfChar(char c2) throws ParseException {
        int i2 = this.index;
        char[] cArr = this.orig;
        if (i2 >= cArr.length) {
            throw new ParseException("No more characters", this.index);
        } else if (cArr[i2] != c2) {
            return false;
        } else {
            this.index = i2 + 1;
            return true;
        }
    }

    public int parseNumber() throws ParseException {
        int length = this.orig.length;
        boolean z = false;
        int i2 = 0;
        while (true) {
            int i3 = this.index;
            if (i3 < length) {
                switch (this.orig[i3]) {
                    case '0':
                        i2 *= 10;
                        break;
                    case '1':
                        i2 = (i2 * 10) + 1;
                        break;
                    case '2':
                        i2 = (i2 * 10) + 2;
                        break;
                    case '3':
                        i2 = (i2 * 10) + 3;
                        break;
                    case '4':
                        i2 = (i2 * 10) + 4;
                        break;
                    case '5':
                        i2 = (i2 * 10) + 5;
                        break;
                    case '6':
                        i2 = (i2 * 10) + 6;
                        break;
                    case '7':
                        i2 = (i2 * 10) + 7;
                        break;
                    case '8':
                        i2 = (i2 * 10) + 8;
                        break;
                    case '9':
                        i2 = (i2 * 10) + 9;
                        break;
                    default:
                        if (z) {
                            return i2;
                        }
                        throw new ParseException("No Number found", this.index);
                }
                this.index = i3 + 1;
                z = true;
            } else if (z) {
                return i2;
            } else {
                throw new ParseException("No Number found", this.index);
            }
        }
    }

    public int parseMonth() throws ParseException {
        /*
            r16 = this;
            r0 = r16
            char[] r1 = r0.orig     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            int r2 = r0.index     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            int r3 = r2 + 1
            r0.index = r3     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            char r4 = r1[r2]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            r7 = 114(0x72, float:1.6E-43)
            r8 = 82
            r10 = 80
            r11 = 65
            r12 = 3
            if (r4 == r11) goto L_0x0121
            r13 = 68
            r14 = 99
            r15 = 67
            r5 = 101(0x65, float:1.42E-43)
            r6 = 69
            if (r4 == r13) goto L_0x010b
            r13 = 70
            if (r4 == r13) goto L_0x00f2
            r13 = 74
            r9 = 97
            if (r4 == r13) goto L_0x00bb
            r13 = 83
            if (r4 == r13) goto L_0x00a3
            if (r4 == r9) goto L_0x0121
            r13 = 100
            if (r4 == r13) goto L_0x010b
            r13 = 102(0x66, float:1.43E-43)
            if (r4 == r13) goto L_0x00f2
            r13 = 106(0x6a, float:1.49E-43)
            if (r4 == r13) goto L_0x00bb
            r13 = 115(0x73, float:1.61E-43)
            if (r4 == r13) goto L_0x00a3
            switch(r4) {
                case 77: goto L_0x0083;
                case 78: goto L_0x0065;
                case 79: goto L_0x004b;
                default: goto L_0x0046;
            }     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
        L_0x0046:
            switch(r4) {
                case 109: goto L_0x0083;
                case 110: goto L_0x0065;
                case 111: goto L_0x004b;
                default: goto L_0x0049;
            }     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
        L_0x0049:
            goto L_0x014f
        L_0x004b:
            int r4 = r2 + 2
            r0.index = r4     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            char r3 = r1[r3]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            if (r3 == r15) goto L_0x0055
            if (r3 != r14) goto L_0x014f
        L_0x0055:
            int r2 = r2 + r12
            r0.index = r2     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            char r1 = r1[r4]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            r2 = 84
            if (r1 == r2) goto L_0x0062
            r2 = 116(0x74, float:1.63E-43)
            if (r1 != r2) goto L_0x014f
        L_0x0062:
            r1 = 9
            return r1
        L_0x0065:
            int r4 = r2 + 2
            r0.index = r4     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            char r3 = r1[r3]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            r5 = 79
            if (r3 == r5) goto L_0x0073
            r5 = 111(0x6f, float:1.56E-43)
            if (r3 != r5) goto L_0x014f
        L_0x0073:
            int r2 = r2 + r12
            r0.index = r2     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            char r1 = r1[r4]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            r2 = 86
            if (r1 == r2) goto L_0x0080
            r2 = 118(0x76, float:1.65E-43)
            if (r1 != r2) goto L_0x014f
        L_0x0080:
            r1 = 10
            return r1
        L_0x0083:
            int r4 = r2 + 2
            r0.index = r4     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            char r3 = r1[r3]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            if (r3 == r11) goto L_0x008d
            if (r3 != r9) goto L_0x014f
        L_0x008d:
            int r2 = r2 + r12
            r0.index = r2     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            char r1 = r1[r4]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            if (r1 == r8) goto L_0x00a1
            if (r1 != r7) goto L_0x0097
            goto L_0x00a1
        L_0x0097:
            r2 = 89
            if (r1 == r2) goto L_0x009f
            r2 = 121(0x79, float:1.7E-43)
            if (r1 != r2) goto L_0x014f
        L_0x009f:
            r1 = 4
            return r1
        L_0x00a1:
            r1 = 2
            return r1
        L_0x00a3:
            int r4 = r2 + 2
            r0.index = r4     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            char r3 = r1[r3]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            if (r3 == r6) goto L_0x00ad
            if (r3 != r5) goto L_0x014f
        L_0x00ad:
            int r2 = r2 + r12
            r0.index = r2     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            char r1 = r1[r4]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            if (r1 == r10) goto L_0x00b8
            r2 = 112(0x70, float:1.57E-43)
            if (r1 != r2) goto L_0x014f
        L_0x00b8:
            r1 = 8
            return r1
        L_0x00bb:
            int r4 = r2 + 2
            r0.index = r4     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            char r3 = r1[r3]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            r5 = 110(0x6e, float:1.54E-43)
            r6 = 78
            if (r3 == r11) goto L_0x00e7
            r7 = 85
            if (r3 == r7) goto L_0x00d1
            if (r3 == r9) goto L_0x00e7
            r7 = 117(0x75, float:1.64E-43)
            if (r3 != r7) goto L_0x014f
        L_0x00d1:
            int r2 = r2 + r12
            r0.index = r2     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            char r1 = r1[r4]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            if (r1 == r6) goto L_0x00e5
            if (r1 != r5) goto L_0x00db
            goto L_0x00e5
        L_0x00db:
            r2 = 76
            if (r1 == r2) goto L_0x00e3
            r2 = 108(0x6c, float:1.51E-43)
            if (r1 != r2) goto L_0x014f
        L_0x00e3:
            r1 = 6
            return r1
        L_0x00e5:
            r1 = 5
            return r1
        L_0x00e7:
            int r2 = r2 + r12
            r0.index = r2     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            char r1 = r1[r4]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            if (r1 == r6) goto L_0x00f0
            if (r1 != r5) goto L_0x014f
        L_0x00f0:
            r1 = 0
            return r1
        L_0x00f2:
            int r4 = r2 + 2
            r0.index = r4     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            char r3 = r1[r3]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            if (r3 == r6) goto L_0x00fc
            if (r3 != r5) goto L_0x014f
        L_0x00fc:
            int r2 = r2 + r12
            r0.index = r2     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            char r1 = r1[r4]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            r2 = 66
            if (r1 == r2) goto L_0x0109
            r2 = 98
            if (r1 != r2) goto L_0x014f
        L_0x0109:
            r1 = 1
            return r1
        L_0x010b:
            int r4 = r2 + 2
            r0.index = r4     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            char r3 = r1[r3]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            if (r3 == r6) goto L_0x0115
            if (r3 != r5) goto L_0x014f
        L_0x0115:
            int r2 = r2 + r12
            r0.index = r2     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            char r1 = r1[r4]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            if (r1 == r15) goto L_0x011e
            if (r1 != r14) goto L_0x014f
        L_0x011e:
            r1 = 11
            return r1
        L_0x0121:
            int r4 = r2 + 2
            r0.index = r4     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            char r3 = r1[r3]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            if (r3 == r10) goto L_0x0145
            r5 = 112(0x70, float:1.57E-43)
            if (r3 != r5) goto L_0x012e
            goto L_0x0145
        L_0x012e:
            r5 = 85
            if (r3 == r5) goto L_0x0136
            r5 = 117(0x75, float:1.64E-43)
            if (r3 != r5) goto L_0x014f
        L_0x0136:
            int r2 = r2 + r12
            r0.index = r2     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            char r1 = r1[r4]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            r2 = 71
            if (r1 == r2) goto L_0x0143
            r2 = 103(0x67, float:1.44E-43)
            if (r1 != r2) goto L_0x014f
        L_0x0143:
            r1 = 7
            return r1
        L_0x0145:
            int r2 = r2 + r12
            r0.index = r2     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            char r1 = r1[r4]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x014f }
            if (r1 == r8) goto L_0x014e
            if (r1 != r7) goto L_0x014f
        L_0x014e:
            return r12
        L_0x014f:
            java.text.ParseException r1 = new java.text.ParseException
            java.lang.String r2 = "Bad Month"
            int r3 = r0.index
            r1.<init>(r2, r3)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.MailDateParser.parseMonth():int");
    }

    public int parseTimeZone() throws ParseException {
        int i2 = this.index;
        char[] cArr = this.orig;
        if (i2 < cArr.length) {
            char c2 = cArr[i2];
            if ('+' == c2 || '-' == c2) {
                return parseNumericTimeZone();
            }
            return parseAlphaTimeZone();
        }
        throw new ParseException("No more characters", this.index);
    }

    public int parseNumericTimeZone() throws ParseException {
        boolean z;
        char[] cArr = this.orig;
        int i2 = this.index;
        int i3 = i2 + 1;
        this.index = i3;
        char c2 = cArr[i2];
        if ('+' == c2) {
            z = true;
        } else if ('-' == c2) {
            z = false;
        } else {
            throw new ParseException("Bad Numeric TimeZone", this.index);
        }
        int parseNumber = parseNumber();
        if (2400 > parseNumber) {
            int i4 = ((parseNumber / 100) * 60) + (parseNumber % 100);
            return z ? -i4 : i4;
        }
        throw new ParseException("Numeric TimeZone out of range", i3);
    }

    public int parseAlphaTimeZone() throws ParseException {
        try {
            char[] cArr = this.orig;
            int i2 = this.index;
            int i3 = i2 + 1;
            this.index = i3;
            boolean z = true;
            int i4 = 0;
            switch (cArr[i2]) {
                case 'C':
                case 'c':
                    i4 = 360;
                    break;
                case 'E':
                case 'e':
                    i4 = MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION;
                    break;
                case 'G':
                case 'g':
                    int i5 = i2 + 2;
                    this.index = i5;
                    char c2 = cArr[i3];
                    if ('M' == c2 || 'm' == c2) {
                        this.index = i2 + 3;
                        char c3 = cArr[i5];
                        if ('T' != c3) {
                            if ('t' == c3) {
                                break;
                            }
                        }
                    }
                    throw new ParseException("Bad Alpha TimeZone", this.index);
                case 'M':
                case 'm':
                    i4 = TypedValues.CycleType.TYPE_EASING;
                    break;
                case 'P':
                case 'p':
                    i4 = 480;
                    break;
                case 'U':
                case 'u':
                    this.index = i2 + 2;
                    char c4 = cArr[i3];
                    if ('T' != c4) {
                        if ('t' == c4) {
                            break;
                        } else {
                            throw new ParseException("Bad Alpha TimeZone", this.index);
                        }
                    }
                    break;
                default:
                    throw new ParseException("Bad Alpha TimeZone", this.index);
            }
            z = false;
            if (!z) {
                return i4;
            }
            int i6 = this.index;
            int i7 = i6 + 1;
            this.index = i7;
            char c5 = cArr[i6];
            if ('S' == c5 || 's' == c5) {
                this.index = i6 + 2;
                char c6 = cArr[i7];
                if ('T' == c6 || 't' == c6) {
                    return i4;
                }
                throw new ParseException("Bad Alpha TimeZone", this.index);
            } else if ('D' != c5 && 'd' != c5) {
                return i4;
            } else {
                this.index = i6 + 2;
                char c7 = cArr[i7];
                if ('T' == c7 || 't' != c7) {
                    return i4 - 60;
                }
                throw new ParseException("Bad Alpha TimeZone", this.index);
            }
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ParseException("Bad Alpha TimeZone", this.index);
        }
    }

    
    public int getIndex() {
        return this.index;
    }
}
