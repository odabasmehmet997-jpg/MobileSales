package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.Position;

import java.lang.reflect.Array;

class ArrayFactory extends Factory {
    public ArrayFactory(final Context context, final Type type) {
        super(context, type);
    }
    public Object getInstance() throws Exception {
        final Class componentType = this.getComponentType();
        if (null != componentType) {
            return Array.newInstance((Class<?>) componentType, 0);
        }
        return null;
    }
    public Instance getInstance(final InputNode inputNode) throws Exception {
        final Position position = inputNode.getPosition();
        final Value override = this.getOverride(inputNode);
        if (null == override) {
            throw new ElementException("Array length required for %s at %s", type, position);
        }
        return this.getInstance(override, override.getType());
    }
    private Instance getInstance(final Value value, final Class cls) throws Exception {
        final Class componentType = this.getComponentType();
        if (!componentType.isAssignableFrom(cls)) {
            throw new InstantiationException("Array of type %s cannot hold %s for %s", componentType, cls, type);
        }
        return new ArrayInstance(value);
    }
    private Class getComponentType() throws Exception {
        final Class type = this.getType();
        if (!type.isArray()) {
            throw new InstantiationException("The %s not an array for %s", type, this.type);
        }
        return type.getComponentType();
    }
}
