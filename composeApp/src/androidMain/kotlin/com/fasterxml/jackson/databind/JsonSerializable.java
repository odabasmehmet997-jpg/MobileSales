package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.proje.mobilesales.features.collections.casefiche.view.activity.CaseFicheActivity;

import java.io.IOException;

public interface JsonSerializable {
    abstract class Base implements JsonSerializable {
        public boolean isEmpty(final SerializerProvider serializerProvider) {
            return false;
        }
        public CaseFicheActivity get() {
            return null;
        }
    }
    void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException;
    void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException;
}
