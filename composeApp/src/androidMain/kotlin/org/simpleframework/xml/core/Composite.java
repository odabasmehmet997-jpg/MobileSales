package org.simpleframework.xml.core;

import org.simpleframework.xml.Version;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.*;

import java.util.Iterator;


class Composite implements Converter {
    private final Context context;
    private final Criteria criteria;
    private final ObjectFactory factory;
    private final Primitive primitive;
    private final Revision revision;
    private final Type type;

    public Composite(final Context context, final Type type) {
        this(context, type, null);
    }

    public Composite(final Context context, final Type type, final Class cls) {
        factory = new ObjectFactory(context, type, cls);
        primitive = new Primitive(context, type);
        criteria = new Collector();
        revision = new Revision();
        this.context = context;
        this.type = type;
    }

    @Override // org.simpleframework.xml.core.Converter
    public Object read(final InputNode inputNode) throws Exception {
        final Instance objectFactory = factory.getInstance(inputNode);
        final Class type = objectFactory.getType();
        if (objectFactory.isReference()) {
            return objectFactory.getInstance();
        }
        if (context.isPrimitive(type)) {
            return this.readPrimitive(inputNode, objectFactory);
        }
        return this.read(inputNode, objectFactory, type);
    }

    @Override // org.simpleframework.xml.core.Converter
    public Object read(final InputNode inputNode, final Object obj) throws Exception {
        final Schema schema = context.getSchema(obj.getClass());
        final Caller caller = schema.getCaller();
        this.read(inputNode, obj, schema);
        criteria.commit(obj);
        caller.validate(obj);
        caller.commit(obj);
        return this.readResolve(inputNode, obj, caller);
    }

    private Object read(final InputNode inputNode, final Instance instance, final Class cls) throws Exception {
        final Schema schema = context.getSchema(cls);
        final Caller caller = schema.getCaller();
        final Object read = this.read(schema, instance).read(inputNode);
        caller.validate(read);
        caller.commit(read);
        instance.setInstance(read);
        return this.readResolve(inputNode, read, caller);
    }

    private Builder read(final Schema schema, final Instance instance) throws Exception {
        if (schema.getInstantiator().isDefault()) {
            return new Builder(this, criteria, schema, instance);
        }
        return new Injector(this, criteria, schema, instance);
    }

    private Object readPrimitive(final InputNode inputNode, final Instance instance) throws Exception {
        final Class type = instance.getType();
        final Object read = primitive.read(inputNode, type);
        if (null != type) {
            instance.setInstance(read);
        }
        return read;
    }

    private Object readResolve(final InputNode inputNode, final Object obj, final Caller caller) throws Exception {
        if (null == obj) {
            return obj;
        }
        final Position position = inputNode.getPosition();
        final Object resolve = caller.resolve(obj);
        final Class type = this.type.type();
        final Class<?> cls = resolve.getClass();
        if (type.isAssignableFrom(cls)) {
            return resolve;
        }
        throw new ElementException("Type %s does not match %s at %s", cls, type, position);
    }

    private void read(final InputNode inputNode, final Object obj, final Schema schema) throws Exception {
        final Section section = schema.getSection();
        this.readVersion(inputNode, obj, schema);
        this.readSection(inputNode, obj, section);
    }

    private void readSection(final InputNode inputNode, final Object obj, final Section section) throws Exception {
        this.readText(inputNode, obj, section);
        this.readAttributes(inputNode, obj, section);
        this.readElements(inputNode, obj, section);
    }

    public void readVersion(final InputNode inputNode, final Object obj, final Schema schema) throws Exception {
        final Label version = schema.getVersion();
        final Class type = this.type.type();
        if (null != version) {
            final InputNode remove = inputNode.getAttributes().remove(version.getName());
            if (null != remove) {
                this.readVersion(remove, obj, version);
                return;
            }
            final Version version2 = context.getVersion(type);
            final Double valueOf = Double.valueOf(revision.getDefault());
            final Double valueOf2 = Double.valueOf(version2.revision());
            criteria.set(version, valueOf);
            revision.compare(valueOf2, valueOf);
        }
    }

