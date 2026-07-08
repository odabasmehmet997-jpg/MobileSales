package org.simpleframework.xml.convert;

import org.simpleframework.xml.strategy.Strategy;
import org.simpleframework.xml.strategy.TreeStrategy;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NodeMap;
import org.simpleframework.xml.stream.OutputNode;

import java.util.Map;

public class AnnotationStrategy implements Strategy {
    private final ConverterScanner scanner;
    private final Strategy strategy;
    public AnnotationStrategy() {
        this(new TreeStrategy());
    }
    public AnnotationStrategy(final Strategy strategy) {
        scanner = new ConverterScanner();
        this.strategy = strategy;
    }
    public Value read(final Type type, final NodeMap<InputNode> nodeMap, final Map map) throws Exception {
        final Value read = strategy.read(type, nodeMap, map);
        return this.isReference(read) ? read : this.read(type, nodeMap, read);
    }
    private Value read(final Type type, final NodeMap<InputNode> nodeMap, final Value value) throws Exception {
        final Converter converter = scanner.getConverter(type, value);
        final InputNode node = nodeMap.getNode();
        if (null == converter) {
            return value;
        }
        final Object read = converter.read(node);
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
        final Converter converter = scanner.getConverter(type, obj);
        final OutputNode node = nodeMap.getNode();
        if (null == converter) {
            return false;
        }
        converter.write(node, obj);
        return true;
    }
    private boolean isReference(final Value value) {
        return null != value && value.isReference();
    }
}
