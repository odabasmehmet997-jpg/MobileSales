package org.simpleframework.xml.core;

import org.simpleframework.xml.*;

import java.lang.annotation.Annotation;
import java.util.Iterator;


class StructureBuilder {
    private final ModelAssembler assembler;
    private final LabelMap attributes;
    private final ExpressionBuilder builder;
    private final LabelMap elements;
    private Instantiator factory;
    private boolean primitive;
    private final InstantiatorBuilder resolver;
    private final Model root;
    private final Scanner scanner;
    private final Support support;
    private Label text;
    private final LabelMap texts;
    private Label version;
    public StructureBuilder(final Scanner scanner, final Detail detail, final Support support) throws Exception {
        final ExpressionBuilder expressionBuilder = new ExpressionBuilder(detail, support);
        builder = expressionBuilder;
        assembler = new ModelAssembler(expressionBuilder, detail, support);
        resolver = new InstantiatorBuilder(scanner, detail);
        root = new TreeModel(scanner, detail);
        attributes = new LabelMap(scanner);
        elements = new LabelMap(scanner);
        texts = new LabelMap(scanner);
        this.scanner = scanner;
        this.support = support;
    }
    public void assemble(final Class cls) throws Exception {
        final Order order = scanner.getOrder();
        if (null != order) {
            assembler.assemble(root, order);
        }
    }
    public void process(final Contact contact, final Annotation annotation) throws Exception {
        if (annotation instanceof Attribute) {
            this.process(contact, annotation, attributes);
        }
        if (annotation instanceof ElementUnion) {
            this.union(contact, annotation, elements);
        }
        if (annotation instanceof ElementListUnion) {
            this.union(contact, annotation, elements);
        }
        if (annotation instanceof ElementMapUnion) {
            this.union(contact, annotation, elements);
        }
        if (annotation instanceof ElementList) {
            this.process(contact, annotation, elements);
        }
        if (annotation instanceof ElementArray) {
            this.process(contact, annotation, elements);
        }
        if (annotation instanceof ElementMap) {
            this.process(contact, annotation, elements);
        }
        if (annotation instanceof Element) {
            this.process(contact, annotation, elements);
        }
        if (annotation instanceof Version) {
            this.version(contact, annotation);
        }
        if (annotation instanceof Text) {
            this.text(contact, annotation);
        }
    }
    private void union(final Contact contact, final Annotation annotation, final LabelMap labelMap) throws Exception {
        for (final Label label : support.getLabels(contact, annotation)) {
            final String path = label.getPath();
            final String name = label.getName();
            if (null != labelMap.get(path)) {
                throw new PersistenceException("Duplicate annotation of name '%s' on %s", name, label);
            }
            this.process(contact, label, labelMap);
        }
    }
    private void process(final Contact contact, final Annotation annotation, final LabelMap labelMap) throws Exception {
        final Label label = support.getLabel(contact, annotation);
        final String path = label.getPath();
        final String name = label.getName();
        if (null != labelMap.get(path)) {
            throw new PersistenceException("Duplicate annotation of name '%s' on %s", name, contact);
        }
        this.process(contact, label, labelMap);
    }
    private void process(final Contact contact, final Label label, final LabelMap labelMap) throws Exception {
        final Expression expression = label.getExpression();
        final String path = label.getPath();
        Model model = root;
        if (!expression.isEmpty()) {
            model = this.register(expression);
        }
        resolver.register(label);
        model.register(label);
        labelMap.put(path, label);
    }
    private void text(final Contact contact, final Annotation annotation) throws Exception {
        final Label label = support.getLabel(contact, annotation);
        final Expression expression = label.getExpression();
        final String path = label.getPath();
        Model model = root;
        if (!expression.isEmpty()) {
            model = this.register(expression);
        }
        if (null != this.texts.get(path)) {
            throw new TextException("Multiple text annotations in %s", annotation);
        }
        resolver.register(label);
        model.register(label);
        texts.put(path, label);
    }
    private void version(final Contact contact, final Annotation annotation) throws Exception {
        final Label label = support.getLabel(contact, annotation);
        if (null != this.version) {
            throw new AttributeException("Multiple version annotations in %s", annotation);
        }
        version = label;
    }
    public Structure build(final Class cls) throws Exception {
        return new Structure(factory, root, version, text, primitive);
    }
    private boolean isElement(final String str) throws Exception {
        final Expression build = builder.build(str);
        final Model lookup = this.lookup(build);
        if (null != lookup) {
            final String last = build.getLast();
            final int index = build.getIndex();
            if (lookup.isElement(last)) {
                return true;
            }
            return lookup.isModel(last) && !lookup.lookup(last, index).isEmpty();
        }
        return false;
    }
    private boolean isAttribute(final String str) throws Exception {
        final Expression build = builder.build(str);
        final Model lookup = this.lookup(build);
        if (null == lookup) {
            return false;
        }
        final String last = build.getLast();
        if (!build.isPath()) {
            return lookup.isAttribute(str);
        }
        return lookup.isAttribute(last);
    }
    private Model lookup(final Expression expression) throws Exception {
        final Expression path = expression.getPath(0, 1);
        if (expression.isPath()) {
            return root.lookup(path);
        }
        return root;
    }
    private Model register(final Expression expression) throws Exception {
        final Model lookup = root.lookup(expression);
        return null != lookup ? lookup : this.create(expression);
    }
    private Model create(Expression expression) throws Exception {
        Model model = root;
        while (null != model) {
            final String prefix = expression.getPrefix();
            final String first = expression.getFirst();
            final int index = expression.getIndex();
            if (null != first) {
                model = model.register(first, prefix, index);
            }
            if (!expression.isPath()) {
                break;
            }
            expression = expression.getPath(1);
        }
        return model;
    }
    public void commit(final Class cls) throws Exception {
        if (null == this.factory) {
            factory = resolver.build();
        }
    }
    public void validate(final Class cls) throws Exception {
        final Order order = scanner.getOrder();
        this.validateUnions(cls);
        this.validateElements(cls, order);
        this.validateAttributes(cls, order);
        this.validateModel(cls);
        this.validateText(cls);
        this.validateTextList(cls);
    }
    private void validateModel(final Class cls) throws Exception {
        if (root.isEmpty()) {
            return;
        }
        root.validate(cls);
    }
    private void validateText(final Class cls) throws Exception {
        final Label text = root.getText();
        if (null != text) {
            if (text.isTextList()) {
                return;
            }
            if (!elements.isEmpty()) {
                throw new TextException("Elements used with %s in %s", text, cls);
            }
            if (root.isComposite()) {
                throw new TextException("Paths used with %s in %s", text, cls);
            }
            return;
        }
        if (scanner.isEmpty()) {
            primitive = this.isEmpty();
        }
    }
    private void validateTextList(final Class cls) throws Exception {
        final Label text = root.getText();
        if (null == text || !text.isTextList()) {
            return;
        }
        final Object key = text.getKey();
        final Iterator<Label> it = elements.iterator();
        while (it.hasNext()) {
            final Label next = it.next();
            if (!next.getKey().equals(key)) {
                throw new TextException("Elements used with %s in %s", text, cls);
            }
            final Class type = next.getDependent().type();
            if (String.class == type) {
                throw new TextException("Illegal entry of %s with text annotations on %s in %s", String.class, text, cls);
            }
        }
        if (root.isComposite()) {
            throw new TextException("Paths used with %s in %s", text, cls);
        }
    }
    private void validateUnions(final Class cls) throws Exception {
        final Iterator<Label> it = elements.iterator();
        while (it.hasNext()) {
            final Label next = it.next();
            final String[] paths = next.getPaths();
            final Contact contact = next.getContact();
            for (final String str : paths) {
                final Annotation annotation = contact.getAnnotation();
                final Label label = elements.get(str);
                if (next.isInline() != label.isInline()) {
                    throw new UnionException("Inline must be consistent in %s for %s", annotation, contact);
                }
                if (next.isRequired() != label.isRequired()) {
                    throw new UnionException("Required must be consistent in %s for %s", annotation, contact);
                }
            }
        }
    }
    private void validateElements(final Class cls, final Order order) throws Exception {
        if (null != order) {
            for (final String str : order.elements()) {
                if (!this.isElement(str)) {
                    throw new ElementException("Ordered element '%s' missing for %s", str, cls);
                }
            }
        }
    }
    private void validateAttributes(final Class cls, final Order order) throws Exception {
        if (null != order) {
            for (final String str : order.attributes()) {
                if (!this.isAttribute(str)) {
                    throw new AttributeException("Ordered attribute '%s' missing in %s", str, cls);
                }
            }
        }
    }
    private boolean isEmpty() {
        if (null != this.text) {
            return false;
        }
        return root.isEmpty();
    }
}
