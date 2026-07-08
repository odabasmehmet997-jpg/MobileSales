package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

public class RawValue implements JsonSerializable {
    protected Object _value;
    public RawValue(final String str) {
        _value = str;
    }
    public void serialize(final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        final Object obj = _value;
        if (obj instanceof JsonSerializable) {
            ((JsonSerializable) obj).serialize(jsonGenerator, serializerProvider);
        } else {
            this._serialize(jsonGenerator);
        }
    }
    public void serializeWithType(final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider, final TypeSerializer typeSerializer) throws IOException {
        final Object obj = _value;
        if (obj instanceof JsonSerializable) {
            ((JsonSerializable) obj).serializeWithType(jsonGenerator, serializerProvider, typeSerializer);
        } else if (obj instanceof SerializableString) {
            this.serialize(jsonGenerator, serializerProvider);
        }
    }
    public void serialize(final JsonGenerator jsonGenerator) throws IOException {
        final Object obj = _value;
        if (obj instanceof JsonSerializable) {
            jsonGenerator.writeObject(obj);
        } else {
            this._serialize(jsonGenerator);
        }
    }
    protected void _serialize(final JsonGenerator jsonGenerator) throws IOException {
        final Object obj = _value;
        if (obj instanceof SerializableString) {
            jsonGenerator.writeRawValue((SerializableString) obj);
        } else {
            jsonGenerator.writeRawValue(String.valueOf(obj));
        }
    }
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RawValue)) {
            return false;
        }
        final Object obj2 = _value;
        final Object obj3 = ((RawValue) obj)._value;
        if (obj2 == obj3) {
            return true;
        }
        return null != obj2 && obj2.equals(obj3);
    }
    public int hashCode() {
        final Object obj = _value;
        if (null == obj) {
            return 0;
        }
        return obj.hashCode();
    }
    public String toString() {
        return String.format("[RawValue of type %s]", ClassUtil.classNameOf(_value));
    }
}
