package rx.internal.util.atomic;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

abstract class BaseLinkedAtomicQueue<E> extends AbstractQueue<E> {
    private final AtomicReference<LinkedQueueNode<E>> producerNode = new AtomicReference<>();
    private final AtomicReference<LinkedQueueNode<E>> consumerNode = new AtomicReference<>();
    protected final LinkedQueueNode<E> lvProducerNode() {
        return this.producerNode.get();
    }
    protected final LinkedQueueNode<E> lpProducerNode() {
        return this.producerNode.get();
    }
    protected final void spProducerNode(LinkedQueueNode<E> linkedQueueNode) {
        this.producerNode.lazySet(linkedQueueNode);
    }
    protected final LinkedQueueNode<E> lvConsumerNode() {
        return this.consumerNode.get();
    }
    protected final LinkedQueueNode<E> lpConsumerNode() {
        return this.consumerNode.get();
    }
    protected final void spConsumerNode(LinkedQueueNode<E> linkedQueueNode) {
        this.consumerNode.lazySet(linkedQueueNode);
    }
    public final Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }
    public final int size() {
        LinkedQueueNode<E> linkedQueueNodeLvNext;
        LinkedQueueNode<E> linkedQueueNodeLvConsumerNode = lvConsumerNode();
        LinkedQueueNode<E> linkedQueueNodeLvProducerNode = lvProducerNode();
        int i2 = 0;
        while (linkedQueueNodeLvConsumerNode != linkedQueueNodeLvProducerNode && i2 < Integer.MAX_VALUE) {
            do {
                linkedQueueNodeLvNext = linkedQueueNodeLvConsumerNode.lvNext();
            } while (linkedQueueNodeLvNext == null);
            i2++;
            linkedQueueNodeLvConsumerNode = linkedQueueNodeLvNext;
        }
        return i2;
    }
    public final boolean isEmpty() {
        return lvConsumerNode() == lvProducerNode();
    }
}
