package org.simpleframework.xml.core;

import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Version;

import java.util.List;


class DefaultScanner implements Scanner {
    private final Detail detail;
    private final Scanner scanner;

    public DefaultScanner(final Detail detail, final Support support) throws Exception {
        final DefaultDetail defaultDetail = new DefaultDetail(detail, DefaultType.FIELD);
        this.detail = defaultDetail;
        scanner = new ObjectScanner(defaultDetail, support);
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
        return scanner.getInstantiator();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Class getType() {
        return scanner.getType();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Decorator getDecorator() {
        return scanner.getDecorator();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Caller getCaller(final Context context) {
        return scanner.getCaller(context);
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Section getSection() {
        return scanner.getSection();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Version getRevision() {
        return scanner.getRevision();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Order getOrder() {
        return scanner.getOrder();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Label getVersion() {
        return scanner.getVersion();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public Label getText() {
        return scanner.getText();
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
        return scanner.isPrimitive();
    }

    @Override // org.simpleframework.xml.core.Scanner
    public boolean isEmpty() {
        return scanner.isEmpty();
    }

    @Override // org.simpleframework.xml.core.Scanner, org.simpleframework.xml.core.Policy
    public boolean isStrict() {
        return scanner.isStrict();
    }
}
