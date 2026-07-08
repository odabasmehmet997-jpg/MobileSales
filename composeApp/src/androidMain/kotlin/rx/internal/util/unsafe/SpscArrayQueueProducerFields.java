package rx.internal.util.unsafe;

/*  INFO: compiled from: SpscArrayQueue.java */

abstract class SpscArrayQueueProducerFields<E> extends SpscArrayQueueL1Pad<E> {
    protected static final long P_INDEX_OFFSET = UnsafeAccess.addressOf(SpscArrayQueueProducerFields.class, "producerIndex");
    protected long producerIndex;

    public SpscArrayQueueProducerFields(int i2) {
        super(i2);
    }
}
