package com.sun.activation.registries;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.os.EnvironmentCompat;

public class MailcapTokenizer {
    private final char autoquoteChar;
    private int currentToken;
    private String currentTokenValue;
    private final String data;
    private int dataIndex;
    private final int dataLength;
    private boolean isAutoquoting;
    private static boolean isSpecialChar(final char c2) {
        if (!('\"' == c2 || ',' == c2 || '/' == c2 || '(' == c2 || ')' == c2)) {
            switch (c2) {
                case ':':
                case ';':
                case '<':
                case '=':
                case '>':
                case '?':
                case '@':
                    break;
                default:
                    switch (c2) {
                        case '[':
                        case '\\':
                        case ']':
                            break;
                        default:
                            return false;
                    }
            }
        }
        return true;
    }
    public MailcapTokenizer(final String str) {
        data = str;
        dataLength = str.length();
        currentToken = 1;
        currentTokenValue = "";
        isAutoquoting = false;
        autoquoteChar = ';';
    }
    public void setIsAutoquoting(final boolean z) {
        isAutoquoting = z;
    }
    public static String nameForToken(final int i2) {
        if (0 == i2) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        if (1 == i2) {
            return "start";
        }
        if (2 == i2) {
            return TypedValues.Custom.S_STRING;
        }
        if (5 == i2) {
            return "EOI";
        }
        if (47 == i2) {
            return "'/'";
        }
        if (59 == i2) {
            return "';'";
        }
        if (61 == i2) {
            return "'='";
        }
        return "really unknown";
    }
    public String getCurrentTokenValue() {
        return currentTokenValue;
    }
    public int nextToken() {
        if (dataIndex < dataLength) {
            while (true) {
                final int i2 = dataIndex;
                if (i2 >= dataLength || !MailcapTokenizer.isWhiteSpaceChar(data.charAt(i2))) {
                    final int i3 = dataIndex;
                    break;
                }
                dataIndex++;
            }
            int i32 = dataIndex;
            if (i32 < dataLength) {
                final char charAt = data.charAt(i32);
                if (isAutoquoting) {
                    if (';' == charAt || '=' == charAt) {
                        currentToken = charAt;
                        currentTokenValue = Character.toString(charAt);
                        dataIndex++;
                    } else {
                        this.processAutoquoteToken();
                    }
                } else if (MailcapTokenizer.isStringTokenChar(charAt)) {
                    this.processStringToken();
                } else if ('/' == charAt || ';' == charAt || '=' == charAt) {
                    currentToken = charAt;
                    currentTokenValue = Character.toString(charAt);
                    dataIndex++;
                } else {
                    currentToken = 0;
                    currentTokenValue = Character.toString(charAt);
                    dataIndex++;
                }
            } else {
                currentToken = 5;
                currentTokenValue = null;
            }
        } else {
            currentToken = 5;
            currentTokenValue = null;
        }
        return currentToken;
    }
    private void processStringToken() {
        final int i2 = dataIndex;
        while (true) {
            final int i3 = dataIndex;
            if (i3 >= dataLength || !MailcapTokenizer.isStringTokenChar(data.charAt(i3))) {
                currentToken = 2;
                currentTokenValue = data.substring(i2, dataIndex);
                break;
            } else {
                dataIndex++;
                break;
            }
        }
    }
    private void processAutoquoteToken() {
        int i2;
        final int i3 = dataIndex;
        boolean z = false;
        while (true) {
            i2 = dataIndex;
            if (i2 >= dataLength || z) {
                currentToken = 2;
                currentTokenValue = MailcapTokenizer.fixEscapeSequences(data.substring(i3, i2));
                break;
            } else if (data.charAt(i2) != autoquoteChar) {
                dataIndex++;
                break;
            } else {
                z = true;
                break;
            }
        }
    }
    private static boolean isControlChar(final char c2) {
        return Character.isISOControl(c2);
    }
    private static boolean isWhiteSpaceChar(final char c2) {
        return Character.isWhitespace(c2);
    }
    private static boolean isStringTokenChar(final char c2) {
        return !MailcapTokenizer.isSpecialChar(c2) && !MailcapTokenizer.isControlChar(c2) && !MailcapTokenizer.isWhiteSpaceChar(c2);
    }
    private static String fixEscapeSequences(final String str) {
        final int length = str.length();
        final StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.ensureCapacity(length);
        int i2 = 0;
        while (i2 < length) {
            final char charAt = str.charAt(i2);
            if ('\\' != charAt) {
                stringBuffer.append(charAt);
            } else if (i2 < length - 1) {
                i2++;
                stringBuffer.append(str.charAt(i2));
            } else {
                stringBuffer.append(charAt);
            }
            i2++;
        }
        return stringBuffer.toString();
    }
}
