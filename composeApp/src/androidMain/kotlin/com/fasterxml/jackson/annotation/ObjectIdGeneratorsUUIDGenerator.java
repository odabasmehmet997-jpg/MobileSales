package com.fasterxml.jackson.annotation;
public final class ObjectIdGeneratorsUUIDGenerator extends ObjectIdGeneratorsBase<UUID> {
    private static final long serialVersionUID = 1;
    public ObjectIdGenerator<UUID> forScope(final Class<?> cls) {
        return this;
    }
    public ObjectIdGenerator<UUID> newForSerialization(final Object obj) {
        return this;
    }
    public ObjectIdGeneratorsUUIDGenerator() {
        this(Object.class);
    }
    private ObjectIdGeneratorsUUIDGenerator(final Class<?> cls) {
        super(Object.class);
    }
    public UUID generateId(final Object obj) {
        return UUID.randomUUID();
    }
    public ObjectIdGenerator.IdKey key(final Object obj) {
        if (null == obj) {
            return null;
        }
        return new ObjectIdGenerator.IdKey(ObjectIdGeneratorsUUIDGenerator.class, null, obj);
    }
    public boolean canUseFor(final ObjectIdGenerator<?> objectIdGenerator) {
        return ObjectIdGeneratorsUUIDGenerator.class == objectIdGenerator.getClass();
    }
}
