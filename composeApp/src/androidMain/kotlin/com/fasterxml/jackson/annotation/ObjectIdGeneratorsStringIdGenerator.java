package com.fasterxml.jackson.annotation;
public final class ObjectIdGeneratorsStringIdGenerator extends ObjectIdGeneratorsBase<String> {
    private static final long serialVersionUID = 1;
    public ObjectIdGenerator<String> forScope(final Class<?> cls) {
        return this;
    }
    public ObjectIdGenerator<String> newForSerialization(final Object obj) {
        return this;
    }

    public ObjectIdGeneratorsStringIdGenerator() {
        this(Object.class);
    }

    private ObjectIdGeneratorsStringIdGenerator(final Class<?> cls) {
        super(Object.class);
    }
    public String generateId(final Object obj) {
        return UUID.randomUUID().toString();
    }
    public ObjectIdGenerator.IdKey key(final Object obj) {
        if (null == obj) {
            return null;
        }
        return new ObjectIdGenerator.IdKey(ObjectIdGeneratorsStringIdGenerator.class, null, obj);
    }
    public boolean canUseFor(final ObjectIdGenerator<?> objectIdGenerator) {
        return objectIdGenerator instanceof ObjectIdGeneratorsStringIdGenerator;
    }
}
