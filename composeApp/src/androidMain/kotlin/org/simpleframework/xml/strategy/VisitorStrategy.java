package org.simpleframework.xml.strategy;

import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NodeMap;
import org.simpleframework.xml.stream.OutputNode;

import java.util.Map;


public class VisitorStrategy implements Strategy {
    private final Strategy strategy;
    private final Visitor visitor;

    public VisitorStrategy(final Visitor visitor) {
        this(visitor, new TreeStrategy());
    }

    public VisitorStrategy(final Visitor visitor, final Strategy strategy) {
        this.strategy = strategy;
        this.visitor = visitor;
    }

    @Override // org.simpleframework.xml.strategy.Strategy
    public Value read(final Type type, final NodeMap<InputNode> nodeMap, final Map map) throws Exception {
        final Visitor visitor = this.visitor;
        if (null != visitor) {
            visitor.read(type, nodeMap);
        }
        return strategy.read(type, nodeMap, map);
    }

    @Override // org.simpleframework.xml.strategy.Strategy
    public boolean write(final Type type, final Object obj, final NodeMap<OutputNode> nodeMap, final Map map) throws Exception {
        final boolean write = strategy.write(type, obj, nodeMap, map);
        final Visitor visitor = this.visitor;
        if (null != visitor) {
            visitor.write(type, nodeMap);
        }
        return write;
    }
}