    private void readVersion(final InputNode inputNode, final Object obj, final Label label) throws Exception {
        final Object readInstance = this.readInstance(inputNode, obj, label);
        final Class type = this.type.type();
        if (null != readInstance) {
            final Double valueOf = Double.valueOf(context.getVersion(type).revision());
            if (readInstance.equals(revision)) {
                return;
            }
            revision.compare(valueOf, readInstance);
        }
    }

    public void readAttributes(final InputNode inputNode, final Object obj, final Section section) throws Exception {
        final NodeMap<InputNode> attributes = inputNode.getAttributes();
        final LabelMap attributes2 = section.getAttributes();
        final Iterator<String> it = attributes.iterator();
        while (it.hasNext()) {
            final InputNode attribute = inputNode.getAttribute(it.next());
            if (null != attribute) {
                this.readAttribute(attribute, obj, section, attributes2);
            }
        }
        this.validate(inputNode, attributes2, obj);
    }

    public void readElements(final InputNode inputNode, final Object obj, final Section section) throws Exception {
        final LabelMap elements = section.getElements();
        InputNode next = inputNode.getNext();
        while (null != next) {
            final Section section2 = section.getSection(next.getName());
            if (null != section2) {
                this.readSection(next, obj, section2);
            } else {
                this.readElement(next, obj, section, elements);
            }
            next = inputNode.getNext();
        }
        this.validate(inputNode, elements, obj);
    }

    public void readText(final InputNode inputNode, final Object obj, final Section section) throws Exception {
        final Label text = section.getText();
        if (null != text) {
            this.readInstance(inputNode, obj, text);
        }
    }

    private void readAttribute(final InputNode inputNode, final Object obj, final Section section, final LabelMap labelMap) throws Exception {
        final String attribute = section.getAttribute(inputNode.getName());
        final Label label = labelMap.getLabel(attribute);
        if (null == label) {
            final Position position = inputNode.getPosition();
            final Class type = context.getType(this.type, obj);
            if (labelMap.isStrict(context) && revision.isEqual()) {
                throw new AttributeException("Attribute '%s' does not have a match in %s at %s", attribute, type, position);
            }
            return;
        }
        this.readInstance(inputNode, obj, label);
    }

    private void readElement(final InputNode inputNode, final Object obj, final Section section, final LabelMap labelMap) throws Exception {
        final String path = section.getPath(inputNode.getName());
        Label label = labelMap.getLabel(path);
        if (null == label) {
            label = criteria.resolve(path);
        }
        if (null == label) {
            final Position position = inputNode.getPosition();
            final Class type = context.getType(this.type, obj);
            if (labelMap.isStrict(context) && revision.isEqual()) {
                throw new ElementException("Element '%s' does not have a match in %s at %s", path, type, position);
            }
            inputNode.skip();
            return;
        }
        this.readUnion(inputNode, obj, labelMap, label);
    }

    private void readUnion(final InputNode inputNode, final Object obj, final LabelMap labelMap, final Label label) throws Exception {
        final Object readInstance = this.readInstance(inputNode, obj, label);
        for (final String str : label.getPaths()) {
            labelMap.getLabel(str);
        }
        if (label.isInline()) {
            criteria.set(label, readInstance);
        }
    }

    private Object readInstance(final InputNode inputNode, final Object obj, final Label label) throws Exception {
        final Object readVariable = this.readVariable(inputNode, obj, label);
        if (null == readVariable) {
            final Position position = inputNode.getPosition();
            final Class type = context.getType(this.type, obj);
            if (label.isRequired() && revision.isEqual()) {
                throw new ValueRequiredException("Empty value for %s in %s at %s", label, type, position);
            }
        } else if (readVariable != label.getEmpty(context)) {
            criteria.set(label, readVariable);
        }
        return readVariable;
    }

    private Object readVariable(final InputNode inputNode, final Object obj, final Label label) throws Exception {
        final Object obj2;
        final Converter converter = label.getConverter(context);
        if (label.isCollection()) {
            final Variable variable = criteria.get(label);
            final Contact contact = label.getContact();
            if (null != variable) {
                return converter.read(inputNode, variable.getValue());
            }
            if (null != obj && null != (obj2 = contact.get(obj))) {
                return converter.read(inputNode, obj2);
            }
        }
        return converter.read(inputNode);
    }

