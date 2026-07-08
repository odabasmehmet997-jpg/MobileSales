package org.simpleframework.xml.core;

import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.Style;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


class EmptyExpression implements Expression {
    private final List<String> list = new LinkedList();
    private final Style style;

    @Override // org.simpleframework.xml.core.Expression
    public String getFirst() {
        return null;
    }

    @Override // org.simpleframework.xml.core.Expression
    public int getIndex() {
        return 0;
    }

    @Override // org.simpleframework.xml.core.Expression
    public String getLast() {
        return null;
    }

    @Override // org.simpleframework.xml.core.Expression
    public Expression getPath(final int i2) {
        return null;
    }

    @Override // org.simpleframework.xml.core.Expression
    public Expression getPath(final int i2, final int i3) {
        return null;
    }

    @Override // org.simpleframework.xml.core.Expression
    public String getPrefix() {
        return null;
    }

    @Override // org.simpleframework.xml.core.Expression
    public boolean isAttribute() {
        return false;
    }

    @Override // org.simpleframework.xml.core.Expression
    public boolean isEmpty() {
        return true;
    }

    @Override // org.simpleframework.xml.core.Expression
    public boolean isPath() {
        return false;
    }

    public EmptyExpression(final Format format) {
        style = format.style();
    }

    @Override // java.lang.Iterable
    public Iterator<String> iterator() {
        return list.iterator();
    }

    @Override // org.simpleframework.xml.core.Expression
    public String getPath() {
        return "";
    }

    @Override // org.simpleframework.xml.core.Expression
    public String getElement(final String str) {
        return style.getElement(str);
    }

    @Override // org.simpleframework.xml.core.Expression
    public String getAttribute(final String str) {
        return style.getAttribute(str);
    }
}
