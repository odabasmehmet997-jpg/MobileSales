package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.util.Set;

public class UnsupportedTypeDeserializer extends StdDeserializer<Object> {
    private static final long serialVersionUID = 1;
    protected final String _message;
    protected final JavaType _type;
    public UnsupportedTypeDeserializer(final JavaType javaType, final String str) {
        super(javaType);
        _type = javaType;
        _message = str;
    }
    public Object deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        deserializationContext.reportBadDefinition(_type, _message);
        return null;
    }
    protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
        return null;
    }
}
