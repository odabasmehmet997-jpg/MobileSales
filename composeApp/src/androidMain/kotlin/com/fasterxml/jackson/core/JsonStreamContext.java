package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.io.CharTypes;

public abstract class JsonStreamContext {
    protected int _index;
    protected int _type;
    public abstract String getCurrentName();
    public Object getCurrentValue() {
        return null;
    }
    public abstract JsonStreamContext getParent();
    public void setCurrentValue(final Object obj) {
    }
    protected JsonStreamContext() {
    }
    protected JsonStreamContext(final JsonStreamContext jsonStreamContext) {
        _type = jsonStreamContext._type;
        _index = jsonStreamContext._index;
    }
    protected JsonStreamContext(final int i2, final int i3) {
        _type = i2;
        _index = i3;
    }
    public final boolean inArray() {
        return 1 == this._type;
    }
    public final boolean inRoot() {
        return 0 == this._type;
    }
    public final boolean inObject() {
        return 2 == this._type;
    }
    public String typeDesc() {
        final int i2 = _type;
        if (0 == i2) {
            return "root";
        }
        if (1 == i2) {
            return "Array";
        }
        if (2 == i2) {
            return "Object";
        }
        return "?";
    }
    public final int getEntryCount() {
        return _index + 1;
    }
    public final int getCurrentIndex() {
        final int i2 = _index;
        if (0 > i2) {
            return 0;
        }
        return i2;
    }
    public String toString() {
        final StringBuilder sb = new StringBuilder(64);
        final int i2 = _type;
        if (0 == i2) {
            sb.append("/");
        } else if (1 == i2) {
            sb.append('[');
            sb.append(this.getCurrentIndex());
            sb.append(']');
        } else {
            sb.append('{');
            final String currentName = this.getCurrentName();
            if (null != currentName) {
                sb.append(JsonFactory.DEFAULT_QUOTE_CHAR);
                CharTypes.appendQuoted(sb, currentName);
                sb.append(JsonFactory.DEFAULT_QUOTE_CHAR);
            } else {
                sb.append('?');
            }
            sb.append('}');
        }
        return sb.toString();
    }
}
