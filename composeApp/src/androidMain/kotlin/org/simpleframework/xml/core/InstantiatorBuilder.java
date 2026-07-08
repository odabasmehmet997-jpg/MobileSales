package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


class InstantiatorBuilder {
    private final Detail detail;
    private Instantiator factory;
    private final Scanner scanner;
    private final List<Creator> options = new ArrayList();
    private final Comparer comparer = new Comparer();
    private final LabelMap attributes = new LabelMap();
    private final LabelMap elements = new LabelMap();
    private final LabelMap texts = new LabelMap();

    public InstantiatorBuilder(final Scanner scanner, final Detail detail) {
        this.scanner = scanner;
        this.detail = detail;
    }

    public Instantiator build() throws Exception {
        if (null == this.factory) {
            this.populate(detail);
            this.build(detail);
            this.validate(detail);
        }
        return factory;
    }

    private Instantiator build(final Detail detail) throws Exception {
        if (null == this.factory) {
            factory = this.create(detail);
        }
        return factory;
    }

    private Instantiator create(final Detail detail) throws Exception {
        final Signature signature = scanner.getSignature();
        return new ClassInstantiator(options, null != signature ? new SignatureCreator(signature) : null, scanner.getParameters(), detail);
    }

    private Creator create(final Signature signature) {
        final SignatureCreator signatureCreator = new SignatureCreator(signature);
        if (null != signature) {
            options.add(signatureCreator);
        }
        return signatureCreator;
    }

    private Parameter create(final Parameter parameter) throws Exception {
        final Label resolve = this.resolve(parameter);
        if (null != resolve) {
            return new CacheParameter(parameter, resolve);
        }
        return null;
    }

    private void populate(final Detail detail) throws Exception {
        final Iterator<Signature> it = scanner.getSignatures().iterator();
        while (it.hasNext()) {
            this.populate(it.next());
        }
    }

    private void populate(final Signature signature) throws Exception {
        final Signature signature2 = new Signature(signature);
        final Iterator<Parameter> it = signature.iterator();
        while (it.hasNext()) {
            final Parameter create = this.create(it.next());
            if (null != create) {
                signature2.add(create);
            }
        }
        this.create(signature2);
    }

    private void validate(final Detail detail) throws Exception {
        for (final Parameter parameter : scanner.getParameters().getAll()) {
            final Label resolve = this.resolve(parameter);
            final String path = parameter.getPath();
            if (null == resolve) {
                throw new ConstructorException("Parameter '%s' does not have a match in %s", path, detail);
            }
            this.validateParameter(resolve, parameter);
        }
        this.validateConstructors();
    }

    private void validateParameter(final Label label, final Parameter parameter) throws Exception {
        final Contact contact = label.getContact();
        final String name = parameter.getName();
        if (!Support.isAssignable(parameter.getType(), contact.type())) {
            throw new ConstructorException("Type is not compatible with %s for '%s' in %s", label, name, parameter);
        }
        this.validateNames(label, parameter);
        this.validateAnnotations(label, parameter);
    }

    private void validateNames(final Label label, final Parameter parameter) throws Exception {
        final String name;
        final String[] names = label.getNames();
        final String name2 = parameter.getName();
        if (this.contains(names, name2) || name2 == (name = label.getName())) {
            return;
        }
        if (null == name2 || null == name) {
            throw new ConstructorException("Annotation does not match %s for '%s' in %s", label, name2, parameter);
        }
        if (!name2.equals(name)) {
            throw new ConstructorException("Annotation does not match %s for '%s' in %s", label, name2, parameter);
        }
    }

    private void validateAnnotations(final Label label, final Parameter parameter) throws Exception {
        final Annotation annotation = label.getAnnotation();
        final Annotation annotation2 = parameter.getAnnotation();
        final String name = parameter.getName();
        if (comparer.equals(annotation, annotation2)) {
            return;
        }
        final Class<? extends Annotation> annotationType = annotation.annotationType();
        final Class<? extends Annotation> annotationType2 = annotation2.annotationType();
        if (!annotationType.equals(annotationType2)) {
            throw new ConstructorException("Annotation %s does not match %s for '%s' in %s", annotationType2, annotationType, name, parameter);
        }
    }

    private void validateConstructors() throws Exception {
        final List<Creator> creators = factory.getCreators();
        if (factory.isDefault()) {
            this.validateConstructors(elements);
            this.validateConstructors(attributes);
        }
        if (creators.isEmpty()) {
            return;
        }
        this.validateConstructors(elements, creators);
        this.validateConstructors(attributes, creators);
    }

    private void validateConstructors(final LabelMap labelMap) throws Exception {
        final Iterator<Label> it = labelMap.iterator();
        while (it.hasNext()) {
            final Label next = it.next();
            if (null != next && next.getContact().isReadOnly()) {
                throw new ConstructorException("Default constructor can not accept read only %s in %s", next, detail);
            }
        }
    }

    private void validateConstructors(final LabelMap labelMap, final List<Creator> list) throws Exception {
        final Iterator<Label> it = labelMap.iterator();
        while (it.hasNext()) {
            final Label next = it.next();
            if (null != next) {
                this.validateConstructor(next, list);
            }
        }
        if (list.isEmpty()) {
            throw new ConstructorException("No constructor accepts all read only values in %s", detail);
        }
    }

    private void validateConstructor(final Label label, final List<Creator> list) throws Exception {
        final Iterator<Creator> it = list.iterator();
        while (it.hasNext()) {
            final Signature signature = it.next().getSignature();
            final Contact contact = label.getContact();
            final Object key = label.getKey();
            if (contact.isReadOnly() && null == signature.get(key)) {
                it.remove();
            }
        }
    }

    public void register(final Label label) throws Exception {
        if (label.isAttribute()) {
            this.register(label, attributes);
        } else if (label.isText()) {
            this.register(label, texts);
        } else {
            this.register(label, elements);
        }
    }

    private void register(final Label label, final LabelMap labelMap) throws Exception {
        final String name = label.getName();
        final String path = label.getPath();
        if (labelMap.containsKey(name)) {
            if (!labelMap.get(name).getPath().equals(name)) {
                labelMap.remove(name);
            }
        } else {
            labelMap.put(name, label);
        }
        labelMap.put(path, label);
    }

    private Label resolve(final Parameter parameter) throws Exception {
        if (parameter.isAttribute()) {
            return this.resolve(parameter, attributes);
        }
        if (parameter.isText()) {
            return this.resolve(parameter, texts);
        }
        return this.resolve(parameter, elements);
    }

    private Label resolve(final Parameter parameter, final LabelMap labelMap) throws Exception {
        final String name = parameter.getName();
        final Label label = labelMap.get(parameter.getPath());
        return null == label ? labelMap.get(name) : label;
    }

    private boolean contains(final String[] strArr, final String str) throws Exception {
        for (final String str2 : strArr) {
            if (str2 == str || str2.equals(str)) {
                return true;
            }
        }
        return false;
    }
}
