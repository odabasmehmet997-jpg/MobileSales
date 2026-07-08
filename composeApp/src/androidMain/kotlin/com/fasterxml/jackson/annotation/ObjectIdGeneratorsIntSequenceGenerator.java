package com.fasterxml.jackson.annotation;

public final class ObjectIdGeneratorsIntSequenceGenerator extends ObjectIdGeneratorsBase<Integer> {
    private static final long serialVersionUID = 1;
    private transient int _nextValue;

    private int initialValue() {
        return 1;
    }
    public  boolean canUseFor(final ObjectIdGenerator objectIdGenerator) {
        return super.canUseFor(objectIdGenerator);
    }
    public ObjectIdGeneratorsIntSequenceGenerator() {
        this(Object.class, -1);
    }
    public ObjectIdGeneratorsIntSequenceGenerator(final Class<?> cls, final int i2) {
        super(cls);
        _nextValue = i2;
    }
    public ObjectIdGenerator<Integer> forScope(final Class<?> cls) {
        return _scope == cls ? this : new ObjectIdGeneratorsIntSequenceGenerator(cls, _nextValue);
    }

    public ObjectIdGenerator<Integer> newForSerialization(final Object obj) {
        return new ObjectIdGeneratorsIntSequenceGenerator(_scope, this.initialValue());
    }
    public ObjectIdGenerator.IdKey key(final Object obj) {
        if (null == obj) {
            return null;
        }
        return new ObjectIdGenerator.IdKey(ObjectIdGeneratorsIntSequenceGenerator.class, _scope, obj);
    }
    public Integer generateId(final Object obj) {
        if (null == obj) {
            return null;
        }
        final int i2 = _nextValue;
        _nextValue = i2 + 1;
        return Integer.valueOf(i2);
    }
}
