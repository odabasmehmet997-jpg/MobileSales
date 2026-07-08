package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;

public class TokenBufferReadContext extends JsonStreamContext {
    protected String _currentName;
    protected Object _currentValue;
    protected final JsonStreamContext _parent;
    protected final JsonLocation _startLocation;
    protected TokenBufferReadContext(final JsonStreamContext jsonStreamContext, final JsonLocation jsonLocation) {
        super(jsonStreamContext);
        _parent = jsonStreamContext.getParent();
        _currentName = jsonStreamContext.getCurrentName();
        _currentValue = jsonStreamContext.getCurrentValue();
        _startLocation = jsonLocation;
    }
    protected TokenBufferReadContext() {
        super(0, -1);
        _parent = null;
        _startLocation = JsonLocation.f792NA;
    }
    protected TokenBufferReadContext(final TokenBufferReadContext tokenBufferReadContext, final int i2, final int i3) {
        super(i2, i3);
        _parent = tokenBufferReadContext;
        _startLocation = tokenBufferReadContext._startLocation;
    }
    public Object getCurrentValue() {
        return _currentValue;
    }
    public void setCurrentValue(final Object obj) {
        _currentValue = obj;
    }
    public static TokenBufferReadContext createRootContext(final JsonStreamContext jsonStreamContext) {
        if (null == jsonStreamContext) {
            return new TokenBufferReadContext();
        }
        return new TokenBufferReadContext(jsonStreamContext, null);
    }
    public TokenBufferReadContext createChildArrayContext() {
        _index++;
        return new TokenBufferReadContext(this, 1, -1);
    }
    public TokenBufferReadContext createChildObjectContext() {
        _index++;
        return new TokenBufferReadContext(this, 2, -1);
    }
    public TokenBufferReadContext parentOrCopy() {
        final JsonStreamContext jsonStreamContext = _parent;
        if (jsonStreamContext instanceof TokenBufferReadContext) {
            return (TokenBufferReadContext) jsonStreamContext;
        }
        if (null == jsonStreamContext) {
            return new TokenBufferReadContext();
        }
        return new TokenBufferReadContext(jsonStreamContext, _startLocation);
    }
    public String getCurrentName() {
        return _currentName;
    }
    public JsonStreamContext getParent() {
        return _parent;
    }
    public void setCurrentName(final String str) throws JsonProcessingException {
        _currentName = str;
    }
    public void updateForValue() {
        _index++;
    }
}
