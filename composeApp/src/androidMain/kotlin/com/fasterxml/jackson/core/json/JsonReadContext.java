package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.*;

public final class JsonReadContext extends JsonStreamContext {
    private JsonReadContext _child;
    private int _columnNr;
    private String _currentName;
    private Object _currentValue;
    private DupDetector _dups;
    private int _lineNr;
    private final JsonReadContext _parent;

    public JsonReadContext(final JsonReadContext jsonReadContext, final DupDetector dupDetector, final int i2, final int i3, final int i4) {
        _parent = jsonReadContext;
        _dups = dupDetector;
        _type = i2;
        _lineNr = i3;
        _columnNr = i4;
        _index = -1;
    }

    public void reset(final int i2, final int i3, final int i4) {
        _type = i2;
        _index = -1;
        _lineNr = i3;
        _columnNr = i4;
        _currentName = null;
        _currentValue = null;
        final DupDetector dupDetector = _dups;
        if (null != dupDetector) {
            dupDetector.reset();
        }
    }

    public JsonReadContext withDupDetector(final DupDetector dupDetector) {
        _dups = dupDetector;
        return this;
    }

    public Object getCurrentValue() {
        return _currentValue;
    }
    public void setCurrentValue(final Object obj) {
        _currentValue = obj;
    }

    public static JsonReadContext createRootContext(final DupDetector dupDetector) {
        return new JsonReadContext(null, dupDetector, 0, 1, 0);
    }

    public JsonReadContext createChildArrayContext(final int i2, final int i3) {
        JsonReadContext jsonReadContext = _child;
        if (null == jsonReadContext) {
            final DupDetector dupDetector = _dups;
            jsonReadContext = new JsonReadContext(this, null == dupDetector ? null : dupDetector.child(), 1, i2, i3);
            _child = jsonReadContext;
        } else {
            jsonReadContext.reset(1, i2, i3);
        }
        return jsonReadContext;
    }

    public JsonReadContext createChildObjectContext(final int i2, final int i3) {
        final JsonReadContext jsonReadContext = _child;
        if (null == jsonReadContext) {
            final DupDetector dupDetector = _dups;
            final JsonReadContext jsonReadContext2 = new JsonReadContext(this, null == dupDetector ? null : dupDetector.child(), 2, i2, i3);
            _child = jsonReadContext2;
            return jsonReadContext2;
        }
        jsonReadContext.reset(2, i2, i3);
        return jsonReadContext;
    }

    public String getCurrentName() {
        return _currentName;
    }

    public JsonReadContext getParent() {
        return _parent;
    }

    public JsonLocation getStartLocation(final Object obj) {
        return new JsonLocation(obj, -1L, _lineNr, _columnNr);
    }

    public JsonReadContext clearAndGetParent() {
        _currentValue = null;
        return _parent;
    }

    public DupDetector getDupDetector() {
        return _dups;
    }

    public boolean expectComma() {
        final int i2 = _index + 1;
        _index = i2;
        return 0 != this._type && 0 < i2;
    }

    public void setCurrentName(final String str) throws JsonProcessingException {
        _currentName = str;
        final DupDetector dupDetector = _dups;
        if (null != dupDetector) {
            this._checkDup(dupDetector, str);
        }
    }

    private void _checkDup(final DupDetector dupDetector, final String str) throws JsonProcessingException {
        if (dupDetector.isDup(str)) {
            final Object source = dupDetector.getSource();
            throw new JsonParseException(source instanceof JsonParser ? (JsonParser) source : null, "Duplicate field '" + str + "'");
        }
    }
}
