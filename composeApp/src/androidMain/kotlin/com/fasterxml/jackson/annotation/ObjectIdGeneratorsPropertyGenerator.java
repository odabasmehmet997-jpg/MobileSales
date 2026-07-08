package com.fasterxml.jackson.annotation;

public abstract class ObjectIdGeneratorsPropertyGenerator extends ObjectIdGeneratorsBase<Object> {
    private static final long serialVersionUID = 1;
    public boolean canUseFor(final ObjectIdGenerator objectIdGenerator) {
        return super.canUseFor(objectIdGenerator);
    }

    protected ObjectIdGeneratorsPropertyGenerator(final Class<?> cls) {
        super(cls);
    }
}
