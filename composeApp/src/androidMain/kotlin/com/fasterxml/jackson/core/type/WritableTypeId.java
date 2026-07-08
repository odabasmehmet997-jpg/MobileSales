package com.fasterxml.jackson.core.type;

import com.fasterxml.jackson.core.JsonToken;

public class WritableTypeId {
    public String asProperty;
    public Object forValue;
    public Class<?> forValueType;
    public Object f798id;
    public Inclusion include;
    public JsonToken valueShape;
    public boolean wrapperWritten;

    public enum Inclusion {
        WRAPPER_ARRAY,
        WRAPPER_OBJECT,
        METADATA_PROPERTY,
        PAYLOAD_PROPERTY,
        PARENT_PROPERTY;

        public boolean requiresObjectContext() {
            return Inclusion.METADATA_PROPERTY == this || Inclusion.PAYLOAD_PROPERTY == this;
        }
    }

    public WritableTypeId() {
    }

    public WritableTypeId(final Object obj, final JsonToken jsonToken) {
        this(obj, jsonToken, null);
    }

    public WritableTypeId(final Object obj, final JsonToken jsonToken, final Object obj2) {
        forValue = obj;
        f798id = obj2;
        valueShape = jsonToken;
    }
}
