package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Value;

class ConversionInstance implements Instance {
    private final Context context;
    private final Class convert;
    private final Value value;
    public ConversionInstance(final Context context, final Value value, final Class cls) throws Exception {
        this.context = context;
        convert = cls;
        this.value = value;
    }
    public Object getInstance() throws Exception {
        if (value.isReference()) {
            return value.getValue();
        }
        final Object conversionInstance = this.getInstance(convert);
        if (null != conversionInstance) {
            this.setInstance(conversionInstance);
        }
        return conversionInstance;
    }
    public Object getInstance(final Class cls) throws Exception {
        return context.getInstance(cls).getInstance();
    }
    public Object setInstance(final Object obj) throws Exception {
        final Value value = this.value;
        if (null != value) {
            value.setValue(obj);
        }
        return obj;
    }
    public Class getType() {
        return convert;
    }
    public boolean isReference() {
        return value.isReference();
    }
}
