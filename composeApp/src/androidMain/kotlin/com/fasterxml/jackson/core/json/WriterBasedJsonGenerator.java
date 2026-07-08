package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.NumberOutput;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;

public class WriterBasedJsonGenerator extends JsonGeneratorImpl {
    protected static final char[] HEX_CHARS = CharTypes.copyHexChars();
    protected char[] _copyBuffer;
    protected SerializableString _currentEscape;
    protected char[] _entityBuffer;
    protected char[] _outputBuffer;
    protected int _outputEnd;
    protected int _outputHead;
    protected int _outputTail;
    protected char _quoteChar;
    protected final Writer _writer;

    public WriterBasedJsonGenerator(final IOContext iOContext, final int i2, final ObjectCodec objectCodec, final Writer writer, final char c2) {
        super(iOContext, i2, objectCodec);
        _writer = writer;
        final char[] cArrAllocConcatBuffer = iOContext.allocConcatBuffer();
        _outputBuffer = cArrAllocConcatBuffer;
        _outputEnd = cArrAllocConcatBuffer.length;
        _quoteChar = c2;
        if ('\"' != c2) {
            _outputEscapes = CharTypes.get7BitOutputEscapes(c2);
        }
    }

    public void writeFieldName(final String str) throws IOException {
        final int iWriteFieldName = _writeContext.writeFieldName(str);
        if (4 == iWriteFieldName) {
            this._reportError("Can not write a field name, expecting a value");
        }
        this._writeFieldName(str, 1 == iWriteFieldName);
    }

    public void writeFieldName(final SerializableString serializableString) throws IOException {
        final int iWriteFieldName = _writeContext.writeFieldName(serializableString.getValue());
        if (4 == iWriteFieldName) {
            this._reportError("Can not write a field name, expecting a value");
        }
        this._writeFieldName(serializableString, 1 == iWriteFieldName);
    }

    protected final void _writeFieldName(final String str, final boolean z) throws IOException {
        if (null != this._cfgPrettyPrinter) {
            this._writePPFieldName(str, z);
            return;
        }
        if (_outputTail + 1 >= _outputEnd) {
            this._flushBuffer();
        }
        if (z) {
            final char[] cArr = _outputBuffer;
            final int i2 = _outputTail;
            _outputTail = i2 + 1;
            cArr[i2] = ',';
        }
        if (_cfgUnqNames) {
            this._writeString(str);
            return;
        }
        final char[] cArr2 = _outputBuffer;
        final int i3 = _outputTail;
        _outputTail = i3 + 1;
        cArr2[i3] = _quoteChar;
        this._writeString(str);
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final char[] cArr3 = _outputBuffer;
        final int i4 = _outputTail;
        _outputTail = i4 + 1;
        cArr3[i4] = _quoteChar;
    }

    protected final void _writeFieldName(final SerializableString serializableString, final boolean z) throws IOException {
        if (null != this._cfgPrettyPrinter) {
            this._writePPFieldName(serializableString, z);
            return;
        }
        if (_outputTail + 1 >= _outputEnd) {
            this._flushBuffer();
        }
        if (z) {
            final char[] cArr = _outputBuffer;
            final int i2 = _outputTail;
            _outputTail = i2 + 1;
            cArr[i2] = ',';
        }
        if (_cfgUnqNames) {
            final char[] cArrAsQuotedChars = serializableString.asQuotedChars();
            this.writeRaw(cArrAsQuotedChars, 0, cArrAsQuotedChars.length);
            return;
        }
        final char[] cArr2 = _outputBuffer;
        final int i3 = _outputTail;
        final int i4 = i3 + 1;
        _outputTail = i4;
        cArr2[i3] = _quoteChar;
        final int iAppendQuoted = serializableString.appendQuoted(cArr2, i4);
        if (0 > iAppendQuoted) {
            this._writeFieldNameTail(serializableString);
            return;
        }
        final int i5 = _outputTail + iAppendQuoted;
        _outputTail = i5;
        if (i5 >= _outputEnd) {
            this._flushBuffer();
        }
        final char[] cArr3 = _outputBuffer;
        final int i6 = _outputTail;
        _outputTail = i6 + 1;
        cArr3[i6] = _quoteChar;
    }

