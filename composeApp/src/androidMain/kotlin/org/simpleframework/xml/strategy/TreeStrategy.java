package org.simpleframework.xml.strategy;

import org.simpleframework.xml.stream.Node;
import org.simpleframework.xml.stream.NodeMap;

import java.lang.reflect.Array;
import java.util.Map;


public class TreeStrategy implements Strategy {
    private final String label;
    private final String length;
    private final Loader loader;

    public TreeStrategy() {
        this(Name.LABEL, Name.LENGTH);
    }

    public TreeStrategy(final String str, final String str2) {
        loader = new Loader();
        length = str2;
        label = str;
    }

    @Override // org.simpleframework.xml.strategy.Strategy
    public Value read(final Type type, final NodeMap nodeMap, final Map map) throws Exception {
        final Class readValue = this.readValue(type, nodeMap);
        final Class type2 = type.type();
        if (type2.isArray()) {
            return this.readArray(readValue, nodeMap);
        }
        if (type2 != readValue) {
            return new ObjectValue(readValue);
        }
        return null;
    }

    private Value readArray(final Class cls, final NodeMap nodeMap) throws Exception {
        final Node remove = nodeMap.remove(length);
        return new ArrayValue(cls, null != remove ? Integer.parseInt(remove.getValue()) : 0);
    }

    private Class readValue(final Type type, final NodeMap nodeMap) throws Exception {
        final Node remove = nodeMap.remove(label);
        Class<?> type2 = type.type();
        if (type2.isArray()) {
            type2 = type2.getComponentType();
        }
        if (null == remove) {
            return type2;
        }
        return loader.load(remove.getValue());
    }

    @Override // org.simpleframework.xml.strategy.Strategy
    public boolean write(final Type type, final Object obj, final NodeMap nodeMap, final Map map) {
        final Class<?> cls = obj.getClass();
        final Class<?> type2 = type.type();
        final Class<?> writeArray = cls.isArray() ? this.writeArray(type2, obj, nodeMap) : cls;
        if (cls == type2) {
            return false;
        }
        nodeMap.put(label, writeArray.getName());
        return false;
    }

    private Class writeArray(final Class cls, final Object obj, final NodeMap nodeMap) {
        final int length = Array.getLength(obj);
        final String str = this.length;
        if (null != str) {
            nodeMap.put(str, String.valueOf(length));
        }
        return cls.getComponentType();
    }
}
