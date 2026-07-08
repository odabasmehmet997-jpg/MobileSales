package org.simpleframework.xml.core;

import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.stream.NamespaceMap;
import org.simpleframework.xml.stream.OutputNode;

import java.util.ArrayList;
import java.util.List;


class NamespaceDecorator implements Decorator {
    private Namespace primary;
    private final List<Namespace> scope = new ArrayList();

    public void set(final Namespace namespace) {
        if (null != namespace) {
            this.add(namespace);
        }
        primary = namespace;
    }

    public void add(final Namespace namespace) {
        scope.add(namespace);
    }

    @Override // org.simpleframework.xml.core.Decorator
    public void decorate(final OutputNode outputNode) {
        this.decorate(outputNode, null);
    }

    @Override // org.simpleframework.xml.core.Decorator
    public void decorate(final OutputNode outputNode, final Decorator decorator) {
        if (null != decorator) {
            decorator.decorate(outputNode);
        }
        this.scope(outputNode);
        this.namespace(outputNode);
    }

    private void scope(final OutputNode outputNode) {
        final NamespaceMap namespaces = outputNode.getNamespaces();
        for (final Namespace namespace : scope) {
            namespaces.setReference(namespace.reference(), namespace.prefix());
        }
    }

    private void namespace(final OutputNode outputNode) {
        final Namespace namespace = primary;
        if (null != namespace) {
            outputNode.setReference(namespace.reference());
        }
    }
}
