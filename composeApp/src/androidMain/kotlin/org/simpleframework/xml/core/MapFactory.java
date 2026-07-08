package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


class MapFactory extends Factory {
    public MapFactory(final Context context, final Type type) {
        super(context, type);
    }

    @Override // org.simpleframework.xml.core.Factory
    public Object getInstance() throws Exception {
        final Class type = this.getType();
        final Class conversion = !isInstantiable(type) ? this.getConversion(type) : type;
        if (!this.isMap(conversion)) {
            throw new InstantiationException("Invalid map %s for %s", type, this.type);
        }
        return conversion.newInstance();
    }

    public Instance getInstance(final InputNode inputNode) throws Exception {
        final Value override = this.getOverride(inputNode);
        Class type = this.getType();
        if (null != override) {
            return this.getInstance(override);
        }
        if (!isInstantiable(type)) {
            type = this.getConversion(type);
        }
        if (!this.isMap(type)) {
            throw new InstantiationException("Invalid map %s for %s", type, this.type);
        }
        return context.getInstance(type);
    }

    public Instance getInstance(final Value value) throws Exception {
        Class type = value.getType();
        if (!isInstantiable(type)) {
            type = this.getConversion(type);
        }
        if (!this.isMap(type)) {
            throw new InstantiationException("Invalid map %s for %s", type, this.type);
        }
        return new ConversionInstance(context, value, type);
    }

    public Class getConversion(final Class cls) throws Exception {
        if (cls.isAssignableFrom(HashMap.class)) {
            return HashMap.class;
        }
        if (cls.isAssignableFrom(TreeMap.class)) {
            return TreeMap.class;
        }
        throw new InstantiationException("Cannot instantiate %s for %s", cls, type);
    }

    private boolean isMap(final Class cls) {
        return Map.class.isAssignableFrom(cls);
    }
}
