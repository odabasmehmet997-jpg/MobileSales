package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.SerializableString;

import java.io.*;
import java.nio.ByteBuffer;
public class SerializedString implements SerializableString, Serializable {
    private static final JsonStringEncoder JSON_ENCODER = JsonStringEncoder.getInstance();
    private static final long serialVersionUID = 1;
    protected transient String _jdkSerializeValue;
    protected char[] _quotedChars;
    protected byte[] _quotedUTF8Ref;
    protected byte[] _unquotedUTF8Ref;
    protected final String _value;
    public SerializedString(final String str) {
        if (null == str) {
            throw new IllegalStateException("Null String illegal for SerializedString");
        }
        _value = str;
    }
    private void readObject(final ObjectInputStream objectInputStream) throws IOException {
        _jdkSerializeValue = objectInputStream.readUTF();
    }
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeUTF(_value);
    }
    protected Object readResolve() {
        return new SerializedString(_jdkSerializeValue);
    }
    public final String getValue() {
        return _value;
    }
    public final int charLength() {
        return _value.length();
    }
    public final char[] asQuotedChars() {
        final char[] cArr = _quotedChars;
        if (null != cArr) {
            return cArr;
        }
        final char[] cArrQuoteAsString = SerializedString.JSON_ENCODER.quoteAsString(_value);
        _quotedChars = cArrQuoteAsString;
        return cArrQuoteAsString;
    }
    public final byte[] asQuotedUTF8() {
        final byte[] bArr = _quotedUTF8Ref;
        if (null != bArr) {
            return bArr;
        }
        final byte[] bArrQuoteAsUTF8 = SerializedString.JSON_ENCODER.quoteAsUTF8(_value);
        _quotedUTF8Ref = bArrQuoteAsUTF8;
        return bArrQuoteAsUTF8;
    }
    public final byte[] asUnquotedUTF8() {
        final byte[] bArr = _unquotedUTF8Ref;
        if (null != bArr) {
            return bArr;
        }
        final byte[] bArrEncodeAsUTF8 = SerializedString.JSON_ENCODER.encodeAsUTF8(_value);
        _unquotedUTF8Ref = bArrEncodeAsUTF8;
        return bArrEncodeAsUTF8;
    }
    public int appendQuoted(final char[] cArr, final int i2) {
        char[] cArrQuoteAsString = _quotedChars;
        if (null == cArrQuoteAsString) {
            cArrQuoteAsString = SerializedString.JSON_ENCODER.quoteAsString(_value);
            _quotedChars = cArrQuoteAsString;
        }
        final int length = cArrQuoteAsString.length;
        if (i2 + length > cArr.length) {
            return -1;
        }
        System.arraycopy(cArrQuoteAsString, 0, cArr, i2, length);
        return length;
    }
    public int appendQuotedUTF8(final byte[] bArr, final int i2) {
        byte[] bArrQuoteAsUTF8 = _quotedUTF8Ref;
        if (null == bArrQuoteAsUTF8) {
            bArrQuoteAsUTF8 = SerializedString.JSON_ENCODER.quoteAsUTF8(_value);
            _quotedUTF8Ref = bArrQuoteAsUTF8;
        }
        final int length = bArrQuoteAsUTF8.length;
        if (i2 + length > bArr.length) {
            return -1;
        }
        System.arraycopy(bArrQuoteAsUTF8, 0, bArr, i2, length);
        return length;
    }
    public int appendUnquoted(final char[] cArr, final int i2) {
        final String str = _value;
        final int length = str.length();
        if (i2 + length > cArr.length) {
            return -1;
        }
        str.getChars(0, length, cArr, i2);
        return length;
    }
    public int appendUnquotedUTF8(final byte[] bArr, final int i2) {
        byte[] bArrEncodeAsUTF8 = _unquotedUTF8Ref;
        if (null == bArrEncodeAsUTF8) {
            bArrEncodeAsUTF8 = SerializedString.JSON_ENCODER.encodeAsUTF8(_value);
            _unquotedUTF8Ref = bArrEncodeAsUTF8;
        }
        final int length = bArrEncodeAsUTF8.length;
        if (i2 + length > bArr.length) {
            return -1;
        }
        System.arraycopy(bArrEncodeAsUTF8, 0, bArr, i2, length);
        return length;
    }
    public int writeQuotedUTF8(final OutputStream outputStream) throws IOException {
        byte[] bArrQuoteAsUTF8 = _quotedUTF8Ref;
        if (null == bArrQuoteAsUTF8) {
            bArrQuoteAsUTF8 = SerializedString.JSON_ENCODER.quoteAsUTF8(_value);
            _quotedUTF8Ref = bArrQuoteAsUTF8;
        }
        final int length = bArrQuoteAsUTF8.length;
        outputStream.write(bArrQuoteAsUTF8, 0, length);
        return length;
    }
    public int writeUnquotedUTF8(final OutputStream outputStream) throws IOException {
        byte[] bArrEncodeAsUTF8 = _unquotedUTF8Ref;
        if (null == bArrEncodeAsUTF8) {
            bArrEncodeAsUTF8 = SerializedString.JSON_ENCODER.encodeAsUTF8(_value);
            _unquotedUTF8Ref = bArrEncodeAsUTF8;
        }
        final int length = bArrEncodeAsUTF8.length;
        outputStream.write(bArrEncodeAsUTF8, 0, length);
        return length;
    }
    public int putQuotedUTF8(final ByteBuffer byteBuffer) {
        byte[] bArrQuoteAsUTF8 = _quotedUTF8Ref;
        if (null == bArrQuoteAsUTF8) {
            bArrQuoteAsUTF8 = SerializedString.JSON_ENCODER.quoteAsUTF8(_value);
            _quotedUTF8Ref = bArrQuoteAsUTF8;
        }
        final int length = bArrQuoteAsUTF8.length;
        if (length > byteBuffer.remaining()) {
            return -1;
        }
        byteBuffer.put(bArrQuoteAsUTF8, 0, length);
        return length;
    }
    public int putUnquotedUTF8(final ByteBuffer byteBuffer) {
        byte[] bArrEncodeAsUTF8 = _unquotedUTF8Ref;
        if (null == bArrEncodeAsUTF8) {
            bArrEncodeAsUTF8 = SerializedString.JSON_ENCODER.encodeAsUTF8(_value);
            _unquotedUTF8Ref = bArrEncodeAsUTF8;
        }
        final int length = bArrEncodeAsUTF8.length;
        if (length > byteBuffer.remaining()) {
            return -1;
        }
        byteBuffer.put(bArrEncodeAsUTF8, 0, length);
        return length;
    }
    public final String toString() {
        return _value;
    }
    public final int hashCode() {
        return _value.hashCode();
    }
    public final boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (null == obj || obj.getClass() != this.getClass()) {
            return false;
        }
        return _value.equals(((SerializedString) obj)._value);
    }
}
