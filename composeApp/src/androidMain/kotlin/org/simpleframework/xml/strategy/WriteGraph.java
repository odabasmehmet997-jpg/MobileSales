package org.simpleframework.xml.strategy;

import org.simpleframework.xml.stream.NodeMap;

import java.lang.reflect.Array;
import java.util.IdentityHashMap;


class WriteGraph extends IdentityHashMap<Object, String> {
    private final String label;
    private final String length;
    private final String mark;
    private final String refer;

    public WriteGraph(final Contract contract) {
        refer = contract.getReference();
        mark = contract.getIdentity();
        length = contract.getLength();
        label = contract.getLabel();
    }

    public boolean write(final Type type, final Object obj, final NodeMap nodeMap) {
        final Class<?> cls = obj.getClass();
        final Class<?> type2 = type.type();
        final Class<?> writeArray = cls.isArray() ? this.writeArray(cls, obj, nodeMap) : cls;
        if (cls != type2) {
            nodeMap.put(label, writeArray.getName());
        }
        return this.writeReference(obj, nodeMap);
    }

    private boolean writeReference(final Object obj, final NodeMap nodeMap) {
        final String str = this.get(obj);
        final int size = this.size();
        if (null != str) {
            nodeMap.put(refer, str);
            return true;
        }
        final String valueOf = String.valueOf(size);
        nodeMap.put(mark, valueOf);
        this.put(obj, valueOf);
        return false;
    }

    private Class writeArray(final Class cls, final Object obj, final NodeMap nodeMap) {
        final int length = Array.getLength(obj);
        if (!this.containsKey(obj)) {
            nodeMap.put(this.length, String.valueOf(length));
        }
        return cls.getComponentType();
    }
}
