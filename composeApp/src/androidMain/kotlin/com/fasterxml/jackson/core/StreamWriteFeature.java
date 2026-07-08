package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.util.JacksonFeature;

public enum StreamWriteFeature implements JacksonFeature {
    AUTO_CLOSE_TARGET(JsonGenerator.Feature.AUTO_CLOSE_TARGET),
    AUTO_CLOSE_CONTENT(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT),
    FLUSH_PASSED_TO_STREAM(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM),
    WRITE_BIGDECIMAL_AS_PLAIN(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN),
    STRICT_DUPLICATE_DETECTION(JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION),
    IGNORE_UNKNOWN(JsonGenerator.Feature.IGNORE_UNKNOWN);
    private final boolean _defaultState;
    private final JsonGenerator.Feature _mappedFeature;
    private final int _mask;
    StreamWriteFeature(final JsonGenerator.Feature feature) {
        _mappedFeature = feature;
        _mask = feature.getMask();
        _defaultState = feature.enabledByDefault();
    }
    public static int collectDefaults() {
        int mask = 0;
        for (final StreamWriteFeature streamWriteFeature : StreamWriteFeature.values()) {
            if (streamWriteFeature.enabledByDefault()) {
                mask |= streamWriteFeature._mask;
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
    public JsonGenerator.Feature mappedFeature() {
        return _mappedFeature;
    }
}
