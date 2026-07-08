package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.AccessPattern;

import java.io.IOException;
import java.util.Collection;

public abstract class DelegatingDeserializer extends StdDeserializer<Object> implements ContextualDeserializer, ResolvableDeserializer {
    private static final long serialVersionUID = 1;
    protected final JsonDeserializer<?> _delegatee;

    protected abstract JsonDeserializer<?> newDelegatingInstance(JsonDeserializer<?> jsonDeserializer);

    protected DelegatingDeserializer(final JsonDeserializer<?> jsonDeserializer) {
        super(jsonDeserializer.handledType());
        _delegatee = jsonDeserializer;
    }
    public void resolve(final DeserializationContext deserializationContext) throws JsonMappingException {
        final NullValueProvider nullValueProvider = _delegatee;
        if (nullValueProvider instanceof ResolvableDeserializer) {
            ((ResolvableDeserializer) nullValueProvider).resolve(deserializationContext);
        }
    }
    public JsonDeserializer<?> createContextual(final DeserializationContext deserializationContext, final BeanProperty beanProperty) throws JsonMappingException {
        final JsonDeserializer<?> jsonDeserializerHandleSecondaryContextualization = deserializationContext.handleSecondaryContextualization(_delegatee, beanProperty, deserializationContext.constructType(_delegatee.handledType()));
        return jsonDeserializerHandleSecondaryContextualization == _delegatee ? this : this.newDelegatingInstance(jsonDeserializerHandleSecondaryContextualization);
    }

    
    public JsonDeserializer<?> replaceDelegatee(final JsonDeserializer<?> jsonDeserializer) {
        return jsonDeserializer == _delegatee ? this : this.newDelegatingInstance(jsonDeserializer);
    }

    
    public Object deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        return _delegatee.deserialize(jsonParser, deserializationContext);
    }

    
    public Object deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws IOException {
        return _delegatee.deserialize(jsonParser, deserializationContext);
    }
    public Object deserializeWithType(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TypeDeserializer typeDeserializer) throws IOException {
        return _delegatee.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
    }

    
    public boolean isCachable() {
        return _delegatee.isCachable();
    }

    
    public Boolean supportsUpdate(final DeserializationConfig deserializationConfig) {
        return _delegatee.supportsUpdate(deserializationConfig);
    }

    
    public JsonDeserializer<?> getDelegatee() {
        return _delegatee;
    }

    
    public SettableBeanProperty findBackReference(final String str) {
        return _delegatee.findBackReference(str);
    }

    
    public AccessPattern getNullAccessPattern() {
        return _delegatee.getNullAccessPattern();
    }

    public Object getNullValue(final DeserializationContext deserializationContext) throws JsonMappingException {
        return _delegatee.getNullValue(deserializationContext);
    }

    
    public Object getEmptyValue(final DeserializationContext deserializationContext) throws JsonMappingException {
        return _delegatee.getEmptyValue(deserializationContext);
    }

    
    public LogicalType logicalType() {
        return _delegatee.logicalType();
    }

    
    public Collection<Object> getKnownPropertyNames() {
        return _delegatee.getKnownPropertyNames();
    }

    
    public ObjectIdReader getObjectIdReader() {
        return _delegatee.getObjectIdReader();
    }
}
