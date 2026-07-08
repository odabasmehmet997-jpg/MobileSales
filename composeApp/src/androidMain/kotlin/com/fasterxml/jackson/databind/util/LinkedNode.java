package com.fasterxml.jackson.databind.util;

public final class LinkedNode<T> {
    private LinkedNode<T> next;
    private final T value;

    public LinkedNode(final T t, final LinkedNode<T> linkedNode) {
        value = t;
        next = linkedNode;
    }

    public void linkNext(final LinkedNode<T> linkedNode) {
        if (null != this.next) {
            throw new IllegalStateException();
        }
        next = linkedNode;
    }

    public LinkedNode<T> next() {
        return next;
    }

    public T value() {
        return value;
    }

    public static <ST> boolean contains(LinkedNode<ST> linkedNode, final ST st) {
        while (null != linkedNode) {
            if (linkedNode.value() == st) {
                return true;
            }
            linkedNode = linkedNode.next();
        }
        return false;
    }
}
