package org.simpleframework.xml.convert;

import org.simpleframework.xml.strategy.Strategy;
import org.simpleframework.xml.strategy.TreeStrategy;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NodeMap;
import org.simpleframework.xml.stream.OutputNode;
import java.util.Map;

public class RegistryStrategy implements Strategy {
    private final Registry registry;
    private final Strategy strategy;
    public RegistryStrategy(final Registry registry) {
        this(registry, new TreeStrategy());
    }
    public RegistryStrategy(final Registry registry, final Strategy strategy) {
        this.registry = registry;
        this.strategy = strategy;
    }
    public Value read(final Type type, final NodeMap<InputNode> nodeMap, final Map map) throws Exception {
        final Value read = strategy.read(type, nodeMap, map);
        return this.isReference(read) ? read : this.read(type, nodeMap, read);
    }
    private Value read(final Type type, final NodeMap<InputNode> nodeMap, final Value value) throws Exception {
        final Converter lookup = this.lookup(type, value);
        final InputNode node = nodeMap.getNode();
        if (null == lookup) {
            return value;
        }
        final Object read = lookup.read(node);
        final Class type2 = type.type();
        if (null != value) {
            value.setValue(read);
        }
        return new Reference(value, read, type2);
    }
    public boolean write(final Type type, final Object obj, final NodeMap<OutputNode> nodeMap, final Map map) throws Exception {
        final boolean write = strategy.write(type, obj, nodeMap, map);
        return !write ? this.write(type, obj, nodeMap) : write;
    }
    private boolean write(final Type type, final Object obj, final NodeMap<OutputNode> nodeMap) throws Exception {
        final Converter lookup = this.lookup(type, obj);
        final OutputNode node = nodeMap.getNode();
        if (null == lookup) {
            return false;
        }
        lookup.write(node, obj);
        return true;
    }
    private Converter lookup(final Type type, final Value value) throws Exception {
        Class type2 = type.type();
        if (null != value) {
            type2 = value.getType();
        }
        return registry.lookup(type2);
    }
    private Converter lookup(final Type type, final Object obj) throws Exception {
        Class<?> type2 = type.type();
        if (null != obj) {
            type2 = obj.getClass();
        }
        return registry.lookup(type2);
    }
    private boolean isReference(final Value value) {
        return null != value && value.isReference();
    }
}
