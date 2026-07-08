package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;

class PrimitiveFactory extends Factory {
    public PrimitiveFactory(final Context context, final Type type) {
        super(context, type);
    }
    public PrimitiveFactory(final Context context, final Type type, final Class cls) {
        super(context, type, cls);
    }
    public Instance getInstance(final InputNode inputNode) throws Exception {
        final Value override = this.getOverride(inputNode);
        final Class type = this.getType();
        if (null == override) {
            return context.getInstance(type);
        }
        return new ObjectInstance(context, override);
    }
    public Object getInstance(final String str, final Class cls) throws Exception {
        return support.read(str, cls);
    }
    public String getText(final Object obj) throws Exception {
        final Class<?> cls = obj.getClass();
        if (cls.isEnum()) {
            return support.write(obj, cls);
        }
        return support.write(obj, cls);
    }
}