    private final void _writeFieldNameTail(final SerializableString serializableString) throws IOException {
        final char[] cArrAsQuotedChars = serializableString.asQuotedChars();
        this.writeRaw(cArrAsQuotedChars, 0, cArrAsQuotedChars.length);
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final char[] cArr = _outputBuffer;
        final int i2 = _outputTail;
        _outputTail = i2 + 1;
        cArr[i2] = _quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeStartArray() throws IOException {
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
        final char[] cArr = _outputBuffer;
        final int i2 = _outputTail;
        _outputTail = i2 + 1;
        cArr[i2] = '[';
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeStartArray(final Object obj) throws IOException {
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
        final char[] cArr = _outputBuffer;
        final int i2 = _outputTail;
        _outputTail = i2 + 1;
        cArr[i2] = '[';
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
        final char[] cArr = _outputBuffer;
        final int i3 = _outputTail;
        _outputTail = i3 + 1;
        cArr[i3] = '[';
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeEndArray() throws IOException {
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
            final char[] cArr = _outputBuffer;
            final int i2 = _outputTail;
            _outputTail = i2 + 1;
            cArr[i2] = ']';
        }
        _writeContext = _writeContext.clearAndGetParent();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeStartObject() throws IOException {
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
        final char[] cArr = _outputBuffer;
        final int i2 = _outputTail;
        _outputTail = i2 + 1;
        cArr[i2] = '{';
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
        final char[] cArr = _outputBuffer;
        final int i2 = _outputTail;
        _outputTail = i2 + 1;
        cArr[i2] = '{';
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeEndObject() throws IOException {
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
            final char[] cArr = _outputBuffer;
            final int i2 = _outputTail;
            _outputTail = i2 + 1;
            cArr[i2] = '}';
        }
        _writeContext = _writeContext.clearAndGetParent();
    }

    protected final void _writePPFieldName(final String str, final boolean z) throws IOException {
        if (z) {
            _cfgPrettyPrinter.writeObjectEntrySeparator(this);
        } else {
            _cfgPrettyPrinter.beforeObjectEntries(this);
        }
        if (_cfgUnqNames) {
            this._writeString(str);
            return;
        }
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final char[] cArr = _outputBuffer;
        final int i2 = _outputTail;
        _outputTail = i2 + 1;
        cArr[i2] = _quoteChar;
        this._writeString(str);
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final char[] cArr2 = _outputBuffer;
        final int i3 = _outputTail;
        _outputTail = i3 + 1;
        cArr2[i3] = _quoteChar;
    }

    protected final void _writePPFieldName(final SerializableString serializableString, final boolean z) throws IOException {
        if (z) {
            _cfgPrettyPrinter.writeObjectEntrySeparator(this);
        } else {
            _cfgPrettyPrinter.beforeObjectEntries(this);
        }
        final char[] cArrAsQuotedChars = serializableString.asQuotedChars();
        if (_cfgUnqNames) {
            this.writeRaw(cArrAsQuotedChars, 0, cArrAsQuotedChars.length);
            return;
        }
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final char[] cArr = _outputBuffer;
        final int i2 = _outputTail;
        _outputTail = i2 + 1;
        cArr[i2] = _quoteChar;
        this.writeRaw(cArrAsQuotedChars, 0, cArrAsQuotedChars.length);
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final char[] cArr2 = _outputBuffer;
        final int i3 = _outputTail;
        _outputTail = i3 + 1;
        cArr2[i3] = _quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeString(final String str) throws IOException {
        this._verifyValueWrite("write a string");
        if (null == str) {
            this._writeNull();
            return;
        }
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final char[] cArr = _outputBuffer;
        final int i2 = _outputTail;
        _outputTail = i2 + 1;
        cArr[i2] = _quoteChar;
        this._writeString(str);
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final char[] cArr2 = _outputBuffer;
        final int i3 = _outputTail;
        _outputTail = i3 + 1;
        cArr2[i3] = _quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeString(final char[] cArr, final int i2, final int i3) throws IOException {
        this._verifyValueWrite("write a string");
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final char[] cArr2 = _outputBuffer;
        final int i4 = _outputTail;
        _outputTail = i4 + 1;
        cArr2[i4] = _quoteChar;
        this._writeString(cArr, i2, i3);
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final char[] cArr3 = _outputBuffer;
        final int i5 = _outputTail;
        _outputTail = i5 + 1;
        cArr3[i5] = _quoteChar;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public void writeString(final SerializableString serializableString) throws IOException {
        this._verifyValueWrite("write a string");
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final char[] cArr = _outputBuffer;
        final int i2 = _outputTail;
        final int i3 = i2 + 1;
        _outputTail = i3;
        cArr[i2] = _quoteChar;
        final int iAppendQuoted = serializableString.appendQuoted(cArr, i3);
        if (0 > iAppendQuoted) {
            this._writeString2(serializableString);
            return;
        }
        final int i4 = _outputTail + iAppendQuoted;
        _outputTail = i4;
        if (i4 >= _outputEnd) {
            this._flushBuffer();
        }
        final char[] cArr2 = _outputBuffer;
        final int i5 = _outputTail;
        _outputTail = i5 + 1;
        cArr2[i5] = _quoteChar;
    }

    private void _writeString2(final SerializableString serializableString) throws IOException {
        final char[] cArrAsQuotedChars = serializableString.asQuotedChars();
        final int length = cArrAsQuotedChars.length;
        if (32 > length) {
            if (length > _outputEnd - _outputTail) {
                this._flushBuffer();
            }
            System.arraycopy(cArrAsQuotedChars, 0, _outputBuffer, _outputTail, length);
            _outputTail += length;
        } else {
            this._flushBuffer();
            _writer.write(cArrAsQuotedChars, 0, length);
        }
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final char[] cArr = _outputBuffer;
        final int i2 = _outputTail;
        _outputTail = i2 + 1;
        cArr[i2] = _quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(final String str) throws IOException {
        final int length = str.length();
        int i2 = _outputEnd - _outputTail;
        if (0 == i2) {
            this._flushBuffer();
            i2 = _outputEnd - _outputTail;
        }
        if (i2 >= length) {
            str.getChars(0, length, _outputBuffer, _outputTail);
            _outputTail += length;
        } else {
            this.writeRawLong(str);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(final SerializableString serializableString) throws IOException {
        final int iAppendUnquoted = serializableString.appendUnquoted(_outputBuffer, _outputTail);
        if (0 > iAppendUnquoted) {
            this.writeRaw(serializableString.getValue());
        } else {
            _outputTail += iAppendUnquoted;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(final char[] cArr, final int i2, final int i3) throws IOException {
        if (32 > i3) {
            if (i3 > _outputEnd - _outputTail) {
                this._flushBuffer();
            }
            System.arraycopy(cArr, i2, _outputBuffer, _outputTail, i3);
            _outputTail += i3;
            return;
        }
        this._flushBuffer();
        _writer.write(cArr, i2, i3);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(final char c2) throws IOException {
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final char[] cArr = _outputBuffer;
        final int i2 = _outputTail;
        _outputTail = i2 + 1;
        cArr[i2] = c2;
    }

    private void writeRawLong(final String str) throws IOException {
        final int i2 = _outputEnd;
        final int i3 = _outputTail;
        int i4 = i2 - i3;
        str.getChars(0, i4, _outputBuffer, i3);
        _outputTail += i4;
        this._flushBuffer();
        int length = str.length() - i4;
        while (true) {
            final int i5 = _outputEnd;
            if (length > i5) {
                final int i6 = i4 + i5;
                str.getChars(i4, i6, _outputBuffer, 0);
                _outputHead = 0;
                _outputTail = i5;
                this._flushBuffer();
                length -= i5;
                i4 = i6;
            } else {
                str.getChars(i4, i4 + length, _outputBuffer, 0);
                _outputHead = 0;
                _outputTail = length;
                return;
            }
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeBinary(final Base64Variant base64Variant, final byte[] bArr, final int i2, final int i3) throws IOException {
        this._verifyValueWrite("write a binary value");
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final char[] cArr = _outputBuffer;
        final int i4 = _outputTail;
        _outputTail = i4 + 1;
        cArr[i4] = _quoteChar;
        this._writeBinary(base64Variant, bArr, i2, i3 + i2);
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final char[] cArr2 = _outputBuffer;
        final int i5 = _outputTail;
        _outputTail = i5 + 1;
        cArr2[i5] = _quoteChar;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public int writeBinary(final Base64Variant base64Variant, final InputStream inputStream, int i2) throws IOException {
        this._verifyValueWrite("write a binary value");
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final char[] cArr = _outputBuffer;
        final int i3 = _outputTail;
        _outputTail = i3 + 1;
        cArr[i3] = _quoteChar;
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
            final char[] cArr2 = _outputBuffer;
            final int i4 = _outputTail;
            _outputTail = i4 + 1;
            cArr2[i4] = _quoteChar;
            return i2;
        } catch (final Throwable th) {
            _ioContext.releaseBase64Buffer(bArrAllocBase64Buffer);
            throw th;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(final short s) throws IOException {
        this._verifyValueWrite("write a number");
        if (_cfgNumbersAsStrings) {
            this._writeQuotedShort(s);
            return;
        }
        if (_outputTail + 6 >= _outputEnd) {
            this._flushBuffer();
        }
        _outputTail = NumberOutput.outputInt(s, _outputBuffer, _outputTail);
    }

    private void _writeQuotedShort(final short s) throws IOException {
        if (_outputTail + 8 >= _outputEnd) {
            this._flushBuffer();
        }
        final char[] cArr = _outputBuffer;
        final int i2 = _outputTail;
        final int i3 = i2 + 1;
        _outputTail = i3;
        cArr[i2] = _quoteChar;
        final int iOutputInt = NumberOutput.outputInt(s, cArr, i3);
        final char[] cArr2 = _outputBuffer;
        _outputTail = iOutputInt + 1;
        cArr2[iOutputInt] = _quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(final int i2) throws IOException {
        this._verifyValueWrite("write a number");
        if (_cfgNumbersAsStrings) {
            this._writeQuotedInt(i2);
            return;
        }
        if (_outputTail + 11 >= _outputEnd) {
            this._flushBuffer();
        }
        _outputTail = NumberOutput.outputInt(i2, _outputBuffer, _outputTail);
    }

    private void _writeQuotedInt(final int i2) throws IOException {
        if (_outputTail + 13 >= _outputEnd) {
            this._flushBuffer();
        }
        final char[] cArr = _outputBuffer;
        final int i3 = _outputTail;
        final int i4 = i3 + 1;
        _outputTail = i4;
        cArr[i3] = _quoteChar;
        final int iOutputInt = NumberOutput.outputInt(i2, cArr, i4);
        final char[] cArr2 = _outputBuffer;
        _outputTail = iOutputInt + 1;
        cArr2[iOutputInt] = _quoteChar;
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

    private void _writeQuotedLong(final long j2) throws IOException {
        if (_outputTail + 23 >= _outputEnd) {
            this._flushBuffer();
        }
        final char[] cArr = _outputBuffer;
        final int i2 = _outputTail;
        final int i3 = i2 + 1;
        _outputTail = i3;
        cArr[i2] = _quoteChar;
        final int iOutputLong = NumberOutput.outputLong(j2, cArr, i3);
        final char[] cArr2 = _outputBuffer;
        _outputTail = iOutputLong + 1;
        cArr2[iOutputLong] = _quoteChar;
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
        if (_cfgNumbersAsStrings || (NumberOutput.notFinite(d2) && this.isEnabled(Feature.QUOTE_NON_NUMERIC_NUMBERS))) {
            this.writeString(String.valueOf(d2));
        } else {
            this._verifyValueWrite("write a number");
            this.writeRaw(String.valueOf(d2));
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(final float f2) throws IOException {
        if (_cfgNumbersAsStrings || (NumberOutput.notFinite(f2) && this.isEnabled(Feature.QUOTE_NON_NUMERIC_NUMBERS))) {
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

    private void _writeQuotedRaw(final String str) throws IOException {
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final char[] cArr = _outputBuffer;
        final int i2 = _outputTail;
        _outputTail = i2 + 1;
        cArr[i2] = _quoteChar;
        this.writeRaw(str);
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final char[] cArr2 = _outputBuffer;
        final int i3 = _outputTail;
        _outputTail = i3 + 1;
        cArr2[i3] = _quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeBoolean(final boolean z) throws IOException {
        final int i2;
        this._verifyValueWrite("write a boolean value");
        if (_outputTail + 5 >= _outputEnd) {
            this._flushBuffer();
        }
        final int i3 = _outputTail;
        final char[] cArr = _outputBuffer;
        if (z) {
            cArr[i3] = 't';
            cArr[i3 + 1] = 'r';
            cArr[i3 + 2] = 'u';
            i2 = i3 + 3;
            cArr[i2] = 'e';
        } else {
            cArr[i3] = 'f';
            cArr[i3 + 1] = 'a';
            cArr[i3 + 2] = 'l';
            cArr[i3 + 3] = 's';
            i2 = i3 + 4;
            cArr[i2] = 'e';
        }
        _outputTail = i2 + 1;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNull() throws IOException {
        this._verifyValueWrite("write a null");
        this._writeNull();
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase
    protected final void _verifyValueWrite(final String str) throws IOException {
        final char c2;
        final int iWriteValue = _writeContext.writeValue();
        if (null != this._cfgPrettyPrinter) {
            this._verifyPrettyValueWrite(str, iWriteValue);
            return;
        }
        if (1 == iWriteValue) {
            c2 = ',';
        } else {
            if (2 != iWriteValue) {
                if (3 != iWriteValue) {
                    if (5 != iWriteValue) {
                        return;
                    }
                    this._reportCantWriteValueExpectName(str);
                    return;
                } else {
                    final SerializableString serializableString = _rootValueSeparator;
                    if (null != serializableString) {
                        this.writeRaw(serializableString.getValue());
                        return;
                    }
                    return;
                }
            }
            c2 = ':';
        }
        if (_outputTail >= _outputEnd) {
            this._flushBuffer();
        }
        final char[] cArr = _outputBuffer;
        final int i2 = _outputTail;
        _outputTail = i2 + 1;
        cArr[i2] = c2;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator, java.io.Flushable
    public void flush() throws IOException {
        this._flushBuffer();
        if (null == this._writer || !this.isEnabled(Feature.FLUSH_PASSED_TO_STREAM)) {
            return;
        }
        _writer.flush();
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
        _outputHead = 0;
        _outputTail = 0;
        if (null != this._writer) {
            if (_ioContext.isResourceManaged() || this.isEnabled(Feature.AUTO_CLOSE_TARGET)) {
                _writer.close();
            } else if (this.isEnabled(Feature.FLUSH_PASSED_TO_STREAM)) {
                _writer.flush();
            }
        }
        this._releaseBuffers();
    }

    protected void _releaseBuffers() {
        final char[] cArr = _outputBuffer;
        if (null != cArr) {
            _outputBuffer = null;
            _ioContext.releaseConcatBuffer(cArr);
        }
        final char[] cArr2 = _copyBuffer;
        if (null != cArr2) {
            _copyBuffer = null;
            _ioContext.releaseNameCopyBuffer(cArr2);
        }
    }

    private void _writeString(final String str) throws IOException {
        final int length = str.length();
        final int i2 = _outputEnd;
        if (length > i2) {
            this._writeLongString(str);
            return;
        }
        if (_outputTail + length > i2) {
            this._flushBuffer();
        }
        str.getChars(0, length, _outputBuffer, _outputTail);
        if (null != this._characterEscapes) {
            this._writeStringCustom(length);
            return;
        }
        final int i3 = _maximumNonEscapedChar;
        if (0 != i3) {
            this._writeStringASCII(length, i3);
        } else {
            this._writeString2(length);
        }
    }

    private void _writeString2(final int i2) throws IOException {
        int i3 = 0;
        final int i4 = _outputTail + i2;
        final int[] iArr = _outputEscapes;
        final int length = iArr.length;
        while (_outputTail < i4) {
            do {
                final char[] cArr = _outputBuffer;
                final int i5 = _outputTail;
                final char c2 = cArr[i5];
                if (c2 >= length || 0 == iArr[c2]) {
                    i3 = i5 + 1;
                    _outputTail = i3;
                } else {
                    final int i6 = _outputHead;
                    final int i7 = i5 - i6;
                    if (0 < i7) {
                        _writer.write(cArr, i6, i7);
                    }
                    final char[] cArr2 = _outputBuffer;
                    final int i8 = _outputTail;
                    _outputTail = i8 + 1;
                    final char c3 = cArr2[i8];
                    this._prependOrWriteCharacterEscape(c3, iArr[c3]);
                }
            } while (i3 < i4);
            return;
        }
    }

    private void _writeLongString(final String str) throws IOException {
        this._flushBuffer();
        final int length = str.length();
        int i2 = 0;
        while (true) {
            int i3 = _outputEnd;
            if (i2 + i3 > length) {
                i3 = length - i2;
            }
            final int i4 = i2 + i3;
            str.getChars(i2, i4, _outputBuffer, 0);
            if (null != this._characterEscapes) {
                this._writeSegmentCustom(i3);
            } else {
                final int i5 = _maximumNonEscapedChar;
                if (0 != i5) {
                    this._writeSegmentASCII(i3, i5);
                } else {
                    this._writeSegment(i3);
                }
            }
            if (i4 >= length) {
                return;
            } else {
                i2 = i4;
            }
        }
    }

    private void _writeSegment(final int i2) throws IOException {
        char[] cArr;
        char c2;
        final int[] iArr = _outputEscapes;
        final int length = iArr.length;
        int i3 = 0;
        int i_prependOrWriteCharacterEscape = 0;
        while (i3 < i2) {
            do {
                cArr = _outputBuffer;
                c2 = cArr[i3];
                if (c2 < length && 0 != iArr[c2]) {
                    break;
                } else {
                    i3++;
                }
            } while (i3 < i2);
            final int i4 = i3 - i_prependOrWriteCharacterEscape;
            if (0 < i4) {
                _writer.write(cArr, i_prependOrWriteCharacterEscape, i4);
                if (i3 >= i2) {
                    return;
                }
            }
            i3++;
            i_prependOrWriteCharacterEscape = this._prependOrWriteCharacterEscape(_outputBuffer, i3, i2, c2, iArr[c2]);
        }
    }
    private void _writeString(final char[] cArr, int i2, final int i3) throws IOException {
        if (null != this._characterEscapes) {
            this._writeStringCustom(cArr, i2, i3);
            return;
        }
        final int i4 = _maximumNonEscapedChar;
        if (0 != i4) {
            this._writeStringASCII(cArr, i2, i3, i4);
            return;
        }
        final int i5 = i3 + i2;
        final int[] iArr = _outputEscapes;
        final int length = iArr.length;
        while (i2 < i5) {
            int i6 = i2;
            do {
                final char c2 = cArr[i6];
                if (c2 < length && 0 != iArr[c2]) {
                    break;
                } else {
                    i6++;
                }
            } while (i6 < i5);
            final int i7 = i6 - i2;
            if (32 > i7) {
                if (_outputTail + i7 > _outputEnd) {
                    this._flushBuffer();
                }
                if (0 < i7) {
                    System.arraycopy(cArr, i2, _outputBuffer, _outputTail, i7);
                    _outputTail += i7;
                }
            } else {
                this._flushBuffer();
                _writer.write(cArr, i2, i7);
            }
            if (i6 >= i5) {
                return;
            }
            i2 = i6 + 1;
            final char c3 = cArr[i6];
            this._appendCharacterEscape(c3, iArr[c3]);
        }
    }
    private void _writeStringASCII(final int i2, final int i3) throws IOException {
        int i4;
        int i5;
        int i6;
        final int i7 = _outputTail + i2;
        final int[] iArr = _outputEscapes;
        final int iMin = Math.min(iArr.length, i3 + 1);
        while (_outputTail < i7) {
            do {
                final char[] cArr = _outputBuffer;
                final int i8 = _outputTail;
                final char c2 = cArr[i8];
                if (c2 < iMin) {
                    i4 = iArr[c2];
                    if (0 != i4) {
                        final int i9 = _outputHead;
                        i5 = i8 - i9;
                        if (0 >= i5) {
                            _writer.write(cArr, i9, i5);
                        }
                        _outputTail++;
                        this._prependOrWriteCharacterEscape(c2, i4);
                    }
                    i6 = i8 + 1;
                    _outputTail = i6;
                } else {
                    if (c2 > i3) {
                        i4 = -1;
                        final int i92 = _outputHead;
                        i5 = i8 - i92;
                        if (0 >= i5) {
                        }
                        _outputTail++;
                        this._prependOrWriteCharacterEscape(c2, i4);
                    }
                    i6 = i8 + 1;
                    _outputTail = i6;
                }
            } while (i6 < i7);
            return;
        }
    }
    private void _writeSegmentASCII(final int i2, final int i3) throws IOException {
        char[] cArr;
        char c2;
        final int[] iArr = _outputEscapes;
        final int iMin = Math.min(iArr.length, i3 + 1);
        int i4 = 0;
        int i_prependOrWriteCharacterEscape = 0;
        int i5 = 0;
        while (i4 < i2) {
            while (true) {
                cArr = _outputBuffer;
                c2 = cArr[i4];
                if (c2 < iMin) {
                    i5 = iArr[c2];
                    if (0 != i5) {
                        break;
                    }
                    i4++;
                    if (i4 >= i2) {
                        break;
                    }
                } else if (c2 > i3) {
                    i5 = -1;
                    break;
                }
            }
            final int i6 = i4 - i_prependOrWriteCharacterEscape;
            if (0 < i6) {
                _writer.write(cArr, i_prependOrWriteCharacterEscape, i6);
                if (i4 >= i2) {
                    return;
                }
            }
            i4++;
            i_prependOrWriteCharacterEscape = this._prependOrWriteCharacterEscape(_outputBuffer, i4, i2, c2, i5);
        }
    }
    private void _writeStringASCII(final char[] cArr, int i2, final int i3, final int i4) throws IOException {
        char c2;
        final int i5 = i3 + i2;
        final int[] iArr = _outputEscapes;
        final int iMin = Math.min(iArr.length, i4 + 1);
        int i6 = 0;
        while (i2 < i5) {
            int i7 = i2;
            while (true) {
                c2 = cArr[i7];
                if (c2 < iMin) {
                    i6 = iArr[c2];
                    if (0 != i6) {
                        break;
                    }
                    i7++;
                    if (i7 >= i5) {
                        break;
                    }
                } else if (c2 > i4) {
                    i6 = -1;
                    break;
                }
            }
            final int i8 = i7 - i2;
            if (32 > i8) {
                if (_outputTail + i8 > _outputEnd) {
                    this._flushBuffer();
                }
                if (0 < i8) {
                    System.arraycopy(cArr, i2, _outputBuffer, _outputTail, i8);
                    _outputTail += i8;
                }
            } else {
                this._flushBuffer();
                _writer.write(cArr, i2, i8);
            }
            if (i7 >= i5) {
                return;
            }
            i2 = i7 + 1;
            this._appendCharacterEscape(c2, i6);
        }
    }
    private void _writeStringCustom(final int i2) throws IOException {
        int i3 = 0;
        int i4;
        int i5 = 0;
        final int i6 = _outputTail + i2;
        final int[] iArr = _outputEscapes;
        int i7 = _maximumNonEscapedChar;
        if (1 > i7) {
            i7 = 65535;
        }
        final int iMin = Math.min(iArr.length, i7 + 1);
        final CharacterEscapes characterEscapes = _characterEscapes;
        while (_outputTail < i6) {
            do {
                final char c2 = _outputBuffer[_outputTail];
                if (c2 < iMin) {
                    i3 = iArr[c2];
                    if (0 != i3) {
                        final int i8 = _outputTail;
                        final int i9 = _outputHead;
                        i4 = i8 - i9;
                        if (0 >= i4) {
                            _writer.write(_outputBuffer, i9, i4);
                        }
                        _outputTail++;
                        this._prependOrWriteCharacterEscape(c2, i3);
                    }
                    i5 = _outputTail + 1;
                    _outputTail = i5;
                } else {
                    if (c2 > i7) {
                        i3 = -1;
                    } else {
                        final SerializableString escapeSequence = characterEscapes.getEscapeSequence(c2);
                        _currentEscape = escapeSequence;
                        if (null != escapeSequence) {
                            i3 = -2;
                        }
                        i5 = _outputTail + 1;
                        _outputTail = i5;
                    }
                    final int i82 = _outputTail;
                    final int i92 = _outputHead;
                    i4 = i82 - i92;
                    if (0 >= i4) {
                    }
                    _outputTail++;
                    this._prependOrWriteCharacterEscape(c2, i3);
                }
            } while (i5 < i6);
            return;
        }
    }
    private void _writeSegmentCustom(final int i2) throws IOException {
        char c2;
        final int[] iArr = _outputEscapes;
        int i3 = _maximumNonEscapedChar;
        if (1 > i3) {
            i3 = 65535;
        }
        final int iMin = Math.min(iArr.length, i3 + 1);
        final CharacterEscapes characterEscapes = _characterEscapes;
        int i4 = 0;
        int i_prependOrWriteCharacterEscape = 0;
        int i5 = 0;
        while (i4 < i2) {
            while (true) {
                c2 = _outputBuffer[i4];
                if (c2 < iMin) {
                    i5 = iArr[c2];
                    if (0 != i5) {
                        break;
                    }
                    i4++;
                    if (i4 >= i2) {
                        break;
                    }
                } else {
                    if (c2 > i3) {
                        i5 = -1;
                        break;
                    }
                    final SerializableString escapeSequence = characterEscapes.getEscapeSequence(c2);
                    _currentEscape = escapeSequence;
                    if (null != escapeSequence) {
                        i5 = -2;
                        break;
                    }
                }
            }
            final int i6 = i4 - i_prependOrWriteCharacterEscape;
            if (0 < i6) {
                _writer.write(_outputBuffer, i_prependOrWriteCharacterEscape, i6);
                if (i4 >= i2) {
                    return;
                }
            }
            i4++;
            i_prependOrWriteCharacterEscape = this._prependOrWriteCharacterEscape(_outputBuffer, i4, i2, c2, i5);
        }
    }
    private void _writeStringCustom(final char[] cArr, int i2, final int i3) throws IOException {
        char c2;
        final int i4 = i3 + i2;
        final int[] iArr = _outputEscapes;
        int i5 = _maximumNonEscapedChar;
        if (1 > i5) {
            i5 = 65535;
        }
        final int iMin = Math.min(iArr.length, i5 + 1);
        final CharacterEscapes characterEscapes = _characterEscapes;
        int i6 = 0;
        while (i2 < i4) {
            int i7 = i2;
            while (true) {
                c2 = cArr[i7];
                if (c2 < iMin) {
                    i6 = iArr[c2];
                    if (0 != i6) {
                        break;
                    }
                    i7++;
                    if (i7 >= i4) {
                        break;
                    }
                } else {
                    if (c2 > i5) {
                        i6 = -1;
                        break;
                    }
                    final SerializableString escapeSequence = characterEscapes.getEscapeSequence(c2);
                    _currentEscape = escapeSequence;
                    if (null != escapeSequence) {
                        i6 = -2;
                        break;
                    }
                }
            }
            final int i8 = i7 - i2;
            if (32 > i8) {
                if (_outputTail + i8 > _outputEnd) {
                    this._flushBuffer();
                }
                if (0 < i8) {
                    System.arraycopy(cArr, i2, _outputBuffer, _outputTail, i8);
                    _outputTail += i8;
                }
            } else {
                this._flushBuffer();
                _writer.write(cArr, i2, i8);
            }
            if (i7 >= i4) {
                return;
            }
            i2 = i7 + 1;
            this._appendCharacterEscape(c2, i6);
        }
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
            final char[] cArr = _outputBuffer;
            final int i9 = iEncodeBase64Chunk + 1;
            _outputTail = i9;
            cArr[iEncodeBase64Chunk] = '\\';
            _outputTail = iEncodeBase64Chunk + 2;
            cArr[i9] = 'n';
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
                final char[] cArr = _outputBuffer;
                final int i10 = iEncodeBase64Chunk + 1;
                _outputTail = i10;
                cArr[iEncodeBase64Chunk] = '\\';
                _outputTail = iEncodeBase64Chunk + 2;
                cArr[i10] = 'n';
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
                final char[] cArr = _outputBuffer;
                final int i9 = iEncodeBase64Chunk + 1;
                _outputTail = i9;
                cArr[iEncodeBase64Chunk] = '\\';
                _outputTail = iEncodeBase64Chunk + 2;
                cArr[i9] = 'n';
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
    private int _readMore(final InputStream inputStream, final byte[] bArr, int i2, final int i3, final int i4) throws IOException {
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
    private final void _writeNull() throws IOException {
        if (_outputTail + 4 >= _outputEnd) {
            this._flushBuffer();
        }
        final int i2 = _outputTail;
        final char[] cArr = _outputBuffer;
        cArr[i2] = 'n';
        cArr[i2 + 1] = 'u';
        cArr[i2 + 2] = 'l';
        cArr[i2 + 3] = 'l';
        _outputTail = i2 + 4;
    }
    private void _prependOrWriteCharacterEscape(char c2, final int i2) throws IOException {
        final String value;
        final int i3;
        if (0 <= i2) {
            final int i4 = _outputTail;
            if (2 <= i4) {
                final int i5 = i4 - 2;
                _outputHead = i5;
                final char[] cArr = _outputBuffer;
                cArr[i5] = '\\';
                cArr[i4 - 1] = (char) i2;
                return;
            }
            char[] cArr_allocateEntityBuffer = _entityBuffer;
            if (null == cArr_allocateEntityBuffer) {
                cArr_allocateEntityBuffer = this._allocateEntityBuffer();
            }
            _outputHead = _outputTail;
            cArr_allocateEntityBuffer[1] = (char) i2;
            _writer.write(cArr_allocateEntityBuffer, 0, 2);
            return;
        }
        if (-2 != i2) {
            final int i6 = _outputTail;
            if (6 <= i6) {
                final char[] cArr2 = _outputBuffer;
                final int i7 = i6 - 6;
                _outputHead = i7;
                cArr2[i7] = '\\';
                cArr2[i6 - 5] = 'u';
                if ('\u00ff' < c2) {
                    final int i8 = c2 >> '\b';
                    final char[] cArr3 = WriterBasedJsonGenerator.HEX_CHARS;
                    cArr2[i6 - 4] = cArr3[(i8 & 255) >> 4];
                    i3 = i6 - 3;
                    cArr2[i3] = cArr3[i8 & 15];
                    c2 = (char) (c2 & '\u00ff');
                } else {
                    cArr2[i6 - 4] = '0';
                    i3 = i6 - 3;
                    cArr2[i3] = '0';
                }
                final char[] cArr4 = WriterBasedJsonGenerator.HEX_CHARS;
                cArr2[i3 + 1] = cArr4[c2 >> 4];
                cArr2[i3 + 2] = cArr4[c2 & 15];
                return;
            }
            char[] cArr_allocateEntityBuffer2 = _entityBuffer;
            if (null == cArr_allocateEntityBuffer2) {
                cArr_allocateEntityBuffer2 = this._allocateEntityBuffer();
            }
            _outputHead = _outputTail;
            if ('\u00ff' < c2) {
                final int i9 = c2 >> '\b';
                final char[] cArr5 = WriterBasedJsonGenerator.HEX_CHARS;
                cArr_allocateEntityBuffer2[10] = cArr5[(i9 & 255) >> 4];
                cArr_allocateEntityBuffer2[11] = cArr5[i9 & 15];
                cArr_allocateEntityBuffer2[12] = cArr5[(c2 & '\u00ff') >> 4];
                cArr_allocateEntityBuffer2[13] = cArr5[c2 & 15];
                _writer.write(cArr_allocateEntityBuffer2, 8, 6);
                return;
            }
            final char[] cArr6 = WriterBasedJsonGenerator.HEX_CHARS;
            cArr_allocateEntityBuffer2[6] = cArr6[c2 >> 4];
            cArr_allocateEntityBuffer2[7] = cArr6[c2 & 15];
            _writer.write(cArr_allocateEntityBuffer2, 2, 6);
            return;
        }
        final SerializableString serializableString = _currentEscape;
        if (null == serializableString) {
            value = _characterEscapes.getEscapeSequence(c2).getValue();
        } else {
            value = serializableString.getValue();
            _currentEscape = null;
        }
        final int length = value.length();
        final int i10 = _outputTail;
        if (i10 >= length) {
            final int i11 = i10 - length;
            _outputHead = i11;
            value.getChars(0, length, _outputBuffer, i11);
        } else {
            _outputHead = i10;
            _writer.write(value);
        }
    }
    private int _prependOrWriteCharacterEscape(final char[] cArr, final int i2, final int i3, char c2, final int i4) throws IOException {
        final String value;
        final int i5;
        if (0 <= i4) {
            if (1 < i2 && i2 < i3) {
                final int i6 = i2 - 2;
                cArr[i6] = '\\';
                cArr[i2 - 1] = (char) i4;
                return i6;
            }
            char[] cArr_allocateEntityBuffer = _entityBuffer;
            if (null == cArr_allocateEntityBuffer) {
                cArr_allocateEntityBuffer = this._allocateEntityBuffer();
            }
            cArr_allocateEntityBuffer[1] = (char) i4;
            _writer.write(cArr_allocateEntityBuffer, 0, 2);
            return i2;
        }
        if (-2 == i4) {
            final SerializableString serializableString = _currentEscape;
            if (null == serializableString) {
                value = _characterEscapes.getEscapeSequence(c2).getValue();
            } else {
                value = serializableString.getValue();
                _currentEscape = null;
            }
            final int length = value.length();
            if (i2 >= length && i2 < i3) {
                final int i7 = i2 - length;
                value.getChars(0, length, cArr, i7);
                return i7;
            }
            _writer.write(value);
            return i2;
        }
        if (5 < i2 && i2 < i3) {
            cArr[i2 - 6] = '\\';
            final int i8 = i2 - 4;
            cArr[i2 - 5] = 'u';
            if ('\u00ff' < c2) {
                final int i9 = c2 >> '\b';
                final int i10 = i2 - 3;
                final char[] cArr2 = WriterBasedJsonGenerator.HEX_CHARS;
                cArr[i8] = cArr2[(i9 & 255) >> 4];
                i5 = i2 - 2;
                cArr[i10] = cArr2[i9 & 15];
                c2 = (char) (c2 & '\u00ff');
            } else {
                final int i11 = i2 - 3;
                cArr[i8] = '0';
                i5 = i2 - 2;
                cArr[i11] = '0';
            }
            final char[] cArr3 = WriterBasedJsonGenerator.HEX_CHARS;
            cArr[i5] = cArr3[c2 >> 4];
            cArr[i5 + 1] = cArr3[c2 & 15];
            return i5 - 4;
        }
        char[] cArr_allocateEntityBuffer2 = _entityBuffer;
        if (null == cArr_allocateEntityBuffer2) {
            cArr_allocateEntityBuffer2 = this._allocateEntityBuffer();
        }
        _outputHead = _outputTail;
        if ('\u00ff' < c2) {
            final int i12 = c2 >> '\b';
            final char[] cArr4 = WriterBasedJsonGenerator.HEX_CHARS;
            cArr_allocateEntityBuffer2[10] = cArr4[(i12 & 255) >> 4];
            cArr_allocateEntityBuffer2[11] = cArr4[i12 & 15];
            cArr_allocateEntityBuffer2[12] = cArr4[(c2 & '\u00ff') >> 4];
            cArr_allocateEntityBuffer2[13] = cArr4[c2 & 15];
            _writer.write(cArr_allocateEntityBuffer2, 8, 6);
            return i2;
        }
        final char[] cArr5 = WriterBasedJsonGenerator.HEX_CHARS;
        cArr_allocateEntityBuffer2[6] = cArr5[c2 >> 4];
        cArr_allocateEntityBuffer2[7] = cArr5[c2 & 15];
        _writer.write(cArr_allocateEntityBuffer2, 2, 6);
        return i2;
    }
    private void _appendCharacterEscape(char c2, final int i2) throws IOException {
        final String value;
        final int i3;
        if (0 <= i2) {
            if (_outputTail + 2 > _outputEnd) {
                this._flushBuffer();
            }
            final char[] cArr = _outputBuffer;
            final int i4 = _outputTail;
            final int i5 = i4 + 1;
            _outputTail = i5;
            cArr[i4] = '\\';
            _outputTail = i4 + 2;
            cArr[i5] = (char) i2;
            return;
        }
        if (-2 != i2) {
            if (_outputTail + 5 >= _outputEnd) {
                this._flushBuffer();
            }
            final int i6 = _outputTail;
            final char[] cArr2 = _outputBuffer;
            cArr2[i6] = '\\';
            final int i7 = i6 + 2;
            cArr2[i6 + 1] = 'u';
            if ('\u00ff' < c2) {
                final int i8 = c2 >> '\b';
                final int i9 = i6 + 3;
                final char[] cArr3 = WriterBasedJsonGenerator.HEX_CHARS;
                cArr2[i7] = cArr3[(i8 & 255) >> 4];
                i3 = i6 + 4;
                cArr2[i9] = cArr3[i8 & 15];
                c2 = (char) (c2 & '\u00ff');
            } else {
                final int i10 = i6 + 3;
                cArr2[i7] = '0';
                i3 = i6 + 4;
                cArr2[i10] = '0';
            }
            final char[] cArr4 = WriterBasedJsonGenerator.HEX_CHARS;
            cArr2[i3] = cArr4[c2 >> 4];
            cArr2[i3 + 1] = cArr4[c2 & 15];
            _outputTail = i3 + 2;
            return;
        }
        final SerializableString serializableString = _currentEscape;
        if (null == serializableString) {
            value = _characterEscapes.getEscapeSequence(c2).getValue();
        } else {
            value = serializableString.getValue();
            _currentEscape = null;
        }
        final int length = value.length();
        if (_outputTail + length > _outputEnd) {
            this._flushBuffer();
            if (length > _outputEnd) {
                _writer.write(value);
                return;
            }
        }
        value.getChars(0, length, _outputBuffer, _outputTail);
        _outputTail += length;
    }
    private char[] _allocateEntityBuffer() {
        final char[] cArr = {'\\', 0, '\\', 'u', '0', '0', 0, 0, '\\', 'u', 0, 0, 0, 0};
        _entityBuffer = cArr;
        return cArr;
    }

    protected void _flushBuffer() throws IOException {
        final int i2 = _outputTail;
        final int i3 = _outputHead;
        final int i4 = i2 - i3;
        if (0 < i4) {
            _outputHead = 0;
            _outputTail = 0;
            _writer.write(_outputBuffer, i3, i4);
        }
    }
}