    private void validate(final InputNode inputNode, final LabelMap labelMap, final Object obj) throws Exception {
        final Class type = context.getType(this.type, obj);
        final Position position = inputNode.getPosition();
        final Iterator<Label> it = labelMap.iterator();
        while (it.hasNext()) {
            final Label next = it.next();
            if (next.isRequired() && revision.isEqual()) {
                throw new ValueRequiredException("Unable to satisfy %s for %s at %s", next, type, position);
            }
            final Object empty = next.getEmpty(context);
            if (null != empty) {
                criteria.set(next, empty);
            }
        }
    }

    @Override // org.simpleframework.xml.core.Converter
    public boolean validate(final InputNode inputNode) throws Exception {
        final Instance objectFactory = factory.getInstance(inputNode);
        if (objectFactory.isReference()) {
            return true;
        }
        objectFactory.setInstance(null);
        return this.validate(inputNode, objectFactory.getType());
    }

    private boolean validate(final InputNode inputNode, final Class cls) throws Exception {
        final Schema schema = context.getSchema(cls);
        final Section section = schema.getSection();
        this.validateText(inputNode, schema);
        this.validateSection(inputNode, section);
        return inputNode.isElement();
    }

    private void validateSection(final InputNode inputNode, final Section section) throws Exception {
        this.validateAttributes(inputNode, section);
        this.validateElements(inputNode, section);
    }

    private void validateAttributes(final InputNode inputNode, final Section section) throws Exception {
        final NodeMap<InputNode> attributes = inputNode.getAttributes();
        final LabelMap attributes2 = section.getAttributes();
        final Iterator<String> it = attributes.iterator();
        while (it.hasNext()) {
            final InputNode attribute = inputNode.getAttribute(it.next());
            if (null != attribute) {
                this.validateAttribute(attribute, section, attributes2);
            }
        }
        this.validate(inputNode, attributes2);
    }

    private void validateElements(final InputNode inputNode, final Section section) throws Exception {
        final LabelMap elements = section.getElements();
        InputNode next = inputNode.getNext();
        while (null != next) {
            final Section section2 = section.getSection(next.getName());
            if (null != section2) {
                this.validateSection(next, section2);
            } else {
                this.validateElement(next, section, elements);
            }
            next = inputNode.getNext();
        }
        this.validate(inputNode, elements);
    }

    private void validateText(final InputNode inputNode, final Schema schema) throws Exception {
        final Label text = schema.getText();
        if (null != text) {
            this.validate(inputNode, text);
        }
    }

    private void validateAttribute(final InputNode inputNode, final Section section, final LabelMap labelMap) throws Exception {
        final Position position = inputNode.getPosition();
        final String attribute = section.getAttribute(inputNode.getName());
        final Label label = labelMap.getLabel(attribute);
        if (null == label) {
            final Class type = this.type.type();
            if (labelMap.isStrict(context) && revision.isEqual()) {
                throw new AttributeException("Attribute '%s' does not exist for %s at %s", attribute, type, position);
            }
            return;
        }
        this.validate(inputNode, label);
    }

    private void validateElement(final InputNode inputNode, final Section section, final LabelMap labelMap) throws Exception {
        final String path = section.getPath(inputNode.getName());
        Label label = labelMap.getLabel(path);
        if (null == label) {
            label = criteria.resolve(path);
        }
        if (null == label) {
            final Position position = inputNode.getPosition();
            final Class type = this.type.type();
            if (labelMap.isStrict(context) && revision.isEqual()) {
                throw new ElementException("Element '%s' does not exist for %s at %s", path, type, position);
            }
            inputNode.skip();
            return;
        }
        this.validateUnion(inputNode, labelMap, label);
    }

    private void validateUnion(final InputNode inputNode, final LabelMap labelMap, final Label label) throws Exception {
        for (final String str : label.getPaths()) {
            labelMap.getLabel(str);
        }
        if (label.isInline()) {
            criteria.set(label, null);
        }
        this.validate(inputNode, label);
    }

    private void validate(final InputNode inputNode, final Label label) throws Exception {
        final Converter converter = label.getConverter(context);
        final Position position = inputNode.getPosition();
        final Class type = this.type.type();
        if (!converter.validate(inputNode)) {
            throw new PersistenceException("Invalid value for %s in %s at %s", label, type, position);
        }
        criteria.set(label, null);
    }

