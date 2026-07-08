package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public class ErrorThrowingDeserializer extends JsonDeserializer<Object> {
    private final Error _cause;
    public ErrorThrowingDeserializer(final NoClassDefFoundError noClassDefFoundError) {
        _cause = noClassDefFoundError;
    }
    public Object deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        throw _cause;
    }
}
