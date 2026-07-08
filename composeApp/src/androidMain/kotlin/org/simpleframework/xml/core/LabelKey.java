package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;


class LabelKey {
    private final Class label;
    private final String name;
    private final Class owner;
    private final Class type;

    public LabelKey(final Contact contact, final Annotation annotation) {
        owner = contact.getDeclaringClass();
        label = annotation.annotationType();
        name = contact.getName();
        type = contact.type();
    }

    public int hashCode() {
        return name.hashCode() ^ owner.hashCode();
    }

    public boolean equals(final Object obj) {
        if (obj instanceof LabelKey) {
            return this.equals((LabelKey) obj);
        }
        return false;
    }

    private boolean equals(final LabelKey labelKey) {
        if (labelKey == this) {
            return true;
        }
        if (labelKey.label == label && labelKey.owner == owner && labelKey.type == type) {
            return labelKey.name.equals(name);
        }
        return false;
    }

    public String toString() {
        return String.format("key '%s' for %s", name, owner);
    }
}