    private void validate(final InputNode inputNode, final LabelMap labelMap) throws Exception {
        final Position position = inputNode.getPosition();
        final Iterator<Label> it = labelMap.iterator();
        while (it.hasNext()) {
            final Label next = it.next();
            final Class type = this.type.type();
            if (next.isRequired() && revision.isEqual()) {
                throw new ValueRequiredException("Unable to satisfy %s for %s at %s", next, type, position);
            }
        }
    }

    @Override // org.simpleframework.xml.core.Converter
    public void write(final OutputNode outputNode, final Object obj) throws Exception {
        final Schema schema = context.getSchema(obj.getClass());
        final Caller caller = schema.getCaller();
        try {
            if (schema.isPrimitive()) {
                primitive.write(outputNode, obj);
            } else {
                caller.persist(obj);
                this.write(outputNode, obj, schema);
            }
            caller.complete(obj);
        } catch (final Throwable th) {
            caller.complete(obj);
            throw th;
        }
    }

    private void write(final OutputNode outputNode, final Object obj, final Schema schema) throws Exception {
        final Section section = schema.getSection();
        this.writeVersion(outputNode, obj, schema);
        this.writeSection(outputNode, obj, section);
    }

    private void writeSection(final OutputNode outputNode, final Object obj, final Section section) throws Exception {
        final NamespaceMap namespaces = outputNode.getNamespaces();
        final String prefix = section.getPrefix();
        if (null != prefix) {
            final String reference = namespaces.getReference(prefix);
            if (null == reference) {
                throw new ElementException("Namespace prefix '%s' in %s is not in scope", prefix, type);
            }
            outputNode.setReference(reference);
        }
        this.writeAttributes(outputNode, obj, section);
        this.writeElements(outputNode, obj, section);
        this.writeText(outputNode, obj, section);
    }

    private void writeVersion(final OutputNode outputNode, final Object obj, final Schema schema) throws Exception {
        final Version revision = schema.getRevision();
        final Label version = schema.getVersion();
        if (null != revision) {
            final Double valueOf = Double.valueOf(this.revision.getDefault());
            final Double valueOf2 = Double.valueOf(revision.revision());
            if (this.revision.compare(valueOf2, valueOf)) {
                if (version.isRequired()) {
                    this.writeAttribute(outputNode, valueOf2, version);
                    return;
                }
                return;
            }
            this.writeAttribute(outputNode, valueOf2, version);
        }
    }

    private void writeAttributes(final OutputNode outputNode, final Object obj, final Section section) throws Exception {
        final Iterator<Label> it = section.getAttributes().iterator();
        while (it.hasNext()) {
            final Label next = it.next();
            Object obj2 = next.getContact().get(obj);
            final Class type = context.getType(this.type, obj);
            if (null == obj2) {
                obj2 = next.getEmpty(context);
            }
            if (null == obj2 && next.isRequired()) {
                throw new AttributeException("Value for %s is null in %s", next, type);
            }
            this.writeAttribute(outputNode, obj2, next);
        }
    }

    private void writeElements(final OutputNode outputNode, final Object obj, final Section section) throws Exception {
        for (final String str : section) {
            final Section section2 = section.getSection(str);
            if (null != section2) {
                this.writeSection(outputNode.getChild(str), obj, section2);
            } else {
                final Label element = section.getElement(section.getPath(str));
                final Class type = context.getType(this.type, obj);
                if (null != this.criteria.get(element)) {
                    continue;
                } else {
                    if (null == element) {
                        throw new ElementException("Element '%s' not defined in %s", str, type);
                    }
                    this.writeUnion(outputNode, obj, section, element);
                }
            }
        }
    }

    private void writeUnion(final OutputNode outputNode, final Object obj, final Section section, final Label label) throws Exception {
        final Object obj2 = label.getContact().get(obj);
        final Class type = context.getType(this.type, obj);
        if (null == obj2 && label.isRequired()) {
            throw new ElementException("Value for %s is null in %s", label, type);
        }
        final Object writeReplace = this.writeReplace(obj2);
        if (null != writeReplace) {
            this.writeElement(outputNode, writeReplace, label);
        }
        criteria.set(label, writeReplace);
    }

