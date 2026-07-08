package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.format.InputAccessor;
import com.fasterxml.jackson.core.format.MatchStrength;
import com.fasterxml.jackson.core.io.MergedStream;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DataFormatReaders {
    protected final int _maxInputLookahead;
    protected final MatchStrength _minimalMatch;
    protected final MatchStrength _optimalMatch;
    protected final ObjectReader[] _readers;
    public DataFormatReaders(final ObjectReader... objectReaderArr) {
        this(objectReaderArr, MatchStrength.SOLID_MATCH, MatchStrength.WEAK_MATCH, 64);
    }
    private DataFormatReaders(final ObjectReader[] objectReaderArr, final MatchStrength matchStrength, final MatchStrength matchStrength2, final int i2) {
        _readers = objectReaderArr;
        _optimalMatch = matchStrength;
        _minimalMatch = matchStrength2;
        _maxInputLookahead = i2;
    }
    public DataFormatReaders with(final DeserializationConfig deserializationConfig) {
        final int length = _readers.length;
        final ObjectReader[] objectReaderArr = new ObjectReader[length];
        for (int i2 = 0; i2 < length; i2++) {
            objectReaderArr[i2] = _readers[i2].with(deserializationConfig);
        }
        return new DataFormatReaders(objectReaderArr, _optimalMatch, _minimalMatch, _maxInputLookahead);
    }
    public DataFormatReaders withType(final JavaType javaType) {
        final int length = _readers.length;
        final ObjectReader[] objectReaderArr = new ObjectReader[length];
        for (int i2 = 0; i2 < length; i2++) {
            objectReaderArr[i2] = _readers[i2].forType(javaType);
        }
        return new DataFormatReaders(objectReaderArr, _optimalMatch, _minimalMatch, _maxInputLookahead);
    }
    public Match findFormat(final InputStream inputStream) throws IOException {
        return this._findFormat(new AccessorForReader(inputStream, new byte[_maxInputLookahead]));
    }
    public Match findFormat(final byte[] bArr, final int i2, final int i3) throws IOException {
        return this._findFormat(new AccessorForReader(bArr, i2, i3));
    }
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append('[');
        final ObjectReader[] objectReaderArr = _readers;
        final int length = objectReaderArr.length;
        if (0 < length) {
            sb.append(objectReaderArr[0].getFactory().getFormatName());
            for (int i2 = 1; i2 < length; i2++) {
                sb.append(", ");
                sb.append(_readers[i2].getFactory().getFormatName());
            }
        }
        sb.append(']');
        return sb.toString();
    }
    private Match _findFormat(final AccessorForReader accessorForReader) throws IOException {
        final ObjectReader[] objectReaderArr = _readers;
        final int length = objectReaderArr.length;
        ObjectReader objectReader = null;
        int i2 = 0;
        MatchStrength matchStrength = null;
        while (true) {
            if (i2 >= length) {
                break;
            }
            final ObjectReader objectReader2 = objectReaderArr[i2];
            accessorForReader.reset();
            final MatchStrength matchStrengthHasFormat = objectReader2.getFactory().hasFormat(accessorForReader);
            if (null != matchStrengthHasFormat && matchStrengthHasFormat.ordinal() >= _minimalMatch.ordinal() && (null == objectReader || matchStrength.ordinal() < matchStrengthHasFormat.ordinal())) {
                if (matchStrengthHasFormat.ordinal() >= _optimalMatch.ordinal()) {
                    objectReader = objectReader2;
                    matchStrength = matchStrengthHasFormat;
                    break;
                }
                objectReader = objectReader2;
                matchStrength = matchStrengthHasFormat;
            }
            i2++;
        }
        return accessorForReader.createMatcher(objectReader, matchStrength);
    }
    protected static class AccessorForReader extends InputAccessor.Std {
        public AccessorForReader(final InputStream inputStream, final byte[] bArr) {
            super(inputStream, bArr);
        }

        public AccessorForReader(final byte[] bArr, final int i2, final int i3) {
            super(bArr, i2, i3);
        }

        public Match createMatcher(final ObjectReader objectReader, final MatchStrength matchStrength) {
            final InputStream inputStream = _in;
            final byte[] bArr = _buffer;
            final int i2 = _bufferedStart;
            return new Match(inputStream, bArr, i2, _bufferedEnd - i2, objectReader, matchStrength);
        }
    }
    public static class Match {
        protected final byte[] _bufferedData;
        protected final int _bufferedLength;
        protected final int _bufferedStart;
        protected final ObjectReader _match;
        protected final MatchStrength _matchStrength;
        protected final InputStream _originalStream;

        protected Match(final InputStream inputStream, final byte[] bArr, final int i2, final int i3, final ObjectReader objectReader, final MatchStrength matchStrength) {
            _originalStream = inputStream;
            _bufferedData = bArr;
            _bufferedStart = i2;
            _bufferedLength = i3;
            _match = objectReader;
            _matchStrength = matchStrength;
        }

        public boolean hasMatch() {
            return null != this._match;
        }

        public ObjectReader getReader() {
            return _match;
        }

        public JsonParser createParserWithMatch() throws IOException {
            final ObjectReader objectReader = _match;
            if (null == objectReader) {
                return null;
            }
            final JsonFactory factory = objectReader.getFactory();
            if (null == this._originalStream) {
                return factory.createParser(_bufferedData, _bufferedStart, _bufferedLength);
            }
            return factory.createParser(this.getDataStream());
        }

        public InputStream getDataStream() {
            if (null == this._originalStream) {
                return new ByteArrayInputStream(_bufferedData, _bufferedStart, _bufferedLength);
            }
            return new MergedStream(null, _originalStream, _bufferedData, _bufferedStart, _bufferedLength);
        }
    }
}
