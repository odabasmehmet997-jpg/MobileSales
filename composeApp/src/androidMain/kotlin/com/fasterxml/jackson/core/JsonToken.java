package com.fasterxml.jackson.core;

public enum JsonToken {
    NOT_AVAILABLE(null, -1),
    START_OBJECT("{", 1),
    END_OBJECT("}", 2),
    START_ARRAY("[", 3),
    END_ARRAY("]", 4),
    FIELD_NAME(null, 5),
    VALUE_EMBEDDED_OBJECT(null, 12),
    VALUE_STRING(null, 6),
    VALUE_NUMBER_INT(null, 7),
    VALUE_NUMBER_FLOAT(null, 8),
    VALUE_TRUE("true", 9),
    VALUE_FALSE("false", 10),
    VALUE_NULL("null", 11);

    final int _id;
    final boolean _isBoolean;
    final boolean _isNumber;
    final boolean _isScalar;
    final boolean _isStructEnd;
    final boolean _isStructStart;
    final String _serialized;
    final byte[] _serializedBytes;
    final char[] _serializedChars;
    JsonToken(final String str, final int i2) {
        boolean z = false;
        if (null == str) {
            _serialized = null;
            _serializedChars = null;
            _serializedBytes = null;
        } else {
            _serialized = str;
            final char[] charArray = str.toCharArray();
            _serializedChars = charArray;
            final int length = charArray.length;
            _serializedBytes = new byte[length];
            for (int i3 = 0; i3 < length; i3++) {
                _serializedBytes[i3] = (byte) _serializedChars[i3];
            }
        }
        _id = i2;
        _isBoolean = 10 == i2 || 9 == i2;
        _isNumber = 7 == i2 || 8 == i2;
        final boolean z2 = 1 == i2 || 3 == i2;
        _isStructStart = z2;
        final boolean z3 = 2 == i2 || 4 == i2;
        _isStructEnd = z3;
        if (!z2 && !z3 && 5 != i2 && -1 != i2) {
            z = true;
        }
        _isScalar = z;
    }
    public final int m307id() {
        return _id;
    }
    public final String asString() {
        return _serialized;
    }
    public final char[] asCharArray() {
        return _serializedChars;
    }
    public final byte[] asByteArray() {
        return _serializedBytes;
    }
    public final boolean isNumeric() {
        return _isNumber;
    }
    public final boolean isStructStart() {
        return _isStructStart;
    }
    public final boolean isStructEnd() {
        return _isStructEnd;
    }
    public final boolean isScalarValue() {
        return _isScalar;
    }
    public final boolean isBoolean() {
        return _isBoolean;
    }
    public int charAt(final int paramInt) {
        return paramInt;
    }
}
