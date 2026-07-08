package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;

import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDeserializer extends ReferenceTypeDeserializer<AtomicReference<Object>> {
    private static final long serialVersionUID = 1;
    public  ReferenceTypeDeserializer<AtomicReference<Object>> withResolved2(final TypeDeserializer typeDeserializer, final JsonDeserializer jsonDeserializer) {
        return this.withResolved(typeDeserializer, (JsonDeserializer<?>) jsonDeserializer);
    }
    public AtomicReferenceDeserializer(final JavaType javaType, final ValueInstantiator valueInstantiator, final TypeDeserializer typeDeserializer, final JsonDeserializer<?> jsonDeserializer) {
        super(javaType, valueInstantiator, typeDeserializer, jsonDeserializer);
    }
    public ReferenceTypeDeserializer<AtomicReference<Object>> withResolved(final TypeDeserializer typeDeserializer, final JsonDeserializer<?> jsonDeserializer) {
        return new AtomicReferenceDeserializer(_fullType, _valueInstantiator, typeDeserializer, jsonDeserializer);
    }
    public AtomicReference<Object> getNullValue(final DeserializationContext deserializationContext) throws JsonMappingException {
        return new AtomicReference<>(_valueDeserializer.getNullValue(deserializationContext));
    }
    public Object getEmptyValue(final DeserializationContext deserializationContext) throws JsonMappingException {
        return this.getNullValue(deserializationContext);
    }
    public AtomicReference<Object> referenceValue(final Object obj) {
        return new AtomicReference<>(obj);
    }
    public Object getReferenced(final AtomicReference<Object> atomicReference) {
        return atomicReference.get();
    }
    public AtomicReference<Object> updateReference(final AtomicReference<Object> atomicReference, final Object obj) {
        atomicReference.set(obj);
        return atomicReference;
    }
    public Boolean supportsUpdate(final DeserializationConfig deserializationConfig) {
        return Boolean.TRUE;
    }

    protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
        return null;
    }
}
