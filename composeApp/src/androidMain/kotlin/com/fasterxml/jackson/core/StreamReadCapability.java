package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.util.JacksonFeature;

public enum StreamReadCapability implements JacksonFeature {
    DUPLICATE_PROPERTIES(false),
    SCALARS_AS_OBJECTS(false),
    UNTYPED_SCALARS(false);
    private final boolean _defaultState;
    private final int _mask = 1 << this.ordinal();
    StreamReadCapability(final boolean z) {
        _defaultState = z;
    }
    public boolean enabledByDefault() {
        return _defaultState;
    }
    public boolean enabledIn(final int i2) {
        return 0 != (i2 & this._mask);
    }
    public int getMask() {
        return _mask;
    }
}
