package org.simpleframework.xml.core;

import org.simpleframework.xml.Order;
import org.simpleframework.xml.Version;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.List;


class ObjectScanner implements Scanner {
    private StructureBuilder builder;
    private final Detail detail;
    private final ClassScanner scanner;
    private Structure structure;
    private final Support support;

    public ObjectScanner(final Detail detail, final Support support) throws Exception {
        scanner = new ClassScanner(detail, support);
        builder = new StructureBuilder(this, detail, support);
        this.support = support;
        this.detail = detail;
        this.scan(detail);
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Signature getSignature() {
        return scanner.getSignature();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public List<Signature> getSignatures() {
        return scanner.getSignatures();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public ParameterMap getParameters() {
        return scanner.getParameters();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Instantiator getInstantiator() {
        return structure.getInstantiator();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Class getType() {
        return detail.getType();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Decorator getDecorator() {
        return scanner.getDecorator();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Caller getCaller(final Context context) {
        return new Caller(this, context);
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Section getSection() {
        return structure.getSection();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Version getRevision() {
        return structure.getRevision();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Order getOrder() {
        return scanner.getOrder();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Label getVersion() {
        return structure.getVersion();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Label getText() {
        return structure.getText();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public String getName() {
        return detail.getName();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Function getCommit() {
        return scanner.getCommit();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Function getValidate() {
        return scanner.getValidate();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Function getPersist() {
        return scanner.getPersist();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Function getComplete() {
        return scanner.getComplete();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Function getReplace() {
        return scanner.getReplace();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Function getResolve() {
        return scanner.getResolve();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public boolean isPrimitive() {
        return structure.isPrimitive();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public boolean isEmpty() {
        return null == this.scanner.getRoot();
    }

    @Override // org.simpleframework.xml.core.Scanner, org.simpleframework.xml.core.Policy
    public boolean isStrict() {
        return detail.isStrict();
    }

    private void scan(final Detail detail) throws Exception {
        this.order(detail);
        this.field(detail);
        this.method(detail);
        this.validate(detail);
        this.commit(detail);
    }

    private void order(final Detail detail) throws Exception {
        builder.assemble(detail.getType());
    }

    private void commit(final Detail detail) throws Exception {
        final Class type = detail.getType();
        if (null == this.structure) {
            structure = builder.build(type);
        }
        builder = null;
    }

    private void validate(final Detail detail) throws Exception {
        final Class type = detail.getType();
        builder.commit(type);
        builder.validate(type);
    }

    private void field(final Detail detail) throws Exception {
        final Iterator<Contact> it = support.getFields(detail.getType(), detail.getOverride()).iterator();
        while (it.hasNext()) {
            final Contact next = it.next();
            final Annotation annotation = next.getAnnotation();
            if (null != annotation) {
                builder.process(next, annotation);
            }
        }
    }

    private void method(final Detail detail) throws Exception {
        final Iterator<Contact> it = support.getMethods(detail.getType(), detail.getOverride()).iterator();
        while (it.hasNext()) {
            final Contact next = it.next();
            final Annotation annotation = next.getAnnotation();
            if (null != annotation) {
                builder.process(next, annotation);
            }
        }
    }
}
