package org.simpleframework.xml.core;

import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.Format;

import java.lang.annotation.Annotation;


class Introspector {
    private final Contact contact;
    private final Format format;
    private final Label label;
    private final Annotation marker;

    public Introspector(final Contact contact, final Label label, final Format format) {
        marker = contact.getAnnotation();
        this.contact = contact;
        this.format = format;
        this.label = label;
    }

    public Contact getContact() {
        return contact;
    }

    public Type getDependent() throws Exception {
        return label.getDependent();
    }

    public String getEntry() throws Exception {
        Class<?> type = this.getDependent().type();
        if (type.isArray()) {
            type = type.getComponentType();
        }
        return this.getName(type);
    }

    private String getName(final Class cls) throws Exception {
        final String root = this.getRoot(cls);
        return null != root ? root : Reflector.getName(cls.getSimpleName());
    }

    private String getRoot(final Class cls) {
        for (Class cls2 = cls; null != cls2; cls2 = cls2.getSuperclass()) {
            final String root = this.getRoot(cls, cls2);
            if (null != root) {
                return root;
            }
        }
        return null;
    }

    private String getRoot(final Class<?> cls, final Class<?> cls2) {
        final String simpleName = cls2.getSimpleName();
        final Root root = cls2.getAnnotation(Root.class);
        if (null == root) {
            return null;
        }
        final String name = root.name();
        return !this.isEmpty(name) ? name : Reflector.getName(simpleName);
    }

    public String getName() throws Exception {
        return !label.isInline() ? this.getDefault() : label.getEntry();
    }

    private String getDefault() throws Exception {
        final String override = label.getOverride();
        return !this.isEmpty(override) ? override : contact.getName();
    }

    public Expression getExpression() throws Exception {
        final String path = this.getPath();
        if (null != path) {
            return new PathParser(path, contact, format);
        }
        return new EmptyExpression(format);
    }

    public String getPath() throws Exception {
        final Path path = contact.getAnnotation(Path.class);
        if (null == path) {
            return null;
        }
        return path.value();
    }

    public boolean isEmpty(final String str) {
        return null == str || 0 == str.length();
    }

    public String toString() {
        return String.format("%s on %s", marker, contact);
    }
}
