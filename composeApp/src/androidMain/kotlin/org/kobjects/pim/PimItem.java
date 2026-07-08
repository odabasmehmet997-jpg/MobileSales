package org.kobjects.pim;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Objects;
import java.util.Vector;

public abstract class PimItem {
    public static final int TYPE_STRING = 0;
    public static final int TYPE_STRING_ARRAY = 1;
    Hashtable fields = new Hashtable();
    public abstract int getArraySize(String str);
    public abstract String getType();
    protected PimItem() {
    }
    protected PimItem(final PimItem pimItem) {
        final Enumeration fields = pimItem.fields();
        while (fields.hasMoreElements()) {
            this.addField(new PimField((PimField) fields.nextElement()));
        }
    }
    public Enumeration fieldNames() {
        return fields.keys();
    }
    public void addField(final PimField pimField) {
        Vector vector = (Vector) fields.get(pimField.name);
        if (null == vector) {
            vector = new Vector();
            fields.put(pimField.name, vector);
        }
        vector.addElement(pimField);
    }
    public Enumeration fields() {
        final Vector vector = new Vector();
        final Enumeration fieldNames = this.fieldNames();
        while (fieldNames.hasMoreElements()) {
            final Enumeration fields = this.fields((String) fieldNames.nextElement());
            while (fields.hasMoreElements()) {
                vector.addElement(fields.nextElement());
            }
        }
        return vector.elements();
    }
    public Enumeration fields(final String str) {
        Vector vector = (Vector) fields.get(str);
        if (null == vector) {
            vector = new Vector();
        }
        return vector.elements();
    }
    public PimField getField(final String str, final int i2) {
        return (PimField) ((Vector) fields.get(str)).elementAt(i2);
    }
    public int getFieldCount(final String str) {
        final Vector vector = (Vector) fields.get(str);
        if (null == vector) {
            return 0;
        }
        return vector.size();
    }
    public int getType(final String str) {
        return -1 == getArraySize(str) ? 0 : 1;
    }
    public void removeField(final String str, final int i2) {
        (( Vector<?> ) Objects.requireNonNull(fields.get(str))).removeElementAt(i2);
    }
    public String toString() {
        return this.getType() + ":" + fields.toString();
    }
}
