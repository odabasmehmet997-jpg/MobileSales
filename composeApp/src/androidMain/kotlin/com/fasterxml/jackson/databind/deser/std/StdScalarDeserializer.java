package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.AccessPattern;
import java.io.IOException;

public abstract class StdScalarDeserializer<T> extends StdDeserializer<T> {
    private static final long serialVersionUID = 1;

    protected StdScalarDeserializer(final Class<?> cls) {
        super(cls);
    }

    protected StdScalarDeserializer(final JavaType javaType) {
        super(javaType);
    }

    protected StdScalarDeserializer(final StdScalarDeserializer<?> stdScalarDeserializer) {
        super(stdScalarDeserializer);
    }

    
    public LogicalType logicalType() {
        return LogicalType.OtherScalar;
    }

    
    public Boolean supportsUpdate(final DeserializationConfig deserializationConfig) {
        return Boolean.FALSE;
    }

    
    public AccessPattern getNullAccessPattern() {
        return AccessPattern.ALWAYS_NULL;
    }

    
    public AccessPattern getEmptyAccessPattern() {
        return AccessPattern.CONSTANT;
    }
    public Object deserializeWithType(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromScalar(jsonParser, deserializationContext);
    }
    public T deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext, final T t) throws IOException {
        deserializationContext.handleBadMerge(this);
        return this.deserialize(jsonParser, deserializationContext);
    }
}
