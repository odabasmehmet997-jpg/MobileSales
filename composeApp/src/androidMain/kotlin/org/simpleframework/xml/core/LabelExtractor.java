package org.simpleframework.xml.core;

import org.simpleframework.xml.*;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


class LabelExtractor {
    private final Cache<LabelGroup> cache = new ConcurrentCache();
    private final Format format;

    public LabelExtractor(final Format format) {
        this.format = format;
    }

    public Label getLabel(final Contact contact, final Annotation annotation) throws Exception {
        final LabelGroup group = this.getGroup(contact, annotation, this.getKey(contact, annotation));
        if (null != group) {
            return group.getPrimary();
        }
        return null;
    }

    public List<Label> getList(final Contact contact, final Annotation annotation) throws Exception {
        final LabelGroup group = this.getGroup(contact, annotation, this.getKey(contact, annotation));
        if (null != group) {
            return group.getList();
        }
        return Collections.emptyList();
    }

    private LabelGroup getGroup(final Contact contact, final Annotation annotation, final Object obj) throws Exception {
        final LabelGroup fetch = cache.fetch(obj);
        if (null != fetch) {
            return fetch;
        }
        final LabelGroup labels = this.getLabels(contact, annotation);
        if (null != labels) {
            cache.cache(obj, labels);
        }
        return labels;
    }

    private LabelGroup getLabels(final Contact contact, final Annotation annotation) throws Exception {
        if (annotation instanceof ElementUnion) {
            return this.getUnion(contact, annotation);
        }
        if (annotation instanceof ElementListUnion) {
            return this.getUnion(contact, annotation);
        }
        if (annotation instanceof ElementMapUnion) {
            return this.getUnion(contact, annotation);
        }
        return this.getSingle(contact, annotation);
    }

    private LabelGroup getSingle(final Contact contact, final Annotation annotation) throws Exception {
        Label label = this.getLabel(contact, annotation, null);
        if (null != label) {
            label = new CacheLabel(label);
        }
        return new LabelGroup(label);
    }

    private LabelGroup getUnion(final Contact contact, final Annotation annotation) throws Exception {
        final Annotation[] annotations = this.getAnnotations(annotation);
        if (0 >= annotations.length) {
            return null;
        }
        final LinkedList linkedList = new LinkedList();
        for (final Annotation annotation2 : annotations) {
            Label label = this.getLabel(contact, annotation, annotation2);
            if (null != label) {
                label = new CacheLabel(label);
            }
            linkedList.add(label);
        }
        return new LabelGroup(linkedList);
    }

    private Annotation[] getAnnotations(final Annotation annotation) throws Exception {
        final Method[] declaredMethods = annotation.annotationType().getDeclaredMethods();
        if (0 < declaredMethods.length) {
            return (Annotation[]) declaredMethods[0].invoke(annotation, null);
        }
        return new Annotation[0];
    }

    private Label getLabel(final Contact contact, final Annotation annotation, final Annotation annotation2) throws Exception {
        final Constructor constructor = this.getConstructor(annotation);
        if (null != annotation2) {
            return (Label) constructor.newInstance(contact, annotation, annotation2, format);
        }
        return (Label) constructor.newInstance(contact, annotation, format);
    }

    private Object getKey(final Contact contact, final Annotation annotation) {
        return new LabelKey(contact, annotation);
    }

    private Constructor getConstructor(final Annotation annotation) throws Exception {
        final Constructor constructor = this.getBuilder(annotation).getConstructor();
        if (!constructor.isAccessible()) {
            constructor.setAccessible(true);
        }
        return constructor;
    }

    private LabelBuilder getBuilder(final Annotation annotation) throws Exception {
        if (annotation instanceof Element) {
            return new LabelBuilder(ElementLabel.class, Element.class);
        }
        if (annotation instanceof ElementList) {
            return new LabelBuilder(ElementListLabel.class, ElementList.class);
        }
        if (annotation instanceof ElementArray) {
            return new LabelBuilder(ElementArrayLabel.class, ElementArray.class);
        }
        if (annotation instanceof ElementMap) {
            return new LabelBuilder(ElementMapLabel.class, ElementMap.class);
        }
        if (annotation instanceof ElementUnion) {
            return new LabelBuilder(ElementUnionLabel.class, ElementUnion.class, Element.class);
        }
        if (annotation instanceof ElementListUnion) {
            return new LabelBuilder(ElementListUnionLabel.class, ElementListUnion.class, ElementList.class);
        }
        if (annotation instanceof ElementMapUnion) {
            return new LabelBuilder(ElementMapUnionLabel.class, ElementMapUnion.class, ElementMap.class);
        }
        if (annotation instanceof Attribute) {
            return new LabelBuilder(AttributeLabel.class, Attribute.class);
        }
        if (annotation instanceof Version) {
            return new LabelBuilder(VersionLabel.class, Version.class);
        }
        if (annotation instanceof Text) {
            return new LabelBuilder(TextLabel.class, Text.class);
        }
        throw new PersistenceException("Annotation %s not supported", annotation);
    }

    private record LabelBuilder(Class type, Class label, Class entry) {
            public LabelBuilder(final Class cls, final Class cls2) {
                this(cls, cls2, null);
            }

        public Constructor getConstructor() throws Exception {
                final Class cls = entry;
                if (null != cls) {
                    return this.getConstructor(label, cls);
                }
                return this.getConstructor(label);
            }

            private Constructor getConstructor(final Class cls) throws Exception {
                return type.getConstructor(Contact.class, cls, Format.class);
            }

            private Constructor getConstructor(final Class cls, final Class cls2) throws Exception {
                return type.getConstructor(Contact.class, cls, cls2, Format.class);
            }
        }
}
