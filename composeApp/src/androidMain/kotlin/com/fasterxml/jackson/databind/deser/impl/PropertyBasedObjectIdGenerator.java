package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGeneratorsPropertyGenerator;
public class PropertyBasedObjectIdGenerator extends ObjectIdGeneratorsPropertyGenerator {
    private static final long serialVersionUID = 1;
    public ObjectIdGenerator<Object> newForSerialization(final Object obj) {
        return this;
    }
    public PropertyBasedObjectIdGenerator(final Class<?> cls) {
        super(cls);
    }
    public Object generateId(final Object obj) {
        throw new UnsupportedOperationException();
    }
    public ObjectIdGenerator<Object> forScope(final Class<?> cls) {
        return cls == _scope ? this : new PropertyBasedObjectIdGenerator(cls);
    }
    public ObjectIdGenerator.IdKey key(final Object obj) {
        if (null == obj) {
            return null;
        }
        return new ObjectIdGenerator.IdKey(this.getClass(), _scope, obj);
    }
}
