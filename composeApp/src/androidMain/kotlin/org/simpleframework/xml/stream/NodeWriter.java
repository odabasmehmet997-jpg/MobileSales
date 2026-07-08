package org.simpleframework.xml.stream;

import java.io.Writer;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


class NodeWriter {
    private final Set active;
    private final OutputStack stack;
    private final boolean verbose;
    private final Formatter writer;

    public NodeWriter(final Writer writer) {
        this(writer, new Format());
    }

    public NodeWriter(final Writer writer, final Format format) {
        this(writer, format, false);
    }

    private NodeWriter(final Writer writer, final Format format, final boolean z) {
        this.writer = new Formatter(writer, format);
        final HashSet hashSet = new HashSet();
        active = hashSet;
        stack = new OutputStack(hashSet);
        verbose = z;
    }

    public OutputNode writeRoot() throws Exception {
        final OutputDocument outputDocument = new OutputDocument(this, stack);
        if (stack.isEmpty()) {
            writer.writeProlog();
        }
        return outputDocument;
    }

    public boolean isRoot(final OutputNode outputNode) {
        return stack.bottom() == outputNode;
    }

    public boolean isCommitted(final OutputNode outputNode) {
        return !active.contains(outputNode);
    }

    public void commit(final OutputNode outputNode) throws Exception {
        if (stack.contains(outputNode)) {
            final OutputNode pVar = stack.top();
            if (!this.isCommitted(pVar)) {
                this.writeStart(pVar);
            }
            while (stack.top() != outputNode) {
                this.writeEnd(stack.pop());
            }
            this.writeEnd(outputNode);
            stack.pop();
        }
    }

    public void remove(final OutputNode outputNode) throws Exception {
        if (stack.top() != outputNode) {
            throw new NodeException("Cannot remove node");
        }
        stack.pop();
    }

    public OutputNode writeElement(final OutputNode outputNode, final String str) throws Exception {
        if (stack.isEmpty()) {
            return this.writeStart(outputNode, str);
        }
        if (!stack.contains(outputNode)) {
            return null;
        }
        final OutputNode pVar = stack.top();
        if (!this.isCommitted(pVar)) {
            this.writeStart(pVar);
        }
        while (stack.top() != outputNode) {
            this.writeEnd(stack.pop());
        }
        if (!stack.isEmpty()) {
            this.writeValue(outputNode);
        }
        return this.writeStart(outputNode, str);
    }

    private OutputNode writeStart(final OutputNode outputNode, final String str) throws Exception {
        final OutputElement outputElement = new OutputElement(outputNode, this, str);
        if (null == str) {
            throw new NodeException("Can not have a null name");
        }
        return stack.push(outputElement);
    }

    private void writeStart(final OutputNode outputNode) throws Exception {
        this.writeComment(outputNode);
        this.writeName(outputNode);
        this.writeAttributes(outputNode);
        this.writeNamespaces(outputNode);
    }

    private void writeComment(final OutputNode outputNode) throws Exception {
        final String comment = outputNode.getComment();
        if (null != comment) {
            writer.writeComment(comment);
        }
    }

    private void writeName(final OutputNode outputNode) throws Exception {
        final String prefix = outputNode.getPrefix(verbose);
        final String name = outputNode.getName();
        if (null != name) {
            writer.writeStart(name, prefix);
        }
    }

    private void writeValue(final OutputNode outputNode) throws Exception {
        Mode mode = outputNode.getMode();
        final String value = outputNode.getValue();
        if (null != value) {
            final Iterator<OutputNode> it = stack.iterator();
            while (it.hasNext()) {
                final OutputNode next = it.next();
                if (Mode.INHERIT != mode) {
                    break;
                } else {
                    mode = next.getMode();
                }
            }
            writer.writeText(value, mode);
        }
        outputNode.setValue(null);
    }

    private void writeEnd(final OutputNode outputNode) throws Exception {
        final String name = outputNode.getName();
        final String prefix = outputNode.getPrefix(verbose);
        if (null != outputNode.getValue()) {
            this.writeValue(outputNode);
        }
        if (null != name) {
            writer.writeEnd(name, prefix);
            writer.flush();
        }
    }

    private void writeAttributes(final OutputNode outputNode) throws Exception {
        final NodeMap<OutputNode> attributes = outputNode.getAttributes();
        for (final String str : attributes) {
            final OutputNode outputNode2 = attributes.get(str);
            writer.writeAttribute(str, outputNode2.getValue(), outputNode2.getPrefix(verbose));
        }
        active.remove(outputNode);
    }

    private void writeNamespaces(final OutputNode outputNode) throws Exception {
        final NamespaceMap namespaces = outputNode.getNamespaces();
        for (final String str : namespaces) {
            writer.writeNamespace(str, namespaces.getPrefix(str));
        }
    }
}
