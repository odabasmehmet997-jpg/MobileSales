package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;

public class TokenFilterContext extends JsonStreamContext {
    protected TokenFilterContext _child;
    protected String _currentName;
    protected TokenFilter _filter;
    protected boolean _needToHandleName;
    protected final TokenFilterContext _parent;
    protected boolean _startHandled;
    public Object getCurrentValue() {
        return null;
    }
    public void setCurrentValue(final Object obj) {
    }
    protected TokenFilterContext(final int i2, final TokenFilterContext tokenFilterContext, final TokenFilter tokenFilter, final boolean z) {
        _type = i2;
        _parent = tokenFilterContext;
        _filter = tokenFilter;
        _index = -1;
        _startHandled = z;
        _needToHandleName = false;
    }
    protected TokenFilterContext reset(final int i2, final TokenFilter tokenFilter, final boolean z) {
        _type = i2;
        _filter = tokenFilter;
        _index = -1;
        _currentName = null;
        _startHandled = z;
        _needToHandleName = false;
        return this;
    }
    public static TokenFilterContext createRootContext(final TokenFilter tokenFilter) {
        return new TokenFilterContext(0, null, tokenFilter, true);
    }
    public TokenFilterContext createChildArrayContext(final TokenFilter tokenFilter, final boolean z) {
        final TokenFilterContext tokenFilterContext = _child;
        if (null == tokenFilterContext) {
            final TokenFilterContext tokenFilterContext2 = new TokenFilterContext(1, this, tokenFilter, z);
            _child = tokenFilterContext2;
            return tokenFilterContext2;
        }
        return tokenFilterContext.reset(1, tokenFilter, z);
    }
    public TokenFilterContext createChildObjectContext(final TokenFilter tokenFilter, final boolean z) {
        final TokenFilterContext tokenFilterContext = _child;
        if (null == tokenFilterContext) {
            final TokenFilterContext tokenFilterContext2 = new TokenFilterContext(2, this, tokenFilter, z);
            _child = tokenFilterContext2;
            return tokenFilterContext2;
        }
        return tokenFilterContext.reset(2, tokenFilter, z);
    }
    public TokenFilter setFieldName(final String str) throws JsonProcessingException {
        _currentName = str;
        _needToHandleName = true;
        return _filter;
    }
    public TokenFilter checkValue(final TokenFilter tokenFilter) {
        final int i2 = _type;
        if (2 == i2) {
            return tokenFilter;
        }
        final int i3 = _index + 1;
        _index = i3;
        if (1 == i2) {
            return tokenFilter.includeElement(i3);
        }
        return tokenFilter.includeRootValue(i3);
    }
    public final TokenFilterContext getParent() {
        return _parent;
    }
    public final String getCurrentName() {
        return _currentName;
    }
    public TokenFilter getFilter() {
        return _filter;
    }
    public boolean isStartHandled() {
        return _startHandled;
    }
    public JsonToken nextTokenToRead() {
        if (!_startHandled) {
            _startHandled = true;
            if (2 == this._type) {
                return JsonToken.START_OBJECT;
            }
            return JsonToken.START_ARRAY;
        }
        if (!_needToHandleName || 2 != this._type) {
            return null;
        }
        _needToHandleName = false;
        return JsonToken.FIELD_NAME;
    }
    public TokenFilterContext findChildOf(final TokenFilterContext tokenFilterContext) {
        TokenFilterContext tokenFilterContext2 = _parent;
        if (tokenFilterContext2 == tokenFilterContext) {
            return this;
        }
        while (null != tokenFilterContext2) {
            final TokenFilterContext tokenFilterContext3 = tokenFilterContext2._parent;
            if (tokenFilterContext3 == tokenFilterContext) {
                return tokenFilterContext2;
            }
            tokenFilterContext2 = tokenFilterContext3;
        }
        return null;
    }
    protected void appendDesc(final StringBuilder sb) {
        final TokenFilterContext tokenFilterContext = _parent;
        if (null != tokenFilterContext) {
            tokenFilterContext.appendDesc(sb);
        }
        final int i2 = _type;
        if (2 != i2) {
            if (1 == i2) {
                sb.append('[');
                sb.append(this.getCurrentIndex());
                sb.append(']');
                return;
            }
            sb.append("/");
            return;
        }
        sb.append('{');
        if (null != this._currentName) {
            sb.append(JsonFactory.DEFAULT_QUOTE_CHAR);
            sb.append(_currentName);
            sb.append(JsonFactory.DEFAULT_QUOTE_CHAR);
        } else {
            sb.append('?');
        }
        sb.append('}');
    }
    public String toString() {
        final StringBuilder sb = new StringBuilder(64);
        this.appendDesc(sb);
        return sb.toString();
    }
}
