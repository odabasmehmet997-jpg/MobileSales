package org.simpleframework.xml.core;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;


class LabelMap extends LinkedHashMap<String, Label> implements Iterable<Label> {
    private final Policy policy;

    public LabelMap() {
        this(null);
    }

    public LabelMap(final Policy policy) {
        this.policy = policy;
    }

    @Override // java.lang.Iterable
    public Iterator<Label> iterator() {
        return this.values().iterator();
    }

    public Label getLabel(final String str) {
        return this.remove(str);
    }

    public String[] getKeys() throws Exception {
        final HashSet hashSet = new HashSet();
        final Iterator<Label> it = this.iterator();
        while (it.hasNext()) {
            final Label next = it.next();
            if (null != next) {
                final String path = next.getPath();
                final String name = next.getName();
                hashSet.add(path);
                hashSet.add(name);
            }
        }
        return this.getArray(hashSet);
    }

    public String[] getPaths() throws Exception {
        final HashSet hashSet = new HashSet();
        final Iterator<Label> it = this.iterator();
        while (it.hasNext()) {
            final Label next = it.next();
            if (null != next) {
                hashSet.add(next.getPath());
            }
        }
        return this.getArray(hashSet);
    }

    public LabelMap getLabels() throws Exception {
        final LabelMap labelMap = new LabelMap(policy);
        final Iterator<Label> it = this.iterator();
        while (it.hasNext()) {
            final Label next = it.next();
            if (null != next) {
                labelMap.put(next.getPath(), next);
            }
        }
        return labelMap;
    }

    private String[] getArray(final Set<String> set) {
        return set.toArray(new String[0]);
    }

    public boolean isStrict(final Context context) {
        if (null == this.policy) {
            return context.isStrict();
        }
        return context.isStrict() && policy.isStrict();
    }
}
