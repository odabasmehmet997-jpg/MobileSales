package rx.internal.util.unsafe;

import java.util.AbstractQueue;

/*  INFO: compiled from: SpscUnboundedArrayQueue.java */

abstract class SpscUnboundedArrayQueueProducerFields<E> extends AbstractQueue<E> {
    protected long producerIndex;

    SpscUnboundedArrayQueueProducerFields() {
    }
}
