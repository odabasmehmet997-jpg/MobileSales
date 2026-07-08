package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.io.NumberInput;

public class JsonPointer {
    protected static final JsonPointer EMPTY = new JsonPointer();
    protected final String _asString;
    protected final int _matchingElementIndex;
    protected final String _matchingPropertyName;
    protected final JsonPointer _nextSegment;
    protected JsonPointer() {
        _nextSegment = null;
        _matchingPropertyName = "";
        _matchingElementIndex = -1;
        _asString = "";
    }
    protected JsonPointer(final String str, final String str2, final JsonPointer jsonPointer) {
        _asString = str;
        _nextSegment = jsonPointer;
        _matchingPropertyName = str2;
        _matchingElementIndex = JsonPointer._parseIndex(str2);
    }
    public static JsonPointer compile(final String str) throws IllegalArgumentException {
        if (null == str || 0 == str.length()) {
            return JsonPointer.EMPTY;
        }
        if ('/' != str.charAt(0)) {
            throw new IllegalArgumentException("Invalid input: JSON Pointer expression must start with '/': \"" + str + "\"");
        }
        return JsonPointer._parseTail(str);
    }
    public boolean matches() {
        return null == this._nextSegment;
    }
    public String getMatchingProperty() {
        return _matchingPropertyName;
    }
    public int getMatchingIndex() {
        return _matchingElementIndex;
    }
    public JsonPointer matchProperty(final String str) {
        if (null == this._nextSegment || !_matchingPropertyName.equals(str)) {
            return null;
        }
        return _nextSegment;
    }
    public JsonPointer matchElement(final int i2) {
        if (i2 != _matchingElementIndex || 0 > i2) {
            return null;
        }
        return _nextSegment;
    }
    public JsonPointer tail() {
        return _nextSegment;
    }
    public String toString() {
        return _asString;
    }
    public int hashCode() {
        return _asString.hashCode();
    }
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (null != obj && (obj instanceof JsonPointer)) {
            return _asString.equals(((JsonPointer) obj)._asString);
        }
        return false;
    }
    private static int _parseIndex(final String str) {
        final int length = str.length();
        if (0 == length || 10 < length) {
            return -1;
        }
        final char cCharAt = str.charAt(0);
        if ('0' >= cCharAt) {
            return (1 == length && '0' == cCharAt) ? 0 : -1;
        }
        if ('9' < cCharAt) {
            return -1;
        }
        for (int i2 = 1; i2 < length; i2++) {
            final char cCharAt2 = str.charAt(i2);
            if ('9' < cCharAt2 || '0' > cCharAt2) {
                return -1;
            }
        }
        if (10 != length || 2147483647L >= NumberInput.parseLong(str)) {
            return NumberInput.parseInt(str);
        }
        return -1;
    }
    protected static JsonPointer _parseTail(final String str) {
        final int length = str.length();
        int i2 = 1;
        while (i2 < length) {
            final char cCharAt = str.charAt(i2);
            if ('/' == cCharAt) {
                return new JsonPointer(str, str.substring(1, i2), JsonPointer._parseTail(str.substring(i2)));
            }
            i2++;
            if ('~' == cCharAt && i2 < length) {
                return JsonPointer._parseQuotedTail(str, i2);
            }
        }
        return new JsonPointer(str, str.substring(1), JsonPointer.EMPTY);
    }
    protected static JsonPointer _parseQuotedTail(final String str, final int i2) {
        final int length = str.length();
        final StringBuilder sb = new StringBuilder(Math.max(16, length));
        if (2 < i2) {
            sb.append(str, 1, i2 - 1);
        }
        int i3 = i2 + 1;
        JsonPointer._appendEscape(sb, str.charAt(i2));
        while (i3 < length) {
            final char cCharAt = str.charAt(i3);
            if ('/' == cCharAt) {
                return new JsonPointer(str, sb.toString(), JsonPointer._parseTail(str.substring(i3)));
            }
            final int i4 = i3 + 1;
            if ('~' == cCharAt && i4 < length) {
                i3 += 2;
                JsonPointer._appendEscape(sb, str.charAt(i4));
            } else {
                sb.append(cCharAt);
                i3 = i4;
            }
        }
        return new JsonPointer(str, sb.toString(), JsonPointer.EMPTY);
    }
    private static void _appendEscape(final StringBuilder sb, char c2) {
        if ('0' == c2) {
            c2 = '~';
        } else if ('1' == c2) {
            c2 = '/';
        } else {
            sb.append('~');
        }
        sb.append(c2);
    }

    public void append(String message) {

    }
}
