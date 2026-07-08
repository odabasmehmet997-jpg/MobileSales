package org.simpleframework.xml.core;

import java.util.*;

class ParameterMap extends LinkedHashMap<Object, Parameter> implements Iterable<Parameter> {
    public Iterator<Parameter> iterator() {
        return this.values().iterator();
    }
    public Parameter get(final int i2) {
        return this.getAll().get(i2);
    }
    public List<Parameter> getAll() {
        final Collection<Parameter> values = this.values();
        if (!values.isEmpty()) {
            return new ArrayList(values);
        }
        return Collections.emptyList();
    }
}
