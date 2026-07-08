package org.simpleframework.xml.stream;

import java.util.Iterator;
import java.util.LinkedHashMap;


class PrefixResolver extends LinkedHashMap<String, String> implements NamespaceMap {
    private final OutputNode source;

    public PrefixResolver(final OutputNode outputNode) {
        source = outputNode;
    }

    @Override // org.simpleframework.xml.stream.NamespaceMap
    public String getPrefix() {
        return source.getPrefix();
    }

    @Override // org.simpleframework.xml.stream.NamespaceMap
    public String setReference(final String str) {
        return this.setReference(str, "");
    }

    @Override // org.simpleframework.xml.stream.NamespaceMap
    public String setReference(final String str, final String str2) {
        if (null != resolvePrefix(str)) {
            return null;
        }
        return this.put(str, str2);
    }

    @Override // org.simpleframework.xml.stream.NamespaceMap
    public String getPrefix(final String str) {
        final String str2;
        return (0 >= size() || null == (str2 = get(str))) ? this.resolvePrefix(str) : str2;
    }

    @Override // org.simpleframework.xml.stream.NamespaceMap
    public String getReference(final String str) {
        if (this.containsValue(str)) {
            final Iterator<String> it = this.iterator();
            while (it.hasNext()) {
                final String next = it.next();
                final String str2 = this.get(next);
                if (null != str2 && str2.equals(str)) {
                    return next;
                }
            }
        }
        return this.resolveReference(str);
    }

    private String resolveReference(final String str) {
        final NamespaceMap namespaces = source.getNamespaces();
        if (null != namespaces) {
            return namespaces.getReference(str);
        }
        return null;
    }

    private String resolvePrefix(final String str) {
        final NamespaceMap namespaces = source.getNamespaces();
        if (null == namespaces) {
            return null;
        }
        final String prefix = namespaces.getPrefix(str);
        if (this.containsValue(prefix)) {
            return null;
        }
        return prefix;
    }

    @Override // org.simpleframework.xml.stream.NamespaceMap, java.lang.Iterable
    public Iterator<String> iterator() {
        return this.keySet().iterator();
    }
}
