package rx.internal.util.atomic;

import java.util.concurrent.atomic.AtomicReference;

public final class LinkedQueueNode<E> extends AtomicReference<LinkedQueueNode<E>> {
    private static final long serialVersionUID = 2404266111789071508L;
    private E value;
    public LinkedQueueNode() {
    }
    public LinkedQueueNode(E e2) {
        spValue(e2);
    }
    public E getAndNullValue() {
        E eLpValue = lpValue();
        spValue(null);
        return eLpValue;
    }
    public E lpValue() {
        return this.value;
    }
    public void spValue(E e2) {
        this.value = e2;
    }
    public void soNext(LinkedQueueNode<E> linkedQueueNode) {
        lazySet(linkedQueueNode);
    }
    public LinkedQueueNode<E> lvNext() {
        return get();
    }
}
