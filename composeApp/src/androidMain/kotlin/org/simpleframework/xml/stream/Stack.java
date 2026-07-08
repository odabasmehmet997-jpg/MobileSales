package org.simpleframework.xml.stream;

import java.util.ArrayList;


class Stack<T> extends ArrayList<T> {
    public Stack(final int i2) {
        super(i2);
    }

    public T pop() {
        final int size = this.size();
        if (0 >= size) {
            return null;
        }
        return this.remove(size - 1);
    }

    public T top() {
        final int size = this.size();
        if (0 >= size) {
            return null;
        }
        return this.get(size - 1);
    }

    public T bottom() {
        if (0 >= size()) {
            return null;
        }
        return this.get(0);
    }

    public T push(final T t) {
        this.add(t);
        return t;
    }
}
