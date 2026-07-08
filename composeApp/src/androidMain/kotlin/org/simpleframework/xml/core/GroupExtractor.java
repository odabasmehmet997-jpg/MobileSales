package org.simpleframework.xml.core;

import org.simpleframework.xml.Text;
import org.simpleframework.xml.stream.Format;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.LinkedHashMap;


class GroupExtractor implements Group {
    private final LabelMap elements;
    private final ExtractorFactory factory;
    private final Annotation label;
    private final Registry registry;

    public GroupExtractor(final Contact contact, final Annotation annotation, final Format format) throws Exception {
        factory = new ExtractorFactory(contact, annotation, format);
        final LabelMap labelMap = new LabelMap();
        elements = labelMap;
        registry = new Registry(labelMap);
        label = annotation;
        this.extract();
    }

    public String[] getNames() throws Exception {
        return elements.getKeys();
    }

    public String[] getPaths() throws Exception {
        return elements.getPaths();
    }

    @Override // org.simpleframework.xml.core.Group
    public LabelMap getElements() throws Exception {
        return elements.getLabels();
    }

    @Override // org.simpleframework.xml.core.Group
    public Label getLabel(final Class cls) {
        return registry.resolve(cls);
    }

    @Override // org.simpleframework.xml.core.Group
    public Label getText() {
        return registry.resolveText();
    }

    public boolean isValid(final Class cls) {
        return null != this.registry.resolve(cls);
    }

    public boolean isDeclared(final Class cls) {
        return registry.containsKey(cls);
    }

    @Override // org.simpleframework.xml.core.Group
    public boolean isInline() {
        final Iterator<Label> it = registry.iterator();
        while (it.hasNext()) {
            if (!it.next().isInline()) {
                return false;
            }
        }
        return !registry.isEmpty();
    }

    @Override // org.simpleframework.xml.core.Group
    public boolean isTextList() {
        return registry.isText();
    }

    private void extract() throws Exception {
        final Extractor extractorFactory = factory.getInstance();
        if (null != extractorFactory) {
            this.extract(extractorFactory);
        }
    }

    private void extract(final Extractor extractor) throws Exception {
        for (final Annotation annotation : extractor.getAnnotations()) {
            this.extract(extractor, annotation);
        }
    }

    private void extract(final Extractor extractor, final Annotation annotation) throws Exception {
        final Label label = extractor.getLabel(annotation);
        final Class type = extractor.getType(annotation);
        final Registry registry = this.registry;
        if (null != registry) {
            registry.register(type, label);
        }
    }

    @Override // org.simpleframework.xml.core.Group
    public String toString() {
        return label.toString();
    }

    private static class Registry extends LinkedHashMap<Class, Label> implements Iterable<Label> {
        private final LabelMap elements;
        private Label text;

        public Registry(final LabelMap labelMap) {
            elements = labelMap;
        }

        public boolean isText() {
            return null != this.text;
        }

        @Override // java.lang.Iterable
        public Iterator<Label> iterator() {
            return this.values().iterator();
        }

        public Label resolveText() {
            return this.resolveText(String.class);
        }

        public Label resolve(final Class cls) {
            final Label resolveText = this.resolveText(cls);
            return null == resolveText ? this.resolveElement(cls) : resolveText;
        }

        private Label resolveText(final Class cls) {
            final Label label = text;
            if (null == label || String.class != cls) {
                return null;
            }
            return label;
        }

        private Label resolveElement(Class cls) {
            while (null != cls) {
                final Label label = this.get(cls);
                if (null != label) {
                    return label;
                }
                cls = cls.getSuperclass();
            }
            return null;
        }

        public void register(final Class cls, final Label label) throws Exception {
            final CacheLabel cacheLabel = new CacheLabel(label);
            this.registerElement(cls, cacheLabel);
            this.registerText(cacheLabel);
        }

        private void registerElement(final Class cls, final Label label) throws Exception {
            final String name = label.getName();
            if (!elements.containsKey(name)) {
                elements.put(name, label);
            }
            if (this.containsKey(cls)) {
                return;
            }
            this.put(cls, label);
        }

        private void registerText(final Label label) throws Exception {
            final Text text = label.getContact().getAnnotation(Text.class);
            if (null != text) {
                this.text = new TextListLabel(label, text);
            }
        }
    }
}
