package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MappingIterator<T> implements Iterator<T>, Closeable {
    protected final boolean _closeParser;
    protected final DeserializationContext _context;
    protected final JsonDeserializer<T> _deserializer;
    protected final JsonParser _parser;
    protected final JsonStreamContext _seqContext;
    protected int _state;
    protected final JavaType _type;
    protected final T _updatedValue;
    protected MappingIterator(final JavaType javaType, final JsonParser jsonParser, final DeserializationContext deserializationContext, final JsonDeserializer<?> jsonDeserializer, final boolean z, final Object obj) {
        _type = javaType;
        _parser = jsonParser;
        _context = deserializationContext;
        _deserializer = (JsonDeserializer<T>) jsonDeserializer;
        _closeParser = z;
        if (obj==null) {
            _updatedValue = null;
        } else {
            _updatedValue = (T) obj;
        }
        if (jsonParser==null) {
            _seqContext = null;
            _state = 0;
            return;
        }
        JsonStreamContext parsingContext = jsonParser.getParsingContext();
        if (z && jsonParser.isExpectedStartArrayToken()) {
            jsonParser.clearCurrentToken();
        } else {
            final JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
            if (JsonToken.START_OBJECT == jsonTokenCurrentToken || JsonToken.START_ARRAY == jsonTokenCurrentToken) {
                parsingContext = parsingContext.getParent();
            }
        }
        _seqContext = parsingContext;
        _state = 2;
    }
    public boolean hasNext() {
        try {
            return this.hasNextValue();
        } catch (final JsonMappingException e2) {
            return this._handleMappingException(e2);
        } catch (final IOException e3) {
            return this._handleIOException(e3);
        }
    }
    public T next() {
        try {
            return this.nextValue();
        } catch (final JsonMappingException e2) {
            return this._handleMappingException(e2);
        } catch (final IOException e3) {
            return this._handleIOException(e3);
        }
    }
    public void remove() {
        throw new UnsupportedOperationException();
    }
    public void close() throws IOException {
        if (0 != this._state) {
            _state = 0;
            final JsonParser jsonParser = _parser;
            if (null != jsonParser) {
                jsonParser.close();
            }
        }
    }
    public boolean hasNextValue() throws IOException {
        final JsonToken jsonTokenNextToken;
        final JsonParser jsonParser;
        final int i2 = _state;
        if (0 == i2) {
            return false;
        }
        if (1 == i2) {
            this._resync();
        } else if (2 != i2) {
            return true;
        }
        if (null == this._parser.currentToken() && (null == (jsonTokenNextToken = this._parser.nextToken()) || JsonToken.END_ARRAY == jsonTokenNextToken)) {
            _state = 0;
            if (_closeParser && null != (jsonParser = this._parser)) {
                jsonParser.close();
            }
            return false;
        }
        _state = 3;
        return true;
    }
    public T nextValue() throws IOException {
        final T tDeserialize;
        final int i2 = _state;
        if (0 == i2) {
            return this._throwNoSuchElement();
        }
        if ((1 == i2 || 2 == i2) && !this.hasNextValue()) {
            return this._throwNoSuchElement();
        }
        try {
            final T t = _updatedValue;
            if (null == t) {
                tDeserialize = _deserializer.deserialize(_parser, _context);
            } else {
                _deserializer.deserialize(_parser, _context, t);
                tDeserialize = _updatedValue;
            }
            _state = 2;
            _parser.clearCurrentToken();
            return tDeserialize;
        } catch (final Throwable th) {
            _state = 1;
            _parser.clearCurrentToken();
            throw th;
        }
    }
    protected void _resync() throws IOException {
        final JsonParser jsonParser = _parser;
        if (jsonParser.getParsingContext() == _seqContext) {
            return;
        }
        while (true) {
            final JsonToken jsonTokenNextToken = jsonParser.nextToken();
            if (JsonToken.END_ARRAY == jsonTokenNextToken || JsonToken.END_OBJECT == jsonTokenNextToken) {
                if (jsonParser.getParsingContext() == _seqContext) {
                    jsonParser.clearCurrentToken();
                    return;
                }
            } else if (JsonToken.START_ARRAY == jsonTokenNextToken || JsonToken.START_OBJECT == jsonTokenNextToken) {
                jsonParser.skipChildren();
            } else if (null == jsonTokenNextToken) {
                return;
            }
        }
    }
    protected <R> R _throwNoSuchElement() {
        throw new NoSuchElementException();
    }
    protected <R> R _handleMappingException(final JsonMappingException jsonMappingException) {
        throw new RuntimeJsonMappingException(jsonMappingException.getMessage(), jsonMappingException);
    }
    protected <R> R _handleIOException(final IOException iOException) {
        throw new RuntimeException(iOException.getMessage(), iOException);
    }
}
