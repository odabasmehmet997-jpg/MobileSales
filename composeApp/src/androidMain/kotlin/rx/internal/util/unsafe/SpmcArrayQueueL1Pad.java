package rx.internal.util.unsafe;

abstract class SpmcArrayQueueL1Pad<E> extends ConcurrentCircularArrayQueue<E> {
    public SpmcArrayQueueL1Pad(int i2) {
        super(i2);
    }
}
