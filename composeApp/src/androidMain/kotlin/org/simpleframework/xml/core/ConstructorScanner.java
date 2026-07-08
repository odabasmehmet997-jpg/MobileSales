package org.simpleframework.xml.core;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

class ConstructorScanner {
    private Signature primary;
    private final Support support;
    private final List<Signature> signatures = new ArrayList();
    private final ParameterMap registry = new ParameterMap();
    public ConstructorScanner(final Detail detail, final Support support) throws Exception {
        this.support = support;
        this.scan(detail);
    }
    public Signature getSignature() {
        return primary;
    }
    public List<Signature> getSignatures() {
        return new ArrayList(signatures);
    }
    public ParameterMap getParameters() {
        return registry;
    }
    private void scan(final Detail detail) throws Exception {
        final Constructor[] constructors = detail.getConstructors();
        if (!detail.isInstantiable()) {
            throw new ConstructorException("Can not construct inner %s", detail);
        }
        for (final Constructor constructor : constructors) {
            if (!detail.isPrimitive()) {
                this.scan(constructor);
            }
        }
    }
    private void scan(final Constructor constructor) throws Exception {
        final SignatureScanner signatureScanner = new SignatureScanner(constructor, registry, support);
        if (signatureScanner.isValid()) {
            for (final Signature signature : signatureScanner.getSignatures()) {
                if (0 == signature.size()) {
                    primary = signature;
                }
                signatures.add(signature);
            }
        }
    }
}
