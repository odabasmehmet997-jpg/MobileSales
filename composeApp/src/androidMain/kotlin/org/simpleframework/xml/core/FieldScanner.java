package org.simpleframework.xml.core;

import org.simpleframework.xml.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.List;


class FieldScanner extends ContactList {
    private final ContactMap done = new ContactMap();
    private final AnnotationFactory factory;
    private final Support support;

    public FieldScanner(final Detail detail, final Support support) throws Exception {
        factory = new AnnotationFactory(detail, support);
        this.support = support;
        this.scan(detail);
    }

    private void scan(final Detail detail) throws Exception {
        final DefaultType override = detail.getOverride();
        final DefaultType access = detail.getAccess();
        final Class cls = detail.getSuper();
        if (null != cls) {
            this.extend(cls, override);
        }
        this.extract(detail, access);
        this.extract(detail);
        this.build();
    }

    private void extend(final Class cls, final DefaultType defaultType) throws Exception {
        final ContactList fields = support.getFields(cls, defaultType);
        if (null != fields) {
            this.addAll(fields);
        }
    }

    private void extract(final Detail detail) {
        for (final FieldDetail fieldDetail : detail.getFields()) {
            final Annotation[] annotations = fieldDetail.getAnnotations();
            final Field field = fieldDetail.getField();
            for (final Annotation annotation : annotations) {
                this.scan(field, annotation, annotations);
            }
        }
    }

    private void extract(final Detail detail, final DefaultType defaultType) throws Exception {
        final List<FieldDetail> fields = detail.getFields();
        if (DefaultType.FIELD == defaultType) {
            for (final FieldDetail fieldDetail : fields) {
                final Annotation[] annotations = fieldDetail.getAnnotations();
                final Field field = fieldDetail.getField();
                final Class<?> type = field.getType();
                if (!this.isStatic(field) && !this.isTransient(field)) {
                    this.process(field, type, annotations);
                }
            }
        }
    }

    private void scan(final Field field, final Annotation annotation, final Annotation[] annotationArr) {
        if (annotation instanceof Attribute) {
            this.process(field, annotation, annotationArr);
        }
        if (annotation instanceof ElementUnion) {
            this.process(field, annotation, annotationArr);
        }
        if (annotation instanceof ElementListUnion) {
            this.process(field, annotation, annotationArr);
        }
        if (annotation instanceof ElementMapUnion) {
            this.process(field, annotation, annotationArr);
        }
        if (annotation instanceof ElementList) {
            this.process(field, annotation, annotationArr);
        }
        if (annotation instanceof ElementArray) {
            this.process(field, annotation, annotationArr);
        }
        if (annotation instanceof ElementMap) {
            this.process(field, annotation, annotationArr);
        }
        if (annotation instanceof Element) {
            this.process(field, annotation, annotationArr);
        }
        if (annotation instanceof Version) {
            this.process(field, annotation, annotationArr);
        }
        if (annotation instanceof Text) {
            this.process(field, annotation, annotationArr);
        }
        if (annotation instanceof Transient) {
            this.remove(field, annotation);
        }
    }

    private void process(final Field field, final Class cls, final Annotation[] annotationArr) throws Exception {
        final Annotation annotationFactory = factory.getInstance(cls, Reflector.getDependents(field));
        if (null != annotationFactory) {
            this.process(field, annotationFactory, annotationArr);
        }
    }

    private void process(final Field field, final Annotation annotation, final Annotation[] annotationArr) {
        final FieldContact fieldContact = new FieldContact(field, annotation, annotationArr);
        final FieldKey fieldKey = new FieldKey(field);
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        this.insert(fieldKey, fieldContact);
    }

    private void insert(final Object obj, Contact contact) {
        final Contact remove = done.remove(obj);
        if (null != remove && this.isText(contact)) {
            contact = remove;
        }
        done.put(obj, contact);
    }

    private boolean isText(final Contact contact) {
        return contact.getAnnotation() instanceof Text;
    }

    private void remove(final Field field, final Annotation annotation) {
        done.remove(new FieldKey(field));
    }

    private void build() {
        final Iterator<Contact> it = done.iterator();
        while (it.hasNext()) {
            this.add(it.next());
        }
    }

    private boolean isStatic(final Field field) {
        return Modifier.isStatic(field.getModifiers());
    }

    private boolean isTransient(final Field field) {
        return Modifier.isTransient(field.getModifiers());
    }

    private static class FieldKey {
        private final String name;
        private final Class type;

        public FieldKey(final Field field) {
            type = field.getDeclaringClass();
            name = field.getName();
        }

        public int hashCode() {
            return name.hashCode();
        }

        public boolean equals(final Object obj) {
            if (obj instanceof FieldKey) {
                return this.equals((FieldKey) obj);
            }
            return false;
        }

        private boolean equals(final FieldKey fieldKey) {
            if (fieldKey.type != type) {
                return false;
            }
            return fieldKey.name.equals(name);
        }
    }
}
