package org.simpleframework.xml.core;

import java.util.Iterator;
import java.util.LinkedHashMap;


class Collector implements Criteria {
    private final Registry alias;
    private final Registry registry;

    public Collector() {
        registry = new Registry();
        alias = new Registry();
    }

    @Override // org.simpleframework.xml.core.Criteria
    public Variable get(final Object obj) {
        return registry.get(obj);
    }

    @Override // org.simpleframework.xml.core.Criteria
    public Variable get(final Label label) throws Exception {
        if (null == label) {
            return null;
        }
        return registry.get(label.getKey());
    }

    @Override // org.simpleframework.xml.core.Criteria
    public Variable resolve(final String str) {
        return alias.get(str);
    }

    @Override // org.simpleframework.xml.core.Criteria
    public Variable remove(final Object obj) throws Exception {
        return registry.remove(obj);
    }

    @Override // java.lang.Iterable
    public Iterator<Object> iterator() {
        return registry.iterator();
    }

    @Override // org.simpleframework.xml.core.Criteria
    public void set(final Label label, final Object obj) throws Exception {
        final Variable variable = new Variable(label, obj);
        if (null != label) {
            final String[] paths = label.getPaths();
            final Object key = label.getKey();
            for (final String str : paths) {
                alias.put(str, variable);
            }
            registry.put(key, variable);
        }
    }

    @Override // org.simpleframework.xml.core.Criteria
    public void commit(final Object obj) throws Exception {
        for (final Variable variable : registry.values()) {
            variable.getContact().set(obj, variable.getValue());
        }
    }

    private static class Registry extends LinkedHashMap<Object, Variable> {
        private Registry() {
        }

        public Iterator<Object> iterator() {
            return this.keySet().iterator();
        }
    }
}
