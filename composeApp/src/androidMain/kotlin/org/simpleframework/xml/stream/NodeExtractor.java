package org.simpleframework.xml.stream;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.LinkedList;


class NodeExtractor extends LinkedList<org.w3c.dom.Node> {
    public NodeExtractor(final Document document) {
        this.extract(document);
    }

    private void extract(final Document document) {
        final Element documentElement = document.getDocumentElement();
        if (null != documentElement) {
            this.offer(documentElement);
            this.extract(documentElement);
        }
    }

    private void extract(final org.w3c.dom.Node node) {
        final NodeList childNodes = node.getChildNodes();
        final int length = childNodes.getLength();
        for (int i2 = 0; i2 < length; i2++) {
            final org.w3c.dom.Node item = childNodes.item(i2);
            if (8 != item.getNodeType()) {
                this.offer(item);
                this.extract(item);
            }
        }
    }
}
