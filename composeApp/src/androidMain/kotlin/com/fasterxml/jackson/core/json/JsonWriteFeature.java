package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.FormatFeature;
import com.fasterxml.jackson.core.JsonGenerator;

public enum JsonWriteFeature implements FormatFeature {
    QUOTE_FIELD_NAMES(true, JsonGenerator.Feature.QUOTE_FIELD_NAMES),
    WRITE_NAN_AS_STRINGS(true, JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS),
    WRITE_NUMBERS_AS_STRINGS(false, JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS),
    ESCAPE_NON_ASCII(false, JsonGenerator.Feature.ESCAPE_NON_ASCII);

    private final boolean _defaultState;
    private final JsonGenerator.Feature _mappedFeature;
    private final int _mask = 1 << this.ordinal();

    public static int collectDefaults() {
        int mask = 0;
        for (final JsonWriteFeature jsonWriteFeature : JsonWriteFeature.values()) {
            if (jsonWriteFeature.enabledByDefault()) {
                mask |= jsonWriteFeature._mask;
            }
        }
        return mask;
    }

    JsonWriteFeature(final boolean z, final JsonGenerator.Feature feature) {
        _defaultState = z;
        _mappedFeature = feature;
    }

    public boolean enabledByDefault() {
        return _defaultState;
    }
 
    public int getMask() {
        return _mask;
    }

    public boolean enabledIn(final int i2) {
        return 0 != (i2 & this._mask);
    }

    public JsonGenerator.Feature mappedFeature() {
        return _mappedFeature;
    }
}
