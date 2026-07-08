package org.simpleframework.xml.strategy;

import org.simpleframework.xml.stream.Node;
import org.simpleframework.xml.stream.NodeMap;

import java.util.HashMap;


class ReadGraph extends HashMap {
    private final String label;
    private final String length;
    private final Loader loader;
    private final String mark;
    private final String refer;

    public ReadGraph(final Contract contract, final Loader loader) {
        refer = contract.getReference();
        mark = contract.getIdentity();
        length = contract.getLength();
        label = contract.getLabel();
        this.loader = loader;
    }

    public Value read(final Type type, final NodeMap nodeMap) throws Exception {
        final Node remove = nodeMap.remove(label);
        Class type2 = type.type();
        if (type2.isArray()) {
            type2 = type2.getComponentType();
        }
        if (null != remove) {
            type2 = loader.load(remove.getValue());
        }
        return this.readInstance(type, type2, nodeMap);
    }

    private Value readInstance(final Type type, final Class cls, final NodeMap nodeMap) throws Exception {
        final Node remove = nodeMap.remove(mark);
        if (null == remove) {
            return this.readReference(type, cls, nodeMap);
        }
        final String value = remove.getValue();
        if (this.containsKey(value)) {
            throw new CycleException("Element '%s' already exists", value);
        }
        return this.readValue(type, cls, nodeMap, value);
    }

    private Value readReference(final Type type, final Class cls, final NodeMap nodeMap) throws Exception {
        final Node remove = nodeMap.remove(refer);
        if (null == remove) {
            return this.readValue(type, cls, nodeMap);
        }
        final String value = remove.getValue();
        final Object v = this.get(value);
        if (!this.containsKey(value)) {
            throw new CycleException("Invalid reference '%s' found", value);
        }
        return new Reference(v, cls);
    }

    private Value readValue(final Type type, final Class cls, final NodeMap nodeMap) throws Exception {
        if (type.type().isArray()) {
            return this.readArray(type, cls, nodeMap);
        }
        return new ObjectValue(cls);
    }

    private Value readValue(final Type type, final Class cls, final NodeMap nodeMap, final String str) throws Exception {
        final Value readValue = this.readValue(type, cls, nodeMap);
        return null != str ? new Allocate(readValue, this, str) : readValue;
    }

    private Value readArray(final Type type, final Class cls, final NodeMap nodeMap) throws Exception {
        final Node remove = nodeMap.remove(length);
        return new ArrayValue(cls, null != remove ? Integer.parseInt(remove.getValue()) : 0);
    }
}
