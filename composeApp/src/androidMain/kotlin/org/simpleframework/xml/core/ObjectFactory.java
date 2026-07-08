package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;


class ObjectFactory extends PrimitiveFactory {
    public ObjectFactory(final Context context, final Type type, final Class cls) {
        super(context, type, cls);
    }

    @Override // org.simpleframework.xml.core.PrimitiveFactory
    public Instance getInstance(final InputNode inputNode) throws Exception {
        final Value override = this.getOverride(inputNode);
        final Class type = this.getType();
        if (null == override) {
            if (!isInstantiable(type)) {
                throw new InstantiationException("Cannot instantiate %s for %s", type, this.type);
            }
            return context.getInstance(type);
        }
        return new ObjectInstance(context, override);
    }
}
