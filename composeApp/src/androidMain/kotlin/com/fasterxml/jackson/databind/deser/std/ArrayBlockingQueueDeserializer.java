package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueDeserializer extends CollectionDeserializer {
    private static final long serialVersionUID = 1;
    protected Collection<Object> createDefaultInstance(final DeserializationContext deserializationContext) throws IOException {
        return null;
    }
    protected CollectionDeserializer withResolved(final JsonDeserializer<?> jsonDeserializer, final JsonDeserializer<?> jsonDeserializer2, final TypeDeserializer typeDeserializer, final NullValueProvider nullValueProvider, final Boolean bool) {
        return withResolved((JsonDeserializer<?>) jsonDeserializer, (JsonDeserializer<?>) jsonDeserializer2, typeDeserializer, nullValueProvider, bool);
    }
    public ArrayBlockingQueueDeserializer(final JavaType javaType, final JsonDeserializer<Object> jsonDeserializer, final TypeDeserializer typeDeserializer, final ValueInstantiator valueInstantiator) {
        super(javaType, jsonDeserializer, typeDeserializer, valueInstantiator);
    }
    protected ArrayBlockingQueueDeserializer(final JavaType javaType, final JsonDeserializer<Object> jsonDeserializer, final TypeDeserializer typeDeserializer, final ValueInstantiator valueInstantiator, final JsonDeserializer<Object> jsonDeserializer2, final NullValueProvider nullValueProvider, final Boolean bool) {
        super(javaType, jsonDeserializer, typeDeserializer, valueInstantiator, jsonDeserializer2, nullValueProvider, bool);
    }
    protected ArrayBlockingQueueDeserializer(final ArrayBlockingQueueDeserializer arrayBlockingQueueDeserializer) {
        super(arrayBlockingQueueDeserializer);
    }
    protected ArrayBlockingQueueDeserializer withResolved(final JsonDeserializer<?> jsonDeserializer, final JsonDeserializer<?> jsonDeserializer2, final TypeDeserializer typeDeserializer, final NullValueProvider nullValueProvider, final Boolean bool) {
        return new ArrayBlockingQueueDeserializer(_containerType, (JsonDeserializer<Object>) jsonDeserializer2, typeDeserializer, _valueInstantiator, (JsonDeserializer<Object>) jsonDeserializer, nullValueProvider, bool);
    }
    protected Collection<Object> _deserializeFromArray(final JsonParser jsonParser, final DeserializationContext deserializationContext, Collection<Object> collection) throws IOException {
        if (null == collection) {
            collection = new ArrayList<>();
        }
        final Collection<Object> collection_deserializeFromArray = super._deserializeFromArray(jsonParser, deserializationContext, collection);
        if (collection_deserializeFromArray.isEmpty()) {
            return new ArrayBlockingQueue(1, false);
        }
        return new ArrayBlockingQueue(collection_deserializeFromArray.size(), false, collection_deserializeFromArray);
    }
    public Object deserializeWithType(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }
}
