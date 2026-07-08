package com.fasterxml.jackson.databind.jsonFormatVisitors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.HashMap;
import java.util.Map;

public enum JsonFormatTypes {
    STRING,
    NUMBER,
    INTEGER,
    BOOLEAN,
    OBJECT,
    ARRAY,
    NULL,
    ANY;

    private static final Map<String, JsonFormatTypes> _byLCName = new HashMap();

    static {
        for (final JsonFormatTypes jsonFormatTypes : JsonFormatTypes.values()) {
            JsonFormatTypes._byLCName.put(jsonFormatTypes.name().toLowerCase(), jsonFormatTypes);
        }
    }
    public String value() {
        return this.name().toLowerCase();
    }

    public static JsonFormatTypes forValue(final String str) {
        return JsonFormatTypes._byLCName.get(str);
    }
}
