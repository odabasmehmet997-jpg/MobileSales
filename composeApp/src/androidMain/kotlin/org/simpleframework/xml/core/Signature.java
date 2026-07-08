package org.simpleframework.xml.core;

import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.List;

class Signature implements Iterable<Parameter> {
    private final Constructor factory;
    private final ParameterMap parameters;
    private final Class type;
    public Signature(final Signature signature) {
        this(signature.factory, signature.type);
    }
    public Signature(final Constructor constructor) {
        this(constructor, constructor.getDeclaringClass());
    }
    public Signature(final Constructor constructor, final Class cls) {
        parameters = new ParameterMap();
        factory = constructor;
        type = cls;
    }
    public int size() {
        return parameters.size();
    }
    public boolean isEmpty() {
        return parameters.isEmpty();
    }
    public boolean contains(final Object obj) {
        return parameters.containsKey(obj);
    }
    public Iterator<Parameter> iterator() {
        return parameters.iterator();
    }
    public Parameter remove(final Object obj) {
        return parameters.remove(obj);
    }
    public Parameter get(final int i2) {
        return parameters.get(i2);
    }
    public Parameter get(final Object obj) {
        return parameters.get(obj);
    }
    public List<Parameter> getAll() {
        return parameters.getAll();
    }
    public void add(final Parameter parameter) {
        final Object key = parameter.getKey();
        if (null != key) {
            parameters.put(key, parameter);
        }
    }
    public void set(final Object obj, final Parameter parameter) {
        parameters.put(obj, parameter);
    }
    public Object create() throws Exception {
        if (!factory.isAccessible()) {
            factory.setAccessible(true);
        }
        return factory.newInstance(null);
    }
    public Object create(final Object[] objArr) throws Exception {
        if (!factory.isAccessible()) {
            factory.setAccessible(true);
        }
        return factory.newInstance(objArr);
    }
    public Signature copy() throws Exception {
        final Signature signature = new Signature(this);
        for (Parameter param : this) {
            signature.add(param);
        }
        return signature;
    }
    public Class getType() {
        return type;
    }
    public String toString() {
        return factory.toString();
    }
}
