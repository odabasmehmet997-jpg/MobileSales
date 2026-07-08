package com.sun.activation.registries;

import java.util.NoSuchElementException;
import java.util.Vector;

public class LineTokenizer {
    private int currentPosition;
    private final int maxPosition;
    private final Vector stack = new Vector();
    private final String str;
    public LineTokenizer(final String str2) {
        str = str2;
        maxPosition = str2.length();
    }
    private void skipWhiteSpace() {
        while (true) {
            final int i2 = currentPosition;
            if (i2 < maxPosition && Character.isWhitespace(str.charAt(i2))) {
                currentPosition++;
            } else {
                return;
            }
        }
    }
    public boolean hasMoreTokens() {
        if (0 < stack.size()) {
            return true;
        }
        this.skipWhiteSpace();
        return currentPosition < maxPosition;
    }
    public String nextToken() {
        final int size = stack.size();
        if (0 < size) {
            final int i2 = size - 1;
            final String str2 = (String) stack.elementAt(i2);
            stack.removeElementAt(i2);
            return str2;
        }
        this.skipWhiteSpace();
        final int i3 = currentPosition;
        if (i3 < maxPosition) {
            final char charAt = str.charAt(i3);
            if ('\"' != charAt) {
                if (0 > "=".indexOf(charAt)) {
                    while (true) {
                        final int i4 = currentPosition;
                        if (i4 >= maxPosition || 0 <= "=".indexOf(this.str.charAt(i4)) || Character.isWhitespace(str.charAt(currentPosition))) {
                            break;
                        }
                        currentPosition++;
                    }
                } else {
                    currentPosition++;
                }
            } else {
                currentPosition++;
                boolean z = false;
                while (true) {
                    final int i5 = currentPosition;
                    if (i5 >= maxPosition) {
                        break;
                    }
                    final String str3 = str;
                    currentPosition = i5 + 1;
                    final char charAt2 = str3.charAt(i5);
                    if ('\\' == charAt2) {
                        currentPosition++;
                        z = true;
                    } else if ('\"' == charAt2) {
                        if (!z) {
                            return str.substring(i3 + 1, currentPosition - 1);
                        }
                        final StringBuffer stringBuffer = new StringBuffer();
                        for (int i6 = i3 + 1; i6 < currentPosition - 1; i6++) {
                            final char charAt3 = str.charAt(i6);
                            if ('\\' != charAt3) {
                                stringBuffer.append(charAt3);
                            }
                        }
                        return stringBuffer.toString();
                    }
                }
            }
            return str.substring(i3, currentPosition);
        }
        throw new NoSuchElementException();
    }
}
