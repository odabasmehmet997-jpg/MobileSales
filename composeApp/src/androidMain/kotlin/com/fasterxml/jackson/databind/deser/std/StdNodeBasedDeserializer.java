package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;

import java.io.IOException;

public abstract class StdNodeBasedDeserializer<T> extends StdDeserializer<T> implements ResolvableDeserializer {
    private static final long serialVersionUID = 1;
    protected JsonDeserializer<Object> _treeDeserializer;
    public abstract T convert(JsonNode jsonNode, DeserializationContext deserializationContext) throws IOException;
    protected StdNodeBasedDeserializer(final JavaType javaType) {
        super(javaType);
    }
    protected StdNodeBasedDeserializer(final Class<T> cls) {
        super(cls);
    }
    protected StdNodeBasedDeserializer(final StdNodeBasedDeserializer<?> stdNodeBasedDeserializer) {
        super(stdNodeBasedDeserializer);
        _treeDeserializer = stdNodeBasedDeserializer._treeDeserializer;
    }
    public void resolve(final DeserializationContext deserializationContext) throws JsonMappingException {
        _treeDeserializer = deserializationContext.findRootValueDeserializer(deserializationContext.constructType(JsonNode.class));
    }
    public T deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        return this.convert((JsonNode) _treeDeserializer.deserialize(jsonParser, deserializationContext), deserializationContext);
    }
    public Object deserializeWithType(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TypeDeserializer typeDeserializer) throws IOException {
        return this.convert((JsonNode) _treeDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer), deserializationContext);
    }
}
