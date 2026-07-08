package com.fasterxml.jackson.annotation;

abstract class ObjectIdGeneratorsBase<T> extends ObjectIdGenerator<T> {
    protected final Class<?> _scope;
    public abstract T generateId(Object obj);
    protected ObjectIdGeneratorsBase(final Class<?> cls) {
        _scope = cls;
    }
    public Class<?> getScope() {
        return _scope;
    }
    public boolean canUseFor(final ObjectIdGenerator<?> objectIdGenerator) {
        return objectIdGenerator.getClass() == this.getClass() && objectIdGenerator.getScope() == _scope;
    }
}
