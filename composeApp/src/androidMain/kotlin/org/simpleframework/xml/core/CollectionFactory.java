package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;


class CollectionFactory extends Factory {
    public CollectionFactory(final Context context, final Type type) {
        super(context, type);
    }
    public Object getInstance() throws Exception {
        final Class type = this.getType();
        final Class conversion = !isInstantiable(type) ? this.getConversion(type) : type;
        if (!this.isCollection(conversion)) {
            throw new InstantiationException("Invalid collection %s for %s", type, this.type);
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
        if (!this.isCollection(type)) {
            throw new InstantiationException("Invalid collection %s for %s", type, this.type);
        }
        return context.getInstance(type);
    }
    public Instance getInstance(final Value value) throws Exception {
        Class type = value.getType();
        if (!isInstantiable(type)) {
            type = this.getConversion(type);
        }
        if (!this.isCollection(type)) {
            throw new InstantiationException("Invalid collection %s for %s", type, this.type);
        }
        return new ConversionInstance(context, value, type);
    }
    public Class getConversion(final Class cls) throws Exception {
        if (cls.isAssignableFrom(ArrayList.class)) {
            return ArrayList.class;
        }
        if (cls.isAssignableFrom(HashSet.class)) {
            return HashSet.class;
        }
        if (cls.isAssignableFrom(TreeSet.class)) {
            return TreeSet.class;
        }
        throw new InstantiationException("Cannot instantiate %s for %s", cls, type);
    }
    private boolean isCollection(final Class cls) {
        return Collection.class.isAssignableFrom(cls);
    }
}