    private Object writeReplace(final Object obj) throws Exception {
        if (null == obj) {
            return obj;
        }
        return context.getCaller(obj.getClass()).replace(obj);
    }

    private void writeText(final OutputNode outputNode, final Object obj, final Section section) throws Exception {
        final Label text = section.getText();
        if (null != text) {
            Object obj2 = text.getContact().get(obj);
            final Class type = context.getType(this.type, obj);
            if (null == obj2) {
                obj2 = text.getEmpty(context);
            }
            if (null == obj2 && text.isRequired()) {
                throw new TextException("Value for %s is null in %s", text, type);
            }
            this.writeText(outputNode, obj2, text);
        }
    }

    private void writeAttribute(final OutputNode outputNode, final Object obj, final Label label) throws Exception {
        if (null != obj) {
            label.getDecorator().decorate(outputNode.setAttribute(label.getName(), factory.getText(obj)));
        }
    }

    private void writeElement(final OutputNode outputNode, final Object obj, final Label label) throws Exception {
        if (null != obj) {
            final Class<?> cls = obj.getClass();
            final Label label2 = label.getLabel(cls);
            final String name = label2.getName();
            final Type type = label.getType(cls);
            final OutputNode child = outputNode.getChild(name);
            if (!label2.isInline()) {
                this.writeNamespaces(child, type, label2);
            }
            if (label2.isInline() || !this.isOverridden(child, obj, type)) {
                final Converter converter = label2.getConverter(context);
                child.setData(label2.isData());
                this.writeElement(child, obj, converter);
            }
        }
    }

    private void writeElement(final OutputNode outputNode, final Object obj, final Converter converter) throws Exception {
        converter.write(outputNode, obj);
    }

    private void writeNamespaces(final OutputNode outputNode, final Type type, final Label label) throws Exception {
        label.getDecorator().decorate(outputNode, context.getDecorator(type.type()));
    }

    private void writeText(final OutputNode outputNode, final Object obj, final Label label) throws Exception {
        if (null == obj || label.isTextList()) {
            return;
        }
        final String text = factory.getText(obj);
        outputNode.setData(label.isData());
        outputNode.setValue(text);
    }

    private boolean isOverridden(final OutputNode outputNode, final Object obj, final Type type) throws Exception {
        return factory.setOverride(type, obj, outputNode);
    }

    private static class Builder {
        protected final Composite composite;
        protected final Criteria criteria;
        protected final Schema schema;
        protected final Instance value;

        public Builder(final Composite composite, final Criteria criteria, final Schema schema, final Instance instance) {
            this.composite = composite;
            this.criteria = criteria;
            this.schema = schema;
            value = instance;
        }

        public Object read(final InputNode inputNode) throws Exception {
            final Object instance = value.getInstance();
            final Section section = schema.getSection();
            value.setInstance(instance);
            composite.readVersion(inputNode, instance, schema);
            composite.readText(inputNode, instance, section);
            composite.readAttributes(inputNode, instance, section);
            composite.readElements(inputNode, instance, section);
            criteria.commit(instance);
            return instance;
        }
    }

    private class Injector extends Builder {
        Injector(final Composite composite, final Composite composite2, final Criteria criteria, final Schema schema, final Instance instance, final C31441 c31441) {
            this(composite2, criteria, schema, instance);
        }

        private Injector(final Composite composite, final Criteria criteria, final Schema schema, final Instance instance) {
            super(composite, criteria, schema, instance);
        }

        @Override // org.simpleframework.xml.core.Composite.Builder
        public Object read(final InputNode inputNode) throws Exception {
            final Section section = schema.getSection();
            composite.readVersion(inputNode, (Object) null, schema);
            composite.readText(inputNode, null, section);
            composite.readAttributes(inputNode, null, section);
            composite.readElements(inputNode, null, section);
            return this.readInject(inputNode);
        }

        private Object readInject(final InputNode inputNode) throws Exception {
            final Object instantiator = schema.getInstantiator().getInstance(criteria);
            value.setInstance(instantiator);
            criteria.commit(instantiator);
            return instantiator;
        }
    }
}
