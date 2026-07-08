package org.simpleframework.xml.core;

import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.stream.OutputNode;

class Qualifier implements Decorator {
    private final NamespaceDecorator decorator = new NamespaceDecorator();
    public Qualifier(final Contact contact) {
        this.scan(contact);
    }
    public void decorate(final OutputNode outputNode) {
        decorator.decorate(outputNode);
    }
    public void decorate(final OutputNode outputNode, final Decorator decorator) {
        this.decorator.decorate(outputNode, decorator);
    }
    private void scan(final Contact contact) {
        this.namespace(contact);
        this.scope(contact);
    }
    private void namespace(final Contact contact) {
        final Namespace namespace = contact.getAnnotation(Namespace.class);
        if (null != namespace) {
            decorator.set(namespace);
            decorator.add(namespace);
        }
    }
    private void scope(final Contact contact) {
        final NamespaceList namespaceList = contact.getAnnotation(NamespaceList.class);
        if (null != namespaceList) {
            for (final Namespace namespace : namespaceList.value()) {
                decorator.add(namespace);
            }
        }
    }
}
