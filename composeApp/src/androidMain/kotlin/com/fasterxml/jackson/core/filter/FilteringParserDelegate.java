package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.util.JsonParserDelegate;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
 
public class FilteringParserDelegate extends JsonParserDelegate {
    protected boolean _allowMultipleMatches;
    protected JsonToken _currToken;
    protected TokenFilterContext _exposedContext;
    protected TokenFilterContext _headContext;
    protected TokenFilter.Inclusion _inclusion;
    protected TokenFilter _itemFilter;
    protected JsonToken _lastClearedToken;
    protected int _matchCount;
    protected TokenFilter rootFilter;
    public FilteringParserDelegate(final JsonParser jsonParser, final TokenFilter tokenFilter, final TokenFilter.Inclusion inclusion, final boolean z) {
        super(jsonParser);
        rootFilter = tokenFilter;
        _itemFilter = tokenFilter;
        _headContext = TokenFilterContext.createRootContext(tokenFilter);
        _inclusion = inclusion;
        _allowMultipleMatches = z;
    }
    public JsonToken getCurrentToken() {
        return _currToken;
    }
    public JsonToken currentToken() {
        return _currToken;
    }
    public final int getCurrentTokenId() {
        return this.currentTokenId();
    }
    public final int currentTokenId() {
        final JsonToken jsonToken = _currToken;
        if (null == jsonToken) {
            return 0;
        }
        return jsonToken.m307id();
    }
    public boolean hasCurrentToken() {
        return null != this._currToken;
    }
    public boolean hasTokenId(final int i2) {
        final JsonToken jsonToken = _currToken;
        return null == jsonToken ? 0 == i2 : jsonToken.m307id() == i2;
    }
    public final boolean hasToken(final JsonToken jsonToken) {
        return _currToken == jsonToken;
    }
    public boolean isExpectedStartArrayToken() {
        return JsonToken.START_ARRAY == this._currToken;
    }
    public boolean isExpectedStartObjectToken() {
        return JsonToken.START_OBJECT == this._currToken;
    }
    public JsonLocation getCurrentLocation() {
        return delegate.getCurrentLocation();
    }
    public JsonStreamContext getParsingContext() {
        return this._filterContext();
    }
    public String getCurrentName() throws IOException {
        final JsonStreamContext jsonStreamContext_filterContext = this._filterContext();
        final JsonToken jsonToken = _currToken;
        if (JsonToken.START_OBJECT == jsonToken || JsonToken.START_ARRAY == jsonToken) {
            final JsonStreamContext parent = jsonStreamContext_filterContext.getParent();
            if (null == parent) {
                return null;
            }
            return parent.getCurrentName();
        }
        return jsonStreamContext_filterContext.getCurrentName();
    }
    public void clearCurrentToken() {
        final JsonToken jsonToken = _currToken;
        if (null != jsonToken) {
            _lastClearedToken = jsonToken;
            _currToken = null;
        }
    }
    public JsonToken nextToken() throws IOException {
        TokenFilter tokenFilterCheckValue;
        final JsonToken jsonToken_nextTokenWithBuffering;
        TokenFilter tokenFilterCheckValue2;
        final JsonToken jsonToken_nextTokenWithBuffering2;
        final TokenFilter tokenFilterIncludeProperty;
        final JsonToken jsonToken_nextTokenWithBuffering3;
        final TokenFilter tokenFilterCheckValue3;
        final JsonToken jsonToken;
        if (!_allowMultipleMatches && null != (jsonToken = this._currToken) && null == this._exposedContext && jsonToken.isScalarValue() && !_headContext.isStartHandled() && TokenFilter.Inclusion.ONLY_INCLUDE_ALL == this._inclusion && _itemFilter == TokenFilter.INCLUDE_ALL) {
            _currToken = null;
            return null;
        }
        TokenFilterContext tokenFilterContextFindChildOf = _exposedContext;
        if (null != tokenFilterContextFindChildOf) {
            do {
                final JsonToken jsonTokenNextTokenToRead = tokenFilterContextFindChildOf.nextTokenToRead();
                if (null != jsonTokenNextTokenToRead) {
                    _currToken = jsonTokenNextTokenToRead;
                    return jsonTokenNextTokenToRead;
                }
                final TokenFilterContext tokenFilterContext = _headContext;
                if (tokenFilterContextFindChildOf == tokenFilterContext) {
                    _exposedContext = null;
                    if (tokenFilterContextFindChildOf.inArray()) {
                        final JsonToken currentToken = delegate.getCurrentToken();
                        _currToken = currentToken;
                        return currentToken;
                    }
                } else {
                    tokenFilterContextFindChildOf = tokenFilterContext.findChildOf(tokenFilterContextFindChildOf);
                    _exposedContext = tokenFilterContextFindChildOf;
                }
            } while (null != tokenFilterContextFindChildOf);
            throw this._constructError("Unexpected problem: chain of filtered context broken");
        }
        final JsonToken jsonTokenNextToken = delegate.nextToken();
        if (null == jsonTokenNextToken) {
            _currToken = jsonTokenNextToken;
            return jsonTokenNextToken;
        }
        final int iM307id = jsonTokenNextToken.m307id();
        if (1 == iM307id) {
            final TokenFilter tokenFilter = _itemFilter;
            final TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
            if (tokenFilter == tokenFilter2) {
                _headContext = _headContext.createChildObjectContext(tokenFilter, true);
                _currToken = jsonTokenNextToken;
                return jsonTokenNextToken;
            }
            if (null == tokenFilter || null == (tokenFilterCheckValue = this._headContext.checkValue(tokenFilter))) {
                delegate.skipChildren();
            } else {
                if (tokenFilterCheckValue != tokenFilter2) {
                    tokenFilterCheckValue = tokenFilterCheckValue.filterStartObject();
                }
                _itemFilter = tokenFilterCheckValue;
                if (tokenFilterCheckValue == tokenFilter2) {
                    _headContext = _headContext.createChildObjectContext(tokenFilterCheckValue, true);
                    _currToken = jsonTokenNextToken;
                    return jsonTokenNextToken;
                }
                if (null != tokenFilterCheckValue && TokenFilter.Inclusion.INCLUDE_NON_NULL == this._inclusion) {
                    _headContext = _headContext.createChildObjectContext(tokenFilterCheckValue, true);
                    _currToken = jsonTokenNextToken;
                    return jsonTokenNextToken;
                }
                final TokenFilterContext tokenFilterContextCreateChildObjectContext = _headContext.createChildObjectContext(tokenFilterCheckValue, false);
                _headContext = tokenFilterContextCreateChildObjectContext;
                if (TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH == this._inclusion && null != (jsonToken_nextTokenWithBuffering = _nextTokenWithBuffering(tokenFilterContextCreateChildObjectContext))) {
                    _currToken = jsonToken_nextTokenWithBuffering;
                    return jsonToken_nextTokenWithBuffering;
                }
            }
        } else if (2 == iM307id) {
            final boolean zIsStartHandled = _headContext.isStartHandled();
            final TokenFilter filter = _headContext.getFilter();
            if (null != filter && filter != TokenFilter.INCLUDE_ALL) {
                filter.filterFinishArray();
            }
            final TokenFilterContext parent = _headContext.getParent();
            _headContext = parent;
            _itemFilter = parent.getFilter();
            if (zIsStartHandled) {
                _currToken = jsonTokenNextToken;
                return jsonTokenNextToken;
            }
        } else if (3 == iM307id) {
            final TokenFilter tokenFilter3 = _itemFilter;
            final TokenFilter tokenFilter4 = TokenFilter.INCLUDE_ALL;
            if (tokenFilter3 == tokenFilter4) {
                _headContext = _headContext.createChildArrayContext(tokenFilter3, true);
                _currToken = jsonTokenNextToken;
                return jsonTokenNextToken;
            }
            if (null == tokenFilter3 || null == (tokenFilterCheckValue2 = this._headContext.checkValue(tokenFilter3))) {
                delegate.skipChildren();
            } else {
                if (tokenFilterCheckValue2 != tokenFilter4) {
                    tokenFilterCheckValue2 = tokenFilterCheckValue2.filterStartArray();
                }
                _itemFilter = tokenFilterCheckValue2;
                if (tokenFilterCheckValue2 == tokenFilter4) {
                    _headContext = _headContext.createChildArrayContext(tokenFilterCheckValue2, true);
                    _currToken = jsonTokenNextToken;
                    return jsonTokenNextToken;
                }
                if (null != tokenFilterCheckValue2 && TokenFilter.Inclusion.INCLUDE_NON_NULL == this._inclusion) {
                    _headContext = _headContext.createChildArrayContext(tokenFilterCheckValue2, true);
                    _currToken = jsonTokenNextToken;
                    return jsonTokenNextToken;
                }
                final TokenFilterContext tokenFilterContextCreateChildArrayContext = _headContext.createChildArrayContext(tokenFilterCheckValue2, false);
                _headContext = tokenFilterContextCreateChildArrayContext;
                if (TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH == this._inclusion && null != (jsonToken_nextTokenWithBuffering2 = _nextTokenWithBuffering(tokenFilterContextCreateChildArrayContext))) {
                    _currToken = jsonToken_nextTokenWithBuffering2;
                    return jsonToken_nextTokenWithBuffering2;
                }
            }
        } else if (4 != iM307id) {
            if (5 == iM307id) {
                final String currentName = delegate.getCurrentName();
                final TokenFilter fieldName = _headContext.setFieldName(currentName);
                final TokenFilter tokenFilter5 = TokenFilter.INCLUDE_ALL;
                if (fieldName == tokenFilter5) {
                    _itemFilter = fieldName;
                    _currToken = jsonTokenNextToken;
                    return jsonTokenNextToken;
                }
                if (null == fieldName || null == (tokenFilterIncludeProperty = fieldName.includeProperty(currentName))) {
                    delegate.nextToken();
                    delegate.skipChildren();
                } else {
                    _itemFilter = tokenFilterIncludeProperty;
                    if (tokenFilterIncludeProperty == tokenFilter5) {
                        if (this._verifyAllowedMatches()) {
                            if (TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH == this._inclusion) {
                                _currToken = jsonTokenNextToken;
                                return jsonTokenNextToken;
                            }
                        } else {
                            delegate.nextToken();
                            delegate.skipChildren();
                        }
                    }
                    if (TokenFilter.Inclusion.ONLY_INCLUDE_ALL != this._inclusion && null != (jsonToken_nextTokenWithBuffering3 = _nextTokenWithBuffering(this._headContext))) {
                        _currToken = jsonToken_nextTokenWithBuffering3;
                        return jsonToken_nextTokenWithBuffering3;
                    }
                }
            } else {
                final TokenFilter tokenFilter6 = _itemFilter;
                final TokenFilter tokenFilter7 = TokenFilter.INCLUDE_ALL;
                if (tokenFilter6 == tokenFilter7) {
                    _currToken = jsonTokenNextToken;
                    return jsonTokenNextToken;
                }
                if (null != tokenFilter6 && (((tokenFilterCheckValue3 = _headContext.checkValue(tokenFilter6)) == tokenFilter7 || (null != tokenFilterCheckValue3 && tokenFilterCheckValue3.includeValue(delegate))) && this._verifyAllowedMatches())) {
                    _currToken = jsonTokenNextToken;
                    return jsonTokenNextToken;
                }
            }
        }
        return this._nextToken2();
    }
    protected final JsonToken _nextToken2() throws IOException {
        TokenFilter tokenFilterCheckValue;
        JsonToken jsonToken_nextTokenWithBuffering;
        JsonToken jsonToken_nextTokenWithBuffering2;
        JsonToken jsonToken_nextTokenWithBuffering3;
        while (true) {
            final JsonToken jsonTokenNextToken = delegate.nextToken();
            if (null == jsonTokenNextToken) {
                _currToken = jsonTokenNextToken;
                return jsonTokenNextToken;
            }
            final int iM307id = jsonTokenNextToken.m307id();
            if (1 == iM307id) {
                final TokenFilter tokenFilter = _itemFilter;
                final TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
                if (tokenFilter == tokenFilter2) {
                    _headContext = _headContext.createChildObjectContext(tokenFilter, true);
                    _currToken = jsonTokenNextToken;
                    return jsonTokenNextToken;
                }
                if (null == tokenFilter) {
                    delegate.skipChildren();
                } else {
                    TokenFilter tokenFilterCheckValue2 = _headContext.checkValue(tokenFilter);
                    if (null == tokenFilterCheckValue2) {
                        delegate.skipChildren();
                    } else {
                        if (tokenFilterCheckValue2 != tokenFilter2) {
                            tokenFilterCheckValue2 = tokenFilterCheckValue2.filterStartObject();
                        }
                        _itemFilter = tokenFilterCheckValue2;
                        if (tokenFilterCheckValue2 == tokenFilter2) {
                            _headContext = _headContext.createChildObjectContext(tokenFilterCheckValue2, true);
                            _currToken = jsonTokenNextToken;
                            return jsonTokenNextToken;
                        }
                        if (null != tokenFilterCheckValue2 && TokenFilter.Inclusion.INCLUDE_NON_NULL == this._inclusion) {
                            _headContext = _headContext.createChildObjectContext(tokenFilterCheckValue2, true);
                            _currToken = jsonTokenNextToken;
                            return jsonTokenNextToken;
                        }
                        final TokenFilterContext tokenFilterContextCreateChildObjectContext = _headContext.createChildObjectContext(tokenFilterCheckValue2, false);
                        _headContext = tokenFilterContextCreateChildObjectContext;
                        if (TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH == this._inclusion && null != (jsonToken_nextTokenWithBuffering3 = _nextTokenWithBuffering(tokenFilterContextCreateChildObjectContext))) {
                            _currToken = jsonToken_nextTokenWithBuffering3;
                            return jsonToken_nextTokenWithBuffering3;
                        }
                    }
                }
            } else {
                if (2 != iM307id) {
                    if (3 == iM307id) {
                        final TokenFilter tokenFilter3 = _itemFilter;
                        final TokenFilter tokenFilter4 = TokenFilter.INCLUDE_ALL;
                        if (tokenFilter3 == tokenFilter4) {
                            _headContext = _headContext.createChildArrayContext(tokenFilter3, true);
                            _currToken = jsonTokenNextToken;
                            return jsonTokenNextToken;
                        }
                        if (null == tokenFilter3) {
                            delegate.skipChildren();
                        } else {
                            TokenFilter tokenFilterCheckValue3 = _headContext.checkValue(tokenFilter3);
                            if (null == tokenFilterCheckValue3) {
                                delegate.skipChildren();
                            } else {
                                if (tokenFilterCheckValue3 != tokenFilter4) {
                                    tokenFilterCheckValue3 = tokenFilterCheckValue3.filterStartArray();
                                }
                                _itemFilter = tokenFilterCheckValue3;
                                if (tokenFilterCheckValue3 == tokenFilter4) {
                                    _headContext = _headContext.createChildArrayContext(tokenFilterCheckValue3, true);
                                    _currToken = jsonTokenNextToken;
                                    return jsonTokenNextToken;
                                }
                                if (null != tokenFilterCheckValue3 && TokenFilter.Inclusion.INCLUDE_NON_NULL == this._inclusion) {
                                    _headContext = _headContext.createChildArrayContext(tokenFilterCheckValue3, true);
                                    _currToken = jsonTokenNextToken;
                                    return jsonTokenNextToken;
                                }
                                final TokenFilterContext tokenFilterContextCreateChildArrayContext = _headContext.createChildArrayContext(tokenFilterCheckValue3, false);
                                _headContext = tokenFilterContextCreateChildArrayContext;
                                if (TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH == this._inclusion && null != (jsonToken_nextTokenWithBuffering2 = _nextTokenWithBuffering(tokenFilterContextCreateChildArrayContext))) {
                                    _currToken = jsonToken_nextTokenWithBuffering2;
                                    return jsonToken_nextTokenWithBuffering2;
                                }
                            }
                        }
                    } else if (4 != iM307id) {
                        if (5 == iM307id) {
                            final String currentName = delegate.getCurrentName();
                            final TokenFilter fieldName = _headContext.setFieldName(currentName);
                            final TokenFilter tokenFilter5 = TokenFilter.INCLUDE_ALL;
                            if (fieldName == tokenFilter5) {
                                _itemFilter = fieldName;
                                _currToken = jsonTokenNextToken;
                                return jsonTokenNextToken;
                            }
                            if (null == fieldName) {
                                delegate.nextToken();
                                delegate.skipChildren();
                            } else {
                                final TokenFilter tokenFilterIncludeProperty = fieldName.includeProperty(currentName);
                                if (null == tokenFilterIncludeProperty) {
                                    delegate.nextToken();
                                    delegate.skipChildren();
                                } else {
                                    _itemFilter = tokenFilterIncludeProperty;
                                    if (tokenFilterIncludeProperty == tokenFilter5) {
                                        if (this._verifyAllowedMatches() && TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH == this._inclusion) {
                                            _currToken = jsonTokenNextToken;
                                            return jsonTokenNextToken;
                                        }
                                    } else if (TokenFilter.Inclusion.ONLY_INCLUDE_ALL != this._inclusion && null != (jsonToken_nextTokenWithBuffering = _nextTokenWithBuffering(this._headContext))) {
                                        _currToken = jsonToken_nextTokenWithBuffering;
                                        return jsonToken_nextTokenWithBuffering;
                                    }
                                }
                            }
                        } else {
                            final TokenFilter tokenFilter6 = _itemFilter;
                            final TokenFilter tokenFilter7 = TokenFilter.INCLUDE_ALL;
                            if (tokenFilter6 == tokenFilter7) {
                                _currToken = jsonTokenNextToken;
                                return jsonTokenNextToken;
                            }
                            if (null != tokenFilter6 && ((tokenFilterCheckValue = _headContext.checkValue(tokenFilter6)) == tokenFilter7 || (null != tokenFilterCheckValue && tokenFilterCheckValue.includeValue(delegate)))) {
                                if (this._verifyAllowedMatches()) {
                                    _currToken = jsonTokenNextToken;
                                    return jsonTokenNextToken;
                                }
                            }
                        }
                    }
                }
                final boolean zIsStartHandled = _headContext.isStartHandled();
                final TokenFilter filter = _headContext.getFilter();
                if (null != filter && filter != TokenFilter.INCLUDE_ALL) {
                    filter.filterFinishArray();
                }
                final TokenFilterContext parent = _headContext.getParent();
                _headContext = parent;
                _itemFilter = parent.getFilter();
                if (zIsStartHandled) {
                    _currToken = jsonTokenNextToken;
                    return jsonTokenNextToken;
                }
            }
        }
    }
    protected final JsonToken _nextTokenWithBuffering(final TokenFilterContext tokenFilterContext) throws IOException {
        TokenFilter tokenFilterCheckValue;
        while (true) {
            final JsonToken jsonTokenNextToken = delegate.nextToken();
            if (null == jsonTokenNextToken) {
                return jsonTokenNextToken;
            }
            final int iM307id = jsonTokenNextToken.m307id();
            boolean z = false;
            if (1 == iM307id) {
                final TokenFilter tokenFilter = _itemFilter;
                final TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
                if (tokenFilter == tokenFilter2) {
                    _headContext = _headContext.createChildObjectContext(tokenFilter, true);
                    return jsonTokenNextToken;
                }
                if (null == tokenFilter) {
                    delegate.skipChildren();
                } else {
                    TokenFilter tokenFilterCheckValue2 = _headContext.checkValue(tokenFilter);
                    if (null == tokenFilterCheckValue2) {
                        delegate.skipChildren();
                    } else {
                        if (tokenFilterCheckValue2 != tokenFilter2) {
                            tokenFilterCheckValue2 = tokenFilterCheckValue2.filterStartObject();
                        }
                        _itemFilter = tokenFilterCheckValue2;
                        if (tokenFilterCheckValue2 == tokenFilter2) {
                            _headContext = _headContext.createChildObjectContext(tokenFilterCheckValue2, true);
                            return this._nextBuffered(tokenFilterContext);
                        }
                        if (null != tokenFilterCheckValue2 && TokenFilter.Inclusion.INCLUDE_NON_NULL == this._inclusion) {
                            _headContext = _headContext.createChildArrayContext(tokenFilterCheckValue2, true);
                            return this._nextBuffered(tokenFilterContext);
                        }
                        _headContext = _headContext.createChildObjectContext(tokenFilterCheckValue2, false);
                    }
                }
            } else {
                if (2 != iM307id) {
                    if (3 == iM307id) {
                        TokenFilter tokenFilterCheckValue3 = _headContext.checkValue(_itemFilter);
                        if (null == tokenFilterCheckValue3) {
                            delegate.skipChildren();
                        } else {
                            final TokenFilter tokenFilter3 = TokenFilter.INCLUDE_ALL;
                            if (tokenFilterCheckValue3 != tokenFilter3) {
                                tokenFilterCheckValue3 = tokenFilterCheckValue3.filterStartArray();
                            }
                            _itemFilter = tokenFilterCheckValue3;
                            if (tokenFilterCheckValue3 == tokenFilter3) {
                                _headContext = _headContext.createChildArrayContext(tokenFilterCheckValue3, true);
                                return this._nextBuffered(tokenFilterContext);
                            }
                            if (null != tokenFilterCheckValue3 && TokenFilter.Inclusion.INCLUDE_NON_NULL == this._inclusion) {
                                _headContext = _headContext.createChildArrayContext(tokenFilterCheckValue3, true);
                                return this._nextBuffered(tokenFilterContext);
                            }
                            _headContext = _headContext.createChildArrayContext(tokenFilterCheckValue3, false);
                        }
                    } else if (4 != iM307id) {
                        if (5 == iM307id) {
                            final String currentName = delegate.getCurrentName();
                            final TokenFilter fieldName = _headContext.setFieldName(currentName);
                            final TokenFilter tokenFilter4 = TokenFilter.INCLUDE_ALL;
                            if (fieldName == tokenFilter4) {
                                _itemFilter = fieldName;
                                return this._nextBuffered(tokenFilterContext);
                            }
                            if (null == fieldName) {
                                delegate.nextToken();
                                delegate.skipChildren();
                            } else {
                                final TokenFilter tokenFilterIncludeProperty = fieldName.includeProperty(currentName);
                                if (null == tokenFilterIncludeProperty) {
                                    delegate.nextToken();
                                    delegate.skipChildren();
                                } else {
                                    _itemFilter = tokenFilterIncludeProperty;
                                    if (tokenFilterIncludeProperty != tokenFilter4) {
                                        continue;
                                    } else {
                                        if (this._verifyAllowedMatches()) {
                                            return this._nextBuffered(tokenFilterContext);
                                        }
                                        _itemFilter = _headContext.setFieldName(currentName);
                                    }
                                }
                            }
                        } else {
                            final TokenFilter tokenFilter5 = _itemFilter;
                            final TokenFilter tokenFilter6 = TokenFilter.INCLUDE_ALL;
                            if (tokenFilter5 == tokenFilter6) {
                                return this._nextBuffered(tokenFilterContext);
                            }
                            if (null != tokenFilter5 && ((tokenFilterCheckValue = _headContext.checkValue(tokenFilter5)) == tokenFilter6 || (null != tokenFilterCheckValue && tokenFilterCheckValue.includeValue(delegate)))) {
                                if (this._verifyAllowedMatches()) {
                                    return this._nextBuffered(tokenFilterContext);
                                }
                            }
                        }
                    }
                }
                final TokenFilter filter = _headContext.getFilter();
                if (null != filter && filter != TokenFilter.INCLUDE_ALL) {
                    filter.filterFinishArray();
                }
                final TokenFilterContext tokenFilterContext2 = _headContext;
                if (tokenFilterContext2 == tokenFilterContext && tokenFilterContext2.isStartHandled()) {
                    z = true;
                }
                final TokenFilterContext parent = _headContext.getParent();
                _headContext = parent;
                _itemFilter = parent.getFilter();
                if (z) {
                    return jsonTokenNextToken;
                }
            }
        }
    }
    private JsonToken _nextBuffered(TokenFilterContext tokenFilterContext) throws IOException {
        _exposedContext = tokenFilterContext;
        final JsonToken jsonTokenNextTokenToRead = tokenFilterContext.nextTokenToRead();
        if (null != jsonTokenNextTokenToRead) {
            return jsonTokenNextTokenToRead;
        }
        while (tokenFilterContext != _headContext) {
            tokenFilterContext = _exposedContext.findChildOf(tokenFilterContext);
            _exposedContext = tokenFilterContext;
            if (null == tokenFilterContext) {
                throw this._constructError("Unexpected problem: chain of filtered context broken");
            }
            final JsonToken jsonTokenNextTokenToRead2 = tokenFilterContext.nextTokenToRead();
            if (null != jsonTokenNextTokenToRead2) {
                return jsonTokenNextTokenToRead2;
            }
        }
        throw this._constructError("Internal error: failed to locate expected buffered tokens");
    }
    private final boolean _verifyAllowedMatches() throws IOException {
        final int i2 = _matchCount;
        if (0 != i2 && !_allowMultipleMatches) {
            return false;
        }
        _matchCount = i2 + 1;
        return true;
    }
    public JsonToken nextValue() throws IOException {
        final JsonToken jsonTokenNextToken = this.nextToken();
        return JsonToken.FIELD_NAME == jsonTokenNextToken ? this.nextToken() : jsonTokenNextToken;
    }
    public JsonParser skipChildren() throws IOException {
        final JsonToken jsonToken = _currToken;
        if (JsonToken.START_OBJECT != jsonToken && JsonToken.START_ARRAY != jsonToken) {
            return this;
        }
        int i2 = 1;
        while (true) {
            final JsonToken jsonTokenNextToken = this.nextToken();
            if (null == jsonTokenNextToken) {
                return this;
            }
            if (jsonTokenNextToken.isStructStart()) {
                i2++;
            } else if (jsonTokenNextToken.isStructEnd() && 0 == i2 - 1) {
                return this;
            }
        }
    }
    public String getText() throws IOException {
        return delegate.getText();
    }
    public boolean hasTextCharacters() {
        return delegate.hasTextCharacters();
    }
    public char[] getTextCharacters() throws IOException {
        return delegate.getTextCharacters();
    }
    public int getTextLength() throws IOException {
        return delegate.getTextLength();
    }
    public int getTextOffset() throws IOException {
        return delegate.getTextOffset();
    }
    public BigInteger getBigIntegerValue() throws IOException {
        return delegate.getBigIntegerValue();
    }
    public byte getByteValue() throws IOException {
        return delegate.getByteValue();
    }
    public short getShortValue() throws IOException {
        return delegate.getShortValue();
    }
    public BigDecimal getDecimalValue() throws IOException {
        return delegate.getDecimalValue();
    }
    public double getDoubleValue() throws IOException {
        return delegate.getDoubleValue();
    }
    public float getFloatValue() throws IOException {
        return delegate.getFloatValue();
    }
    public int getIntValue() throws IOException {
        return delegate.getIntValue();
    }
    public long getLongValue() throws IOException {
        return delegate.getLongValue();
    }
    public JsonParser.NumberType getNumberType() throws IOException {
        return delegate.getNumberType();
    }
    public Number getNumberValue() throws IOException {
        return delegate.getNumberValue();
    }
    public int getValueAsInt() throws IOException {
        return delegate.getValueAsInt();
    }
    public int getValueAsInt(final int i2) throws IOException {
        return delegate.getValueAsInt(i2);
    }
    public long getValueAsLong() throws IOException {
        return delegate.getValueAsLong();
    }
    public long getValueAsLong(final long j2) throws IOException {
        return delegate.getValueAsLong(j2);
    }
    public String getValueAsString() throws IOException {
        return delegate.getValueAsString();
    }
    public String getValueAsString(final String str) throws IOException {
        return delegate.getValueAsString(str);
    }
    public Object getEmbeddedObject() throws IOException {
        return delegate.getEmbeddedObject();
    }
    public byte[] getBinaryValue(final Base64Variant base64Variant) throws IOException {
        return delegate.getBinaryValue(base64Variant);
    }
    public int readBinaryValue(final Base64Variant base64Variant, final OutputStream outputStream) throws IOException {
        return delegate.readBinaryValue(base64Variant, outputStream);
    }
    public JsonLocation getTokenLocation() {
        return delegate.getTokenLocation();
    }
    protected JsonStreamContext _filterContext() {
        final TokenFilterContext tokenFilterContext = _exposedContext;
        return null != tokenFilterContext ? tokenFilterContext : _headContext;
    }
}
