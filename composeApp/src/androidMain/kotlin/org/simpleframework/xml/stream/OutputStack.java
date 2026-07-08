package org.simpleframework.xml.stream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;


class OutputStack extends ArrayList<OutputNode> {
    private final Set active;

    public OutputStack(final Set set) {
        active = set;
    }

    public OutputNode pop() {
        final int size = this.size();
        if (0 >= size) {
            return null;
        }
        return this.purge(size - 1);
    }

    public OutputNode top() {
        final int size = this.size();
        if (0 >= size) {
            return null;
        }
        return this.get(size - 1);
    }

    public OutputNode bottom() {
        if (0 >= size()) {
            return null;
        }
        return this.get(0);
    }

    public OutputNode push(final OutputNode outputNode) {
        active.add(outputNode);
        this.add(outputNode);
        return outputNode;
    }

    public OutputNode purge(final int i2) {
        final OutputNode remove = this.remove(i2);
        if (null != remove) {
            active.remove(remove);
        }
        return remove;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<OutputNode> iterator() {
        return new Sequence();
    }

    private class Sequence implements Iterator<OutputNode> {
        private int cursor;

        public Sequence() {
            cursor = size();
        }

        @Override // java.util.Iterator
        public OutputNode next() {
            if (!this.hasNext()) {
                return null;
            }
            final OutputStack outputStack = OutputStack.this;
            final int i2 = cursor - 1;
            cursor = i2;
            return outputStack.get(i2);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return 0 < this.cursor;
        }

        @Override // java.util.Iterator
        public void remove() {
            purge(cursor);
        }
    }
}
