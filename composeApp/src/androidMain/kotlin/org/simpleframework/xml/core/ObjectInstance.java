package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Value;


class ObjectInstance implements Instance {
    private final Context context;
    private final Class type;
    private final Value value;

    public ObjectInstance(final Context context, final Value value) {
        type = value.getType();
        this.context = context;
        this.value = value;
    }

    @Override // org.simpleframework.xml.core.Instance
    public Object getInstance() throws Exception {
        if (value.isReference()) {
            return value.getValue();
        }
        final Object objectInstance = this.getInstance(type);
        final Value value = this.value;
        if (null != value) {
            value.setValue(objectInstance);
        }
        return objectInstance;
    }

    public Object getInstance(final Class cls) throws Exception {
        return context.getInstance(cls).getInstance();
    }

    @Override // org.simpleframework.xml.core.Instance
    public Object setInstance(final Object obj) {
        final Value value = this.value;
        if (null != value) {
            value.setValue(obj);
        }
        return obj;
    }

    @Override // org.simpleframework.xml.core.Instance
    public boolean isReference() {
        return value.isReference();
    }

    @Override // org.simpleframework.xml.core.Instance
    public Class getType() {
        return type;
    }
}
