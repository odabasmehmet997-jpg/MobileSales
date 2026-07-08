package org.simpleframework.xml.core;

import org.simpleframework.xml.Version;
import org.simpleframework.xml.filter.Filter;
import org.simpleframework.xml.strategy.Strategy;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NodeMap;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

class Source implements Context {
    private final TemplateEngine engine;
    private final Filter filter;
    private final Session session;
    private final Strategy strategy;
    private final Support support;
    public Source(final Strategy strategy, final Support support, final Session session) {
        final TemplateFilter templateFilter = new TemplateFilter(this, support);
        filter = templateFilter;
        engine = new TemplateEngine(templateFilter);
        this.strategy = strategy;
        this.support = support;
        this.session = session;
    }
    public boolean isStrict() {
        return session.isStrict();
    }
    public Session getSession() {
        return session;
    }
    public Support getSupport() {
        return support;
    }
    public Style getStyle() {
        return support.getStyle();
    }
    public boolean isFloat(final Class cls) throws Exception {
        return Support.isFloat(cls);
    }
    public boolean isFloat(final Type type) throws Exception {
        return this.isFloat(type.type());
    }
    public boolean isPrimitive(final Class cls) throws Exception {
        return support.isPrimitive(cls);
    }
    public boolean isPrimitive(final Type type) throws Exception {
        return this.isPrimitive(type.type());
    }
    public Instance getInstance(final Class cls) {
        return support.getInstance(cls);
    }
    public Instance getInstance(final Value value) {
        return support.getInstance(value);
    }
    public String getName(final Class cls) throws Exception {
        return support.getName(cls);
    }
    public Version getVersion(final Class cls) throws Exception {
        return this.getScanner(cls).getRevision();
    }
    private Scanner getScanner(final Class cls) throws Exception {
        return support.getScanner(cls);
    }
    public Decorator getDecorator(final Class cls) throws Exception {
        return this.getScanner(cls).getDecorator();
    }
    public Caller getCaller(final Class cls) throws Exception {
        return this.getScanner(cls).getCaller(this);
    }
    public Schema getSchema(final Class cls) throws Exception {
        final Scanner scanner = this.getScanner(cls);
        if (null == scanner) {
            throw new PersistenceException("Invalid schema class %s", cls);
        }
        return new ClassSchema(scanner, this);
    }
    public Object getAttribute(final Object obj) {
        return session.get(obj);
    }
    public Value getOverride(final Type type, final InputNode inputNode) throws Exception {
        final NodeMap<InputNode> attributes = inputNode.getAttributes();
        if (null == attributes) {
            throw new PersistenceException("No attributes for %s", inputNode);
        }
        return strategy.read(type, attributes, session);
    }
    public boolean setOverride(final Type type, final Object obj, final OutputNode outputNode) throws Exception {
        final NodeMap<OutputNode> attributes = outputNode.getAttributes();
        if (null == attributes) {
            throw new PersistenceException("No attributes for %s", outputNode);
        }
        return strategy.write(type, obj, attributes, session);
    }
    public Class getType(final Type type, final Object obj) {
        if (null != obj) {
            return obj.getClass();
        }
        return type.type();
    }
    public String getProperty(final String str) {
        return engine.process(str);
    }
}
