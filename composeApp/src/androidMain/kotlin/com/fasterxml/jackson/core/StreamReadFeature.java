package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.util.JacksonFeature;

public enum StreamReadFeature implements JacksonFeature {
    AUTO_CLOSE_SOURCE(JsonParser.Feature.AUTO_CLOSE_SOURCE),
    STRICT_DUPLICATE_DETECTION(JsonParser.Feature.STRICT_DUPLICATE_DETECTION),
    IGNORE_UNDEFINED(JsonParser.Feature.IGNORE_UNDEFINED),
    INCLUDE_SOURCE_IN_LOCATION(JsonParser.Feature.INCLUDE_SOURCE_IN_LOCATION);
    private final boolean _defaultState;
    private final JsonParser.Feature _mappedFeature;
    private final int _mask;
    StreamReadFeature(final JsonParser.Feature feature) {
        _mappedFeature = feature;
        _mask = feature.getMask();
        _defaultState = feature.enabledByDefault();
    }
    public static int collectDefaults() {
        int mask = 0;
        for (final StreamReadFeature streamReadFeature : StreamReadFeature.values()) {
            if (streamReadFeature.enabledByDefault()) {
                mask |= streamReadFeature._mask;
            }
        }
        return mask;
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
    public JsonParser.Feature mappedFeature() {
        return _mappedFeature;
    }
}
