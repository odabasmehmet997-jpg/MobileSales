package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.NumberOutput;
import org.kxml2.wap.Wbxml;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public class UTF8JsonGenerator extends JsonGeneratorImpl {
    protected boolean _bufferRecyclable;
    protected char[] _charBuffer;
    protected final int _charBufferLength;
    protected byte[] _outputBuffer;
    protected final int _outputEnd;
    protected final int _outputMaxContiguous;
    protected final OutputStream _outputStream;
    protected int _outputTail;
    protected byte _quoteChar;
    private static final byte[] HEX_CHARS = CharTypes.copyHexBytes();
    private static final byte[] NULL_BYTES = {110, 117, 108, 108};
    private static final byte[] TRUE_BYTES = {116, 114, 117, 101};
    private static final byte[] FALSE_BYTES = {102, 97, 108, 115, 101};
    private Object i10;

    public UTF8JsonGenerator(final IOContext iOContext, final int i2, final ObjectCodec objectCodec, final OutputStream outputStream, final char c2) {
        super(iOContext, i2, objectCodec);
        _outputStream = outputStream;
        _quoteChar = (byte) c2;
        if ('\"' != c2) {
            _outputEscapes = CharTypes.get7BitOutputEscapes(c2);
        }
        _bufferRecyclable = true;
        final byte[] bArrAllocWriteEncodingBuffer = iOContext.allocWriteEncodingBuffer();
        _outputBuffer = bArrAllocWriteEncodingBuffer;
        final int length = bArrAllocWriteEncodingBuffer.length;
        _outputEnd = length;
        _outputMaxContiguous = length >> 3;
        final char[] cArrAllocConcatBuffer = iOContext.allocConcatBuffer();
        _charBuffer = cArrAllocConcatBuffer;
        _charBufferLength = cArrAllocConcatBuffer.length;
        if (this.isEnabled(Feature.ESCAPE_NON_ASCII)) {
            this.setHighestNonEscapedChar(127);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeFieldName(final String str) throws IOException {
        if (null != this._cfgPrettyPrinter) {
            this._writePPFieldName(str);
            return;
        }
        final int iWriteFieldName = _writeContext.writeFieldName(str);
        if (4 == iWriteFieldName) {
            this._reportError("Can not write a field name, expecting a value");
        }
        if (1 == iWriteFieldName) {
            if (_outputTail >= _outputEnd) {
                this._flushBuffer();
            }
            final byte[] bArr = _outputBuffer;
            final int i2 = _outputTail;
            _outputTail = i2 + 1;
            bArr[i2] = 44;
        }
        if (_cfgUnqNames) {
            this._writeStringSegments(str, false);
            return;
        }
        final int length = str.length();
        if (length > _charBufferLength) {
            this._writeStringSegments(str, true);
            return;
        }
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final byte[] bArr2 = _outputBuffer;
        final int i3 = _outputTail;
        final int i4 = i3 + 1;
        _outputTail = i4;
        bArr2[i3] = _quoteChar;
        if (length <= _outputMaxContiguous) {
            if (i4 + length > _outputEnd) {
                this._flushBuffer();
            }
            this._writeStringSegment(str, 0, length);
        } else {
            this._writeStringSegments(str, 0, length);
        }
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final byte[] bArr3 = _outputBuffer;
        final int i5 = _outputTail;
        _outputTail = i5 + 1;
        bArr3[i5] = _quoteChar;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public void writeFieldName(final SerializableString serializableString) throws IOException {
        if (null != this._cfgPrettyPrinter) {
            this._writePPFieldName(serializableString);
            return;
        }
        final int iWriteFieldName = _writeContext.writeFieldName(serializableString.getValue());
        if (4 == iWriteFieldName) {
            this._reportError("Can not write a field name, expecting a value");
        }
        if (1 == iWriteFieldName) {
            if (_outputTail >= _outputEnd) {
                this._flushBuffer();
            }
            final byte[] bArr = _outputBuffer;
            final int i2 = _outputTail;
            _outputTail = i2 + 1;
            bArr[i2] = 44;
        }
        if (_cfgUnqNames) {
            this._writeUnq(serializableString);
            return;
        }
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final byte[] bArr2 = _outputBuffer;
        final int i3 = _outputTail;
        final int i4 = i3 + 1;
        _outputTail = i4;
        bArr2[i3] = _quoteChar;
        final int iAppendQuotedUTF8 = serializableString.appendQuotedUTF8(bArr2, i4);
        if (0 > iAppendQuotedUTF8) {
            this._writeBytes(serializableString.asQuotedUTF8());
        } else {
            _outputTail += iAppendQuotedUTF8;
        }
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final byte[] bArr3 = _outputBuffer;
        final int i5 = _outputTail;
        _outputTail = i5 + 1;
        bArr3[i5] = _quoteChar;
    }

    private final void _writeUnq(final SerializableString serializableString) throws IOException {
        final int iAppendQuotedUTF8 = serializableString.appendQuotedUTF8(_outputBuffer, _outputTail);
        if (0 > iAppendQuotedUTF8) {
            this._writeBytes(serializableString.asQuotedUTF8());
        } else {
            _outputTail += iAppendQuotedUTF8;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeStartArray() throws IOException {
        this._verifyValueWrite("start an array");
        _writeContext = _writeContext.createChildArrayContext();
        final PrettyPrinter prettyPrinter = _cfgPrettyPrinter;
        if (null != prettyPrinter) {
            prettyPrinter.writeStartArray(this);
            return;
        }
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final byte[] bArr = _outputBuffer;
        final int i2 = _outputTail;
        _outputTail = i2 + 1;
        bArr[i2] = 91;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeStartArray(final Object obj) throws IOException {
        this._verifyValueWrite("start an array");
        _writeContext = _writeContext.createChildArrayContext(obj);
        final PrettyPrinter prettyPrinter = _cfgPrettyPrinter;
        if (null != prettyPrinter) {
            prettyPrinter.writeStartArray(this);
            return;
        }
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final byte[] bArr = _outputBuffer;
        final int i2 = _outputTail;
        _outputTail = i2 + 1;
        bArr[i2] = 91;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeStartArray(final Object obj, final int i2) throws IOException {
        this._verifyValueWrite("start an array");
        _writeContext = _writeContext.createChildArrayContext(obj);
        final PrettyPrinter prettyPrinter = _cfgPrettyPrinter;
        if (null != prettyPrinter) {
            prettyPrinter.writeStartArray(this);
            return;
        }
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final byte[] bArr = _outputBuffer;
        final int i3 = _outputTail;
        _outputTail = i3 + 1;
        bArr[i3] = 91;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeEndArray() throws IOException {
        if (!_writeContext.inArray()) {
            this._reportError("Current context not Array but " + _writeContext.typeDesc());
        }
        final PrettyPrinter prettyPrinter = _cfgPrettyPrinter;
        if (null != prettyPrinter) {
            prettyPrinter.writeEndArray(this, _writeContext.getEntryCount());
        } else {
            if (_outputTail >= _outputEnd) {
                this._flushBuffer();
            }
            final byte[] bArr = _outputBuffer;
            final int i2 = _outputTail;
            _outputTail = i2 + 1;
            bArr[i2] = 93;
        }
        _writeContext = _writeContext.clearAndGetParent();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeStartObject() throws IOException {
        this._verifyValueWrite("start an object");
        _writeContext = _writeContext.createChildObjectContext();
        final PrettyPrinter prettyPrinter = _cfgPrettyPrinter;
        if (null != prettyPrinter) {
            prettyPrinter.writeStartObject(this);
            return;
        }
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final byte[] bArr = _outputBuffer;
        final int i2 = _outputTail;
        _outputTail = i2 + 1;
        bArr[i2] = 123;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public void writeStartObject(final Object obj) throws IOException {
        this._verifyValueWrite("start an object");
        _writeContext = _writeContext.createChildObjectContext(obj);
        final PrettyPrinter prettyPrinter = _cfgPrettyPrinter;
        if (null != prettyPrinter) {
            prettyPrinter.writeStartObject(this);
            return;
        }
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final byte[] bArr = _outputBuffer;
        final int i2 = _outputTail;
        _outputTail = i2 + 1;
        bArr[i2] = 123;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeEndObject() throws IOException {
        if (!_writeContext.inObject()) {
            this._reportError("Current context not Object but " + _writeContext.typeDesc());
        }
        final PrettyPrinter prettyPrinter = _cfgPrettyPrinter;
        if (null != prettyPrinter) {
            prettyPrinter.writeEndObject(this, _writeContext.getEntryCount());
        } else {
            if (_outputTail >= _outputEnd) {
                this._flushBuffer();
            }
            final byte[] bArr = _outputBuffer;
            final int i2 = _outputTail;
            _outputTail = i2 + 1;
            bArr[i2] = 125;
        }
        _writeContext = _writeContext.clearAndGetParent();
    }

    protected final void _writePPFieldName(final String str) throws IOException {
        final int iWriteFieldName = _writeContext.writeFieldName(str);
        if (4 == iWriteFieldName) {
            this._reportError("Can not write a field name, expecting a value");
        }
        if (1 == iWriteFieldName) {
            _cfgPrettyPrinter.writeObjectEntrySeparator(this);
        } else {
            _cfgPrettyPrinter.beforeObjectEntries(this);
        }
        if (_cfgUnqNames) {
            this._writeStringSegments(str, false);
            return;
        }
        final int length = str.length();
        if (length > _charBufferLength) {
            this._writeStringSegments(str, true);
            return;
        }
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final byte[] bArr = _outputBuffer;
        final int i2 = _outputTail;
        _outputTail = i2 + 1;
        bArr[i2] = _quoteChar;
        str.getChars(0, length, _charBuffer, 0);
        if (length <= _outputMaxContiguous) {
            if (_outputTail + length > _outputEnd) {
                this._flushBuffer();
            }
            this._writeStringSegment(_charBuffer, 0, length);
        } else {
            this._writeStringSegments(_charBuffer, 0, length);
        }
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final byte[] bArr2 = _outputBuffer;
        final int i3 = _outputTail;
        _outputTail = i3 + 1;
        bArr2[i3] = _quoteChar;
    }

    protected final void _writePPFieldName(final SerializableString serializableString) throws IOException {
        final int iWriteFieldName = _writeContext.writeFieldName(serializableString.getValue());
        if (4 == iWriteFieldName) {
            this._reportError("Can not write a field name, expecting a value");
        }
        if (1 == iWriteFieldName) {
            _cfgPrettyPrinter.writeObjectEntrySeparator(this);
        } else {
            _cfgPrettyPrinter.beforeObjectEntries(this);
        }
        final boolean z = _cfgUnqNames;
        if (!z) {
            if (_outputTail >= _outputEnd) {
                this._flushBuffer();
            }
            final byte[] bArr = _outputBuffer;
            final int i2 = _outputTail;
            _outputTail = i2 + 1;
            bArr[i2] = _quoteChar;
        }
        final int iAppendQuotedUTF8 = serializableString.appendQuotedUTF8(_outputBuffer, _outputTail);
        if (0 > iAppendQuotedUTF8) {
            this._writeBytes(serializableString.asQuotedUTF8());
        } else {
            _outputTail += iAppendQuotedUTF8;
        }
        if (z) {
            return;
        }
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final byte[] bArr2 = _outputBuffer;
        final int i3 = _outputTail;
        _outputTail = i3 + 1;
        bArr2[i3] = _quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeString(final String str) throws IOException {
        this._verifyValueWrite("write a string");
        if (null == str) {
            this._writeNull();
            return;
        }
        final int length = str.length();
        if (length > _outputMaxContiguous) {
            this._writeStringSegments(str, true);
            return;
        }
        if (_outputTail + length >= _outputEnd) {
            this._flushBuffer();
        }
        final byte[] bArr = _outputBuffer;
        final int i2 = _outputTail;
        _outputTail = i2 + 1;
        bArr[i2] = _quoteChar;
        this._writeStringSegment(str, 0, length);
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final byte[] bArr2 = _outputBuffer;
        final int i3 = _outputTail;
        _outputTail = i3 + 1;
        bArr2[i3] = _quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeString(final char[] cArr, final int i2, final int i3) throws IOException {
        this._verifyValueWrite("write a string");
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final byte[] bArr = _outputBuffer;
        final int i4 = _outputTail;
        final int i5 = i4 + 1;
        _outputTail = i5;
        bArr[i4] = _quoteChar;
        if (i3 <= _outputMaxContiguous) {
            if (i5 + i3 > _outputEnd) {
                this._flushBuffer();
            }
            this._writeStringSegment(cArr, i2, i3);
        } else {
            this._writeStringSegments(cArr, i2, i3);
        }
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final byte[] bArr2 = _outputBuffer;
        final int i6 = _outputTail;
        _outputTail = i6 + 1;
        bArr2[i6] = _quoteChar;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public final void writeString(final SerializableString serializableString) throws IOException {
        this._verifyValueWrite("write a string");
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final byte[] bArr = _outputBuffer;
        final int i2 = _outputTail;
        final int i3 = i2 + 1;
        _outputTail = i3;
        bArr[i2] = _quoteChar;
        final int iAppendQuotedUTF8 = serializableString.appendQuotedUTF8(bArr, i3);
        if (0 > iAppendQuotedUTF8) {
            this._writeBytes(serializableString.asQuotedUTF8());
        } else {
            _outputTail += iAppendQuotedUTF8;
        }
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final byte[] bArr2 = _outputBuffer;
        final int i4 = _outputTail;
        _outputTail = i4 + 1;
        bArr2[i4] = _quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(final String str) throws IOException {
        final int length = str.length();
        final char[] cArr = _charBuffer;
        if (length <= cArr.length) {
            str.getChars(0, length, cArr, 0);
            this.writeRaw(cArr, 0, length);
        } else {
            this.writeRaw(str, 0, length);
        }
    }

    public void writeRaw(final String str, int i2, int i3) throws IOException {
        char c2;
        final char[] cArr = _charBuffer;
        final int length = cArr.length;
        if (i3 <= length) {
            str.getChars(i2, i2 + i3, cArr, 0);
            this.writeRaw(cArr, 0, i3);
            return;
        }
        final int i4 = _outputEnd;
        final int iMin = Math.min(length, (i4 >> 2) + (i4 >> 4));
        final int i5 = iMin * 3;
        while (0 < i3) {
            int iMin2 = Math.min(iMin, i3);
            str.getChars(i2, i2 + iMin2, cArr, 0);
            if (_outputTail + i5 > _outputEnd) {
                this._flushBuffer();
            }
            if (1 < iMin2 && '\ud800' <= (c2 = cArr[iMin2 - 1]) && '\udbff' >= c2) {
                iMin2--;
            }
            this._writeRawSegment(cArr, 0, iMin2);
            i2 += iMin2;
            i3 -= iMin2;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(final SerializableString serializableString) throws IOException {
        final int iAppendUnquotedUTF8 = serializableString.appendUnquotedUTF8(_outputBuffer, _outputTail);
        if (0 > iAppendUnquotedUTF8) {
            this._writeBytes(serializableString.asUnquotedUTF8());
        } else {
            _outputTail += iAppendUnquotedUTF8;
        }
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public void writeRawValue(final SerializableString serializableString) throws IOException {
        this._verifyValueWrite("write a raw (unencoded) value");
        final int iAppendUnquotedUTF8 = serializableString.appendUnquotedUTF8(_outputBuffer, _outputTail);
        if (0 > iAppendUnquotedUTF8) {
            this._writeBytes(serializableString.asUnquotedUTF8());
        } else {
            _outputTail += iAppendUnquotedUTF8;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeRaw(final char[] cArr, int i2, final int i3) throws IOException {
        final int i4 = i3 + i3 + i3;
        final int i5 = _outputTail + i4;
        final int i6 = _outputEnd;
        if (i5 > i6) {
            if (i6 < i4) {
                this._writeSegmentedRaw(cArr, i2, i3);
                return;
            }
            this._flushBuffer();
        }
        final int i7 = i3 + i2;
        while (i2 < i7) {
            do {
                final char c2 = cArr[i2];
                if ('\u007f' < c2) {
                    i2++;
                    if ('\u0800' > c2) {
                        final byte[] bArr = _outputBuffer;
                        final int i8 = _outputTail;
                        final int i9 = i8 + 1;
                        _outputTail = i9;
                        bArr[i8] = (byte) ((c2 >> 6) | Wbxml.EXT_0);
                        _outputTail = i8 + 2;
                        bArr[i9] = (byte) ((c2 & '?') | 128);
                    } else {
                        i2 = this._outputRawMultiByteChar(c2, cArr, i2, i7);
                    }
                } else {
                    final byte[] bArr2 = _outputBuffer;
                    final int i10 = _outputTail;
                    _outputTail = i10 + 1;
                    bArr2[i10] = (byte) c2;
                    i2++;
                }
            } while (i2 < i7);
            return;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(final char c2) throws IOException {
        if (_outputTail + 3 >= _outputEnd) {
            this._flushBuffer();
        }
        final byte[] bArr = _outputBuffer;
        if ('\u007f' >= c2) {
            final int i2 = _outputTail;
            _outputTail = i2 + 1;
            bArr[i2] = (byte) c2;
        } else {
            if ('\u0800' > c2) {
                final int i3 = _outputTail;
                final int i4 = i3 + 1;
                _outputTail = i4;
                bArr[i3] = (byte) ((c2 >> 6) | Wbxml.EXT_0);
                _outputTail = i3 + 2;
                bArr[i4] = (byte) ((c2 & '?') | 128);
                return;
            }
            this._outputRawMultiByteChar(c2, null, 0, 0);
        }
    }

    private final void _writeSegmentedRaw(final char[] cArr, int i2, final int i3) throws IOException {
        final int i4 = _outputEnd;
        final byte[] bArr = _outputBuffer;
        final int i5 = i3 + i2;
        while (i2 < i5) {
            do {
                final char c2 = cArr[i2];
                if ('\u0080' > c2) {
                    if (_outputTail >= i4) {
                        this._flushBuffer();
                    }
                    final int i6 = _outputTail;
                    _outputTail = i6 + 1;
                    bArr[i6] = (byte) c2;
                    i2++;
                } else {
                    if (_outputTail + 3 >= _outputEnd) {
                        this._flushBuffer();
                    }
                    final int i7 = i2 + 1;
                    final char c3 = cArr[i2];
                    if ('\u0800' > c3) {
                        final int i8 = _outputTail;
                        final int i9 = i8 + 1;
                        _outputTail = i9;
                        bArr[i8] = (byte) ((c3 >> 6) | Wbxml.EXT_0);
                        _outputTail = i8 + 2;
                        bArr[i9] = (byte) ((c3 & '?') | 128);
                        i2 = i7;
                    } else {
                        i2 = this._outputRawMultiByteChar(c3, cArr, i7, i5);
                    }
                }
            } while (i2 < i5);
            return;
        }
    }

    private void _writeRawSegment(final char[] cArr, int i2, final int i3) throws IOException {
        while (i2 < i3) {
            do {
                final char c2 = cArr[i2];
                if ('\u007f' < c2) {
                    i2++;
                    if ('\u0800' > c2) {
                        final byte[] bArr = _outputBuffer;
                        final int i4 = _outputTail;
                        final int i5 = i4 + 1;
                        _outputTail = i5;
                        bArr[i4] = (byte) ((c2 >> 6) | Wbxml.EXT_0);
                        _outputTail = i4 + 2;
                        bArr[i5] = (byte) ((c2 & '?') | 128);
                    } else {
                        i2 = this._outputRawMultiByteChar(c2, cArr, i2, i3);
                    }
                } else {
                    final byte[] bArr2 = _outputBuffer;
                    final int i6 = _outputTail;
                    _outputTail = i6 + 1;
                    bArr2[i6] = (byte) c2;
                    i2++;
                }
            } while (i2 < i3);
            return;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeBinary(final Base64Variant base64Variant, final byte[] bArr, final int i2, final int i3) throws IOException {
        this._verifyValueWrite("write a binary value");
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final byte[] bArr2 = _outputBuffer;
        final int i4 = _outputTail;
        _outputTail = i4 + 1;
        bArr2[i4] = _quoteChar;
        this._writeBinary(base64Variant, bArr, i2, i3 + i2);
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final byte[] bArr3 = _outputBuffer;
        final int i5 = _outputTail;
        _outputTail = i5 + 1;
        bArr3[i5] = _quoteChar;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public int writeBinary(final Base64Variant base64Variant, final InputStream inputStream, int i2) throws IOException {
        this._verifyValueWrite("write a binary value");
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final byte[] bArr = _outputBuffer;
        final int i3 = _outputTail;
        _outputTail = i3 + 1;
        bArr[i3] = _quoteChar;
        final byte[] bArrAllocBase64Buffer = _ioContext.allocBase64Buffer();
        try {
            if (0 > i2) {
                i2 = this._writeBinary(base64Variant, inputStream, bArrAllocBase64Buffer);
            } else {
                final int i_writeBinary = this._writeBinary(base64Variant, inputStream, bArrAllocBase64Buffer, i2);
                if (0 < i_writeBinary) {
                    this._reportError("Too few bytes available: missing " + i_writeBinary + " bytes (out of " + i2 + ")");
                }
            }
            _ioContext.releaseBase64Buffer(bArrAllocBase64Buffer);
            if (_outputTail >= _outputEnd) {
                this._flushBuffer();
            }
            final byte[] bArr2 = _outputBuffer;
            final int i4 = _outputTail;
            _outputTail = i4 + 1;
            bArr2[i4] = _quoteChar;
            return i2;
        } catch (final Throwable th) {
            _ioContext.releaseBase64Buffer(bArrAllocBase64Buffer);
            throw th;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(final short s) throws IOException {
        this._verifyValueWrite("write a number");
        if (_outputTail + 6 >= _outputEnd) {
            this._flushBuffer();
        }
        if (_cfgNumbersAsStrings) {
            this._writeQuotedShort(s);
        } else {
            _outputTail = NumberOutput.outputInt(s, _outputBuffer, _outputTail);
        }
    }

    private final void _writeQuotedShort(final short s) throws IOException {
        if (_outputTail + 8 >= _outputEnd) {
            this._flushBuffer();
        }
        final byte[] bArr = _outputBuffer;
        final int i2 = _outputTail;
        final int i3 = i2 + 1;
        _outputTail = i3;
        bArr[i2] = _quoteChar;
        final int iOutputInt = NumberOutput.outputInt(s, bArr, i3);
        final byte[] bArr2 = _outputBuffer;
        _outputTail = iOutputInt + 1;
        bArr2[iOutputInt] = _quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(final int i2) throws IOException {
        this._verifyValueWrite("write a number");
        if (_outputTail + 11 >= _outputEnd) {
            this._flushBuffer();
        }
        if (_cfgNumbersAsStrings) {
            this._writeQuotedInt(i2);
        } else {
            _outputTail = NumberOutput.outputInt(i2, _outputBuffer, _outputTail);
        }
    }

    private final void _writeQuotedInt(final int i2) throws IOException {
        if (_outputTail + 13 >= _outputEnd) {
            this._flushBuffer();
        }
        final byte[] bArr = _outputBuffer;
        final int i3 = _outputTail;
        final int i4 = i3 + 1;
        _outputTail = i4;
        bArr[i3] = _quoteChar;
        final int iOutputInt = NumberOutput.outputInt(i2, bArr, i4);
        final byte[] bArr2 = _outputBuffer;
        _outputTail = iOutputInt + 1;
        bArr2[iOutputInt] = _quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(final long j2) throws IOException {
        this._verifyValueWrite("write a number");
        if (_cfgNumbersAsStrings) {
            this._writeQuotedLong(j2);
            return;
        }
        if (_outputTail + 21 >= _outputEnd) {
            this._flushBuffer();
        }
        _outputTail = NumberOutput.outputLong(j2, _outputBuffer, _outputTail);
    }

    private final void _writeQuotedLong(final long j2) throws IOException {
        if (_outputTail + 23 >= _outputEnd) {
            this._flushBuffer();
        }
        final byte[] bArr = _outputBuffer;
        final int i2 = _outputTail;
        final int i3 = i2 + 1;
        _outputTail = i3;
        bArr[i2] = _quoteChar;
        final int iOutputLong = NumberOutput.outputLong(j2, bArr, i3);
        final byte[] bArr2 = _outputBuffer;
        _outputTail = iOutputLong + 1;
        bArr2[iOutputLong] = _quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(final BigInteger bigInteger) throws IOException {
        this._verifyValueWrite("write a number");
        if (null == bigInteger) {
            this._writeNull();
        } else if (_cfgNumbersAsStrings) {
            this._writeQuotedRaw(bigInteger.toString());
        } else {
            this.writeRaw(bigInteger.toString());
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(final double d2) throws IOException {
        if (_cfgNumbersAsStrings || (NumberOutput.notFinite(d2) && Feature.QUOTE_NON_NUMERIC_NUMBERS.enabledIn(_features))) {
            this.writeString(String.valueOf(d2));
        } else {
            this._verifyValueWrite("write a number");
            this.writeRaw(String.valueOf(d2));
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(final float f2) throws IOException {
        if (_cfgNumbersAsStrings || (NumberOutput.notFinite(f2) && Feature.QUOTE_NON_NUMERIC_NUMBERS.enabledIn(_features))) {
            this.writeString(String.valueOf(f2));
        } else {
            this._verifyValueWrite("write a number");
            this.writeRaw(String.valueOf(f2));
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(final BigDecimal bigDecimal) throws IOException {
        this._verifyValueWrite("write a number");
        if (null == bigDecimal) {
            this._writeNull();
        } else if (_cfgNumbersAsStrings) {
            this._writeQuotedRaw(this._asString(bigDecimal));
        } else {
            this.writeRaw(this._asString(bigDecimal));
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(final String str) throws IOException {
        this._verifyValueWrite("write a number");
        if (null == str) {
            this._writeNull();
        } else if (_cfgNumbersAsStrings) {
            this._writeQuotedRaw(str);
        } else {
            this.writeRaw(str);
        }
    }

    private final void _writeQuotedRaw(final String str) throws IOException {
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final byte[] bArr = _outputBuffer;
        final int i2 = _outputTail;
        _outputTail = i2 + 1;
        bArr[i2] = _quoteChar;
        this.writeRaw(str);
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final byte[] bArr2 = _outputBuffer;
        final int i3 = _outputTail;
        _outputTail = i3 + 1;
        bArr2[i3] = _quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeBoolean(final boolean z) throws IOException {
        this._verifyValueWrite("write a boolean value");
        if (_outputTail + 5 >= _outputEnd) {
            this._flushBuffer();
        }
        final byte[] bArr = z ? UTF8JsonGenerator.TRUE_BYTES : UTF8JsonGenerator.FALSE_BYTES;
        final int length = bArr.length;
        System.arraycopy(bArr, 0, _outputBuffer, _outputTail, length);
        _outputTail += length;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNull() throws IOException {
        this._verifyValueWrite("write a null");
        this._writeNull();
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase
    protected final void _verifyValueWrite(final String str) throws IOException {
        final byte b2;
        final int iWriteValue = _writeContext.writeValue();
        if (null != this._cfgPrettyPrinter) {
            this._verifyPrettyValueWrite(str, iWriteValue);
            return;
        }
        if (1 == iWriteValue) {
            b2 = 44;
        } else {
            if (2 != iWriteValue) {
                if (3 != iWriteValue) {
                    if (5 != iWriteValue) {
                        return;
                    }
                    this._reportCantWriteValueExpectName(str);
                    return;
                }
                final SerializableString serializableString = _rootValueSeparator;
                if (null != serializableString) {
                    final byte[] bArrAsUnquotedUTF8 = serializableString.asUnquotedUTF8();
                    if (0 < bArrAsUnquotedUTF8.length) {
                        this._writeBytes(bArrAsUnquotedUTF8);
                        return;
                    }
                    return;
                }
                return;
            }
            b2 = 58;
        }
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final byte[] bArr = _outputBuffer;
        final int i2 = _outputTail;
        _outputTail = i2 + 1;
        bArr[i2] = b2;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator, java.io.Flushable
    public void flush() throws IOException {
        this._flushBuffer();
        if (null == this._outputStream || !this.isEnabled(Feature.FLUSH_PASSED_TO_STREAM)) {
            return;
        }
        _outputStream.flush();
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
        if (null != this._outputBuffer && this.isEnabled(Feature.AUTO_CLOSE_JSON_CONTENT)) {
            while (true) {
                final JsonStreamContext outputContext = this.getOutputContext();
                if (outputContext.inArray()) {
                    this.writeEndArray();
                } else if (!outputContext.inObject()) {
                    break;
                } else {
                    this.writeEndObject();
                }
            }
        }
        this._flushBuffer();
        _outputTail = 0;
        if (null != this._outputStream) {
            if (_ioContext.isResourceManaged() || this.isEnabled(Feature.AUTO_CLOSE_TARGET)) {
                _outputStream.close();
            } else if (this.isEnabled(Feature.FLUSH_PASSED_TO_STREAM)) {
                _outputStream.flush();
            }
        }
        this._releaseBuffers();
    }

    protected void _releaseBuffers() {
        final byte[] bArr = _outputBuffer;
        if (null != bArr && _bufferRecyclable) {
            _outputBuffer = null;
            _ioContext.releaseWriteEncodingBuffer(bArr);
        }
        final char[] cArr = _charBuffer;
        if (null != cArr) {
            _charBuffer = null;
            _ioContext.releaseConcatBuffer(cArr);
        }
    }

    private final void _writeBytes(final byte[] bArr) throws IOException {
        final int length = bArr.length;
        if (_outputTail + length > _outputEnd) {
            this._flushBuffer();
            if (512 < length) {
                _outputStream.write(bArr, 0, length);
                return;
            }
        }
        System.arraycopy(bArr, 0, _outputBuffer, _outputTail, length);
        _outputTail += length;
    }

    private final void _writeStringSegments(final String str, final boolean z) throws IOException {
        if (z) {
            if (_outputTail >= _outputEnd) {
                this._flushBuffer();
            }
            final byte[] bArr = _outputBuffer;
            final int i2 = _outputTail;
            _outputTail = i2 + 1;
            bArr[i2] = _quoteChar;
        }
        int length = str.length();
        int i3 = 0;
        while (0 < length) {
            final int iMin = Math.min(_outputMaxContiguous, length);
            if (_outputTail + iMin > _outputEnd) {
                this._flushBuffer();
            }
            this._writeStringSegment(str, i3, iMin);
            i3 += iMin;
            length -= iMin;
        }
        if (z) {
            if (_outputTail >= _outputEnd) {
                this._flushBuffer();
            }
            final byte[] bArr2 = _outputBuffer;
            final int i4 = _outputTail;
            _outputTail = i4 + 1;
            bArr2[i4] = _quoteChar;
        }
    }

    private final void _writeStringSegments(final char[] cArr, int i2, int i3) throws IOException {
        do {
            final int iMin = Math.min(_outputMaxContiguous, i3);
            if (_outputTail + iMin > _outputEnd) {
                this._flushBuffer();
            }
            this._writeStringSegment(cArr, i2, iMin);
            i2 += iMin;
            i3 -= iMin;
        } while (0 < i3);
    }

    private final void _writeStringSegments(final String str, int i2, int i3) throws IOException {
        do {
            final int iMin = Math.min(_outputMaxContiguous, i3);
            if (_outputTail + iMin > _outputEnd) {
                this._flushBuffer();
            }
            this._writeStringSegment(str, i2, iMin);
            i2 += iMin;
            i3 -= iMin;
        } while (0 < i3);
    }

    private final void _writeStringSegment(final char[] cArr, int i2, final int i3) throws IOException {
        final int i4 = i3 + i2;
        int i5 = _outputTail;
        final byte[] bArr = _outputBuffer;
        final int[] iArr = _outputEscapes;
        while (i2 < i4) {
            final char c2 = cArr[i2];
            if ('\u007f' < c2 || 0 != iArr[c2]) {
                break;
            }
            bArr[i5] = (byte) c2;
            i2++;
            i5++;
        }
        _outputTail = i5;
        if (i2 < i4) {
            if (null != this._characterEscapes) {
                this._writeCustomStringSegment2(cArr, i2, i4);
            } else if (0 == this._maximumNonEscapedChar) {
                this._writeStringSegment2(cArr, i2, i4);
            } else {
                this._writeStringSegmentASCII2(cArr, i2, i4);
            }
        }
    }

    private final void _writeStringSegment(final String str, int i2, final int i3) throws IOException {
        final int i4 = i3 + i2;
        int i5 = _outputTail;
        final byte[] bArr = _outputBuffer;
        final int[] iArr = _outputEscapes;
        while (i2 < i4) {
            final char cCharAt = str.charAt(i2);
            if ('\u007f' < cCharAt || 0 != iArr[cCharAt]) {
                break;
            }
            bArr[i5] = (byte) cCharAt;
            i2++;
            i5++;
        }
        _outputTail = i5;
        if (i2 < i4) {
            if (null != this._characterEscapes) {
                this._writeCustomStringSegment2(str, i2, i4);
            } else if (0 == this._maximumNonEscapedChar) {
                this._writeStringSegment2(str, i2, i4);
            } else {
                this._writeStringSegmentASCII2(str, i2, i4);
            }
        }
    }

    private final void _writeStringSegment2(final char[] cArr, int i2, final int i3) throws IOException {
        if (_outputTail + ((i3 - i2) * 6) > _outputEnd) {
            this._flushBuffer();
        }
        int i_outputMultiByteChar = _outputTail;
        final byte[] bArr = _outputBuffer;
        final int[] iArr = _outputEscapes;
        while (i2 < i3) {
            final int i4 = i2 + 1;
            final char c2 = cArr[i2];
            if ('\u007f' >= c2) {
                final int i5 = iArr[c2];
                if (0 == i5) {
                    bArr[i_outputMultiByteChar] = (byte) c2;
                    i2 = i4;
                    i_outputMultiByteChar++;
                } else if (0 < i5) {
                    final int i6 = i_outputMultiByteChar + 1;
                    bArr[i_outputMultiByteChar] = 92;
                    i_outputMultiByteChar += 2;
                    bArr[i6] = (byte) i5;
                } else {
                    i_outputMultiByteChar = this._writeGenericEscape(c2, i_outputMultiByteChar);
                }
            } else if ('\u07ff' >= c2) {
                final int i7 = i_outputMultiByteChar + 1;
                bArr[i_outputMultiByteChar] = (byte) ((c2 >> 6) | Wbxml.EXT_0);
                i_outputMultiByteChar += 2;
                bArr[i7] = (byte) ((c2 & '?') | 128);
            } else {
                i_outputMultiByteChar = this._outputMultiByteChar(c2, i_outputMultiByteChar);
            }
            i2 = i4;
        }
        _outputTail = i_outputMultiByteChar;
    }

    private final void _writeStringSegment2(final String str, int i2, final int i3) throws IOException {
        if (_outputTail + ((i3 - i2) * 6) > _outputEnd) {
            this._flushBuffer();
        }
        int i_outputMultiByteChar = _outputTail;
        final byte[] bArr = _outputBuffer;
        final int[] iArr = _outputEscapes;
        while (i2 < i3) {
            final int i4 = i2 + 1;
            final char cCharAt = str.charAt(i2);
            if ('\u007f' >= cCharAt) {
                final int i5 = iArr[cCharAt];
                if (0 == i5) {
                    bArr[i_outputMultiByteChar] = (byte) cCharAt;
                    i2 = i4;
                    i_outputMultiByteChar++;
                } else if (0 < i5) {
                    final int i6 = i_outputMultiByteChar + 1;
                    bArr[i_outputMultiByteChar] = 92;
                    i_outputMultiByteChar += 2;
                    bArr[i6] = (byte) i5;
                } else {
                    i_outputMultiByteChar = this._writeGenericEscape(cCharAt, i_outputMultiByteChar);
                }
            } else if ('\u07ff' >= cCharAt) {
                final int i7 = i_outputMultiByteChar + 1;
                bArr[i_outputMultiByteChar] = (byte) ((cCharAt >> 6) | Wbxml.EXT_0);
                i_outputMultiByteChar += 2;
                bArr[i7] = (byte) ((cCharAt & '?') | 128);
            } else {
                i_outputMultiByteChar = this._outputMultiByteChar(cCharAt, i_outputMultiByteChar);
            }
            i2 = i4;
        }
        _outputTail = i_outputMultiByteChar;
    }

    private final void _writeStringSegmentASCII2(final char[] cArr, int i2, final int i3) throws IOException {
        if (_outputTail + ((i3 - i2) * 6) > _outputEnd) {
            this._flushBuffer();
        }
        int i_outputMultiByteChar = _outputTail;
        final byte[] bArr = _outputBuffer;
        final int[] iArr = _outputEscapes;
        final int i4 = _maximumNonEscapedChar;
        while (i2 < i3) {
            final int i5 = i2 + 1;
            final char c2 = cArr[i2];
            if ('\u007f' >= c2) {
                final int i6 = iArr[c2];
                if (0 == i6) {
                    bArr[i_outputMultiByteChar] = (byte) c2;
                    i2 = i5;
                    i_outputMultiByteChar++;
                } else if (0 < i6) {
                    final int i7 = i_outputMultiByteChar + 1;
                    bArr[i_outputMultiByteChar] = 92;
                    i_outputMultiByteChar += 2;
                    bArr[i7] = (byte) i6;
                } else {
                    i_outputMultiByteChar = this._writeGenericEscape(c2, i_outputMultiByteChar);
                }
            } else if (c2 > i4) {
                i_outputMultiByteChar = this._writeGenericEscape(c2, i_outputMultiByteChar);
            } else if ('\u07ff' >= c2) {
                final int i8 = i_outputMultiByteChar + 1;
                bArr[i_outputMultiByteChar] = (byte) ((c2 >> 6) | Wbxml.EXT_0);
                i_outputMultiByteChar += 2;
                bArr[i8] = (byte) ((c2 & '?') | 128);
            } else {
                i_outputMultiByteChar = this._outputMultiByteChar(c2, i_outputMultiByteChar);
            }
            i2 = i5;
        }
        _outputTail = i_outputMultiByteChar;
    }

    private final void _writeStringSegmentASCII2(final String str, int i2, final int i3) throws IOException {
        if (_outputTail + ((i3 - i2) * 6) > _outputEnd) {
            this._flushBuffer();
        }
        int i_outputMultiByteChar = _outputTail;
        final byte[] bArr = _outputBuffer;
        final int[] iArr = _outputEscapes;
        final int i4 = _maximumNonEscapedChar;
        while (i2 < i3) {
            final int i5 = i2 + 1;
            final char cCharAt = str.charAt(i2);
            if ('\u007f' >= cCharAt) {
                final int i6 = iArr[cCharAt];
                if (0 == i6) {
                    bArr[i_outputMultiByteChar] = (byte) cCharAt;
                    i2 = i5;
                    i_outputMultiByteChar++;
                } else if (0 < i6) {
                    final int i7 = i_outputMultiByteChar + 1;
                    bArr[i_outputMultiByteChar] = 92;
                    i_outputMultiByteChar += 2;
                    bArr[i7] = (byte) i6;
                } else {
                    i_outputMultiByteChar = this._writeGenericEscape(cCharAt, i_outputMultiByteChar);
                }
            } else if (cCharAt > i4) {
                i_outputMultiByteChar = this._writeGenericEscape(cCharAt, i_outputMultiByteChar);
            } else if ('\u07ff' >= cCharAt) {
                final int i8 = i_outputMultiByteChar + 1;
                bArr[i_outputMultiByteChar] = (byte) ((cCharAt >> 6) | Wbxml.EXT_0);
                i_outputMultiByteChar += 2;
                bArr[i8] = (byte) ((cCharAt & '?') | 128);
            } else {
                i_outputMultiByteChar = this._outputMultiByteChar(cCharAt, i_outputMultiByteChar);
            }
            i2 = i5;
        }
        _outputTail = i_outputMultiByteChar;
    }

    private final void _writeCustomStringSegment2(final char[] cArr, int i2, final int i3) throws IOException {
        if (_outputTail + ((i3 - i2) * 6) > _outputEnd) {
            this._flushBuffer();
        }
        int i_outputMultiByteChar = _outputTail;
        final byte[] bArr = _outputBuffer;
        final int[] iArr = _outputEscapes;
        int i4 = _maximumNonEscapedChar;
        if (0 >= i4) {
            i4 = 65535;
        }
        final CharacterEscapes characterEscapes = _characterEscapes;
        while (i2 < i3) {
            final int i5 = i2 + 1;
            final char c2 = cArr[i2];
            if ('\u007f' >= c2) {
                final int i6 = iArr[c2];
                if (0 == i6) {
                    bArr[i_outputMultiByteChar] = (byte) c2;
                    i2 = i5;
                    i_outputMultiByteChar++;
                } else if (0 < i6) {
                    final int i7 = i_outputMultiByteChar + 1;
                    bArr[i_outputMultiByteChar] = 92;
                    i_outputMultiByteChar += 2;
                    bArr[i7] = (byte) i6;
                } else if (-2 == i6) {
                    final SerializableString escapeSequence = characterEscapes.getEscapeSequence(c2);
                    if (null == escapeSequence) {
                        this._reportError("Invalid custom escape definitions; custom escape not found for character code 0x" + Integer.toHexString(c2) + ", although was supposed to have one");
                    }
                    i_outputMultiByteChar = this._writeCustomEscape(bArr, i_outputMultiByteChar, escapeSequence, i3 - i5);
                } else {
                    i_outputMultiByteChar = this._writeGenericEscape(c2, i_outputMultiByteChar);
                }
            } else if (c2 > i4) {
                i_outputMultiByteChar = this._writeGenericEscape(c2, i_outputMultiByteChar);
            } else {
                final SerializableString escapeSequence2 = characterEscapes.getEscapeSequence(c2);
                if (null != escapeSequence2) {
                    i_outputMultiByteChar = this._writeCustomEscape(bArr, i_outputMultiByteChar, escapeSequence2, i3 - i5);
                } else if ('\u07ff' >= c2) {
                    final int i8 = i_outputMultiByteChar + 1;
                    bArr[i_outputMultiByteChar] = (byte) ((c2 >> 6) | Wbxml.EXT_0);
                    i_outputMultiByteChar += 2;
                    bArr[i8] = (byte) ((c2 & '?') | 128);
                } else {
                    i_outputMultiByteChar = this._outputMultiByteChar(c2, i_outputMultiByteChar);
                }
            }
            i2 = i5;
        }
        _outputTail = i_outputMultiByteChar;
    }

    private final void _writeCustomStringSegment2(final String str, int i2, final int i3) throws IOException {
        if (_outputTail + ((i3 - i2) * 6) > _outputEnd) {
            this._flushBuffer();
        }
        int i_outputMultiByteChar = _outputTail;
        final byte[] bArr = _outputBuffer;
        final int[] iArr = _outputEscapes;
        int i4 = _maximumNonEscapedChar;
        if (0 >= i4) {
            i4 = 65535;
        }
        final CharacterEscapes characterEscapes = _characterEscapes;
        while (i2 < i3) {
            final int i5 = i2 + 1;
            final char cCharAt = str.charAt(i2);
            if ('\u007f' >= cCharAt) {
                final int i6 = iArr[cCharAt];
                if (0 == i6) {
                    bArr[i_outputMultiByteChar] = (byte) cCharAt;
                    i2 = i5;
                    i_outputMultiByteChar++;
                } else if (0 < i6) {
                    final int i7 = i_outputMultiByteChar + 1;
                    bArr[i_outputMultiByteChar] = 92;
                    i_outputMultiByteChar += 2;
                    bArr[i7] = (byte) i6;
                } else if (-2 == i6) {
                    final SerializableString escapeSequence = characterEscapes.getEscapeSequence(cCharAt);
                    if (null == escapeSequence) {
                        this._reportError("Invalid custom escape definitions; custom escape not found for character code 0x" + Integer.toHexString(cCharAt) + ", although was supposed to have one");
                    }
                    i_outputMultiByteChar = this._writeCustomEscape(bArr, i_outputMultiByteChar, escapeSequence, i3 - i5);
                } else {
                    i_outputMultiByteChar = this._writeGenericEscape(cCharAt, i_outputMultiByteChar);
                }
            } else if (cCharAt > i4) {
                i_outputMultiByteChar = this._writeGenericEscape(cCharAt, i_outputMultiByteChar);
            } else {
                final SerializableString escapeSequence2 = characterEscapes.getEscapeSequence(cCharAt);
                if (null != escapeSequence2) {
                    i_outputMultiByteChar = this._writeCustomEscape(bArr, i_outputMultiByteChar, escapeSequence2, i3 - i5);
                } else if ('\u07ff' >= cCharAt) {
                    final int i8 = i_outputMultiByteChar + 1;
                    bArr[i_outputMultiByteChar] = (byte) ((cCharAt >> 6) | Wbxml.EXT_0);
                    i_outputMultiByteChar += 2;
                    bArr[i8] = (byte) ((cCharAt & '?') | 128);
                } else {
                    i_outputMultiByteChar = this._outputMultiByteChar(cCharAt, i_outputMultiByteChar);
                }
            }
            i2 = i5;
        }
        _outputTail = i_outputMultiByteChar;
    }

    private final int _writeCustomEscape(final byte[] bArr, final int i2, final SerializableString serializableString, final int i3) throws IOException {
        final byte[] bArrAsUnquotedUTF8 = serializableString.asUnquotedUTF8();
        final int length = bArrAsUnquotedUTF8.length;
        if (6 < length) {
            return this._handleLongCustomEscape(bArr, i2, _outputEnd, bArrAsUnquotedUTF8, i3);
        }
        System.arraycopy(bArrAsUnquotedUTF8, 0, bArr, i2, length);
        return i2 + length;
    }

    private final int _handleLongCustomEscape(final byte[] bArr, int i2, final int i3, final byte[] bArr2, final int i4) throws IOException {
        final int length = bArr2.length;
        if (i2 + length > i3) {
            _outputTail = i2;
            this._flushBuffer();
            i2 = _outputTail;
            if (length > bArr.length) {
                _outputStream.write(bArr2, 0, length);
                return i2;
            }
        }
        System.arraycopy(bArr2, 0, bArr, i2, length);
        final int i5 = i2 + length;
        if ((i4 * 6) + i5 <= i3) {
            return i5;
        }
        _outputTail = i5;
        this._flushBuffer();
        return _outputTail;
    }

    protected final void _writeBinary(final Base64Variant base64Variant, final byte[] bArr, int i2, final int i3) throws IOException {
        int iEncodeBase64Chunk = 0;
        final int i4 = i3 - 3;
        final int i5 = _outputEnd - 6;
        int maxLineLength = base64Variant.getMaxLineLength();
        while (true) {
            int i6 = maxLineLength >> 2;
            while (i2 <= i4) {
                if (_outputTail > i5) {
                    this._flushBuffer();
                }
                final int i7 = i2 + 2;
                final int i8 = ((bArr[i2 + 1] & 255) | (bArr[i2] << 8)) << 8;
                i2 += 3;
                iEncodeBase64Chunk = base64Variant.encodeBase64Chunk(i8 | (bArr[i7] & 255), _outputBuffer, _outputTail);
                _outputTail = iEncodeBase64Chunk;
                i6--;
                if (0 >= i6) {
                    break;
                }
            }
            final byte[] bArr2 = _outputBuffer;
            final int i9 = iEncodeBase64Chunk + 1;
            _outputTail = i9;
            bArr2[iEncodeBase64Chunk] = 92;
            _outputTail = iEncodeBase64Chunk + 2;
            bArr2[i9] = 110;
            maxLineLength = base64Variant.getMaxLineLength();
        }


    }

    protected final int _writeBinary(final Base64Variant base64Variant, final InputStream inputStream, final byte[] bArr, final int i2) throws IOException {
        final int i_readMore;
        final int i3 = _outputEnd - 6;
        int i4 = 2;
        int i5 = -3;
        int i6 = i2;
        int maxLineLength = base64Variant.getMaxLineLength() >> 2;
        int i7 = 0;
        int i_readMore2 = 0;
        while (true) {
            if (2 >= i6) {
                break;
            }
            if (i7 > i5) {
                i_readMore2 = this._readMore(inputStream, bArr, i7, i_readMore2, i6);
                if (3 > i_readMore2) {
                    i7 = 0;
                    break;
                }
                i5 = i_readMore2 - 3;
                i7 = 0;
            }
            if (_outputTail > i3) {
                this._flushBuffer();
            }
            final int i8 = i7 + 2;
            final int i9 = ((bArr[i7 + 1] & 255) | (bArr[i7] << 8)) << 8;
            i7 += 3;
            i6 -= 3;
            final int iEncodeBase64Chunk = base64Variant.encodeBase64Chunk(i9 | (bArr[i8] & 255), _outputBuffer, _outputTail);
            _outputTail = iEncodeBase64Chunk;
            maxLineLength--;
            if (0 >= maxLineLength) {
                final byte[] bArr2 = _outputBuffer;
                final int i10 = iEncodeBase64Chunk + 1;
                _outputTail = i10;
                bArr2[iEncodeBase64Chunk] = 92;
                _outputTail = iEncodeBase64Chunk + 2;
                bArr2[i10] = 110;
                maxLineLength = base64Variant.getMaxLineLength() >> 2;
            }
        }
        if (0 >= i6 || 0 >= (i_readMore = _readMore(inputStream, bArr, i7, i_readMore2, i6))) {
            return i6;
        }
        if (_outputTail > i3) {
            this._flushBuffer();
        }
        int i11 = bArr[0] << 16;
        if (1 < i_readMore) {
            i11 |= (bArr[1] & 255) << 8;
        } else {
            i4 = 1;
        }
        _outputTail = base64Variant.encodeBase64Partial(i11, i4, _outputBuffer, _outputTail);
        return i6 - i4;
    }

    protected final int _writeBinary(final Base64Variant base64Variant, final InputStream inputStream, final byte[] bArr) throws IOException {
        final int i2 = _outputEnd - 6;
        int i3 = 2;
        int i4 = -3;
        int maxLineLength = base64Variant.getMaxLineLength() >> 2;
        int i5 = 0;
        int i_readMore = 0;
        int i6 = 0;
        while (true) {
            if (i5 > i4) {
                i_readMore = this._readMore(inputStream, bArr, i5, i_readMore, bArr.length);
                if (3 > i_readMore) {
                    break;
                }
                i4 = i_readMore - 3;
                i5 = 0;
            }
            if (_outputTail > i2) {
                this._flushBuffer();
            }
            final int i7 = i5 + 2;
            final int i8 = ((bArr[i5 + 1] & 255) | (bArr[i5] << 8)) << 8;
            i5 += 3;
            i6 += 3;
            final int iEncodeBase64Chunk = base64Variant.encodeBase64Chunk(i8 | (bArr[i7] & 255), _outputBuffer, _outputTail);
            _outputTail = iEncodeBase64Chunk;
            maxLineLength--;
            if (0 >= maxLineLength) {
                final byte[] bArr2 = _outputBuffer;
                final int i9 = iEncodeBase64Chunk + 1;
                _outputTail = i9;
                bArr2[iEncodeBase64Chunk] = 92;
                _outputTail = iEncodeBase64Chunk + 2;
                bArr2[i9] = 110;
                maxLineLength = base64Variant.getMaxLineLength() >> 2;
            }
        }
        if (0 >= i_readMore) {
            return i6;
        }
        if (_outputTail > i2) {
            this._flushBuffer();
        }
        int i10 = bArr[0] << 16;
        if (1 < i_readMore) {
            i10 |= (bArr[1] & 255) << 8;
        } else {
            i3 = 1;
        }
        final int i11 = i6 + i3;
        _outputTail = base64Variant.encodeBase64Partial(i10, i3, _outputBuffer, _outputTail);
        return i11;
    }

    private final int _readMore(final InputStream inputStream, final byte[] bArr, int i2, final int i3, final int i4) throws IOException {
        int i5 = 0;
        while (i2 < i3) {
            bArr[i5] = bArr[i2];
            i5++;
            i2++;
        }
        final int iMin = Math.min(i4, bArr.length);
        do {
            final int i6 = iMin - i5;
            if (0 == i6) {
                break;
            }
            final int i7 = inputStream.read(bArr, i5, i6);
            if (0 > i7) {
                return i5;
            }
            i5 += i7;
        } while (3 > i5);
        return i5;
    }

    private final int _outputRawMultiByteChar(final int i2, final char[] cArr, final int i3, final int i4) throws IOException {
        if (55296 <= i2 && 57343 >= i2) {
            if (i3 >= i4 || null == cArr) {
                this._reportError(String.format("Split surrogate on writeRaw() input (last character): first character 0x%4x", Integer.valueOf(i2)));
            } else {
                this._outputSurrogates(i2, cArr[i3]);
            }
            return i3 + 1;
        }
        final byte[] bArr = _outputBuffer;
        final int i5 = _outputTail;
        final int i6 = i5 + 1;
        _outputTail = i6;
        bArr[i5] = (byte) ((i2 >> 12) | 224);
        final int i7 = i5 + 2;
        _outputTail = i7;
        bArr[i6] = (byte) (((i2 >> 6) & 63) | 128);
        _outputTail = i5 + 3;
        bArr[i7] = (byte) ((i2 & 63) | 128);
        return i3;
    }

    protected final void _outputSurrogates(final int i2, final int i3) throws IOException {
        final int i_decodeSurrogate = this._decodeSurrogate(i2, i3);
        if (_outputTail + 4 > _outputEnd) {
            this._flushBuffer();
        }
        final byte[] bArr = _outputBuffer;
        final int i4 = _outputTail;
        final int i5 = i4 + 1;
        _outputTail = i5;
        bArr[i4] = (byte) ((i_decodeSurrogate >> 18) | 240);
        final int i6 = i4 + 2;
        _outputTail = i6;
        bArr[i5] = (byte) (((i_decodeSurrogate >> 12) & 63) | 128);
        final int i7 = i4 + 3;
        _outputTail = i7;
        bArr[i6] = (byte) (((i_decodeSurrogate >> 6) & 63) | 128);
        _outputTail = i4 + 4;
        bArr[i7] = (byte) ((i_decodeSurrogate & 63) | 128);
    }

    private final int _outputMultiByteChar(final int i2, final int i3) throws IOException {
        final byte[] bArr = _outputBuffer;
        if (55296 <= i2 && 57343 >= i2) {
            bArr[i3] = 92;
            bArr[i3 + 1] = 117;
            final byte[] bArr2 = UTF8JsonGenerator.HEX_CHARS;
            bArr[i3 + 2] = bArr2[(i2 >> 12) & 15];
            bArr[i3 + 3] = bArr2[(i2 >> 8) & 15];
            final int i4 = i3 + 5;
            bArr[i3 + 4] = bArr2[(i2 >> 4) & 15];
            final int i5 = i3 + 6;
            bArr[i4] = bArr2[i2 & 15];
            return i5;
        }
        bArr[i3] = (byte) ((i2 >> 12) | 224);
        final int i6 = i3 + 2;
        bArr[i3 + 1] = (byte) (((i2 >> 6) & 63) | 128);
        final int i7 = i3 + 3;
        bArr[i6] = (byte) ((i2 & 63) | 128);
        return i7;
    }

    private final void _writeNull() throws IOException {
        if (_outputTail + 4 >= _outputEnd) {
            this._flushBuffer();
        }
        System.arraycopy(UTF8JsonGenerator.NULL_BYTES, 0, _outputBuffer, _outputTail, 4);
        _outputTail += 4;
    }

    private int _writeGenericEscape(int i2, final int i3) throws IOException {
        final int i4;
        final byte[] bArr = _outputBuffer;
        bArr[i3] = 92;
        final int i5 = i3 + 2;
        bArr[i3 + 1] = 117;
        if (255 < i2) {
            final int i6 = i2 >> 8;
            final int i7 = i3 + 3;
            final byte[] bArr2 = UTF8JsonGenerator.HEX_CHARS;
            bArr[i5] = bArr2[(i6 & 255) >> 4];
            i4 = i3 + 4;
            bArr[i7] = bArr2[i6 & 15];
            i2 &= 255;
        } else {
            final int i8 = i3 + 3;
            bArr[i5] = 48;
            i4 = i3 + 4;
            bArr[i8] = 48;
        }
        final int i9 = i4 + 1;
        final byte[] bArr3 = UTF8JsonGenerator.HEX_CHARS;
        bArr[i4] = bArr3[i2 >> 4];
        final int i10 = i4 + 2;
        bArr[i9] = bArr3[i2 & 15];
        return i10;
    }

    protected final void _flushBuffer() throws IOException {
        final int i2 = _outputTail;
        if (0 < i2) {
            _outputTail = 0;
            _outputStream.write(_outputBuffer, 0, i2);
        }
    }
}
