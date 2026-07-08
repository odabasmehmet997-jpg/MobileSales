package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;

public class JsonWriteContext extends JsonStreamContext {
    protected JsonWriteContext _child;
    protected String _currentName;
    protected Object _currentValue;
    protected DupDetector _dups;
    protected boolean _gotName;
    protected final JsonWriteContext _parent;

    protected JsonWriteContext(final int i2, final JsonWriteContext jsonWriteContext, final DupDetector dupDetector) {
        _type = i2;
        _parent = jsonWriteContext;
        _dups = dupDetector;
        _index = -1;
    }

    protected JsonWriteContext(final int i2, final JsonWriteContext jsonWriteContext, final DupDetector dupDetector, final Object obj) {
        _type = i2;
        _parent = jsonWriteContext;
        _dups = dupDetector;
        _index = -1;
        _currentValue = obj;
    }

    public JsonWriteContext reset(final int i2) {
        _type = i2;
        _index = -1;
        _currentName = null;
        _gotName = false;
        _currentValue = null;
        final DupDetector dupDetector = _dups;
        if (null != dupDetector) {
            dupDetector.reset();
        }
        return this;
    }

    public JsonWriteContext reset(final int i2, final Object obj) {
        _type = i2;
        _index = -1;
        _currentName = null;
        _gotName = false;
        _currentValue = obj;
        final DupDetector dupDetector = _dups;
        if (null != dupDetector) {
            dupDetector.reset();
        }
        return this;
    }

    public JsonWriteContext withDupDetector(final DupDetector dupDetector) {
        _dups = dupDetector;
        return this;
    }

    public Object getCurrentValue() {
        return _currentValue;
    }

    public void setCurrentValue(final Object obj) {
        _currentValue = obj;
    }

    public static JsonWriteContext createRootContext(final DupDetector dupDetector) {
        return new JsonWriteContext(0, null, dupDetector);
    }

    public JsonWriteContext createChildArrayContext() {
        final JsonWriteContext jsonWriteContext = _child;
        if (null == jsonWriteContext) {
            final DupDetector dupDetector = _dups;
            final JsonWriteContext jsonWriteContext2 = new JsonWriteContext(1, this, null == dupDetector ? null : dupDetector.child());
            _child = jsonWriteContext2;
            return jsonWriteContext2;
        }
        return jsonWriteContext.reset(1);
    }

    public JsonWriteContext createChildArrayContext(final Object obj) {
        final JsonWriteContext jsonWriteContext = _child;
        if (null == jsonWriteContext) {
            final DupDetector dupDetector = _dups;
            final JsonWriteContext jsonWriteContext2 = new JsonWriteContext(1, this, null == dupDetector ? null : dupDetector.child(), obj);
            _child = jsonWriteContext2;
            return jsonWriteContext2;
        }
        return jsonWriteContext.reset(1, obj);
    }

    public JsonWriteContext createChildObjectContext() {
        final JsonWriteContext jsonWriteContext = _child;
        if (null == jsonWriteContext) {
            final DupDetector dupDetector = _dups;
            final JsonWriteContext jsonWriteContext2 = new JsonWriteContext(2, this, null == dupDetector ? null : dupDetector.child());
            _child = jsonWriteContext2;
            return jsonWriteContext2;
        }
        return jsonWriteContext.reset(2);
    }

    public JsonWriteContext createChildObjectContext(final Object obj) {
        final JsonWriteContext jsonWriteContext = _child;
        if (null == jsonWriteContext) {
            final DupDetector dupDetector = _dups;
            final JsonWriteContext jsonWriteContext2 = new JsonWriteContext(2, this, null == dupDetector ? null : dupDetector.child(), obj);
            _child = jsonWriteContext2;
            return jsonWriteContext2;
        }
        return jsonWriteContext.reset(2, obj);
    }

    public final JsonWriteContext getParent() {
        return _parent;
    }
 
    public final String getCurrentName() {
        return _currentName;
    }

    public JsonWriteContext clearAndGetParent() {
        _currentValue = null;
        return _parent;
    }

    public DupDetector getDupDetector() {
        return _dups;
    }

    public int writeFieldName(final String str) throws JsonProcessingException {
        if (2 != this._type || _gotName) {
            return 4;
        }
        _gotName = true;
        _currentName = str;
        final DupDetector dupDetector = _dups;
        if (null != dupDetector) {
            this._checkDup(dupDetector, str);
        }
        return 0 > this._index ? 0 : 1;
    }

    private final void _checkDup(final DupDetector dupDetector, final String str) throws JsonProcessingException {
        if (dupDetector.isDup(str)) {
            final Object source = dupDetector.getSource();
            throw new JsonGenerationException("Duplicate field '" + str + "'", source instanceof JsonGenerator ? (JsonGenerator) source : null);
        }
    }

    public int writeValue() {
        final int i2 = _type;
        if (2 == i2) {
            if (!_gotName) {
                return 5;
            }
            _gotName = false;
            _index++;
            return 2;
        }
        if (1 == i2) {
            final int i3 = _index;
            _index = i3 + 1;
            return 0 > i3 ? 0 : 1;
        }
        final int i4 = _index + 1;
        _index = i4;
        return 0 == i4 ? 0 : 3;
    }
}
