package org.simpleframework.xml.core;

import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.strategy.Type;


class Entry {
    private static final String DEFAULT_NAME = "entry";
    private final boolean attribute;
    private final Contact contact;
    private String entry;
    private String key;
    private Class keyType;
    private final ElementMap label;
    private String value;
    private Class valueType;
    public Entry(final Contact contact, final ElementMap elementMap) {
        attribute = elementMap.attribute();
        entry = elementMap.entry();
        value = elementMap.value();
        key = elementMap.key();
        this.contact = contact;
        label = elementMap;
    }
    public Contact getContact() {
        return contact;
    }
    public boolean isAttribute() {
        return attribute;
    }
    public boolean isInline() throws Exception {
        return this.attribute;
    }
    public Converter getKey(final Context context) throws Exception {
        final Type keyType = this.getKeyType();
        if (context.isPrimitive(keyType)) {
            return new PrimitiveKey(context, this, keyType);
        }
        return new CompositeKey(context, this, keyType);
    }
    public Converter getValue(final Context context) throws Exception {
        final Type valueType = this.getValueType();
        if (context.isPrimitive(valueType)) {
            return new PrimitiveValue(context, this, valueType);
        }
        return new CompositeValue(context, this, valueType);
    }
    protected Type getKeyType() throws Exception {
        if (null == this.keyType) {
            final Class keyType = label.keyType();
            this.keyType = keyType;
            if (keyType == Void.TYPE) {
                this.keyType = this.getDependent(0);
            }
        }
        return new ClassType(keyType);
    }
    protected Type getValueType() throws Exception {
        if (null == this.valueType) {
            final Class valueType = label.valueType();
            this.valueType = valueType;
            if (valueType == Void.TYPE) {
                this.valueType = this.getDependent(1);
            }
        }
        return new ClassType(valueType);
    }
    private Class getDependent(final int i2) throws Exception {
        final Class[] dependents = contact.getDependents();
        return (dependents.length >= i2 && 0 != dependents.length) ? dependents[i2] : Object.class;
    }
    public String getKey() throws Exception {
        final String str = key;
        if (null == str) {
            return str;
        }
        if (this.isEmpty(str)) {
            key = null;
        }
        return key;
    }
    public String getValue() throws Exception {
        final String str = value;
        if (null == str) {
            return str;
        }
        if (this.isEmpty(str)) {
            value = null;
        }
        return value;
    }
    public String getEntry() throws Exception {
        final String str = entry;
        if (null == str) {
            return str;
        }
        if (this.isEmpty(str)) {
            entry = Entry.DEFAULT_NAME;
        }
        return entry;
    }
    private boolean isEmpty(final String str) {
        return 0 == str.length();
    }
    public String toString() {
        return String.format("%s on %s", label, contact);
    }
}
