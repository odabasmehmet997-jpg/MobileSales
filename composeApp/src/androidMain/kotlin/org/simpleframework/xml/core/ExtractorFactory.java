package org.simpleframework.xml.core;

import org.simpleframework.xml.*;
import org.simpleframework.xml.stream.Format;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;


class ExtractorFactory {
    private final Contact contact;
    private final Format format;
    private final Annotation label;

    public ExtractorFactory(final Contact contact, final Annotation annotation, final Format format) {
        this.contact = contact;
        this.format = format;
        label = annotation;
    }

    public Extractor getInstance() throws Exception {
        return (Extractor) this.getInstance(label);
    }

    private Object getInstance(final Annotation annotation) throws Exception {
        final Constructor constructor = this.getBuilder(annotation).getConstructor();
        if (!constructor.isAccessible()) {
            constructor.setAccessible(true);
        }
        return constructor.newInstance(contact, annotation, format);
    }

    private ExtractorBuilder getBuilder(final Annotation annotation) throws Exception {
        if (annotation instanceof ElementUnion) {
            return new ExtractorBuilder(ElementUnion.class, ElementExtractor.class);
        }
        if (annotation instanceof ElementListUnion) {
            return new ExtractorBuilder(ElementListUnion.class, ElementListExtractor.class);
        }
        if (annotation instanceof ElementMapUnion) {
            return new ExtractorBuilder(ElementMapUnion.class, ElementMapExtractor.class);
        }
        throw new PersistenceException("Annotation %s is not a union", annotation);
    }

    private record ExtractorBuilder(Class label, Class type) {

        
            public Constructor getConstructor() throws Exception {
                return type.getConstructor(Contact.class, label, Format.class);
            }
        }

    private static class ElementExtractor implements Extractor<Element> {
        private final Contact contact;
        private final Format format;
        private final ElementUnion union;

        public ElementExtractor(final Contact contact, final ElementUnion elementUnion, final Format format) throws Exception {
            this.contact = contact;
            this.format = format;
            union = elementUnion;
        }

        @Override // org.simpleframework.xml.core.Extractor
        public Element[] getAnnotations() {
            return union.value();
        }

        @Override // org.simpleframework.xml.core.Extractor
        public Label getLabel(final Element element) {
            return new ElementLabel(contact, element, format);
        }

        @Override // org.simpleframework.xml.core.Extractor
        public Class getType(final Element element) {
            final Class type = element.type();
            return type == Void.TYPE ? contact.type() : type;
        }
    }

    private static class ElementListExtractor implements Extractor<ElementList> {
        private final Contact contact;
        private final Format format;
        private final ElementListUnion union;

        public ElementListExtractor(final Contact contact, final ElementListUnion elementListUnion, final Format format) throws Exception {
            this.contact = contact;
            this.format = format;
            union = elementListUnion;
        }

        @Override // org.simpleframework.xml.core.Extractor
        public ElementList[] getAnnotations() {
            return union.value();
        }

        @Override // org.simpleframework.xml.core.Extractor
        public Label getLabel(final ElementList elementList) {
            return new ElementListLabel(contact, elementList, format);
        }

        @Override // org.simpleframework.xml.core.Extractor
        public Class getType(final ElementList elementList) {
            return elementList.type();
        }
    }

    private static class ElementMapExtractor implements Extractor<ElementMap> {
        private final Contact contact;
        private final Format format;
        private final ElementMapUnion union;

        public ElementMapExtractor(final Contact contact, final ElementMapUnion elementMapUnion, final Format format) throws Exception {
            this.contact = contact;
            this.format = format;
            union = elementMapUnion;
        }

        @Override // org.simpleframework.xml.core.Extractor
        public ElementMap[] getAnnotations() {
            return union.value();
        }

        @Override // org.simpleframework.xml.core.Extractor
        public Label getLabel(final ElementMap elementMap) {
            return new ElementMapLabel(contact, elementMap, format);
        }

        @Override // org.simpleframework.xml.core.Extractor
        public Class getType(final ElementMap elementMap) {
            return elementMap.valueType();
        }
    }
}
